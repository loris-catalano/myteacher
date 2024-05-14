package it.unisannio.gruppo3.entities;

import java.util.Date;

import java.util.Date;

public class Lesson {
    private int price;
    private String subject;
    private Teacher teacher;
    private Date date;
    private Student student;
    private LessonsAgenda studentAgenda;
    private LessonsAgenda teacherAgenda;

    public Lesson(int price, String subject, Teacher teacher, Date date, Student student, LessonsAgenda studentAgenda, LessonsAgenda teacherAgenda) {
        this.price = price;
        this.subject = subject;
        this.teacher = teacher;
        this.date = date;
        this.student = student;
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

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
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
}

