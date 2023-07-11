package YBank;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;
import YBank.utils.*;
import java.util.HashMap;
import java.util.Map;
import java.sql.Statement;
import java.sql.SQLException;
final public class AccountView implements BankSoftwareCaller
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
			AccountCaller account=null;
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("\nUSERID :"+lm.getUserId()+"\t\t\tACCOUNT VIEW\t\t\tPOSITION :"+lm.job.getJobType());
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("\n\t\t\t\t1.NEW CUSTOMER\n\t\t\t\t2.ACCOUNT GENERATION\n\t\t\t\t3.STATEMENT\n\t\t\t\t4.ACCOUNT DETAIL\n\t\t\t\t5.CLOSE ACCOUNT\n\t\t\t\t6.EXIT");
			try
			{
				switch(Validation.getInstance().isNumber("ENTER YOUR CHOICE"))
				{
					case 1:
						account=new AddCustomerView();
						break;
					case 2:
						if(!(lm.job.getJobType().equals("officer")||lm.job.getJobType().equals("manager")))
						{
							throw new InvalidOperationException("*****ACCOUNT GENERATION NOT FOR CLERK");
						}
						account=new AccountVerificationView();
						break;
					case 3:
						account=new AccountStatementView();
						break;
					case 4:
						account=new AccountDetailView();
						break;
					case 5:
						account=new AccountClosingView();
						break;
					case 6:
						account=null;
						flag=false;
						break;
					default:
						account=null;
						System.out.println("\n\t\t\t**ENTER CORECT CHOICE!!");
				}
				if(account!=null)
					account.accountCompile(lm);
			}
			catch(InvalidOperationException ioe)
			{
				System.out.println("\n"+ioe.getMessage());
			}
			catch(UpdationFailedException ufe)
			{
				System.out.println("\n"+ufe.getMessage());
			}
			catch(DataExistsException dee)
			{
				System.out.println("\n"+dee.getMessage());
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
				System.out.println("UNEXPECTED ERROR OCCURE IN ACCOUNT MENU!!!");
			}
		}
	}
	
}
