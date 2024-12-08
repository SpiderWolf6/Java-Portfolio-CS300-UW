//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Library Manager
// Course:   CS 300 Fall 2024
//
// Author:   Soham Mukherjee
// Email:    smukherjee39@wisc.edu
// Lecturer: Blerina Gkotse
//

/**
* This class contains methods for creating and managing books
*/
public class Book {
  // create five private fields for Book class
  private String title;
  private String author;
  private int yearOfPublication;
  private String publisher;
  private int numberOfPages;
  
  // define the constructor for Book class
  public Book(String title, String author, int yearOfPublication, String publisher, int numberOfPages) {
    // if yearofPublication is negative or exceeds current year, throw exception
    if ((yearOfPublication < 0) | (yearOfPublication > 2024)) {
      throw new IllegalArgumentException("yearOfPublication must be non-negative and less than current year");
    }
    // if numberOfPages is negative, throw exception
    if (numberOfPages < 0) {
      throw new IllegalArgumentException("numberOfPages must be non-negative");
    }
    this.title = title;
    this.author = author;
    this.yearOfPublication = yearOfPublication;
    this.publisher = publisher;
    this.numberOfPages = numberOfPages;
  }
  

  /**
   * Gets the private field title
   *
   * @param None
   * @return the title of the book
   */
  public String getTitle() {
    return title;
  }
  
  /**
   * Gets the private field author
   *
   * @param None
   * @return the author of the book
   */
  public String getAuthor() {
    return author;
  }
  
  /**
   * Gets the private field yearOfPublication
   *
   * @param None
   * @return the year of publication of the book
   */
  public int getYearOfPublication() {
    return yearOfPublication;
  }
  
  /**
   * Gets the private field publisher
   *
   * @param None
   * @return the publisher of the book
   */
  public String getPublisher() {
    return publisher;
  }
  
  /**
   * Gets the private field numberOfPages
   *
   * @param None
   * @return the number of pages in the book
   */
  public int getNumberOfPages() {
    return numberOfPages;
  }
  
  /**
   * Sets a new title as the title of the book
   *
   * @param newTitle a String
   * @return None
   */
  public void setTitle(String newTitle) {
    title = newTitle;
  }
  
  /**
   * Sets a new author as the author of the book
   *
   * @param newAuthor a String
   * @return None
   */
  public void setAuthor(String newAuthor) {
    author = newAuthor;
  }
  
  /**
   * Sets a new yearOfPublication as the yearOfPublication of the book
   *
   * @param newYearOfPublication an int
   * @return None
   */
  public void setYearOfPublication(int newYearOfPublication) {
    if ((newYearOfPublication < 0) | (newYearOfPublication > 2024)) {
      throw new IllegalArgumentException("newYearOfPublication must be non-negative and less than current year");
    }
    yearOfPublication = newYearOfPublication;
  }
  
  /**
   * Sets a new publisher as the publisher of the book
   *
   * @param newPublisher a String
   * @return None
   */
  public void setPublisher(String newPublisher) {
    publisher = newPublisher;
  }
  
  /**
   * Sets a new numberOfPages as the numberOfPages of the book
   *
   * @param newNumberOfPages an int
   * @return None
   */
  public void setNumberOfPages(int newNumberOfPages) {
    if (newNumberOfPages < 0) {
      throw new IllegalArgumentException("newNumberOfPages must be non-negative");
    }
    numberOfPages = newNumberOfPages;
  }
  
}
