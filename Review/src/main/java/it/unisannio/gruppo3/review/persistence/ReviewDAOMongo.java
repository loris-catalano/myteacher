package it.unisannio.gruppo3.review.persistence;

import com.mongodb.DuplicateKeyException;
import com.mongodb.MongoWriteException;
import com.mongodb.client.*;


import com.mongodb.client.model.IndexOptions;
import com.mongodb.client.model.Indexes;
import com.mongodb.client.result.UpdateResult;
import it.unisannio.gruppo3.entities.Review;
import org.bson.Document;


import com.mongodb.client.model.Filters;


import java.util.ArrayList;
import java.util.Date;

public class ReviewDAOMongo implements ReviewDAO{
    private static String host = System.getenv("MONGO_ADDRESS");
    private static String port = System.getenv("MONGO_PORT");

    private final MongoClient mongoClient;
    private final MongoDatabase database;
    private final MongoCollection<Document> reviewsCollection;

    /**
     * To always have a unique generated id I created a separate collection in MongoDB called
     * "highestId", where there is only one document that has the "hId" field that contains the highest id,
     * because we do them in progressive order so 1, 2, 3 etc. Every time a new user is created
     * we update this progressive number in the DB and the highest number is assigned to the new user
     */
    private Long highestID;


    public ReviewDAOMongo() {
        if (host == null) {
            host = "localhost";
        }
        if (port == null) {
            port = "27017";
        }

        String URI = "mongodb://" + host + ":" + port;
        this.mongoClient = MongoClients.create(URI);
        this.database = mongoClient.getDatabase(DATABASE_NAME);
        this.reviewsCollection = database.getCollection(COLLECTION_REVIEWS);

        this.highestID = reviewsCollection.find(Filters.eq("_id","counter")).first().getLong(ELEMENT_HIGHEST_ID);

        this.createDB();
    }

    public boolean dropDB() {
        database.drop();
        return true;
    }

    public boolean createDB() {
        try {
            IndexOptions indexOptions = new IndexOptions().unique(true);
            String resultCreateIndex = this.reviewsCollection.createIndex(Indexes.ascending(ELEMENT_REVIEW_ID), indexOptions);
        } catch (DuplicateKeyException e) {
            System.out.printf("duplicate field values encountered, couldn't create index: \t%s\n", e);
            return false;
        }
        return true;
    }

    private void updateHighestId(){
        // Define the update operation
        Document updateOperation = new Document("$set", new Document(ELEMENT_HIGHEST_ID, ++highestID));

        // Perform the update
        reviewsCollection.updateOne(Filters.eq("_id","counter"), updateOperation);
    }

    public Long createReview(Review review){
        try {
            updateHighestId();
            review.setReviewId(highestID);
            Document reviewDocument = reviewToDocument(review);
            this.reviewsCollection.insertOne(reviewDocument);
            return highestID;
        } catch (MongoWriteException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Creates a new MongoDB document from a Review object
     * @param review The review
     * @return The document
     */
    private Document reviewToDocument(Review review) {
        return new Document()
                .append(ELEMENT_REVIEW_ID, review.getReviewId())
                .append(ELEMENT_STARS, review.getStars())
                .append(ELEMENT_REVIEW_TITLE, review.getTitle())
                .append(ELEMENT_REVIEW_BODY, review.getBody())
                .append(ELEMENT_STUDENT_ID, review.getStudentId())
                .append(ELEMENT_TEACHER_ID, review.getTeacherId())
                .append(ELEMENT_CREATION_TIME, review.getCreationTime());
    }

    private Review reviewFromDocument(Document document){
        return new Review(
                document.getLong(ELEMENT_REVIEW_ID),
                document.getInteger(ELEMENT_STARS),
                document.getString(ELEMENT_REVIEW_TITLE),
                document.getString(ELEMENT_REVIEW_BODY),
                document.getLong(ELEMENT_STUDENT_ID),
                document.getLong(ELEMENT_TEACHER_ID),
                ((Date) document.get(ELEMENT_CREATION_TIME)).toInstant()
        );
    }

    @Override
    public ArrayList<Review>  getReviewsByStars(int stars){
        ArrayList<Review> reviews=new ArrayList<>();
        for(Document doc: reviewsCollection.find(Filters.eq("stars",stars))){
            Review review=reviewFromDocument(doc);
            reviews.add(review);
        }
        return reviews;
    }

    @Override
    public Review getReview(Long id) {
        Document d = reviewsCollection.find(Filters.eq(ELEMENT_REVIEW_ID,id)).first();
        return reviewFromDocument(d);
    }

    @Override
    public ArrayList<Review> getAllReviews() {
        ArrayList<Review> reviews = new ArrayList<>();
        for(Document doc:reviewsCollection.find()){
            if(doc.containsKey(ELEMENT_HIGHEST_ID)) {continue;}
            reviews.add(reviewFromDocument(doc));
            System.out.println(reviews);
        }
        return reviews;
    }


    /**
     * In this version if the given review has an invalid id, the filter will
     * not find a review to update so nothing will happen
     *
     * @param review The review to update (with a set id)
     * @return The same review that the server updated in the db.
     */
    @Override
    public Review updateReview(Review review) {
        Document filter = new Document(ELEMENT_REVIEW_ID, review.getReviewId());
        Document updateOperation = new Document("$set", reviewToDocument(review));
        UpdateResult res = reviewsCollection.updateOne(filter, updateOperation);

        return review;
    }

    @Override
    public boolean deleteReview(Long id) {
        return reviewsCollection.deleteOne(Filters.eq(ELEMENT_REVIEW_ID,id)).wasAcknowledged();
    }

    @Override
    public boolean closeConnection() {
        return false;
    }


}