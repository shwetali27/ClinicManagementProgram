package com.bridgelabz.dao;

import java.util.ArrayList;

import com.bridgelabz.model.AppointmentModel;
import com.bridgelabz.model.ClinicModel;
import com.bridgelabz.model.DoctorModel;
import com.bridgelabz.model.PatientModel;

public interface DatabaseDao {
	public void addClinic(ClinicModel pClinicModel);

	public void addDoctor(DoctorModel pDoctorModel);

	public void addPatient(PatientModel pPatientModel);

	public void createTables();

	public String checkForPatient(int pPatientId);
	
	public ArrayList<ClinicModel> takeclinicInfo(int pPatientId);

	public ArrayList<DoctorModel> takeDoctorInfo(int pClinicId,String pAvailability);
	
	public int checkAppointment(AppointmentModel pAppointmentModel);
	
	public String takeAppointment(AppointmentModel pAppointmodel);
	
	public String checkDoctAvailability(AppointmentModel pAppointmentModel);
}
