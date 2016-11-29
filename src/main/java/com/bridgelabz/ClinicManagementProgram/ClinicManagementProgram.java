package com.bridgelabz.ClinicManagementProgram;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

import org.json.simple.parser.ParseException;

import com.bridgelabz.DataReader.JsonDataReader;
import com.bridgelabz.dao.DatabaseConnection;

public class ClinicManagementProgram{

	public static void main(String[] args) throws FileNotFoundException, IOException, ParseException, ClassNotFoundException, SQLException{
		
		//creating the objects for class 
		JsonDataReader mJsonReader = new JsonDataReader();
		DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
	
		//getting the json file for doctors data
		File mDoctFile = new File("JsonData.json");
		
		//reading the file and storing the values inside list
		 mJsonReader.readData(mDoctFile);
		
		 System.out.println("Done");
		 databaseConnection.closeConnection();
	}
}
