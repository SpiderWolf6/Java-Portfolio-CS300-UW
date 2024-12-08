//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Calculating Partitions
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

/**
 * Method that calculates a list of unique partitions based off a given integer
 */
public class PartitionCalculator {

  /**
   * 
   * Recursive helper method that generates all the partitions of int n, and adds each partition to allPartitions
   * only if it is unique
   * @param p the partition being generated
   * @param n number being partitioned
   * @param max maximum number that can be used in the partition
   * @param allPartitions List that stores all partitions for the number n
   * @param uniquePartitions list storing only the unique partitions for n
   */
    private static void compilePartitions(Partition p, int n, int max, ArrayList<Partition> allPartitions, ArrayList<Partition> uniquePartitions) {
        if (n == 0) {
            allPartitions.add(p.copyOf());
            if (!isDuplicate(p, 0, uniquePartitions)) {
                uniquePartitions.add(p.copyOf());
            }
            return;
        }

        if (max > 0) {
            if (max <= n) {
                p.addNumber(max);
                compilePartitions(p, n - max, max, allPartitions, uniquePartitions);
                p.removeLast();
            }
            compilePartitions(p, n, max - 1, allPartitions, uniquePartitions);
        }
    }

    /**
     * Helper method that checks if a partition of p is a duplicate within uniquePartitions
     * @param p the partition checking for duplicates
     * @param index current index in uniquePartitions to compare against
     * @param uniquePartitions List unique partitions to check for duplicates
     * @return true if p is a duplicate, false otherwise
     */
    private static boolean isDuplicate(Partition p, int index, ArrayList<Partition> uniquePartitions) {
        if (index >= uniquePartitions.size()) {
            return false; 
        }
        if (uniquePartitions.get(index).equals(p)) {
            return true; 
        }
        return isDuplicate(p, index + 1, uniquePartitions);
    }

    /**
     * method that calculates the number of unique partitions for a given integer
     * @param N int to partition
     * @return the number of partitions for n
     */
    public static int numOfPartitions(int N) {
      ArrayList<Partition> allPartitions = new ArrayList<>();
      ArrayList<Partition> uniquePartitions = new ArrayList<>();
        compilePartitions(new Partition(N), N, N, allPartitions, uniquePartitions);
        return uniquePartitions.size();
    }

    /**
     * method that calculates and returns an arraylist of unique partitions for a given integer
     * @param N
     * @return
     */
    public static ArrayList<Partition> calculatePartitions(int N) {
      ArrayList<Partition> allPartitions = new ArrayList<>();
      ArrayList<Partition> uniquePartitions = new ArrayList<>();
        compilePartitions(new Partition(N), N, N, allPartitions, uniquePartitions);
        return new ArrayList<>(uniquePartitions);
    }
      
    /**
     * Method that calculates all unique permutation of a partition in a given list
     * 
     * @param partitions list of unique partitions to permute
     * @return list containing all unique permutations of each partition
     */
      public static ArrayList<Partition> calculateAllPermutations(ArrayList<Partition> partitions) {
        ArrayList<Partition> allPermutations = new ArrayList<>();
        for(int i = 0; i<partitions.size(); i++) {
          sortArray(partitions.get(i));
          backtrack(partitions.get(i), new Partition(partitions.get(i).getSum()), new boolean[partitions.get(i).length()], allPermutations);
        }
        return new ArrayList<Partition>(allPermutations);
      }
      
      /**
       * A recursive helper method that generates all unique permutations of a partition using backtracking
       * @param partition partition being permutated
       * @param current the permutation being generated
       * @param used boolean array tracking which elements have been used
       * @param allPermutations List that stores all unique permutation that have been generated
       */
      private static void backtrack(Partition partition, Partition current, boolean[] used, ArrayList<Partition> allPermutations) {
        // if current permutation sums to partition sum, done exploring this branch, add permutation to allPermutations array
        if(current.getSum() == partition.getSum()) {
          Partition addedPermutation = current.copyOf();
          addedPermutation.orderMatters = true;
          allPermutations.add(addedPermutation);
          return;
        }
        // loop through each integer in current partition to explore all possible branches
        for(int i = 0; i<partition.length(); i++) {
          if(used[i] || ((i>0) && (partition.getNumAt(i) == partition.getNumAt(i-1)) && (!used[i-1]))) {
            // if number has already been explored for current permutation or is a duplicate then drop it
            continue;
          }
        // set current number as already used in this branch, and add number to current permutation
        used[i] = true;
        current.addNumber(partition.getNumAt(i));
        
        // build back for other branches
        backtrack(partition, current, used, allPermutations);
        
        // reset current number as not used, remove last number
        used[i] = false;
        current.removeLast();
        } 
      }
      
      /**
       * Helper method that sorts a partition in ascending order using a swap algorithm    
       * @param currentPartition partition being sorted
       */
      private static void sortArray(Partition currentPartition) {
        for(int i = 0; i<currentPartition.length(); i++) {
          for(int j = i+1; j<currentPartition.length(); j++) {
            if(currentPartition.getNumAt(i) > currentPartition.getNumAt(j)) {
              currentPartition.swapNumbers(i, j);
            }
          }
        }
      }
}