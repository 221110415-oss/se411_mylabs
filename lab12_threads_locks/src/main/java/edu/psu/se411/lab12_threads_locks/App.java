package edu.psu.se411.lab12_threads_locks;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Main program using a FIXED thread pool.
 * Build and run using Maven goal: clean package exec:java
 * Optional arguments: nbAccounts nbTransactions [unordered]
 *   e.g. -Dexec.args="1000 1000000"   (case a)
 *        -Dexec.args="10 1000000"     (case b)
 *        -Dexec.args="10 1000000 unordered" (locks without resource ordering, can deadlock)
 */
public class App {

	static Logger logger = LoggerFactory.getLogger(App.class);

	public static void main(String[] args) {
		logger.info("Application is starting...");
		
		int nbAccounts = args.length > 0 ? Integer.parseInt(args[0]) : 10;
		int nbTransactions = args.length > 1 ? Integer.parseInt(args[1]) : 1000000;
		boolean ordered = !(args.length > 2 && args[2].equals("unordered"));
		
		ExecutorService pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
		System.out.println("Fixed thread pool: " + nbAccounts + " accounts, " + nbTransactions + " transactions");
		Simulation.run(pool, nbAccounts, nbTransactions, ordered);
		
		logger.info("Application is closing...");
	}
}
