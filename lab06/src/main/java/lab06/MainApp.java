package lab06;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lab06.exceptions.InsufficientFundsException;
import lab06.exceptions.InvalidAgeException;
import lab06.model.Wallet;

public class MainApp {

	static Logger logger = LoggerFactory.getLogger(MainApp.class);

	public static void main(String[] args) {
		logger.info("Application is starting...");
		
		try {
			validateAge(17);
		} catch (InvalidAgeException e) {
			logger.error("Invalid age", e);
			e.printStackTrace();
		}
		
		try {
			validateAge(20);
		} catch (InvalidAgeException e) {
			logger.error("Invalid age", e);
			e.printStackTrace();
		}
		
		Wallet wallet = new Wallet("wallet_1", 100.0);
		try {
			wallet.withdraw(30.0);
			System.out.println("Withdrawal done, balance: " + wallet.getBalance());
			wallet.withdraw(500.0);
		} catch (InsufficientFundsException e) {
			logger.error("Withdrawal failed", e);
			System.out.println("Withdrawal failed: " + e.getMessage());
		}
		
		logger.info("Application is closing...");
		
	}
	
	public static boolean validateAge(int age) throws InvalidAgeException {
		if(age < Config.MIN_AGE || age < 0) {
			throw new InvalidAgeException(age);
			
		}
		System.out.println("Age valid");
		return true;
	}
}
