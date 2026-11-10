package edu.psu.se411.lab07.model;

import java.util.Date;

import edu.psu.se411.lab07.util.Config;
import edu.psu.se411.lab07.util.SeatClass;

public class TrainRides extends Bookings {

	private SeatClass seatClass;
	private int distance;
	
	public TrainRides(String id, String fullName, Date dateOfTravel, String destination) {
		super(id, fullName, dateOfTravel, destination);
		setSeatClass(seatClass);
		
		}
	

	@Override
	public double computePrice() {
		if(seatClass == seatClass.STANDARD) {
			return distance * Config.TRAIN_STANDARD_RATE;
		} else if (seatClass == seatClass.FIRST_CLASS) {
			return distance * Config.TRAIN_FIRST_CLASS_RATE;
		} else {
			throw new IllegalArgumentException("Invalid seat class");
		}
	}

	public SeatClass getSeatClass() {
		return seatClass;
	}

	public void setSeatClass(SeatClass seatClass) {
		this.seatClass = seatClass;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}
	
}
