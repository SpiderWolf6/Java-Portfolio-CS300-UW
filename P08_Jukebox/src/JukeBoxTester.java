//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Jukebox
// Course:   CS 300 Fall 2024
//
// Author:   Soham Mukherjee
// Email:    smukherjee39@wisc.edu
// Lecturer: Blerina Gkotse
//
import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * Tester class for testing the functionality of the LinkedQueue, LinkedStack, Album, Song, and
 * Jukebox classes.
 */
public class JukeBoxTester {

  /**
   * Test the behavior of adding an element to the stack.
   * 
   * @return true if element is correctly added to the stack, false otherwise
   */
  public static boolean testStackAdd() {
    LinkedStack<String> stack = new LinkedStack<>(); // create stack and add elements
    stack.push("10");
    stack.push("20");
    stack.push("30");
    ArrayList<String> referenceStack = new ArrayList<>(); // create reference stack to double check
    referenceStack.add("30");
    referenceStack.add("20");
    referenceStack.add("10");
    if((stack.peek().equals("30")) && (stack.getList().equals(referenceStack))) {
      return true; // elements added correctly
    }
    return false;
  }

  /**
   * Test the behavior of removing an element from the stack.
   * 
   * @return true if element is correctly removed from the stack, false otherwise
   */
  public static boolean testStackRemove() {
    LinkedStack<String> stack = new LinkedStack<>(); // create stack and add elements
    stack.push("10");
    stack.push("20");
    stack.push("30");
    String removedValue = stack.pop();
    if(removedValue.equals("30")) {
      return true; // most recently added element was popped correctly
    }
    return false;
  }

  /**
   * Test the behavior of adding an element to the queue.
   * 
   * @return true if element is correctly added to the queue, false otherwise
   */
  public static boolean testQueueAdd() {
    LinkedQueue<Integer> queue = new LinkedQueue<>(); // create queue and add elements
    queue.enqueue(10);
    queue.enqueue(20);
    queue.enqueue(30);
    ArrayList<Integer> referenceQueue = new ArrayList<>(); // create reference queue to double check
    referenceQueue.add(10);
    referenceQueue.add(20);
    referenceQueue.add(30);
    if((queue.peek() == 10) && (queue.getList().equals(referenceQueue))) {
      return true; // elements added correctly
    }
    return false;
  }

  /**
   * Test the behavior of removing an element from the queue.
   * 
   * @return true if element is correctly removed from the queue, false otherwise
   */
  public static boolean testQueueRemove() {
    LinkedQueue<Integer> queue = new LinkedQueue<>(); // create queue and add elements
    queue.enqueue(10);
    queue.enqueue(20);
    queue.enqueue(30);
    int currentSize = queue.size();
    int removedValue = queue.dequeue();
    if((removedValue == 10) && (queue.size() == currentSize-1)) {
      return true; // least recently added element was dequeued properly
    }
    return false;
  }

  /**
   * Test the behavior of peeking at the top element (for stack) and the front element (for queue).
   * 
   * @return true if the correct element returned for both data structures, false otherwise
   */
  public static boolean testPeek() {
    LinkedStack<String> stack = new LinkedStack<>(); // create stack and add elements
    stack.push("10");
    stack.push("20");
    stack.push("30");
    LinkedQueue<Integer> queue = new LinkedQueue<>(); // create queue and add elements
    queue.enqueue(10);
    queue.enqueue(20);
    queue.enqueue(30);
    if((stack.peek().equals("30")) && (stack.contains("30")) && (queue.peek() == 10) && (queue.contains(10))) {
      return true; // if both peek methods return the correct values, test passed
    }
    return false;
  }

