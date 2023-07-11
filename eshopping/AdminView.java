package com.zoho.E_Shopping;
import java.sql.SQLException;
import java.io.IOException;
class AdminView
{
    public void menu(UserModel sm)
    {
        boolean flag=true;
		while(flag)
		{
			try
			{
				System.out.println("=======================================================================================================");
				System.out.println("\n\t\t\tADMIN MASTER");
				System.out.println("=======================================================================================================");
				System.out.print("\n\t\t\t1--->VIEW PEOFILE");
                System.out.print("\n\t\t\t2--->REMOVE VENDOR");
				System.out.print("\n\t\t\t3--->LOGOUT");
				switch(Validation.getInstance().isDigit("YOUR CHOICE"))
				{
					case 1:
						    break;
					case 2:
							break;
                    case 3:
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