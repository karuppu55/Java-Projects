package com.zoho.WeChat;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.io.*; 
class Notification
{
    public void notification(User us)throws SQLException,IOException
    {
        System.out.println("=======================================================================================================");
        System.out.println("\n\t\t\t\tNOTIFICATION");
        System.out.println("=======================================================================================================");
		getData(us);
    }
    public void getData(User us)throws SQLException,IOException
    {
        PreparedStatement ps=Database.getInstance().getConnection().prepareStatement("select c.id,c.userid,u.name,c.postid from likes c JOIN posts p ON p.id=c.postid JOIN users u ON c.userid=u.id where p.userid=? AND c.userid!=? AND c.viewstatus='f';");
        ps.setInt(1,us.getId());
        ps.setInt(2,us.getId());
        ResultSet rs=ps.executeQuery();
        int c=0;
        while(rs.next())
        {
            c++;
            System.out.println("\n\t\t->"+rs.getString(3)+" like your post (ID:"+rs.getInt(4)+")");
            ps=Database.getInstance().getConnection().prepareStatement("UPDATE likes set viewstatus='t' where id=?");
            ps.setInt(1,rs.getInt(1));
            ps.executeUpdate();
            if(c%9==0&&Validation.getInstance().isYNE("IF YOU GO NEXT PAGE (Y) OTHERVISE (N)").toLowerCase().equals("n"))
            {
                break;
            }
        }
        c=0;
        ps=Database.getInstance().getConnection().prepareStatement("select c.id,c.userid,u.name,c.postid,c.commend from commands c JOIN posts p ON p.id=c.postid JOIN users u ON c.userid=u.id where p.userid=? AND c.userid!=? AND c.viewstatus='f';");
        ps.setInt(1,us.getId());
        ps.setInt(2,us.getId());
        rs=ps.executeQuery();
        while(rs.next())
        {
            c++;
            System.out.println("\n\t\t->"+rs.getString(3)+" command your post (ID:"+rs.getInt(4)+") "+rs.getString(5));
            ps=Database.getInstance().getConnection().prepareStatement("UPDATE commands set viewstatus='t' where id=?");
            ps.setInt(1,rs.getInt(1));
            ps.executeUpdate();
            if(c%9==0&&Validation.getInstance().isYNE("IF YOU GO NEXT PAGE (Y) OTHERVISE (N)").toLowerCase().equals("n"))
            {
                break;
            }
        }
    }
}