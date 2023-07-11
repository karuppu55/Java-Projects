package com.zoho.E_Shopping;
public class OrderModel {
    private int id;
    private int userid;
    private int productid;
    private int count;
    private double amount;
    private String orderdate;
    private String paymenttype;
    private boolean paymentstatus;
    private String status;
    private int shopid;
    ProductDetailModel pm=null;
    UserModel um=null;
    Address ad=null;
     public OrderModel(String productname,String category,String specification,double price,String username,int count, double amount,String paymenttype,boolean paymentstatus,String status,int doorno,String street,String city,String district,Long pincode) 
     {
        pm=new ProductDetailModel(productname,category,specification,price);
        um=new UserModel(username);
        ad=new Address(doorno,street,city,district,pincode);
        this.count = count;
        this.amount = amount;
        this.paymenttype = paymenttype;
        this.paymentstatus = paymentstatus;
        this.status = status;
    }
    public OrderModel(String productname,String category,String specification,double price,int count, double amount,String paymenttype,boolean paymentstatus,String status) 
     {
        pm=new ProductDetailModel(productname,category,specification,price);
        this.count = count;
        this.amount = amount;
        this.paymenttype = paymenttype;
        this.paymentstatus = paymentstatus;
        this.status = status;
    }
    public OrderModel(int userid, int productid, int count, double amount, String orderdate,
                      String paymenttype, boolean paymentstatus, String status) {
        this.userid = userid;
        this.productid = productid;
        this.count = count;
        this.amount = amount;
        this.orderdate = orderdate;
        this.paymenttype = paymenttype;
        this.paymentstatus = paymentstatus;
        this.status = status;
    }
    public OrderModel(int id,int count, double amount,String paymenttype, boolean paymentstatus,String status) {
        this.id=id;
        this.count = count;
        this.amount = amount;
        this.paymenttype = paymenttype;
        this.paymentstatus = paymentstatus;
        this.status=status;
    }
    public OrderModel(int id,String status) {
        this.id=id;
        this.status=status;
    }
     public OrderModel(int id) {
        this.id=id;
    }
    public OrderModel(int id,String status,int count,int productid) {
        this.id=id;
        this.productid=productid;
        this.count=count;
        this.status=status;
    }
    public OrderModel(int id,String status,int count,int productid,double amount,int shopid,String paymenttype) {
        this.id=id;
        this.paymenttype=paymenttype;
        this.shopid=shopid;
        this.amount=amount;
        this.productid=productid;
        this.count=count;
        this.status=status;
    }
    public OrderModel(int userid, int productid, int count, double amount,
                      String paymenttype, boolean paymentstatus) {
        this.userid = userid;
        this.productid = productid;
        this.count = count;
        this.amount = amount;
        this.paymenttype = paymenttype;
        this.paymentstatus = paymentstatus;
    }
    // Getters and setters for all the fields
    public int getUserId() {
        return userid;
    }

    public void setUserId(int userid) {
        this.userid = userid;
    }
     public int getShopId() {
        return shopid;
    }

    public void setShopId(int shopid) {
        this.shopid = shopid;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductId() {
        return productid;
    }

    public void setProductId(int productid) {
        this.productid = productid;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getOrderDate() {
        return orderdate;
    }

    public void setOrderDate(String orderdate) {
        this.orderdate = orderdate;
    }

    public String getPaymentType() {
        return paymenttype;
    }

    public void setPaymentType(String paymenttype) {
        this.paymenttype = paymenttype;
    }

    public boolean getPaymentStatus() {
        return paymentstatus;
    }

    public void setPaymentStatus(boolean paymentstatus) {
        this.paymentstatus =paymentstatus;
    }
      public String getStatus() {
        return status;
    }

    public void setStatus(String Status) {
        this.status = status;
    }
}
