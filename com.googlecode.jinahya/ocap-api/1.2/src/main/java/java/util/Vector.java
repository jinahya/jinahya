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

/** 
 * The <code>Vector</code> class implements a growable array of 
 * objects. Like an array, it contains components that can be 
 * accessed using an integer index. However, the size of a 
 * <code>Vector</code> can grow or shrink as needed to accommodate 
 * adding and removing items after the <code>Vector</code> has been created.<p>
 *
 * Each vector tries to optimize storage management by maintaining a 
 * <code>capacity</code> and a <code>capacityIncrement</code>. The 
 * <code>capacity</code> is always at least as large as the vector 
 * size; it is usually larger because as components are added to the 
 * vector, the vector's storage increases in chunks the size of 
 * <code>capacityIncrement</code>. An application can increase the 
 * capacity of a vector before inserting a large number of 
 * components; this reduces the amount of incremental reallocation. <p>
 *
 * As of the Java 2 platform v1.2, this class has been retrofitted to
 * implement List, so that it becomes a part of Java's collection framework.
 * Unlike the new collection implementations, Vector is synchronized.<p>
 *
 * The Iterators returned by Vector's iterator and listIterator
 * methods are <em>fail-fast</em>: if the Vector is structurally modified
 * at any time after the Iterator is created, in any way except through the
 * Iterator's own remove or add methods, the Iterator will throw a
 * ConcurrentModificationException.  Thus, in the face of concurrent
 * modification, the Iterator fails quickly and cleanly, rather than risking
 * arbitrary, non-deterministic behavior at an undetermined time in the future.
 * The Enumerations returned by Vector's elements method are <em>not</em>
 * fail-fast.
 *
 * <p>Note that the fail-fast behavior of an iterator cannot be guaranteed
 * as it is, generally speaking, impossible to make any hard guarantees in the
 * presence of unsynchronized concurrent modification.  Fail-fast iterators
 * throw <tt>ConcurrentModificationException</tt> on a best-effort basis. 
 * Therefore, it would be wrong to write a program that depended on this
 * exception for its correctness:  <i>the fail-fast behavior of iterators
 * should be used only to detect bugs.</i><p>
 *
 * This class is a member of the 
 * <a href="{@docRoot}/../guide/collections/index.html">
 * Java Collections Framework</a>.
 *
 * @author  Lee Boynton
 * @author  Jonathan Payne
 * @version 1.71, 04/18/00
 * @see Collection
 * @see List
 * @see ArrayList
 * @see LinkedList
 * @since   JDK1.0
 */
