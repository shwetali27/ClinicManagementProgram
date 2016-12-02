package com.bridgelabz.userOperations;

import com.bridgelabz.dao.DatabaseDao;
import com.bridgelabz.dao.DatabaseDaoImpl;
import com.bridgelabz.model.AppointmentModel;
import com.bridgelabz.utility.Utility;

public class UserOperations {
	//objects for variables
	String checkAvalability;

	//objects for class
	DatabaseDao databaseDao = new DatabaseDaoImpl();
	Utility utility = new Utility();
	
	public void checkFuction(AppointmentModel pAppointmentModel){
		int valid = databaseDao.checkAppointment(pAppointmentModel);

		if(valid==1){
			System.out.println("Appointment is already taken");
		}
		
		else if(valid==2){
			System.out.println("No appointments available");
			checkAvalability = databaseDao.checkDoctAvailability(pAppointmentModel);
			System.out.println(checkAvalability);
			System.out.println("app session: "+pAppointmentModel.getSession());
			if(checkAvalability.trim().equals("Morning-Evening")){
				System.out.println(pAppointmentModel.getSession()+"::");
				if(pAppointmentModel.getSession()=="Morning"){
					System.out.println("Do you want to take appointment for evening session? y/n");
					String choice = utility.inputString();
					if(choice.equals("y")){
						pAppointmentModel.setSession("Evening");
						this.checkFuction(pAppointmentModel);
					}
					else{
						System.out.println("Thanks for visit");
					}
				}
				else{
					System.out.println("Evening");
				}
			}
		}
		else{
			System.out.println(pAppointmentModel.getClinicId() + "," + pAppointmentModel.getPatientId() + ","
					+ pAppointmentModel.getDoctorId() + "," + pAppointmentModel.getSession() + ","
					+ pAppointmentModel.getDate());

			String result = databaseDao.takeAppointment(pAppointmentModel);
			System.out.println(result);
		}
	}
}
