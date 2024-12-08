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
import processing.core.PApplet;

/**
 * The CaboGame class implements the main game logic for the card game CABO.
 * It manages the deck, discard pile, players, game state, and user interactions.
 */
public class CaboGame extends PApplet {
  private Deck deck;
  private Deck discard;
  private Player[] players;
  private int currentPlayer;
  private boolean gameOver;
  private int caboPlayer;
  private Button[] buttons;
  private int selectedCardFromCurrentPlayer;
  private BaseCard drawnCard;
  
  /**
   * Enum representing the different action states in the game
   * (e.g., swapping cards, peeking, spying, switching).
   * 
   * This allows us to easily restrict the possible values of a variable.
   */
  private enum ActionState {
    NONE, SWAPPING, PEEKING, SPYING, SWITCHING
  }
  private ActionState actionState = ActionState.NONE;
  
  // provided data fields for tracking the players' moves through the game
  private ArrayList<String> gameMessages = new ArrayList<>();
  
  /**
   * Launch the game window; PROVIDED. Note: the argument to PApplet.main() must match the name
   * of this class, or it won't run!
   * @param args unused
   */
  public static void main(String[] args) {
    PApplet.main("CaboGame");
  }
  
  /**
   * Sets up the initial window size for the game; PROVIDED.
   */
  @Override
  public void settings() {
    size(1000, 800);
  }
  
  /**
   * Sets up the game environment, including the font, game state, and game elements.
   */
  @Override
  public void setup() {
    textFont(createFont("Arial", 16));
    BaseCard.setProcessing(this);
    Deck.setProcessing(this);
    Button.setProcessing(this);
    // TODO: setProcessing for the classes which require it
    
    discard = new Deck(new ArrayList<BaseCard>());
    ArrayList<BaseCard> newDeck = new ArrayList<>();
    newDeck = Deck.createDeck();
    deck = new Deck(newDeck);
    deckCheck();
    
    drawnCard = null;
    players = new Player[4];
    players[0] = new Player("Cyntra", 0, false);
    players[1] = new Player("Avalon", 1, true);
    players[2] = new Player("Balthor", 2, true);
    players[3] = new Player("Ophira", 3, true);
    currentPlayer = 0;
    caboPlayer = -1;
    selectedCardFromCurrentPlayer = -1;
    
    setGameStatus("Turn for " + players[currentPlayer].getName());
    
    for(int i = 0; i<4; i++) {
      for(int j = 0; j<players.length; j++) {
        players[j].addCardToHand(deck.drawCard());
      }
    }
    
    players[0].getHand().cardList.get(0).setFaceUp(true);
    players[0].getHand().cardList.get(1).setFaceUp(true);
    
    buttons = new Button[5];
    buttons[0] = new Button("Draw from Deck", 50, 600, 150, 40);
    buttons[1] = new Button("Swap a Card", 220, 600, 150, 40);
    buttons[2] = new Button("Declare Cabo", 390, 600, 150, 40);
    buttons[3] = new Button("Use Action", 390 + 170, 600, 150, 40);
    buttons[4] = new Button("End Turn", 390 + 170 + 170, 600, 150, 40);
    
    updateButtonStates();
    
    // TODO: set up deck and discard pile
    // TODO: set up players array and deal their cards
    // TODO: set up buttons and update their states for the beginning of the game
    // TODO: update the gameMessages log: "Turn for "+currentPlayer.name
  }
  
