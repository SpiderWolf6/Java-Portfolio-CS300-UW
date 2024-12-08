//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Jukebox
// Course:   CS 300 Fall 2024
//
// Author:   Soham Mukherjee
// Email:    smukherjee39@wisc.edu
// Lecturer: Blerina Gkotse
//
import java.util.NoSuchElementException;
import java.util.ArrayList;

/**
 * This class manages album objects, including basic actions like adding and removing songs to/from the album
 */
public class Album extends Object {
  private String albumName;
  private int size;
  private LinkedStack<Song> trackList;
  
  /**
   * Constructs a new, empty album with the specified name.
   *
   * @param albumName a String
   * @throws None
   */
  public Album(String albumName) {
    this.albumName = albumName;
    trackList = new LinkedStack<>();
    size = 0;
  }
  
  /** Adds a new song to the top of the album, checking for duplicates
   * @param s a Song
   * @return None
  */
  public void addSong(Song s) {
    if(trackList.contains(s)) {
      throw new IllegalArgumentException("song already exists in album");
    }
    trackList.push(s);
    s.setAlbum(this);
  }
  
  /** Removes and returns the top song of the album
   * @param None
   * @return the top Song
  */
  public Song removeSong() {
    if(trackList.peek() == null) {
      throw new NoSuchElementException("album is empty");
    }
    return trackList.pop();
  }
  
  /** Returns the top song without removing
   * @param None
   * @return the top Song
  */
  public Song firstSong() {
    return trackList.peek();
  }
  
  /** Gets the name of the album
   * @param None
   * @return albumName a String
  */
  public String getAlbumName() {
    return albumName;
  }
  
  /** Gets the size of the album
   * @param None
   * @return size of the album
  */
  public int size() {
    return trackList.getList().size();
  }
  
  /** Converts the album to a valid String representation with correct order of elements
   * @param None
   * @return the String representation of the album
  */
  public String toString() {
    ArrayList<Song> arr = trackList.getList();
    String returnString = this.albumName;
    // iterate through arraylist of album to create String form
    for(Song s : arr) {
      returnString = returnString +  "\n" + s.toString();
    }
    return returnString;
  }
  
}