//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Jukebox
// Course:   CS 300 Fall 2024
//
// Author:   Soham Mukherjee
// Email:    smukherjee39@wisc.edu
// Lecturer: Blerina Gkotse
//
import java.util.ArrayList;

/**
 * This class manages linked queue objects, including basic actions like enqueueing, dequeueing, peeking at elements
 */
public class LinkedQueue<T> extends Object implements QueueADT<T> {
  private LinkedNode<T> back;
  private LinkedNode<T> front;
  private int size;
  
  /** Adds a new value to the back of the queue
   * @param value of type T
   * @return None
  */
  public void enqueue(T value) {
    LinkedNode<T> newNode = new LinkedNode<T>(value);
    // if empty queue then initialize front and back nodes correctly
    if(size == 0) {
      front = newNode;
      back = newNode;
      size += 1;
    }
    // else add new element to back of the queue
    else {
      back.setNext(newNode);
      back = newNode;
      size += 1;
    }
  }
  
  /** Removes and returns an element from the front of the queue
   * @param None
   * @return the first element of type T
  */
  public T dequeue() {
    if(size == 0) {
      return null; // if empty queue return null
    }
    else {
      // remove first element, return its reference, and update size
      T value = front.getData();
      front = front.getNext();
      size = size - 1;
      return value;
    }
  }
  
  /** Returns the first element in the queue without removing
   * @param None
   * @return the first element of type T
  */
  public T peek() {
    if(size == 0) {
      return null;
    }
    return front.getData();
  }
  
  /** Checks to see if this queue is empty
   * @param None
   * @return true if size of the queue is 0, false otherwise
  */
  public boolean isEmpty() {
    if(size == 0) {
      return true;
    }
    return false;
  }
  
  /** Gets the size of the queue
   * @param None
   * @return size of the queue
  */
  public int size() {
    return size;
  }
  
  /** Checks to see if a given element is in the queue
   * @param value of type T
   * @return true if given element is found, false otherwise
  */
  public boolean contains(T value) {
    LinkedNode<T> current = front;
    // traverse queue to check if element exists
    while(current != null) {
      if(current.getData().equals(value)) {
        return true;
      }
      current = current.getNext();
    }
    return false;
  }
  
  /** Clearing method to reset the queue 
   * @param None
   * @return None
  */
  public void clear() {
    // reset queue
    front = null;
    back = null;
    size = 0;
  }
  
  /** Converts queue representation to ArrayList representation and returns list
   * @param None
   * @return an ArrayList of type T representing the queue's elements in order
  */
  public ArrayList<T> getList() {
    ArrayList<T> arr = new ArrayList<T>();
    LinkedNode<T> current = front;
    // traverse queue appending elements in the same order to arraylist format
    while(current != null) {
      arr.add(current.getData());
      current = current.getNext();
    }
    return arr;
  }
  
}