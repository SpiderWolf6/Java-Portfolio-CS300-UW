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
 * Tester class for testing the Transaction, TransactionHeap, and BankManager classes
 * including creating, heapifying, and executing transactions and more functionalities 
 */
public class BankManagerTester {

	/**
	 * Tests the constructor for the Transaction class.
	 * 
	 * @return true if the test passes
	 */
	public static boolean testTransactionConstructor() {
	  boolean check1 = false;
	  boolean check2 = false;
	  boolean check3 = false;
	  boolean check4 = false;
	  boolean check5 = false;
	  
	   // create an account and transactions for testing purposes
	  Account messi = new Account(100, 1000);
	  Account ronaldo = new Account(70, 700);
	  Transaction t1 = new Transaction(messi, 100, Transaction.Type.DEPOSIT);
	  if(t1.getPriority().ordinal() == 2) {
	    check1 = true; // if correct priority assigned, test passed
	  }
	  Transaction t2 = new Transaction(ronaldo, 100, Transaction.Type.WITHDRAWAL);
	  if(t2.getPriority().ordinal() == 1) {
	    check2 = true; // if correct priority assigned, test passed
	  }
	  Transaction t3 = new Transaction(messi, 1000, Transaction.Type.LOAN_APPLICATION);
	  if(t3.getPriority().ordinal() == 3) {
	    check3 = true; // if correct priority assigned, test passed
	  }
	  Transaction t4 = new Transaction(ronaldo, 2200, Transaction.Type.LOAN_APPLICATION);
	  if(t4.getPriority().ordinal() == 0) {
	    check4 = true; // if correct priority assigned, test passed
	  }
	  try {
	    Transaction t5 = new Transaction(messi, -200, Transaction.Type.DEPOSIT);
	    // try constructing an illegal transaction
	  }
	  catch (IllegalArgumentException e) {
	    System.out.println(e.getMessage());
	    check5 = true; // error correctly thrown as expected, test passed
	  }
	  return check1 && check2 && check3 && check4 && check5; // all tests passed
	}

	/**
	 * tests the Transaction.compareTo when the priorities are different
	 * 
	 * @return true if the test passes
	 */
	public static boolean testTransactionCompareToPriority() {
	  boolean check1 = false;
	  boolean check2 = false;
	  boolean check3 = false;
	  
      // create accounts and transactions for testing purposes
      Account messi = new Account(100, 1000);
      Account ronaldo = new Account(70, 700);
      Transaction t1 = new Transaction(messi, 100, Transaction.Type.DEPOSIT); // priority HIGH, ordinal = 2
      Transaction t2 = new Transaction(ronaldo, 100, Transaction.Type.WITHDRAWAL); // priority NORMAL, ordinal = 1
      Transaction t3 = new Transaction(messi, 1000, Transaction.Type.LOAN_APPLICATION); // priority URGENT, ordinal = 3
      Transaction t4 = new Transaction(ronaldo, 2200, Transaction.Type.LOAN_APPLICATION); // priority LOW, ordinal = 0
      
      // test different comparisons between transactions
      if(t1.compareTo(t3) < 0) {
        check1 = true;
      }
      if(t2.compareTo(t4) > 0) {
        check2 = true;
      }
      if(t1.compareTo(t4) > 0) {
        check3 = true;
      }
      return check1 && check2 && check3; // all tests passed
	}

	/**
	 * tests the Transaction.compareTo when the priorities are the same
	 * 
	 * @return true if the test passes
	 */
	public static boolean testTransactionCompareToAccountBalance() {
      // create accounts and transactions for testing purposes
      Account messi = new Account(100, 1000);
      Account ronaldo = new Account(70, 700);
      Transaction t1 = new Transaction(messi, 100, Transaction.Type.DEPOSIT); // priority HIGH, ordinal = 2
      Transaction t2 = new Transaction(ronaldo, 100, Transaction.Type.DEPOSIT); // priority HIGH, ordinal = 2
      if(t1.compareTo(t2) > 0) {
        return true; // if compared correctly, test passed
      }
      return false;
	}