  /**
   * Console-only output for verifying the setup of the card objects and the deck containing them
   */
  public void deckCheck() {
    System.out.println("TODO");
    
    boolean suitsCheck = false;
    boolean actionCheck = false;
    
    boolean deckSize = false;
    if(deck.size() == 52) {
      deckSize = true;
    }
    
    System.out.println("Deck size is 52: " + deckSize);
    
    int [] suitCounter = new int[]{0,0,0,0};
    int [] typeCounter = new int[]{0,0,0};
    
    for(int i = 0;i<deck.size();i++) {
      BaseCard currentCard = deck.cardList.get(i);
      
//      if(currentCard.toString() == "King -1") {
//        System.out.println("King of Diamonds found");
//      }
      
      if(currentCard.suit == "Hearts") {
        suitCounter[0] = suitCounter[0] + 1;
      }
      if(currentCard.suit == "Diamonds") {
        suitCounter[1] = suitCounter[1] + 1;
      }
      if(currentCard.suit == "Clubs") {
        suitCounter[2] = suitCounter[2] + 1;
      }
      if(currentCard.suit == "Spades") {
        suitCounter[3] = suitCounter[3] + 1;
      }
      
      if(deck.cardList.get(i) instanceof ActionCard) {
        ActionCard a = (ActionCard) currentCard;
        if(a.getActionType() == "peek") {
          typeCounter[0] = typeCounter[0] + 1;
        }
        if(a.getActionType() == "spy") {
          typeCounter[1] = typeCounter[1] + 1;
        }
        if(a.getActionType() == "switch") {
          typeCounter[2] = typeCounter[2] + 1;
        }
      }
      
      if((typeCounter[0] == 8) && (typeCounter[1] == 8) && (typeCounter[2] == 8)) {
        actionCheck = true;
      }
      if((suitCounter[0] == 13) && (suitCounter[1] == 13) && (suitCounter[2] == 13) && (suitCounter[3] == 13)) {
        suitsCheck = true;
      }
    }
    
    System.out.println("Found correct numbers of action cards: " + actionCheck);
    System.out.println("Found correct numbers of each suit: " + suitsCheck);
    // TODO: verify that there are 52 cards in the deck
    // TODO: verify that there are 8 of each type of ActionCard
    // TODO: verify that there are 13 of each suit
    // TODO: verify that the king of diamonds' getRank() returns -1
  }
  
  /**
   * Updates the state of the action buttons based on the current game state.
   * Activates or deactivates buttons depending on whether it's the start of a player's turn, a card has been drawn, or the player is an AI.
   */
  public void updateButtonStates() {
    if (players[currentPlayer].isComputer()) {
      for(int i = 0;i<buttons.length; i++) {
        buttons[i].setActive(false);
      }
    }
    else {
      if(drawnCard == null) {
        for(int i = 0;i<buttons.length; i++) {
          if (buttons[i].getLabel().equals("Draw from Deck") || (buttons[i].getLabel().equals("Declare Cabo"))) {
            buttons[i].setActive(true);
          }
          else {
            buttons[i].setActive(false);
          }
        }
      }
      else {
        for(int i = 0;i<buttons.length; i++) {
          if (drawnCard instanceof ActionCard) {
            ActionCard a = (ActionCard) drawnCard;
            buttons[3].setActive(true);
            buttons[3].setLabel(a.getActionType().toUpperCase());
          }
          if (buttons[i].getLabel().equals("Swap a Card") || (buttons[i].getLabel().equals("End Turn"))) {
            buttons[i].setActive(true);
          }
          else {
            buttons[i].setActive(false);
          }
        }
      }
    }
    
    // TODO: if the current player is a computer, deactivate all buttons
    // TODO: otherwise, if no card has been drawn, activate accordingly (see writeup)
    // TODO: otherwise, if a card has been drawn, activate accordingly (see writeup)
  }
  
  /**
   * Renders the graphical user interface; also handles some game logic for the computer players.
   */
  @Override
  public void draw() {
    background(0, 128, 0);

    textSize(16);
    fill(255);
    text("Deck:", 520, 60);
    text("Discard Pile:", 644, 60);
    
    deck.draw(500, 80, false);
    discard.draw(600, 80, true);
    
    for(int i = 0; i<players.length; i++) {
      textSize(16);
      fill(255);
      text(players[i].getName(), 50, 45+150*i);
      players[i].getHand().draw(60+150*i);
    }
    
    for(int i = 0; i<buttons.length; i++) {
      buttons[i].draw();
    }
    
    if(drawnCard != null) {
      drawnCard.draw(500, 300);
    }
    
    if(gameOver) {
      displayGameOver();
    }
    else if ((!gameOver) && (players[currentPlayer].isComputer())) {
      if(players[currentPlayer] instanceof AIPlayer) {
        performAITurn();
      }
    }
    
 // Display game messages with different colors based on the content
    int y = 200; // Starting y-position for messages
    for (String message : gameMessages) {
      textSize(16);
      if (message.contains("CABO")) {
        fill(255, 128, 0);
      } else if (message.contains("switched")) {
          fill(255, 204, 153);
      } else if (message.contains("spied")) {
          fill(255, 229, 204);
      } else {
          fill(255);
      } 
      text(message, width - 300, y); // Adjust x-position as needed
      y += 20; // Spacing between messages
    }
    
    // TODO: draw the deck and discard pile
    // TODO: draw the players' hands
    // TODO: draw the buttons
    // TODO: show the drawn card, if there is one
    
    // TODO: if the game is over, display the game over status
    // TODO: handle the computer players' turns
  }
  
