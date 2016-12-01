package com.bridgelabz.ClinicManagementProgram;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import org.json.simple.parser.ParseException;

import com.bridgelabz.DataReader.JsonDataReader;
import com.bridgelabz.dao.DatabaseDao;
import com.bridgelabz.dao.DatabaseDaoImpl;
import com.bridgelabz.model.ClinicModel;
import com.bridgelabz.model.DoctorModel;
import com.bridgelabz.utility.Utility;

public class ClinicManagementProgram {

	public static void main(String[] args)
			throws FileNotFoundException, IOException, ParseException, ClassNotFoundException, SQLException {

		// creating the objects for class
		Utility utility = new Utility();
		JsonDataReader mJsonReader = new JsonDataReader();
		DatabaseDao databaseDao = new DatabaseDaoImpl();

		// objects for lists
		ArrayList<ClinicModel> clinicModelList = new ArrayList<ClinicModel>();
		ArrayList<DoctorModel> doctorModelList = new ArrayList<DoctorModel>();
		// objects for variables
		int choice, patientId;
		// getting the json file for doctors data
		File mDoctFile = new File("JsonData.json");

		// reading the file and storing the values inside list
		mJsonReader.readData(mDoctFile);

		boolean check = true;
		while (check) {
			System.out.println("\n\nWelcome, Please choose Your Option");

			System.out.println("1.Take Appoinment \n2.Exit");
			choice = utility.inputInteger();
			switch (choice) {
			case 1: {
				System.out.println("Please Enter your Id");
				patientId = utility.inputInteger();
				String patientName = databaseDao.checkForPatient(patientId);
				if (patientName == null) {
					System.out.println("Sorry No Data Available For ID=" + patientId);
					break;
				}
				
				System.out.println("Hello " + patientName);
				System.out.println("Available Clinics are:");
				clinicModelList = databaseDao.takeclinicInfo(patientId);
				for (int i = 0; i < clinicModelList.size(); i++) {
					System.out.println("Clinic Name: " + clinicModelList.get(i).getClinicName());
					System.out.println("Clinic Id: " + clinicModelList.get(i).getClinicId() + "\n--------");
				}
				
				System.out.println("Please Choose Clinic Id");
				int clinicId = utility.inputInteger();
				doctorModelList = databaseDao.takeDoctorInfo(clinicId);
				System.out.println("Available doctors are:");
				for (int i = 0; i < doctorModelList.size(); i++) {
					System.out.println(doctorModelList.get(i).getDoctName());
				}
				break;
			}
			case 2: {
				check = false;
				break;
			}
			default: {
				System.out.println("Wrong Choice!!");
				break;
			}
			}// end of switch
		} // end of while

		// databaseDaoImpl.showData();
		System.out.println("Done");
		// databaseConnection.closeConnection();
	}
}
