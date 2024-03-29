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

import java.io.Serializable;

/** 
 * This class consists exclusively of static methods that operate on or return
 * collections.  It contains polymorphic algorithms that operate on
 * collections, "wrappers", which return a new collection backed by a
 * specified collection, and a few other odds and ends.
 *
 * <p>The methods of this class all throw a <tt>NullPointerException</tt>
 * if the collections provided to them are null.
 *
 * <p>The documentation for the polymorphic algorithms contained in this class
 * generally includes a brief description of the <i>implementation</i>.  Such
 * descriptions should be regarded as <i>implementation notes</i>, rather than
 * parts of the <i>specification</i>.  Implementors should feel free to
 * substitute other algorithms, so long as the specification itself is adhered
 * to.  (For example, the algorithm used by <tt>sort</tt> does not have to be
 * a mergesort, but it does have to be <i>stable</i>.)
 *
 * <p>The "destructive" algorithms contained in this class, that is, the
 * algorithms that modify the collection on which they operate, are specified
 * to throw <tt>UnsupportedOperationException</tt> if the collection does not
 * support the appropriate mutation primitive(s), such as the <tt>set</tt>
 * method.  These algorithms may, but are not required to, throw this
 * exception if an invocation would have no effect on the collection.  For
 * example, invoking the <tt>sort</tt> method on an unmodifiable list that is
 * already sorted may or may not throw <tt>UnsupportedOperationException</tt>.
 *
 * <p>This class is a member of the 
 * <a href="{@docRoot}/../guide/collections/index.html">
 * Java Collections Framework</a>.
 *
 * @author  Josh Bloch
 * @version 1.45, 02/17/00
 * @see	    Collection
 * @see	    Set
 * @see	    List
 * @see	    Map
 * @since   1.2
 */
public class Collections
{
    /** 
     * The empty set (immutable).  This set is serializable.
     */
    public static final Set EMPTY_SET = null;

    /** 
     * The empty list (immutable).  This list is serializable.
     */
    public static final List EMPTY_LIST = null;

    /** 
     * The empty map (immutable).  This map is serializable.
     *
     * @since 1.3
     */
    public static final Map EMPTY_MAP = null;

    /*
     * This hidden constructor does not necessarily correspond to
     * a constructor in the original source file -- it keeps javadoc
     * from generating an inappropriate default constructor.
     */
    private Collections() { }

    /** 
     * Sorts the specified list into ascending order, according to the
     * <i>natural ordering</i> of its elements.  All elements in the list must
     * implement the <tt>Comparable</tt> interface.  Furthermore, all elements
     * in the list must be <i>mutually comparable</i> (that is,
     * <tt>e1.compareTo(e2)</tt> must not throw a <tt>ClassCastException</tt>
     * for any elements <tt>e1</tt> and <tt>e2</tt> in the list).<p>
     *
     * This sort is guaranteed to be <i>stable</i>:  equal elements will
     * not be reordered as a result of the sort.<p>
     *
     * The specified list must be modifiable, but need not be resizable.<p>
     *
     * The sorting algorithm is a modified mergesort (in which the merge is
     * omitted if the highest element in the low sublist is less than the
     * lowest element in the high sublist).  This algorithm offers guaranteed
     * n log(n) performance. 
     *
     * This implementation dumps the specified list into an array, sorts
     * the array, and iterates over the list resetting each element
     * from the corresponding position in the array.  This avoids the
     * n<sup>2</sup> log(n) performance that would result from attempting
     * to sort a linked list in place.
     *
     * @param  list the list to be sorted.
     * @throws ClassCastException if the list contains elements that are not
     *	       <i>mutually comparable</i> (for example, strings and integers).
     * @throws UnsupportedOperationException if the specified list's
     *	       list-iterator does not support the <tt>set</tt> operation.
     * @see Comparable
     */
    public static void sort(List list) { }

    /** 
     * Sorts the specified list according to the order induced by the
     * specified comparator.  All elements in the list must be <i>mutually
     * comparable</i> using the specified comparator (that is,
     * <tt>c.compare(e1, e2)</tt> must not throw a <tt>ClassCastException</tt>
     * for any elements <tt>e1</tt> and <tt>e2</tt> in the list).<p>
     *
     * This sort is guaranteed to be <i>stable</i>:  equal elements will
     * not be reordered as a result of the sort.<p>
     *
     * The sorting algorithm is a modified mergesort (in which the merge is
     * omitted if the highest element in the low sublist is less than the
     * lowest element in the high sublist).  This algorithm offers guaranteed
     * n log(n) performance. 
     *
     * The specified list must be modifiable, but need not be resizable.
     * This implementation dumps the specified list into an array, sorts
     * the array, and iterates over the list resetting each element
     * from the corresponding position in the array.  This avoids the
     * n<sup>2</sup> log(n) performance that would result from attempting
     * to sort a linked list in place.
     *
     * @param  list the list to be sorted.
     * @param  c the comparator to determine the order of the list.  A
     *        <tt>null</tt> value indicates that the elements' <i>natural
     *        ordering</i> should be used.
     * @throws ClassCastException if the list contains elements that are not
     *	       <i>mutually comparable</i> using the specified comparator.
     * @throws UnsupportedOperationException if the specified list's
     *	       list-iterator does not support the <tt>set</tt> operation.
     * @see Comparator
     */
    public static void sort(List list, Comparator c) { }

    /** 
     * Searches the specified list for the specified object using the binary
     * search algorithm.  The list must be sorted into ascending order
     * according to the <i>natural ordering</i> of its elements (as by the
     * <tt>sort(List)</tt> method, above) prior to making this call.  If it is
     * not sorted, the results are undefined.  If the list contains multiple
     * elements equal to the specified object, there is no guarantee which one
     * will be found.<p>
     *
     * This method runs in log(n) time for a "random access" list (which
     * provides near-constant-time positional access).  If the specified list
     * does not implement the {@link RandomAccess} and is large, this method
     * will do an iterator-based binary search that performs O(n) link
     * traversals and O(log n) element comparisons.
     *
     * @param  list the list to be searched.
     * @param  key the key to be searched for.
     * @return index of the search key, if it is contained in the list;
     *	       otherwise, <tt>(-(<i>insertion point</i>) - 1)</tt>.  The
     *	       <i>insertion point</i> is defined as the point at which the
     *	       key would be inserted into the list: the index of the first
     *	       element greater than the key, or <tt>list.size()</tt>, if all
     *	       elements in the list are less than the specified key.  Note
     *	       that this guarantees that the return value will be &gt;= 0 if
     *	       and only if the key is found.
     * @throws ClassCastException if the list contains elements that are not
     *	       <i>mutually comparable</i> (for example, strings and
     *	       integers), or the search key in not mutually comparable
     *	       with the elements of the list.
     * @see    Comparable
     * @see #sort(List)
     */
    public static int binarySearch(List list, Object key) {
        return 0;
    }

