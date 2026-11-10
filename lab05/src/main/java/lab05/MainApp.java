package lab05;

import lab05.exceptions.InvalidAgeException;

public class MainApp {

	public static void main(String[] args) {
		try {
			validateAge(17);
		} catch (InvalidAgeException e) {
			e.printStackTrace();
		}
		
	}
	
	public static boolean validateAge(int age) throws InvalidAgeException {
		if(age < Config.MIN_AGE || age < 0) {
			throw new InvalidAgeException(age);
			
		}
		return true;
	}
}
