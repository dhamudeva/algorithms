package com.algo.dynamicprog;

/**
 * Given integer array, find a contagious sub array that produces maximum sum
 * 
 * Simple idea of the Kadane's algorithm is to look for all positive 
 * contiguous segments of the array (prevSum is used for this). 
 * And keep track of maximum sum contiguous segment among all
 * positive segments (currSum is used for this). Each time we get 
 * a positive sum compare it with prevSum and update prevSum if it is greater 
 * than prevSum
 * 
 * This does not work from all negative values. You could either
 * return zero or find if array has all negatives and try to return
 * the smallest negative number
 * 
 * @author dhamudeva
 *
 */
public class FindMaximumContagiousSubArray {
  
  public static void main(String[] argv) {
    
    /*int[] inputArray = {1, -1, 2, -2, 5, -2, 5 };*/
    
    int[] inputArray = {-2, -3, -4, -5};
    
    /*int[] inputArray = {-2, -3, 4, -1, -2, 1, 5, -3};*/
    
    int start = 0;
    int end = 0;
    int currSum = 0;
    int prevSum = 0;
    
    for (int i = 0; i < inputArray.length; i++) {
      currSum += inputArray[i];
      
      if (currSum <= 0) {
        start = i+1;
        end = i+1;
        currSum = 0;
        prevSum = 0;
      } else if (prevSum < currSum) {
        end = i;
        prevSum = currSum;
      }
    }
    
    System.out.println("Maximum sum: " + prevSum);
    if (currSum != 0) {
      System.out.println("Values are:");
      for (int i = start; i <= end; i++) {
        System.out.print(inputArray[i] + " ");
      }
      System.out.println();
    }
  }
}
