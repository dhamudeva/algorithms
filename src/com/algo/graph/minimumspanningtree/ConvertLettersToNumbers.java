package com.algo.graph.minimumspanningtree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

// LATER I USED THE FOLLOWING TO FINISH EXERCIESES
// java -cp ./algs4.jar edu.princeton.cs.algs4.KruskalMST 
// "/Users/dhamod/tmp/delete it/input.txt"
// java -cp ./algs4.jar edu.princeton.cs.algs4.PrimMST 
// "/Users/dhamod/tmp/delete it/input1.txt"
//
public class ConvertLettersToNumbers {
  
  public static void main(String[] args) throws NumberFormatException, IOException {
    
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    
    int numberOfEdges = Integer.parseInt(reader.readLine());
    List<String> inputEdges = new ArrayList<String>();
    
    for (int i = 0; i < numberOfEdges; i++) {
      inputEdges.add(reader.readLine());
    }
    
    for (int i = 0; i < numberOfEdges; i++) {
      String[] brokenLine = inputEdges.get(i).trim().split(" ");
      int j = 0;
      for (; j < brokenLine.length - 1; j++) {
        System.out.print((char) (brokenLine[j].charAt(0)) - 'A');
        System.out.print(" ");
      }
      System.out.print(brokenLine[j]);
      System.out.println();
    }
  }
  
  
}
