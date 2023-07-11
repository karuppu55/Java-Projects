package YBank;
import YBank.utils.*;
import YBank.Validation;
import java.util.ArrayList;
import java.io.*;
import java.sql.SQLException;
import java.sql.Statement;
public class InquiryDeposit implements DepositCaller
{	
	public void depositCompile(LoginModel lm)throws IOException,SQLException
	{
		this.inquiry(lm);
	}
	public void inquiry(LoginModel lm) throws IOException,SQLException,DataNotFoundException,NotEligibleException
	{
		char ch=' ';
		do
		{
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("\nUSERID :"+lm.getUserId()+"\t\tDEPOSITE INQUIRY\t\tPOSITION :"+lm.job.getJobType());
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			int vcno=Validation.getInstance().isNumber("ENTER VOUCHER NUMBER");
			VoucherModel voucher=VoucherControler.getInstance().getVoucher(vcno);
			if(voucher==null)
			{
				throw new DataNotFoundException("INVALID VOUCHER NUMBER!!!!!");
			}
			DepositView.printDetail(voucher);	
			ch=Character.toLowerCase(Validation.getInstance().isContinue("DO YOU WANT TO CONTINE PRESS (Y) OTHERWISE (N)"));
		}while(ch=='y');
	}
}
