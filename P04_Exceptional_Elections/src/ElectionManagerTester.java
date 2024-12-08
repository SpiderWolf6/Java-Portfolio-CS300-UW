//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Exceptional Elections
// Course:   CS 300 Fall 2024
//
// Author:   Soham Mukherjee
// Email:    smukherjee39@wisc.edu
// Lecturer: Blerina Gkotse
//

import java.util.NoSuchElementException;

/**
 * A tester class for the Election Manager project. It contains various tests
 * to check the correctness of the Candidate, Election, and Ballot classes.
 *
 */
public class ElectionManagerTester {

  /**
   * Tests the Candidate constructor, toString(), and getter method for correctness.
   * This test accounts for the fact that a bad implementation may throw an Exception.
   *
   * @return true if all tests pass, false otherwise
   */
  public static boolean testCandidateConstructorAndGetters() {

    // in case we get an unexpected exception from a broken implementation
    // we handle it with a try-catch to avoid our tester from crashing
    try { 
      // test the 2-argument constructor
      Candidate c = new Candidate("lebron james", "basketball");

      // check that the instance data fields have been initialized correctly
      // using the toString to do this we are also checking its correctness!
      // in a bad implementation either:
      //   1) the constructor didn't intialize a data field correctly OR
      //   2) toString() doesn't return the correct value
      if (!c.toString().equals("lebron james (basketball): 0")) return false;
      
      // let's also verify the one getter method agrees with the toString() output:
      if (c.getNumVotes() != 0) return false;

    } catch (Exception e) { 
      // we encountered an exception when we should not have, it is a bad implementation!
      e.printStackTrace();
      return false;
    }
    
    // all tests pass:
    return true;
  }

  /**
   * Verifies that the Candidate constructor throws the correct type of exception(s) 
   * where an exception is expected. See the Candidate documentation for details.
   *
   * @return true if all tests pass, false otherwise
   */
  public static boolean testCandidateConstructorExceptions() {
    try { 
      Candidate c = new Candidate("", "basketball");
    } catch (IllegalArgumentException e) { 
      System.out.println(e.getMessage());
      return true;
    }
    return false; // TODO
  }

  /**
   * Tests the Election constructor and associated getter methods for correctness. (Note that
   * SEAT_NAME is a publicly-accessible constant and can be verified directly.)
   * This test accounts for the fact a bad implementation may throw an Exception.
   * 
   * @return true if all tests pass, false otherwise
   */

  public static boolean testElectionConstructorAndGetters() {

    try { 
      Election e = new Election("Best Politician", 5);
      
      if (e.capacity() != 5) return false;

      if (!e.toString().equals("Best Politician")) return false;
      
    } catch (Exception e) { 
      e.printStackTrace();
      return false;
    }
    
    // all tests pass:
    return true; // TODO
  }

  /**
   * Verifies that the Election constructor throws the correct type of exception(s) 
   * in situations where an exception is expected. See the Election documentation for
   * details.
   *
   * @return true if all tests pass, false otherwise
   */
  public static boolean testElectionConstructorExceptions() {
    try { 
      Election e = new Election("Best Politician", -5);
    } catch (IllegalArgumentException e) { 
      System.out.println(e.getMessage());
      return true;
    }
    return false; // TODO
  }

  /**
   * Tests the Election's addCandidate() method for correctness in non-Exception situations.
   * This test accounts for the fact a bad implementation may throw an Exception.
   *
   * @return true if all tests pass, false otherwise
   */
  public static boolean testAddCandidate() {
    try { 
      Election e = new Election("Best Politician", 5);
      e.addCandidate(new Candidate("lebron james", "basketball"));
    } catch (Exception e) { 
      e.printStackTrace();
      return false;
    }
    return true; // TODO
  }
  
  /**
   * Verifies that the Election's addCandidate() method throws the correct type of exception(s) 
   * in situations where an exception is expected. See the Election documentation for
   * details.
   *
   * @return true if all tests pass, false otherwise
   */
  public static boolean testAddCandidateExceptions() {
    try { 
      Election e = new Election("Best Politician", 5);
      e.addCandidate(new Candidate("lebron james", "basketball"));
      e.addCandidate(new Candidate("lebron james", "basketball"));
    } catch (IllegalArgumentException e) { 
      System.out.println(e.getMessage());
      return true;
    }
    return false; // TODO
  }

