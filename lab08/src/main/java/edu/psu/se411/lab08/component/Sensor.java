package edu.psu.se411.lab08.component;

import java.util.ArrayList;
import java.util.List;

import edu.psu.se411.lab08.observer.IObserver;
import edu.psu.se411.lab08.observer.ISubject;

public class Sensor implements ISubject {
	
	//when you clone this later only clone the state of the sensor, not the observers
	//State of the sensor
	private String name; //must be unique
	private double data = 0;
	
	private List<IObserver> observers = new ArrayList<IObserver>();
	
	@Override
	public Sensor clone() {
		try {
			Sensor s = (Sensor) super.clone();
			s.observers = new ArrayList<IObserver>();
			return s;
		}
		catch (CloneNotSupportedException e) {
			throw new AssertionError();
		}
	}
	
	
	public Sensor(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getData() {
		return data;
	}
	public void setData(double data) {
		this.data = data;
		notifyObservers();
	}

	@Override
	public void register(IObserver o) {
		observers.add(o);
		
	}

	@Override
	public void unregister(IObserver o) {
		observers.remove(o);
		
	}

	@Override
	public void notifyObservers() {
		for(IObserver o: observers) {
			o.update(this);
		}
		
	}
	
	

}