  /**
   * Handles mouse press events during the game. It manages user interactions with buttons (that is, 
   * drawing a card, declaring CABO, swapping cards, using action cards) and updates the game state 
   * accordingly.
   */
  @Override
  public void mousePressed() {
    // TODO: if game is over or it's the computer's turn, do nothing
    // TODO: handle button clicks
    for(int i = 0; i<buttons.length; i++) {
      if(buttons[i].isActive() && buttons[i].isMouseOver() && this.mousePressed) {
        if(buttons[i].getLabel().equals("Draw from Deck")) {
          drawFromDeck();
          System.out.println("draw");
        }
        else if(buttons[i].getLabel().equals("Swap a Card")) {
          System.out.println("swap");
          actionState = ActionState.SWAPPING;
          setGameStatus("Click a card in your hand to swap it with the drawn card.");
        }
        else if(buttons[i].getLabel().equals("Declare Cabo")) {
          System.out.println("declare");
          declareCabo();
        }
        else if(buttons[i].getLabel().equals("Use Action")) {
          buttons[i].setLabel("Use Action");
          if(drawnCard instanceof ActionCard) {
            ActionCard a = (ActionCard) drawnCard;
            if(a.getActionType().equals("peek")) {
              actionState = ActionState.PEEKING;
              setGameStatus("Click a card in your hand to peek at it.");
            }
            else if(a.getActionType().equals("spy")) {
              actionState = ActionState.SPYING;
              setGameStatus("Click a card in another player's hand to spy on it.");
            }
            else if(a.getActionType().equals("switch")) {
              actionState = ActionState.SPYING;
              setGameStatus("Click a card from your hand, then a card from another Kingdom's hand to switch.");
            }
          }
        }
        else if(buttons[i].getLabel().equals("End Turn")) {
          System.out.println("end");
          nextTurn();
        }
      }
    } 
    
    // handle additional action states (TODO: complete these methods)
    switch (actionState) {
      case SWAPPING -> handleCardSwap();
      case PEEKING -> handlePeek();
      case SPYING -> handleSpy();
      case SWITCHING -> handleSwitch();
      default -> { /* No action to be taken */ }
    }
  }
  
  ///////////////////////////////////// BUTTON CLICK HANDLERS /////////////////////////////////////
  
  /**
   * Handles the action of drawing a card from the deck.
   * If the deck is empty, the game ends. Otherwise, the drawn card is displayed in the middle of the table.
   * The game status and button states are updated accordingly.
   */
  public void drawFromDeck() {
    if(deck.isEmpty()) {
      gameOver = true;
    }
    else {
      drawnCard = deck.cardList.get(deck.cardList.size()-1);
      drawnCard.setFaceUp(true);
      setGameStatus(players[currentPlayer].getName() + " drew a card.");
      deck.cardList.remove(drawnCard);
    }
    updateButtonStates();
    // TODO: if the deck is empty, game over
    // TODO: otherwise, draw the next card from the deck
    // TODO: update the gameMessages log: player.name+" drew a card."
    // TODO: update the button states
  }
  
  /**
   * Handles the action of declaring CABO.
   * Updates the game status to show that the player has declared CABO.
   */
  public void declareCabo() {
    setGameStatus(players[currentPlayer].getName() + " declares CABO!");
    caboPlayer = currentPlayer;
    nextTurn();
    // TODO: update the gameMessages log: player.name+" declares CABO!"
    // TODO: set the caboPlayer to the current player's index
    // TODO: end this player's turn
  }
  
  ///////////////////////////////////// ACTION STATE HANDLERS /////////////////////////////////////
  
