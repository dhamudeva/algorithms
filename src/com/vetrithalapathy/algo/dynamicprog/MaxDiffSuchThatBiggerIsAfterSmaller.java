package com.vetrithalapathy.algo.dynamicprog;

public class MaxDiffSuchThatBiggerIsAfterSmaller {
	
	i = 0; j = 1; currentMax = a[j] - a[i];
	for (k = j+1; k < n; k++) {

	if ( a[k] > a[j] ) {
	j = k;
	currentMax = a[j] - a[i];
	} else if ( ( a[k] < a[i] ) && (k < j) ) {
	i = k;
	currentMax = a[j] - a[i];
	}
	}

}
