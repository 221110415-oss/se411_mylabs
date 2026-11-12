package lab06.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InsufficientFundsException extends Exception {
	static Logger logger = LoggerFactory.getLogger(InsufficientFundsException.class);
	
	private Double balance;
	private Double amount;
	
	public InsufficientFundsException(Double balance, Double amount) {
		super("Insufficient funds: balance is " + balance + " but you tried to withdraw " + amount);
		this.balance = balance;
		this.amount = amount;
		logger.warn("InsufficientFundsException object created: balance={}, amount={}", balance, amount);
	}
	
	public Double getBalance() {
		return balance;
	}
	
	public Double getAmount() {
		return amount;
	}
}
