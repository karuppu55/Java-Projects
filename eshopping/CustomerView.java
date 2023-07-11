package com.zoho.E_Shopping;
import java.sql.SQLException;
import java.io.IOException;
class CustomerView
{
    public void menu(UserModel um)
    {
        boolean flag=true;
		while(flag)
		{
			try
			{
				System.out.println("=======================================================================================================");
				System.out.println("\n\t\t\tE-SHOPPING\t\t\t"+um.getName().toUpperCase());
				System.out.println("=======================================================================================================");
				System.out.print("\n\t\t\t1--->PROFILE");
				System.out.print("\n\t\t\t2--->BROWSE PRODUCTS");
                System.out.print("\n\t\t\t3--->MANAGE ORDERS"); 
				System.out.print("\n\t\t\t4--->LOGOUT");
				switch(Validation.getInstance().isDigit("YOUR CHOICE"))
				{
					case 1:
						System.out.println("=======================================================================================================");
						System.out.println("\n\t\t\tYOUR PROFILE");
						System.out.println("=======================================================================================================");
						System.out.println("\n\tNAME\t:"+um.getName()+"\t\tID\t:"+um.getId());
						System.out.println("\tMOBILE\t:"+um.getMobile());
						System.out.println("\n\tADDRESS	:\n\t\t"+um.ad.getDoorNo()+","+um.ad.getStreet()+","+um.ad.getCity()+","+um.ad.getDistrict()+"-"+um.ad.getPincode());
						System.out.println("=======================================================================================================");
						break;
					case 2:
						new BrowseProduct().browseProduct(um);
						break;
                    case 3:
						new CustomerManageOrder().menu(um);
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
			}*/
			catch(DatNotFoundException enf)
			{
				System.out.println(enf.getMessage());
			}
			catch(SQLException sql)
			{
				sql.printStackTrace();
			}
			catch(IOException io)
			{
				
				io.printStackTrace();
			}
			catch(PaymentCancelledException dnf)
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