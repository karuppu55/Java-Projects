package com.zoho.WeChat;
import java.util.Map;
import java.util.HashMap;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.io.*; 
class PostPrivacy extends PostRemove
{
    public void privacy(User us)throws SQLException,IOException
    {
        System.out.println("=======================================================================================================");
        System.out.println("\n\t\t\tUPDATE POST PRIVACY");
        System.out.println("=======================================================================================================");
		HashMap<Integer,PostModel> posts=getData(us);
        if(posts.size()==0)
            throw new DatNotFoundException("NO POST FOUND");
        printData(posts);
        updatePrivacy(posts);
    }
    public void updatePrivacy(HashMap<Integer,PostModel> posts)throws IOException,SQLException
    {
        while(true)
        {
            if(Validation.getInstance().isYesorNo("PRESS Y TO UPDATE POST").toLowerCase().equals("n"))
                break;
            int id=Validation.getInstance().isDigit("POST ID");
            while(!posts.containsKey(id))
            {
                id=Validation.getInstance().isDigit("CORRECT POST ID");
            }
            String privacy=new PostUpload().getPrivacy();
            if(privacy.equals(posts.get(id).getPrivacy()))
            {
                throw new DatNotFoundException("POST ALREADY IN SAME PRIVACY");
            }
            if(updateStatus(id,privacy)==0)
            {

            }
            System.out.println("\n\t*POST UPDATED SUCCUSSFULLY");
        }
    }
    public int  updateStatus(int id,String privacy)throws SQLException
    {
        PreparedStatement ps=Database.getInstance().getConnection().prepareStatement("UPDATE posts set privacy=? where id=?;");
        ps.setString(1,privacy);
        ps.setInt(2,id);
        return ps.executeUpdate();
    }
}