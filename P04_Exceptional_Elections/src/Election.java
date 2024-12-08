//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Exceptional Elections
// Course:   CS 300 Fall 2024
//
// Author:   Soham Mukherjee
// Email:    smukherjee39@wisc.edu
// Lecturer: Blerina Gkotse
//

/**
 * A class that represents an election and the different methods 
 * to control election actions.
 *
 */
import java.util.NoSuchElementException;

public class Election {
  //create private and final class variables
  private Candidate[] candidates;
  private int numCandidates;
  public final String SEAT_NAME;
  
  /**
   * Constructor for election class accounting for poor implementation
   *
   * @param seatName a String, maxCandidates an int
   * @return None
   */
  public Election(String seatName, int maxCandidates) {
    if (maxCandidates<=0) {
      // throw associated exception
      throw new IllegalArgumentException("max number of Candidates must be at least 0");
    }
    SEAT_NAME = seatName;
    candidates = new Candidate[maxCandidates];
    // loop through candidates list
    for (int i = 0; i<maxCandidates; i++) {
      candidates[i] = null;
    }
    numCandidates = 0;
  }
  
  /**
   * Adds candidate to election accounting for possible exceptions given bad implementation
   *
   * @param c a Candidate
   * @return None
   */ 
  public void addCandidate(Candidate c) {
    // loop through candidates list
    for (int i = 0; i<candidates.length; i++) {
      if (candidates[i] != null) {
        if (candidates[i].equals(c)) {
          // throw associated exception
          throw new IllegalArgumentException("candidate already present in this election");
        }
      }
      else {
        candidates[i] = c;
        numCandidates += 1;
        break;
      }
    }
  }
  
  /**
   * Getter method for full length of candidates array
   *
   * @param None
   * @return length of candidates array
   */
  public int capacity() {
    return candidates.length;
  }
  
  /**
   * Removes candidate from election accounting for possible exceptions given bad implementation
   *
   * @param c a Candidate
   * @return None
   */ 
  public void removeCandidate (Candidate c) {
    boolean found = false;
    if (candidates[0] == null) {
      // throw associated exception
      throw new IllegalStateException("candidates list is empty");
    }
    else {
      // loop through candidates list to drop
      for (int i=0; i<candidates.length; i++) {
        if (candidates[i] != null) {
          if (candidates[i].equals(c)) {
            candidates[i] = null;
            found = true;
            numCandidates = numCandidates - 1;
            break;
          }
        }
      }
      // loop through candidates list to make compact
      for (int i=0; i<candidates.length-1; i++) {
        if ((candidates[i+1] != null) && (candidates[i] == null)) {
          candidates[i] = candidates[i+1];
          candidates[i+1] = null;
        }
      }
    }
    if (!found) {
      // throw associated exception
      throw new NoSuchElementException("given candidate not present in this election");
    }
  }
  
  /**
   * Adds vote for given candidate in list of candidates, given candidate exists in list
   *
   * @param c a Candidate
   * @return None
   */
  public void vote (Candidate c) {
    boolean found = false;
    // loop through candidates list
    for (int i = 0; i<candidates.length; i++) {
      if (candidates[i] != null) {
        if (candidates[i].equals(c)) {
          candidates[i].addVote();
          found = true;
        }
      }
    }
    if (!found) {
      // throw associated exception
      throw new NoSuchElementException("given candidate not present in this election");
    }
  }
  
  /**
   * Getter method for number of occupied elements in list of candidates
   *
   * @param None
   * @return number of candidates in list
   */
  public int getNumCandidates() {
    return numCandidates;
  }
  
  /**
   * Finds the candidate with more than 50% of the votes in the election
   *
   * @param None
   * @return a reference to Candidate c who own the election, or throw exception if contingent or empty list
   */
  public Candidate findWinner() {
    if (candidates.length == 0) {
      // throw associated exception
      throw new IllegalStateException("candidates list is empty");
    }
    int total_votes = 0;
    // loop through candidates list get total votes
    for (Candidate c: candidates) {
      total_votes += c.getNumVotes();
    }
    // loop through candidates list to find winner
    for (Candidate c: candidates) {
      if ((double)c.getNumVotes()/total_votes > 0.5) {
        return c;
      }
    }
    // throw associated exception
    throw new NoSuchElementException("no one candidate has more than 50% of votes, contingent election");
  }
  
  /**
   * Overrides equals, check if a given object is comparable to election object
   *
   * @param anObject an Object
   * @return true if anObject is an election AND seatName matches election's seatName, false otherwise
   */
  @Override
  public boolean equals (Object anObject) {
    if (anObject instanceof Election) {
      Election e = (Election)anObject;
      if (SEAT_NAME.equals(e.SEAT_NAME)) {
        return true;
      }
    }
    return false;
  }
  
  /**
   * Overrides toString(), converts election object to proper String format
   *
   * @param None
   * @return String format of election, listing seatName and every candidate object
   */
  @Override
  public String toString() {
    String returnString = SEAT_NAME;
    // loop through candidates list
    for (Candidate c: candidates) {
      if (c != null) {
        returnString = returnString + "\n" + c.toString();
      }
    }
    return returnString;
  }
  
}