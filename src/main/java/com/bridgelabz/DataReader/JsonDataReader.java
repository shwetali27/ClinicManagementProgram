package com.bridgelabz.DataReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.bridgelabz.dao.DatabaseDao;
import com.bridgelabz.dao.DatabaseDaoImpl;
import com.bridgelabz.model.ClinicModel;
import com.bridgelabz.model.DoctorModel;
import com.bridgelabz.model.PatientModel;

public class JsonDataReader {
	//creating the object for class
	DatabaseDao databaseDao = new DatabaseDaoImpl();
	JSONParser parser = new JSONParser();
	
	// method for reading json file
	public void readData(File file) throws FileNotFoundException, IOException, ParseException, ClassNotFoundException, SQLException {
		databaseDao.createTables();
		ArrayList<Integer> mlist = new ArrayList<Integer>();

		Object object = parser.parse(new FileReader(file));
		JSONObject jsonObj = (JSONObject) object;// json object created

		/*------------------reading the data for Clinic-------------------------*/
		JSONArray clinicArray = (JSONArray) jsonObj.get("Clinic");
		for (int i = 0; i < clinicArray.size(); i++) {
			// creating the object for clinic model
			ClinicModel clinicModel = new ClinicModel();

			// reading the json data and setting the values inside clinic model
			JSONObject clinic = (JSONObject) clinicArray.get(i);
			String clinicName = (String) clinic.get("Name");
			clinicModel.setClinicName(clinicName);

			int clinicId = Integer.parseInt(String.valueOf(clinic.get("ClinicId")));
			clinicModel.setClinicId(clinicId);

			databaseDao.addClinic(clinicModel);
		}

		/*------------------reading the data for doctors-------------------------*/
		JSONArray doctorsArray = (JSONArray) jsonObj.get("Doctors");

		for (int i = 0; i < doctorsArray.size(); i++) {
			// creating the object for doctor model
			DoctorModel doctorModel = new DoctorModel();

			// reading the json & setting the values inside model
			JSONObject doctor = (JSONObject) doctorsArray.get(i);
			String name = (String) doctor.get("Name");
			doctorModel.setDoctName(name);

			int idInfo = Integer.parseInt(String.valueOf(doctor.get("DoctID")));
			doctorModel.setDoctId(idInfo);

			String specialization = (String) doctor.get("Specialization");
			doctorModel.setDoctSpecialization(specialization);

			mlist.clear();
			JSONArray clinicIdArray = (JSONArray) doctor.get("ClinicId");
			for (int j = 0; j < clinicIdArray.size(); j++) {
				mlist.add(Integer.parseInt(String.valueOf(clinicIdArray.get(j))));
			}
			doctorModel.setClinicIdList(mlist);
			
			databaseDao.addDoctor(doctorModel);
		}
		

		/*------------------reading the data for Patients-------------------------*/
		JSONArray patientsArray = (JSONArray) jsonObj.get("Patients");
		for (int i = 0; i < patientsArray.size(); i++) {
			// creating patient model
			PatientModel patientModel = new PatientModel();
			JSONObject patient = (JSONObject) patientsArray.get(i);

			int patientId = Integer.parseInt(String.valueOf(patient.get("PatientId")));
			patientModel.setPatientId(patientId);
			
			String patientNo = (String) patient.get("MobileNo");
			patientModel.setPatientNumber(patientNo);

			String patientName = (String) patient.get("Name");
			patientModel.setPatientName(patientName);

			int patientAge = Integer.parseInt(String.valueOf(patient.get("Age")));
			patientModel.setPatientAge(patientAge);

			mlist.clear();
			JSONArray clinicIdArray = (JSONArray) patient.get("ClinicId");
			for (int j = 0; j < clinicIdArray.size(); j++) {
				mlist.add(Integer.parseInt(String.valueOf(clinicIdArray.get(j))));
			}
			patientModel.setClinicIdList(mlist);

			databaseDao.addPatient(patientModel);
		}
		
	}// end of method

}
