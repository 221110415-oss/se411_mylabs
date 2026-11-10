package edu.psu.se411.lab07.model;

import java.sql.Date;

public class CarRental extends Bookings{

		String carType;
		int rentalDuration; // in days
		double rentalCost;

		public CarRental(String id, String fullName, Date dateOfTravel, String destination, String carType, int rentalDuration, double rentalCost) {
			this.id = id;
			this.fullName = fullName;
			this.dateOfTravel = dateOfTravel;
			this.destination = destination;
			this.carType = carType;
			this.rentalDuration = rentalDuration;
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

		public void setRentalDuration(int rentalDuration) {
			this.rentalDuration = rentalDuration;
		}

		public double getRentalCost() {
			return rentalCost;
		}

		public void setRentalCost(double rentalCost) {
			this.rentalCost = rentalCost;
		}

		@Override
		public double computePrice() {
			// TODO Auto-generated method stub
			return 0;
		}

	}

}
