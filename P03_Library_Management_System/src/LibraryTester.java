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
 * Tests methods of Book and Library classes.
 */
public class LibraryTester {
  /**
   * PROVIDED TESTER METHOD: example test method for testing the getTitle method.
   *
   * @return true if the test passes, false otherwise
   */
  public static boolean testGetTitle() {
    Book book = new Book("1984", "George Orwell", 1949, "Secker & Warburg", 328);
    return "1984".equals(book.getTitle());
  }

  /**
   * PROVIDED TESTER METHOD: example test method for testing the setTitle method.
   *
   * @return true if the test passes, false otherwise
   */
  public static boolean testSetTitle() {
    Book book = new Book("1984", "George Orwell", 1949, "Secker & Warburg", 328);
    book.setTitle("Animal Farm");
    return "Animal Farm".equals(book.getTitle());
  }

  /**
   * Tests if the getAuthor method works as expected
   *
   * @param None
   * @return true if the author of the book matches the expected author and false otherwise
   */
  public static boolean testGetAuthor() {
    Book book = new Book("1984", "George Orwell", 1949, "Secker & Warburg", 328);
    return "George Orwell".equals(book.getAuthor()); // TODO
  }

  /**
   * Tests if the setAuthor method works as expected
   *
   * @param None
   * @return true if the new author of the book matches the expected author and false otherwise
   */
  public static boolean testSetAuthor() {
    Book book = new Book("1984", "George Orwell", 1949, "Secker & Warburg", 328);
    book.setAuthor("J.K. Rowling");
    return "J.K. Rowling".equals(book.getAuthor()); // TODO
  }

  /**
   * Tests if the getYearOfPublication method works as expected
   *
   * @param None
   * @return true if the year of publication matches the expected year of publication and false otherwise
   */
  public static boolean testGetYearOfPublication() {
    Book book = new Book("1984", "George Orwell", 1949, "Secker & Warburg", 328);
    return 1949 == book.getYearOfPublication(); // TODO
  }

  /**
   * Tests if the setYearOfPublication method works as expected
   *
   * @param None
   * @return true if the new year of publication matches the expected year of publication and false otherwise
   */
  public static boolean testSetYearOfPublication() {
    Book book = new Book("1984", "George Orwell", 1949, "Secker & Warburg", 328);
    book.setYearOfPublication(2005);
    return 2005 == book.getYearOfPublication(); // TODO
  }

  /**
   * Tests if the getPublisher method works as expected
   *
   * @param None
   * @return true if the publisher of the book matches the expected publisher and false otherwise
   */
  public static boolean testGetPublisher() {
    Book book = new Book("1984", "George Orwell", 1949, "Secker & Warburg", 328);
    return "Secker & Warburg".equals(book.getPublisher()); // TODO
  }

  /**
   * Tests if the setPublisher method works as expected
   *
   * @param None
   * @return true if the new publisher of the book matches the expected publisher and false otherwise
   */
  public static boolean testSetPublisher() {
    Book book = new Book("1984", "George Orwell", 1949, "Secker & Warburg", 328);
    book.setPublisher("Oxford");
    return "Oxford".equals(book.getPublisher()); // TODO
  }

  /**
   * Tests if the getNumberOfPages method works as expected
   *
   * @param None
   * @return true if the number of pages matches the expected number of pages and false otherwise
   */
  public static boolean testGetNumberOfPages() {
    Book book = new Book("1984", "George Orwell", 1949, "Secker & Warburg", 328);
    return 328 == book.getNumberOfPages(); // TODO
  }

  /**
   * Tests if the setNumberOfPages method works as expected
   *
   * @param None
   * @return true if the new number of pages matches the expected number of pages and false otherwise
   */
  public static boolean testSetNumberOfPages() {
    Book book = new Book("1984", "George Orwell", 1949, "Secker & Warburg", 328);
    book.setNumberOfPages(369);
    return 369 == book.getNumberOfPages(); // TODO
  }

  /**
   * PROVIDED TESTER METHOD: Retrieves the total number of books in the library.
   * 
   * @return the total number of books
   */
  public static boolean testGetTotalBooks() {
    Library library = new Library();
    library.addBook(new Book("Book 1", "Author A", 2023, "Publisher Y", 200));
    library.addBook(new Book("Book 2", "Author B", 2023, "Publisher Z", 300));

    int expected = 2;
    int result = library.getTotalBooks();

    ArrayList<Book> expectedA = new ArrayList<>();
    expectedA.add(new Book("Book 1", "Author A", 2023, "Publisher Y", 200));
    expectedA.add(new Book("Book 2", "Author B", 2023, "Publisher Z", 300));

    if (expected != result) {
      return false;
    }
    return compareBooks(expectedA, library.getAllBooks());
  }


