package YBank;
import YBank.utils.Selections;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.sql.SQLException;
import java.sql.Statement;
public class EmployeeRelivingView implements ManageEmployeeCaller
{
	public void manageemployeecompile(LoginModel lm)throws IOException,SQLException
	{
		this.releiving(lm);
	}
	private void releiving(LoginModel lm) throws IOException,SQLException,DataNotFoundException,DataExistsException,UpdationFailedException
	{
		char ch;
		do
		{
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("\nUSER ID :"+lm.getUserId()+"\t\t\t*RELIEVE*\t\tPOSITION :"+lm.job.getJobType());
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			int id=Validation.getInstance().isNumber("ENTER EMPLOYEE ID");
			EmployeeModel emp=ManagEmployeeControler.getInstance().getEmployee(id);
			if(emp==null)
			{
				throw new DataNotFoundException("EMPLOYEE NOT FOUND!!!!");
			}
			ManageEmployeeView.printEmpDetail(emp);
			boolean empstatus=emp.getStatus();
			if(!(empstatus))
			{
				throw new DataExistsException("EMPLOYEE ALREADY RELIVED!!!");
			}
			if(Validation.getInstance().isYesorNo("CHECK ABOVE DETAIL AND CLICK (Y) TO CONTINUE").toLowerCase().equals("y"))
			{
				emp.setStatus(false);
				if(!ManagEmployeeControler.getInstance().updateRelive(emp))
				{
					throw new UpdationFailedException("POSTING NOT UPDATED!!!!");			
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
