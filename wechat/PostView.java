package com.zoho.WeChat;
import java.sql.SQLException;
import java.io.IOException;
class PostView
{
    public void menu(User us)
    {
        boolean flag=true;
		while(flag)
		{
			try
			{
				System.out.println("=======================================================================================================");
				System.out.println("\n\t\t\tPOST");
				System.out.println("=======================================================================================================");
				System.out.print("\n\t\t\t1--->UPLOAD");
                System.out.print("\n\t\t\t2--->REMOVE");
                System.out.print("\n\t\t\t3--->PRIVACY");
				System.out.print("\n\t\t\t4--->BACK");
				switch(Validation.getInstance().isDigit("YOUR CHOICE"))
				{
					case 1:
							new PostUpload().upload(us);
                            break;
                    case 2:
							new PostRemove().remove(us);
							break;
                    case 3:
							new PostPrivacy().privacy(us);
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
			catch(DatNotFoundException dnf)
			{
				System.out.println(dnf.getMessage());
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
    }
}