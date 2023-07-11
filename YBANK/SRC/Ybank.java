package YBank;
import java.io.*;
import java.sql.SQLException;
public class Ybank
{
	public void menu()			//STARTING MENU
	{
		YbankCaller bank=null;
		boolean flag=true;
		while(flag)
		{
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("\n\t\t\t*_____WELCOME TO YBANK______*");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("\n\t\t\t\t1.LOGIN\n\t\t\t\t2.APPLICATION PAGE\n\t\t\t\t3.EXIT");
			try
			{
				int choice=Validation.getInstance().isNumber("ENTER YOUR CHOICE");
				switch(choice)
				{
					case 1:
						bank=new LoginView();
						break;
					case 2:
						bank=new ApplicationView();
						break;
					case 3:
						flag=false;
						bank=null;
						Database.getDatabaseInstance().closeConnection();
						//SingletonStatement.getStatement().closeStatement();
						if(Validation.getInstance().isYesorNo("ARE YOU SURE?(Y/N)").toLowerCase().equals("y"))
						{
							flag=false;
						}	
						break;
					default:
						bank=null;
						System.out.println("\nt\t\t **ENTER CORRECT CHOICE!!");
				}
				if(bank!=null)	
					bank.compile();	
			}
			catch(DataNotFoundException dnfe)
			{
				System.out.println("\n"+dnfe.getMessage());
			}
			catch(UpdationFailedException ufe)
			{
				System.out.println("\n"+ufe.getMessage());
			}
			catch(NumberFormatException num)
			{
				System.out.println("ENTER INPUT IN CORRECT FORMAT!!!");
			}
			catch(SQLException sql)
			{
				System.out.println("\n"+sql.getMessage()+""+sql);
				sql.printStackTrace();
			}	
			catch(IOException io)
			{
				System.out.println(io);
				
			}
			catch(Exception e)
			{
				System.out.println("UNEXPECTED ERROR OCCURE IN YBANK!!!");
			}
		}	
	}
	public static void main(String[] args)
	{
		try
		{
			new Tables().createTables();					//TABLE CREATION
			new Defaultdata().insertion();                          		     //DEFAULT DATA INSERTION
			new Ybank().menu();					       	//YBANK STARTING PAGE
		}
		catch(SQLException sql)
		{
			System.out.println("*DATABASE NOT CONNECTED");
		}
		catch(IOException io)
		{
			System.out.println("\n**PROPERTY FILES ARE MISSING!!");
		}
		catch(Exception e)
		{
			System.out.println("\nAN UNEXPECTED ERROR OCCURED PLEASE TRY AGAIN!!");
		}
	}
}
