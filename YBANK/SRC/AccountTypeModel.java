package YBank;
class AccountTypeModel
{
	private int acid;
	private String actype;
	private long schemecode;
	private int minage;
	private int maxage;
	private String status;
	AccountTypeModel(int acid,String actype)
	{
		this.actype=actype;
		this.acid=acid;
	}
	AccountTypeModel(String actype,long schemecode,int minage,int maxage)
	{
		this.actype=actype;
		this.schemecode=schemecode;
		this.minage=minage;
		this.maxage=maxage;
	}
	AccountTypeModel(String actype)
	{
		this.actype=actype;
	}
	AccountTypeModel(int acid)
	{
		this.acid=acid;
	}
	public int getAcid()
	{
		return acid;
	}
	public void setAcid(int acid)
	{
		this.acid=acid;
	}
	public String getActype()
	{
		return actype;
	}
	public void setActype(String actype)
	{
		this.actype=actype;
	}
	public long getSchemecode()
	{
		return schemecode;
	}
	public void setSchemecode(long schemecode)
	{
		this.schemecode=schemecode;
	}
	public int getMinage()
	{
		return minage;
	}
	public void setMinage(int minage)
	{
		this.minage=minage;
	}
	public int getMaxage()
	{
		return maxage;
	}
	public void setMaxage(int maxage)
	{
		this.maxage=maxage;
	}
}