    /** 
     * Searches the specified list for the specified object using the binary
     * search algorithm.  The list must be sorted into ascending order
     * according to the specified comparator (as by the <tt>Sort(List,
     * Comparator)</tt> method, above), prior to making this call.  If it is
     * not sorted, the results are undefined.  If the list contains multiple
     * elements equal to the specified object, there is no guarantee which one
     * will be found.<p>
     *
     * This method runs in log(n) time for a "random access" list (which
     * provides near-constant-time positional access).  If the specified list
     * does not implement the {@link RandomAccess} and is large, this
     * this method will do an iterator-based binary search that performs
     * O(n) link traversals and O(log n) element comparisons.
     *
     * @param  list the list to be searched.
     * @param  key the key to be searched for.
     * @param  c the comparator by which the list is ordered.  A
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
     * @throws ClassCastException if the list contains elements that are not
     *	       <i>mutually comparable</i> using the specified comparator,
     *	       or the search key in not mutually comparable with the
     *	       elements of the list using this comparator.
     * @see    Comparable
     * @see #sort(List, Comparator)
     */
    public static int binarySearch(List list, Object key, Comparator c) {
        return 0;
    }

    /** 
     * Reverses the order of the elements in the specified list.<p>
     *
     * This method runs in linear time.
     *
     * @param  list the list whose elements are to be reversed.
     * @throws UnsupportedOperationException if the specified list or
     *         its list-iterator does not support the <tt>set</tt> method.
     */
    public static void reverse(List list) { }

    /** 
     * Randomly permutes the specified list using a default source of
     * randomness.  All permutations occur with approximately equal
     * likelihood.<p>
     *
     * The hedge "approximately" is used in the foregoing description because
     * default source of randomenss is only approximately an unbiased source
     * of independently chosen bits. If it were a perfect source of randomly
     * chosen bits, then the algorithm would choose permutations with perfect
     * uniformity.<p>
     *
     * This implementation traverses the list backwards, from the last element
     * up to the second, repeatedly swapping a randomly selected element into
     * the "current position".  Elements are randomly selected from the
     * portion of the list that runs from the first element to the current
     * position, inclusive.<p>
     *
     * This method runs in linear time.  If the specified list does not
     * implement the {@link RandomAccess} interface and is large, this
     * implementation dumps the specified list into an array before shuffling
     * it, and dumps the shuffled array back into the list.  This avoids the
     * quadratic behavior that would result from shuffling a "sequential
     * access" list in place.
     *
     * @param  list the list to be shuffled.
     * @throws UnsupportedOperationException if the specified list or
     *         its list-iterator does not support the <tt>set</tt> method.
     */
    public static void shuffle(List list) { }

    /** 
     * Randomly permute the specified list using the specified source of
     * randomness.  All permutations occur with equal likelihood
     * assuming that the source of randomness is fair.<p>
     *
     * This implementation traverses the list backwards, from the last element
     * up to the second, repeatedly swapping a randomly selected element into
     * the "current position".  Elements are randomly selected from the
     * portion of the list that runs from the first element to the current
     * position, inclusive.<p>
     *
     * This method runs in linear time.  If the specified list does not
     * implement the {@link RandomAccess} interface and is large, this
     * implementation dumps the specified list into an array before shuffling
     * it, and dumps the shuffled array back into the list.  This avoids the
     * quadratic behavior that would result from shuffling a "sequential
     * access" list in place.
     *
     * @param  list the list to be shuffled.
     * @param  rnd the source of randomness to use to shuffle the list.
     * @throws UnsupportedOperationException if the specified list or its
     *         list-iterator does not support the <tt>set</tt> operation.
     */
    public static void shuffle(List list, Random rnd) { }

    /** 
     * Swaps the elements at the specified positions in the specified list.
     * (If the specified positions are equal, invoking this method leaves
     * the list unchanged.)
     *
     * @param list The list in which to swap elements.
     * @param i the index of one element to be swapped.
     * @param j the index of the other element to be swapped.
     * @throws IndexOutOfBoundsException if either <tt>i</tt> or <tt>j</tt>
     *         is out of range (i &lt; 0 || i &gt;= list.size()
     *         || j &lt; 0 || j &gt;= list.size()).
     * @since 1.4
     */
    public static void swap(List list, int i, int j) { }

    /** 
     * Replaces all of the elements of the specified list with the specified
     * element. <p>
     *
     * This method runs in linear time.
     *
     * @param  list the list to be filled with the specified element.
     * @param  obj The element with which to fill the specified list.
     * @throws UnsupportedOperationException if the specified list or its
     *	       list-iterator does not support the <tt>set</tt> operation.
     */
    public static void fill(List list, Object obj) { }

    /** 
     * Copies all of the elements from one list into another.  After the
     * operation, the index of each copied element in the destination list
     * will be identical to its index in the source list.  The destination
     * list must be at least as long as the source list.  If it is longer, the
     * remaining elements in the destination list are unaffected. <p>
     *
     * This method runs in linear time.
     *
     * @param  dest The destination list.
     * @param  src The source list.
     * @throws IndexOutOfBoundsException if the destination list is too small
     *         to contain the entire source List.
     * @throws UnsupportedOperationException if the destination list's
     *         list-iterator does not support the <tt>set</tt> operation.
     */
    public static void copy(List dest, List src) { }

    /** 
     * Returns the minimum element of the given collection, according to the
     * <i>natural ordering</i> of its elements.  All elements in the
     * collection must implement the <tt>Comparable</tt> interface.
     * Furthermore, all elements in the collection must be <i>mutually
     * comparable</i> (that is, <tt>e1.compareTo(e2)</tt> must not throw a
     * <tt>ClassCastException</tt> for any elements <tt>e1</tt> and
     * <tt>e2</tt> in the collection).<p>
     *
     * This method iterates over the entire collection, hence it requires
     * time proportional to the size of the collection.
     *
     * @param  coll the collection whose minimum element is to be determined.
     * @return the minimum element of the given collection, according
     *         to the <i>natural ordering</i> of its elements.
     * @throws ClassCastException if the collection contains elements that are
     *	       not <i>mutually comparable</i> (for example, strings and
     *	       integers).
     * @throws NoSuchElementException if the collection is empty.
     * @see Comparable
     */
    public static Object min(Collection coll) {
        return null;
    }

