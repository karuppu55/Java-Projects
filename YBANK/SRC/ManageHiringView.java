package YBank;
import java.util.Scanner;
import YBank.utils.Selections;
import java.util.ArrayList;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.sql.SQLException;
public class ManageHiringView implements AdminCaller
{
	public void adminCompile(LoginModel lm)
	{
		this.menu(lm);
	}
	public void menu(LoginModel lm)
	{
		ManageHiringCaller managehiring=null;
		boolean flag=true;
		while(flag)
		{
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("\nUSER ID :"+lm.getUserId()+"\t\t\t*MANAGE HIRING DASHBOARD*\t\tPOSITION :"+lm.job.getJobType());
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("\n\t\t\t\t1.NEW JOB\n\t\t\t\t2.HIRE EMPLOYEE\n\t\t\t\t3.EXIT");
			try
			{
				switch(Validation.getInstance().isNumber("ENTER YOUR CHOICE"))
				{
					case 1:
						managehiring=new AddNewJobView();
						break;
					case 2:
						managehiring=new AddEmployeeView();
						break;
					case 3:
						managehiring=null;
						flag=false;
						break;
					default:
					managehiring=null;
					System.out.println("\n\t\t\t**ENTER CORECT CHOICE!!");
				}
				if(managehiring!=null)
					managehiring.managehiringcompile(lm);
			}
			catch(UpdationFailedException ufe)
			{
				System.out.println("\n"+ufe.getMessage());
			}
			catch(DataNotFoundException dnfe)
			{
				System.out.println("\n"+dnfe.getMessage());
			}
			catch(IOException io)
			{
				System.out.println(io);
			}
			catch(Exception e)
			{
				System.out.println("UNEXPECTED ERROR OCCURE IN HIRING MENU!!!"+e);
				e.printStackTrace();
			}		
		}
	}
}
