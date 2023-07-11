package com.zoho.WeChat;
public class Profile {
    private int id;
    private int userId;
    private String name;
    private byte[] profilePicture;
    private boolean status;

    public Profile() {
    }

    public Profile(int userId, String name, byte[] profilePicture) {
        this.userId = userId;
        this.name = name;
        this.profilePicture = profilePicture;
    }
     public Profile(String name, byte[] profilePicture) {
        this.name = name;
        this.profilePicture = profilePicture;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(byte[] profilePicture) {
        this.profilePicture = profilePicture;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
