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
 * This class tests the LoopStation class, and by extension, the Track class
 */
public class LoopStationTester {
  
  /**
   * Checks the correctness of the createPod() method. This method should:
   * - create a Pod with the given capacity and podClass
   * - add it to the correct end of the correct Track in the LoopStation
   * - return a reference (shallow copy) to that Pod
   * Note that the tracks in LoopStation are protected, so you may access them directly for testing
   * purposes
   * @return true if createPod() is functioning correctly, false otherwise
   */
  public static boolean testCreatePod() {
    // create LoopStation object for testing purposes
    LoopStation station = new LoopStation();
    int economySize = station.waitingEconomy.size();
    int firstSize = station.waitingFirst.size();
    Pod referencePod = new Pod(20, 0); // copy of Pod being added to check if addition was completed properly
    Pod testPod = station.createPod(20, true);
    if((referencePod.equals(testPod)) && ((economySize + 1 == station.waitingEconomy.size()) || (firstSize + 1 == station.waitingFirst.size()))) {
      return true; // if Pod added correctly to the correct track then return true
    }
    return false; // return false if test failed
  }
  
  /**
   * Checks the correctness of the launchPod() method. This method should:
   * - throw a NoSuchElementException if no pods are waiting to launch
   * - launch first class pods from the END of the waitingFirst track
   * - launch economy class pods from the BEGINNING of the waitingEconomy track
   * - launch ALL first class pods before launching ANY economy class pods
   * Note that the tracks in LoopStation are protected, so you may access them directly for testing
   * purposes
   * @return true if launchPod() is functioning correctly, false otherwise
   */
  public static boolean testLaunchPod() {
    // create LoopStation object and add Pods for testing purposes
    LoopStation station = new LoopStation();
    Pod testPod1 = station.createPod(20, true);
    Pod testPod2 = station.createPod(15, false);
    Pod testPod3 = station.createPod(25, true);
    Pod testPod4 = station.createPod(30, false);
    Pod lastFirstPod = station.waitingFirst.get(station.waitingFirst.size()-1);
    Pod firstEconomyPod = station.waitingEconomy.get(0);
    
    // Check the initial state of the station
    if((station.getNumWaiting() != 4) || (station.getNumLaunched() != 0)) {
      return false;
    }
    
    try {
      station.launchPod();  // Launch the first Pod (expected to be from first class)
      // Check that one first-class Pod has been removed
      if(station.waitingFirst.size() != 1) {
        System.out.println("caught1");
        return false;
      }
      // Check that the last first-class Pod was removed as expected
      if(station.waitingFirst.get(station.waitingFirst.size()-1).equals(lastFirstPod)) {
        System.out.println("caught3");
        return false;
      }
      // Continue launching Pods
      station.launchPod();
      station.launchPod();
      // Check that there is only one economy Pod remaining
      if(station.waitingEconomy.size() != 1) {
        System.out.println("caught2");
        return false;
      }
      // Check that the first economy-class Pod has been removed as expected
      if(station.waitingEconomy.get(0).equals(firstEconomyPod)) {
        System.out.println("caught4");
        return false;
      }
      // Try to launch another Pod to test exception handling when all are launched
      station.launchPod();
      station.launchPod();
      return false; // Fail if no exception is thrown (NoSuchElementException expected)
    }
    catch (NoSuchElementException e) {
      System.out.println(e.getMessage());
    }
    return true; // All tests passed
  }
  
