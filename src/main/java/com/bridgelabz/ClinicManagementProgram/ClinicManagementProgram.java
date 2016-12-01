package com.bridgelabz.ClinicManagementProgram;

import java.io.File;
import java.util.ArrayList;

import com.bridgelabz.DataReader.JsonDataReader;
import com.bridgelabz.dao.DatabaseDao;
import com.bridgelabz.dao.DatabaseDaoImpl;
import com.bridgelabz.model.ClinicModel;
import com.bridgelabz.model.DoctorModel;
import com.bridgelabz.utility.Utility;

public class ClinicManagementProgram {

	public static void main(String[] args) {

		// creating the objects for class
		Utility utility = new Utility();
		JsonDataReader mJsonReader = new JsonDataReader();
		DatabaseDao databaseDao = new DatabaseDaoImpl();

		// objects for lists
		ArrayList<ClinicModel> clinicModelList = new ArrayList<ClinicModel>();
		ArrayList<DoctorModel> doctorModelList = new ArrayList<DoctorModel>();
		
		ArrayList<Integer> clinicList = new ArrayList<Integer>();
		ArrayList<Integer> doctorList = new ArrayList<Integer>();
		
		// objects for variables
		int choice, patientId,timeChoice,clinicId,doctorId;
		String availability;
		
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
				clinicList.clear();
				doctorList.clear();
				
				System.out.println("Please Enter your Id");
				patientId = utility.inputInteger();
				String patientName = databaseDao.checkForPatient(patientId);
				if (patientName == null) {
					System.out.println("Sorry No Data Available For ID=" + patientId);
					break;
				}

				System.out.println("Hello " + patientName);
				
				//asking user for morning or evening time
				System.out.println("Please Select\n1.Morning\n2.Evening");
				timeChoice = utility.inputInteger();
				while(!(timeChoice==1|timeChoice==2)){
					System.out.println("Please select 1 or 2");
					timeChoice = utility.inputInteger();
				}
				if(timeChoice==1){
					availability = "Morning";
				}
				else{
					availability = "Evening";
				}
				
				//displaying clinics for patient
				clinicModelList = databaseDao.takeclinicInfo(patientId);
				System.out.println("Available Clinics are:" + "\nClinic_ID\tClinic_Name");

				for (ClinicModel clinicModel : clinicModelList) {
					System.out.print(clinicModel.getClinicId() + "\t\t");
					clinicList.add(clinicModel.getClinicId());
					System.out.print(clinicModel.getClinicName() + "\n");
				}
				
				//getting doctors for particular data
				System.out.println("Please Choose Clinic Id");
				clinicId = utility.inputInteger();

				while (!(clinicList.contains(clinicId))) {
					System.out.println("Wrong Choice!! Please Choose valid Clinic Id");
					clinicId = utility.inputInteger();
				}

				doctorModelList = databaseDao.takeDoctorInfo(clinicId,availability);
				if(doctorModelList.size()==0){
					System.out.println("Sorry no doctor available for this session");
					break;
				}
				
				System.out.println("Available doctors are:\nDoct_ID\tDoct_Name\tDoct_Specialization");
				for (DoctorModel doctorModel : doctorModelList) {
					doctorList.add(doctorModel.getDoctId());
					System.out.println(doctorModel.getDoctId() 
							+ "\t" + doctorModel.getDoctName()
							+ "\t" + doctorModel.getDoctSpecialization());
				}
				
				//selecting doctor
				System.out.println("Please Choose Doctor ID: ");
				doctorId = utility.inputInteger();
				while (!(doctorList.contains(clinicId))) {
					System.out.println("Wrong Choice!! Please Choose valid Clinic Id");
					clinicId = utility.inputInteger();
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
