package com.bridgelabz.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.bridgelabz.model.AppointmentModel;
import com.bridgelabz.model.ClinicModel;
import com.bridgelabz.model.DoctorClinic;
import com.bridgelabz.model.DoctorModel;
import com.bridgelabz.model.PatientModel;
import com.bridgelabz.utility.Utility;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException;

public class DatabaseDaoImpl implements DatabaseDao {
	// objects for classes
	Utility utility = new Utility();
	DatabaseConnection databaseConnection = DatabaseConnection.getInstance();

	// objects of list
	ArrayList<ClinicModel> clinicModelList = new ArrayList<ClinicModel>();
	ArrayList<DoctorModel> doctorModelList = new ArrayList<DoctorModel>();

	// variables
	private Statement stmt,stmt2;
	private String sql;
	
	// method for creating tables inside database
	public void createTables() {
		try{
			stmt = databaseConnection.getConnection().createStatement();
		} catch (MySQLSyntaxErrorException e) {
			System.out.println("Table Already Exists");
		} catch (SQLException e) {
			System.out.println(e);
		}
		this.createClinicTable();
		this.createDoctorTable();
		this.createPatientTable();
		this.createAppointmentsTable();
	}

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
					+ "availability VARCHAR(255), " + "FOREIGN KEY (clinicId) REFERENCES CLINIC (clinicId))";

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

