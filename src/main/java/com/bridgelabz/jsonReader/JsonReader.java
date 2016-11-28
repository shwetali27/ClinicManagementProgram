package com.bridgelabz.jsonReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.bridgelabz.model.DoctorModel;

public class JsonReader {

	JSONParser parser = new JSONParser();
	ArrayList<DoctorModel> doctorList = new ArrayList<DoctorModel>();
	public void readData(File file) throws FileNotFoundException, IOException, ParseException{
		
		Object object = parser.parse(new FileReader(file));
		JSONObject jsonObj = (JSONObject) object;// json object created
		
		JSONArray doctorsArray = (JSONArray) jsonObj.get("Doctors");
		
		for(int i=0;i<doctorsArray.size();i++){
			//creating the object for doctor model
			DoctorModel doctorModel = new DoctorModel();
			
			//reading the json & setting the values inside model 
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
		
			JSONArray clinicIdArray = (JSONArray) doctor.get("ClinicId");
			for(int j=0;j<clinicIdArray.size();j++){
				JSONObject clinicId = (JSONObject) clinicIdArray.get(j);
				
			}
			//adding the model into list
			doctorList.add(doctorModel);
		}
		
		JSONArray patientsArray = (JSONArray) jsonObj.get("Patients");
		System.out.println(patientsArray.size());
		
	}
	
}
