package com.zoho.E_Shopping;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.io.*;
class CancelOrder extends OrderHistory implements Order<OrderModel>
{
    public void cancelOrder(UserModel um)throws SQLException,IOException,OrderNotFoundException,ItemDeliveredException
    {
         HashMap<Integer,OrderModel> order=getOrder(um.getId());
        if(order.size()==0)
        {
            throw new OrderNotFoundException("**NO ORDER FOUND");
        }
        printOrder(order);
        if(Validation.getInstance().isYesorNo("IF YOU WANT TO CANCEL ANY ORDER PRESS (Y) OR (N)").toLowerCase().equals("y"))
        {
            int orderid=Validation.getInstance().isDigit("ORDER ID TO PLACE ORDER");
            while(!order.containsKey(orderid))
            {
                orderid=Validation.getInstance().isDigit("CORRECT ORDER ID TO PLACE ORDER");
            }
            if(!order.get(orderid).getStatus().equals("shipped"))
            {
                throw new ItemNotDeliveredException("**ITEM NOT AVAILABLE FOR CANCELATION");
            }
            if(Validation.getInstance().isYesorNo("PRESS Y TO CONFIRM CANCELEATION").toLowerCase().equals("y"))
            {
                String reason=Validation.getInstance().isLetter("RESON FOR RETURN");
                int id=addCancel(new CancelationModel(orderid,reason));
                if(id==0)
                {
                }
                System.out.println("\n\t*ORDER CANCEL PROCESSSING....");
            }
        }
    }
    public int addCancel(CancelationModel cm)throws SQLException
    {
        PreparedStatement ps=Database.getInstance().getConnection().prepareStatement("INSERT INTO cancel(orderid,reason) VALUES (?,?) RETURNING id");
        ps.setInt(1,cm.getOrderid());
        ps.setString(2,cm.getReason());
        ResultSet rs=ps.executeQuery();
        if(rs.next())
            return rs.getInt("id");
        return 0;
    }
}