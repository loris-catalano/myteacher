package it.unisannio.gruppo3.entities;

import java.util.ArrayList;

public class LessonsAgenda {
    private Long id;
    private ArrayList<Long> lessons;

    public LessonsAgenda(){
        lessons=new ArrayList<>();
    }

    public LessonsAgenda(ArrayList<Long> lessons){
        this.lessons=lessons;
    }

    public LessonsAgenda(Long id, ArrayList<Long> lessons){
        this.id = id;
        this.lessons=lessons;
    }

    public ArrayList<Long> getLessons(){return lessons;}

    public void setLessons(ArrayList<Long> lessons) {
        this.lessons = lessons;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
