package com.algo.graph;

import java.util.ArrayList;

public abstract class Graph {

  private ArrayList<Node> graph;
  
  protected Graph(int numberOfNodes) {
    graph = new ArrayList<Node>(numberOfNodes);
    for (int i = 0; i < numberOfNodes; i++) {
      graph.add(new Node());
    }
  }

  public abstract boolean hasPath(int start, int dest);

  public abstract ArrayList<Integer> breadthFirstSearch();

  public abstract ArrayList<Integer> postOrderDepthFirstSearch();

  public abstract ArrayList<Integer> toplogicalSort();

  public abstract boolean isCyclic();

  public abstract Graph reverse();

  public abstract void computeConnectedComponents(String inputOrder);

  protected ArrayList<Integer> getAdjacentNodes(int nodeNumber) {
    if (nodeNumber < 0 || nodeNumber > graph.size()) {
      throw new IllegalArgumentException("Incorrect node number");
    }
    return graph.get(nodeNumber).getAdjacentNodes();
  }

  protected int getNumberOfNodes() {
    return graph.size();
  }

  protected void createEdge(int left, int right) {
    graph.get(left).getAdjacentNodes().add(right);
  }

  protected boolean isMarked(int nodeNumber) {
    return graph.get(nodeNumber).isMarked();
  }

  protected void setMarked(int nodeNumber, boolean marked) {
    graph.get(nodeNumber).setMarked(marked);
  }

  protected void setLabel(int nodeNumber, int label) {
    graph.get(nodeNumber).setLabel(label);
  }

  protected void unMarkAllNodes() {
    for (Node node : graph) {
      node.setMarked(false);
    }
  }

  protected void clearLabels() {
    for (Node node : graph) {
      node.setLabel(-1);
    }
  }

  /**
   * Print the graph.
   */
  public void print() {
    System.out.println("Number of nodes: " + getNumberOfNodes());
    for (int nodeNumber = 0; nodeNumber < getNumberOfNodes(); nodeNumber++) {
      System.out.print(nodeNumber + " " + (char) (nodeNumber + 'A') + "("
          + graph.get(nodeNumber).getLabel() + ")" + ": ");
      for (int adjancentNode : getAdjacentNodes(nodeNumber)) {
        System.out.print((char) (adjancentNode + 'A') + " ");
      }
      System.out.println();
    }
  }

  final class Node {
    private boolean marked;
    private int label;
    private ArrayList<Integer> adjacentNodes;

    private Node() {
      setMarked(false);
      adjacentNodes = new ArrayList<Integer>();
    }

    private boolean isMarked() {
      return marked;
    }

    private void setMarked(boolean marked) {
      this.marked = marked;
    }

    private ArrayList<Integer> getAdjacentNodes() {
      return adjacentNodes;
    }

    public int getLabel() {
      return label;
    }

    public void setLabel(int label) {
      this.label = label;
    }
  }
}
