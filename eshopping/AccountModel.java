package com.zoho.E_Shopping;
class AccountModel
{
    private int userid;
    private String password;
    AccountModel(int userid,String password)
    {
        this.userid=userid;
        this.password=password;
    }
     AccountModel(String password)
    {
        this.password=password;
    }
    public int getUserId()
    {
        return userid;
    }
    public void setUserId(int userid)
    {
        this.userid=userid;
    }
    public String getPassword()
    {
        return password;
    }
    public void setPassword(String password)
    {
        this.password=password;
    }
}