    /** 
     * Returns the minimum element of the given collection, according to the
     * order induced by the specified comparator.  All elements in the
     * collection must be <i>mutually comparable</i> by the specified
     * comparator (that is, <tt>comp.compare(e1, e2)</tt> must not throw a
     * <tt>ClassCastException</tt> for any elements <tt>e1</tt> and
     * <tt>e2</tt> in the collection).<p>
     *
     * This method iterates over the entire collection, hence it requires
     * time proportional to the size of the collection.
     *
     * @param  coll the collection whose minimum element is to be determined.
     * @param  comp the comparator with which to determine the minimum element.
     *         A <tt>null</tt> value indicates that the elements' <i>natural
     *         ordering</i> should be used.
     * @return the minimum element of the given collection, according
     *         to the specified comparator.
     * @throws ClassCastException if the collection contains elements that are
     *	       not <i>mutually comparable</i> using the specified comparator.
     * @throws NoSuchElementException if the collection is empty.
     * @see Comparable
     */
    public static Object min(Collection coll, Comparator comp) {
        return null;
    }

    /** 
     * Returns the maximum element of the given collection, according to the
     * <i>natural ordering</i> of its elements.  All elements in the
     * collection must implement the <tt>Comparable</tt> interface.
     * Furthermore, all elements in the collection must be <i>mutually
     * comparable</i> (that is, <tt>e1.compareTo(e2)</tt> must not throw a
     * <tt>ClassCastException</tt> for any elements <tt>e1</tt> and
     * <tt>e2</tt> in the collection).<p>
     *
     * This method iterates over the entire collection, hence it requires
     * time proportional to the size of the collection.
     *
     * @param  coll the collection whose maximum element is to be determined.
     * @return the maximum element of the given collection, according
     *         to the <i>natural ordering</i> of its elements.
     * @throws ClassCastException if the collection contains elements that are
     *	       not <i>mutually comparable</i> (for example, strings and
     *         integers).
     * @throws NoSuchElementException if the collection is empty.
     * @see Comparable
     */
    public static Object max(Collection coll) {
        return null;
    }

    /** 
     * Returns the maximum element of the given collection, according to the
     * order induced by the specified comparator.  All elements in the
     * collection must be <i>mutually comparable</i> by the specified
     * comparator (that is, <tt>comp.compare(e1, e2)</tt> must not throw a
     * <tt>ClassCastException</tt> for any elements <tt>e1</tt> and
     * <tt>e2</tt> in the collection).<p>
     *
     * This method iterates over the entire collection, hence it requires
     * time proportional to the size of the collection.
     *
     * @param  coll the collection whose maximum element is to be determined.
     * @param  comp the comparator with which to determine the maximum element.
     *         A <tt>null</tt> value indicates that the elements' <i>natural
     *        ordering</i> should be used.
     * @return the maximum element of the given collection, according
     *         to the specified comparator.
     * @throws ClassCastException if the collection contains elements that are
     *	       not <i>mutually comparable</i> using the specified comparator.
     * @throws NoSuchElementException if the collection is empty.
     * @see Comparable
     */
    public static Object max(Collection coll, Comparator comp) {
        return null;
    }

    /** 
     * Rotates the elements in the specified list by the specified distance.
     * After calling this method, the element at index <tt>i</tt> will be
     * the element previously at index <tt>(i - distance)</tt> mod
     * <tt>list.size()</tt>, for all values of <tt>i</tt> between <tt>0</tt>
     * and <tt>list.size()-1</tt>, inclusive.  (This method has no effect on
     * the size of the list.)
     *
     * <p>For example, suppose <tt>list</tt> comprises<tt> [t, a, n, k, s]</tt>.
     * After invoking <tt>Collections.rotate(list, 1)</tt> (or
     * <tt>Collections.rotate(list, -4)</tt>), <tt>list</tt> will comprise
     * <tt>[s, t, a, n, k]</tt>.
     *
     * <p>Note that this method can usefully be applied to sublists to
     * move one or more elements within a list while preserving the
     * order of the remaining elements.  For example, the following idiom
     * moves the element at index <tt>j</tt> forward to position
     * <tt>k</tt> (which must be greater than or equal to <tt>j</tt>):
     * <pre>
     *     Collections.rotate(list.subList(j, k+1), -1);
     * </pre>
     * To make this concrete, suppose <tt>list</tt> comprises
     * <tt>[a, b, c, d, e]</tt>.  To move the element at index <tt>1</tt>
     * (<tt>b</tt>) forward two positions, perform the following invocation:
     * <pre>
     *     Collections.rotate(l.subList(1, 4), -1);
     * </pre>
     * The resulting list is <tt>[a, c, d, b, e]</tt>.
     * 
     * <p>To move more than one element forward, increase the absolute value
     * of the rotation distance.  To move elements backward, use a positive
     * shift distance.
     *
     * <p>If the specified list is small or implements the {@link
     * RandomAccess} interface, this implementation exchanges the first
     * element into the location it should go, and then repeatedly exchanges
     * the displaced element into the location it should go until a displaced
     * element is swapped into the first element.  If necessary, the process
     * is repeated on the second and successive elements, until the rotation
     * is complete.  If the specified list is large and doesn't implement the
     * <tt>RandomAccess</tt> interface, this implementation breaks the
     * list into two sublist views around index <tt>-distance mod size</tt>.
     * Then the {@link #reverse(List)} method is invoked on each sublist view,
     * and finally it is invoked on the entire list.  For a more complete
     * description of both algorithms, see Section 2.3 of Jon Bentley's
     * <i>Programming Pearls</i> (Addison-Wesley, 1986).
     *
     * @param list the list to be rotated.
     * @param distance the distance to rotate the list.  There are no
     *        constraints on this value; it may be zero, negative, or
     *        greater than <tt>list.size()</tt>.
     * @throws UnsupportedOperationException if the specified list or
     *         its list-iterator does not support the <tt>set</tt> method.
     * @since 1.4
     */
    public static void rotate(List list, int distance) { }

    /** 
     * Replaces all occurrences of one specified value in a list with another.
     * More formally, replaces with <tt>newVal</tt> each element <tt>e</tt>
     * in <tt>list</tt> such that
     * <tt>(oldVal==null ? e==null : oldVal.equals(e))</tt>.
     * (This method has no effect on the size of the list.)
     *
     * @param list the list in which replacement is to occur.
     * @param oldVal the old value to be replaced.
     * @param newVal the new value with which <tt>oldVal</tt> is to be
     *        replaced.
     * @return <tt>true</tt> if <tt>list</tt> contained one or more elements
     *         <tt>e</tt> such that
     *         <tt>(oldVal==null ?  e==null : oldVal.equals(e))</tt>.
     * @throws UnsupportedOperationException if the specified list or
     *         its list-iterator does not support the <tt>set</tt> method.
     * @since  1.4
     */
    public static boolean replaceAll(List list, Object oldVal, Object newVal) {
        return false;
    }

