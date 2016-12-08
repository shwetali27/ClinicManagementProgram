package com.bridgelabz.model;

import java.util.List;

public class DoctorModel {
	private int mDoctId;
	private String mDoctName;
	private String mDoctSpecialization;
	
	private List<DoctorClinic> mDoctorClinicList;
	
	//getters and setters
	public int getDoctId() {
		return mDoctId;
	}
	public void setDoctId(int pDoctId) {
		this.mDoctId = pDoctId;
	}
	public String getDoctName() {
		return mDoctName;
	}
	public void setDoctName(String pDoctName) {
		this.mDoctName = pDoctName;
	}
	public String getDoctSpecialization() {
		return mDoctSpecialization;
	}
	public void setDoctSpecialization(String pDoctSpecialization) {
		this.mDoctSpecialization = pDoctSpecialization;
	}
	public List<DoctorClinic> getDoctorClinicList() {
		return mDoctorClinicList;
	}
	public void setDoctorClinicList(List<DoctorClinic> pDoctorClinicList) {
		this.mDoctorClinicList = pDoctorClinicList;
	}
	
}