  /**
   * PROVIDED TESTER METHOD: example test method for adding a single book to the library.
   * 
   * @return true if the test passes, false otherwise
   */
  public static boolean testAddBook() {
    Library library = new Library();
    Book book = new Book("East of Eden", "John Steinbeck", 1952, "Penguin Classics", 608);
    library.addBook(book);
    
    ArrayList<Book> expected = new ArrayList<>();
    expected.add(new Book("East of Eden", "John Steinbeck", 1952, "Penguin Classics", 608));
    return compareBooks(expected, library.getAllBooks());
  }

  /**
   * Tests to check if multiple books are added correctly to the library
   *
   * @param None
   * @return true if the books in the library are added in the correct chronological order and match expected list, and false otherwise
   */
  public static boolean testAddMultipleBooks() {
    Library library = new Library();
    Book book = new Book("The Colossus of New York", "Colson Whitehead", 2003, "Doubleday", 176);
    Book book2 = new Book("Atomic Habits", "James Clear", 2018, "Penguin Random House", 320);
    Book book3 = new Book("Chains", "Laurie Halse Anderson", 2008, "Simon & Schuster", 316);
    library.addBook(book);
    library.addBook(book2);
    library.addBook(book3);
    
    ArrayList<Book> expected = new ArrayList<>();
    expected.add(new Book("The Colossus of New York", "Colson Whitehead", 2003, "Doubleday", 176));
    expected.add(new Book("Chains", "Laurie Halse Anderson", 2008, "Simon & Schuster", 316));
    expected.add(new Book("Atomic Habits", "James Clear", 2018, "Penguin Random House", 320));
    return compareBooks(expected, library.getAllBooks());
  }

  /**
   * PROVIDED TESTER METHOD: example test method for removing a book by title from the library.
   * 
   * @return true if the test passes, false otherwise
   */
  public static boolean testRemoveBookByTitle() {
    Library library = new Library();
    library.addBook(new Book("East of Eden", "John Steinbeck", 1952, "Penguin Classics", 608));
    boolean result = library.removeBookByTitle("East of Eden");

    // checking result from removeBookByTitle("Test Book")
    if (result != true) {
      return false;
    }
    // checking resulted number of books
    if (library.getTotalBooks() != 0) {
      return false;
    }
    ArrayList<Book> expected = new ArrayList<>();
    // checking resulted library
    if (!compareBooks(expected, library.getAllBooks())) {
      return false;
    }
    return true;
  }

  /**
   * Tests to check if one book can be removed correctly from the library
   *
   * @param None
   * @return true if the returned list of books matches the expected list and the size is as expected, and false otherwise
   */
  public static boolean testRemoveOneOfManyBooks() {
    Library library = new Library();
    Book book = new Book("The Colossus of New York", "Colson Whitehead", 2003, "Doubleday", 176);
    Book book2 = new Book("Atomic Habits", "James Clear", 2018, "Penguin Random House", 320);
    Book book3 = new Book("Chains", "Laurie Halse Anderson", 2008, "Simon & Schuster", 316);
    library.addBook(book);
    library.addBook(book2);
    library.addBook(book3);
    library.removeBookByTitle("Chains");
    
    int expectedSize = 2;
    ArrayList<Book> expected = new ArrayList<>();
    expected.add(new Book("The Colossus of New York", "Colson Whitehead", 2003, "Doubleday", 176));
    expected.add(new Book("Atomic Habits", "James Clear", 2018, "Penguin Random House", 320));
    if (compareBooks(expected, library.getAllBooks()) && (expectedSize == library.getTotalBooks())) {
      return true;
    }
    else { return false; }
    // TODO
  }

  /**
   * Tests to check if a book can be found by author
   *
   * @param None
   * @return true if the returned list of books by the author matches expected list and false otherwise
   */
  public static boolean testFindBooksByAuthor() {
    Library library = new Library();
    Book book = new Book("The Colossus of New York", "Colson Whitehead", 2003, "Doubleday", 176);
    Book book2 = new Book("Atomic Habits", "James Clear", 2018, "Penguin Random House", 320);
    Book book3 = new Book("Chains", "Laurie Halse Anderson", 2008, "Simon & Schuster", 316);
    library.addBook(book);
    library.addBook(book2);
    library.addBook(book3);
    
    ArrayList<Book> expected = new ArrayList<>();
    expected.add(new Book("The Colossus of New York", "Colson Whitehead", 2003, "Doubleday", 176));
    ArrayList<Book> returnedBooks = new ArrayList<>();
    returnedBooks = library.findBooksByAuthor("Colson Whitehead");
    
    if (!compareBooks(expected, returnedBooks)) {
      return false;
    }
    return true; // TODO
  }

