import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
class Customer {
    private String name;
    private Address address;
    private ArrayList<Orders> orders;

    public Customer(String name, int doorno, String street, String city, String district, String state) {
        this.name = name;
        this.address = new Address(doorno, street, city, district, state);
        this.orders = new ArrayList<>();
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Address getAddress() {
        return address;
    }
    public void setAddress(Address address) {
        this.address = address;
    }
	public ArrayList<Orders> getOrders()
	{
			return orders;
	}
	public void setOrder(Orders order,ArrayList<Integer> menus)
	{
		orders.add(order);
		for(int i=0;i<menus.size();i++)
		{
			orders.get(orders.size()-1).setOrderMenu(menus.get(i));
		}
	}
	public String toString()
	{
		return "Name :"+name+"Address :"+address;
	}
}
interface DeliveryApp
{
	 void menu();
}