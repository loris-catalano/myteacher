package it.unisannio.gruppo3.entities;

public class Payment {
    public Payment(Double amount){
        this.amount=amount;
    }


    public Double getAmount(){return amount;}

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    private Double amount;
}
