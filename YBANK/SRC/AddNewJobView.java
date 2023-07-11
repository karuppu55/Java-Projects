package YBank;
import java.util.Scanner;
import YBank.utils.Selections;
import java.util.ArrayList;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.sql.SQLException;
public class AddNewJobView implements ManageHiringCaller
{
	public void managehiringcompile(LoginModel lm)throws IOException,SQLException,UpdationFailedException
	{
		this.addJob(lm);
	}
	private void printJobType(HashMap<Integer,JobTypeModel> jt)
	{
		for(Map.Entry<Integer,JobTypeModel> job:jt.entrySet())
		{
			System.out.println("\n\t\t\t\t"+job.getKey()+"-->"+job.getValue().getJobType());
		}
	}
	private void addJob(LoginModel lm) throws IOException,SQLException,UpdationFailedException
	{
		char ch=' ';
		do
		{
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("\nUSER ID :"+lm.getUserId()+"\t\t\t*NEW JOB CREATION*\t\tPOSITION :"+lm.job.getJobType());
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			HashMap<Integer,JobTypeModel> jt=ManagEmployeeControler.getInstance().getJobType();
			printJobType(jt);
			int jobtypeid=Validation.getInstance().isNumber("ENTER JOB ID");
			while(!jt.containsKey(jobtypeid))
			{
				System.out.println("\n\t\t\t**ENTER CORRECT JOBID!!");
				jobtypeid=Validation.getInstance().isNumber("ENTER");
			}
			String jobtype=jt.get(jobtypeid).getJobType();
			String qualifi=Validation.getInstance().isCharacter("QUALIFICATION (SSLC)or(HSC)or(UG)or(PG)");
			int age=Validation.getInstance().isNumber("ENTER AGE");
			String date=Validation.getInstance().isDate("ENTER DOB (DD-MM-YYYY)");
			System.out.println("\n\t\t\tENTERED DETAILS\n\t\t\t_____________________________\n\n\t\t\tJob Type	:"+jobtype+"\n\t\t\tqualification	:"+qualifi+"\n\t\t\tAge		:"+age+"\n\t\t\tEnding date	:"+date);
			if(Validation.getInstance().isYesorNo("CHECK ABOVE DETAIL AND CLICK (Y) TO CONTINUE").toLowerCase().equals("y"))
			{
				int id=ManageHiringControler.getInstance().addJob(new JobModel(jobtypeid,qualifi,age,date,lm.getUserId()));
				if(id==0)
				{
					throw new UpdationFailedException("JOB NOT CREATED!!!");
					
				}
				System.out.println("\n\t\t\tJOB ADDED SUCCUSSFULLY.....");
			}
			else
			{
				System.out.println("\n\t\t\t*CANCELLED");
			}
			ch=Character.toLowerCase(Validation.getInstance().isContinue("DO YOU WANT TO CONTINE PRESS (Y) OTHERWISE (N)"));
		}while(ch=='y');
	}
}
