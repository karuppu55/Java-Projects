package LibraryManagement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.io.*;
class ReturnBook
{
    public void returnBook()throws DatNotFoundException,SQLException,IOException
    {
        System.out.println("=======================================================================================================");
        System.out.println("\n\t\t\tRETURN BOOK");
        System.out.println("=======================================================================================================");
            
        int borrowid=Validation.getInstance().isDigit("BORROW ID");
        BorrowModel bm=isExits(borrowid);
        if(bm==null)
        {
            throw new DatNotFoundException("BORROW ID NOT FOUND");
        }
        System.out.println("\n\tBORROWER NAME   :"+bm.um.getName()+"\n\tBOOK NAME   :"+bm.bdm.getBookName()+"\n\tAUTHOR NAME    :"+bm.bdm.getAuthorName()+"\n\tCATEGORY  :"+bm.bdm.getCategory());
        if(Validation.getInstance().isYesorNo("CHECK ABOVE DETAIL AND CLICK (Y) TO CONTINUE").toLowerCase().equals("y"))
		{
            if(acceptReturn(bm))
            {
                System.out.println("\n\tBOOK RETURNED SUCCUSSFULLY");
            }
        }
    }
    public BorrowModel isExits(int id)throws SQLException
    {
        ResultSet rs=SingletonStatement.getStatement().executeQuery("SELECT br.id,u.name,b.name,a.name,c.name,br.bookid FROM borrower br JOIN users u ON  br.userid=u.id JOIN bookdetails bd ON br.bookid=bd.id JOIN books b ON bd.bookid=b.id JOIN authors a ON a.id=bd.authorid JOIN category c ON  bd.categoryid=c.id where br.id="+id+" AND br.status=true;");
        if(rs.next())
            return new BorrowModel(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getInt(6));
        return null;  
    }
    public boolean acceptReturn(BorrowModel bm)throws SQLException
    {
        int borrowid=bm.getId();
        int bookid=bm.bdm.getBookID();
        int row=SingletonStatement.getStatement().executeUpdate("UPDATE borrower set status=false Where id="+borrowid+";");
        if(row!=0)
        {
            ResultSet rs1=SingletonStatement.getStatement().executeQuery("SELECT id FROM reservation WHERE bookid="+bookid+" AND status='pending';");
            if(rs1.next())
            {
                int id=rs1.getInt(1);
                int row1=SingletonStatement.getStatement().executeUpdate("UPDATE reservation set status='approve' Where id="+id+";");
            }
            int row2=SingletonStatement.getStatement().executeUpdate("update bookdetails set count=(select count from bookdetails where id="+bookid+")+1 where id="+bookid+";");
            if(row2!=0)
                return true;
        }
        return false;  
    }
}