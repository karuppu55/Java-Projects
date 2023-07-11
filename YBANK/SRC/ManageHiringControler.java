package YBank;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.IOException;
class ManageHiringControler
{
	private static ManageHiringControler managehiring=null;
	private ManageHiringControler()throws SQLException,IOException
	{
	}
	public  static ManageHiringControler getInstance() throws SQLException,IOException
	{
		if(managehiring==null)
			managehiring=new ManageHiringControler();
		return managehiring;
	}
	public  int addJob(JobModel nm) throws SQLException,IOException
	{
		int id=0;
		java.sql.Date enddate=Validation.getInstance().toDate(nm.getEnddate());
		PreparedStatement ps=Database.getDatabaseInstance().getConnection().prepareStatement("Insert into jobs(jobtype,qualification,age,enddate,createdby) values"+"(?,?,?,?,?) RETURNING id;");
		ps.setInt(1,nm.job.getJobId());
		ps.setString(2,nm.getQualification());
		ps.setInt(3,nm.getAgeLimit());
		ps.setDate(4,enddate);
		ps.setInt(5,nm.getCreatedby());
		ResultSet rs=ps.executeQuery();
		if(rs.next())
		{
			id=rs.getInt("id");
		}
		ps.close();
		rs.close();		
	return id;
	}
	public  HashMap<Integer,CandidatesModel> getApplicants() throws SQLException,IOException
	{
		HashMap<Integer,CandidatesModel> al=new  HashMap<>();
		ResultSet rs=SingletonStatement.getStatement().executeQuery("SELECT r.name,r.fathername,r.gender,r.age,r.qualification,j.jobtype,c.id,c.status,c.jobid,em.status,r.docname,r.document from canditates c inner join registration r ON c.regid=r.id inner join jobtype j on c.jobid=j.id LEFT JOIN employee em On canid=c.id;");
		while(rs.next())
		{	
			if((rs.getBoolean(8))&&!(rs.getBoolean(10)))
			{
				al.put(rs.getInt(7),new CandidatesModel(rs.getString(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getString(5),rs.getString(6),rs.getInt(7),rs.getBoolean(8),rs.getInt(9),rs.getString(11),rs.getBytes(12)));
			}
		}	
		rs.close();
		return al;
	}
	public  String branchcheck(int bankid)  throws SQLException,IOException
	{
		String s=null;
		PreparedStatement ps=Database.getDatabaseInstance().getConnection().prepareStatement("SELECT id,branchname from branch WHERE id=? ;");
		ps.setInt(1,bankid);
		ResultSet rs=ps.executeQuery();
		if(rs.next()&&rs.getInt(1)==bankid)
				s=rs.getString(2);
		ps.close();
		rs.close();
		return s;
	}
	public  boolean updateStatus(boolean status,int appid)  throws SQLException,IOException
	{
	
		PreparedStatement ps=Database.getDatabaseInstance().getConnection().prepareStatement("UPDATE canditates set status=? WHERE id=?;");
		ps.setBoolean(1,status);
		ps.setInt(1,appid);
		ps.executeUpdate();
		ps.close();
		return true;
	
	}
	public  int addEmployee(EmployeeModel em)  throws SQLException,IOException
	{
		int id=0;
		PreparedStatement ps=Database.getDatabaseInstance().getConnection().prepareStatement("INSERT INTO employee(canid,emptype,bankid,createdby) VALUES (?,?,?,?) RETURNING id;");
		ps.setInt(1,em.candidate.getAppid());
		ps.setInt(2,em.job.getJobId());
		ps.setInt(3,em.getBankId());
		ps.setInt(4,em.getCreatedby());
		ResultSet rs=ps.executeQuery();
		rs.next();
		if(rs.getInt("id")!=0)
		{
			ps=Database.getDatabaseInstance().getConnection().prepareStatement("INSERT INTO login(userid,password) VALUES (?,?) RETURNING id;");
			ps.setInt(1,rs.getInt("id"));
			ps.setString(2,em.login.getPassword());
			rs=ps.executeQuery();
			rs.next();
			id=rs.getInt("id");
		}	
		ps.close();
		rs.close();
	return id;
	}
}
