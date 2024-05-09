package it.unisannio.gruppo3.MyTeacher;

import java.util.List;

public class Studente {
    public Studente(String nome, String cognome, int puntiBonusLezione, List<Recensione> recensioniEffettuate, AgendaLezioni agendaStudente) {
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
    public List<Recensione> getRecensioniEffettuate() {
        return recensioniEffettuate;
    }
    public void setRecensioniEffettuate(List<Recensione> recensioniEffettuate) {
        this.recensioniEffettuate = recensioniEffettuate;
    }
    public AgendaLezioni getAgendaStudente() {
        return agendaStudente;
    }
    public void setAgendaStudente(AgendaLezioni agendaStudente) {
        this.agendaStudente = agendaStudente;
    }

    private String nome;
    private String cognome;
    private int puntiBonusLezione;
    private List<Recensione> recensioniEffettuate;
    private AgendaLezioni agendaStudente;

}
