package com.bridgelabz.dao;

import java.sql.SQLException;

import com.bridgelabz.model.ClinicModel;
import com.bridgelabz.model.DoctorModel;
import com.bridgelabz.model.PatientModel;

public interface DatabaseDao {
	public void addClinic(ClinicModel clinicModel) throws SQLException, ClassNotFoundException;

	public void addDoctor(DoctorModel doctorModel) throws SQLException, ClassNotFoundException;

	public void addPatient(PatientModel patientModel) throws SQLException, ClassNotFoundException;

	public void createTables() throws ClassNotFoundException, SQLException;
}
