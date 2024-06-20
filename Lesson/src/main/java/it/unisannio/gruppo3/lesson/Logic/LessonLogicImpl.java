package it.unisannio.gruppo3.lesson.Logic;

import it.unisannio.gruppo3.entities.Lesson;
import it.unisannio.gruppo3.entities.Student;
import it.unisannio.gruppo3.lesson.Persistence.LessonDAO;
import it.unisannio.gruppo3.lesson.Persistence.LessonDAOMongo;

import java.util.ArrayList;

public class LessonLogicImpl implements LessonLogic {

    LessonDAO lessonDAO;

    public LessonLogicImpl(){
        lessonDAO=new LessonDAOMongo();
    }

    @Override
    public Long createLesson(Lesson lesson) {
        return lessonDAO.createLesson(lesson);
    }

    @Override
    public Lesson getLesson(Long id) {
        return lessonDAO.getLesson(id);
    }

    @Override
    public Lesson updateLesson(Lesson lesson) {
       return lessonDAO.updateLesson(lesson);
    }

    @Override
    public boolean deleteLesson(Long id) {
        return lessonDAO.deleteLesson(id);
    }

    @Override
    public ArrayList<Lesson> getAllLessons() {
        return lessonDAO.getAllLessons();
    }
    @Override
    public Long getNextId(){return lessonDAO.getNextId();}
}
