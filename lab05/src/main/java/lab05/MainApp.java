package lab05;

import lab05.exceptions.InsufficientFundsException;
import lab05.exceptions.InvalidAgeException;
import lab05.model.Wallet;

public class MainApp {

	public static void main(String[] args) {
		try {
			validateAge(17);
		} catch (InvalidAgeException e) {
			e.printStackTrace();
		}
		
		try {
			validateAge(20);
		} catch (InvalidAgeException e) {
			e.printStackTrace();
		}
		
		Wallet wallet = new Wallet("wallet_1", 100.0);
		try {
			wallet.withdraw(30.0);
			System.out.println("Withdrawal done, balance: " + wallet.getBalance());
			wallet.withdraw(500.0);
		} catch (InsufficientFundsException e) {
			System.out.println("Withdrawal failed: " + e.getMessage());
		}
		
	}
	
	public static boolean validateAge(int age) throws InvalidAgeException {
		if(age < Config.MIN_AGE || age < 0) {
			throw new InvalidAgeException(age);
			
		}
		System.out.println("Age valid");
		return true;
	}
}
