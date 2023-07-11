package YBank;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;
import YBank.utils.*;
import java.util.HashMap;
import java.util.Map;
import java.sql.Statement;
import java.sql.SQLException;
public class AddCustomerView implements AccountCaller
{
	public void accountCompile(LoginModel lm)throws IOException,SQLException
	{
		this.addCustomer(lm);
	}
	public void addCustomer(LoginModel lm)  throws IOException,SQLException,DataExistsException,DataNotFoundException,UpdationFailedException
	{
		char ch=' ';
		do
		{
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("\nUSERID :"+lm.getUserId()+"\t\t\tADD CUSTOMER\t\t\tPOSITION :"+lm.job.getJobType());
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			String name=Validation.getInstance().isCharacter("ENTER NAME");
			String fathername=Validation.getInstance().isCharacter("ENTER FATHER NAME");
			System.out.print("\n\t\t\t\t1.MALE\n\t\t\t\t2.FEMALE\n\t\t\t\t3.TRANSGENDER");
			int choice=Validation.getInstance().isNumber("ENTER");
			while(!(choice>=1&&choice<=3))
			{	
				System.out.println("\n\t\t\t**ENTER CORECT CHOICE!!");
				choice=Validation.getInstance().isNumber("ENTER");
			}
			String gender=Selections.gender(choice);
			String dob=Validation.getInstance().isDate("ENTER DOB (DD-MM-YYYY)");
			int age=Calculations.agecalculation(dob);
			String phno=Validation.getInstance().isMobile("ENTER PHONE NUMBER");
			String email=Validation.getInstance().isMail("ENTER EMAIL");
			String adharno=Validation.getInstance().isAdhar("ENTER ADHAR");
			String panno=Validation.getInstance().isPan("ENTER PAN").toUpperCase();		
			int doornumber=Validation.getInstance().isNumber("ENTER DOOR NO");
			String Street=Validation.getInstance().isCharacter("ENTER STREET");
			String city=Validation.getInstance().isCharacter("ENTER CITY");
			String district=Validation.getInstance().isCharacter("ENTER DISTRICT");
			int pincode=Validation.getInstance().isPincode("ENTER PINCODE");
			String address=doornumber+","+Street+","+city+","+district+"-"+pincode;
			System.out.print("\n\t\t\tDETAILS ARE\n\t\t\t\tNAME\t\t:"+name+"\n\t\t\t\tFATHER NAME\t:"+fathername+"\n\t\t\t\tGENDER\t\t:"+gender+"\n\t\t\t\tDOB\t\t:"+dob+"\n\t\t\t\tAGE\t\t:"+age+"\n\t\t\t\tMOBILE NO\t:"+phno+"\n\t\t\t\tADHAR NO\t:"+adharno+"\n\t\t\t\tPAN NO\t\t"+panno+"\n\t\t\t\tADDRESS\t\t:"+address);
			CustomerModel cm=new CustomerModel(name,fathername,gender,dob,age,phno,email,adharno,panno,address,lm.getUserId(),lm.branch.getBankId());
			if(Validation.getInstance().isYesorNo("CHECK ABOVE DETAIL AND CLICK (Y) TO CONTINUE").toLowerCase().equals("y"))
			{
				int checkid=AccountControler.getInstance().isCustomer(cm);
				if(checkid!=0)
				{
					throw new DataExistsException("CUSTOMER ALREADY EXITS!!!");
				}
				int id=AccountControler.getInstance().addCustomer(cm);
				if(id==0)
				{	
					throw new UpdationFailedException("CUSTOMER ID NOT CREATED!!!");
				}
				System.out.println("\n\t\t\tDETAILS ADDED SUCCUSSFULLY.....\n\t\t\t CUSTOMER ID is :"+id);
			}
			else
			{
				System.out.println("\n\t\t\t*CANCELLED");
			}
			ch=Character.toLowerCase(Validation.getInstance().isContinue("DO YOU WANT TO CONTINE PRESS (Y) OTHERWISE (N)"));
		}while(ch=='y');
	}
}
