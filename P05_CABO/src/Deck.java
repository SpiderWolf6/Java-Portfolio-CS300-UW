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
import java.util.Collections;

/**
 * The Deck class represents a deck of playing cards for the game Cabo. It manages a collection of
 * cards, including shuffling, drawing, and adding cards.
 */
public class Deck {
  
  protected ArrayList<BaseCard> cardList;
  protected static processing.core.PApplet processing;
  
  
  public Deck(ArrayList<BaseCard> deck) {
    if(Deck.processing == null) {
      throw new IllegalStateException("Processing environment has not been set");
    }
    if (deck.size() == 0) {
      cardList = new ArrayList<>();
    }
    else {
      cardList = deck;
    }
  }
  
  // TODO: add everything else
  
  /**
   * Sets up the deck with CABO cards, including action cards. Initializes the deck with all
   * necessary cards and shuffles them.
   *
   * @return the completed ArrayList of CABO cards
   */
  public static ArrayList<BaseCard> createDeck() {
    ArrayList<BaseCard> cardList = new ArrayList<>();

    // Define the suits
    String[] suits = {"Clubs", "Diamonds", "Hearts", "Spades"};

    // Cards from 1 (Ace) to 13 (King)
    for (int rank = 1; rank <= 13; ++rank) {
      // Loop through each suit
      for (String suit : suits) {
        if (rank >= 7 && rank <= 12) {
          // Special action cards
          String actionType = "";
          if (rank == 7 || rank == 8) {
            actionType = "peek";
          } else if (rank == 9 || rank == 10) {
            actionType = "spy";
          } else {
            actionType = "switch";
          }
          cardList.add(new ActionCard(rank, suit, actionType));  // Add ActionCard to deck
        } else {
          cardList.add(new BaseCard(rank, suit));  // Add NumberCard to deck
        }
      }
    }
    Collections.shuffle(cardList);
    return cardList;
  }
  
  /**
  Method that sets the processing environment to be used by the deck class
  @param processing - the processing environment to be used for drawing and interaction*/
  public static void setProcessing(processing.core.PApplet processing) {
    Deck.processing = processing;
  }
  
  /**
  Method that draws a card from the top of the deck
  @return top card from the deck, null if the deck is empty*/
  public BaseCard drawCard() {
    if (isEmpty()) {
      return null;
    }
    BaseCard returnCard = cardList.get(cardList.size()-1);
    cardList.remove(cardList.size()-1);
    return returnCard;
  }
  
  /**
  Adds a card to the top(end) of the deck
  @param card*/
  public void addCard(BaseCard card) {
    cardList.add(card);
  }
  
  /**
  Gets the current number of cards in the Deck
  @return the size of the deck as an integer*/
  public int size() {
    return cardList.size();
  }
  
  /**
  Method that checks if the deck is empty
  @return a boolean true or false depending on empty deck */
  public boolean isEmpty() {
    if(cardList.size() == 0) {
      return true;
    }
    return false;
  }
  
  /**
  Method that draws the top card of the deck onto the processing canvas at the specified position
  If empty draws a placeholder to indicate the deck is empty.
  @param x - x coordinate to draw the card
  @param y - y coordinate to draw the card
  @param isDiscard - whether the deck is a discard pile, in which case it should be face up. if
  empty it will draw a placeholder.*/
  public void draw(int x, int y, boolean isDiscard) {
    if(cardList.size() == 0) {
      processing.stroke(0);
      processing.fill(0);
      processing.rect(x, y, 50, 70, 7);
      processing.fill(255);
      processing.textSize(12);
      processing.textAlign(processing.CENTER, processing.CENTER);
      processing.text("Empty", x + 25, y + 35);
    }
    else {
      if(isDiscard) {
        cardList.get(cardList.size() - 1).setFaceUp(isDiscard);
      }
      cardList.get(cardList.size()-1).draw(x, y);
    }
    //TODO
  }
  
}
