/*
<p>This is not an official specification document, and usage is restricted.
</p>
<a name="notice"><strong><center>
NOTICE
</center></strong><br>
<br>

(c) 2005-2007 Sun Microsystems, Inc. All Rights Reserved.
<p>
Neither this file nor any files generated from it describe a complete
specification, and they may only be used as described below. For
example, no permission is given for you to incorporate this file, in
whole or in part, in an implementation of a Java specification.
<p>
Sun Microsystems Inc. owns the copyright in this file and it is provided
to you for informative, as opposed to normative, use. The file and any
files generated from it may be used to generate other informative
documentation, such as a unified set of documents of API signatures for
a platform that includes technologies expressed as Java APIs. The file
may also be used to produce "compilation stubs," which allow
applications to be compiled and validated for such platforms.
<p>
Any work generated from this file, such as unified javadocs or compiled
stub files, must be accompanied by this notice in its entirety.
<p>
This work corresponds to the API signatures of JSR 219: Foundation
Profile 1.1. In the event of a discrepency between this work and the
JSR 219 specification, which is available at
http://www.jcp.org/en/jsr/detail?id=219, the latter takes precedence.
*/


  


package java.util;

import java.lang.reflect.*;

/** 
 * This class contains various methods for manipulating arrays (such as
 * sorting and searching).  This class also contains a static factory 
 * that allows arrays to be viewed as lists.
 *
 * <p>The methods in this class all throw a <tt>NullPointerException</tt> if
 * the specified array reference is null.
 *
 * <p>The documentation for the methods contained in this class includes
 * briefs description of the <i>implementations</i>.  Such descriptions should
 * be regarded as <i>implementation notes</i>, rather than parts of the
 * <i>specification</i>.  Implementors should feel free to substitute other
 * algorithms, so long as the specification itself is adhered to.  (For
 * example, the algorithm used by <tt>sort(Object[])</tt> does not have to be
 * a mergesort, but it does have to be <i>stable</i>.)
 *
 * <p>This class is a member of the 
 * <a href="{@docRoot}/../guide/collections/index.html">
 * Java Collections Framework</a>.
 *
 * @author  Josh Bloch
 * @version 1.42, 03/12/05
 * @see     Comparable
 * @see     Comparator
 * @since   1.2
 */
public class Arrays
{

    /*
     * This hidden constructor does not necessarily correspond to
     * a constructor in the original source file -- it keeps javadoc
     * from generating an inappropriate default constructor.
     */
    private Arrays() { }

    /** 
     * Sorts the specified array of longs into ascending numerical order.
     * The sorting algorithm is a tuned quicksort, adapted from Jon
     * L. Bentley and M. Douglas McIlroy's "Engineering a Sort Function",
     * Software-Practice and Experience, Vol. 23(11) P. 1249-1265 (November
     * 1993).  This algorithm offers n*log(n) performance on many data sets
     * that cause other quicksorts to degrade to quadratic performance.
     *
     * @param a the array to be sorted.
     */
    public static void sort(long[] a) { }

    /** 
     * Sorts the specified range of the specified array of longs into
     * ascending numerical order.  The range to be sorted extends from index
     * <tt>fromIndex</tt>, inclusive, to index <tt>toIndex</tt>, exclusive.
     * (If <tt>fromIndex==toIndex</tt>, the range to be sorted is empty.)
     *
     * <p>The sorting algorithm is a tuned quicksort, adapted from Jon
     * L. Bentley and M. Douglas McIlroy's "Engineering a Sort Function",
     * Software-Practice and Experience, Vol. 23(11) P. 1249-1265 (November
     * 1993).  This algorithm offers n*log(n) performance on many data sets
     * that cause other quicksorts to degrade to quadratic performance.
     *
     * @param a the array to be sorted.
     * @param fromIndex the index of the first element (inclusive) to be
     *        sorted.
     * @param toIndex the index of the last element (exclusive) to be sorted.
     * @throws IllegalArgumentException if <tt>fromIndex &gt; toIndex</tt>
     * @throws ArrayIndexOutOfBoundsException if <tt>fromIndex &lt; 0</tt> or
     * <tt>toIndex &gt; a.length</tt>
     */
    public static void sort(long[] a, int fromIndex, int toIndex) { }

    /** 
     * Sorts the specified array of ints into ascending numerical order.
     * The sorting algorithm is a tuned quicksort, adapted from Jon
     * L. Bentley and M. Douglas McIlroy's "Engineering a Sort Function",
     * Software-Practice and Experience, Vol. 23(11) P. 1249-1265 (November
     * 1993).  This algorithm offers n*log(n) performance on many data sets
     * that cause other quicksorts to degrade to quadratic performance.
     *
     * @param a the array to be sorted.
     */
    public static void sort(int[] a) { }

    /** 
     * Sorts the specified range of the specified array of ints into
     * ascending numerical order.  The range to be sorted extends from index
     * <tt>fromIndex</tt>, inclusive, to index <tt>toIndex</tt>, exclusive.
     * (If <tt>fromIndex==toIndex</tt>, the range to be sorted is empty.)<p>
     *
     * The sorting algorithm is a tuned quicksort, adapted from Jon
     * L. Bentley and M. Douglas McIlroy's "Engineering a Sort Function",
     * Software-Practice and Experience, Vol. 23(11) P. 1249-1265 (November
     * 1993).  This algorithm offers n*log(n) performance on many data sets
     * that cause other quicksorts to degrade to quadratic performance.
     *
     * @param a the array to be sorted.
     * @param fromIndex the index of the first element (inclusive) to be
     *        sorted.
     * @param toIndex the index of the last element (exclusive) to be sorted.
     * @throws IllegalArgumentException if <tt>fromIndex &gt; toIndex</tt>
     * @throws ArrayIndexOutOfBoundsException if <tt>fromIndex &lt; 0</tt> or
     *	       <tt>toIndex &gt; a.length</tt>
     */
    public static void sort(int[] a, int fromIndex, int toIndex) { }

    /** 
     * Sorts the specified array of shorts into ascending numerical order.
     * The sorting algorithm is a tuned quicksort, adapted from Jon
     * L. Bentley and M. Douglas McIlroy's "Engineering a Sort Function",
     * Software-Practice and Experience, Vol. 23(11) P. 1249-1265 (November
     * 1993).  This algorithm offers n*log(n) performance on many data sets
     * that cause other quicksorts to degrade to quadratic performance.
     *
     * @param a the array to be sorted.
     */
    public static void sort(short[] a) { }