  /**
   * This method tests whether the contains method correctly identifies whether a specific element
   * exists in a stack and a queue.
   * 
   * @return true if the test passes, false otherwise
   */
  public static boolean testContains() {
    boolean checkStack = false;
    boolean checkQueue = false;
    
    LinkedStack<String> stack = new LinkedStack<>(); // create stack and start adding elements
    stack.push("10");
    stack.push("20");
    stack.push("30");
    if(stack.contains("20")) {
      checkStack = true; // true if given element exists in the stack
    }
    
    LinkedQueue<Integer> queue = new LinkedQueue<>(); // create queue and start adding elements
    queue.enqueue(10);
    queue.enqueue(20);
    queue.enqueue(30);
    if(queue.contains(20)) {
      checkQueue = true; // true if the given element exists in the queue
    }
    return checkStack && checkQueue; // return true if both tests passed
  }

  /**
   * Test the behavior of getting the list of elements in the stack and queue.
   * 
   * @return true if method returns a correctly ordered list for both data structures, false
   *         otherwise
   */
  public static boolean testGetList() {
    boolean checkStack = false;
    boolean checkQueue = false;
    
    LinkedStack<String> stack = new LinkedStack<>(); // create stack and start adding elements
    stack.push("10");
    stack.push("20");
    stack.push("30");
    ArrayList<String> referenceStack = new ArrayList<>(); // create reference stack to double check
    referenceStack.add("30");
    referenceStack.add("20");
    referenceStack.add("10");
    if(stack.getList().equals(referenceStack)) {
      checkStack = true; // if stack's arraylist form matches expected reference then this test passed
    }
    
    LinkedQueue<Integer> queue = new LinkedQueue<>(); // create queue and start adding elements
    queue.enqueue(10);
    queue.enqueue(20);
    queue.enqueue(30);
    ArrayList<Integer> referenceQueue = new ArrayList<>(); // create reference queue to double check
    referenceQueue.add(10);
    referenceQueue.add(20);
    referenceQueue.add(30);
    if(queue.getList().equals(referenceQueue)) {
      checkQueue = true; // if queue's arraylist form matches expected reference then this test passed
    }
    return checkStack && checkQueue; // return true if both tests passed
  }

  /**
   * Tests adding songs to an Album and verifies the size and content. Checks if songs are correctly
   * added in LIFO order.
   * 
   * @return true if it passes all test cases, false otherwise
   */
  public static boolean testAddSongToAlbum() {
    boolean check1 = false; // check duplicate condition
    boolean check2 = false; // check correctly added condition
    Album album = new Album("Upbeat"); // create album and start adding songs
    try {
      album.addSong(new Song("Despacito", "Luis Fonsi"));
      album.addSong(new Song("Danza Kuduro", "Don Omar"));
      album.addSong(new Song("Shape of You", "Ed Sheeran"));
      Song duplicateSong = new Song("Despacito", "Luis Fonsi"); // create duplicate song properly
      duplicateSong.setAlbum(album);
      album.addSong(duplicateSong); // attempt to add duplicate song
    } catch (IllegalArgumentException e) {
      System.out.println(e.getMessage());
      check1 = true; // if caught properly then check1 passed
    }
    System.out.println(check1);
    int currentSize = album.size();
    ArrayList<Song> referenceTrack = new ArrayList<>(); // create reference track to double check
    referenceTrack.add(new Song("Shape of You", "Ed Sheeran"));
    referenceTrack.add(new Song("Danza Kuduro", "Don Omar")); 
    referenceTrack.add(new Song("Despacito", "Luis Fonsi"));
    String referenceString = "Upbeat";
    for(Song s : referenceTrack) {
      s.setAlbum(new Album("Upbeat"));
      referenceString = referenceString + "\n" + s.toString(); // construct reference string to check if album's content is correct
    }
    if((album.size() == currentSize) && (album.toString().equals(referenceString))) {
      check2 = true; // albums size and contents match so check2 passed
    }
    return check1 && check2;
  }

