package edu.psu.se411.lab07.model;

import java.util.Date;

import edu.psu.se411.lab07.util.Config;
import edu.psu.se411.lab07.util.InvalidArgumentException;
import edu.psu.se411.lab07.util.MissingInformationException;

public class CarRental extends Bookings{

		String carType;
		int rentalDuration; // in days
		double rentalCost; // daily rental rate

		public CarRental(String id, String fullName, Date dateOfTravel, String destination, String carType, int rentalDuration, double rentalCost) {
			super(id, fullName, dateOfTravel, destination);
			this.carType = carType;
			this.rentalDuration = rentalDuration;
			this.rentalCost = rentalCost;
		}

		//the number of days is entered later by the customer
		public CarRental(String id, String fullName, Date dateOfTravel, String destination, String carType, double rentalCost) {
			super(id, fullName, dateOfTravel, destination);
			this.carType = carType;
			this.rentalCost = rentalCost;
		}

		public String getCarType() {
			return carType;
		}

		public void setCarType(String carType) {
			this.carType = carType;
		}

		public int getRentalDuration() {
			return rentalDuration;
		}

		public void setRentalDuration(int rentalDuration) throws InvalidArgumentException {
			if(rentalDuration < Config.MIN_RENTAL_DAYS || rentalDuration > Config.MAX_RENTAL_DAYS) {
				throw new InvalidArgumentException("Number of rental days must be between " + Config.MIN_RENTAL_DAYS + " and " + Config.MAX_RENTAL_DAYS);
			}
			this.rentalDuration = rentalDuration;
		}

		public double getRentalCost() {
			return rentalCost;
		}

		public void setRentalCost(double rentalCost) {
			this.rentalCost = rentalCost;
		}

		@Override
		public double computePrice() throws MissingInformationException {
			if(rentalDuration == 0) { // 0 means the number of days was not provided yet
				throw new MissingInformationException("Number of rental days was not provided");
			}
			return rentalCost * rentalDuration;
		}

	}
