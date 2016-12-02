package com.bridgelabz.userOperations;

import com.bridgelabz.dao.DatabaseDao;
import com.bridgelabz.dao.DatabaseDaoImpl;
import com.bridgelabz.model.AppointmentModel;
import com.bridgelabz.utility.Utility;

public class UserOperations {

	// objects for class
	DatabaseDao databaseDao = new DatabaseDaoImpl();
	Utility utility = new Utility();

	public void checkFuction(AppointmentModel pAppointmentModel) {
		// objects for variables
		String checkAvalability, patientAvailabilityChoice;
		patientAvailabilityChoice = pAppointmentModel.getSession();
		int valid = databaseDao.checkAppointment(pAppointmentModel);

		if (valid == 1) {
			System.out.println("Appointment is already taken for Date " + pAppointmentModel.getDate() + " on "
					+ pAppointmentModel.getSession() + " Session");
		}

		else if (valid == 2) {
			System.out.println("No appointments available for Date " + pAppointmentModel.getDate() + " on "
					+ pAppointmentModel.getSession() + " Session");

			// checking availability for doctor
			checkAvalability = databaseDao.checkDoctAvailability(pAppointmentModel);

			// if doctor is available for both sessions then asking user for
			// another session
			if (checkAvalability.trim().equals("Morning-Evening")) {
				if (pAppointmentModel.getSession() == "Morning") {
					System.out.println("Do you want to take appointment for evening session? y/n");
					String choice = utility.inputString();
					if (choice.equals("y")) {
						pAppointmentModel.setSession("Evening");
						this.checkFuction(pAppointmentModel);
					} else {
						System.out.println("Do you want to take appointment for");
					}
				} else {
					System.out.println("Evening");
				}
			} else {
				System.out.println("Appointment for another day");
			}
		} else {

			/*System.out.println(pAppointmentModel.getClinicId() + "," + pAppointmentModel.getPatientId() + ","
					+ pAppointmentModel.getDoctorId() + "," + pAppointmentModel.getSession() + ","
					+ pAppointmentModel.getDate());*/

			databaseDao.takeAppointment(pAppointmentModel);
			System.out.println("Your appointment is done for " + pAppointmentModel.getSession() + " Session on "
					+ pAppointmentModel.getDate());
		}
	}
}
