package YBank;
import YBank.utils.*;
import YBank.Validation;
import java.util.ArrayList;
import java.io.*;
import java.sql.SQLException;
public class WithdrawView extends DepositView implements BankSoftwareCaller
{
	public void banksoftwareCompile(LoginModel lm)
	{
		this.menu(lm);
	}
	@Override
	public void menu(LoginModel lm)   
	{
		boolean flag=true;
		while(flag)
		{
			WithdrawCaller withdraw=null;
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("\nUSERID :"+lm.getUserId()+"\t\tWITHDRAW VIEW\t\tPOSITION :"+lm.job.getJobType());
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("\n\t\t\t\t1.ADD\n\t\t\t\t2.VERIFY\n\t\t\t\t3.INQUIRY\n\t\t\t\t4.DELETE\n\t\t\t\t5.EXIT");
			try
			{
				switch(Validation.getInstance().isNumber("ENTER YOUR CHOICE"))
				{
					case 1:
						withdraw=new AddWithdraw();
						break;
					case 2:
						if(!(lm.job.getJobType().equals("officer")||lm.job.getJobType().equals("manager")))
						{
							throw new InvalidOperationException("*VERIFY OPTION ONLY FOR OFFICER AND MANAGER!!!");
						}
						withdraw=new VerifyWithdraw();
						break;
					case 3:
						withdraw=new InquiryWithdraw();
						break;
					case 4:	
						withdraw=new DeleteWithdraw();
						break;
					case 5:
						withdraw=null;
						flag=false;
						break;
					default:
						withdraw=null;
						System.out.println("\n\t\t\t**ENTER CORECT CHOICE!!");
				}
				if(withdraw!=null)
					withdraw.withdrawCompile(lm);
			}
			catch(InsufficiantBalanceException isb)
			{
				System.out.println("\n"+isb.getMessage());
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
				System.out.println("UNEXPECTED ERROR OCCURE IN WITHDRAW MENU!!!");
			}
		}
	}

}
