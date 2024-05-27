package it.unisannio.gruppo3.review.logic;

import it.unisannio.gruppo3.entities.Review;

import java.util.ArrayList;

public interface ReviewLogic {
    Long createReview(Review review);

    Review getReview(Long id);

    Review updateReview(Review review);

    boolean deleteReview(Long id);

    ArrayList<Review> getAllReviews();

     ArrayList<Review> getReviewsByStars(int stars) ;}
