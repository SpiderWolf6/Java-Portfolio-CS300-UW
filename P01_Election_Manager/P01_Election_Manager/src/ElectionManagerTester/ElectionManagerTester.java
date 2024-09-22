//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Election Manager Tester
// Course:   CS 300 Fall 2024
//
// Author:   Soham Mukherjee
// Email:    smukherjee39@wisc.edu
// Lecturer: Blerina Gkotse
//
import java.util.Arrays;

/**
* This class contains methods for testing the functionality of ElectionManager
*/
public class ElectionManagerTester {
	
  /**
   * Checks whether the list of candidates is empty (all-null)
   *
   * @param None
   * @return false if the containsCandidate method returns true (false expected) and true otherwise
   */
  public static boolean testContainsEmpty() {
   // set up test variables
   String[][] candidateList = {null, null, null, null, null, null};
   int size = 0;
   boolean expected = false;
   
   // call the method we are testing
   boolean actual =
           ElectionManager.containsCandidate(candidateList, size, null, null);
   if (expected != actual) return false;
   return true;
  }
  
  /**
   * Checks whether the list of candidates does not contain a specific candidate
   *
   * @param None
   * @return false if the containsCandidate method returns true (false expected) or if the array was modified and true otherwise
   */  
  public static boolean testDoesNotContain() {
   // set up the test variables
   String[][] candidateList = {
       {"Slowpoke", "Water", "3"},
       {"Squirtle", "Water", "127"},
       {"Wooper", "Water", "300"},
       null, null, null};
   String[][] candidateCopy = Arrays.copyOf(candidateList, candidateList.length);
   int size = 3;
   String targetName = "Charizard";
   String targetParty = "Fire";
   boolean expected = false;

   // call the method we are testing
   boolean actual =
       ElectionManager.containsCandidate(candidateList, size, targetName, targetParty);
  
   if (expected != actual) return false;
  
   if (!Arrays.deepEquals(candidateList, candidateCopy)) return false;
  
   return true;
  }
  
  /**
  * PROVIDED TESTER METHOD: example test method for verifying whether a candidate has
  * already been added to the race.
  *
  * NOTE: This method ONLY tests scenarios where the candidate IS PRESENT in the list;
  * situations where the candidate is not present or the list is empty should be
  * tested in the other contains tester methods.
  *
  * @return false if any of the scenarios we test have results other than what we expect;
  * true ONLY if all of our expectations are met by the method we are testing
  */
 public static boolean testDoesContain() {
  
   // (1a) set up the test variables
   String[][] candidateList = {
       {"Slowpoke", "Water", "3"},
       {"Squirtle", "Water", "127"},
       {"Wooper", "Water", "300"},
       null, null, null};
   String[][] candidateCopy = Arrays.copyOf(candidateList, candidateList.length);
   int size = 3;
   String targetName = "Wooper";
   String targetParty = "Water";
   boolean expected = true;
  
   // (1b) call the method we are testing
   boolean actual =
       ElectionManager.containsCandidate(candidateList, size, targetName, targetParty);
  
   // (2) verify that the expected method return value and the actual return value match
   if (expected != actual) return false;
  
   // (3) since THIS method should not modify the array, check it against a copy we made
   if (!Arrays.deepEquals(candidateList, candidateCopy)) return false;
  
   // (4) if we have not yet returned false, we can now return true as all tests have passed!
   return true;
  
  }
 
  /**
   * Checks whether new candidate has been added to the empty list of candidates (all-null)
   *
   * @param None
   * @return false if the addCandidate method returns an unexpected int or if the array was modified incorrectly 
   * true otherwise ONLY if the conditions have been met correctly
   */  
  public static boolean testAddToEmpty() {
   // set up the test variables
   String[][] candidateList = {null, null, null, null, null, null};
   int size = 0;
  
   String newName = "Goldeen";
   String newParty = "Water";
   int newVotes = 5;
  
   String[][] expectedList = {
           {"Goldeen", "Water", "5"}, 
           null, null, null, null, null};  
   int expected = 1;
  
   // call the method we are testing
   int actual =
       ElectionManager.addCandidate(candidateList, size, newName, newParty, newVotes);
  
   // check that the expected value matches the returned value
   if (expected != actual) return false;
  
   // this method modifies the input array; check if it was modified correctly
   if (!Arrays.deepEquals(candidateList, expectedList)) return false;
  
   // if we have not yet returned false, we can now return true as all checks are complete
   return true;
  }
  
