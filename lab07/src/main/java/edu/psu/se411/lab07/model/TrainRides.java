package edu.psu.se411.lab07.model;

import java.util.Date;

import edu.psu.se411.lab07.util.Config;
import edu.psu.se411.lab07.util.InvalidArgumentException;
import edu.psu.se411.lab07.util.MissingInformationException;
import edu.psu.se411.lab07.util.SeatClass;

public class TrainRides extends Bookings {

	private SeatClass seatClass;
	private int distance;
	
	public TrainRides(String id, String fullName, Date dateOfTravel, String destination) {
		super(id, fullName, dateOfTravel, destination);
		setSeatClass(seatClass);
		
		}
	
	public TrainRides(String id, String fullName, Date dateOfTravel, String destination, SeatClass seatClass) {
		super(id, fullName, dateOfTravel, destination);
		setSeatClass(seatClass);
	}

	@Override
	public double computePrice() throws MissingInformationException {
		if(distance == 0) { // 0 means the distance was not provided yet
			throw new MissingInformationException("Distance was not provided");
		}
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

	public void setDistance(int distance) throws InvalidArgumentException {
		if(distance < Config.MIN_TRAIN_DISTANCE || distance > Config.MAX_TRAIN_DISTANCE) {
			throw new InvalidArgumentException("Distance must be between " + Config.MIN_TRAIN_DISTANCE + " and " + Config.MAX_TRAIN_DISTANCE + " km");
		}
		this.distance = distance;
	}
	
}
