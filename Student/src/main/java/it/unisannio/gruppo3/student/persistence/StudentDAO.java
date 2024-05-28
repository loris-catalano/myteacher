package it.unisannio.gruppo3.student.persistence;

import it.unisannio.gruppo3.entities.Student;

import java.util.ArrayList;

public interface StudentDAO {

    String DATABASE_NAME = "myteacher";
    String COLLECTION_STUDENTS = "Students";


    String ELEMENT_FNAME = "firstName";
    String ELEMENT_LNAME = "lastName";
    String ELEMENT_LESSON_BONUS_POINTS = "lessonBonusPoints";
    String ELEMENT_COMPLETED_REVIEWS = "completedReviews";
    String ELEMENT_AGENDA = "agenda";
    String ELEMENT_ID = "id";
    String ELEMENT_EMAIL="email";
    String ELEMENT_NROCELL="nroCell";

    String ELEMENT_HIGHEST_ID = "highest";

    boolean dropDB();

    boolean createDB();

    Long createStudent(Student student);

    Student getStudent(Long id);

    ArrayList<Student> getAllStudents();

    ArrayList<Student> searchStudentsByFirstName(String fName);

    Student updateStudent(Student student);

    boolean deleteStudent(Long id);

    boolean closeConnection();
}