  /**
   * Checks the correctness of the clearMalfunctioning() method. This method should:
   * - repeatedly check the launched track for malfunctioning pods
   * - remove those pods correctly
   * - report the number of pods it removed once there are no longer any malfunctioning pods
   * 
   * Things to consider when you are testing:
   * 
   * - there is a protected setNonFunctional() method you may use for testing purposes to ensure
   *   that at least one pod is non-functional
   *   
   * - calling isFunctional() on a Pod may cause it to malfunction! You should come up with an
   *   alternate way to check whether a Pod is functional, if you have not already.
   *   
   * - verify that the difference in number of pods from before the method was called and after
   *   the method was called is equal to the number that it reported
   *   
   * @return true if clearMalfunctioning() is functioning correctly, false otherwise
   */
  public static boolean testClearMalfunctioning() {
    // create LoopStation object and add Pods for testing purposes
    LoopStation station = new LoopStation();
    Pod testPod1 = station.createPod(20, true);
    Pod testPod2 = station.createPod(15, false);
    Pod testPod3 = station.createPod(25, true);
    Pod testPod4 = station.createPod(20, false);
    Pod testPod5 = station.createPod(10, false);

    try {
        // Set up initial conditions
        testPod1.addPassenger("John");
        testPod1.addPassenger("Jasmine");
        testPod2.addPassenger("Jerry");
        testPod3.addPassenger("Jeffrey");
        testPod4.addPassenger("Jeremy");
        testPod4.addPassenger("James");
        testPod5.addPassenger("Jackson");

        station.launchPod();
        station.launchPod();
        station.launchPod();
        station.launchPod();
        station.launchPod();

    } catch (MalfunctioningPodException e) {
        System.out.println(e.getMessage());
    }

    // Mark some pods as non-functional for testing
    testPod2.setNonFunctional();
    testPod4.setNonFunctional();
    testPod5.setNonFunctional();

    // Count malfunctioning pods before clearing
    int initialMalfunctioningCount = 0;
    for (int i = 0; i < station.launched.size(); i++) {
        Pod pod = station.launched.get(i);
        if (!pod.isFunctional()) {
            initialMalfunctioningCount++;
        }
    }

    // Call clearMalfunctioning() and get the reported number of removals
    int removedCount = station.clearMalfunctioning();

    // Check that removedCount matches the initial malfunctioning count
    if (removedCount != initialMalfunctioningCount) {
        return false;
    }

    // Verify that all malfunctioning pods are removed
    for (int i = 0; i < station.launched.size(); i++) {
        if (!station.launched.get(i).isFunctional()) {
            return false;  // Found a malfunctioning pod that wasn't removed
        }
    }

    // All tests passed
    return true;
  }

  /**
   * Checks the correctness of the three getNumXXX() methods from LoopStation. This will require
   * adding Pods of various types, loading them with passengers, and launching them.
   * @return true if the getNumXXX() methods are all functioning correctly, false otherwise
   */
  public static boolean testGetNums() {
    // Create LoopStation object and add Pods for testing purposes
    LoopStation station = new LoopStation();
    Pod testPod1 = station.createPod(20, true);
    Pod testPod2 = station.createPod(15, false);
    Pod testPod3 = station.createPod(25, true);
    Pod testPod4 = station.createPod(20, false);
    Pod testPod5 = station.createPod(10, false);
    // If number of waiting and launched pods does not match, test failed
    if((station.getNumWaiting() != 5) || (station.getNumLaunched() != 0)) {
      return false;
    }
    try {
      // Add passengers to each Pod for testing getNumPassengers method
      testPod1.addPassenger("John");
      testPod1.addPassenger("Jasmine");
      testPod2.addPassenger("Jerry");
      testPod3.addPassenger("Jeffrey");
      testPod4.addPassenger("Jeremy");
      testPod4.addPassenger("James");
      testPod5.addPassenger("Jackson");
      station.launchPod(); // Start launching pods
      station.launchPod(); 
      // Check if getNumXXX() methods return expected values
      if((station.getNumWaiting() != 3) || (station.getNumLaunched() != 2) || (station.getNumPassengers() != 7)) {
        return false;
      }
      station.launchPod(); // Launch another pod
      // Recheck getNumXXX() methods
      if((station.getNumWaiting() != 2) || (station.getNumLaunched() != 3) || (station.getNumPassengers() != 7)) {
        return false;
      }
      station.launchPod(); // Continue launching pods
      station.launchPod();
      // Recheck getNumXXX() methods
      if((station.getNumWaiting() != 0) || (station.getNumLaunched() != 5) || (station.getNumPassengers() != 7)) {
        return false;
      }
    }
    catch (Exception e) {
      System.out.println(e.getMessage());
      return true;
    }
    return true; // All tests passed
  }

  public static void main(String[] args) {
    boolean test1 = testCreatePod();
    System.out.println("testCreatePod: "+(test1?"PASS":"fail"));
    
    boolean test2 = testLaunchPod();
    System.out.println("testLaunchPod: "+(test2?"PASS":"fail"));
    
    boolean test3 = testClearMalfunctioning();
    System.out.println("testClearMalfunctioning: "+(test3?"PASS":"fail"));
    
    boolean test4 = testGetNums();
    System.out.println("testGetNums: "+(test4?"PASS":"fail"));
    
    System.out.println("ALL TESTS: "+((test1&&test2&&test3&&test4)?"PASS":"fail"));
  }

}
