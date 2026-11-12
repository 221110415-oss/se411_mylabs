package edu.psu.se411.lab07.model;

import java.util.Date;

import edu.psu.se411.lab07.util.Config;
import edu.psu.se411.lab07.util.InvalidArgumentException;
import edu.psu.se411.lab07.util.MissingInformationException;

public class FlightBooking extends Bookings{
	private double basePrice;
	private Double luggageWeight; // null until the customer enters it
	public FlightBooking(String id, String fullName, Date dateOfTravel, String destination, double basePrice, double luggageWeight) {
		super(id, fullName, dateOfTravel, destination);
		this.basePrice = basePrice;
		this.luggageWeight = luggageWeight;
	}
	
	//the luggage weight is entered later by the customer
	public FlightBooking(String id, String fullName, Date dateOfTravel, String destination, double basePrice) {
		super(id, fullName, dateOfTravel, destination);
		this.basePrice = basePrice;
	}
	
	@Override
	public double computePrice() throws MissingInformationException {
		if(luggageWeight == null) {
			throw new MissingInformationException("Luggage weight was not provided");
		}
		return basePrice + (luggageWeight * Config.EXTRA_LUGGAGE_RATE);
	}

	public double getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(double basePrice) {
		this.basePrice = basePrice;
	}

	public Double getLuggageWeight() {
		return luggageWeight;
	}

	public void setLuggageWeight(double luggageWeight) throws InvalidArgumentException {
		if(luggageWeight < 0 || luggageWeight > Config.MAX_LUGGAGE_WEIGHT) {
			throw new InvalidArgumentException("Luggage weight must be between 0 and " + Config.MAX_LUGGAGE_WEIGHT + " kg");
		}
		this.luggageWeight = luggageWeight;
	}
	
}
