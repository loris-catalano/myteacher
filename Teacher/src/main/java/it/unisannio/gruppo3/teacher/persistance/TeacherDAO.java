package it.unisannio.gruppo3.teacher.persistance;

import it.unisannio.gruppo3.entities.Teacher;

import java.util.ArrayList;

public interface TeacherDAO {
    String DATABASE_NAME = "myteacher";
    String COLLECTION_TEACHERS = "Teachers";


    String ELEMENT_FNAME = "firstName";
    String ELEMENT_LNAME = "lastName";
    String ELEMENT_AGE="age";
    String ELEMENT_PREMIUM="premiumAcc";
    String COLLECTION_SUBJECTS = "subjects";
    String COLLECTION_RECEIVED_REVIEWS = "receivedReviews";
    String ELEMENT_LOCATION = "pos";
    String ELEMENT_ID = "_id";
    String ELEMENT_AGENDA = "agenda";
    String ELEMENT_CURRICULUM="curriculum";
    String ELEMENT_AVAILABLE_TIME_SLOT="availableTimeSlot";
    String ELEMENT_EMAIL="email";
    String ELEMENT_NROCELL="nroCell";

    String ELEMENT_HIGHEST_ID = "highest";


    boolean dropDB();

    Teacher getTeacher(Long id) ;

    Long createTeacher(Teacher teacher);

    ArrayList<Teacher> getTeachers();

    Teacher updateTeacher(Teacher teacher);

    boolean deleteTeacher(Long id);

    boolean closeConnection();

    ArrayList<Teacher> getTeachersByAge(int age);

    ArrayList<Teacher> getTeachersByAgeGte(int age);

    ArrayList<Teacher> getTeachersByAgeLte(int age);

    ArrayList<Teacher> getTeachersBySubjects(String subject);



}
