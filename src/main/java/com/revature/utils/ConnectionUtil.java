package com.revature.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
	public static Connection getConnection() throws SQLException{
		try {
			Class.forName("org.postgresql.Driver");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}

	String url = System.getenv("URL");
	String userName = System.getenv("USERNAME");
	String password = System.getenv("PASSWORD");
	
		
	return DriverManager.getConnection(url, userName, password);
	}
}
