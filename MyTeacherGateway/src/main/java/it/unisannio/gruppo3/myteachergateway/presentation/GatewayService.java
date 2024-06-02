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
import java.util.ArrayList;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/")

public class GatewayService {
    GatewayLogic logic;

    public GatewayService() {
        logic = new GatewayLogicImpl();
    }


    @GET
    @Path("/students/{id}")
    @RolesAllowed({"TEACHER"})
    public Response getStudent(@PathParam("id") Long id) {
        Student student = logic.getStudent(id);
        return Response.ok(student).build();
    }

    @POST
    @Path("/students/")
    @PermitAll
    public Response createStudent(Student student) {
        return logic.createStudent(student);
    }


    @GET
    @Path("/teachers/{id}")
    @RolesAllowed({"STUDENT"})
    public Response getTeacher(@PathParam("id") Long id) {
        Teacher teacher = logic.getTeacher(id);
        return Response.ok(teacher).build();
    }

    @POST
    @Path("/teachers/")
    @PermitAll
    public Response createTeacher(Teacher teacher) {
        return logic.createTeacher(teacher);
    }


    @GET
    @Path("/teachers/{id}/reviews")
    @RolesAllowed({"STUDENT","TEACHER"})
    public Response getTeacherReviews(@PathParam("id") Long teacherId) {
        Teacher teacher = logic.getTeacher(teacherId);
        ArrayList<Long> reviewsIds = (ArrayList<Long>) teacher.getReceivedReviews();

        if (reviewsIds==null) return Response.noContent().build();

        ArrayList<Review> reviews = new ArrayList<>();

        for(Long reviewId: reviewsIds){
            reviews.add(logic.getReview(reviewId));
        }

        return Response.ok(reviews).build();
    }

    @GET
    @Path("/reviews/{id}")
    @RolesAllowed({"STUDENT","TEACHER"})
    public Response getReview(@PathParam("id") Long id) {
        Review review = logic.getReview(id);
        return Response.ok(review).build();
    }

    @POST
    @Path("/reviews/")
    @RolesAllowed({"STUDENT"})
    public Response createReview(Review review) {
        //if the ids of the student and the teacher don't exist there is an error

        if(getTeacher(review.getTeacherId()).hasEntity() && getStudent(review.getStudentId()).hasEntity())
            return logic.createReview(review);
        else return Response.serverError().build();
    }

}