	/**
	 * Tests the TransactionHeap.addTransaction() method
	 * 
	 * @return true if the test passes
	 */
	public static boolean testAddTransactionToHeap() {
      boolean check1 = false;
      boolean check2 = false;
      
      // create an account and transactions for testing purposes
	  Account messi = new Account(100, 1000);
      Transaction t1 = new Transaction(messi, 100, Transaction.Type.DEPOSIT); // priority HIGH, ordinal = 2
      TransactionHeap testerHeap = new TransactionHeap(1); // create new heap
      testerHeap.addTransaction(t1);
      if(testerHeap.getSize() == 1) {
        check1 = true; // check if transaction was added correctly
      }
      Transaction t2 = new Transaction(messi, 500, Transaction.Type.WITHDRAWAL); // priority NORMAL, ordinal = 1
      try {
        testerHeap.addTransaction(t2); // try adding to heap at max capacity, no space left
      } catch (IllegalStateException e) {
        System.out.println(e.getMessage());
        check2 = true; // error thrown as expected, test passed
      }
      return check1 && check2; // all tests passed
	}

	/**
	 * Tests the TransactionHeap.heapifyUp() and TransactionHeap.heapifyDown()
	 * methods
	 * 
	 * @return true if the test passes
	 */
	public static boolean testHeapify() {
      boolean check1 = true;
      boolean check2 = true;
      
      // create an account and transactions for testing purposes
      Account messi = new Account(100, 1000);
      Transaction t1 = new Transaction(messi, 100, Transaction.Type.DEPOSIT); // priority HIGH, ordinal = 2
      Transaction t2 = new Transaction(messi, 500, Transaction.Type.WITHDRAWAL); // priority NORMAL, ordinal = 1
      Transaction t3 = new Transaction(messi, 200, Transaction.Type.LOAN_APPLICATION); // priority URGENT, ordinal = 3
      TransactionHeap testerHeap = new TransactionHeap(3); // create new heap
      testerHeap.addTransaction(t1);
      testerHeap.addTransaction(t2);
      testerHeap.addTransaction(t3);
      Transaction[] testerHeapArray = testerHeap.getHeapData(); // get the array representation of the heap
      Transaction[] checkerHeapArray = new Transaction[3]; // create second array to check against
      checkerHeapArray[0] = t3;
      checkerHeapArray[1] = t2;
      checkerHeapArray[2] = t1;
      // check if elements of heap follow max-heap properties after adding
      for(int i = 0; i<testerHeapArray.length; i++) {
        if(testerHeapArray[i].compareTo(checkerHeapArray[i]) != 0) {
          check1 = false;
        }
      }

      // remove highest priority  transaction
      Transaction removedTransaction = testerHeap.getNextTransaction();
      if(removedTransaction.compareTo(t3) != 0) {
        check2 = false; // expected transaction removed correctly
      }
      testerHeapArray = testerHeap.getHeapData(); // get the array representation of the heap
      // set up second array to check against
      checkerHeapArray[0] = t1;
      checkerHeapArray[1] = t2;
      checkerHeapArray[2] = null;
      // check if elements of heap follow max-heap properties after removing
      for(int i = 0; i<testerHeapArray.length; i++) {
        if(testerHeapArray[i] == null && checkerHeapArray[i] == null) {
          continue;
        }
        if(testerHeapArray[i].compareTo(checkerHeapArray[i]) != 0) {
          check2 = false;
        }
      }
      return check1 && check2; // all tests passed
	}

