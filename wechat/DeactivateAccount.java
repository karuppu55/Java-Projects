package com.zoho.WeChat;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.io.*; 
class DeactivateAccount
{
    public void changeData(User us)throws SQLException,IOException
    {
        System.out.println("=======================================================================================================");
        System.out.println("\n\t\t\tUPDATE USER NAME");
        System.out.println("=======================================================================================================");
        if(Validation.getInstance().isYesorNo("ARE YOU SURE CHANGE PROFILE (Y) OR (N)").toLowerCase().equals("y"))
        {
            us.setStatus(false);
            if(updateData(us))
            {
                System.out.println("\n\t\t*MAIL UPDATE SUCCSSFULLY..");
            }
        }
    }
    public boolean updateData(User us)throws SQLException
    {

        PreparedStatement ps=Database.getInstance().getConnection().prepareStatement("UPDATE users set status=? where id=?;");
        ps.setBoolean(1,us.getStatus());
        ps.setInt(2,us.getId());
        ps.executeUpdate();
        return true;
    }
}