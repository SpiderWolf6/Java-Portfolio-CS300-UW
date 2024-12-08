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
 * The class PartitionCalculatorTester is responsible for testing various methods of the
 * PartitionCalculator. It includes unit tests for basic scenarios, recursive cases, and random input cases.
 * These tests validate the correctness of partition counting, partition calculation, and generating all permutations.
 */
public class PartitionCalculatorTester {

  /**
   * Verifies the behavior of the PartitionCalculator.numOfPartitions method for small inputs (n = 1, 2).
   * Also checks for potential errors or exceptions during execution.
   * 
   * @return true if all tests are successful, false if any test fails
   */
  public static boolean testNumOfPartitionsBase() {
    try {
      // Test case for n = 1, expected result is 1 partition
      if (PartitionCalculator.numOfPartitions(1) != TesterUtility.getPartitionCount(1)) {
        return false;
      }

      // Test case for n = 2, expected result is 2 partitions
      if (PartitionCalculator.numOfPartitions(2) != TesterUtility.getPartitionCount(2)) {
        return false;
      }
    } catch (Exception e) {
      // Return false if any exception occurs
      return false;
    }
    return true; // Success if no issues encountered
  }

  /**
   * Tests the PartitionCalculator.numOfPartitions method with larger values of n (n >= 3).
   * Ensures correctness through multiple test cases and checks for potential errors.
   * 
   * @return true if all tests pass, false if any test fails
   */
  public static boolean testNumOfPartitionsRecursive() {
    try {
      // Test case for n = 3
      if (PartitionCalculator.numOfPartitions(3) != TesterUtility.getPartitionCount(3)) {
        return false;
      }

      // Test case for n = 5
      if (PartitionCalculator.numOfPartitions(5) != TesterUtility.getPartitionCount(5)) {
        return false;
      }

      // Test case for n = 22
      if (PartitionCalculator.numOfPartitions(22) != TesterUtility.getPartitionCount(22)) {
        return false;
      }
    } catch (Exception e) {
      // If an exception occurs, return false
      return false;
    }
    return true; // All tests passed
  }

  /**
   * Performs fuzz testing on PartitionCalculator.numOfPartitions by testing it with randomly generated values for N.
   * This also checks for any exceptions that may arise.
   * 
   * @return true if all test cases pass, false if any test fails
   */
  public static boolean testNumOfPartitionsFuzz() {
    int testCount = 100 + (int) (Math.random() * 100); // Generate random test count between 100 and 199

    for (int i = 0; i < testCount; i++) {
      try {
        int N = 1 + (int) (Math.random() * 50); // Random N between 1 and 50
        // Compare the calculated number of partitions to the expected result
        if (PartitionCalculator.numOfPartitions(N) != TesterUtility.getPartitionCount(N)) {
          return false;
        }
      } catch (Exception e) {
        // If an error occurs, fail the test
        return false;
      }
    }
    return true; // If all iterations pass, return true
  }

  /**
   * Verifies the correctness of the PartitionCalculator.calculatePartitions method for the base cases (n = 1 and 2).
   * Compares the generated partition lists with expected outputs.
   * 
   * @return true if the test passes, false if an issue is found
   */
  public static boolean testCalcPartitionsBase() {
    try {
      // Check partition calculation for n = 1
      ArrayList<Partition> result1 = PartitionCalculator.calculatePartitions(1);
      if (!TesterUtility.comparePartitionLists(result1, TesterUtility.getPartitions(1, false), false)) {
        return false;
      }

      // Check partition calculation for n = 2
      ArrayList<Partition> result2 = PartitionCalculator.calculatePartitions(2);
      if (!TesterUtility.comparePartitionLists(result2, TesterUtility.getPartitions(2, false), false)) {
        return false;
      }
    } catch (Exception e) {
      // If an exception occurs, return false
      return false;
    }
    return true; // Success if no issues found
  }

  /**
   * Tests PartitionCalculator.calculatePartitions for larger values of n (n >= 3).
   * This checks if the method can handle recursion correctly and computes the expected partitions.
   * 
   * @return true if all tests pass, false if any issue is encountered
   */
  public static boolean testCalcPartitionsRecursive() {
    try {
      // Test case for n = 3
      ArrayList<Partition> result3 = PartitionCalculator.calculatePartitions(3);
      if (!TesterUtility.comparePartitionLists(result3, TesterUtility.getPartitions(3, false), false)) {
        return false;
      }

      // Test case for n = 6
      ArrayList<Partition> result6 = PartitionCalculator.calculatePartitions(6);
      if (!TesterUtility.comparePartitionLists(result6, TesterUtility.getPartitions(6, false), false)) {
        return false;
      }

      // Test case for n = 20
      ArrayList<Partition> result20 = PartitionCalculator.calculatePartitions(20);
      if (!TesterUtility.comparePartitionLists(result20, TesterUtility.getPartitions(20, false), false)) {
        return false;
      }
    } catch (Exception e) {
      // Return false if any exception occurs
      return false;
    }
    return true; // All tests passed
  }

