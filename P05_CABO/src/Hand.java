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

import java.util.ArrayList;

/**
 * A class for managing the cards in a given player's hands
 */
public class Hand extends Deck {
  private final int HAND_SIZE = 4;
  
  /**
  constructor method for the hand class*/
  public Hand() {
    super(new ArrayList<>());
  }
  
  /**
  method to add a new card to the hand
  @param card - card to be added to the hand*/
  @Override
  public void addCard(BaseCard card) {
    if(cardList.size() < HAND_SIZE) {
      cardList.add(card);
    }
    else {
      throw new IllegalStateException("Player is already holding the maximum number of cards");
    }
  }
  
  /**
  method that switches a card in the current hand with another card
  @param newCard - the new card being swapped
  @param index - the index of where to place the hand
  @return - the card previously in the index*/
  public BaseCard swap(BaseCard newCard, int index) {
    BaseCard currentCard = cardList.get(index);
    cardList.set(index, newCard);
    return currentCard;
  }
  
  /**
  Method that switches a card in this hand with a card in the other hand
  @param myIndex - index of the card in this hand to switch
  @param otherHand - the other hand to switch cards with
  @param otherIndex - The index of the card in the other hand to switch*/
  public void switchCards(int myIndex, Hand otherHand, int otherIndex) {
    BaseCard otherCard = otherHand.cardList.get(otherIndex);
    otherHand.cardList.set(otherIndex, cardList.get(myIndex));
    cardList.set(myIndex, otherCard);
  }
  
  /**
  Method that changes the face up value of the card at the given index to the provided value
  @param index index of the card to change
  @param faceUp - true if the card should be face up, false otherwise*/
  public void setFaceUp(int index, boolean faceUp) {
    cardList.get(index).setFaceUp(faceUp);
  }
  
  /**
  Draws the whole hand at the y coordinate
  @param y - the y coordinate of the upper-left corner of all the card in this hand*/
  public void draw(int y) {
    for(int i = 0; i<cardList.size() ;i++) {
      cardList.get(i).draw(50+60*i, y);
    }
    //TODO
  }
  
  /**
  Method that checks if the mouse is currently over any of the card in the hand and returns the 
  index of the card the mouse is over. Returns -1 if the mouse is not over a card
  @return - index of the card the mouse is over, -1 otherwise*/
  public int indexOfMouseOver() {
    for(int i = 0;i<cardList.size();i++) {
      if(cardList.get(i).isMouseOver()) {
        return i;
      }
    }
    //TODO
    return -1;
  }
  
  /**
  Method that gets the rank of a card at a given index
  @param index - of the card to access
  @return the rank of the card at that index*/
  public int getRankAtIndex(int index) {
    int currentRank = cardList.get(index).getRank();
    return currentRank;
  }
  
  /**
  Method that determines the total value of the cards in this hand as a sum of their ranks
  @return the total value of the Player's hand*/
  public int calcHand() {
    int value = 0;
    for (int i=0; i<cardList.size(); i++) {
      value += cardList.get(i).getRank();
    }
    return value;
  }
  
}