package YBank;
import YBank.utils.*;
import YBank.Validation;
import java.util.ArrayList;
import java.io.*;
import java.sql.SQLException;
import java.sql.Statement;
public class VerifyDeposit implements DepositCaller
{	
	final String STATUS="verified";
	public void depositCompile(LoginModel lm)throws IOException,SQLException
	{
		this.verify(lm);
	}
	public void verify(LoginModel lm) throws IOException,SQLException,DataNotFoundException,NotEligibleException
	{
		char ch=' ';
		String vctype="deposit";
			do
			{
				System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				System.out.println("\nUSERID :"+lm.getUserId()+"\t\tVERIFY DEPOSIT\t\tPOSITION :"+lm.job.getJobType());;
				System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				int vcno=Validation.getInstance().isNumber("ENTER VOUCHER NUMBER");
				VoucherModel voucher=VoucherControler.getInstance().getVoucher(vcno);
				if(voucher==null)
				{
					throw new DataNotFoundException("INVALID VOUCHER NUMBER!!!!!");
				}
				BranchModel branch=VoucherControler.getInstance().getAccount(lm.branch.getBankId());
				if(!(voucher.getBankId()==lm.branch.getBankId()))
				{
					throw new NotEligibleException("VOUCHER IS NOT YOUR BRACH!!!");
				}
				if(!(voucher.getStatus().equals("added")))
				{
					throw new NotEligibleException("VOUCHER ALREADY VERIFIED!!!");
				}
				if(!(voucher.getVcType().equals(vctype)))
				{
					throw new NotEligibleException("DEPOSIT VOUCHER ONLY ALLOWED TO VERIFIED!!!");
				}
				DepositView.printDetail(voucher);
				if(Validation.getInstance().isYesorNo("CHECK ABOVE DETAIL AND CLICK (Y) TO CONTINUE").toLowerCase().equals("y"))
				{
					branch.setAmount(branch.getAmount()+voucher.getAmount());
					AccountModel am=AccountControler.getInstance().getAccount(voucher.account.getAcno());
					am.setAmount(am.getAmount()+voucher.getAmount());
					if(!VoucherControler.getInstance().updateAmount(am,branch))
					{
						throw new DataNotFoundException("AMOUNT NOT CREDITED!!!!!");
					}
					System.out.println("\n\t\t\t=>AMOUNT CREDITED SUCCUSSFULLY!!!");
					voucher.setStatus(STATUS);
					if(!VoucherControler.getInstance().updateStatus(voucher))
					{
						throw new DataNotFoundException("STATUS NOT UPDATED!!!!!");
					}	
					System.out.println("\n\t\t\t=>STATUS UPDATED SUCCUSSFULLY!!!");
				}
				else
				{
					System.out.println("\n\t\t\t**CANCELLED.....");
				}
			
			ch=Character.toLowerCase(Validation.getInstance().isContinue("DO YOU WANT TO CONTINE PRESS (Y) OTHERWISE (N)"));
		}while(ch=='y');
	}
}
