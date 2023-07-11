package YBank;
public class ApplicationModel
{
	private String name;
	private int regid;
	private String fathername;
	private String adharno;
	private String panno;
	private String email;
	private String gender;
	private String dob;
	private String phno;
	private String status;
	private String createdon;
	private String qualification;
	private int age;
	private byte[] data;
	private String filename;
	ApplicationModel(String name,String fathername,String gender,String dob,int age,String phno,String email,String adharno,String panno,String qualification,String filename,byte[] data)
	{
		this.qualification=qualification;
		this.name=name;
		this.fathername=fathername;
		this.gender=gender;
		this.dob=dob;
		this.phno=phno;
		this.adharno=adharno;
		this.panno=panno;
		this.email=email;
		this.age=age;
		this.filename=filename;
		this.data=data;
	}
	ApplicationModel()
	{					
	}
	ApplicationModel(int regid)
	{
		this.regid=regid;
	}
	ApplicationModel(String name,String fathername,String gender,int age,String qualification,String filename,byte[] data)
	{
		this.qualification=qualification;
		this.name=name;
		this.age=age;
		this.fathername=fathername;
		this.gender=gender;
		this.filename=filename;
		this.data=data;
	}
	public byte[] getData()
	{
		return data;
	}
	public void setData(byte[] data)
	{
		this.data=data;
	}
	public int getRegid()
	{
		return regid;
	}
	public void setRegid(int regid)
	{
		this.regid=regid;
	}
	public String getEmail()
	{
		return email;
	}
	public void setEmail(String email)
	{
		this.email=email;
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
	public void setGender(String gender)
	{
		this.gender=gender;
	}
	public String getDob()
	{
		return dob;
	}
	public void setDob(String don)
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
	public String getPhno()
	{
		return phno;
	}
	public void setPhno(String phno)
	{
		this.phno=phno;
	}
	public String getAdharno()
	{
		return adharno;
	}
	public String getQualification()
	{
		return qualification;
	}
	public void setQualification(String qualification)
	{
		this.qualification=qualification;
	}
	public void setAdharno(String adharno)
	{
		this.adharno=adharno;
	}
	public String getFileName()
	{
		return filename;
	}
	public void setFileName(String filename)
	{
		this.filename=filename;
	}
	public String getPanno()
	{
		return panno;
	}
	public void setPanno(String panno)
	{
		this.panno=panno;
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

