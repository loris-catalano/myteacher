package it.unisannio.gruppo3.teacher.presentation;

import it.unisannio.gruppo3.entities.Student;
import it.unisannio.gruppo3.entities.Teacher;
import it.unisannio.gruppo3.teacher.logic.TeacherLogic;
import it.unisannio.gruppo3.teacher.logic.TeacherLogicImpl;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;

import java.net.URI;
import java.util.ArrayList;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/teacherService")

public class TeacherService {
    TeacherLogic logic;


    public TeacherService(){
        logic = new TeacherLogicImpl();
    }

    @GET
    @Path("/subjects")
    public Response getTeachersBySubjects(@QueryParam("subjects") String subject) {

        ArrayList<Teacher> teachers = logic.getTeachersBySubjects(subject);

        if(teachers != null){
            return Response.ok(teachers).build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();

    }




    @GET
    @Path("/age")
    public Response getTeachersByAge(@QueryParam("age") int age) {

        ArrayList<Teacher> teachers = logic.getTeachersByAge(age);

        if(teachers != null){
            return Response.ok(teachers).build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();

    }

    @GET
    @Path("/age/gte")
    public Response getTeachersByAgeGte(@QueryParam("age") int age) {

        ArrayList<Teacher> teachers = logic.getTeachersByAgeGte(age);

        if(teachers != null){
            return Response.ok(teachers).build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();

    }

    @GET
    @Path("/age/lte")
    public Response getTeachersByAgeLte(@QueryParam("age") int age) {

        ArrayList<Teacher> teachers = logic.getTeachersByAgeLte(age);

        if(teachers != null){
            return Response.ok(teachers).build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();

    }


    @POST
    public Response createTeacher(Teacher teacher){
        Long createdTeacherId = logic.createTeacher(teacher);
        if(createdTeacherId != null){
            URI uri = UriBuilder.fromPath("/teacher/teacherService/{id}").build(createdTeacherId);
            return Response.created(uri).build();
        } else{
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("/{id}")
    public Response getTeacher(@PathParam("id") Long id){
        Teacher teacherById = logic.getTeacher(id);
        if (teacherById != null){
            return Response.ok(teacherById).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    @Path("/email/{email}")
    public Response getTeacherByEmail(@PathParam("email") String email){
        Teacher teacherByEmail = logic.getTeacherByEmail(email);
        if (teacherByEmail != null){
            return Response.ok(teacherByEmail).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    public Response getAlTeachers() {
        ArrayList<Teacher> teachers = logic.getAllTeachers();
        return Response.ok(teachers).build();
    }

//
    @PUT
    public Response updateTeacher(Teacher teacher) {

        Teacher teacherUpdated = logic.updateTeacher(teacher);

        return Response.ok(teacherUpdated).build();

    }

    @DELETE
    @Path("/{id}")
    public Response deleteTeacher(@PathParam("id") Long id) {

        boolean isDeleted = logic.deleteTeacher(id);

        if(isDeleted) {
            return Response.noContent().build();
        } else {
            return Response.serverError().build();
        }




}}
