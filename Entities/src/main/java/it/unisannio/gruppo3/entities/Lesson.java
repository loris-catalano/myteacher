package it.unisannio.gruppo3.entities;

import java.util.Date;

import java.util.Date;
import java.util.LongSummaryStatistics;

public class Lesson {
    private Long id;
    private int price;
    private String subject;
    private Long teacherId;
    private Date startLesson;
    private int duration;
    private Long studentId;

    public Lesson(Long id,int price, String subject, Long teacherId, Date startLesson,int duration,Long studentId) {
        this.id=id;
        this.price = price;
        this.subject = subject;
        this.teacherId = teacherId;
        this.startLesson=startLesson;
        this.duration=duration;
        this.studentId = studentId;
    }
    public Lesson (){}

    public Lesson(int price, String subject, Long teacherId, Date startLesson,int duration, Long studentId) {
        this.id=null;
        this.price = price;
        this.subject = subject;
        this.teacherId = teacherId;
        this.startLesson=startLesson;
        this.duration=duration;
        this.studentId = studentId;
    }
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    //public Teacher getTeacher() {return teacher;}

    //public void setTeacher(Teacher teacher) {this.teacher = teacher;}

    public Date getStartLesson() {
        return this.startLesson;
    }

    public void setStartLesson(Date startLesson) {
        this.startLesson=startLesson;
    }

    public int getDuration(){return this.duration;}


    public Long getStudentId() {
        return this.studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getTeacherId() {
        return this.teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId=teacherId;
    }


    public Long getId(){
        return this.id;
    }
    public void setId(Long id){
        this.id=id;
    }
}

