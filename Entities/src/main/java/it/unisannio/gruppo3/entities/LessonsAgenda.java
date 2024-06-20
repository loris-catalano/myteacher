package it.unisannio.gruppo3.entities;

import java.util.ArrayList;
import java.util.Objects;

public class LessonsAgenda implements Comparable<LessonsAgenda>{
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

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LessonsAgenda lssAg = (LessonsAgenda) o;
        return Objects.equals(lessons, lssAg.lessons);
}


    @Override
    public int compareTo(LessonsAgenda lsnAg) {
        return Integer.compare(this.getLessons().size(),lsnAg.getLessons().size());}


public int hashCode() {
    final int PRIME = 5;
    int result = 1;
    result = PRIME * result + (id == null ? 0 : id.hashCode());;
    result = PRIME * result + this.getLessons().hashCode();
    return result;}}
