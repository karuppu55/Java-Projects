package YBank;
public class BranchModel
{
	EmployeeModel employee=null;
	BranchTypeModel bank=null; 
	StateModel state=null;
	DistrictModel district=null;
	CityModel city=null;
	VoucherModel voucher=null;
	AccountModel account=null;
	private int bankid;
	private String location;
	private String branchname;
	private String acno;
	private  double amount;
	private String status;
	private int createdby;
	private String createdon;
	{
		amount=0;
	}
	BranchModel(int cityid,String branchname,int branchtypeid,String acno,int createdby)
	{
		city=new CityModel(cityid);
		bank=new BranchTypeModel(branchtypeid);
		this.branchname=branchname;
		this.acno=acno;
		this.createdby=createdby;
	}
	BranchModel(int bankid,String statename,String districtname,String cityname,String branchname,String branchtype,String acno)
	{
		state=new StateModel(statename);
		district=new DistrictModel(districtname);
		city=new CityModel(cityname);
		bank=new BranchTypeModel(branchtype);
		this.bankid=bankid;
		this.branchname=branchname;
		this.acno=acno;
	}
	BranchModel(int bankid,String statename,String districtname,String cityname,String branchname,String branchtype,String acno,double amount)
	{
		state=new StateModel(statename);
		district=new DistrictModel(districtname);
		city=new CityModel(cityname);
		bank=new BranchTypeModel(branchtype);
		this.bankid=bankid;
		this.branchname=branchname;
		this.acno=acno;
		this.amount=amount;
	}
	BranchModel(String branchname,String branchtype,String acno,double amount,String createdon,String cityname,String districtname,String statename,int bankid)
	{
		state=new StateModel(statename);
		district=new DistrictModel(districtname);
		city=new CityModel(cityname);
		bank=new BranchTypeModel(branchtype);
		this.amount=amount;
		this.branchname=branchname;
		this.acno=acno;
		this.createdon=createdon;
		this.bankid=bankid;
	}
	BranchModel(String branchname,String acno,double amount,String cusacno,String particular,double transamount,String vctype)
	{
		account=new AccountModel(cusacno);
		voucher=new VoucherModel(particular,transamount,vctype);
		this.amount=amount;
		this.branchname=branchname;
		this.acno=acno;
	}
	BranchModel(String cityname,String branchname,String name,int userid,String jobtype)
	{
		employee=new EmployeeModel(name,jobtype,userid);
		city=new CityModel(cityname);
		this.branchname=branchname;
	}
	BranchModel(String branchname,int bankid)
	{
		this.bankid=bankid;
		this.branchname=branchname;
	}
	BranchModel(String location,String branchname)
	{
		this.location=location;
		this.branchname=branchname;
	}
	BranchModel(String branchname)
	{
		this.branchname=branchname;
	}
	BranchModel(int bankid)
	{
		this.bankid=bankid;
	}
	BranchModel(int bankid,double amount)
	{
		this.bankid=bankid;
		this.amount=amount;
	}
	BranchModel()
	{
	}
	public int getBankId()
	{
		return bankid;
	}
	public void setBankId(int bankid)
	{
		this.bankid=bankid;
	}
	public String getAcno()
	{
		return acno;
	}
	public void setAcno(String acno)
	{
		this.acno=acno;
	}
	public String getBranchName()
	{
		return branchname;
	}
	public void setBranchName(String branchname)
	{
		this.branchname=branchname;
	}
	public  double getAmount()
	{
		return amount;
	}
	public  void setAmount(double amount)
	{
		this.amount=amount;
	}
	public int getCreatedby()
	{
		return createdby;
	}
	public void setCreatedby(int createdby)
	{
		this.createdby=createdby;
	}
	public String getStatus()
	{
		return status;
	}
	public void setStatus(String status)
	{
		this.status=status;
	}
	public String getCreatedon()
	{
		return createdon;
	}
	public void setCreatedon(String createdon)
	{
		this.createdon=createdon;
	}
}
