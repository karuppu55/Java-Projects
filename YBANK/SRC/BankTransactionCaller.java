package YBank;
import java.io.*;
import java.sql.SQLException;
public interface BankTransactionCaller
{
	public void banktransactionCompile(LoginModel lm)throws IOException,SQLException;
}
