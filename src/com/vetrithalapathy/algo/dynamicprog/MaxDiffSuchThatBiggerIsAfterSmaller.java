package com.vetrithalapathy.algo.dynamicprog;

/*
 * Maximum difference between two elements
 *
 * Given an array arr[] of integers, find out the difference between any two elements such that 
 * larger element appears after the smaller number in arr[].
 *
 * Examples: If array is [2, 3, 10, 6, 4, 8, 1] then returned value should be 8 (Diff between 10 and 2). 
 * If array is [ 7, 9, 5, 6, 3, 2 ] then returned value should be 2 (Diff between 7 and 9)
 */

public class MaxDiffSuchThatBiggerIsAfterSmaller {
	
	public static void main(String[] args) {
		//int input[] = {80, 2, 6, 3, 100};
		//int input[] = {2, 3, 10, 6, 4, 8, 1};
		int input[] = {7, 9, 5, 6, 3, 2};
		//int input[] = {1, 2, 6, 80, 100};
		//int input[] = {10,7,8,9,0};
		//int input[] = {10,10,10,10,10};
		//int input[] = {1,10};
		//int input[] = {1};
		printMaxAndElements(input);
	}

	public static void printMaxAndElements(int[] input) {

		int i = 0, j = 1, currentMax;
		if (input.length < 2) {
			System.out.println("No maximum difference is possible");
			return;
		}
		
		if (input[i] < input[j]) {
			currentMax = input[j] - input[i];
		} else {
			currentMax = input[i] - input[j];
			i = 1; j = 0;
		}
		
		for (int k = j+1; k < input.length; k++) {
	
			if ( (input[k] > input[j]) && (k > j)){
				j = k;
				currentMax = input[j] - input[i];
			} else if ( ( input[k] < input[i] ) && (k < j) ) {
				i = k;
				currentMax = input[j] - input[i];
			}
		}
		
		if (i > j) {
			System.out.println("No maximum difference is possible");
		} else {
			System.out.println("Maximum difference is " + currentMax);
			System.out.println("Small Element is " + input[i]);
			System.out.println("Big Element is " + input[j]);
		}
	}

}
