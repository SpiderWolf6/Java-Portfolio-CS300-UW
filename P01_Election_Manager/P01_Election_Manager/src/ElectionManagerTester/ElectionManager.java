//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Election Manager
// Course:   CS 300 Fall 2024
//
// Author:   Soham Mukherjee
// Email:    smukherjee39@wisc.edu
// Lecturer: Blerina Gkotse
//

/**
* This class contains methods for managing the candidates in an election
*/
public class ElectionManager {
	
	/**
	 * Checks whether the list of candidates contains a specific candidate
	 *
	 * @param candidates an oversize String array, numCandidates an int, name a String, party a String
	 * @return true if the name and party are contained in the candidate list and false otherwise
	 */
	public static boolean containsCandidate(String[][] candidates, int numCandidates, String name, String party) {
		// loop through candidates starting at first element
	    for (int i = 0; i<numCandidates; i++) {
			if ((candidates[i][0] == name) & (candidates[i][1] == party)) {
				return true; // if candidate exists in array, return true
			}
		}
		return false;
	}
	
	/**
	 * Adds a specific candidate to the list of candidates
	 *
	 * @param candidates an oversize String array, numCandidates an int, name a String, party a String, numVotes an int
	 * @return new size of candidates (numCandidates+1) if candidate added successfully and current value of numCandidates otherwise
	 */
	public static int addCandidate(String[][] candidates, int numCandidates, String name, String party, int numVotes) {
		String[] a;
		a = new String[] {name, party, Integer.toString(numVotes)};
		// check candidates size first
		if (numCandidates == 0) {
			candidates[0] = a;
			return numCandidates+1;
		}
		else {
			if ((!containsCandidate(candidates, numCandidates, name, party)) & (numVotes > 0) & (numCandidates != candidates.length)) {
				// loop through candidates starting at first element
			    for (int i = numCandidates; i>=0; i--) {
					if (i == 0) {
						candidates[i] = a; // set current element to new candidate
						return numCandidates+1;
					}
					// check for alphabetical order
					if (name.compareTo(candidates[i-1][0]) > 0) {
						candidates[i] = a;
						return numCandidates+1;
					}
					else {
						candidates[i] = candidates[i-1]; // shift elements
					}
				}
			}
			return numCandidates;
		}
	}
	
	/**
	 * Drops a specific candidate from the list of candidates
	 *
	 * @param candidates an oversize String array, numCandidates an int, name a String, party a String
	 * @return new size of candidates (numCandidates-1) if candidate dropped successful and current value of numCandidates otherwise
	 */
	public static int dropCandidate(String[][] candidates, int numCandidates, String name, String party) {
	    // check candidates size first
		if (containsCandidate(candidates, numCandidates, name, party)) {
			if (numCandidates == 1) {
				candidates[0] = null;
			}
			else {
			    // loop through candidates twice, starting at first element both times
				for (int i = numCandidates-1; i>-1; i--) {
					if ((candidates[i][0] == name) & (candidates[i][1] == party)) {
						candidates[i] = null; // drop element
					}
				}
				for (int j = 0; j < numCandidates-1; j++) {
					if ((candidates[j] == null) & (candidates[j+1] != null)) {
						candidates[j] = candidates[j+1]; // shift elements
						candidates[j+1] = null; // shift elements
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
		// loop through candidates twice, starting at first element both times
		for (int i = 0; i<numCandidates; i++) {
			tot_votes = tot_votes + Integer.parseInt(candidates[i][2]); // sum votes
		}
		for (int i = 0; i<numCandidates; i++) {
			pct = Integer.parseInt(candidates[i][2])/(double)tot_votes;
			if (pct > 0.5) {
				return candidates[i][0] + " (" + candidates[i][1] + ") - " +  pct*100 + "%"; // return using correct format
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
	    // check candidates size first
		if (numCandidates < 2) {
			return "UNCONTESTED";
		}
		else {
			int min = Integer.parseInt(candidates[0][2]);
			int min_index = 0;
			// loop through candidates starting at first element
			for (int i = 0; i<numCandidates; i++) {
				if (Integer.parseInt(candidates[i][2]) == min) {
					if ((candidates[i][0].compareTo(candidates[min_index][0])) < 0) {
						min_index = i; // set new minimum index
					}
				}
				if (Integer.parseInt(candidates[i][2]) < min) {
					min_index = i;
				}
			}
			return candidates[min_index][0] + " (" + candidates[min_index][1] + ") - " +  candidates[min_index][2]; // return using correct format
		}
	}
}

