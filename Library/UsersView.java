package LibraryManagement;
import java.sql.PreparedStatement;
import java.io.*;
import java.sql.SQLException;
import java.sql.ResultSet;
class UsersView
{
    public void addUser()throws IOException,SQLException,DataExistException
    {   
        System.out.println("=======================================================================================================");
        System.out.println("\n\t\t\tADD USERS");
        System.out.println("=======================================================================================================");
        String name=Validation.getInstance().isName("NAME");
        int age=Validation.getInstance().isDigit("AGE");
        String gender=Validation.getInstance().isLetter("GENDER");
        long phoneno=Validation.getInstance().isMobile("MOBILE");
        if(isExist(phoneno))
        {
            throw new DataExistException("\n*MOBILE NUMBER ALREADY EXISTS");
        }
        System.out.print("\n\tEnter ADDRESS :");
        String address=new BufferedReader((new InputStreamReader(System.in))).readLine();
        if(Validation.getInstance().isYesorNo("IF ANY CHANGES PRESS (Y OR (N)	:").toLowerCase().equals("y"))
        {
            while(true)
            {
                System.out.println("SELECT WHAT YOU CHANGE");
                System.out.println("\n\t\t\t1.NAME\n\t\t\t2.AGE\n\t\t\t3.GENDER\n\t\t\t4.MOBILE\n\t\t\t5.ADDRESS\n\t\t\t6.EXIT");
                int choice=Validation.getInstance().isDigit("CHOICE");
                if(choice==1)
                {
                name=Validation.getInstance().isName("NAME");
                }
                if(choice==2)
                {
                    age=Validation.getInstance().isDigit("AGE");
                }
                if(choice==3)
                {
                gender=Validation.getInstance().isLetter("GENDER");
                }
                if(choice==4)
                {
                    phoneno=Validation.getInstance().isMobile("MOBILE");
                }
                if(choice==5)
                {
                    address=new BufferedReader((new InputStreamReader(System.in))).readLine();
                }
                if(choice==6)
                {
                    break;
                }
                }
        }
        int id=insertUserData(new UsersModel(name,age,gender,phoneno,address));
        if(id!=0)
        {
            System.out.println("\nUSER ADDED\nUSER ID :"+id);
        }
    }
    public boolean isExist(long phoneno)throws SQLException
    {
        ResultSet rs=SingletonStatement.getStatement().executeQuery("SELECT id FROM users WHERE phoneno="+phoneno+";");
        if(rs.next())
            return true;
        return false;
    }
    public int insertUserData(UsersModel us)throws SQLException
    {
        PreparedStatement ps=Database.getInstance().getConnection().prepareStatement("INSERT INTO users(name,age,gender,phoneno,address) VALUES (?,?,?,?,?)RETURNING id");
        ps.setString(1,us.getName());
        ps.setInt(2,us.getAge());
        ps.setString(3,us.getGender());
        ps.setLong(4,us.getPhoneno());
        ps.setString(5,us.getAddress());
        ResultSet rs=ps.executeQuery();
        if(rs.next())
            return rs.getInt("id");
        return 0;
    }
}