    /** 
     * Sorts the specified range of the specified array of shorts into
     * ascending numerical order.  The range to be sorted extends from index
     * <tt>fromIndex</tt>, inclusive, to index <tt>toIndex</tt>, exclusive.
     * (If <tt>fromIndex==toIndex</tt>, the range to be sorted is empty.)<p>
     *
     * The sorting algorithm is a tuned quicksort, adapted from Jon
     * L. Bentley and M. Douglas McIlroy's "Engineering a Sort Function",
     * Software-Practice and Experience, Vol. 23(11) P. 1249-1265 (November
     * 1993).  This algorithm offers n*log(n) performance on many data sets
     * that cause other quicksorts to degrade to quadratic performance.
     *
     * @param a the array to be sorted.
     * @param fromIndex the index of the first element (inclusive) to be
     *        sorted.
     * @param toIndex the index of the last element (exclusive) to be sorted.
     * @throws IllegalArgumentException if <tt>fromIndex &gt; toIndex</tt>
     * @throws ArrayIndexOutOfBoundsException if <tt>fromIndex &lt; 0</tt> or
     *	       <tt>toIndex &gt; a.length</tt>
     */
    public static void sort(short[] a, int fromIndex, int toIndex) { }

    /** 
     * Sorts the specified array of chars into ascending numerical order.
     * The sorting algorithm is a tuned quicksort, adapted from Jon
     * L. Bentley and M. Douglas McIlroy's "Engineering a Sort Function",
     * Software-Practice and Experience, Vol. 23(11) P. 1249-1265 (November
     * 1993).  This algorithm offers n*log(n) performance on many data sets
     * that cause other quicksorts to degrade to quadratic performance.
     *
     * @param a the array to be sorted.
     */
    public static void sort(char[] a) { }

    /** 
     * Sorts the specified range of the specified array of chars into
     * ascending numerical order.  The range to be sorted extends from index
     * <tt>fromIndex</tt>, inclusive, to index <tt>toIndex</tt>, exclusive.
     * (If <tt>fromIndex==toIndex</tt>, the range to be sorted is empty.)<p>
     *
     * The sorting algorithm is a tuned quicksort, adapted from Jon
     * L. Bentley and M. Douglas McIlroy's "Engineering a Sort Function",
     * Software-Practice and Experience, Vol. 23(11) P. 1249-1265 (November
     * 1993).  This algorithm offers n*log(n) performance on many data sets
     * that cause other quicksorts to degrade to quadratic performance.
     *
     * @param a the array to be sorted.
     * @param fromIndex the index of the first element (inclusive) to be
     *        sorted.
     * @param toIndex the index of the last element (exclusive) to be sorted.
     * @throws IllegalArgumentException if <tt>fromIndex &gt; toIndex</tt>
     * @throws ArrayIndexOutOfBoundsException if <tt>fromIndex &lt; 0</tt> or
     *	       <tt>toIndex &gt; a.length</tt>
     */
    public static void sort(char[] a, int fromIndex, int toIndex) { }

    /** 
     * Sorts the specified array of bytes into ascending numerical order.
     * The sorting algorithm is a tuned quicksort, adapted from Jon
     * L. Bentley and M. Douglas McIlroy's "Engineering a Sort Function",
     * Software-Practice and Experience, Vol. 23(11) P. 1249-1265 (November
     * 1993).  This algorithm offers n*log(n) performance on many data sets
     * that cause other quicksorts to degrade to quadratic performance.
     *
     * @param a the array to be sorted.
     */
    public static void sort(byte[] a) { }

    /** 
     * Sorts the specified range of the specified array of bytes into
     * ascending numerical order.  The range to be sorted extends from index
     * <tt>fromIndex</tt>, inclusive, to index <tt>toIndex</tt>, exclusive.
     * (If <tt>fromIndex==toIndex</tt>, the range to be sorted is empty.)<p>
     *
     * The sorting algorithm is a tuned quicksort, adapted from Jon
     * L. Bentley and M. Douglas McIlroy's "Engineering a Sort Function",
     * Software-Practice and Experience, Vol. 23(11) P. 1249-1265 (November
     * 1993).  This algorithm offers n*log(n) performance on many data sets
     * that cause other quicksorts to degrade to quadratic performance.
     *
     * @param a the array to be sorted.
     * @param fromIndex the index of the first element (inclusive) to be
     *        sorted.
     * @param toIndex the index of the last element (exclusive) to be sorted.
     * @throws IllegalArgumentException if <tt>fromIndex &gt; toIndex</tt>
     * @throws ArrayIndexOutOfBoundsException if <tt>fromIndex &lt; 0</tt> or
     *	       <tt>toIndex &gt; a.length</tt>
     */
    public static void sort(byte[] a, int fromIndex, int toIndex) { }

    /** 
     * Sorts the specified array of doubles into ascending numerical order.
     * <p>
     * The <code>&lt;</code> relation does not provide a total order on
     * all floating-point values; although they are distinct numbers
     * <code>-0.0 == 0.0</code> is <code>true</code> and a NaN value
     * compares neither less than, greater than, nor equal to any
     * floating-point value, even itself.  To allow the sort to
     * proceed, instead of using the <code>&lt;</code> relation to
     * determine ascending numerical order, this method uses the total
     * order imposed by {@link Double#compareTo}.  This ordering
     * differs from the <code>&lt;</code> relation in that
     * <code>-0.0</code> is treated as less than <code>0.0</code> and
     * NaN is considered greater than any other floating-point value.
     * For the purposes of sorting, all NaN values are considered
     * equivalent and equal.
     * <p>
     * The sorting algorithm is a tuned quicksort, adapted from Jon
     * L. Bentley and M. Douglas McIlroy's "Engineering a Sort Function",
     * Software-Practice and Experience, Vol. 23(11) P. 1249-1265 (November
     * 1993).  This algorithm offers n*log(n) performance on many data sets
     * that cause other quicksorts to degrade to quadratic performance.
     *
     * @param a the array to be sorted.
     */
    public static void sort(double[] a) { }

