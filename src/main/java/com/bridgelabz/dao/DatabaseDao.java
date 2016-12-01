package com.bridgelabz.dao;

import java.util.ArrayList;

import com.bridgelabz.model.ClinicModel;
import com.bridgelabz.model.DoctorModel;
import com.bridgelabz.model.PatientModel;

public interface DatabaseDao {
	public void addClinic(ClinicModel clinicModel);

	public void addDoctor(DoctorModel doctorModel);

	public void addPatient(PatientModel patientModel);

	public void createTables();

	public String checkForPatient(int pPatientId);
	
	public ArrayList<ClinicModel> takeclinicInfo(int pPatientId);

	public ArrayList<DoctorModel> takeDoctorInfo(int pClinicId);
	
	
}
