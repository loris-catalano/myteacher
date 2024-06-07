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
import org.springframework.security.core.parameters.P;

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

    @POST
    @Path("/users/")
    @PermitAll
    public Response createUser(User user) {
        return logic.createUser(user);
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
        return logic.createStudent(student);    //this also creates an empty LessonsAgenda and puts the id in the student
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
        return logic.createTeacher(teacher);     //this also creates an empty LessonsAgenda and puts the id in the teacher
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

    /**
     * Enables the teacher to answer to a review.
     */
    @PUT
    @Path("/reviews/{id}/answer")
    @RolesAllowed({"TEACHER"})
    public Response answerToReview(@PathParam("id")Long id, String answer) {
        Review review = logic.getReview(id);
        review.setAnswer(answer);
        return logic.updateReview(review);
    }

    /**
     * Enables the student to edit a review that he already made.
     */
    @PUT
    @Path("/reviews/{id}/editbody")
    @RolesAllowed({"STUDENT"})
    public Response editBodyOfReview(@PathParam("id")Long id, String newBody) {
        Review review = logic.getReview(id);
        review.setBody(newBody);
        return logic.updateReview(review);
    }

    @DELETE
    @Path("/reviews/{id}")
    @RolesAllowed({"STUDENT"})
    public Response deleteReview(@PathParam("id")Long id) {
        return logic.deleteReview(id);
    }

    /* I don't know if it is necessary
    @PUT
    @Path("/reviews/")
    @RolesAllowed({"STUDENT","TEACHER"})
    public Response updateReview(Review review) {
        return logic.updateReview(review);
    }*/


    @GET
    @Path("/lessons/{id}")
    @RolesAllowed({"STUDENT","TEACHER"})
    public Response getLesson(@PathParam("id") Long id) {
        Lesson lesson = logic.getLesson(id);
        return Response.ok(lesson).build();
    }

    @POST
    @Path("/lessons/")
    @RolesAllowed({"TEACHER"})
    public Response createLesson(Lesson lesson) {
        return logic.createLesson(lesson);
    }


    @GET
    @Path("lessons/{lessonId}/student/{studentId}")
    @RolesAllowed({"STUDENT"})
    public Response payLesson(@PathParam("lessonId") Long lessonId, @PathParam("studentId") Long studentId){
        return logic.payLesson(lessonId,studentId);
    }


    @GET
    @Path("/lessonsAgendas/{id}")
    @RolesAllowed({"STUDENT","TEACHER"})
    public Response getLessonsAgenda(@PathParam("id") Long id) {
        LessonsAgenda lessonsAgenda = logic.getLessonsAgenda(id);
        return Response.ok(lessonsAgenda).build();
    }

    @POST
    @Path("/lessonsAgendas/")
    @RolesAllowed({"STUDENT","TEACHER"})
    public Response createLessonsAgenda(LessonsAgenda lessonsAgenda) {
        return logic.createLessonsAgenda(lessonsAgenda);
    }


    /*Don't know if necessary
    @PUT
    @Path("/lessonsAgendas/")
    @RolesAllowed({"STUDENT","TEACHER"})
    public Response updateLessonsAgenda(LessonsAgenda lessonsAgenda) {
        return logic.updateLessonsAgenda(lessonsAgenda);
    }*/


    @GET
    @Path("/payments/{id}")
    @RolesAllowed({"STUDENT","TEACHER"})
    public Response getPayment(@PathParam("id") Long id) {
        Payment payment = logic.getPayment(id);
        return Response.ok(payment).build();
    }

    @POST
    @Path("/payments/")
    @RolesAllowed({"STUDENT","TEACHER"})
    public Response createPayment(Payment payment) {
        return logic.createPayment(payment);
    }


}
