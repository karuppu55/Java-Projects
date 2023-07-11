package LibraryManagement;
import java.sql.SQLException;
import java.io.*;
class AddBooks
{
	public void addBook()throws DataExistException,SQLException,IOException
	{
		System.out.println("=======================================================================================================");
        System.out.println("\n\t\t\t\t\tADD BOOKS ");
        System.out.println("=======================================================================================================");
		String bookname=Validation.getInstance().isAlphaNumeric("BOOK NAME");
		String category=Validation.getInstance().isLetter("CATEGORY");
		String author=Validation.getInstance().isAlphaNumeric("AUTHOR NAME");
		if(Validation.getInstance().isYesorNo("IF ANY CHANGES PRESS (Y OR (N)	:").toLowerCase().equals("y"))
		{
			System.out.println("SELECT WHAT YOU CHANGE");
			System.out.println("\n\t\t\t1.BOOKNAME\n\t\t\t2.CATEGORY\n\t\t\t3.AUTHUR");
			int choice=Validation.getInstance().isDigit("CHOICE");
			if(choice==1)
			{
				bookname=Validation.getInstance().isAlphaNumeric("BOOK NAME");
			}
			if(choice==2)
			{
				category=Validation.getInstance().isLetter("CATEGORY");
			}
			if(choice==3)
			{
				author=Validation.getInstance().isAlphaNumeric("AUTHOR NAME");
			}
		}
		int bookid=new AddBooksControler().getId(bookname,"books");
		int categoryid=new AddBooksControler().getId(category,"category");
		int authorid=new AddBooksControler().getId(author,"authors");
		int count=Validation.getInstance().isDigit("BOOK COUNT");
		int id=new AddBooksControler().addBook(new BookDetailModel(bookid,categoryid,authorid,count));
		if(id!=0)
		{
			System.out.println("\nBOOK ADDED SUCCUSSFULLY \nBOOK ID	:"+id);
		}
	}
}
