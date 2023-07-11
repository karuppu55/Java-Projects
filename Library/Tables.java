package LibraryManagement;
import java.sql.Statement;
import java.sql.SQLException;
class Tables
{
	Tables()
	{
		try
		{
			book();
			author();
			category();
			bookDetail();
			user();
			borrower();
			reservation();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public void book()throws SQLException
	{
		SingletonStatement.getStatement().executeUpdate("CREATE TABLE IF NOT EXISTS books(id SERIAL primary key,name varchar(50));");
	}
	public void author()throws SQLException
	{
		SingletonStatement.getStatement().executeUpdate("CREATE TABLE IF NOT EXISTS authors(id SERIAL primary key,name varchar(50));");
	}
	public void category()throws SQLException
	{
		SingletonStatement.getStatement().executeUpdate("CREATE TABLE IF NOT EXISTS category(id SERIAL primary key,name varchar(50));");
	}
	public void bookDetail()throws SQLException
	{
		SingletonStatement.getStatement().executeUpdate("CREATE TABLE IF NOT EXISTS bookdetails(id SERIAL primary key,bookid int,authorid int,categoryid int,count int,status boolean default true,CONSTRAINT fkbookid FOREIGN KEY(bookid) REFERENCES books(id),CONSTRAINT fkauthorid FOREIGN KEY(authorid) REFERENCES authors(id),CONSTRAINT fkcategoryid FOREIGN KEY(categoryid) REFERENCES category(id));");
	}
	public void user()throws SQLException
	{
		SingletonStatement.getStatement().executeUpdate("CREATE TABLE IF NOT EXISTS users(id SERIAL primary key,name varchar(30),age int,gender varchar(10),phoneno bigInt,address varchar(50),status boolean default true);");
	}
	public void borrower()throws SQLException
	{
		SingletonStatement.getStatement().executeUpdate("CREATE TABLE IF NOT EXISTS borrower(id SERIAL primary key,userid int,bookid int,borrowdate date,returndate date,status boolean default true, CONSTRAINT fkbookid FOREIGN KEY(bookid) REFERENCES books(id),CONSTRAINT fkuserid FOREIGN KEY(userid) REFERENCES users(id));");
	}
	public void reservation()throws SQLException
	{
		SingletonStatement.getStatement().executeUpdate("CREATE TABLE IF NOT EXISTS Reservation(id SERIAL PRIMARY KEY,bookid int,borrowid int,status varchar(15) default 'pending',CONSTRAINT fk_bookid FOREIGN KEY(bookid) REFERENCES books(id),CONSTRAINT fk_borrowid FOREIGN KEY(borrowid) REFERENCES users(id));;");
	}
}
