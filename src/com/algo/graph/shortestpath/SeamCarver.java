package com.algo.graph.shortestpath;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.awt.Color;

import edu.princeton.cs.algs4.Picture;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

// y is row
// x is column
// y's limit is height
// x's limit is width
// (x, y) -> (col, row)
public class SeamCarver {

  private static final double DEFAULT_ENERGY = 1000.0;
  // Array of Maps. Each map contains column and RGB color
  // List size is equal to height
  // Map size is equal to width
  private List<Map<Integer, Color>> pixelMaps;
  private double[][] energyCache;
  private int[][] edgeTo;
  private double[][] totalEnergy;
  private int width;
  private int height;
  private Picture currentPicture;
  
  public SeamCarver(final Picture picture) {
    if (picture == null) {
      throw new NullPointerException();
    }

    currentPicture = picture;
    pixelMaps = new ArrayList<Map<Integer, Color>>();
    energyCache = new double[currentPicture.height()][currentPicture.width()];
    
    initialize();
    
    width = pixelMaps.get(0).size();
    height = pixelMaps.size();
  }
  
  public int width() {
    return width;
  }
  
  public int height() {
    return height;
  }

  public Picture picture() {
    if (currentPicture.width() == width() && currentPicture.height() == height()) {
      return currentPicture;
    }
    
    Picture newPicture = new Picture(width(), height());
    for (int y = 0; y < height(); y++) {
      for (int x = 0; x < width(); x++) {
        newPicture.set(x, y, pixelMaps.get(y).get(x));
      }
    }
    currentPicture = newPicture;
    return currentPicture;
  }
  
  public double energy(int x, int y) {
    
    if (y < 0 || y > (height() - 1) 
        || x < 0 || x > (width() - 1)) {
      throw new IndexOutOfBoundsException("x is " + x + " y is " + y);
    }
    
    if (y == 0 || y == (height() - 1) 
        || x == 0 || x == (width() - 1)) {
      energyCache[y][x] = DEFAULT_ENERGY;
      return DEFAULT_ENERGY;
    }
    
    if (energyCache[y][x] != Double.POSITIVE_INFINITY) {
      return energyCache[y][x];
    }
    
    int redY = getColorAt(x, y+1).getRed() - getColorAt(x, y-1).getRed();
    int greenY = getColorAt(x, y+1).getGreen() - getColorAt(x, y-1).getGreen();
    int blueY = getColorAt(x, y+1).getBlue() - getColorAt(x, y-1).getBlue();
    
    int redX = getColorAt(x+1, y).getRed() - getColorAt(x-1, y).getRed();
    int greenX = getColorAt(x+1, y).getGreen() - getColorAt(x-1, y).getGreen();
    int blueX = getColorAt(x+1, y).getBlue() - getColorAt(x-1, y).getBlue();
    
    double deltaYsquare = (redY * redY) + (greenY * greenY) + (blueY * blueY);
    double deltaXsquare = (redX * redX) + (greenX * greenX) + (blueX * blueX);
    
    energyCache[y][x] = Math.sqrt(deltaXsquare + deltaYsquare);
    return energyCache[y][x];
  }
  
  public int[] findVerticalSeam() {
    
    SeamAndEnergy bestSeamAndEnergySoFar = null;
    SeamAndEnergy currentSeamAndEnergy = null;
    
    for (int x = 0; x < width(); x++) {
      currentSeamAndEnergy = findVerticalShortestPath(x);
      if (bestSeamAndEnergySoFar == null 
          || currentSeamAndEnergy.energyTotal < bestSeamAndEnergySoFar.energyTotal) {
        bestSeamAndEnergySoFar = currentSeamAndEnergy;
      }
    }
    
    return bestSeamAndEnergySoFar.seam;
  }
  
  public int[] findHorizontalSeam() {
    
    SeamAndEnergy bestSeamAndEnergySoFar = null;
    SeamAndEnergy currentSeamAndEnergy = null;
    
    for (int y = 0; y < height(); y++) {
      currentSeamAndEnergy = findHorizontalShortestPath(y);
      if (bestSeamAndEnergySoFar == null 
          || currentSeamAndEnergy.energyTotal < bestSeamAndEnergySoFar.energyTotal) {
        bestSeamAndEnergySoFar = currentSeamAndEnergy;
      }
    }
    
    return bestSeamAndEnergySoFar.seam;
  }
  