    /** 
     * Returns the starting position of the first occurrence of the specified
     * target list within the specified source list, or -1 if there is no
     * such occurrence.  More formally, returns the the lowest index <tt>i</tt>
     * such that <tt>source.subList(i, i+target.size()).equals(target)</tt>,
     * or -1 if there is no such index.  (Returns -1 if
     * <tt>target.size() > source.size()</tt>.)
     *
     * <p>This implementation uses the "brute force" technique of scanning
     * over the source list, looking for a match with the target at each
     * location in turn.
     *
     * @param source the list in which to search for the first occurrence
     *        of <tt>target</tt>.
     * @param target the list to search for as a subList of <tt>source</tt>.
     * @return the starting position of the first occurrence of the specified
     *         target list within the specified source list, or -1 if there
     *         is no such occurrence.
     * @since  1.4
     */
    public static int indexOfSubList(List source, List target) {
        return 0;
    }

    /** 
     * Returns the starting position of the last occurrence of the specified
     * target list within the specified source list, or -1 if there is no such
     * occurrence.  More formally, returns the the highest index <tt>i</tt>
     * such that <tt>source.subList(i, i+target.size()).equals(target)</tt>,
     * or -1 if there is no such index.  (Returns -1 if
     * <tt>target.size() > source.size()</tt>.)
     *
     * <p>This implementation uses the "brute force" technique of iterating
     * over the source list, looking for a match with the target at each
     * location in turn.
     *
     * @param source the list in which to search for the last occurrence
     *        of <tt>target</tt>.
     * @param target the list to search for as a subList of <tt>source</tt>.
     * @return the starting position of the last occurrence of the specified
     *         target list within the specified source list, or -1 if there
     *         is no such occurrence.
     * @since  1.4
     */
    public static int lastIndexOfSubList(List source, List target) {
        return 0;
    }

    /** 
     * Returns an unmodifiable view of the specified collection.  This method
     * allows modules to provide users with "read-only" access to internal
     * collections.  Query operations on the returned collection "read through"
     * to the specified collection, and attempts to modify the returned
     * collection, whether direct or via its iterator, result in an
     * <tt>UnsupportedOperationException</tt>.<p>
     *
     * The returned collection does <i>not</i> pass the hashCode and equals
     * operations through to the backing collection, but relies on
     * <tt>Object</tt>'s <tt>equals</tt> and <tt>hashCode</tt> methods.  This
     * is necessary to preserve the contracts of these operations in the case
     * that the backing collection is a set or a list.<p>
     *
     * The returned collection will be serializable if the specified collection
     * is serializable. 
     *
     * @param  c the collection for which an unmodifiable view is to be
     *	       returned.
     * @return an unmodifiable view of the specified collection.
     */
    public static Collection unmodifiableCollection(Collection c) {
        return null;
    }

    /** 
     * Returns an unmodifiable view of the specified set.  This method allows
     * modules to provide users with "read-only" access to internal sets.
     * Query operations on the returned set "read through" to the specified
     * set, and attempts to modify the returned set, whether direct or via its
     * iterator, result in an <tt>UnsupportedOperationException</tt>.<p>
     *
     * The returned set will be serializable if the specified set
     * is serializable. 
     *
     * @param  s the set for which an unmodifiable view is to be returned.
     * @return an unmodifiable view of the specified set.
     */
    public static Set unmodifiableSet(Set s) {
        return null;
    }

    /** 
     * Returns an unmodifiable view of the specified sorted set.  This method
     * allows modules to provide users with "read-only" access to internal
     * sorted sets.  Query operations on the returned sorted set "read
     * through" to the specified sorted set.  Attempts to modify the returned
     * sorted set, whether direct, via its iterator, or via its
     * <tt>subSet</tt>, <tt>headSet</tt>, or <tt>tailSet</tt> views, result in
     * an <tt>UnsupportedOperationException</tt>.<p>
     *
     * The returned sorted set will be serializable if the specified sorted set
     * is serializable. 
     *
     * @param s the sorted set for which an unmodifiable view is to be
     *        returned. 
     * @return an unmodifiable view of the specified sorted set.
     */
    public static SortedSet unmodifiableSortedSet(SortedSet s) {
        return null;
    }

    /** 
     * Returns an unmodifiable view of the specified list.  This method allows
     * modules to provide users with "read-only" access to internal
     * lists.  Query operations on the returned list "read through" to the
     * specified list, and attempts to modify the returned list, whether
     * direct or via its iterator, result in an
     * <tt>UnsupportedOperationException</tt>.<p>
     *
     * The returned list will be serializable if the specified list
     * is serializable. Similarly, the returned list will implement
     * {@link RandomAccess} if the specified list does.
     * the 
     *
     * @param  list the list for which an unmodifiable view is to be returned.
     * @return an unmodifiable view of the specified list.
     */
    public static List unmodifiableList(List list) {
        return null;
    }

    /** 
     * Returns an unmodifiable view of the specified map.  This method
     * allows modules to provide users with "read-only" access to internal
     * maps.  Query operations on the returned map "read through"
     * to the specified map, and attempts to modify the returned
     * map, whether direct or via its collection views, result in an
     * <tt>UnsupportedOperationException</tt>.<p>
     *
     * The returned map will be serializable if the specified map
     * is serializable. 
     *
     * @param  m the map for which an unmodifiable view is to be returned.
     * @return an unmodifiable view of the specified map.
     */
    public static Map unmodifiableMap(Map m) {
        return null;
    }

    /** 
     * Returns an unmodifiable view of the specified sorted map.  This method
     * allows modules to provide users with "read-only" access to internal
     * sorted maps.  Query operations on the returned sorted map "read through"
     * to the specified sorted map.  Attempts to modify the returned
     * sorted map, whether direct, via its collection views, or via its
     * <tt>subMap</tt>, <tt>headMap</tt>, or <tt>tailMap</tt> views, result in
     * an <tt>UnsupportedOperationException</tt>.<p>
     *
     * The returned sorted map will be serializable if the specified sorted map
     * is serializable. 
     *
     * @param m the sorted map for which an unmodifiable view is to be
     *        returned. 
     * @return an unmodifiable view of the specified sorted map.
     */
    public static SortedMap unmodifiableSortedMap(SortedMap m) {
        return null;
    }

