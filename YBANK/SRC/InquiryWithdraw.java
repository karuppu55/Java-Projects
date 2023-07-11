package YBank;
import YBank.utils.*;
import YBank.Validation;
import java.util.ArrayList;
import java.io.*;
import java.sql.SQLException;
public class InquiryWithdraw extends InquiryDeposit implements WithdrawCaller
{
	public void withdrawCompile(LoginModel lm)throws IOException,SQLException
	{
		this.inquiry(lm);
	}
}

