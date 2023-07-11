package YBank;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;
import YBank.utils.*;
import java.util.HashMap;
import java.util.Map;
import java.sql.Statement;
import java.sql.SQLException;
public class AccountClosingView implements AccountCaller
{
	public void accountCompile(LoginModel lm)throws IOException,SQLException
	{
		this.accountClose(lm);
	}
	public void accountClose(LoginModel lm)  throws IOException,SQLException,AccountNotFoundException,UpdationFailedException,InvalidOperationException
	{
		char ch=' ';
		do
		{
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("\nUSERID :"+lm.getUserId()+"\t\t\tACCOUNT CLOSING\t\t\tPOSITION :"+lm.job.getJobType());	
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			String acno=Validation.getInstance().isAccountno("ENTER ACCOUNT NUMBER");
			System.out.println("\n\n=========================================================================================================");
			AccountModel ad=AccountControler.getInstance().getAccount(acno);	
			if(ad==null)
			{
				throw new AccountNotFoundException("ACCOUNT NOT FOUND!!!");
			}
			AccountDetailView.printAccountDetail(ad);
			if(Validation.getInstance().isYesorNo("CHECK ABOVE DETAIL AND CLICK (Y) TO CONTINUE").toLowerCase().equals("y"))
			{
				if(ad.getStatus()==false)
				{
					throw new InvalidOperationException("ACCOUNT ALREADY CLOSED!!!");
				}
				if(ad.getAmount()!=0)
				{
					throw new InvalidOperationException("FIRST WITHDRAW YOUR AMOUNT NEXT CLOSE THE ACCOUNT!!!");
				}
				ad.setStatus(false);
				if(AccountControler.getInstance().updateStatus(ad)==0)
				{
					throw new UpdationFailedException("ACCOUNT NOT CLOSED!!!");
				}
				System.out.println("\n\t\t\tACCOUNT CLOSED");
			}
			else
			{
				System.out.println("\n\t\t\tCANCELLED...");
			}
			ch=Character.toLowerCase(Validation.getInstance().isContinue("DO YOU WANT TO CONTINE PRESS (Y) OTHERWISE (N)"));
		}while(ch=='y');
	}
}
