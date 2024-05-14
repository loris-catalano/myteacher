package it.unisannio.gruppo3.entities;

import java.io.File;
import java.sql.Time;
import java.util.List;


import java.util.List;
import java.sql.Time;

public class Teacher {

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

    public List<String> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<String> subjects) {
        this.subjects = subjects;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isPremium() {
        return premium;
    }

    public void setPremium(boolean premium) {
        this.premium = premium;
    }

    public List<Review> getReceivedReviews() {
        return receivedReviews;
    }

    public void setReceivedReviews(List<Review> receivedReviews) {
        this.receivedReviews = receivedReviews;
    }

    public Location getPosition() {
        return position;
    }

    public void setPosition(Location position) {
        this.position = position;
    }

    public Time getAvailableTimeSlot() {
        return availableTimeSlot;
    }

    public void setAvailableTimeSlot(Time availableTimeSlot) {
        this.availableTimeSlot = availableTimeSlot;
    }

    public LessonsAgenda getTeacherAgenda() {
        return teacherAgenda;
    }

    public void setTeacherAgenda(LessonsAgenda teacherAgenda) {
        this.teacherAgenda = teacherAgenda;
    }

    public File getResume() {
        return resume;
    }

    public void setResume(File resume) {
        this.resume = resume;
    }

    private String firstName;
    private String lastName;
    private List<String> subjects;
    private int age;
    private boolean premium;
    private List<Review> receivedReviews;
    private Location position;
    private Time availableTimeSlot;
    private LessonsAgenda teacherAgenda;
    private File resume;
}

