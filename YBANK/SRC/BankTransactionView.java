package YBank;
import java.util.Scanner;
import YBank.utils.*;
import YBank.Validation;
import java.util.ArrayList;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.sql.SQLException;
final public class BankTransactionView implements BankSoftwareCaller
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
			BankTransactionCaller banktransaction=null;
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("\nUSERID :"+lm.getUserId()+"\t\t\tBANK TRANSACTION\t\t\tPOSITION :"+lm.job.getJobType());
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("\n\t\t\t\t1.ADD HEAD OFFICE TRANSACTION\n\t\t\t\t2.VERIFY HEAD OFFICE TRANSACTION\n\t\t\t\t3.GENERATE OPENING BALANCE\n\t\t\t\t4.GENERATE CLOSING BALANCE\n\t\t\t\t5.VIEW HEAD OFFICE\n\t\t\t\t6.EXIT");
			try
			{
				switch(Validation.getInstance().isNumber("ENTER YOUR CHOICE"))
				{
					case 1:
						banktransaction=new AddTransactionView();
						break;
					case 2:
						banktransaction=new VerifyTransactionView();
						break;
					case 3:
						banktransaction=new OpeningBalanceView();
						break;
					case 4:
						banktransaction=new ClosingBalanceView();
						break;
					case 5:
						banktransaction=new HeadOfficeDetailView();
						break;
					case 6:
						banktransaction=null;
						flag=false;
						break;
					default:
						banktransaction=null;
						System.out.println("\n\t\t\t**ENTER CORECT CHOICE!!");
				}
				if(banktransaction!=null)
					banktransaction.banktransactionCompile(lm);
			}
			catch(InsufficiantBalanceException isb)
			{
				System.out.println("\n"+isb.getMessage());
			}
			catch(UpdationFailedException ufe)
			{
				System.out.println("\n"+ufe.getMessage());
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
