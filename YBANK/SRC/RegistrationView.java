package YBank;
import YBank.utils.*;
import java.io.*;
import java.sql.SQLException;
public class RegistrationView implements ApplicationCaller
{
	public void appCompile()throws IOException,SQLException
	{
		this.registration();
	}
	public void registration() throws IOException,SQLException,DataNotFoundException
	{
		char ch=' ';
		do
		{
			String msg,phno,adharno,panno,email;
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("\n\t\t\t___REGISTRATION PAGE___");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			String name=Validation.getInstance().isCharacter("ENTER YOUR NAME");
			String fathername=Validation.getInstance().isCharacter("ENTER FATHER NAME");
			System.out.println("\n\t\t\t\t1.MALE\n\t\t\t\t2.FEMALE\n\t\t\t\t3.TRANSGENDER");
			int choice=Validation.getInstance().isNumber("ENTER");
			while(!(choice>=1&&choice<=3))
			{	
				System.out.println("\n\t\t\t**ENTER CORECT CHOICE!!");
				choice=Validation.getInstance().isNumber("ENTER");
			}
			String gender=Selections.gender(choice);
			String dob=Validation.getInstance().isDate("ENTER DOB (DD-MM-YYYY)");
			int age=Calculations.agecalculation(dob);
			phno=Validation.getInstance().isMobile("ENTER PHONE NUMBER");
			email=Validation.getInstance().isMail("ENTER EMAIL");
			adharno=Validation.getInstance().isAdhar("ENTER ADHAR");
			panno=Validation.getInstance().isPan("ENTER PAN").toUpperCase();		
			String qualification=Validation.getInstance().isCharacter("QUALIFICATION (SSLC)or(HSC)or(UG)or(PG)");
			System.out.println("\n\t\t\t**UPLOAD YOUR DOCUMENTS");
			String location=Validation.getInstance().isLocation("GIVE LOCATION AND FILE NAME(eg://folder//..//filename.type)");
			File filelocation=new File(location);
			while(!checkFile(filelocation))
			{
				System.out.println("\n\t\t\t**DOCUMENT NOT FOUND");
				location=Validation.getInstance().isLocation("GIVE LOCATION AND FILE NAME(eg://folder//..//filename.type)");
				filelocation=new File(location);
			}
			byte[] data=getFileData(filelocation);
			ApplicationModel rm=new ApplicationModel(name,fathername,gender,dob,age,phno,email,adharno,panno,qualification,filelocation.getName(),data);
			if(Validation.getInstance().isYesorNo("CHECK ABOVE DETAIL AND CLICK (Y) TO CONTINUE").toLowerCase().equals("y"))
			{
				int id=ApplicationControler.getApplicationInstance().registration(rm);
				System.out.println("\n\t\t\tAPPLICATION REGISTERED===>REGISTER ID :"+id);
			}
			else
				System.out.println("\n\tREGISTRATION CANCELLED...");
			ch=Character.toLowerCase(Validation.getInstance().isContinue("DO YOU WANT TO CONTINE PRESS (Y) OTHERWISE (N)"));
		}while(ch=='y');	
	}
	public byte[] getFileData(File location)throws IOException
	{
		InputStream in=new FileInputStream(location);
		byte[] data=new byte[(int)location.length()];
		in.read(data);
		in.close();
		return data;
	}
	public boolean checkFile(File location)throws IOException
	{
		if(location.exists())
		{
			return true;
		}
		return false;
	}
}	
