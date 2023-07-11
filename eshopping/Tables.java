package com.zoho.E_Shopping;
import java.sql.Statement;
import java.sql.SQLException;
class Tables
{
	Tables()
	{
		try
		{
			role();
			usersdetail();
			products();
			category();
			account();
			shop();
			productsdetail();
            orders();
			returnorder();
			address();
			wallet();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
    public void role()throws SQLException
	{
		SingletonStatement.getStatement().executeUpdate("create  TABLE IF NOT EXISTS role(id serial PRIMARY KEY,name varchar(30));");
	}
	public void usersdetail()throws SQLException
	{
		SingletonStatement.getStatement().executeUpdate("create  TABLE IF NOT EXISTS usersdetail(id serial Primary Key,roleid int NOT NULL,name varchar(50) NOT NULL,gender varchar(15) NOT NULL,mobile BIGINT UNIQUE,gmail varchar(100) UNIQUE,status boolean default 't',CONSTRAINT fkroleid FOREIGN KEY(roleid) REFERENCES role(id));");
	}
    public void products()throws SQLException
	{
		SingletonStatement.getStatement().executeUpdate("CREATE TABLE IF NOT EXISTS products(id serial PRIMARY KEY,name varchar(40) UNIQUE);");
	}
	public void category()throws SQLException
	{
		SingletonStatement.getStatement().executeUpdate("CREATE TABLE IF NOT EXISTS category(id serial PRIMARY KEY,name varchar(40) UNIQUE);");
	}
	public void account()throws SQLException
	{
		SingletonStatement.getStatement().executeUpdate("CREATE TABLE IF NOT EXISTS account(id serial PRIMARY KEY,userid int NOT NULL,password varchar(30) NOT NULL,CONSTRAINT fkuserid FOREIGN KEY(userid) REFERENCES usersdetail(id));");
	}
	public void productsdetail()throws SQLException
	{
		SingletonStatement.getStatement().executeUpdate("CREATE TABLE IF NOT EXISTS productsdetail(id serial PRIMARY KEY,productid int NOT NULL,categoryid int NOT NULL,shopid int NOT NULL,count int,status boolean default 'f',price decimal NOT NULL,specification varchar(200),CONSTRAINT fkproductid FOREIGN KEY(productid) REFERENCES products(id),CONSTRAINT fkcategoryid FOREIGN KEY(categoryid) REFERENCES category(id),CONSTRAINT fkvendorid FOREIGN KEY(shopid) REFERENCES shop(id));");
	}
	public void orders()throws SQLException
	{
		SingletonStatement.getStatement().executeUpdate("CREATE TABLE IF NOT EXISTS orders(id serial PRIMARY KEY,userid int NOT NULL,productid int NOT NULL,count int NOT NULL,amount decimal NOT NULL,orderdate date,paymenttype varchar(30),paymentstatus boolean default 't',status varchar(20) default 'ordered',CONSTRAINT fkuserid FOREIGN KEY(userid) REFERENCES usersdetail(id),CONSTRAINT fkproductid FOREIGN KEY(productid) REFERENCES productsdetail(id));");
	}
    public void returnorder()throws SQLException
	{
		SingletonStatement.getStatement().executeUpdate("create  TABLE IF NOT EXISTS return(id serial PRIMARY KEY,orderid int NOT NULL,feedback varchar(30),status boolean default 't',days int null,returndate date default CURRENT_TIMESTAMP,CONSTRAINT fkorderid FOREIGN KEY(orderid) REFERENCES orders(id));");
	}
	public void cancelnorder()throws SQLException
	{
		SingletonStatement.getStatement().executeUpdate("Create table if not exists cancel(id serial primary key,orderid int,reason varchar(200),status boolean default 't',CONSTRAINT fkorderid FOREIGN KEY(orderid) REFERENCES orders(id));");
	}
	public void address()throws SQLException
	{
		SingletonStatement.getStatement().executeUpdate("CREATE TABLE IF NOT EXISTS address(id serial PRIMARY KEY,userid int NOT NULL,doorno int,street varchar(30),city varchar(30),district varchar(40),pincode BIGINT,CONSTRAINT fkuserid FOREIGN KEY(userid) REFERENCES usersdetail(id));");
	}
	public void shop()throws SQLException
	{
		SingletonStatement.getStatement().executeUpdate("CREATE TABLE IF NOT EXISTS shop(id SERIAL PRIMARY KEY,userid int,name varchar(100),type varchar(50),status boolean default 't',CONSTRAINT fkuserid FOREIGN KEY(userid) REFERENCES usersdetail(id));");
	}
	public void wallet()throws SQLException
	{
		SingletonStatement.getStatement().executeUpdate("CREATE TABLE IF NOT EXISTS wallet(id serial PRIMARY KEY,shopid int,amount decimal,CONSTRAINT fkshopid FOREIGN KEY(shopid) REFERENCES shop(id));");
	}
}
