package it.unisannio.gruppo3.MyTeacher;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.File;
import java.sql.Time;
import java.util.List;


public class Docente {

    @POST
    @Path("answertoreview")
    public void answerToReview(@QueryParam("answer") String answer,@QueryParam("reviewid") int reviewid) {

    }

    @POST
    @Path("insertlecture")
    public void insertLecture(@RequestBody Lezione lez, AgendaLezioni agendaDoc) {
        agendaDoc.getLezioni().add(lez);
    }

    Response richiestaPremium(PagamentoPremium pagPrem) {
        return null; // Placeholder return
    }

    Response deleteLecture(Lezione lez, AgendaLezioni agendaDoc) {
        return null; // Placeholder return
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

    public List<String> getMaterie() {
        return materie;
    }

    public void setMaterie(List<String> materie) {
        this.materie = materie;
    }

    public int getEta() {
        return eta;
    }

    public void setEta(int eta) {
        this.eta = eta;
    }

    public boolean isPremium() {
        return premium;
    }

    public void setPremium(boolean premium) {
        this.premium = premium;
    }

    public List<Recensione> getRecensioniRicevute() {
        return recensioniRicevute;
    }

    public void setRecensioniRicevute(List<Recensione> recensioniRicevute) {
        this.recensioniRicevute = recensioniRicevute;
    }

    public Localita getPosizione() {
        return posizione;
    }

    public void setPosizione(Localita posizione) {
        this.posizione = posizione;
    }

    public Time getFasciaOrariaDisponibile() {
        return fasciaOrariaDisponibile;
    }

    public void setFasciaOrariaDisponibile(Time fasciaOrariaDisponibile) {
        this.fasciaOrariaDisponibile = fasciaOrariaDisponibile;
    }

   /* @GET
    @Path("getagendadoc")
    @Produces({"application/json"})*/
    public AgendaLezioni getAgendaDoc() {
        return agendaDoc;
    }

    public void setAgendaDoc(AgendaLezioni agendaDoc) {
        this.agendaDoc = agendaDoc;
    }

    public File getCurriculum() {
        return curriculum;
    }

    public void setCurriculum(File curriculum) {
        this.curriculum = curriculum;
    }

    private String nome;
    private String cognome;
    private List<String> materie;
    private int eta;
    private boolean premium;
    private List<Recensione> recensioniRicevute;
    private Localita posizione;
    private Time fasciaOrariaDisponibile;
    private AgendaLezioni agendaDoc;
    private File curriculum;
}
