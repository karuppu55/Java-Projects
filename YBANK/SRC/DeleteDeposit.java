package YBank;
import YBank.utils.*;
import YBank.Validation;
import java.util.ArrayList;
import java.io.*;
import java.sql.SQLException;
import java.sql.Statement;
public class DeleteDeposit implements DepositCaller
{	
	final String STATUS="deleted";
	public void depositCompile(LoginModel lm)throws IOException,SQLException
	{
		this.delete(lm);
	}
	
	public void delete(LoginModel lm) throws IOException,SQLException,DataNotFoundException,NotEligibleException
	{
		char ch=' ';
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("\nUSERID :"+lm.getUserId()+"\t\tDELETE DEPOSIT\t\tPOSITION :"+lm.job.getJobType());
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		do
		{
			int vcno=Validation.getInstance().getInstance().isNumber("ENTER VOUCHER NUMBER");
			VoucherModel voucher=VoucherControler.getInstance().getVoucher(vcno);
			if(voucher==null)
			{
				throw new DataNotFoundException("INVALID VOUCHER NUMBER!!!!!");
			}
			if(voucher.getStatus().equals("verified"))
			{
				throw new NotEligibleException("VOUCHER ALREADY VERIFIED!!!");
			}
			DepositView.printDetail(voucher);	
			if(Validation.getInstance().isYesorNo("CHECK ABOVE DETAIL AND CLICK (Y) TO CONTINUE").toLowerCase().equals("y"))
			{
				voucher.setStatus(STATUS);
				if(!VoucherControler.getInstance().updateStatus(voucher))
				{
						throw new DataNotFoundException("STATUS NOT UPDATED!!!!!");
				}
				System.out.println("\n\t\t\t=>DELETION SUCCUSSFULLY");
			}
			else
			{
				System.out.println("\n\t\t\t*CANCELLED DELETION");
			}
			ch=Character.toLowerCase(Validation.getInstance().getInstance().isContinue("DO YOU WANT TO CONTINE PRESS (Y) OTHERWISE (N)"));
		}while(ch=='y');
	}
}
