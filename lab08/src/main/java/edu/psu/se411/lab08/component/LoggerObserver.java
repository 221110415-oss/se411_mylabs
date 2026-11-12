package edu.psu.se411.lab08.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.psu.se411.lab08.observer.IObserver;
import edu.psu.se411.lab08.observer.ISubject;

public class LoggerObserver implements IObserver {
	
	static Logger logger = LoggerFactory.getLogger(LoggerObserver.class);
	
	private String name;
	
	public LoggerObserver(String name) {
		this.name = name;
	}
	
	@Override
	public void update(ISubject subject) {
		if(subject instanceof Sensor) {
			Sensor sensor = (Sensor) subject;
			System.out.printf("Logger %s: %s changed to %.2f%n", name, sensor.getName(), sensor.getData());
			logger.info("{} reading changed to {}", sensor.getName(), sensor.getData());
		}
	}

}
