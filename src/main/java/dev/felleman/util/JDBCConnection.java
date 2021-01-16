package dev.felleman.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCConnection {
	
	// Create an empty connection object
	private static Connection conn = null;
	
	// Method to establish connection
	public static Connection getConnection() {
		
		// try catch block for any SQL errors
		try {
			
			// check for an existing connection (or lack of)
			if (conn == null) {
				
				// force driver to load
				Class.forName("oracle.jdbc.driver.OracleDriver");
		
				// Create new Properties Object to get required credentials - probly will fix later with envvar
				Properties props = new Properties();
				
				// Load in file from resources and assign to variables 
				FileInputStream input = new FileInputStream(JDBCConnection.class.getClassLoader().getResource("connection.properties").getFile());	
				props.load(input);
				String url = props.getProperty("url");
				String username = props.getProperty("username");
				String password = props.getProperty("password");
				
				// Establish the connection using credentials
				conn = DriverManager.getConnection(url, username, password);
				
				return conn;
				
			} else {
				// if connection already exists
				return conn;
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		} 
		// if connection fails return null
		return null;
	}

//	// just to test that this is working
//	
//	public static void main(String[] args) {
//		Connection conn1 = getConnection();
//		Connection conn2 = getConnection();
//		Connection conn3 = getConnection();
//
//		
//		System.out.println(conn1);
//		System.out.println(conn2);
//		System.out.println(conn3);
//
//	}
	
}
