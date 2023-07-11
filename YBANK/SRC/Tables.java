package YBank;
import YBank.*;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;
import java.io.IOException;
final public class Tables
{
	private Statement statement=null;
	public  void createTables() throws SQLException,IOException
	{
		if(statement==null||statement.isClosed())
		{
			statement=Database.getDatabaseInstance().getConnection().createStatement();
		}
		Database.getDatabaseInstance().getConnection().setAutoCommit(false);
		registration();
		jobtype();
		jobs();
		cantidates();
		branchtype();
		state();
		district();
		city();
		branch();
		employee();
		customerdetail();
		accounttype();
		account();
		logindetail();
		voucher();
		bankbalance();
		Database.getDatabaseInstance().getConnection().commit();
		Database.getDatabaseInstance().getConnection().setAutoCommit(true);
		intertransaction();
		statement.close();
	}
	private void jobtype() throws SQLException
	{
			statement.executeUpdate("CREATE TABLE IF NOT EXISTS jobtype(id serial PRIMARY KEY,jobtype varchar(15) NOT NULL,status boolean DEFAULT 'true',createdon date NOT NULL DEFAULT CURRENT_DATE);");
	}
	private  void jobs() throws SQLException
	{
		
			statement.executeUpdate("CREATE TABLE IF NOT EXISTS jobs(id SERIAL PRIMARY KEY,jobtype int  NOT NULL,qualification varchar(20) NOT NULL,enddate DATE NOT NULL,age int NOT NULL,status boolean DEFAULT true,createdby int NOT NULL,createdon date NOT NULL DEFAULT CURRENT_DATE,CONSTRAINT fk_jobtype FOREIGN KEY(jobtype) REFERENCES jobtype(id) ON DELETE CASCADE);");
	}
	private  void registration() throws SQLException
	{
		
			statement.executeUpdate("CREATE TABLE IF NOT EXISTS registration(id serial PRIMARY KEY,name varchar(30) NOT NULL,fathername varchar(30) NOT NULL,panno varchar(14) NOT NULL,adharno varchar(14) UNIQUE NOT NULL,email varchar(50) UNIQUE NOT NULL,age int NOT NULL,gender varchar(10) NOT NULL,dob DATE NOT NULL,phoneno varchar(11) NOT NULL,qualification varchar(10) NOT NULL,docname varchar(50),document bytea,status boolean DEFAULT false,createdon date NOT NULL DEFAULT CURRENT_DATE);");
	}
	private  void branchtype() throws SQLException
	{
		
			statement.executeUpdate("CREATE TABLE IF NOT EXISTS branchtype(id SERIAL PRIMARY KEY,branchtype varchar(15) NOT NULL,status boolean DEFAULT 'true',createdon DATE NOT NULL DEFAULT CURRENT_DATE);");
		
	}
	private  void state() throws SQLException
	{
		
			statement.executeUpdate("CREATE TABLE IF NOT EXISTS states(id SERIAL PRIMARY KEY,state varchar(50),status boolean default true);");
		
	}
	private  void district() throws SQLException
	{
		
			statement.executeUpdate("CREATE TABLE IF NOT EXISTS districts(id SERIAL PRIMARY KEY,stateid int NOT NULL,district varchar(50),status boolean default true,CONSTRAINT fk_stateid FOREIGN KEY(stateid) REFERENCES states(id));");
		
	}
	private  void city() throws SQLException
	{
		
			statement.executeUpdate("CREATE TABLE IF NOT EXISTS cities(id SERIAL PRIMARY KEY,districtid int NOT NULL,city varchar(50),status boolean default true,CONSTRAINT fk_districtid FOREIGN KEY(districtid) REFERENCES districts(id));");
		
	}
	private  void branch() throws SQLException
	{
			statement.executeUpdate("CREATE TABLE IF NOT EXISTS branch(id SERIAL PRIMARY KEY,city int NOT NULL,branchname varchar(20) NOT NULL,btype int NOT NULL,acno varchar(20) UNIQUE NOT NULL,amount decimal NOT NULL,status boolean DEFAULT 'true',createdon DATE NOT NULL DEFAULT CURRENT_DATE,createdby int NOT NULL,CONSTRAINT fk_city FOREIGN KEY(city) REFERENCES cities(id),CONSTRAINT fk_btype FOREIGN KEY(btype) REFERENCES branchtype(id)ON DELETE CASCADE);");
		 
	}
	
