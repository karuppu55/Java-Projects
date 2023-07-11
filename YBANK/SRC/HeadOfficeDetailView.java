package YBank;
import java.util.Scanner;
import YBank.utils.*;
import YBank.Validation;
import java.util.ArrayList;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.sql.SQLException;
final public class HeadOfficeDetailView implements BankTransactionCaller
{
	public void banktransactionCompile(LoginModel lm)throws IOException,SQLException
	{
		this.viewHeadOffice(lm);
	}
	
	public  void viewHeadOffice(LoginModel lm) throws IOException,SQLException,UpdationFailedException,DataNotFoundException
	{
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("\nUSERID :"+lm.getUserId()+"\t\t\tCREDIT TO HEAD OFFICE\t\t\tPOSITION :"+lm.job.getJobType());
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		
		HashMap<Integer,BranchModel> headoffice=BankTransactionControler.getInstance().getHeadOfficeDetail();
		System.out.println("\n\n"+String.format("|%-20s|%-20s|%-20s|%-20s|%-20s|%-20s|%-20s|","BRANCH ID","STATE","DISTRICT","CITY","BRANCH NAME","TYPE","AC NUMBER"));
		if(headoffice.isEmpty())
		{
			throw new DataNotFoundException("NO BRANCH FOUND!!!");
		}
		for(Map.Entry<Integer,BranchModel> branch:headoffice.entrySet())
		{
			System.out.println("\n=====================================================================================================================================================");
			System.out.println("\n"+String.format("|%-20s|%-20s|%-20s|%-20s|%-20s|%-20s|%-20s|",branch.getKey(),branch.getValue().state.getState(),branch.getValue().district.getDistrict(),branch.getValue().city.getCity(),branch.getValue().getBranchName(),branch.getValue().bank.getBankType(),branch.getValue().getAcno()));
		}
	}
}
