package YBank;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;
import java.io.IOException;
class VoucherControler
{
	private static VoucherControler voucher=null;
	private VoucherControler()
	{
	}
	public static VoucherControler getInstance()
	{	
		if(voucher==null)
			voucher=new VoucherControler();
		return voucher;
	}
	public BranchModel getAccount(int bankid)throws SQLException,IOException
	{
		BranchModel am=null;
		PreparedStatement ps=Database.getDatabaseInstance().getConnection().prepareStatement("select b.branchname,bt.branchtype,b.acno,b.amount,b.createdon,c.city,d.district,s.state,b.id FROM branch b JOIN branchtype bt ON b.btype=bt.id JOIN cities c ON b.city=c.id JOIN districts d ON c.districtid=d.id JOIN states s ON d.stateid=s.id WHERE b.id=?;");
		ps.setInt(1,bankid);
		ResultSet rs=ps.executeQuery();
		while(rs.next())
		{
		am=new BranchModel(rs.getString(1),rs.getString(2),rs.getString(3),rs.getDouble(4),String.valueOf(rs.getDate(5)),rs.getString(6),rs.getString(7),rs.getString(8),rs.getInt(9));
		}
		ps.close();
		rs.close();
		return am;	
	}
	public int addVoucher(VoucherModel dd) throws SQLException,IOException
	{
		int id=0;
		PreparedStatement ps=Database.getDatabaseInstance().getConnection().prepareStatement("insert into voucher(acid,particular,amount,bankid,createdby,vctype) values (?,?,?,?,?,?) RETURNING id;");	
		ps.setInt(1,dd.account.getAcid());
		ps.setString(2,dd.getParticular());
		ps.setDouble(3,dd.getAmount());
		ps.setInt(4,dd.getBankId());
		ps.setInt(5,dd.getCreatedby());
		ps.setString(6,dd.getVcType());
		ResultSet rs=ps.executeQuery();
		if(rs.next())
		{
			id=rs.getInt("id");
		}
		ps.close();
		rs.close();
		return id;
	}
	public  int isVoucher(int vcno) throws SQLException,IOException
	{
		int id=0;
		PreparedStatement ps=Database.getDatabaseInstance().getConnection().prepareStatement("select vcno,status from voucher where id=?;");
		ps.setInt(1,vcno);
		ResultSet rs=ps.executeQuery();
		while(rs.next())
		{ 
			if(vcno==rs.getInt(1)&&!(rs.getString(2).equals("Verified")||rs.getString(2).equals("deleted")))
			{
				id=rs.getInt(1);
			}	
		}
		ps.close();
		rs.close();
		return id;
	}
	public  VoucherModel getVoucher(int vcno) throws SQLException,IOException
	{
		VoucherModel dm=null;
		PreparedStatement ps=Database.getDatabaseInstance().getConnection().prepareStatement("select v.id,v.particular,v.amount,v.vctype,v.createdby,v.bankid,v.status,a.acno,c.name from account a INNER JOIN voucher v on a.id=v.acid INNER JOIN customer c on c.id=a.cusid where v.id=?;");
		ps.setInt(1,vcno);
		ResultSet rs=ps.executeQuery();
		if(rs.next())
		{
			dm=new VoucherModel(rs.getInt(1),rs.getString(8),rs.getString(2),rs.getDouble(3),rs.getString(4),rs.getInt(5),rs.getInt(6),rs.getString(7),rs.getString(9));
		}
		ps.close();
		rs.close();
		return dm;

	}
	public  boolean updateAmount(AccountModel am,BranchModel b)throws SQLException,IOException
	{
		int row=0;
		PreparedStatement ps=Database.getDatabaseInstance().getConnection().prepareStatement("update account set amount=? where acno=?;");
		ps.setDouble(1,am.getAmount());
		ps.setString(2,am.getAcno());
		row=ps.executeUpdate();
		if(row!=0)
		{
			ps=Database.getDatabaseInstance().getConnection().prepareStatement("update branch set amount=? where id=?;");
			ps.setDouble(1,b.getAmount());
			ps.setInt(2,b.getBankId());
			row=ps.executeUpdate();
		}
		ps.close();
		if(row==0)
		{
			return false;
		}
		return true;	
	}
	public  boolean updateStatus(VoucherModel dm) throws SQLException,IOException
	{
		PreparedStatement ps=Database.getDatabaseInstance().getConnection().prepareStatement("update voucher set status=? where id=?;");
		ps.setString(1,dm.getStatus());
		ps.setInt(2,dm.getVcno());
		ps.executeUpdate();
		ps.close();
		return true;
	}
	
}
