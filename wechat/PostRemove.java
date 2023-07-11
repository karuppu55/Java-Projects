package com.zoho.WeChat;
import java.util.Map;
import java.util.HashMap;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.io.*; 
class PostRemove
{
    final boolean STATUS=false; 
    public void remove(User us)throws SQLException,IOException
    {
        System.out.println("=======================================================================================================");
        System.out.println("\n\t\t\tREMOVE POST");
        System.out.println("=======================================================================================================");
		HashMap<Integer,PostModel> posts=getData(us);
        if(posts.size()==0)
            throw new DatNotFoundException("NO POST FOUND");
        printData(posts);
        delete(posts);
    }
    public void delete(HashMap<Integer,PostModel> posts)throws IOException,SQLException
    {
        while(true)
        {
            if(Validation.getInstance().isYesorNo("PRESS Y TO UPLOAD POST").toLowerCase().equals("n"))
                break;
            int id=Validation.getInstance().isDigit("POST ID");
            while(!posts.containsKey(id))
            {
                id=Validation.getInstance().isDigit("CORRECT POST ID");
            }
            if(updateStatus(id,STATUS)==0)
            {

            }
            System.out.println("\n\t*POST DELETED SUCCUSSFULLY");
        }
    }
    public void printData(HashMap<Integer,PostModel> posts)throws SQLException,IOException
    {
        System.out.println("_________________________________________________________________________");
        for(Map.Entry<Integer,PostModel> post:posts.entrySet())
        {
            System.out.println("\n\tID:"+post.getKey());
            System.out.println("\n\tCAPTION    :"+post.getValue().getCaption());
            System.out.println("\n\tPRIVACY    :"+post.getValue().getPrivacy());
            System.out.println("\n\tPOSTED ON    :"+post.getValue().getPostedOn());
            System.out.println("\n\tstatus    :"+post.getValue().getStatus());
            System.out.println("_________________________________________________________________________");
        }
        
    }
    public int  updateStatus(int id,boolean status)throws SQLException
    {
        PreparedStatement ps=Database.getInstance().getConnection().prepareStatement("UPDATE posts set status=? where id=?;");
        ps.setBoolean(1,status);
        ps.setInt(2,id);
        return ps.executeUpdate();
    }
    public HashMap<Integer,PostModel> getData(User us)throws SQLException
    {
        HashMap<Integer,PostModel> hm=new HashMap<>();
        PreparedStatement ps=Database.getInstance().getConnection().prepareStatement("select p.id,p.post,p.caption,p.postedon,p.privacy,p.status from posts p JOIN users u ON p.userid=u.id WHERE u.id=? AND p.status='t';");
        ps.setInt(1,us.getId());
        ResultSet rs=ps.executeQuery();
        while(rs.next())
        {
             hm.put(rs.getInt(1),new PostModel(rs.getBytes(2),rs.getString(3),rs.getString(5),rs.getDate(4),rs.getBoolean(6)));
        }
        return hm;
    }
}