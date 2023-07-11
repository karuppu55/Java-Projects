package YBank;
import YBank.utils.*;
import java.io.*;
import java.sql.SQLException;
final public class BankSoftware implements LoginCaller
{
	public void loginCompile(LoginModel lm)
	{
		this.menu(lm);
	}
	public void menu(LoginModel lm)
	{
		BankSoftwareCaller banksoftware=null;
		boolean flag=true;
		while(flag)
		{
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("\nUSERID :"+lm.getUserId()+"\t\t\t**YBANK SOFTWARE**\t\t\tPOSITION :"+lm.job.getJobType());
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("\n\t\t\t\t1.ACCOUNTS\n\t\t\t\t2.DEPOSITE\n\t\t\t\t3.WITHDRAW\n\t\t\t\t4.BRANCH DETAIL\n\t\t\t\t5.BANK TANSACTION\n\t\t\t\t6.CHANGE PASSWORD\n\t\t\t\t7.LOGOUT");
			try
			{
				switch(Validation.getInstance().isNumber("ENTER YOUR CHOICE"))
				{
					case 1:
						banksoftware=new AccountView();
						break;
					case 2:
						banksoftware=new DepositView();
						break;
					case 3:
						banksoftware=new WithdrawView();
						break;
					case 4:
						banksoftware=new BranchView();
						break;
					case 5:
						banksoftware=new BankTransactionView();
						break;
					case 6:
						banksoftware=null;
						flag=false;
						break;
					case 7:
						banksoftware=null;
						if(Validation.getInstance().isYesorNo("ARE YOU SURE?(Y/N)").toLowerCase().equals("y"))
						{
							flag=false;
						}		
							break;
					default:
					System.out.println("\n\t\t\t**ENTER CORECT CHOICE!!");
				}
				if(banksoftware!=null)
					banksoftware.banksoftwareCompile(lm);
			}
			catch(UpdationFailedException ufe)
			{
				System.out.println("\n"+ufe.getMessage());
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
				System.out.println("UNEXPECTED ERROR OCCURE IN WORKSPACE MENU!!!");
			}
		}
	}	
}