  /**
   * Tests the Election's vote() method for correctness in non-Exception situations.
   * This test accounts for the fact a bad implementation may throw an Exception.
   *
   * @return true if all tests pass, false otherwise
   */
  public static boolean testVote() {
    try { 
      Election e = new Election("Best Politician", 5);
      e.addCandidate(new Candidate("lebron james", "basketball"));
      e.vote(new Candidate("lebron james", "basketball"));
    } catch (NoSuchElementException e) { 
      System.out.println(e.getMessage());
      return false;
    }
    return true; // TODO
  }

  /**
   * Verifies that the Election's vote() method throws the correct type of exception(s) 
   * in situations where an exception is expected. See the Election documentation for
   * details.
   *
   * @return true if all tests pass, false otherwise
   */
  public static boolean testVoteExceptions() {
    ////////////////////////////////////////////////////////////////////////////////////////
    // we're doing the setup separately, so we can isolate the actual test later.
    // if anything fails HERE, that's a different problem than the one we're trying to test,
    // and the test should fail.
    
    Election election = null; // declare outside of the try block for scope reasons
    try {
      election = new Election("Sportsball", 10);
      Candidate c1 = new Candidate("lebron james", "basketball");
      Candidate c2 = new Candidate("messi", "soccer");
      election.addCandidate(c1); election.addCandidate(c2);
    } catch (Exception e) {
      System.out.println("Unable to continue with this test for unrelated reasons!!");
      e.printStackTrace();
      return false;
    }

    ////////////////////////////////////////////////////////////////////////////////////////
    // THIS part is what we are actually testing:

    try {
      election.vote(new Candidate("usain bolt", "athletics"));
      return false; // this line only runs if NO exception is thrown from the previous line
    } catch(NoSuchElementException e) {
      // this is correct
    } catch(Exception e) {
      // this only runs if an exception other than NoSuchElementException was thrown,
      // which is wrong!
      e.printStackTrace();
      return false;
    }
    
    // all tests pass:
    return true;
  }
  
  /**
   * Tests the Election's removeCandidate() method for correctness.
   * This test accounts for the fact a bad implementation may throw an Exception.
   *
   * @return true if all tests pass, false otherwise
   */
  public static boolean testRemoveCandidate() {
    try { 
      Election e = new Election("Best Politician", 5);
      e.addCandidate(new Candidate("lebron james", "basketball"));
      e.removeCandidate(new Candidate("lebron james", "basketball"));
    } catch (NoSuchElementException e) { 
      System.out.println(e.getMessage());
      return false;
    }
    return true; // TODO
  }

  /**
   * Verifies that the Election's removeCandidate() method throws the correct type of exception(s) 
   * in situations where an exception is expected.
   *
   * @return true if all tests pass, false otherwise
   */
  public static boolean testRemoveCandidateExceptions() {
    boolean expected1 = false;
    boolean expected2 = false;
    try { 
      Election e = new Election("Best Politician", 5);
      e.addCandidate(new Candidate("lebron james", "basketball"));
      e.removeCandidate(new Candidate("lionel messi", "soccer"));
    } catch (NoSuchElementException e) { 
      System.out.println(e.getMessage());
      expected1 = true;
    }
    
    try { 
      Election e = new Election("Best Pokemon", 4);
      e.removeCandidate(new Candidate("charizard", "kanto"));
    } catch (IllegalStateException e) { 
      System.out.println(e.getMessage());
      expected2 = true;
    }
    if (expected1 && expected2) {return true;} else {return false;} // TODO
  }

