package it.unisannio.gruppo3.lesson.Persistence;

import it.unisannio.gruppo3.entities.Lesson;
import it.unisannio.gruppo3.entities.Student;

import java.util.ArrayList;

public interface LessonDAO {
    String DATABASE_NAME = "myteacher";
    String COLLECTION_LESSONS = "Lessons";

    String ELEMENT_DURATION="duration";
    String ELEMENT_LESSON_START="startLesson";
    String ELEMENT_SUBJECT="subject";
    String ELEMENT_PRICE="price";
    String ELEMENT_STUDENT_ID="studentId";
    String ELEMENT_TEACHER_ID="teacherId";
    String ELEMENT_ID = "_id";

    String ELEMENT_HIGHEST_ID = "highest";


    boolean dropDB();

    Long createLesson(Lesson lesson);

    Lesson getLesson(Long id);

    Lesson updateLesson(Lesson lesson);

    boolean deleteLesson(Long id);

    boolean closeConnection();

    ArrayList<Lesson> getAllLessons();

    Long getNextId();
}