  public void removeHorizontalSeam(int[] seam) {
    if (seam == null) {
      throw new NullPointerException();
    }
    
    if (seam.length != width() || height() <= 1) {
      throw new IllegalArgumentException();
    }

    int prevSeam = -1;
    int currSeam = -1;
    for (int x = 0; x < width(); x++) {
      
      prevSeam = currSeam;
      currSeam = seam[x];
      
      if (currSeam < 0 || currSeam > height() - 1 
          || (prevSeam != -1 && Math.abs(currSeam - prevSeam) > 1)) {
        throw new IllegalArgumentException();
      }
      
      pixelMaps.get(currSeam).remove(x);
      removeEnergyForNeighbors(x, currSeam);
      movePixelsUp(x, currSeam+1);
    }
    setHeight(height() - 1);
  }
  
  public void removeVerticalSeam(int[] seam) {
    
    if (seam == null) {
      throw new NullPointerException();
    }
    
    if (seam.length != height() || width() <= 1) {
      throw new IllegalArgumentException();
    }
    
    int prevSeam = -1;
    int currSeam = -1;
    
    for (int y = 0; y < height(); y++) {
      
      prevSeam = currSeam;
      currSeam = seam[y];
      
      if (currSeam < 0 || currSeam > width() - 1
          || (prevSeam != -1 && Math.abs(currSeam - prevSeam) > 1)) {
        throw new IllegalArgumentException();
      }
      
      pixelMaps.get(y).remove(currSeam);
      removeEnergyForNeighbors(currSeam, y);
      movePixelsLeft(currSeam+1, y);
    }
    setWidth(width() - 1);
  }
  
  private void setWidth(int width) {
    this.width = width;
  }
  
  private void setHeight(int height) {
    this.height = height;
  }
  
  private void movePixelsUp(int x, int y) {
    for (; y < height(); y++) {
      Color color = pixelMaps.get(y).get(x);
      pixelMaps.get(y-1).put(x, color);
    }
  }    
  
  private void movePixelsLeft(int x, int y) {
    for (; x < width(); x++) {
      Color color = pixelMaps.get(y).remove(x);
      pixelMaps.get(y).put(x-1, color);
    }
  }
  
  private Color getColorAt(int x, int y) {
    return pixelMaps.get(y).get(x);
  }
  
  private void removeEnergyForNeighbors(int x, int y) {
    energyCache[y][x] = Double.POSITIVE_INFINITY;
    if (y != 0) {
      energyCache[y-1][x] = Double.POSITIVE_INFINITY;
    }
    if (y != height() - 1) {
      energyCache[y+1][x] = Double.POSITIVE_INFINITY;  
    }
    if (x != 0) {
      energyCache[y][x-1] = Double.POSITIVE_INFINITY;
    }
    if (x != width() - 1) {
      energyCache[y][x+1] = Double.POSITIVE_INFINITY;
    }
  }
  
  private void initialize() {
    
    // For each row
    for (int y = 0; y < currentPicture.height(); y++) {
      Map<Integer, Color> widthMap = new HashMap<>();

      // For each column
      for (int x = 0; x < currentPicture.width(); x++) {
        widthMap.put(x, currentPicture.get(x, y));
        energyCache[y][x] = Double.POSITIVE_INFINITY;
      }
      pixelMaps.add(widthMap);
    }
  }
  
  private SeamAndEnergy findVerticalShortestPath(int x) {
    
    edgeTo = new int[height()][width()];
    totalEnergy = new double[height()][width()];

    int y = 0;
    int prevX = x;
    int prevY = 0;
    int oddOrEven = 1;
    double bestEnergyTotalSofar = Double.POSITIVE_INFINITY;
    int bestX = -1;
    
    for (int j = 0; j < height(); j++) {
      for (int i = 0; i < width(); i++) {
        edgeTo[j][i] = -1;
      }
    }
    
    edgeTo[y][x] = x;
    totalEnergy[y][x] = energy(x, y);
    
    while (prevY != (height() -1)) {

      relaxPixelAt(x-1, y+1, x, y);
      relaxPixelAt(x, y+1, x, y);
      relaxPixelAt(x+1, y+1, x, y);
      
      x++;
      y++;
      
      if (x >= width() || y >= height()) {
        oddOrEven++;
        x = prevX;
        y = prevY;
        if (oddOrEven % 2 == 0) {
          y++;
        } else {
          if (x != 0) {
            x--;
          } else {
            y++;
          }
        }
        prevX = x;
        prevY = y;
      }
    }
    
    for (x = 0; x < width(); x++) {
      double currentEnergy = totalEnergy[height()-1][x];
      if (currentEnergy != 0.0 && currentEnergy < bestEnergyTotalSofar) {
        bestEnergyTotalSofar =  currentEnergy;
        bestX = x;
      }
    }
    
    SeamAndEnergy seamAndEnergy = new SeamAndEnergy(height());
    seamAndEnergy.energyTotal = bestEnergyTotalSofar;
    // Go up to height to find the reverse of the seam
    for (y = height() - 1; y >= 0; y--) {
      seamAndEnergy.seam[y] = bestX;
      bestX = edgeTo[y][bestX];
    }
    
    return seamAndEnergy;
  }
  
