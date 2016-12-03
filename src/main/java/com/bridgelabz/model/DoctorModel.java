package com.bridgelabz.model;

import java.util.List;

public class DoctorModel {
	private int doctId;
	private String doctName;
	private String doctSpecialization;
	
	private List<DoctorClinic> doctorClinicList;
	
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
	public List<DoctorClinic> getDoctorClinicList() {
		return doctorClinicList;
	}
	public void setDoctorClinicList(List<DoctorClinic> doctorClinicList) {
		this.doctorClinicList = doctorClinicList;
	}
	
}
