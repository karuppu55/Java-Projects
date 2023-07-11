package YBank;
import java.util.Scanner;
import YBank.utils.*;
import YBank.Validation;
import java.util.ArrayList;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.sql.SQLException;
public class CashBookView implements BranchCaller
{
	public void branchCompile(LoginModel lm)
	{
		this.branchStatement(lm);
	}

	public void branchStatement(LoginModel lm)
	{
		boolean flag=true;
		while(flag)
		{
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("\nUSERID :"+lm.getUserId()+"\t\t\tACCOUNT DETAIL\t\t\tPOSITION :"+lm.job.getJobType());	
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			try
			{
				System.out.println("\n\t\t\t\t1.VIEW BY DATE\n\t\t\t\t2.VIEW BY DATE BETWEEN\n\t\t\t\t3.EXIT");
				int choice=Validation.getInstance().isNumber("ENTER YOUR CHOICE");
				ArrayList<BranchModel> state=new ArrayList<>();
				switch(choice)
				{
					case 1:
						System.out.println("\n\t\t\t\tVIEW BY DATE");
						String date=Validation.getInstance().isDate("ENTER A DATE(DD_MM_YYYY)");
						state=BranchControler.getInstance().statements(lm.branch.getBankId(),date);
						break;
					case 2:
						String from=Validation.getInstance().isDate("ENTER A DATE(DD_MM_YYYY)");
						String to=Validation.getInstance().isDate("ENTER A DATE(DD_MM_YYYY)");
						System.out.println("\n\t\t\t\tVIEW BY DATE BETWEEN");
						state=BranchControler.getInstance().statements(lm.branch.getBankId(),from,to);
						break;
					case 3:
						flag=false;
						break;
					default:
						System.out.println("\n\t\t\t**ENTER CORECT CHOICE!!");
				}
				if(state.isEmpty())
				{
					throw new DataNotFoundException("NO RECORD FOUND!!!");
				}
				System.out.println("\n\t\t\tBRANCH NAME\t:"+state.get(0).getBranchName());
				System.out.println("\n=========================================================================================================");
				System.out.println(String.format("|%-20s|%-20s|%-20s|%-20s|%-20s|","SI NO","ACCOUNT NO","PARTICULAR","CREDIT_AMOUNT","TRANSACTION TYPE"));
				System.out.println("\n=========================================================================================================");
				int i=1;
				for(BranchModel statement:state)
				{
				System.out.println("\n"+String.format("|%-20s|%-20s|%-20s|%-20s|%-20s|",i++,statement.account.getAcno(),statement.voucher.getParticular(),statement.voucher.getAmount(),statement.voucher.getVcType()));
				}
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
				System.out.println("UNEXPECTED ERROR OCCURE IN ACCOUNT MENU!!!");
			}
		}
	}
}
