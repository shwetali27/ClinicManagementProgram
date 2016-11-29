package com.bridgelabz.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

	private static DatabaseConnection databaseConnection = new DatabaseConnection();
	private String URL = "jdbc:mysql://localhost/ClinicManagement";
	private Connection connection;

	// Database connection to mysql
	private DatabaseConnection(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			this.connection = DriverManager.getConnection(URL, "root", "bridgeit");
		} catch (ClassNotFoundException e) {
			System.out.println(e);
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

	public static DatabaseConnection getInstance() {
		return databaseConnection;
	}

	public Connection getConnection() {
		return connection;
	}

	public void closeConnection() throws SQLException {
		System.out.println("Database Connection Closed");
		this.connection.close();
	}
}
