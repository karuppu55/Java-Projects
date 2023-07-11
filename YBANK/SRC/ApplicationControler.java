package YBank;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.sql.SQLException;
import java.io.IOException;
import java.sql.Statement;
import java.io.*;
public class ApplicationControler
{
	private static ApplicationControler application=null;
	Statement statement=null;
	private ApplicationControler()throws SQLException,IOException
	{	
		if(statement==null||statement.isClosed())
		{
			statement=Database.getDatabaseInstance().getConnection().createStatement();
		}
	}
	public static ApplicationControler getApplicationInstance()throws SQLException,IOException
	{
		if(application==null)
		{
			application=new ApplicationControler();
		}
		return application;
	}
	public int registration(ApplicationModel rm) throws SQLException,IOException
	{	
		int id=0;
		java.sql.Date dob=Validation.getInstance().toDate(rm.getDob());
		PreparedStatement ps=Database.getDatabaseInstance().getConnection().prepareStatement("INSERT INTO registration(name,fathername,panno,adharno,email,gender,dob,age,phoneno,qualification,docname,document)" 	
										+"values(?,?,?,?,?,?,?,?,?,?,?,?) RETURNING id;");
		ps.setString(1,rm.getName());
		ps.setString(2,rm.getFathername());
		ps.setString(3,rm.getPanno());
		ps.setString(4,rm.getAdharno());
		ps.setString(5,rm.getEmail());
		ps.setString(6,rm.getGender());
		ps.setDate(7,dob);
		ps.setInt(8,rm.getAge());
		ps.setString(9,rm.getPhno());
		ps.setString(10,rm.getQualification());
		ps.setString(11,rm.getFileName());
		ps.setBytes(12,rm.getData());
		ResultSet r=ps.executeQuery();
		if(r.next())
		{
			id=r.getInt("id");		
		}
		ps.close();
		r.close();
		return id;
	}
	public  int isApply(CandidatesModel cm) throws SQLException,IOException
	{
		int id=0;
		PreparedStatement ps=Database.getDatabaseInstance().getConnection().prepareStatement("select c.id,c.regid,r.age from canditates c INNER JOIN registration r ON c.regid=r.id WHERE c.jobid=? and c.regid=?;");
		ps.setInt(1,cm.job.getJobId());
		ps.setInt(2,cm.registration.getRegid());
		ResultSet r=ps.executeQuery();
		if(r.next())
			id=r.getInt(1);
		ps.close();
		r.close();
		return id;	
	}
	public  HashMap<Integer,JobModel> getJobs() throws SQLException,IOException
	{
		HashMap<Integer,JobModel> al=new HashMap<>();
		ResultSet r=SingletonStatement.getStatement().executeQuery("select jt.jobtype,j.qualification,j.age,j.enddate,j.id from jobs j INNER JOIN jobtype jt ON j.jobtype=jt.id where j.enddate>CURRENT_DATE;");
		while(r.next())
			al.put(r.getInt(5),new JobModel(r.getInt(5),r.getString(1),r.getString(2),r.getInt(3),r.getString(4)));
		r.close();
		return al;	
	}
	public  int jobApply(CandidatesModel cm) throws SQLException,IOException
	{
		int id=0;
		PreparedStatement ps=Database.getDatabaseInstance().getConnection().prepareStatement("Insert into canditates(jobid,regid) values (?,?) RETURNING id;");
		ps.setInt(1,cm.job.getJobId());
		ps.setInt(2,cm.registration.getRegid());
		ResultSet r=ps.executeQuery();
		if(r.next())
			id=r.getInt("id");
		ps.close();
		r.close();
		return id;
	}
	public int isRegister(ApplicationModel rm)  throws SQLException,IOException
	{
		int id=0;
		PreparedStatement ps=Database.getDatabaseInstance().getConnection().prepareStatement("select id,adharno,age from registration where id=?;");
		ps.setInt(1,rm.getRegid());
		ResultSet r=ps.executeQuery();
		if(r.next())
			id=r.getInt(3);
		ps.close();
		r.close();
		return id;	
	}
	public  EmployeeModel ApplyStatus(int regid) throws SQLException,IOException
	{
		EmployeeModel bm=null;
		PreparedStatement ps=Database.getDatabaseInstance().getConnection().prepareStatement("SELECT l.id,l.password,jt.jobtype,s.state,d.district,c.city,b.branchname,cn.status,e.status,l.changepass FROM canditates cn LEFT JOIN employee e ON e.canid=cn.id LEFT JOIN jobtype jt ON e.emptype=jt.id LEFT JOIN branch b ON e.bankid=b.id LEFT JOIN login l ON l.userid=e.id LEFT JOIN cities c ON b.city=c.id LEFT JOIN districts d ON c.districtid=d.id LEFT JOIN states s ON d.stateid=s.id Where cn.id=?;");
		ps.setInt(1,regid);
		ResultSet r=ps.executeQuery();
		while(r.next())
			bm=new EmployeeModel(r.getInt(1),r.getString(2),r.getString(3),r.getString(4),r.getString(5),r.getString(6),r.getString(7),r.getBoolean(8),r.getBoolean(9),r.getBoolean(10));
		ps.close();
		r.close();
		return bm;	
	}
}