  /**
   * This method runs when the human player has chosen to SWAP the drawn card with one from their
   * hand. Detect if the mouse is over a card from the currentPlayer's hand and, if it is, swap the
   * drawn card with that card.
   * 
   * If the mouse is not currently over a card from the currentPlayer's hand, this method does 
   * nothing.
   */
  public void handleCardSwap() {
    int index = players[currentPlayer].getHand().indexOfMouseOver();
    boolean cardFound = false;
 
//    if(players[currentPlayer] instanceof Player) {
//      for(int i = 0;i<4;i++) {
//        if((players[currentPlayer].getHand().cardList.get(i).isMouseOver()) && (this.mousePressed)) {
//          System.out.println("got it");
//          index = i;
//          cardFound = true;
//          break;
//        }
//        else {continue;}
//      }
//    }
    
    if (index != -1) {
      BaseCard swappedCard = players[currentPlayer].getHand().swap(drawnCard, index);
      discard.addCard(swappedCard);
      setGameStatus("Swapped the drawn card with card "+(index+1)+" in the hand.");
      drawnCard = null;
      actionState = ActionState.NONE;
      for(int i = 0; i<buttons.length; i++) {
        if(buttons[i].getLabel().equals("End Turn")) {
          buttons[i].setActive(true);
        }
        else {
          buttons[i].setActive(false);
        }
      }
    }
    // TODO: find a card from the current player's hand that the mouse is currently over
      // TODO: swap that card with the drawnCard
      // TODO: add the swapped-out card from the player's hand to the discard pile
      // TODO: update the gameMessages log: "Swapped the drawn card with card "+(index+1)+" in the hand."
      // TODO: set the drawnCard to null and the actionState to NONE
      // TODO: set all buttons except End Turn to inactive
    
      // TODO: uncomment this code to erase all knowledge of the card at that index from the AI
      // (you may need to adjust its indentation and/or change some variables)
    
      AIPlayer AI;
      for (int j = 1; j < players.length; ++j) {
        if(players[j] instanceof AIPlayer) {
        AI = (AIPlayer) players[j];
        AI.setCardKnowledge(0, index, false);
        }//
      }
    }
  
  /**
   * Handles the action of peeking at one of your cards. The player selects a card from their own 
   * hand, which is then revealed (set face-up).
   * 
   * If the mouse is not currently over a card from the currentPlayer's hand, this method does 
   * nothing.
   */
  public void handlePeek() {
    System.out.println("peek");
    int index = players[currentPlayer].getHand().indexOfMouseOver();
    if(index != -1) {
      players[currentPlayer].getHand().cardList.get(index).setFaceUp(true);
      setGameStatus("Revealed card "+(index+1)+" in the hand.");
      discard.addCard(drawnCard);
    
      drawnCard = null;
      actionState = ActionState.NONE;
      for(int i = 0; i<buttons.length; i++) {
        if(buttons[i].getLabel().equals("End Turn")) {
          buttons[i].setActive(true);
        }
        else {
          buttons[i].setActive(false);
        }
      }
    }
    
    // TODO: find a card from the current player's hand that the mouse is currently over
      // TODO: set that card to be face-up
      // TODO: update the gameMessages log: "Revealed card "+(index+1)+" in the hand."
      // TODO: add the drawnCard to the discard, set drawnCard to null and actionState to NONE
      // TODO: set all buttons except End Turn to inactive
  }
  
  /**
   * Handles the spy action, allowing the current player to reveal one of another player's cards.
   * The current player selects a card from another player's hand, which is temporarily revealed.
   * 
   * If the mouse is not currently over a card from another player's hand, this method does nothing.
   */
  public void handleSpy() {
    System.out.println("spy");
    int index = players[currentPlayer].getHand().indexOfMouseOver();
    if(index != -1) {
      players[currentPlayer].getHand().cardList.get(index).setFaceUp(true);
      setGameStatus("Spied on "+ players[currentPlayer].getName() +"'s card.");
      discard.addCard(drawnCard);
    
      drawnCard = null;
      actionState = ActionState.NONE;
      for(int i = 0; i<buttons.length; i++) {
        if(buttons[i].getLabel().equals("End Turn")) {
          buttons[i].setActive(true);
        }
        else {
          buttons[i].setActive(false);
        }
      }
    }
    
    // TODO: find a card from any player's hand that the mouse is currently over
      // TODO: if it is not one of their own cards, set it to be face-up
      // TODO: update the gameMessages log: "Spied on "+player.name+"'s card.";
      // TODO: add the drawnCard to the discard, set drawnCard to null and actionState to NONE
      // TODO: set all buttons except End Turn to inactive
  }
  

