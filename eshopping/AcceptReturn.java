package com.zoho.E_Shopping;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;
import java.io.*;
class AcceptReturn implements Order<ReturnModel>
{
    public void acceptReturn(UserModel um)throws SQLException,IOException
    {
        HashMap<Integer,ReturnModel> returned=getOrder(um.sm.getShopId());
        if(returned.size()==0)
        {
             throw new DatNotFoundException("**NO DATA FOUND");
        }
        printOrder(returned);
        if(Validation.getInstance().isYesorNo("PRESS Y TO ACCEPT ORDER").toLowerCase().equals("y"))
        {
            int returnedid=Validation.getInstance().isDigit("ORDER ID TO RETURN ORDER");
            while(!returned.containsKey(returnedid))
            {
                returnedid=Validation.getInstance().isDigit("CORRECT ORDER ID TO PLACE ORDER");
            }
            if(updateStatus(new ReturnModel(returnedid,false))!=0)
            {
                System.out.println("\n\t*ORDER STATUS UPDATED.");
            }
        }
    }
    @Override
    public void printOrder(HashMap<Integer,ReturnModel> returned)throws IOException
    {
        int count=0;
        System.out.println("_________________________________________________________________________");
        for(Map.Entry<Integer,ReturnModel> can:returned.entrySet())
        {
            count++;
            System.out.println("\n\tRETURNING ID  :"+can.getKey());
            System.out.println("\n\tPRODUCT DEATAIL :");
            System.out.println("\n\t\tNAME  :"+can.getValue().pm.getProductName()+"\t\tCATEGORY    :"+can.getValue().pm.getCategory());
            System.out.println("\n\t\tSPECIFICATION  :"+can.getValue().pm.getSpecification());
            System.out.println("\n\t\tPRICE  :"+can.getValue().pm.getPrice());
            System.out.println("\n\tCUSTOMER DEATAIL :");
            System.out.println("\n\t\tNAME  :"+can.getValue().um.getName());
            System.out.println("\n\tCANCELATION DETAIL:");
            System.out.println("\n\t\tID   :"+can.getKey()+"\tREASON    :"+can.getValue().getReason());
             System.out.println("\n\tPAYMENT DETAIL:");
            System.out.println("\n\t\tPAYMENTTYPE  :"+can.getValue().om.getPaymentType()+"\tPAYMENT STATUS    :"+can.getValue().om.getPaymentStatus());            
            System.out.println("_________________________________________________________________________");
            if((count%5==0)&&Validation.getInstance().isYesorNo("NEXT PAGE PRESS (Y) OTHERWISE (N)").toLowerCase().equals("n"))
            {
                break;
            }            
        }
    }
    @Override
    public HashMap<Integer,ReturnModel> getOrder(int shopid)throws SQLException
    {
        HashMap<Integer,ReturnModel> returned=new HashMap<>();
        PreparedStatement ps=Database.getInstance().getConnection().prepareStatement("SELECT o.id,p.name,c.name,pd.specification,pd.price,u.name,o.count,o.amount,o.paymenttype,o.paymentstatus,o.status,re.id,re.feedback,re.status from productsdetail pd JOIN products p ON pd.productid=p.id JOIN category c ON c.id=pd.categoryid JOIN orders o ON o.productid=pd.id JOIN usersdetail u on  u.id=o.userid JOIN address a ON a.userid=u.id JOIN return re ON re.orderid=o.id Where re.status='t' AND pd.shopid=?;");
        ps.setInt(1,shopid);
        ResultSet rs=ps.executeQuery();
        while(rs.next())
            returned.put(rs.getInt(12),new ReturnModel(rs.getString(2),rs.getString(3),rs.getString(4),rs.getDouble(5),rs.getString(6),rs.getInt(7),rs.getDouble(8),rs.getString(9),rs.getBoolean(10),rs.getString(11),rs.getInt(1),rs.getString(13),rs.getBoolean(14)));
        return returned;
    }
    public int updateStatus(ReturnModel returned)throws SQLException
    {
        PreparedStatement ps=Database.getInstance().getConnection().prepareStatement("UPDATE return set status=?,enddate=? where id=?;");
        ps.setBoolean(1,returned.getStatus());
        ps.setDate(2,java.sql.Date.valueOf(java.time.LocalDate.now().plusDays(new Random().nextInt(3)+3)));
        ps.setInt(3,returned.getId());
        return ps.executeUpdate();
    }
}