package it.unisannio.gruppo3.entities;


import java.time.Instant;

public class Review {

    public Review(Long reviewId, int stars, String title, String body, Long studentId, Long teacherId, Instant creationTime) {
        this.reviewId = reviewId;
        this.stars = stars;
        this.title = title;
        this.body = body;
        this.studentId = studentId;
        this.teacherId = teacherId;
        this.creationTime = creationTime;
    }

    //Called when there is a POST request
    public Review(){
        this.creationTime = Instant.now();
    }

    public Long getReviewId() {
        return reviewId;
    }

    public void setReviewId(Long id) {
        this.reviewId = id;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
    public Long getStudentId() {
        return studentId;
    }


    public void setStudentId(Long id) {
        this.studentId = id;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long id) {
        this.teacherId = id;
    }

    public Instant getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Instant creationTime) {
        this.creationTime = Instant.now();
    }

    private Long reviewId;
    private int stars;
    private String title;
    private String body;
    private Long studentId;
    private Long teacherId;
    private Instant creationTime;
}

