import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
public class FoodDelivery implements DeliveryApp
{
	HashMap<Integer,Restaurant> restaurent;
	HashMap<Integer,Customer> customer;
	Scanner s=new Scanner(System.in);
	FoodDelivery()
	{
		customer=new HashMap<>();
		restaurent=new HashMap<>();
	}
	public void menu()
	{
			System.out.println("\n\t\t\tFOOD DELIVERY APP");
			while(true)
			{
				System.out.println("\n\t\t\t1.RESTAURENT\n\t\t\t2.CUSTOMER\n\t\t\t3.ADD RESTAURENT\n\t\t\t4.ADD CUSTOMER\n\t\t\t5.EXIT");
				outer:
				switch(Integer.parseInt(s.nextLine()))
				{
					case 1:
						System.out.print("\nEnter Restaurent Id:");
						int id=Integer.parseInt(s.nextLine());
						int count=0;
						while(!restaurent.containsKey(id))
						{
							if(count==3)
							{
								System.out.println("\nTry Again!!");
								break outer;
							}
							count++;
							System.out.print("\nINVALID ID \nENTER ID AGAIN:");
							id=Integer.parseInt(s.nextLine());
						}
						System.out.println("\nWELCOME:");
						Restaurant rs=restaurent.get(id);
						restaurentmenu(rs,id);
						
						break;
					case 2:
						System.out.print("\nEnter Customer Id:");
						id=Integer.parseInt(s.nextLine());
						count=0;
						while(!customer.containsKey(id))
						{
							if(count==3)
							{
								System.out.println("\nTry Again!!");
								break outer;
							}
							count++;
							System.out.print("\nINVALID ID \nENTER ID AGAIN:");
							id=Integer.parseInt(s.nextLine());
						}
							System.out.println("\nWELCOME:");
							Customer cs=customer.get(id);
							customermenu(cs,id);
						
						break;
					case 3:
						addrestaurent();
						break;
					case 4:
						addCustomer();
						break;
					case 5:
							return;
	
					default:
						System.out.println("\nEnter COrrect option!");
				}
			}
	}
	public void restaurentmenu(Restaurant rs,int resid)
	{
		System.out.println("\n\t\t\tRESTAURENT");
			while(true)
			{
				System.out.println("\n\t\t\t1.ADD MENUS\n\t\t\t2.VIEW MENU\n\t\t\t2.VIEW ORDERS\n\t\t\t4.MANAGE ORDERS\n\t\t\t5.EXIT");
				outer:
				switch(Integer.parseInt(s.nextLine()))
				{
					case 1:
						System.out.print("\nEnter Menu name:");
						String name=s.nextLine();
						System.out.print("\nEnter menu Type:");
						String type=s.nextLine();
						System.out.print("\nEnter Price Amount:");
						double price=Double.parseDouble(s.nextLine());
						rs.setMenu(name,type,price);
						System.out.println("\nMenu Added!");
						break;
					case 2:
						for(Map.Entry<Integer,Menu> menu:rs.getMenus().entrySet())
						{
							System.out.println("ID :"+menu.getKey()+" "+menu.getValue());
						}
						break;
					case 3:
						ArrayList<Integer> pendingorder=rs.getPendingOrders();
						if(pendingorder.size()==0)
						{
							System.out.println("\nNo Order Found");
							break outer;
						}
						ArrayList<Orders> orders=rs.getOrders();
						for(int i=0;i<pendingorder.size();i++)
						{
								System.out.println(orders.get(i));
								ArrayList<Integer> items=orders.get(i).getOrderDetail();
								for(int j=0;j<items.size();j++)
								{
									System.out.println(rs.getMenus().get(items.get(i)));
								}
						}
						break;
					case 4:
						System.out.println("\nEnter Order Id :");
						int id=Integer.parseInt(s.nextLine());
						pendingorder=rs.getPendingOrders();
						int count=0;
						while(!pendingorder.contains(id-1))
						{
							if(count==3)
							{
								System.out.println("\nSorry Bucy!!:");
								break outer;
							}
							System.out.println("\nEnter Correct Order Id :");
							id=Integer.parseInt(s.nextLine());
							count++;
						}
						orders=rs.getOrders();
						orders.get(id).setOrderstatus(true);
						pendingorder.remove(pendingorder.indexOf(id));
						System.out.println("\nORDER COMPLETED");
						break;
					case 5:
							return;
		
					default:
						System.out.println("\nEnter COrrect option!");
				}
			}
	}
	public void customermenu(Customer cs,int cusid)
	{
		System.out.println("\n\t\t\tCUSTOMER");
			while(true)
			{
				System.out.println("\n\t\t\t1.VIEW HOTEL\n\t\t\t2.PLACE ORDER\n\t\t\t3.VIEW ORDER\n\t\t\t4.EXIT");
				outer:
				switch(Integer.parseInt(s.nextLine()))
				{
					case 1:
						for(Map.Entry<Integer,Restaurant> rest:restaurent.entrySet())
						{
							System.out.println("Id :"+rest.getKey()+" "+rest.getValue());
						}
						break;
					case 2:
						System.out.println("Enter Hotel Id");
						int hotelid=Integer.parseInt(s.nextLine());
						int count=0;
						while(!restaurent.containsKey(hotelid))
						{
							if(count==3)
							{
								System.out.println("SORRY TRY AGAIN!!");
								break outer;
							}
							hotelid=Integer.parseInt(s.nextLine());
							count++;
						}
						HashMap<Integer,Menu> menus=restaurent.get(hotelid).getMenus();
						if(menus.size()==0)
						{
							System.out.println("\nNo Menus Found in the Hotel");
							break outer;
						}
						for(Map.Entry<Integer,Menu> menu:menus.entrySet())
						{
							System.out.println("Id :"+menu.getKey()+" "+menu.getValue());
						}
						System.out.println("If you Want to order Press Y Otherwise N");
						if(s.nextLine().toLowerCase().equals("y"))
						{
							double amount=0;
							ArrayList<Integer> arr=new ArrayList<>();
							int id=0;
							do
							{	    
								System.out.println("Enter Menu Id");
								id=Integer.parseInt(s.nextLine());
								while(!menus.containsKey(id))
								{
									System.out.println("Enter Correct Menu Id");
									id=Integer.parseInt(s.nextLine());
								}
								arr.add(id);
								amount+=menus.get(id).getPrice();
							System.out.println("Add Another Food Press Y otherwise N ");
						}while(s.nextLine().toLowerCase().equals("y"));
						System.out.println("AMOUNT "+amount);
						Orders order=new Orders(id,cusid,hotelid,amount,false,false);
						restaurent.get(hotelid).setOrder(order,arr);
						cs.setOrder(order,arr);
						}
						break;
					case 3:
						ArrayList<Orders> orders=cs.getOrders();
						if(orders.size()==0)
						{
							System.out.println("\nNo Order Found");
							break outer;
						}
						for(int i=0;i<orders.size();i++)
						{
							System.out.println(orders.get(i));
							ArrayList<Integer> menu=orders.get(i).getOrderDetail();
							for(int j=0;j<menu.size();j++)
							{
								System.out.println(restaurent.get(orders.get(i).getHotelId()).getMenus().get(menu.get(i)));
							}
						}
						break;
					case 4:
							return;
					
					default:
						System.out.println("\nEnter COrrect option!");
				}
		}
	}
	public void addCustomer()
	{
		System.out.print("\nEnter Customer Name:");
		String name=s.nextLine();
		System.out.print("\nEnter Door No:");
		int doorno=Integer.parseInt(s.nextLine());
		System.out.print("\nEnter street Name:");
		String street=s.nextLine();
		System.out.print("\nEnter city Name:");
		String city=s.nextLine();
		System.out.print("\nEnter district Name:");
		String district=s.nextLine();
		System.out.print("\nEnter state Name:");
		String state=s.nextLine();
		customer.put(customer.size()+1,new Customer(name,doorno,street,city,district,state));
		System.out.println("\nCustomer Added Succussfully!!\nCustomer ID :"+customer.size());
	}
	public void addrestaurent()
	{
		System.out.print("\nEnter Hotel Name:");
		String name=s.nextLine();
		System.out.print("\nEnter Hotel Type:");
		String type=s.nextLine();
		System.out.print("\nEnter Door No:");
		int doorno=Integer.parseInt(s.nextLine());
		System.out.print("\nEnter street Name:");
		String street=s.nextLine();
		System.out.print("\nEnter city Name:");
		String city=s.nextLine();
		System.out.print("\nEnter district Name:");
		String district=s.nextLine();
		System.out.print("\nEnter state Name:");
		String state=s.nextLine();
		restaurent.put(restaurent.size()+1,new Restaurant(name,type,doorno,street,city,district,state));
		System.out.println("\nRestaurent Added Succussfully!!\n Restaurant ID :"+restaurent.size());
	}
	public static void main(String[] ar)
	{
		new FoodDelivery().menu();
	}
}