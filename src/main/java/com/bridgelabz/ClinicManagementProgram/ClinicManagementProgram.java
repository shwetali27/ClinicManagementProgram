package com.bridgelabz.ClinicManagementProgram;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

import org.json.simple.parser.ParseException;

import com.bridgelabz.dao.DoctorDatabaseData;
import com.bridgelabz.jsonReader.JsonReader;

public class ClinicManagementProgram {

	public static void main(String[] args) throws FileNotFoundException, IOException, ParseException, ClassNotFoundException, SQLException{
		
		//creating the objects for class 
		JsonReader mJsonReader = new JsonReader();
		
		//getting the json file for doctors data
		File mDoctFile = new File("JsonData.json");
		
		mJsonReader.readData(mDoctFile);
		
		
	}
}
