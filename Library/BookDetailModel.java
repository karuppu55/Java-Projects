package LibraryManagement;
class BookDetailModel
{
	private int count;
	private int bookid;
	private String bookname;
	private int categoryid;
	private String category;
	private int authorid;
	private String authorname;
	BookDetailModel(String bookname,String category)
	{
	}
	BookDetailModel(int bookid,int categoryid,int authorid,int count)
	{
		this.bookid=bookid;
		this.categoryid=categoryid;
		this.authorid=authorid;
		this.count=count;
	}
	BookDetailModel(String bookname,String authorname,String category,int bookid)
	{
		this.bookid=bookid;
		this.bookname=bookname;
		this.category=category;
		this.authorname=authorname;
	}
	BookDetailModel(String bookname,String authorname,String category)
	{
		this.bookname=bookname;
		this.category=category;
		this.authorname=authorname;
	}
	BookDetailModel(int count,String bookname,String authorname,String category)
	{
		this.bookname=bookname;
		this.category=category;
		this.authorname=authorname;
		this.count=count;
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
	public int getAuthorID()
	{
		return authorid;
	}
	public void setAuthorid(int authoridid)
	{
		this.authorid=authorid;
	}
	public String getAuthorName()
	{
		return authorname;
	}
	public void setAuthorName(String authorname)
	{
		this.authorname=authorname;
	}
	public int getCategoryID()
	{
		return categoryid;
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
	public int getCount()
	{
		return count;
	}
	public void setCount(int count)
	{
		this.count=count;
	}	
}