  /**
   * Handles the switch action, allowing the current player to switch one of their cards with a 
   * card from another player's hand.
   * 
   * This action is performed in 2 steps, in this order:
   *   (1) select a card from the current player's hand
   *   (2) select a card from another player's hand
   * 
   * If the mouse is not currently over a card, this method does nothing.
   */
  public void handleSwitch() {
    int currentPlayerCardIndex = 0;
    int otherPlayerCardIndex = 0;
    int index = 0;
    if(selectedCardFromCurrentPlayer == 0) {
      for(int i = 0;i<4;i++) {
        if(players[currentPlayer].getHand().cardList.get(i).isMouseOver()) {
          selectedCardFromCurrentPlayer = i;
        }
      }
    }
    else {
      currentPlayerCardIndex = selectedCardFromCurrentPlayer;
      for(int i = 0; i<players.length; i++) {
        if(!players[i].getName().equals(players[currentPlayer].getName())) {
          for(int j = 0; j<4; j++) {
            if(players[i].getHand().cardList.get(j).isMouseOver()) {
              index = i;
              otherPlayerCardIndex = j;
              BaseCard tempCard = players[i].getHand().cardList.get(otherPlayerCardIndex);
              players[i].getHand().cardList.set(otherPlayerCardIndex, players[currentPlayer].getHand().cardList.get(currentPlayerCardIndex));
              players[currentPlayer].getHand().cardList.set(currentPlayerCardIndex, tempCard);
            }
          }
        }
      }
    }
    
    discard.addCard(drawnCard);
    drawnCard = null;
    actionState = ActionState.NONE;
    for(int i = 0; i<buttons.length; i++) {
      if(buttons[i].getLabel().equals("End Turn")) {
        buttons[i].setActive(true);
      }
      else {
        buttons[i].setActive(false);
      }
    }
    // TODO: add CaboGame instance variable to store the index of the card from the currentPlayer's hand
    
    // TODO: check if the player has selected a card from their own hand yet
    // TODO: if they haven't: determine which card in their own hand the mouse is over & store it
    // and do nothing else
    
    // TODO: if they have selected a card from their own hand already:
      // TODO: find a card from any OTHER player's hand that the mouse is currently over
      // TODO: swap the selected card with the card from the currentPlayer's hand
      // TODO: update the gameMessages log: "Switched a card with "+player.name
      // TODO: add the drawnCard to the discard, set drawnCard to null and actionState to NONE
      // TODO: set all buttons except End Turn to inactive
    
      // TODO: uncomment this code to update the knowledge of the swapped card for the other player
      // (you may need to adjust its indentation and variables)
    
      boolean knowledge = ((AIPlayer)players[index]).getCardKnowledge(index, otherPlayerCardIndex);
      ((AIPlayer)players[index]).setCardKnowledge(index, otherPlayerCardIndex,
          ((AIPlayer)players[index]).getCardKnowledge(currentPlayer, currentPlayerCardIndex));
      ((AIPlayer)players[index]).setCardKnowledge(currentPlayer, currentPlayerCardIndex, knowledge);//

      // TODO: reset the selected card instance variable to -1
  }
  
  /////////////////////////////////////////////////////////////////////////////////////////////////
  