	/**
	 * Tests the TransactionHeap.getNextTransaction() method
	 * 
	 * @return true if the test passes
	 */
	public static boolean testGetNextTransactionFromHeap() {
	  boolean check1 = false;
	  boolean check2 = false;
	  
      // create an account and transactions for testing purposes
      Account messi = new Account(100, 1000);
      Transaction t1 = new Transaction(messi, 100, Transaction.Type.DEPOSIT); // priority HIGH, ordinal = 2
      Transaction t2 = new Transaction(messi, 500, Transaction.Type.WITHDRAWAL); // priority NORMAL, ordinal = 1
      Transaction t3 = new Transaction(messi, 200, Transaction.Type.LOAN_APPLICATION); // priority URGENT, ordinal = 3
      TransactionHeap testerHeap = new TransactionHeap(3); // create new heap
      // add transactions to heap
      testerHeap.addTransaction(t1);
      testerHeap.addTransaction(t2);
      testerHeap.addTransaction(t3);
      Transaction removedTransaction = testerHeap.getNextTransaction(); // remove highest priority transaction
      if(removedTransaction.compareTo(t3) == 0) {
        check1 = true; // expected transaction removed correctly
      }
      
      TransactionHeap emptyHeap = new TransactionHeap(2); // create empty heap
      try {
        Transaction emptyTransaction = emptyHeap.getNextTransaction(); // attempt to remove transaction from empty heap
      } catch (NoSuchElementException e) {
        System.out.println(e.getMessage());
        check2 = true; // error thrown as expected
      }
      return check1 && check2; // all tests passed
	}

	/**
	 * Tests the BankManager.queueTransaction() method
	 * 
	 * @return true if the test passes
	 */
	public static boolean testQueueTransaction() {
	  boolean check1 = false;
	  boolean check2 = false;
	  boolean check3 = false;
	  
      // create an account and transactions for testing purposes
      Account messi = new Account(100, 1000);
      Transaction t1 = new Transaction(messi, 100, Transaction.Type.DEPOSIT); // BankManager LOW priority
      Transaction t2 = new Transaction(messi, 1500, Transaction.Type.WITHDRAWAL); // BankManager MEDIUM priority
      Transaction t3 = new Transaction(messi, 1500000, Transaction.Type.LOAN_APPLICATION); // BankManager HIGH priority
      // add transactions to new bank manager
      BankManager manager = new BankManager(4);
      manager.queueTransaction(t1);
      manager.queueTransaction(t2);
      manager.queueTransaction(t3);
      // check if each peek heap returns expected highest priority transaction, then tests passed
      if(manager.high.peek().compareTo(t3) == 0) {
        check1 = true;
      }
      if(manager.medium.peek().compareTo(t2) == 0) {
        check2 = true;
      }
      if(manager.low.peek().compareTo(t1) == 0) {
        check3 = true;
      }
      return check1 && check2 && check3; // all tests passed
	}

	/**
	 * Tests the BankManager.performTransaction() method
	 * 
	 * @return true if the test passes
	 */
	public static boolean testPerformTransaction() {
	  // create three accounts for testing purposes and deposit initial amount
      Account messi = new Account();
      messi.deposit(1000);
      Account ronaldo = new Account();
      ronaldo.deposit(2000);
      Account neymar = new Account();
      neymar.deposit(3000);
      
      // create five new test transactions
      Transaction t1 = new Transaction(messi, 2000, Transaction.Type.DEPOSIT); // BankManager MEDIUM priority
      Transaction t2 = new Transaction(ronaldo, 1500, Transaction.Type.WITHDRAWAL); // BankManager MEDIUM priority
      Transaction t3 = new Transaction(neymar, 5000, Transaction.Type.LOAN_APPLICATION); // BankManager LOW priority
      Transaction t4 = new Transaction(ronaldo, 15000000, Transaction.Type.WITHDRAWAL); // BankManager HIGH priority
      Transaction t5 = new Transaction(ronaldo, 2000000, Transaction.Type.LOAN_APPLICATION); // BankManager HIGH priority
      // create bank manager and add transactions
      BankManager manager = new BankManager(3);
      manager.queueTransaction(t1);
      manager.queueTransaction(t2);
      manager.queueTransaction(t3);
      manager.queueTransaction(t4);
      manager.queueTransaction(t5);
      
      // try first transaction, illegal withdrawal 
      try {
        manager.performTransaction();
        return false;
      } catch(IllegalStateException e) {
        System.out.println(e.getMessage()); // error correctly thrown
      }
      
      // try second transaction, illegal loan application
      manager.performTransaction();
      if(ronaldo.getBalance() != 2000) {
        return false; // if loan not skipped correctly or any changes/errors, test failed
      }
      
      // try third transaction, valid loan application
      manager.performTransaction();
      if(neymar.getBalance() != 8000) {
        return false; // loan not executed properly
      }
      
      // try fourth transaction, valid deposit
      try {
        manager.performTransaction();
        if(messi.getBalance() != 3000) {
          return false; // deposit not executed properly
        }
      } catch(IllegalArgumentException e) {
        System.out.println(e.getMessage());
        return false; // error thrown but not expected, test failed
      }
      
      // try fifth transaction, valid withdrawal
      try {
        manager.performTransaction();
        if(ronaldo.getBalance() != 500) {
          return false; // withdrawal not executed properly
        }
      } catch(IllegalStateException e) {
        System.out.println(e.getMessage());
        return false; // error thrown but not expected, test failed
      }
      
      if(!manager.high.isEmpty() || !manager.medium.isEmpty() || !manager.low.isEmpty()) {
        return false; // all transactions executed and removed, if any remaining then test failed
      }
      
      try {
        manager.performTransaction();
        return false;
      } catch(NoSuchElementException e) {
        System.out.println(e.getMessage()); // no transactions left, error correctly thrown
      }
      return true; // all tests passed
	}

