package com.khacchung.demo.chatsocket.model;

public class ChatMessageModel {
    private String sender;
    private String content;
    private MessageType messageType;

    public enum MessageType {
        CHAT,
        CHAT_IMAGE,
        JOIN,
        LEAVE
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }
}
