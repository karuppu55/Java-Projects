package YBank;
import YBank.utils.Selections;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.sql.SQLException;
import java.sql.Statement;
public class EmployeeTransferView implements ManageEmployeeCaller
{
	public void manageemployeecompile(LoginModel lm)throws IOException,SQLException
	{
		this.transfer(lm);
	}
	private void transfer(LoginModel lm) throws IOException,SQLException,DataNotFoundException,DataExistsException,UpdationFailedException
	{
		char ch;
		do
		{
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("\nUSER ID :"+lm.getUserId()+"\t\t\t*TRANSFER*\t\tPOSITION :"+lm.job.getJobType());
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			int id=Validation.getInstance().isNumber("ENTER EMPLOYEE ID");
			EmployeeModel emp=ManagEmployeeControler.getInstance().getEmployee(id);
			if(emp==null)
			{
				throw new DataNotFoundException("EMPLOYEE NOT FOUND!!!!");
			}
			ManageEmployeeView.printEmpDetail(emp);
			int bankid=Validation.getInstance().isNumber("ENTER BANK ID");
			BranchModel bm1=ManageBankControler.getInstance().getbankdetail(bankid);
			if(bm1==null)
			{
				throw new DataNotFoundException("BANKID NOT FOUND!!!!");
			}
			if(bankid==emp.branch.getBankId())
			{
				throw new DataExistsException("EMPLOYEE ALREADY SAME BRANCH!!!");
			}
			emp.branch.setBankId(bankid);
			System.out.println("\n\t\tBRANCH NAME	:"+bm1.getBranchName());
			if(Validation.getInstance().isYesorNo("CHECK ABOVE DETAIL AND CLICK (Y) TO CONTINUE").toLowerCase().equals("y"))
			{
				if(!ManagEmployeeControler.getInstance().updateTransfer(emp))
				{
					throw new UpdationFailedException("TRANSFER NOT UPDATED!!!!");
				}
				System.out.println("\n\t\t\tUPDATED....");	
			}
			else
			{
				System.out.println("\n\t\t\t*PROCESS CANCELLED...");
			}
			ch=Character.toLowerCase(Validation.getInstance().isContinue("DO YOU WANT TO CONTINE PRESS (Y) OTHERWISE (N)"));
		}while(ch=='y');
	}
}