    /** 
     * Sorts the specified range of the specified array of doubles into
     * ascending numerical order.  The range to be sorted extends from index
     * <tt>fromIndex</tt>, inclusive, to index <tt>toIndex</tt>, exclusive.
     * (If <tt>fromIndex==toIndex</tt>, the range to be sorted is empty.)
     * <p>
     * The <code>&lt;</code> relation does not provide a total order on
     * all floating-point values; although they are distinct numbers
     * <code>-0.0 == 0.0</code> is <code>true</code> and a NaN value
     * compares neither less than, greater than, nor equal to any
     * floating-point value, even itself.  To allow the sort to
     * proceed, instead of using the <code>&lt;</code> relation to
     * determine ascending numerical order, this method uses the total
     * order imposed by {@link Double#compareTo}.  This ordering
     * differs from the <code>&lt;</code> relation in that
     * <code>-0.0</code> is treated as less than <code>0.0</code> and
     * NaN is considered greater than any other floating-point value.
     * For the purposes of sorting, all NaN values are considered
     * equivalent and equal.
     * <p>
     * The sorting algorithm is a tuned quicksort, adapted from Jon
     * L. Bentley and M. Douglas McIlroy's "Engineering a Sort Function",
     * Software-Practice and Experience, Vol. 23(11) P. 1249-1265 (November
     * 1993).  This algorithm offers n*log(n) performance on many data sets
     * that cause other quicksorts to degrade to quadratic performance.
     *
     * @param a the array to be sorted.
     * @param fromIndex the index of the first element (inclusive) to be
     *        sorted.
     * @param toIndex the index of the last element (exclusive) to be sorted.
     * @throws IllegalArgumentException if <tt>fromIndex &gt; toIndex</tt>
     * @throws ArrayIndexOutOfBoundsException if <tt>fromIndex &lt; 0</tt> or
     *	       <tt>toIndex &gt; a.length</tt>
     */
    public static void sort(double[] a, int fromIndex, int toIndex) { }

    /** 
     * Sorts the specified array of floats into ascending numerical order.
     * <p>
     * The <code>&lt;</code> relation does not provide a total order on
     * all floating-point values; although they are distinct numbers
     * <code>-0.0f == 0.0f</code> is <code>true</code> and a NaN value
     * compares neither less than, greater than, nor equal to any
     * floating-point value, even itself.  To allow the sort to
     * proceed, instead of using the <code>&lt;</code> relation to
     * determine ascending numerical order, this method uses the total
     * order imposed by {@link Float#compareTo}.  This ordering
     * differs from the <code>&lt;</code> relation in that
     * <code>-0.0f</code> is treated as less than <code>0.0f</code> and
     * NaN is considered greater than any other floating-point value.
     * For the purposes of sorting, all NaN values are considered
     * equivalent and equal.
     * <p>
     * The sorting algorithm is a tuned quicksort, adapted from Jon
     * L. Bentley and M. Douglas McIlroy's "Engineering a Sort Function",
     * Software-Practice and Experience, Vol. 23(11) P. 1249-1265 (November
     * 1993).  This algorithm offers n*log(n) performance on many data sets
     * that cause other quicksorts to degrade to quadratic performance.
     *
     * @param a the array to be sorted.
     */
    public static void sort(float[] a) { }

    /** 
     * Sorts the specified range of the specified array of floats into
     * ascending numerical order.  The range to be sorted extends from index
     * <tt>fromIndex</tt>, inclusive, to index <tt>toIndex</tt>, exclusive.
     * (If <tt>fromIndex==toIndex</tt>, the range to be sorted is empty.)
     * <p>
     * The <code>&lt;</code> relation does not provide a total order on
     * all floating-point values; although they are distinct numbers
     * <code>-0.0f == 0.0f</code> is <code>true</code> and a NaN value
     * compares neither less than, greater than, nor equal to any
     * floating-point value, even itself.  To allow the sort to
     * proceed, instead of using the <code>&lt;</code> relation to
     * determine ascending numerical order, this method uses the total
     * order imposed by {@link Float#compareTo}.  This ordering
     * differs from the <code>&lt;</code> relation in that
     * <code>-0.0f</code> is treated as less than <code>0.0f</code> and
     * NaN is considered greater than any other floating-point value.
     * For the purposes of sorting, all NaN values are considered
     * equivalent and equal.
     * <p>
     * The sorting algorithm is a tuned quicksort, adapted from Jon
     * L. Bentley and M. Douglas McIlroy's "Engineering a Sort Function",
     * Software-Practice and Experience, Vol. 23(11) P. 1249-1265 (November
     * 1993).  This algorithm offers n*log(n) performance on many data sets
     * that cause other quicksorts to degrade to quadratic performance.
     *
     * @param a the array to be sorted.
     * @param fromIndex the index of the first element (inclusive) to be
     *        sorted.
     * @param toIndex the index of the last element (exclusive) to be sorted.
     * @throws IllegalArgumentException if <tt>fromIndex &gt; toIndex</tt>
     * @throws ArrayIndexOutOfBoundsException if <tt>fromIndex &lt; 0</tt> or
     *	       <tt>toIndex &gt; a.length</tt>
     */
    public static void sort(float[] a, int fromIndex, int toIndex) { }

    /** 
     * Sorts the specified array of objects into ascending order, according to
     * the <i>natural ordering</i> of its elements.  All elements in the array
     * must implement the <tt>Comparable</tt> interface.  Furthermore, all
     * elements in the array must be <i>mutually comparable</i> (that is,
     * <tt>e1.compareTo(e2)</tt> must not throw a <tt>ClassCastException</tt>
     * for any elements <tt>e1</tt> and <tt>e2</tt> in the array).<p>
     *
     * This sort is guaranteed to be <i>stable</i>:  equal elements will
     * not be reordered as a result of the sort.<p>
     *
     * The sorting algorithm is a modified mergesort (in which the merge is
     * omitted if the highest element in the low sublist is less than the
     * lowest element in the high sublist).  This algorithm offers guaranteed
     * n*log(n) performance.
     * 
     * @param a the array to be sorted.
     * @throws  ClassCastException if the array contains elements that are not
     *		<i>mutually comparable</i> (for example, strings and integers).
     * @see Comparable
     */
    public static void sort(Object[] a) { }

