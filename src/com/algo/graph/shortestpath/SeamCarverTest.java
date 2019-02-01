package com.algo.graph.shortestpath;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import edu.princeton.cs.algs4.Picture;

public class SeamCarverTest {
  
  private Picture picture = new Picture("/Users/dhamod/Application data/Git Repo/"
      + "algorithms/seamcarving/seamCarving/12x10.png");
  
  private SeamCarver seamCarver = new SeamCarver(picture);
  
  private Picture picture1 = new Picture("/Users/dhamod/Application data/Git Repo/"
      + "algorithms/seamcarving/seamCarving/3x4.png");
    
  private SeamCarver seamCarver1 = new SeamCarver(picture1);

  private Picture picture2 = new Picture("/Users/dhamod/Application data/Git Repo/"
      + "algorithms/seamcarving/seamCarving/diagonals.png");
  
  private SeamCarver seamCarver2 = new SeamCarver(picture2);
  
  private Picture picture3 = new Picture("/Users/dhamod/Application data/Git Repo/"
      + "algorithms/seamcarving/seamCarving/7x10.png");
  
  private SeamCarver seamCarver3 = new SeamCarver(picture3);
  
  private Picture picture4 = new Picture("/Users/dhamod/Application data/Git Repo/"
      + "algorithms/seamcarving/seamCarving/5x6.png");
  
  private SeamCarver seamCarver4 = new SeamCarver(picture4);
  
  @Test(expected = NullPointerException.class)
  @SuppressWarnings("unused")
  public void testConstructor() {
    SeamCarver seamCarverNull = new SeamCarver(null);
  }

  @Test
  public void testWidth() {
    
    Assert.assertEquals(12, seamCarver.width());
  }
  
  @Test
  public void testHeight() {
    Assert.assertEquals(10, seamCarver.height());
  }
  
  @Test(expected = IndexOutOfBoundsException.class)
  public void testEnergy1() {
    seamCarver.energy(-1, 300);
  }
  
  @Test(expected = IndexOutOfBoundsException.class)
  public void testEnergy2() {
    seamCarver.energy(200, -1);
  }
  
  @Test(expected = IndexOutOfBoundsException.class)
  public void testEnergy3() {
    seamCarver.energy(200, 2448);
  }
  
  @Test(expected = IndexOutOfBoundsException.class)
  public void testEnergy4() {
    seamCarver.energy(3264, 2448);
  }
  
  @Test(expected = IndexOutOfBoundsException.class)
  public void testEnergy5() {
    seamCarver.energy(3265, 2449);
  }
  
  @Test
  public void testEnergy6() {
    Assert.assertEquals(213.614, seamCarver.energy(5, 6), 1.0);
  }
  
  @Test
  public void testEnergy7() {
    Assert.assertEquals(1000, seamCarver.energy(0, 6), 1.0);
  }
  
  @Test
  public void testEnergy8() {
    Assert.assertEquals(1000, seamCarver.energy(4, 0), 1.0);
  }
  
  @Test
  public void testEnergy9() {
    Assert.assertEquals(1000, seamCarver.energy(11, 5), 1.0);
  }
  
  @Test
  public void testEnergy10() {
    Assert.assertEquals(1000, seamCarver.energy(4, 9), 1.0);
  }
  
  @Test
  public void testEnergy11() {
    Assert.assertEquals(237.307, seamCarver.energy(6, 5), 1.0);
  }
  
  @Test
  public void testEnergy12() {
    Assert.assertEquals(343.91, seamCarver.energy(2, 3), 1.0);
  }
  
  @Test
  public void testEnergy13() {
    Assert.assertEquals(217.37, seamCarver.energy(9, 8), 1.0);
  }
  
  @Test
  public void testEnergy14() {
    Assert.assertEquals(99.89, seamCarver.energy(7, 7), 1.0);
  }
  
  @Test
  public void testEnergy15() {
    Assert.assertEquals(127.79, seamCarver.energy(10, 1), 1.0);
  }
  
  //@Test
  public void testEnergy16() throws IOException {
    int[] actual = seamCarver3.findVerticalSeam();
    double energyTotal = 0.0;
    for (int y = 0; y < seamCarver3.height(); y++) {
      energyTotal += seamCarver3.energy(actual[y], y);
    }
    //Assert.assertEquals(3443.1978197452986, energyTotal, 1.0);
    
    //2 3 4 3 4 3 3 2 2 1
    //3 3 4 3 4 3 3 2 2 1
    int[] expected = {3, 3, 4, 3, 4, 3, 3, 2, 2, 1};
    Assert.assertEquals(expected[0], actual[0]);
    Assert.assertEquals(expected[1], actual[1]);
    Assert.assertEquals(expected[2], actual[2]);
    Assert.assertEquals(expected[3], actual[3]);
    Assert.assertEquals(expected[4], actual[4]);
    Assert.assertEquals(expected[5], actual[5]);
    Assert.assertEquals(expected[6], actual[6]);
    Assert.assertEquals(expected[7], actual[7]);
    Assert.assertEquals(expected[8], actual[8]);
    Assert.assertEquals(expected[9], actual[9]);
  }
  
