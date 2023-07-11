package com.zoho.E_Shopping;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import java.io.*;
class ViewProduct implements GetProduct
{
    public void viewProduct(int shopid)throws SQLException,IOException
    {
        System.out.println("\n=======================================================================================================");
        System.out.println("\n\t\t\t\tPRODUCTS");
        System.out.println("=======================================================================================================");
        HashMap<Integer,ProductDetailModel> product=getProduct(shopid);
        printProduct(product);
   }
   @Override
    public void printProduct(HashMap<Integer,ProductDetailModel> product)throws IOException
    {
        if(product.size()!=0)
        {
            int count=0;
            System.out.println("_________________________________________________________________________");
            for(Map.Entry<Integer,ProductDetailModel> pr:product.entrySet())
            {
                    count++;
                    System.out.println("\n\tPRODUCT ID  :"+pr.getKey());
                    System.out.println("\n\tNAME  :"+pr.getValue().getProductName());
                    System.out.println("\n\tCATEGORY    :"+pr.getValue().getCategory());
                    System.out.println("\n\tSPECIFICATION  :"+pr.getValue().getSpecification());
                    System.out.println("\n\tPRICE  :"+pr.getValue().getPrice());
                    System.out.println("_________________________________________________________________________");
                     if((count%5==0)&&Validation.getInstance().isYesorNo("NEXT PAGE PRESS (Y) OTHERWISE (N)").toLowerCase().equals("n"))
                     {
                        break;
                     }
            }
        }
    }   
    @Override
    public HashMap<Integer,ProductDetailModel> getProduct(int shopid)throws SQLException
    {
        HashMap<Integer,ProductDetailModel> product=new HashMap<>();
        ResultSet rs=SingletonStatement.getStatement().executeQuery("SELECT pd.id,p.name,c.name,pd.specification,pd.price,pd.count,pd.status from productsdetail pd JOIN products p ON pd.productid=p.id JOIN category c ON c.id=pd.categoryid Where shopid="+shopid+" AND status='t';");
        while(rs.next())
            product.put(rs.getInt(1),new ProductDetailModel(rs.getString(2),rs.getString(3),rs.getString(4),rs.getLong(5),rs.getInt(6),rs.getBoolean(7)));
        return product;
    }
}