package com.zoho.WeChat;
import java.util.Date;

public class LikeModel {
    private int id;
    private int userId;
    private int postId;
    private boolean status;
    private String viewStatus;
    private Date likedOn;
    User us=null;
    public LikeModel(int id, int userId, int postId, boolean status, String viewStatus, Date likedOn) {
        this.id = id;
        this.userId = userId;
        this.postId = postId;
        this.status = status;
        this.viewStatus = viewStatus;
        this.likedOn = likedOn;
    }

    public LikeModel(int id, String name) {
        us=new User(id,name);
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
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

    public Date getLikedOn() {
        return likedOn;
    }

    @Override
    public String toString() {
        return "Like ID: " + id +
                "\nUser ID: " + userId +
                "\nPost ID: " + postId +
                "\nStatus: " + status +
                "\nView Status: " + viewStatus +
                "\nLiked On: " + likedOn;
    }
}
