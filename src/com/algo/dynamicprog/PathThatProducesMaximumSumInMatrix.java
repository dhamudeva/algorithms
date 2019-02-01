package com.algo.dynamicprog;

import java.util.LinkedList;
import java.util.List;

/**
 * Given a matrix, find the path that gives maximum sum.
 * From each index, you can either go right or down.
 * 
 * Method 1:
 * Similar to the problem described below:
 * 
 * Given a matrix with each cell containing each number of candies, 
 * and a constraint that you can move only right or down, from the top 
 * left corner to the bottom right corner, find the path that gets you 
 * maximum candies.
 * 
 * Matrix 1
 * 
 *  5 6 3
 *  4 8 7
 *  
 *  can be viewed as (tree) 
 *  
 *           5
 *         /   \
 *        /     \
 *       4       6
 *        \      / \
 *         8    8   3
 *          \    \   \
 *           7    7   7
 *           
 *           OR
 *           
 * can be viewed as (graph)
 * 
 *           5
 *         /  \
 *        4    6
 *         \  / \
 *           8   3
 *            \  /
 *              7 
 *           
 *           
 *  Now just find the path that produces maximum sum.
 *  Ans: 5 6 8 7 = 26
 *   
 * Matrix 2
 * 
 * 10  2 100  5  6
 *  9  5   8  9  9
 * 11 12  40 90 77
 *  9  7   6  7  9
 *  3  4   1  3  9
 * 
 * Method 1:
 * - Consider it as a graph
 * - Initialize currMaximumSum matrix of same size to hold current
 * maximum sum at that index
 * - Initialize path matrix of same size to hold which direction
 * to go to get the maximum sum (since only two way is possible, boolean
 * matrix is good enough. 0 mean it came from left node, 1 means it came from
 * top node)
 * - Do BFS and construct these matrix with data
 * - Then from the path matrix, construct the path starting from 
 * right bottom corner index (n-1, m-1) to top left corner index. (0, 0)
 * 
 * Method 2: (This does not returnt the path and works for NxN matrix)
 * 
 * - Consider it as dynamic programming
 * 
 * 
 * 
 * @author dhamudeva
 *
 */

public class PathThatProducesMaximumSumInMatrix {

  //Maximum number of rows and/or columns
  private static final int M = 100;
  
  public static void main(String[] argv) {
    
    /*int[][] inputMatrix = { {10, 2, 100},
                            {7, 9, 2},
                            {9, 5, 6}};*/
   /* 
    int[][] inputMatrix = { {10, 2, 100, 5, 6},
        {9, 5, 8, 9, 9},
        {11, 12, 40, 90, 77},
        {9, 7, 6, 7, 9},
        {3, 4, 1, 3, 9}};*/
    
    int[][] inputMatrix = { {1, 2, 3}, 
        {4, 5, 6},
        {7, 8, 9}
    };
    
    List<Node> path = findPathThatProducesMaximumSum(inputMatrix);
    
    int sum = 0;
    for (Node node : path) {
      System.out.println("row: " + node.row + " column: " + node.column);
      sum += node.data;
    }
    System.out.println("Maximum Sum: " + sum);
  }
  
