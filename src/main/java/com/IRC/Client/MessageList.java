package com.IRC.Client;

import java.util.LinkedList;

public class MessageList extends LinkedList<MessageList.Message> {
    public boolean add(MessageType messageType, String message) {
        return add(new Message(messageType, message));
    }

    public static enum MessageType {
        MESSAGE,
        NOTICE,
        COMMAND
    }

    public static class Message {
        private MessageType type;
        private String message;

        public Message(MessageType messageType, String message) {
            this.type = messageType;
            this.message = message;
        }

        public MessageType getType() {
            return type;
        }

        public String getMessage() {
            return message;
        }
    }
}
