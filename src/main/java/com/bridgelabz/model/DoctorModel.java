package com.bridgelabz.model;

import java.util.ArrayList;

public class DoctorModel {
	private int doctId;
	private String doctName;
	private String doctSpecialization;
	private ArrayList<Integer> clinicIdList;
	private ArrayList<String> availabilityList;
	
	//getters and setters
	public int getDoctId() {
		return doctId;
	}
	public void setDoctId(int doctId) {
		this.doctId = doctId;
	}
	public String getDoctName() {
		return doctName;
	}
	public void setDoctName(String doctName) {
		this.doctName = doctName;
	}
	public String getDoctSpecialization() {
		return doctSpecialization;
	}
	public void setDoctSpecialization(String doctSpecialization) {
		this.doctSpecialization = doctSpecialization;
	}
	public ArrayList<Integer> getClinicIdList() {
		return clinicIdList;
	}
	public void setClinicIdList(ArrayList<Integer> clinicIdList) {
		this.clinicIdList = clinicIdList;
	}
	public ArrayList<String> getAvailabilityList() {
		return availabilityList;
	}
	public void setAvailabilityList(ArrayList<String> availabilityList) {
		this.availabilityList = availabilityList;
	}
	
}
