package com.zoho.WeChat;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.io.*; 
class ChangePrivacy
{
    public void changeData(User us)throws SQLException,IOException
    {
        System.out.println("=======================================================================================================");
        System.out.println("\n\t\t\tUPDATE PRIVACY");
        System.out.println("=======================================================================================================");
        String privacy=new PostUpload().getPrivacy();
        if(Validation.getInstance().isYesorNo("ARE YOU SURE CHANGE PROFILE (Y) OR (N)").toLowerCase().equals("y"))
        {
            us.setPrivacy(privacy);
            if(updateData(us))
            {
                System.out.println("\n\t\t*UPDATED SUCCSSFULLY..");
            }
            else
            {
                System.out.println("\n\t\t*PRIVACY ALREADY SAME");
            }
        }
    }
    public boolean updateData(User us)throws SQLException
    {
        PreparedStatement ps=Database.getInstance().getConnection().prepareStatement("select id from users where privacy=? AND id=?");
        ps.setString(1,us.getPrivacy());
        ps.setInt(2,us.getId());
        if(ps.executeQuery().next())
            return false;
        ps=Database.getInstance().getConnection().prepareStatement("UPDATE users set privacy=? where id=?;");
        ps.setString(1,us.getName());
        ps.setInt(2,us.getId());
        ps.executeUpdate();
        return true;
    }
}