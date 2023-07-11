package com.zoho.E_Shopping;
import java.sql.Statement;
import java.sql.SQLException;
class SingletonStatement
{
    private static Statement state=null;
    static SingletonStatement sing=null;
    private SingletonStatement()
    {
        try
        {
            if(state==null)
                state=Database.getInstance().getConnection().createStatement();
        }
        catch(SQLException sql)
        {
            System.out.println("STATEMENT ERROR");
        }
    }
    public static Statement getStatement()
    {
        if(sing==null)
            sing=new SingletonStatement();
        return state;
    }
}