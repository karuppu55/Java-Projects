package com.zoho.E_Shopping;
import java.sql.ResultSet;
import java.io.*;
import java.sql.SQLException;
class SignIn
{
    public void signIn()throws SQLException,IOException
    {
        System.out.println("=======================================================================================================");
        System.out.println("\n\t\t\tSIGN IN");
        System.out.println("=======================================================================================================");
		long mobile=Validation.getInstance().isMobile("MOBILE NUMBER");
        String password=Validation.getInstance().isPassword("PASSWORD");
        UserModel sm=getUser(mobile,password);
        if(sm==null)
            throw new DatNotFoundException("INVALID USERNAME OR PASSWORD!!");
        if(sm.getRoleId()==1)
            new AdminView().menu(sm);
        else if(sm.getRoleId()==2)
            new VendorView().menu(sm);
        else
            new CustomerView().menu(sm);
    }
    public UserModel getUser(long mobile,String password)throws SQLException
    {
        ResultSet rs=SingletonStatement.getStatement().executeQuery("select u.id as userid,u.name,u.mobile,u.gmail,r.name as role,r.id as roleid,ad.id as addressid,ad.doorno,ad.street,ad.city,ad.district,ad.pincode,a.password,s.id,s.name,s.type,am.amount FROM usersdetail u JOIN role r ON u.roleid=r.id JOIN account a ON a.userid=u.id JOIN address ad ON ad.userid=u.id LEFT JOIN shop s ON s.userid=u.id LEFT JOIN wallet am ON am.shopid=s.id WHERE u.mobile="+mobile+" AND a.password='"+password+"' AND u.status='t';");
        if(rs.next())
        {
            if(rs.getInt(6)<=2)
                return new UserModel(rs.getInt(1),rs.getString(2),rs.getLong(3),rs.getString(4),rs.getString(5),rs.getInt(6),rs.getInt(7),rs.getInt(8),rs.getString(9),rs.getString(10),rs.getString(11),rs.getLong(12),rs.getString(13),rs.getInt(14),rs.getString(15),rs.getString(16),rs.getDouble(17)); 
            else
                return new UserModel(rs.getInt(1),rs.getString(2),rs.getLong(3),rs.getString(4),rs.getString(5),rs.getInt(6),rs.getInt(7),rs.getInt(8),rs.getString(9),rs.getString(10),rs.getString(11),rs.getLong(12),rs.getString(13));
        }
        return null;
    }
}