  /**
   * Tests to check if books can be found by multiple authors
   *
   * @param None
   * @return true if the returned list of books by the authors matches expected lists and false otherwise
   */
  public static boolean testFindBooksByMultipleAuthors() {
    Library library = new Library();
    Book book = new Book("The Colossus of New York", "Colson Whitehead", 2003, "Doubleday", 176);
    Book book2 = new Book("Atomic Habits", "James Clear", 2018, "Penguin Random House", 320);
    Book book3 = new Book("Chains", "Laurie Halse Anderson", 2008, "Simon & Schuster", 316);
    library.addBook(book);
    library.addBook(book2);
    library.addBook(book3);
    
    ArrayList<Book> expected = new ArrayList<>();
    expected.add(new Book("The Colossus of New York", "Colson Whitehead", 2003, "Doubleday", 176));
    ArrayList<Book> returnedBooks = new ArrayList<>();
    returnedBooks = library.findBooksByAuthor("Colson Whitehead");
    
    ArrayList<Book> expected2 = new ArrayList<>();
    expected.add(new Book("Atomic Habits", "James Clear", 2018, "Penguin Random House", 320));
    ArrayList<Book> returnedBooks2 = new ArrayList<>();
    returnedBooks = library.findBooksByAuthor("James Clear");
    
    if ((!compareBooks(expected, returnedBooks)) && (!compareBooks(expected2, returnedBooks2))) {
      return false;
    }
    return true; // TODO
  }


  /**
   * Tests if the setTitle method works as expected for a single book
   *
   * @param None
   * @return true if the updated list of titles matches the expected list and false otherwise
   */
  public static boolean testUpdateBookTitle() {
    Library library = new Library();
    library.addBook(new Book("East of Eden", "John Steinbeck", 1952, "Penguin Classics", 608));
    boolean returnedBoolean = library.updateBookTitle("East of Eden", "The Westing Game");
    
    ArrayList<Book> expected = new ArrayList();
    expected.add(new Book("The Westing Game", "John Steinbeck", 1952, "Penguin Classics", 608));
    if ((!compareBooks(expected, library.getAllBooks())) && (!returnedBoolean)) {
      return false;
    }
    return true; // TODO
  }

  /**
   * Tests if the setTitle method works as expected for multiple books
   *
   * @param None
   * @return true if the updated list of titles matches the expected list and false otherwise
   */
  public static boolean testUpdateMultipleBookTitles() {
    Library library = new Library();
    library.addBook(new Book("East of Eden", "John Steinbeck", 1952, "Penguin Classics", 608));
    library.addBook(new Book("Chains", "Laurie Halse Anderson", 2008, "Simon & Schuster", 316));
    library.updateBookTitle("East of Eden", "The Westing Game");
    
    ArrayList<Book> expected = new ArrayList();
    expected.add(new Book("The Westing Game", "John Steinbeck", 1952, "Penguin Classics", 608));
    expected.add(new Book("Chains", "Laurie Halse Anderson", 2008, "Simon & Schuster", 316));
    ArrayList<Book> returnedBooks = new ArrayList<>();
    returnedBooks = library.getAllBooks();
    
    library.updateBookTitle("Chains", "Harry Potter");
    ArrayList<Book> expected2 = new ArrayList();
    expected2.add(new Book("The Westing Game", "John Steinbeck", 1952, "Penguin Classics", 608));
    expected2.add(new Book("Harry Potter", "Laurie Halse Anderson", 2008, "Simon & Schuster", 316));
    ArrayList<Book> returnedBooks2 = new ArrayList<>();
    returnedBooks2 = library.getAllBooks();
    
    if ((!compareBooks(expected, returnedBooks)) && (!compareBooks(expected2, returnedBooks2))) {
      return false;
    }
    return true; // TODO
  }

  /**
   * Tests if the setAuthor method works as expected for a single book
   *
   * @param None
   * @return true if the updated list of authors matches the expected list and false otherwise
   */
  public static boolean testUpdateBookAuthor() {
    Library library = new Library();
    library.addBook(new Book("East of Eden", "John Steinbeck", 1952, "Penguin Classics", 608));
    boolean returnedBoolean = library.updateBookAuthor("East of Eden", "J.K. Rowling");
    
    ArrayList<Book> expected = new ArrayList();
    expected.add(new Book("East of Eden", "J.K. Rowling", 1952, "Penguin Classics", 608));
    if ((!compareBooks(expected, library.getAllBooks())) && (!returnedBoolean)) {
      return false;
    }
    return true; // TODO
  }

