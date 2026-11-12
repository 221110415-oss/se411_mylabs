package edu.psu.se411.lab07.model;

import java.util.Date;

import edu.psu.se411.lab07.util.MissingInformationException;

public abstract class Bookings { //not an interface because it has fields and methods????
	private String id;
	private String fullName;
	private Date dateOfTravel;
	private String destination;

	public abstract double computePrice() throws MissingInformationException;

public Bookings(String id, String fullName, Date dateOfTravel, String destination) {
	this.id = id;
	this.fullName = fullName;
	this.dateOfTravel = dateOfTravel;
	this.destination = destination;
}
	public void setDateOfTravel(Date dateOfTravel) {
		if(dateOfTravel == null || dateOfTravel.before(new Date())) {
			throw new IllegalArgumentException("Date cannot be null or in the past");
		}
		this.dateOfTravel = dateOfTravel;
	}

	public Date getDate() {
		return dateOfTravel;
	}
	
	public void setDestination(String destination) {
		if(destination == null || destination.isEmpty()) {
			throw new IllegalArgumentException("Destination cannot be null or empty");
		}
		this.destination = destination;
	}
	
	public String getDestination() {
		return destination;
	}
	
	public void setFullName(String fullName) {
		if(fullName == null || fullName.isEmpty()) {
			throw new IllegalArgumentException("Full name cannot be null or empty");
		}
		this.fullName = fullName;
	}
	
	public String getFullName() {
		return fullName;
	}
	
	public void setId(String id) {
		if(id == null || id.isEmpty()) {
			throw new IllegalArgumentException("ID cannot be null or empty");
		}
		this.id = id;
	}
	
	public String getId() {
		return id;
	}
}
