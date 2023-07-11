package YBank;
import java.util.Scanner;
import YBank.utils.*;
import YBank.Validation;
import java.util.ArrayList;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.sql.SQLException;
final public class VerifyTransactionView implements BankTransactionCaller
{
	public void banktransactionCompile(LoginModel lm)throws IOException,SQLException,DataNotFoundException,UpdationFailedException
	{
		this.Verify(lm);
	}
	public  void Verify(LoginModel lm) throws IOException,SQLException,UpdationFailedException,DataNotFoundException
	{
		int id=Validation.getInstance().isNumber("ENTER TRANSACTION ID");
		BankTransaction trans=BankTransactionControler.getInstance().getTransaction(id);
		if(trans==null)
		{
			throw new DataNotFoundException("VOUCHER NOT FOUND!!!!");
		}
		if(!(trans.getStatus().equals("added")))
		{
			throw new DataNotFoundException("VOUCHER ALREADY VERIFIED!!!!");
		}
		System.out.println("\t\t\t\t\nTRANSACTION ID\t:"+trans.getId());
		System.out.println("\t\t\t\tBRANCH ID\t:"+trans.getBankId());
		System.out.println("\t\t\t\tAMOUNT\t\t:"+trans.getAmount());
		System.out.println("\t\t\t\tHEAD OFFICE ID\t:"+trans.getOffice());
		System.out.println("\t\t\t\tTRANS TYPE\t:"+trans.getTranstype());
		System.out.println("\t\t\t\tSTATUS\t\t:"+trans.getStatus());
		if(Validation.getInstance().isYesorNo("CHECK ABOVE DETAIL AND CLICK (Y) TO CONTINUE").toLowerCase().equals("y"))
		{
			int bankid=lm.branch.getBankId();
			int officeid=trans.getOffice();
			double amount=trans.getAmount();
			BranchModel office=BankTransactionControler.getInstance().getBranch(officeid);
			BranchModel branch=BankTransactionControler.getInstance().getBranch(bankid);
			if(trans.getTranstype().equals("debit"))
			{	
				office.setAmount(office.getAmount()-amount);
				branch.setAmount(branch.getAmount()+amount);
			}
			else
			{
				office.setAmount(office.getAmount()+amount);
				branch.setAmount(branch.getAmount()-amount);
			}
			if(BankTransactionControler.getInstance().updateProcess(office,branch,id)==0)
			{
				throw new UpdationFailedException("AMOUNT NOT UPDATED!!!");	
			}
			System.out.println("\n\t\t\tAMOUNT UPDATED!!!");
		}
		else
		System.out.println("\n\t\t\tCANCELLED!!!");
	}
}
