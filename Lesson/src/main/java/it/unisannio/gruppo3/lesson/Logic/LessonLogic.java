package it.unisannio.gruppo3.lesson.Logic;

import it.unisannio.gruppo3.entities.Lesson;
import it.unisannio.gruppo3.entities.Student;

import java.util.ArrayList;

public interface LessonLogic {
    Long createLesson(Lesson lesson);

    Lesson getLesson(Long id);

    Lesson updateLesson(Lesson lesson);

    boolean deleteLesson(Long id);

    ArrayList<Lesson> getAllLessons();

    Long getNextId();
}
