import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
class Address {
    private int doorno;
    private String street;
    private String city;
    private String district;
    private String state;

    public Address(int doorno, String street, String city, String district, String state) {
        this.doorno = doorno;
        this.street = street;
        this.city = city;
        this.district = district;
        this.state = state;
    }

    public int getDoorno() {
        return doorno;
    }

    public void setDoorno(int doorno) {
        this.doorno = doorno;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
	public String toString()
	{
		return "Doorno :"+doorno+" street :"+street+" City :"+city+" District :"+district+" state :"+state;
	}
}
