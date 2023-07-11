package com.zoho.E_Shopping;
import java.sql.SQLException;
import java.io.IOException;
class VendorManageOrder
{
    public void menu(UserModel um)
    {
        boolean flag=true;
		while(flag)
		{
			try
			{
				System.out.println("=======================================================================================================");
				System.out.println("\n\t\t\tMANAGE ORDER");
				System.out.println("=======================================================================================================");
				System.out.print("\n\t\t\t1--->ACCEPT ORDER");
                System.out.print("\n\t\t\t2--->ACCEPT RETURN");
				System.out.print("\n\t\t\t3--->ACCPET CANCELATION"); 
				System.out.print("\n\t\t\t4--->EXIT");
				switch(Validation.getInstance().isDigit("YOUR CHOICE"))
				{
					case 1:
							new AcceptOrder().acceptOrder(um);
						break;
					case 2:
							new AcceptReturn().acceptReturn(um);
						break;
                    case 3:
							new AcceptCancel().acceptCancel(um);
						break;
                    case 4:
							flag=false;
							break;
					default:
						System.out.println("\n\t**ENTER VALID CHOICE");
				}
			}
			/*catch(DataExistException dee)
			{
				System.out.println(dee.getMessage());
			}*/
			catch(SQLException sql)
			{
				sql.printStackTrace();
			}
			catch(IOException io)
			{
				io.printStackTrace();
			}
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