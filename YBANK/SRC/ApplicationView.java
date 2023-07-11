package YBank;
import java.util.Scanner;	
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import YBank.utils.*;
import java.io.*;
import java.sql.Statement;
import java.sql.SQLException;
public class ApplicationView implements YbankCaller
{
	public void compile()
	{
		this.menu();
	}
	public static void menu() 
	{
		ApplicationCaller application=null;
		boolean flag=true;
		while(flag)
		{
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("\n\t\t\t___APPLICANT MENU___");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("\n\t\t\t\t1.REGISTRATION \n\t\t\t\t2.JOB APPLY \n\t\t\t\t3.APPLY STATUS\n\t\t\t\t4.EXIT");
			try
			{
				switch(Validation.getInstance().isNumber("ENTER YOUR CHOICE"))
				{
					case 1:
						application=new RegistrationView();
						break;
					case 2:
						application=new JobApplyView();
						break;
					case 3:
						application=new ApplicationStatusView();
						break;
					case 4:
						flag=false;
						application=null;
						break;
					default:
						application=null;
						System.out.println("\n\t\t\t**ENTER CORECT CHOICE!!");
				}
				if(application!=null)
				{
					application.appCompile();
				}
			}
			catch(DataExistsException dee)
			{
				System.out.println("\n"+dee.getMessage());
			}
			catch(NotEligibleException nee)
			{
				System.out.println("\n"+nee.getMessage());
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
				System.out.println("UNEXPECTED ERROR OCCURED!!!");
			}
		}
	}
}
