package LibraryManagement;
import java.sql.PreparedStatement;
import java.io.*;
import java.sql.SQLException;
import java.util.*;
import java.sql.ResultSet;
class BorrowHistory
{
    HashMap<Integer,BorrowModel> borrow=null;
    final static boolean BORROW_STATUS=true;
    int userid;
    int count;
    BorrowHistory()
    {
        System.out.println("=======================================================================================================");
        System.out.println("\n\t\t\tBORRW HISTORY");
        System.out.println("=======================================================================================================");
        this.borrow=new HashMap<>();
        try
        {
            userid=Validation.getInstance().isDigit("USER ID"); 
            getDetail(userid);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public void menu()
    {
        boolean flag=true;
		while(flag)
		{
			try
			{
                System.out.print("\n\t\t\t1--->FULL HISTORY");
				System.out.print("\n\t\t\t2--->BOOKS ON HAND"); 
				System.out.print("\n\t\t\t3--->EXIT");
				switch(Validation.getInstance().isDigit("Your Choice"))
				{
					case 1:
							getHistory();
						break;
					case 2:
							getBookOnHand();
						break;
					case 3:
							flag=false;
							break;
					default:
						System.out.println("\n\t**ENTER VALID CHOICE");
				}
			}
			catch(Exception e)
			{
				System.out.println(e.getMessage());
			}
		}
    }
    public void getHistory()throws IOException
    {
        System.out.println("===========================================================================================================================");
        System.out.println("\n\t\t\t\t\tBORROW HISTORY");
        System.out.println("===========================================================================================================================");
       // System.out.println("\n\t\tBORROWER NAME :"+borrow.get().um.getName());
        System.out.println("\n___________________________________________________________________________________________________________________________");
        System.out.println(String.format("|%-20s|%-45s|%-20s|%-20s","BORROW ID","BOOK NAME","AUTHOR NAME","CATEGORY TYPE"));
        System.out.println("\n___________________________________________________________________________________________________________________________");
        count=0;       
        for(Map.Entry<Integer,BorrowModel> br:borrow.entrySet())
        {
                count++;
                System.out.println(String.format("|%-20s|%-45s|%-20s|%-20s|",br.getKey(),br.getValue().bdm.getBookName(),br.getValue().bdm.getAuthorName(),br.getValue().bdm.getCategory()));   
        }
        if(count==0)
            System.out.println("\n\tNO RECORD FOUND!!!");
        System.out.println("\n___________________________________________________________________________________________________________________________");
    }
    public void getBookOnHand()throws IOException
    {
        System.out.println("===========================================================================================================================");
        System.out.println("\n\t\t\t\t\tVIEW BY STATUS");
        System.out.println("===========================================================================================================================");
       // System.out.println("\n\t\tBORROWER NAME :"+borrow.get(userid).um.getName());
        System.out.println("\n___________________________________________________________________________________________________________________________");
        System.out.println(String.format("|%-20s|%-45s|%-20s|%-20s","BORROW ID","BOOK NAME","AUTHOR NAME","CATEGORY TYPE"));
        System.out.println("\n___________________________________________________________________________________________________________________________");
        count=0;       
        for(Map.Entry<Integer,BorrowModel> br:borrow.entrySet())
        {
            if(br.getValue().getStatus()==BORROW_STATUS)
            {
                count++;
                System.out.println(String.format("|%-20s|%-45s|%-20s|%-20s|",br.getKey(),br.getValue().bdm.getBookName(),br.getValue().bdm.getAuthorName(),br.getValue().bdm.getCategory()));   
            }
        }
        if(count==0)
            System.out.println("\n\tNO RECORD FOUND!!!");
        System.out.println("\n___________________________________________________________________________________________________________________________");
    }
    public void getDetail(int id)throws SQLException
    {
        ResultSet rs=SingletonStatement.getStatement().executeQuery("SELECT br.id,u.name,b.name,a.name,c.name,br.status FROM borrower br JOIN users u ON  br.userid=u.id JOIN bookdetails bd ON br.bookid=bd.id JOIN books b ON bd.bookid=b.id JOIN authors a ON a.id=bd.authorid JOIN category c ON  bd.categoryid=c.id where br.userid="+id+";");
        while(rs.next())
            borrow.put(rs.getInt(1),new BorrowModel(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getBoolean(6)));
    }
}