	private  void employee() throws SQLException
	{
		
			statement.executeUpdate("CREATE TABLE IF NOT EXISTS employee(id serial PRIMARY KEY,canid int NOT NULL,emptype int NOT NULL,bankid bigint NOT NULL,status boolean DEFAULT 'true',createdon date NOT NULL default CURRENT_DATE,createdby bigint NOT NULL,CONSTRAINT fk_canid FOREIGN KEY(canid) REFERENCES canditates(id),CONSTRAINT fk_etype FOREIGN KEY(emptype) REFERENCES jobtype(id),CONSTRAINT fk_bankid FOREIGN KEY(bankid) REFERENCES branch(id)ON DELETE CASCADE);");
		
	}
	private  void logindetail() throws SQLException
	{
		
			statement.executeUpdate("CREATE TABLE IF NOT EXISTS login(id serial PRIMARY KEY,userid int NOT NULL,password varchar(16) NOT NULL,changepass BOOLEAN DEFAULT false, CONSTRAINT fk_userid FOREIGN KEY(userid) REFERENCES employee(id)ON DELETE CASCADE);");
		
	}
	private   void cantidates()throws SQLException
	{
		
			statement.executeUpdate("CREATE TABLE IF NOT EXISTS canditates(id SERIAL PRIMARY KEY,jobid int NOT NULL,regid int NOT NULL,status varchar(10) default 'true',createdon DATE DEFAULT CURRENT_DATE,CONSTRAINT fk_jobid FOREIGN KEY(jobid) REFERENCES jobs(id),CONSTRAINT fk_adhar FOREIGN KEY(regid) REFERENCES registration(id)ON DELETE CASCADE);");
		
	}
	
	private   void customerdetail() throws SQLException
	{
		
			statement.executeUpdate("CREATE TABLE IF NOT EXISTS customer(id SERIAL PRIMARY KEY,name varchar(30) NOT NULL,fathername varchar(30) NOT NULL,gender varchar(10) NOT NULL,dob varchar(15) NOT NULL,age int NOT NULL,mobileno varchar(11) NOT NULL,email varchar(50) NOT NULL,adharno varchar(14) UNIQUE,panno varchar(12),address varchar(50) NOT NULL,bankid bigint,status boolean DEFAULT false,createdon DATE NOT NULL DEFAULT CURRENT_DATE,createdby int NOT NULL,CONSTRAINT fk_bankid FOREIGN KEY(bankid) REFERENCES branch(id),CONSTRAINT fk_createdby FOREIGN KEY(createdby) REFERENCES employee(id)ON DELETE CASCADE);");
		 
	}
	private   void accounttype()throws SQLException
	{
		
			statement.executeUpdate("CREATE TABLE IF NOT EXISTS accounttype(id SERIAL PRIMARY KEY,actype varchar(20) NOT NULL,schemecode bigint NOT NULL,minage int,maxage int,status boolean DEFAULT 'true',createdon DATE NOT NULL DEFAULT CURRENT_DATE);");
		 
	}
	private   void account()throws SQLException
	{
		
			statement.executeUpdate("CREATE TABLE IF NOT EXISTS account(id serial PRIMARY KEY,cusid int NOT NULL,acno varchar(30) UNIQUE NOT NULL,amount decimal NOT NULL,actype int NOT NULL,bankid int NOT NULL,status boolean DEFAULT true,createdon DATE NOT NULL DEFAULT CURRENT_DATE,createdby int NOT NULL,CONSTRAINT fk_cusid FOREIGN KEY(cusid) REFERENCES customer(id),CONSTRAINT fk_createdby FOREIGN KEY(createdby) REFERENCES employee(id),CONSTRAINT fk_actype FOREIGN KEY(actype) REFERENCES accounttype(id),CONSTRAINT fk_bankid FOREIGN KEY(bankid) REFERENCES branch(id)ON DELETE CASCADE);");
		
	}
	private   void voucher()throws SQLException
	{
		
			statement.executeUpdate("CREATE TABLE IF NOT EXISTS voucher(id SERIAL PRIMARY KEY,acid int NOT NULL,particular varchar(50) NOT NULL,amount bigint NOT NULL,vctype varchar(10),bankid bigint NOT NULL,status varchar(15) DEFAULT 'added',createdon DATE NOT NULL DEFAULT CURRENT_DATE,createdby int NOT NULL,CONSTRAINT fk_cusid FOREIGN KEY(acid) REFERENCES account(id),CONSTRAINT fk_createdby FOREIGN KEY(createdby) REFERENCES employee(id),CONSTRAINT fk_bankid FOREIGN KEY(bankid) REFERENCES branch(id)ON DELETE CASCADE);");
		
	}
	private void bankbalance() throws SQLException
	{
		statement.executeUpdate("create table IF NOT EXISTS bankbalance(id SERIAL PRIMARY KEY,bankid int,opening decimal,closing decimal,createdon DATE NOT NULL DEFAULT CURRENT_DATE,CONSTRAINT fk_bankid FOREIGN KEY(bankid) REFERENCES branch(id));");
	}
	public void intertransaction() throws SQLException
	{
		statement.executeUpdate("create table IF NOT EXISTS intertransaction(id serial primary key,bankid int,amount decimal,office int,transtype varchar(40),status varchar(20) default 'added',CONSTRAINT fk_bankid FOREIGN KEY(bankid) REFERENCES branch(id),CONSTRAINT fk_office FOREIGN KEY(office) REFERENCES branch(id));");
	}
}