	private void createAppointmentsTable() {
		try {
			sql = "CREATE TABLE APPOINMENTS " + "(patientId int NOT NULL, " + "doctId int NOT NULL, "
					+ "clinicId int NOT NULL, " + " date VARCHAR(255), " + " session VARCHAR(255), "
					+ "FOREIGN KEY (clinicId) REFERENCES CLINIC (clinicId),"
					+ "FOREIGN KEY (doctId) REFERENCES DOCTOR (doctId),"
					+ "FOREIGN KEY (patientId) REFERENCES PATIENT (patientId))";

			stmt.executeUpdate(sql);
		} catch (MySQLSyntaxErrorException e) {
			System.out.println("Table Already Exists");
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

		/*for (int i = 0; i < doctorModel.getClinicIdList().size(); i++) {
			try {
				sql = "INSERT INTO DOCTOR_CLINIC(doctId,clinicId,availability) VALUES (" + doctorModel.getDoctId() + ","
						+ doctorModel.getClinicIdList().get(i) + ",'" + doctorModel.getAvailabilityList().get(i) + "')";
				stmt.executeUpdate(sql);
			} catch (MySQLIntegrityConstraintViolationException e) {
				System.out.println(e);
			} catch (SQLException e) {
				System.out.println(e);
			}
		}*/

		for (DoctorClinic doctorClinic : doctorModel.getDoctorClinicList()) {
			try {
				sql = "INSERT INTO DOCTOR_CLINIC(doctId,clinicId,availability) VALUES (" + doctorClinic.getDoctorId() + ","
						+ doctorClinic.getClinicId()+ ",'" + doctorClinic.getDoctAvailability() + "')";
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

	// method to check for patient
	public String checkForPatient(int pPatientId) {
		ResultSet patientresultSet;
		try {
			stmt = databaseConnection.getConnection().createStatement();
			sql = "SELECT PATIENT.patientName FROM PATIENT WHERE PATIENT.patientId=" + pPatientId + ";";
			patientresultSet = stmt.executeQuery(sql);
			if (patientresultSet.next()) {
				return patientresultSet.getString("patientName");
			}

		} catch (MySQLIntegrityConstraintViolationException e) {
			System.out.println(e);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	// method for taking Clinic information
	public ArrayList<ClinicModel> takeclinicInfo(int pPatientId) {
		ResultSet patientClinicResultSet,clinicResultSet;
		clinicModelList.clear();
		try {
			stmt = databaseConnection.getConnection().createStatement();
			stmt2 = databaseConnection.getConnection().createStatement();

			sql = "SELECT PATIENT_CLINIC.clinicId FROM PATIENT_CLINIC NATURAL JOIN PATIENT WHERE PATIENT.patientId="
					+ pPatientId + ";";
			patientClinicResultSet = stmt.executeQuery(sql);

			while (patientClinicResultSet.next()) {

				sql = "SELECT * FROM CLINIC WHERE CLINIC.clinicId=" + patientClinicResultSet.getInt("clinicId") + ";";
				clinicResultSet = stmt2.executeQuery(sql);
				if (clinicResultSet.next()) {
					ClinicModel clinicModel = new ClinicModel();
					clinicModel.setClinicId(clinicResultSet.getInt("clinicId"));
					clinicModel.setClinicName(clinicResultSet.getString("clinicName"));
					clinicModelList.add(clinicModel);
				}

			}
		} catch (MySQLIntegrityConstraintViolationException e) {
			System.out.println(e);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return clinicModelList;
	}

	// method for taking doctors information
	public ArrayList<DoctorModel> takeDoctorInfo(int pClinicId, String pAvailability) {
		ResultSet resultSet;
		doctorModelList.clear();
		try {
			stmt = databaseConnection.getConnection().createStatement();
			sql = "SELECT * FROM DOCTOR NATURAL JOIN DOCTOR_CLINIC WHERE DOCTOR_CLINIC.clinicId=" + pClinicId
					+ " and (DOCTOR_CLINIC.availability='" + pAvailability
					+ "' or DOCTOR_CLINIC.availability='Morning-Evening');";

			resultSet = stmt.executeQuery(sql);
			while (resultSet.next()) {
				DoctorModel doctorModel = new DoctorModel();
				doctorModel.setDoctId(resultSet.getInt("doctId"));
				doctorModel.setDoctName(resultSet.getString("doctName"));
				doctorModel.setDoctSpecialization(resultSet.getString("doctSpecialization"));
				doctorModelList.add(doctorModel);
			}
		} catch (MySQLIntegrityConstraintViolationException e) {
			System.out.println(e);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return doctorModelList;

	}

	// method for checking for appointment
	public int checkAppointment(AppointmentModel appointmentModel) {
		ResultSet resultSet,patientresultSet;
		try {
			int i = 0, j = 0;
			stmt = databaseConnection.getConnection().createStatement();
			stmt2 = databaseConnection.getConnection().createStatement();
			sql = "SELECT * FROM APPOINMENTS WHERE APPOINMENTS.doctId=" + appointmentModel.getDoctorId()
					+ " AND APPOINMENTS.session='" + appointmentModel.getSession() + "' AND APPOINMENTS.date='"
					+ appointmentModel.getDate() + "';";
			resultSet = stmt.executeQuery(sql);

			//System.out.println(sql);
			sql = "SELECT * FROM APPOINMENTS WHERE APPOINMENTS.patientId=" + appointmentModel.getPatientId()
					+ " AND APPOINMENTS.session='" + appointmentModel.getSession() + "' AND APPOINMENTS.date='"
					+ appointmentModel.getDate() + "' AND APPOINMENTS.doctId=" + appointmentModel.getDoctorId() + ";";

			//System.out.println(sql);
			patientresultSet = stmt2.executeQuery(sql);
			while (resultSet.next()) {
				i++;
			}
			while (patientresultSet.next()) {
				j++;
			}

			if (j != 0) {
				return 1;
			}
			if (i >= 3) {
				return 2;
			}
		} catch (MySQLIntegrityConstraintViolationException e) {
			System.out.println(e);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return 3;
	}

	// method for taking appointment
	public String takeAppointment(AppointmentModel appointmentmodel) {
		try {
			stmt = databaseConnection.getConnection().createStatement();
			sql = "INSERT INTO APPOINMENTS(patientId,doctId,clinicId,date,session) VALUES("
					+ appointmentmodel.getPatientId() + "," + appointmentmodel.getDoctorId() + ","
					+ appointmentmodel.getClinicId() + ",'" + appointmentmodel.getDate() + "','"
					+ appointmentmodel.getSession() + "');";

			//System.out.println(sql);
			stmt.executeUpdate(sql);

		} catch (MySQLIntegrityConstraintViolationException e) {
			System.out.println(e);
		} catch (SQLException e) {
			System.out.println(e);
		}

		return "Successful";
	}

	// checking method for doctors availability
	public String checkDoctAvailability(AppointmentModel appointmentModel) {
		ResultSet resultSet;
		try {
			stmt = databaseConnection.getConnection().createStatement();
			sql = "SELECT * FROM DOCTOR NATURAL JOIN DOCTOR_CLINIC WHERE DOCTOR_CLINIC.doctId="
					+ appointmentModel.getDoctorId() + " AND DOCTOR_CLINIC.clinicId=" + appointmentModel.getClinicId()
					+ ";";

			resultSet = stmt.executeQuery(sql);
			if (resultSet.next()) {
				return resultSet.getString("availability");
			}
		} catch (MySQLIntegrityConstraintViolationException e) {
			System.out.println(e);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
}
