package YBank;
import java.util.Scanner;
import java.io.*;
import java.sql.SQLException;
public class LoginView implements YbankCaller
{
	public void compile()throws IOException,SQLException,DataNotFoundException,UpdationFailedException
	{
		this.login();
	}
	public void login() throws IOException,IOException,SQLException,DataNotFoundException,UpdationFailedException
	{
		LoginCaller login=null;
		char[] password=null;
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("\n\t\t\t___YBANK LOGIN____");
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		int userid=Validation.getInstance().isNumber("USER ID");
		System.out.print("\n\t\t\t PASSWORD	:");
		password=Validation.getInstance().readpassword.readPassword();
		if(LoginControler.getLoginInstance().isUser(userid,String.valueOf(password))==0)
			throw new DataNotFoundException("**INVALID USERID!!!!!");
		LoginModel lm=new LoginModel(userid,String.valueOf(password));
		LoginControler.getLoginInstance().getUser(lm);
		if(!(lm.getPassStatus()))
		{
			changePassword(lm);
		}
		else
		{
			if(lm.job.getJobId()==5)
				login=new AdminView();
			else
				login=new BankSoftware();
			if(login!=null)
				login.loginCompile(lm);
		}
			
	}
	public void changePassword(LoginModel lm) throws IOException,SQLException,UpdationFailedException
	{
		boolean status=true;
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("\n\t\t\t****CHANGE PASSWORD******");
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		String password=Validation.getInstance().isPassword("NEW PASSWORD");
		while(!(password.equals(Validation.getInstance().isPassword("RE ENTER NEW PASSWORD"))))
		{
			System.out.println("\n\t\t\t PASSWORD NOT MATCHED");
			password=Validation.getInstance().isPassword("NEW PASSWORD");
		}
		lm.setPassword(password);
		lm.setPassStatus(status);
		if(!LoginControler.getLoginInstance().updatePassword(lm))
			throw new UpdationFailedException("**PASSWORD NOT UPDATED!!!!!");
		System.out.println("\n\t\t\t =>PASSWORD UPDATED");
	}
}
