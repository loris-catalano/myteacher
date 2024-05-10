package it.unisannio.gruppo3.MyTeacher;

import java.util.Date;

public class Lesson {
    public Lesson(int prezzo, String materia, Teacher docente, Date data, Student student, DiaryLessons agendaStudente, DiaryLessons agendaDocente) {
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
    private Teacher docente;
    private Date data;
    private Student student;
    private DiaryLessons agendaStudente;
    private DiaryLessons agendaDocente;

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

    public Teacher getDocente() {
        return docente;
    }

    public void setDocente(Teacher docente) {
        this.docente = docente;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public DiaryLessons getAgendaStudente() {
        return agendaStudente;
    }

    public void setAgendaStudente(DiaryLessons agendaStudente) {
        this.agendaStudente = agendaStudente;
    }

    public DiaryLessons getAgendaDocente() {
        return agendaDocente;
    }

    public void setAgendaDocente(DiaryLessons agendaDocente) {
        this.agendaDocente = agendaDocente;
    }

}
