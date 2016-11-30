package com.bridgelabz.ClinicManagementProgram;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

import org.json.simple.parser.ParseException;

import com.bridgelabz.DataReader.JsonDataReader;
import com.bridgelabz.dao.DatabaseDao;
import com.bridgelabz.dao.DatabaseDaoImpl;
import com.bridgelabz.utility.Utility;


public class ClinicManagementProgram{

	public static void main(String[] args) throws FileNotFoundException, IOException, ParseException, ClassNotFoundException, SQLException{
		
		//creating the objects for class 
		Utility utility = new Utility();
		JsonDataReader mJsonReader = new JsonDataReader();
		DatabaseDao databaseDao = new DatabaseDaoImpl();
	
		
		//objects for variables
		int choice,patientId;
		//getting the json file for doctors data
		File mDoctFile = new File("JsonData.json");
		
		//reading the file and storing the values inside list
		 mJsonReader.readData(mDoctFile);
		 
		 boolean check = true;
			while(check){
				System.out.println("\n\nWelcome, Please choose Your Option");
				
				System.out.println("1.Take Appoinment \n2.Exit");
				choice = utility.inputInteger();
				switch(choice){
					case 1:{
						System.out.println("Please Enter your Id");
						patientId = utility.inputInteger();
						databaseDao.takeAppointment(patientId);
						break;
					}
					case 2:{
						check = false;
						break;
					}
					default:{
						System.out.println("Wrong Choice!!");
						break;
					}
				}//end of switch
			}//end of while
			
		
		 //databaseDaoImpl.showData();
		 System.out.println("Done");
		 //databaseConnection.closeConnection();
	}
}
