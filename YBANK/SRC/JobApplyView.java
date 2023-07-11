package YBank;
import java.util.Scanner;	
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import YBank.utils.*;
import java.io.*;
import java.sql.Statement;
import java.sql.SQLException;
public class JobApplyView implements ApplicationCaller
{
	public void appCompile()throws IOException,SQLException,DataNotFoundException,NotEligibleException
	{
		this.jobApply();
	}
	public void jobApply() throws IOException,SQLException,DataNotFoundException,NotEligibleException
	{	
		char ch=' ' ;
		do
		{
			int regid=Validation.getInstance().isNumber("ENTER REGISTRATION ID");
			int age=ApplicationControler.getApplicationInstance().isRegister(new ApplicationModel(regid));	
			if(age==0)
			{
				throw new DataNotFoundException("**PLEASE REGISTER YOUR SELF FIRST!!!");
			}
			HashMap<Integer,JobModel> job=ApplicationControler.getApplicationInstance().getJobs();
			printJob(job);
			int jobid=Validation.getInstance().isNumber("ENTER JOB ID FOR APPLY");
			while(!job.containsKey(jobid))
			{
				System.out.println("\n\t\t\t*INVALID JOB ID");
				jobid=Validation.getInstance().isNumber("ENTER");	
			}
			CandidatesModel cm=new CandidatesModel(jobid,regid,"");
			int id=ApplicationControler.getApplicationInstance().isApply(cm);
			if(id!=0)
			{
				throw new NotEligibleException("*YOUR ALREADY APPLY FOR THIS JOB!!");	
			}
			if(job.get(jobid).getAgeLimit()<age)
			{
				throw new NotEligibleException("*YOUR NOT ELIGIBLE FOR THIS JOB!!");
			}
			if(Validation.getInstance().isYesorNo("CHECK ABOVE DETAIL AND CLICK (Y) TO CONTINUE").toLowerCase().equals("y"))
			{
				int appllyid=ApplicationControler.getApplicationInstance().jobApply(cm);
				if(appllyid==0)
				{
					throw new DataNotFoundException("*JOB APPLICATION ERROR!!");
				}
				System.out.println("\n\t\t\t JOB APPLIED======>APPLY ID :"+appllyid);
			}
			else
				System.out.println("\n\t\t\tJOB APPLY CANCELLED...");
		ch=Character.toLowerCase(Validation.getInstance().isContinue("DO YOU WANT TO CONTINE PRESS (Y) OTHERWISE (N)"));
		}while(ch=='y');
	}
	public void printJob(HashMap<Integer,JobModel> al) throws DataNotFoundException
	{
		
		System.out.println(String.format("|%-20s|%-20s|%-20s|%-20s|%-20s|","JOBID","JOB TYPE","QUALIFICATION","AGELIMT","LAST DATE"));
		System.out.println("________________________________________________________________________________________________________");
		if(al.isEmpty())
		{
			throw new DataNotFoundException("**NO JOBS ARE AVAILALE!!!");
		}
		for(Map.Entry<Integer,JobModel> nm:al.entrySet())
		{
			System.out.println(String.format("|%-20s|%-20s|%-20s|%-20s|    %-20s|",nm.getKey(),nm.getValue().job.getJobType(),nm.getValue().getQualification(),nm.getValue().getAgeLimit(),nm.getValue().getEnddate()));	
			System.out.println("________________________________________________________________________________________________________");
		}
	}
}
