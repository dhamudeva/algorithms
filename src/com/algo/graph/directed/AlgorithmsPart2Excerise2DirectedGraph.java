package com.algo.graph.directed;

import com.algo.graph.Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class AlgorithmsPart2Excerise2DirectedGraph {

  /**
   * Main.
   * @param args Arguments for main.
   * @throws IOException IOException.
   */
  public static void main(String[] args) throws IOException {
    
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    System.out.println("Enter number of nodes:");
    final int numberOfNodes = Integer.parseInt(reader.readLine());
    System.out.println("Enter number of edges:");
    final int numberOfEdges = Integer.parseInt(reader.readLine());
    
    ArrayList<String> edges = new ArrayList<String>(numberOfEdges);
    
    System.out.println("Enter edges one by one separated by space");
    for (int i = 0; i < numberOfEdges; i++) {
      edges.add(reader.readLine());
    }
    
    Graph directedGraph = DirectedGraph.getDirectedGraph(numberOfNodes, edges);
    
    directedGraph.print();
    
    ArrayList<Integer> results = directedGraph.breadthFirstSearch();
    System.out.print("Breadth First Search Order: ");
    printResults(results);
    
    results = directedGraph.toplogicalSort();
    System.out.print("Toplogical Sort: ");
    printResults(results);
    
    Graph reverseGraph = directedGraph.reverse();
    System.out.print("Reversed Graph: ");
    reverseGraph.print();
    
    results = reverseGraph.toplogicalSort();
    System.out.print("Reverse Post Order DFS of Reversed Graph: ");
    printResults(results);
    
    System.out.println("Enter DFS order for computing connected components");
    directedGraph.computeConnectedComponents(reader.readLine());
    directedGraph.print();
  }

  private static void printResults(ArrayList<Integer> results) {
    for (int result : results) {
      System.out.print((char) (result + 'A') + " ");
    }
    System.out.println();
  }
}
