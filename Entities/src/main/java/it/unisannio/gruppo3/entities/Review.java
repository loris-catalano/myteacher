package it.unisannio.gruppo3.entities;


import java.time.Instant;
import java.util.Objects;

public class Review implements Comparable<Review>{

    public Review(Long id, int stars, String title, String body, String answer, Long studentId, Long teacherId, Instant creationTime) {
        this.id = id;
        this.stars = stars;
        this.title = title;
        this.body = body;
        this.answer = answer;
        this.studentId = studentId;
        this.teacherId = teacherId;
        this.creationTime = creationTime;
    }

    //Called when there is a POST request
    public Review(){
        this.creationTime = Instant.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getAnswer() {return answer;}

    public void setAnswer(String answer) {
        this.answer = answer;
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

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Review rvw= (Review) o;
        return this.id==rvw.getId() && this.title.equals((rvw.getTitle()));
    }

    public int compareTo(Review rvw){
        return Integer.compare(this.stars,rvw.getStars());
    }

    public int hashCode(){

            final int PRIME = 2;
            int result = 1;
            result = PRIME * result +  id.hashCode();
            result = PRIME * result + title.hashCode();
            return result;
        }


    private Long id;
    private int stars;
    private String title;
    private String body;
    private String answer;
    private Long studentId;
    private Long teacherId;
    private Instant creationTime;
}

