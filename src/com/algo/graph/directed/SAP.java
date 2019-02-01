package com.algo.graph.directed;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Shortest Ancenstral Path. 
 * @author dhamod
 */
public class SAP {
  private Digraph digraph;
  private Map<Vertices, DistanceAndAncestor> verticesToDistanceAndAncestor;
  
  /**
   * Constructor for SAP.
   * @param inputGraph Directed Graph
   */
  public SAP(Digraph inputGraph) {
    if (inputGraph == null) {
      throw new NullPointerException();
    }
    digraph = new Digraph(inputGraph);
    verticesToDistanceAndAncestor = new HashMap<Vertices, DistanceAndAncestor>();
  }
  
  /**
   * Return length of shortest ancestral path.
   * @param v vertex
   * @param w another vertex
   * @return number
   */
  public int length(int v, int w) {
    if (v < 0 || v > (digraph.V() - 1) || w < 0 || w > (digraph.V() - 1)) {
      throw new IndexOutOfBoundsException();
    }

    DistanceAndAncestor distanceAndAncestor = computeBestDistanceAndAncestor(v, w);
    return distanceAndAncestor.bestDistance;
  }
  
  /**
   * Return best length between any two vertices.
   * @param v vertex1 
   * @param w vertex2
   * @return best length
   */
  public int length(Iterable<Integer> v, Iterable<Integer> w) {
    if (v == null || w == null) {
      throw new NullPointerException();
    }

    int bestLengthSofar = Integer.MAX_VALUE;
    int currentLength;
    
    for (int vertex1 : v) {
      for (int vertex2 : w) {
        if (vertex1 < 0 || vertex1 > (digraph.V() - 1) 
            || vertex2 < 0 || vertex2 > (digraph.V() - 1)) {
          throw new IndexOutOfBoundsException();
        }

        currentLength = length(vertex1, vertex2);
        if (currentLength != -1 && currentLength < bestLengthSofar) {
          bestLengthSofar = currentLength;
        }
      }
    }
    if (bestLengthSofar == Integer.MAX_VALUE) {
      return -1;
    } else {
      return bestLengthSofar;
    }
  }

  /**
   * Return ancestor of shortest ancestral path.
   * @param v vertex
   * @param w another vertex
   * @return number
   */
  public int ancestor(int v, int w) {
    if (v < 0 || v > (digraph.V() - 1) || w < 0 || w > (digraph.V() - 1)) {
      throw new IndexOutOfBoundsException();
    }

    DistanceAndAncestor distanceAndAncestor = computeBestDistanceAndAncestor(v, w);
    return distanceAndAncestor.bestAncestor;
  }
  
  /**
   * Best ancestor between any vertex in v and any vertex in w.
   * @param v vertex1
   * @param w vertex2
   * @return best ancestor
   */
  public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
    if (v == null || w == null) {
      throw new NullPointerException();
    }
    
    int bestAncestorSofar = -1;
    int bestDistanceSofar = Integer.MAX_VALUE;
    
