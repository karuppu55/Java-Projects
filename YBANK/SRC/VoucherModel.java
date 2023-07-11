package YBank;
public class VoucherModel
{
	AccountModel account=null;
	CustomerModel customer=null;
	private int vcno;
	private String particular;	
	private int createdby;
	private int bankid;
	private String vctype;
	private double amount;	
	private String status;	
	private String createdon;
	VoucherModel(int acid,String particular,double amount,String vctype,int createdby,int bankid)
	{
		account=new AccountModel(acid);
		this.vctype=vctype;		
		this.particular=particular;
		this.createdby=createdby;
		this.bankid=bankid;
		this.amount=amount;
	}
	VoucherModel(int vcno,String acno,String particular,double amount,String vctype,int createdby,int bankid,String status,String name)
	{
		customer=new CustomerModel(name);
		account=new AccountModel(acno);
		this.vcno=vcno;
		this.vctype=vctype;
		this.particular=particular;
		this.createdby=createdby;	
		this.bankid=bankid;
		this.status=status;
		this.amount=amount;
	}
	VoucherModel(int vcno,String particular,double amount,String vctype,String createdon)
	{
		this.vcno=vcno;
		this.particular=particular;
		this.vctype=vctype;	
		this.amount=amount;
		this.createdon=createdon;
	}
	VoucherModel(String particular,double amount,String vctype)
	{
		this.particular=particular;
		this.vctype=vctype;	
		this.amount=amount;
	}
	public int getVcno()
	{
		return vcno;
	}
	public void setVcno(int vcno)
	{
		this.vcno=vcno;
		
	}
	public int getBankId()
	{
		return bankid;
	}
	public void setBankId(int bankid)
	{
		this.bankid=bankid;
		
	}
	public String getParticular()
	{
		return particular;
	}
	public void setParticular(String particular)
	{
		this.particular=particular;
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
	public double getAmount()
	{
		return amount;
	}
	public void setAmount(double amount)
	{
		this.amount=amount;		
	}
	public String getVcType()
	{
		return vctype;
	}
	public void setVcType(String vctype)
	{
		this.vctype=vctype;		
	}
}
