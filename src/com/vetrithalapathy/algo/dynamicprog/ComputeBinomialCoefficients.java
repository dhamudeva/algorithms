package com.vetrithalapathy.algo.dynamicprog;

/*
 * Given n and m, find out the binomial coefficient of it.
 * binomial coefficients, where (n
 *                               k) 
 * counts the number of ways to choose k things out of n possibilities.
 * 
 * Example problem is 
 * 
 * Paths Across a Grid — How many ways are there to travel from the upper-left
corner of an n × m grid to the lower-right corner by walking only down and to
the right? Every path must consist of n + m steps, n downward and m to the
right. Every path with a different set of downward moves is distinct, so there are
(n + m
 n)
such sets/paths.
 
 * It is easy to do with Pascal Triangle

 * Refer Programming Challenges - Chapter 6 Section 3.
 */

public class ComputeBinomialCoefficients {
	
	public static int MAXN = 100;
	
	public static void main(String[] args) {
		printBinomialCoefficients(4, 2);  // What are the possible ways to choose 2 committee member from 4 people
		printBinomialCoefficients(8, 5);  // What are the possible ways to choose 5 committee member from 8 people OR 
		// In 5x3 matrix, what are the ways you can only move downward and right to reach the lower-left from upper-right.
	}
	
	public static void printBinomialCoefficients(int n, int m) {
		
		int i, j;
		
		if (n > 100) {
			System.out.println("Not possible");
			return;
		}
		
		long binomialCoefficient[][] = new long[ComputeBinomialCoefficients.MAXN][ComputeBinomialCoefficients.MAXN];
		
		
		/*
		 * The below code basically constructs
		 * 1  0  0  0  0  0
		 * 1  1  0  0  0  0
		 * 1  2  1  0  0  0
		 * 1  3  3  1  0  0
		 * 1  4  6  4  1  0
		 * 1  5  10 10 5  1
		 * 
		 * which is nothing but Pascal Triangle. It only fills up half of the matrix
		 */
		
		for(i = 0; i <= n; i++) {
			binomialCoefficient[i][0] = 1; // 0 ways to choose is empty set, which is one.
		}
		
		for (j = 0; i <= n; j++) {
			binomialCoefficient[j][j] = 1; // n ways to choose from n possibilities is complete set; which is one.
		}
		
		for (i = 1; i <= n; i++) {
			for (j = 1; j < i; j++) {
				binomialCoefficient[i][j] = binomialCoefficient[i-1][j-1] + binomialCoefficient[i-1][j];
			}
		}
		
		System.out.println("Binomial Coefficient for " + n + " and " + m + " is :" + binomialCoefficient[n][m]);
	}
}