  /**
  * PROVIDED TESTER METHOD: example test method for verifying whether a new candidate has
  * been added correctly to the race.
  *
  * @return false if any of the scenarios we test have results other than what we expect;
  * true ONLY if all of our expectations are met by the method we are testing
  */
 public static boolean testAddToNonEmpty() {
  
   // (1a) set up the test variables
   String[][] candidateList = {
       {"Slowpoke", "Water", "3"},
       {"Squirtle", "Water", "127"},
       {"Wooper", "Water", "300"},
       null, null, null};
   String newName = "Goldeen";
   String newParty = "Water";
   int newVotes = 5;
  
   String[][] expectedList = {
       {"Goldeen", "Water", "5"}, // alphabetically first, new candidate will be added here
       {"Slowpoke", "Water", "3"},
       {"Squirtle", "Water", "127"},
       {"Wooper", "Water", "300"},
       null, null};  // now only TWO null values in this length-6 array!
   int size = 3;
   int expected = 4;
  
   // (1b) call the method we are testing
   int actual =
       ElectionManager.addCandidate(candidateList, size, newName, newParty, newVotes);
  
   // (2) verify that the expected method return value and the actual return value match
   if (expected != actual) return false;
  
   // (3) this method modifies the input array; verify that it was modified correctly
   if (!Arrays.deepEquals(candidateList, expectedList)) return false;
  
   // (4) if we have not yet returned false, we can now return true as all tests have passed!
   return true;
 }
 
  /**
   * Checks whether duplicate candidate or candidate with negative votes can be added to the list of candidates 
   *
   * @param None
   * @return false if the addCandidate method returns an unexpected int or if the array was modified
   * true otherwise ONLY if the conditions have been met correctly
   */ 
  public static boolean testAddCandidateErrors() {
   // set up the test variables
   String[][] candidateList = {
       {"Slowpoke", "Water", "3"},
       {"Squirtle", "Water", "127"},
       {"Wooper", "Water", "300"},
       null, null, null};
  
   String newName = "Goldeen";
   String newParty = "Water";
   int newVotes = -10;
  
   String[][] expectedList = {
       {"Slowpoke", "Water", "3"},
       {"Squirtle", "Water", "127"},
       {"Wooper", "Water", "300"},
       null, null, null}; 
   int size = 3;
   int expected = 3;
  
   // call the method we are testing
   int actual1 =
       ElectionManager.addCandidate(candidateList, size, newName, newParty, newVotes);
  
   newName = "Wooper";
   newParty = "Water";
   newVotes = 300;
  
   int actual2 =
       ElectionManager.addCandidate(candidateList, size, newName, newParty, newVotes);
  
   // check that the expected value matches the returned value 
   if ((expected != actual1) & (expected != actual2)) return false;
  
   // this method modifies the input array; check if it was modified correctly
   if (!Arrays.deepEquals(candidateList, expectedList)) return false;
  
   // if we have not yet returned false, we can now return true as all checks are complete
   return true;  
  }
  
  /**
   * Checks whether new candidate can be added to a full list of candidates 
   *
   * @param None
   * @return false if the addCandidate method returns an unexpected int or if the array was modified
   * true otherwise ONLY if the conditions have been met correctly
   */ 
  public static boolean testAddToFull() {
	// set up the test variables
   String[][] candidateList = {
   	{"Aegislash", "Steel", "29"},	
   	{"Charizard", "Fire", "100"},	
   	{"Goldeen", "Water", "5"},	
       {"Slowpoke", "Water", "3"},
       {"Squirtle", "Water", "127"},
       {"Wooper", "Water", "300"},
   };
  
   String newName = "Blastoise";
   String newParty = "Water";
   int newVotes = 34;
  
   String[][] expectedList = {
   	{"Aegislash", "Steel", "29"},	
   	{"Charizard", "Fire", "100"},	
   	{"Goldeen", "Water", "5"},	
       {"Slowpoke", "Water", "3"},
       {"Squirtle", "Water", "127"},
       {"Wooper", "Water", "300"},
   };
   int size = 6;
   int expected = 6;
  
   // call the method we are testing
   int actual =
       ElectionManager.addCandidate(candidateList, size, newName, newParty, newVotes);
  
   // check that the expected value matches the returned value
   if (expected != actual) return false;
  
   // this method modifies the input array; check if it was modified correctly
   if (!Arrays.deepEquals(candidateList, expectedList)) return false;
  
   // if we have not yet returned false, we can now return true as all checks are complete
   return true;     
  }

