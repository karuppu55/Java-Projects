package com.zoho.E_Shopping;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import java.io.*;
import java.sql.PreparedStatement;
class RemoveProduct
{
    public void removeProduct(int shopid)throws SQLException,IOException
    {
         HashMap<Integer,ProductDetailModel> product=new ViewProduct().getProduct(shopid);
         printProduct(product);
    }
    public void printProduct(HashMap<Integer,ProductDetailModel> product)throws IOException,SQLException
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
        if(Validation.getInstance().isYesorNo("IF YO REMOVE PRODUCT PRESS (Y) OR (N)").toLowerCase().equals("y"))
        {
            int productid=Validation.getInstance().isDigit("PRODUCT ID TO DELETE");
            while(!product.containsKey(productid))
            {
                productid=Validation.getInstance().isDigit("CORRECT PRODUCT ID TO DELETE");
            }
            System.out.println("\n\tNAME  :"+product.get(productid).getProductName());
            System.out.println("\n\tCATEGORY    :"+product.get(productid).getCategory());
            System.out.println("\n\tSPECIFICATION  :"+product.get(productid).getSpecification());
            System.out.println("\n\tPRICE  :"+product.get(productid).getPrice());
            if(Validation.getInstance().isYesorNo("CHECK ABOVE DETAIL AND CLICK (Y) TO CONFIRM").toLowerCase().equals("y"))
            {
                if(updateStatus(productid))
                {
                    System.out.println("\n\tPRODUCT REMOVED SUCCUSSFULLY");
                }
            }
        }
    }
    public boolean updateStatus(int productid)throws SQLException
    {
        PreparedStatement ps=Database.getInstance().getConnection().prepareStatement("update productsdetail set status=? where id=?");
        ps.setBoolean(1,false);
        ps.setInt(2,productid);    
        int id=ps.executeUpdate();
        if(id>0)
            return true;
        return false;
   }   
}