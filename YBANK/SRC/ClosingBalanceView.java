package YBank;
import java.util.Scanner;
import YBank.utils.*;
import YBank.Validation;
import java.util.ArrayList;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.sql.SQLException;
final public class ClosingBalanceView implements BankTransactionCaller
{
	public void banktransactionCompile(LoginModel lm)throws IOException,SQLException,InsufficiantBalanceException
	{
		this.getBalance(lm);
	}
	public  void getBalance(LoginModel lm)throws SQLException,InsufficiantBalanceException,IOException
	{
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("\nUSERID :"+lm.getUserId()+"\t\t\tCLOSING BALANCE\t\t\tPOSITION :"+lm.job.getJobType());
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		if(Validation.getInstance().isYesorNo("ARE YOU CLOSE YOUR CASH").toLowerCase().equals("y"))
			System.out.println("\n\t\t\tCLOSING AMOUNT	:"+BankTransactionControler.getInstance().closingAmount(lm.branch.getBankId()));
		else
			System.out.println("\n\t\t\tCANCELLED!!!");
	}
}
