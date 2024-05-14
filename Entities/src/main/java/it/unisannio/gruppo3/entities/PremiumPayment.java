package it.unisannio.gruppo3.entities;

public class PremiumPayment extends Payment {
    public PremiumPayment(Double amount, Teacher teacher){
        super(amount);
        this.teacher = teacher;
    }

    public Teacher getTeacher(){return teacher;}

    private Teacher teacher;
}
