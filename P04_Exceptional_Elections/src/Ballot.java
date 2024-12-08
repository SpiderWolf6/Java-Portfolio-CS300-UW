//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Exceptional Elections
// Course:   CS 300 Fall 2024
//
// Author:   Soham Mukherjee
// Email:    smukherjee39@wisc.edu
// Lecturer: Blerina Gkotse
//

import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * A class that represents a ballot and the different methods 
 * to control ballot actions.
 *
 */
public class Ballot {
  // create private class variables
  private static boolean ballotsCreated = false;
  private static ArrayList<Election> elections = new ArrayList<Election>();
  private boolean[] hasVoted;
  
  /**
   * Constructor for election class accounting for poor setup
   *
   * @param None
   * @return None
   */
  public Ballot() {
    if (elections.size() == 0) {
      // throw associated exception
      throw new IllegalStateException("there are no elections to vote in yet");
    }
    hasVoted = new boolean[elections.size()];
    if (!ballotsCreated) {
      ballotsCreated = true;
    }
  }
  
  /**
   * Adds election to ballot accounting for possible exceptions given bad implementation
   *
   * @param election an Election
   * @return None
   */ 
  public static void addElection(Election election) {
    if (ballotsCreated) {
      // throw associated exception
      throw new IllegalStateException("ballots have been created, no more elections may be added");
    }
    if (elections.size() == 0) {
      elections.add(election);
    }
    else {
      // loop through elections list
      for (int i = 0; i<elections.size(); i++) {
        if (elections.get(i).equals(election)) {
          // throw associated exception
          throw new IllegalArgumentException("election is already present in the list"); 
        }
      }
      elections.add(election);
    }
  }
  
  /**
   * Checks if ballot has already voted in election for given seatName
   *
   * @param seatName a String
   * @return true if ballot has already voted in election, false otherwise
   */ 
  public boolean hasVoted(String seatName) {
    boolean found = false;
    for (int i = 0;i<elections.size(); i++) {
      if (elections.get(i) != null) {
        if (elections.get(i).SEAT_NAME.equals(seatName)) {
          found = true;
          if (hasVoted[i]) {
            return true;
          }
        }
      }
    }
    if (!found) {
      // throw associated exception
      throw new NoSuchElementException("given seat name does not correspond to an election on this ballot"); 
    }
    return false;
  }
  
  /**
   * Helper method for testing purposes
   *
   * @param None
   * @return None
   */ 
  public static void clearElections() { 
    // empties the elections ArrayList and resets ballotsCreated, for testing purposes only
    elections.clear();
    ballotsCreated = false;
  }
  
  /**
   * Casts a vote for the given candidate in the given election, accounting for possible exceptions
   *
   * @param seatName a String, candidate a Candidate
   * @return None
   */
  public void vote(String seatName, Candidate candidate) {
    // loop through elections list
      for (int i = 0; i<elections.size(); i++) {
        try {
          if (!hasVoted(seatName)) {
            try {
              elections.get(i).vote(candidate);
            } catch (NoSuchElementException e) {
              throw e;
            }
            hasVoted[i] = true;
            break;
          }
          else {
            // throw associated exception
            throw new IllegalStateException("ballot has already voted in given election");
          }
        }  catch (NoSuchElementException e) {
          throw e;
        }
    }
  }
  
  /**
   * Overrides toString(), converts ballot to proper String format
   *
   * @param None
   * @return String format of ballot, listing seatName and voted status
   */
  @Override
  public String toString() {
    String returnString = "";
    // loop through elections list
    for (int i = 0; i<elections.size(); i++) {
      if (i == 0) {
        returnString = returnString + elections.get(i).SEAT_NAME + ": " + hasVoted[i];
      }
      else {
        returnString = returnString + "\n" + elections.get(i).SEAT_NAME + ": " + hasVoted[i];
      }
    }
    return returnString;
  }
  
}