  /**
   * Tests removing a song from an Album and verifies the size and content after removal. Checks if
   * songs are correctly removed in LIFO order.
   * 
   * @return true if it passes all test cases, false otherwise
   */
  public static boolean testRemoveSongFromAlbum() {
    boolean check1 = false;
    boolean check2 = false;
    Album album = new Album("Upbeat"); // create album and start adding songs
    album.addSong(new Song("Despacito", "Luis Fonsi"));
    album.addSong(new Song("Danza Kuduro", "Don Omar"));
    album.addSong(new Song("Shape of You", "Ed Sheeran"));
    int currentSize = album.size();
    Song firstSong = album.firstSong();
    Song removedSong = album.removeSong(); // get the removed song from the album
    
    ArrayList<Song> referenceStack = new ArrayList<>(); // create reference album to double check
    referenceStack.add(new Song("Danza Kuduro", "Don Omar"));
    referenceStack.add(new Song("Despacito", "Luis Fonsi"));
    String referenceString = "Upbeat";
    for(Song s : referenceStack) {
      s.setAlbum(new Album("Upbeat"));
      referenceString = referenceString + "\n" + s.toString(); // construct reference string to check if album's content is correct after removal
    }
    if((firstSong.equals(removedSong)) && (album.size() == currentSize-1) && (album.toString().equals(referenceString))) {
      check1 = true; // correct song removed and size and content matches, so test passed
    }
    
    Album emptyAlbum = new Album("Empty");
    try {
      emptyAlbum.removeSong();
      return false;
    }
    catch(NoSuchElementException e) {
      System.out.println(e.getMessage());
      check2 = true;
    }
    return check1 && check2;
  }

  /**
   * Tests the toString method of the Album class. Verifies that the returned string correctly
   * represents all songs in LIFO order.
   * 
   * @return true if it passes all test cases, false otherwise
   */
  public static boolean testAlbumToString() {
    Album album = new Album("Upbeat"); // create album and start adding songs
    album.addSong(new Song("Despacito", "Luis Fonsi"));
    album.addSong(new Song("Danza Kuduro", "Don Omar"));
    album.addSong(new Song("Shape of You", "Ed Sheeran"));
    ArrayList<Song> referenceTrack = new ArrayList<>(); // create reference track to double check
    referenceTrack.add(new Song("Shape of You", "Ed Sheeran"));
    referenceTrack.add(new Song("Danza Kuduro", "Don Omar"));
    referenceTrack.add(new Song("Despacito", "Luis Fonsi"));
    String referenceString = "Upbeat";
    for(Song s : referenceTrack) {
      s.setAlbum(new Album("Upbeat"));
      referenceString = referenceString  + "\n" + s.toString(); // construct reference string to check if album's content is correct
    }
    if(album.toString().equals(referenceString)) {
      return true; // albums size and contents match so test passed
    }
    return false;
  }

  /**
   * Tests adding a song to the Jukebox and verifies the queue contents and size.
   * 
   * @return true if it passes all test cases, false otherwise
   */
  public static boolean testAddSongToJukebox() {
    boolean check1 = false; // check duplicates condition
    boolean check2 = false; // check if added correctly
    JukeBox jukebox = new JukeBox(4); // create jukebox and start adding songs
    try {
      jukebox.addSongToQueue(new Song("Despacito", "Luis Fonsi"));
      jukebox.addSongToQueue(new Song("Danza Kuduro", "Don Omar"));
      jukebox.addSongToQueue(new Song("Shape of You", "Ed Sheeran"));
      jukebox.addSongToQueue(new Song("Despacito", "Luis Fonsi")); // attempt to add duplicate song
    } catch (IllegalArgumentException e) {
      System.out.println(e.getMessage());
      check1 = true; // if caught correctly then check1 passed
    }
    int currentSize = jukebox.size();
    ArrayList<Song> referenceJukebox = new ArrayList<>(); // create reference jukebox to double check
    referenceJukebox.add(new Song("Despacito", "Luis Fonsi"));
    referenceJukebox.add(new Song("Danza Kuduro", "Don Omar"));
    referenceJukebox.add(new Song("Shape of You", "Ed Sheeran"));
    String referenceString = "";
    for(Song s : referenceJukebox) {
      referenceString = referenceString + s.toString() + " -> "; // construct reference string to check if jukebox's content is correct
    }
    referenceString = referenceString + "END";
    if((jukebox.size() == currentSize) && (jukebox.toString().equals(referenceString))) {
      check2 = true; // jukebox's size and contents match so check2 passed
    }
    return check1 && check2;
  }

