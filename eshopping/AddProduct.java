package com.zoho.E_Shopping;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.io.*;
class AddProduct
{
    public void addProduct(UserModel um)throws SQLException,IOException
    {
        System.out.println("=======================================================================================================");
        System.out.println("\n\t\t\tADD PRODUCT");
        System.out.println("=======================================================================================================");
		int productid=getProduct(Validation.getInstance().isAlphaNumeric("PRODUCT NAME").trim());
        int categoryid=getCategory(Validation.getInstance().isLetter("CATEGORY").trim());
        String specification=Validation.getInstance().isAlphaNumeric("SPECIFICATION");
        int count=Validation.getInstance().isDigit("COUNT");
        double price=Validation.getInstance().isDigit("PRICE");
        if(Validation.getInstance().isYesorNo("CHECK ABOVE DETAIL AND CLICK (Y) TO CONFIRM").toLowerCase().equals("y"))
        {
                int id=insertData(new ProductDetailModel(productid,categoryid,specification,count,price,um.sm.getShopId()));
                if(id==0)
                {

                }
                else if(id==-1)
                {
                    System.out.println("\n\tPRODUCT ALREADY EXIST \n\tCOUNT UPDATED");
                }
                else
                {
                    System.out.println("\n\tPRODUCT ADDED SUCCUSSFULLY");
                }
        }
    }
    public int getCategory(String category)throws SQLException
    {
        ResultSet rs=SingletonStatement.getStatement().executeQuery("SELECT id,name FROM category where lower(name)=lower('"+category+"');");
        if(rs.next())
            return rs.getInt(1);
        else    
        {
            ResultSet rs1=SingletonStatement.getStatement().executeQuery("INSERT INTO category(name) VALUES ('"+category+"') RETURNING id;");
            if(rs1.next())
                return rs1.getInt("id");
        }
        return 0;
    }
    public int getProduct(String product)throws SQLException
    {
        ResultSet rs=SingletonStatement.getStatement().executeQuery("SELECT id,name FROM products where lower(name)=lower('"+product+"');");
        if(rs.next())
            return rs.getInt(1);
        else    
        {
            ResultSet rs1=SingletonStatement.getStatement().executeQuery("INSERT INTO products(name) VALUES ('"+product+"') RETURNING id;");
            if(rs1.next())
                return rs1.getInt("id");
        }
        return 0;
    }
    public int insertData(ProductDetailModel pd)throws SQLException
    {
        PreparedStatement ps=Database.getInstance().getConnection().prepareStatement("SELECT id from productsdetail WHERE productid=? AND categoryid=? AND shopid=? AND price=?;");
        ps.setInt(1,pd.getProductId());
        ps.setInt(2,pd.getCategoryId());   
        ps.setInt(3,pd.getShopId());
        ps.setDouble(4,pd.getPrice());
        ResultSet rs=ps.executeQuery();
        if(rs.next())
        {
            int pid=rs.getInt(1);
            ps=Database.getInstance().getConnection().prepareStatement("UPDATE productsdetail set count=count+? where id=?;");
            ps.setInt(1,pd.getCount());
            ps.setInt(2,pid);
            int id=ps.executeUpdate();
            if(id>0)
                return -1; 
        }
        else
        {
            ps=Database.getInstance().getConnection().prepareStatement("INSERT INTO productsdetail(productid,categoryid,specification,count,price,shopid) VALUES (?,?,?,?,?,?) RETURNING id;");
            ps.setInt(1,pd.getProductId());
            ps.setInt(2,pd.getCategoryId());
            ps.setString(3,pd.getSpecification());
            ps.setInt(4,pd.getCount());
            ps.setDouble(5,pd.getPrice());
            ps.setInt(6,pd.getShopId());
            rs=ps.executeQuery();
            if(rs.next())
                return rs.getInt("id");
        }
        return 0;
    }
}