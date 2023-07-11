package YBank;
class DistrictModel
{
	private int id;
	private String district;
	private String status;
	StateModel state=null;
	DistrictModel(int id,String district)
	{
		this.district=district;
		this.id=id;
	}
	DistrictModel(int id)
	{
		this.id=id;
	}
	DistrictModel(String district,int stateid)
	{
		state=new StateModel(stateid);
		this.district=district;
	}
	DistrictModel(String district)
	{
		this.district=district;
	}
	DistrictModel()
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
	public String getDistrict()
	{
		return district;
	}
	public void setDistrict(String district)
	{
		this.district=district;
	}
}
