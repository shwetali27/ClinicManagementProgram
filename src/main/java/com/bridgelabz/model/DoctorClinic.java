package com.bridgelabz.model;

public class DoctorClinic {
	private int mClinicId;
	private int mDoctorId;
	private String mDoctAvailability;
	
	//getters and setters 
	
	public String getDoctAvailability() {
		return mDoctAvailability;
	}
	public void setDoctAvailability(String pDoctAvailability) {
		this.mDoctAvailability = pDoctAvailability;
	}
	public int getClinicId() {
		return mClinicId;
	}
	public void setClinicId(int pClinicId) {
		this.mClinicId = pClinicId;
	}
	public int getDoctorId() {
		return mDoctorId;
	}
	public void setDoctorId(int cDoctorId) {
		this.mDoctorId = cDoctorId;
	}
	
}
