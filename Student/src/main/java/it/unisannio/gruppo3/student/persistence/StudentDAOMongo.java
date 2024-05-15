package it.unisannio.gruppo3.student.persistence;

import com.google.gson.*;
import com.mongodb.DuplicateKeyException;
import com.mongodb.MongoWriteException;
import com.mongodb.client.*;


import com.mongodb.client.model.IndexOptions;
import com.mongodb.client.model.Indexes;
import it.unisannio.gruppo3.entities.Lesson;
import it.unisannio.gruppo3.entities.LessonsAgenda;
import it.unisannio.gruppo3.entities.Review;
import it.unisannio.gruppo3.entities.Student;
import org.bson.Document;
import org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration;
import org.springframework.boot.json.GsonJsonParser;


import static com.mongodb.client.model.Filters.eq;


import java.util.ArrayList;
import java.util.List;

public class StudentDAOMongo implements StudentDAO{
    private static String host = System.getenv("MONGO_ADDRESS");
    private static String port = System.getenv("MONGO_PORT");

    private final MongoClient mongoClient;
    private final MongoDatabase database;
    private final MongoCollection<Document> collection;

    private Document highestIdDocument;
    private Long highestID;


    public StudentDAOMongo() {
        if (host == null) {
            host = "localhost";
        }
        if (port == null) {
            port = "27017";
        }

        String URI = "mongodb://" + host + ":" + port;
        this.mongoClient = MongoClients.create(URI);
        this.database = mongoClient.getDatabase(DATABASE_NAME);
        this.collection = database.getCollection(COLLECTION_STUDENTS);

        highestIdDocument = database.getCollection(COLLECTION_HIGHEST_ID).find().first();
        assert highestIdDocument != null;
        highestID = highestIdDocument.getLong(ELEMENT_HIGHEST_ID);

        this.createDB();
    }

    public boolean dropDB() {
        database.drop();
        return true;
    }

    public boolean createDB() {
        try {
            IndexOptions indexOptions = new IndexOptions().unique(true);
            String resultCreateIndex = this.collection.createIndex(Indexes.ascending(ELEMENT_ID), indexOptions);
//            System.out.println(String.format("Index created: %s", resultCreateIndex));
        } catch (DuplicateKeyException e) {
            System.out.printf("duplicate field values encountered, couldn't create index: \t%s\n", e);
            return false;
        }
        return true;
    }

    private void updateHighestId(){
        highestID = highestIdDocument.getLong(ELEMENT_HIGHEST_ID) + 1;      // Gets the highest id + 1
        highestIdDocument.put(ELEMENT_HIGHEST_ID, highestID);       // Puts the new highest id
    }

    public Long getNextId(){
        updateHighestId();
        return highestID;
    }

    public Long createStudent(Student student){
        try {
            updateHighestId();
            Long currentId = highestID;
            student.setId(currentId);
            
            Document studentDocument = studentToDocument(student);
            this.collection.insertOne(studentDocument);
            return student.getId();
        } catch (MongoWriteException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Creates a new MongoDB document from a Student object
     * @param student
     * @return
     */
    private Document studentToDocument(Student student) {
        return new Document()
                .append(ELEMENT_ID,student.getId())
                .append(ELEMENT_FNAME,student.getFirstName())
                .append(ELEMENT_LNAME, student.getLastName())
                .append(ELEMENT_LESSON_BONUS_POINTS, student.getLessonBonusPoints())
                .append(ELEMENT_COMPLETED_REVIEWS, student.getCompletedReviews())
                .append(ELEMENT_AGENDA, student.getStudentAgenda());
    }

    private Student studentFromDocument(Document document){
        if(document!=null)
            return new Student(
                    document.getLong(ELEMENT_ID),
                    document.getString(ELEMENT_FNAME),
                    document.getString(ELEMENT_LNAME),
                    document.getInteger(ELEMENT_LESSON_BONUS_POINTS),
                    (List<Review>) document.get(ELEMENT_COMPLETED_REVIEWS),
                    (LessonsAgenda) document.get(ELEMENT_AGENDA)
                    );
        else return null;




        /* GSON ATTEMPT (UNCOMPLETED)
        String studentJson = document.toJson();
        //System.out.println("prova stampa: " + studentJson);
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(studentJson, Student.class);
        */
    }


    @Override
    public Student getStudent(Long id) {
        Document d = collection.find(eq(ELEMENT_ID,id)).first();
        return studentFromDocument(d);
    }

    @Override
    public ArrayList<Student> getAllStudents() {
        return null;
    }

    @Override
    public ArrayList<Student> searchStudentsByFirstName(String fName) {
        return null;
    }

    @Override
    public Student updateStudent(Student student) {
        return null;
    }

    @Override
    public boolean deleteStudent(Long id) {
        return false;
    }

    @Override
    public boolean closeConnection() {
        return false;
    }

}
