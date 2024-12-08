//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    CaboGame
// Course:   CS 300 Fall 2024
//
// Author:   Soham Mukherjee
// Email:    smukherjee39@wisc.edu
// Lecturer: Blerina Gkotse
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
// 
// Partner Name:    Fabio Colindres
// Partner Email:   colindres@wisc.edu
// Partner Lecturer's Name: Blerina Gkotse
// 
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   X Write-up states that pair programming is allowed for this assignment.
//   X We have both read and understand the course Pair Programming Policy.
//   X We have registered our team prior to the team registration deadline.
//

/**
 * A class for managing special BaseCards with unique CaboGame actions
 * 
 */
public class ActionCard extends BaseCard {
  private String actionType;
  
  /**
  Constructor for the ActionCard class
  @param rank of the card
  @param suit of the card
  @param actionType of the card*/
  public ActionCard(int rank, String suit, String actionType) {
    super(rank, suit);
    this.actionType = actionType;
  }
  
  /**
  Method to access the ActionType of a card and returns it as a String
  @return A string which indicates the action type of the card*/
  public String getActionType() {
    return actionType;
  }
  
}