package it.unisannio.gruppo3.entities;

public class Payment {
    public Payment(Double amount, Long id, Long teacherId, Long studentId){
        this.amount=amount;
        this.id= id;
        this.teacherId=teacherId;
        this.studentId=studentId;
    }


    public Double getAmount(){return amount;}

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public void setId(Long id) {this.id=id;
    }

    public Long getId(){return this.id;}

    public Long getTeacherId(){return this.teacherId;}

    public Long getStudentId(){return this.studentId;}



    private Double amount;
    private Long id;
    private Long teacherId;
    private Long studentId;



}
