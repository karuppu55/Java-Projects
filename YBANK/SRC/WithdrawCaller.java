package YBank;
import java.io.*;
import java.sql.SQLException;
public interface WithdrawCaller
{
	public void withdrawCompile(LoginModel lm)throws IOException,SQLException;
}
