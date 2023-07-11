package com.zoho.E_Shopping;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.io.*;
class ReturnOrder extends CancelOrder implements Order<OrderModel>
{
    public void returnOrder(UserModel um)throws SQLException,IOException,OrderNotFoundException,ItemNotDeliveredException
    {
        HashMap<Integer,OrderModel> order=getOrder(um.getId());
        if(order.size()==0)
        {
            throw new OrderNotFoundException("**NO ORDER FOUND");
        }
        printOrder(order);
        if(Validation.getInstance().isYesorNo("IF YOU RETURN ORDER PRESS (Y) OR (N)").toLowerCase().equals("y"))
        {
            int orderid=Validation.getInstance().isDigit("ORDER ID TO RETURN ORDER");
            while(!order.containsKey(orderid))
            {
                orderid=Validation.getInstance().isDigit("CORRECT ORDER ID TO PLACE ORDER");
            }
            if(!order.get(orderid).getStatus().equals("delivered"))
            {
                throw new ItemNotDeliveredException("**ITEM NOT AVAILABLE FOR RETURNING");
            }
            if(Validation.getInstance().isYesorNo("PRESS Y TO CONFIRM RETURNING").toLowerCase().equals("y"))
            {
                String reason=Validation.getInstance().isLetter("RESON FOR RETURN");
                int id=addReturn(new ReturnModel(orderid,reason));
                if(id==0)
                {
                }
                System.out.println("\n\t*ORDER RETURNED PROCESSSING....");
            }
        }
    }
    public int addReturn(ReturnModel rm)throws SQLException
    {
        PreparedStatement ps=Database.getInstance().getConnection().prepareStatement("INSERT INTO return(orderid,feedback) VALUES (?,?) RETURNING id");
        ps.setInt(1,rm.getOrderid());
        ps.setString(2,rm.getReason());
        ResultSet rs=ps.executeQuery();
        if(rs.next())
            return rs.getInt("id");
        return 0;
    }
}