package edu.psu.se411.lab11_threads;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.psu.se411.lab11_threads.model.CryptoAccount;
import edu.psu.se411.lab11_threads.model.Transaction;

/**
 * Build and run using Maven goal: clean package exec:java
 */
public class App {

	static Logger logger = LoggerFactory.getLogger(App.class);

	public static Integer nbAccounts = 10;
	public static Double initialBalance = 1000.0;
	public static Integer nbTransactions = 10000;

	public static void main(String[] args) {
		logger.info("Application is starting...");
		
		// 1- Create accounts
		CryptoAccount[] accounts = new CryptoAccount[nbAccounts];
		for (Integer i = 0; i < nbAccounts; i++) {
			accounts[i] = new CryptoAccount("acc_" + i.toString(), initialBalance);
		}
		System.out.println("Total balance before transactions: " + getTotalBalance(accounts));
		// 2- create all transactions in separate threads
		Thread[] threads = new Thread[nbTransactions];
		for(int i = 0; i < nbTransactions; i++) {
			CryptoAccount sender = accounts[(int)(Math.random() * nbAccounts)];
			CryptoAccount receiver = accounts[(int)(Math.random() * nbAccounts)];
			if(sender == receiver) {
				//skip this transaction
				i--;
				continue;
			}
			double amount = (double)(Math.random() * 10 + 1);
			Transaction t = new Transaction("Trx_" + i, sender, receiver, amount);
			Thread thread = new Thread(t);
			threads[i] = thread;
			thread.start();
		}
		
		//3- wait for all threads to finish
		for(int i = 0; i < threads.length; i++) {
			try{ 
				threads[i].join();
			}
			catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		//4- verify that we still have the same total balance in the monetary system
		System.out.println("Total balance after transactions: " + getTotalBalance(accounts));		

		logger.info("Application is closing...");
	}
	public static double getTotalBalance(CryptoAccount[] accounts) {
		Double totalBalance = 0.0;
		for (CryptoAccount account : accounts) {
			totalBalance += account.getBalance();
		}
		return totalBalance;
	}
}
