package com.algo.graph.minimumspanningtree;

import edu.princeton.cs.algs4.DirectedEdge;
import edu.princeton.cs.algs4.EdgeWeightedDigraph;
import edu.princeton.cs.algs4.EdgeWeightedDirectedCycle;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class BellmanFordForExcerise {

  private double[] distTo;               
  // distTo[v] = distance  of shortest s->v path
  private DirectedEdge[] edgeTo;         
  // edgeTo[v] = last edge on shortest s->v path
  private boolean[] onQueue;             // onQueue[v] = is v currently on the queue?
  private Queue<Integer> queue;          // queue of vertices to relax
  private int cost;                      // number of calls to relax()
  private Iterable<DirectedEdge> cycle;  // negative cycle (or null if no such cycle)

  /**
   * Computes a shortest paths tree from <tt>s</tt> to every other vertex in
   * the edge-weighted digraph <tt>G</tt>.
   * @param G the acyclic digraph
   * @param s the source vertex
   * @throws IllegalArgumentException unless 0 &le; <tt>s</tt> &le; <tt>V</tt> - 1
   */
  public BellmanFordForExcerise(EdgeWeightedDigraph G, int s) {
      distTo  = new double[G.V()];
      edgeTo  = new DirectedEdge[G.V()];
      onQueue = new boolean[G.V()];
      for (int v = 0; v < G.V(); v++)
          distTo[v] = Double.POSITIVE_INFINITY;
      distTo[s] = 0.0;

      // Bellman-Ford algorithm
      queue = new Queue<Integer>();
      queue.enqueue(s);
      onQueue[s] = true;
      /*while (!queue.isEmpty() && !hasNegativeCycle()) {
          int v = queue.dequeue();
          onQueue[v] = false;
          relax(G, v);
      }*/

      for (int i = 0; i < G.V(); i++) {
        for (int j = 0; j < G.V(); j++) {
          for (DirectedEdge e : G.adj(j)) {
            relax(e);
          }
        }
        if (i == 2) {
          print(G.V());
        }
      }
      
      assert check(G, s);
  }
  
//relax edge e and update pq if changed
  private void relax(DirectedEdge e) {
      int v = e.from(), w = e.to();
      if (distTo[w] > distTo[v] + e.weight()) {
          distTo[w] = distTo[v] + e.weight();
          edgeTo[w] = e;
      }
  }
  
  private void print(int V) {
    for (int i = 0; i < V; i++) {
      System.out.print(distTo(i) + " ");
    }
    System.out.println();
  }

  // relax vertex v and put other endpoints on queue if changed
  private void relax(EdgeWeightedDigraph G, int v) {
      for (DirectedEdge e : G.adj(v)) {
          int w = e.to();
          if (distTo[w] > distTo[v] + e.weight()) {
              distTo[w] = distTo[v] + e.weight();
              edgeTo[w] = e;
              if (!onQueue[w]) {
                  queue.enqueue(w);
                  onQueue[w] = true;
              }
          }
          if (cost++ % G.V() == 0) {
              findNegativeCycle();
              if (hasNegativeCycle()) return;  // found a negative cycle
          }
      }
  }

  /**
   * Is there a negative cycle reachable from the source vertex <tt>s</tt>?
   * @return <tt>true</tt> if there is a negative cycle reachable from the
   *    source vertex <tt>s</tt>, and <tt>false</tt> otherwise
   */
  public boolean hasNegativeCycle() {
      return cycle != null;
  }

  /**
   * Returns a negative cycle reachable from the 
   * source vertex <tt>s</tt>, or <tt>null</tt>
   * if there is no such cycle.
   * @return a negative cycle reachable from the soruce vertex <tt>s</tt> 
   *    as an iterable of edges, and <tt>null</tt> if there is no such cycle
   */
  public Iterable<DirectedEdge> negativeCycle() {
      return cycle;
  }

  // by finding a cycle in predecessor graph
  private void findNegativeCycle() {
      int V = edgeTo.length;
      EdgeWeightedDigraph spt = new EdgeWeightedDigraph(V);
      for (int v = 0; v < V; v++)
          if (edgeTo[v] != null)
              spt.addEdge(edgeTo[v]);

      EdgeWeightedDirectedCycle finder = new EdgeWeightedDirectedCycle(spt);
      cycle = finder.cycle();
  }

  /**
   * Returns the length of a shortest path from the 
   * source vertex <tt>s</tt> to vertex <tt>v</tt>.
   * @param v the destination vertex
   * @return the length of a shortest path from the 
   * source vertex <tt>s</tt> to vertex <tt>v</tt>;
   *    <tt>Double.POSITIVE_INFINITY</tt> if no such path
   * @throws UnsupportedOperationException if there
   *  is a negative cost cycle reachable
   *    from the source vertex <tt>s</tt>
   */
  public double distTo(int v) {
      if (hasNegativeCycle())
          throw new UnsupportedOperationException("Negative cost cycle exists");
      return distTo[v];
  }

  /**
   * Is there a path from the source <tt>s</tt> to vertex <tt>v</tt>?
   * @param v the destination vertex
   * @return <tt>true</tt> if there is a path from the source vertex
   *    <tt>s</tt> to vertex <tt>v</tt>, and <tt>false</tt> otherwise
   */
  public boolean hasPathTo(int v) {
      return distTo[v] < Double.POSITIVE_INFINITY;
  }

  /**
   * Returns a shortest path from the source <tt>s</tt> to vertex <tt>v</tt>.
   * @param v the destination vertex
   * @return a shortest path from the source <tt>s</tt> to vertex <tt>v</tt>
   *    as an iterable of edges, and <tt>null</tt> if no such path
   * @throws UnsupportedOperationException 
   * if there is a negative cost cycle reachable
   *    from the source vertex <tt>s</tt>
   */
  public Iterable<DirectedEdge> pathTo(int v) {
      if (hasNegativeCycle())
          throw new UnsupportedOperationException("Negative cost cycle exists");
      if (!hasPathTo(v)) return null;
      Stack<DirectedEdge> path = new Stack<DirectedEdge>();
      for (DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()]) {
          path.push(e);
      }
      return path;
  }

  // check optimality conditions: either 
  // (i) there exists a negative cycle reacheable from s
  //     or 
  // (ii)  for all edges e = v->w:            distTo[w] <= distTo[v] + e.weight()
  // (ii') for all edges e = v->w on the SPT: distTo[w] == distTo[v] + e.weight()
  private boolean check(EdgeWeightedDigraph G, int s) {

      // has a negative cycle
      if (hasNegativeCycle()) {
          double weight = 0.0;
          for (DirectedEdge e : negativeCycle()) {
              weight += e.weight();
          }
          if (weight >= 0.0) {
              System.err.println("error: weight of negative cycle = " + weight);
              return false;
          }
      }

      // no negative cycle reachable from source
      else {

          // check that distTo[v] and edgeTo[v] are consistent
          if (distTo[s] != 0.0 || edgeTo[s] != null) {
              System.err.println("distanceTo[s] and edgeTo[s] inconsistent");
              return false;
          }
          for (int v = 0; v < G.V(); v++) {
              if (v == s) continue;
              if (edgeTo[v] == null && distTo[v] != Double.POSITIVE_INFINITY) {
                  System.err.println("distTo[] and edgeTo[] inconsistent");
                  return false;
              }
          }

          // check that all edges e = v->w satisfy
          //distTo[w] <= distTo[v] + e.weight()
          for (int v = 0; v < G.V(); v++) {
              for (DirectedEdge e : G.adj(v)) {
                  int w = e.to();
                  if (distTo[v] + e.weight() < distTo[w]) {
                      System.err.println("edge " + e + " not relaxed");
                      return false;
                  }
              }
          }

          // check that all edges e = v->w on SPT 
          // satisfy distTo[w] == distTo[v] + e.weight()
          for (int w = 0; w < G.V(); w++) {
              if (edgeTo[w] == null) continue;
              DirectedEdge e = edgeTo[w];
              int v = e.from();
              if (w != e.to()) return false;
              if (distTo[v] + e.weight() != distTo[w]) {
                  System.err.println("edge " + e + " on shortest path not tight");
                  return false;
              }
          }
      }

      StdOut.println("Satisfies optimality conditions");
      StdOut.println();
      return true;
  }

  /**
   * Unit tests the <tt>BellmanFordSP</tt> data type.
   */
  public static void main(String[] args) {
      In in = new In("/Users/dhamod/tmp/delete it/input2.txt");
      int s = 3;
      EdgeWeightedDigraph G = new EdgeWeightedDigraph(in);

      BellmanFordForExcerise sp = new BellmanFordForExcerise(G, s);

      // print negative cycle
      if (sp.hasNegativeCycle()) {
          for (DirectedEdge e : sp.negativeCycle())
              StdOut.println(e);
      }

      // print shortest paths
      else {
          for (int v = 0; v < G.V(); v++) {
              if (sp.hasPathTo(v)) {
                  StdOut.printf("%d to %d (%5.2f)  ", s, v, sp.distTo(v));
                  for (DirectedEdge e : sp.pathTo(v)) {
                      StdOut.print(e + "   ");
                  }
                  StdOut.println();
              }
              else {
                  StdOut.printf("%d to %d           no path\n", s, v);
              }
          }
      }

  }
}
