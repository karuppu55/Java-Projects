package YBank;
import YBank.utils.*;
import YBank.Validation;
import java.util.ArrayList;
import java.io.*;
import java.sql.SQLException;
public class DeleteWithdraw extends DeleteDeposit implements WithdrawCaller
{
	public void withdrawCompile(LoginModel lm)throws IOException,SQLException
	{
		this.delete(lm);
	}
}

