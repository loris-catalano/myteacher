package it.unisannio.gruppo3.review.presentation;

import it.unisannio.gruppo3.entities.Review;
import it.unisannio.gruppo3.entities.Student;
import it.unisannio.gruppo3.review.logic.*;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.*;

import jakarta.ws.rs.*;

import java.net.URI;
import java.util.ArrayList;


@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/reviewService")
public class ReviewService {
    ReviewLogic logic;


    public ReviewService(){
        logic = new ReviewLogicImpl();
    }

    @POST
    public Response createReview(Review review){
        Long createdReviewId = logic.createReview(review);
        if(createdReviewId != null){
            URI uri = UriBuilder.fromPath("/review/reviewService/{id}").build(createdReviewId);
            return Response.created(uri).build();
        } else{
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("/stars")
    public Response getReviewsByStars(@QueryParam("stars") int stars){
        ArrayList<Review> reviews=logic.getReviewsByStars(stars);
        if (reviews!= null){
            return Response.ok(reviews).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }


    @GET
    @Path("/{id}")
    public Response getReview(@PathParam("id") Long id){
        Review reviewById = logic.getReview(id);
        if (reviewById != null){
            return Response.ok(reviewById).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    public Response getAllReviews() {
        ArrayList<Review> reviews = logic.getAllReviews();
        return Response.ok(reviews).build();
    }



    @PUT
    public Response updateReview(Review review) {
        Review reviewUpdated = logic.updateReview(review);
        return Response.ok(reviewUpdated).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteReview(@PathParam("id") Long id) {
        boolean isDeleted = logic.deleteReview(id);
        if (isDeleted){
            return Response.noContent().build();
        } else {
            return Response.serverError().build();
        }
    }
    /*
    @GET
    @Path("/student")
    public Response getStudentByReview(@QueryParam("studentId") Long studentId) {
        Client client = ClientBuilder.newClient();
        try {
            WebTarget target = client.target("http://localhost:8081/student/studentService/" + studentId);

            System.out.println("Target: "+target);
            Response response = target.request(MediaType.APPLICATION_JSON).get();
            System.out.println("Response: "+response);
            if(response.getStatus() == Response.Status.OK.getStatusCode()){
                Student student = response.readEntity(Student.class);
                return Response.ok(student).build();
            }else if(response.getStatus() == Response.Status.NOT_FOUND.getStatusCode()){
                return Response.status(Response.Status.NOT_FOUND).build();
            }else {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
            }
        }finally {
            client.close();
        }
    }*/
}
