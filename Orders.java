import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
class Orders {
    private int id;
	private int cusid;
    private double amount;
    private boolean orderstatus;
    private boolean paystatus;
    private int hotelid;
    private ArrayList<Integer> orders;

    public Orders(int id,int cusid ,int hotelid,double amount, boolean orderstatus, boolean paystatus) {
        this.id = id;
		this.hotelid=hotelid;
		this.cusid=cusid;
        this.amount = amount;
        this.orderstatus = orderstatus;
        this.paystatus = paystatus;
		orders=new ArrayList<>();
    }
	public void setOrderMenu(int id)
	{
		orders.add(id);
	}
    public int getId() {
        return id;
    }

    public void setCusId(int cusid) {
        this.cusid = cusid;
    }
	
	 public int getHotelId() {
        return hotelid;
    }

    public void setHotelId(int hotelid) {
        this.hotelid = hotelid;
    }
	
	public int getCusId() {
        return cusid;
    }

    public void setId(int id) {
        this.id = id;
    }
	
    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public boolean isOrderstatus() {
        return orderstatus;
    }

    public void setOrderstatus(boolean orderstatus) {
        this.orderstatus = orderstatus;
    }

    public boolean isPaystatus() {
        return paystatus;
    }

    public void setPaystatus(boolean paystatus) {
        this.paystatus = paystatus;
    }
	public ArrayList<Integer> getOrderDetail()
	{
		return orders;
	}
	public String toString()
	{
		return "Id :"+id+" Amount :"+amount+" OrderStatus :"+orderstatus+" PaymentStatus :"+paystatus;
	}
}
