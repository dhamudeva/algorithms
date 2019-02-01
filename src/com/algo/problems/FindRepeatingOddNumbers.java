package com.algo.problems;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import com.google.common.collect.Multiset.Entry;

/**
 * You have an array of integers, such that each integer 
 * is present an odd number of time, except 3 of them. Find the three numbers.
 * 
 * Method 1:
 * - Sort the array first [ O(nlogn) ]
 * - Traverse the array from the beginning and start counting [ O(n) ]
 * - Stop when number differs
 * - Now check if the count is odd or even
 * - If it is even, add it to result set
 * 
 * Method 2:
 * - Traverse the array [ O(n) ]
 * - Add each number to a set with its seen count (reference count) 
 * [ Set is called MultiSet from google ]
 * - Traverse the set and find a number which is seen even times.
 * 
 * Method 3:
 * - Traverse the array
 * - Add each number to a map<integer, integer> (first integer is given integer,
 *  second is its count)
 * - While adding to map, if element already is in the map, increment the value
 * of the count and reinsert it.  
 * - Traverse the set and find a number which is seen even times.
 * 
 * @author dhamudeva
 *
 */
public class FindRepeatingOddNumbers {
  
  public static void main(String[] argv) {
    int[] inputArray = { 1, 2, 3, 1, 2, 3, 2, 2, 3, 3, 3, 3, 
        4, 4, 4, 5, 5, 6, 6, 7, 7, 7, 5, 6};
    
    Multiset<Integer> inputArrayWithCount = HashMultiset.create();
    
    // O(n)
    for (int element : inputArray) {
      inputArrayWithCount.add(element);
    }
    
    // O(n)
    for (Entry<Integer> element : inputArrayWithCount.entrySet()) {
      if (element.getCount() % 2 == 0) {
        System.out.println("Element which is found even times: " 
            + element.getElement());
      }
    }
  }
}
