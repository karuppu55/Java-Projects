package com.zoho.WeChat;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.io.*; 
class ChangeUserName
{
    public void changeData(User us)throws SQLException,IOException
    {
        System.out.println("=======================================================================================================");
        System.out.println("\n\t\t\tUPDATE USER NAME");
        System.out.println("=======================================================================================================");
        String str=Validation.getInstance().isLetter("USER NAME");
         if(Validation.getInstance().isYesorNo("ARE YOU SURE CHANGE USER NAME (Y) OR (N)").toLowerCase().equals("y"))
        {
            us.setName(str);
            if(updateData(us))
            {
                System.out.println("\n\t\t*USER NAME UPDATE SUCCSSFULLY..");
            }
            else
            {
                System.out.println("\n\t\t*USER NAME ALREADY EXISTS");
            }
        }
    }
    public boolean updateData(User us)throws SQLException
    {
        PreparedStatement ps=Database.getInstance().getConnection().prepareStatement("select id from users where name=?");
        ps.setString(1,us.getName());
        if(ps.executeQuery().next())
            return false;
        ps=Database.getInstance().getConnection().prepareStatement("UPDATE users set name=? where id=?;");
        ps.setString(1,us.getName());
        ps.setInt(2,us.getId());
        ps.executeUpdate();
        return true;
    }
}