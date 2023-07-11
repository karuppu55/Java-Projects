package com.zoho.WeChat;
import java.sql.SQLException;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
class Home
{
    public void menu(User us)
    {
        boolean flag=true;
		while(flag)
		{
			try
			{
				int count=getCount(us);
				int msg=getMessage(us);
				System.out.println("=======================================================================================================");
				System.out.println("\n\t\t\tHOME");
				System.out.println("=======================================================================================================");
				System.out.print("\n\t\t\t1--->PROFILE");
                System.out.print("\n\t\t\t2--->NEWS FEED");
                System.out.print("\n\t\t\t3--->POST");
				System.out.print("\n\t\t\t4--->MESSAGES( "+msg+" )");
				System.out.print("\n\t\t\t5--->FRIENDS");
                System.out.print("\n\t\t\t6--->NOTIFICATION( "+count+" )"); 
                System.out.print("\n\t\t\t7--->SETTINGS");
				switch(Validation.getInstance().isDigit("YOUR CHOICE"))
				{
					case 1:
                            new ProfileView().profile(us);
                            break;
                    case 2:
							new NewsFeed().feed(us);
							break;
                    case 3:
							new PostView().menu(us);
							break;
                    case 4:
							new MessageView().menu(us);
	    					break;
                    case 5:
							new FriendsView().menu(us);
    						break;
					case 6:
							new Notification().notification(us);
							break;
					case 7:
							flag=new SettingsView().menu(us);
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
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
    }
	public int getCount(User us)throws SQLException
	{
		PreparedStatement ps=Database.getInstance().getConnection().prepareStatement("select id,(select count(id) from likes where postid=(select id from posts where userid=?) AND viewstatus='f' AND status='t' AND userid!=?)as like,(select count(id) from commands where postid=(select id from posts where userid=? )AND viewstatus='f' AND status='t' AND userid!=?)as commands from users where id=?;");
        ps.setInt(1,us.getId());
		ps.setInt(2,us.getId());
		ps.setInt(3,us.getId());
		ps.setInt(4,us.getId());
		ps.setInt(5,us.getId());
        ResultSet rs=ps.executeQuery();
		if(rs.next())
			return rs.getInt(2)+rs.getInt(3);
		return 0;
	}
	public int getMessage(User us)throws SQLException
	{
		PreparedStatement ps=Database.getInstance().getConnection().prepareStatement("select count(*) as count from(select senderid from message where  senderid in(select senderid from message where receiverid=? AND view_status='send') group by senderid) sub;");
        ps.setInt(1,us.getId());
        ResultSet rs=ps.executeQuery();
		if(rs.next())
			return rs.getInt(1);
		return 0;
	}
}