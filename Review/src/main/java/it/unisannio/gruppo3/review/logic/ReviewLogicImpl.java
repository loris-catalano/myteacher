package it.unisannio.gruppo3.review.logic;

import it.unisannio.gruppo3.entities.Review;
import it.unisannio.gruppo3.review.persistence.*;

import java.util.ArrayList;

public class ReviewLogicImpl implements ReviewLogic{

    ReviewDAO reviewDAO;

    public ReviewLogicImpl(){
        reviewDAO = new ReviewDAOMongo();
    }

    @Override
    public Long createReview(Review review) {
        return reviewDAO.createReview(review);
    }

    @Override
    public Review getReview(Long id) {
        return reviewDAO.getReview(id);
    }

    @Override
    public Review updateReview(Review review) {
        return reviewDAO.updateReview(review);
    }

    @Override
    public boolean deleteReview(Long id) {
        return reviewDAO.deleteReview(id);
    }

    @Override
    public ArrayList<Review> getAllReviews() {
        return reviewDAO.getAllReviews();
    }
}
