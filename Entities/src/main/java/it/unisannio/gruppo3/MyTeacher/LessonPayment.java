package it.unisannio.gruppo3.MyTeacher;

public class LessonPayment extends Payment {
    public LessonPayment(Double importo, Lesson lezione, Student studente, Teacher docente) {
        super(importo);
        this.lezione = lezione;
        this.studente = studente;
        this.docente = docente;
    }

    public Lesson getLezione() {
        return lezione;
    }

    public void setLezione(Lesson lezione) {
        this.lezione = lezione;
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

    private Lesson lezione;

    //queste due forse possono essere tolte perché ricavabili da lezione
    private Student studente;
    private Teacher docente;
}