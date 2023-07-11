package com.zoho.WeChat;
import java.util.Date;

public class CommandModel {
    private int id;
    private int userId;
    private String command;
    private int postId;
    private boolean status;
    private String viewStatus;
    private Date commandOn;
    User us=null;
    public CommandModel(int id, int userId, String command, int postId, boolean status, String viewStatus, Date commandOn) {
        this.id = id;
        this.userId = userId;
        this.command = command;
        this.postId = postId;
        this.status = status;
        this.viewStatus = viewStatus;
        this.commandOn = commandOn;
    }

     public CommandModel(int id,String name,String command) {
        us=new User(id,name);
        this.command = command;
     }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public String getCommand() {
        return command;
    }

    public int getPostId() {
        return postId;
    }

    public boolean getStatus() {
        return status;
    }

    public String getViewStatus() {
        return viewStatus;
    }

    public Date getCommandOn() {
        return commandOn;
    }

    @Override
    public String toString() {
        return "Command ID: " + id +
                "\nUser ID: " + userId +
                "\nCommand: " + command +
                "\nPost ID: " + postId +
                "\nStatus: " + status +
                "\nView Status: " + viewStatus +
                "\nCommand On: " + commandOn;
    }
}
