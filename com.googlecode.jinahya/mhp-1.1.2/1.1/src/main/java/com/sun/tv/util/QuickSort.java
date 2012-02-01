/*
 * Copyright (c) 1994-2000 Sun Microsystems, Inc. All Rights Reserved.
 *
 * Permission to use, copy, modify, and distribute this software
 * and its documentation for NON-COMMERCIAL purposes and without
 * fee is hereby granted provided that this copyright notice
 * appears in all copies. Please refer to the file "copyright.html"
 * for further important copyright and licensing information.
 *
 * SUN MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF
 * THE SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED
 * TO THE IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A
 * PARTICULAR PURPOSE, OR NON-INFRINGEMENT. SUN SHALL NOT BE LIABLE FOR
 * ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR
 * DISTRIBUTING THIS SOFTWARE OR ITS DERIVATIVES.
 */

package com.sun.tv.util;

import java.lang.String;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

/**
 **
 ** @version      1.2, 05 May 1996
 ** @author 	Tom Nevin
 **/

public class QuickSort implements CompareInterface {
	/**
	 ** A convienence method for sorting strings
	 **/
	public static void sort(String a[]) {
		if (a == null || a.length == 0)
			return;

		QuickSort qs = new QuickSort();

      		quicksort(a, 0, a.length - 1, qs);
	}

	public static String[] sort(Hashtable a) {
		return sort(a.keys(), a.size());
	}

	public static String[] sort(Vector a) {
		return sort(a.elements(), a.size());
	}

	/**
	 ** A convienence method for Enumeration strings
	 **/
	public static String[] sort(Enumeration a, int length) {
		if (a == null || length == 0)
			return null;

		String strs[] = new String[length];
		if (strs == null) 
			return null;

		int i = 0;
		while (a.hasMoreElements()) {
			strs[i] = (String)a.nextElement();
			i++;
		}

		sort(strs);

		return strs;
	}

	/**
	 ** Generalized sort executive, call this to compare anything
	 ** just provide the comparer interface.
	 **/
	public static void sort(Object a[], CompareInterface comparer) {
		if (a == null || a.length == 0)
			return;

      		quicksort(a, 0, a.length - 1, comparer);
	}

	/**
	 ** String Comparison routine, uses the comapreTo in the
	 ** String class.
	 **/
	public int compareTo(Object a, Object b) {
		String sa = (String)a;
		String sb = (String)b;
		return sa.compareTo(sb);
	}

	/**
	 ** This sort program uses the quicksort algorithm,
	 ** see just about any Computer book on the subject
	 ** of sorting algorithms, Knuth. 
	 **/
	private static void quicksort(
		Object a[], int l, int r, CompareInterface c) {
		if (l >= r)
			return;

		int i = l - 1;
		int j = r;
		Object T = null;

		// loop through the array until indices cross
		while (true) {
			while (c.compareTo(a[++i], a[r]) < 0)
				;
			while (j > 0 && c.compareTo(a[--j], a[r]) > 0)
				;
			T = a[i]; a[i] = a[j]; a[j] = T;
			if (j <= i)
				break;
		}

		a[j] = a[i]; a[i] = a[r]; a[r] = T;
		quicksort(a, l, i-1, c);
		quicksort(a, i+1, r, c);
	}
}
