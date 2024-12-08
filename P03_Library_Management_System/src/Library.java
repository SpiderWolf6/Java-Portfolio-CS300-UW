//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Library Manager
// Course:   CS 300 Fall 2024
//
// Author:   Soham Mukherjee
// Email:    smukherjee39@wisc.edu
// Lecturer: Blerina Gkotse
//

import java.util.ArrayList;


/**
* This class contains methods for managing books in the library database
*/
public class Library {
  private ArrayList<Book> books = new ArrayList<Book>();   // create ArrayList of Book objects
  
  
  /**
   * Adds a new book to the list of books in the library
   *
   * @param b a Book object
   * @return None
   */
  public void addBook(Book b) {
    if (books.size() == 0) {
      books.add(b);
    }
    else {
      // loop through books ArrayList
      for(int i=books.size()-1; i>=0; i--) {
        if (books.get(i).getYearOfPublication() < b.getYearOfPublication()) {
          books.add(i+1, b);
        }
      }
    }
  }
  
  
  /**
   * Gets the total number of books in the library
   *
   * @param None
   * @return the length of the ArrayList books
   */
  public int getTotalBooks() {
    return books.size();
  }
  
  
  /**
   * Gets the list of books in the library
   *
   * @param None
   * @return the ArrayList of books
   */
  public ArrayList<Book> getAllBooks() {
    return books;
  }
  
  
  /**
   * Removes a book by title from the list of books in the library
   *
   * @param title a String
   * @return true if the book was removed correctly and false otherwise
   */
  public boolean removeBookByTitle(String title) {
    // loop through books ArrayList
    for(int i=0; i<books.size(); i++) {
      Book currentBook = books.get(i);
      if (currentBook.getTitle().equals(title)) {
        books.remove(i);
        return true;
      }
    }
    return false;
  }
  
  
  /**
   * Finds a book by author from the list of books in the library
   *
   * @param author a String
   * @return an ArrayList authorBooks containing all books written by the specified author
   */
  public ArrayList<Book> findBooksByAuthor(String author) {
    ArrayList<Book> authorBooks = new ArrayList<Book>();
    // loop through books ArrayList
    for(int i=0; i<books.size(); i++) {
      Book currentBook = books.get(i);
      if (currentBook.getAuthor().equals(author)) {
        authorBooks.add(currentBook);
      }
    }
    return authorBooks;
  }
  
  
  /**
   * Updates a book's title in the library
   *
   * @param oldTitle a String, newTitle a String
   * @return true if the book title was updated correctly and false otherwise
   */
  public boolean updateBookTitle(String oldTitle, String newTitle) {
    // loop through books ArrayList
    for(int i=0; i<books.size(); i++) {
      Book currentBook = books.get(i);
      if (currentBook.getTitle().equals(oldTitle)) {
        currentBook.setTitle(newTitle);
        return true;
      }
    }
    return false;
  }
  
  
  /**
   * Updates a book's author in the library
   *
   * @param oldAuthor a String, newAuthor a String
   * @return true if the book's author was updated correctly and false otherwise
   */
  public boolean updateBookAuthor(String title, String newAuthor) {
    // loop through books ArrayList
    for(int i=0; i<books.size(); i++) {
      Book currentBook = books.get(i);
      if (currentBook.getTitle().equals(title)) {
        currentBook.setAuthor(newAuthor);
        return true;
      }
    }
    return false;
  }
  
  
  /**
   * For every book in the list of books, prints the title and author
   *
   * @param None
   * @return None
   */
  public void printAllBooks() {
    // loop through books ArrayList
    for(int i=0; i<books.size(); i++) {
      Book currentBook = books.get(i);
      System.out.println("Title: " + currentBook.getTitle() + ", Author: " + currentBook.getAuthor());
    }
  }
}