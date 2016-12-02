package com.bridgelabz.userOperations;

import com.bridgelabz.dao.DatabaseDao;
import com.bridgelabz.dao.DatabaseDaoImpl;
import com.bridgelabz.model.AppointmentModel;

public class UserOperations {
	
	String checkAvalability;

	DatabaseDao databaseDao = new DatabaseDaoImpl();
	public void checkFuction(AppointmentModel pAppointmentModel){
		int valid = databaseDao.checkAppointment(pAppointmentModel);

		if(valid==1){
			System.out.println("Appointment is already taken");
		}
		
		else if(valid==2){
			System.out.println("No appointments available");
			checkAvalability = databaseDao.checkDoctAvailability(pAppointmentModel);
			if(checkAvalability=="Morning/Evening"){
				
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
