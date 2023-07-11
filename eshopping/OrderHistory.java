package com.zoho.E_Shopping;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.io.*;
class OrderHistory implements Order<OrderModel>
{
    public void orderHistory(UserModel um)throws SQLException,IOException,OrderNotFoundException
    {
        HashMap<Integer,OrderModel> order=getOrder(um.getId());
        if(order.size()==0)
        {
            throw new OrderNotFoundException("**NO ORDER FOUND");
        }
        printOrder(order);
    }
    @Override
    public void printOrder(HashMap<Integer,OrderModel> order)throws IOException
    {
        int count=0;
        System.out.println("_________________________________________________________________________");
        for(Map.Entry<Integer,OrderModel> or:order.entrySet())
        {
            count++;
            System.out.println("\n\tORDER ID  :"+or.getKey());
            System.out.println("\n\tPRODUCT DEATAIL :");
            System.out.println("\n\t\tNAME  :"+or.getValue().pm.getProductName()+"\t\tCATEGORY    :"+or.getValue().pm.getCategory());
            System.out.println("\n\t\tSPECIFICATION  :"+or.getValue().pm.getSpecification());
            System.out.println("\n\t\tPRICE  :"+or.getValue().pm.getPrice());
            System.out.println("\n\t\tORDER STATUS  :"+or.getValue().getStatus());
            System.out.println("\n\t\tPAYMENT TYPE  :"+or.getValue().getPaymentType()+"\t\tPAYMENT STATUS  :"+or.getValue().getPaymentStatus()); 
            System.out.println("_________________________________________________________________________");
            if((count%5==0)&&Validation.getInstance().isYesorNo("NEXT PAGE PRESS (Y) OTHERWISE (N)").toLowerCase().equals("n"))
            {
                break;
            }
        }
    }
    @Override
    public HashMap<Integer,OrderModel> getOrder(int userid)throws SQLException
    {
        HashMap<Integer,OrderModel> order=new HashMap<>();
        PreparedStatement ps=Database.getInstance().getConnection().prepareStatement("SELECT o.id,p.name,c.name,pd.specification,pd.price,u.name,o.count,o.amount,o.paymenttype,o.paymentstatus,o.status from productsdetail pd JOIN products p ON pd.productid=p.id JOIN category c ON c.id=pd.categoryid JOIN orders o ON o.productid=pd.id JOIN usersdetail u on  u.id=o.userid JOIN address a ON a.userid=u.id Where o.userid=?;");
        ps.setInt(1,userid);
        ResultSet rs=ps.executeQuery();
        while(rs.next())
            order.put(rs.getInt(1),new OrderModel(rs.getString(2),rs.getString(3),rs.getString(4),rs.getDouble(5),rs.getInt(7),rs.getDouble(8),rs.getString(9),rs.getBoolean(10),rs.getString(11)));
        return order;
    }
}