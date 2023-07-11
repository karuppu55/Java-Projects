package YBank;
class CityModel
{
	private int id;
	private String city;
	private String status;
	DistrictModel district=null;
	CityModel(int id,String city)
	{
		this.city=city;
		this.id=id;
	}
	CityModel(int id)
	{
		this.id=id;
	}
	CityModel(String city)
	{
		this.city=city;
	}
	CityModel(String city,int districtid)
	{
		district=new DistrictModel(districtid);
		this.city=city;
	}
	CityModel()
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
	public String getCity()
	{
		return city;
	}
	public void setCity(String city)
	{
		this.city=city;
	}
}
