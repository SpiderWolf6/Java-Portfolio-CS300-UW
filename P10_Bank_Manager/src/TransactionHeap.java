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
 * Class for implementing a heap/priority queue on Transactions
 */
public class TransactionHeap {
	private Transaction[] transactions;
	private int size;

	/**
	 * Initializes transactions array with size capacity
	 * 
	 * @param capacity the length of the transactions heap array
	 */
	public TransactionHeap(int capacity) {
		transactions = new Transaction[capacity];
		size = 0;
	}

	/**
	 * This method adds a transaction to the heap if space allows.
	 * 
	 * @param transaction the transaction to add to the heap
	 * @throws IllegalStateException if the TransactionHeap is full.
	 */
	public void addTransaction(Transaction transaction) {
		if(size == transactions.length) {
		  throw new IllegalStateException("TransactionHeap is full"); // if full heap, throw error
		}
		// add transaction, update size, and heapify correctly
		transactions[size] = transaction;
		size++;
		heapifyUp(size-1); 
	}

	/**
	 * Reinforces the heap rules after adding a Transaction to the end
	 * 
	 * @param index the index of the new Transaction
	 */
	public void heapifyUp(int index) {
	  // starting from the last index, check against its parent element
		for(int i=index; i>0; i--) {
		  if(transactions[i].compareTo(transactions[(i-1)/2]) > 0) {
		    // if parent is smaller than self, swap elements
		    Transaction temp = transactions[i];
		    transactions[i] = transactions[(i-1)/2];
		    transactions[(i-1)/2] = temp;
		  }
		}
	}

	/**
	 * Removes the next transaction from the priority queue
	 * 
	 * @return the next transaction in the priority queue
	 * @throws NoSuchElementException if there are no transactions in the heap
	 */
	public Transaction getNextTransaction() {
	  if(size == 0) {
	    throw new NoSuchElementException("no transactions in the heap"); // if empty heap, throw error
	  }
      // remove transaction, update size, and heapify correctly
	  Transaction nextTransaction = transactions[0];
	  transactions[0] = transactions[size-1];
	  transactions[size-1] = null;
	  size--;
      heapifyDown(0);
	  return nextTransaction; // return a reference to the removed transaction
	}

	/**
	 * Enforces the heap conditions after removing a Transaction from the heap
	 * 
	 * @param index the index whose subtree needs to be heapified
	 */
	public void heapifyDown(int index) {
      // starting from the first index, check against its child element
	  while (index < size) {
	    int leftChildIndex = 2 * index + 1;
	    int rightChildIndex = 2 * index + 2;
	    int largest = index;

	    if (leftChildIndex < size && transactions[leftChildIndex].compareTo(transactions[largest]) > 0) {
	      largest = leftChildIndex;
	    }

	    if (rightChildIndex < size && transactions[rightChildIndex].compareTo(transactions[largest]) > 0) {
	      largest = rightChildIndex;
	    }

	    // if child is greater than self, swap elements
	    if (largest != index) {
	      Transaction temp = transactions[index];
	      transactions[index] = transactions[largest];
	      transactions[largest] = temp;
	      index = largest; 
	    } else {
	      break; 
	    }
	  }
	}

	/**
	 * Returns the highest priority transaction without removing it from the heap.
	 * 
	 * @return the highest priority transaction without removing it from the heap.
	 * @throws NoSuchElementException if there are no transactions in the heap
	 */
	public Transaction peek() {
	  if(size == 0) {
	    throw new NoSuchElementException("no transactions in the heap"); // if empty heap, throw error
	  }
	  return transactions[0]; // peek first transaction (highest priority) without removing
	}

	/**
	 * Getter method for the heap size
	 * 
	 * @return the size
	 */
	public int getSize() {
		return size;
	}

	/**
	 * Tells if the heap has any elements in it
	 * 
	 * @return whether or not the heap is empty
	 */
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * PROVIDED Creates and returns a deep copy of the heap's array of data.
	 * 
	 * @return the deep copy of the array holding the heap's data
	 */
	public Transaction[] getHeapData() {
		Transaction[] list = new Transaction[this.transactions.length];
		for (int i = 0; i < list.length; i++)
			list[i] = this.transactions[i];
		return list;
	}

}
