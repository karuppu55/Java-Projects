package com.zoho.WeChat;
import java.sql.Date;
public class RequestModel {
    private int senderid;
    private int id;
    private String sender;
    private int receiverid;
    private String status;
    User us=null;
    Profile p=null;
    RequestModel(int senderid,int receiverid)
    {
        this.senderid=senderid;
        this.receiverid=receiverid;
    }
    public RequestModel(String name, String fullname,String gender,Date dob,String bio,String privacy,String profile,byte[] profilepicture) {
        p=new Profile(profile,profilepicture);
        us=new User(name,fullname,gender,dob,bio,privacy);
    }

    RequestModel(int id,String status)
    {
        this.id=id;
        this.status=status;
    }

    public int getSendId() {
        return senderid;
    }

    public void setSendId(int senderid) {
        this.senderid = senderid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public int getReceiverId() {
        return receiverid;
    }

    public void setReceiverId(int receiverId) {
        this.receiverid = receiverid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
