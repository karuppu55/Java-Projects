package com.zoho.WeChat;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.io.*; 
class ChatList{
    public void chatList(User us)throws SQLException,IOException
    {
        System.out.println("=======================================================================================================");
        System.out.println("\n\t\t\t\tCHAT LIST");
        System.out.println("=======================================================================================================");
		HashMap<Integer,MessageModel> chats=getData(us);
        if(chats.size()==0)
        {
            throw new DatNotFoundException("NO CHAT FOUND");
        }
        printData(chats);
        if(Validation.getInstance().isYNE("IF YOU WANT VIEW MESSAGE (Y) OTHERWISE (N)").toLowerCase().equals("y"))
        {
            do
            {
                int chatid=Validation.getInstance().isDigit("ID");
                while(!chats.containsKey(chatid))
                {
                    chatid=Validation.getInstance().isDigit("ID");
                }
                MessageModel msg=chats.get(chatid);
                msg.setReceiverId(chatid);
                msg.setSenderId(us.getId());
                viewMessage(msg);
                if(Validation.getInstance().isYNE("IF YOU WANT SEND MESSAGE (Y) OTHERWISE (N)").toLowerCase().equals("y"))
                {
                    do
                    {
                        String message=Validation.getInstance().isLetter("ENTER YOUR MESSAGE");
                        if(new NewChat().sendMessage(new ChatModel(us.getId(),chatid,message)))
                            System.out.println("\n\t*MESSAGE SEND SUCCUSSFULLY");
                        viewMessage(msg);
                    }while(Validation.getInstance().isYNE("CONTINUE TO SEND MESSAGE (Y) OTHERWISE (N)").toLowerCase().equals("y"));
                }
            }
            while(Validation.getInstance().isYNE("CONTINUE TO VIEW MESSAGE (Y) OTHERWISE (N)").toLowerCase().equals("y"));
        }
    }
    public void viewMessage(MessageModel msg)throws SQLException,IOException
    {
        System.out.println("_________________________________________________________________________");
        System.out.println("\n\t\t\t"+msg.getReceiver());
        System.out.println("_________________________________________________________________________");
        getData(msg);
    }
    public void printData(HashMap<Integer,MessageModel> chats)throws IOException
    {
        int count=0;
        for(Map.Entry<Integer,MessageModel> chat:chats.entrySet())
        {
                count++;
                System.out.println("_________________________________________________________________________");
                System.out.print("\n\tID:"+chat.getKey());
                System.out.print("\n\t\t\t\t\t\tUNREAD    :"+chat.getValue().getCount());
                System.out.print("\n\tPROFILE NAME    :"+chat.getValue().getReceiver());
                System.out.println("\n_________________________________________________________________________");
                if(count%11==0&&Validation.getInstance().isYesorNo("IF YOU GO NEXT PAGE (Y) OTHERVISE (N)").toLowerCase().equals("n"))
                {
                    break;
                }
        }
    }
    public HashMap<Integer,MessageModel> getData(User us)throws SQLException
    {
        HashMap<Integer,MessageModel> hm=new HashMap<>();
        PreparedStatement ps=Database.getInstance().getConnection().prepareStatement("select u.id,u.name,(select count(id) from message where senderid=u.id AND receiverid=? AND view_status='send' and status='t')as msgcount from users u where u.id in(select sendid from friends where receiverid=? AND status='friends' AND chat='t') OR u.id in(select receiverid from friends where sendid=? AND status='friends'AND chat='t');");
        ps.setInt(1,us.getId());
        ps.setInt(2,us.getId());
        ps.setInt(3,us.getId());
        ResultSet rs=ps.executeQuery();
        while(rs.next())
        {
             hm.put(rs.getInt(1),new MessageModel(rs.getString(2),rs.getInt(3)));
        }
        return hm;
    }
    public void getData(MessageModel msg)throws SQLException,IOException
    {
        PreparedStatement ps=Database.getInstance().getConnection().prepareStatement("select m.id,m.senderid,m.receiverid,m.message,m.view_status,m.sendedon from message m where (senderid=? AND receiverid=?) OR (receiverid=? AND senderid=?);");
        ps.setInt(1,msg.getSenderId());
        ps.setInt(2,msg.getReceiverId());
        ps.setInt(3,msg.getSenderId());
        ps.setInt(4,msg.getReceiverId());
        ResultSet rs=ps.executeQuery();
        int count=0;
        while(rs.next())
        {
            if(rs.getInt(2)==msg.getReceiverId())
                System.out.print("\n\t\t("+rs.getDate(6)+") "+rs.getString(4));
            else    
                System.out.print("\n\t\t\t\t"+rs.getString(4)+" ("+rs.getDate(6)+")");
          
            if(msg.getSenderId()==rs.getInt(3)&&rs.getString(5).equals("send"))
                UpdateViewStatus(rs.getInt(1),"read");
        }
        System.out.println();
    }
    public void UpdateViewStatus(int id,String status)throws SQLException
    {
        PreparedStatement ps=Database.getInstance().getConnection().prepareStatement("UPDATE message set view_status=? where id=?;");
        ps.setString(1,status);
        ps.setInt(2,id);
        ps.executeUpdate();
        ps.close();
    }
}