package lab06.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lab06.exceptions.InsufficientFundsException;

public class Wallet {
	
	static Logger logger = LoggerFactory.getLogger(Wallet.class);
	
	private String walletId;
	private Double balance;
	
	public Wallet(String walletId, Double balance) {
		super();
		setWalletId(walletId);
		setBalance(balance);
		logger.debug("Wallet account created: {} with balance {}", walletId, balance);
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
		logger.debug("Deposit of {} into wallet {}, new balance {}", amount, walletId, balance);
	}
	
	//withdraws money from the wallet to the owner's bank account
	public void withdraw(Double amount) throws InsufficientFundsException {
		if(amount == null || amount <= 0) {
			throw new IllegalArgumentException("Withdraw amount must be positive and non-null");
		}
		if(this.balance < amount) {
			InsufficientFundsException e = new InsufficientFundsException(this.balance, amount);
			logger.error("Withdrawal from wallet {} failed, throwing exception: {}", walletId, e.getMessage());
			throw e;
		}
		this.balance -= amount;
		logger.debug("Withdraw of {} from wallet {}, new balance {}", amount, walletId, balance);
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
