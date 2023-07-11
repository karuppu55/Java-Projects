package com.zoho.E_Shopping;
public class Address {
    private int userid;
    private int id;
    private int doorno;
    private String street;
    private String city;
    private String district;
    private long pincode;
    public Address(int doorno, String street, String city, String district, long pincode) {
        this.doorno = doorno;
        this.street = street;
        this.city = city;
        this.district = district;
        this.pincode = pincode;
    }
    public Address(int id,int doorno, String street, String city, String district, long pincode) {
        this.id=id;
        this.doorno = doorno;
        this.street = street;
        this.city = city;
        this.district = district;
        this.pincode = pincode;
    }
    // Getter methods
    
    public int getUserId() {
        return userid;
    }
     public int GetId() {
        return id;
    }
    
    public int getDoorNo() {
        return doorno;
    }
    
    public String getStreet() {
        return street;
    }
    
    public String getCity() {
        return city;
    }
    
    public String getDistrict() {
        return district;
    }
    
    public long getPincode() {
        return pincode;
    }
    
    // Setter methods
    
    public void setUserId(int userid) {
        this.userid = userid;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public void setDoorNo(int doorno) {
        this.doorno = doorno;
    }
    
    public void setStreet(String street) {
        this.street = street;
    }
    
    public void setCity(String city) {
        this.city = city;
    }
    
    public void setDistrict(String district) {
        this.district = district;
    }
    
    public void setPincode(long pincode) {
        this.pincode = pincode;
    }
    
    // Rest of the class...
}