  /**
   * Advances the game to the next player's turn.
   * Hides all players' cards, updates the current player, checks for game-over conditions,
   * resets action states, and updates the UI button states for the new player's turn.
   */
  public void nextTurn() {
    if(drawnCard != null) {
      discard.addCard(drawnCard);
      drawnCard = null;
    }
    
    if(caboPlayer == currentPlayer) {
      gameOver = true;
      displayGameOver();
    }
    
    if (currentPlayer <= 2) {
      currentPlayer = currentPlayer + 1;
    }
    else {
      currentPlayer = 0;
    }
    
    setGameStatus("Turn for " + players[currentPlayer].getName());
    actionState = ActionState.NONE;
    updateButtonStates();
    // TODO: hide all players' cards
    // TODO: if there is still an active drawnCard, discard it and set drawnCard to null
    // TODO: advance the current player to the next one in the list
    // TODO: check if the new player is the one who declared CABO (and end the game if so)
    // TODO: update the gameMessages log: "Turn for "+player.name
    // TODO: reset the action state to NONE
    // TODO: update the button states
    
    for(int i = 0; i < 4; i++) {
      for(int j = 0; j < 4; j++) {
        players[i].getHand().cardList.get(j).setFaceUp(false);
      }
    }
    
  }
  
  /**
   * Displays the game-over screen and reveals all players' cards.
   * The method calculates each player's score, identifies the winner, and displays
   * a message about the game's result, including cases where there is no winner.
   * 
   * We've provided the code for the GUI parts, but the logic behind this method is still TODO
   */
  public void displayGameOver() {
    // Create a dimmed background overlay
    fill(0, 0, 0, 200);
    rect(0, 0, width, height);
    fill(255);
    textSize(32);
    textAlign(CENTER, CENTER);
    text("Game Over!", (float) width / 2, (float) height / 2 - 150);

    // TODO: reveal all players' cards
    
    int[] scores = new int[4];
    for(int i = 0; i < 4; i++) {
      for(int j = 0; j < 4; j++) {
        players[i].getHand().cardList.get(j).setFaceUp(true);
        scores[i] = scores[i] + players[i].getHand().cardList.get(j).getRank();
      }
    }
    
    // TODO: calculate and display each player's score
    int yPosition = height / 2 - 100;
    textSize(24);
    
    for(int k = 0;k<scores.length;k++) {
      text(players[k].getName() + "'s score: " + scores[k], (float) width / 2, yPosition);
      yPosition += 30;
    }

    int minIndex = 0;
    int count = 0;
    for (int i = 1;i<scores.length; i++) {
      if(scores[i]<scores[minIndex]) {
        minIndex=i;
        count = 1;
      }
      else if (scores[i] == scores[minIndex]) {
        count++;
      }
    }
    if(count > 1) {
      text("No Winner. The war starts.", (float) width / 2, yPosition + 30);
    }
    else {
      String winner = players[minIndex].getName();
      text("Winner: " + winner, (float) width / 2, yPosition + 30);
    }
  }
  
  /**
   * PROVIDED: Sets the current game status message and updates the message log.
   * If the message log exceeds a maximum number of messages, the oldest message is removed.
   *
   * @param message the message to set as the current game status.
   */
  private void setGameStatus(String message) {
    gameMessages.add(message);
    int MAX_MESSAGES = 15;
    if (gameMessages.size() > MAX_MESSAGES) {
      gameMessages.remove(0); // Remove the oldest message
    }
  }
  
  /////////////////////////////////////////////////////////////////////////////////////////////////
  // The 2 methods below this line are PROVIDED in their entirety to run the AIPlayer interactions 
  // with the CABO game. Uncomment them once you are ready to add AIPlayer actions to your game!
  /////////////////////////////////////////////////////////////////////////////////////////////////
  
