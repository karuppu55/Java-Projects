package LibraryManagement;
class BookModel
{
	private int bookid;
	private String bookname;
	private int categoryid;
	private String category;
	BookModel(String bookname,String category)
	{
		this.bookname=bookname;
		this.category=category;
	}
	BookModel(int bookid,int categoryid)
	{
		this.bookid=bookid;
		this.categoryid=categoryid;
	}
	public int getBookID()
	{
		return bookid;
	}
	public void setBookid(int bookid)
	{
		this.bookid=bookid;
	}
	public String getBookName()
	{
		return bookname;
	}
	public void setBookName(String bookname)
	{
		this.bookname=bookname;
	}
	public int getCategoryID()
	{
		return bookid;
	}
	public void setCategoryid(int bookid)
	{
		this.categoryid=categoryid;
	}
	public String getCategory()
	{
		return category;
	}
	public void setCategory(String category)
	{
		this.category=category;
	}
}
