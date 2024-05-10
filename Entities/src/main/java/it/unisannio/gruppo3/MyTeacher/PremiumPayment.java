package it.unisannio.gruppo3.MyTeacher;

public class PremiumPayment extends Payment {
    public PremiumPayment(Double importo, Teacher docente){
        super(importo);
        this.docente = docente;
    }

    public Teacher getDocente(){return docente;}

    private Teacher docente;
}
