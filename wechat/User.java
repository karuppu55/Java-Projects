package com.zoho.WeChat;
import java.sql.Date;
public class User {
    private int id;
    private String name;
    private String fullname;
    private String email;
    private Date dob;
    private Date createdOn;
    private String privacy;
    private boolean status;
    private String password;
    private String gender;
    private String bio;
    Profile p=null;
    public User() {
    }
    public User(int id,String name) {
         this.name = name;
         this.id=id;
    }
    public User(String name) {
         this.name = name;

    }
    public User(String name, String fullname,String gender,String email, Date dob,String password) {
        this.name = name;
        this.gender=gender;
        this.password=password;
        this.fullname = fullname;
        this.email = email;
        this.dob = dob;
    }
     public User(String name, String fullname,String gender,Date dob,String bio,String privacy,String profile,byte[] profilepicture) {
        p=new Profile(profile,profilepicture);
        this.name = name;
        this.gender=gender;
        this.privacy=privacy;
        this.bio=bio;
        this.fullname = fullname;
        this.dob=dob;
    }
      public User(String name, String fullname,String gender,Date dob,String bio,String privacy) {
        this.name = name;
        this.gender=gender;
        this.privacy=privacy;
        this.bio=bio;
        this.fullname = fullname;
        this.dob=dob;
    }
     public User(String email,String password) {
        p=new Profile();
        this.email = email;
        this.password=password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public String getPrivacy() {
        return privacy;
    }

    public void setPrivacy(String privacy) {
        this.privacy = privacy;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
     public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
