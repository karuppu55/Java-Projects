import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
class Restaurant {
    private String name;
    private String type;
    private Address address;
    private ArrayList<Integer> pendingorder;
    private ArrayList<Orders> orders;
    private HashMap<Integer, Menu> menus;

    public Restaurant(String name, String type, int doorno, String street, String city, String district, String state) {
        this.name = name;
        this.type = type;
        this.address = new Address(doorno, street, city, district, state);
        this.pendingorder = new ArrayList<>();
        this.orders = new ArrayList<>();
        this.menus = new HashMap<>();
    }
	public void setMenu(String name,String type,double price)
	{
		menus.put(menus.size()+1,new Menu(name,type,price));
	}
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
	
	public void setOrder(Orders order,ArrayList<Integer> menus)
	{
		orders.add(order);
		pendingorder.add(orders.size()-1);
		for(int i=0;i<menus.size();i++)
		{
			orders.get(orders.size()-1).setOrderMenu(menus.get(i));
		}
		
	}
	
    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
	public HashMap<Integer,Menu> getMenus()
	{
		return menus;
	}
	public ArrayList<Integer> getPendingOrders()
	{
		return pendingorder;
	}
	public ArrayList<Orders> getOrders()
	{
		return orders;
	}
	
	public String toString()
	{
		return "Name :"+name+" Type :"+type+" Address :"+address;
	}
}
