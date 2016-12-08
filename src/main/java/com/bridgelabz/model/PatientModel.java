package com.bridgelabz.model;

import java.util.List;

public class PatientModel {

	private String mPatientName;
	private String mPatientNumber;
	private int mPatientId;
	private int mPatientAge;
	private List<Integer> mClinicIdList;
	
	//getters and setters
	public String getPatientName() {
		return mPatientName;
	}
	public void setPatientName(String pPatientName) {
		this.mPatientName = pPatientName;
	}
	public String getPatientNumber() {
		return mPatientNumber;
	}
	public void setPatientNumber(String pPatientNumber) {
		this.mPatientNumber = pPatientNumber;
	}
	public int getPatientId() {
		return mPatientId;
	}
	public void setPatientId(int pPatientId) {
		this.mPatientId = pPatientId;
	}
	public int getPatientAge() {
		return mPatientAge;
	}
	public void setPatientAge(int pPatientAge) {
		this.mPatientAge = pPatientAge;
	}
	public List<Integer> getClinicIdList() {
		return mClinicIdList;
	}
	public void setClinicIdList(List<Integer> pClinicIdList) {
		this.mClinicIdList = pClinicIdList;
	}
	
	
}
