package YBank;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;
import YBank.utils.*;
import java.util.HashMap;
import java.util.Map;
import java.sql.Statement;
import java.sql.SQLException;
public class AccountDetailView implements AccountCaller
{
	public void accountCompile(LoginModel lm)throws IOException,SQLException
	{
		this.accountInfo(lm);
	}
	
	public void accountInfo(LoginModel lm)  throws IOException,SQLException,DataNotFoundException,AccountNotFoundException
	{
		char ch=' ';
		do
		{
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("\nUSERID :"+lm.getUserId()+"\t\t\tACCOUNT DETAIL\t\t\tPOSITION :"+lm.job.getJobType());	
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			String acno=Validation.getInstance().isAccountno("ENTER ACCOUNT NUMBER");
			System.out.println("\n\n=========================================================================================================");
			AccountModel ad=AccountControler.getInstance().getAccount(acno);	
			if(ad==null)
			{
				throw new AccountNotFoundException("ACCOUNT NOT FOUND!!!");
			}
			printAccountDetail(ad);
			AccountStatementView.printStatement(AccountControler.getInstance().statements(acno));
			ch=Character.toLowerCase(Validation.getInstance().isContinue("DO YOU WANT TO CONTINE PRESS (Y) OTHERWISE (N)"));
		}while(ch=='y');
	}
	public static void printAccountDetail(AccountModel ad)
	{
		System.out.println("\n\t\tACCOUNT NUMBER\t:"+ad.getAcno()+"\t\tCREATED ON\t:"+ad.customer.getCreatedon()+"\n\n\t\tNAME\t\t:"+ad.customer.getName()+"\t\t\tMOBILE NUMBER\t:"+ad.customer.getMobile()+"\n\n\t\tBALANCE AMOUNT\t:"+ad.getAmount()+"\t\t\tSTATUS\t:"+ad.getStatus()+"\n\n\t\tCUS ID\t:"+ad.customer.getCusId());	
		System.out.println("\n\n=========================================================================================================");
	}
}
