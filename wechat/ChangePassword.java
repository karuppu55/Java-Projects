package com.zoho.WeChat;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.io.*; 
class ChangePassword
{
    public void changeData(User us)throws SQLException,IOException
    {
        System.out.println("=======================================================================================================");
        System.out.println("\n\t\t\tUPDATE USER NAME");
        System.out.println("=======================================================================================================");
        String password=Validation.getInstance().isPassword("ENTER NEW PASSWORD");
        while(!password.equals(Validation.getInstance().isPassword("RE-ENTER NEW PASSWORD")))
            System.out.println("\n\tPASSWORD NOT MATCHED");
        if(Validation.getInstance().isYesorNo("ARE YOU SURE CHANGE PROFILE (Y) OR (N)").toLowerCase().equals("y"))
        {
            us.setPassword(password);
            if(updateData(us))
            {
                System.out.println("\n\t\t*PASSWORD UPDATE SUCCSSFULLY..");
            }
            else
            {
                System.out.println("\n\t\t*PASSWORD ALREADY EXISTS");
            }
        }
    }
    public boolean updateData(User us)throws SQLException
    {
        PreparedStatement ps=Database.getInstance().getConnection().prepareStatement("select id from users where name=?");
        ps.setString(1,us.getName());
        if(ps.executeQuery().next())
            return false;
        ps=Database.getInstance().getConnection().prepareStatement("UPDATE users set password=? where  id=?;");
        ps.setString(1,us.getName());
        ps.setInt(2,us.getId());
        ps.executeUpdate();
        return true;
    }
}