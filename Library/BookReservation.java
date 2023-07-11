package LibraryManagement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.io.*;
class BookReservation
{
    public void bookReservation()throws IOException,SQLException,DatNotFoundException,DataExistException
    {
        System.out.println("=======================================================================================================");
        System.out.println("\n\t\t\tBOOK RESERVATION");
        System.out.println("=======================================================================================================");
        int borrowerid=Validation.getInstance().isDigit("BORROWER ID");
        UsersModel um=new BorrowView().getBorrowerDetail(borrowerid);
        if(um==null)
            throw new DatNotFoundException("BORROWER NOT REGISTERED");
        System.out.println("\n\tBORROWER NAME   :"+um.getName()+"\n\tAGE    :"+um.getAge()+"\n\tGender  :"+um.getGender()+"\n\tPHONE NO :"+um.getPhoneno());
        int bookid=Validation.getInstance().isDigit("BOOK ID");
        BookDetailModel bdm=getBookDetail(bookid);
        if(bdm==null)
            throw new DatNotFoundException("BOOK NOT FOUND");
        System.out.println("\n\tBOOK NAME   :"+bdm.getBookName()+"\n\tAUTHOR NAME :"+bdm.getAuthorName()+"\n\tCATEGORY  :"+bdm.getCategory());
        if(bdm.getCount()!=0&&checkReserve(bookid)<bdm.getCount())
            throw new DataExistException("BOOK ALREADY IN STACK");
        if(Validation.getInstance().isYesorNo("CHECK ABOVE DETAIL AND CLICK (Y) TO CONTINUE").toLowerCase().equals("y"))
		{
            int id=insertReservation(new ReservationModel(bookid,borrowerid));
            if(id!=0)
            {
                System.out.println("\n\tRESERVATION ADDED SUCCUSSFULLY\n\tRESERVE ID :"+id);
            }
        }
    }
    public int insertReservation(ReservationModel rm)throws SQLException
    {
        PreparedStatement ps=Database.getInstance().getConnection().prepareStatement("INSERT INTO reservation(bookid,borrowid) VALUES (?,?)RETURNING id;");
			ps.setInt(1,rm.getBookId());
			ps.setInt(2,rm.getBorrowerId());
			ResultSet rs1=ps.executeQuery();
			if(rs1.next())
				return rs1.getInt("id");
		return 0;
    }
    public int checkReserve(int bookid)throws SQLException
    {
         ResultSet rs=SingletonStatement.getStatement().executeQuery("SELECT count(id) from reservation where bookid="+bookid+" AND (status='pending' OR status='approve');");
            if(rs.next())
                return rs.getInt(1);
            return 0;
    }
    public BookDetailModel getBookDetail(int bookid) throws SQLException
    {
        ResultSet rs=SingletonStatement.getStatement().executeQuery("SELECT b.name,a.name,c.name,bd.count from bookdetails bd JOIN books b ON b.id=bd.bookid JOIN authors a ON a.id=bd.authorid JOIN category c On c.id=bd.categoryid where bd.id="+bookid+";");
        if(rs.next())
            return new BookDetailModel(rs.getInt(4),rs.getString(1),rs.getString(2),rs.getString(3));
        return null;   
    }
}