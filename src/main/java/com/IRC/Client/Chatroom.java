package com.IRC.Client;

public class Chatroom {
    private MessageList messages;
    private RoomType roomType;
    private String topic;

    Chatroom(RoomType roomType, String topic) {
        this.messages = null;
        this.roomType = roomType;
        this.topic = topic;
    }

    public enum RoomType {
        PRIVATE,
        CHATROOM
    }

    public MessageList getMessages() {
        return messages;
    }

    public void setMessages(MessageList messages) {
        this.messages = messages;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }
    
}
