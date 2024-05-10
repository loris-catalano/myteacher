package it.unisannio.gruppo3.MyTeacher;

import java.util.List;

public class Student {
    public Student(String nome, String cognome, int puntiBonusLezione, List<Review> recensioniEffettuate, DiaryLessons agendaStudente) {
        this.nome = nome;
        this.cognome = cognome;
        this.puntiBonusLezione = puntiBonusLezione;
        this.recensioniEffettuate = recensioniEffettuate;
        this.agendaStudente = agendaStudente;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getCognome() {
        return cognome;
    }
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }
    public int getPuntiBonusLezione() {
        return puntiBonusLezione;
    }
    public void setPuntiBonusLezione(int puntiBonusLezione) {
        this.puntiBonusLezione = puntiBonusLezione;
    }
    public List<Review> getRecensioniEffettuate() {
        return recensioniEffettuate;
    }
    public void setRecensioniEffettuate(List<Review> recensioniEffettuate) {
        this.recensioniEffettuate = recensioniEffettuate;
    }
    public DiaryLessons getAgendaStudente() {
        return agendaStudente;
    }
    public void setAgendaStudente(DiaryLessons agendaStudente) {
        this.agendaStudente = agendaStudente;
    }

    private String nome;
    private String cognome;
    private int puntiBonusLezione;
    private List<Review> recensioniEffettuate;
    private DiaryLessons agendaStudente;

}
