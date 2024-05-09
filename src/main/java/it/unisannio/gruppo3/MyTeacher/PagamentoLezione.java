package it.unisannio.gruppo3.MyTeacher;

public class PagamentoLezione extends Pagamento{
    public PagamentoLezione(Double importo, Lezione lezione, Studente studente, Docente docente) {
        super(importo);
        this.lezione = lezione;
        this.studente = studente;
        this.docente = docente;
    }

    public Lezione getLezione() {
        return lezione;
    }

    public void setLezione(Lezione lezione) {
        this.lezione = lezione;
    }

    public Studente getStudente() {
        return studente;
    }

    public void setStudente(Studente studente) {
        this.studente = studente;
    }

    public Docente getDocente() {
        return docente;
    }

    public void setDocente(Docente docente) {
        this.docente = docente;
    }

    private Lezione lezione;

    //queste due forse possono essere tolte perché ricavabili da lezione
    private Studente studente;
    private Docente docente;
}