    /** 
     * Sorts the specified range of the specified array of objects into
     * ascending order, according to the <i>natural ordering</i> of its
     * elements.  The range to be sorted extends from index
     * <tt>fromIndex</tt>, inclusive, to index <tt>toIndex</tt>, exclusive.
     * (If <tt>fromIndex==toIndex</tt>, the range to be sorted is empty.)  All
     * elements in this range must implement the <tt>Comparable</tt>
     * interface.  Furthermore, all elements in this range must be <i>mutually
     * comparable</i> (that is, <tt>e1.compareTo(e2)</tt> must not throw a
     * <tt>ClassCastException</tt> for any elements <tt>e1</tt> and
     * <tt>e2</tt> in the array).<p>
     *
     * This sort is guaranteed to be <i>stable</i>:  equal elements will
     * not be reordered as a result of the sort.<p>
     *
     * The sorting algorithm is a modified mergesort (in which the merge is
     * omitted if the highest element in the low sublist is less than the
     * lowest element in the high sublist).  This algorithm offers guaranteed
     * n*log(n) performance.
     * 
     * @param a the array to be sorted.
     * @param fromIndex the index of the first element (inclusive) to be
     *        sorted.
     * @param toIndex the index of the last element (exclusive) to be sorted.
     * @throws IllegalArgumentException if <tt>fromIndex &gt; toIndex</tt>
     * @throws ArrayIndexOutOfBoundsException if <tt>fromIndex &lt; 0</tt> or
     *	       <tt>toIndex &gt; a.length</tt>
     * @throws    ClassCastException if the array contains elements that are
     *		  not <i>mutually comparable</i> (for example, strings and
     *		  integers).
     * @see Comparable
     */
    public static void sort(Object[] a, int fromIndex, int toIndex) { }

    /** 
     * Sorts the specified array of objects according to the order induced by
     * the specified comparator.  All elements in the array must be
     * <i>mutually comparable</i> by the specified comparator (that is,
     * <tt>c.compare(e1, e2)</tt> must not throw a <tt>ClassCastException</tt>
     * for any elements <tt>e1</tt> and <tt>e2</tt> in the array).<p>
     *
     * This sort is guaranteed to be <i>stable</i>:  equal elements will
     * not be reordered as a result of the sort.<p>
     *
     * The sorting algorithm is a modified mergesort (in which the merge is
     * omitted if the highest element in the low sublist is less than the
     * lowest element in the high sublist).  This algorithm offers guaranteed
     * n*log(n) performance. 
     *
     * @param a the array to be sorted.
     * @param c the comparator to determine the order of the array.  A
     *        <tt>null</tt> value indicates that the elements' <i>natural
     *        ordering</i> should be used.
     * @throws  ClassCastException if the array contains elements that are
     *		not <i>mutually comparable</i> using the specified comparator.
     * @see Comparator
     */
    public static void sort(Object[] a, Comparator c) { }

    /** 
     * Sorts the specified range of the specified array of objects according
     * to the order induced by the specified comparator.  The range to be
     * sorted extends from index <tt>fromIndex</tt>, inclusive, to index
     * <tt>toIndex</tt>, exclusive.  (If <tt>fromIndex==toIndex</tt>, the
     * range to be sorted is empty.)  All elements in the range must be
     * <i>mutually comparable</i> by the specified comparator (that is,
     * <tt>c.compare(e1, e2)</tt> must not throw a <tt>ClassCastException</tt>
     * for any elements <tt>e1</tt> and <tt>e2</tt> in the range).<p>
     *
     * This sort is guaranteed to be <i>stable</i>:  equal elements will
     * not be reordered as a result of the sort.<p>
     *
     * The sorting algorithm is a modified mergesort (in which the merge is
     * omitted if the highest element in the low sublist is less than the
     * lowest element in the high sublist).  This algorithm offers guaranteed
     * n*log(n) performance. 
     *
     * @param a the array to be sorted.
     * @param fromIndex the index of the first element (inclusive) to be
     *        sorted.
     * @param toIndex the index of the last element (exclusive) to be sorted.
     * @param c the comparator to determine the order of the array.  A
     *        <tt>null</tt> value indicates that the elements' <i>natural
     *        ordering</i> should be used.
     * @throws ClassCastException if the array contains elements that are not
     *	       <i>mutually comparable</i> using the specified comparator.
     * @throws IllegalArgumentException if <tt>fromIndex &gt; toIndex</tt>
     * @throws ArrayIndexOutOfBoundsException if <tt>fromIndex &lt; 0</tt> or
     *	       <tt>toIndex &gt; a.length</tt>
     * @see Comparator
     */
    public static void sort(Object[] a, int fromIndex, int toIndex, Comparator
        c)
    { }

    /** 
     * Searches the specified array of longs for the specified value using the
     * binary search algorithm.  The array <strong>must</strong> be sorted (as
     * by the <tt>sort</tt> method, above) prior to making this call.  If it
     * is not sorted, the results are undefined.  If the array contains
     * multiple elements with the specified value, there is no guarantee which
     * one will be found.
     *
     * @param a the array to be searched.
     * @param key the value to be searched for.
     * @return index of the search key, if it is contained in the list;
     *	       otherwise, <tt>(-(<i>insertion point</i>) - 1)</tt>.  The
     *	       <i>insertion point</i> is defined as the point at which the
     *	       key would be inserted into the list: the index of the first
     *	       element greater than the key, or <tt>list.size()</tt>, if all
     *	       elements in the list are less than the specified key.  Note
     *	       that this guarantees that the return value will be &gt;= 0 if
     *	       and only if the key is found.
     * @see #sort(long[])
     */
    public static int binarySearch(long[] a, long key) {
        return 0;
    }

    /** 
     * Searches the specified array of ints for the specified value using the
     * binary search algorithm.  The array <strong>must</strong> be sorted (as
     * by the <tt>sort</tt> method, above) prior to making this call.  If it
     * is not sorted, the results are undefined.  If the array contains
     * multiple elements with the specified value, there is no guarantee which
     * one will be found.
     *
     * @param a the array to be searched.
     * @param key the value to be searched for.
     * @return index of the search key, if it is contained in the list;
     *	       otherwise, <tt>(-(<i>insertion point</i>) - 1)</tt>.  The
     *	       <i>insertion point</i> is defined as the point at which the
     *	       key would be inserted into the list: the index of the first
     *	       element greater than the key, or <tt>list.size()</tt>, if all
     *	       elements in the list are less than the specified key.  Note
     *	       that this guarantees that the return value will be &gt;= 0 if
     *	       and only if the key is found.
     * @see #sort(int[])
     */
    public static int binarySearch(int[] a, int key) {
        return 0;
    }

