package it.unisannio.gruppo3.entities;


import java.time.Instant;

public class Review {
    public Review(Long id, int stars, String title, String body, Student student, Teacher teacher, Instant creationTime) {
        this.id = id;
        this.stars = stars;
        this.title = title;
        this.body = body;
        this.student = student;
        this.teacher = teacher;
        this.creationTime = creationTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Instant getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Instant creationTime) {
        this.creationTime = creationTime;
    }

    private Long id;
    private int stars;
    private String title;
    private String body;
    private Student student;
    private Teacher teacher;
    private Instant creationTime;
}

