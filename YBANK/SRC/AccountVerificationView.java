package YBank;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;
import YBank.utils.*;
import java.util.HashMap;
import java.util.Map;
import java.sql.Statement;
import java.sql.SQLException;
public class AccountVerificationView implements AccountCaller
{
	public void accountCompile(LoginModel lm)throws IOException,SQLException
	{
		this.accountVerfication(lm);
	}
	public void accountVerfication(LoginModel lm)  throws IOException,SQLException,DataExistsException,DataNotFoundException,NotEligibleException,UpdationFailedException
	{
		char ch=' ';
		do
		{
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("\nUSERID :"+lm.getUserId()+"\t\t\tACCOUNT VERIFICATION\t\t\tPOSITION :"+lm.job.getJobType());	
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			int cusid=Validation.getInstance().isNumber("ENTER CUSTOMER ID");
			int id=AccountControler.getInstance().isAccount(cusid);
			if(id!=0)
			{
				throw new DataExistsException("ACCOUNT ALREADY OPENED!!!");
			}
			CustomerModel cm=AccountControler.getInstance().getCustomer(cusid);	
			if(cm==null)
			{
				throw new DataNotFoundException("CUSTOMER DATA NOT FOUND!!!");
			}
			if(lm.branch.getBankId()!=cm.getBankId())
			{
				throw new NotEligibleException("CUSTOMER ID NOT YOUR BRANCH!!!");
			}
			printCustomer(cm);
			HashMap<Integer,AccountTypeModel> actype=AccountControler.getInstance().getAccountType();
			printAccountType(actype);
			int actypeid=Validation.getInstance().isNumber("ACCOUNT TYPE ID");
			while(!actype.containsKey(actypeid)||!(cm.getAge()>=actype.get(actypeid).getMinage() && (cm.getAge()<=actype.get(actypeid).getMaxage()||actype.get(actypeid).getMaxage()==0)))
			{
				System.out.println("\n\t\t\t*INVALID TYPE");
				actypeid=Validation.getInstance().isNumber("ACCOUNT TYPE ID");
			}
			int count=AccountControler.getInstance().getBranchAccountCount(lm.branch.getBankId(),actypeid);
			Accountnumber accountno=new Accountnumber(lm.branch.getBankId(),actype.get(actypeid).getSchemecode(),count);
			String acno=accountno.toString();
			System.out.println("\n\t\t\tACCOUNT NUMBER :"+acno+"\n\t\t\tACCOUNT TYPE	:"+actype.get(actypeid).getActype());
			if(Validation.getInstance().isYesorNo("CHECK ABOVE DETAIL AND CLICK (Y) TO CONTINUE").toLowerCase().equals("y"))
			{
				cm.setStatus(true);
				int id1=AccountControler.getInstance().addAccount(new AccountModel(cusid,acno,actypeid,lm.getUserId(),lm.branch.getBankId()));
				if(id1==0)
				{
					throw new UpdationFailedException("ACCOUNT NOT CREATED!!!");
				}
				AccountControler.getInstance().updateCustomer(cm);
				System.out.println("\n\t\t\tACCOUNT ACTIVATED...");
			
			}
			else
			{
				System.out.println("\n\t\t\tCANCELLED...");
			}
			ch=Character.toLowerCase(Validation.getInstance().isContinue("DO YOU WANT TO CONTINE PRESS (Y) OTHERWISE (N)"));
		}while(ch=='y');
	}
	public void printCustomer(CustomerModel cm)
	{
		System.out.print("\n\t\t\tNAME\t\t:"+cm.getName()+"\n\t\t\tFATHER NAME\t:"+cm.getFathername()+"\n\t\t\tGENDER\t\t:"+cm.getGender()+"\n\t\t\tDOB\t\t:"+cm.getDob()+"\n\t\t\tAGE\t\t:"+cm.getAge()+"\n\t\t\tMOBILE\t\t:"+cm.getMobile()+"\n\t\t\tADHAR NO\t:"+cm.getAdharno()+"\n\t\t\tPAN NO\t\t:"+cm.getPanno()+"\n\t\t\tADDRESS\t\t:"+cm.getAddress());
	}
	public void printAccountType(HashMap<Integer,AccountTypeModel> actype)
	{
		for(Map.Entry<Integer,AccountTypeModel> ac:actype.entrySet())
		{
			System.out.println("\n\t\t\t"+ac.getKey()+"-->"+ac.getValue().getActype());
		}	
	}
}