  /**
   * Tests the Ballot two-phase setup process in non-Exception situations.
   * This test accounts for the fact that a bad implementation may throw an Exception.
   * 
   * @return true if all tests pass, false otherwise
   */
  public static boolean testBallotSetup() {
    Ballot.clearElections();
    
    Election e1 = new Election("Best Politician", 5);
    e1.addCandidate(new Candidate("lebron james", "basketball"));
    e1.addCandidate(new Candidate("lionel messi", "soccer"));
    
    Election e2 = new Election("Best Pokemon", 5);
    e2.addCandidate(new Candidate("charizard", "kanto"));
    e2.addCandidate(new Candidate("greninja", "kalos"));
    
    try {
      Ballot.addElection(e1);
      Ballot.addElection(e2);
      Ballot b = new Ballot();
  
      String expected = "Best Politician: false\n"
          + "Best Pokemon: false";
      
      if (!expected.equals(b.toString())) { 
        System.out.println(expected);
        System.out.println(b.toString());
        System.out.println("its this");
        return false; 
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return false;
    }
    
    // Phase 1: add elections to the Ballot class
    // Phase 2: create a Ballot and verify that it has the correct number of elections
    // (hint: use toString())
    return true; // TODO
  }

  /**
   * Verifies that the Ballot two-phase setup process throws the correct type of exception(s)
   * in situations where an exception is expected. See the Ballot documentation for details.
   * 
   * @return true if all tests pass, false otherwise
   */
  public static boolean testBallotSetupExceptions() {
    Ballot.clearElections();

    // try the phases out of order!
    Election e1 = new Election("Best Politician", 5);
    e1.addCandidate(new Candidate("lebron james", "basketball"));
    e1.addCandidate(new Candidate("lionel messi", "soccer"));
    
    Election e2 = new Election("Best Pokemon", 5);
    e2.addCandidate(new Candidate("charizard", "kanto"));
    e2.addCandidate(new Candidate("greninja", "kalos"));
    
    boolean expected1 = false;
    boolean expected2 = false;
    boolean expected3 = false;
    
    try {
      Ballot b = new Ballot();
    } catch (IllegalStateException e) {
      System.out.println(e.getMessage());
      expected1 = true;
    }

    Ballot.clearElections();
    
    try {
      Ballot.addElection(e1);
      Ballot b = new Ballot();
      Ballot.addElection(e2);
    } catch (IllegalStateException e) {
      System.out.println(e.getMessage());
      expected2 = true;
    }
    
    Ballot.clearElections();
    
    try {
      Ballot.addElection(e1);
      Ballot.addElection(e1);
    } catch (IllegalArgumentException e) {
      System.out.println(e.getMessage());
      expected3 = true;
    }

    return expected1 && expected2 && expected3; // TODO
  }

  /**
   * Tests the Ballot vote() and hasVoted() methods in non-Exception situations.
   * This test accounts for the fact that a bad implementation may throw an Exception.
   * 
   * @return true if all tests pass, false otherwise
   */
  public static boolean testBallotVote() {
    Ballot.clearElections();
    
    Election e1 = new Election("Best Politician", 5);
    e1.addCandidate(new Candidate("lebron james", "basketball"));
    e1.addCandidate(new Candidate("lionel messi", "soccer"));
    
    Election e2 = new Election("Best Pokemon", 5);
    e2.addCandidate(new Candidate("charizard", "kanto"));
    e2.addCandidate(new Candidate("greninja", "kalos"));
    
    try {
      Ballot.addElection(e1);
      Ballot.addElection(e2);
      Ballot b = new Ballot();
      b.vote("Best Politician", new Candidate("lionel messi", "soccer"));
      if ((b.hasVoted("Best Politician") && (!b.hasVoted("Best Pokemon")))) {
        return true;
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return false;
    }
    return false; // TODO
  }

  /**
   * Verifies that the Ballot vote() and hasVoted() methods throw the correct type of
   * exception(s) in situations where an exception is expected. See the Ballot documentation
   * for details.
   * 
   * @return true if all tests pass, false otherwise 
   */
  public static boolean testBallotVoteExceptions() {
    Ballot.clearElections();
    boolean expected1 = false;
    boolean expected2 = false;
    boolean expected3 = false;
    
    Election e1 = new Election("Best Politician", 5);
    e1.addCandidate(new Candidate("lebron james", "basketball"));
    e1.addCandidate(new Candidate("lionel messi", "soccer"));
    
    Election e2 = new Election("Best Pokemon", 5);
    e2.addCandidate(new Candidate("charizard", "kanto"));
    e2.addCandidate(new Candidate("greninja", "kalos"));
    
    Ballot.addElection(e1);
    Ballot.addElection(e2);
    Ballot b = new Ballot();
    
    try {
      b.vote("Best Athlete", new Candidate("lionel messi", "soccer"));
      return false;
    } catch (NoSuchElementException e) {
      System.out.println(e.getMessage());
      expected1 = true;
    }
    
    try {
      b.vote("Best Pokemon", new Candidate("blastoise", "kanto"));
      return false;
    } catch (NoSuchElementException e) {
      System.out.println(e.getMessage());
      expected2 = true;
    }
    
    try {
      b.vote("Best Politician", new Candidate("lebron james", "basketball"));
      b.vote("Best Politician", new Candidate("lionel messi", "soccer"));
      return false;
    } catch (IllegalStateException e) {
      System.out.println(e.getMessage());
      expected3 = true;
    }
    
    if (expected1 && expected2 && expected3) {
      return true;
    }
    
    return false; // TODO
  }

  /**
   * Runs all testing methods and prints out their results.
   * @return true if and only if all the tests return true, false otherwise
   */
  public static boolean runAllRequiredTests() {
    
    boolean test1 = testCandidateConstructorAndGetters();
    System.out.println("testCandidateConstructorAndGetters(): " + (test1 ? "PASS" : "FAIL"));
    
    boolean test2 = testCandidateConstructorExceptions();
    System.out.println("testCandidateConstructorExceptions(): " + (test2 ? "PASS" : "FAIL"));

    boolean test3 = testElectionConstructorAndGetters();
    System.out.println("testElectionConstructorAndGetters(): " + (test3 ? "PASS" : "FAIL"));

    boolean test4 = testElectionConstructorExceptions();
    System.out.println("testElectionConstructorExceptions(): " + (test4 ? "PASS" : "FAIL"));

    boolean test5 = testAddCandidate();
    System.out.println("testAddCandidate(): " + (test5 ? "PASS" : "FAIL"));
    
    boolean test6 = testAddCandidateExceptions();
    System.out.println("testAddCandidateExceptions(): " + (test6 ? "PASS" : "FAIL"));

    boolean test7 = testVote();
    System.out.println("testVote(): " + (test7 ? "PASS" : "FAIL"));

    boolean test8 = testVoteExceptions();
    System.out.println("testVoteExceptions(): " + (test8 ? "PASS" : "FAIL"));

    boolean test9 = testRemoveCandidate();
    System.out.println("testRemoveCandidate(): " + (test9 ? "PASS" : "FAIL"));

    boolean test10 = testRemoveCandidateExceptions();
    System.out.println("testRemoveCandidateExceptions(): " + (test10 ? "PASS" : "FAIL"));

    boolean test11 = testBallotSetup();
    System.out.println("testBallotSetup(): " + (test11 ? "PASS" : "FAIL"));

    boolean test12 = testBallotSetupExceptions();
    System.out.println("testBallotSetupExceptions(): " + (test12 ? "PASS" : "FAIL"));

    boolean test13 = testBallotVote();
    System.out.println("testBallotVote(): " + (test13 ? "PASS" : "FAIL"));

    boolean test14 = testBallotVoteExceptions();
    System.out.println("testBallotVoteExceptions(): " + (test14 ? "PASS" : "FAIL"));

    return test1 && test2 && test3 && test4 && test5 && test6 && test7 && test8 && test9 && test10
        && test11 && test12 && test13 && test14;
  }

  /**
   * Calls runAllRequiredTests and displays the output. If you add additional private testers, call 
   * them directly in the main method rather than adding them to the previous method.
   * @param args unused
   */
  public static void main(String[] args) {
    System.out.println("runAllRequiredTests(): " + runAllRequiredTests());
  }


}
