package YBank;
import java.io.*;
import java.sql.SQLException;
public interface BranchCaller
{
	public void branchCompile(LoginModel lm)throws IOException,SQLException;
}
