package com.bridgelabz.dao;

import java.sql.ResultSet;
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
	private ResultSet resultSet,patientresultSet;
	
	// method for creating table clinic inside database
	private void createClinicTable() {

		try {
			sql = "CREATE TABLE CLINIC " + "(clinicId int NOT NULL, " + " clinicName VARCHAR(255), "
					+ " PRIMARY KEY ( clinicId ))";

			stmt.executeUpdate(sql);
		} catch (MySQLSyntaxErrorException e) {
			System.out.println("Table Already Exists");
		} catch (SQLException e) {
			System.out.println(e);
		}

	}

	// method for creating table doctor & doctor-clinic inside database
	private void createDoctorTable() {
		try {
			sql = "CREATE TABLE DOCTOR " + "(doctId int NOT NULL, " + " doctName VARCHAR(255), "
					+ " doctSpecialization VARCHAR(255), " + " PRIMARY KEY ( doctId ))";

			stmt.executeUpdate(sql);
		} catch (MySQLSyntaxErrorException e) {
			System.out.println("Table 'DOCTOR' already Exists");
		} catch (SQLException e) {
			System.out.println(e);
		}

		try {
			sql = "CREATE TABLE DOCTOR_CLINIC " + "(doctId int NOT NULL, " + " clinicId int NOT NULL ,"
					+ "FOREIGN KEY (clinicId) REFERENCES CLINIC (clinicId))";

			stmt.executeUpdate(sql);
		} catch (MySQLSyntaxErrorException e) {
			try {
				sql = "TRUNCATE TABLE DOCTOR_CLINIC";
				stmt.executeUpdate(sql);
			} catch (SQLException e2) {
				System.out.println(e2);
			}

			System.out.println("Table 'DOCTOR_CLINIC' already exists");
		} catch (SQLException e) {
			System.out.println(e);
		}

	}

	// method for creating the patient and clinic table
	private void createPatientTable() {
		try {
			sql = "CREATE TABLE PATIENT " + "(patientId int NOT NULL, " + " patientName VARCHAR(255), "
					+ " patientNumber VARCHAR(255), " + "patientAge int ," + " PRIMARY KEY ( patientId ))";

			stmt.executeUpdate(sql);
		} catch (MySQLSyntaxErrorException e) {
			// System.out.println("Table 'PATIENT' already exists");
		} catch (SQLException e) {
			System.out.println(e);
		}

		try {
			sql = "CREATE TABLE PATIENT_CLINIC " + "(patientId int NOT NULL, " + " clinicId int NOT NULL,"
					+ "FOREIGN KEY (clinicId) REFERENCES CLINIC (clinicId))";
			stmt.executeUpdate(sql);
		} catch (MySQLSyntaxErrorException e) {
			try {
				sql = "TRUNCATE TABLE PATIENT_CLINIC";
				stmt.executeUpdate(sql);
			} catch (SQLException e2) {
				System.out.println(e2);
			}
			// System.out.println("Table 'PATIENT_CLINIC' already exists");
		} catch (SQLException e) {
			System.out.println(e);
		}

	}

	/*--------------------------------------adding data inside database-------------------------------------*/

	// putting the values inside clinic
	public void addClinic(ClinicModel clinicModel) {

		try {
			sql = "INSERT INTO CLINIC(clinicId,clinicName) VALUES (" + clinicModel.getClinicId() + ",'"
					+ clinicModel.getClinicName() + "')";
			stmt.executeUpdate(sql);
		} catch (MySQLIntegrityConstraintViolationException e) {
			System.out.println(e);
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

	// putting value inside doctor & doctor-clinic table
	public void addDoctor(DoctorModel doctorModel) {
		try {
			sql = "INSERT INTO DOCTOR(doctId,doctName,doctSpecialization) VALUES (" + doctorModel.getDoctId() + ",'"
					+ doctorModel.getDoctName() + "','" + doctorModel.getDoctSpecialization() + "')";
			stmt.executeUpdate(sql);
		} catch (MySQLIntegrityConstraintViolationException e) {
			System.out.println(e);
		} catch (SQLException e) {
			System.out.println(e);
		}

		for (int i = 0; i < doctorModel.getClinicIdList().size(); i++) {
			try {
				sql = "INSERT INTO DOCTOR_CLINIC(doctId,clinicId) VALUES (" + doctorModel.getDoctId() + ","
						+ doctorModel.getClinicIdList().get(i) + ")";
				stmt.executeUpdate(sql);
			} catch (MySQLIntegrityConstraintViolationException e) {
				System.out.println(e);
			} catch (SQLException e) {
				System.out.println(e);
			}
		}

	}

	// putting the values inside patient and patient_clinic table
	public void addPatient(PatientModel patientModel) {
		try {
			sql = "INSERT INTO PATIENT(patientId,patientName,patientNumber,patientAge) VALUES("
					+ patientModel.getPatientId() + ",'" + patientModel.getPatientName() + "','"
					+ patientModel.getPatientNumber() + "'," + patientModel.getPatientAge() + ")";
			stmt.executeUpdate(sql);

		} catch (MySQLIntegrityConstraintViolationException e) {
			System.out.println(e);
		} catch (SQLException e) {
			System.out.println(e);
		}

		for (int i = 0; i < patientModel.getClinicIdList().size(); i++) {
			try {
				sql = "INSERT INTO PATIENT_CLINIC(patientId,clinicId) VALUES(" + patientModel.getPatientId() + ","
						+ patientModel.getClinicIdList().get(i) + ")";
				stmt.executeUpdate(sql);

			} catch (MySQLIntegrityConstraintViolationException e) {
				System.out.println(e);
			} catch (SQLException e) {
				System.out.println(e);
			}
		}
	}

	// method for creating tables inside database
	public void createTables() {
		DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
		try {
			this.stmt = databaseConnection.getConnection().createStatement();
		} catch (SQLException e) {
			System.out.println(e);
		}

		this.createClinicTable();
		this.createDoctorTable();
		this.createPatientTable();
	}

	public void showData() {
		DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
		try {
			this.stmt = databaseConnection.getConnection().createStatement();
			sql = "SELECT * FROM DOCTOR_CLINIC NATURAL JOIN DOCTOR WHERE DOCTOR_CLINIC.clinicId=10001";
			resultSet = stmt.executeQuery(sql);

			while (resultSet.next()) {
				System.out.println("inside while");
				// Retrieve by column name
				int id = resultSet.getInt("doctId");
				int clinicId = resultSet.getInt("clinicId");
				String name = resultSet.getString("doctName");
				String specialization = resultSet.getString("doctSpecialization");

				// Display values
				System.out.print("ID: " + id);
				System.out.print(", clinic id: " + clinicId);
				System.out.print(", doctName: " + name);
				System.out.print(", First: " + specialization);

			}
		} catch (MySQLIntegrityConstraintViolationException e) {
			System.out.println(e);
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

	// method for taking appointment for particular patient
	public void takeAppointment(int patientId) {
		DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
		try {
			this.stmt = databaseConnection.getConnection().createStatement();
			sql = "SELECT * FROM PATIENT WHERE PATIENT.patientId="+patientId+";";
			patientresultSet = stmt.executeQuery(sql);
			if (patientresultSet.next()){
			String name = patientresultSet.getString("patientName");
			System.out.println("Hello "+name);
			}
			else{
				System.out.println("no data found");
			}
			/*sql = "SELECT * FROM PATIENT_CLINIC NATURAL JOIN PATIENT WHERE PATIENT.patientId=1";
			ResultSet rs = stmt.executeQuery(sql);*/
			
		} catch (MySQLIntegrityConstraintViolationException e) {
			System.out.println(e);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}//end of method
}
