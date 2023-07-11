package com.zoho.E_Shopping;
public class ProductModel {
    private int productid;
    private String productname;
    private boolean status;

    // Default constructor
    public ProductModel() {
    }

    // Parameterized constructor
    public ProductModel(int productid, String productname, boolean status) {
        this.productid = productid;
        this.productname = productname;
        this.status = status;
    }

    // Getter methods
    public int getProductId() {
        return productid;
    }

    public String getProductName() {
        return productname;
    }

    public boolean getStatus() {
        return status;
    }

    // Setter methods
    public void setProductId(int productid) {
        this.productid = productid;
    }

    public void setProductName(String productname) {
        this.productname = productname;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
