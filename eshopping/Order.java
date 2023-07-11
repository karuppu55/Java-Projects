package com.zoho.E_Shopping;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
interface Order<T>
{
    HashMap<Integer,T> getOrder(int id)throws SQLException;
    void printOrder(HashMap<Integer,T> order)throws IOException;
}