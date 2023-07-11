package YBank;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.sql.SQLException;
import java.io.IOException;
class BankTransactionControler
{
	private static BankTransactionControler banktransaction=null;
	Statement statement=null;
	private BankTransactionControler()throws SQLException,IOException
	{
		if(statement==null||statement.isClosed())
			statement=Database.getDatabaseInstance().getConnection().createStatement();
	}
	public static BankTransactionControler getInstance()throws SQLException,IOException
	{
		if(banktransaction== null)
			banktransaction=new BankTransactionControler();
		return banktransaction;	
	}
	public  int addTransaction(BankTransaction bt)throws SQLException,IOException
	{
		int id=0;
		PreparedStatement ps=Database.getDatabaseInstance().getConnection().prepareStatement("INSERT INTO intertransaction(bankid,amount,office,transtype)VALUES(?,?,?,?) RETURNING id;");
		ps.setInt(1,bt.getBankId());
		ps.setDouble(2,bt.getAmount());
		ps.setInt(3,bt.getOffice());
		ps.setString(4,bt.getTranstype());
		ResultSet rs=ps.executeQuery();
		if(rs.next())
		{
			id=rs.getInt("id");
		}
		ps.close();
		rs.close();
		return id;	
	}
	public  BankTransaction getTransaction(int id)throws SQLException,IOException
	{
		BankTransaction bt=null;
		PreparedStatement ps=Database.getDatabaseInstance().getConnection().prepareStatement("select id,bankid,amount,office,transtype,status from intertransaction where id=?;");
		ps.setInt(1,id);
		ResultSet rs=ps.executeQuery();
		if(rs.next())
			bt=new BankTransaction(rs.getInt(1),rs.getInt(2),rs.getDouble(3),rs.getInt(4),rs.getString(5),rs.getString(6));
		ps.close();
		rs.close();
		return bt;
	}
	public  double opeingAmount(int bankid)throws SQLException,IOException
	{
		double amount=0;
		PreparedStatement ps=Database.getDatabaseInstance().getConnection().prepareStatement("select id,opening from bankbalance where bankid=? AND createdon=CURRENT_DATE;");
		ps.setInt(1,bankid);
		ResultSet rs=ps.executeQuery();
		if(rs.next())
		{
			return amount=rs.getDouble(2);
		}
		else
		{
			ps=Database.getDatabaseInstance().getConnection().prepareStatement("select closing from bankbalance where id=(select max(id) from bankbalance where bankid=? group by bankid);");
			ps.setInt(1,bankid);
			ResultSet rs1=ps.executeQuery();
			if(rs1.next())
			{
				amount=rs1.getDouble(1);
			}
			ps=Database.getDatabaseInstance().getConnection().prepareStatement("INSERT INTO bankbalance (opening,bankid) VALUES (?,?);");
			ps.setDouble(1,amount);
			ps.setInt(2,bankid);
			ps.executeUpdate();
			return amount;
		}
	}
	public  double getdeposit(int bankid)throws SQLException,IOException
	{
		double amount=0;
		PreparedStatement ps=Database.getDatabaseInstance().getConnection().prepareStatement("select sum(v.amount) as deposit,v.vctype from voucher v JOIN branch b ON b.id=v.bankid WHERE b.id=? and v.vctype='deposit' and v.createdon=CURRENT_DATE group by v.vctype;");
		ps.setInt(1,bankid);
		ResultSet rs=ps.executeQuery();
		if(rs.next())
			amount=rs.getDouble("deposit");
		return amount;
	}
	public  double getwithdraw(int bankid)throws SQLException,IOException
	{
		double amount=0;
		PreparedStatement ps=Database.getDatabaseInstance().getConnection().prepareStatement("select sum(v.amount) as withdraw,v.vctype from voucher v JOIN branch b ON b.id=v.bankid WHERE b.id=? and v.vctype='withdraw' and v.createdon=CURRENT_DATE group by v.vctype;");
		ps.setInt(1,bankid);
		ResultSet rs=ps.executeQuery();
		if(rs.next())
			amount=rs.getDouble("withdraw");
		return amount;
	}
	public  double getHeadOfficecredit(int bankid)throws SQLException,IOException
	{
		double amount=0;
		PreparedStatement ps=Database.getDatabaseInstance().getConnection().prepareStatement("select sum(i.amount),i.transtype from intertransaction i JOIN branch b ON b.id=i.bankid Where b.id=? AND i.transtype='credit' AND i.createdon=CURRENT_DATE group by i.transtype;");
		ps.setInt(1,bankid);
		ResultSet rs=ps.executeQuery();
		if(rs.next())
			amount=rs.getDouble(1);
		return amount;
	}
	public  double getHeadOfficedebit(int bankid)throws SQLException,IOException
	{
		double amount=0;
		PreparedStatement ps=Database.getDatabaseInstance().getConnection().prepareStatement("select sum(i.amount),i.transtype from intertransaction i JOIN branch b ON b.id=i.bankid Where b.id=? AND i.transtype='debit' AND i.createdon=CURRENT_DATE group by i.transtype;");
		ps.setInt(1,bankid);
		ResultSet rs=ps.executeQuery();
		if(rs.next())
			amount=rs.getDouble(1);
		return amount;
	}
	public  double closingAmount(int bankid)throws SQLException,IOException,InsufficiantBalanceException
	{
		double amount=0;
		int id=0;
		PreparedStatement ps=Database.getDatabaseInstance().getConnection().prepareStatement("select id,closing from bankbalance where bankid=? AND createdon=CURRENT_DATE;");
		ps.setInt(1,bankid);
		ResultSet rs=ps.executeQuery();
		if(rs.next()&&rs.getDouble(2)!=0)
		{
			amount=rs.getDouble(2);
		}
		else
		{
			amount+=opeingAmount(bankid);
			amount+=getdeposit(bankid);
			amount-=getwithdraw(bankid);
			amount-=getHeadOfficecredit(bankid);
			amount+=getHeadOfficedebit(bankid);
			if(amount<=0)
			{
				throw new InsufficiantBalanceException("CLOSING BALANCE IS ZERO DONTS CLOSE CASH");
			}
			ps=Database.getDatabaseInstance().getConnection().prepareStatement("update bankbalance set closing=? WHERE bankid=? AND createdon=CURRENT_DATE;");
			ps.setDouble(1,amount);
			ps.setInt(2,bankid);
			ps.executeUpdate();
		}
		return amount;
	}
	public  HashMap<Integer,BranchModel> getHeadOfficeDetail() throws SQLException,IOException	//GETBANKS DETAIL
	{
		HashMap<Integer,BranchModel> al=new HashMap<>();
		ResultSet rs=SingletonStatement.getStatement().executeQuery("SELECT br.id,s.state,d.district,c.city,br.branchname,bt.branchtype,br.acno,br.amount from branch br JOIN branchtype bt ON br.btype=bt.id JOIN cities c ON br.city=c.id JOIN districts d ON c.districtid=d.id JOIN states s ON d.stateid=s.id WHERE br.btype=4");
		while(rs.next())
		{
				al.put(rs.getInt(1),new BranchModel(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getDouble(8)));
		}
		rs.close();	
		return al;	
	}	
	
