package com.algo.tree;

/**
 * Given a binarytree, construct its mirror image in place
 * 
 * Method 1:
 * - Do post order traversal
 * - Once both node left and right are visited, swap the children
 * - Do this in recursion
 * 
 * @author dhamudeva
 *
 */
public class MirrageImageOfBinaryTree {
  
  private Node root;
  
  public MirrageImageOfBinaryTree() {
    root = new Node(1, null, null);
  }
  
  /**
   * Given a binary tree, construct its mirror image in place
   */
  public void mirrorImage() {
    postOrderTraversal(root);
  }
  
  private void postOrderTraversal(Node rootInput) {
    if (rootInput == null) {
      return;
    }
    
    postOrderTraversal(rootInput.left);
    postOrderTraversal(rootInput.right);
    
    swapChildren(rootInput);
  }
  
  private void swapChildren(Node rootInput) {
    Node tmp = rootInput.left;
    rootInput.left = rootInput.right;
    rootInput.right = tmp;
  }
  
  private class Node {
    private int data;
    private Node left;
    private Node right;
    
    Node(int dataInput, Node leftInput, Node rightInput) {
      data = dataInput;
      left = leftInput;
      right = rightInput;
    }
  }
  
}
