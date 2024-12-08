//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Leaderboard
// Course:   CS 300 Fall 2024
//
// Author:   Soham Mukherjee
// Email:    smukherjee39@wisc.edu
// Lecturer: Blerina Gkotse
//

/**
 * Tester class for testing the functionality of the Player and Leaderboard classes
 */
public class LeaderboardTester {
  
  /////////////////////////////////////////// COMPARE TO ///////////////////////////////////////////

  /**
   * Test the different cases for comparing two players
   * 
   * @return true if all of the tests passed, false otherwise
   */
  public static boolean testPlayerCompareTo() {
    boolean test1 = testCompareToDiffScore();
    boolean test2 = testCompareToSameScoreDiffName();
    boolean test3 = testCompareToEqual();
    if (!test1) System.out.print("diffScore FAIL ");
    if (!test2) System.out.print("diffName FAIL ");
    if (!test3) System.out.print("equals FAIL ");
    return test1 && test2 && test3;
  }
  
  /**
   * Tests if two players with different scores are compared correctly
   * 
   * @return true if players are compared correctly, false otherwise
   */
  private static boolean testCompareToDiffScore() {
    Player player1 = new Player("Messi", 1000);
    Player player2 = new Player("Ronaldo", 700);
    if(player1.compareTo(player2) > 0) {
      return true; // check if scores are compared correctly, test passed
    }
    return false;
  }
  
  /**
   * Tests if two players with same scores but different names are compared correctly
   * 
   * @return true if players are compared correctly, false otherwise
   */
  private static boolean testCompareToSameScoreDiffName() {
    Player player1 = new Player("Messi", 1000);
    Player player2 = new Player("Ronaldo", 1000);
    if(player1.compareTo(player2) < 0) {
      return true; // check if names are compared correctly, test passed
    }
    return false;
  }
  
  /**
   * Tests if two players with same scores and same names are compared correctly
   * 
   * @return true if players are compared correctly, false otherwise
   */
  private static boolean testCompareToEqual() {
    Player player1 = new Player("Messi", 1000);
    Player player2 = new Player("Messi", 1000);
    if(player1.compareTo(player2) == 0) {
      return true; // check if players are correctly determined to be equal, test passed
    }
    return false;
  }
  
  ///////////////////////////////////////// LOOKUP: NAME /////////////////////////////////////////
  
  /**
   * Test the different cases for looking up a player in the Leaderboard
   * 
   * @return true if all of the tests passed, false otherwise
   */
  public static boolean testNameLookup() {
    boolean test1 = testLookupRoot();
    boolean test2 = testLookupLeft();
    boolean test3 = testLookupRight();
    boolean test4 = testLookupNotPresent();
    if (!test1) System.out.print("lookupRoot FAIL ");
    if (!test2) System.out.print("lookupLeft FAIL ");
    if (!test3) System.out.print("lookupRight FAIL ");
    if (!test4) System.out.print("lookupNotPresent FAIL ");
    return test1 && test2 && test3 && test4;
  }

  /**
   * Tests if the root player is correctly found using lookUp
   * 
   * @return true if the correct player is returned, false otherwise
   */
  private static boolean testLookupRoot() {
    Leaderboard lb = new Leaderboard(); // create Leaderboard object and start adding players
    Player player1 = new Player("Messi", 1000);
    lb.addPlayer(player1);
    Player foundPlayer = lb.lookup("Messi");
    // check if the returned player matches the added player
    return foundPlayer != null && foundPlayer.equals(player1);
  }

  /**
   * Tests if a player in the left subtree is correctly found using lookUp
   * 
   * @return true if the correct player is returned, false otherwise
   */
  private static boolean testLookupLeft() {
    Leaderboard lb = new Leaderboard(); // create Leaderboard object and start adding players
    Player player1 = new Player("Messi", 1000);
    Player player2 = new Player("Suarez", 900);
    Player player3 = new Player("Neymar", 1100);
    lb.addPlayer(player1);
    lb.addPlayer(player2);
    lb.addPlayer(player3);
    Player foundPlayer = lb.lookup("Suarez");
    // check if the returned player matches the player in the left subtree
    return foundPlayer != null && foundPlayer.equals(player2);
  }

