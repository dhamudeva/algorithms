package com.algo.graph.directed;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.HashMap;
import java.util.Map;

/**
 * Return the outcast.
 * @author dhamod
 */
public class Outcast {
  private WordNet wordNet;
  private Map<TwoNouns, Integer> twoNounsToDistance;
  
  /**
   * Constructor.
   * @param wordnet WordNet
   */
  public Outcast(WordNet wordnet) {
    if (wordnet == null) {
      throw new NullPointerException();
    }
    wordNet = wordnet;
    twoNounsToDistance = new HashMap<Outcast.TwoNouns, Integer>();
  }

  /**
   * Return the outcast among nouns.
   * @param nouns Array of nouns
   * @return Outcast noun
   */
  public String outcast(String[] nouns) {
    if (nouns == null) {
      throw new NullPointerException();
    }

    String bestNounSofar = null;
    int bestDistanceSofar = -1;
    int currentDistance = 0;

    for (int i = 0; i < nouns.length; i++) {
      currentDistance = 0;
      for (int j = 0; j < nouns.length; j++) {
        if (i == j) {
          // Skip it because the distance is zero
          continue;
        }
        currentDistance += getDistance(nouns[i], nouns[j]);
      }
      if (currentDistance > bestDistanceSofar) {
        bestDistanceSofar = currentDistance;
        bestNounSofar = nouns[i];
      }
    }
    return bestNounSofar;
  }

  /**
   * Main Method.
   * @param args Arguments.
   */
  public static void main(String[] args) {
    WordNet wordnet = new WordNet(args[0], args[1]);
    Outcast outcast = new Outcast(wordnet);
    for (int t = 2; t < args.length; t++) {
      In in = new In(args[t]);
      String[] nouns = in.readAllStrings();
      StdOut.println(args[t] + ": " + outcast.outcast(nouns));
    }
  }

  private int getDistance(String noun1, String noun2) {
    TwoNouns twoNouns = new TwoNouns(noun1, noun2);
    TwoNouns reversedTwoNouns = new TwoNouns(noun2, noun1);
    int distance;
    if (!twoNounsToDistance.containsKey(twoNouns)) {
       if (!twoNounsToDistance.containsKey(reversedTwoNouns)) {
         distance = wordNet.distance(noun1, noun2);
         twoNounsToDistance.put(twoNouns, distance);
         twoNounsToDistance.put(reversedTwoNouns, distance);
       } else {
         distance = twoNounsToDistance.get(reversedTwoNouns);
       }
    } else {
      distance = twoNounsToDistance.get(twoNouns);
    }
    
    return distance;
  }

  private class TwoNouns {
    private String noun1;
    private String noun2;

    TwoNouns(String nounOne, String nounTwo) {
      noun1 = nounOne;
      noun2 = nounTwo;
    }

    public boolean equals(Object object) {
      if (object == null) {
        return false;
      }
      if (!(object.getClass().isAssignableFrom(TwoNouns.class))) {
        return false;
      }
      if (object == this) {
        return true;
      }
      TwoNouns anotherTwoNouns = (TwoNouns) object;
      return this.noun1.equals(anotherTwoNouns.noun1) 
          && this.noun2.equals(anotherTwoNouns.noun2); 
    }
    
    public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + noun1.hashCode() + noun2.hashCode();
      return result;
    }
    
    public void reverse() {
      String temp = noun1;
      noun1 = noun2;
      noun2 = temp;
    }
  }
}
