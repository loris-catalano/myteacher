package it.unisannio.gruppo3.MyTeacher;

public class Review {
    public Review(int stelle, Student studente, Teacher docente) {
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

    public Student getStudente() {
        return studente;
    }

    public void setStudente(Student studente) {
        this.studente = studente;
    }

    public Teacher getDocente() {
        return docente;
    }

    public void setDocente(Teacher docente) {
        this.docente = docente;
    }

    private int stelle;
    private Student studente;
    private Teacher docente;
}
