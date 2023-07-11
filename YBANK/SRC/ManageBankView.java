package YBank;
import YBank.utils.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.io.*;
import java.sql.SQLException;
public class ManageBankView implements AdminCaller
{	
	public void adminCompile(LoginModel lm)
	{
		this.menu(lm);
	}
	public void menu(LoginModel lm)
	{
		ManageBankViewCaller bankview=null;
		boolean flag=true;
		while(flag)
		{
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("\nUSER ID :"+lm.getUserId()+"\t\t\t*MANAGE BANK DASHBOARD*\t\tPOSITION :"+lm.job.getJobType());
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("\n\t\t\t\t1.ADD BRANCH\n\t\t\t\t2.BRANCH DETAIL\n\t\t\t\t3.BRANCH TYPE CHANGE\n\t\t\t\t4.BRANCH EMPLOYEE\n\t\t\t\t5.EXIT");
			try
			{
				switch(Validation.getInstance().isNumber("ENTER YOUR CHOICE"))
				{
					case 1:
						bankview=new AddBranchView();
						break;
					case 2:
						bankview=new BranchDetailsView();
						break;
					case 3:
						bankview=new BranchUpdationView();
						break;
					case 4:
						bankview=new BranchEmployeeView();
						break;
					case 5:
						flag=false;
						bankview=null;
						break;
					default:
						bankview=null;
						System.out.println("\n\t\t\t**ENTER CORECT CHOICE!!");
				}
				if(bankview!=null)
					bankview.bankviewCompile(lm);
			}
			catch(UpdationFailedException ufe)
			{
				System.out.println("\n"+ufe.getMessage());
			}
			catch(DataExistsException dee)
			{
				System.out.println("\n"+dee.getMessage());
			}
			catch(DataNotFoundException dnfe)
			{
				System.out.println("\n"+dnfe.getMessage());
			}
			catch(SQLException sql)
			{
				System.out.println("\n"+sql.getMessage());
			}	
			catch(IOException io)
			{
				System.out.println(io);
			}
			catch(Exception e)
			{
				System.out.println("UNEXPECTED ERROR OCCURE IN MANAGE BANK MENU!!!"+e);
				e.printStackTrace();
			}	
		}
	}
	
}
