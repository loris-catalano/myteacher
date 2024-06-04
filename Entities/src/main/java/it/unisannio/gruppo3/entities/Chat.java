package it.unisannio.gruppo3.entities;

import java.util.ArrayList;

public class Chat {

    public Chat(){}

    public Chat(Long studentId, Long teacherId){
        this.id = null;
        this.studentId = studentId;
        this.teacherId = teacherId;
        this.messages = new ArrayList<Message>();
    }

    public Chat(Long id, Long studentId, Long teacherId, ArrayList<Message> messages) {
        this.id = id;
        this.studentId = studentId;
        this.teacherId = teacherId;
        this.messages = messages;
    }

    public Long getStudentId() {
        return this.studentId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTeacherId() {
        return this.teacherId;
    }
    public Long getId() {return this.id;}

    public ArrayList<Message> getMessages() {
        return this.messages;
    }

    private Long id, studentId, teacherId;
    private ArrayList<Message> messages;


}

