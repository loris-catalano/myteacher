package it.unisannio.gruppo3.MyTeacher;

public class PagamentoPremium extends Pagamento{
    public PagamentoPremium(Double importo, Docente docente){
        super(importo);
        this.docente = docente;
    }

    public Docente getDocente(){return docente;}

    private Docente docente;
}