    for (int vertex1 : v) {
      for (int vertex2 : w) {
        if (vertex1 < 0 || vertex1 > (digraph.V() - 1) 
            || vertex2 < 0 || vertex2 > (digraph.V() - 1)) {
          throw new IndexOutOfBoundsException();
        }

        DistanceAndAncestor currentDistanceAndAncestor = 
            computeBestDistanceAndAncestor(vertex1, vertex2);
        if (currentDistanceAndAncestor.bestDistance != -1 && 
            currentDistanceAndAncestor.bestDistance < bestDistanceSofar) {
          bestDistanceSofar = currentDistanceAndAncestor.bestDistance;
          bestAncestorSofar = currentDistanceAndAncestor.bestAncestor;
        }
      }
    }
    return bestAncestorSofar;
  }

  private void bfs(int vertex, ArrayList<Integer> ancestor, 
      ArrayList<Integer> distance) {
    
    boolean[] marked = new boolean[digraph.V()];
    
    LinkedList<Integer> bfsQueue = new LinkedList<Integer>();
    
    bfsQueue.addLast(vertex);
    ancestor.add(vertex);
    distance.add(0);
     
    while (!bfsQueue.isEmpty()) {
      int currentNode = bfsQueue.removeFirst();

      if (!marked[currentNode]) {
        marked[currentNode] = true;
    
        int index = 0;
        while (!ancestor.get(index).equals(currentNode)) {
          index++;
        }
        int currentDistance = distance.get(index) + 1;
        
        for (int adjacentNode : digraph.adj(currentNode)) {
          if (!marked[adjacentNode]) {
            bfsQueue.addLast(adjacentNode);
            distance.add(currentDistance);
            ancestor.add(adjacentNode);
          }
        }
      }
    }
  }
  
  private class DistanceAndAncestor {
    private int bestDistance;
    private int bestAncestor;
  
    DistanceAndAncestor(int distance, int ancestor) {
      bestDistance = distance;
      bestAncestor = ancestor;
    }
  }
  
  private DistanceAndAncestor computeBestDistanceAndAncestor(int vertex1, 
      int vertex2) {
    Vertices vertices = new Vertices(vertex1, vertex2);
    Vertices reverserdVertices = new Vertices(vertex2, vertex1);
    
    DistanceAndAncestor bestDistanceAndAncestor = 
        verticesToDistanceAndAncestor.get(vertices);
    
    if (bestDistanceAndAncestor == null) {
      bestDistanceAndAncestor = verticesToDistanceAndAncestor.get(reverserdVertices);
      if (bestDistanceAndAncestor == null) {
        bestDistanceAndAncestor = doBfsAndComputeBestDisanceAndAncestor(vertices);
        verticesToDistanceAndAncestor.put(vertices, bestDistanceAndAncestor);
        verticesToDistanceAndAncestor.put(reverserdVertices, 
            bestDistanceAndAncestor);
      }
    }
    
    return bestDistanceAndAncestor;
  }
  
  private DistanceAndAncestor doBfsAndComputeBestDisanceAndAncestor(
      Vertices vertices) {
    ArrayList<Integer> distanceForV = new ArrayList<>();
    ArrayList<Integer> ancestorForV = new ArrayList<>();
    ArrayList<Integer> distanceForW = new ArrayList<>();
    ArrayList<Integer> ancestorForW = new ArrayList<>();
    
    int vertex1 = vertices.vertex1;
    int vertex2 = vertices.vertex2;
    
    bfs(vertex1, ancestorForV, distanceForV);
    bfs(vertex2, ancestorForW, distanceForW);
    
    int bestDistanceSofar = Integer.MAX_VALUE;
    int bestAncestorSofar = -1;
    int currentDistance = -1;
    for (int i = 0; i < ancestorForV.size(); i++) {
      for (int j = 0; j < ancestorForW.size(); j++) {
        if (ancestorForV.get(i).equals(ancestorForW.get(j))) {
          currentDistance = distanceForV.get(i) + distanceForW.get(j);
          if (currentDistance < bestDistanceSofar) {
            bestDistanceSofar = currentDistance;
            bestAncestorSofar = ancestorForV.get(i);
          }
        }
      }
    }
    
    if (bestDistanceSofar == Integer.MAX_VALUE) {
      bestDistanceSofar = -1;
    }
    
    return new DistanceAndAncestor(bestDistanceSofar, bestAncestorSofar);
  }
  
  private class Vertices {
    private int vertex1;
    private int vertex2;

    Vertices(int vertexOne, int vertexTwo) {
      vertex1 = vertexOne;
      vertex2 = vertexTwo;
    }
    
    public boolean equals(Object object) {
      if (object == null) {
        return false;
      }
      if (!(object.getClass().isAssignableFrom(Vertices.class))) {
        return false;
      }
      if (object == this) {
        return true;
      }
      Vertices anotherTwoNouns = (Vertices) object;
      return this.vertex1 == anotherTwoNouns.vertex1 
          && this.vertex2 == anotherTwoNouns.vertex2; 
    }
      
    public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + vertex1 + vertex2;
      return result;
    }
  }
  
  /**
   * Main.
   * @param args file.
   */
  public static void main(String[] args) {
    In in = new In(args[0]);
    Digraph G = new Digraph(in);
    SAP sap = new SAP(G);
    while (!StdIn.isEmpty()) {
      int v = StdIn.readInt();
      int w = StdIn.readInt();
      int length   = sap.length(v, w);
      int ancestor = sap.ancestor(v, w);
      StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);
    }
  }
}