    /** 
     * Searches the specified array of shorts for the specified value using
     * the binary search algorithm.  The array <strong>must</strong> be sorted
     * (as by the <tt>sort</tt> method, above) prior to making this call.  If
     * it is not sorted, the results are undefined.  If the array contains
     * multiple elements with the specified value, there is no guarantee which
     * one will be found.
     *
     * @param a the array to be searched.
     * @param key the value to be searched for.
     * @return index of the search key, if it is contained in the list;
     *	       otherwise, <tt>(-(<i>insertion point</i>) - 1)</tt>.  The
     *	       <i>insertion point</i> is defined as the point at which the
     *	       key would be inserted into the list: the index of the first
     *	       element greater than the key, or <tt>list.size()</tt>, if all
     *	       elements in the list are less than the specified key.  Note
     *	       that this guarantees that the return value will be &gt;= 0 if
     *	       and only if the key is found.
     * @see #sort(short[])
     */
    public static int binarySearch(short[] a, short key) {
        return 0;
    }

    /** 
     * Searches the specified array of chars for the specified value using the
     * binary search algorithm.  The array <strong>must</strong> be sorted (as
     * by the <tt>sort</tt> method, above) prior to making this call.  If it
     * is not sorted, the results are undefined.  If the array contains
     * multiple elements with the specified value, there is no guarantee which
     * one will be found.
     *
     * @param a the array to be searched.
     * @param key the value to be searched for.
     * @return index of the search key, if it is contained in the list;
     *	       otherwise, <tt>(-(<i>insertion point</i>) - 1)</tt>.  The
     *	       <i>insertion point</i> is defined as the point at which the
     *	       key would be inserted into the list: the index of the first
     *	       element greater than the key, or <tt>list.size()</tt>, if all
     *	       elements in the list are less than the specified key.  Note
     *	       that this guarantees that the return value will be &gt;= 0 if
     *	       and only if the key is found.
     * @see #sort(char[])
     */
    public static int binarySearch(char[] a, char key) {
        return 0;
    }

    /** 
     * Searches the specified array of bytes for the specified value using the
     * binary search algorithm.  The array <strong>must</strong> be sorted (as
     * by the <tt>sort</tt> method, above) prior to making this call.  If it
     * is not sorted, the results are undefined.  If the array contains
     * multiple elements with the specified value, there is no guarantee which
     * one will be found.
     *
     * @param a the array to be searched.
     * @param key the value to be searched for.
     * @return index of the search key, if it is contained in the list;
     *	       otherwise, <tt>(-(<i>insertion point</i>) - 1)</tt>.  The
     *	       <i>insertion point</i> is defined as the point at which the
     *	       key would be inserted into the list: the index of the first
     *	       element greater than the key, or <tt>list.size()</tt>, if all
     *	       elements in the list are less than the specified key.  Note
     *	       that this guarantees that the return value will be &gt;= 0 if
     *	       and only if the key is found.
     * @see #sort(byte[])
     */
    public static int binarySearch(byte[] a, byte key) {
        return 0;
    }

    /** 
     * Searches the specified array of doubles for the specified value using
     * the binary search algorithm.  The array <strong>must</strong> be sorted
     * (as by the <tt>sort</tt> method, above) prior to making this call.  If
     * it is not sorted, the results are undefined.  If the array contains
     * multiple elements with the specified value, there is no guarantee which
     * one will be found.  This method considers all NaN values to be 
     * equivalent and equal.
     *
     * @param a the array to be searched.
     * @param key the value to be searched for.
     * @return index of the search key, if it is contained in the list;
     *	       otherwise, <tt>(-(<i>insertion point</i>) - 1)</tt>.  The
     *	       <i>insertion point</i> is defined as the point at which the
     *	       key would be inserted into the list: the index of the first
     *	       element greater than the key, or <tt>list.size()</tt>, if all
     *	       elements in the list are less than the specified key.  Note
     *	       that this guarantees that the return value will be &gt;= 0 if
     *	       and only if the key is found.
     * @see #sort(double[])
     */
    public static int binarySearch(double[] a, double key) {
        return 0;
    }

    /** 
     * Searches the specified array of floats for the specified value using
     * the binary search algorithm.  The array <strong>must</strong> be sorted
     * (as by the <tt>sort</tt> method, above) prior to making this call.  If
     * it is not sorted, the results are undefined.  If the array contains
     * multiple elements with the specified value, there is no guarantee which
     * one will be found.  This method considers all NaN values to be 
     * equivalent and equal.
     *
     * @param a the array to be searched.
     * @param key the value to be searched for.
     * @return index of the search key, if it is contained in the list;
     *	       otherwise, <tt>(-(<i>insertion point</i>) - 1)</tt>.  The
     *	       <i>insertion point</i> is defined as the point at which the
     *	       key would be inserted into the list: the index of the first
     *	       element greater than the key, or <tt>list.size()</tt>, if all
     *	       elements in the list are less than the specified key.  Note
     *	       that this guarantees that the return value will be &gt;= 0 if
     *	       and only if the key is found.
     * @see #sort(float[])
     */
    public static int binarySearch(float[] a, float key) {
        return 0;
    }

    /** 
     * Searches the specified array for the specified object using the binary
     * search algorithm.  The array must be sorted into ascending order
     * according to the <i>natural ordering</i> of its elements (as by
     * <tt>Sort(Object[]</tt>), above) prior to making this call.  If it is
     * not sorted, the results are undefined.
     * (If the array contains elements that are not  mutually comparable (for
     * example,strings and integers), it <i>cannot</i> be sorted according 
     * to the natural order of its elements, hence results are undefined.)
     *  If the array contains multiple
     * elements equal to the specified object, there is no guarantee which
     * one will be found.
     *
     * @param a the array to be searched.
     * @param key the value to be searched for.
     * @return index of the search key, if it is contained in the list;
     *	       otherwise, <tt>(-(<i>insertion point</i>) - 1)</tt>.  The
     *	       <i>insertion point</i> is defined as the point at which the
     *	       key would be inserted into the list: the index of the first
     *	       element greater than the key, or <tt>list.size()</tt>, if all
     *	       elements in the list are less than the specified key.  Note
     *	       that this guarantees that the return value will be &gt;= 0 if
     *	       and only if the key is found.
     * @throws ClassCastException if the search key in not comparable to the
     *         elements of the array.
     * @see Comparable
     * @see #sort(Object[])
     */
    public static int binarySearch(Object[] a, Object key) {
        return 0;
    }

