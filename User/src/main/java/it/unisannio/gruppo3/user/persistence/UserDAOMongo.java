package it.unisannio.gruppo3.user.persistence;

import com.mongodb.DuplicateKeyException;
import com.mongodb.MongoWriteException;
import com.mongodb.client.*;


import com.mongodb.client.model.IndexOptions;
import com.mongodb.client.model.Indexes;
import it.unisannio.gruppo3.entities.LessonsAgenda;
import it.unisannio.gruppo3.entities.Review;
import it.unisannio.gruppo3.entities.User;
import org.bson.Document;


import com.mongodb.client.model.Filters;


import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class UserDAOMongo implements UserDAO{
    private static String host = System.getenv("MONGO_ADDRESS");
    private static String port = System.getenv("MONGO_PORT");

    private final MongoClient mongoClient;
    private final MongoDatabase database;
    private final MongoCollection<Document> usersCollection;

    /**
     * To always have a unique generated id I created a separate collection in MongoDB called
     * "highestId", where there is only one document that has the "hId" field that contains the highest id,
     * because we do them in progressive order so 1, 2, 3 etc. Every time a new user is created
     * we update this progressive number in the DB and the highest number is assigned to the new user
     */
    private Long highestID;


    public UserDAOMongo() {
        if (host == null) {
            host = "localhost";
        }
        if (port == null) {
            port = "27017";
        }

        String URI = "mongodb://" + host + ":" + port;
        this.mongoClient = MongoClients.create(URI);
        this.database = mongoClient.getDatabase(DATABASE_NAME);
        this.usersCollection = database.getCollection(COLLECTION_USERS);

        this.highestID = usersCollection.find(Filters.eq("_id","counter")).first().getLong(ELEMENT_HIGHEST_ID);
    }

    public boolean dropDB() {
        database.drop();
        return true;
    }

    private void updateHighestId(){
        // Define the update operation
        Document updateOperation = new Document("$set", new Document(ELEMENT_HIGHEST_ID, ++highestID));

        // Perform the update
        usersCollection.updateOne(Filters.eq("_id","counter"), updateOperation);
    }

    public Long createUser(User user){
        try {
            updateHighestId();
            user.setId(highestID);
            user.setPassword(user.getPassword()); //Sets the password as hashed
            Document userDocument = userToDocument(user);
            this.usersCollection.insertOne(userDocument);
            return highestID;
        } catch (MongoWriteException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    /**
     * Creates a new MongoDB document from a User object
     * @param user The user
     * @return The document
     */
    private Document userToDocument(User user) {
        return new Document()
                .append(ELEMENT_ID,user.getId())
                .append(ELEMENT_EMAIL,user.getEmail())
                .append(ELEMENT_PASSWORD,user.getPassword())
                .append(ELEMENT_ROLES,user.getRoles());
    }

    private User userFromDocument(Document document){
        if(document!=null)
            return new User(
                    document.getLong(ELEMENT_ID),
                    document.getString(ELEMENT_EMAIL),
                    document.getString(ELEMENT_PASSWORD),
                    document.getList(ELEMENT_ROLES, String.class)
            );
        else return null;
    }


    @Override
    public User getUser(Long id) {
        Document d = usersCollection.find(Filters.eq(ELEMENT_ID,id)).first();
        return userFromDocument(d);
    }

    @Override
    public ArrayList<User> getAllUsers() {
        ArrayList<User> users = new ArrayList<>();
        for(Document doc:usersCollection.find()){
            if(doc.containsKey(ELEMENT_HIGHEST_ID)) {continue;}
            users.add(userFromDocument(doc));
            System.out.println(users);
        }
        return users;
    }

    /**
     * In this version if the given user has an invalid id, the filter will
     * not find a user to update so nothing will happen
     *
     * @param user The user to update (with a set id)
     * @return The same user that the server updated in the db.
     */
    @Override
    public User updateUser(User user) {
        Document filter = new Document(ELEMENT_ID, user.getId());
        Document updateOperation = new Document("$set", userToDocument(user));
        usersCollection.updateOne(filter, updateOperation);

        return user;
    }

    @Override
    public boolean deleteUser(Long id) {
        return usersCollection.deleteOne(Filters.eq(ELEMENT_ID,id)).wasAcknowledged();
    }

    @Override
    public boolean closeConnection() {
        return false;
    }

    @Override
    public Long getNextId() {
        Long actualHighestID = usersCollection.find(Filters.eq("_id","counter")).first().getLong(ELEMENT_HIGHEST_ID);
        return actualHighestID+1;
    }
}
