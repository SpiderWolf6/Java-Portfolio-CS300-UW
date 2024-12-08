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
 * This class manages linked stack objects, including basic actions like pushing, popping, peeking at elements
 */
public class LinkedStack<T> extends Object implements StackADT<T> {
  private LinkedNode<T> top;
  
  /** Adds a new value to the top of the stack
   * @param value of type T
   * @return None
  */
  public void push(T value) {
    // if empty stack then initialize top node properly
    if(top == null) {
      top = new LinkedNode<T>(value);
    }
    else {
      // add new element to the top of the stack
      LinkedNode<T> newNode = new LinkedNode<T>(value);
      newNode.setNext(top);
      top = newNode;
    }
  }

  /** Removes and returns the top item of the stack
   * @param None
   * @return top element of type T
  */
  public T pop() {
    if(top == null) {
       return null; // if empty stack then return null
    }
    else {
      // remove top element and return its reference
      T value = top.getData();
      top = top.getNext();
      return value;
    }
  }
  
  /** Returns top element without removing
   * @param None
   * @return top element of type T
  */
  public T peek() {
    if(this.top == null) {
      return null;
    }
    return top.getData();
  }
  
  /** Checks to see if this stack is empty
   * @param None
   * @return true if size of the stack is 0, false otherwise
  */
  public boolean isEmpty() {
    if(top == null) {
      return true;
    }
    return false;
  }
  
  /** Checks to see if a given element is in the stack
   * @param value of type T
   * @return true if given element is found, false otherwise
  */
  public boolean contains(T value) {
    LinkedNode<T> current = top;
    // traverse stack to check if given element exists
    while(current != null) {
      if(current.getData().equals(value)) {
        return true;
      }
      current = current.getNext();
    }
    return false;
  }
  
  /** Converts stack representation to ArrayList representation and returns list
   * @param None
   * @return an ArrayList of type T representing the stack's elements in order
  */
  public ArrayList<T> getList() {
    ArrayList<T> arr = new ArrayList<T>();
    LinkedNode<T> current = top;
    // traverse stack adding elements in the correct order to its arraylist format
    while(current != null) {
      arr.add(current.getData());
      current = current.getNext();
    }
    return arr;
  }
  
}