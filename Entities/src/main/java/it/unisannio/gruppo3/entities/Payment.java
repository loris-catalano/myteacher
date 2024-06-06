package it.unisannio.gruppo3.entities;

public class Payment {
    public Payment(Long id, Double amount, Long lessonId){
        this.id= id;
        this.amount=amount;
        this.lessonId=lessonId;
    }


    public Double getAmount(){return amount;}

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public void setId(Long id) {this.id=id;
    }
    public Long getId(){return this.id;}

    public Long getLessonId() {
        return lessonId;
    }
    public void setLessonId(Long lessonId) {
        this.lessonId = lessonId;
    }


    private Double amount;
    private Long id;
    private Long lessonId;
}
