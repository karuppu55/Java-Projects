package YBank;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.sql.SQLException;
import java.io.IOException;
class ManageBankControler
{	
	private static ManageBankControler managebank=null;
	private ManageBankControler()throws SQLException,IOException
	{
	}
	public static ManageBankControler getInstance() throws SQLException,IOException
	{
		if(managebank==null)
			managebank=new ManageBankControler();
		return managebank;
	}	
	public  int bankid() throws SQLException ,IOException				//for ACCOUNT NUMBER GENERATION
	{
		ResultSet rs=SingletonStatement.getStatement().executeQuery("SELECT count(id) FROM branch;");
		if(rs.next())
		{
			return rs.getInt(1)+1;
		}
	return 0;
	}
	public  HashMap<Integer,BranchTypeModel> getBranchType() throws SQLException ,IOException	 //LIST OF BRANCH TYPES
	{
		HashMap<Integer,BranchTypeModel> bank=new HashMap<>();
		ResultSet rs=SingletonStatement.getStatement().executeQuery("SELECT id,branchtype FROM branchtype;");
		while(rs.next())
		{
			bank.put(rs.getInt(1),new BranchTypeModel(rs.getString(2)));
		}
		rs.close();
		return bank;
	}
	public  int addDetail(BranchModel b) throws SQLException,IOException		//ADD NEW BRANCH
	{
		int id=0;
		PreparedStatement ps=Database.getDatabaseInstance().getConnection().prepareStatement("Insert into branch(city,branchname,btype,amount,acno,createdby) values (?,?,?,?,?,?) RETURNING id;");
		ps.setInt(1,b.city.getId());
		ps.setString(2,b.getBranchName());
		ps.setInt(3,b.bank.getBid());
		ps.setDouble(4,b.getAmount());
		ps.setString(5,b.getAcno());
		ps.setInt(6,b.getCreatedby());
		ResultSet rs=ps.executeQuery();
		if(rs.next())
		{
			id=rs.getInt("id");
		}
		ps.close();
		rs.close();		
	return id;
	}
	public  HashMap<Integer,StateModel> getStates() throws SQLException,IOException		//GET STATES
	{
		HashMap<Integer,StateModel> states=new HashMap<>();
		ResultSet rs=SingletonStatement.getStatement().executeQuery("SELECT id,state FROM states;");
		while(rs.next())
		{
			states.put(rs.getInt(1),new StateModel(rs.getString(2)));
		}
		rs.close();
		return states;
	}
	public  HashMap<Integer,DistrictModel> getDistricts(int stateid) throws SQLException,IOException		//GET DISTRICTS
	{
		HashMap<Integer,DistrictModel> districts=new HashMap<>();
		PreparedStatement ps=Database.getDatabaseInstance().getConnection().prepareStatement("SELECT id,district FROM districts WHERE stateid=?;");
		ps.setInt(1,stateid);
		ResultSet rs=ps.executeQuery();
		while(rs.next())
		{
			districts.put(rs.getInt(1),new DistrictModel(rs.getString(2)));
		}
		ps.close();
		rs.close();
		return districts;
	}
	public  HashMap<Integer,CityModel>  getCities(int districtid) throws SQLException,IOException		//GET CITIES
	{
		HashMap<Integer,CityModel> cities=new HashMap<>();
		PreparedStatement ps=Database.getDatabaseInstance().getConnection().prepareStatement("SELECT id,city FROM cities WHERE districtid=?;");
		ps.setInt(1,districtid);
		ResultSet rs=ps.executeQuery();
		while(rs.next())
		{
			cities.put(rs.getInt(1),new CityModel(rs.getString(2)));
		}
		ps.close();
		rs.close();
		return cities;
	}
	public  int addState(StateModel state)throws SQLException,IOException				//ADD NEW STATE
	{
		PreparedStatement ps=Database.getDatabaseInstance().getConnection().prepareStatement("Insert into states(state) values (?) RETURNING id;");
		ps.setString(1,state.getState());
		ResultSet rs=ps.executeQuery();
		rs.next();
		return rs.getInt("id");
			
	}
	public  int addDistrict(DistrictModel district)throws SQLException,IOException				//ADD NEW DISTRICTS
	{
		PreparedStatement ps=Database.getDatabaseInstance().getConnection().prepareStatement("Insert into districts(stateid,district) values (?,?) RETURNING id;");
		ps.setInt(1,district.state.getId());
		ps.setString(2,district.getDistrict());
		ResultSet rs=ps.executeQuery();
		rs.next();
		return rs.getInt("id");
	}
	public  int addCity(CityModel city)throws SQLException,IOException				//ADD NEW CITIES
	{
		PreparedStatement ps=Database.getDatabaseInstance().getConnection().prepareStatement("Insert into cities(districtid,city) values (?,?) RETURNING id;");
		ps.setInt(1,city.district.getId());
		ps.setString(2,city.getCity());
		ResultSet rs=ps.executeQuery();
		rs.next();
		return rs.getInt("id");
	}
	public  ArrayList<BranchModel> getBankDetail(int state,int district,int city,int bankid) throws SQLException,IOException	//GETBANKS DETAIL
	{
		ArrayList<BranchModel> al=new ArrayList<>();
		PreparedStatement ps=Database.getDatabaseInstance().getConnection().prepareStatement("SELECT br.id,s.state,d.district,c.city,br.branchname,bt.branchtype,br.acno from branch br JOIN branchtype bt ON br.btype=bt.id JOIN cities c ON br.city=c.id JOIN districts d ON c.districtid=d.id JOIN states s ON d.stateid=s.id WHERE s.id=? OR d.id=? OR c.id=? OR br.id=?;");
		ps.setInt(1,state);
		ps.setInt(2,district);
		ps.setInt(3,city);
		ps.setInt(4,bankid);
		ResultSet rs=ps.executeQuery();
		while(rs.next())
		{
				al.add(new BranchModel(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7)));
		}
		ps.close();
		rs.close();	
		return al;	
	}
	public  ArrayList<BranchModel> getEmployee(int bankid)  throws SQLException,IOException		//GET BRANCH EMPLOYEES
	{
		ArrayList<BranchModel> al=new ArrayList<>();
		PreparedStatement ps=Database.getDatabaseInstance().getConnection().prepareStatement("SELECT c.city,br.branchname,re.name,jt.jobtype,em.id FROM employee em INNER JOIN jobtype jt on em.emptype=jt.id INNER JOIN branch br on em.bankid=br.id INNER JOIN canditates ce ON em.canid=ce.id JOIN registration re On re.id=ce.regid JOIN cities c ON br.city=c.id WHERE br.id=? AND em.status='true';");
		ps.setInt(1,bankid);
		ResultSet rs=ps.executeQuery();
		while(rs.next())
			al.add(new BranchModel(rs.getString(1),rs.getString(2),rs.getString(3),rs.getInt(5),rs.getString(4)));	
		ps.close();
		rs.close();
		return al;
	}	
	public  BranchModel getbankdetail(int bankid)  throws SQLException,IOException				//GET BRANCH DETAIL	
	{
		BranchModel bm=null;
		PreparedStatement ps=Database.getDatabaseInstance().getConnection().prepareStatement("SELECT br.id,s.state,d.district,c.city,br.branchname,bt.branchtype,br.acno from branch br JOIN branchtype bt ON br.btype=bt.id JOIN cities c ON br.city=c.id JOIN districts d ON c.districtid=d.id JOIN states s ON d.stateid=s.id WHERE br.id=?;");
		ps.setInt(1,bankid);
		ResultSet rs=ps.executeQuery();
		if(rs.next())
		{
				bm=new BranchModel(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7));
		}
		ps.close();
		rs.close();
		return bm;	
	}	
	public  boolean changetype(BranchModel bm)  throws SQLException,IOException			//CHANGE BRANCH TYPE
	{
		PreparedStatement ps=Database.getDatabaseInstance().getConnection().prepareStatement("UPDATE branch SET btype=? WHERE id=?;");
		ps.setInt(1,bm.bank.getBid());
		ps.setInt(2,bm.getBankId());
		ps.executeUpdate();
		return true;	
	}
}
