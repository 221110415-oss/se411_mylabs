package edu.psu.se411.lab12_threads_locks;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Main program using a CACHED thread pool instead of the fixed thread pool.
 * Run it with: mvn clean package exec:java -Dexec.mainClass=edu.psu.se411.lab12_threads_locks.AppCachedPool
 * Optional arguments: nbAccounts nbTransactions [unordered]
 */
public class AppCachedPool {

	static Logger logger = LoggerFactory.getLogger(AppCachedPool.class);

	public static void main(String[] args) {
		logger.info("Application is starting...");
		
		int nbAccounts = args.length > 0 ? Integer.parseInt(args[0]) : 10;
		int nbTransactions = args.length > 1 ? Integer.parseInt(args[1]) : 1000000;
		boolean ordered = !(args.length > 2 && args[2].equals("unordered"));
		
		ExecutorService pool = Executors.newCachedThreadPool();
		System.out.println("Cached thread pool: " + nbAccounts + " accounts, " + nbTransactions + " transactions");
		Simulation.run(pool, nbAccounts, nbTransactions, ordered);
		
		logger.info("Application is closing...");
	}
}
