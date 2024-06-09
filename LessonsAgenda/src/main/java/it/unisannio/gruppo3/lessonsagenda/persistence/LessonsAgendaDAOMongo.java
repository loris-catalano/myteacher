package it.unisannio.gruppo3.lessonsagenda.persistence;

import com.mongodb.DuplicateKeyException;
import com.mongodb.MongoWriteException;
import com.mongodb.client.*;

import com.mongodb.client.model.IndexOptions;
import com.mongodb.client.model.Indexes;
import com.mongodb.client.result.UpdateResult;
import it.unisannio.gruppo3.entities.LessonsAgenda;
import org.bson.Document;

import com.mongodb.client.model.Filters;

import java.util.ArrayList;

public class LessonsAgendaDAOMongo implements LessonsAgendaDAO{
    private static String host = System.getenv("MONGO_ADDRESS");
    private static String port = System.getenv("MONGO_PORT");

    private final MongoClient mongoClient;
    private final MongoDatabase database;
    private final MongoCollection<Document> lessonsAgendasCollection;

    private Long highestID;

    public LessonsAgendaDAOMongo() {
        if (host == null) {
            host = "localhost";
        }
        if (port == null) {
            port = "27017";
        }

        String URI = "mongodb://" + host + ":" + port;
        this.mongoClient = MongoClients.create(URI);
        this.database = mongoClient.getDatabase(DATABASE_NAME);
        this.lessonsAgendasCollection = database.getCollection(COLLECTION_LESSONS_AGENDAS);

        this.highestID = lessonsAgendasCollection.find(Filters.eq("_id","counter")).first().getLong(ELEMENT_HIGHEST_ID);
    }

    public boolean dropDB() {
        database.drop();
        return true;
    }


    private void updateHighestId(){
        // Define the update operation
        Document updateOperation = new Document("$set", new Document(ELEMENT_HIGHEST_ID, ++highestID));

        // Perform the update
        //lessonsAgendasCollection.updateOne(filter, updateOperation);
        lessonsAgendasCollection.updateOne(Filters.eq("_id","counter"), updateOperation);
    }

    public Long createLessonsAgenda(LessonsAgenda lessonsAgenda){
        try {
            updateHighestId();
            lessonsAgenda.setId(highestID);
            System.out.println(lessonsAgenda.getId());
            Document lessonsAgendaDocument = lessonsAgendaToDocument(lessonsAgenda);
            this.lessonsAgendasCollection.insertOne(lessonsAgendaDocument);
            return highestID;
        } catch (MongoWriteException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Creates a new MongoDB document from a LessonsAgenda object
     * @param lessonsAgenda The lessonsAgenda
     * @return The document
     */
    private Document lessonsAgendaToDocument(LessonsAgenda lessonsAgenda) {
        return new Document()
                .append(ELEMENT_ID, lessonsAgenda.getId())
                .append(ELEMENT_LESSONS, lessonsAgenda.getLessons());
    }

    private LessonsAgenda lessonsAgendaFromDocument(Document document){
        return new LessonsAgenda(
                document.getLong(ELEMENT_ID),
                (ArrayList<Long>) document.get(ELEMENT_LESSONS)
        );
    }

    @Override
    public LessonsAgenda getLessonsAgenda(Long id) {
        Document d = lessonsAgendasCollection.find(Filters.eq(ELEMENT_ID,id)).first();
        if(d!=null) return lessonsAgendaFromDocument(d);
        else{
            return null;
        }
    }

    @Override
    public ArrayList<LessonsAgenda> getAllLessonsAgendas() {
        ArrayList<LessonsAgenda> lessonsAgendas = new ArrayList<>();
        for(Document doc:lessonsAgendasCollection.find()){
            if(doc.containsKey(ELEMENT_HIGHEST_ID)) {continue;}
            lessonsAgendas.add(lessonsAgendaFromDocument(doc));
        }
        return lessonsAgendas;
    }

    /**
     * In this version if the given lessonsAgenda has an invalid id, the filter will
     * not find a lessonsAgenda to update so nothing will happen
     *
     * @param lessonsAgenda The lessonsAgenda to update (with a set id)
     * @return The same lessonsAgenda that the server updated in the db.
     */
    @Override
    public LessonsAgenda updateLessonsAgenda(LessonsAgenda lessonsAgenda) {
        System.out.println("id:"+lessonsAgenda.getId());
        Document filter = new Document(ELEMENT_ID, lessonsAgenda.getId());
        Document updateOperation = new Document("$set", lessonsAgendaToDocument(lessonsAgenda));
        UpdateResult res = lessonsAgendasCollection.updateOne(filter, updateOperation);
        System.out.println("Filter:"+filter+"\nupdate:"+updateOperation+"\nres:"+res);

        return lessonsAgenda;
    }

    @Override
    public boolean deleteLessonsAgenda(Long id) {
        return lessonsAgendasCollection.deleteOne(Filters.eq(ELEMENT_ID,id)).wasAcknowledged();
    }

    @Override
    public boolean closeConnection() {
        return false;
    }

}
