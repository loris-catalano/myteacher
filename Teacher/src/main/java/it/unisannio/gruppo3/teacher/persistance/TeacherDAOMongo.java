package it.unisannio.gruppo3.teacher.persistance;

import com.mongodb.DuplicateKeyException;
import com.mongodb.MongoWriteException;
import com.mongodb.client.*;
import com.mongodb.client.model.IndexOptions;
import com.mongodb.client.model.Indexes;
import it.unisannio.gruppo3.entities.LessonsAgenda;
import it.unisannio.gruppo3.entities.*;
import org.bson.Document;
import com.mongodb.client.model.Filters;
import java.io.File;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;



public class TeacherDAOMongo implements TeacherDAO {

    private static String host = System.getenv("MONGO_ADDRESS");
    private static String port = System.getenv("MONGO_PORT");
    private final MongoClient mongoClient;
    private final MongoDatabase database;
    private final MongoCollection<Document> collection;

    private Long highestID;


    public TeacherDAOMongo(){
        if(host==null ){
            host="localhost";
        }
        if(port==null){
            port="27017";
        }
        String URI = "mongodb://" + host + ":" + port;
        this.mongoClient = MongoClients.create(URI);
        this.database = mongoClient.getDatabase(DATABASE_NAME);
        this.collection = database.getCollection(COLLECTION_TEACHERS);

        this.highestID = collection.find(Filters.eq("_id","counter")).first().getLong(ELEMENT_HIGHEST_ID);
    }



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
    public Teacher getTeacher(Long id) {
        Document d = collection.find(Filters.eq(ELEMENT_ID,id)).first();
        return teacherFromDocument(d);
    }

    public Teacher teacherFromDocument(Document d){
        if(d!=null) {
            return new Teacher(d.getLong(ELEMENT_ID),
                    d.getString(ELEMENT_FNAME),
                    d.getString(ELEMENT_LNAME),
                    d.getInteger(ELEMENT_AGE),
                    d.getBoolean(ELEMENT_PREMIUM),
                    (List<String>)d.get(COLLECTION_SUBJECTS),
                    d.getList(COLLECTION_RECEIVED_REVIEWS, Long.class),
                    d.getDouble(ELEMENT_LATITUDE),
                    d.getDouble(ELEMENT_LONGITUDE),
                    d.getLong(ELEMENT_AGENDA),
                    d.getString(ELEMENT_CURRICULUM),
                    (Time)d.get(ELEMENT_AVAILABLE_TIME_SLOT),
                    d.getString(ELEMENT_EMAIL),
                    d.getString(ELEMENT_CELL_NUMBER));
        }
        return null;
    }


    /**
     * Creates a new MongoDB document from a Student object
     * @param teacher
     * @return
     */
    private Document teacherToDocument(Teacher teacher) {
        return new Document()
                .append(ELEMENT_ID, teacher.getId())
                .append(ELEMENT_FNAME, teacher.getFirstName())
                .append(ELEMENT_LNAME, teacher.getLastName())
                .append(ELEMENT_AGE, teacher.getAge())
                .append(ELEMENT_PREMIUM, teacher.isPremium())
                .append (COLLECTION_SUBJECTS,teacher.getSubjects())
                .append(COLLECTION_RECEIVED_REVIEWS,teacher.getReceivedReviews())
                .append(ELEMENT_LATITUDE, teacher.getLatitude())
                .append(ELEMENT_LONGITUDE, teacher.getLongitude())
                .append(ELEMENT_AGENDA, teacher.getTeacherAgenda())
                .append(ELEMENT_CURRICULUM, teacher.getResume())
                .append(ELEMENT_AVAILABLE_TIME_SLOT,teacher.getAvailableTimeSlot())
                .append(ELEMENT_EMAIL,teacher.getEmail())
                .append(ELEMENT_CELL_NUMBER,teacher.getCellNumber());

         }


    @Override
    public Long createTeacher(Teacher teacher) {
        try {
            updateHighestId();
            teacher.setId(highestID);
            Document teacherDocument = teacherToDocument(teacher);
            this.collection.insertOne(teacherDocument);
           return highestID;
        } catch (MongoWriteException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<Teacher> getTeachers() {
        return null;
    }



    @Override
    public Teacher updateTeacher(Teacher teacher) {
    Document filter = new Document(ELEMENT_ID, teacher.getId());
    Document update = teacherToDocument(teacher);

    collection.updateOne(filter, new Document("$set", update));

    return teacher;
    }
//

    @Override
    public boolean deleteTeacher(Long id) {
        return collection.deleteOne(Filters.eq(ELEMENT_ID,id)).wasAcknowledged();}

    @Override
    public boolean closeConnection() {
        return false;
    }



    @Override
    public ArrayList<Teacher> getTeachersByAge(int age) {

        ArrayList<Teacher> teachers = new ArrayList<>();

        for(Document doc : collection.find(Filters.eq("age", age))) {

            Teacher teacher = teacherFromDocument(doc);
            teachers.add(teacher);

        }

        return teachers;

    }

    @Override
    public ArrayList<Teacher> getTeachersByAgeGte(int age) {
        ArrayList<Teacher> teachers = new ArrayList<>();

        for(Document doc : collection.find(Filters.gte("age", age))) {

            Teacher teacher = teacherFromDocument(doc);
            teachers.add(teacher);
        }
        return teachers;
    }

    @Override
    public ArrayList<Teacher> getTeachersByAgeLte(int age) {
        ArrayList<Teacher> teachers=new ArrayList<>();
        for(Document doc:collection.find(Filters.lte("age", age))){
            Teacher teacher=teacherFromDocument((doc));
                    teachers.add(teacher);
        }
        return teachers;
    }

    @Override
    public ArrayList<Teacher> getTeachersBySubjects(String subject) {
            ArrayList<Teacher> teachers = new ArrayList<>();

            for(Document doc : collection.find(Filters.eq("subjects", subject))) {
                Teacher teacher = teacherFromDocument(doc);
                teachers.add(teacher);
            }

            return teachers;
        }

    @Override
    public Teacher getTeacherByEmail(String email) {
        Document d = collection.find(Filters.eq(ELEMENT_EMAIL,email)).first();
        return teacherFromDocument(d);
    }

    @Override
    public ArrayList<Teacher> getAllTeachers() {
        ArrayList<Teacher> teachers = new ArrayList<>();
        for(Document doc:collection.find()){
            if(doc.containsKey(ELEMENT_HIGHEST_ID)) {continue;}
            teachers.add(teacherFromDocument(doc));
        }
        return teachers;
    }

}


