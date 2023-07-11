package YBank;
class StateModel
{
	private int id;
	private String state;
	private String status;
	StateModel(int id,String state)
	{
	this.state=state;
	this.id=id;
	}
	StateModel(int id)
	{
	this.id=id;
	}
	StateModel(String state)
	{
	this.state=state;
	}
	StateModel()
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
	public String getState()
	{
		return state;
	}
	public void setState(String state)
	{
		this.state=state;
	}
}
