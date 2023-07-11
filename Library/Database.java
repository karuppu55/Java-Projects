package LibraryManagement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
class Database
{
	private static Database db=null;
	private Connection con=null;
	private Database()
	{
		try
		{
		if(con==null)
			con=DriverManager.getConnection("jdbc:postgresql://localhost:5432/library","postgres","1234");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public static Database getInstance()
	{	
		if(db==null)
			db=new Database();
		return db;
	}
	public Connection getConnection()
	{
		return con;
	}
	public void closeConnection()throws SQLException
	{
		if(con!=null)
			con.close();
	}
}
