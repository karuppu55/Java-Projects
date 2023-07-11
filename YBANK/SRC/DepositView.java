package YBank;
import YBank.utils.*;
import YBank.Validation;
import java.util.ArrayList;
import java.io.*;
import java.sql.SQLException;
import java.sql.Statement;
public class DepositView implements BankSoftwareCaller
{	
	public void banksoftwareCompile(LoginModel lm)
	{
		this.menu(lm);
	}
	public void menu(LoginModel lm)
	{
		boolean flag=true;
		while(flag)
		{
			DepositCaller deposit=null;
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("\nUSERID :"+lm.getUserId()+"\t\tDEPOSITE VIEW\t\tPOSITION :"+lm.job.getJobType());
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("\n\t\t\t\t1.ADD\n\t\t\t\t2.VERIFY\n\t\t\t\t3.INQUIRY\n\t\t\t\t4.DELETE\n\t\t\t\t5.EXIT");
			try
			{
				switch(Validation.getInstance().isNumber("ENTER YOUR CHOICE"))
				{
					case 1:
						deposit=new AddDeposit();
						break;
					case 2:
						if(!(lm.job.getJobType().equals("officer")||lm.job.getJobType().equals("manager")))
						{
							throw new InvalidOperationException("*VERIFY OPTION ONLY FOR OFFICER AND MANAGER!!!");
						}
						deposit=new VerifyDeposit();
						break;
					case 3:
						deposit=new InquiryDeposit();
						break;
					case 4:	
						deposit=new DeleteDeposit();
						break;
					case 5:
						deposit=null;
						flag=false;
						break;
					default:
						deposit=null;
						System.out.println("\n**ENTER CORECT CHOICE!!");
				}
				if(deposit!=null)
					deposit.depositCompile(lm);
			}
			catch(InvalidOperationException nee)
			{
				System.out.println("\n"+nee.getMessage());
			}
			catch(NotEligibleException nee)
			{
				System.out.println("\n"+nee.getMessage());
			}
			catch(AccountNotFoundException anfe)
			{
				System.out.println("\n"+anfe.getMessage());
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
			catch(NumberFormatException num)
			{
				System.out.println("ENTER INPUT IN CORRECT FORMAT!!!");
			}
			catch(Exception e)
			{
				System.out.println("UNEXPECTED ERROR OCCURE IN DEPOSIT MENU!!!"+e);
				e.printStackTrace();
				
			}
		}	
	}
	public static void printDetail(VoucherModel voucher)
	{
		System.out.println("\n\t\t\tVOUCHER NO	:"+voucher.getVcno()+"\n\t\t\tACCOUNT NO	:"+voucher.account.getAcno()+"\n\t\t\tNAME		:"+voucher.customer.getName()+"\n\t\t\tPARTICULAR	:"+voucher.getParticular()+"\n\t\t\tAMOUNT		:"+voucher.getAmount()+"\n\t\t\tENTERED BY	:"+voucher.getCreatedby()+"\n\t\t\tBANK ID		:"+voucher.getBankId()+"\n\t\t\tSTATUS		:"+voucher.getStatus());
	}
	
}
