package LibraryManagement;
class UsersModel
{
    private int id;
    private String name;
    private int age;
    private Long phoneno;
    private String address;
    private String gender;
    private boolean status;
    UsersModel(String name,int age,String gender,long phoneno,String address)
    {
        this.name=name;
        this.age=age;
        this.gender=gender;
        this.phoneno=phoneno;
        this.address=address;
    }
    UsersModel(String name,int age,String gender,long phoneno)
    {
        this.name=name;
        this.age=age;
        this.gender=gender;
        this.phoneno=phoneno;
    }
     UsersModel(String name,int id)
    {
        this.name=name;
        this.id=id;
    }
     public int getId()
    {
        return id;
    }
    public void setId(int id)
    {
        this.id=id;
    }
     UsersModel(String name)
    {
        this.name=name;
    }
    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name=name;
    }
    public int getAge()
    {
        return age;
    }
    public void setAge(int age)
    {
        this.age=age;
    }
    public String getGender()
    {
        return gender;
    }
    public void setGender(String gender)
    {
        this.gender=gender;
    }
    public long getPhoneno()
    {
        return phoneno;
    }
    public void setPhoneno(long phoneno)
    {
        this.phoneno=phoneno;
    }
    public String getAddress()
    {
        return address;
    }
    public void setAddress(String address)
    {
        this.address=address;
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