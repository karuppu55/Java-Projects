package YBank;
import java.util.Scanner;
import YBank.utils.*;
import YBank.Validation;
import java.util.ArrayList;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.sql.SQLException;
public class BranchDetailView implements BranchCaller
{
	public void branchCompile(LoginModel lm)throws IOException,SQLException
	{
		this.accountInfo(lm);
	}
	public void accountInfo(LoginModel lm)  throws IOException,SQLException,DataNotFoundException,AccountNotFoundException
	{
	
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("\nUSERID :"+lm.getUserId()+"\t\t\tACCOUNT DETAIL\t\t\tPOSITION :"+lm.job.getJobType());	
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		BranchModel ad=BranchControler.getInstance().getAccount(lm.branch.getBankId());	
		if(ad==null)
		{
			throw new AccountNotFoundException("BRANCH DETAIL NOT FOUND!!!");
		}
		System.out.println("\n\t\tBRANCH NAME\t:"+ad.getBranchName()+"\t\tBRANCH TYPE:"+ad.bank.getBankType()+"\n\n\t\tBRANCH ACCOUNT\t\t:"+ad.getAcno()+"\t\tCASH IN HAND\t:"+ad.getAmount()+"\n\n\t\tOPENING DATE\t:"+ad.getCreatedon()+"\t\tCITY\t:"+ad.city.getCity()+"\n\n\t\tDISTRICT\t:"+ad.district.getDistrict()+"\t\tOPENING DATE\t:"+ad.state.getState());	

	}
}