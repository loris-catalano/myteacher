package it.unisannio.gruppo3.entities;

public class Message {
    public Message(Long senderId, String text){
        this.senderId = senderId;
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public Long getId() {
        return id;
    }

    public Long getSenderId() {
        return senderId;
    }

    private String text;
    private Long id, senderId;
}
