package com.bridgelabz.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

	private String URL = "jdbc:mysql://localhost/ClinicManagement";
	private Connection connection;

	//Database connection to mysql
	public DatabaseConnection() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		this.connection = DriverManager.getConnection(URL, "root", "bridgeit");
	}
	public Connection getConnection() {
		return connection;
	}
	
}
