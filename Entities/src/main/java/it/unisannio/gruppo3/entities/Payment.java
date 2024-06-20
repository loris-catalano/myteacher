package it.unisannio.gruppo3.entities;

public class Payment implements Comparable<Payment> {


    public Payment(Long id, Double amount, Long lessonId){
        this.id= id;
        this.amount=amount;
        this.lessonId=lessonId;
    }

    public Payment(Double amount, Long lessonId){
        this.amount=amount;
        this.lessonId=lessonId;
    }

    public Payment(){}


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

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Payment pymt = (Payment) obj;
        return id == pymt.getId();}

    public int compareTo(Payment pymt){
        return this.amount.compareTo(pymt.getAmount());
    }

    public int hashCode(){
          final int PRIME = 3;
          int result = 1;
          result = PRIME * result + id.hashCode();
          return result;
    }

    private Double amount;
    private Long id;
    private Long lessonId;
}