    /** 
     * Searches the specified array for the specified object using the binary
     * search algorithm.  The array must be sorted into ascending order
     * according to the specified comparator (as by the <tt>Sort(Object[],
     * Comparator)</tt> method, above), prior to making this call.  If it is
     * not sorted, the results are undefined. 
     * If the array contains multiple
     * elements equal to the specified object, there is no guarantee which one
     * will be found.
     *
     * @param a the array to be searched.
     * @param key the value to be searched for.
     * @param c the comparator by which the array is ordered.  A
     *        <tt>null</tt> value indicates that the elements' <i>natural
     *        ordering</i> should be used.
     * @return index of the search key, if it is contained in the list;
     *	       otherwise, <tt>(-(<i>insertion point</i>) - 1)</tt>.  The
     *	       <i>insertion point</i> is defined as the point at which the
     *	       key would be inserted into the list: the index of the first
     *	       element greater than the key, or <tt>list.size()</tt>, if all
     *	       elements in the list are less than the specified key.  Note
     *	       that this guarantees that the return value will be &gt;= 0 if
     *	       and only if the key is found.
     * @throws ClassCastException if the array contains elements that are not
     *	       <i>mutually comparable</i> using the specified comparator,
     *	       or the search key in not mutually comparable with the
     *	       elements of the array using this comparator.
     * @see Comparable
     * @see #sort(Object[], Comparator)
     */
    public static int binarySearch(Object[] a, Object key, Comparator c) {
        return 0;
    }

    /** 
     * Returns <tt>true</tt> if the two specified arrays of longs are
     * <i>equal</i> to one another.  Two arrays are considered equal if both
     * arrays contain the same number of elements, and all corresponding pairs
     * of elements in the two arrays are equal.  In other words, two arrays
     * are equal if they contain the same elements in the same order.  Also,
     * two array references are considered equal if both are <tt>null</tt>.<p>
     *
     * @param a one array to be tested for equality.
     * @param a2 the other array to be tested for equality.
     * @return <tt>true</tt> if the two arrays are equal.
     */
    public static boolean equals(long[] a, long[] a2) {
        return false;
    }

    /** 
     * Returns <tt>true</tt> if the two specified arrays of ints are
     * <i>equal</i> to one another.  Two arrays are considered equal if both
     * arrays contain the same number of elements, and all corresponding pairs
     * of elements in the two arrays are equal.  In other words, two arrays
     * are equal if they contain the same elements in the same order.  Also,
     * two array references are considered equal if both are <tt>null</tt>.<p>
     *
     * @param a one array to be tested for equality.
     * @param a2 the other array to be tested for equality.
     * @return <tt>true</tt> if the two arrays are equal.
     */
    public static boolean equals(int[] a, int[] a2) {
        return false;
    }

    /** 
     * Returns <tt>true</tt> if the two specified arrays of shorts are
     * <i>equal</i> to one another.  Two arrays are considered equal if both
     * arrays contain the same number of elements, and all corresponding pairs
     * of elements in the two arrays are equal.  In other words, two arrays
     * are equal if they contain the same elements in the same order.  Also,
     * two array references are considered equal if both are <tt>null</tt>.<p>
     *
     * @param a one array to be tested for equality.
     * @param a2 the other array to be tested for equality.
     * @return <tt>true</tt> if the two arrays are equal.
     */
    public static boolean equals(short[] a, short[] a2) {
        return false;
    }

    /** 
     * Returns <tt>true</tt> if the two specified arrays of chars are
     * <i>equal</i> to one another.  Two arrays are considered equal if both
     * arrays contain the same number of elements, and all corresponding pairs
     * of elements in the two arrays are equal.  In other words, two arrays
     * are equal if they contain the same elements in the same order.  Also,
     * two array references are considered equal if both are <tt>null</tt>.<p>
     *
     * @param a one array to be tested for equality.
     * @param a2 the other array to be tested for equality.
     * @return <tt>true</tt> if the two arrays are equal.
     */
    public static boolean equals(char[] a, char[] a2) {
        return false;
    }

    /** 
     * Returns <tt>true</tt> if the two specified arrays of bytes are
     * <i>equal</i> to one another.  Two arrays are considered equal if both
     * arrays contain the same number of elements, and all corresponding pairs
     * of elements in the two arrays are equal.  In other words, two arrays
     * are equal if they contain the same elements in the same order.  Also,
     * two array references are considered equal if both are <tt>null</tt>.<p>
     *
     * @param a one array to be tested for equality.
     * @param a2 the other array to be tested for equality.
     * @return <tt>true</tt> if the two arrays are equal.
     */
    public static boolean equals(byte[] a, byte[] a2) {
        return false;
    }

    /** 
     * Returns <tt>true</tt> if the two specified arrays of booleans are
     * <i>equal</i> to one another.  Two arrays are considered equal if both
     * arrays contain the same number of elements, and all corresponding pairs
     * of elements in the two arrays are equal.  In other words, two arrays
     * are equal if they contain the same elements in the same order.  Also,
     * two array references are considered equal if both are <tt>null</tt>.<p>
     *
     * @param a one array to be tested for equality.
     * @param a2 the other array to be tested for equality.
     * @return <tt>true</tt> if the two arrays are equal.
     */
    public static boolean equals(boolean[] a, boolean[] a2) {
        return false;
    }

    /** 
     * Returns <tt>true</tt> if the two specified arrays of doubles are
     * <i>equal</i> to one another.  Two arrays are considered equal if both
     * arrays contain the same number of elements, and all corresponding pairs
     * of elements in the two arrays are equal.  In other words, two arrays
     * are equal if they contain the same elements in the same order.  Also,
     * two array references are considered equal if both are <tt>null</tt>.<p>
     *
     * Two doubles <tt>d1</tt> and <tt>d2</tt> are considered equal if:
     * <pre>    <tt>new Double(d1).equals(new Double(d2))</tt></pre>
     * (Unlike the <tt>==</tt> operator, this method considers
     * <tt>NaN</tt> equals to itself, and 0.0d unequal to -0.0d.)
     *
     * @param a one array to be tested for equality.
     * @param a2 the other array to be tested for equality.
     * @return <tt>true</tt> if the two arrays are equal.
     * @see Double#equals(Object)
     */
    public static boolean equals(double[] a, double[] a2) {
        return false;
    }

    /** 
     * Returns <tt>true</tt> if the two specified arrays of floats are
     * <i>equal</i> to one another.  Two arrays are considered equal if both
     * arrays contain the same number of elements, and all corresponding pairs
     * of elements in the two arrays are equal.  In other words, two arrays
     * are equal if they contain the same elements in the same order.  Also,
     * two array references are considered equal if both are <tt>null</tt>.<p>
     *
     * Two floats <tt>f1</tt> and <tt>f2</tt> are considered equal if:
     * <pre>    <tt>new Float(f1).equals(new Float(f2))</tt></pre>
     * (Unlike the <tt>==</tt> operator, this method considers
     * <tt>NaN</tt> equals to itself, and 0.0f unequal to -0.0f.)
     *
     * @param a one array to be tested for equality.
     * @param a2 the other array to be tested for equality.
     * @return <tt>true</tt> if the two arrays are equal.
     * @see Float#equals(Object)
     */
    public static boolean equals(float[] a, float[] a2) {
        return false;
    }

