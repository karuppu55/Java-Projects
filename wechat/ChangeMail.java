package com.zoho.WeChat;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.io.*; 
class ChangeMail
{
    public void changeData(User us)throws SQLException,IOException
    {
        System.out.println("=======================================================================================================");
        System.out.println("\n\t\t\tUPDATE USER NAME");
        System.out.println("=======================================================================================================");
        String str=Validation.getInstance().isMail("MAIL");
        if(Validation.getInstance().isYesorNo("ARE YOU SURE CHANGE PROFILE (Y) OR (N)").toLowerCase().equals("y"))
        {
            us.setEmail(str);
            if(updateData(us))
            {
                System.out.println("\n\t\t*MAIL UPDATE SUCCSSFULLY..");
            }
            else
            {
                System.out.println("\n\t\t*MAIL ALREADY EXISTS");
            }
        }
    }
    public boolean updateData(User us)throws SQLException
    {
        PreparedStatement ps=Database.getInstance().getConnection().prepareStatement("select id from users where email=?");
        ps.setString(1,us.getEmail());
        if(ps.executeQuery().next())
            return false;
        ps=Database.getInstance().getConnection().prepareStatement("UPDATE users set email=? where id=?;");
        ps.setString(1,us.getEmail());
        ps.setInt(2,us.getId());
        ps.executeUpdate();
        return true;
    }
}