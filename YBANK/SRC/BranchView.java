package YBank;
import java.util.Scanner;
import YBank.utils.*;
import YBank.Validation;
import java.util.ArrayList;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.sql.SQLException;
public class BranchView implements BankSoftwareCaller
{
	public void banksoftwareCompile(LoginModel lm)
	{
		this.menu(lm);
	}
	public void menu(LoginModel lm)
	{
		boolean flag=true;
		while(flag)
		{
			BranchCaller branch=null;
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("\nUSERID :"+lm.getUserId()+"\t\t\tBRANCH DETAIL\t\t\tPOSITION :"+lm.job.getJobType());
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("\n\t\t\t\t1.BRANCH DETAIL\n\t\t\t\t2.CASH BOOK\n\t\t\t\t3.EXIT");
			try
			{
				switch(Validation.getInstance().isNumber("ENTER YOUR CHOICE"))
				{
					case 1:
						branch=new CashBookView();
						break;
					case 2:
						branch=new BranchDetailView();
						break;
					case 3:
						branch=null;
						flag=false;
						break;
					default:
						branch=null;
						System.out.println("\n\t\t\t**ENTER CORECT CHOICE!!");
				}
				if(branch!=null)
					branch.branchCompile(lm);
			}
			catch(AccountNotFoundException anfe)
			{
				System.out.println("\n"+anfe.getMessage());
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
			catch(NumberFormatException num)
			{
				System.out.println("ENTER INPUT IN CORRECT FORMAT!!!");
			}
			catch(Exception e)
			{
				System.out.println("UNEXPECTED ERROR OCCURE IN ACCOUNT MENU!!!"+e);
				e.printStackTrace();
			}
		}
	}
}