    /** 
     * Returns <tt>true</tt> if the two specified arrays of Objects are
     * <i>equal</i> to one another.  The two arrays are considered equal if
     * both arrays contain the same number of elements, and all corresponding
     * pairs of elements in the two arrays are equal.  Two objects <tt>e1</tt>
     * and <tt>e2</tt> are considered <i>equal</i> if <tt>(e1==null ? e2==null
     * : e1.equals(e2))</tt>.  In other words, the two arrays are equal if
     * they contain the same elements in the same order.  Also, two array
     * references are considered equal if both are <tt>null</tt>.<p>
     *
     * @param a one array to be tested for equality.
     * @param a2 the other array to be tested for equality.
     * @return <tt>true</tt> if the two arrays are equal.
     */
    public static boolean equals(Object[] a, Object[] a2) {
        return false;
    }

    /** 
     * Assigns the specified long value to each element of the specified array
     * of longs.
     *
     * @param a the array to be filled.
     * @param val the value to be stored in all elements of the array.
     */
    public static void fill(long[] a, long val) { }

    /** 
     * Assigns the specified long value to each element of the specified 
     * range of the specified array of longs.  The range to be filled
     * extends from index <tt>fromIndex</tt>, inclusive, to index
     * <tt>toIndex</tt>, exclusive.  (If <tt>fromIndex==toIndex</tt>, the 
     * range to be filled is empty.)
     *
     * @param a the array to be filled.
     * @param fromIndex the index of the first element (inclusive) to be
     *        filled with the specified value.
     * @param toIndex the index of the last element (exclusive) to be
     *        filled with the specified value.
     * @param val the value to be stored in all elements of the array.
     * @throws IllegalArgumentException if <tt>fromIndex &gt; toIndex</tt>
     * @throws ArrayIndexOutOfBoundsException if <tt>fromIndex &lt; 0</tt> or
     *	       <tt>toIndex &gt; a.length</tt>
     */
    public static void fill(long[] a, int fromIndex, int toIndex, long val) { }

    /** 
     * Assigns the specified int value to each element of the specified array
     * of ints.
     *
     * @param a the array to be filled.
     * @param val the value to be stored in all elements of the array.
     */
    public static void fill(int[] a, int val) { }

    /** 
     * Assigns the specified int value to each element of the specified 
     * range of the specified array of ints.  The range to be filled
     * extends from index <tt>fromIndex</tt>, inclusive, to index
     * <tt>toIndex</tt>, exclusive.  (If <tt>fromIndex==toIndex</tt>, the 
     * range to be filled is empty.)
     *
     * @param a the array to be filled.
     * @param fromIndex the index of the first element (inclusive) to be
     *        filled with the specified value.
     * @param toIndex the index of the last element (exclusive) to be
     *        filled with the specified value.
     * @param val the value to be stored in all elements of the array.
     * @throws IllegalArgumentException if <tt>fromIndex &gt; toIndex</tt>
     * @throws ArrayIndexOutOfBoundsException if <tt>fromIndex &lt; 0</tt> or
     *	       <tt>toIndex &gt; a.length</tt>
     */
    public static void fill(int[] a, int fromIndex, int toIndex, int val) { }

    /** 
     * Assigns the specified short value to each element of the specified array
     * of shorts.
     *
     * @param a the array to be filled.
     * @param val the value to be stored in all elements of the array.
     */
    public static void fill(short[] a, short val) { }

    /** 
     * Assigns the specified short value to each element of the specified 
     * range of the specified array of shorts.  The range to be filled
     * extends from index <tt>fromIndex</tt>, inclusive, to index
     * <tt>toIndex</tt>, exclusive.  (If <tt>fromIndex==toIndex</tt>, the 
     * range to be filled is empty.)
     *
     * @param a the array to be filled.
     * @param fromIndex the index of the first element (inclusive) to be
     *        filled with the specified value.
     * @param toIndex the index of the last element (exclusive) to be
     *        filled with the specified value.
     * @param val the value to be stored in all elements of the array.
     * @throws IllegalArgumentException if <tt>fromIndex &gt; toIndex</tt>
     * @throws ArrayIndexOutOfBoundsException if <tt>fromIndex &lt; 0</tt> or
     *	       <tt>toIndex &gt; a.length</tt>
     */
    public static void fill(short[] a, int fromIndex, int toIndex, short val)
    { }

    /** 
     * Assigns the specified char value to each element of the specified array
     * of chars.
     *
     * @param a the array to be filled.
     * @param val the value to be stored in all elements of the array.
     */
    public static void fill(char[] a, char val) { }

    /** 
     * Assigns the specified char value to each element of the specified 
     * range of the specified array of chars.  The range to be filled
     * extends from index <tt>fromIndex</tt>, inclusive, to index
     * <tt>toIndex</tt>, exclusive.  (If <tt>fromIndex==toIndex</tt>, the 
     * range to be filled is empty.)
     *
     * @param a the array to be filled.
     * @param fromIndex the index of the first element (inclusive) to be
     *        filled with the specified value.
     * @param toIndex the index of the last element (exclusive) to be
     *        filled with the specified value.
     * @param val the value to be stored in all elements of the array.
     * @throws IllegalArgumentException if <tt>fromIndex &gt; toIndex</tt>
     * @throws ArrayIndexOutOfBoundsException if <tt>fromIndex &lt; 0</tt> or
     *	       <tt>toIndex &gt; a.length</tt>
     */
    public static void fill(char[] a, int fromIndex, int toIndex, char val) { }

    /** 
     * Assigns the specified byte value to each element of the specified array
     * of bytes.
     *
     * @param a the array to be filled.
     * @param val the value to be stored in all elements of the array.
     */
    public static void fill(byte[] a, byte val) { }

