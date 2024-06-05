package it.unisannio.gruppo3.payment.Persistence;

import it.unisannio.gruppo3.entities.*;

import com.mongodb.DuplicateKeyException;
import com.mongodb.MongoWriteException;
import com.mongodb.client.*;


import com.mongodb.client.model.IndexOptions;
import com.mongodb.client.model.Indexes;
import org.bson.Document;


import com.mongodb.client.model.Filters;

import java.util.ArrayList;

public class PaymentDaoMongo implements PaymentDao {
private static String host = System.getenv("MONGO_ADDRESS");
private static String port = System.getenv("MONGO_PORT");
private final MongoClient mongoClient;
private final MongoDatabase database;
private final MongoCollection<Document> collection;

private Long highestID;


public PaymentDaoMongo(){
    if(host==null ){
        host="localhost";
    }
    if(port==null){
        port="27017";
    }
    String URI = "mongodb://" + host + ":" + port;
    this.mongoClient = MongoClients.create(URI);
    this.database = mongoClient.getDatabase(DATABASE_NAME);
    this.collection = database.getCollection(COLLECTION_PAYMENTS);

    this.highestID = collection.find(Filters.eq("_id","counter")).first().getLong(ELEMENT_HIGHEST_ID);
    //this.createDB();
}

/*
    @Override
    public boolean createDB() {
        try {
            IndexOptions indexOptions = new IndexOptions().unique(true);
            String resultCreateIndex = this.collection.createIndex(Indexes.ascending(ELEMENT_IDS), indexOptions);
        } catch (DuplicateKeyException e) {
            System.out.printf("duplicate field values encountered, couldn't create index: \t%s\n", e);
            return false;
        }
        return true;

    }*/

    @Override
    public boolean dropDB() {
        database.drop();
        return true;
    }

    private void updateHighestId(){
        // Define the update operation
        Document updateOperation = new Document("$set", new Document(ELEMENT_HIGHEST_ID, ++highestID));

        // Perform the update
        collection.updateOne(Filters.eq("_id","counter"), updateOperation);
    }



    @Override
    public Payment getPayment(Long id) {
        Document d = collection.find(Filters.eq(ELEMENT_ID,id)).first();
        return paymentFromDocument(d);
    }

    public Payment paymentFromDocument(Document d){
        if(d!=null) {
            return new Payment(
                    d.getDouble(ELEMENT_AMOUNT),
                    d.getLong(ELEMENT_ID),
                    d.getLong(ELEMENT_TEACHERID),
                    d.getLong(ELEMENT_STUDENTID));
        }
        return null;
    }


    /**
     * Creates a new MongoDB document from a Student object
     * @param payment
     * @return
     */
    private Document paymentToDocument(Payment payment) {
        return new Document()
                .append(ELEMENT_AMOUNT, payment.getAmount())
                .append(ELEMENT_ID, payment.getId())
                .append(ELEMENT_TEACHERID, payment.getTeacherId())
                .append(ELEMENT_STUDENTID, payment.getStudentId());


    }


    @Override
    public Long createPayment(Payment payment) {
        try {
            updateHighestId();
            payment.setId(highestID);
            Document paymentDocument = paymentToDocument(payment);
            this.collection.insertOne(paymentDocument);
            return highestID;
        } catch (MongoWriteException e) {
            e.printStackTrace();
        }
        return null;
    }

        @Override
        public boolean deletePayment(Long id) {
            return collection.deleteOne(Filters.eq(ELEMENT_ID,id)).wasAcknowledged();}

    @Override
    public ArrayList<Payment> getAllPayments() {{
        ArrayList<Payment> payments = new ArrayList<>();
        for(Document doc:collection.find()){
            if(doc.containsKey(ELEMENT_HIGHEST_ID)) {continue;}
            payments.add(paymentFromDocument(doc));
            System.out.println(payments);
        }
        return payments;
    }

}
@Override
public boolean closeConnection() {
    return false;
}


}

