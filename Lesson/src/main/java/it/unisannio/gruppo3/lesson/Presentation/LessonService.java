package it.unisannio.gruppo3.lesson.Presentation;

import it.unisannio.gruppo3.entities.Lesson;
import it.unisannio.gruppo3.entities.Student;
import it.unisannio.gruppo3.entities.Teacher;
import it.unisannio.gruppo3.lesson.Logic.LessonLogic;
import it.unisannio.gruppo3.lesson.Logic.LessonLogicImpl;
import jakarta.ws.rs.*;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.*;

import java.net.URI;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/lessonService")
public class LessonService {
    LessonLogic logic;


    public LessonService(){
        logic = new LessonLogicImpl();
    }

    @POST
    public Response createLesson(Lesson lesson){
        Long createdLessonId = logic.createLesson(lesson);
        if(createdLessonId != null){
            URI uri = UriBuilder.fromPath("/lesson/lessonService/{id}").build(createdLessonId);
            return Response.created(uri).build();
        } else{
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("/{id}")
    public Response getLesson(@PathParam("id") Long id){
        Lesson lessonById = logic.getLesson(id);
        if (lessonById != null){
            return Response.ok(lessonById).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
/*
    @GET
    public Response getAllStudents() {
        ArrayList<Student> students = logic.getAllStudents();
        return Response.ok(students).build();
    }


    public Response searchStudentsByFirstName(@QueryParam("firstName") String fName) {
        return null;
    }
*/
    @PUT
    public Response updateLesson(Lesson lesson) {
        Lesson lessonUpdated = logic.updateLesson(lesson);
        return Response.ok(lessonUpdated).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteLesson(@PathParam("id") Long id) {
        boolean isDeleted = logic.deleteLesson(id);
        if (isDeleted){
            return Response.noContent().build();
        } else {
            return Response.serverError().build();
        }
    }
/*
    @GET
    @Path("/{studentId}")
    public Response getStudentByLesson(@PathParam("studentId")Long studentId){
        Client client = ClientBuilder.newClient();
        try {
            // Costruisci il target dell'endpoint del servizio Student
            WebTarget target = client.target("http://localhost:8081/student/studs/" + studentId);

            // Effettua la richiesta GET
            Response response = target.request(MediaType.APPLICATION_JSON).get();

            // Verifica la risposta e restituisci di conseguenza
            if (response.getStatus() == Response.Status.OK.getStatusCode()) {
                Student student = response.readEntity(Student.class);
                return Response.ok(student).build();
            } else if (response.getStatus() == Response.Status.NOT_FOUND.getStatusCode()) {
                return Response.status(Response.Status.NOT_FOUND).build();
            } else {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
            }
        } finally {
            // Chiudi il client per rilasciare le risorse
            client.close();
        }
    }

    @GET
    @Path("/{teacherId}")
    public Response getTeacherByLesson(@PathParam("teacherId")Long teacherId){
        Client client = ClientBuilder.newClient();
        try {
            // Costruisci il target dell'endpoint del servizio Student
            WebTarget target = client.target("http://localhost:8082/teacher/teacherService/" + teacherId);

            // Effettua la richiesta GET
            Response response = target.request(MediaType.APPLICATION_JSON).get();

            // Verifica la risposta e restituisci di conseguenza
            if (response.getStatus() == Response.Status.OK.getStatusCode()) {
                Teacher teacher = response.readEntity(Teacher.class);
                return Response.ok(teacher).build();
            } else if (response.getStatus() == Response.Status.NOT_FOUND.getStatusCode()) {
                return Response.status(Response.Status.NOT_FOUND).build();
            } else {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
            }
        } finally {
            // Chiudi il client per rilasciare le risorse
            client.close();
        }
    }
*/


}