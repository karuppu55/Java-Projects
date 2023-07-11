package LibraryManagement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
class AddBooksControler
{
	public int getId(String str,String table) throws SQLException
	{
		ResultSet rs=SingletonStatement.getStatement().executeQuery("SELECT id FROM "+table+" WHERE name='"+str+"';");
		if(rs.next())
			return rs.getInt(1);
		else
		{
			ResultSet rs1=SingletonStatement.getStatement().executeQuery("INSERT INTO "+table+"(name) VALUES('"+str+"') RETURNING id;");
			if(rs1.next())
				return rs1.getInt("id");
		}
		return 0;
	}
	public int addBook(BookDetailModel dm) throws SQLException
	{
		int bookid=dm.getBookID();
		int authorid=dm.getAuthorID();
		int categoryid=dm.getCategoryID();
		int count=dm.getCount();
		ResultSet rs=SingletonStatement.getStatement().executeQuery("SELECT id,count FROM bookdetails WHERE bookid="+bookid+" AND authorid="+authorid+" AND categoryid="+categoryid+";");
		if(rs.next())
		{
			int id=rs.getInt(1);
			count+=rs.getInt(2);
			int rowcount=SingletonStatement.getStatement().executeUpdate("UPDATE bookdetails set count="+count+"WHERE id="+id+";");
			return id;
		}
		else
		{
			PreparedStatement ps=Database.getInstance().getConnection().prepareStatement("INSERT INTO bookdetails(bookid,authorid,categoryid,count) VALUES (?,?,?,?)RETURNING id;");
			ps.setInt(1,bookid);
			ps.setInt(2,authorid);
			ps.setInt(3,categoryid);
			ps.setInt(4,count);
			ResultSet rs1=ps.executeQuery();
			if(rs1.next())
				return rs1.getInt("id");
		}
		return 0;
	}
}
