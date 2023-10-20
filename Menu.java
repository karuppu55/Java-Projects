import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
class Menu {
    private String name;
    private String type;
    private double price;

    public Menu(String name, String type, double price) {
        this.name = name;
        this.type = type;
        this.price = price;
    }
    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPrice(double price) {
        this.price = price;
    }
	public String toString()
	{
		return "Menu Name :"+name+" Type :"+type+" price "+price;
	}
}

