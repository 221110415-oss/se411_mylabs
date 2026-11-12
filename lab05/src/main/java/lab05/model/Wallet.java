package lab05.model;

import lab05.exceptions.InsufficientFundsException;

public class Wallet {
	
	private String walletId;
	private Double balance;
	
	public Wallet(String walletId, Double balance) {
		super();
		setWalletId(walletId);
		setBalance(balance);
	}
	
	public void transferTo(Wallet receiver, Double amount) {
		if(receiver == null) {
			throw new IllegalArgumentException("Receiver wallet cannot be null");
		}
		if(amount == null || amount <= 0) {
			throw new IllegalArgumentException("Transfer amount must be positive and non-null");
		}
		if(this.balance < amount) {
			throw new IllegalArgumentException("Insufficient funds for transfer");
		}
		
		this.balance -= amount; //balance is not going through the setter, could cause issues
		receiver.balance += amount;
	}
	
	
	
	public void deposit(Double amount) {
		if(amount == null || amount <= 0) {
			throw new IllegalArgumentException("Deposit amount must be positive and non-null");
		}
		this.balance += amount;
	}
	
	//withdraws money from the wallet to the owner's bank account
	public void withdraw(Double amount) throws InsufficientFundsException {
		if(amount == null || amount <= 0) {
			throw new IllegalArgumentException("Withdraw amount must be positive and non-null");
		}
		if(this.balance < amount) {
			throw new InsufficientFundsException(this.balance, amount);
		}
		this.balance -= amount;
	}
	
	public void setWalletId(String walletId) {
		if(walletId == null || walletId.isEmpty()) {
			throw new IllegalArgumentException("Wallet ID cannot be null or empty");
		}
		this.walletId = walletId;
	}
	
	public void setBalance(Double balance) {
		if(balance == null || balance < 0) {
			throw new IllegalArgumentException("Balance cannot be negative or null");
		}
		this.balance = balance;
	}
	
	public String getWalletId() {
		return walletId;
	}
	
	public Double getBalance() {
		return balance;
	}
}
