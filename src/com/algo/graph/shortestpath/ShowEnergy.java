package com.algo.graph.shortestpath;

/******************************************************************************
 *  Compilation:  javac ShowEnergy.java
 *  Execution:    java ShowEnergy input.png
 *  Dependencies: SeamCarver.java SCUtility.java
 *                
 *
 *  Read image from file specified as command line argument. Show original
 *  image (only useful if image is large enough).
 *
 ******************************************************************************/

import edu.princeton.cs.algs4.Picture;
import edu.princeton.cs.algs4.StdOut;

public class ShowEnergy {

    public static void main(String[] args) {
        Picture picture = new Picture("/Users/dhamod/Application data/Git Repo/"
            + "algorithms/seamcarving/seamCarving/7x10.png");
        
        //Picture picture = new Picture("/Users/dhamod/Application data/"
        //    + "Git Repo/algorithms/seamcarving/seamCarving/chameleon.png");
        
        StdOut.printf("image is %d columns by %d rows\n",
            picture.width(), picture.height());
        picture.show();        
        SeamCarver sc = new SeamCarver(picture);
        
        StdOut.printf("Displaying energy calculated for each pixel.\n");
        SCUtility.showEnergy(sc);

    }

}