    /** 
     * Returns a synchronized (thread-safe) collection backed by the specified
     * collection.  In order to guarantee serial access, it is critical that
     * <strong>all</strong> access to the backing collection is accomplished
     * through the returned collection.<p>
     *
     * It is imperative that the user manually synchronize on the returned
     * collection when iterating over it:
     * <pre>
     *  Collection c = Collections.synchronizedCollection(myCollection);
     *     ...
     *  synchronized(c) {
     *      Iterator i = c.iterator(); // Must be in the synchronized block
     *      while (i.hasNext())
     *         foo(i.next());
     *  }
     * </pre>
     * Failure to follow this advice may result in non-deterministic behavior.
     *
     * <p>The returned collection does <i>not</i> pass the <tt>hashCode</tt>
     * and <tt>equals</tt> operations through to the backing collection, but
     * relies on <tt>Object</tt>'s equals and hashCode methods.  This is
     * necessary to preserve the contracts of these operations in the case
     * that the backing collection is a set or a list.<p>
     *
     * The returned collection will be serializable if the specified collection
     * is serializable. 
     *
     * @param  c the collection to be "wrapped" in a synchronized collection.
     * @return a synchronized view of the specified collection.
     */
    public static Collection synchronizedCollection(Collection c) {
        return null;
    }

    /** 
     * Returns a synchronized (thread-safe) set backed by the specified
     * set.  In order to guarantee serial access, it is critical that
     * <strong>all</strong> access to the backing set is accomplished
     * through the returned set.<p>
     *
     * It is imperative that the user manually synchronize on the returned
     * set when iterating over it:
     * <pre>
     *  Set s = Collections.synchronizedSet(new HashSet());
     *      ...
     *  synchronized(s) {
     *      Iterator i = s.iterator(); // Must be in the synchronized block
     *      while (i.hasNext())
     *          foo(i.next());
     *  }
     * </pre>
     * Failure to follow this advice may result in non-deterministic behavior.
     *
     * <p>The returned set will be serializable if the specified set is
     * serializable.
     *
     * @param  s the set to be "wrapped" in a synchronized set.
     * @return a synchronized view of the specified set.
     */
    public static Set synchronizedSet(Set s) {
        return null;
    }

    /** 
     * Returns a synchronized (thread-safe) sorted set backed by the specified
     * sorted set.  In order to guarantee serial access, it is critical that
     * <strong>all</strong> access to the backing sorted set is accomplished
     * through the returned sorted set (or its views).<p>
     *
     * It is imperative that the user manually synchronize on the returned
     * sorted set when iterating over it or any of its <tt>subSet</tt>,
     * <tt>headSet</tt>, or <tt>tailSet</tt> views.
     * <pre>
     *  SortedSet s = Collections.synchronizedSortedSet(new HashSortedSet());
     *      ...
     *  synchronized(s) {
     *      Iterator i = s.iterator(); // Must be in the synchronized block
     *      while (i.hasNext())
     *          foo(i.next());
     *  }
     * </pre>
     * or:
     * <pre>
     *  SortedSet s = Collections.synchronizedSortedSet(new HashSortedSet());
     *  SortedSet s2 = s.headSet(foo);
     *      ...
     *  synchronized(s) {  // Note: s, not s2!!!
     *      Iterator i = s2.iterator(); // Must be in the synchronized block
     *      while (i.hasNext())
     *          foo(i.next());
     *  }
     * </pre>
     * Failure to follow this advice may result in non-deterministic behavior.
     *
     * <p>The returned sorted set will be serializable if the specified
     * sorted set is serializable.
     *
     * @param  s the sorted set to be "wrapped" in a synchronized sorted set.
     * @return a synchronized view of the specified sorted set.
     */
    public static SortedSet synchronizedSortedSet(SortedSet s) {
        return null;
    }

    /** 
     * Returns a synchronized (thread-safe) list backed by the specified
     * list.  In order to guarantee serial access, it is critical that
     * <strong>all</strong> access to the backing list is accomplished
     * through the returned list.<p>
     *
     * It is imperative that the user manually synchronize on the returned
     * list when iterating over it:
     * <pre>
     *  List list = Collections.synchronizedList(new ArrayList());
     *      ...
     *  synchronized(list) {
     *      Iterator i = list.iterator(); // Must be in synchronized block
     *      while (i.hasNext())
     *          foo(i.next());
     *  }
     * </pre>
     * Failure to follow this advice may result in non-deterministic behavior.
     *
     * <p>The returned list will be serializable if the specified list is
     * serializable.
     *
     * @param  list the list to be "wrapped" in a synchronized list.
     * @return a synchronized view of the specified list.
     */
    public static List synchronizedList(List list) {
        return null;
    }

    /** 
     * Returns a synchronized (thread-safe) map backed by the specified
     * map.  In order to guarantee serial access, it is critical that
     * <strong>all</strong> access to the backing map is accomplished
     * through the returned map.<p>
     *
     * It is imperative that the user manually synchronize on the returned
     * map when iterating over any of its collection views:
     * <pre>
     *  Map m = Collections.synchronizedMap(new HashMap());
     *      ...
     *  Set s = m.keySet();  // Needn't be in synchronized block
     *      ...
     *  synchronized(m) {  // Synchronizing on m, not s!
     *      Iterator i = s.iterator(); // Must be in synchronized block
     *      while (i.hasNext())
     *          foo(i.next());
     *  }
     * </pre>
     * Failure to follow this advice may result in non-deterministic behavior.
     *
     * <p>The returned map will be serializable if the specified map is
     * serializable.
     *
     * @param  m the map to be "wrapped" in a synchronized map.
     * @return a synchronized view of the specified map.
     */
    public static Map synchronizedMap(Map m) {
        return null;
    }

    /** 
     * Returns a synchronized (thread-safe) sorted map backed by the specified
     * sorted map.  In order to guarantee serial access, it is critical that
     * <strong>all</strong> access to the backing sorted map is accomplished
     * through the returned sorted map (or its views).<p>
     *
     * It is imperative that the user manually synchronize on the returned
     * sorted map when iterating over any of its collection views, or the
     * collections views of any of its <tt>subMap</tt>, <tt>headMap</tt> or
     * <tt>tailMap</tt> views.
     * <pre>
     *  SortedMap m = Collections.synchronizedSortedMap(new HashSortedMap());
     *      ...
     *  Set s = m.keySet();  // Needn't be in synchronized block
     *      ...
     *  synchronized(m) {  // Synchronizing on m, not s!
     *      Iterator i = s.iterator(); // Must be in synchronized block
     *      while (i.hasNext())
     *          foo(i.next());
     *  }
     * </pre>
     * or:
     * <pre>
     *  SortedMap m = Collections.synchronizedSortedMap(new HashSortedMap());
     *  SortedMap m2 = m.subMap(foo, bar);
     *      ...
     *  Set s2 = m2.keySet();  // Needn't be in synchronized block
     *      ...
     *  synchronized(m) {  // Synchronizing on m, not m2 or s2!
     *      Iterator i = s.iterator(); // Must be in synchronized block
     *      while (i.hasNext())
     *          foo(i.next());
     *  }
     * </pre>
     * Failure to follow this advice may result in non-deterministic behavior.
     *
     * <p>The returned sorted map will be serializable if the specified
     * sorted map is serializable.
     *
     * @param  m the sorted map to be "wrapped" in a synchronized sorted map.
     * @return a synchronized view of the specified sorted map.
     */
    public static SortedMap synchronizedSortedMap(SortedMap m) {
        return null;
    }

