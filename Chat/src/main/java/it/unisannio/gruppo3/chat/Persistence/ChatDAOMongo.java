package it.unisannio.gruppo3.chat.Persistence;

import com.mongodb.MongoWriteException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.IndexOptions;
import com.mongodb.client.model.Indexes;
import it.unisannio.gruppo3.entities.Chat;

import it.unisannio.gruppo3.entities.Message;
import org.yaml.snakeyaml.constructor.DuplicateKeyException;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Date;

public class ChatDAOMongo implements ChatDAO{
    private Long highestID;
    private static String host = System.getenv("MONGO_ADDRESS");
    private static String port = System.getenv("MONGO_PORT");

    private final MongoClient mongoClient;
    private final MongoDatabase database;
    private final MongoCollection<Document> chatsCollection;

    public ChatDAOMongo(){
        if (host == null) {
            host = "localhost";
        }
        if (port == null) {
            port = "27017";
        }

        String URI = "mongodb://" + host + ":" + port;
        this.mongoClient = MongoClients.create(URI);
        this.database = mongoClient.getDatabase(DATABASE_NAME);
        this.chatsCollection = database.getCollection(COLLECTION_LESSONS);

        this.highestID = chatsCollection.find(Filters.eq("_id","counter")).first().getLong(ELEMENT_HIGHEST_ID);

        this.createDB();
    }

    @Override
    public boolean dropDB() {
        database.drop();
        return true;
    }

    @Override
    public boolean createDB() {
        try {
            IndexOptions indexOptions = new IndexOptions().unique(true);
            String resultCreateIndex = this.chatsCollection.createIndex(Indexes.ascending(ELEMENT_ID), indexOptions);
        } catch (DuplicateKeyException e) {
            System.out.printf("duplicate field values encountered, couldn't create index: \t%s\n", e);
            return false;
        }
        return true;
    }

    private void updateHighestId(){
        Document updateOperation = new Document("$set", new Document(ELEMENT_HIGHEST_ID, ++highestID));

        // Perform the update
        chatsCollection.updateOne(Filters.eq("_id","counter"), updateOperation);
    }

    @Override
    public Long createChat(Chat chat) {
        try {
            updateHighestId();
            chat.setId(highestID);
            Document chatDocument = chatToDocument(chat);
            this.chatsCollection.insertOne(chatDocument);
            return highestID;
        } catch (MongoWriteException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Chat getChat(Long id) {
        Document d = chatsCollection.find(Filters.eq(ELEMENT_ID,id)).first();
        return chatFromDocument(d);
    }

    @Override
    public Chat updateChat(Chat chat) {
        Document filter = new Document(ELEMENT_ID, chat.getId());
        Document updateOperation = new Document("$set", chatToDocument(chat));
        chatsCollection.updateOne(filter, updateOperation);

        return chat;
    }

    @Override
    public boolean deleteChat(Long id) {
        return chatsCollection.deleteOne(Filters.eq(ELEMENT_ID,id)).wasAcknowledged();
    }

    @Override
    public boolean closeConnection() {
        return false;
    }

    public static Document chatToDocument(Chat chat) {
        return new Document()
                .append(ELEMENT_ID, chat.getId())
                .append(ELEMENT_STUDENT_ID, chat.getStudentId())
                .append(ELEMENT_TEACHER_ID, chat.getTeacherId())
                .append(ELEMENT_MESSAGES, chat.getMessages());
    }

    public static Chat chatFromDocument(Document document) {
        if (document != null)
            return new Chat(
                    document.getLong(ELEMENT_ID),
                    document.getLong(ELEMENT_STUDENT_ID),
                    document.getLong(ELEMENT_TEACHER_ID),
                    (ArrayList<Message>) document.get(ELEMENT_MESSAGES)
            );
        else
            return null;
    }
}
