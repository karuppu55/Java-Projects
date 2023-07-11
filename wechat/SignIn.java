package com.zoho.WeChat;
import java.sql.ResultSet;
import java.io.*;
import java.sql.SQLException;
import java.sql.PreparedStatement;
class SignIn
{
    public void signIn()throws SQLException,IOException
    {
        System.out.println("=======================================================================================================");
        System.out.println("\n\t\t\tSIGN IN");
        System.out.println("=======================================================================================================");
		String email=Validation.getInstance().isMail("E-MAIL");
        String password=Validation.getInstance().isPassword("PASSWORD");
        User us=new User(email,password);
        getUser(us);
        if(us.getId()==0)
            throw new DatNotFoundException("INVALID USERNAME OR PASSWORD!!");
        System.out.println("\n\t\t**LOGIN SUCCUSSFULLY!!!");
        new Home().menu(us);
    }
    public void getUser(User us)throws SQLException
    {
        PreparedStatement ps=Database.getInstance().getConnection().prepareStatement("select u.id,u.name,u.fullname,u.dob,u.createdon,u.privacy,u.status,u.gender,p.name,p.profilepicture,p.status from users u LEFT JOIN profile p ON p.userid=u.id WHERE u.email=? AND u.password=?;");
        ps.setString(1,us.getEmail());
        ps.setString(2,us.getPassword());
        ResultSet rs=ps.executeQuery();
        if(rs.next())
        {
                us.setId(rs.getInt(1));
                us.setName(rs.getString(2));
                us.setFullname(rs.getString(3));
                us.setDob(rs.getDate(4));
                us.setCreatedOn(rs.getDate(5));
                us.setPrivacy(rs.getString(6));
                us.setStatus(rs.getBoolean(7));
                us.setGender(rs.getString(8));
                us.p.setName(rs.getString(9));
                us.p.setProfilePicture(rs.getBytes(10));
                us.p.setStatus(rs.getBoolean(11));
        }
    }
}