  /**
   * Tests adding an album to the Jukebox and verifies the queue contents and size.
   * 
   * @return true if it passes all test cases, false otherwise
   */
  public static boolean testAddAlbumToJukebox() {
    boolean check1 = false;
    boolean check2 = false;
    Album album = new Album("Upbeat"); // create album and start adding songs
    album.addSong(new Song("Despacito", "Luis Fonsi"));
    album.addSong(new Song("Danza Kuduro", "Don Omar"));
    album.addSong(new Song("Shape of You", "Ed Sheeran"));
    JukeBox jukebox = new JukeBox(3); // create jukebox and start adding songs
    jukebox.addAlbumToQueue(album);
    ArrayList<Song> referenceQueue = new ArrayList<>(); // create reference jukebox to double check
    referenceQueue.add(new Song("Shape of You", "Ed Sheeran"));
    referenceQueue.add(new Song("Danza Kuduro", "Don Omar"));
    referenceQueue.add(new Song("Despacito", "Luis Fonsi"));
    String referenceString = "";
    for(Song s : referenceQueue) {
      s.setAlbum(new Album("Upbeat"));
      referenceString = referenceString + s.toString() + " -> "; // construct reference string to check if jukebox's content is correct
    }
    referenceString = referenceString + "END";
    if(jukebox.toString().equals(referenceString)) {
      check1 = true; // jukebox's size and contents match so test passed
    }
   
    Album partialAlbum = new Album("Upbeat"); // create album and start adding songs
    partialAlbum.addSong(new Song("Despacito", "Luis Fonsi"));
    partialAlbum.addSong(new Song("Danza Kuduro", "Don Omar"));
    partialAlbum.addSong(new Song("Shape of You", "Ed Sheeran"));
    JukeBox partialJukebox = new JukeBox(2); // create jukebox and start adding songs
    partialJukebox.addAlbumToQueue(partialAlbum);
    ArrayList<Song> referenceQueue2 = new ArrayList<>(); // create reference jukebox to double check
    referenceQueue2.add(new Song("Shape of You", "Ed Sheeran"));
    referenceQueue2.add(new Song("Danza Kuduro", "Don Omar"));
    String referenceString2 = "";
    for(Song s : referenceQueue2) {
      s.setAlbum(new Album("Upbeat"));
      referenceString2 = referenceString2 + s.toString() + " -> "; // construct reference string to check if jukebox's content is correct
    }
    referenceString2 = referenceString2 + "END";
    if(partialJukebox.toString().equals(referenceString2)) {
      check2 = true; // jukebox's size and contents match so test passed
    }
    
    return check1 && check2;
  }

  /**
   * Tests playing a song from the JukeboxQueue. Verifies that the song is removed from the queue
   * after playback.
   * 
   * @return true if it passes all test cases, false otherwise
   */
  public static boolean testPlaySongFromJukebox() {
    boolean check1 = false; // check if correct song is played
    boolean check2 = false; // check empty jukebox condition
    JukeBox jukebox = new JukeBox(3); // create jukebox and start adding songs
    jukebox.addSongToQueue(new Song("Despacito", "Luis Fonsi"));
    jukebox.addSongToQueue(new Song("Danza Kuduro", "Don Omar"));
    jukebox.addSongToQueue(new Song("Shape of You", "Ed Sheeran"));
    int currentSize = jukebox.size();
    try { 
      Song playedSong = jukebox.playSong();
      ArrayList<Song> referenceJukebox = new ArrayList<>(); // create reference jukebox to double check
      referenceJukebox.add(new Song("Despacito", "Luis Fonsi"));
      referenceJukebox.add(new Song("Danza Kuduro", "Don Omar"));
      referenceJukebox.add(new Song("Shape of You", "Ed Sheeran"));
      if((playedSong.equals(referenceJukebox.get(0))) && (jukebox.size() == currentSize-1)) {
        check1 = true; // if correct song is removed and size is decremented correctly, check1 passed
      }
    } catch (NoSuchElementException e) {
      System.out.println(e.getMessage());
      return false;
    }
    
    JukeBox emptyJukebox = new JukeBox(2); // create empty jukebox
    try {
      emptyJukebox.playSong(); // attempt to play song from empty jukebox
    } catch (NoSuchElementException e) {
      System.out.println(e.getMessage());
      check2 = true; // check2 passed if exception correctly thrown
    }
    return check1 && check2;
  }

