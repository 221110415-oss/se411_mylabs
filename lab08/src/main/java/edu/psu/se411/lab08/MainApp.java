package edu.psu.se411.lab08;

import edu.psu.se411.lab08.component.Dashboard;
import edu.psu.se411.lab08.component.Sensor;
import java.util.Random;

public class MainApp {

	public static void main(String[] args) {
		
		Sensor s1 = new Sensor("Sensor 1");
		Sensor s2 = new Sensor("Sensor 2");
		
		Dashboard d1 = new Dashboard("Dashboard 1");
		
		s1.register(d1);
		s2.register(d1);
		
		Random random = new Random();
		
		for (int i = 0; i < 10; i++) {
            s1.setData(20 + random.nextDouble() * 15);
            s2.setData(40 + random.nextDouble() * 20);


            try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
        }


	}

}
