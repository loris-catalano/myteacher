package it.unisannio.gruppo3.review.persistence;

import it.unisannio.gruppo3.entities.Review;

import java.util.ArrayList;

public interface ReviewDAO{

    String DATABASE_NAME = "myteacher";
    String COLLECTION_REVIEWS = "Reviews";

    String ELEMENT_REVIEW_ID = "_id";
    String ELEMENT_STARS = "stars";
    String ELEMENT_REVIEW_TITLE = "title";
    String ELEMENT_REVIEW_BODY = "body";
    String ELEMENT_REVIEW_ANSWER = "answer";
    String ELEMENT_STUDENT_ID = "studentId";
    String ELEMENT_TEACHER_ID = "teacherId";
    String ELEMENT_CREATION_TIME = "creationTime";

    String ELEMENT_HIGHEST_ID = "highest";

    boolean dropDB();

    Long createReview(Review review);

    Review getReview(Long id);

    ArrayList<Review> getAllReviews();

    Review updateReview(Review review);

    boolean deleteReview(Long id);

    boolean closeConnection();

    ArrayList<Review>  getReviewsByStars(int stars);
}
