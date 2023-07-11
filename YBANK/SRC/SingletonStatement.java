package YBank;
import java.sql.Statement;
import java.sql.SQLException;
import java.io.*;
class SingletonStatement
{
    private static Statement state=null;
    static SingletonStatement sing=null;
    private SingletonStatement()throws IOException
    {
        try
        {
            if(state==null)
                state=Database.getDatabaseInstance().getConnection().createStatement();
        }
        catch(SQLException sql)
        {
            System.out.println("STATEMENT ERROR");
        }
    }
    public static Statement getStatement()throws IOException
    {
        if(sing==null)
            sing=new SingletonStatement();
        return state;
    }
    public void closeStatement()throws IOException,SQLException
    {
        if(state!=null)
            state.close();
    }
}