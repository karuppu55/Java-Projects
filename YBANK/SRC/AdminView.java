package YBank;
import java.util.Scanner;
import java.io.*;
import java.sql.SQLException;
public class AdminView implements LoginCaller
{
	public void loginCompile(LoginModel lm)
	{
		this.menu(lm);
	}
	public void menu(LoginModel lm)
	{
		boolean flag=true;
		while(flag)
		{
			AdminCaller admin=null;
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("\nUSER ID :"+lm.getUserId()+"\t\t\t___ADMIN PAGE__\t\tPOSITION :"+lm.job.getJobType());
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("\n\t\t\t\t1.MANAGE BANK\n\t\t\t\t2.MANAGE EMPLOYEE\n\t\t\t\t3.HIRING\n\t\t\t\t4.CHANGE PASSWORD\n\t\t\t\t5.LOGOUT");
			try
			{
				switch(Validation.getInstance().isNumber("ENTER YOUR CHOICE"))
				{
					case 1:
						admin=new ManageBankView();
						break;	
					case 2:
						admin=new ManageEmployeeView();
						break;
					case 3:
						admin=new ManageHiringView();
						break;
					case 4:
						admin=null;
						flag=false;
						break;
					case 5:
						admin=null;
						if(Validation.getInstance().isYesorNo("ARE YOU SURE?(Y/N)").toLowerCase().equals("y"))
						{
							flag=false;
						}
						break;
					default:
						admin=null;
						System.out.println("\n\t\t*ENTER CORRECT CHOICE");
				}
				if(admin!=null)
					admin.adminCompile(lm);
			}
			/*catch(UpdationFailedException ufe)
			{
				System.out.println("\n"+ufe.getMessage());
			}
			catch(SQLException sql)
			{
				System.out.println("\n"+sql.getMessage());
			}	
			catch(IOException io)
			{
				System.out.println(io);
			}*/
			catch(Exception e)
			{
				System.out.println("UNEXPECTED ERROR OCCURE IN ADMIN MENU!!!");
			}
		}
	}
	
	
	
}
