package YBank;
import java.util.Scanner;
import YBank.utils.*;
import YBank.Validation;
import java.util.ArrayList;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.sql.SQLException;
final public class OpeningBalanceView implements BankTransactionCaller
{
	public void banktransactionCompile(LoginModel lm)throws IOException,SQLException
	{
		this.getBalance(lm);
	}
	public  void getBalance(LoginModel lm)throws SQLException,IOException
	{
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("\nUSERID :"+lm.getUserId()+"\t\t\tOPENING BALANCE\t\t\tPOSITION :"+lm.job.getJobType());
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("\n\t\t\tOPENING AMOUNT	:"+BankTransactionControler.getInstance().opeingAmount(lm.branch.getBankId()));
	}
}
