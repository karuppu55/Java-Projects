package YBank;
import java.io.*;
import java.sql.SQLException;
public interface AccountCaller
{
	public void accountCompile(LoginModel lm)throws IOException,SQLException;
}
