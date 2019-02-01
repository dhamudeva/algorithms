package com.algo.list;

/**
 * Find if there is a loop in linked list and find the node
 * that causes looping.
 * 
 * Method 1: (Hashing)
 * - Parse the list, add node's address to a set
 * - If you encounter a node that is already in the set, then
 * the list has loop
 * - And the node that is found in the set is the node causing
 * the loop.
 * 
 * Method 2: (brute force) 
 * - Parse the same list with two inner for loops
 * 
 * Method 3: 
 * - Parse the list and mark the node as visited
 * (or a boolean array with same size as the list)
 * - Stop when boolen array has true in a node's place
 * 
 * Method 4: (Two pointers) (Tortise and Hare algorithm)
 * - Have two pointers, one is parsing step by step and another 
 * parsing two steps at a time.
 * - If two pointers meet, then there is loop
 * 
 * Method 5: (
 * Richard Brent described an alternative cycle detection algorithm, 
 * which is pretty much like the hare and the tortoise [Floyd's cycle] 
 * except that, the slow node here doesn't move, but is later "teleported" 
 * to the position of the fast node at fixed intervals.
 * 
 * The description is available here : http://www.siafoo.net/algorithm/11 
 * Brent claims that his algorithm is 24 to 36 % faster than the 
 * Floyd's cycle algorithm. O(n) time complexity, O(1) space complexity.

  public static boolean hasLoop(Node root){
      if(root == null) return false;
  
      Node slow = root, fast = root;
      int taken = 0, limit = 2;
  
      while (fast.next != null) {
          fast = fast.next;
          taken++;
          if(slow == fast) return true;
  
          if(taken == limit){
              taken = 0;
              limit <<= 1;    // equivalent to limit *= 2;
              slow = fast;    // teleporting the turtle (to the hare's position) 
          }
      }
      return false;
  }
 *
 * @author dhamudeva
 *
 */

public class LoopInList {

}
