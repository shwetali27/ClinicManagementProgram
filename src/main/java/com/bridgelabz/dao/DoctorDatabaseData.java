package com.bridgelabz.dao;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.bridgelabz.model.DoctorModel;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException;

public class DoctorDatabaseData implements DatabaseDao{

	private Statement stmt;
	private String sql;

	//method for creating table inside database
	public void createTable() throws ClassNotFoundException, SQLException {
		DatabaseConnection databaseConnection = new DatabaseConnection();
		this.stmt = databaseConnection.getConnection().createStatement();
		try {
			sql = "CREATE TABLE DOCTOR " + "(doctId int NOT NULL, " + " doctName VARCHAR(255), "
					+ " doctSpecialization VARCHAR(255), " + " PRIMARY KEY ( doctId ))";

			stmt.executeUpdate(sql);
		} catch (MySQLSyntaxErrorException e) {
			System.out.println("Table Already Exists");
		}

	}

	// method for inserting data inside database
	public void insertData(ArrayList<DoctorModel> pDoctorModelList) throws ClassNotFoundException, SQLException {
		this.createTable();
		try {
			for (int i = 0; i < pDoctorModelList.size(); i++) {
				sql = "INSERT INTO DOCTOR (doctId,doctName,doctSpecialization) VALUES ("
						+ pDoctorModelList.get(i).getDoctId()+",'"+pDoctorModelList.get(i).getDoctName() + "','"
						+ pDoctorModelList.get(i).getDoctSpecialization() + "')";
				stmt.executeUpdate(sql);
			}
			System.out.println("Inserted records into the table");
		} catch (MySQLIntegrityConstraintViolationException e) {
			System.out.println("Data already present inside database");;
		}
	}

	public void insertData() {
		
		
	}
}
