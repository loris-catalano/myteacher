package it.unisannio.gruppo3.MyTeacher;

public class Location {
    public Location(String citta, String via, int civico, Teacher docente) {
        this.citta = citta;
        this.via = via;
        this.civico = civico;
        this.docente = docente;
    }

    public String getCitta() {
        return citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    public String getVia() {
        return via;
    }

    public void setVia(String via) {
        this.via = via;
    }

    public int getCivico() {
        return civico;
    }

    public void setCivico(int civico) {
        this.civico = civico;
    }

    public Teacher getDocente() {
        return docente;
    }

    public void setDocente(Teacher docente) {
        this.docente = docente;
    }

    private String citta;
    private String via;
    private int civico;
    private Teacher docente;
}
