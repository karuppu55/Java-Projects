package com.zoho.E_Shopping;
import java.sql.SQLException;
import java.util.HashMap;
import java.io.*;
interface GetProduct extends PrintProduct{
HashMap<Integer,ProductDetailModel> getProduct(int id)throws SQLException;
}