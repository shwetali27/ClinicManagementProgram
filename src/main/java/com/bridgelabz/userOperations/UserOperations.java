package com.bridgelabz.userOperations;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.bridgelabz.dao.DatabaseDao;
import com.bridgelabz.factory.DaoFactory;
import com.bridgelabz.model.AppointmentModel;
import com.bridgelabz.utility.Utility;

public class UserOperations {

	// objects for class
	DaoFactory daoFactory = new DaoFactory();

	DatabaseDao databaseDao = daoFactory.getDatabaseImpl("mysql");
	Utility utility = new Utility();

	private String patientAvailabilityChoice;

	// setting the patients's first session choice
	public void setSessionChoice(String patientAvailabilityChoice) {
		this.patientAvailabilityChoice = patientAvailabilityChoice;
	}

	public void checkFuction(AppointmentModel pAppointmentModel) {
		try {
			// objects for variables
			String checkAvalability;
			char choice;
			// System.out.println("patientAvailabilityChoice:"+patientAvailabilityChoice);
			int valid = databaseDao.checkAppointment(pAppointmentModel);

			// if that patient had already taken an appointment for session
			if (valid == 1) {
				System.out.println("Appointment is already taken for Date " + pAppointmentModel.getDate() + " on "
						+ pAppointmentModel.getSession() + " Session");
			}

			// if no appointments are available for particular doctor on
			// particular time
			else if (valid == 2) {
				System.out.println("No appointments available for Date " + pAppointmentModel.getDate() + " on "
						+ pAppointmentModel.getSession() + " Session");

				// checking availability for doctor
				checkAvalability = databaseDao.checkDoctAvailability(pAppointmentModel);
				boolean b = true;
				// if doctor is available for both sessions then asking user for
				// another session
				if (checkAvalability.trim().equals("Morning-Evening")) {
					// if patient's session is Morning then asking for Evening
					// Session
					if (pAppointmentModel.getSession() == "Morning") {
						pAppointmentModel.setSession("Evening");
						// checking for availability before asking
						valid = databaseDao.checkAppointment(pAppointmentModel);
						if (valid != 2) {
							System.out.println("Do you want to take appointment for Evening session? y/n");
							choice = utility.inputChar();
							if (choice == 'y') {
								b = false;
								this.checkFuction(pAppointmentModel);
							} else {
								pAppointmentModel.setSession(patientAvailabilityChoice);
								System.out.println("Do you want to take appointment for next day?? y/n");
								choice = utility.inputChar();
								if (choice == 'y') {

									DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
									Calendar calender = Calendar.getInstance();
									calender.setTime(dateFormat.parse(pAppointmentModel.getDate()));
									calender.add(Calendar.DATE, 1);
									String nextDay = dateFormat.format(calender.getTime());
									pAppointmentModel.setDate(nextDay);
									b = false;
									this.checkFuction(pAppointmentModel);

								} else {
									b = false;
									System.out.println("Thanks for Visit");
								}
							}
						}
					}
					// if patient's session is Evening then asking for Morning
					// Session
					else {
						pAppointmentModel.setSession("Morning");
						// checking for availability before asking
						valid = databaseDao.checkAppointment(pAppointmentModel);
						if (valid != 2) {
							System.out.println("Do you want to take appointment for Morning session? y/n");
							choice = utility.inputChar();
							if (choice == 'y') {
								b = false;
								this.checkFuction(pAppointmentModel);
							} else {
								pAppointmentModel.setSession(patientAvailabilityChoice);
								System.out.println("Do you want to take appointment for next day?? y/n");
								choice = utility.inputChar();
								if (choice == 'y') {
									DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
									Calendar calender = Calendar.getInstance();
									calender.setTime(dateFormat.parse(pAppointmentModel.getDate()));
									calender.add(Calendar.DATE, 1);
									String nextDay = dateFormat.format(calender.getTime());
									pAppointmentModel.setDate(nextDay);
									b = false;
									this.checkFuction(pAppointmentModel);

								} else {
									b = false;
									System.out.println("Thanks for Visit");
								}
							}
						}
					}
				}
				// if doctor is not available for both sessions then asking for
				// next day session
				if (b) {

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

			// adding the appointment for patient
			else {

				System.out.println(pAppointmentModel.getClinicId() + "," + pAppointmentModel.getPatientId() + ","
						+ pAppointmentModel.getDoctorId() + "," + pAppointmentModel.getSession() + ","
						+ pAppointmentModel.getDate());

				databaseDao.takeAppointment(pAppointmentModel);
				System.out.println("Your appointment is done for " + pAppointmentModel.getSession() + " Session on "
						+ pAppointmentModel.getDate());
			}
		} catch (ParseException e) {
			System.out.println(e);
		}
	}
}
