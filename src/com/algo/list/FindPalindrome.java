package com.algo.list;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Given a singly linked list, find out if it is a palindrome, 
 * also asked to write code for this and then read out the code.
 * 
 * What are the properties of data and what data do you need to
 * solve the problem
 * 
 * - It is a linked list and it can go only one direction
 * 
 * Palindrome properties:
 * - Reverse the original value and you get the same value
 * 
 * Method 1: 
 * - Reverse the list
 * - Traverse both lists (original and reversed list) simultaneously
 * - When node differs, stop the traversal because it is not palindrome
 * 
 * 
 * @author dhamudeva
 */
public class FindPalindrome {
  
  public static void main(String[] argv) {
    
    List<String> inputList = new ArrayList<>();
    inputList.add("m");
    inputList.add("a");
    inputList.add("d");
    inputList.add("b");
    inputList.add("a");
    inputList.add("m");
    
    List<String> reversedList = new ArrayList<>(inputList);
    
    Collections.reverse(reversedList);
    
    System.out.println("currentList " + reversedList.toString());
    System.out.println("inputList " + inputList.toString());
    
    Iterator<String> iterator1 = reversedList.iterator();
    Iterator<String> iterator2 = inputList.iterator();
    
    boolean palindrome = true;
    while (iterator1.hasNext()) {
      String str1 = iterator1.next();
      String str2 = iterator2.next();
      
      if (!str1.equals(str2)) {
        palindrome = false;
        break;
      }
    }
    
    if (!palindrome) {
      System.out.println("Not a palindrome");
    } else {
      System.out.println("List is palindrome");
    }
  }
}
