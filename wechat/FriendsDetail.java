package com.zoho.WeChat;
import java.util.Map;
import java.util.HashMap;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.io.*; 
class FriendsDetail implements IFriendsData<FriendsModel>
{
    public void friends(User us)throws SQLException,IOException
    {
        System.out.println("=======================================================================================================");
        System.out.println("\n\t\t\tFRIENDS");
        System.out.println("=======================================================================================================");
				
        HashMap<Integer,FriendsModel> request=getData(us);
        printData(request);
    }
    @Override
    public void printData( HashMap<Integer,FriendsModel> friends)throws SQLException,IOException
    {
        int count=0;

        System.out.println("_________________________________________________________________________");
        for(Map.Entry<Integer,FriendsModel> friend:friends.entrySet())
        {
            count++;
            System.out.println("\n\tID:"+friend.getKey());
            System.out.println("\n\tPROFILE NAME    :"+friend.getValue().getName());
            System.out.println("_________________________________________________________________________");
            if(count%5==0&&Validation.getInstance().isYNE("IF YOU GO NEXT PAGE (Y) OTHERVISE (N)").toLowerCase().equals("n"))
            {
                break;
            }
        }
    }
    @Override
    public HashMap<Integer,FriendsModel> getData(User us)throws SQLException
    {
        HashMap<Integer,FriendsModel> hm=new HashMap<>();
        PreparedStatement ps=Database.getInstance().getConnection().prepareStatement("select u.id,u.name,u.fullname,u.gender,u.dob,u.bio,u.privacy,p.name,p.profilepicture from users u LEFT JOIN profile p ON p.userid=u.id where u.id IN (select receiverid from friends where sendid=? AND status='friends') OR u.id IN (select sendid from friends where receiverid=? AND status='friends');");
        ps.setInt(1,us.getId());
        ps.setInt(2,us.getId());
        ResultSet rs=ps.executeQuery();
        while(rs.next())
        {
             hm.put(rs.getInt(1),new FriendsModel(rs.getString(2),rs.getString(3),rs.getString(4),rs.getDate(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getBytes(9)));
        }
        return hm;
    }
}