  private static List<Node> findPathThatProducesMaximumSum(
      int[][] inputMatrix) {
    
    LinkedList<Node> queue = new LinkedList<>();
    int rowSize = inputMatrix.length;
    int columSize = inputMatrix[0].length;
    SumAndFrom[][] sumAndFroms = new SumAndFrom[rowSize][columSize];
    
    // Initialize SumAndFroms
    for (int i = 0; i < rowSize; i++) {
      for (int j = 0; j < columSize; j++) {
        sumAndFroms[i][j] = new SumAndFrom();
      }
    }
    
    // Add the first index to queue
    queue.addLast(new Node(0, 0, inputMatrix[0][0], -1));
    
    // Start the BFS and end it when queue is empty
    while (!queue.isEmpty()) {
      Node currentNode = queue.removeFirst();
      
      int existingSum = sumAndFroms[currentNode.row][currentNode.column].sum;
      int newSum;
      
      // Find out the new sum
      if (currentNode.from == 0) { // From left node, so reduce column
        newSum = sumAndFroms[currentNode.row][currentNode.column - 1].sum 
            + currentNode.data;
      } else if (currentNode.from == 1) { // From right node, so reduce row
        newSum = sumAndFroms[currentNode.row - 1][currentNode.column].sum
            + currentNode.data;
      } else { // First Node (0,0)
        newSum = 0;
      }
      
      // Validate and adjust the sum and from
      if (newSum > existingSum) {
        sumAndFroms[currentNode.row][currentNode.column].sum = newSum;
        sumAndFroms[currentNode.row][currentNode.column].from = currentNode.from;
      }
      
      Node node = getRightNode(currentNode.row, currentNode.column, inputMatrix);
      if (node != null) {
        queue.addLast(node);
      }
      
      node = getBottomNode(currentNode.row, currentNode.column, inputMatrix);
      if (node != null) {
        queue.addLast(node);
      }
    }
    
    // Let's find the path
    int row = sumAndFroms.length - 1;
    int column = sumAndFroms[0].length - 1;
    LinkedList<Node> path = new LinkedList<>();
    
    // Start from the (n-1, m-1) when it reaches beyond (0,0)
    while (row >= 0 && column >= 0) {
      path.push(new Node(row, column, inputMatrix[row][column], 0));
      
      if (sumAndFroms[row][column].from == 1) {
        row = row - 1;
      } else {
        column = column - 1;
      }
    }
    
    return path;
  }
  
  private static Node getRightNode(int row, int column, int[][] inputMatrix) {
    int columnSize = inputMatrix[0].length;
    if (column == columnSize - 1) {
      return null;
    }
    return new Node(row, column + 1, inputMatrix[row][column+1], 0);
  }
  
  private static Node getBottomNode(int row, int column, int[][] inputMatrix) {
    int rowSize = inputMatrix.length;
    if (row == rowSize - 1) {
      return null;
    }
    return new Node(row + 1, column, inputMatrix[row+1][column], 1);
  }
  
  private static class Node {
    private int row;
    private int column;
    private int data;
    private int from; // 0 means came from left,  1 means came from top
    
    Node(int r, int c, int d, int f) {
      row = r;
      column = c;
      data = d;
      from = f;
    }
  }
  
  private static class SumAndFrom {
    private int sum;
    private int from;
    
    SumAndFrom() {
      sum = 0;
      from = -1;
    }
  }
  
  //method returns maximum average of all path of
  //cost matrix
  double maxAverageOfPath(int[][] inputMatrix) {
    int lengthOfMatrix = inputMatrix.length;
    int[][] currentMaxSum = new int[lengthOfMatrix+1][lengthOfMatrix+1];
    currentMaxSum[0][0] = inputMatrix[0][0];
   
    /* Initialize first column of total cost(currentMaxSum) array */
    for (int i = 1; i < lengthOfMatrix; i++) {
      currentMaxSum[i][0] = currentMaxSum[i-1][0] + inputMatrix[i][0];
    }
   
    /* Initialize first row of currentMaxSum array */
    for (int j = 1; j < lengthOfMatrix; j++) {
      currentMaxSum[0][j] = currentMaxSum[0][j-1] + inputMatrix[0][j];
    }
   
    /* Construct rest of the currentMaxSum array */
    for (int i = 1; i < lengthOfMatrix; i++) {
      for (int j = 1; j <= lengthOfMatrix; j++) {
        currentMaxSum[i][j] = Math.max(currentMaxSum[i-1][j], 
            currentMaxSum[i][j-1]) + inputMatrix[i][j];
      }
    }
    
    // divide maximum sum by constant path
    // length : (2N - 1) for getting average
    return (double) currentMaxSum[lengthOfMatrix-1][lengthOfMatrix-1] /
        (2*lengthOfMatrix-1);
   }
}





