package YBank;
public class BankTransaction 
{
	private int id;
	private int bankid;
	private double amount;
	private int office;
	private String transtype;
	private String status;
	BankTransaction(int bankid,double amount,int office,String transtype)
	{
		this.bankid=bankid;
		this.amount=amount;
		this.office=office;
		this.transtype=transtype;
	}
	BankTransaction(int id,int bankid,double amount,int office,String transtype,String status)
	{
		this.id=id;
		this.bankid=bankid;
		this.amount=amount;
		this.office=office;
		this.transtype=transtype;
		this.status=status;
	}
	BankTransaction()
	{
	
	}
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id=id;	
	}
	public int getBankId()
	{
		return bankid;
	}
	public void setBankId(int bankid)
	{
		this.bankid=bankid;	
	}
	public String getTranstype()
	{
		return transtype;
	}
	public void setAcno(String transtype)
	{
		this.transtype=transtype;
	}
	public double getAmount()
	{
		return amount;
	}
	public void setAmount(double amount)
	{
		this.amount=amount;
	}
	public int getOffice()
	{
		return office;
	}
	public void setOffice(int office)
	{
		this.office=office;	
	}
	public String getStatus()
	{
		return status;
	}
	public void setStatus(String status)
	{
		this.status=status;
	}
}