  /**
   * Tests if a player in the right subtree is correctly found using lookUp
   * 
   * @return true if the correct player is returned, false otherwise
   */
  private static boolean testLookupRight() {
    Leaderboard lb = new Leaderboard(); // create Leaderboard object and start adding players
    Player player1 = new Player("Messi", 1000);
    Player player2 = new Player("Suarez", 900);
    Player player3 = new Player("Neymar", 1100);
    lb.addPlayer(player1);
    lb.addPlayer(player2);
    lb.addPlayer(player3);
    Player foundPlayer = lb.lookup("Neymar");
    // check if the returned player matches the player in the right subtree
    return foundPlayer != null && foundPlayer.equals(player3);
  }

  /**
   * Tests if a player not present in the subtree is correctly dealt with
   * 
   * @return true if the returned player is null, and false otherwise
   */
  private static boolean testLookupNotPresent() {
    Leaderboard lb = new Leaderboard(); // create Leaderboard object and start adding players
    Player player1 = new Player("Messi", 1000);
    Player player2 = new Player("Suarez", 900);
    Player player3 = new Player("Neymar", 1100);
    lb.addPlayer(player1);
    lb.addPlayer(player2);
    lb.addPlayer(player3);
    Player foundPlayer = lb.lookup("Pique");
    // ensure the lookup returns null for a player not in the tree
    return foundPlayer == null;
  }
  
  //////////////////////////////////////////// ADD ////////////////////////////////////////////
  
  /**
   * Test the different cases for adding a player
   * 
   * @return true if all of the tests passed, false otherwise
   */
  public static boolean testAdd() {
    boolean test1 = testAddPlayerEmpty();
    boolean test2 = testAddPlayer();
    boolean test3 = testAddPlayerDuplicate();
    if (!test1) System.out.print("addEmpty FAIL ");
    if (!test2) System.out.print("addPlayer FAIL ");
    if (!test3) System.out.print("addDuplicate FAIL ");
    return test1 && test2 && test3;
  }
  

  /**
   * Tests if a player is correctly added to an empty Leaderboard
   * 
   * @return true if the player was added and size updated properly, and false otherwise
   */
  private static boolean testAddPlayerEmpty() {
    Leaderboard lb = new Leaderboard(); // create Leaderboard object and start adding players
    Player player1 = new Player("Messi", 1000);
    boolean added = lb.addPlayer(player1);
    if(lb.size() == 1 && added) {
      return true; // if player added correctly, test passed
    }
    return false;
  }
  
  /**
   * Tests if a player is correctly added to an existing Leaderboard
   * Checks both the left and right subtree cases
   * 
   * @return true if the player was added and size updated properly, and false otherwise
   */
  private static boolean testAddPlayer() {
    boolean checkRight = false;
    boolean checkLeft = false;
    Leaderboard lb = new Leaderboard(); // create Leaderboard object and start adding players
    Player player1 = new Player("Messi", 1000);
    lb.addPlayer(player1);
    Player player2 = new Player("Neymar", 1100);
    int currentSize = lb.size();
    int currentCount = lb.count();
    boolean rightAdded = lb.addPlayer(player2);
    if(lb.size() == currentSize+1 && lb.count() == currentCount+1 && rightAdded && lb.getRoot().getRight().getData().compareTo(lb.getRoot().getData()) > 0) {
      checkRight = true; // if player added correctly to the right subtree, test passed
    }
    currentSize = lb.size();
    currentCount = lb.count();
    Player player3 = new Player("Suarez", 900);
    boolean leftAdded = lb.addPlayer(player3);
    if(lb.size() == currentSize+1 && lb.count() == currentCount+1 && leftAdded && lb.getRoot().getLeft().getData().compareTo(lb.getRoot().getData()) < 0) {
      checkLeft = true; // if player added correctly to the left subtree, test passed
    }
    return checkRight && checkLeft;
  }
  
  /**
   * Tests if a duplicate player is correctly ignored when trying to add to a Leaderboard
   * 
   * @return true if the player was not added and size remained same, and false otherwise
   */
  private static boolean testAddPlayerDuplicate() {
    Leaderboard lb = new Leaderboard(); // create Leaderboard object and start adding players
    Player player1 = new Player("Messi", 1000);
    Player player2 = new Player("Messi", 1000);
    lb.addPlayer(player1);
    int currentSize = lb.size();
    int currentCount = lb.count();
    try {
      boolean added = lb.addPlayer(player2);
      if(lb.size() != currentSize || lb.count() != currentCount || added) {
        return false; // if duplicate player was added, test failed
      }
      return true; // if player not added, test passed
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return false; // if exception thrown, test failed
    }
  }
  
