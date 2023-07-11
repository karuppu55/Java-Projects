package com.zoho.WeChat;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.io.*; 
class ChangeProfile
{
    public void changeData(User us)throws SQLException,IOException
    {
        System.out.println("=======================================================================================================");
        System.out.println("\n\t\t\tUPDATE USER NAME");
        System.out.println("=======================================================================================================");
        System.out.println("\n\t\t\t**UPLOAD YOUR DB");
        String location=Validation.getInstance().isLocation("GIVE IMAGE LOCATION AND FILE NAME(eg://folder//..//filename.type)");
        File filelocation=new File(location);
        while(!new SignUp().checkFile(filelocation))
        {
            System.out.println("\n\t\t\t**DOCUMENT NOT FOUND");
            location=Validation.getInstance().isLocation("GIVE IMAGE AND LOCATION FILE NAME(eg://folder//..//filename.type)");
            filelocation=new File(location);
        }
        byte[] data=new SignUp().getFileData(filelocation);
        if(Validation.getInstance().isYesorNo("ARE YOU SURE CHANGE PROFILE (Y) OR (N)").toLowerCase().equals("y"))
        {
             us.p.setProfilePicture(data);
            if(updateData(us))
            {
                System.out.println("\n\t\t*PROFILE PICTURE UPDATE SUCCSSFULLY..");
            }
        }
    }
    public boolean updateData(User us)throws SQLException
    {
        PreparedStatement ps=Database.getInstance().getConnection().prepareStatement("UPDATE profile set profilepicture=? where userid=?;");
        ps.setBytes(1,us.p.getProfilePicture());
        ps.setInt(2,us.getId());
        ps.executeUpdate();
        return true;
    }
}