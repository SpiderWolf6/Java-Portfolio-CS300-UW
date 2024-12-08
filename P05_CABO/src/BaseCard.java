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

import java.io.File;

import processing.core.PImage;
/**
 * A class for creating and managing the cards in the CaboGame
 */
public class BaseCard extends Object {
  private static processing.core.PImage cardBack;
  private processing.core.PImage cardImage;
  protected boolean faceUp;
  private final int HEIGHT = 70;
  protected static processing.core.PApplet processing;
  protected int rank;
  protected String suit;
  private final int WIDTH = 50;
  private int x;
  private int y;
  
  /**
  Constructor for the BaseCard class
  @param rank - rank of the card being created
  @param suit - suit of the card being created*/
  public BaseCard(int rank, String suit) {
    if(BaseCard.processing == null) {
      throw new IllegalStateException("Processing environment has not been set");
    }
    this.rank = rank;
    this.suit = suit;
    faceUp = false;
  }
  
  /**
  Sets the Processing environment to be used for drawing and interacting with cards
  @param processing - processing PApplet environment*/
  public static void setProcessing(processing.core.PApplet processing) {
    BaseCard.processing = processing;
  }
  
  /**
  Accessor method for the rank
  @return - the rank of the card as an integer*/
  public int getRank() {
    if((rank == 13) && (suit.compareTo("King") == 0)) {
      return -1;
    }
    else { return rank; }
  }
  
  /**
  Method to set the boolean value of faceUp to true
  @param faceUp - the value that determines if a card is face up*/
  public void setFaceUp(boolean faceUp) {
    this.faceUp = faceUp;
  }
  
  /**
  Override of the toString method for the BaseCard class*/
  @Override
  public String toString() {
    return suit + " " + Integer.toString(rank);
  }
  
  /**
  Method that draws the PApplet at the specified position as well as it's white rectangle
  @param xPosition - the x position of the card
  @param yPosition - the y position of the card*/
  public void draw(int xPosition, int yPosition) {
    processing.fill(225);
    processing.rect(xPosition, yPosition, WIDTH, HEIGHT);
    if(faceUp == true) {
      PImage image = processing.loadImage("images" + File.separator + rank + "_of_" + suit.toLowerCase() + ".png");
      processing.image(image, xPosition, yPosition, WIDTH, HEIGHT);
    }
    else {
      PImage cardBack = processing.loadImage("images" + File.separator + "back.png");
      processing.image(cardBack, xPosition, yPosition, WIDTH, HEIGHT);
    }
    //TODO
  }
  
  /**
  Method that checks if the mouse is over the card
  @return - true if the mouse is over, false otherwise*/
  public boolean isMouseOver() {
    int mouseX = processing.mouseX;
    int mouseY = processing.mouseY;
    
    if ((mouseX > x) && (mouseX < (x+WIDTH)) && (mouseY > (y-HEIGHT)) && (mouseY < (y))) {
      return true;
    }
    //TODO
    return false;
  }
  
}