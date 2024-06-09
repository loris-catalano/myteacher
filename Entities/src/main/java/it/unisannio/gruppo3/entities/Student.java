package it.unisannio.gruppo3.entities;

import java.util.List;
import java.util.Objects;

public class Student {
    public Student(String firstName, String lastName, int lessonBonusPoints, List<Long> completedReviews, Long studentAgenda,String email,String cellNumber) {
        this.id = null;//the id will be set by us when creating
        this.email=email;
        this.cellNumber=cellNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.lessonBonusPoints = lessonBonusPoints;
        this.completedReviews = completedReviews;
        this.studentAgenda = studentAgenda;
    }
    public Student(Long id, String firstName, String lastName, int lessonBonusPoints, List<Long> completedReviews, Long studentAgenda,String email,String cellNumber) {
        this.id = id;
        this.email=email;
        this.cellNumber=cellNumber;
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
    public List<Long> getCompletedReviews() {
        return completedReviews;
    }
    public void setCompletedReviews(List<Long> completedReviews) {
        this.completedReviews = completedReviews;
    }
    public Long getStudentAgenda() {
        return studentAgenda;
    }
    public void setStudentAgenda(Long studentAgenda) {
        this.studentAgenda = studentAgenda;
    }
    public String getCellNumber() {
        return this.cellNumber;
    }
    public String getEmail() {
        return this.email;
    }
    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", lessonBonusPoints=" + lessonBonusPoints +
                ", completedReviews=" + completedReviews +
                ", studentAgenda=" + studentAgenda +
                ", email='" + email + '\'' +
                ", cellNumber='" + cellNumber + '\'' +
                '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return lessonBonusPoints == student.lessonBonusPoints &&
                firstName.equals(student.firstName) &&
                lastName.equals(student.lastName) &&
                email.equals(student.email) &&
                cellNumber.equals(student.cellNumber) &&
                Objects.equals(id, student.id) &&
                Objects.equals(completedReviews, student.completedReviews) &&
                Objects.equals(studentAgenda, student.studentAgenda);
    }


    private Long id;
    private String firstName;
    private String lastName;
    private int lessonBonusPoints;
    private List<Long> completedReviews;
    private Long studentAgenda;
    private String email;
    private String cellNumber;
}

