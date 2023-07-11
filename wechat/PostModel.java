package com.zoho.WeChat;
import java.util.Date;

public class PostModel {
    private int id;
    private int userId;
    private byte[] post;
    private String caption;
    private Date postedOn;
    private String privacy;
    private boolean status;
    private int like;
    private String commands;
    private int command;
    User us=null;
    public PostModel(int id, int userId, byte[] post, String caption, Date postedOn, String privacy, boolean status) {
        this.id = id;
        this.userId = userId;
        this.post = post;
        this.caption = caption;
        this.postedOn = postedOn;
        this.privacy = privacy;
        this.status = status;
    }
     public PostModel(int id, String commands) {
        this.id = id;
        this.commands=commands;
     }
      public PostModel(int id) {
        this.id = id;
      }
    public PostModel(String name,byte[] post, String caption, Date postedOn, String privacy, boolean status,int like,int command,int userId) {
        us=new User(name);
        this.like=like;
        this.userId = userId;
        this.command=command;
        this.post = post;
        this.caption = caption;
        this.postedOn = postedOn;
        this.privacy = privacy;
        this.status = status;
    }
public PostModel(int userId, byte[] post, String caption,String privacy) {
        
        this.userId = userId;
        this.post = post;
        this.caption = caption;
       
        this.privacy = privacy;
      
    }
    public PostModel(byte[] post, String caption,String privacy,Date postedOn,boolean status) {
        this.post = post;
        this.postedOn=postedOn;
        this.status=status;
        this.caption = caption;
        this.privacy = privacy;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public int getCommand() {
        return command;
    }

    public void setCommand(int command) {
        this.command = command;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public byte[] getPost() {
        return post;
    }

    public void setPost(byte[] post) {
        this.post = post;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getCommands() {
        return commands;
    }

    public void setCommands(String command) {
        this.commands = commands;
    }

    public Date getPostedOn() {
        return postedOn;
    }

    public void setPostedOn(Date postedOn) {
        this.postedOn = postedOn;
    }

    public String getPrivacy() {
        return privacy;
    }

    public void setPrivacy(String privacy) {
        this.privacy = privacy;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Post ID: " + id +
                "\nUser ID: " + userId +
                "\nCaption: " + caption +
                "\nPosted On: " + postedOn +
                "\nPrivacy: " + privacy +
                "\nStatus: " + status;
    }
}
