package it.unisannio.gruppo3.student.persistence;

import com.mongodb.DuplicateKeyException;
import com.mongodb.MongoWriteException;
import com.mongodb.client.*;


import com.mongodb.client.model.IndexOptions;
import com.mongodb.client.model.Indexes;
import it.unisannio.gruppo3.entities.LessonsAgenda;
import it.unisannio.gruppo3.entities.Review;
import it.unisannio.gruppo3.entities.Student;
import org.bson.Document;


import com.mongodb.client.model.Filters;


import java.util.ArrayList;
import java.util.List;

public class StudentDAOMongo implements StudentDAO{
    private static String host = System.getenv("MONGO_ADDRESS");
    private static String port = System.getenv("MONGO_PORT");

    private final MongoClient mongoClient;
    private final MongoDatabase database;
    private final MongoCollection<Document> studentsCollection;

    /**
     * To always have a unique generated id I created a separate collection in MongoDB called
     * "highestId", where there is only one document that has the "hId" field that contains the highest id,
     * because we do them in progressive order so 1, 2, 3 etc. Every time a new user is created
     * we update this progressive number in the DB and the highest number is assigned to the new user
     */
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
        this.studentsCollection = database.getCollection(COLLECTION_STUDENTS);

        this.highestID = studentsCollection.find(Filters.exists(ELEMENT_HIGHEST_ID)).first().getLong(ELEMENT_HIGHEST_ID);

        this.createDB();
    }

    public boolean dropDB() {
        database.drop();
        return true;
    }

    public boolean createDB() {
        try {
            IndexOptions indexOptions = new IndexOptions().unique(true);
            String resultCreateIndex = this.studentsCollection.createIndex(Indexes.ascending(ELEMENT_ID), indexOptions);
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
        studentsCollection.updateOne(filter, updateOperation);
    }

    public Long createStudent(Student student){
        try {
            updateHighestId();
            student.setId(highestID);
            Document studentDocument = studentToDocument(student);
            this.studentsCollection.insertOne(studentDocument);
            return highestID;
        } catch (MongoWriteException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Creates a new MongoDB document from a Student object
     * @param student The student
     * @return The document
     */
    private Document studentToDocument(Student student) {
        return new Document()
                .append(ELEMENT_ID,student.getId())
                .append(ELEMENT_FNAME,student.getFirstName())
                .append(ELEMENT_LNAME, student.getLastName())
                .append(ELEMENT_LESSON_BONUS_POINTS, student.getLessonBonusPoints())
                .append(ELEMENT_COMPLETED_REVIEWS, student.getCompletedReviews())
                .append(ELEMENT_AGENDA, student.getStudentAgenda())
                .append(ELEMENT_EMAIL,student.getEmail())
                .append(ELEMENT_NROCELL,student.getNroCell());
    }

    private Student studentFromDocument(Document document){
        if(document!=null)
            return new Student(
                    document.getLong(ELEMENT_ID),
                    document.getString(ELEMENT_FNAME),
                    document.getString(ELEMENT_LNAME),
                    document.getInteger(ELEMENT_LESSON_BONUS_POINTS),
                    (List<Review>) document.get(ELEMENT_COMPLETED_REVIEWS),
                    (LessonsAgenda) document.get(ELEMENT_AGENDA),
                    document.getString(ELEMENT_EMAIL),
                    document.getString(ELEMENT_NROCELL)
                    );
        else return null;
    }


    @Override
    public Student getStudent(Long id) {
        Document d = studentsCollection.find(Filters.eq(ELEMENT_ID,id)).first();
        return studentFromDocument(d);
    }

    @Override
    public ArrayList<Student> getAllStudents() {
        ArrayList<Student> students = new ArrayList<>();
        for(Document doc:studentsCollection.find()){
            if(doc.containsKey(ELEMENT_HIGHEST_ID)) {continue;}
            students.add(studentFromDocument(doc));
            System.out.println(students);
        }
        return students;
    }

    @Override
    public ArrayList<Student> searchStudentsByFirstName(String fName) {
        return null;
    }

    /**
     * In this version if the given student has an invalid id, the filter will
     * not find a student to update so nothing will happen
     *
     * @param student The student to update (with a set id)
     * @return The same student that the server updated in the db.
     */
    @Override
    public Student updateStudent(Student student) {
        Document filter = new Document(ELEMENT_ID, student.getId());
        Document updateOperation = new Document("$set", studentToDocument(student));
        studentsCollection.updateOne(filter, updateOperation);

        return student;
    }

    @Override
    public boolean deleteStudent(Long id) {
        return studentsCollection.deleteOne(Filters.eq(ELEMENT_ID,id)).wasAcknowledged();
    }

    @Override
    public boolean closeConnection() {
        return false;
    }

    @Override
    public Long getNextId() {
        Document result = studentsCollection.find().sort(new Document(ELEMENT_ID, -1)).first();
        if (result == null) {
            return 1L;
        }
        return result.getLong(ELEMENT_ID) + 1;

    }


}
