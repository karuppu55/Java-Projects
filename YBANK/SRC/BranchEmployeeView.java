package YBank;
import YBank.utils.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.io.*;
import java.sql.SQLException;
public class BranchEmployeeView implements ManageBankViewCaller
{	
	public void bankviewCompile(LoginModel lm)throws IOException,SQLException
	{
		this.branchEmployees(lm);
	}
	private void branchEmployees(LoginModel lm) throws IOException,SQLException,DataNotFoundException
	{
		char ch=' ';
		do
		{
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("\nUSER ID :"+lm.getUserId()+"\t\t\t*BRANCH EMPLOYEES*\t\tPOSITION :"+lm.job.getJobType());
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			int bankid=Validation.getInstance().isNumber("ENTER BANK ID");
			ArrayList<BranchModel> al=ManageBankControler.getInstance().getEmployee(bankid);
			if(al.isEmpty())
			{
				throw new DataNotFoundException("NO EMPLOYEEE IN THE BRANCH!!!");
			}
			System.out.println(String.format("|%-20s|%-20s|%-20s|%-20s|%-20s|","REGION","BRANCH NAME","EMP NAME","EMP ID","EMP POSITION"));
			for(BranchModel bank:al)
			{
				System.out.println("===========================================================================================================");
				System.out.println(String.format("|%-20s|%-20s|%-20s|%-20s|%-20s|",bank.city.getCity(),bank.getBranchName(),bank.employee.getName(),bank.employee.getId(),bank.employee.job.getJobType()));
			}
			ch=Character.toLowerCase(Validation.getInstance().isContinue("DO YOU WANT TO CONTINE PRESS (Y) OTHERWISE (N)"));
		}while(ch=='y');
	}
	
}
