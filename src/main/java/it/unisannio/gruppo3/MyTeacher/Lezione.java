package it.unisannio.gruppo3.MyTeacher;

import java.util.Date;

public class Lezione {
    public Lezione(int prezzo, String materia, Docente docente, Date data, Studente student, AgendaLezioni agendaStudente, AgendaLezioni agendaDocente) {
        this.prezzo = prezzo;
        this.materia = materia;
        this.docente = docente;
        this.data = data;
        this.student = student;
        this.agendaStudente = agendaStudente;
        this.agendaDocente = agendaDocente;
    }
    private int prezzo;
    private String materia;
    private Docente docente;
    private Date data;
    private Studente student;
    private AgendaLezioni agendaStudente;
    private AgendaLezioni agendaDocente;
    private int testPush;


    public int getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(int prezzo) {
        this.prezzo = prezzo;
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public Docente getDocente() {
        return docente;
    }

    public void setDocente(Docente docente) {
        this.docente = docente;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Studente getStudent() {
        return student;
    }

    public void setStudent(Studente student) {
        this.student = student;
    }

    public AgendaLezioni getAgendaStudente() {
        return agendaStudente;
    }

    public void setAgendaStudente(AgendaLezioni agendaStudente) {
        this.agendaStudente = agendaStudente;
    }

    public AgendaLezioni getAgendaDocente() {
        return agendaDocente;
    }

    public void setAgendaDocente(AgendaLezioni agendaDocente) {
        this.agendaDocente = agendaDocente;
    }

}