  /**
   * Performs fuzz testing on the PartitionCalculator.calculatePartitions method with random inputs for N.
   * 
   * @return true if no errors occur during all test cases, false otherwise
   */
  public static boolean testCalcPartitionsFuzz() {
    int testCount = 1 + (int) (Math.random() * 20); // Generate a random number of tests (1 to 20)

    for (int i = 0; i < testCount; i++) {
      int N = 1 + (int) (Math.random() * 20); // Generate a random N between 1 and 20
      try {
        // Calculate partitions for the generated N and compare with expected result
        ArrayList<Partition> partitionsN = PartitionCalculator.calculatePartitions(N);
        if (!TesterUtility.comparePartitionLists(partitionsN, TesterUtility.getPartitions(N, false), false)) {
          return false;
        }
      } catch (Exception e) {
        // If an exception occurs, fail the test
        return false;
      }
    }
    return true; // All tests passed
  }

  /**
   * Tests the correctness of PartitionCalculator.calculateAllPermutations method for n = 1 and n = 2.
   * Compares the list of permutations with expected results.
   * 
   * @return true if the test passes, false if it fails
   */
  public static boolean testCalculateAllPermutationsBase() {
    try {
      // Check permutations for n = 1
      ArrayList<Partition> partitions1 = PartitionCalculator.calculatePartitions(1);
      ArrayList<Partition> permutations1 = PartitionCalculator.calculateAllPermutations(partitions1);
      if (!TesterUtility.comparePartitionLists(permutations1, TesterUtility.getPartitions(1, true), true)) {
        return false;
      }

      // Check permutations for n = 2
      ArrayList<Partition> partitions2 = PartitionCalculator.calculatePartitions(2);
      ArrayList<Partition> permutations2 = PartitionCalculator.calculateAllPermutations(partitions2);
      if (!TesterUtility.comparePartitionLists(permutations2, TesterUtility.getPartitions(2, true), true)) {
        return false;
      }
    } catch (Exception e) {
      return false; // If an exception occurs, fail the test
    }
    return true; // Success if no issues encountered
  }

  /**
   * Verifies that PartitionCalculator.calculateAllPermutations works for larger values of n (n >= 3).
   * 
   * @return true if all tests pass, false if any test fails
   */
  public static boolean testCalculateAllPermutationsRecursive() {
    try {
      // Test case for n = 3
      ArrayList<Partition> partitions3 = PartitionCalculator.calculatePartitions(3);
      ArrayList<Partition> permutations3 = PartitionCalculator.calculateAllPermutations(partitions3);
      if (!TesterUtility.comparePartitionLists(permutations3, TesterUtility.getPartitions(3, true), true)) {
        return false;
      }

      // Test case for n = 4
      ArrayList<Partition> partitions4 = PartitionCalculator.calculatePartitions(4);
      ArrayList<Partition> permutations4 = PartitionCalculator.calculateAllPermutations(partitions4);
      if (!TesterUtility.comparePartitionLists(permutations4, TesterUtility.getPartitions(4, true), true)) {
        return false;
      }

      // Test case for n = 5
      ArrayList<Partition> partitions5 = PartitionCalculator.calculatePartitions(5);
      ArrayList<Partition> permutations5 = PartitionCalculator.calculateAllPermutations(partitions5);
      if (!TesterUtility.comparePartitionLists(permutations5, TesterUtility.getPartitions(5, true), true)) {
        return false;
      }
    } catch (Exception e) {
      return false; // If an exception occurs, return false
    }
    return true; // Success if no issues found
  }

  /**
   * Executes all test methods and prints the results.
   * 
   * @return true if all tests pass, false if any test fails
   */
  public static boolean runAllTests() {
    boolean test1 = testNumOfPartitionsBase();
    System.out.println("testNumOfPartitionsBase(): " + (test1 ? "PASS" : "FAIL"));

    boolean test2 = testNumOfPartitionsRecursive();
    System.out.println("testNumOfPartitionsRecursive(): " + (test2 ? "PASS" : "FAIL"));

    boolean test3 = testCalcPartitionsBase();
    System.out.println("testUniquePartitionsBase(): " + (test3 ? "PASS" : "FAIL"));

    boolean test4 = testCalcPartitionsRecursive();
    System.out.println("testUniquePartitionsRecursive(): " + (test4 ? "PASS" : "FAIL"));

    boolean test5 = testCalculateAllPermutationsBase();
    System.out.println("testCalculateAllPermutationsBase(): " + (test5 ? "PASS" : "FAIL"));

    boolean test6 = testCalculateAllPermutationsRecursive();
    System.out.println("testCalculateAllPermutationsRecursive(): " + (test6 ? "PASS" : "FAIL"));

    boolean test7 = testNumOfPartitionsFuzz();
    System.out.println("testNumOfPartitionsFuzz(): " + (test7 ? "PASS" : "FAIL"));

    boolean test8 = testCalcPartitionsFuzz();
    System.out.println("testUniquePartitionsFuzz(): " + (test8 ? "PASS" : "FAIL"));

    return test1 && test2 && test3 && test4 && test5 && test6 && test7 && test8;
  }

  public static void main(String[] args) {
    System.out.println("runAllTest(): " + (runAllTests() ? "PASS" : "FAIL"));
  }
}