public class Vector extends AbstractList
    implements List, RandomAccess, Cloneable, java.io.Serializable
{
    /** 
     * The array buffer into which the components of the vector are
     * stored. The capacity of the vector is the length of this array buffer, 
     * and is at least large enough to contain all the vector's elements.<p>
     *
     * Any array elements following the last element in the Vector are null.
     *
     * @serial
     */
    protected Object[] elementData;

    /** 
     * The number of valid components in this <tt>Vector</tt> object. 
     * Components <tt>elementData[0]</tt> through 
     * <tt>elementData[elementCount-1]</tt> are the actual items.
     *
     * @serial
     */
    protected int elementCount;

    /** 
     * The amount by which the capacity of the vector is automatically 
     * incremented when its size becomes greater than its capacity.  If 
     * the capacity increment is less than or equal to zero, the capacity
     * of the vector is doubled each time it needs to grow.
     *
     * @serial
     */
    protected int capacityIncrement;

    /** use serialVersionUID from JDK 1.0.2 for interoperability */
    private static final long serialVersionUID = -2767605614048989439L;

    /** 
     * Constructs an empty vector with the specified initial capacity and
     * capacity increment. 
     *
     * @param   initialCapacity     the initial capacity of the vector.
     * @param   capacityIncrement   the amount by which the capacity is
     *                              increased when the vector overflows.
     * @exception IllegalArgumentException if the specified initial capacity
     *               is negative
     */
    public Vector(int initialCapacity, int capacityIncrement) { }

    /** 
     * Constructs an empty vector with the specified initial capacity and 
     * with its capacity increment equal to zero.
     *
     * @param   initialCapacity   the initial capacity of the vector.
     * @exception IllegalArgumentException if the specified initial capacity
     *               is negative
     */
    public Vector(int initialCapacity) { }

    /** 
     * Constructs an empty vector so that its internal data array 
     * has size <tt>10</tt> and its standard capacity increment is 
     * zero. 
     */
    public Vector() { }

    /** 
     * Constructs a vector containing the elements of the specified
     * collection, in the order they are returned by the collection's
     * iterator.
     *
     * @param c the collection whose elements are to be placed into this
     *       vector.
     * @throws NullPointerException if the specified collection is null.
     * @since   1.2
     */
    public Vector(Collection c) { }

    /** 
     * Copies the components of this vector into the specified array. The 
     * item at index <tt>k</tt> in this vector is copied into component 
     * <tt>k</tt> of <tt>anArray</tt>. The array must be big enough to hold 
     * all the objects in this vector, else an 
     * <tt>IndexOutOfBoundsException</tt> is thrown.
     *
     * @param   anArray   the array into which the components get copied.
     * @throws  NullPointerException if the given array is null.
     */
    public synchronized void copyInto(Object[] anArray) { }

    /** 
     * Trims the capacity of this vector to be the vector's current 
     * size. If the capacity of this vector is larger than its current 
     * size, then the capacity is changed to equal the size by replacing 
     * its internal data array, kept in the field <tt>elementData</tt>, 
     * with a smaller one. An application can use this operation to 
     * minimize the storage of a vector. 
     */
    public synchronized void trimToSize() { }

    /** 
     * Increases the capacity of this vector, if necessary, to ensure 
     * that it can hold at least the number of components specified by 
     * the minimum capacity argument.
     *
     * <p>If the current capacity of this vector is less than
     * <tt>minCapacity</tt>, then its capacity is increased by replacing its
     * internal data array, kept in the field <tt>elementData</tt>, with a
     * larger one.  The size of the new data array will be the old size plus
     * <tt>capacityIncrement</tt>, unless the value of
     * <tt>capacityIncrement</tt> is less than or equal to zero, in which case
     * the new capacity will be twice the old capacity; but if this new size
     * is still smaller than <tt>minCapacity</tt>, then the new capacity will
     * be <tt>minCapacity</tt>.
     *
     * @param minCapacity the desired minimum capacity.
     */
    public synchronized void ensureCapacity(int minCapacity) { }

    /** 
     * Sets the size of this vector. If the new size is greater than the 
     * current size, new <code>null</code> items are added to the end of 
     * the vector. If the new size is less than the current size, all 
     * components at index <code>newSize</code> and greater are discarded.
     *
     * @param   newSize   the new size of this vector.
     * @throws  ArrayIndexOutOfBoundsException if new size is negative.
     */
    public synchronized void setSize(int newSize) { }

    /** 
     * Returns the current capacity of this vector.
     *
     * @return  the current capacity (the length of its internal 
     *          data array, kept in the field <tt>elementData</tt> 
     *          of this vector).
     */
    public synchronized int capacity() {
        return 0;
    }

    /** 
     * Returns the number of components in this vector.
     *
     * @return  the number of components in this vector.
     */
    public synchronized int size() {
        return 0;
    }

    /** 
     * Tests if this vector has no components.
     *
     * @return  <code>true</code> if and only if this vector has 
     *          no components, that is, its size is zero;
     *          <code>false</code> otherwise.
     */
    public synchronized boolean isEmpty() {
        return false;
    }

    /** 
     * Returns an enumeration of the components of this vector. The 
     * returned <tt>Enumeration</tt> object will generate all items in 
     * this vector. The first item generated is the item at index <tt>0</tt>, 
     * then the item at index <tt>1</tt>, and so on. 
     *
     * @return  an enumeration of the components of this vector.
     * @see     Enumeration
     * @see     Iterator
     */
    public Enumeration elements() {
        return null;
    }

    /** 
     * Tests if the specified object is a component in this vector.
     *
     * @param   elem   an object.
     * @return  <code>true</code> if and only if the specified object 
     * is the same as a component in this vector, as determined by the 
     * <tt>equals</tt> method; <code>false</code> otherwise.
     */
    public boolean contains(Object elem) {
        return false;
    }

    /** 
     * Searches for the first occurence of the given argument, testing 
     * for equality using the <code>equals</code> method. 
     *
     * @param   elem   an object.
     * @return  the index of the first occurrence of the argument in this
     *          vector, that is, the smallest value <tt>k</tt> such that 
     *          <tt>elem.equals(elementData[k])</tt> is <tt>true</tt>; 
     *          returns <code>-1</code> if the object is not found.
     * @see     Object#equals(Object)
     */
    public int indexOf(Object elem) {
        return 0;
    }

    /** 
     * Searches for the first occurence of the given argument, beginning 
     * the search at <code>index</code>, and testing for equality using 
     * the <code>equals</code> method. 
     *
     * @param   elem    an object.
     * @param   index   the non-negative index to start searching from.
     * @return  the index of the first occurrence of the object argument in
     *          this vector at position <code>index</code> or later in the
     *          vector, that is, the smallest value <tt>k</tt> such that 
     *          <tt>elem.equals(elementData[k]) && (k &gt;= index)</tt> is 
     *          <tt>true</tt>; returns <code>-1</code> if the object is not 
     *          found. (Returns <code>-1</code> if <tt>index</tt> &gt;= the
     *          current size of this <tt>Vector</tt>.)
     * @exception  IndexOutOfBoundsException  if <tt>index</tt> is negative.
     * @see     Object#equals(Object)
     */
    public synchronized int indexOf(Object elem, int index) {
        return 0;
    }

    /** 
     * Returns the index of the last occurrence of the specified object in
     * this vector.
     *
     * @param   elem   the desired component.
     * @return  the index of the last occurrence of the specified object in
     *          this vector, that is, the largest value <tt>k</tt> such that 
     *          <tt>elem.equals(elementData[k])</tt> is <tt>true</tt>; 
     *          returns <code>-1</code> if the object is not found.
     */
    public synchronized int lastIndexOf(Object elem) {
        return 0;
    }

    /** 
     * Searches backwards for the specified object, starting from the 
     * specified index, and returns an index to it. 
     *
     * @param  elem    the desired component.
     * @param  index   the index to start searching from.
     * @return the index of the last occurrence of the specified object in this
     *          vector at position less than or equal to <code>index</code> in
     *          the vector, that is, the largest value <tt>k</tt> such that 
     *          <tt>elem.equals(elementData[k]) && (k &lt;= index)</tt> is 
     *          <tt>true</tt>; <code>-1</code> if the object is not found.
     *          (Returns <code>-1</code> if <tt>index</tt> is negative.)
     * @exception  IndexOutOfBoundsException  if <tt>index</tt> is greater
     *             than or equal to the current size of this vector.
     */
    public synchronized int lastIndexOf(Object elem, int index) {
        return 0;
    }

    /** 
     * Returns the component at the specified index.<p>
     *
     * This method is identical in functionality to the get method
     * (which is part of the List interface).
     *
     * @param      index   an index into this vector.
     * @return     the component at the specified index.
     * @exception  ArrayIndexOutOfBoundsException  if the <tt>index</tt> 
     *             is negative or not less than the current size of this 
     *             <tt>Vector</tt> object.
     *             given.
     * @see	   #get(int)
     * @see	   List
     */
    public synchronized Object elementAt(int index) {
        return null;
    }

    /** 
     * Returns the first component (the item at index <tt>0</tt>) of 
     * this vector.
     *
     * @return     the first component of this vector.
     * @exception  NoSuchElementException  if this vector has no components.
     */
    public synchronized Object firstElement() {
        return null;
    }

    /** 
     * Returns the last component of the vector.
     *
     * @return  the last component of the vector, i.e., the component at index
     *          <code>size()&nbsp;-&nbsp;1</code>.
     * @exception  NoSuchElementException  if this vector is empty.
     */
    public synchronized Object lastElement() {
        return null;
    }

    /** 
     * Sets the component at the specified <code>index</code> of this 
     * vector to be the specified object. The previous component at that 
     * position is discarded.<p>
     *
     * The index must be a value greater than or equal to <code>0</code> 
     * and less than the current size of the vector. <p>
     *
     * This method is identical in functionality to the set method
     * (which is part of the List interface). Note that the set method reverses
     * the order of the parameters, to more closely match array usage.  Note
     * also that the set method returns the old value that was stored at the
     * specified position.
     *
     * @param      obj     what the component is to be set to.
     * @param      index   the specified index.
     * @exception  ArrayIndexOutOfBoundsException  if the index was invalid.
     * @see        #size()
     * @see        List
     * @see	   #set(int, java.lang.Object)
     */
    public synchronized void setElementAt(Object obj, int index) { }

    /** 
     * Deletes the component at the specified index. Each component in 
     * this vector with an index greater or equal to the specified 
     * <code>index</code> is shifted downward to have an index one 
     * smaller than the value it had previously. The size of this vector 
     * is decreased by <tt>1</tt>.<p>
     *
     * The index must be a value greater than or equal to <code>0</code> 
     * and less than the current size of the vector. <p>
     *
     * This method is identical in functionality to the remove method
     * (which is part of the List interface).  Note that the remove method
     * returns the old value that was stored at the specified position.
     *
     * @param      index   the index of the object to remove.
     * @exception  ArrayIndexOutOfBoundsException  if the index was invalid.
     * @see        #size()
     * @see	   #remove(int)
     * @see	   List
     */
    public synchronized void removeElementAt(int index) { }

    /** 
     * Inserts the specified object as a component in this vector at the 
     * specified <code>index</code>. Each component in this vector with 
     * an index greater or equal to the specified <code>index</code> is 
     * shifted upward to have an index one greater than the value it had 
     * previously. <p>
     *
     * The index must be a value greater than or equal to <code>0</code> 
     * and less than or equal to the current size of the vector. (If the
     * index is equal to the current size of the vector, the new element
     * is appended to the Vector.)<p>
     *
     * This method is identical in functionality to the add(Object, int) method
     * (which is part of the List interface). Note that the add method reverses
     * the order of the parameters, to more closely match array usage.
     *
     * @param      obj     the component to insert.
     * @param      index   where to insert the new component.
     * @exception  ArrayIndexOutOfBoundsException  if the index was invalid.
     * @see        #size()
     * @see	   #add(int, Object)
     * @see	   List
     */
    public synchronized void insertElementAt(Object obj, int index) { }

    /** 
     * Adds the specified component to the end of this vector, 
     * increasing its size by one. The capacity of this vector is 
     * increased if its size becomes greater than its capacity. <p>
     *
     * This method is identical in functionality to the add(Object) method
     * (which is part of the List interface).
     *
     * @param   obj   the component to be added.
     * @see	   #add(Object)
     * @see	   List
     */
    public synchronized void addElement(Object obj) { }

    /** 
     * Removes the first (lowest-indexed) occurrence of the argument 
     * from this vector. If the object is found in this vector, each 
     * component in the vector with an index greater or equal to the 
     * object's index is shifted downward to have an index one smaller 
     * than the value it had previously.<p>
     *
     * This method is identical in functionality to the remove(Object) 
     * method (which is part of the List interface).
     *
     * @param   obj   the component to be removed.
     * @return  <code>true</code> if the argument was a component of this
     *          vector; <code>false</code> otherwise.
     * @see	List#remove(Object)
     * @see	List
     */
    public synchronized boolean removeElement(Object obj) {
        return false;
    }

    /** 
     * Removes all components from this vector and sets its size to zero.<p>
     *
     * This method is identical in functionality to the clear method
     * (which is part of the List interface).
     *
     * @see	#clear
     * @see	List
     */
    public synchronized void removeAllElements() { }

    /** 
     * Returns a clone of this vector. The copy will contain a
     * reference to a clone of the internal data array, not a reference 
     * to the original internal data array of this <tt>Vector</tt> object. 
     *
     * @return  a clone of this vector.
     */
    public synchronized Object clone() {
        return null;
    }

    /** 
     * Returns an array containing all of the elements in this Vector
     * in the correct order.
     *
     * @since 1.2
     */
    public synchronized Object[] toArray() {
        return null;
    }

    /** 
     * Returns an array containing all of the elements in this Vector in the
     * correct order; the runtime type of the returned array is that of the
     * specified array.  If the Vector fits in the specified array, it is
     * returned therein.  Otherwise, a new array is allocated with the runtime
     * type of the specified array and the size of this Vector.<p>
     *
     * If the Vector fits in the specified array with room to spare
     * (i.e., the array has more elements than the Vector),
     * the element in the array immediately following the end of the
     * Vector is set to null.  This is useful in determining the length
     * of the Vector <em>only</em> if the caller knows that the Vector
     * does not contain any null elements.
     *
     * @param a the array into which the elements of the Vector are to
     *		be stored, if it is big enough; otherwise, a new array of the
     * 		same runtime type is allocated for this purpose.
     * @return an array containing the elements of the Vector.
     * @exception ArrayStoreException the runtime type of a is not a supertype
     * of the runtime type of every element in this Vector.
     * @throws NullPointerException if the given array is null.
     * @since 1.2
     */
    public synchronized Object[] toArray(Object[] a) {
        return null;
    }

    /** 
     * Returns the element at the specified position in this Vector.
     *
     * @param index index of element to return.
     * @return object at the specified index
     * @exception ArrayIndexOutOfBoundsException index is out of range (index
     * 		  &lt; 0 || index &gt;= size()).
     * @since 1.2
     */
    public synchronized Object get(int index) {
        return null;
    }

    /** 
     * Replaces the element at the specified position in this Vector with the
     * specified element.
     *
     * @param index index of element to replace.
     * @param element element to be stored at the specified position.
     * @return the element previously at the specified position.
     * @exception ArrayIndexOutOfBoundsException index out of range
     *		  (index &lt; 0 || index &gt;= size()).
     * @since 1.2
     */
    public synchronized Object set(int index, Object element) {
        return null;
    }

    /** 
     * Appends the specified element to the end of this Vector.
     *
     * @param o element to be appended to this Vector.
     * @return true (as per the general contract of Collection.add).
     * @since 1.2
     */
    public synchronized boolean add(Object o) {
        return false;
    }

    /** 
     * Removes the first occurrence of the specified element in this Vector
     * If the Vector does not contain the element, it is unchanged.  More
     * formally, removes the element with the lowest index i such that
     * <code>(o==null ? get(i)==null : o.equals(get(i)))</code> (if such
     * an element exists).
     *
     * @param o element to be removed from this Vector, if present.
     * @return true if the Vector contained the specified element.
     * @since 1.2
     */
    public boolean remove(Object o) {
        return false;
    }

    /** 
     * Inserts the specified element at the specified position in this Vector.
     * Shifts the element currently at that position (if any) and any
     * subsequent elements to the right (adds one to their indices).
     *
     * @param index index at which the specified element is to be inserted.
     * @param element element to be inserted.
     * @exception ArrayIndexOutOfBoundsException index is out of range
     *		  (index &lt; 0 || index &gt; size()).
     * @since 1.2
     */
    public void add(int index, Object element) { }

    /** 
     * Removes the element at the specified position in this Vector.
     * shifts any subsequent elements to the left (subtracts one from their
     * indices).  Returns the element that was removed from the Vector.
     *
     * @exception ArrayIndexOutOfBoundsException index out of range (index
     * 		  &lt; 0 || index &gt;= size()).
     * @param index the index of the element to removed.
     * @return element that was removed
     * @since 1.2
     */
    public synchronized Object remove(int index) {
        return null;
    }

    /** 
     * Removes all of the elements from this Vector.  The Vector will
     * be empty after this call returns (unless it throws an exception).
     *
     * @since 1.2
     */
    public void clear() { }

    /** 
     * Returns true if this Vector contains all of the elements in the
     * specified Collection.
     *
     * @param   c a collection whose elements will be tested for containment
     *          in this Vector
     * @return true if this Vector contains all of the elements in the
     *	       specified collection.
     * @throws NullPointerException if the specified collection is null.
     */
    public synchronized boolean containsAll(Collection c) {
        return false;
    }

    /** 
     * Appends all of the elements in the specified Collection to the end of
     * this Vector, in the order that they are returned by the specified
     * Collection's Iterator.  The behavior of this operation is undefined if
     * the specified Collection is modified while the operation is in progress.
     * (This implies that the behavior of this call is undefined if the
     * specified Collection is this Vector, and this Vector is nonempty.)
     *
     * @param c elements to be inserted into this Vector.
     * @return <tt>true</tt> if this Vector changed as a result of the call.
     * @throws NullPointerException if the specified collection is null.
     * @since 1.2
     */
    public synchronized boolean addAll(Collection c) {
        return false;
    }

    /** 
     * Removes from this Vector all of its elements that are contained in the
     * specified Collection.
     *
     * @param c a collection of elements to be removed from the Vector
     * @return true if this Vector changed as a result of the call.
     * @throws NullPointerException if the specified collection is null.
     * @since 1.2
     */
    public synchronized boolean removeAll(Collection c) {
        return false;
    }

    /** 
     * Retains only the elements in this Vector that are contained in the
     * specified Collection.  In other words, removes from this Vector all
     * of its elements that are not contained in the specified Collection. 
     *
     * @param c a collection of elements to be retained in this Vector
     *          (all other elements are removed)
     * @return true if this Vector changed as a result of the call.
     * @throws NullPointerException if the specified collection is null.
     * @since 1.2
     */
    public synchronized boolean retainAll(Collection c) {
        return false;
    }

    /** 
     * Inserts all of the elements in in the specified Collection into this
     * Vector at the specified position.  Shifts the element currently at
     * that position (if any) and any subsequent elements to the right
     * (increases their indices).  The new elements will appear in the Vector  
     * in the order that they are returned by the specified Collection's
     * iterator.
     *
     * @param index index at which to insert first element
     *		    from the specified collection.
     * @param c elements to be inserted into this Vector.
     * @return <tt>true</tt> if this Vector changed as a result of the call.
     * @exception ArrayIndexOutOfBoundsException index out of range (index
     *		  &lt; 0 || index &gt; size()).
     * @throws NullPointerException if the specified collection is null.
     * @since 1.2
     */
    public synchronized boolean addAll(int index, Collection c) {
        return false;
    }

    /** 
     * Compares the specified Object with this Vector for equality.  Returns
     * true if and only if the specified Object is also a List, both Lists
     * have the same size, and all corresponding pairs of elements in the two
     * Lists are <em>equal</em>.  (Two elements <code>e1</code> and
     * <code>e2</code> are <em>equal</em> if <code>(e1==null ? e2==null :
     * e1.equals(e2))</code>.)  In other words, two Lists are defined to be
     * equal if they contain the same elements in the same order.
     *
     * @param o the Object to be compared for equality with this Vector.
     * @return true if the specified Object is equal to this Vector
     */
    public synchronized boolean equals(Object o) {
        return false;
    }

    /** 
     * Returns the hash code value for this Vector.
     */
    public synchronized int hashCode() {
        return 0;
    }

    /** 
     * Returns a string representation of this Vector, containing
     * the String representation of each element.
     */
    public synchronized String toString() {
        return null;
    }

    /** 
     * Returns a view of the portion of this List between fromIndex,
     * inclusive, and toIndex, exclusive.  (If fromIndex and ToIndex are
     * equal, the returned List is empty.)  The returned List is backed by this
     * List, so changes in the returned List are reflected in this List, and
     * vice-versa.  The returned List supports all of the optional List
     * operations supported by this List.<p>
     *
     * This method eliminates the need for explicit range operations (of
     * the sort that commonly exist for arrays).   Any operation that expects
     * a List can be used as a range operation by operating on a subList view
     * instead of a whole List.  For example, the following idiom
     * removes a range of elements from a List:
     * <pre>
     *	    list.subList(from, to).clear();
     * </pre>
     * Similar idioms may be constructed for indexOf and lastIndexOf,
     * and all of the algorithms in the Collections class can be applied to
     * a subList.<p>
     *
     * The semantics of the List returned by this method become undefined if
     * the backing list (i.e., this List) is <i>structurally modified</i> in
     * any way other than via the returned List.  (Structural modifications are
     * those that change the size of the List, or otherwise perturb it in such
     * a fashion that iterations in progress may yield incorrect results.)
     *
     * @param fromIndex low endpoint (inclusive) of the subList.
     * @param toIndex high endpoint (exclusive) of the subList.
     * @return a view of the specified range within this List.
     * @throws IndexOutOfBoundsException endpoint index value out of range
     *         <code>(fromIndex &lt; 0 || toIndex &gt; size)</code>
     * @throws IllegalArgumentException endpoint indices out of order
     *	       <code>(fromIndex &gt; toIndex)</code>
     */
    public synchronized List subList(int fromIndex, int toIndex) {
        return null;
    }

    /** 
     * Removes from this List all of the elements whose index is between
     * fromIndex, inclusive and toIndex, exclusive.  Shifts any succeeding
     * elements to the left (reduces their index).
     * This call shortens the ArrayList by (toIndex - fromIndex) elements.  (If
     * toIndex==fromIndex, this operation has no effect.)
     *
     * @param fromIndex index of first element to be removed.
     * @param toIndex index after last element to be removed.
     */
    protected void removeRange(int fromIndex, int toIndex) { }

    /** 
     * Save the state of the <tt>Vector</tt> instance to a stream (that
     * is, serialize it).  This method is present merely for synchronization.
     * It just calls the default readObject method.
     */
    private synchronized void writeObject(java.io.ObjectOutputStream s)
        throws java.io.IOException
    { }
}
