package com.algo.graph.directed;

import org.junit.Assert;
import org.junit.Test;

import edu.princeton.cs.algs4.In;

public class OutcastTest {

    private String rootDir = "/Users/dhamod/Application "
        + "data/Git Repo/algorithms/WordNet/wordnet/";
    private String outcastFile;
    private String synsetsFile;
    private String hypernymsFile;

    @Test
  public void test() {
    outcastFile = rootDir + "outcast2" + ".txt";
    synsetsFile = rootDir + "synsets" + ".txt";
    hypernymsFile = rootDir + "hypernyms" + ".txt";
    
    WordNet wordNet = new WordNet(synsetsFile, hypernymsFile);
      Outcast outcast = new Outcast(wordNet);
        In in = new In(outcastFile);
        String[] nouns = in.readAllStrings();
        Assert.assertEquals("Turing", outcast.outcast(nouns));
        
        outcastFile = rootDir + "outcast3" + ".txt";
        in = new In(outcastFile);
        nouns = in.readAllStrings();
        Assert.assertEquals("Mickey_Mouse", outcast.outcast(nouns));
        
        outcastFile = rootDir + "outcast4" + ".txt";
        in = new In(outcastFile);
        nouns = in.readAllStrings();
        Assert.assertEquals("probability", outcast.outcast(nouns));
        
        outcastFile = rootDir + "outcast5" + ".txt";
        in = new In(outcastFile);
        nouns = in.readAllStrings();
        Assert.assertEquals("table", outcast.outcast(nouns));
        
        outcastFile = rootDir + "outcast5a" + ".txt";
        in = new In(outcastFile);
        nouns = in.readAllStrings();
        Assert.assertEquals("heart", outcast.outcast(nouns));
        
        outcastFile = rootDir + "outcast7" + ".txt";
        in = new In(outcastFile);
        nouns = in.readAllStrings();
        Assert.assertEquals("India", outcast.outcast(nouns));
        
    outcastFile = rootDir + "outcast8" + ".txt";
        in = new In(outcastFile);
        nouns = in.readAllStrings();
        Assert.assertEquals("bed", outcast.outcast(nouns));
        
        outcastFile = rootDir + "outcast8a" + ".txt";
        in = new In(outcastFile);
        nouns = in.readAllStrings();
        Assert.assertEquals("playboy", outcast.outcast(nouns));
        
        outcastFile = rootDir + "outcast8b" + ".txt";
        in = new In(outcastFile);
        nouns = in.readAllStrings();
        Assert.assertEquals("cabbage", outcast.outcast(nouns));
        
        outcastFile = rootDir + "outcast8c" + ".txt";
        in = new In(outcastFile);
        nouns = in.readAllStrings();
        Assert.assertEquals("tree", outcast.outcast(nouns));
        
        outcastFile = rootDir + "outcast9" + ".txt";
        in = new In(outcastFile);
        nouns = in.readAllStrings();
        Assert.assertEquals("tree", outcast.outcast(nouns));
        
        outcastFile = rootDir + "outcast9a" + ".txt";
        in = new In(outcastFile);
        nouns = in.readAllStrings();
        Assert.assertEquals("eyes", outcast.outcast(nouns));
        
        outcastFile = rootDir + "outcast10" + ".txt";
        in = new In(outcastFile);
        nouns = in.readAllStrings();
        Assert.assertEquals("albatross", outcast.outcast(nouns));
        
        outcastFile = rootDir + "outcast10a" + ".txt";
        in = new In(outcastFile);
        nouns = in.readAllStrings();
        Assert.assertEquals("serendipity", outcast.outcast(nouns));
        
        outcastFile = rootDir + "outcast11" + ".txt";
        in = new In(outcastFile);
        nouns = in.readAllStrings();
        Assert.assertEquals("potato", outcast.outcast(nouns));
        
        outcastFile = rootDir + "outcast12" + ".txt";
        in = new In(outcastFile);
        nouns = in.readAllStrings();
        Assert.assertEquals("Minneapolis", outcast.outcast(nouns));
        
        outcastFile = rootDir + "outcast12a" + ".txt";
        in = new In(outcastFile);
        nouns = in.readAllStrings();
        Assert.assertEquals("mongoose", outcast.outcast(nouns));
        
        outcastFile = rootDir + "outcast17" + ".txt";
        in = new In(outcastFile);
        nouns = in.readAllStrings();
        Assert.assertEquals("particularism", outcast.outcast(nouns));
        
        outcastFile = rootDir + "outcast20" + ".txt";
        in = new In(outcastFile);
        nouns = in.readAllStrings();
        Assert.assertEquals("particularism", outcast.outcast(nouns));
        
        outcastFile = rootDir + "outcast29" + ".txt";
        in = new In(outcastFile);
        nouns = in.readAllStrings();
        Assert.assertEquals("acorn", outcast.outcast(nouns));
  }
}
