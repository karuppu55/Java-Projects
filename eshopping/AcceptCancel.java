package com.zoho.E_Shopping;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.io.*;
class AcceptCancel implements Order<CancelationModel>
{
    static final String ORDER_STATUS="cancelled";
    public void acceptCancel(UserModel um)throws SQLException,IOException
    {
        HashMap<Integer,CancelationModel> cancel=getOrder(um.sm.getShopId());
        if(cancel.size()==0)
        {
            throw new DatNotFoundException("**NO DATA FOUND");
        }
        printOrder(cancel);
        if(Validation.getInstance().isYesorNo("PRESS Y TO CANCEL ORDER").toLowerCase().equals("y"))
        {
            int cancelid=Validation.getInstance().isDigit("ORDER ID TO RETURN ORDER");
            while(!cancel.containsKey(cancelid))
            {
                cancelid=Validation.getInstance().isDigit("CORRECT ORDER ID TO PLACE ORDER");
            }
            int id=updateStatus(new OrderModel(cancel.get(cancelid).om.getId(),ORDER_STATUS,cancel.get(cancelid).om.getCount(),cancel.get(cancelid).pm.getProductId(),cancel.get(cancelid).om.getAmount(),um.sm.getShopId(),cancel.get(cancelid).om.getPaymentType()),new CancelationModel(cancelid,false));
            if(id!=0)
            {
                System.out.println("\n\t*ORDER STATUS UPDATED.");
            }
        }
    }
    @Override
    public void printOrder(HashMap<Integer,CancelationModel> cancel)throws IOException
    {
        int count=0;
        System.out.println("_________________________________________________________________________");
        for(Map.Entry<Integer,CancelationModel> can:cancel.entrySet())
        {
            count++;
            System.out.println("\n\tCANCELATION ID  :"+can.getKey());
            System.out.println("\n\tPRODUCT DEATAIL :");
            System.out.println("\n\t\tNAME  :"+can.getValue().pm.getProductName()+"\t\tCATEGORY    :"+can.getValue().pm.getCategory());
            System.out.println("\n\t\tSPECIFICATION  :"+can.getValue().pm.getSpecification());
            System.out.println("\n\t\tPRICE  :"+can.getValue().pm.getPrice());
            System.out.println("\n\tCUSTOMER DEATAIL :");
            System.out.println("\n\t\tNAME  :"+can.getValue().um.getName());
            System.out.println("\n\tCANCELATION DETAIL:");
            System.out.println("\n\t\tID   :"+can.getKey()+"\tREASON    :"+can.getValue().getReason());
             System.out.println("\n\tPAYMENT DETAIL:");
            System.out.println("\n\t\tPAYMENT TYPE  :"+can.getValue().om.getPaymentType()+"\tPAYMENT STATUS    :"+can.getValue().om.getPaymentStatus());            
            System.out.println("_________________________________________________________________________");
            if((count%5==0)&&Validation.getInstance().isYesorNo("NEXT PAGE PRESS (Y) OTHERWISE (N)").toLowerCase().equals("n"))
            {
                break;
            }            
        }
    }
    @Override
    public HashMap<Integer,CancelationModel> getOrder(int shopid)throws SQLException
    {
        HashMap<Integer,CancelationModel> cancel=new HashMap<>();
        PreparedStatement ps=Database.getInstance().getConnection().prepareStatement("SELECT o.id,p.name,c.name,pd.specification,pd.price,u.name,o.count,o.amount,o.paymenttype,o.paymentstatus,o.status,ca.id,ca.reason,ca.status,pd.id from productsdetail pd JOIN products p ON pd.productid=p.id JOIN category c ON c.id=pd.categoryid JOIN orders o ON o.productid=pd.id JOIN usersdetail u on  u.id=o.userid JOIN address a ON a.userid=u.id JOIN cancel ca ON ca.orderid=o.id Where ca.status='t' AND pd.shopid=?;");
        ps.setInt(1,shopid);
        ResultSet rs=ps.executeQuery();
        while(rs.next())
            cancel.put(rs.getInt(12),new CancelationModel(rs.getString(2),rs.getString(3),rs.getString(4),rs.getDouble(5),rs.getString(6),rs.getInt(7),rs.getDouble(8),rs.getString(9),rs.getBoolean(10),rs.getString(11),rs.getInt(1),rs.getString(13),rs.getBoolean(14),rs.getInt(15)));
        return cancel;
    }
    public int updateStatus(OrderModel order,CancelationModel cancel) throws SQLException
    {
        if(!order.getPaymentType().equals("cod"))
        {
            Double shopamount=(10.0/100.0)*order.getAmount();
            PreparedStatement ps=Database.getInstance().getConnection().prepareStatement("UPDATE shop set amount=amount-? where id=1;");
            ps.setDouble(1,shopamount);   
            ps.executeUpdate();
            ps=Database.getInstance().getConnection().prepareStatement("UPDATE shop set amount=amount-? where id=?;");
            ps.setDouble(1,(shopamount-order.getAmount()));
            ps.setInt(2,order.getShopId());  
            ps.executeUpdate();
        }
        PreparedStatement ps=Database.getInstance().getConnection().prepareStatement("UPDATE orders set status=? where id=?;");
        ps.setString(1,order.getStatus());
        ps.setInt(2,order.getId());
        ps.executeUpdate();
        ps=Database.getInstance().getConnection().prepareStatement("UPDATE cancel set status=? where id=?;");
        ps.setBoolean(1,cancel.getStatus());
        ps.setInt(2,cancel.getId());
        ps=Database.getInstance().getConnection().prepareStatement("UPDATE productsdetail set count=count+? where id=?;");
        ps.setInt(1,order.getCount());
        ps.setInt(2,order.getProductId());
        return ps.executeUpdate();    
    }
}