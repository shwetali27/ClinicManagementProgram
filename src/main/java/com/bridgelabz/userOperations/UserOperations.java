package com.bridgelabz.userOperations;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.bridgelabz.dao.DatabaseDao;
import com.bridgelabz.dao.DatabaseDaoImpl;
import com.bridgelabz.model.AppointmentModel;
import com.bridgelabz.utility.Utility;

public class UserOperations {

	// objects for class
	DatabaseDao databaseDao = new DatabaseDaoImpl();
	Utility utility = new Utility();

	private String patientAvailabilityChoice;
	
	public void setSessionChoice(String patientAvailabilityChoice){
		this.patientAvailabilityChoice = patientAvailabilityChoice; 
	}
	
	public void checkFuction(AppointmentModel pAppointmentModel){
		// objects for variables
		try {
			String checkAvalability;
			char choice;
			//System.out.println("patientAvailabilityChoice:"+patientAvailabilityChoice);
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
					//if patient's session is Morning then asking for Evening Session
					if (pAppointmentModel.getSession() == "Morning") {
						System.out.println("Do you want to take appointment for Evening session? y/n");
						choice = utility.inputChar();
						if (choice == 'y') {
							pAppointmentModel.setSession("Evening");
							this.checkFuction(pAppointmentModel);
						} else {
							System.out.println("Do you want to take appointment for next day?? y/n");
							choice = utility.inputChar();
							if (choice == 'y') {

								pAppointmentModel.setSession(patientAvailabilityChoice);
								DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
								Calendar calender = Calendar.getInstance();
								calender.setTime(dateFormat.parse(pAppointmentModel.getDate()));
								calender.add(Calendar.DATE, 1);
								String nextDay = dateFormat.format(calender.getTime());
								pAppointmentModel.setDate(nextDay);
								this.checkFuction(pAppointmentModel);

							} else {
								System.out.println("Thanks for Visit");
							}
						}
					}
					//if patient's session is Evening then asking for Morning Session
					else {

						System.out.println("Do you want to take appointment for Morning session? y/n");
						choice = utility.inputChar();
						if (choice == 'y') {
							pAppointmentModel.setSession("Morning");
							this.checkFuction(pAppointmentModel);
						} else {
							System.out.println("Do you want to take appointment for next day?? y/n");
							choice = utility.inputChar();
							if (choice == 'y') {

								pAppointmentModel.setSession(patientAvailabilityChoice);
								DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
								Calendar calender = Calendar.getInstance();
								calender.setTime(dateFormat.parse(pAppointmentModel.getDate()));
								calender.add(Calendar.DATE, 1);
								String nextDay = dateFormat.format(calender.getTime());
								pAppointmentModel.setDate(nextDay);
								this.checkFuction(pAppointmentModel);

							} else {
								System.out.println("Thanks for Visit");
							}
						}
					
					}
				}
				// if doctor is not available for both sessions then asking for next day session
				else {

					System.out.println("Do you want to take appointment for next day?? y/n");
					choice = utility.inputChar();
					if (choice == 'y') {

						pAppointmentModel.setSession(patientAvailabilityChoice);
						DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
						Calendar calender = Calendar.getInstance();
						calender.setTime(dateFormat.parse(pAppointmentModel.getDate()));
						calender.add(Calendar.DATE, 1);
						String nextDay = dateFormat.format(calender.getTime());
						pAppointmentModel.setDate(nextDay);
						this.checkFuction(pAppointmentModel);

					} else {
						System.out.println("Thanks for Visit");
					}
				
				}
			} else {

				/*System.out.println(pAppointmentModel.getClinicId() + "," + pAppointmentModel.getPatientId() + ","
						+ pAppointmentModel.getDoctorId() + "," + pAppointmentModel.getSession() + ","
						+ pAppointmentModel.getDate());*/

				databaseDao.takeAppointment(pAppointmentModel);
				System.out.println("Your appointment is done for " + pAppointmentModel.getSession() + " Session on "
						+ pAppointmentModel.getDate());
			}
		} catch (ParseException e) {
			System.out.println(e);
		}
	}
}
