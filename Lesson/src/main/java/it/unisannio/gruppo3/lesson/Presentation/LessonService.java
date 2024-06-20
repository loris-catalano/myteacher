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
import java.util.ArrayList;

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

    @GET
    public Response getAllLessons() {
        ArrayList<Lesson> lessons = logic.getAllLessons();
        return Response.ok(lessons).build();
    }


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


}