package com.zoho.E_Shopping;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;
import java.io.*;
class AcceptOrder implements Order<OrderModel>
{
    static final String ORDER_STATUS="shipped";
    public void acceptOrder(UserModel um)throws SQLException,IOException,DatNotFoundException
    {
        HashMap<Integer,OrderModel> order=getOrder(um.sm.getShopId());
        if(order.size()==0)
        {
            throw new DatNotFoundException("**NO DATA FOUND");
        }
        printOrder(order);
        if(Validation.getInstance().isYesorNo("PRESS Y TO ACCEPT ORDER").toLowerCase().equals("y"))
        {
            int orderid=Validation.getInstance().isDigit("ORDER ID TO RETURN ORDER");
            while(!order.containsKey(orderid))
            {
                orderid=Validation.getInstance().isDigit("CORRECT ORDER ID TO PLACE ORDER");
            }
            int id=updateStatus(new OrderModel(orderid,ORDER_STATUS));
            if(id!=0)
            {
                System.out.println("\n\t*ORDER STATUS UPDATED.");
            }
        }
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
            System.out.println("\n\tCUSTOMER DEATAIL :");
            System.out.println("\n\t\tNAME  :"+or.getValue().um.getName());
            System.out.println("\n\tSHIIPING ADDRESS    :");
            System.out.println("\n\t\t"+or.getValue().ad.getDoorNo()+","+or.getValue().ad.getStreet()+","+or.getValue().ad.getCity()+","+or.getValue().ad.getDistrict()+"-"+or.getValue().ad.getPincode());
            System.out.println("_________________________________________________________________________");
            if((count%5==0)&&Validation.getInstance().isYesorNo("NEXT PAGE PRESS (Y) OTHERWISE (N)").toLowerCase().equals("n"))
            {
                break;
            }            
        }
    }
    @Override
    public HashMap<Integer,OrderModel> getOrder(int shopid)throws SQLException
    {
        HashMap<Integer,OrderModel> order=new HashMap<>();
        PreparedStatement ps=Database.getInstance().getConnection().prepareStatement("SELECT o.id,p.name,c.name,pd.specification,pd.price,u.name,o.count,o.amount,o.paymenttype,o.paymentstatus,o.status,a.doorno,a.street,a.city,a.district,a.pincode from productsdetail pd JOIN products p ON pd.productid=p.id JOIN category c ON c.id=pd.categoryid JOIN orders o ON o.productid=pd.id JOIN usersdetail u on  u.id=o.userid JOIN address a ON a.userid=u.id Where o.status='ordered' AND pd.shopid=?;");
        ps.setInt(1,shopid);
        ResultSet rs=ps.executeQuery();
        while(rs.next())
        {
            order.put(rs.getInt(1),new OrderModel(rs.getString(2),rs.getString(3),rs.getString(4),rs.getDouble(5),rs.getString(6),rs.getInt(7),rs.getDouble(8),rs.getString(9),rs.getBoolean(10),rs.getString(11),rs.getInt(12),rs.getString(13),rs.getString(14),rs.getString(15),rs.getLong(16)));
        }
        return order;
    }
    public int updateStatus(OrderModel order)throws SQLException
    {
        PreparedStatement ps=Database.getInstance().getConnection().prepareStatement("UPDATE orders set status=?,deliverydate=? where id=?");
        ps.setString(1,order.getStatus());
        ps.setDate(2,java.sql.Date.valueOf(java.time.LocalDate.now().plusDays(new Random().nextInt(3)+3)));
        ps.setInt(3,order.getId());
        return ps.executeUpdate();
    }
}