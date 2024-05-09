package it.unisannio.gruppo3.MyTeacher;

public class Recensione {
    public Recensione(int stelle, Studente studente, Docente docente) {
        this.stelle = stelle;
        this.studente = studente;
        this.docente = docente;
    }

    public int getStelle() {
        return stelle;
    }

    public void setStelle(int stelle) {
        this.stelle = stelle;
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

    private int stelle;
    private Studente studente;
    private Docente docente;
}
