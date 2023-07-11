package com.zoho.E_Shopping;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
class TableInsertion
{
     public void insertData()throws SQLException
     {	
		if(isEmpty("role")==0)
		 	role();
          if(isEmpty("usersdetail")==0)
               user();
          if(isEmpty("account")==0)
               account();
          if(isEmpty("address")==0)
               address();
          if(isEmpty("shop")==0)
               shop();
          if(isEmpty("wallet")==0)
               wallet();
           if(isEmpty("products")==0)
               product();
          if(isEmpty("category")==0)
               category();
          if(isEmpty("productsdetail")==0)
               productsdetail();
          updateDelivery();
          updateReturn();

     }
     private void role()throws SQLException
     {
          SingletonStatement.getStatement().executeUpdate("INSERT INTO role (name)VALUES ('admin'),('vendor'),('customer');");                                   
     }
     private void user()throws SQLException
     {
          SingletonStatement.getStatement().executeUpdate("INSERT INTO usersdetail (roleid,name,gender,mobile,gmail)VALUES (1,'harish','male',8978674567,'harish@gmail.com'),(2,'ram','male',8978674589,'ram@gmail.com'),(2,'gupta','male',8977474567,'gupta@gmail.com');");                                   
     }
      private void address()throws SQLException
     {
          SingletonStatement.getStatement().executeUpdate("INSERT INTO address (userid,doorno,street,city,district,pincode)VALUES (1,12,'east street','srivilliputtur','virdhunagar',626125),(2,32,'west street','sivakasi','virdhunagar',626125),(3,109,'line street','sivakasi','virdhunagar',626125);");                                   
     }
     private void account()throws SQLException
     {
          SingletonStatement.getStatement().executeUpdate("INSERT INTO account (userid,password)VALUES (1,'Mks*5532'),(2,'Mks*5532'),(3,'Mks*5532');");                                   
     }
     private void shop()throws SQLException
     {
          SingletonStatement.getStatement().executeUpdate("INSERT INTO shop (userid,name,type)VALUES (1,'amazon','online purchase'),(2,'jk gifs','gift shop'),(3,'beauty','parler');");                                   
     }
     private void wallet()throws SQLException
     {
          SingletonStatement.getStatement().executeUpdate("INSERT INTO wallet (shopid,amount)VALUES (1,0.0),(2,0.0),(3,0.0);");                                   
     }
      private void product()throws SQLException
     {
          SingletonStatement.getStatement().executeUpdate("INSERT INTO products (name) VALUES ('Samsung Galaxy M31'),('Apple iPhone 12'),('OnePlus 9 Pro'),('Xiaomi Redmi Note 10 Pro'),('Realme 8 Pro'),('Oppo F19 Pro'),('Vivo V21'),('Mi 11X'),('Samsung Galaxy S21 Ultra'),('Apple iPhone SE'),('OnePlus Nord CE'),('Xiaomi Poco X3'),('Realme Narzo 30 Pro'),('Oppo A53'),('Vivo Y20'),('Mi 10T Pro'),('Samsung Galaxy A52'),('Apple iPhone 11'),('OnePlus 8T'),('Xiaomi Redmi 9 Power'),('Realme 7'),('Oppo Reno 5 Pro'),('Vivo V20 Pro'),('Mi 10T Lite'),('Samsung Galaxy M51'),('Apple iPhone XR'),('OnePlus 8 Pro'),('Xiaomi Redmi Note 9 Pro'),('Realme 6 Pro'),('Oppo F17 Pro'),('Vivo Y51');");
     }
     private void category()throws SQLException
     {
          SingletonStatement.getStatement().executeUpdate("INSERT INTO category (name) VALUES ('Mobiles'),('Laptops'),('Televisions'),('Cameras'),('Headphones'),('Speakers'),('Wearable Devices'),('Home Appliances'),('Gaming Consoles'),('Printers');");                                   
     }
      private void productsdetail()throws SQLException
     {
          SingletonStatement.getStatement().executeUpdate("INSERT INTO productsdetail (productid, categoryid, shopid, specification, count, price, status) VALUES (1, 1, 2, 'Screen Size: 6.4 inches, RAM: 4GB, Storage: 64GB, Camera: 48MP', 10, 9999.99, true), (2, 2, 3, 'Screen Size: 13.3 inches, RAM: 8GB, Storage: 512GB, Processor: Intel Core i7', 5, 19999.99, true), (3, 1, 2, 'Screen Size: 6.7 inches, RAM: 6GB, Storage: 128GB, Camera: 64MP', 20, 14999.99, true), (4, 3, 2, 'Screen Size: 55 inches, Resolution: 4K Ultra HD, Smart TV: Yes', 15, 24999.99, true), (5, 2, 3, 'Type: Over-ear, Connectivity: Bluetooth 5.0, Battery Life: 30 hours', 8, 17999.99, true), (6, 1, 2, 'Screen Size: 6.5 inches, RAM: 4GB, Storage: 128GB, Camera: 48MP', 12, 11999.99, true), (7, 4, 2, 'Resolution: 20.1 MP, Sensor Type: CMOS, Zoom: 10x Optical Zoom', 30, 29999.99, true), (8, 2, 3, 'Type: True Wireless Earbuds, Battery Life: 6 hours, Water Resistance: IPX7', 3, 15999.99, true), (9, 1, 2, 'Screen Size: 6.2 inches, RAM: 6GB, Storage: 256GB, Camera: 64MP', 18, 13999.99, true), (10, 3, 2, 'Screen Size: 65 inches, Resolution: 4K Ultra HD, Smart TV: Yes', 7, 21999.99, true), (11, 1, 3, 'Screen Size: 6.3 inches, RAM: 4GB, Storage: 64GB, Camera: 48MP', 10, 8999.99, true), (12, 2, 2, 'Screen Size: 15.6 inches, RAM: 16GB, Storage: 1TB SSD, Processor: AMD Ryzen 7', 3, 29999.99, true), (13, 3, 3, 'Screen Size: 49 inches, Resolution: Full HD, Smart TV: Yes', 8, 13999.99, true), (14, 1, 2, 'Screen Size: 6.1 inches, RAM: 6GB, Storage: 128GB, Camera: 64MP', 15, 10999.99, true), (15, 2, 3, 'Type: In-ear, Connectivity: Wired, Frequency Response: 20Hz-20kHz', 20, 599.99, true);");                                   
     }
      private int isEmpty(String tablename)throws SQLException
     {
                 ResultSet rs=SingletonStatement.getStatement().executeQuery("SELECT COUNT(id) FROM "+tablename+";");
                 if(rs.next())
                 return rs.getInt(1);
        return 0;
     }
     public void updateDelivery()throws SQLException
     {
          int id=0;
          ResultSet rs=SingletonStatement.getStatement().executeQuery("select id,paymenttype from orders where status='shipped' AND deliverydate=CURRENT_DATE;");
          while(rs.next())
          {
               id=rs.getInt(1);
               if(rs.getString(2).equals("cod"))
               {
                    PreparedStatement ps=Database.getInstance().getConnection().prepareStatement("UPDATE orders set status=?,paymentstatus='t' where id=?");
                    ps.setString(1,"delivered");
                    ps.setInt(2,id);
                    ps.executeUpdate();
               }
               else
               {
                    PreparedStatement ps=Database.getInstance().getConnection().prepareStatement("UPDATE orders set status=? where id=?");
                    ps.setString(1,"delivered");
                    ps.setInt(2,id);
                    ps.executeUpdate();
               }
          }
     }
     public void updateReturn()throws SQLException
     {
          int id=0;
          int count=0;
          int pid=0;
          int shopid=0;
          double amount=0;
          ResultSet rs=SingletonStatement.getStatement().executeQuery("select o.id,o.count,o.productid,p.shopid,o.amount from orders o JOIN return r ON r.orderid=o.id JOIN productsdetail p ON p.id=o.productid WHERE o.status='delivered' AND r.status='f' AND r.enddate=CURRENT_DATE;");
          while(rs.next())
          {
               id=rs.getInt(1);
               count=rs.getInt(2);
               pid=rs.getInt(3);
               shopid=rs.getInt(4);
               amount=rs.getDouble(5);
               PreparedStatement ps=Database.getInstance().getConnection().prepareStatement("UPDATE orders set status=?,paymentstatus='f' where id=?");
               ps.setString(1,"returned");
               ps.setInt(2,id);
               ps.executeUpdate();
               ps=Database.getInstance().getConnection().prepareStatement("UPDATE productsdetail set count=count+? where id=?");
               ps.setInt(1,count);
               ps.setInt(2,pid);
               ps.executeUpdate();
               double shopamount=(10.0/100.0)/amount;
               ps=Database.getInstance().getConnection().prepareStatement("UPDATE wallet set amount=amount-? where id=?");
               ps.setDouble(1,(amount-shopamount));
               ps.setInt(2,shopid);
               ps.executeUpdate();
               ps=Database.getInstance().getConnection().prepareStatement("UPDATE wallet set amount=amount-? where id=1");
               ps.setDouble(1,shopamount);
               ps.executeUpdate();
          }
               
     }
}
