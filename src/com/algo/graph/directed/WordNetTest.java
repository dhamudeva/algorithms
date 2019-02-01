package com.algo.graph.directed;

import java.util.Set;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class WordNetTest {

  @Rule
  public ExpectedException expectedException = ExpectedException.none();

  private WordNet wordNet = null;
  
  private String rootDir = "/Users/dhamod/Application data/Git "
      + "Repo/algorithms/WordNet/wordnet/";
  private String synsetsFile;
  private String hypernymsFile;
  
  @Test
  public void testWordNetForInvalidCycle1() {
    
    synsetsFile = rootDir + "synsets3" + ".txt";
    hypernymsFile = rootDir + "hypernyms3InvalidCycle" + ".txt";
    
    expectedException.expect(IllegalArgumentException.class);
    
    wordNet = new WordNet(synsetsFile, hypernymsFile);
    
    Assert.fail("You should not see this message, it must have thrown "
        + "exception before");
  }
  
  @Test
  public void testWordNetForInvalidTwoRoots1() {
    synsetsFile = rootDir + "synsets3" + ".txt";
    hypernymsFile = rootDir + "hypernyms3InvalidTwoRoots" + ".txt";
    
    expectedException.expect(IllegalArgumentException.class);
    
    wordNet = new WordNet(synsetsFile, hypernymsFile);
    
    Assert.fail("You should not see this message, it must have thrown "
        + "exception before");
  }
  
  @Test
  public void testWordNetForInvalidCycle2() {
    synsetsFile = rootDir + "synsets6" + ".txt";
    hypernymsFile = rootDir + "hypernyms6InvalidCycle" + ".txt";
    
    expectedException.expect(IllegalArgumentException.class);
    
    wordNet = new WordNet(synsetsFile, hypernymsFile);
    
    Assert.fail("You should not see this message, it must have thrown "
        + "exception before");
  }

  @Test
  public void testWordNetForInvalidTwoRoots2() {
    synsetsFile = rootDir + "synsets6" + ".txt";
    hypernymsFile = rootDir + "hypernyms6InvalidCycle+Path" + ".txt";
    
    expectedException.expect(IllegalArgumentException.class);
    
    wordNet = new WordNet(synsetsFile, hypernymsFile);
    
    Assert.fail("You should not see this message, it must have thrown "
        + "exception before");
  }
  
  @Test
  public void testWordNetForInvalidTwoRoots3() {
    synsetsFile = rootDir + "synsets6" + ".txt";
    hypernymsFile = rootDir + "hypernyms6InvalidTwoRoots" + ".txt";
    
    expectedException.expect(IllegalArgumentException.class);
    
    wordNet = new WordNet(synsetsFile, hypernymsFile);
    
    Assert.fail("You should not see this message, it must have thrown "
        + "exception before");
  }
  
  @Test
  public void testWordNetForInvalidInput1() {
    hypernymsFile = rootDir + "hypernyms6InvalidTwoRoots" + ".txt";
    
    expectedException.expect(NullPointerException.class);
    
    wordNet = new WordNet(null, hypernymsFile);
  }
  
  @Test
  public void testWordNetForInvalidInput2() {
    synsetsFile = rootDir + "synsets" + ".txt";
    
    expectedException.expect(NullPointerException.class);
    
    wordNet = new WordNet(synsetsFile, null);
  }
  
  @Test
  public void testWordNetForInvalidInput3() {
    
    expectedException.expect(NullPointerException.class);
    
    wordNet = new WordNet(null, null);
  }
  
  @Test
  public void testWordNetForInvalidInput4() {
    
    hypernymsFile = rootDir + "hypernyms6InvalidTwoRoots" + ".txt";
    
    expectedException.expect(NullPointerException.class);
    
    wordNet = new WordNet("", hypernymsFile);
  }
  
  @Test
  public void testWordNetForInvalidInput5() {
    
    expectedException.expect(NullPointerException.class);
    
    wordNet = new WordNet("", "");
  }
  
  @Test
  public void testWordNetForInvalidInput6() {
    
    synsetsFile = rootDir + "synsets" + ".txt";
    
    expectedException.expect(NullPointerException.class);
    
    wordNet = new WordNet(synsetsFile, "");
  }
  
  @Test
  public void testWordNetForValidTwoAncestors() {
    synsetsFile = rootDir + "synsets" + ".txt";
    hypernymsFile = rootDir + "hypernyms" + ".txt";
    
    wordNet = new WordNet(synsetsFile, hypernymsFile);
  }
  
  @Test
  public void testWordNetForValidGraph() {
    synsetsFile = rootDir + "synsets" + ".txt";
    hypernymsFile = rootDir + "hypernyms" + ".txt";
    
    wordNet = new WordNet(synsetsFile, hypernymsFile);
  }
  
  @Test
  public void testIsNounForSuccess1() {
    synsetsFile = rootDir + "synsets100-subgraph" + ".txt";
    hypernymsFile = rootDir + "hypernyms100-subgraph" + ".txt";
    
    wordNet = new WordNet(synsetsFile, hypernymsFile);
    Assert.assertEquals(wordNet.isNoun("serum_albumin"), Boolean.TRUE);
  }
  
  @Test
  public void testIsNounForSuccess2() {
    synsetsFile = rootDir + "synsets100-subgraph" + ".txt";
    hypernymsFile = rootDir + "hypernyms100-subgraph" + ".txt";
    
    wordNet = new WordNet(synsetsFile, hypernymsFile);
    Assert.assertEquals(wordNet.isNoun("tetanus_immunoglobulin"), Boolean.TRUE);
  }
  
  @Test
  public void testIsNounForFailure1() {
    synsetsFile = rootDir + "synsets100-subgraph" + ".txt";
    hypernymsFile = rootDir + "hypernyms100-subgraph" + ".txt";
    
    wordNet = new WordNet(synsetsFile, hypernymsFile);
    Assert.assertEquals(wordNet.isNoun("Dhamu"), Boolean.FALSE);
  }
  
  @Test
  public void testIsNounForFailure2() {
    synsetsFile = rootDir + "synsets100-subgraph" + ".txt";
    hypernymsFile = rootDir + "hypernyms100-subgraph" + ".txt";
    
    expectedException.expect(IllegalArgumentException.class);
    
    wordNet = new WordNet(synsetsFile, hypernymsFile);
    wordNet.isNoun("");
  }
  
  @Test
  public void testIsNounForFailure3() {
    synsetsFile = rootDir + "synsets100-subgraph" + ".txt";
    hypernymsFile = rootDir + "hypernyms100-subgraph" + ".txt";
    
    expectedException.expect(NullPointerException.class);
    
    wordNet = new WordNet(synsetsFile, hypernymsFile);
    wordNet.isNoun(null);
  }
  
  @Test
  public void testIsNounForSuccess3() {
    synsetsFile = rootDir + "synsets100-subgraph" + ".txt";
    hypernymsFile = rootDir + "hypernyms100-subgraph" + ".txt";
    
    wordNet = new WordNet(synsetsFile, hypernymsFile);
    Assert.assertEquals(wordNet.isNoun("gamma_globulin human_gamma_globulin"), 
        Boolean.FALSE);
  }
  
  @Test
  public void testNounsForSuccess1() {
    synsetsFile = rootDir + "synsets100-subgraph" + ".txt";
    hypernymsFile = rootDir + "hypernyms100-subgraph" + ".txt";
    
    wordNet = new WordNet(synsetsFile, hypernymsFile);
    Assert.assertEquals(157, ((Set<String>) (wordNet.nouns())).size());
    
    synsetsFile = rootDir + "synsets" + ".txt";
    hypernymsFile = rootDir + "hypernyms" + ".txt";
    
    wordNet = new WordNet(synsetsFile, hypernymsFile);
    Assert.assertEquals(119188, ((Set<String>) (wordNet.nouns())).size());
  }
  
  @Test
  public void testDistanceForFailure1() {
    synsetsFile = rootDir + "synsets100-subgraph" + ".txt";
    hypernymsFile = rootDir + "hypernyms100-subgraph" + ".txt";
    
    expectedException.expect(NullPointerException.class);
    
    wordNet = new WordNet(synsetsFile, hypernymsFile);
    wordNet.distance("", null);
  }
  
  @Test
  public void testDistanceForFailure2() {
    synsetsFile = rootDir + "synsets100-subgraph" + ".txt";
    hypernymsFile = rootDir + "hypernyms100-subgraph" + ".txt";
    
    expectedException.expect(IllegalArgumentException.class);
    
    wordNet = new WordNet(synsetsFile, hypernymsFile);
    wordNet.distance("", "");
  }
  
  @Test
  public void testDistanceForFailure3() {
    synsetsFile = rootDir + "synsets100-subgraph" + ".txt";
    hypernymsFile = rootDir + "hypernyms100-subgraph" + ".txt";
    
    expectedException.expect(NullPointerException.class);
    
    wordNet = new WordNet(synsetsFile, hypernymsFile);
    wordNet.distance(null, "");
  }
  
  @Test
  public void testDistanceForFailure4() {
    synsetsFile = rootDir + "synsets100-subgraph" + ".txt";
    hypernymsFile = rootDir + "hypernyms100-subgraph" + ".txt";
    
    expectedException.expect(IllegalArgumentException.class);
    
    wordNet = new WordNet(synsetsFile, hypernymsFile);
    wordNet.distance("Dhamu", "tetanus_immunoglobulin");
  }
  
  @Test
  public void testDistanceForFailure5() {
    synsetsFile = rootDir + "synsets100-subgraph" + ".txt";
    hypernymsFile = rootDir + "hypernyms100-subgraph" + ".txt";
    
    expectedException.expect(IllegalArgumentException.class);
    
    wordNet = new WordNet(synsetsFile, hypernymsFile);
    wordNet.distance("tetanus_immunoglobulin", "Dhamu");
  }
  
  @Test
  public void testDistanceForFailure6() {
    synsetsFile = rootDir + "synsets100-subgraph" + ".txt";
    hypernymsFile = rootDir + "hypernyms100-subgraph" + ".txt";
    
    expectedException.expect(IllegalArgumentException.class);
    
    wordNet = new WordNet(synsetsFile, hypernymsFile);
    wordNet.distance("gamma_globulin human_gamma_globulin", "Dhamu");
  }
  
  @Test
  public void testDistanceForSuccess() {
    synsetsFile = rootDir + "synsets100-subgraph" + ".txt";
    hypernymsFile = rootDir + "hypernyms100-subgraph" + ".txt";
    
    wordNet = new WordNet(synsetsFile, hypernymsFile);
    int distance = wordNet.distance("tetanus_immunoglobulin", 
        "tetanus_immunoglobulin");
    Assert.assertNotEquals(-1, distance);
    Assert.assertEquals(0, distance);
    
    distance = wordNet.distance("entity", "tetanus_immunoglobulin");
    Assert.assertNotEquals(-1, distance);
    Assert.assertEquals(10, distance);
    
    distance = wordNet.distance("freshener", "tetanus_immunoglobulin");
    Assert.assertNotEquals(-1, distance);
    Assert.assertEquals(12, distance);
    
    distance = wordNet.distance("zymase", "keratin");
    Assert.assertNotEquals(-1, distance);
    Assert.assertEquals(5, distance);
  }
  
  @Test
  public void testDistanceForSuccess1() {
    synsetsFile = rootDir + "synsets" + ".txt";
    hypernymsFile = rootDir + "hypernyms" + ".txt";
    
    wordNet = new WordNet(synsetsFile, hypernymsFile);
    
    int distance = wordNet.distance("white_marlin", "mileage");
    Assert.assertNotEquals(-1, distance);
    Assert.assertEquals(23, distance);
    
    distance = wordNet.distance("Black_Plague", "black_marlin");
    Assert.assertNotEquals(-1, distance);
    Assert.assertEquals(33, distance);
    
    distance = wordNet.distance("American_water_spaniel", "histology");
    Assert.assertNotEquals(-1, distance);
    Assert.assertEquals(27, distance);
    
    distance = wordNet.distance("Brown_Swiss", "barrel_roll");
    Assert.assertNotEquals(-1, distance);
    Assert.assertEquals(29, distance);
    
    distance = wordNet.distance("Brown_Swiss", "barrel_roll");
    Assert.assertNotEquals(-1, distance);
    Assert.assertEquals(29, distance);
  }
  
  @Test
  public void testSapForFailure1() {
    synsetsFile = rootDir + "synsets100-subgraph" + ".txt";
    hypernymsFile = rootDir + "hypernyms100-subgraph" + ".txt";
    
    expectedException.expect(NullPointerException.class);
    
    wordNet = new WordNet(synsetsFile, hypernymsFile);
    wordNet.sap("", null);
  }
  
  @Test
  public void testSapForFailure2() {
    synsetsFile = rootDir + "synsets100-subgraph" + ".txt";
    hypernymsFile = rootDir + "hypernyms100-subgraph" + ".txt";
    
    expectedException.expect(IllegalArgumentException.class);
    
    wordNet = new WordNet(synsetsFile, hypernymsFile);
    wordNet.sap("", "");
  }
  
  @Test
  public void testSapForFailure3() {
    synsetsFile = rootDir + "synsets100-subgraph" + ".txt";
    hypernymsFile = rootDir + "hypernyms100-subgraph" + ".txt";
    
    expectedException.expect(NullPointerException.class);
    
    wordNet = new WordNet(synsetsFile, hypernymsFile);
    wordNet.sap(null, "");
  }
  
  @Test
  public void testSapForFailure4() {
    synsetsFile = rootDir + "synsets100-subgraph" + ".txt";
    hypernymsFile = rootDir + "hypernyms100-subgraph" + ".txt";
    
    expectedException.expect(IllegalArgumentException.class);
    
    wordNet = new WordNet(synsetsFile, hypernymsFile);
    wordNet.sap("Dhamu", "tetanus_immunoglobulin");
  }
  
  @Test
  public void testSapForFailure5() {
    synsetsFile = rootDir + "synsets100-subgraph" + ".txt";
    hypernymsFile = rootDir + "hypernyms100-subgraph" + ".txt";
    
    expectedException.expect(IllegalArgumentException.class);
    
    wordNet = new WordNet(synsetsFile, hypernymsFile);
    wordNet.sap("tetanus_immunoglobulin", "Dhamu");
  }
  
  @Test
  public void testSapForFailure6() {
    synsetsFile = rootDir + "synsets100-subgraph" + ".txt";
    hypernymsFile = rootDir + "hypernyms100-subgraph" + ".txt";
    
    expectedException.expect(IllegalArgumentException.class);
    
    wordNet = new WordNet(synsetsFile, hypernymsFile);
    wordNet.sap("gamma_globulin human_gamma_globulin", "Dhamu");
  }
  
  @Test
  public void testSapForSuccess1() {
    synsetsFile = rootDir + "synsets100-subgraph" + ".txt";
    hypernymsFile = rootDir + "hypernyms100-subgraph" + ".txt";
    
    wordNet = new WordNet(synsetsFile, hypernymsFile);
    
    String synset = wordNet.sap("freshener", "tetanus_immunoglobulin");
    Assert.assertNotNull(synset);
    Assert.assertEquals("entity", synset);
  }
  
  @Test
  public void testSapForSuccess2() {
    
    synsetsFile = rootDir + "synsets" + ".txt";
    hypernymsFile = rootDir + "hypernyms" + ".txt";
    
    wordNet = new WordNet(synsetsFile, hypernymsFile);
    
    String synset = wordNet.sap("worm", "bird");
    Assert.assertNotNull(synset);
    Assert.assertEquals("animal animate_being beast brute creature fauna",
        synset);
    
    synset = wordNet.sap("municipality", "region");
    Assert.assertNotNull(synset);
    Assert.assertEquals("region", synset);
    
    synset = wordNet.sap("individual", "edible_fruit");
    Assert.assertNotNull(synset);
    Assert.assertEquals("physical_entity", synset);
  }
}
