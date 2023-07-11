package YBank;
import YBank.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.sql.SQLException;
import java.io.IOException;
import java.sql.Statement;
class BranchControler
{
	private static BranchControler branch=null;
	Statement statement=null;
	private BranchControler()throws SQLException,IOException
	{
		if(statement==null||statement.isClosed())
			statement=Database.getDatabaseInstance().getConnection().createStatement();
	}
	public static BranchControler getInstance()throws SQLException,IOException
	{
		if(branch==null)
			branch=new BranchControler();
		return branch;
	}
	public  BranchModel getAccount(int bankid)throws SQLException,IOException
	{
		BranchModel am=null;
		PreparedStatement ps=Database.getDatabaseInstance().getConnection().prepareStatement("select b.branchname,bt.branchtype,b.acno,b.amount,b.createdon,c.city,d.district,s.state,b.id FROM branch b JOIN branchtype bt ON b.btype=bt.id JOIN cities c ON b.city=c.id JOIN districts d ON c.districtid=d.id JOIN states s ON d.stateid=s.id WHERE b.id=?;");
		ps.setInt(1,bankid);
		ResultSet rs=ps.executeQuery();
		while(rs.next())
			am=new BranchModel(rs.getString(1),rs.getString(2),rs.getString(3),rs.getDouble(4),String.valueOf(rs.getDate(5)),rs.getString(6),rs.getString(7),rs.getString(8),rs.getInt(9));
		ps.close();
		rs.close();
		return am;	
	}
	public  ArrayList<BranchModel> statements(int bankid,String ondate) throws SQLException	,IOException	//OVERLOADING
	{
		ArrayList<BranchModel> al=new ArrayList<>();
		PreparedStatement ps=Database.getDatabaseInstance().getConnection().prepareStatement("select b.branchname,b.acno,b.amount,a.acno,v.particular,v.amount,v.vctype FROM branch b JOIN voucher v ON v.bankid=b.id JOIN account a ON v.acid=a.id Where v.status='verified' and b.id=? and v.createdon=?;");
		ps.setInt(1,bankid);
		ps.setDate(2,Validation.getInstance().toDate(ondate));
		ResultSet rs=ps.executeQuery();
		while(rs.next())
		{	
			al.add(new BranchModel(rs.getString(1),rs.getString(2),rs.getDouble(3),rs.getString(4),rs.getString(5),rs.getDouble(6),rs.getString(7)));
		}
		ps.close();
		rs.close();
		return al;	
	}
	public  ArrayList<BranchModel> statements(int bankid,String fromdate,String todate) throws SQLException,NotEligibleException,IOException		//OVERLOADING
	{
		java.sql.Date from=Validation.getInstance().toDate(fromdate);
		java.sql.Date to=Validation.getInstance().toDate(todate);
		if(from.compareTo(to)==1)
		{
			throw new NotEligibleException("FROM DATE IS PRIOR DATE OF TO DATE");
		}
		ArrayList<BranchModel> al=new ArrayList<>();
		PreparedStatement ps=Database.getDatabaseInstance().getConnection().prepareStatement("select b.branchname,b.acno,b.amount,a.acno,v.particular,v.amount,v.vctype FROM branch b JOIN voucher v ON v.bankid=b.id JOIN account a ON v.acid=a.id Where v.status='verified' and b.id=? and v.createdon>=? and v.createdon<=?;");
		ps.setInt(1,bankid);
		ps.setDate(2,from);
		ps.setDate(3,to);
		ResultSet rs=ps.executeQuery();
		while(rs.next())
			al.add(new BranchModel(rs.getString(1),rs.getString(2),rs.getDouble(3),rs.getString(4),rs.getString(5),rs.getDouble(6),rs.getString(7)));
		ps.close();
		rs.close();
		return al;	
	}
}
