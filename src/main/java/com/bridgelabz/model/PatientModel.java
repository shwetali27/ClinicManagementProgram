package com.bridgelabz.model;

import java.util.ArrayList;

public class PatientModel {

	private String patientName;
	private String patientNumber;
	private int patientId;
	private int patientAge;
	private ArrayList<Integer> clinicIdList;
	
	//getters and setters
	public String getPatientName() {
		return patientName;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	public String getPatientNumber() {
		return patientNumber;
	}
	public void setPatientNumber(String patientNumber) {
		this.patientNumber = patientNumber;
	}
	public int getPatientId() {
		return patientId;
	}
	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}
	public int getPatientAge() {
		return patientAge;
	}
	public void setPatientAge(int patientAge) {
		this.patientAge = patientAge;
	}
	public ArrayList<Integer> getClinicIdList() {
		return clinicIdList;
	}
	public void setClinicIdList(ArrayList<Integer> clinicIdList) {
		this.clinicIdList = clinicIdList;
	}
	
	
}
