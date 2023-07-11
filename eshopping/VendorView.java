package com.zoho.E_Shopping;
import java.sql.SQLException;
import java.io.IOException;
class VendorView
{
    public void menu(UserModel um)
    {
        boolean flag=true;
		while(flag)
		{
			try
			{
				System.out.println("=======================================================================================================");
				System.out.println("\n\t\t\tVENDOR MASTER");
				System.out.println("=======================================================================================================");
				System.out.print("\n\t\t\t1--->VIEW PROFILE");
                System.out.print("\n\t\t\t2--->ADD PRODUCTS");
				System.out.print("\n\t\t\t3--->VIEW PRODUCTS");
                System.out.print("\n\t\t\t4--->REMOVE PRODUCTS"); 
                System.out.print("\n\t\t\t5--->MANAGE ORDERS");   
				System.out.print("\n\t\t\t6--->LOGOUT");
				switch(Validation.getInstance().isDigit("YOUR CHOICE"))
				{
					case 1:
						System.out.println("=======================================================================================================");
						System.out.println("\n\t\t\tSHOP DETAILS");
						System.out.println("=======================================================================================================");
						System.out.println("\n\t\t\tSHOP NAME\t:"+um.sm.getShopName().toUpperCase());
						System.out.println("\n\tTYPE\t:"+um.sm.getType()+"\t\tAMOUNT\t:"+um.sm.getAmount());
						System.out.println("\n\tADDRESS	:\n\t\t"+um.ad.getDoorNo()+","+um.ad.getStreet()+","+um.ad.getCity()+","+um.ad.getDistrict()+"-"+um.ad.getPincode());
						System.out.println("\n\tOWNER	:\n\t\tNAME\t:"+um.getName()+"\t\tID\t:"+um.getId());
						System.out.println("\t\tMOBILE\t:"+um.getMobile());
						System.out.println("=======================================================================================================");
						break;
					case 2:
							new AddProduct().addProduct(um);
						break;
                    case 3:
							new ViewProduct().viewProduct(um.sm.getShopId());
						break;
                    case 4:
							new RemoveProduct().removeProduct(um.sm.getShopId());
						break;
                    case 5:
						new VendorManageOrder().menu(um);
						break;
					case 6:
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