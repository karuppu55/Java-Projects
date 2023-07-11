package YBank;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.IOException;
import java.sql.Statement;
public class LoginControler
{
	private static LoginControler login=null;
	private LoginControler()
	{	
		
	}
	public static LoginControler getLoginInstance()
	{
		if(login==null)
			login=new LoginControler();
		return login;
	}
	public void getUser(LoginModel lm) throws SQLException,IOException
	{
		PreparedStatement ps=Database.getDatabaseInstance().getConnection().prepareStatement("SELECT lo.userid,lo.password,jo.jobtype,em.bankid,lo.changepass,em.emptype from login lo INNER JOIN employee"
								     +" em ON lo.userid=em.id INNER JOIN jobtype jo ON em.emptype=jo.id WHERE lo.userid="+lm.getUserId()+";");
		ResultSet rs=ps.executeQuery();
		if(rs.next())
		{
				lm.setUserId(rs.getInt(1));
				lm.job.setJobType(rs.getString(3));
				lm.branch.setBankId(rs.getInt(4));
				lm.setPassStatus(rs.getBoolean(5));
				lm.job.setJobId(rs.getInt(6));
		}
		ps.close();
		rs.close();
	}
	public int isUser(int userid,String password) throws SQLException,IOException
	{
		int id=0;
		PreparedStatement ps=Database.getDatabaseInstance().getConnection().prepareStatement("SELECT userid,password from login WHERE userid="+userid+" and password='"+password+"';");
		ResultSet rs=ps.executeQuery();
		if(rs.next())
		{
			id=rs.getInt(1);	
		}
		ps.close();
		rs.close();
		return id;
	}
	public boolean updatePassword(LoginModel lm) throws SQLException,IOException
	{
		String password=lm.getPassword();
		boolean changepass=lm.getPassStatus();
		int id=lm.getUserId();
		PreparedStatement ps=Database.getDatabaseInstance().getConnection().prepareStatement("UPDATE login SET password='"+password+"',changepass="+changepass+" WHERE userid="+id+";");
		int row=ps.executeUpdate();
		if(row==0)
		{
			throw new SQLException("**PASSWORD NOT CHANGED!!!!!");
		}
		ps.close();
		return true;
	}
}
