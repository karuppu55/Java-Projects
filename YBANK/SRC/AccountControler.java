package YBank;
import YBank.*;
import YBank.Database;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.sql.SQLException;
final class AccountControler
{
	private static AccountControler account=null;
	private AccountControler()throws SQLException,IOException
	{
	}
	public static AccountControler getInstance()throws SQLException,IOException
	{
		if(account==null)
			account=new AccountControler();
		return account;
	}
	public  int isCustomer(CustomerModel cm) throws SQLException,IOException
	{
		int id=0;
		PreparedStatement ps=Database.getDatabaseInstance().getConnection().prepareStatement("select id from customer where adharno=?;");
		ps.setString(1,cm.getAdharno());
		ResultSet rs=ps.executeQuery();
		if(rs.next())
			id=rs.getInt(1);
		ps.close();
		rs.close();
		return id;
	}
	public  int addCustomer(CustomerModel cd) throws SQLException,IOException
	{	
		int id=0;	
		PreparedStatement ps=Database.getDatabaseInstance().getConnection().prepareStatement("insert into customer(name,fathername,gender,dob,age,mobileno,email,adharno,panno,address,bankid,createdby)"
		+"values(?,?,?,?,?,?,?,?,?,?,?,?) RETURNING id;");
		ps.setString(1,cd.getName());
		ps.setString(2,cd.getFathername());
		ps.setString(3,cd.getGender());
		ps.setString(4,cd.getDob());
		ps.setInt(5,cd.getAge());
		ps.setString(6,cd.getMobile());
		ps.setString(7,cd.getEmail());
		ps.setString(8,cd.getAdharno());
		ps.setString(9,cd.getPanno());
		ps.setString(10,cd.getAddress());
		ps.setInt(11,cd.getBankId());
		ps.setInt(12,cd.getCreatedby());
		ResultSet rs=ps.executeQuery();
		if(rs.next())
			id=rs.getInt("id");
		ps.close();
		rs.close();
		return id;
		
	}
	public  CustomerModel getCustomer(int cusid) throws SQLException,IOException
	{		
		CustomerModel cm=null;
		ResultSet rs=SingletonStatement.getStatement().executeQuery("select c.id,c.name,c.fathername,c.gender,c.dob,c.age,c.mobileno,c.email,c.adharno,c.panno,c.address,c.bankid,c.createdby,c.status,a.status from customer c LEFT JOIN account a ON a.cusid=c.id;");
		while(rs.next())
		{ 
			if(!(rs.getBoolean(14))&&!(rs.getBoolean(15)))
			cm=new CustomerModel(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getInt(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11),rs.getInt(12),rs.getInt(13),rs.getBoolean(14));
		}
		rs.close();
		return cm;
	}
	
	
	public  HashMap<Integer,AccountTypeModel> getAccountType() throws SQLException,IOException
	{
		HashMap<Integer,AccountTypeModel> actype=new HashMap<>();
		ResultSet rs=SingletonStatement.getStatement().executeQuery("select id,actype,schemecode,minage,maxage from accounttype;");
		while(rs.next())
		{
			actype.put(rs.getInt(1),new AccountTypeModel(rs.getString(2),rs.getLong(3),rs.getInt(4),rs.getInt(5)));
		}
		rs.close();
		return actype;
	}
	
	
	public  int isAccount(int cusid) throws SQLException,IOException
	{
		int id=0;
		ResultSet rs=SingletonStatement.getStatement().executeQuery("select id from account where cusid="+cusid+";");
		if(rs.next())
			id=rs.getInt(1);
		rs.close();
		return id;
	}
	
	
	public  int getBranchAccountCount(int bankid,int actype)throws SQLException,IOException	//GET ACCOUNT SCHEMS COUNT FOR ACCOUNT NUMBER GENERATION
	{
		int count=0;
		ResultSet rs=SingletonStatement.getStatement().executeQuery("select count(*) from branch b INNER join account a ON a.bankid=b.id where bankid="+bankid+" and a.actype="+actype+";");
		if(rs.next())
			count=rs.getInt(1);
		rs.close();
		return count+1;
	}
	
	public  int addAccount(AccountModel am)throws SQLException,IOException
	{
		int id=0;
		PreparedStatement ps=Database.getDatabaseInstance().getConnection().prepareStatement("insert into account(cusid,acno,amount,actype,bankid,createdby) values"+"(?,?,?,?,?,?) RETURNING id;");
		ps.setInt(1,am.customer.getCusId());
		ps.setString(2,am.getAcno());
		ps.setDouble(3,am.getAmount());
		ps.setInt(4,am.acctype.getAcid());
		ps.setInt(5,am.customer.getBankId());
		ps.setInt(6,am.customer.getCreatedby());
		ResultSet rs=ps.executeQuery();
		if(rs.next())
			id=rs.getInt("id");
		ps.close();
		rs.close();
		return id;
	}
	
