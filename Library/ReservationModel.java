package LibraryManagement;
class ReservationModel
{
    private int id;
    private int bookid;
    private int borrowerid;
    private String status;
    UsersModel um=null;
    BookDetailModel bdm=null;
    ReservationModel(int bookid,int borrowerid)
    {
        this.bookid=bookid;
        this.borrowerid=borrowerid;
    }
    ReservationModel(int id,String bookname,String authorname,String category,String username,int userid,String status,int bookid)
    {
        this.id=id;
        bdm=new BookDetailModel(bookname,authorname,category);
        um=new UsersModel(username,userid);
        this.bookid=bookid;
        this.status=status;
    }
     public int getId()
    {
        return id;
    }
    public void setId(int id)
    {
        this.id=id;
    }
    public int getBookId()
    {
        return bookid;
    }
    public void setBookId(int bookid)
    {
        this.bookid=bookid;
    }
    public int getBorrowerId()
    {
        return borrowerid;
    }
    public void setBorrowerId(int borrowerid)
    {
        this.borrowerid=borrowerid;
    }
    public String getStatus()
    {
        return status;
    }
    public void setStatus(String status)
    {
        this.status=status;
    }
}