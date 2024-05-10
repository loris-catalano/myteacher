package it.unisannio.gruppo3.MyTeacher;

public class Payment {
    public Payment(Double importo){
        this.importo=importo;
    }


    public Double getImporto(){return importo;}

    public void setImporto(Double importo) {
        this.importo = importo;
    }

    private Double importo;
}