  //1 2 2 3 2 1
  @Test
  public void testFindVerticalSeam3() {
    int[] actual = seamCarver4.findVerticalSeam();
    Assert.assertEquals(6, actual.length);
    
    int[] expected = {1, 2, 2, 3, 2, 1};
    Assert.assertEquals(expected[0], actual[0]);
    Assert.assertEquals(expected[1], actual[1]);
    Assert.assertEquals(expected[2], actual[2]);
    Assert.assertEquals(expected[3], actual[3]);
    Assert.assertEquals(expected[4], actual[4]);
    Assert.assertEquals(expected[5], actual[5]);
  }
  
  // 6 7 7 6 6 7 7 7 8 7
  // 7 7 7 6 6 7 7 7 8 7
  @Test
  public void testFindVerticalSeam() {
    int[] actual = seamCarver.findVerticalSeam();
    Assert.assertEquals(10, actual.length);
    
    int[] expected = {6, 7, 7, 6, 6, 7, 7, 7, 8, 7};
    Assert.assertEquals(expected[0], actual[0]);
    Assert.assertEquals(expected[1], actual[1]);
    Assert.assertEquals(expected[2], actual[2]);
    Assert.assertEquals(expected[3], actual[3]);
    Assert.assertEquals(expected[4], actual[4]);
    Assert.assertEquals(expected[5], actual[5]);
    Assert.assertEquals(expected[6], actual[6]);
    Assert.assertEquals(expected[7], actual[7]);
    Assert.assertEquals(expected[8], actual[8]);
    Assert.assertEquals(expected[9], actual[9]);
  }

  // 7 8 7 8 7 6 5 6 6 5 4 3
  // 7 8 7 8 7 6 5 6 6 5 4 3
  @Test
  public void testFindHorizontalSeam() {
    int[] actual = seamCarver.findHorizontalSeam();
    Assert.assertEquals(12, actual.length);
    
    int[] expected = {7, 8, 7, 8, 7, 6, 5, 6, 6, 5, 4, 3};
    Assert.assertEquals(expected[0], actual[0]);
    Assert.assertEquals(expected[1], actual[1]);
    Assert.assertEquals(expected[2], actual[2]);
    Assert.assertEquals(expected[3], actual[3]);
    Assert.assertEquals(expected[4], actual[4]);
    Assert.assertEquals(expected[5], actual[5]);
    Assert.assertEquals(expected[6], actual[6]);
    Assert.assertEquals(expected[7], actual[7]);
    Assert.assertEquals(expected[8], actual[8]);
    Assert.assertEquals(expected[9], actual[9]);
    Assert.assertEquals(expected[10], actual[10]);
    Assert.assertEquals(expected[11], actual[11]);
  }
  
  //0 1 1 0
  //0 1 1 0
  @Test
  public void testFindVerticalSeam1() {
    int[] actual = seamCarver1.findVerticalSeam();
    Assert.assertEquals(4, actual.length);
    
    int[] expected = {0, 1, 1, 0};
    Assert.assertEquals(expected[0], actual[0]);
    Assert.assertEquals(expected[1], actual[1]);
    Assert.assertEquals(expected[2], actual[2]);
    Assert.assertEquals(expected[3], actual[3]);
  }

  // 1 2 1
  // 1 2 1
  @Test
  public void testFindHorizontalSeam1() {
    int[] actual = seamCarver1.findHorizontalSeam();
    Assert.assertEquals(3, actual.length);
    
    int[] expected = {1, 2, 1};
    Assert.assertEquals(expected[0], actual[0]);
    Assert.assertEquals(expected[1], actual[1]);
    Assert.assertEquals(expected[2], actual[2]);
  }
  
  //0 1 1 1 1 1 1 1 1 1 1 0
  //0 1 1 1 1 1 1 1 1 1 1 0
  @Test
  public void testFindVerticalSeam2() {
    int[] actual = seamCarver2.findVerticalSeam();
    Assert.assertEquals(12, actual.length);
    
    int[] expected = {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0};
    Assert.assertEquals(expected[0], actual[0]);
    Assert.assertEquals(expected[1], actual[1]);
    Assert.assertEquals(expected[2], actual[2]);
    Assert.assertEquals(expected[3], actual[3]);
    Assert.assertEquals(expected[4], actual[4]);
    Assert.assertEquals(expected[5], actual[5]);
    Assert.assertEquals(expected[6], actual[6]);
    Assert.assertEquals(expected[7], actual[7]);
    Assert.assertEquals(expected[8], actual[8]);
    Assert.assertEquals(expected[9], actual[9]);
    Assert.assertEquals(expected[10], actual[10]);
    Assert.assertEquals(expected[11], actual[11]);
  }

  // 0 1 1 1 1 1 1 1 0 
  // 0 1 1 1 1 1 1 1 0 
  @Test
  public void testFindHorizontalSeam2() {
    int[] actual = seamCarver2.findHorizontalSeam();
    Assert.assertEquals(9, actual.length);
    
    int[] expected = {0, 1, 1, 1, 1, 1, 1, 1, 0};
    Assert.assertEquals(expected[0], actual[0]);
    Assert.assertEquals(expected[1], actual[1]);
    Assert.assertEquals(expected[2], actual[2]);
    Assert.assertEquals(expected[3], actual[3]);
    Assert.assertEquals(expected[4], actual[4]);
    Assert.assertEquals(expected[5], actual[5]);
    Assert.assertEquals(expected[6], actual[6]);
    Assert.assertEquals(expected[7], actual[7]);
    Assert.assertEquals(expected[8], actual[8]);
  }
  
}
