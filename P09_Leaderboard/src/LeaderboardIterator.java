//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Leaderboard
// Course:   CS 300 Fall 2024
//
// Author:   Soham Mukherjee
// Email:    smukherjee39@wisc.edu
// Lecturer: Blerina Gkotse
//

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This class is the iterator class for Leaderboard
 * including traversing a Leaderboard in ascending order
 */
public class LeaderboardIterator implements Iterator<Player> {
  private Leaderboard lb;
  private Player currentPlayer;
  
  /**
   * Creates a Leaderboard iterator
   * @param lb a Leaderboard
   * @throws None
   */
  public LeaderboardIterator(Leaderboard lb) {
    this.lb = lb;
    currentPlayer = lb.getMinScore();
  }
  
  /**
   * Checks if a player exists after current player
   * @param None
   * @return true if there exists a next player, false otherwise
   */
  @Override
  public boolean hasNext() {
    return currentPlayer != null;
  }

  /**
   * Gets the next higher player in the leaderboard and iterates currentPlayer
   * @param None
   * @return a reference to the next higher player
   * @throws a NoSuchElementException if there is no next player
   */
  @Override
  public Player next() {
    if(!hasNext()) {
      throw new NoSuchElementException();
    }
    Player playerToReturn = currentPlayer;
    currentPlayer = lb.next(currentPlayer);
    return playerToReturn;
  }
  
}