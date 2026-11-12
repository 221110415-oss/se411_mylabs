package edu.psu.se411.lab07;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.psu.se411.lab07.model.Bookings;
import edu.psu.se411.lab07.model.CarRental;
import edu.psu.se411.lab07.model.FlightBooking;
import edu.psu.se411.lab07.model.TrainRides;
import edu.psu.se411.lab07.util.InvalidArgumentException;
import edu.psu.se411.lab07.util.MissingInformationException;
import edu.psu.se411.lab07.util.SeatClass;

/**
 * Build and run using Maven goal: clean package exec:java
 */
public class MainApp {

	static Logger logger = LoggerFactory.getLogger(MainApp.class);

	//works for any type of booking (polymorphism)
	public static double computeTotalPrice(Bookings booking) throws MissingInformationException {
		return booking.computePrice();
	}

	public static void main(String[] args) {
		logger.info("Application is starting...");
		
		Date travelDate = new Date();
		List<Bookings> bookings = new ArrayList<Bookings>();
		
		FlightBooking flight = new FlightBooking("F1", "Salah Ahmed", travelDate, "Riyadh", 500.0);
		TrainRides train = new TrainRides("T1", "Sara Ali", travelDate, "Dammam", SeatClass.FIRST_CLASS);
		CarRental car = new CarRental("C1", "Omar Alrajhi", travelDate, "Jeddah", "SUV", 150.0);
		bookings.add(flight);
		bookings.add(train);
		bookings.add(car);
		
		//1- information that has not been entered yet
		for(Bookings b : bookings) {
			try {
				System.out.println(b.getId() + " total price: " + computeTotalPrice(b));
			} catch (MissingInformationException e) {
				logger.error("Missing information for booking " + b.getId(), e);
				System.out.println(b.getId() + " failed: " + e.getMessage());
			}
		}
		
		//2- invalid information
		try {
			flight.setLuggageWeight(55);
		} catch (InvalidArgumentException e) {
			logger.error("Invalid luggage weight for booking " + flight.getId(), e);
			System.out.println(flight.getId() + " failed: " + e.getMessage());
		}
		try {
			train.setDistance(2500);
		} catch (InvalidArgumentException e) {
			logger.error("Invalid distance for booking " + train.getId(), e);
			System.out.println(train.getId() + " failed: " + e.getMessage());
		}
		try {
			car.setRentalDuration(0);
		} catch (InvalidArgumentException e) {
			logger.error("Invalid rental duration for booking " + car.getId(), e);
			System.out.println(car.getId() + " failed: " + e.getMessage());
		}
		
		//3- valid information entered later
		try {
			flight.setLuggageWeight(25);
			train.setDistance(400);
			car.setRentalDuration(5);
		} catch (InvalidArgumentException e) {
			logger.error("Invalid argument", e);
		}
		
		for(Bookings b : bookings) {
			try {
				System.out.println(b.getId() + " total price: " + computeTotalPrice(b));
			} catch (MissingInformationException e) {
				logger.error("Missing information for booking " + b.getId(), e);
				System.out.println(b.getId() + " failed: " + e.getMessage());
			}
		}
		
		logger.info("Application is closing...");
	}

}
