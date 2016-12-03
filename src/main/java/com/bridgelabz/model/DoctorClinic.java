package com.bridgelabz.model;

public class DoctorClinic {
	private int clinicId;
	private int doctorId;
	private String doctAvailability;
	
	//getters and setters 
	
	public String getDoctAvailability() {
		return doctAvailability;
	}
	public void setDoctAvailability(String doctAvailability) {
		this.doctAvailability = doctAvailability;
	}
	public int getClinicId() {
		return clinicId;
	}
	public void setClinicId(int clinicId) {
		this.clinicId = clinicId;
	}
	public int getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}
	
}
