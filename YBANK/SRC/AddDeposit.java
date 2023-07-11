package YBank;
import YBank.utils.*;
import YBank.Validation;
import java.util.ArrayList;
import java.io.*;
import java.sql.SQLException;
import java.sql.Statement;
public class AddDeposit implements DepositCaller
{	
	final String VCTYPE="deposit";
	public void depositCompile(LoginModel lm)throws IOException,SQLException
	{
		this.add(lm);
	}
	public void add(LoginModel lm) throws IOException,SQLException,DataNotFoundException,AccountNotFoundException
	{
		char ch=' ';
		do
		{	
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("\nUSERID :"+lm.getUserId()+"\t\tNEW DEPOSITE \t\tPOSITION :"+lm.job.getJobType());
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			String acno=Validation.getInstance().isAccountno("ENTER ACCOUNT NUMBER");
			AccountModel ad=AccountControler.getInstance().getAccount(acno);	
			if(ad==null)
			{
				throw new AccountNotFoundException("INVALID ACCOUNT NUMBER!!!");	
			}
				System.out.println("\n\t\t\t\tNAME\t\t:"+ad.customer.getName()+"\t\tAMOUNT\t:"+ad.getAmount());
				if(!ad.getStatus())
				{
					throw new AccountNotFoundException("ACCOUNT CLOSED!!!");
				}
				double amount=Validation.getInstance().isAmount("ENTER AMOUNT");
				String particular=Validation.getInstance().isCharacter("ENTER PARTICULAR IF OWN (self) OR PARTY NAME");
				if(Validation.getInstance().isYesorNo("CHECK ABOVE DETAIL AND CLICK (Y) TO CONTINUE").toLowerCase().equals("y"))
				{
					VoucherModel voucher=new VoucherModel(ad.getAcid(),particular,amount,VCTYPE,lm.getUserId(),lm.branch.getBankId());
					int id=VoucherControler.getInstance().addVoucher(voucher);
					if(id==0)
					{
						throw new DataNotFoundException("VOUCHER NOT CREATED!!!!");
					}
					System.out.println("\n\t\t\tVOUCHER NUMBER IS	:"+id);
				}
				else
				{
					System.out.println("\n\t\t\t*CANCELLED!!");
				}
			ch=Character.toLowerCase(Validation.getInstance().isContinue("DO YOU WANT TO CONTINE PRESS (Y) OTHERWISE (N)"));
		}while(ch=='y');
	}
}
