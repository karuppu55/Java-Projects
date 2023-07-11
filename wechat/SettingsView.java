package com.zoho.WeChat;
class SettingsView
{
    public boolean menu(User us)
    {
        boolean flag=true;
		while(flag)
		{
			try
			{
				System.out.println("=======================================================================================================");
				System.out.println("\n\t\t\tHOME");
				System.out.println("=======================================================================================================");
				System.out.print("\n\t\t\t1--->CHANGE USER NAME");
                System.out.print("\n\t\t\t2--->CHANGE EMAIL");
                System.out.print("\n\t\t\t3--->CHANGE PASSWORD");
				System.out.print("\n\t\t\t4--->CHANGE PROFILE PICTURE");
				System.out.print("\n\t\t\t5--->CHANGE PRIVACY");
                System.out.print("\n\t\t\t6--->DEACTIVATE ACCOUNT");  
				System.out.print("\n\t\t\t7--->LOGOUT");
				System.out.print("\n\t\t\t8--->BACK");
				switch(Validation.getInstance().isDigit("YOUR CHOICE"))
				{
					case 1:
                            new ChangeUserName().changeData(us);
                            break;
                    case 2:
							new ChangeMail().changeData(us);
							break;
                    case 3:
							new ChangePassword().changeData(us);
							break;
                    case 4:
							new ChangeProfile().changeData(us);
	    					break;
                    case 5:
							new ChangePrivacy().changeData(us);
    						break;
					case 6:
							new DeactivateAccount().changeData(us);
							break;
					case 7:
							if(Validation.getInstance().isYesorNo("ARE YOU SURE TO LOGOUT (Y) OR (N)").toLowerCase().equals("y"))
                            {
                                return false;
                            }
					case 8:
							return true;
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
		return true;
    }
}