  //////////////////////////////////////////// REMOVE ////////////////////////////////////////////
  
  /**
   * Test the different cases for removing a player
   * 
   * @return true if all of the tests passed, false otherwise
   */
  public static boolean testRemove() {
    boolean test1 = testRemoveLeaf();
    boolean test2 = testRemoveOneChild();
    boolean test3 = testRemoveTwoChildren();
    boolean test4 = testRemoveNotInTree();
    if (!test1) System.out.print("removeLeaf FAIL ");
    if (!test2) System.out.print("removeOneChild FAIL ");
    if (!test3) System.out.print("removeTwoChildren FAIL ");
    if (!test4) System.out.print("removeNotInTree FAIL ");
    return test1 && test2 && test3 && test4;
  }

  /**
   * Tests if a leaf is correctly removed from the Leaderboard
   * 
   * @return true if the leaf was removed properly and size updated correctly, and false otherwise
   */
  private static boolean testRemoveLeaf() {
    Leaderboard lb = new Leaderboard(); // create Leaderboard object and start adding players
    Player player1 = new Player("Messi", 1000);
    Player player2 = new Player("Suarez", 800);
    Player player3 = new Player("Pique", 1100);
    Player player4 = new Player("Neymar", 1200);
    lb.addPlayer(player1);
    lb.addPlayer(player2);
    lb.addPlayer(player3);
    lb.addPlayer(player4);
    int currentSize = lb.size();
    boolean removed = lb.removePlayer(player4); // remove a leaf node
    // verify size, player removed correctly, and tree structure intact
    return removed && lb.size() == currentSize - 1 && lb.lookup("Neymar") == null;
  }

  /**
   * Tests if a player with one child node is correctly removed from the Leaderboard
   * 
   * @return true if the player was removed properly and size updated correctly, and false otherwise
   */
  private static boolean testRemoveOneChild() {
    Leaderboard lb = new Leaderboard(); // create Leaderboard object and start adding players
    Player player1 = new Player("Messi", 1000);
    Player player2 = new Player("Suarez", 800);
    Player player3 = new Player("Ramos", 900);
    Player player4 = new Player("Benzema", 700);
    Player player5 = new Player("Pique", 1100);
    Player player6 = new Player("Neymar", 1200);
    lb.addPlayer(player1);
    lb.addPlayer(player2);
    lb.addPlayer(player3);
    lb.addPlayer(player4);
    lb.addPlayer(player5);
    lb.addPlayer(player6);
    int currentSize = lb.size();
    boolean removed = lb.removePlayer(player5); // remove node with one child
    // verify size, player removed correctly, and tree structure intact
    return removed && lb.size() == currentSize - 1 && lb.lookup("Pique") == null && lb.lookup("Neymar") != null;
  }

  /**
   * Tests if a player with two child nodes is correctly removed from the Leaderboard
   * 
   * @return true if the player was removed properly and size updated correctly, and false otherwise
   */
  private static boolean testRemoveTwoChildren() {
    Leaderboard lb = new Leaderboard(); // create Leaderboard object and start adding players
    Player player1 = new Player("Messi", 1000);
    Player player2 = new Player("Suarez", 800);
    Player player3 = new Player("Ramos", 900);
    Player player4 = new Player("Benzema", 700);
    Player player5 = new Player("Pique", 1100);
    Player player6 = new Player("Neymar", 1200);
    lb.addPlayer(player1);
    lb.addPlayer(player2);
    lb.addPlayer(player3);
    lb.addPlayer(player4);
    lb.addPlayer(player5);
    lb.addPlayer(player6);
    int currentSize = lb.size();
    boolean removed = lb.removePlayer(player2); // remove node with two children
    // verify size, player removed correctly, and tree structure intact
    return removed && lb.size() == currentSize - 1 && lb.lookup("Suarez") == null && lb.lookup("Ramos") != null;
  }

