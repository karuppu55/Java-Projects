package com.zoho.E_Shopping;
class UserModel
{
    private int id;
    private String name;
    private String gender;
    private long mobile;
    private String email;
    private int roleid;
    private String role;
    private boolean status;
    AccountModel am=null;
    ShopModel sm=null;
    Address ad=null;
    UserModel(String name,String gender,long mobile,String email,int roleid)
    {
        this.name=name;
        this.gender=gender;
        this.mobile=mobile;
        this.email=email;
        this.roleid=roleid;
    }
     UserModel(String name)
     {
        this.name=name;
     }
    UserModel(int id,String name,long mobile,String email,String role,int roleid,int addressid,int doorno,String street,String city,String district,long pincode,String password)
    {
        this.id=id;
        this.name=name;
        this.mobile=mobile;
        this.email=email;
        this.role=role;
        this.roleid=roleid;
        am=new AccountModel(password);
        ad=new Address(addressid,doorno,street,city,district,pincode);

    }
     UserModel(int id,String name,long mobile,String email,String role,int roleid,int addressid,int doorno,String street,String city,String district,long pincode,String password,int shopid,String shopname,String type,double amount)
    {
        this.id=id;
        this.name=name;
        this.mobile=mobile;
        this.email=email;
        this.role=role;
        this.roleid=roleid;
        sm=new ShopModel(shopid,shopname,type,amount);
        am=new AccountModel(password);
        ad=new Address(addressid,doorno,street,city,district,pincode);

    }
    public int getId()
    {
        return id;
    }
    public void setId(int id)
    {
        this.id=id;
    }
    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name=name;
    }
    public String getGender()
    {
        return gender;
    }
    public void setGender(String gender)
    {
        this.gender=gender;
    }
     public String getRole()
    {
        return role;
    }
    public void setRole(String role)
    {
        this.role=role;
    }
    public long getMobile()
    {
        return mobile;
    }
    public void setMobile(long mobile)
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
    public int getRoleId()
    {
        return roleid;
    }
    public void setRoleId(int roleid)
    {
        this.roleid=roleid;
    }
    
}