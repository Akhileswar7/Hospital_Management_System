package com.safehomes.website.programs;

import java.sql.Connection;
import java.sql.DriverManager;

public class Database {
	
	public static Connection getConnection() {
		Connection connection=null;
		try {
			 Class.forName("org.postgresql.Driver");
			connection=DriverManager.getConnection("jdbc:postgresql://localhost:5432/safehomes","postgres", "Akhil@63057");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}
}
