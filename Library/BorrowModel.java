package LibraryManagement;
class BorrowModel
{
    private int bookid;
    private int userid;
    private int id;
    private String borrowdate;
    private String returndate;
    private boolean status;
    BookDetailModel bdm=null;
    UsersModel um=null;
    BorrowModel(int userid,int bookid)
    {
        this.bookid=bookid;
        this.userid=userid;
    }
     BorrowModel(int id,String username,String bookname,String authorname,String category,int bookid)
    {
        this.id=id;
        bdm=new BookDetailModel(bookname,authorname,category,bookid);
        um=new UsersModel(username);
    }
    BorrowModel(int id,String username,String bookname,String authorname,String category,boolean status)
    {
        this.id=id;
        this.status=status;
        bdm=new BookDetailModel(bookname,authorname,category);
        um=new UsersModel(username);
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
    public int getUserId()
    {
        return userid;
    }
    public void setUserId(int userid)
    {
        this.userid=userid;
    }
    public boolean getStatus()
    {
        return status;
    }
    public void setStatus(boolean status)
    {
        this.status=status;
    }
    public String getBorrowDate()
    {
        return borrowdate;
    }
    public void setBorrowDate(String borrowdate)
    {
        this.borrowdate=borrowdate;
    }
     public String getReturnDate()
    {
        return returndate;
    }
    public void setReturnDate(String returndate)
    {
        this.returndate=returndate;
    }
}
