package YBank;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;
import YBank.utils.*;
import java.util.HashMap;
import java.util.Map;
import java.sql.Statement;
import java.sql.SQLException;
public class AccountStatementView implements AccountCaller
{
	public void accountCompile(LoginModel lm)throws IOException,SQLException
	{
		this.accountStatement(lm);
	}
	
	public static void printStatement(ArrayList<AccountModel> state) throws DataNotFoundException
	{
		System.out.println(String.format("|%-20s|%-20s|%-20s|%-20s|%-20s|","TRANSACTION ID","PARTICULAR","CREDIT_AMOUNT","TRANSACTION TYPE","CREATEDON"));
		System.out.println("\n=========================================================================================================");
		if(state==null)
		{
			throw new DataNotFoundException("NO RECORD FOUND!!!");
		}
		for(AccountModel statement:state)
		{
		System.out.println("\n"+String.format("|%-20s|%-20s|%-20s|%-20s|%-20s|",statement.voucher.getVcno(),statement.voucher.getParticular(),statement.voucher.getAmount(),statement.voucher.getVcType(),statement.voucher.getCreatedon()));
		}
	}
	public void accountStatement(LoginModel lm)  throws IOException,SQLException,DataNotFoundException,NotEligibleException
	{
		
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("\nUSERID :"+lm.getUserId()+"\t\t\tACCOUNT DETAIL\t\t\tPOSITION :"+lm.job.getJobType());	
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		String acno=Validation.getInstance().isAccountno("ENTER ACCOUNT NUMBER");
		char ch=' ';
		do
		{
			System.out.println("\n\t\t\t\t1.VIEW BY NO OF TRANSACTION\n\t\t\t\t2.VIEW BY DATE\n\t\t\t\t3.VIEW BY DATE BETWEEN");
			ArrayList<AccountModel> state=new ArrayList<>();
			switch(Validation.getInstance().isNumber("ENTER YOUR CHOICE"))
			{
				case 1:
					System.out.println("\n\t\t\t\tVIEW BY NO OF TRANSACTION");
					int count=Validation.getInstance().isNumber("ENTER NO OF TRANSACTION");
					state=AccountControler.getInstance().statements(acno,count);
					break;
				case 2:
					System.out.println("\n\t\t\t\tVIEW BY DATE");
					String date=Validation.getInstance().isDate("ENTER A DATE(DD_MM_YYYY)");
					state=AccountControler.getInstance().statements(acno,date);
					break;
				case 3:
					String from=Validation.getInstance().isDate("ENTER A DATE(DD_MM_YYYY)");
					String to=Validation.getInstance().isDate("ENTER A DATE(DD_MM_YYYY)");
					System.out.println("\n\t\t\t\tVIEW BY DATE BETWEEN");
					state=AccountControler.getInstance().statements(acno,from,to);
					break;
				default:
					System.out.println("\n\t\tENTER CORRECT CHOICE");
			}
			printStatement(state);
			ch=Character.toLowerCase(Validation.getInstance().isContinue("DO YOU WANT TO CONTINE PRESS (Y) OTHERWISE (N)"));
		}while(ch=='y');
	}
}