  private void relaxPixelAt(int x, int y, int fromX, int fromY) {
    if (x < 0 || x > width() - 1
        || y < 0 || y > height() - 1) {
      // Nothing to relax
      return;
    }
    
    double energy = energy(x, y);
    if (totalEnergy[y][x] == 0.0
        || totalEnergy[y][x] > totalEnergy[fromY][fromX] + energy) {
      totalEnergy[y][x] = totalEnergy[fromY][fromX] + energy;
      edgeTo[y][x] = fromX;
    }
  }

  private SeamAndEnergy findHorizontalShortestPath(int y) {
    
    SeamAndEnergy seamAndEnergy = new SeamAndEnergy(width());
    
    // Transpose the image and do vertical scan
    return seamAndEnergy;
  }
  
  
  private class SeamAndEnergy {
    private int[] seam;
    private double energyTotal;
    
    SeamAndEnergy(int seamSize) {
      seam = new int[seamSize];
      energyTotal = 0.0;
    }
    
    public boolean equals(Object object) {
      if (object == null) {
        return false;
      }
      if (!(object.getClass().isAssignableFrom(SeamAndEnergy.class))) {
        return false;
      }
      if (object == this) {
        return true;
      }
      SeamAndEnergy anotherSeam = (SeamAndEnergy) object;
      return this.energyTotal == anotherSeam.energyTotal 
          && seamEquals(this.seam, anotherSeam.seam); 
    }
      
    public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + seam[0];
      return result;
    }
    
    private boolean seamEquals(int[] seam1, int[] seam2) {
      if (seam1.length != seam2.length) {
        return false;
      }
      
      for (int i = 0; i < seam1.length; i++) {
        if (seam1[i] != seam2[i]) {
          return false;
        }
      }
      return true;
    }
  }
  
  public static void main(String[] args) {
    //Picture inputImg = new Picture("/Users/dhamod/Application data/"
    //    + "Git Repo/algorithms/seamcarving/seamCarving/HJocean.png");
    
    //Picture inputImg = new Picture("/Users/dhamod/Application data/"
    //    + "Git Repo/algorithms/seamcarving/seamCarving/12x10.png");
    
    Picture inputImg = new Picture("/Users/dhamod/Application data/"
        + "Git Repo/algorithms/seamcarving/IMG_1317.JPG");
    
    //Picture inputImg = new Picture("/Users/dhamod/Application data/"
    //      + "Git Repo/algorithms/seamcarving/seamCarving/chameleon.png");
        
    int removeColumns = 2500;
    int removeRows = 1500; 

    StdOut.printf("image is %d columns by %d rows\n", 
        inputImg.width(), inputImg.height());
    SeamCarver sc = new SeamCarver(inputImg);

    Stopwatch sw = new Stopwatch();

    for (int i = 0; i < removeRows; i++) {
        int[] horizontalSeam = sc.findHorizontalSeam();
        sc.removeHorizontalSeam(horizontalSeam);
    }

    for (int i = 0; i < removeColumns; i++) {
        int[] verticalSeam = sc.findVerticalSeam();
        sc.removeVerticalSeam(verticalSeam);
    }
    Picture outputImg = sc.picture();

    StdOut.printf("new image size is %d columns by %d rows\n", 
        sc.width(), sc.height());

    StdOut.println("Resizing time: " + sw.elapsedTime() + " seconds.");
    inputImg.show();
    outputImg.show();
  }
}
