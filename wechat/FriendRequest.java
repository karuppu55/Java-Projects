package com.zoho.WeChat;
import java.util.Map;
import java.util.HashMap;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.io.*;
class FriendRequest implements IFriendsData<RequestModel>
{
    public void request(User us)throws SQLException,IOException
    {
        System.out.println("=======================================================================================================");
        System.out.println("\n\t\t\tFRIENDS REQUEST");
        System.out.println("=======================================================================================================");
				
        HashMap<Integer,RequestModel> request=getData(us);
        printData(request);
    }
    @Override
    public void printData( HashMap<Integer,RequestModel> request)throws SQLException,IOException
    {

        System.out.println("_________________________________________________________________________");
        for(Map.Entry<Integer,RequestModel> req:request.entrySet())
        {
            System.out.println("\n\tID:"+req.getKey());
            System.out.println("\n\tPROFILE NAME    :"+req.getValue().us.getName());
            System.out.println("_________________________________________________________________________");
            String option=Validation.getInstance().isYNE("IF YOU WANT TO ADD FRIENDS (Y) NEXT (N) REJECT (C) EXIT(E)");
            if(option.toLowerCase().equals("y"))
            {
                if(updateStatus(new RequestModel(req.getKey(),"friends"))==0)
                {

                }
                System.out.println("\n\t\t**ACCEPTED");
            }
            else if(option.toLowerCase().equals("c"))
            {
                if(updateStatus(new RequestModel(req.getKey(),"cancelled"))==0)
                {

                }
                System.out.println("\n\t\t**REQUEST CANCELLED");
            }
            else if(option.toLowerCase().equals("e"))
            {
                break;
            }
        }
    }
    public int updateStatus(RequestModel req)throws SQLException
    {
        PreparedStatement ps=Database.getInstance().getConnection().prepareStatement("Update friends set status=? where id=?;");
        ps.setString(1,req.getStatus());
        ps.setInt(2,req.getId());
        return ps.executeUpdate();
    }
    @Override
    public HashMap<Integer,RequestModel> getData(User us)throws SQLException
    {
        HashMap<Integer,RequestModel> hm=new HashMap<>();
        PreparedStatement ps=Database.getInstance().getConnection().prepareStatement("select f.id,u.name,u.fullname,u.gender,u.dob,u.bio,u.privacy,p.name,p.profilepicture from users u LEFT JOIN profile p ON p.userid=u.id LEFT JOIN friends f ON f.sendid=u.id where f.receiverid=? AND f.status='requested';");
        ps.setInt(1,us.getId());
        ResultSet rs=ps.executeQuery();
        while(rs.next())
        {
             hm.put(rs.getInt(1),new RequestModel(rs.getString(2),rs.getString(3),rs.getString(4),rs.getDate(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getBytes(9)));
        }
        return hm;
    }
}