	public  BranchModel getBranch(int bankid)  throws SQLException,IOException			//GET BRANCH DETAIL	
	{
		BranchModel bm=null;
		PreparedStatement ps=Database.getDatabaseInstance().getConnection().prepareStatement("SELECT id,amount from branch WHERE id=?;");
		ps.setInt(1,bankid);
		ResultSet rs=ps.executeQuery();
		if(rs.next())
		{
				bm=new BranchModel(rs.getInt(1),rs.getDouble(2));
		}
		ps.close();
		rs.close();
		return bm;	
	}
	public  int updateProcess(BranchModel office,BranchModel branch,int transid) throws SQLException,IOException
	{	
		int row=0;
		double officeamount=office.getAmount();
		double brancamount=branch.getAmount();
		int officeid=office.getBankId();
		int branchid=branch.getBankId();
		Database.getDatabaseInstance().getConnection().setAutoCommit(false); 
		PreparedStatement ps=Database.getDatabaseInstance().getConnection().prepareStatement("UPDATE branch SET amount=? WHERE id=?;");
		ps.setDouble(1,officeamount);
		ps.setInt(2,officeid);
		row=ps.executeUpdate();
		if(row!=0)
		{
			ps=Database.getDatabaseInstance().getConnection().prepareStatement("UPDATE branch SET amount=? WHERE id=?;");
			ps.setDouble(1,brancamount);
			ps.setInt(2,branchid);
			row=ps.executeUpdate();
			if(row!=0)
			{
				ps=Database.getDatabaseInstance().getConnection().prepareStatement("UPDATE intertransaction SET status='verified' WHERE id=?;");
				ps.setInt(1,transid);
				row=ps.executeUpdate();
			}
		}
		if(row!=0)
		{
		Database.getDatabaseInstance().getConnection().commit();
		Database.getDatabaseInstance().getConnection().setAutoCommit(true);
		}
		ps.close();
		return row;
	}	
}
