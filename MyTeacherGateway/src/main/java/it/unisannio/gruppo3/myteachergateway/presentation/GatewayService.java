package it.unisannio.gruppo3.myteachergateway.presentation;

import it.unisannio.gruppo3.entities.*;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.MediaType;
import it.unisannio.gruppo3.myteachergateway.logic.GatewayLogic;
import it.unisannio.gruppo3.myteachergateway.logic.GatewayLogicImpl;
import jakarta.ws.rs.core.UriBuilder;

import java.net.URI;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/")

public class GatewayService {
    GatewayLogic logic;

    public GatewayService() {
        logic = new GatewayLogicImpl();
    }


    @GET
    @Path("/student/{id}")
    @RolesAllowed({"TEACHER"})
    public Response getStudent(@PathParam("id") Long id) {
        Student student = logic.getStudent(id);
        return Response.ok(student).build();
    }

    @POST
    @Path("/student/")
    @PermitAll
    public Response createStudent(Student student) {
        return logic.createStudent(student);
    }


    @GET
    @Path("/teacher/{id}")
    @RolesAllowed({"STUDENT"})
    public Response getTeacher(@PathParam("id") Long id) {
        Teacher teacher = logic.getTeacher(id);
        return Response.ok(teacher).build();
    }

    @POST
    @Path("/teacher/")
    @PermitAll
    public Response createTeacher(Teacher teacher) {
        return logic.createTeacher(teacher);
    }


    @POST
    @Path("/review/")
    @RolesAllowed({"STUDENT"})
    public Response createReview(Review review) {
        //if the ids of the student and the teacher don't exist there is an error

        if(getTeacher(review.getTeacherId()).hasEntity() && getStudent(review.getStudentId()).hasEntity())
            return logic.createReview(review);
        else return Response.serverError().build();
    }

}