  /**
   * Checks whether only candidate has been dropped from the list of candidates
   *
   * @param None
   * @return false if the dropCandidate method returns an unexpected int or if the array was modified incorrectly 
   * true otherwise ONLY if the conditions have been met correctly
   */ 
  public static boolean testDropOnlyCandidate() {
   // set up the test variables
   String[][] candidateList = {
       {"Slowpoke", "Water", "3"},
       null, null, null, null, null};
   String name = "Slowpoke";
   String party = "Water";
   int size = 1;
   int expected = 0;
  
   String[][] expectedList = {null, null, null, null, null, null};
  
   // call the method we are testing
   int actual =
       ElectionManager.dropCandidate(candidateList, size, name, party);
  
   // check that the expected value matches the returned value
   if (expected != actual) return false;
   // this scenario should NOT modify the input array; check it against a copy we made
   if (!Arrays.deepEquals(candidateList, expectedList)) return false;
  
   // if we have not yet returned false, we can now return true as all checks are complete
   return true; 
  }
  
  /**
   * Checks whether the the first candidate has been dropped from the list of candidates
   *
   * @param None
   * @return false if the dropCandidate method returns an unexpected int or if the array was modified incorrectly 
   * true otherwise ONLY if the conditions have been met correctly
   */ 
  public static boolean testDropFirstCandidate() {
   // set up the test variables
   String[][] candidateList = {
       {"Slowpoke", "Water", "3"},
       {"Squirtle", "Water", "127"},
       {"Wooper", "Water", "300"},
       null, null, null};
    String name = "Slowpoke";
   String party = "Water";
   int size = 3;
   int expected = 2;
  
   String[][] expectedList = {
           {"Squirtle", "Water", "127"},
           {"Wooper", "Water", "300"},
           null, null, null, null
   };
  
   // call the method we are testing
   int actual =
       ElectionManager.dropCandidate(candidateList, size, name, party);
  
   // check that expected value matches return value
   if (expected != actual) return false;
   // this scenario should NOT modify the input array; check it against a copy we made
   if (!Arrays.deepEquals(candidateList, expectedList)) return false;
  
   // if we have not yet returned false, we can now return true as all checks are complete
   return true;
  }
  
  /**
  * PROVIDED TESTER METHOD: example test method for verifying whether trying to drop a
  * candidate who is not running in the race correctly has NO effect on the candidate list.
  *
  * @return false if any of the scenarios we test have results other than what we expect;
  * true ONLY if all of our expectations are met by the method we are testing
  */
 public static boolean testDropCandidateNotRunning() {
  
   // (1a) set up the test variables
   String[][] candidateList = {
       {"Slowpoke", "Water", "3"},
       {"Squirtle", "Water", "127"},
       {"Wooper", "Water", "300"},
       null, null, null};
   String[][] candidateCopy = Arrays.copyOf(candidateList, candidateList.length);
   String name = "Goldeen";
   String party = "Water";
   int size = 3;
   int expected = 3;
  
   // (1b) call the method we are testing
   int actual =
       ElectionManager.dropCandidate(candidateList, size, name, party);
  
   // (2) verify that the expected method return value and the actual return value match
   if (expected != actual) return false;
  
   // (2a) sometimes you may want to REPEAT the process with slightly different variables:
   name = "Slowpoke";
   party = "Fire"; // try with a name that's present but a different PARTY; should still not drop
   actual = ElectionManager.dropCandidate(candidateList, size, name, party);
   if (expected != actual) return false;
  
   // (3) this scenario should NOT modify the input array; check it against a copy we made
   if (!Arrays.deepEquals(candidateList, candidateCopy)) return false;
  
   // (4) if we have not yet returned false, we can now return true as all tests have passed!
   return true;
 }
 
 
  /**
   * Checks whether the winner is found correctly in a candidate list with size 1 (only candidate)
   *
   * @param None
   * @return false if the findWinner method returns an incorrect string or if the array was modified at all
   * true otherwise ONLY if the conditions have been met correctly
   */ 
  public static boolean testUncontestedWinner() {
   // set up the test variables
   String[][] candidateList = {
       {"Slowpoke", "Water", "3"},
       null, null, null, null, null};
   String[][] candidateCopy = Arrays.copyOf(candidateList, candidateList.length);
   String expectedName = "Slowpoke";
   String expectedParty = "(Water)";
   double expectedVotePct = 3.0/(3)*100;
   int size = 1;
  
   // call the method we are testing
   String result = ElectionManager.findWinner(candidateList, size);
      
   // get the space-separated pieces of the string
   String[] resultPieces = result.split(" ");
  
   // test for wrong format, name, or party
   if (resultPieces.length != 4) return false; 
   if (!resultPieces[3].endsWith("%")) return false; 
  
   if (!resultPieces[0].equals(expectedName) || !resultPieces[1].equals(expectedParty))
     return false; 
  
   if (!resultPieces[2].equals("-")) return false;
  
   // do a range check on the calculated vote percentage (margin of error)
   double actualVotePct = Double.valueOf(resultPieces[3].substring(0,resultPieces[3].length()-1));
   if (Math.abs(actualVotePct-expectedVotePct) > 0.01) return false;
  
   // this scenario should NOT modify the input array; check it against a copy we made
   if (!Arrays.deepEquals(candidateList, candidateCopy)) return false;
  
   // if we have not yet returned false, we can now return true as all checks are complete
   return true;
  }

