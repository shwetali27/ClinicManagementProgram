package com.bridgelabz.dao;

import java.sql.SQLException;
import java.sql.Statement;

import com.bridgelabz.model.ClinicModel;
import com.bridgelabz.model.DoctorModel;
import com.bridgelabz.model.PatientModel;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException;

public class DatabaseDaoImpl implements DatabaseDao {
	
	private Statement stmt;
	private String sql;

	// method for creating table clinic inside database
	private void createClinicTable() throws ClassNotFoundException, SQLException {
		
		try {
			sql = "CREATE TABLE CLINIC " + "(clinicId int NOT NULL, " + " clinicName VARCHAR(255), "
					+ " PRIMARY KEY ( clinicId ))";

			stmt.executeUpdate(sql);
		} catch (MySQLSyntaxErrorException e) {
			// System.out.println("Table Already Exists");
		}

	}

	// method for creating table doctor & doctor-clinic inside database
	private void createDoctorTable() throws ClassNotFoundException, SQLException {
		try {
			sql = "CREATE TABLE DOCTOR " + "(doctId int NOT NULL, " + " doctName VARCHAR(255), "
					+ " doctSpecialization VARCHAR(255), " + " PRIMARY KEY ( doctId ))";

			stmt.executeUpdate(sql);
		} catch (MySQLSyntaxErrorException e) {
			// System.out.println("Table 'DOCTOR' already Exists");
		}

		try {
			sql = "CREATE TABLE DOCTOR_CLINIC " + "(doctId int NOT NULL, " + " clinicId int NOT NULL ,"
					+ "FOREIGN KEY (clinicId) REFERENCES CLINIC (clinicId))";

			stmt.executeUpdate(sql);
		} catch (MySQLSyntaxErrorException e) {
			sql = "TRUNCATE TABLE DOCTOR_CLINIC";
			stmt.executeUpdate(sql);
			// System.out.println("Table 'DOCTOR_CLINIC' already exists");
		}

	}

	// method for creating the patient and clinic table
	private void createPatientTable() throws ClassNotFoundException, SQLException {
		try {
			sql = "CREATE TABLE PATIENT " + "(patientId int NOT NULL, " + " patientName VARCHAR(255), "
					+ " patientNumber VARCHAR(255), " + "patientAge int ," + " PRIMARY KEY ( patientId ))";

			stmt.executeUpdate(sql);
		} catch (MySQLSyntaxErrorException e) {
			// System.out.println("Table 'PATIENT' already exists");
		}

		try {
			sql = "CREATE TABLE PATIENT_CLINIC " + "(patientId int NOT NULL, " + " clinicId int NOT NULL," + "FOREIGN KEY (clinicId) REFERENCES CLINIC (clinicId))";
			stmt.executeUpdate(sql);
		} catch (MySQLSyntaxErrorException e) {
			sql = "TRUNCATE TABLE PATIENT_CLINIC";
			stmt.executeUpdate(sql);
			// System.out.println("Table 'PATIENT_CLINIC' already exists");
		}

	}

	/*--------------------------------------adding data inside database-------------------------------------*/

	// putting the values inside clinic
	public void addClinic(ClinicModel clinicModel) throws SQLException, ClassNotFoundException {

		try {
			sql = "INSERT INTO CLINIC(clinicId,clinicName) VALUES (" + clinicModel.getClinicId() + ",'"
					+ clinicModel.getClinicName() + "')";
			stmt.executeUpdate(sql);
		} catch (MySQLIntegrityConstraintViolationException e) {
			// System.out.println(e);
		}
	}

	// putting value inside doctor & doctor-clinic table
	public void addDoctor(DoctorModel doctorModel) throws ClassNotFoundException, SQLException {
		try {
			sql = "INSERT INTO DOCTOR(doctId,doctName,doctSpecialization) VALUES (" + doctorModel.getDoctId() + ",'"
					+ doctorModel.getDoctName() + "','" + doctorModel.getDoctSpecialization() + "')";
			stmt.executeUpdate(sql);
		} catch (MySQLIntegrityConstraintViolationException e) {
			// System.out.println(e);
		}

		for (int i = 0; i < doctorModel.getClinicIdList().size(); i++) {
			try {
				sql = "INSERT INTO DOCTOR_CLINIC(doctId,clinicId) VALUES (" + doctorModel.getDoctId() + ","
						+ doctorModel.getClinicIdList().get(i) + ")";
				stmt.executeUpdate(sql);
			} catch (MySQLIntegrityConstraintViolationException e) {
				// System.out.println(e);
			}
		}

	}

	// putting the values inside patient and patient_clinic table
	public void addPatient(PatientModel patientModel) throws ClassNotFoundException, SQLException {
		try {
			sql = "INSERT INTO PATIENT(patientId,patientName,patientNumber,patientAge) VALUES("
					+ patientModel.getPatientId() + ",'" + patientModel.getPatientName() + "','"
					+ patientModel.getPatientNumber() + "'," + patientModel.getPatientAge() + ")";
			stmt.executeUpdate(sql);

		} catch (MySQLIntegrityConstraintViolationException e) {
			// System.out.println(e);
		}

		for (int i = 0; i < patientModel.getClinicIdList().size(); i++) {
			try {
				sql = "INSERT INTO PATIENT_CLINIC(patientId,clinicId) VALUES(" + patientModel.getPatientId() + ","
						+ patientModel.getClinicIdList().get(i) + ")";
				stmt.executeUpdate(sql);

			} catch (MySQLIntegrityConstraintViolationException e) {
				// System.out.println(e);
			}
		}
	}

	// method for creating tables inside database
	public void createTables() throws ClassNotFoundException, SQLException {
		DatabaseConnection databaseConnection = new DatabaseConnection();
		this.stmt = databaseConnection.getConnection().createStatement();
		this.createClinicTable();
		this.createDoctorTable();
		this.createPatientTable();
	}

}