    /** 
     * Assigns the specified byte value to each element of the specified 
     * range of the specified array of bytes.  The range to be filled
     * extends from index <tt>fromIndex</tt>, inclusive, to index
     * <tt>toIndex</tt>, exclusive.  (If <tt>fromIndex==toIndex</tt>, the 
     * range to be filled is empty.)
     *
     * @param a the array to be filled.
     * @param fromIndex the index of the first element (inclusive) to be
     *        filled with the specified value.
     * @param toIndex the index of the last element (exclusive) to be
     *        filled with the specified value.
     * @param val the value to be stored in all elements of the array.
     * @throws IllegalArgumentException if <tt>fromIndex &gt; toIndex</tt>
     * @throws ArrayIndexOutOfBoundsException if <tt>fromIndex &lt; 0</tt> or
     *	       <tt>toIndex &gt; a.length</tt>
     */
    public static void fill(byte[] a, int fromIndex, int toIndex, byte val) { }

    /** 
     * Assigns the specified boolean value to each element of the specified
     * array of booleans.
     *
     * @param a the array to be filled.
     * @param val the value to be stored in all elements of the array.
     */
    public static void fill(boolean[] a, boolean val) { }

    /** 
     * Assigns the specified boolean value to each element of the specified 
     * range of the specified array of booleans.  The range to be filled
     * extends from index <tt>fromIndex</tt>, inclusive, to index
     * <tt>toIndex</tt>, exclusive.  (If <tt>fromIndex==toIndex</tt>, the 
     * range to be filled is empty.)
     *
     * @param a the array to be filled.
     * @param fromIndex the index of the first element (inclusive) to be
     *        filled with the specified value.
     * @param toIndex the index of the last element (exclusive) to be
     *        filled with the specified value.
     * @param val the value to be stored in all elements of the array.
     * @throws IllegalArgumentException if <tt>fromIndex &gt; toIndex</tt>
     * @throws ArrayIndexOutOfBoundsException if <tt>fromIndex &lt; 0</tt> or
     *	       <tt>toIndex &gt; a.length</tt>
     */
    public static void fill(boolean[] a, int fromIndex, int toIndex, boolean
        val)
    { }

    /** 
     * Assigns the specified double value to each element of the specified
     * array of doubles.
     *
     * @param a the array to be filled.
     * @param val the value to be stored in all elements of the array.
     */
    public static void fill(double[] a, double val) { }

    /** 
     * Assigns the specified double value to each element of the specified 
     * range of the specified array of doubles.  The range to be filled
     * extends from index <tt>fromIndex</tt>, inclusive, to index
     * <tt>toIndex</tt>, exclusive.  (If <tt>fromIndex==toIndex</tt>, the 
     * range to be filled is empty.)
     *
     * @param a the array to be filled.
     * @param fromIndex the index of the first element (inclusive) to be
     *        filled with the specified value.
     * @param toIndex the index of the last element (exclusive) to be
     *        filled with the specified value.
     * @param val the value to be stored in all elements of the array.
     * @throws IllegalArgumentException if <tt>fromIndex &gt; toIndex</tt>
     * @throws ArrayIndexOutOfBoundsException if <tt>fromIndex &lt; 0</tt> or
     *	       <tt>toIndex &gt; a.length</tt>
     */
    public static void fill(double[] a, int fromIndex, int toIndex, double val)
    { }

    /** 
     * Assigns the specified float value to each element of the specified array
     * of floats.
     *
     * @param a the array to be filled.
     * @param val the value to be stored in all elements of the array.
     */
    public static void fill(float[] a, float val) { }

    /** 
     * Assigns the specified float value to each element of the specified 
     * range of the specified array of floats.  The range to be filled
     * extends from index <tt>fromIndex</tt>, inclusive, to index
     * <tt>toIndex</tt>, exclusive.  (If <tt>fromIndex==toIndex</tt>, the 
     * range to be filled is empty.)
     *
     * @param a the array to be filled.
     * @param fromIndex the index of the first element (inclusive) to be
     *        filled with the specified value.
     * @param toIndex the index of the last element (exclusive) to be
     *        filled with the specified value.
     * @param val the value to be stored in all elements of the array.
     * @throws IllegalArgumentException if <tt>fromIndex &gt; toIndex</tt>
     * @throws ArrayIndexOutOfBoundsException if <tt>fromIndex &lt; 0</tt> or
     *	       <tt>toIndex &gt; a.length</tt>
     */
    public static void fill(float[] a, int fromIndex, int toIndex, float val)
    { }

    /** 
     * Assigns the specified Object reference to each element of the specified
     * array of Objects.
     *
     * @param a the array to be filled.
     * @param val the value to be stored in all elements of the array.
     */
    public static void fill(Object[] a, Object val) { }

    /** 
     * Assigns the specified Object reference to each element of the specified 
     * range of the specified array of Objects.  The range to be filled
     * extends from index <tt>fromIndex</tt>, inclusive, to index
     * <tt>toIndex</tt>, exclusive.  (If <tt>fromIndex==toIndex</tt>, the 
     * range to be filled is empty.)
     *
     * @param a the array to be filled.
     * @param fromIndex the index of the first element (inclusive) to be
     *        filled with the specified value.
     * @param toIndex the index of the last element (exclusive) to be
     *        filled with the specified value.
     * @param val the value to be stored in all elements of the array.
     * @throws IllegalArgumentException if <tt>fromIndex &gt; toIndex</tt>
     * @throws ArrayIndexOutOfBoundsException if <tt>fromIndex &lt; 0</tt> or
     *	       <tt>toIndex &gt; a.length</tt>
     */
    public static void fill(Object[] a, int fromIndex, int toIndex, Object val)
    { }

    /** 
     * Returns a fixed-size list backed by the specified array.  (Changes to
     * the returned list "write through" to the array.)  This method acts
     * as bridge between array-based and collection-based APIs, in
     * combination with <tt>Collection.toArray</tt>.  The returned list is
     * serializable and implements {@link RandomAccess}.
     *
     * @param a the array by which the list will be backed.
     * @return a list view of the specified array.
     * @see Collection#toArray()
     */
    public static List asList(Object[] a) {
        return null;
    }

    /**
     * @serial include
     */
    private static class ArrayList extends AbstractList
			   implements RandomAccess, java.io.Serializable
    {
        private static final long serialVersionUID = -2764017481108945198L;
      	private Object[] a;

	ArrayList(Object[] array) {
	}

	public int size() {
	    return 0;
	}

	public Object[] toArray() {
	    return null;
	}

	public Object get(int index) {
	    return null;
	}

	public Object set(int index, Object element) {
	    return null;
	}

        public int indexOf(Object o) {
            return 0;
        }

        public boolean contains(Object o) {
            return false;
        }
    }
}
