package com.algo.graph.minimumspanningtree;

import java.util.ArrayList;
import java.util.List;

import edu.princeton.cs.algs4.DirectedEdge;
import edu.princeton.cs.algs4.EdgeWeightedDigraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Topological;

public class AcyclicDAGShortestPathForExcerise {
  
  private double[] distTo;         // distTo[v] = distance  of shortest s->v path
  private DirectedEdge[] edgeTo;   // edgeTo[v] = last edge on shortest s->v path


  /**
   * Computes a shortest paths tree from <tt>s</tt> to every other vertex in
   * the directed acyclic graph <tt>G</tt>.
   * @param G the acyclic digraph
   * @param s the source vertex
   * @throws IllegalArgumentException if the digraph is not acyclic
   * @throws IllegalArgumentException unless 0 &le; <tt>s</tt> &le; <tt>V</tt> - 1
   */
  public AcyclicDAGShortestPathForExcerise(EdgeWeightedDigraph G, int s) {
      distTo = new double[G.V()];
      edgeTo = new DirectedEdge[G.V()];
      for (int v = 0; v < G.V(); v++)
          distTo[v] = Double.POSITIVE_INFINITY;
      distTo[s] = 0.0;

      // visit vertices in toplogical order
      Topological topological = new Topological(G);
      if (!topological.hasOrder())
          throw new IllegalArgumentException("Digraph is not acyclic.");
      // Fix the topological order 
      // D C G H B A F E
      List<Integer> order = new ArrayList<Integer>();
      order.add(3);
      order.add(2);
      order.add(6);
      order.add(7);
      order.add(1);
      order.add(0);
      order.add(5);
      order.add(4);
      
      for (int v : order) {
          for (DirectedEdge e : G.adj(v))
              relax(e);
          if (v == 0) {
            print(G.V());
          }
      }
  }
  
  private void print(int V) {
    for (int i = 0; i < V; i++) {
      System.out.print(distTo(i) + " ");
    }
    System.out.println();
  }


  // relax edge e
  private void relax(DirectedEdge e) {
      int v = e.from(), w = e.to();
      if (distTo[w] > distTo[v] + e.weight()) {
          distTo[w] = distTo[v] + e.weight();
          edgeTo[w] = e;
      }       
  }

  /**
   * Returns the length of a shortest path from 
   * the source vertex <tt>s</tt> to vertex <tt>v</tt>.
   * @param v the destination vertex
   * @return the length of a shortest path from
   *  the source vertex <tt>s</tt> to vertex <tt>v</tt>;
   *    <tt>Double.POSITIVE_INFINITY</tt> if no such path
   */
  public double distTo(int v) {
      return distTo[v];
  }

  /**
   * Is there a path from the source vertex <tt>s</tt> to vertex <tt>v</tt>?
   * @param v the destination vertex
   * @return <tt>true</tt> if there is a path from the source vertex
   *    <tt>s</tt> to vertex <tt>v</tt>, and <tt>false</tt> otherwise
   */
  public boolean hasPathTo(int v) {
      return distTo[v] < Double.POSITIVE_INFINITY;
  }

  /**
   * Returns a shortest path from the source vertex <tt>s</tt> to vertex <tt>v</tt>.
   * @param v the destination vertex
   * @return a shortest path from the source vertex <tt>s</tt> to vertex <tt>v</tt>
   *    as an iterable of edges, and <tt>null</tt> if no such path
   */
  public Iterable<DirectedEdge> pathTo(int v) {
      if (!hasPathTo(v)) return null;
      Stack<DirectedEdge> path = new Stack<DirectedEdge>();
      for (DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()]) {
          path.push(e);
      }
      return path;
  }


  /**
   * Unit tests the <tt>AcyclicSP</tt> data type.
   */
  public static void main(String[] args) {
      In in = new In("/Users/dhamod/tmp/delete it/input1.txt");
      int s = 3;
      EdgeWeightedDigraph G = new EdgeWeightedDigraph(in);

      // find shortest path from s to each other vertex in DAG
      AcyclicDAGShortestPathForExcerise sp = 
          new AcyclicDAGShortestPathForExcerise(G, s);
      for (int v = 0; v < G.V(); v++) {
          if (sp.hasPathTo(v)) {
              StdOut.printf("%d to %d (%.2f)  ", s, v, sp.distTo(v));
              for (DirectedEdge e : sp.pathTo(v)) {
                  StdOut.print(e + "   ");
              }
              StdOut.println();
          }
          else {
              StdOut.printf("%d to %d         no path\n", s, v);
          }
      }
  }

}
