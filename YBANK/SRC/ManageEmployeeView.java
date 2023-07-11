package YBank;
import YBank.utils.Selections;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.sql.SQLException;
import java.sql.Statement;
public class ManageEmployeeView implements AdminCaller
{
	public void adminCompile(LoginModel lm)
	{
		this.menu(lm);
	}
	public void menu(LoginModel lm)
	{
		ManageEmployeeCaller manageemployee=null;
		char ch;
		boolean flag=true;
		while(flag)
		{
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("\nUSER ID :"+lm.getUserId()+"\t\t\t*MANAGE EMPLOYEE DASHBOARD*\t\tPOSITION :"+lm.job.getJobType());
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("\n\t\t\t\t1.TRANSFER\n\t\t\t\t2.POSTING\n\t\t\t\t3.RELEIVING\n\t\t\t\t4.EXIT");
			try
			{
				switch(Validation.getInstance().isNumber("ENTER YOUR CHOICE"))
				{
				case 1:
					manageemployee=new EmployeeTransferView();
					break;
				case 2:
					manageemployee=new EmployeePostingView();
					break;
				case 3:
					manageemployee=new EmployeeRelivingView();
					break;
				case 4:
					flag=false;
					manageemployee=null;
					break;
				default:
				manageemployee=null;
				System.out.println("\n\t\t\t**ENTER CORECT CHOICE!!");
				}
				if(manageemployee!=null)
					manageemployee.manageemployeecompile(lm);
			}
			catch(UpdationFailedException ufe)
			{
				System.out.println("\n"+ufe.getMessage());
			}
			catch(DataExistsException dee)
			{
				System.out.println("\n"+dee.getMessage());
			}
			catch(DataNotFoundException dnfe)
			{
				System.out.println("\n"+dnfe.getMessage());
			}
			catch(SQLException sql)
			{
				System.out.println("\n"+sql.getMessage());
			}	
			catch(IOException io)
			{
				System.out.println(io);
			}
			catch(Exception e)
			{
				System.out.println("UNEXPECTED ERROR OCCURE IN MANAGE EMPLOYEE MENU!!!");
			}	
		}
	}
	public static void printEmpDetail(EmployeeModel emp)
	{
		System.out.println("\n\t\t\tEMP NAME	:"+emp.getName()+"\n\t\t\tBANK ID		:"+emp.branch.getBankId()+"\n\t\t\tBANK LOCATION	:"+emp.city.getCity()+"\n\t\t\tBRANCH NAME	:"+emp.branch.getBranchName()+"\n\t\t\tEMPLOYEE TYPE	:"+emp.job.getJobType());
	}
}
