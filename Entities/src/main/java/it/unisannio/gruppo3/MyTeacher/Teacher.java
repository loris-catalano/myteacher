package it.unisannio.gruppo3.MyTeacher;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.File;
import java.sql.Time;
import java.util.List;


public class Teacher {

    @POST
    @Path("answertoreview")
    public void answerToReview(@QueryParam("answer") String answer,@QueryParam("reviewid") int reviewid) {

    }

    @POST
    @Path("insertlecture")
    public void insertLecture(@RequestBody Lesson lez, DiaryLessons agendaDoc) {
        agendaDoc.getLezioni().add(lez);
    }

    Response richiestaPremium(PremiumPayment pagPrem) {
        return null; // Placeholder return
    }

    Response deleteLecture(Lesson lez, DiaryLessons agendaDoc) {
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

    public List<Review> getRecensioniRicevute() {
        return recensioniRicevute;
    }

    public void setRecensioniRicevute(List<Review> recensioniRicevute) {
        this.recensioniRicevute = recensioniRicevute;
    }

    public Location getPosizione() {
        return posizione;
    }

    public void setPosizione(Location posizione) {
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
    public DiaryLessons getAgendaDoc() {
        return agendaDoc;
    }

    public void setAgendaDoc(DiaryLessons agendaDoc) {
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
    private List<Review> recensioniRicevute;
    private Location posizione;
    private Time fasciaOrariaDisponibile;
    private DiaryLessons agendaDoc;
    private File curriculum;
}
