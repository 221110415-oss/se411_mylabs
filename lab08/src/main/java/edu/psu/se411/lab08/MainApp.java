package edu.psu.se411.lab08;

import edu.psu.se411.lab08.component.Dashboard;
import edu.psu.se411.lab08.component.LoggerObserver;
import edu.psu.se411.lab08.component.Sensor;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainApp {

	static Logger logger = LoggerFactory.getLogger(MainApp.class);

	public static void main(String[] args) {
		logger.info("Application is starting...");
		
		Sensor s1 = new Sensor("Sensor 1");
		Sensor s2 = new Sensor("Sensor 2");
		
		Dashboard d1 = new Dashboard("Dashboard 1");
		LoggerObserver l1 = new LoggerObserver("Logger 1");
		
		s1.register(d1);
		s2.register(d1);
		s1.register(l1);
		s2.register(l1);
		
		//a clone has the same state but is not observed by the same observers
		Sensor s3 = s1.clone();
		s3.setName("Sensor 3");
		s3.register(l1);
		
		Random random = new Random();
		
		for (int i = 0; i < 10; i++) {
            s1.setData(20 + random.nextDouble() * 15);
            s2.setData(40 + random.nextDouble() * 20);
            s3.setData(10 + random.nextDouble() * 10);


            try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
        }

		logger.info("Application is closing...");

	}

}
