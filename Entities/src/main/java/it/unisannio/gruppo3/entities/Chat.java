package it.unisannio.gruppo3.entities;

import java.util.ArrayList;

public class Chat implements Comparable<Chat>{

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

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Chat cht = (Chat) obj;
        return id == cht.getId() &&
                (this.studentId.equals(cht.getStudentId())) &&
                (this.teacherId.equals(cht.getTeacherId()));}

    public int compareTo(Chat cht){
        return Integer.compare(this.getMessages().size(),cht.getMessages().size());}

    public int hashCode() {
        final int PRIME = 5;
        int result = 1;
        result = PRIME * result + (id == null ? 0 : id.hashCode());;
        result = PRIME * result + studentId.hashCode();
        result = PRIME * result + teacherId.hashCode();

        return result;
    }
    private Long id, studentId, teacherId;
    private ArrayList<Message> messages;


}

