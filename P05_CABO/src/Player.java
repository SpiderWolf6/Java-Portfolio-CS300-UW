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
 * A class for creating players in the CaboGame and managing player actions and controls
 */
public class Player extends Object {
  private Hand hand;
  private boolean isComputer;
  private int label;
  private String name;
  
  /**
  constructor method for the player class
  @param name - name of the player
  @param label - home of the player
  @param isComputer - boolean that determines if the player is a robot*/
  public Player(String name, int label, boolean isComputer) {
    this.name = name;
    this.label = label;
    this.isComputer = isComputer;
    this.hand = new Hand();
  }
  
  /**
  getter method for the name value
  @return - the name value as a String*/
  public String getName() {
    return name;
  }
  
  /**
  Getter method for the label
  @return the label value as a String*/
  public int getLabel() {
    return label;
  }
  
  /**
  Getter method for the hand value
  @return - the hand of the player*/
  public Hand getHand() {
    return hand;
  }
  
  /**
  Getter method for the isComputer value
  @return - the value of isComputer as a boolean*/
  public boolean isComputer() {
    return isComputer;
  }
  
  /**
  Method that adds a card to the hand
  @param card - card to be added to the hand*/
  public void addCardToHand(BaseCard card) {
    hand.addCard(card);
  }
  
}