package com.algo.graph.directed;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class WordNet {
  
  private Set<String> sortedNouns = new TreeSet<>();
  private Map<String, List<Integer>> nounsToSynsetId;
  private Map<Integer, String> synsetIdToNouns;
  private SAP sap;
  private Digraph wordNetGraph;
  private boolean cyclic = false;
  
  /**
   * WordNet construction.
   * @param synsets Synsets file location.
   * @param hypernyms Hypernyms file location.
   */
  public WordNet(String synsets, String hypernyms) {
    
    if (synsets == null || hypernyms == null 
       || synsets.isEmpty() || hypernyms.isEmpty()) {
      throw new NullPointerException();
    }
    
    nounsToSynsetId = new HashMap<>();
    synsetIdToNouns = new HashMap<>();

    In synsetsIn = new In(synsets);
    int numberOfVertices = 0;
    while (synsetsIn.hasNextLine()) {
      String[] splitCurrentLine = synsetsIn.readLine().split(",");
      numberOfVertices++;
      int synsetId = Integer.parseInt(splitCurrentLine[0]);
      synsetIdToNouns.put(synsetId, splitCurrentLine[1]);
      for (String str : splitCurrentLine[1].split(" ")) {
        sortedNouns.add(str);
        if (nounsToSynsetId.containsKey(str)) {
          List<Integer> synsetIds = nounsToSynsetId.get(str);
          synsetIds.add(synsetId);
        } else {
          List<Integer> synsetIds = new ArrayList<>();
          synsetIds.add(synsetId);
          nounsToSynsetId.put(str, synsetIds);
        }
      }
    }
    
    wordNetGraph = new Digraph(numberOfVertices);
    
    In hypernymsIn = new In(hypernyms);
    while (hypernymsIn.hasNextLine()) {
      String[] splitCurrentLine = hypernymsIn.readLine().split(",");
      int left = Integer.parseInt(splitCurrentLine[0]);
      for (int i = 1; i < splitCurrentLine.length; i++) {
        wordNetGraph.addEdge(left, Integer.parseInt(splitCurrentLine[i]));
      }
    }
    
    findAnyCycle();
    
    if (cyclic) {
      throw new IllegalArgumentException();
    }
    
    if (hasMoreThanOneRoot()) {
      throw new IllegalArgumentException();
    }

    sap = new SAP(wordNetGraph);
  }
  
  /*
   * If we can find more than one vertex that has
   * no adjacent vertices, then we found a graph with more than 
   * one root.
   */
  private boolean hasMoreThanOneRoot() {
    boolean haveSeenRootBefore = false;
    for (int v = 0; v < wordNetGraph.V(); v++) {
      if (!wordNetGraph.adj(v).iterator().hasNext()) {
        if (haveSeenRootBefore) {
          return true;
        }
        haveSeenRootBefore = true;
      }
    }
    return false;
  }
  
  private void findAnyCycle() {
    boolean[] marked = new boolean[wordNetGraph.V()];
    boolean[] onStack = new boolean[wordNetGraph.V()];
    for (int v = 0; v < wordNetGraph.V(); v++) {
      if (!marked[v]) {
        dfs(marked, onStack, v);
        if (cyclic) {
          break;
        }
      }
    }
  }
  
  /*
   * If a vertex is already marked and it is found in the stack,
   * then the graph has cycle. Since we cannot access the program stack,
   * we are using our own stack. This must be reset before starting
   * a dfs on another vertex.
   */
  private void dfs(boolean[] marked, boolean[] onStack, int vertex) {
    marked[vertex] = true;
    onStack[vertex] = true;
    for (int w : wordNetGraph.adj(vertex)) {
      if (!marked[w]) {
        dfs(marked, onStack, w);   
      } else if (onStack[w]) {
        cyclic = true;
        break;
      }
    }
    onStack[vertex] = false;
  }
  
  /**
   * Returns the sorted list of nouns.
   * @return List of strings
   */
  public Iterable<String> nouns() {
    return sortedNouns;
  }
  
  /**
   * Return true if given word is a noun.
   * @param word A string representing a word.
   * @return true or false.
   */
  public boolean isNoun(String word) {
    if (word == null) {
      throw new NullPointerException();
    }
    if (word.isEmpty()) {
      throw new IllegalArgumentException();
    }
    return sortedNouns.contains(word);
  }
  
  /**
   * Return the distance between two nouns.
   * @param nounA Valid noun in the graph.
   * @param nounB Valid noun in the graph.
   * @return distance.
   */
  public int distance(String nounA, String nounB) {
    if (nounA == null || nounB == null) {
      throw new NullPointerException();
    }
    
    if (nounA.isEmpty() || nounB.isEmpty() || !isNoun(nounA) || !isNoun(nounB)) {
      throw new IllegalArgumentException();
    }
    
    return sap.length(nounsToSynsetId.get(nounA), nounsToSynsetId.get(nounB));
  }
  
  /**
   * Shortest Ancestral Path.
   * @param nounA Valid noun in the graph.
   * @param nounB Valid noun in the graph.
   * @return Path in string.
   */
  public String sap(String nounA, String nounB) {
    if (nounA == null || nounB == null) {
      throw new NullPointerException();
    }

    if (nounA.isEmpty() || nounB.isEmpty() || !isNoun(nounA) || !isNoun(nounB)) {
      throw new IllegalArgumentException();
    }
    int synsetId = sap.ancestor(nounsToSynsetId.get(nounA), 
        nounsToSynsetId.get(nounB));
    return synsetIdToNouns.get(synsetId);
  }
}
