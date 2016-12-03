package com.bridgelabz.dao;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DatabaseConnection {

	private static DatabaseConnection databaseConnection = new DatabaseConnection();
	private Connection connection;

	// Database connection to mysql
	private DatabaseConnection(){
		try {
		ComboPooledDataSource cpds = new ComboPooledDataSource();
		cpds.setDriverClass("com.mysql.jdbc.Driver"); // loads the jdbc
														// driver
		cpds.setJdbcUrl("jdbc:mysql://localhost/ClinicManagement");
		cpds.setUser("root");
		cpds.setPassword("bridgeit");

		// the settings below are optional -- c3p0 can work with defaults
		cpds.setMinPoolSize(5);
		cpds.setAcquireIncrement(5);
		cpds.setMaxPoolSize(20);
		
		this.connection  = cpds.getConnection();
		} catch (PropertyVetoException e) {
			System.out.println(e);
		}
		catch (SQLException e) {
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
