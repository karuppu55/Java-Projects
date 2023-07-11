package YBank;
import YBank.utils.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.io.*;
import java.sql.SQLException;
public class  BranchUpdationView implements ManageBankViewCaller
{	
	public void bankviewCompile(LoginModel lm)throws IOException,SQLException
	{
		this.changeBranchType(lm);
	}
	private void changeBranchType(LoginModel lm) throws IOException,SQLException,DataExistsException,DataNotFoundException,UpdationFailedException
	{
		char ch=' ';
		do
		{
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("\nUSER ID :"+lm.getUserId()+"\t\t\t*BRANCH TYPE MODIFY*\t\tPOSITION :"+lm.job.getJobType());
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		int bankid=Validation.getInstance().isNumber("ENTER BRANCH ID");
		BranchModel bm=ManageBankControler.getInstance().getbankdetail(bankid);
		if(bm==null)
		{
			throw new DataNotFoundException("*INVALID BRANCH ID!!!");
		}
		System.out.println("\n\t\t\tBANK ID	:"+bm.getBankId()+"\n\t\t\tBANK LOCATION	:"+bm.city.getCity()+"\n\t\t\tBRANCH NAME	:"+bm.getBranchName()+"\n\t\t\tBRANCH TYPE	:"+bm.bank.getBankType());
		HashMap<Integer,BranchTypeModel> bt=ManageBankControler.getInstance().getBranchType();
		AddBranchView.printBankType(bt);
		int banktypeid=Validation.getInstance().isNumber("ENTER BRANCH TYPE ID");
		while(!bt.containsKey(banktypeid))
		{
			System.out.println("\n\t\t\t**ENTER CORRECT BANKID!!");
			banktypeid=Validation.getInstance().isNumber("ENTER");
		}
		if(bm.getBankId()==banktypeid)
		{
			throw new DataExistsException("BRANCH AREADY SAME TYPE!!!");
		}
		System.out.println("\n\t\t\tBRANCH TYPE	:"+bt.get(banktypeid).getBankType());
		if(Validation.getInstance().isYesorNo("CHECK ABOVE DETAIL AND CLICK (Y) TO CONTINUE").toLowerCase().equals("y"))
		{
			bm.bank.setBid(banktypeid);
			if(!ManageBankControler.getInstance().changetype(bm))
			{
				throw new UpdationFailedException("*BRANCH TYPE NOT UPDATED!!!");
			}
			System.out.println("\n\t\t\t BRANCH TYPE CHANGED....");
		}
		else
		{
			System.out.println("\n\t\t\t***CANCELLED!!");	
		}
		ch=Character.toLowerCase(Validation.getInstance().isContinue("DO YOU WANT TO CONTINE PRESS (Y) OTHERWISE (N)"));
		}while(ch=='y');
	}
}
