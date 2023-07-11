package com.zoho.WeChat;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
interface IFriendsData<T>
{
    HashMap<Integer,T> getData(User us)throws SQLException;
    void printData(HashMap<Integer,T> order)throws IOException,SQLException;
}