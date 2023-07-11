package com.zoho.WeChat;
import java.sql.SQLException;
import java.io.IOException;
class FriendsView
{
    public void menu(User us)
    {
        boolean flag=true;
		while(flag)
		{
			try
			{
				System.out.println("=======================================================================================================");
				System.out.println("\n\t\t\tFRIENTS");
				System.out.println("=======================================================================================================");
				System.out.print("\n\t\t\t1--->VIEW");
                System.out.print("\n\t\t\t2--->REQUEST");
                System.out.print("\n\t\t\t3--->ADD FRIENDS");
				System.out.print("\n\t\t\t4--->EXIT");
				switch(Validation.getInstance().isDigit("YOUR CHOICE"))
				{
					case 1:
                            new FriendsDetail().friends(us);
                            break;
                    case 2:
							new FriendRequest().request(us);
							break;
                    case 3:
							new AddFriends().addFriend(us);
                        	break;
                    case 4:
                            if(Validation.getInstance().isYesorNo("DO YOU WANT TO EXIT (Y) OR (N)").toLowerCase().equals("y"))
                            {
                                flag=false;
                            }
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
}