	/**
	 * Tests the BankManager.peekHighestPriorityTransaction() method
	 * 
	 * @return true if the test passes
	 */
	public static boolean testPeekHighestPriorityTransaction() {
	  boolean check1 = false;
	  boolean check2 = false;
	  
	  // create an account and transactions for testing purposes
      Account messi = new Account(100, 1000);
      Transaction t1 = new Transaction(messi, 100, Transaction.Type.DEPOSIT); // BankManager LOW priority
      Transaction t2 = new Transaction(messi, 1500, Transaction.Type.WITHDRAWAL); // BankManager MEDIUM priority
      Transaction t3 = new Transaction(messi, 1500000, Transaction.Type.LOAN_APPLICATION); // BankManager HIGH priority
      Transaction t4 = new Transaction(messi, 2000000, Transaction.Type.DEPOSIT); // BankManager HIGH priority
      // begin adding transactions to new bank manager
      BankManager manager = new BankManager(4);
      manager.queueTransaction(t1);
      manager.queueTransaction(t2);
      manager.queueTransaction(t3);
      // peek the highest priority transaction
      Transaction peekTransaction = manager.peekHighestPriorityTransaction();
      // if peeked transaction matches expected and is not removed, test passed
      if((peekTransaction.compareTo(t3) == 0) && (manager.high.peek().compareTo(t3) == 0)) {
        check1 = true;
      }
      // add another transaction
      manager.queueTransaction(t4);
      peekTransaction = manager.peekHighestPriorityTransaction();
      // if peeked transaction matches expected and is not removed, test passed
      if((peekTransaction.compareTo(t4) == 0) && (manager.high.peek().compareTo(t4) == 0)) {
        check2 = true;
      }
      return check1 && check2; // all tests passed
	}

	public static void main(String[] args) {
		System.out.println("Transaction Constructor Tests: " + (testTransactionConstructor() ? "PASS" : "FAIL"));
		System.out.println("CompareTo Tests for Priority: " + (testTransactionCompareToPriority() ? "PASS" : "FAIL"));
		System.out.println(
				"CompareTo Tests for Account Balance: " + (testTransactionCompareToAccountBalance() ? "PASS" : "FAIL"));
		System.out.println("Testing Add Transaction to Heap: " + (testAddTransactionToHeap() ? "PASS" : "FAIL"));
		System.out.println("Testing Heapify: " + (testHeapify() ? "PASS" : "FAIL"));
		System.out.println("Testing Get Next Transaction: " + (testGetNextTransactionFromHeap() ? "PASS" : "FAIL"));
		System.out.println("Testing Queue Transaction: " + (testQueueTransaction() ? "PASS" : "FAIL"));
		System.out.println("Testing Perform Transaction: " + (testPerformTransaction() ? "PASS" : "FAIL"));
		System.out.println("Testing Peek Highest Priority Transaction: "
				+ (testPeekHighestPriorityTransaction() ? "PASS" : "FAIL"));
	}
}
