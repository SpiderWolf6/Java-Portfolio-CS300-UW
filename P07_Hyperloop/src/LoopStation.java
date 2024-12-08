//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Hyperloop
// Course:   CS 300 Fall 2024
//
// Author:   Soham Mukherjee
// Email:    smukherjee39@wisc.edu
// Lecturer: Blerina Gkotse
//

import java.util.NoSuchElementException;

/**
 * This class manages different tracks, including actions such as creating and launching Pods
 */
public class LoopStation extends Object{
  // create three separate tracks for waiting and launched Pods
  protected Track waitingFirst = new Track();
  protected Track waitingEconomy = new Track();
  protected Track launched = new Track();
  
  /**Basic LoopStation constructor
   * @param None
   */
  public LoopStation() {
  }
  
  /**
   * Checks for malfunctioning Pods on the launched track and removes those Pods
   * Keeps track of the number of removed Pods
   * @param None
   * @return number of malfunctioning Pods that were removed, 0 if none were removed
   */
  public int clearMalfunctioning() {
    int count = 0; // Originally 0 removed Pods
    int i = 0;
    while(i < launched.size()) {
      if(!launched.get(i).isFunctional()) { // Check if the Pod is malfunctioning
        launched.remove(i); // Remove malfunctioning Pod
        count += 1; // Increment count of removed Pods
      }
      i += 1; // Move to the next Pod in the launched track
    }
    return count; // Return total number of malfunctioning Pods removed
  }
  
  /**
   * Gets the total number of waiting Pods, both First and Economy
   * @param None
   * @return the summed total number of Pods in the waiting tracks
  */
  public int getNumWaiting() {
    return waitingFirst.size() + waitingEconomy.size();
  }
  
  /**
   * Gets the number of launched Pods
   * @param None
   * @return the number of Pods in the launched track
  */
  public int getNumLaunched() {
    return launched.size();
  }
  
  /**
   * Gets the total number of passengers across all Pods in all tracks
   * @param None
   * @return the summed total number of passengers
  */
  public int getNumPassengers() {
    int total = 0;
    int count = 0;
    // Count passengers in first class waiting track
    while(count < waitingFirst.size()) {
      try {
        total += waitingFirst.get(count).getNumPassengers(); // Add passengers in each Pod
      }
      catch (MalfunctioningPodException e) {
        System.out.println(e.getMessage());
      }
      finally {
        count += 1; // Move to the next Pod
      }
    }
    
    count = 0;
    // Repeat by counting passengers in economy class waiting track
    while(count < waitingEconomy.size()) {
      try {
        total += waitingEconomy.get(count).getNumPassengers(); // Add passengers in each Pod
      }
      catch (MalfunctioningPodException e) {
        System.out.println(e.getMessage());
      }
      finally {
        count += 1; // Move to the next Pod
      }
    }
    
    count = 0;
    // Repeat by counting passengers in launched track
    while(count < launched.size()) {
      try {
        total += launched.get(count).getNumPassengers(); // Add passengers in each Pod
      }
      catch (MalfunctioningPodException e) {
        System.out.println(e.getMessage());
      }
      finally {
        count += 1; // Move to the next Pod
      }
    }
    return total; // Return total passenger count across all tracks
  }
  
  /**
   * Creates a new Pod with the given capacity and class designation and adds it
   * to the appropriate waiting track
   * @param capacity an int, isFirstClass a boolean
   * @return a reference to the newly created Pod
   */
  public Pod createPod(int capacity, boolean isFirstClass) {
    if(isFirstClass) {
      Pod newPod = new Pod(capacity, 0); // Create new first class Pod
      waitingFirst.add(newPod); // Add to first class waiting track
      return newPod;
    }
    else {
      Pod newPod = new Pod(capacity, 1); // Create new economy class Pod
      waitingEconomy.add(newPod); // Add to economy class waiting track
      return newPod;
    }
  }
 
  /**
   * Launches the highest-priority, least-recently-created Pod by moving it
   * from the waiting track to the launched track
   * @param None
   * @return None
   */
  public void launchPod() {
    boolean unlaunched = true;
    // Try to launch a first class Pod from the end of the waitingFirst track
    if(waitingFirst.size() > 0) {
      int i = waitingFirst.size()-1;
      while(i >= 0 && unlaunched) {
        Pod currentPod = waitingFirst.get(i);
        if(currentPod.isFunctional()) {
          launched.add(currentPod); // Add functional Pod to launched track
          unlaunched = false; // Mark as launched
        }
        waitingFirst.remove(i); // Remove launched Pod from waiting track
        i -= 1;
      }
    }
    // If no first class Pod was launched, try to launch an economy Pod from the beginning
    if(waitingEconomy.size() > 0) {
      int i = 0;
      while(i < waitingEconomy.size() && unlaunched) {
        Pod currentPod = waitingEconomy.get(i);
        if(currentPod.isFunctional()) {
          launched.add(currentPod); // Launch a functional Pod from economy track
          unlaunched = false; // Mark as launched
        }
        waitingEconomy.remove(i); // Remove launched Pod from waiting track
        i += 1;
      }
    }
    else {
      throw new NoSuchElementException("no Pods are waiting to launch"); // Exception if no Pods to launch
    }
  }
  
}