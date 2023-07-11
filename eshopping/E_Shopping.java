package com.zoho.E_Shopping;
import java.sql.SQLException;
import java.io.IOException;
class E_Shopping
{
    public void menu()
    {
        boolean flag=true;
		while(flag)
		{
			try
			{
				System.out.println("=======================================================================================================");
				System.out.println("\n\t\t\tE-SHOPPING");
				System.out.println("=======================================================================================================");
				System.out.print("\n\t\t\t1--->SIGN IN");
				System.out.print("\n\t\t\t2--->SIGN UP"); 
				System.out.print("\n\t\t\t3--->EXIT");
				switch(Validation.getInstance().isDigit("YOUR CHOICE"))
				{
					case 1:
                            new SignIn().signIn();
						    break;
					case 2:
                            new SignUp().signUp();
						    break;
					case 3:
							if(Validation.getInstance().isYesorNo("DO YOU WANT TO EXIT (Y) OR (N)").toLowerCase().equals("y"))
        					{
								Database.getInstance().closeConnection();
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
            */
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
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
    }
    public static void main(String[] args)
    {
        try
        {
            new Tables();
            new TableInsertion().insertData();
            new E_Shopping().menu();
        }
        catch(Exception e)
			{
				e.printStackTrace();
			}
    }
}