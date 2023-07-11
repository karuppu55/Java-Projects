package YBank;
public class AccountModel 
{
	AccountTypeModel acctype=null;
	CustomerModel customer=null;
	VoucherModel voucher=null;
	private int acid;
	private String acno;
	private double amount;
	private boolean status; 
	{
		amount=0;
	}
	AccountModel(int cusid,String acno,int actypeid,int createdby,int bankid)
	{
		customer=new CustomerModel(cusid,createdby,bankid);
		acctype=new AccountTypeModel(actypeid);
		this.acno=acno;
	}
	AccountModel(String acno,Double amount)
	{
		this.acno=acno;
		this.amount=amount;
	}
	AccountModel(String acno)
	{
		this.acno=acno;
	}
	AccountModel(int acid)
	{
		this.acid=acid;
	}
	AccountModel(int cusid,int acid,String acno,double amount,String name,String mobile,String adharno,String createdon,boolean status,String actype,int bankid)
	{
		customer=new CustomerModel(cusid,name,mobile,adharno,bankid,createdon);
		this.status=status;
		this.acid=acid;
		this.amount=amount;
		acctype=new AccountTypeModel(actype);
		this.acno=acno;
	}
	AccountModel(int vcid,String particular,double amount,String vctype,String createdon)
	{
		voucher=new VoucherModel(vcid,particular,amount,vctype,createdon);
	}
	AccountModel()
	{
	
	}
	public int getAcid()
	{
		return acid;
	}
	public void setAcid(int acid)
	{
		this.acid=acid;	
	}
	public String getAcno()
	{
		return acno;
	}
	public void setAcno(String acno)
	{
		this.acno=acno;
	}
	public double getAmount()
	{
		return amount;
	}
	public void setAmount(double amount)
	{
		this.amount=amount;
	}
	public boolean getStatus()
	{
		return status;
	}
	public void setStatus(boolean status)
	{
		this.status=status;
	}
}
