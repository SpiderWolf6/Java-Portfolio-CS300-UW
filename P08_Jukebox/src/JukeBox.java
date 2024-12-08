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
import java.util.Collections;

/**
 * This class manages jukebox objects, including basic actions like adding and playing songs to/from the queue of the jukebox
 */
public class JukeBox extends Object {
  private int capacity;
  private LinkedQueue<Song> songQueue;
  
  /**
   * Constructs a new, empty jukebox with the specified capacity.
   *
   * @param capacity an int
   * @throws IllegalArgumentException if the provided capacity is a negative integer
   */
  public JukeBox(int capacity) {
    if(capacity < 0) {
      throw new IllegalArgumentException("provided capacity is negative");
    }
    songQueue = new LinkedQueue<>();
    this.capacity = capacity;
  }
  
  /** Adds a new song to the front of the jukebox queue, checking for duplicates and max capacity
   * @param song a Song
   * @return None
  */
  public void addSongToQueue(Song song) {
    // if song already exists or queue is at max capacity, do not enqueue
    if(songQueue.size() == capacity) {
      throw new IllegalStateException("queue is at maximum capacity");
    }
    if(songQueue.contains(song)) {
      throw new IllegalArgumentException("song already exists in queue");
    }
    songQueue.enqueue(song);
  }
  
  /** Adds an entire album to the front of the jukebox queue, while considering max capacity
   * @param album an Album
   * @return None
  */
  public void addAlbumToQueue(Album album) {
    Album newAlbum = album;
    // for every song in the album, continue adding to queue until it is at max capacity or full album as been added
    try {
      while(newAlbum.size() != 0) {
        this.addSongToQueue(newAlbum.removeSong());
      }
    } catch (IllegalStateException e) {
      System.out.println(e.getMessage());
      return;
    }
  }
  
  /** Removes and returns the song at the front of the jukebox queue
   * @param None
   * @return the first song in the queue
  */
  public Song playSong() {
    if(songQueue.size() == 0) {
      throw new NoSuchElementException("queue is empty");
    }
    return songQueue.dequeue();
  }
  
  /** Shuffles the order of songs in the jukebox queue
   * @param None
   * @return None
  */
  public void shuffleSongQueue() {
    // use Collections.shuffle to change order of songs
    ArrayList<Song> queue = songQueue.getList();
    Collections.shuffle(queue);
    songQueue.clear();
    while(queue.size() != 0) {
      songQueue.enqueue(queue.get(0));
      queue.remove(0);
    }
  }
  
  /** Gets the size of the album
   * @param None
   * @return size of the album
  */
  public int size() {
    return songQueue.size();
  }
  
  /** Gets the capacity of the album
   * @param None
   * @return capacity an int
  */
  public int capacity() {
    return capacity;
  }
  
  /** Checks to see if the queue is fully filled up
   * @param None
   * @return true if the size of the queue matches the capacity, false otherwise
  */
  public boolean isFull() {
    if(songQueue.size() == capacity) {
      return true;
    }
    return false;
  }
  
  /** Checks to see if the size of the queue is 0
   * @param None
   * @return true if the queue is currently empty, false otherwise
  */
  public boolean isEmpty() {
    if(songQueue.size() == 0) {
      return true;
    }
    return false;
  }
  
  /** Converts the jukebox queue to a valid String representation with correct order of elements
   * @param None
   * @return the String representation of the jukebox
  */
  public String toString() {
    ArrayList<Song> arr = songQueue.getList();
    String returnString = "";
 // iterate through arraylist of jukebox to create String form
    for(Song s : arr) {
      returnString = returnString + s.toString() + " -> ";
    }
    return returnString + "END";
  }
  
}