package it.unisannio.gruppo3.lesson.Persistence;


import com.mongodb.DuplicateKeyException;
import com.mongodb.MongoWriteException;
import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.IndexOptions;
import com.mongodb.client.model.Indexes;
import it.unisannio.gruppo3.entities.*;
import org.bson.Document;

import java.util.Date;


public class LessonDAOMongo implements LessonDAO {

    private static String host = System.getenv("MONGO_ADDRESS");
    private static String port = System.getenv("MONGO_PORT");

    private final MongoClient mongoClient;
    private final MongoDatabase database;
    private final MongoCollection<Document> lessonsCollection;

    /**
     * To always have a unique generated id I created a separate collection in MongoDB called
     * "highestId", where there is only one document that has the "hId" field that contains the highest id,
     * because we do them in progressive order so 1, 2, 3 etc. Every time a new user is created
     * we update this progressive number in the DB and the highest number is assigned to the new user
     */
    private Long highestID;


    public LessonDAOMongo() {
        if (host == null) {
            host = "localhost";
        }
        if (port == null) {
            port = "27017";
        }

        String URI = "mongodb://" + host + ":" + port;
        this.mongoClient = MongoClients.create(URI);
        this.database = mongoClient.getDatabase(DATABASE_NAME);
        this.lessonsCollection = database.getCollection(COLLECTION_LESSONS);

        this.highestID = lessonsCollection.find(Filters.exists(ELEMENT_HIGHEST_ID)).first().getLong(ELEMENT_HIGHEST_ID);

        this.createDB();
    }


    public boolean dropDB() {
        database.drop();
        return true;
    }

    public boolean createDB() {
        try {
            IndexOptions indexOptions = new IndexOptions().unique(true);
            String resultCreateIndex = this.lessonsCollection.createIndex(Indexes.ascending(ELEMENT_ID), indexOptions);
        } catch (DuplicateKeyException e) {
            System.out.printf("duplicate field values encountered, couldn't create index: \t%s\n", e);
            return false;
        }
        return true;
    }



    private void updateHighestId(){
        // Define the filter to match the document to update. In this case we search for documents that have a field "ELEMENT_HIGHEST_ID"
        Document filter = new Document(ELEMENT_HIGHEST_ID, new Document("$exists", true));

        // Define the update operation
        Document updateOperation = new Document("$set", new Document(ELEMENT_HIGHEST_ID, ++highestID));

        // Perform the update
        lessonsCollection.updateOne(filter, updateOperation);
    }

    public Long createLesson(Lesson lesson){
        try {
            updateHighestId();
            lesson.setId(highestID);
            Document lessonDocument = lessonToDocument(lesson);
            this.lessonsCollection.insertOne(lessonDocument);
            return highestID;
        } catch (MongoWriteException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Lesson getLesson(Long id) {
        Document d = lessonsCollection.find(Filters.eq(ELEMENT_ID,id)).first();
        return lessonFromDocument(d);
    }

    @Override
    public Lesson updateLesson(Lesson lesson) {
        Document filter = new Document(ELEMENT_ID, lesson.getId());
        Document updateOperation = new Document("$set", lessonToDocument(lesson));
        lessonsCollection.updateOne(filter, updateOperation);

        return lesson;
    }

    @Override
    public boolean deleteLesson(Long id) {
        return lessonsCollection.deleteOne(Filters.eq(ELEMENT_ID,id)).wasAcknowledged();
    }

    @Override
    public boolean closeConnection() {
        return false;
    }

    /**
     * Creates a new MongoDB document from a Student object
     * @param lesson The lesson
     * @return The document
     */
    public static Document lessonToDocument(Lesson lesson) {
        return new Document()
                .append(ELEMENT_ID, lesson.getId())
                .append(ELEMENT_TEACHER_AGENDA, lesson.getTeacherAgenda())
                .append(ELEMENT_STUDENT_AGENDA, lesson.getStudentAgenda())
                .append(ELEMENT_STUDENT_ID, lesson.getStudentId())
                .append(ELEMENT_TEACHER_ID, lesson.getTeacherId())
                .append(ELEMENT_SUBJECT, lesson.getSubject())
                .append(ELEMENT_PRICE, lesson.getPrice())
                .append(ELEMENT_DURATION,lesson.getDuration())
                .append(ELEMENT_LESSON_START,lesson.getStartLesson());


    }

    public static Lesson lessonFromDocument(Document document) {
        if (document != null)
            return new Lesson(
                    document.getLong(ELEMENT_ID),
                    document.getInteger(ELEMENT_PRICE),
                    document.getString(ELEMENT_SUBJECT),
                    document.getLong(ELEMENT_TEACHER_ID),
                    (Date)document.get(ELEMENT_LESSON_START),
                    document.getInteger(ELEMENT_DURATION),
                    document.getLong(ELEMENT_STUDENT_ID),
                    (LessonsAgenda) document.get(ELEMENT_STUDENT_AGENDA),
                    (LessonsAgenda) document.get(ELEMENT_TEACHER_AGENDA)
            );
        else
            return null;
    }


}
