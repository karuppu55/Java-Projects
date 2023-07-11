package com.zoho.WeChat;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.io.*;
class PostUpload
{
    public void upload(User us)throws SQLException,IOException
    {
        System.out.println("=======================================================================================================");
        System.out.println("\n\t\t\tUPLOAD POST");
        System.out.println("=======================================================================================================");
		String location=Validation.getInstance().isLocation("GIVE IMAGE LOCATION AND FILE NAME(eg://folder//..//filename.type)");
        File filelocation=new File(location);
        while(!checkFile(filelocation))
        {
            System.out.println("\n\t\t\t**DOCUMENT NOT FOUND");
            location=Validation.getInstance().isLocation("GIVE IMAGE AND LOCATION FILE NAME(eg://folder//..//filename.type)");
            filelocation=new File(location);
        }
        byte[] data=getFileData(filelocation);
        String caption=Validation.getInstance().isLetter("CAPTION");
        String privacy=getPrivacy();
        if(Validation.getInstance().isYesorNo("PRESS Y TO UPLOAD POST").toLowerCase().equals("y"))
        {
            if(!addPost(new PostModel(us.getId(),data,caption,privacy)))
            {

            }
            System.out.println("\n\t**POST UPLOADED SUCCUSSFULLY");
        }
    }
    public boolean addPost(PostModel post)throws SQLException
    {
        PreparedStatement ps=Database.getInstance().getConnection().prepareStatement("INSERT INTO posts(userid,post,caption,privacy) VALUES(?,?,?,?) RETURNING id;");
        ps.setInt(1,post.getUserId());
        ps.setBytes(2,post.getPost());
        ps.setString(3,post.getCaption());
        ps.setString(4,post.getPrivacy());
        ResultSet rs=ps.executeQuery();  
        return rs.next(); 
    }
    public String getPrivacy()throws IOException
    {
        System.out.println("\n\t\t\t1.PUBLIC\n\t\t\t2.PRIVATE\n\t\t\t3.FRIENDS");
        boolean flag=true;
        while(flag)
        {
            switch(Validation.getInstance().isDigit("ENTER YOUR OPTION"))
            {
                case 1:
                        flag=false;
                        return "public";
                case 2:
                        flag=false;
                        return "private";
                case 3:
                        flag=false;
                        return "friends";
            }
        }
        return "";
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
}