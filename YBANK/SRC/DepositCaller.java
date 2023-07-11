package YBank;
import java.io.*;
import java.sql.SQLException;
public interface DepositCaller
{
	public void depositCompile(LoginModel lm)throws IOException,SQLException;
}
