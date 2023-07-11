package YBank;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.IOException;
class ManagEmployeeControler
{
	private static ManagEmployeeControler managebank=null;
	private ManagEmployeeControler()
	{
	}
	public static  ManagEmployeeControler getInstance()throws SQLException,IOException
	{
		if(managebank==null)
			managebank=new ManagEmployeeControler();
		return managebank;
	}
	public  EmployeeModel getEmployee(int id)throws SQLException,IOException
	{
		EmployeeModel bm=null;
		PreparedStatement ps=Database.getDatabaseInstance().getConnection().prepareStatement("SELECT c.city,br.branchname,re.name,jt.jobtype,em.id,br.id,em.status FROM branch br INNER JOIN employee em on em.bankid=br.id INNER JOIN canditates ce ON ce.id=em.canid INNER JOIN registration re on ce.regid=re.id INNER JOIN jobtype jt ON em.emptype=jt.id JOIN cities c ON br.city=c.id WHERE em.id=?;");
		ps.setInt(1,id);
		ResultSet rs=ps.executeQuery();
		if(rs.next())
		{
			bm=new EmployeeModel(rs.getString(1),rs.getString(2),rs.getString(3),rs.getInt(5),rs.getString(4),rs.getInt(6),rs.getBoolean(7));	
		}
		ps.close();
		rs.close();
		return bm;
	}
	public  HashMap<Integer,JobTypeModel> getJobType()throws SQLException,IOException
	{
		HashMap<Integer,JobTypeModel> job=new HashMap<>();
		ResultSet rs=SingletonStatement.getStatement().executeQuery("SELECT id,jobtype FROM jobtype;");
		while(rs.next())
		{
			job.put(rs.getInt(1),new JobTypeModel(rs.getString(2)));
		}
		rs.close();
		return job;
	}
	public  boolean updateTransfer(EmployeeModel emp)throws SQLException,IOException
	{

		PreparedStatement ps=Database.getDatabaseInstance().getConnection().prepareStatement("UPDATE employee SET bankid=? WHERE id=?;");
		ps.setInt(1,emp.branch.getBankId());
		ps.setInt(2,emp.getId());
		ps.executeUpdate();
		ps.close();
		return true;	
	
	}	
	public  boolean updatePosting(EmployeeModel emp)throws SQLException,IOException
	{
		PreparedStatement ps=Database.getDatabaseInstance().getConnection().prepareStatement("UPDATE employee SET emptype=? WHERE id=?;");
		ps.setInt(1,emp.job.getJobId());
		ps.setInt(2,emp.getId());
		ps.executeUpdate();
		ps.close();
		return true;	

	}	
	public  boolean updateRelive(EmployeeModel emp)throws SQLException,IOException
	{
		int canid=0;
		PreparedStatement ps=Database.getDatabaseInstance().getConnection().prepareStatement("UPDATE employee SET status=? WHERE id=? returning canid;");
		ps.setBoolean(1,emp.getStatus());
		ps.setInt(2,emp.getId());
		ResultSet rs=ps.executeQuery();
		if(rs.next())
		{
			canid=rs.getInt("canid");
		}
		if(canid!=0)
		{
			ps=Database.getDatabaseInstance().getConnection().prepareStatement("UPDATE canditates SET status=? WHERE id=?;");
			ps.setBoolean(1,emp.getStatus());
			ps.setInt(2,canid);
			ps.executeUpdate();
		}
		ps.close();
		rs.close();
		return true;	
	}	
}
