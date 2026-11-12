package edu.psu.se411.lab12_threads_locks.model;

import java.util.concurrent.atomic.AtomicInteger;

import edu.psu.se411.lab12_threads_locks.exceptions.InsufficientBalanceException;

public class Transaction {
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
	
	/**
	 * Solution (resource ordering): there is a total order on the accounts (their id),
	 * the locks are always taken in ascending order, so two transactions can never wait for each other.
	 */
	public void execute() throws InsufficientBalanceException {
		CryptoAccount first = sender;
		CryptoAccount second = receiver;
		if(sender.getId().compareTo(receiver.getId()) > 0) {
			first = receiver;
			second = sender;
		}
		first.getLock().lock();
		try {
			second.getLock().lock();
			try {
				transfer();
			} finally {
				second.getLock().unlock();
			}
		} finally {
			first.getLock().unlock();
		}
	}
	
	/**
	 * What could go wrong? The locks are taken in the order sender -> receiver.
	 * If a transaction A -> B and a transaction B -> A run at the same time, each one holds
	 * the first lock and waits forever for the second one: DEADLOCK.
	 */
	public void executeUnordered() throws InsufficientBalanceException {
		sender.getLock().lock();
		try {
			receiver.getLock().lock();
			try {
				transfer();
			} finally {
				receiver.getLock().unlock();
			}
		} finally {
			sender.getLock().unlock();
		}
	}
	
	private void transfer() throws InsufficientBalanceException {
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
	public CryptoAccount getReceiver() {
		return receiver;
	}
	public Double getAmount() {
		return amount;
	}
	public String getId() {
		return id;
	}
}
