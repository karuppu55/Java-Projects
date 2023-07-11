package YBank;
import YBank.utils.*;
import YBank.Validation;
import java.util.ArrayList;
import java.io.*;
import java.sql.SQLException;
public class AddWithdraw extends AddDeposit implements WithdrawCaller
{
	public void withdrawCompile(LoginModel lm)throws IOException,SQLException
	{
		this.add(lm);
	}
	@Override
	public void add(LoginModel lm) throws IOException,SQLException,AccountNotFoundException,InsufficiantBalanceException,DataNotFoundException
	{
		char ch=' ';
		do
		{
			String vctype="withdraw";
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("\nUSERID :"+lm.getUserId()+"\t\tNEW WITHDRAW\t\tPOSITION :"+lm.job.getJobType());;
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			String acno=Validation.getInstance().isAccountno("ENTER ACCOUNT NUMBER");
			AccountModel ad=AccountControler.getInstance().getAccount(acno);	
			if(ad==null)
			{
				throw new AccountNotFoundException("INVALID ACCOUNT NUMBER!!!");	
			}
			System.out.println("\n\t\t\tNAME\t\t:"+ad.customer.getName()+"\t\tAMOUNT\t:"+ad.getAmount());	
			double amount=Validation.getInstance().isAmount("ENTER AMOUNT");
			if((ad.getAmount()<amount))
			{
				throw new InsufficiantBalanceException("INSUFFICIENT BALANCE!!!");	
			}
			String particular=Validation.getInstance().isCharacter("ENTER PARTICULAR IF OWN (self) or PARTY NAME");
			if(Validation.getInstance().isYesorNo("CHECK ABOVE DETAIL AND CLICK (Y) TO CONTINUE").toLowerCase().equals("y"))
			{
				VoucherModel withdraw=new VoucherModel(ad.getAcid(),particular,amount,vctype,lm.getUserId(),lm.branch.getBankId());
				int id=VoucherControler.getInstance().addVoucher(withdraw);
				if(id==0)
				{
					throw new DataNotFoundException("VOUCHER NOT CREATED!!!!");
				}
				System.out.println("\n\t\t\tVOUCHER NUMBER IS	:"+id);
			}
			else	
			{
				System.out.println("\n\t\t\t*CANCELLED......");
			}
			ch=Character.toLowerCase(Validation.getInstance().isContinue("DO YOU WANT TO CONTINE PRESS (Y) OTHERWISE (N)"));
		}while(ch=='y');
	}

}

