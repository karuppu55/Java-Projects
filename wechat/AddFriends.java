package com.zoho.WeChat;
import java.util.Map;
import java.util.HashMap;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.io.*;
class AddFriends
{
    public void addFriend(User us)
    {
        System.out.println("=======================================================================================================");
        System.out.println("\n\t\t\tADD FRIENDS");
        System.out.println("=======================================================================================================");
		boolean flag=true;
		while(flag)
		{
			try
			{
				System.out.print("\n\t\t\t1--->SEARCH");
                System.out.print("\n\t\t\t2--->USER LIST");
				System.out.print("\n\t\t\t4--->BACK");
				switch(Validation.getInstance().isDigit("YOUR CHOICE"))
				{
					case 1:
                            searchFriend(us);
                            break;
                    case 2:
                            list(us);
							break;
                    case 3:
                            if(Validation.getInstance().isYesorNo("DO YOU WANT TO EXIT (Y) OR (N)").toLowerCase().equals("y"))
                            {
                                flag=false;
                            }
							break;
					default:
						System.out.println("\n\t**ENTER VALID CHOICE");
				}
            }
            /*catch(DataExistException dee)
			{
				System.out.println(dee.getMessage());
			}
			catch(DatNotFoundException dnf)
			{
				System.out.println(dnf.getMessage());
			}
			catch(SQLException sql)
			{
				sql.printStackTrace();
			}
			catch(IOException io)
			{	
				io.printStackTrace();
			}*/
            catch(DatNotFoundException dnf)
			{
				System.out.println(dnf.getMessage());
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
        }
    }
    public void searchFriend(User us)throws SQLException,IOException
    {
        System.out.println("=======================================================================================================");
        System.out.println("\n\t\t\tSEARCH FRIENDS");
        System.out.println("=======================================================================================================");
		String name=Validation.getInstance().isName("NAME");
        HashMap<Integer,User> user=getData(name+"%",us);
        if(user.size()==0)
        {
            throw new DatNotFoundException("NO DATA FOUND");
        }
        printData(user,us);
    }
    public void list(User us)throws SQLException,IOException
    {
        System.out.println("=======================================================================================================");
        System.out.println("\n\t\t\tYOUR LIST");
        System.out.println("=======================================================================================================");
        HashMap<Integer,User> user=getData(us);
        if(user.size()==0)
        {
            throw new DatNotFoundException("NO DATA FOUND");
        }
        printData(user,us);
    }
     public boolean sendRequest(RequestModel req)throws SQLException
    {
        PreparedStatement ps=Database.getInstance().getConnection().prepareStatement("INSERT INTO friends(sendid,receiverid) VALUES(?,?) RETURNING id;");
        ps.setInt(1,req.getSendId());
        ps.setInt(2,req.getReceiverId());
        ResultSet rs=ps.executeQuery();  
        return rs.next(); 
    }
    public void printData(HashMap<Integer,User> user,User use) throws IOException,SQLException
    {

        System.out.println("_________________________________________________________________________");
        for(Map.Entry<Integer,User> us:user.entrySet())
        {
            System.out.println("\n\tID:"+us.getKey());
            System.out.println("\n\tPROFILE NAME    :"+us.getValue().getName());
            System.out.println("_________________________________________________________________________");
            String option=Validation.getInstance().isYNE("IF YOU WANT TO ADD FRIENDS (Y) NEXT (N) EXIT(E)");
            if(option.toLowerCase().equals("y"))
            {
                if(!sendRequest(new RequestModel(use.getId(),us.getKey())))
                {

                }
                System.out.println("\n\t\t**REQUESTED");
            }
            else if(option.toLowerCase().equals("e"))
            {
                break;
            }
        }
        
    }
   
    public HashMap<Integer,User> getData(User us)throws SQLException
    {
        HashMap<Integer,User> hm=new HashMap<>();
        PreparedStatement ps=Database.getInstance().getConnection().prepareStatement("select u.id,u.name,u.fullname,u.gender,u.dob,u.email,u.bio,u.privacy,u.status,p.name,p.profilepicture,u.status from users u LEFT JOIN profile p ON p.userid=u.id LEFT JOIN friends f ON f.sendid=u.id where u.status='t' AND f.id NOT IN(select receiverid from friends where sendid=?) AND f.id NOT IN(select sendid from friends where receiverid=?) AND u.id!=?;");
        ps.setInt(1,us.getId());
        ps.setInt(2,us.getId());
        ps.setInt(3,us.getId());
        ResultSet rs=ps.executeQuery();
        while(rs.next())
        {
            hm.put(rs.getInt(1),new User(rs.getString(2),rs.getString(3),rs.getString(4),rs.getDate(5),rs.getString(7),rs.getString(8),rs.getString(10),rs.getBytes(11)));
        }
        return hm;
    }
    public HashMap<Integer,User> getData(String name,User us)throws SQLException
    {
        HashMap<Integer,User> hm=new HashMap<>();
        PreparedStatement ps=Database.getInstance().getConnection().prepareStatement("select u.id,u.name,u.fullname,u.gender,u.email,u.dob,u.bio,u.privacy,u.status,p.name,p.profilepicture,u.status from users u LEFT JOIN profile p ON p.userid=u.id LEFT JOIN friends f ON f.sendid=u.id where u.status='t' AND u.name LIKE '"+name+"' AND f.id NOT IN(select receiverid from friends where sendid=?) AND f.id NOT IN(select sendid from friends where receiverid=?) AND u.id!=?;");
        ps.setInt(1,us.getId());
        ps.setInt(2,us.getId());
        ps.setInt(3,us.getId());
        ResultSet rs=ps.executeQuery();
        while(rs.next())
        {
             hm.put(rs.getInt(1),new User(rs.getString(2),rs.getString(3),rs.getString(4),rs.getDate(6),rs.getString(7),rs.getString(8),rs.getString(10),rs.getBytes(11)));
        }
        return hm;
    }
}