    /** 
     * Returns an immutable set containing only the specified object.
     * The returned set is serializable.
     *
     * @param o the sole object to be stored in the returned set.
     * @return an immutable set containing only the specified object.
     */
    public static Set singleton(Object o) {
        return null;
    }

    /** 
     * Returns an immutable list containing only the specified object.
     * The returned list is serializable.
     *
     * @param o the sole object to be stored in the returned list.
     * @return an immutable list containing only the specified object.
     * @since 1.3
     */
    public static List singletonList(Object o) {
        return null;
    }

    /** 
     * Returns an immutable map, mapping only the specified key to the
     * specified value.  The returned map is serializable.
     *
     * @param key the sole key to be stored in the returned map.
     * @param value the value to which the returned map maps <tt>key</tt>.
     * @return an immutable map containing only the specified key-value
     *         mapping.
     * @since 1.3
     */
    public static Map singletonMap(Object key, Object value) {
        return null;
    }

    /** 
     * Returns an immutable list consisting of <tt>n</tt> copies of the
     * specified object.  The newly allocated data object is tiny (it contains
     * a single reference to the data object).  This method is useful in
     * combination with the <tt>List.addAll</tt> method to grow lists.
     * The returned list is serializable.
     *
     * @param  n the number of elements in the returned list.
     * @param  o the element to appear repeatedly in the returned list.
     * @return an immutable list consisting of <tt>n</tt> copies of the
     * 	       specified object.
     * @throws IllegalArgumentException if n &lt; 0.
     * @see    List#addAll(Collection)
     * @see    List#addAll(int, Collection)
     */
    public static List nCopies(int n, Object o) {
        return null;
    }

    /** 
     * Returns a comparator that imposes the reverse of the <i>natural
     * ordering</i> on a collection of objects that implement the
     * <tt>Comparable</tt> interface.  (The natural ordering is the ordering
     * imposed by the objects' own <tt>compareTo</tt> method.)  This enables a
     * simple idiom for sorting (or maintaining) collections (or arrays) of
     * objects that implement the <tt>Comparable</tt> interface in
     * reverse-natural-order.  For example, suppose a is an array of
     * strings. Then: <pre>
     * 		Arrays.sort(a, Collections.reverseOrder());
     * </pre> sorts the array in reverse-lexicographic (alphabetical) order.<p>
     *
     * The returned comparator is serializable.
     *
     * @return a comparator that imposes the reverse of the <i>natural
     * 	       ordering</i> on a collection of objects that implement
     *	       the <tt>Comparable</tt> interface.
     * @see Comparable
     */
    public static Comparator reverseOrder() {
        return null;
    }

    /** 
     * Returns an enumeration over the specified collection.  This provides
     * interoperatbility with legacy APIs that require an enumeration
     * as input.
     *
     * @param c the collection for which an enumeration is to be returned.
     * @return an enumeration over the specified collection.
     * @see Enumeration
     */
    public static Enumeration enumeration(Collection c) {
        return null;
    }

    /** 
     * Returns an array list containing the elements returned by the
     * specified enumeration in the order they are returned by the
     * enumeration.  This method provides interoperatbility between
     * legacy APIs that return enumerations and new APIs that require
     * collections.
     *
     * @param e enumeration providing elements for the returned
     *          array list
     * @return an array list containing the elements returned
     *         by the specified enumeration.
     * @since 1.4
     * @see Enumeration
     * @see ArrayList
     */
    public static ArrayList list(Enumeration e) {
        return null;
    }

    /**
     * @serial include
     */
    static class UnmodifiableSortedSet extends UnmodifiableSet
    				 implements SortedSet, Serializable {
        private SortedSet ss;

	UnmodifiableSortedSet(SortedSet s) {super(s);}

        public Comparator comparator()     {return null;}

        public SortedSet subSet(Object fromElement, Object toElement) {
            return null;
        }
        public SortedSet headSet(Object toElement) {
            return null;
        }
        public SortedSet tailSet(Object fromElement) {
            return null;
        }

        public Object first() 	           {return null;}
        public Object last()  	           {return null;}
    }

    /**
     * @serial include
     */
    static class UnmodifiableRandomAccessList extends UnmodifiableList
                                              implements RandomAccess
    {
        UnmodifiableRandomAccessList(List list) {
            super(list);
        }

	public List subList(int fromIndex, int toIndex) {
            return null;
        }

        private static final long serialVersionUID = -2542308836966382001L;

        /**
         * Allows instances to be deserialized in pre-1.4 JREs (which do
         * not have UnmodifiableRandomAccessList).  UnmodifiableList has
         * a readResolve method that inverts this transformation upon
         * deserialization.
         */
        private Object writeReplace() {
            return null;
        }
    }

    /**
     * @serial include
     */
    private static class UnmodifiableMap implements Map, Serializable {
	// use serialVersionUID from JDK 1.2.2 for interoperability
	private static final long serialVersionUID = -1034234728574286014L;

	private final Map m = null;

	UnmodifiableMap(Map m) {
        }

	public int size() 		         {return 0;}
	public boolean isEmpty() 	         {return false;}
	public boolean containsKey(Object key)   {return false;}
	public boolean containsValue(Object val) {return false;}
	public Object get(Object key) 	         {return null;}

	public Object put(Object key, Object value) {
            return null;
        }

	public Object remove(Object key) {
            return null;
        }

	public void putAll(Map t) {	
        }

	public void clear() {
        }

	public Set keySet() {
	    return null;
	}

	public Set entrySet() {
	    return null;
	}

	public Collection values() {
	    return null;
	}

	public boolean equals(Object o) {return false;}
	public int hashCode()           {return 0;}
        public String toString()        {return null;}

        /**
         * We need this class in addition to UnmodifiableSet as
         * Map.Entries themselves permit modification of the backing Map
         * via their setValue operation.  This class is subtle: there are
         * many possible attacks that must be thwarted.
         *
         * @serial include
         */
        static class UnmodifiableEntrySet extends UnmodifiableSet {
            UnmodifiableEntrySet(Set s) {
                super(s);
            }

            public Iterator iterator() {
                return null;
            }

            public Object[] toArray() {
                return null;
            }

            public Object[] toArray(Object a[]) {
                return null;
            }