  /**
  * PROVIDED TESTER METHOD: example test method for verifying the results of an election
  * where one candidate has received a clear majority of the votes cast.
  *
  * @return false if any of the scenarios we test have results other than what we expect;
  * true ONLY if all of our expectations are met by the method we are testing
  */
  public static boolean testClearWinner() {
  
   // (1a) set up the test variables
   String[][] candidateList = {
       {"Slowpoke", "Water", "3"},
       {"Squirtle", "Water", "97"},
       {"Wooper", "Water", "300"},
       null, null, null};
   String[][] candidateCopy = Arrays.copyOf(candidateList, candidateList.length);
   String expectedName = "Wooper";
   String expectedParty = "(Water)";
   double expectedVotePct = 300.0/(300+97+3)*100;
   int size = 3;
  
   // (1b) call the method we are testing
   String result = ElectionManager.findWinner(candidateList, size);
      
   // (2) verify that the expected method return value and the actual return value match
   // NOTE: for a String, this takes a little more processing to do sensitively.
   // We expect this result to be "Wooper (Water) - 75.0%" but there may be some weirdness
   // especially with that percentage. See how we do it here:
  
   String[] resultPieces = result.split(" "); // get the space-separated pieces of the string
  
   if (resultPieces.length != 4) return false; // incorrect formatting
   if (!resultPieces[3].endsWith("%")) return false; // no % at the end
  
   if (!resultPieces[0].equals(expectedName) || !resultPieces[1].equals(expectedParty))
     return false; // wrong name or wrong party
  
   if (!resultPieces[2].equals("-")) return false; // forgot the "-" between party and %
  
   // do a range check on the calculated vote percentage, since it's not always going to come out
   // exactly the same:
   double actualVotePct = Double.valueOf(resultPieces[3].substring(0,resultPieces[3].length()-1));
   if (Math.abs(actualVotePct-expectedVotePct) > 0.01) return false;
  
   // (3) this scenario should NOT modify the input array; check it against a copy we made
   if (!Arrays.deepEquals(candidateList, candidateCopy)) return false;
  
   // (4) if we have not yet returned false, we can now return true as all tests have passed!
   return true;
  }
 
  /**
   * Checks whether the winner is found correctly if no candidate earned more than 50% of total votes
   *
   * @param None
   * @return false if the findWinner method returns an incorrect string or if the array was modified at all
   * true otherwise ONLY if the conditions have been met correctly
   */ 
  public static boolean testContingentElection() {
   // set up the test variables
   String[][] candidateList = {
       {"Slowpoke", "Water", "90"},
       {"Squirtle", "Water", "95"},
       {"Wooper", "Water", "100"},
       null, null, null};
   String[][] candidateCopy = Arrays.copyOf(candidateList, candidateList.length);
   String expected = "CONTINGENT";
   int size = 3;
  
   // call the method we are testing
   String result = ElectionManager.findWinner(candidateList, size);
      
   if (!result.equals(expected)) return false;
  
   return true;
  }
  
