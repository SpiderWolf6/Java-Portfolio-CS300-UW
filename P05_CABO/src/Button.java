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
 * A class for creating and managing the CaboGame buttons for game controls and flow
 */
public class Button extends Object {
  private boolean active;
  private int height;
  private String label;
  protected static processing.core.PApplet processing;
  private int width;
  private int x;
  private int y;
  
  /**
  Constructor for the button class
  @param label - the words displayed on the button
  @param x - x axis location of the button
  @param y - y axis location of the button
  @param width - width value of the button
  @param height - height value of the button*/
  public Button(String label, int x, int y, int width, int height) {
    if(Button.processing == null) {
      throw new IllegalStateException("Processing environment has not been set");
    }
    this.label = label;
    this.x = x;
    this.y = y;
    this.height = height;
    this.width = width;
  }
  
  /**
  Getter method for the Label value
  @return - String value of the label*/
  public String getLabel() {
    return label;
  }
  
  /**
  Setter method of the label value
  @param label of the button*/
  public void setLabel(String label) {
    this.label = label;
  }
  
  /**
  getter method to check if the button is active
  @return - status of the active variable*/
  public boolean isActive() {
    return active;
  }
  
  /**
  setter method for active variable
  @param active*/
  public void setActive(boolean active) {
    this.active = active;
  }
  
  /**
  setter method for the processing variable
  @param processing - the processing environment to be used for drawing and interaction*/
  public static void setProcessing(processing.core.PApplet processing) {
    Button.processing = processing;
  }
  
  /**
   * method to render the buttons on the processing canvas and change their colors based on the
   * IsActive parameter
   */
  public void draw() {
    if(isMouseOver() && isActive()){
      processing.fill(150);
    } 
    else if(isActive()) {
      processing.fill(200);
    } 
    if(isActive() != true){
      processing.fill(255,51,51);
    } 
    processing.rect(x, y, width, height, 5);
    processing.fill(0);
    processing.textSize(14);
    processing.textAlign(processing.CENTER, processing.CENTER);
    processing.text(label, x + width/2, y + height/2);
    
    //TODO
  }
  
  /**
   * method to check if the mouse is over the button
   * @return - true if the method is over the button
   */
  public boolean isMouseOver() {
    int mouseX = processing.mouseX;
    int mouseY = processing.mouseY;
    
    if ((mouseX > x) && (mouseX <= (x+width)) && (mouseY >= (y)) && (mouseY <= (y+height))) {
      return true;
    }
    //TODO
    return false;
  }
   
}