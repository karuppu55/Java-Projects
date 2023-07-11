package com.zoho.WeChat;
import java.util.Map;
import java.util.HashMap;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.io.*; 
class NewChat extends FriendsDetail implements IFriendsData<FriendsModel>
{
    public void chatList(User us)throws SQLException,IOException
    {
        System.out.println("=======================================================================================================");
        System.out.println("\n\t\t\tSEARCH FRIEND");
        System.out.println("=======================================================================================================");
		HashMap<Integer,FriendsModel> friends=getData(us);
        printData(friends);
        if(Validation.getInstance().isYesorNo("IF YOU WANT TO CHAT (Y) OTHERVISE (N)").toLowerCase().equals("y"))
            {
                int id=Validation.getInstance().isDigit("FRIEND ID");
                while(!friends.containsKey(id))
                {
                    id=Validation.getInstance().isDigit("FRIEND ID");
                }
                if(friends.get(id).getChatStatus()==false)
                {
                    FriendsModel frd=friends.get(id);
                    frd.setId(id);
                    addToChat(frd,us);
                    System.out.println("\n\tSAY HI TO START THE CHAT");
                }
                String msg=Validation.getInstance().isLetter("MESSAGE");
                if(sendMessage(new ChatModel(us.getId(),id,msg)))
                {
                    System.out.println("\n\t*MESSAGE SEND SUCCUSSFULLY");
                }
            }
    }
    public void printData( HashMap<Integer,FriendsModel> friends)throws SQLException,IOException
    {
        String name=Validation.getInstance().isLetter("NAME");
        int count=0;
        System.out.println("_________________________________________________________________________");
        for(Map.Entry<Integer,FriendsModel> friend:friends.entrySet())
        {
            if(friend.getValue().getName().contains(name))
            {
                count++;
                System.out.println("\n\tID:"+friend.getKey());
                System.out.println("\n\tPROFILE NAME    :"+friend.getValue().getName());
                System.out.println("_________________________________________________________________________");
                if(count%5==0&&Validation.getInstance().isYesorNo("IF YOU GO NEXT PAGE (Y) OTHERVISE (N)").toLowerCase().equals("n"))
                {
                    break;
                }
            }
            
        }
    }
    public void addToChat(FriendsModel friend,User us)throws SQLException
    {
        PreparedStatement ps=Database.getInstance().getConnection().prepareStatement("UPDATE friends set chat='t' Where id=(select id from friends where(sendid=? AND receiverid=?) OR (sendid=? AND receiverid=?));");
        ps.setInt(1,friend.getId());
        ps.setInt(2,us.getId());
        ps.setInt(3,us.getId());
        ps.setInt(4,friend.getId());
        ps.executeUpdate();  
    }
    public boolean sendMessage(ChatModel chat)throws SQLException
    {
        PreparedStatement ps=Database.getInstance().getConnection().prepareStatement("INSERT INTO message(senderid,receiverid,message) VALUES(?,?,?) RETURNING id;");
        ps.setInt(1,chat.getSenderId());
        ps.setInt(2,chat.getReceiverId());
        ps.setString(3,chat.getMessage());
        ResultSet rs=ps.executeQuery();  
        return rs.next();
    }
    @Override
    public HashMap<Integer,FriendsModel> getData(User us)throws SQLException
    {
        HashMap<Integer,FriendsModel> hm=new HashMap<>();
        PreparedStatement ps=Database.getInstance().getConnection().prepareStatement("select u.id,u.name,u.fullname,u.gender,u.dob,u.bio,u.privacy,p.name,p.profilepicture,(select chat from friends where (sendid=u.id AND receiverid=?) OR(sendid=? AND receiverid=u.id) )as chatstatus from users u LEFT JOIN profile p ON p.userid=u.id where u.id IN (select receiverid from friends where sendid=? AND status='friends') OR u.id IN (select sendid from friends where receiverid=? AND status='friends');");
        ps.setInt(1,us.getId());
        ps.setInt(2,us.getId());
        ps.setInt(3,us.getId());
        ps.setInt(4,us.getId());
        ResultSet rs=ps.executeQuery();
        while(rs.next())
        {
             hm.put(rs.getInt(1),new FriendsModel(rs.getString(2),rs.getString(3),rs.getString(4),rs.getDate(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getBytes(9),rs.getBoolean(10)));
        }
        return hm;
    }
}