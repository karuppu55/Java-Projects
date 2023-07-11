package com.zoho.E_Shopping;
public class ProductDetailModel {
    private int productid;
    private String productname;
    private int categoryid;
    private String category;
    private int shopid;
    private int count;
    private double price;
    private boolean status;
    private String specification;

    // Default constructor
    public ProductDetailModel() {
    }

    // Parameterized constructor
    public ProductDetailModel(int productid, String productname, int categoryid, String category, int shopid, int count, double price, boolean status, String specification) {
        this.productid = productid;
        this.productname = productname;
        this.categoryid = categoryid;
        this.category = category;
        this.shopid = shopid;
        this.count = count;
        this.price = price;
        this.status = status;
        this.specification = specification;
    }
     public ProductDetailModel(String productname,String category,  String specification,double price,int count, boolean status) {
        this.productname = productname;
        this.category = category;
        this.count = count;
        this.price = price;
        this.status = status;
        this.specification = specification;
    }
    public ProductDetailModel(String productname,String category,  String specification,double price,int count, boolean status,int shopid) {
        this.productname = productname;
        this.category = category;
        this.count = count;
        this.price = price;
        this.shopid=shopid;
        this.status = status;
        this.specification = specification;
    }
     public ProductDetailModel(String productname,String category,  String specification,double price) {
        this.productname = productname;
        this.category = category;
        this.price = price;
        this.specification = specification;
    }
    public ProductDetailModel(String productname,String category,  String specification,double price,int productid) {
        this.productname = productname;
        this.productid=productid;
        this.category = category;
        this.price = price;
        this.specification = specification;
    }
     public ProductDetailModel(int productid,int categoryid,String specification,int count, double price,int shopid) {
        this.productid = productid;
        this.categoryid = categoryid;
        this.shopid = shopid;
        this.count = count;
        this.price = price;
        this.specification = specification;
    }

    // Getter methods
    public int getProductId() {
        return productid;
    }

    public String getProductName() {
        return productname;
    }

    public int getCategoryId() {
        return categoryid;
    }

    public String getCategory() {
        return category;
    }

    public int getShopId() {
        return shopid;
    }

    public int getCount() {
        return count;
    }

    public double getPrice() {
        return price;
    }

    public boolean getStatus() {
        return status;
    }

    public String getSpecification() {
        return specification;
    }

    // Setter methods
    public void setProductId(int productid) {
        this.productid = productid;
    }

    public void setProductName(String productname) {
        this.productname = productname;
    }

    public void setCategoryId(int categoryid) {
        this.categoryid = categoryid;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setShopId(int shopid) {
        this.shopid = shopid;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }
}
