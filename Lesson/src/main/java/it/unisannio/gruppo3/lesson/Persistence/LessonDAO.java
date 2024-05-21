package it.unisannio.gruppo3.lesson.Persistence;

import it.unisannio.gruppo3.entities.Lesson;

public interface LessonDAO {
    String DATABASE_NAME = "myteacher";
    String COLLECTION_LESSONS = "Lessons";

    String ELEMENT_DURATION="duration";
    String ELEMENT_LESSON_START="lessonStart";
    String ELEMENT_TEACHER_AGENDA="teacherAgenda";
    String ELEMENT_STUDENT_AGENDA="StudentAgenda";
//  String ELEMENT_STUDENT="student";
//  String ELEMENT_TEACHER="teacher";
    String ELEMENT_SUBJECT="subject";
    String ELEMENT_PRICE="price";
    String ELEMENT_STUDENT_ID="studentId";
    String ELEMENT_TEACHER_ID="teacherId";
    String ELEMENT_ID = "id";

    String ELEMENT_HIGHEST_ID = "highestId";


    boolean dropDB();

    boolean createDB();

    Long createLesson(Lesson lesson);

    Lesson getLesson(Long id);

    Lesson updateLesson(Lesson lesson);

    boolean deleteLesson(Long id);

    boolean closeConnection();
}
