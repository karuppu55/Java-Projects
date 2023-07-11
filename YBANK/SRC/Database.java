package YBank;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
final class Database
{
	private static Database db=null;
	private FileReader file=null;
	private Properties data=new Properties(); 
	private Connection con=null;
	private String url=null;
	private String user=null;
	private String pass=null;
	private Database() throws SQLException,IOException
	{
		if(con==null)
		{
			file=new FileReader("db.properties");
			data.load(file);
			url=data.getProperty("url");
			user=data.getProperty("user");
			pass=data.getProperty("pass");
			con=DriverManager.getConnection(url,user,pass);
		}
	}
	public static Database getDatabaseInstance() throws SQLException,IOException
	{
		if(db==null||db.getConnection().isClosed())
			db=new Database();
		return db;
	}
	public  Connection getConnection()
	{
		return con;
	}
	public  void closeConnection()  throws SQLException
	{
		if(con!=null)
		{		
			con.close();
		}
	}	
}
