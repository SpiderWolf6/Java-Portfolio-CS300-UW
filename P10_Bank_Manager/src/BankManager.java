//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Bank Manager
// Course:   CS 300 Fall 2024
//
// Author:   Soham Mukherjee
// Email:    smukherjee39@wisc.edu
// Lecturer: Blerina Gkotse
//

import java.util.NoSuchElementException;

/**
 * This class manages different transaction heaps with different priorities, including
 * adding, removing, and peeking transactions
 */
public class BankManager {
	protected TransactionHeap low;
	protected TransactionHeap medium;
	protected TransactionHeap high;

	 /**
     * Constructor for the BankManager class
     * Initializes low, medium, and high transaction heaps corresponding to the
     * different levels of priority
     * @param capacity   The max limit of the number of transactions in each heap
     * @throws None
     */
	public BankManager(int capacity) {
		low = new TransactionHeap(capacity);
		medium = new TransactionHeap(capacity);
		high = new TransactionHeap(capacity);
	}

	/**
	 * Gets and removes the next transaction from the priority queues. Take the
	 * transaction from the highest available priority queue (high -> medium ->
	 * low).
	 * 
	 * @return The next transaction to process, null if there are no transactions.
	 */
	public Transaction getNextTransaction() {
	  if(high.getSize() != 0) {
	    return high.getNextTransaction(); // get next transaction from high priority heap
	  }
	  else if (medium.getSize() != 0) {
	    return medium.getNextTransaction(); // get next transaction from medium priority heap
	  }
	  else if (low.getSize() != 0) {
	    return low.getNextTransaction(); // get next transaction from low priority heap
	  }
	  return null; // no transactions to process
	}

	/**
	 * Gets the highest priority transaction from the priority queues without
	 * removing it. Take the transaction from the highest available priority queue
	 * (high -> medium -> low).
	 * 
	 * @return the transaction with highest priority from all heaps and null if
	 *         there are no transactions.
	 */
	public Transaction peekHighestPriorityTransaction() {
      if(high.getSize() != 0) {
        return high.peek(); // peek next transaction from high priority heap
      }
      else if (medium.getSize() != 0) {
        return medium.peek(); // peek next transaction from medium priority heap
      }
      else if (low.getSize() != 0) {
        return low.peek(); // peek next transaction from low priority heap
      }
      return null; // no transactions to peek
	}

	/**
	 * Adds a transaction to the BankManager according to the amount that the
	 * transaction is for low: < 1,000 medium: 1,000 <= t < 1,000,000 high: >= 1,000,000
	 * 
	 * @param transaction the transaction to add to the BankManager
	 */
	public void queueTransaction(Transaction transaction) {
	  if(transaction.getAmount() > 1000000) {
	    high.addTransaction(transaction); // if amount higher than 1000000, add to high priority heap
	  }
	  else if(transaction.getAmount() > 1000) {
	    medium.addTransaction(transaction); // if amount higher than 1000, add to medium priority heap
	  }
	  else {
	    low.addTransaction(transaction); // if amount lower than 1000, add to low priority heap
	  }
	}

	/**
	 * Removes and processes the next transaction in the priority queue. Withdrawals
	 * should remove the amount from the balance, deposits should add the amount to
	 * the balance, and loan applications add the amount to the balance only if the
	 * loan amount is less than ten (10) times the account's current balance.
	 * 
	 * @throws NoSuchElementException if there are no transactions to process
	 * @throws IllegalStateException  if the account would overdraft
	 */
	public void performTransaction() {
	  if(low.isEmpty() && medium.isEmpty() && high.isEmpty()) {
	    throw new NoSuchElementException("no transactions to process"); // throw error because no transactions to process
	  }
	  Transaction currentTransaction = this.getNextTransaction();
	  if(currentTransaction.getType() == Transaction.Type.DEPOSIT) {
	    try {
	      currentTransaction.getUser().deposit(currentTransaction.getAmount()); // execute deposit transaction
	    } catch(IllegalArgumentException e) {
	      throw e; // catch illegal deposit transaction
	    }
	  }
	  if(currentTransaction.getType() == Transaction.Type.WITHDRAWAL) {
	    try {
	      currentTransaction.getUser().withdraw(currentTransaction.getAmount()); // execute withdrawal transaction
	      } catch(IllegalStateException e) {
	        throw e; // catch illegal withdrawal transaction
	      }
	    }
	  if(currentTransaction.getType() == Transaction.Type.LOAN_APPLICATION) {
	    if(currentTransaction.getUser().getBalance() * 10 > currentTransaction.getAmount()) {
	      currentTransaction.getUser().deposit(currentTransaction.getAmount()); // execute loan if condition is met
	    }
	  }
	}

}
