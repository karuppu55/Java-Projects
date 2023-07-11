package com.zoho.WeChat;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.io.*; 
class NewsFeed
{
    public void feed(User us)throws SQLException,IOException
    {
        System.out.println("=======================================================================================================");
        System.out.println("\n\t\t\tNEWS FEED");
        System.out.println("=======================================================================================================");
        HashMap<Integer,PostModel> posts=getData(us);
        LoadingData(posts,us);
    }
    public void LoadingData(HashMap<Integer,PostModel> posts,User us)throws SQLException,IOException
    {
        System.out.println("_________________________________________________________________________");
        for(Map.Entry<Integer,PostModel> post:posts.entrySet())
        {
            PostModel pos=post.getValue();
            outer:
            while(true)
            {
                System.out.println("\n\tID:"+post.getKey());
                pos.setId(post.getKey());
                System.out.println("\n\tNAME    :"+pos.us.getName());
                System.out.println("\n\tCAPTION    :"+pos.getCaption());
                System.out.println("\n\tPOSTED ON    :"+pos.getPostedOn());
                System.out.println("\n\tLIKES :"+pos.getLike()+"\tCOMMANDS  :"+pos.getCommand());
                System.out.println("_________________________________________________________________________");
                String like=isLikeExists(post.getKey(),us.getId());
                System.out.println("\n\t1."+like+"\t2.COMMAND\t3.SAVE\t4.VIEW LIKES\t5.VIEW COMMAND\t6.NEXT");
                switch(Validation.getInstance().isDigit("YOUR CHOICE"))
                {
                    case 1:
                            if(like.equals("LIKE"))
                            {
                                if(addLike(us,pos))
                                {
                                    pos.setLike(pos.getLike()+1);
                                    System.out.println("\n\t\t**LIKED");
                                }
                            }
                            else if(unLike(us,pos)>0)
                            {
                                pos.setLike(pos.getLike()-1);
                                System.out.println("\n\t\t**UNLIKED");
                            }
                            break;
                    case 2:
                            String command=Validation.getInstance().isLetter("COMMAND");
                            if(addCommand(us,new PostModel(post.getKey(),command)))
                            {
                                pos.setCommand(pos.getCommand()+1);
                                System.out.println("\n\t\t**COMMANDED");
                            }
                            break;
                    case 3:
                            if(readDocument(pos.us.getName(),pos.getPost()))
                                System.out.println("\n\t\tFILE SAVED SUCCUSSFULLY");
                            break;
                    case 4:
                            System.out.println("____________________________LIKES_____________________________");
        
                            ArrayList<LikeModel> likes=getLikes(pos);
                            for(LikeModel lk:likes)
                            {
                                System.out.println("\n\tID  :"+lk.us.getId()+"\tNAME :"+lk.us.getName());
                            }
                            System.out.println("______________________________________________________________");
        
                            break;
                    case 5:
                            System.out.println("____________________________COMMANDS_____________________________");
                            ArrayList<CommandModel> commands=getCommands(pos);
                            for(CommandModel cmd:commands)
                            {
                                System.out.println("\n\tID  :"+cmd.us.getId()+"\tNAME :"+cmd.us.getName()+"\n\tCOMMAND  :"+cmd.getCommand());
                            }
                             System.out.println("________________________________________________________________");
                            break;
                    case 6:
                            break outer;
                    default:
                            System.out.println("\n\t\t**ENTER CORRECT OPTION");
                }
            }
            if(Validation.getInstance().isYesorNo("IF YOU WANT CONTINUE (y) OR (N)").toLowerCase().equals("n"))
                break;
                
        }
    }
    public boolean readDocument(String filename,byte[] data)throws IOException
	{
		File file=new File("//home//zoho//Downloads//"+filename);
		OutputStream os=new FileOutputStream(file);
		os.write(data);
		return true;
	}
    public ArrayList<LikeModel> getLikes(PostModel pos)throws SQLException
    {
        ArrayList<LikeModel> likes=new ArrayList<>();
        PreparedStatement ps=Database.getInstance().getConnection().prepareStatement("select u.id,u.name from users u JOIN likes l ON l.userid=u.id where postid=? AND l.status='t';");
        ps.setInt(1,pos.getId());
        ResultSet rs=ps.executeQuery();
        while(rs.next())   
            likes.add(new LikeModel(rs.getInt(1),rs.getString(2)));
        return likes;
    }
     public ArrayList<CommandModel> getCommands(PostModel pos)throws SQLException
    {
        ArrayList<CommandModel> likes=new ArrayList<>();
        PreparedStatement ps=Database.getInstance().getConnection().prepareStatement("select u.id,u.name,c.commend from users u JOIN commands c ON c.userid=u.id where postid=?;");
        ps.setInt(1,pos.getId());
        ResultSet rs=ps.executeQuery();
        while(rs.next())   
            likes.add(new CommandModel(rs.getInt(1),rs.getString(2),rs.getString(3)));
        return likes;
    }
    public String isLikeExists(int postid,int userid)throws SQLException
    {
        PreparedStatement ps=Database.getInstance().getConnection().prepareStatement("SELECT id from likes where userid=? AND postid=? AND status='t';");
        ps.setInt(1,userid);
        ps.setInt(2,postid);
        ResultSet rs=ps.executeQuery();
        if(rs.next())
            return "UNLIKE";
        return "LIKE";
    }
    public boolean addLike(User us,PostModel pm)throws SQLException
    {
        PreparedStatement ps=Database.getInstance().getConnection().prepareStatement("SELECT id from likes where userid=? AND postid=? AND status='f';");
        ps.setInt(1,us.getId());
        ps.setInt(2,pm.getId());
        ResultSet rs=ps.executeQuery();
        if(rs.next())
        {
            ps=Database.getInstance().getConnection().prepareStatement("UPDATE likes set status='t' where postid=? AND userid=?;");
            ps.setInt(1,pm.getId());
            ps.setInt(2,us.getId());
            if(ps.executeUpdate()>0)
                return true;
        }
        else
        {
            ps=Database.getInstance().getConnection().prepareStatement("INSERT INTO likes(userid,postid) VALUES(?,?) RETURNING id;");
            ps.setInt(1,us.getId());
            ps.setInt(2,pm.getId());
            rs=ps.executeQuery();  
            if(rs.next())
                return true; 
        }
        return false; 
    }
    public int unLike(User us,PostModel pm)throws SQLException
    {
        PreparedStatement ps=Database.getInstance().getConnection().prepareStatement("UPDATE likes set status='f' where status='t' AND postid=? AND userid=?;");
        ps.setInt(1,pm.getId());
        ps.setInt(2,us.getId());
        return ps.executeUpdate();  
         
    }
    public boolean addCommand(User us,PostModel pm)throws SQLException
    {
        PreparedStatement ps=Database.getInstance().getConnection().prepareStatement("INSERT INTO commands(userid,postid,commend) VALUES(?,?,?) RETURNING id;");
        ps.setInt(1,us.getId());
        ps.setInt(2,pm.getId());
        ps.setString(3,pm.getCommands());
        ResultSet rs=ps.executeQuery();  
        return rs.next(); 
    }
    public HashMap<Integer,PostModel> getData(User us)throws SQLException
    {
        HashMap<Integer,PostModel> posts=new HashMap<>();
        PreparedStatement ps=Database.getInstance().getConnection().prepareStatement("select p.id,u.name,p.post,p.caption,p.postedon,p.privacy,p.status,(select count(id) from likes where postid=p.id AND status='t') as count,(select count(id) from commands where postid=p.id AND status='t')as command,p.userid from posts p JOIN users u ON  u.id=p.userid where (p.userid IN(select receiverid from friends where sendid=? ) OR p.userid IN(select sendid from friends where receiverid=?) OR p.userid=?) AND (p.privacy='public' OR p.privacy='friends') AND p.status='t' AND p.postedon>CURRENT_DATE-3;");
        ps.setInt(1,us.getId());
        ps.setInt(2,us.getId());
        ps.setInt(3,us.getId());
        ResultSet rs=ps.executeQuery();
        while(rs.next())
            posts.put(rs.getInt(1),new PostModel(rs.getString(2),rs.getBytes(3),rs.getString(4),rs.getDate(5),rs.getString(6),rs.getBoolean(7),rs.getInt(8),rs.getInt(9),rs.getInt(10)));
        return posts;
    }
}