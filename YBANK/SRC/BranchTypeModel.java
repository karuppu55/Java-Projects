package YBank;
class BranchTypeModel
{
	private int bid;
	private String banktype;
	private String status;
	BranchTypeModel(int bid,String banktype)
	{
	this.banktype=banktype;
	this.bid=bid;
	}
	BranchTypeModel(String banktype)
	{
	this.banktype=banktype;
	}
	BranchTypeModel(int bid)
	{
	this.bid=bid;
	}
	BranchTypeModel()
	{
	
	}
	public int getBid()
	{
		return bid;
	}
	public void setBid(int bid)
	{
		this.bid=bid;
	}
	public String getBankType()
	{
		return banktype;
	}
	public void setBankType(String banktype)
	{
		this.banktype=banktype;
	}
}
