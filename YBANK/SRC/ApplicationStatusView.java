package YBank;
import java.util.Scanner;	
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import YBank.utils.*;
import java.io.*;
import java.sql.Statement;
import java.sql.SQLException;
public class ApplicationStatusView implements ApplicationCaller
{
	public void appCompile()throws IOException,SQLException,DataNotFoundException,DataExistsException
	{
		this.getApplicationStatus();
	}
	public static void getApplicationStatus()  throws IOException,SQLException,DataNotFoundException,DataExistsException
	{
		char ch=' ';
		do
		{
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("\n\t\t\t___Application Result___");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			int regid=Validation.getInstance().isNumber("ENTER REGISTRATION ID");
			EmployeeModel employee=ApplicationControler.getApplicationInstance().ApplyStatus(regid);
			if(employee==null)
			{
				throw new DataNotFoundException("**PLEASE REGISTER YOUR SELF FIRST!!!");
			}
			if(employee.login.getPassStatus())
			{
				throw new DataExistsException("**YOUR ALREADY EMPLOYEEE!!!");
			}
			if(employee.candidate.getStatus()&&employee.getStatus())
			{
				System.out.println("\n\t\t\t"+String.format("%-20s:%-20s","BRANCH NAME",employee.branch.getBranchName()));
				System.out.println("\n\t\t\t"+String.format("%-20s:%-20s","USER ID",employee.getId()));
				System.out.println("\n\t\t\t"+String.format("%-20s:%-20s","PASSWORD",employee.login.getPassword()));
				System.out.println("\n\t\t\t"+String.format("%-20s:%-20s","JOB TYPE",employee.job.getJobType()));
				System.out.println("\n\t\t\t"+String.format("%-20s:%-20s","STATE",employee.state.getState()));
				System.out.println("\n\t\t\t"+String.format("%-20s:%-20s","DISTRICT",employee.district.getDistrict()));
				System.out.println("\n\t\t\t"+String.format("%-20s:%-20s","CITY",employee.city.getCity()));
			}
			else if(employee.candidate.getStatus()&&!(employee.getStatus()))
			{
				System.out.println("\n\t\t\t*APPLICATION IN PROCESS!!");
			}
			else
			{
				System.out.println("\n\t\t\t***BETTER LUCK NEXT TIME!!!!");
			}
			ch=Character.toLowerCase(Validation.getInstance().isContinue("DO YOU WANT TO CONTINE PRESS (Y) OTHERWISE (N)"));
		}while(ch=='y');
	}	
}
