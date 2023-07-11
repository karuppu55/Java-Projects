package YBank;
public class CustomerModel
{
	private int cusid;
	private String name;
	private String fathername;
	private String gender;
	private String dob;
	private int age;
	private String email;
	private String mobile;
	private String adharno;
	private String panno;
	private String address;
	private int createdby;
	private int bankid;
	private boolean status;
	private String createdon;
	CustomerModel(String name,String fathername,String gender,String dob,int age,String mobile,String email,String adharno,String panno,String address,int createdby,int bankid)
	{
		this.name=name;
		this.fathername=fathername;
		this.gender=gender;
		this.dob=dob;                          
		this.age=age;
		this.mobile=mobile;
		this.email=email;
		this.adharno=adharno;
		this.panno=panno;
		this.address=address;
		this.createdby=createdby;
		this.bankid=bankid;	
	}
	CustomerModel(int cusid,int createdby,int bankid)
	{
		this.cusid=cusid;
		this.adharno=adharno;
		this.createdby=createdby;
		this.bankid=bankid;
	}
	CustomerModel(int cusid,String name,String fathername,String gender,String dob,int age,String mobile,String email,String adharno,String panno,String address,int bankid,int createdby,boolean status)
	{
		this.cusid=cusid;
		this.name=name;
		this.fathername=fathername;
		this.gender=gender;
		this.dob=dob;                          
		this.age=age;
		this.mobile=mobile;
		this.email=email;
		this.adharno=adharno;
		this.panno=panno;
		this.address=address;
		this.createdby=createdby;
		this.bankid=bankid;	
		this.status=status;
	}
	CustomerModel(int cusid,String name,String mobile,String adharno,int bankid,String createdon)
	{
		this.cusid=cusid;
		this.name=name;
		this.mobile=mobile;
		this.adharno=adharno;
		this.bankid=bankid;	
		this.status=status;
		this.createdon=createdon;
	}
	CustomerModel(int cusid,boolean status)
	{
		this.cusid=cusid;
		this.status=status;
	}
	CustomerModel(String name)
	{
		this.name=name;
	}
	CustomerModel()
	{				
	}
	
	public int getCusId()
	{
		return cusid;
	}
	public void setCusId(int cusid)
	{
		this.cusid=cusid;	
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name=name;
		
	}
	public String getFathername()
	{
		return fathername;
	}
	public void setFathername(String fathername)
	{
		this.fathername=fathername;
	}
	public String getGender()
	{
		return gender;
	}
	public void setGender()
	{
		this.gender=gender;
	}
	public String getDob()
	{
		return dob;
	}
	public void setDob(String dob)
	{
		this.dob=dob;
	}
	public int getAge()
	{
		return age;
	}
	public void setAge(int age)
	{
		this.age=age;	
	}
	public String getMobile()
	{
		return mobile;
	}
	public void setMobile(String mobile)
	{
		this.mobile=mobile;	
	}
	public String getEmail()
	{
		return email;
	}
	public void setEmail(String email)
	{
		this.email=email;
		
	}
	public String getAdharno()
	{
		return adharno;
	}
	public void setAdharno(String adharno)
	{
		this.adharno=adharno;
	}
	public String getPanno()
	{
		return panno;
	}
	public void setPanno(String panno)
	{
		this.panno=panno;	
	}
	public String getAddress()
	{
		return address;
	}
	public void setAddress(String address)
	{
		this.address=address;
	}
	public int getCreatedby()
	{
		return createdby;
	}
	public void setCreatedby(int createdby)
	{
		this.createdby=createdby;		
	}
	public int getBankId()
	{
		return bankid;
	}
	public void setBankId(int bankid)
	{
		this.bankid=bankid;		
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