            /**
             * This method is overridden to protect the backing set against
             * an object with a nefarious equals function that senses
             * that the equality-candidate is Map.Entry and calls its
             * setValue method.
             */
            public boolean contains(Object o) {
                return false;
            }

            /**
             * The next two methods are overridden to protect against
             * an unscrupulous List whose contains(Object o) method senses
             * when o is a Map.Entry, and calls o.setValue.
             */
            public boolean containsAll(Collection coll) {
                return false;
            }

            public boolean equals(Object o) {
                return false;
            }

            /**
             * This "wrapper class" serves two purposes: it prevents
             * the client from modifying the backing Map, by short-circuiting
             * the setValue method, and it protects the backing Map against
             * an ill-behaved Map.Entry that attempts to modify another
             * Map Entry when asked to perform an equality check.
             */
            private static class UnmodifiableEntry implements Map.Entry {
                private Map.Entry e;

                UnmodifiableEntry(Map.Entry e) {this.e = e;}

                public Object getKey()	  {return null;}
                public Object getValue()  {return null;}
                public Object setValue(Object value) {
                    return null;
                }
                public int hashCode()	  {return 0;}
                public boolean equals(Object o) {
                    return false;
                }
                public String toString()  {return null;}
            }
        }
    }

    /**
     * @serial include
     */
    static class UnmodifiableList extends UnmodifiableCollection
    				  implements List {
        static final long serialVersionUID = -283967356065247728L;
	List list;

	UnmodifiableList(List list) {
	    super(list);
	}

	public boolean equals(Object o) {return false;}
	public int hashCode() 		{return 0;}

	public Object get(int index) {return null;}
	public Object set(int index, Object element) {
            return null;
        }
	public void add(int index, Object element) {
        }
	public Object remove(int index) {
	    return null;
        }
	public int indexOf(Object o)            {return 0;}
	public int lastIndexOf(Object o)        {return 0;}
	public boolean addAll(int index, Collection c) {
            return false;
        }
	public ListIterator listIterator() 	{return null;}

	public ListIterator listIterator(final int index) {
	    return null;
	}

	public List subList(int fromIndex, int toIndex) {
            return null;
        }

        /**
         * UnmodifiableRandomAccessList instances are serialized as
         * UnmodifiableList instances to allow them to be deserialized
         * in pre-1.4 JREs (which do not have UnmodifiableRandomAccessList).
         * This method inverts the transformation.  As a beneficial
         * side-effect, it also grafts the RandomAccess marker onto
         * UnmodifiableList instances that were serialized in pre-1.4 JREs.
         *
         * Note: Unfortunately, UnmodifiableRandomAccessList instances
         * serialized in 1.4.1 and deserialized in 1.4 will become
         * UnmodifiableList instances, as this method was missing in 1.4.
         */
        private Object readResolve() {
            return null;
        }
    }

    /**
     * @serial include
     */
    static class UnmodifiableSet extends UnmodifiableCollection
    				 implements Set, Serializable {
	UnmodifiableSet(Set s) 		{super(s);}

	public boolean equals(Object o) {return false;}
	public int hashCode() 		{return 0;}
    }

    /**
     * @serial include
     */
    static class UnmodifiableCollection implements Collection, Serializable {
	// use serialVersionUID from JDK 1.2.2 for interoperability
	private static final long serialVersionUID = 1820017752578914078L;

	Collection c;

	UnmodifiableCollection(Collection c) {
        }

	public int size() 		    {return 0;}
	public boolean isEmpty() 	    {return false;}
	public boolean contains(Object o)   {return false;}
	public Object[] toArray() 	    {return null;}
	public Object[] toArray(Object[] a) {return null;}
        public String toString()            {return null;}

	public Iterator iterator() {
	    return new Iterator() {
		Iterator i = c.iterator();

		public boolean hasNext() {return i.hasNext();}
		public Object next() 	 {return i.next();}
		public void remove() {
		    throw new UnsupportedOperationException();
                }
	    };
        }

	public boolean add(Object o){
            return false;
        }
	public boolean remove(Object o) {
            return false;
        }

	public boolean containsAll(Collection coll) {
            return false;
        }

	public boolean addAll(Collection coll) {
            return false;
        }

	public boolean removeAll(Collection coll) {
            return false;
        }

	public boolean retainAll(Collection coll) {
            return false;
        }

	public void clear() {
        }
    }

    /**
     * @serial include
     */
    static class SynchronizedSortedSet extends SynchronizedSet
			         implements SortedSet
    {
        private SortedSet ss;

	SynchronizedSortedSet(SortedSet s) {
            super(s);
        }
	SynchronizedSortedSet(SortedSet s, Object mutex) {
            super(s, mutex);
        }

	public Comparator comparator() {
            return null;
        }

        public SortedSet subSet(Object fromElement, Object toElement) {
            return null;
        }

        public SortedSet headSet(Object toElement) {
            return null;
        }

        public SortedSet tailSet(Object fromElement) {
            return null;
        }

        public Object first() {
            return null;
        }

        public Object last() {
            return null;
        }
    }

    /**
     * @serial include
     */
    static class SynchronizedSortedMap extends SynchronizedMap
			         implements SortedMap
    {
        private SortedMap sm;

	SynchronizedSortedMap(SortedMap m) {
            super(m);
        }
	SynchronizedSortedMap(SortedMap m, Object mutex) {
            super(m, mutex);
        }

	public Comparator comparator() {
            return null;
        }

        public SortedMap subMap(Object fromKey, Object toKey) {
            return null;
        }

        public SortedMap headMap(Object toKey) {
            return null;
        }

        public SortedMap tailMap(Object fromKey) {
            return null;
        }

        public Object firstKey() {
            return null;
        }

        public Object lastKey() {
            return null;
        }
    }

    /**
     * @serial include
     */
    static class SynchronizedSet extends SynchronizedCollection
			         implements Set {
	SynchronizedSet(Set s) {
            super(s);
        }
	SynchronizedSet(Set s, Object mutex) {
            super(s, mutex);
        }

	public boolean equals(Object o) {
	    return false;
        }

	public int hashCode() {
            return 0;
        }
    }

    /**
     * @serial include
     */
    static class SynchronizedRandomAccessList extends SynchronizedList
                                              implements RandomAccess
    {
        SynchronizedRandomAccessList(List list) {
            super(list);
        }

	SynchronizedRandomAccessList(List list, Object mutex) {
            super(list, mutex);
        }

	public List subList(int fromIndex, int toIndex) {
	    return null;
        }

        static final long serialVersionUID = 1530674583602358482L;

        /**
         * Allows instances to be deserialized in pre-1.4 JREs (which do
         * not have SynchronizedRandomAccessList).  SynchronizedList has
         * a readResolve method that inverts this transformation upon
         * deserialization.
         */
        private Object writeReplace() {
            return null;
        }
    }