  /**
   * Tests shuffling the JukeBox queue. Verifies that the songs are reordered randomly after the
   * operation.
   * 
   * @return true if it passes all test cases, false otherwise
   */
  public static boolean testJukeboxShuffle() {
    JukeBox jukebox = new JukeBox(10); // create jukebox and start adding songs
    jukebox.addSongToQueue(new Song("Despacito", "Luis Fonsi"));
    jukebox.addSongToQueue(new Song("Danza Kuduro", "Don Omar"));
    jukebox.addSongToQueue(new Song("Shape of You", "Ed Sheeran"));
    jukebox.addSongToQueue(new Song("Heat Waves", "Glass Animals"));
    jukebox.addSongToQueue(new Song("Starboy", "The Weeknd"));
    jukebox.addSongToQueue(new Song("Blinding Lights", "The Weeknd"));
    jukebox.addSongToQueue(new Song("Counting Stars", "One Republic"));
    jukebox.addSongToQueue(new Song("Hide", "Juice WRLD"));
    jukebox.addSongToQueue(new Song("Cold", "Maroon 5"));
    jukebox.addSongToQueue(new Song("Calm Down", "Rema"));
    jukebox.shuffleSongQueue(); // shuffle jukebox order
    if(!jukebox.playSong().equals(new Song("Despacito", "Luis Fonsi"))) {
      return true; // if order of songs has been switched, then shuffled correctly
    }
    return false;
  }

  public static void main(String[] args) {
    // Running and printing results for all the tests

    boolean test1 = testStackAdd();
    System.out.println("testStackAdd: " + (test1 ? "PASS" : "FAIL"));

    boolean test2 = testStackRemove();
    System.out.println("testStackRemove: " + (test2 ? "PASS" : "FAIL"));

    boolean test3 = testQueueAdd();
    System.out.println("testQueueAdd: " + (test3 ? "PASS" : "FAIL"));

    boolean test4 = testQueueRemove();
    System.out.println("testQueueRemove: " + (test4 ? "PASS" : "FAIL"));

    boolean test5 = testPeek();
    System.out.println("testPeek: " + (test5 ? "PASS" : "FAIL"));

    boolean test6 = testContains();
    System.out.println("testContains: " + (test6 ? "PASS" : "FAIL"));

    boolean test7 = testGetList();
    System.out.println("testGetList: " + (test7 ? "PASS" : "FAIL"));

    boolean test8 = testAddSongToAlbum();
    System.out.println("testAddSongToAlbum: " + (test8 ? "PASS" : "FAIL"));

    boolean test9 = testRemoveSongFromAlbum();
    System.out.println("testRemoveSongFromAlbum: " + (test9 ? "PASS" : "FAIL"));

    boolean test10 = testAlbumToString();
    System.out.println("testAlbumToString: " + (test10 ? "PASS" : "FAIL"));

    boolean test11 = testAddSongToJukebox();
    System.out.println("testAddSongToJukebox: " + (test11 ? "PASS" : "FAIL"));

    boolean test12 = testAddAlbumToJukebox();
    System.out.println("testAddAlbumToJukebox: " + (test12 ? "PASS" : "FAIL"));

    boolean test13 = testPlaySongFromJukebox();
    System.out.println("testPlaySongFromJukebox: " + (test13 ? "PASS" : "FAIL"));

    boolean test14 = testJukeboxShuffle();
    System.out.println("testJukeboxShuffle: " + (test14 ? "PASS" : "FAIL"));

    System.out.println("ALL TESTS: " + (test1 && test2 && test3 && test4 && test5 && test6 && test7
        && test8 && test9 && test10 && test11 && test12 && test13 && test14 ? "PASS" : "FAIL"));
  }
}
