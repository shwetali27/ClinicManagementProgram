package com.bridgelabz.ClinicManagementProgram;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.parser.ParseException;

import com.bridgelabz.DataReader.JsonDataReader;
import com.bridgelabz.dao.DatabaseDao;
import com.bridgelabz.dao.DatabaseDaoImpl;

public class ClinicManagementProgram{

	public static <T> void main(String[] args) throws FileNotFoundException, IOException, ParseException, ClassNotFoundException, SQLException{
		
		//creating the objects for class 
		JsonDataReader mJsonReader = new JsonDataReader();
		DatabaseDao databaseDao = new DatabaseDaoImpl();
		
		//creating list object
		List<ArrayList<T>> allModelList = new ArrayList<ArrayList<T>>();
		//getting the json file for doctors data
		File mDoctFile = new File("JsonData.json");
		
		//reading the file and storing the values inside list
		allModelList = mJsonReader.readData(mDoctFile);
		
		//inserting data inside database
		databaseDao.insertData(allModelList);
		
	}
}
