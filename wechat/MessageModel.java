package com.zoho.WeChat;
import java.time.LocalDate;

public class MessageModel {
    private int id;
    private String receiver;
    private int senderId;
    private int count;
    private int receiverId;
    private String message;
    private LocalDate sentOn;
    private String viewStatus;
    private boolean status;

    public MessageModel(String receiver, int senderId, int count, int receiverId, String message) {
        this.receiver = receiver;
        this.senderId = senderId;
        this.count = count;
        this.receiverId = receiverId;
        this.message = message;
        this.sentOn = LocalDate.now();
        this.viewStatus = "send";
        this.status = true;
    }
        public MessageModel(String receiver,int count) {
        this.receiver = receiver;
        this.count = count;
    }
    // Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(int receiverId) {
        this.receiverId = receiverId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDate getSentOn() {
        return sentOn;
    }

    public void setSentOn(LocalDate sentOn) {
        this.sentOn = sentOn;
    }

    public String getViewStatus() {
        return viewStatus;
    }

    public void setViewStatus(String viewStatus) {
        this.viewStatus = viewStatus;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
