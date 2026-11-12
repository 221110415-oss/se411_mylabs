package lab05.exceptions;

public class InsufficientFundsException extends Exception {
	private Double balance;
	private Double amount;
	
	public InsufficientFundsException(Double balance, Double amount) {
		super("Insufficient funds: balance is " + balance + " but you tried to withdraw " + amount);
		this.balance = balance;
		this.amount = amount;
	}
	
	public Double getBalance() {
		return balance;
	}
	
	public Double getAmount() {
		return amount;
	}
}
