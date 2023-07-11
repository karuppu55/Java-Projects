package com.zoho.E_Shopping;
import java.util.Map;
import java.util.HashMap;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.io.*;
import java.util.Random;
class SignUp
{
    public void signUp()throws SQLException,IOException
    {
        System.out.println("=======================================================================================================");
        System.out.println("\n\t\t\tSIGN UP");
        System.out.println("=======================================================================================================");
		String name=Validation.getInstance().isLetter("NAME");
        String gender=Validation.getInstance().isLetter("GENDER");
        long mobile=Validation.getInstance().isMobile("PHONE NO");
        String email=Validation.getInstance().isMail("ENTER MAIL");
        System.out.println("\n\t\tROLES");
        for(Map.Entry<Integer,String> role:getRole().entrySet())
            System.out.println("\n\t\t"+role.getKey()+"-->"+role.getValue().toUpperCase());
        int roleid=Validation.getInstance().isDigit("ROLE ID");
        while(!getRole().containsKey(roleid))
            roleid=Validation.getInstance().isDigit("CORRECT ROLE ID");
        int doorno=Validation.getInstance().isDigit("DOOR NO");
        String street=Validation.getInstance().isLetter("STREET");
        String city=Validation.getInstance().isLetter("CITY");
        String district=Validation.getInstance().isLetter("DISTRICT");
        long pincode=Validation.getInstance().isDigit("PINCODE");
        String password=Validation.getInstance().isPassword("PASSWORD");
        while(!password.equals(Validation.getInstance().isPassword("RE-ENTER PASSWORD")))
            System.out.println("\n\tPASSWORD NOT MATCHED");
        if(Validation.getInstance().isYesorNo("CHECK ABOVE DETAIL AND CLICK (Y) TO CONTINUE").toLowerCase().equals("y"))
        {
                String otp=getOtp();
                System.out.println("\n\tYOUR OTP IS :"+otp);
                while(!(otp.equals(String.valueOf(Validation.getInstance().isDigit("OTP")))))
                    System.out.println("\n\tENTER CORRECT OTP");
                int id=0;
                if(roleid==2)
                {
                    String shopname=Validation.getInstance().isLetter("SHOP NAME");
                    String type=Validation.getInstance().isLetter("TYPE");
                    id=registration(new UserModel(name,gender,mobile,email,roleid),new AccountModel(password),new Address(doorno,street,city,district,pincode),new ShopModel(shopname,type));
                }
                else
                {
                    id=registration(new UserModel(name,gender,mobile,email,roleid),new AccountModel(password),new Address(doorno,street,city,district,pincode));
                }
                if(id==0)
                {

                }
                System.out.println("\nREGISTER SUCCUSSFULLY");
        }
    }
    public HashMap<Integer,String> getRole()throws SQLException
    {
        HashMap<Integer,String> role=new HashMap<>();
        ResultSet rs=SingletonStatement.getStatement().executeQuery("SELECT id,name FROM role where id!=1;");
        while(rs.next())
            role.put(rs.getInt(1),rs.getString(2));
        return role;
    }
    public String getOtp()
    {
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<6;i++)
            sb.append(new Random().nextInt(10));
        return new String(sb);
    }
    public int registration(UserModel si,AccountModel ac,Address ad)throws SQLException
    {
        PreparedStatement ps=Database.getInstance().getConnection().prepareStatement("INSERT INTO usersdetail(name,gender,mobile,gmail,roleid) VALUES (?,?,?,?,?) RETURNING id;");
        ps.setString(1,si.getName());
        ps.setString(2,si.getGender());
        ps.setLong(3,si.getMobile());
        ps.setString(4,si.getEmail());
        ps.setInt(5,si.getRoleId());
        ResultSet rs=ps.executeQuery();
        if(rs.next())
        {
            ps=Database.getInstance().getConnection().prepareStatement("INSERT INTO account(userid,password) VALUES (?,?) RETURNING id;");
            ps.setInt(1,rs.getInt("id"));
            ps.setString(2,ac.getPassword());
            rs=ps.executeQuery();
            if(rs.next())
            {
                ps=Database.getInstance().getConnection().prepareStatement("INSERT INTO address(userid,doorno,street,city,district,pincode) VALUES (?,?,?,?,?,?) RETURNING id;");
                ps.setInt(1,rs.getInt("id"));
                ps.setInt(2,ad.getDoorNo());
                ps.setString(3,ad.getStreet());
                ps.setString(4,ad.getCity());
                ps.setString(5,ad.getDistrict());
                ps.setLong(6,ad.getPincode());
                rs=ps.executeQuery();
                if(rs.next())
                {
                        return rs.getInt("id");
                }
            }
        }
        return 0;
    }
    public int registration(UserModel si,AccountModel ac,Address ad,ShopModel sm)throws SQLException
    {
        int id=this.registration(si,ac,ad);
        if(id!=0)
        {
            PreparedStatement ps=Database.getInstance().getConnection().prepareStatement("INSERT INTO shop(userid,name,type) VALUES (?,?,?) RETURNING id;");
            ps.setInt(1,id);
            ps.setString(2,sm.getShopName());
            ps.setString(3,sm.getType());
            ResultSet rs=ps.executeQuery();
            if(rs.next())
            {
                ps=Database.getInstance().getConnection().prepareStatement("INSERT INTO wallet(shopid) VALUES (?) RETURNING id;");
                ps.setInt(1,rs.getInt("id"));
                rs=ps.executeQuery();
                if(rs.next())
                    return rs.getInt("id");
            }
        }
        return 0;
    }
}