package com.algo.graph.directed;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class SAPTest {
  
  private SAP sap = null;
  private String rootDir = "/Users/dhamod/Application "
    + "data/Git Repo/algorithms/WordNet/wordnet/";
  private String digraphFile;

  @Test
  public void testDistanceForDigraph1() {
    digraphFile = rootDir + "digraph1" + ".txt";
    In in = new In(digraphFile);
      Digraph G = new Digraph(in);
      sap = new SAP(G);
      
      Assert.assertEquals(4, sap.length(3, 11));
      Assert.assertEquals(1, sap.ancestor(3, 11));
      
      Assert.assertEquals(3, sap.length(9, 12));
      Assert.assertEquals(5, sap.ancestor(9, 12));
      
      Assert.assertEquals(4, sap.length(7, 2));
      Assert.assertEquals(0, sap.ancestor(7, 2));
      
      Assert.assertEquals(-1, sap.length(1, 6));
      Assert.assertEquals(-1, sap.ancestor(1, 6));
      
      ArrayList<Integer> v = new ArrayList<>();
      v.add(3);
      v.add(9);
      v.add(7);
      v.add(1);
      
      ArrayList<Integer> w = new ArrayList<>();
      w.add(11);
      w.add(12);
      w.add(2);
      w.add(6);
      
      Assert.assertEquals(2, sap.length(v, w));
      Assert.assertEquals(0, sap.ancestor(v, w));
  }
  
  @Test
  public void testDistanceForDigraph2() {
    digraphFile = rootDir + "digraph2" + ".txt";
    In in = new In(digraphFile);
      Digraph G = new Digraph(in);
      sap = new SAP(G);
      
      Assert.assertEquals(1, sap.length(0, 1));
      
      Assert.assertEquals(1, sap.length(2, 1));
      Assert.assertEquals(2, sap.ancestor(2, 1));
  }
  
  @Test
  public void testDistanceForDigraph3() {
    digraphFile = rootDir + "digraph3" + ".txt";
    In in = new In(digraphFile);
      Digraph G = new Digraph(in);
      sap = new SAP(G);
      
      Assert.assertEquals(5, sap.length(7, 14));
      Assert.assertEquals(8, sap.ancestor(7, 14));
  }
  
  @Test
  public void testDistanceForDigraph4() {
    digraphFile = rootDir + "digraph4" + ".txt";
    In in = new In(digraphFile);
      Digraph G = new Digraph(in);
      sap = new SAP(G);
      
      Assert.assertEquals(3, sap.length(6, 1));
      Assert.assertEquals(2, sap.length(3, 1));
  }
  
  @Test
  public void testDistanceForDigraph5() {
    digraphFile = rootDir + "digraph5" + ".txt";
    In in = new In(digraphFile);
      Digraph G = new Digraph(in);
      sap = new SAP(G);
      
      Assert.assertEquals(3, sap.length(7, 10));
      Assert.assertEquals(2, sap.length(20, 12));
  }
  
  @Test
  public void testDistanceForDigraph6() {
    digraphFile = rootDir + "digraph6" + ".txt";
    In in = new In(digraphFile);
      Digraph G = new Digraph(in);
      sap = new SAP(G);
      
      Assert.assertEquals(4, sap.length(5, 1));
      Assert.assertEquals(4, sap.length(1, 5));
      Assert.assertEquals(4, sap.ancestor(1, 5));
  }
  
  @Test
  public void testDistanceForDigraph9() {
    digraphFile = rootDir + "digraph9" + ".txt";
    In in = new In(digraphFile);
      Digraph G = new Digraph(in);
      sap = new SAP(G);
      
      Assert.assertEquals(3, sap.length(0, 4));
      Assert.assertEquals(3, sap.length(4, 0));
  }
  
  @Test
  public void testDistanceForDigraphWordNet() {
    digraphFile = rootDir + "digraph-wordnet" + ".txt";
    In in = new In(digraphFile);
      Digraph G = new Digraph(in);
      sap = new SAP(G);
      
      Assert.assertEquals(17, sap.length(35205, 21385));
      Assert.assertEquals(38003, sap.ancestor(35205, 21385));
      
      Assert.assertEquals(9, sap.length(35083, 48629));
      Assert.assertEquals(18840, sap.ancestor(35083, 48629));
      
      
      List<Integer> vertices1 = new ArrayList<>();
      vertices1.add(7093);
      
      List<Integer> vertices2 = new ArrayList<>();
      vertices2.add(18765);
      vertices2.add(21191);
      
      Assert.assertEquals(12, sap.length(vertices1, vertices2));
      Assert.assertEquals(38003, sap.ancestor(vertices1, vertices2));
      
      vertices1 = new ArrayList<>();
      vertices1.add(33733);
      vertices1.add(81309);
      
      vertices2 = new ArrayList<>();
      vertices2.add(6202);
      
      Assert.assertEquals(14, sap.length(vertices1, vertices2));
      Assert.assertEquals(38003, sap.ancestor(vertices1, vertices2));
      
      vertices1 = new ArrayList<>();
      vertices1.add(60136);
      vertices1.add(77834);
      
      vertices2 = new ArrayList<>();
      vertices2.add(43425);
      vertices2.add(51151);
      
      Assert.assertEquals(12, sap.length(vertices1, vertices2));
      Assert.assertEquals(60600, sap.ancestor(vertices1, vertices2));
      
      vertices1 = new ArrayList<>();
      vertices1.add(77401);
      
      vertices2 = new ArrayList<>();
      vertices2.add(33587);
      vertices2.add(40679);
      
      Assert.assertEquals(15, sap.length(vertices1, vertices2));
      Assert.assertEquals(38545, sap.ancestor(vertices1, vertices2));
      
      vertices1 = new ArrayList<>();
      vertices1.add(3583);
      vertices1.add(30030);
      
      vertices2 = new ArrayList<>();
      vertices2.add(15616);
      
      Assert.assertEquals(10, sap.length(vertices1, vertices2));
      Assert.assertEquals(44437, sap.ancestor(vertices1, vertices2));
      
      vertices1 = new ArrayList<>();
      vertices1.add(61845);
      vertices1.add(69010);
      
      vertices2 = new ArrayList<>();
      vertices2.add(23731);
      vertices2.add(60265);
      
      Assert.assertEquals(13, sap.length(vertices1, vertices2));
      Assert.assertEquals(81004, sap.ancestor(vertices1, vertices2));
      
      
  }
}
