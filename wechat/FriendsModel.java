package com.zoho.WeChat;
import java.sql.Date;
class FriendsModel extends User
{
    private boolean chatstatus;
    public FriendsModel(String name, String fullname,String gender,Date dob,String bio,String privacy,String profile,byte[] profilepicture,boolean chatstatus) {
        super(name,fullname,gender,dob,bio,privacy,profile,profilepicture);
        this.chatstatus=chatstatus;
    }
    public FriendsModel(String name, String fullname,String gender,Date dob,String bio,String privacy,String profile,byte[] profilepicture) {
        super(name,fullname,gender,dob,bio,privacy,profile,profilepicture);
    }
    public boolean getChatStatus()
    {
        return chatstatus;
    }
    public void setChatStatus(boolean chatstatus)
    {
        this.chatstatus=chatstatus;
    }
}