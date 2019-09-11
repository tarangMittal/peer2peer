package org.booksforyou.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseService {
	
	Connection connection;

	public BaseService() {
		System.out.println("-------- PostgreSQL "
				+ "JDBC Connection Testing ------------");
		try {
			Class.forName("org.postgresql.Driver");
			//Class.forName("com.mysql.jdbc.Driver");
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return; 
		}
			
		System.out.println("PostgreSQL JDBC Driver Registered!");
		try {
			/* connection = DriverManager.getConnection(
					"jdbc:mysql://127.0.0.1:3306/booksdb", "root",
					"password"); */
			connection = DriverManager.getConnection(
					"jdbc:postgresql://127.0.0.1:5432/booksdb", "postgres",
					"passpass");
			System.out.println("Database Connection received !");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
