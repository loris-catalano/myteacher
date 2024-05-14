package it.unisannio.gruppo3.entities;

import java.util.List;

public class LessonsAgenda {
    private List<Lesson> lessons;

    public List<Lesson> getLessons(){return lessons;}

    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }
}