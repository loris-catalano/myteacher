package it.unisannio.gruppo3.entities;

import java.util.List;

public class Student {
    public Student(String firstName, String lastName, int lessonBonusPoints, List<Review> completedReviews, LessonsAgenda studentAgenda) {
        this.id = null;     //the id will be set by us when creating
        this.firstName = firstName;
        this.lastName = lastName;
        this.lessonBonusPoints = lessonBonusPoints;
        this.completedReviews = completedReviews;
        this.studentAgenda = studentAgenda;
    }
    public Student(String firstName, String lastName, int lessonBonusPoints, List<Review> completedReviews, LessonsAgenda studentAgenda, Long id) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.lessonBonusPoints = lessonBonusPoints;
        this.completedReviews = completedReviews;
        this.studentAgenda = studentAgenda;
    }

    public Student(){};

    public Long getId(){return id;}
    public void setId(Long id){this.id=id;}
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public int getLessonBonusPoints() {
        return lessonBonusPoints;
    }
    public void setLessonBonusPoints(int lessonBonusPoints) {
        this.lessonBonusPoints = lessonBonusPoints;
    }
    public List<Review> getCompletedReviews() {
        return completedReviews;
    }
    public void setCompletedReviews(List<Review> completedReviews) {
        this.completedReviews = completedReviews;
    }
    public LessonsAgenda getStudentAgenda() {
        return studentAgenda;
    }
    public void setStudentAgenda(LessonsAgenda studentAgenda) {
        this.studentAgenda = studentAgenda;
    }


    private Long id;
    private String firstName;
    private String lastName;
    private int lessonBonusPoints;
    private List<Review> completedReviews;
    private LessonsAgenda studentAgenda;
}

