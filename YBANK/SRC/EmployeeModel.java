package YBank;
public class EmployeeModel
{
	CandidatesModel candidate=null;
	JobTypeModel job=null; 
	BranchModel branch=null;
	CityModel city=null;
	StateModel state=null;
	DistrictModel district=null;
	LoginModel login=null;
	private int empid;
	private String empname;
	private String empadharno;
	private int empcreatedby;
	private int empbankid;
	private boolean empstatus;
	private String createdon;
	EmployeeModel(int appid,int jobid,int empbankid,int empcreatedby)
	{	
		login=new LoginModel();
		candidate=new CandidatesModel(appid);
		job=new JobTypeModel(jobid);
		this.empbankid=empbankid;
		this.empcreatedby=empcreatedby;
	}
	EmployeeModel(String empname,String empjobtype,boolean empstatus,int empid)
	{
		job=new JobTypeModel(empjobtype);
		this.empname=empname;
		this.empid=empid;
		this.empstatus=empstatus;
	}
	EmployeeModel(String empname,String empjobtype,int empid)
	{
		job=new JobTypeModel(empjobtype);
		this.empname=empname;
		this.empid=empid;
	}
	EmployeeModel(String cityname,String branchname,String empname,int empid,String jobtype,int bankid,boolean empstatus)
	{
		branch=new BranchModel(branchname,bankid);
		city=new CityModel(cityname);
		job=new JobTypeModel(jobtype);
		this.empid=empid;
		this.empname=empname;
		this.empstatus=empstatus;
	}
	EmployeeModel(int empid,String password,String jobtype,String statename,String districtname,String cityname,String branchname,boolean applystatus,boolean empstatus,boolean passstatus)
	{
		job=new JobTypeModel(jobtype);
		branch=new BranchModel(branchname);
		city=new CityModel(cityname);
		state=new StateModel(statename);
		district=new DistrictModel(districtname);
		candidate=new CandidatesModel(applystatus);
		login=new LoginModel(password,passstatus);
		this.empid=empid;
		this.empstatus=empstatus;
	}
	EmployeeModel()
	{
		
	}																			
	public String getName()
	{
		return empname;
	}
	public void setName(String empname)
	{
		this.empname=empname;
	}
	public int getId()
	{
		return empid;
	}
	public void setid(int empid)
	{
		this.empid=empid;
	}
	public int getBankId()
	{
		return empbankid;
	}
	public void setBankId(int empbankid)
	{
		this.empbankid=empbankid;
	}
	public void setAdharno(String empadharno)
	{
		this.empadharno=empadharno;
	}
	public String getAdharno()
	{
		return empadharno;
	}
	public void setCreatedby(int empcreatedby)
	{
		this.empcreatedby=empcreatedby;
	}
	public int getCreatedby()
	{
		return empcreatedby;
	}
	public boolean getStatus()
	{
		return empstatus;
	}
	public void setStatus(boolean empstatus)
	{
		this.empstatus=empstatus;
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
