package com.bridgelabz.model;

public class AppointmentModel {

	private int mPatientId;
	private int mDoctorId;
	private int mClinicId;
	private String mDate;
	private String mSession;
	
	//getters and setters
	public int getPatientId() {
		return mPatientId;
	}
	public void setPatientId(int pPatientId) {
		mPatientId = pPatientId;
	}
	public int getDoctorId() {
		return mDoctorId;
	}
	public void setDoctorId(int pDoctorId) {
		mDoctorId = pDoctorId;
	}
	public int getClinicId() {
		return mClinicId;
	}
	public void setClinicId(int pCinicId) {
		mClinicId = pCinicId;
	}
	public String getDate() {
		return mDate;
	}
	public void setDate(String pDate) {
		mDate = pDate;
	}
	public String getSession() {
		return mSession;
	}
	public void setSession(String pSession) {
		mSession = pSession;
	}
	
}
