package com.zoho.WeChat;
class MessageView
{
    public void menu(User us)
    {
        boolean flag=true;
		while(flag)
		{
			try
			{
				System.out.println("=======================================================================================================");
				System.out.println("\n\t\t\tHOME");
				System.out.println("=======================================================================================================");
				System.out.print("\n\t\t\t1--->CHAT LIST");
                System.out.print("\n\t\t\t2--->SEARCH");
                System.out.print("\n\t\t\t3--->DELETE CHAT");
				System.out.print("\n\t\t\t4--->EXIT");
				switch(Validation.getInstance().isDigit("YOUR CHOICE"))
				{
					case 1:
							new ChatList().chatList(us);
                            break;
                    case 2:
                            new NewChat().chatList(us);
							break;
                    case 3:
							new DeleteChat().changeData(us);
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