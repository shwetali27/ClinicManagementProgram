package com.bridgelabz.dao;

import com.bridgelabz.model.ClinicModel;
import com.bridgelabz.model.DoctorModel;
import com.bridgelabz.model.PatientModel;

public interface DatabaseDao {
	public void addClinic(ClinicModel clinicModel);

	public void addDoctor(DoctorModel doctorModel);

	public void addPatient(PatientModel patientModel);

	public void createTables();
	
	public void takeAppointment(int patientId);
	
	public void showData();
}
