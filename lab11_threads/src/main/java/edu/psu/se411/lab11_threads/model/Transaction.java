package edu.psu.se411.lab11_threads.model;

import java.util.concurrent.atomic.AtomicInteger;

import edu.psu.se411.lab11_threads.exceptions.InsufficientBalanceException;

public class Transaction implements Runnable{
	private String id;
	private CryptoAccount sender;
	private CryptoAccount receiver;
	private Double amount;
	
	private static final AtomicInteger counter = new AtomicInteger();
	
	public Transaction(CryptoAccount sender, CryptoAccount receiver, Double amount) {
		this("Trx_" + counter.incrementAndGet(), sender, receiver, amount);
	}
	
	public Transaction(String id, CryptoAccount sender, CryptoAccount receiver, Double amount) {
		super();
		if(id == null || sender == null || receiver == null || amount < 0) throw new IllegalArgumentException();
		this.id = id;
		this.sender = sender;
		this.receiver = receiver;
		this.amount = amount;
	}
	@Override
	public void run() {
		try {
			execute();
		} catch (InsufficientBalanceException e) {
			System.out.println("Transaction " + id + " failed: " + e.getMessage());
		}
		
	}
	
	public void execute() throws InsufficientBalanceException {
		transfer(sender, receiver, amount);
	}
	
	//only one transfer at a time in the whole system, so no balance can be corrupted
	private static synchronized void transfer(CryptoAccount sender, CryptoAccount receiver, Double amount) throws InsufficientBalanceException {
		if(sender.getBalance() >= amount) {
			sender.retrieveFromBalance(amount);
			receiver.addToBalance(amount);
		} else {
			throw new InsufficientBalanceException("Insufficient funds in sender account");
		}
	}
	
	public CryptoAccount getSender() {
		return sender;
	}
	public void setSender(CryptoAccount sender) {
		this.sender = sender;
	}
	public CryptoAccount getReceiver() {
		return receiver;
	}
	public void setReceiver(CryptoAccount receiver) {
		this.receiver = receiver;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public String getId() {
		return id;
	}
}