  /**
   * Tests if the setAuthor method works as expected for multiple books
   *
   * @param None
   * @return true if the updated list of authors matches the expected list and false otherwise
   */
  public static boolean testUpdateMultipleBookAuthors() {
    Library library = new Library();
    library.addBook(new Book("East of Eden", "John Steinbeck", 1952, "Penguin Classics", 608));
    library.addBook(new Book("Chains", "Laurie Halse Anderson", 2008, "Simon & Schuster", 316));
    library.updateBookAuthor("East of Eden", "J.K. Rowling");
    
    ArrayList<Book> expected = new ArrayList();
    expected.add(new Book("East of Eden", "J.K. Rowling", 1952, "Penguin Classics", 608));
    expected.add(new Book("Chains", "Laurie Halse Anderson", 2008, "Simon & Schuster", 316));
    ArrayList<Book> returnedBooks = new ArrayList<>();
    returnedBooks = library.getAllBooks();
    
    library.updateBookAuthor("Chains", "Rick Riordan");
    ArrayList<Book> expected2 = new ArrayList();
    expected2.add(new Book("East of Eden", "J.K. Rowling", 1952, "Penguin Classics", 608));
    expected2.add(new Book("Chains", "Rick Riordan", 2008, "Simon & Schuster", 316));
    ArrayList<Book> returnedBooks2 = new ArrayList<>();
    returnedBooks2 = library.getAllBooks();
    
    if ((!compareBooks(expected, returnedBooks)) && (!compareBooks(expected2, returnedBooks2))) {
      return false;
    }
    return true; // TODO
  }

  /**
   * Tests if the removeNonExistentBook method works correctly
   *
   * @param None
   * @return true if the null books have been removed correctly and matches expected list and false otherwise
   */
  public static boolean testRemoveNonExistentBook() {
    Library library = new Library();
    library.addBook(new Book("East of Eden", "John Steinbeck", 1952, "Penguin Classics", 608));
    boolean result = library.removeBookByTitle("Harry Book");

    ArrayList<Book> expected = new ArrayList<>();
    expected.add(new Book("East of Eden", "John Steinbeck", 1952, "Penguin Classics", 608));
    if ((!compareBooks(expected, library.getAllBooks())) && (!result)) {
      return false;
    }
    return true; // TODO
  }

  /**
   * Compares two lists of books for equality.
   * 
   * @param expected the expected list of books
   * @param result   the list of books to compare
   * @return true if both lists contain the same books, false otherwise
   */
  private static boolean compareBooks(ArrayList<Book> expected, ArrayList<Book> result) {
    if (expected.size() != result.size()) {
      return false;
    }
    for (int i = 0; i < expected.size(); i++) {
      Book expectedBook = expected.get(i);
      Book resultBook = result.get(i);
      if (!expectedBook.getTitle().equals(resultBook.getTitle())
          || !expectedBook.getAuthor().equals(resultBook.getAuthor())
          || !(expectedBook.getPublisher().equals(resultBook.getPublisher()))
          || !(expectedBook.getNumberOfPages() == resultBook.getNumberOfPages())
          || !(expectedBook.getYearOfPublication() == resultBook.getYearOfPublication())) {
        return false;
      }
    }
    return true;
  }

  public static void main(String[] args) {
    // test two functions in book.class
    System.out.println("Test getTitle: " + testGetTitle());
    System.out.println("Test setTitle: " + testSetTitle());
    System.out.println("Test getAuthor: " + testGetAuthor());
    System.out.println("Test setAuthor: " + testSetAuthor());
    System.out.println("Test getYearOfPublication: " + testGetYearOfPublication());
    System.out.println("Test setYearOfPublication: " + testSetYearOfPublication());
    System.out.println("Test getPublisher: " + testGetPublisher());
    System.out.println("Test setPublisher: " + testSetPublisher());
    System.out.println("Test getNumberOfPages: " + testGetNumberOfPages());
    System.out.println("Test setNumberOfPages: " + testSetNumberOfPages());
    System.out.println("Test addBook: " + testAddBook());
    System.out.println("Test addMultipleBooks: " + testAddMultipleBooks());
    System.out.println("Test removeBookByTitle: " + testRemoveBookByTitle());
    System.out.println("Test removeOneOfManyBooks: " + testRemoveOneOfManyBooks());
    System.out.println("Test findBooksByAuthor: " + testFindBooksByAuthor());
    System.out.println("Test findBooksByMultipleAuthors: " + testFindBooksByMultipleAuthors());
    System.out.println("Test updateBookTitle: " + testUpdateBookTitle());
    System.out.println("Test updateMultipleBookTitles: " + testUpdateMultipleBookTitles());
    System.out.println("Test updateBookAuthor: " + testUpdateBookAuthor());
    System.out.println("Test updateMultipleBookAuthors: " + testUpdateMultipleBookAuthors());
    System.out.println("Test removeNonExistentBook: " + testRemoveNonExistentBook());

  }
}