    /**
     * @serial include
     */
    private static class SynchronizedMap implements Map, Serializable {
	// use serialVersionUID from JDK 1.2.2 for interoperability
	private static final long serialVersionUID = 1978198479659022715L;

	private Map m;	       
        Object      mutex;

	SynchronizedMap(Map m) {
        }

	SynchronizedMap(Map m, Object mutex) {
        }

	public int size() {
            return 0;
        }

	public boolean isEmpty(){
	    return false;
        }

	public boolean containsKey(Object key) {
	    return false;
        }

	public boolean containsValue(Object value){
	    return false;
        }

	public Object get(Object key) {
	    return null;
        }

	public Object put(Object key, Object value) {
	    return null;
        }

	public Object remove(Object key) {
	    return null;
        }

	public void putAll(Map map) {
        }

	public void clear() {
        }

	public Set keySet() {
	    return null;
	}

	public Set entrySet() {
	    return null;
	}

	public Collection values() {
	    return null;
        }

	public boolean equals(Object o) {
            return false;
        }

	public int hashCode() {
            return 0;
        }

	public String toString() {
            return null;
        }
    }

    /**
     * @serial include
     */
    static class SynchronizedList extends SynchronizedCollection
    			          implements List {
        static final long serialVersionUID = -7754090372962971524L;

	List list;

	SynchronizedList(List list) {
	    super(list);
	}
	SynchronizedList(List list, Object mutex) {
	    super(list, mutex);
        }

	public boolean equals(Object o) {
            return false;
        }

	public int hashCode() {
            return 0;
        }

	public Object get(int index) {
	    return null;
        }

	public Object set(int index, Object element) {
	    return null;
        }

	public void add(int index, Object element) {
        }

	public Object remove(int index) {
	    return null;
        }

	public int indexOf(Object o) {
	    return 0;
        }

	public int lastIndexOf(Object o) {
	    return 0;
        }

	public boolean addAll(int index, Collection c) {
            return false;
        }

	public ListIterator listIterator() {
	    return null;
        }

	public ListIterator listIterator(int index) {
	    return null;
        }

	public List subList(int fromIndex, int toIndex) {
	    return null;
        }

        /**
         * SynchronizedRandomAccessList instances are serialized as
         * SynchronizedList instances to allow them to be deserialized
         * in pre-1.4 JREs (which do not have SynchronizedRandomAccessList).
         * This method inverts the transformation.  As a beneficial
         * side-effect, it also grafts the RandomAccess marker onto
         * SynchronizedList instances that were serialized in pre-1.4 JREs.
         *
         * Note: Unfortunately, SynchronizedRandomAccessList instances
         * serialized in 1.4.1 and deserialized in 1.4 will become
         * SynchronizedList instances, as this method was missing in 1.4.
         */
        private Object readResolve() {
            return (list instanceof RandomAccess ?
                    new SynchronizedRandomAccessList(list) : this);
        }
    }


    /**
     * @serial include
     */
    static class SynchronizedCollection implements Collection, Serializable {
	// use serialVersionUID from JDK 1.2.2 for interoperability
	private static final long serialVersionUID = 3053995032091335093L;

	Collection c;	   
	Object	   mutex;  

	SynchronizedCollection(Collection c) {
        }

	SynchronizedCollection(Collection c, Object mutex) {
        }

	public int size() {
	    return 0;
        }

	public boolean isEmpty() {
	    return false;
        }

	public boolean contains(Object o) {
 	    return false;
        }

	public Object[] toArray() {
	    return null;
        }

	public Object[] toArray(Object[] a) {
	    return null;
        }

	public Iterator iterator() {
            return null;
        }

	public boolean add(Object o) {
	    return false;
        }

	public boolean remove(Object o) {
	    return false;
        }

	public boolean containsAll(Collection coll) {
	    return false;
        }

	public boolean addAll(Collection coll) {
	    return false;
        }

	public boolean removeAll(Collection coll) {
	    return false;
        }

	public boolean retainAll(Collection coll) {
	    return false;
        }

	public void clear() {	
        }

	public String toString() {
            return null;
        }
    }

    /**
     * @serial include
     */
    private static class SingletonSet extends AbstractSet
                                      implements Serializable
    {
	// use serialVersionUID from JDK 1.2.2 for interoperability
	private static final long serialVersionUID = 3193687207550431679L;

        private Object element;

        SingletonSet(Object o) { }

        public Iterator iterator() {
            return null;
        }

        public int size() {return 0;}

        public boolean contains(Object o) {return false;}
    }

    /**
     * @serial include
     */
    private static class ReverseComparator implements Comparator,Serializable {

	// use serialVersionUID from JDK 1.2.2 for interoperability
	private static final long serialVersionUID = 7207038068494060240L;

        public int compare(Object o1, Object o2) {
            return 0;
        }
    }

    /**
     * @serial include
     */
    private static class EmptyList extends AbstractList
                                   implements RandomAccess, Serializable {

	// use serialVersionUID from JDK 1.2.2 for interoperability
	private static final long serialVersionUID = 8842843931221139166L;

        public int size() {return 0;}

        public boolean contains(Object obj) {return false;}

        public Object get(int index) {
            return null;
        }

        private Object readResolve() {
            return null;
        }
    }

    /**
     * @serial include
     */
    private static class CopiesList extends AbstractList
                                    implements RandomAccess, Serializable
    {
        static final long serialVersionUID = 2739099268398711800L;

        int n;
        Object element;

        CopiesList(int n, Object o) {
        }

        public int size() {
            return 0;
        }

        public boolean contains(Object obj) {
            return false;
        }

        public Object get(int index) {
            return null;
        }
    }

    /**
     * @serial include
     */
    private static class EmptySet extends AbstractSet implements Serializable {
	// use serialVersionUID from JDK 1.2.2 for interoperability
	private static final long serialVersionUID = 1582296315990362920L;

        public Iterator iterator() {
            return null;
        }

        public int size() {return 0;}

        public boolean contains(Object obj) {return false;}

        // Preserves singleton property
        private Object readResolve() {
            return null;
        }
    }

    /**
     * @serial include
     */
    static class UnmodifiableSortedMap extends UnmodifiableMap
    				 implements SortedMap, Serializable {
        private SortedMap sm;

	UnmodifiableSortedMap(SortedMap m) {super(m);}

        public Comparator comparator()     {return null;}

        public SortedMap subMap(Object fromKey, Object toKey) {
            return null;
        }
        public SortedMap headMap(Object toKey) {
            return null;
        }
        public SortedMap tailMap(Object fromKey) {
            return null;
        }

        public Object firstKey()           {return null;}
        public Object lastKey()            {return null;}
    }
}
