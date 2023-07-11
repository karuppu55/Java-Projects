package LibraryManagement;
import java.sql.PreparedStatement;
import java.io.*;
import java.sql.SQLException;
import java.util.*;
import java.sql.ResultSet;
class ViewBooks
{
    HashMap<Integer,BookDetailModel> books=null;
    int count;
    ViewBooks()
    {
        try
        {
            if(books==null)
            {
                books=new HashMap<>();
                getBooks();
            }
        }
        catch(SQLException sql)
        {
            System.out.println(sql.getMessage());
        }
    }
    public void menu()
    {
        boolean flag=true;
		while(flag)
		{
		
			try
			{
				System.out.println("=======================================================================================================");
				System.out.println("\n\t\t\tLIBRARY MANAGEMENT SYSTEM");
				System.out.println("=======================================================================================================");
				System.out.print("\n\t\t\t1--->ALL BOOK");
				System.out.print("\n\t\t\t2--->VIEW BY BOOK NAME"); 
				System.out.print("\n\t\t\t3--->VIEW BY CATGORY");
				System.out.print("\n\t\t\t4--->VIEW BY AUTHOR");
				System.out.print("\n\t\t\t5--->EXIT");
				switch(Validation.getInstance().isDigit("Your Choice"))
				{
					case 1:
							viewBooks();
						break;
					case 2:
							getByBook();
						break;
					case 3:
							getByCategory();
						break;
					case 4:
						    getByAuthor();
						break;
					case 5:
							flag=false;
							break;
					default:
						System.out.println("\n\t**ENTER VALID CHOICE");
				}
			}
			catch(Exception e)
			{
				System.out.println(e.getMessage());
			}
		}
    }
    public void viewBooks()
    {
        System.out.println("===========================================================================================================================");
        System.out.println("\n\t\t\t\t\tBOOK DETAILS");
        System.out.println("===========================================================================================================================");
        System.out.println(String.format("|%-20s|%-45s|%-20s|%-20s|%-20s","BOOK ID","BOOK NAME","AUTHOR NAME","CATEGORY TYPE","BOOK COUNT"));
        System.out.println("\n___________________________________________________________________________________________________________________________");
        count=0;
        for(Map.Entry<Integer,BookDetailModel> book:books.entrySet())
        {
            count++;
            System.out.println(String.format("|%-20s|%-45s|%-20s|%-20s|%-20s",book.getKey(),book.getValue().getBookName(),book.getValue().getAuthorName(),book.getValue().getCategory(),book.getValue().getCount()));
        }
        if(count==0)
            System.out.println("\n\tNO RECORD FOUND!!!");    
        System.out.println("\n___________________________________________________________________________________________________________________________");
    }
    public void getByBook()throws IOException
    {
        System.out.println("===========================================================================================================================");
        System.out.println("\n\t\t\t\t\tVIEW BY BOOK NAME");
        System.out.println("===========================================================================================================================");
        String bookname=Validation.getInstance().isAlphaNumeric("BOOK NAME");
        System.out.println("\n___________________________________________________________________________________________________________________________");
        System.out.println(String.format("|%-20s|%-45s|%-20s|%-20s|%-20s","BOOK ID","BOOK NAME","AUTHOR NAME","CATEGORY TYPE","BOOK COUNT"));
        System.out.println("\n___________________________________________________________________________________________________________________________");
        count=0;
        for(Map.Entry<Integer,BookDetailModel> book:books.entrySet())
        {
            if(book.getValue().getBookName().toLowerCase().equals(bookname.toLowerCase()))
            {
                count++;
                System.out.println(String.format("|%-20s|%-45s|%-20s|%-20s|%-20s",book.getKey(),book.getValue().getBookName(),book.getValue().getAuthorName(),book.getValue().getCategory(),book.getValue().getCount()));
            }
        }
        if(count==0)
            System.out.println("\n\tNO RECORD FOUND!!!");
        System.out.println("\n___________________________________________________________________________________________________________________________");
    }
    public void getByCategory()throws IOException
    {
        System.out.println("===========================================================================================================================");
        System.out.println("\n\t\t\t\t\tVIEW BY CATEGORY");
        System.out.println("===========================================================================================================================");
        String category=Validation.getInstance().isLetter("Category");
        System.out.println(String.format("|%-20s|%-45s|%-20s|%-20s|%-20s","BOOK ID","BOOK NAME","AUTHOR NAME","CATEGORY TYPE","BOOK COUNT"));
        System.out.println("\n___________________________________________________________________________________________________________________________");
        count=0;
        for(Map.Entry<Integer,BookDetailModel> book:books.entrySet())
        {
            if(book.getValue().getCategory().toLowerCase().equals(category.toLowerCase()))
            {
                count++;
                System.out.println(String.format("|%-20s|%-45s|%-20s|%-20s|%-20s",book.getKey(),book.getValue().getBookName(),book.getValue().getAuthorName(),book.getValue().getCategory(),book.getValue().getCount()));
            }
        }
        if(count==0)
            System.out.println("\n\tNO RECORD FOUND!!!");
        System.out.println("\n___________________________________________________________________________________________________________________________");
    }
    public void getByAuthor()throws IOException
    {
        System.out.println("===========================================================================================================================");
        System.out.println("\n\t\t\t\t\tVIEW BY AUTHUR");
        System.out.println("===========================================================================================================================");
        System.out.println("\n___________________________________________________________________________________________________________________________");
        String author=Validation.getInstance().isAlphaNumeric("AUTHUR NAME");
        System.out.println("\n___________________________________________________________________________________________________________________________");
        System.out.println(String.format("|%-20s|%-45s|%-20s|%-20s|%-20s","BOOK ID","BOOK NAME","AUTHOR NAME","CATEGORY TYPE","BOOK COUNT"));
        System.out.println("\n___________________________________________________________________________________________________________________________");
        count=0;       
        for(Map.Entry<Integer,BookDetailModel> book:books.entrySet())
        {
            if(book.getValue().getAuthorName().toLowerCase().equals(author.toLowerCase()))
            {
                count++;
                System.out.println(String.format("|%-20s|%-45s|%-20s|%-20s|%-20s",book.getKey(),book.getValue().getBookName(),book.getValue().getAuthorName(),book.getValue().getCategory(),book.getValue().getCount()));   
            }
        }
        if(count==0)
            System.out.println("\n\tNO RECORD FOUND!!!");
        System.out.println("\n___________________________________________________________________________________________________________________________");
    }
    public void getBooks()throws SQLException
    {
        ResultSet rs=SingletonStatement.getStatement().executeQuery("SELECT bd.id,b.name,a.name,c.name,bd.count from bookdetails bd JOIN books b ON b.id=bd.bookid JOIN authors a ON a.id=bd.authorid JOIN category c On c.id=bd.categoryid order by bd.id;");
        while(rs.next())
            books.put(rs.getInt(1),new BookDetailModel(rs.getInt(5),rs.getString(2),rs.getString(3),rs.getString(4)));
    }
}