package edu.psu.se411.lab12_threads_locks.model;

import java.util.concurrent.locks.ReentrantLock;

import edu.psu.se411.lab12_threads_locks.exceptions.InsufficientBalanceException;

public class CryptoAccount {
	private String id;
	private Double balance;
	
	//explicit lock of the account (replaces the "synchronized" keyword)
	private final ReentrantLock lock = new ReentrantLock();
	
	public CryptoAccount(String id, Double balance) {
		this.id = id;
		this.balance = balance;
	}
	
	public void addToBalance(Double amount) {
		if(amount < 0) throw new IllegalArgumentException();
		balance += amount;
	}
	
	public void retrieveFromBalance(Double amount) throws InsufficientBalanceException {
		if (amount <0) throw new IllegalArgumentException();
		if (balance < amount) throw new InsufficientBalanceException("Insufficient funds");
		balance -= amount;
	}
	
	public String getId() {
		return id;
	}
	public Double getBalance() {
		return balance;
	}
	public ReentrantLock getLock() {
		return lock;
	}
	
}
