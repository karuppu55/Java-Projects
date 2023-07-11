package YBank;
import java.util.Scanner;
import YBank.utils.Selections;
import java.util.ArrayList;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.sql.SQLException;
public class AddEmployeeView implements ManageHiringCaller
{
	public void managehiringcompile(LoginModel lm)throws IOException,SQLException,UpdationFailedException,DataNotFoundException
	{
		this.hiringEmp(lm);
	}
	private void printCantidates(HashMap<Integer,CandidatesModel> al)
	{
		System.out.println(String.format("|%-20s|%-20s|%-20s|%-20s|%-20s|%-20s|%-20s|%-20s","APPLICATION ID","NAME","FATHER NAME","GENDER","AGE","QUALIFICATION","JOBTYPE","STATUS","FILENAME"));
			for(Map.Entry<Integer,CandidatesModel> det:al.entrySet())
			{
System.out.println("__________________________________________________________________________________________________________________________________________________");
			System.out.println(String.format("|%-20s|%-20s|%-20s|%-20s|%-20s|%-20s|%-20s|%-20s",det.getKey(),det.getValue().registration.getName(),det.getValue().registration.getFathername(),det.getValue().registration.getGender(),det.getValue().registration.getAge(),det.getValue().registration.getQualification(),det.getValue().job.getJobType(),det.getValue().getStatus(),det.getValue().registration.getFileName()));
			}
	}
	private void hiringEmp(LoginModel lm) throws IOException,SQLException,DataNotFoundException,UpdationFailedException
	{
		char ch=' ';
		do
		{
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("\nUSER ID :"+lm.getUserId()+"\t\t\t*HIRING CANDITATES*\t\tPOSITION :"+lm.job.getJobType());
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("\n\t\t\t___APPLICANTS VIEW___");
			HashMap<Integer,CandidatesModel> al=ManageHiringControler.getInstance().getApplicants();
			printCantidates(al);
			if(al.isEmpty())
			{	
				throw new DataNotFoundException("NO DATA FOUND!!!!");	
			}
			int appid=Validation.getInstance().isNumber("ENTER APPLICATION ID");
			while(!al.containsKey(appid))
			{
				System.out.println("\n\t\t\t**ENTER CORRECT APPLICATION!!");
				appid=Validation.getInstance().isNumber("ENTER");
			}
			readDocument(al.get(appid).registration.getFileName(),al.get(appid).registration.getData());
			System.out.println("\n\t\t\t\t1.SELECT\n\t\t\t\t2.REJECT\n\t\t\t\t3.CANCEL");
			boolean status=Selections.empselect(Integer.parseInt(Validation.getInstance().reader.readLine()));
			if(status)
			{
				int bankid=Validation.getInstance().isNumber("ENTER BANK ID");
				String branch=ManageHiringControler.getInstance().branchcheck(bankid);
				if(branch==null)
				{
					throw new DataNotFoundException("BRANCH NOT FOUND!!!!");	
				}
				EmployeeModel em=new EmployeeModel(appid,al.get(appid).job.getJobId(),bankid,lm.getUserId());
				int id=ManageHiringControler.getInstance().addEmployee(em);
				if(id==0)
				{
					throw new UpdationFailedException("EMPLOYEE NOT ADDED!!!!");
				}
				if(!ManageHiringControler.getInstance().updateStatus(status,appid))
				{
					throw new UpdationFailedException("SELECTION STATUS NOT UPDATED!!!!");
				}
				System.out.println("\n\t\t\tEMPLOYEE ID	:"+id+"\n\t\t\tBRANCH NAME	:"+branch);
					
			}
			else if(!status)
			{
				if(!ManageHiringControler.getInstance().updateStatus(status,appid))
				{
					throw new UpdationFailedException("REJECTION STATUS NOT UPDATED!!!!");
				}
				System.out.println("\n\t\t\t**EMPLOYEE REJECTION STATUS UPDATED!!");
			}
			ch=Character.toLowerCase(Validation.getInstance().isContinue("DO YOU WANT TO CONTINE PRESS (Y) OTHERWISE (N)"));
		}while(ch=='y');
	}
	public boolean readDocument(String filename,byte[] data)throws IOException
	{
		File file=new File("//home//zoho//Documents//Karuppu//PROJECT DETAIL//YBANK//EMPDATA//"+filename);
		OutputStream os=new FileOutputStream(file);
		os.write(data);
		return true;
	}
}
