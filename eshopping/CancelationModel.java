package com.zoho.E_Shopping;
public class CancelationModel {
    private int id;
    private int orderid;
    private String reason;
    private boolean status;
    ProductDetailModel pm=null;
    UserModel um=null;
    OrderModel om=null;
    public CancelationModel(String productname,String category,String specification,double price,String username,int count, double amount,String paymenttype,boolean paymentstatus,String orderstatus,int id,String reason,boolean status,int productid) 
     {
        pm=new ProductDetailModel(productname,category,specification,price,productid);
        um=new UserModel(username);
        om=new OrderModel(id,count,amount,paymenttype,paymentstatus,orderstatus);
        this.reason=reason;
        this.status=status;
    }
    CancelationModel(int orderid,String reason)
    {
        this.orderid=orderid;
        this.reason=reason;
    }
    CancelationModel(int id,boolean status)
    {
         this.id=id;
         this.status = status;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderid() {
        return orderid;
    }

    public void setOrderid(int orderid) {
        this.orderid = orderid;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
