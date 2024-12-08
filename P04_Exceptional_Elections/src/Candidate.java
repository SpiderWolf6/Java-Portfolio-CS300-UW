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
 * A class that represents a candidate in an election and the different methods 
 * to change candidate attributes.
 *
 */
public class Candidate {
  // create private class variables
  private String name;
  private String party;
  private int numVotes;
  
  /**
   * Constructor for candidate class
   *
   * @param n a String, p a String
   * @return None
   */
  public Candidate (String n, String p) {
    if ((n == null) || (p == null) || (n.isBlank()) || (p.isBlank())) {
      // throw associated exception
      throw new IllegalArgumentException("no null or blank arguments for name or party");
    }
    else {
      name = n;
      party = p;
      numVotes = 0;
    }
  }
  
  /**
   * Getter method for private field numVotes
   *
   * @param None
   * @return numVotes
   */
  public int getNumVotes() {
    return numVotes;
  }
  
  /**
   * Increment votes for a candidate by 1
   *
   * @param None
   * @return None
   */
  public void addVote() {
    numVotes += 1;
  }
  
  /**
   * Overrides equals, check if a given object is comparable to candidate object
   *
   * @param anObject an Object
   * @return true if anObject is a candidate AND exactly matches candidate object, false otherwise
   */
  @Override
  public boolean equals(Object anObject) {
    if (anObject instanceof Candidate) {
      Candidate c = (Candidate)anObject;
      if((name.equals(c.name)) && (party.equals(c.party)) && (numVotes == c.numVotes)) {
        return true;
      }
    }
    return false;
  }
  
  /**
   * Overrides toString(), converts candidate object to proper String format
   *
   * @param None
   * @return String format of candidate, listing name, party, and number of votes
   */
  @Override
  public String toString() {
    return name + " (" + party + "): " + Integer.toString(numVotes);
  }
  
}