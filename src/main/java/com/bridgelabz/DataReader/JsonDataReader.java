package com.bridgelabz.DataReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.bridgelabz.model.ClinicModel;
import com.bridgelabz.model.DoctorModel;
import com.bridgelabz.model.PatientModel;

public class JsonDataReader<T> {

	JSONParser parser = new JSONParser();
	private ArrayList<DoctorModel> doctorList = new ArrayList<DoctorModel>();
	private ArrayList<PatientModel> patientList = new ArrayList<PatientModel>();
	private ArrayList<ClinicModel> clinicList = new ArrayList<ClinicModel>();

	private List<ArrayList<T>> allModelList = new ArrayList<ArrayList<T>>();

	// method for reading json file
	public List<ArrayList<T>> readData(File file) throws FileNotFoundException, IOException, ParseException {
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

			// adding the model into list
			clinicList.add(clinicModel);
		}

		allModelList.add((ArrayList<T>) clinicList);
		/*------------------reading the data for doctors-------------------------*/
		JSONArray doctorsArray = (JSONArray) jsonObj.get("Doctors");

		for (int i = 0; i < doctorsArray.size(); i++) {
			// creating the object for doctor model
			DoctorModel doctorModel = new DoctorModel();

			// reading the json & setting the values inside model
			JSONObject doctor = (JSONObject) doctorsArray.get(i);
			Object obj = doctor.get("Name");
			String name = (String) obj;
			doctorModel.setDoctName(name);

			obj = doctor.get("DoctID");
			int idInfo = Integer.parseInt(String.valueOf(obj));
			doctorModel.setDoctId(idInfo);

			obj = doctor.get("Specialization");
			String specialization = (String) obj;
			doctorModel.setDoctSpecialization(specialization);

			mlist.clear();
			JSONArray clinicIdArray = (JSONArray) doctor.get("ClinicId");
			for (int j = 0; j < clinicIdArray.size(); j++) {
				mlist.add(Integer.parseInt(String.valueOf(clinicIdArray.get(j))));
			}
			doctorModel.setClinicIdList(mlist);
			// adding the model into list
			doctorList.add(doctorModel);
		}
		allModelList.add((ArrayList<T>) doctorList);

		/*------------------reading the data for Patients-------------------------*/
		JSONArray patientsArray = (JSONArray) jsonObj.get("Patients");
		for (int i = 0; i < patientsArray.size(); i++) {
			// creating patient model
			PatientModel patientModel = new PatientModel();
			JSONObject patient = (JSONObject) patientsArray.get(i);

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

			patientList.add(patientModel);
		}
		allModelList.add((ArrayList<T>) patientList);

		return allModelList;
	}// end of method

}
