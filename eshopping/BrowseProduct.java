package com.zoho.E_Shopping;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import java.io.*;
import java.sql.PreparedStatement;
class BrowseProduct implements PrintProduct
{
    static final String ORDER_STATUS="ordered";
    public void browseProduct(UserModel um)throws SQLException,IOException, PaymentCancelledException,DatNotFoundException
    {
        System.out.println("\n=======================================================================================================");
        System.out.println("\n\t\t\t\tBROWSE PRODUCT");
        System.out.println("=======================================================================================================");
        
        String column=null;
        boolean flag=true;
        HashMap<Integer,ProductDetailModel> product=null;
        while(flag)
        {
            System.out.println("************************************************************************");
            System.out.println("\n\t\tFILTER BY\n\t\t1.PRODUCT NAME\n\t\t2.CATEGORY\n\t\t3.EXIT");
            switch(Validation.getInstance().isDigit("YOUR OPTION"))
            {
                case 1:
                        column="name";
                        break;
                case 2:
                        column="category";
                        break;
                case 3:
                        flag=false;
                        break;
                default:
                    System.out.println("\nSELECT CORRECT OPTION");
            }
            if(flag)
            {
                String value=Validation.getInstance().isAlphaNumeric(column);
                product=getProduct(column,value);
                printProduct(product);
            }
             if(Validation.getInstance().isYesorNo("IF YOU ORDER PRODUCT PRESS (Y) OR (N)").toLowerCase().equals("y"))
                {
                    order(product,um);
                }
        }
    }
    public void order(HashMap<Integer,ProductDetailModel> product,UserModel um)throws IOException,SQLException
    {
        int productid=Validation.getInstance().isDigit("PRODUCT ID TO PLACE ORDER");
        while(!product.containsKey(productid))
        {
            productid=Validation.getInstance().isDigit("CORRECT PRODUCT ID TO PLACE ORDER");
        }
        if(product.get(productid).getCount()==0)
        {
            throw new DatNotFoundException("OUT OF STOCK");
        }
        System.out.println("\n\tNAME  :"+product.get(productid).getProductName());
        System.out.println("\n\tPRICE  :"+product.get(productid).getPrice());
        System.out.println("\n\tCOUNT  :"+product.get(productid).getCount());
        int count=Validation.getInstance().isDigit("ENTER NO OF QUANTITY");
        while(count>product.get(productid).getCount()||count<=0)
        {
            count=Validation.getInstance().isDigit("ENTER NO OF QUANTITY");
        }
        double amount=count*product.get(productid).getPrice();
        System.out.println("\n\t\tTOTEL :"+amount);
        if(Validation.getInstance().isYesorNo("PRESS Y TO CONFIRM ORDER").toLowerCase().equals("y"))
        {
            String paymenttype=paymentType();
            boolean paymentstatus=paymentStatus(paymenttype,amount);
            int id=placeOrder(new OrderModel(um.getId(),productid,count,amount,paymenttype,paymentstatus));
            if(id==0)
            {

            }
            System.out.println("\n\tORDER SUCCUSSFULLY\n\tOERDER ID :"+id);
            if(paymentstatus)
            {
                double amazonamount=(double)(10.0/100.0)*amount;
                double vendoramount=amount-amazonamount;
                updateAmount(amazonamount,vendoramount,product.get(productid).getShopId());
            }
        }

    }
    public void updateAmount(double amazonamount,double vendoramount,int shopid)throws SQLException
    {
        PreparedStatement ps=Database.getInstance().getConnection().prepareStatement("UPDATE wallet set amount=amount+? where shopid=1");
        ps.setDouble(1,amazonamount);
        int id=ps.executeUpdate();
        if(id!=0)
        {
            ps=Database.getInstance().getConnection().prepareStatement("UPDATE wallet set amount=amount+? where shopid=?");
            ps.setDouble(1,vendoramount);
            ps.setInt(2,shopid);
            int id1=ps.executeUpdate();
        }
    }
    public int placeOrder(OrderModel om)throws SQLException
    {
        PreparedStatement ps=Database.getInstance().getConnection().prepareStatement("INSERT INTO orders(userid,productid,count,amount,paymenttype,paymentstatus,orderdate) VALUES (?,?,?,?,?,?,?) RETURNING id;");
        ps.setInt(1,om.getUserId());
        ps.setInt(2,om.getProductId());
        ps.setInt(3,om.getCount());
        ps.setDouble(4,om.getAmount());
        ps.setString(5,om.getPaymentType());
        ps.setBoolean(6,om.getPaymentStatus());       
        ps.setDate(7,new java.sql.Date(new java.util.Date().getTime()));
        ResultSet rs=ps.executeQuery();
        if(rs.next())
        {
            int id=rs.getInt("id");
            ps=Database.getInstance().getConnection().prepareStatement("UPDATE productsdetail set count=count-? where id=?");
            ps.setInt(1,om.getCount());
            ps.setInt(2,om.getProductId());
            ps.executeUpdate();
            return id;
        }
        return 0;
    }
    public boolean paymentStatus(String type,double amount)throws PaymentCancelledException,IOException
    {
        if(type.equals("cod"))
            return false;
        else if(type.equals("upi"))
        {
            new Payment().payByUpi(amount);
            return true;
        }
        else if(type.equals("netbanking"))
        {
            return true;
        }
        else if(type.equals("credit"))
        {
            return true;
        }else if(type.equals("debit"))
        {
            return true;
        }
        return false;
    }
    public String paymentType()throws IOException
    {
        boolean flag=true;
        while(flag)
        {
            System.out.println("\n\t\tPAYMENT TYPE\n\t\t1.COD\n\t\t2.UPI\n\t\t3.NETBANKING\n\t\t4.CREDIT\n\t\t5.DEBIT");
            switch(Validation.getInstance().isDigit("YOUR OPTION"))
            {
                case 1:
                       return "cod";
                    
                case 2:
                        return "upi";
                        
                case 3:
                        return "netbanking";
                        
                case 4:
                        return "credit";
                        
                case 5:
                        return "debit";
                
                default:
                    System.out.println("\nSELECT CORRECT OPTION");
            }
        }
        return null;
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
                    System.out.println("\n\tPRODUCT ID  :"+pr.getKey()+"\t\tNAME  :"+pr.getValue().getProductName());
                    System.out.println("\n\tCATEGORY    :"+pr.getValue().getCategory());
                    System.out.println("\n\tSPECIFICATION  :"+pr.getValue().getSpecification());
                    System.out.println("\n\tPRICE  :"+pr.getValue().getPrice());
                    if(pr.getValue().getCount()==0)
                         System.out.println("\n\t*OUT OF STOCK");
                    System.out.println("_________________________________________________________________________");
                     if((count%5==0)&&Validation.getInstance().isYesorNo("NEXT PAGE PRESS (Y) OTHERWISE (N)").toLowerCase().equals("n"))
                     {
                        break;
                     }
            }
       
        }
    }   
    public HashMap<Integer,ProductDetailModel> getProduct(String column,String value)throws SQLException
    {
        HashMap<Integer,ProductDetailModel> product=new HashMap<>();
        PreparedStatement ps=Database.getInstance().getConnection().prepareStatement("SELECT pd.id,p.name,c.name,pd.specification,pd.price,pd.count,pd.status,pd.shopid from productsdetail pd JOIN products p ON pd.productid=p.id JOIN category c ON c.id=pd.categoryid Where status='t';");
        ResultSet rs=ps.executeQuery();
        while(rs.next())
        {
            if(column.equals("name")&&rs.getString(2).toLowerCase().contains(value.toLowerCase()))
                product.put(rs.getInt(1),new ProductDetailModel(rs.getString(2),rs.getString(3),rs.getString(4),rs.getLong(5),rs.getInt(6),rs.getBoolean(7),rs.getInt(8)));
            if(column.equals("category")&&rs.getString(3).toLowerCase().contains(value.toLowerCase()))
                 product.put(rs.getInt(1),new ProductDetailModel(rs.getString(2),rs.getString(3),rs.getString(4),rs.getLong(5),rs.getInt(6),rs.getBoolean(7),rs.getInt(8)));
        }
        return product;
    }
}