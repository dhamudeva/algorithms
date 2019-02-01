package com.algo.list;

/**
 * Intersection of two lists. 
 * 
 * There are various methods to find the intersection of two linked lists
 * 
 * Method 1:
 * - Reverse both lists
 * - Parse from the beginning of both lists simultaneously.
 * - Compare node's addresses from both lists. 
 * - The beginning nodes will be identical if lists had intersecting node 
 * - Stop when node's addresses start to differ
 * 
 * Method 2: (This changes the node's content)
 * - Parse the first list and add 'visited' to each node
 * - Parse the second list and find if any node has visited is
 * set to true, then that is the intersecting node
 * 
 * Method 3:
 * - Parse the first list and add the address into a set
 * - Parse the second list and check if the set has any of the
 * node's address in it
 * 
 * Method 4: 
 * - Find the longest list out of two lists 
 * - Get the absolute diff of length between two lists
 * - Parse the longest list only by diff nodes
 * - Then start parsing both lists and compare node's address
 * - The first node that matches is the intersecting node
 * 
 *  Method 5:
 *  - Attach one list to other at the end of the first list
 *  - If lists had intersecting node then there is a loop in 
 *  the new joined list.
 *  - Now find the node that is causing the loop, that is the 
 *  intersecting node.
 *  
 *  Mehod 6: (brute force) - O(mn) - O(n^2)
 *  - Simply have two for loops (one outer and one inner)
 *  - Keep comparing the node's address
 *
 * Method 7: (Not sure of this, but found it in internet)
 * - Let X be the length of the first linked list until intersection point.
 * - Let Y be the length of the second linked list until the intersection point.
 * - Let Z be the length of the linked list from intersection point to End of
 *  the linked list including the intersection node.
 * - We Have
 *          X + Z = C1;
 *          Y + Z = C2;
 * - Reverse first linked list.
 * - Traverse Second linked list. Let C3 be the length of second list - 1. 
 *    Now we have
 *      X + Y = C3
 *    We have 3 linear equations. By solving them, we get
 *      X = (C1 + C3 – C2)/2;
 *      Y = (C2 + C3 – C1)/2;
 *      Z = (C1 + C2 – C3)/2;
 *     WE GOT THE INTERSECTION POINT.
 * - Reverse first linked list back.
 * 
 * Method 8: (Only find if there is a intersecting node)
 * - Parse list 1
 * - Parse list 2
 * - Compare the last node's address
 * - If they are equal, then the lists have intersecting node
 *
 * @author dhamudeva
 */
public class IntersectionOfTwoLists {
  
  public static void main(String[] argv) {
    ArrayList list1 = new ArrayList();
    
    list1.add(1);
    list1.add(2);
    list1.add(3);
    list1.add(4);
    
    list1.print();
    
    ArrayList list2 = new ArrayList();
    
    list2.add(8);
    list2.add(7);
    list2.add(6);
    list2.add(5);
    list2.add(1);
    list2.add(2);
    list2.add(3);
    list2.add(4);
    
    list2.print();

    System.out.println("Intersection of two lists: " 
        + list1.findIntersection(list2));
  }
}
