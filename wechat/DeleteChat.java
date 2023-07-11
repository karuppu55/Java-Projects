package com.zoho.WeChat;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.io.*; 
import java.util.HashMap;
import java.util.Map;
class DeleteChat
{
    public void changeData(User us)throws SQLException,IOException
    {
         System.out.println("=======================================================================================================");
        System.out.println("\n\t\t\t\tDELETE CHAT");
        System.out.println("=======================================================================================================");
		HashMap<Integer,MessageModel> chats=new ChatList().getData(us);
        if(chats.size()==0)
        {
            throw new DatNotFoundException("NO CHAT FOUND");
        }
        new ChatList().printData(chats);
        if(Validation.getInstance().isYNE("IF YOU WANT DELETE CHAT (Y) OTHERWISE (N)").toLowerCase().equals("y"))
        {
            do
            {
                if(chats.size()==0)
                {
                    throw new DatNotFoundException("NO CHAT FOUND");
                }
                int chatid=Validation.getInstance().isDigit("ID");
                while(!chats.containsKey(chatid))
                {
                    chatid=Validation.getInstance().isDigit("ID");
                }
                if(Validation.getInstance().isYNE("ARE YOU CONFIRM  (Y) OTHERWISE (N)").toLowerCase().equals("y"))
                {
                    
                    MessageModel msg=chats.get(chatid);
                    msg.setReceiverId(chatid);
                    msg.setSenderId(us.getId());
                    if(updateData(msg))
                    {
                        chats.remove(chatid);
                        System.out.println("\n\t*CHAT DELETER SUCCUSFULLY");
                    }
                }
                else
                {
                     System.out.println("\n\t*CANCELL PROCESS");
                }
            }while(Validation.getInstance().isYNE("CONTINUE TO (Y) OTHERWISE (N)").toLowerCase().equals("y"));
        }
    }
    public boolean updateData(MessageModel msg)throws SQLException
    {
        PreparedStatement ps=Database.getInstance().getConnection().prepareStatement("UPDATE friends set chat='f' Where id=(select id from friends where(sendid=? AND receiverid=?) OR (sendid=? AND receiverid=?));");
        ps.setInt(1,msg.getSenderId());
        ps.setInt(2,msg.getReceiverId());
        ps.setInt(3,msg.getReceiverId());
        ps.setInt(4,msg.getSenderId());
        ps.executeUpdate();
        return true;  
    }
}