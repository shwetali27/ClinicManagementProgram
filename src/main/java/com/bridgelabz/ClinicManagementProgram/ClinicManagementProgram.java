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
		// objects for variables
		int choice, patientId;
		// getting the json file for doctors data
		File mDoctFile = new File("JsonData.json");

		// reading the file and storing the values inside list
		mJsonReader.readData(mDoctFile);

		/*boolean check = true;
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
				clinicModelList = databaseDao.takeclinicInfo(patientId);
				System.out.println("Available Clinics are:" + "\nClinic_ID\tClinic_Name");

				for (ClinicModel clinicModel : clinicModelList) {
					System.out.print(clinicModel.getClinicId() + "\t\t");
					System.out.print(clinicModel.getClinicName() + "\n");
				}
				
				System.out.println("Please Choose Clinic Id");
				int clinicId = utility.inputInteger();
				doctorModelList = databaseDao.takeDoctorInfo(clinicId);

				while (doctorModelList.size() == 0) {
					System.out.println("Wrong Choice!! Please Choose valid Clinic Id");
					clinicId = utility.inputInteger();
					doctorModelList = databaseDao.takeDoctorInfo(clinicId);
				}

				doctorModelList = databaseDao.takeDoctorInfo(clinicId);
				System.out.println("Available doctors are:\nDoct_ID\tDoct_Name\tDoct_Specialization");
				
				for (DoctorModel doctorModel : doctorModelList) {
					System.out.println(doctorModel.getDoctId() 
							+ "\t" + doctorModel.getDoctName()
							+ "\t" + doctorModel.getDoctSpecialization());
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
*/
		// databaseDaoImpl.showData();
		System.out.println("Done");
		// databaseConnection.closeConnection();
	}
}
