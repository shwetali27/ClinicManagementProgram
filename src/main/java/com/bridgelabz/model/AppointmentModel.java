package com.bridgelabz.model;

public class AppointmentModel {

	private int patientId;
	private int doctorId;
	private int clinicId;
	private String date;
	private String session;
	private String doctAvailability;
	//getters and setters
	public int getPatientId() {
		return patientId;
	}
	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}
	public int getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}
	public int getClinicId() {
		return clinicId;
	}
	public void setClinicId(int clinicId) {
		this.clinicId = clinicId;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getSession() {
		return session;
	}
	public void setSession(String session) {
		this.session = session;
	}
	public String getDoctAvailability() {
		return doctAvailability;
	}
	public void setDoctAvailability(String doctAvailability) {
		this.doctAvailability = doctAvailability;
	}
	
}
