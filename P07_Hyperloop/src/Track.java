//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Hyperloop
// Course:   CS 300 Fall 2024
//
// Author:   Soham Mukherjee
// Email:    smukherjee39@wisc.edu
// Lecturer: Blerina Gkotse
//

/**
 * This class manages different Pods in a track, including actions such as adding and removing Pods
 */
public class Track extends Object implements ListADT<Pod> {
  // Create head, tail, and size for LinkedList of Pods in track
  protected LinkedNode head;
  private int size;
  protected LinkedNode tail;
  
  /** Adds a new Pod to the track
   * @param newElement a Pod
   * @return None
  */
  public void add(Pod newElement) {
    LinkedNode newPod = new LinkedNode(newElement);
    // If the track is empty, initialize head and tail with new Pod
    if(size == 0) {
      head = newPod;
      tail = newPod;
    }
    try {
      // Check if the Pod belongs in the First Class or Economy class
      if(newElement.getPodClass() == 0) {
        // For First Class, add at the beginning of the list
        newPod.setNext(head);
        head.setPrev(newPod);
        head = newPod;
        size += 1; // Increase size if Pod was added
      }
      else {
        // For Economy, add at the end of the list
        newPod.setPrev(tail);
        tail.setNext(newPod);
        tail = newPod;
        size += 1; // Increase size if Pod was added
      }
    }
    catch (MalfunctioningPodException e) {
      System.out.println(e.getMessage());
    }
  }
  
  /** Adds passenger to correct Pod based on class designation and open seats
   * @param name a String, isFirstClass a boolean
   * @return true if passenger was added properly and false otherwise
  */
  public boolean addPassenger(String name, boolean isFirstClass) {
    LinkedNode current = head;
    // Traverse the track to find the appropriate Pod
    while(current != null) {
      if(isFirstClass) {
        try {
          // Check if the Pod is First Class and has available seats
          if(current.getPod().getPodClass() == 0) {
            if(!current.getPod().isFull()) {
              current.getPod().addPassenger(name);
              return true;
            }
          }
        }
        catch (MalfunctioningPodException e) {
          System.out.println(e.getMessage());
        }
       }
      else {
        try {
          // Check if the Pod is Economy Class and has available seats
          if(current.getPod().getPodClass() == 1) {
            if(!current.getPod().isFull()) {
              current.getPod().addPassenger(name);
              return true;
            }
          }
        }
        catch (MalfunctioningPodException e) {
          System.out.println(e.getMessage());
        }
      }
      current = current.getNext(); // Move to the next Pod in the track
    }
    return false;
  }
  
  /** Clears current track by resetting head, tail, and size
   * @return None
  */
  public void clear() {
    head = null;
    tail = null;
    size = 0;
  }
  
  /** Checks if given Pod exists in this track 
   * @return true if Pod found in the track, false otherwise
  */
  public boolean contains(Pod toFind) {
    LinkedNode current = head;
    // Traverse the track, checking each Pod
    while(current != null) {
      Pod currentPod = current.getPod();
      if(currentPod.equals(toFind)) {
          return true;
      }
      current = current.getNext();
    }
    return false;
  }
  
  /** Checks the track for the first occurence of a malfunctioning Pod
   * @param None
   * @return the index of the first non functional Pod
  */
  public int findFirstNonFunctional() {
    if(size == 0) {
      return -1; // return -1 if track is empty
    }
    int i = 0;
    LinkedNode current = head;
    // Traverse each Pod to locate the first malfunctioning Pod
    while(current != null) {
      Pod currentPod = current.getPod();
      if(!currentPod.isFunctional()) {
          return i;
      }
      current = current.getNext();
      i += 1;
    }
    return -1; // no non functional Pods found
  }
  
  /** Checks every Pod in the track to locate the given passenger
   * @param name a String
   * @return the index of the Pod containing the given passenger, return -1 otherwise
  */
  public int findPassenger(String name) {
    if(size == 0) {
      return -1; // return -1 if track is empty
    }
    int i = 0;
    // Traverse the track, searching for the passenger
    LinkedNode current = head;
    while(current != null) {
      Pod currentPod = current.getPod();
      try {
        if(currentPod.containsPassenger(name)) {
          return i; // if passenger found, return index of Pod
        }
      }
      catch (MalfunctioningPodException e) {
        System.out.println(e.getMessage());
      }
      finally {
        current = current.getNext();
        i += 1;
      }
    }
    return -1; // passenger not found
  }
  
  /** Gets the Pod in the track at the given index
   * @param index an int
   * @return a reference to the Pod at the correct index, 
   * throw exception if index greater than size of track
  */
  public Pod get(int index) {
    LinkedNode current = head;
    // Traverse the track until reaching the target index
    while(current != null) {
      Pod currentPod = current.getPod();
      if(index == 0) {
        return currentPod; // return Pod at given index
      }
      current = current.getNext();
      index = index - 1;
    }
    throw new IndexOutOfBoundsException("given index is invalid");
  }
  
  
  /** Checks to see if the track is currently empty
   * @param None
   * @return true if track is empty, false otherwise
  */
   public boolean isEmpty() {
     if(size == 0) {
       return true;
     }
     return false;
   }
  
   /** Removes the Pod at the given index from the track
    * @param index an int
    * @return a reference to the removed Pod
   */
   public Pod remove(int index) {
     if (head == null || index < 0 || index >= size) {
       throw new IndexOutOfBoundsException("given index is invalid");
     }
     LinkedNode current = head;
     Pod currentPod = current.getPod();
     // Traverse to find the Pod at the given index
     while(current != null) {
       currentPod = current.getPod();
       // Handle removing head, tail, or intermediate node
       if(index == 0) {
         if(current == head) {
           head = current.getNext();
           if (head != null) {
             head.setPrev(null);
           } else {
             tail = null; // If head becomes null, list is now empty, so tail should also be null
           }
           size -= 1;
         }
         else if(current == tail) {
           tail = current.getPrev();
           tail.setNext(null);
           size -= 1;
         }
         else {
           current.getPrev().setNext(current.getNext());
           current.getNext().setPrev(current.getPrev());
           size -= 1;
         }
         return currentPod;
       }
       current = current.getNext();
       index = index - 1;
     }
     return currentPod; // return a reference to the removed Pod
   }
 
   /** Gets the size of the track
    * @param None
    * @return an int representing the number of Pods in the track
   */
  public int size() {
    return size;
  }
  
  /** Creates a String representation of the track and every Pod in it
   * @param None
   * @return A String representation of the track 
  */
  public String toString() {
    LinkedNode current = head;
    String returnString = "";
    while(current != null) {
      returnString += current.getPod().toString();
      current = current.getNext();
    }
    return returnString;
  }

}