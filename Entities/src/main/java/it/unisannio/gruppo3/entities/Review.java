package it.unisannio.gruppo3.entities;

public class Review {
    public Review(int stars, Student student, Teacher teacher) {
        this.stars = stars;
        this.student = student;
        this.teacher = teacher;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
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

    private int stars;
    private Student student;
    private Teacher teacher;
}

