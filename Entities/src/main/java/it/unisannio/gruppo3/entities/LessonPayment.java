package it.unisannio.gruppo3.entities;

public class LessonPayment extends Payment {
    private Lesson lesson;
    //maybe these two below are not necessary
    private Student student;
    private Teacher teacher;

    public LessonPayment(Double amount, Lesson lesson, Student student, Teacher teacher) {
        super(amount);
        this.lesson = lesson;
        this.student = student;
        this.teacher = teacher;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
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
}
