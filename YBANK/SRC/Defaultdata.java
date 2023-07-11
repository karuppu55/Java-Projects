package YBank;
import YBank.*;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.Scanner;
final class Defaultdata
{
	Statement statement=null;
	public  void insertion() throws SQLException,IOException
	{
		if(statement==null)
		{
			statement=Database.getDatabaseInstance().getConnection().createStatement();
		}
		if(isData("registration")==0)
		{
			insertRegistration();
		}
		if(isData("jobtype")==0)
		{
			insertEmployeeType();
		}
		if(isData("jobs")==0)
		{
			insertJob();
		}	
		if(isData("canditates")==0)
		{
			insertCanditates();
		}	
		if(isData("branchtype")==0)
		{
			insertBranchType();
		}
		if(isData("states")==0)
		{
			insertState();
		}
		if(isData("districts")==0)
		{
			insertDistrict();
		}
		if(isData("cities")==0)
		{
			insertCity();
		}
		if(isData("branch")==0)
		{
			insertBranch();
		}
		if(isData("employee")==0)
		{
			insertEmployee();
		}	
		if(isData("login")==0)
		{
			insertLogin();
		}
		if(isData("accounttype")==0)
		{
			insertAccountType();
		}
		statement.close();
	}
	private int isData(String tablename) throws SQLException
	{
		int count=0;
		ResultSet r=statement.executeQuery("SELECT COUNT(*) as status FROM "+tablename+";");
		if(r.next())
			count=r.getInt(1);
		r.close();
		return count;	
	}
	private void insertRegistration() throws SQLException 
	{
			statement.executeUpdate("INSERT INTO registration(name,fathername,panno,adharno,email,gender,dob,age,phoneno,qualification) VALUES" 	+"('karuppasamy','madasamy','ALSPD1234F','123456789123','karuppu@gmail.com','male','13-07-2000',22,'9025283296','mca');");	
	}
	private void insertBranchType() throws SQLException
	{
			statement.executeUpdate("INSERT INTO branchtype(branchtype) VALUES ('urban'),('semi urban'),('metro'),('office');");		
	}
	private void insertState() throws SQLException
	{
			statement.executeUpdate("INSERT INTO states(state) VALUES ('tamilnadu');");		
	}
	private void insertDistrict() throws SQLException
	{
			statement.executeUpdate("INSERT INTO districts(stateid,district) VALUES (1,'virdhunagar');");		
	}
	private void insertCity() throws SQLException
	{
			statement.executeUpdate("INSERT INTO cities(districtid,city) VALUES (1,'srivilliputtur');");		
	}
	private void insertBranch() throws SQLException
	{
			statement.executeUpdate("INSERT INTO branch(city,branchname,btype,acno,amount,createdby) VALUES (1,'chepauk',"		
			+"4,'001100100100',0,1);");
	}
	private void insertEmployeeType() throws SQLException
	{
			statement.executeUpdate("INSERT INTO jobtype(jobtype) VALUES ('clerk'),('officer'),('manager'),('supporting'),('admin');");
	}
	private void insertEmployee() throws SQLException
	{
			statement.executeUpdate("INSERT INTO employee(canid,emptype,bankid,createdby) VALUES (1,5,1,1);");
	}
	private void insertLogin() throws SQLException
	{
			statement.executeUpdate("INSERT INTO login(userid,password) VALUES (1,'ybank@123');");
	}
	private void insertCanditates() throws SQLException
	{	
			statement.executeUpdate("INSERT INTO canditates(jobid,regid) VALUES (1,1);");	
	}
	private void insertJob() throws SQLException
	{
		statement.executeUpdate("INSERT INTO jobs(jobtype,qualification,enddate,age,createdby) VALUES (5,'ug','10-04-2023',25,1);");	
	}
	private void insertAccountType() throws SQLException
	{
		statement.executeUpdate("INSERT INTO accounttype(actype,schemecode,minage,maxage) VALUES ('minor',1001,0,18),('major',1018,18,60),('senior',1060,60,0),('simple',1000,0,0)");
	}
}

