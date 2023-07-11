package YBank;
class JobTypeModel
{
	private int jobid;
	private String jobtype;
	private String status;
	JobTypeModel(int jobid,String jobtype)
	{
	this.jobtype=jobtype;
	this.jobid=jobid;
	}
	JobTypeModel(int jobid)
	{
	this.jobid=jobid;
	}
	JobTypeModel(String jobtype)
	{
	this.jobtype=jobtype;
	}
	JobTypeModel()
	{
	}
	public int getJobId()
	{
		return jobid;
	}
	public void setJobId(int jobid)
	{
		this.jobid=jobid;
	}
	public String getJobType()
	{
		return jobtype;
	}
	public void setJobType(String jobtype)
	{
		this.jobtype=jobtype;
	}
}
