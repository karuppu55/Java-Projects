package YBank;
import YBank.utils.Selections;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.sql.SQLException;
import java.sql.Statement;
public class EmployeePostingView implements ManageEmployeeCaller
{
	public void manageemployeecompile(LoginModel lm)throws IOException,SQLException
	{
		this.posting(lm);
	}
	private void posting(LoginModel lm) throws IOException,SQLException,DataNotFoundException,DataExistsException,UpdationFailedException
	{
		char ch;
		do
		{
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("\nUSER ID :"+lm.getUserId()+"\t\t\t*POSTING*\t\tPOSITION :"+lm.job.getJobType());
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			int id=Validation.getInstance().isNumber("ENTER EMPLOYEE ID");
			EmployeeModel emp=ManagEmployeeControler.getInstance().getEmployee(id);
			if(emp==null)
			{
				throw new DataNotFoundException("EMPLOYEE NOT FOUND!!!!");
			}
				ManageEmployeeView.printEmpDetail(emp);
				String type=emp.job.getJobType();
				HashMap<Integer,JobTypeModel> jt=ManagEmployeeControler.getInstance().getJobType();
				for(Map.Entry<Integer,JobTypeModel> job:jt.entrySet())
				{
					System.out.println("\n\t\t\t\t"+job.getKey()+"-->"+job.getValue().getJobType());
				}
				int jobtypeid=Validation.getInstance().isNumber("ENTER JOB ID");
				while(!jt.containsKey(jobtypeid))
				{
					System.out.println("\n\t\t\t**ENTER CORRECT JOBID!!");
					jobtypeid=Validation.getInstance().isNumber("ENTER");
				}
				String jobtype=jt.get(jobtypeid).getJobType();
				System.out.println("\n\t\t\tJOB TYPE	:"+jobtype);
				if(jobtype.equals(type))
				{
					throw new DataExistsException("EMPLOYEE ALREADY SAME TYPE!!!");
				}
				if(Validation.getInstance().isYesorNo("CHECK ABOVE DETAIL AND CLICK (Y) TO CONTINUE").toLowerCase().equals("y"))
				{
					emp.job.setJobId(jobtypeid);
					if(!ManagEmployeeControler.getInstance().updatePosting(emp))
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
