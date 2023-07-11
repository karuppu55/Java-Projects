package YBank.utils;
final public class Accountnumber
{
	private long schemecode;
	private int bankid;
	private String type;
	int cusid;
	public Accountnumber(int bankid,long schemecode,int cusid)
	{
		this.cusid=cusid;
		this.schemecode=schemecode;
		this.bankid=bankid;
	}
	public Accountnumber(int bankid,long schemecode)
	{
		this.schemecode=schemecode;
		this.bankid=bankid;
	}
	private String bankid()
	{
		int id=bankid;
		int num,count=0;
		while(id!=0)
		{
			id=id/10;
			count++;
		}
		String code;
		if(count==1)
		{
			code="00";
			return code+""+bankid;
		}
		else if(count==2)
		{
			code="0";
			return code+""+bankid;
		}	
		else
		{
			return ""+bankid;
		}
	}
	private String cusid()
	{
		int id=cusid;
		int num,count=0;
		while(id!=0)
		{
			id=id/10;
			count++;
		}
		String code;
		if(count==1)
		{
			code="0000";
			return code+""+cusid;
		}
		else if(count==2)
		{
			code="000";
			return code+""+cusid;
		}	
		else if(count==3)
		{
			code="00";
			return code+""+cusid;
		}
		else if(count==4)
		{
			code="0";
			return code+""+cusid;
		}
		else
		{
			return ""+cusid;
		}
	}
	public String toString()
	{
		return bankid()+""+schemecode+""+cusid();
	}
}
