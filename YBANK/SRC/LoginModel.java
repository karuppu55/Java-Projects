package YBank;
public class LoginModel
{
	JobModel jobs=null;
	BranchModel branch=null;
	JobTypeModel job=null; 
	private int userid;
	private String password="";
	private boolean passstatus; 
	{
		password="ybank@123";
	}
	LoginModel(int userid,String password,String designation)
	{
		jobs=new JobModel(designation);
		this.userid=userid;
		this.password=password;	
	}
	LoginModel(int userid,String jobtype,int bankid,boolean passstatus,int jobid)
	{
		branch=new BranchModel(bankid);
		job=new JobTypeModel(jobid,jobtype);
		this.userid=userid;	
		this.passstatus=passstatus;
	}

	LoginModel(int userid,String password)
	{
		job=new JobTypeModel();
		branch=new BranchModel();
		this.userid=userid;
		this.password=password;	
	}
	LoginModel(String password,boolean passstatus)
	{
		this.passstatus=passstatus;
		this.password=password;	
	}
	LoginModel(String password,int userid,String jobtype,String location,String branchname)
	{
		job=new JobTypeModel(jobtype);
		branch=new BranchModel(location,branchname);
		this.userid=userid;
		this.password=password;
	}
	LoginModel()
	{
	}
	public int getUserId()
	{
		return userid;
	}
	public void setUserId(int userid)
	{
		this.userid=userid;
	}
	public  String getPassword()
	{
		return password;
	}
	public  void setPassword(String password)
	{
		this.password=password;
	}
	public boolean getPassStatus()
	{
		return passstatus;
	}
	public void setPassStatus(boolean passstatus)
	{
		this.passstatus=passstatus;
	}
}
