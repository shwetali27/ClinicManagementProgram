package com.bridgelabz.dao;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bridgelabz.model.ClinicModel;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException;

public class DatabaseDaoImpl implements DatabaseDao{

	private Statement stmt;
	private String sql;

	//method for creating table inside database
	public void createTable() throws ClassNotFoundException, SQLException {
		DatabaseConnection databaseConnection = new DatabaseConnection();
		this.stmt = databaseConnection.getConnection().createStatement();
		/*try {
			sql = "CREATE TABLE DOCTOR " + "(doctId int NOT NULL, " + " doctName VARCHAR(255), "
					+ " doctSpecialization VARCHAR(255), " + " PRIMARY KEY ( doctId ))";

			stmt.executeUpdate(sql);
		} catch (MySQLSyntaxErrorException e) {
			System.out.println("Table Already Exists");
		}
		*/
		try {
			sql = "CREATE TABLE CLINIC " + "(clinicId int NOT NULL, " + " clinicName VARCHAR(255), "
					+ " PRIMARY KEY ( clinicId ))";

			stmt.executeUpdate(sql);
		} catch (MySQLSyntaxErrorException e) {
			System.out.println("Table Already Exists");
		}

	}

	// method for inserting data inside database
	public <T> void insertData(List<ArrayList<T>> pAllModelList) throws ClassNotFoundException, SQLException {
		
		this.createTable();
		/*-----------------------------------Data Insertion For Clinic----------------------------------------*/
		try{
			String clinicName;
			int clinicId;
			
			//inserting each data for clinic inside database
			for (int i = 0; i < pAllModelList.get(0).size(); i++) {
				clinicName = ((ClinicModel) pAllModelList.get(0).get(i)).getClinicName();
				clinicId = ((ClinicModel) pAllModelList.get(0).get(i)).getClinicId();
				System.out.println(clinicName+":"+clinicId);
				
				sql = "INSERT INTO CLINIC(clinicId,clinicName) VALUES ("+clinicId+",'"+clinicName+"')";
				stmt.executeUpdate(sql);
			}

		}
		catch(MySQLIntegrityConstraintViolationException e){
			System.out.println(e);
		}
		
		
		/*try {
			for (int i = 0; i < pDoctorModelList.size(); i++) {
				sql = "INSERT INTO DOCTOR (doctId,doctName,doctSpecialization) VALUES ("
						+ pDoctorModelList.get(i).getDoctId()+",'"+pDoctorModelList.get(i).getDoctName() + "','"
						+ pDoctorModelList.get(i).getDoctSpecialization() + "')";
				stmt.executeUpdate(sql);
			}
			System.out.println("Inserted records into the table");
		} catch (MySQLIntegrityConstraintViolationException e) {
			System.out.println("Data already present inside database");;
		}*/
	}

}