  /**
   * Checks whether the lowest polling candidate is found correctly in a candidate list with size 1 (only candidate)
   *
   * @param None
   * @return false if the findLowestPollingCandidate method returns an incorrect string
   * true otherwise ONLY if the returned string is "UNCONTESTED"
   */
  public static boolean testUncontestedLowestPolling() {
   // set up the test variables
   String[][] candidateList = {
       {"Slowpoke", "Water", "3"},
       null, null, null, null, null};
   int size = 1;
   String expected = "UNCONTESTED";
  
   // call the method we are testing
   String actual = ElectionManager.findLowestPollingCandidate(candidateList, size);
  
   if (!actual.equals(expected)) return false;
  
   return true; 
  }
  
  /**
   * Checks whether the lowest polling candidate is found correctly in the list of candidates
   *
   * @param None
   * @return false if the findLowestPollingCandidate method returns an incorrect string
   * true otherwise ONLY if the returned string matches expected string
   */  
  public static boolean testLowestUniqueVoteCount() {
   // set up the test variables
   String[][] candidateList = {
           {"Slowpoke", "Water", "3"},
           {"Wailord", "Water", "10"},
           null, null, null, null};
   int size = 2;
   String expected = "Slowpoke (Water) - 3";
  
   // call the method we are testing
   String actual = ElectionManager.findLowestPollingCandidate(candidateList, size);
  
   if (!actual.equals(expected)) return false;
  
   return true;
  }
  
  /**
   * Checks whether the lowest polling candidate is found correctly if two candidates are tied
   *
   * @param None
   * @return false if the findLowestPollingCandidate method returns an incorrect string
   * true otherwise ONLY if the returned string matches expected string
   */  
  public static boolean testLowestVoteCountTied() {
   // set up the test variables
   String[][] candidateList = {
           {"Slowpoke", "Water", "3"},
           {"Wailord", "Water", "3"},
           null, null, null, null};
   int size = 2;
   String expected = "Slowpoke (Water) - 3";
  
   // call the method we are testing
   String actual = ElectionManager.findLowestPollingCandidate(candidateList, size);
  
   if (!actual.equals(expected)) return false;
  
   return true;
  }
  
  /**
   * PROVIDED MAIN METHOD to manage the tester methods above.
   *
   * We're getting a little esoteric here to take advantage of loops to keep the code short;
   * each pass through the loop could also be written as follows:
   *
   *   boolean singleTest = testMethodCall();
   *   allPass &= singleTest;
   *   System.out.println("testMethodCall : " + singleTest);
   *
   * @throws NoSuchMethodException if you spell a method name incorrectly
   * 
   * And a couple of other "checked" exceptions that should never happen with our usage here:
   * @throws IllegalAccessException
   * @throws java.lang.reflect.InvocationTargetException
   */
  public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException,
   java.lang.reflect.InvocationTargetException {
   boolean allPass = true, singlePass = true;
   String printFormat = "%-29s: %s\n";
  
   // NOTE TO STUDENTS: If you create any additional tests for any of the methods in
   // ElectionManager, add their names to the appropriate array below!
   String[] containsTests = {"testContainsEmpty", "testDoesNotContain", "testDoesContain"};
   String[] addTests = {"testAddToEmpty", "testAddToNonEmpty", "testAddCandidateErrors",
       "testAddToFull"};
   String[] dropTests = {"testDropOnlyCandidate", "testDropFirstCandidate",
       "testDropCandidateNotRunning"};
   String[] winTests = {"testUncontestedWinner", "testClearWinner", "testContingentElection"};
   String[] lowTests = {"testUncontestedLowestPolling", "testLowestUniqueVoteCount",
       "testLowestVoteCountTied"};
  
   String[][] testNames = {containsTests, addTests, dropTests, winTests, lowTests};
  
   // NOTE TO STUDENTS: this for-loop is moving through the method names we've added to the 2D
   // array testNames and attempting to call methods with those names from this tester
   // (specifically line 286 here). See Java's reflection framework for more details!
   for (String[] testSet : testNames) {
     for (String name : testSet) {
       singlePass = (boolean) ElectionManagerTester.class.getDeclaredMethod(name).invoke(null);
       allPass &= singlePass;
       System.out.printf(printFormat, name, singlePass);
     }
     System.out.println();
   }
   System.out.println("ALL TESTS: "+allPass);
  }
}

