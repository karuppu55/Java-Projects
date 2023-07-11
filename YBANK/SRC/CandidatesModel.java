package YBank;
public class CandidatesModel
{
	ApplicationModel registration=null;
	JobTypeModel job=null;
	static BranchModel branch=new BranchModel();
	private int appid;
	private boolean status;
	private String createdon;
	
	CandidatesModel(int jobid,int regid,String extra)
	{
		registration=new ApplicationModel(regid);
		job=new JobTypeModel(jobid);
	}
	CandidatesModel(int appid)
	{
		this.appid=appid;
	}
	CandidatesModel()
	{
	
	}
	CandidatesModel(String name,String fathername,String gender,int age,String qualification,String jobtype,int appid,boolean status,int jobid,String filename,byte[] data)
	{
		registration=new ApplicationModel(name,fathername,gender,age,qualification,filename,data);
		job=new JobTypeModel(jobid,jobtype);
		this.appid=appid;
		this.status=status;
	}
	CandidatesModel(boolean status)
	{
		this.status=status;
	}
	public int getAppid()
	{
		return appid;
	}
	public void setAppid(int appid)
	{
		this.appid=appid;
	}
	public boolean getStatus()
	{
		return status;
	}	
	public void setStatus(boolean status)
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
