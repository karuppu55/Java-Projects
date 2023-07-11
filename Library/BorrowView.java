package LibraryManagement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.io.*;
class BorrowView
{
    public void addBorrow()throws DatNotFoundException,SQLException,IOException
    {
        System.out.println("=======================================================================================================");
        System.out.println("\n\t\t\t\t\tBORROW BOOKS ");
        System.out.println("=======================================================================================================");
        System.out.println("\n\t\t\t1--->NEW \n\t\t\t2---->RESERVED");
        int choice=Validation.getInstance().isDigit("YOUR CHOICE");
        if(choice==1)
        {
            System.out.println("=======================================================================================================");
            System.out.println("\n\t\t\t\t\tNEW BORROW ");
            System.out.println("=======================================================================================================");
            int bookid=Validation.getInstance().isDigit("BOOK ID");
            BookDetailModel bdm=getBookDetail(bookid);
            if(bdm==null)
                throw new DatNotFoundException("BOOK NOT FOUND");
            System.out.println("\n\tBOOK NAME   :"+bdm.getBookName()+"\n\tAUTHOR NAME :"+bdm.getAuthorName()+"\n\tCATEGORY  :"+bdm.getCategory());
            if(bdm.getCount()==0||bdm.getCount()<=new BookReservation().checkReserve(bookid))
            {
                throw new DatNotFoundException("**PLEASE RESERVE YOUR BOOK");
            }
            int borrowerid=Validation.getInstance().isDigit("BORROWER ID");
            UsersModel um=getBorrowerDetail(borrowerid);
            if(um==null)
                throw new DatNotFoundException("BORROWER NOT REGISTERED");
            System.out.println("\n\tBORROWER NAME   :"+um.getName()+"\n\tAGE    :"+um.getAge()+"\n\tGender  :"+um.getGender()+"\n\tPHONE NO :"+um.getPhoneno());
            if(Validation.getInstance().isYesorNo("CHECK ABOVE DETAIL AND CLICK (Y) TO CONTINUE").toLowerCase().equals("y"))
            {
                if(!checkBorrower(borrowerid))
                {
                    int id=addBorrow(new BorrowModel(borrowerid,bookid));
                    if(id!=0&&(updateBookCount(bookid)!=0))
                        System.out.println("\n\tBORROW ID   :"+id);
                }
                else
                {
                    System.out.println("*YOU ALREADY HAVE A MAXIMUM OF BOOKS");
                }
            }
        }
        else if(choice==2)
        {
            System.out.println("=======================================================================================================");
            System.out.println("\n\t\t\tRESERVED BOOK");
            System.out.println("=======================================================================================================");
            int reserveid=Validation.getInstance().isDigit("RESERVED ID");
            ReservationModel rm=getReservedDetail(reserveid);
            if(rm==null)
                 throw new DatNotFoundException("RESERVATION ID NOT FOUND");
            System.out.println("\n\tBOOK NAME  :"+rm.bdm.getBookName());
            System.out.println("\n\tAUTHUR NAME    :"+rm.bdm.getAuthorName());
            System.out.println("\n\tCATEGORY   :"+rm.bdm.getCategory());
            System.out.println("\n\tBORROWER NAME  :"+rm.um.getName());
            System.out.println("\n\tBORROWER ID    :"+rm.um.getId());
            System.out.println("\n\tRESEVED STATUS :"+rm.getStatus());
            if(rm.getStatus().equals("deliverd"))
            {

            }
            if(rm.getStatus().equals("pending"))
            {
            
            }
            else
            {
                if(Validation.getInstance().isYesorNo("CHECK ABOVE DETAIL AND CLICK (Y) TO CONTINUE").toLowerCase().equals("y"))
                {
                    int id=addBorrow(new BorrowModel(rm.um.getId(),rm.getBookId()));
                    if(updateBookCount(rm.getBookId())!=0&&updateReservationstatus(rm.getId())!=0&&id!=0)
                    {
                        System.out.println("*RESEVATION PROCESS COMPLETED");
                        System.out.println("\n\tBORROW ID   :"+id);    
                    }
                }
            }
        }
    }
    public boolean checkBorrower(int borrowerid)throws SQLException
    {
        ResultSet rs=SingletonStatement.getStatement().executeQuery("SELECT count(id) from borrower where userid="+borrowerid+"AND status=true;");
        return (rs.next()&&rs.getInt(1)>=5);
    }
    public BookDetailModel getBookDetail(int bookid) throws SQLException
     {
        ResultSet rs=SingletonStatement.getStatement().executeQuery("SELECT b.name,a.name,c.name,bd.count from bookdetails bd JOIN books b ON b.id=bd.bookid JOIN authors a ON a.id=bd.authorid JOIN category c On c.id=bd.categoryid where bd.id="+bookid+";");
        if(rs.next())
            return new BookDetailModel(rs.getInt(4),rs.getString(1),rs.getString(2),rs.getString(3));
        return null;     
    }
    public UsersModel getBorrowerDetail(int borrowerid)throws SQLException
    {
        ResultSet rs=SingletonStatement.getStatement().executeQuery("select name,age,gender,phoneno from users where id="+borrowerid+";");   
        if(rs.next())
            return new UsersModel(rs.getString(1),rs.getInt(2),rs.getString(3),rs.getLong(4));
        return null;
    }
    public int addBorrow(BorrowModel bm)throws SQLException
    {
       PreparedStatement ps=Database.getInstance().getConnection().prepareStatement("INSERT INTO borrower(userid,bookid,borrowdate,returndate) VALUES (?,?,?,?)RETURNING id;");
			ps.setInt(1,bm.getUserId());
			ps.setInt(2,bm.getBookId());
			ps.setDate(3,new java.sql.Date(new java.util.Date().getTime()));
			ps.setDate(4,java.sql.Date.valueOf(java.time.LocalDate.now().plusDays(30)));
			ResultSet rs1=ps.executeQuery();
			if(rs1.next())
				return rs1.getInt("id");
		return 0;
    }
    public ReservationModel getReservedDetail(int reserveid)throws SQLException
    {
        ResultSet rs=SingletonStatement.getStatement().executeQuery("SELECT r.id,b.name,c.name,a.name,u.name,u.id,r.status,bd.id from reservation r JOIN bookdetails bd ON r.bookid=bd.id JOIN books b ON b.id=bd.bookid JOIN authors a ON a.id=bd.authorid JOIN category c ON c.id=bd.categoryid JOIN users u ON u.id=r.borrowid WHERE r.id="+reserveid+";");
        if(rs.next())
            return new ReservationModel(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getInt(6),rs.getString(7),rs.getInt(8));
        return null;
    }
    public int updateReservationstatus(int reserveid)throws SQLException
    {
        return SingletonStatement.getStatement().executeUpdate("update reservation set status='delivered' where id="+reserveid+";");
    }
    public int updateBookCount(int bookid)throws SQLException
    {
        return SingletonStatement.getStatement().executeUpdate("update bookdetails set count=count-1 where id="+bookid+";");
    }
}