	public  int updateCustomer(CustomerModel cm)throws SQLException,IOException
	{
		int row=0;
		PreparedStatement ps=Database.getDatabaseInstance().getConnection().prepareStatement("update customer set status=? where id=?;");
		ps.setBoolean(1,cm.getStatus());
		ps.setInt(2,cm.getCusId());
		row=ps.executeUpdate();
		ps.close();
		return row;
	}
	
	public  AccountModel getAccount(String acno)throws SQLException,IOException
	{
		AccountModel am=null;
		PreparedStatement ps=Database.getDatabaseInstance().getConnection().prepareStatement("select a.cusid,a.id,a.acno,a.amount,c.name,c.mobileno,c.adharno,a.createdon,a.status,a.actype,a.bankid from account a inner join customer c on a.cusid=c.id where acno=?;");
		ps.setString(1,acno);
		ResultSet rs=ps.executeQuery();
		while(rs.next())
			am=new AccountModel(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getDouble(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getBoolean(9),rs.getString(10),rs.getInt(11));
		ps.close();
		rs.close();
		return am;	
	}
	
	public  ArrayList<AccountModel> statements(String acno) throws SQLException,IOException
	{
		ArrayList<AccountModel> al=new ArrayList<>();
		PreparedStatement ps=Database.getDatabaseInstance().getConnection().prepareStatement("select v.id,v.particular,v.amount,v.vctype,v.createdon from voucher v JOIN account a ON a.id=v.acid where a.acno=? and v.status='verified' order by (id) desc;");
		ps.setString(1,acno);
		ResultSet rs=ps.executeQuery();
		while(rs.next())
			al.add(new AccountModel(rs.getInt(1),rs.getString(2),rs.getDouble(3),rs.getString(4),String.valueOf(rs.getDate(5))));
		ps.close();
		rs.close();
		return al;	
	}
	public  ArrayList<AccountModel> statements(String acno,int count) throws SQLException,IOException		//OVERLOADING
	{
		ArrayList<AccountModel> al=new ArrayList<>();
		PreparedStatement ps=Database.getDatabaseInstance().getConnection().prepareStatement("select v.id,v.particular,v.amount,v.vctype,v.createdon from voucher v JOIN account a ON a.id=v.acid where a.acno=? and v.status='verified' order by (id) desc limit ?;");
		ps.setString(1,acno);
		ps.setInt(2,count);
		ResultSet rs=ps.executeQuery();
		while(rs.next())
			al.add(new AccountModel(rs.getInt(1),rs.getString(2),rs.getDouble(3),rs.getString(4),String.valueOf(rs.getDate(5))));
		ps.close();
		rs.close();
		return al;	
	}
	public  ArrayList<AccountModel> statements(String acno,String ondate) throws SQLException,IOException		//OVERLOADING
	{
		ArrayList<AccountModel> al=new ArrayList<>();
		PreparedStatement ps=Database.getDatabaseInstance().getConnection().prepareStatement("select v.id,v.particular,v.amount,v.vctype,v.createdon from voucher v JOIN account a ON a.id=v.acid where a.acno=? and v.createdon=? and v.status='verified' order by (id) desc;");
		ps.setString(1,acno);
		ps.setDate(2,Validation.getInstance().toDate(ondate));
		ResultSet rs=ps.executeQuery();
		while(rs.next())
			al.add(new AccountModel(rs.getInt(1),rs.getString(2),rs.getDouble(3),rs.getString(4),String.valueOf(rs.getDate(5))));
		ps.close();
		rs.close();
		return al;	
	}
	public  ArrayList<AccountModel> statements(String acno,String fromdate,String todate) throws SQLException,NotEligibleException,IOException		//OVERLOADING
	{
		java.sql.Date from=Validation.getInstance().toDate(fromdate);
		java.sql.Date to=Validation.getInstance().toDate(todate);
		if(from.compareTo(to)==1)
		{
			throw new NotEligibleException("FROM DATE IS PRIOR DATE OF TO DATE");
		}
		ArrayList<AccountModel> al=new ArrayList<>();
		PreparedStatement ps=Database.getDatabaseInstance().getConnection().prepareStatement("select v.id,v.particular,v.amount,v.vctype,v.createdon from voucher v JOIN account a ON a.id=v.acid where a.acno=? and v.createdon>=? and v.createdon<=? and v.status='verified' order by (id) desc;");
		ps.setString(1,acno);
		ps.setDate(2,from);
		ps.setDate(3,to);
		ResultSet rs=ps.executeQuery();
		while(rs.next())
			al.add(new AccountModel(rs.getInt(1),rs.getString(2),rs.getDouble(3),rs.getString(4),String.valueOf(rs.getDate(5))));
		ps.close();
		rs.close();
		return al;	
	}
	public  int updateStatus(AccountModel cm)throws SQLException,IOException
	{
		int row=0;
		PreparedStatement ps=Database.getDatabaseInstance().getConnection().prepareStatement("update account set status=? where id=?;");
		ps.setBoolean(1,cm.getStatus());
		ps.setInt(2,cm.getAcid());
		row=ps.executeUpdate();
		ps.close();
		return row;
	}
}
