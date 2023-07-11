package com.zoho.WeChat;
import java.sql.Statement;
import java.sql.SQLException;
class Tables
{
	Tables()
	{
		try
		{
			userdetails();
			profile();
			friends();
			post();
			likes();
			commands();
			message();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public void userdetails()throws SQLException
	{
		SingletonStatement.getStatement().executeUpdate("create table if not exists users(id serial primary key,name varchar(50) UNIQUE NOT NULL,fullname varchar(50) NOT NULL,gender varchar(15),email varchar(100) UNIQUE,dob date,password varchar(20),bio varchar(300),createdon date default CURRENT_TIMESTAMP,privacy varchar(12) default 'public',status boolean default 't');");
	}
	public void profile()throws SQLException
	{
		SingletonStatement.getStatement().executeUpdate("create table if not exists profile(id serial primary key,userid int,name varchar(40),profilepicture bytea,status boolean default 't',CONSTRAINT fkuserid FOREIGN KEY(userid) REFERENCES users(id));");
	}
	public void friends()throws SQLException
	{
		SingletonStatement.getStatement().executeUpdate("create table if not exists friends(id serial primary key,sendid int NOT NULL,receiverid int NOT NULL, status varchar(30) default 'requested',chat boolean default 'f',CONSTRAINT fksendid FOREIGN KEY(sendid) REFERENCES users(id),CONSTRAINT fkreceiverid FOREIGN KEY (receiverid) REFERENCES users(id));");
	}
	public void post()throws SQLException
	{
		SingletonStatement.getStatement().executeUpdate("create table if not exists posts(id serial primary key,userid int NOT NULL,post bytea,caption varchar(120),postedon date default CURRENT_TIMESTAMP,privacy varchar(15),status boolean default 't',CONSTRAINT fkuserid FOREIGN KEY(userid) REFERENCES users(id));");
	}
	public void likes()throws SQLException
	{
		SingletonStatement.getStatement().executeUpdate("create table if not exists likes(id serial primary key,userid int NOT NULL,postid int NOT NULL,status boolean default 't',viewstatus boolean default 'f',likedon date default CURRENT_TIMESTAMP,CONSTRAINT fkuserif FOREIGN KEY(userid) REFERENCES users(id),CONSTRAINT fkpostid FOREIGN KEY(postid) REFERENCES posts(id));");
	}
	public void commands()throws SQLException
	{
		SingletonStatement.getStatement().executeUpdate("create table if not exists commands(id serial primary key,userid int NOT NULL,commend varchar(200),postid int NOT NULL,status boolean default 't',viewstatus boolean default 'f',commandon date default CURRENT_TIMESTAMP,CONSTRAINT fkuserif FOREIGN KEY(userid) REFERENCES users(id),CONSTRAINT fkpostid FOREIGN KEY(postid) REFERENCES posts(id));");
	}
	public void message()throws SQLException
	{
		SingletonStatement.getStatement().executeUpdate("create table if not exists message(id serial primary key,senderid int NOT NULL,receiverid int NOT NULL,message varchar(200),sendedon date default CURRENT_TIMESTAMP,view_status varchar(15) default 'send',status boolean default 't');");
	}
}
