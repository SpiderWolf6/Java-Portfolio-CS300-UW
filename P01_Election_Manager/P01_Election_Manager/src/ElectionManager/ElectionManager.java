package ElectionManager;
//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Election Manager
// Course:   CS 300 Fall 2024
//
// Author:   Soham Mukherjee
// Email:    smukherjee39@wisc.edu
// Lecturer: Blerina Gkotse
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:    (name of your pair programming partner)
// Partner Email:   (email address of your programming partner)
// Partner Lecturer's Name: (name of your partner's lecturer)
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   ___ Write-up states that pair programming is allowed for this assignment.
//   ___ We have both read and understand the course Pair Programming Policy.
//   ___ We have registered our team prior to the team registration deadline.
//
//////////////////////// ASSISTANCE/HELP CITATIONS ////////////////////////////
//
// Persons:         Soham Mukherjee
// Online Sources:  N/A
//
///////////////////////////////////////////////////////////////////////////////
import java.util.Arrays;
/**
* This class contains methods for managing the candidates in an election
*/
//public class ElectionManager extends ElectionManagerTester {
public class ElectionManager {
	
	/**
	 * Checks whether the list of candidates contains a specific candidate
	 *
	 * @param candidates an oversize String array, numCandidates an int, name a String, party a String
	 * @return true if the name and party are contained in the candidate list and false otherwise
	 */
	public static boolean containsCandidate(String[][] candidates, int numCandidates, String name, String party) {
		for (int i = 0; i<numCandidates; i++) {
			if ((candidates[i][0] == name) & (candidates[i][1] == party)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Adds a specific candidate to the list of candidates
	 *
	 * @param candidates an oversize String array, numCandidates an int, name a String, party a String, numVotes an int
	 * @return new size of candidates (numCandidates+1) if candidate added successfully and numCandidates otherwise
	 */
	public static int addCandidate(String[][] candidates, int numCandidates, String name, String party, int numVotes) {
		String[] a;
		a = new String[] {name, party, Integer.toString(numVotes)};
		if (numCandidates == 0) {
			candidates[0] = a;
			return numCandidates+1;
		}
		else {
			if ((!containsCandidate(candidates, numCandidates, name, party)) & (numVotes > 0) & (numCandidates < candidates.length)) {
				for (int i = numCandidates-1; i>-1; i--) {
					if (name.compareTo(candidates[i][0]) < 0) {
						candidates[i+1] = candidates[i];
						candidates[i] = a;
//						System.out.println(Arrays.deepToString(candidates));
					}
				}
				return numCandidates+1;
			}
			return numCandidates;
		}
	}
	
	/**
	 * Drops a specific candidate from the list of candidates
	 *
	 * @param candidates an oversize String array, numCandidates an int, name a String, party a String
	 * @return new size of candidates (numCandidates-1) if candidate dropped successful numCandidates otherwise
	 */
	public static int dropCandidate(String[][] candidates, int numCandidates, String name, String party) {
		if (containsCandidate(candidates, numCandidates, name, party)) {
			if (numCandidates == 1) {
				candidates[0] = null;
			}
			else {
				for (int i = numCandidates-1; i>-1; i--) {
					if ((candidates[i][0] == name) & (candidates[i][1] == party)) {
						candidates[i] = null;
					}
				}
				for (int j = 0; j < numCandidates; j++) {
					if ((candidates[j] == null) & (candidates[j+1] != null)) {
						candidates[j] = candidates[j+1];
						candidates[j+1] = null;
					}
				}
			}
			return numCandidates-1;
		}
		else {
			return numCandidates;
		}
	}
	
	/**
	 * Find the winning candidate of the election
	 *
	 * @param candidates an oversize String array, numCandidates an int
	 * @return "name (party) - votePct%" for the winning candidate with the most votes and "CONTINGENT" otherwise
	 */
	public static String findWinner(String[][] candidates, int numCandidates) {
		int tot_votes = 0;
		double pct;
		for (int i = 0; i<numCandidates; i++) {
			tot_votes = tot_votes + Integer.parseInt(candidates[i][2]);
		}
		for (int i = 0; i<numCandidates; i++) {
			pct = Integer.parseInt(candidates[i][2])/(double)tot_votes;
			if (pct > 0.5) {
//				System.out.println(candidates[i][0] + " (" + candidates[i][1] + ") - " +  pct*100 + "%");
				return candidates[i][0] + " (" + candidates[i][1] + ") - " +  pct*100 + "%";
			}
		}
		return "CONTINGENT";
	}
	
	/**
	 * Find the candidate with the lowest number of votes
	 *
	 * @param candidates an oversize String array, numCandidates an int
	 * @return "name (party) - numVotes" for the candidate with the least number of votes and "UNCONTESTED" otherwise
	 */
	public static String findLowestPollingCandidate(String[][] candidates, int numCandidates) {
		if (numCandidates < 2) {
			return "UNCONTESTED";
		}
		else {
			int min = Integer.parseInt(candidates[0][2]);
			int min_index = 0;
			for (int i = 0; i<numCandidates; i++) {
				if (Integer.parseInt(candidates[i][2]) == min) {
					if ((candidates[i][0].compareTo(candidates[min_index][0])) < 0) {
						min_index = i;
					}
				}
				if (Integer.parseInt(candidates[i][2]) < min) {
					min_index = i;
				}
			}
			return candidates[min_index][0] + " (" + candidates[min_index][1] + ") - " +  candidates[min_index][2];
		}
	}
}

