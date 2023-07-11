package com.zoho.WeChat;
import java.util.Map;
import java.util.HashMap;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.io.*;
import java.util.Random;
import java.sql.Date;
class SignUp
{
    public void signUp()throws SQLException,IOException
    {
        System.out.println("=======================================================================================================");
        System.out.println("\n\t\t\tSIGN UP");
        System.out.println("=======================================================================================================");
		String email=Validation.getInstance().isMail("MAIL");
        String fullname=Validation.getInstance().isLetter("FULL NAME");
        String name=Validation.getInstance().isLetter("USER NAME");
        String gender=Validation.getInstance().isLetter("GENDER");
        Date dob=Validation.getInstance().isDate("DOB");
        String password=Validation.getInstance().isPassword("ENTER PASSWORD");
        while(!password.equals(Validation.getInstance().isPassword("RE-ENTER PASSWORD")))
            System.out.println("\n\tPASSWORD NOT MATCHED");
        if(Validation.getInstance().isYesorNo("CHECK ABOVE DETAIL AND CLICK (Y) TO CONTINUE").toLowerCase().equals("y"))
        {
            String otp=getOtp();
            System.out.println("\n\tYOUR OTP IS :"+otp);
            while(!(otp.equals(String.valueOf(Validation.getInstance().isOtp("OTP")))))
                System.out.println("\n\tENTER CORRECT OTP");
            int id=registration(new User(name,fullname,gender,email,dob,password));
            if(id==0)
            {

            }
            System.out.println("\nREGISTER SUCCUSSFULLY");
            if(Validation.getInstance().isYesorNo("IF YOU WANT TO ADD PROFILE").toLowerCase().equals("y"))
            {
                System.out.println("\n\t\t\t**UPLOAD YOUR DB");
                String location=Validation.getInstance().isLocation("GIVE IMAGE LOCATION AND FILE NAME(eg://folder//..//filename.type)");
                File filelocation=new File(location);
                while(!checkFile(filelocation))
                {
                    System.out.println("\n\t\t\t**DOCUMENT NOT FOUND");
                    location=Validation.getInstance().isLocation("GIVE IMAGE AND LOCATION FILE NAME(eg://folder//..//filename.type)");
                    filelocation=new File(location);
                }
                byte[] data=getFileData(filelocation);
                int id1=addProile(new Profile(id,filelocation.getName(),data));
                if(id1==0)
                {
                }
                System.out.println("\nPROFILE SUCCUSSFULLY");
            }   
        }
    }
    public byte[] getFileData(File location)throws IOException
	{
		InputStream in=new FileInputStream(location);
		byte[] data=new byte[(int)location.length()];
		in.read(data);
		in.close();
		return data;
	}
	public boolean checkFile(File location)throws IOException
	{
		if(location.exists())
		{
			return true;
		}
		return false;
	}
    public String getOtp()
    {
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<6;i++)
           sb.append(new Random().nextInt(10));
     return new String(sb);
    }
    public int registration(User us)throws SQLException
    {
        PreparedStatement ps=Database.getInstance().getConnection().prepareStatement("INSERT INTO users(name,fullname,gender,email,dob,password) VALUES (?,?,?,?,?,?) RETURNING id;");
        ps.setString(1,us.getName());
        ps.setString(2,us.getFullname());
        ps.setString(3,us.getGender());
        ps.setString(4,us.getEmail());
        ps.setDate(5,us.getDob());
        ps.setString(6,us.getPassword());
        ResultSet rs=ps.executeQuery();
        if(rs.next())
        {
            return rs.getInt("id");
        }
        return 0;
    }
    public int addProile(Profile p)throws SQLException
    {
        PreparedStatement ps=Database.getInstance().getConnection().prepareStatement("INSERT INTO profile(userid,name,profilepicture) VALUES (?,?,?) RETURNING id;");
        ps.setInt(1,p.getUserId());
        ps.setString(2,p.getName());
        ps.setBytes(3,p.getProfilePicture());
        ResultSet rs=ps.executeQuery();
        if(rs.next())
        {
            return rs.getInt("id");
        }
        return 0;
    }
}