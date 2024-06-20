package it.unisannio.gruppo3.entities;

import java.util.Objects;

public class Message implements Comparable<Message> {
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

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message msg = (Message) o;
        return this.senderId == msg.senderId &&
             this.text.equals(msg.text);}

    @Override
    public int compareTo(Message message) {
        return this.senderId.compareTo(message.getSenderId());
    }

    public int hashCode(){
            final int PRIME = 2;
            int result = 1;
            result = PRIME * result + senderId.hashCode();
            result = PRIME * result + this.text.hashCode();
            return result;
        }






    private String text;
    private Long id, senderId;



}
