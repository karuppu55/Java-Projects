package com.zoho.E_Shopping;
import java.sql.SQLException;
import java.util.HashMap;
import java.io.*;
interface PrintProduct{
    void printProduct(HashMap<Integer,ProductDetailModel> product)throws IOException;
}