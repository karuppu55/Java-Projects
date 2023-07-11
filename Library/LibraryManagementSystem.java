package LibraryManagement;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.SQLException;
class LibraryManagementSystem
{
	public void menu() throws IOException
	{
		boolean flag=true;
		while(flag)
		{
		
			try
			{
				System.out.println("=======================================================================================================");
				System.out.println("\n\t\t\tLIBRARY MANAGEMENT SYSTEM");
				System.out.println("=======================================================================================================");
				System.out.print("\n\t\t\t1--->ADD BOOK");
				System.out.print("\n\t\t\t2--->VIEW BOOK"); 
				System.out.print("\n\t\t\t3--->ADD USER");
				System.out.print("\n\t\t\t4--->ADD BORROW");
				System.out.print("\n\t\t\t5--->RETURN BOOK");
				System.out.print("\n\t\t\t6--->ADD RESERVE");
				System.out.print("\n\t\t\t7--->BORROW HISTORY");
				System.out.print("\n\t\t\t8--->EXIT");
				switch(Validation.getInstance().isDigit("Your Choice"))
				{
					case 1:
							new AddBooks().addBook();
						break;
					case 2:
							new ViewBooks().menu();
						break;
					case 3:
							new UsersView().addUser();
						break;
					case 4:
							new BorrowView().addBorrow();
						break;

					case 5:
							new ReturnBook().returnBook();
						break;
					case 6:
							new BookReservation().bookReservation();
							break;
					case 7:
							new BorrowHistory().menu();
						break;
					case 8:
							if(Validation.getInstance().isYesorNo("ARE YOU WANT TO EXIT PRESS (Y) OR (N)	:").toLowerCase().equals("y"))
							{
								Database.getInstance().closeConnection();
								flag=false;
							}
							break;
					default:
						System.out.println("\n\t**ENTER VALID CHOICE");
				}
			}
			catch(DataExistException dee)
			{
				System.out.println(dee.getMessage());
			}
			catch(DatNotFoundException dnf)
			{
				System.out.println(dnf.getMessage());
			}
			catch(SQLException sql)
			{
				sql.printStackTrace();
			}
			catch(IOException io)
			{
				
				io.printStackTrace();
			}
			catch(Exception e)
			{
				System.out.println("io");
				e.printStackTrace();
			}
		}
	}
	public static void main(String[] args)
	{
		try
		{	
			Tables tb=new Tables();
			TableInsertion ti=new TableInsertion();
			ti.insertOrder();
			LibraryManagementSystem lms=new LibraryManagementSystem();
			lms.menu();
		}
		catch(IOException io)
		{
			io.printStackTrace();
		}
		catch(SQLException sql)
		{
			sql.printStackTrace();
		}
	}
}
