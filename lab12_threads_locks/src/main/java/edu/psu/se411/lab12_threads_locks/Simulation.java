package edu.psu.se411.lab12_threads_locks;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import edu.psu.se411.lab12_threads_locks.exceptions.InsufficientBalanceException;
import edu.psu.se411.lab12_threads_locks.model.CryptoAccount;
import edu.psu.se411.lab12_threads_locks.model.Transaction;

/**
 * Code shared by the two main programs (fixed thread pool and cached thread pool).
 */
public class Simulation {

	public static final Double initialBalance = 1000.0;

	/**
	 * @param pool            the thread pool that executes the transactions
	 * @param nbAccounts      number of accounts
	 * @param nbTransactions  number of transactions
	 * @param ordered         true: locks are taken with resource ordering, false: locks taken sender -> receiver (can deadlock)
	 */
	public static void run(ExecutorService pool, int nbAccounts, int nbTransactions, boolean ordered) {
		CryptoAccount[] accounts = new CryptoAccount[nbAccounts];
		for (int i = 0; i < nbAccounts; i++) {
			accounts[i] = new CryptoAccount("acc_" + i, initialBalance);
		}
		System.out.println("Total balance before transactions: " + getTotalBalance(accounts));
		
		AtomicInteger failed = new AtomicInteger();
		long start = System.currentTimeMillis();
		
		for (int i = 0; i < nbTransactions; i++) {
			CryptoAccount sender = accounts[(int)(Math.random() * nbAccounts)];
			CryptoAccount receiver = accounts[(int)(Math.random() * nbAccounts)];
			if(sender == receiver) {
				//skip this transaction
				i--;
				continue;
			}
			double amount = (double)(Math.random() * 10 + 1);
			Transaction t = new Transaction(sender, receiver, amount);
			pool.submit(() -> {
				try {
					if(ordered) {
						t.execute();
					} else {
						t.executeUnordered();
					}
				} catch (InsufficientBalanceException e) {
					failed.incrementAndGet();
				}
			});
		}
		
		pool.shutdown();
		try {
			if(!pool.awaitTermination(60, TimeUnit.SECONDS)) {
				ThreadMXBean bean = ManagementFactory.getThreadMXBean();
				long[] deadlocked = bean.findDeadlockedThreads();
				System.out.println("Not finished after 60s, deadlocked threads: " + (deadlocked == null ? 0 : deadlocked.length));
				System.exit(1);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		long elapsed = System.currentTimeMillis() - start;
		System.out.println("Total balance after transactions: " + getTotalBalance(accounts));
		System.out.println("Failed transactions (insufficient funds): " + failed.get());
		System.out.println("Time: " + elapsed + " ms");
	}
	
	public static double getTotalBalance(CryptoAccount[] accounts) {
		Double totalBalance = 0.0;
		for (CryptoAccount account : accounts) {
			totalBalance += account.getBalance();
		}
		return totalBalance;
	}
}
