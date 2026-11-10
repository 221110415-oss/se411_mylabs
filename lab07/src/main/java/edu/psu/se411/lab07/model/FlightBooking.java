package edu.psu.se411.lab07.model;

import java.util.Date;

import edu.psu.se411.lab07.util.Config;

public class FlightBooking extends Bookings{
	private double basePrice;
	private double luggageWeight;
	public FlightBooking(String id, String fullName, Date dateOfTravel, String destination, double basePrice, double luggageWeight) {
		super(id, fullName, dateOfTravel, destination);
		this.basePrice = basePrice;
		this.luggageWeight = luggageWeight;
	}
	
	@Override
	public double computePrice() {
		return basePrice + (luggageWeight * Config.EXTRA_LUGGAGE_RATE);
	}

	public double getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(double basePrice) {
		this.basePrice = basePrice;
	}

	public double getLuggageWeight() {
		return luggageWeight;
	}

	public void setLuggageWeight(double luggageWeight) {
		if(luggageWeight < 0 || luggageWeight > 40) {
			throw InvalidArgumentException("Luggage weight must be between 0 and 40 pounds");
		}
		this.luggageWeight = luggageWeight;
	}
	
}
