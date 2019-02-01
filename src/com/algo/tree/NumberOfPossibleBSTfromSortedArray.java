package com.algo.tree;

/**
 * 
 * This is basically catalan number.
 * http://stackoverflow.com/questions/1352776/the-possible-number-of-
 * binary-search-trees-that-can-be-created-with-n-keys-is-gi
 * 
 * http://stackoverflow.com/questions/11724226/given-a-sorted-integer-
 * array-how-may-binary-search-trees-can-be-formed-from-it
 * 
 * 
 * Here is a systematic way to enumerate these BSTs. 
 * Consider all possible binary search trees with each element at the root. 
 * If there are n nodes, then for each choice of root node, 
 * there are n – 1 non-root nodes and these non-root nodes must be
 * partitioned into those that are less than a chosen root and those that are 
 * greater than the chosen root.
 * 
 * Let’s say node i is chosen to be the root. 
 * Then there are i – 1 nodes smaller than i and n – i nodes bigger
 * than i. For each of these two sets of nodes, there is a 
 * certain number of possible subtrees.
 * 
 * Let t(n) be the total number of BSTs with n nodes. 
 * The total number of BSTs with i at the root is t(i – 1) t(n – i). 
 * The two terms are multiplied together because the arrangements in the 
 * left and right subtrees are independent. That is, for each arrangement 
 * in the left tree and for each arrangement in the right tree, you get one
 * BST with i at the root.
 * 
 * Summing over i gives the total number of binary search trees with n nodes.
 * 
 * The base case is t(0) = 1 and t(1) = 1, i.e. 
 * there is one empty BST and there is one BST with one node.
 * 
 * http://www.geeksforgeeks.org/g-fact-18/
 * 
 * @author dhamudeva
 *
 */
public class NumberOfPossibleBSTfromSortedArray {
  

  public static void main(String[] argv) {
  
    System.out.println(countTrees(1));
    System.out.println(countTrees(2));
    System.out.println(countTrees(3));
    System.out.println(countTrees(4));
    System.out.println(countTrees(5));
  }
  
  private static int countTrees(int numkeys) {

    if (numkeys > 1) { 
        int i = 1;
        int sum = 0;

        for (i = 1; i <= numkeys; i++)  {

            int lcount = countTrees(i-1);
            int rcount = countTrees(numkeys-i);
            sum += lcount*rcount;
        }
        return sum;
        
    } else {
        return 1;
    }
  }
}