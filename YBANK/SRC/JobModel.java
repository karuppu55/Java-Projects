package YBank;
class JobModel
{
	private String enddate,qualification,status,createdon;
	private int agelimit,createdby;
	JobTypeModel job=null;                               
	JobModel()
	{
	}
	JobModel(int jobid,String qualification,int agelimit,String enddate,int createdby)
	{
		job=new JobTypeModel(jobid);
		this.qualification=qualification;
		this.agelimit=agelimit;
		this.enddate=enddate;
		this.createdby=createdby;
	}
	JobModel(int jobid,String jobtype,String qualification,int agelimit,String enddate)
	{
		job=new JobTypeModel(jobid,jobtype);
		this.qualification=qualification;
		this.agelimit=agelimit;
		this.enddate=enddate;
	}
	JobModel(String qualification)
	{
		this.qualification=qualification;
	}
	public String getQualification()
	{
		return qualification;
	}
	public void setQualification(String qualification)
	{
		this.qualification=qualification;
	}
	public String getEnddate()
	{
		return enddate;
	}
	public void setEnddate(String enddate)
	{
		this.enddate=enddate;
	}
	public int getAgeLimit()
	{
		return agelimit;
	}
	public void setAgeLimit(int agelimit)
	{
		this.agelimit=agelimit;
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
