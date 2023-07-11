package com.zoho.WeChat;
import java.util.Date;

public class ChatModel {
    private int id;
    private int senderId;
    private int receiverId;
    private String message;
    private Date sentOn;
    private String viewStatus;
    private boolean status;

    public ChatModel(int id, int senderId, int receiverId, String message, Date sentOn, String viewStatus, boolean status) {
        this.id = id;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.message = message;
        this.sentOn = sentOn;
        this.viewStatus = viewStatus;
        this.status = status;
    }
    public ChatModel(int senderId, int receiverId, String message) {
      
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.message = message;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
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

    public Date getSentOn() {
        return sentOn;
    }

    public void setSentOn(Date sentOn) {
        this.sentOn = sentOn;
    }

    public String getViewStatus() {
        return viewStatus;
    }

    public void setViewStatus(String viewStatus) {
        this.viewStatus = viewStatus;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Message ID: " + id +
                "\nSender ID: " + senderId +
                "\nReceiver ID: " + receiverId +
                "\nMessage: " + message +
                "\nSent On: " + sentOn +
                "\nView Status: " + viewStatus +
                "\nStatus: " + status;
    }
}