  /**
   * Tests if a player not in the Leaderboard is dealt with correctly
   * 
   * @return true if the size remains size and content remains unchanged, and false otherwise
   */
  private static boolean testRemoveNotInTree() {
    Leaderboard lb = new Leaderboard(); // create Leaderboard object and start adding players
    Player player1 = new Player("Messi", 1000);
    Player player2 = new Player("Suarez", 800);
    Player player3 = new Player("Ramos", 900);
    Player player4 = new Player("Benzema", 700);
    Player player5 = new Player("Pique", 1100);
    Player player6 = new Player("Neymar", 1200);
    lb.addPlayer(player1);
    lb.addPlayer(player2);
    lb.addPlayer(player3);
    lb.addPlayer(player4);
    lb.addPlayer(player5);
    lb.addPlayer(player6);
    int currentSize = lb.size();
    Player newPlayer = new Player("Bale", 100); // not in tree
    boolean removed = lb.removePlayer(newPlayer);
    // verify the size remains unchanged and the method returns false
    return !removed && lb.size() == currentSize;
  }
  
  //////////////////////////////////////////// GET NEXT ////////////////////////////////////////////
  
  /**
   * Test the different cases for getting the next player in the Leaderboard
   * 
   * @return true if all of the tests passed, false otherwise
   */
  public static boolean testGetNext() {
    boolean test1 = testGetNextAfterRoot();
    boolean test2 = testGetNextAfterLeftSubtree();
    boolean test3 = testGetNextAfterRightSubtree();
    if (!test1) System.out.print("afterRoot FAIL ");
    if (!test2) System.out.print("afterLeft FAIL ");
    if (!test3) System.out.print("afterRight FAIL ");
    return test1 && test2 && test3;
  }
  
  /**
   * Tests if the next player from the root node is found correctly
   * 
   * @return true if this returned correct player, and false otherwise
   */
  private static boolean testGetNextAfterRoot() {
    Leaderboard lb = new Leaderboard(); // create Leaderboard object and start adding players
    Player player1 = new Player("Messi", 1000);
    lb.addPlayer(player1);
    Player player2 = new Player("Suarez", 900);
    Player player3 = new Player("Neymar", 1100);
    lb.addPlayer(player2);
    lb.addPlayer(player3);
    Player nextPlayer = lb.next(lb.getRoot().getData());
    if(nextPlayer.compareTo(player3) == 0) {
      return true; // if correct player returned from the root, test passed
    }
    return false;
  }
  
  /**
   * Tests if the next player from the left subtree is found correctly
   * 
   * @return true if this returned correct player, and false otherwise
   */
  private static boolean testGetNextAfterLeftSubtree() {
    Leaderboard lb = new Leaderboard(); // create Leaderboard object and start adding players
    Player player1 = new Player("Messi", 1000);
    lb.addPlayer(player1);
    Player player2 = new Player("Suarez", 800);
    Player player3 = new Player("Pique", 900);
    Player player4 = new Player("Neymar", 1200);
    lb.addPlayer(player2);
    lb.addPlayer(player3);
    lb.addPlayer(player4);
    Player nextPlayer = lb.next(lb.getRoot().getLeft().getData());
    if(nextPlayer.compareTo(player3) == 0) {
      return true; // if correct player returned from the left subtree, test passed
    }
    return false;
  }
  
  /**
   * Tests if the next player from the right subtree is found correctly
   * 
   * @return true if this returned correct player, and false otherwise
   */
  private static boolean testGetNextAfterRightSubtree() {
    Leaderboard lb = new Leaderboard(); // create Leaderboard object and start adding players
    Player player1 = new Player("Messi", 1000);
    lb.addPlayer(player1);
    Player player2 = new Player("Suarez", 800);
    Player player3 = new Player("Pique", 1100);
    Player player4 = new Player("Neymar", 1200);
    lb.addPlayer(player2);
    lb.addPlayer(player3);
    lb.addPlayer(player4);
    Player nextPlayer = lb.next(lb.getRoot().getRight().getData());
    if(nextPlayer.compareTo(player4) == 0) {
      return true; // if correct player returned from the right subtree, test passed
    }
    return false;
  }
  
  //////////////////////////////////////////// MAIN ////////////////////////////////////////////
  
  public static void main(String[] args) {
    // Running and printing results for all the tests

    System.out.print("Player compareTo(): ");
    System.out.println(testPlayerCompareTo()?"PASS":"");
    
    System.out.print("Leaderboard lookup(): ");
    System.out.println(testNameLookup()?"PASS":"");
    
    System.out.print("Leaderboard add(): ");
    System.out.println(testAdd()?"PASS":"");

    System.out.print("Leaderboard remove(): ");
    System.out.println(testRemove()?"PASS":"");

    System.out.print("Leaderboard next(): ");
    System.out.println(testGetNext()?"PASS":"");
  }
  
}
