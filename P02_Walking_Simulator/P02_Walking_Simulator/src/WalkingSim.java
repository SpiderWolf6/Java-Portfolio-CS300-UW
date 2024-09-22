// Title:    Walking Simulator
// Course:   CS 300 Fall 2024
//
// Author:   Soham Mukherjee
// Email:    smukherjee39@wisc.edu
// Lecturer: Blerina Gkotse

import java.util.Random;
import java.io.File;
import processing.core.PImage;

/**
* This class contains methods for creating a walking animation using Class objects
*/
public class WalkingSim extends Object {
  // Create variables and arrays which will be used later for animation
  private static Random randGen;
  private static int bgColor;
  private static PImage[] frames;
  private static Walker[] walkers;
  
  /**
   * Initializes variables properly and fills arrays with correct items, setup to the animation
   *
   * @param None
   * @return None
   */
  public static void setup() {
    randGen = new Random();
    bgColor = randGen.nextInt();
    // loop through frames to insert correct images at correct indices
    frames = new PImage[Walker.NUM_FRAMES];
    for(int i = 0; i<Walker.NUM_FRAMES; i++) {
      frames[i] = Utility.loadImage("images" + File.separator + "walk-" + i + ".png"); 
    }
    // loop through walker to generate random number of objects in array
    walkers = new Walker[8];
    int r = randGen.nextInt(1,9);
    for(int i = 0; i<r; i++) {
      walkers[i] = new Walker(randGen.nextInt(Utility.width()), randGen.nextInt(Utility.height()));
    }
  }
  
  
  /**
   * Implements the walking animation using the provided methods, variables, and arrays
   *
   * @param None
   * @return None
   */
  public static void draw() {
    Utility.background(bgColor);
    // loop through non-null walkers to update frame and change position
    for(int i = 0; i<8; i++) {
      if (walkers[i] != null) {
        if (walkers[i].isWalking() == true) {
          walkers[i].update();
          walkers[i].setPositionX((walkers[i].getPositionX()+3) % Utility.width());
        }
        Utility.image(frames[walkers[i].getCurrentFrame()], walkers[i].getPositionX(), walkers[i].getPositionY());
        // print statement when mouse hovers over walker
        if (isMouseOver(walkers[i]) == true) {
          System.out.println("Mouse is over a walker!");
        }
      }
    }
  }
  
  
  /**
   * Checks whether the mouse is hovering over any part of any Walker image
   *
   * @param walker a Walker object
   * @return true if the conditions are satisfied and false otherwise
   */
  public static boolean isMouseOver(Walker walker) {
    // check the mouse relative to the upper, lower, left, and right bounds for walker image
    if ((Utility.mouseX()<(walker.getPositionX()+(frames[0].width/2))) 
      & (Utility.mouseX()>(walker.getPositionX()-(frames[0].width/2)))
      & (Utility.mouseY()<(walker.getPositionY()+(frames[0].height/2)))
      & (Utility.mouseY()>(walker.getPositionY()-(frames[0].height/2)))) {
      return true;
    }
    else {return false;}
  }
  
  
  /**
   * Sets isWalking to true when user clicks on a walker image
   *
   * @param None
   * @return None
   */
  public static void mousePressed() {
    // loop through non-null walkers to check if mouse click on walker
    for(int i = 0; i<8; i++) {
      if (walkers[i] != null) {
        if (isMouseOver(walkers[i]) == true) {
          walkers[i].setWalking(true);
          break;
        } 
      }
    }
  }
  
  
  /**
   * Implements functions like adding a new walker or stopping animation depending on pressed key
   *
   * @param c a character
   * @return None
   */
  public static void keyPressed (char c) {
    // if 'a' key is pressed and walkers array has null element, insert new walker
    if (c == 'a') {
      for(int i = 0; i<8; i++) {
        if (walkers[i] == null) {
          walkers[i] = new Walker(randGen.nextInt(Utility.width()), randGen.nextInt(Utility.height()));
          break;
        }
      }
    }
    // if 's' key is pressed, change all walker.setWalking to false and stop animation
    if (c == 's') {
      for(int i = 0; i<8; i++) {
        if (walkers[i] != null) {          
          walkers[i].setWalking(false);
        }
      }
    }
  }
  
  
  /**
   * Runs application using Utility.runApplication()
   *
   * @param args an array of String type
   * @return None
   */
  public static void main(String[] args) {
    Utility.runApplication();
  }
}