package it.unisannio.gruppo3.review.presentation;

import it.unisannio.gruppo3.entities.Review;
import it.unisannio.gruppo3.review.logic.*;
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
}
