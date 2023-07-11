package YBank;
import java.util.Scanner;
import YBank.utils.*;
import YBank.Validation;
import java.util.ArrayList;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.sql.SQLException;
final public class AddTransactionView implements BankTransactionCaller
{
	public void banktransactionCompile(LoginModel lm)throws IOException,SQLException,InsufficiantBalanceException,UpdationFailedException
	{
		this.add(lm);
	}
	public  void add(LoginModel lm) throws IOException,SQLException,InsufficiantBalanceException,UpdationFailedException
	{
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("\nUSERID :"+lm.getUserId()+"\t\t\tDEBIT FROM HEAD OFFICE\t\t\tPOSITION :"+lm.job.getJobType());
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		HashMap<Integer,BranchModel> headoffice=BankTransactionControler.getInstance().getHeadOfficeDetail();
		if(headoffice.isEmpty())
		{
			throw new DataNotFoundException("NO BRANCH FOUND!!!");
		}
		for(Map.Entry<Integer,BranchModel> branch:headoffice.entrySet())
		{
			System.out.println("\n=====================================================================================================================================================");
			System.out.println("\n"+String.format("|%-20s|%-20s|%-20s|%-20s|%-20s|%-20s|%-20s|",branch.getKey(),branch.getValue().state.getState(),branch.getValue().district.getDistrict(),branch.getValue().city.getCity(),branch.getValue().getBranchName(),branch.getValue().bank.getBankType(),branch.getValue().getAcno()));
		}
		System.out.println("\n\t\t\tSELECT TYPE OF TRANSACTION");
		System.out.println("\n\t\t\t1.CREDIT TO HEADOFFICE");
		System.out.println("\n\t\t\t2.DEBIT FROM HEADOFFICE");
		boolean flag=true;
		String transtype="";
		while(flag)
		{
			int choice=Validation.getInstance().isNumber("ENTER YOUR TRANSACTION TYPE");
			switch(choice)
			{
				case 1:
					transtype="credit";
					flag=false;
					break;
				case 2:
					transtype="debit";
					flag=false;
					break;
				default:
					System.out.println("\n\t\t\tSELECT CORRECT CHOICE");
			}
		}
		int bankid=Validation.getInstance().isNumber("ENTER BANK ID");
		while(!headoffice.containsKey(bankid))
		{
			bankid=Validation.getInstance().isNumber("ENTER CORRECT BANK ID");
		}	
		double amount=Validation.getInstance().isAmount("ENTER AMOUNT");
		BranchModel branch=BankTransactionControler.getInstance().getBranch(lm.branch.getBankId());
		if(transtype.equals("credit")&&amount>branch.getAmount())
		{
			throw new InsufficiantBalanceException("INSUFFICIENT BALANCE IN YOUR BRANCH!!!");	
		}
		if(transtype.equals("debit")&&amount>headoffice.get(bankid).getAmount())
		{
			throw new InsufficiantBalanceException("INSUFFICIENT BALANCE IN YOUR OFFICE!!!");	
		}
		if(Validation.getInstance().isYesorNo("CHECK ABOVE DETAIL AND CLICK (Y) TO CONTINUE").toLowerCase().equals("y"))
		{
			BankTransaction bt=new BankTransaction(lm.branch.getBankId(),amount,bankid,transtype);
			int id=BankTransactionControler.getInstance().addTransaction(bt);
			if(id==0)
			{
				
			}
			System.out.println("\n\t\t\tTRANSACTION ADDED AND ID	:"+id);
		}
		else
			System.out.println("\n\t\t\tCANCELLED!!!");
	}
}