  /**
   * Performs the AI player's turn by drawing a card and deciding whether to swap, discard, or use 
   * an action card.
   * If the AI player draws a card that is better than their highest card, they swap it; otherwise, 
   * they discard it.
   * If the drawn card is an action card, the AI player performs the corresponding action.
   * If the AI player's hand value is low enough, they may declare CABO.
   */
  private void performAITurn() {
    AIPlayer aiPlayer = (AIPlayer) players[currentPlayer];
    String gameStatus = aiPlayer.getName() + " is taking their turn.";
    setGameStatus(gameStatus);

    // Draw a card from the deck
    drawnCard = deck.drawCard();
    if (drawnCard == null) {
      gameOver = true;
      return;
    }

    gameStatus = aiPlayer.getName() + " drew a card.";
    setGameStatus(gameStatus);

    // Determine if AI should swap or discard
    int drawnCardValue = drawnCard.getRank();
    int highestCardIndex = aiPlayer.getHighestIndex();
    if (highestCardIndex == -1) {
      highestCardIndex = 0;
    }
    int highestCardValue = aiPlayer.getHand().getRankAtIndex(highestCardIndex);

    // Swap if the drawn card has a lower value than the highest card in hand
    if (drawnCardValue < highestCardValue) {
      BaseCard cardInHand = aiPlayer.getHand().swap(drawnCard, highestCardIndex);
      aiPlayer.setCardKnowledge(aiPlayer.getLabel(), highestCardIndex, true);
      discard.addCard(cardInHand);
      gameStatus = aiPlayer.getName() + " swapped the drawn card with card " + (highestCardIndex + 1) + " in their hand.";
      setGameStatus(gameStatus);
    } else if (drawnCard instanceof ActionCard) {
      // Use the action card
      String actionType = ((ActionCard) drawnCard).getActionType();
      gameStatus = aiPlayer.getName() + " uses an action card: " + actionType;
      setGameStatus(gameStatus);
      performAIAction(aiPlayer, actionType);
      discard.addCard(drawnCard);
    } else {
      // Discard the drawn card
      discard.addCard(drawnCard);
      gameStatus = aiPlayer.getName() + " discarded the drawn card: " + drawnCard;
      setGameStatus(gameStatus);
    }

    // AI may declare Cabo if hand value is low enough
    int handValue = aiPlayer.calcHandBlind();
    if (handValue <= random(13, 21) && caboPlayer == -1) {
      declareCabo();
    }

    // Prepare for the next turn
    drawnCard = null;
    nextTurn();
  }//
  
  /**
   * Performs the specified action for the AI player based on the drawn action card.
   * Actions include peeking at their own cards, spying on another player's card, or switching cards with another player.
   *
   * @param aiPlayer   the AI player performing the action.
   * @param actionType the type of action to perform ("peek", "spy", or "switch").
   */
  private void performAIAction(AIPlayer aiPlayer, String actionType) {
    Player otherPlayer = players[0]; // Assuming Player 1 is the human player
    String gameStatus = "";
    switch (actionType) {
      case "peek" -> {
        // AI peeks at one of its own cards
        int unknownCardIndex = aiPlayer.getUnknownCardIndex();
        if (unknownCardIndex != -1) {
          aiPlayer.setCardKnowledge(aiPlayer.getLabel(), unknownCardIndex, true);
          gameStatus = aiPlayer.getName() + " peeked at their card " + (unknownCardIndex + 1);
          setGameStatus(gameStatus);
        }
      }
      case "spy" -> {
        // AI spies on one of the human player's cards
        int spyIndex = aiPlayer.getSpyIndex();
        if (spyIndex != -1) {
          aiPlayer.setCardKnowledge(0, spyIndex, true);
          gameStatus = aiPlayer.getName() + " spied on Player 1's card " + (spyIndex + 1);
          setGameStatus(gameStatus);
        }
      }
      case "switch" -> {
        // AI switches one of its cards with one of the human player's cards
        int aiCardIndex = aiPlayer.getHighestIndex();
        if (aiCardIndex == -1) {
          aiCardIndex = (int) random(aiPlayer.getHand().size());
        }
        int otherCardIndex = aiPlayer.getLowestIndex(otherPlayer);
        if (otherCardIndex == -1)
          otherCardIndex = (int) random(otherPlayer.getHand().size());

        // Swap the cards between AI and the human player
        aiPlayer.getHand().switchCards(aiCardIndex, otherPlayer.getHand(), otherCardIndex);
        boolean preCardKnowledge = aiPlayer.getCardKnowledge(aiPlayer.getLabel(), aiCardIndex);
        aiPlayer.setCardKnowledge(aiPlayer.getLabel(), aiCardIndex, aiPlayer.getCardKnowledge(0, otherCardIndex));
        aiPlayer.setCardKnowledge(0, otherCardIndex, preCardKnowledge);

        gameStatus = aiPlayer.getName() + " switched card " + (aiCardIndex + 1) + " with " + otherPlayer.getName() + "'s " + (otherCardIndex + 1) + ".";
        setGameStatus(gameStatus);
      }
    }
  }//

}
