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
    private LessonsAgenda studentAgenda;
    private LessonsAgenda teacherAgenda;

    public Lesson(Long id,int price, String subject, Long teacherId, Date startLesson,int duration,Long studentId, LessonsAgenda studentAgenda, LessonsAgenda teacherAgenda) {
        this.id=id;
        this.price = price;
        this.subject = subject;
        this.teacherId = teacherId;
        this.startLesson=startLesson;
        this.duration=duration;
        this.studentId = studentId;
        this.studentAgenda = studentAgenda;
        this.teacherAgenda = teacherAgenda;
    }
    public Lesson (){}

    public Lesson(int price, String subject, Long teacherId, Date startLesson,int duration, Long studentId, LessonsAgenda studentAgenda, LessonsAgenda teacherAgenda) {
        this.id=null;
        this.price = price;
        this.subject = subject;
        this.teacherId = teacherId;
        this.startLesson=startLesson;
        this.duration=duration;
        this.studentId = studentId;
        this.studentAgenda = studentAgenda;
        this.teacherAgenda = teacherAgenda;
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


    public LessonsAgenda getStudentAgenda() {
        return studentAgenda;
    }

    public void setStudentAgenda(LessonsAgenda studentAgenda) {
        this.studentAgenda = studentAgenda;
    }

    public LessonsAgenda getTeacherAgenda() {
        return teacherAgenda;
    }

    public void setTeacherAgenda(LessonsAgenda teacherAgenda) {
        this.teacherAgenda = teacherAgenda;
    }

    public Long getId(){
        return this.id;
    }
    public void setId(Long id){
        this.id=id;
    }
}

