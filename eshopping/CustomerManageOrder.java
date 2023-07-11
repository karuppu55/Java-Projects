package com.zoho.E_Shopping;
import java.sql.SQLException;
import java.io.IOException;
class CustomerManageOrder
{
    public void menu(UserModel um)
    {
        boolean flag=true;
		while(flag)
		{
			try
			{
				System.out.println("=======================================================================================================");
				System.out.println("\n\t\t\tMANAGE ORDER MASTER");
				System.out.println("=======================================================================================================");
				System.out.print("\n\t\t\t1--->VIEW ORDERS");
                System.out.print("\n\t\t\t2--->RETURN ORDER");
				System.out.print("\n\t\t\t3--->CANCEL ORDER"); 
				System.out.print("\n\t\t\t4--->EXIT");
				switch(Validation.getInstance().isDigit("YOUR CHOICE"))
				{
					case 1:
						new OrderHistory().orderHistory(um);
						break;
					case 2:
						new ReturnOrder().returnOrder(um);
						break;
                    case 3:
						new CancelOrder().cancelOrder(um);
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
			}
			catch(DatNotFItemDeliveredExceptionoundException dnf)
			{
				System.out.println(dnf.getMessage());
			}*/
			catch(ItemNotDeliveredException dnf)
			{
				System.out.println(dnf.getMessage());
			}
			catch(ItemDeliveredException dnf)
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
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
    }
}