package com.algo.graph.directed;

import com.algo.graph.Graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

public class DirectedGraph extends Graph {

  private DirectedGraph(int numberOfNodes) {
    super(numberOfNodes);
  }
  
  /**
   * Create directed graph.
   * @param numberOfNodes Number of Nodes.
   * @param edges All the edges between nodes.
   */
  private DirectedGraph(int numberOfNodes, ArrayList<String> edges) {
    super(numberOfNodes);
    
    for (String edge : edges) {
      int left = edge.split(" ")[0].trim().charAt(0) - 'A';
      int right = edge.split(" ")[1].trim().charAt(0) - 'A';
      
      createEdge(left, right);
    }
  }

  public static Graph getDirectedGraph(int numberOfNodes) {
    Graph directedGraph = new DirectedGraph(numberOfNodes);
    return directedGraph;
  }
  
  public static Graph getDirectedGraph(int numberOfNodes, ArrayList<String> edges) {
    Graph directedGraph = new DirectedGraph(numberOfNodes, edges);
    return directedGraph;
  }
  
  @Override
  public boolean hasPath(int start, int dest) {
    // TODO Auto-generated method stub
    return false;
    
  }

  @Override
  public ArrayList<Integer> breadthFirstSearch() {
    LinkedList<Integer> bfsQueue = new LinkedList<Integer>();
    ArrayList<Integer> result = new ArrayList<Integer>();
    
    //First unmark all the nodes
    unMarkAllNodes();
    
    // This loop is to make sure all the nodes are visited. It could be possible
    // to have disjoint nodes as part of graph
    for (int nodeNumber = 0; nodeNumber < getNumberOfNodes(); nodeNumber++) {
      if (isMarked(nodeNumber)) {
        continue;
      }
      
      bfsQueue.addLast(nodeNumber);
      
      while (!bfsQueue.isEmpty()) {
        int currentNode = bfsQueue.removeFirst();

        if (!isMarked(currentNode)) {
          result.add(currentNode);
          setMarked(currentNode, true);
        
          for (int adjacentNode : getAdjacentNodes(currentNode)) {
            bfsQueue.addLast(adjacentNode);
          }
        }
      }
    }
    return result;
  }

  @Override
  public boolean isCyclic() {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public ArrayList<Integer> postOrderDepthFirstSearch() {
    
    ArrayList<Integer> postOrderResult = new ArrayList<Integer>(getNumberOfNodes());
    
    unMarkAllNodes();
    for (int nodeNumber = 0; nodeNumber < getNumberOfNodes(); nodeNumber++) {
      if (!isMarked(nodeNumber)) {
        performPostOrderDfs(nodeNumber, postOrderResult);
      }
    }
    
    return postOrderResult;
  }
  
  private void performPostOrderDfs(int startingNode, int label) {
    setMarked(startingNode, true);
    for (int adjacentNode : getAdjacentNodes(startingNode)) {
      if (!isMarked(adjacentNode)) {
        performPostOrderDfs(adjacentNode, label);
      }
    }
    setLabel(startingNode, label);
  }
  
  private ArrayList<Integer> performPostOrderDfs(int startingNode, 
      ArrayList<Integer> postOrderResult) {
    
    setMarked(startingNode, true);
    for (int adjacentNode : getAdjacentNodes(startingNode)) {
      if (!isMarked(adjacentNode)) {
        performPostOrderDfs(adjacentNode, postOrderResult);
      }
    }
    postOrderResult.add(startingNode);
    return postOrderResult;
  }

  @Override
  public Graph reverse() {
    ArrayList<String> edges = new ArrayList<String>();
    for (int nodeNumber = 0; nodeNumber < getNumberOfNodes(); nodeNumber++) {
      for (int adjacentNode : getAdjacentNodes(nodeNumber)) {
        edges.add((char) (adjacentNode + 'A') + " " + (char) (nodeNumber + 'A'));
      }
    }
    
    return new DirectedGraph(getNumberOfNodes(), edges);
  }
  
  @Override
  public ArrayList<Integer> toplogicalSort() {
    
    ArrayList<Integer> postOrderResult = new ArrayList<Integer>(getNumberOfNodes());
    unMarkAllNodes();

    for (int nodeNumber = 0; nodeNumber < getNumberOfNodes(); nodeNumber++) {
      if (!isMarked(nodeNumber)) {
        performPostOrderDfs(nodeNumber, postOrderResult);
      }
    }
    
    Collections.reverse(postOrderResult);
    return postOrderResult;
  }
  
  @Override
  public void computeConnectedComponents(String inputOrder) {
    
    ArrayList<Integer> orderOfDfs = new ArrayList<Integer>(getNumberOfNodes());
        
    if (inputOrder != null && !inputOrder.isEmpty()) {
      for (String str : inputOrder.split(" ")) {
        orderOfDfs.add((int) (str.trim().charAt(0)) - 'A');
      }
    }
    
    unMarkAllNodes();
    clearLabels();
    int label = 0;
    for (int nodeNumber : orderOfDfs) {
      if (!isMarked(nodeNumber)) {
        performPostOrderDfs(nodeNumber, label);
        label++;
      }
    }
  }
}
