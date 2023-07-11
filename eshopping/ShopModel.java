package com.zoho.E_Shopping;
public class ShopModel {
    private int shopid;
    private String shopname;
    private String type;
    private double amount;
    // Default constructor
    public ShopModel() {
    }
    public ShopModel(String shopname, String type) {
        this.shopname = shopname;
        this.type = type;
    }
    // Parameterized constructor
    public ShopModel(int shopid, String shopname, String type) {
        this.shopid = shopid;
        this.shopname = shopname;
        this.type = type;
    }
    public ShopModel(int shopid, String shopname, String type,double amount) {
        this.shopid = shopid;
        this.shopname = shopname;
        this.type = type;
        this.amount=amount;
    }
    // Getter methods
    public int getShopId() {
        return shopid;
    }
    
    public String getShopName() {
        return shopname;
    }
    
    public String getType() {
        return type;
    }
    
    public double getAmount() {
        return amount;
    }
    // Setter methods
    public void setShopId(int shopid) {
        this.shopid = shopid;
    }
    
    public void setShopName(String shopname) {
        this.shopname = shopname;
    }
    
    public void setType(String type) {
        this.type = type;
    }

     public void setAmount(double amount) {
        this.amount = amount;
    }
}
