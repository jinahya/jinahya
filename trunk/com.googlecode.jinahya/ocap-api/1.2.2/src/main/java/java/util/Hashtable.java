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

import java.io.*;

/** 
 * This class implements a hashtable, which maps keys to values. Any 
 * non-<code>null</code> object can be used as a key or as a value. <p>
 *
 * To successfully store and retrieve objects from a hashtable, the 
 * objects used as keys must implement the <code>hashCode</code> 
 * method and the <code>equals</code> method. <p>
 *
 * An instance of <code>Hashtable</code> has two parameters that affect its
 * performance: <i>initial capacity</i> and <i>load factor</i>.  The
 * <i>capacity</i> is the number of <i>buckets</i> in the hash table, and the
 * <i>initial capacity</i> is simply the capacity at the time the hash table
 * is created.  Note that the hash table is <i>open</i>: in the case of a "hash
 * collision", a single bucket stores multiple entries, which must be searched
 * sequentially.  The <i>load factor</i> is a measure of how full the hash
 * table is allowed to get before its capacity is automatically increased.
 * When the number of entries in the hashtable exceeds the product of the load
 * factor and the current capacity, the capacity is increased by calling the
 * <code>rehash</code> method.<p>
 *
 * Generally, the default load factor (.75) offers a good tradeoff between
 * time and space costs.  Higher values decrease the space overhead but
 * increase the time cost to look up an entry (which is reflected in most
 * <tt>Hashtable</tt> operations, including <tt>get</tt> and <tt>put</tt>).<p>
 *
 * The initial capacity controls a tradeoff between wasted space and the
 * need for <code>rehash</code> operations, which are time-consuming.
 * No <code>rehash</code> operations will <i>ever</i> occur if the initial
 * capacity is greater than the maximum number of entries the
 * <tt>Hashtable</tt> will contain divided by its load factor.  However,
 * setting the initial capacity too high can waste space.<p>
 *
 * If many entries are to be made into a <code>Hashtable</code>, 
 * creating it with a sufficiently large capacity may allow the 
 * entries to be inserted more efficiently than letting it perform 
 * automatic rehashing as needed to grow the table. <p>
 *
 * This example creates a hashtable of numbers. It uses the names of 
 * the numbers as keys:
 * <p><blockquote><pre>
 *     Hashtable numbers = new Hashtable();
 *     numbers.put("one", new Integer(1));
 *     numbers.put("two", new Integer(2));
 *     numbers.put("three", new Integer(3));
 * </pre></blockquote>
 * <p>
 * To retrieve a number, use the following code: 
 * <p><blockquote><pre>
 *     Integer n = (Integer)numbers.get("two");
 *     if (n != null) {
 *         System.out.println("two = " + n);
 *     }
 * </pre></blockquote>
 * <p>
 * As of the Java 2 platform v1.2, this class has been retrofitted to
 * implement Map, so that it becomes a part of Java's collection framework.
 * Unlike the new collection implementations, Hashtable is synchronized.<p>
 *
 * The Iterators returned by the iterator and listIterator methods
 * of the Collections returned by all of Hashtable's "collection view methods"
 * are <em>fail-fast</em>: if the Hashtable is structurally modified
 * at any time after the Iterator is created, in any way except through the
 * Iterator's own remove or add methods, the Iterator will throw a
 * ConcurrentModificationException.  Thus, in the face of concurrent
 * modification, the Iterator fails quickly and cleanly, rather than risking
 * arbitrary, non-deterministic behavior at an undetermined time in the future.
 * The Enumerations returned by Hashtable's keys and values methods are
 * <em>not</em> fail-fast.
 *
 * <p>Note that the fail-fast behavior of an iterator cannot be guaranteed
 * as it is, generally speaking, impossible to make any hard guarantees in the
 * presence of unsynchronized concurrent modification.  Fail-fast iterators
 * throw <tt>ConcurrentModificationException</tt> on a best-effort basis. 
 * Therefore, it would be wrong to write a program that depended on this
 * exception for its correctness: <i>the fail-fast behavior of iterators
 * should be used only to detect bugs.</i><p>
 *
 * This class is a member of the 
 * <a href="{@docRoot}/../guide/collections/index.html">
 * Java Collections Framework</a>.
 *
 * @author  Arthur van Hoff
 * @author  Josh Bloch
 * @version 1.82, 02/02/00
 * @see     Object#equals(java.lang.Object)
 * @see     Object#hashCode()
 * @see     Hashtable#rehash()
 * @see     Collection
 * @see	    Map
 * @see	    HashMap
 * @see	    TreeMap
 * @since JDK1.0
 */
public class Hashtable extends Dictionary
    implements Map, Cloneable, Serializable
{
    /** 
     * The table is rehashed when its size exceeds this threshold.  (The
     * value of this field is (int)(capacity * loadFactor).)
     *
     * @serial
     */
    private int threshold;

    /** 
     * The load factor for the hashtable.
     *
     * @serial
     */
    private float loadFactor;

    /** use serialVersionUID from JDK 1.0.2 for interoperability */
    private static final long serialVersionUID = 1421746759512286392L;

    /** 
     * Constructs a new, empty hashtable with the specified initial 
     * capacity and the specified load factor.
     *
     * @param      initialCapacity   the initial capacity of the hashtable.
     * @param      loadFactor        the load factor of the hashtable.
     * @exception  IllegalArgumentException  if the initial capacity is less
     *             than zero, or if the load factor is nonpositive.
     */
    public Hashtable(int initialCapacity, float loadFactor) { }

    /** 
     * Constructs a new, empty hashtable with the specified initial capacity
     * and default load factor, which is <tt>0.75</tt>.
     *
     * @param     initialCapacity   the initial capacity of the hashtable.
     * @exception IllegalArgumentException if the initial capacity is less
     *              than zero.
     */
    public Hashtable(int initialCapacity) { }

    /** 
     * Constructs a new, empty hashtable with a default initial capacity (11)
     * and load factor, which is <tt>0.75</tt>. 
     */
    public Hashtable() { }

    /** 
     * Constructs a new hashtable with the same mappings as the given 
     * Map.  The hashtable is created with an initial capacity sufficient to
     * hold the mappings in the given Map and a default load factor, which is
     * <tt>0.75</tt>.
     *
     * @param t the map whose mappings are to be placed in this map.
     * @throws NullPointerException if the specified map is null.
     * @since   1.2
     */
    public Hashtable(Map t) { }

    /** 
     * Returns the number of keys in this hashtable.
     *
     * @return  the number of keys in this hashtable.
     */
    public synchronized int size() {
        return 0;
    }

    /** 
     * Tests if this hashtable maps no keys to values.
     *
     * @return  <code>true</code> if this hashtable maps no keys to values;
     *          <code>false</code> otherwise.
     */
    public synchronized boolean isEmpty() {
        return false;
    }

    /** 
     * Returns an enumeration of the keys in this hashtable.
     *
     * @return  an enumeration of the keys in this hashtable.
     * @see     Enumeration
     * @see     #elements()
     * @see	#keySet()
     * @see	Map
     */
    public synchronized Enumeration keys() {
        return null;
    }

    /** 
     * Returns an enumeration of the values in this hashtable.
     * Use the Enumeration methods on the returned object to fetch the elements
     * sequentially.
     *
     * @return  an enumeration of the values in this hashtable.
     * @see     java.util.Enumeration
     * @see     #keys()
     * @see	#values()
     * @see	Map
     */
    public synchronized Enumeration elements() {
        return null;
    }

    /** 
     * Tests if some key maps into the specified value in this hashtable.
     * This operation is more expensive than the <code>containsKey</code>
     * method.<p>
     *
     * Note that this method is identical in functionality to containsValue,
     * (which is part of the Map interface in the collections framework).
     * 
     * @param      value   a value to search for.
     * @return     <code>true</code> if and only if some key maps to the
     *             <code>value</code> argument in this hashtable as 
     *             determined by the <tt>equals</tt> method;
     *             <code>false</code> otherwise.
     * @exception  NullPointerException  if the value is <code>null</code>.
     * @see        #containsKey(Object)
     * @see        #containsValue(Object)
     * @see	   Map
     */
    public synchronized boolean contains(Object value) {
        return false;
    }

    /** 
     * Returns true if this Hashtable maps one or more keys to this value.<p>
     *
     * Note that this method is identical in functionality to contains
     * (which predates the Map interface).
     *
     * @param value value whose presence in this Hashtable is to be tested.
     * @return <tt>true</tt> if this map maps one or more keys to the
     *         specified value.
     * @throws NullPointerException  if the value is <code>null</code>.
     * @see	   Map
     * @since 1.2
     */
    public boolean containsValue(Object value) {
        return false;
    }

    /** 
     * Tests if the specified object is a key in this hashtable.
     * 
     * @param   key   possible key.
     * @return  <code>true</code> if and only if the specified object 
     *          is a key in this hashtable, as determined by the 
     *          <tt>equals</tt> method; <code>false</code> otherwise.
     * @throws  NullPointerException  if the key is <code>null</code>.
     * @see     #contains(Object)
     */
    public synchronized boolean containsKey(Object key) {
        return false;
    }

    /** 
     * Returns the value to which the specified key is mapped in this hashtable.
     *
     * @param   key   a key in the hashtable.
     * @return  the value to which the key is mapped in this hashtable;
     *          <code>null</code> if the key is not mapped to any value in
     *          this hashtable.
     * @throws  NullPointerException  if the key is <code>null</code>.
     * @see     #put(Object, Object)
     */
    public synchronized Object get(Object key) {
        return null;
    }

    /** 
     * Increases the capacity of and internally reorganizes this 
     * hashtable, in order to accommodate and access its entries more 
     * efficiently.  This method is called automatically when the 
     * number of keys in the hashtable exceeds this hashtable's capacity 
     * and load factor. 
     */
    protected void rehash() { }

    /** 
     * Maps the specified <code>key</code> to the specified 
     * <code>value</code> in this hashtable. Neither the key nor the 
     * value can be <code>null</code>. <p>
     *
     * The value can be retrieved by calling the <code>get</code> method 
     * with a key that is equal to the original key. 
     *
     * @param      key     the hashtable key.
     * @param      value   the value.
     * @return     the previous value of the specified key in this hashtable,
     *             or <code>null</code> if it did not have one.
     * @exception  NullPointerException  if the key or value is
     *               <code>null</code>.
     * @see     Object#equals(Object)
     * @see     #get(Object)
     */
    public synchronized Object put(Object key, Object value) {
        return null;
    }

    /** 
     * Removes the key (and its corresponding value) from this 
     * hashtable. This method does nothing if the key is not in the hashtable.
     *
     * @param   key   the key that needs to be removed.
     * @return  the value to which the key had been mapped in this hashtable,
     *          or <code>null</code> if the key did not have a mapping.
     * @throws  NullPointerException  if the key is <code>null</code>.
     */
    public synchronized Object remove(Object key) {
        return null;
    }

    /** 
     * Copies all of the mappings from the specified Map to this Hashtable
     * These mappings will replace any mappings that this Hashtable had for any
     * of the keys currently in the specified Map. 
     *
     * @param t Mappings to be stored in this map.
     * @throws NullPointerException if the specified map is null.
     * @since 1.2
     */
    public synchronized void putAll(Map t) { }

    /** 
     * Clears this hashtable so that it contains no keys. 
     */
    public synchronized void clear() { }

    /** 
     * Creates a shallow copy of this hashtable. All the structure of the 
     * hashtable itself is copied, but the keys and values are not cloned. 
     * This is a relatively expensive operation.
     *
     * @return  a clone of the hashtable.
     */
    public synchronized Object clone() {
        return null;
    }

    /** 
     * Returns a string representation of this <tt>Hashtable</tt> object 
     * in the form of a set of entries, enclosed in braces and separated 
     * by the ASCII characters "<tt>,&nbsp;</tt>" (comma and space). Each 
     * entry is rendered as the key, an equals sign <tt>=</tt>, and the 
     * associated element, where the <tt>toString</tt> method is used to 
     * convert the key and element to strings. <p>Overrides to 
     * <tt>toString</tt> method of <tt>Object</tt>.
     *
     * @return  a string representation of this hashtable.
     */
    public synchronized String toString() {
        return null;
    }

    /** 
     * Returns a Set view of the keys contained in this Hashtable.  The Set
     * is backed by the Hashtable, so changes to the Hashtable are reflected
     * in the Set, and vice-versa.  The Set supports element removal
     * (which removes the corresponding entry from the Hashtable), but not
     * element addition.
     *
     * @return a set view of the keys contained in this map.
     * @since 1.2
     */
    public Set keySet() {
        return null;
    }

    /** 
     * Returns a Set view of the entries contained in this Hashtable.
     * Each element in this collection is a Map.Entry.  The Set is
     * backed by the Hashtable, so changes to the Hashtable are reflected in
     * the Set, and vice-versa.  The Set supports element removal
     * (which removes the corresponding entry from the Hashtable),
     * but not element addition.
     *
     * @return a set view of the mappings contained in this map.
     * @see   Map.Entry
     * @since 1.2
     */
    public Set entrySet() {
        return null;
    }

    /** 
     * Returns a Collection view of the values contained in this Hashtable.
     * The Collection is backed by the Hashtable, so changes to the Hashtable
     * are reflected in the Collection, and vice-versa.  The Collection
     * supports element removal (which removes the corresponding entry from
     * the Hashtable), but not element addition.
     *
     * @return a collection view of the values contained in this map.
     * @since 1.2
     */
    public Collection values() {
        return null;
    }

    /** 
     * Compares the specified Object with this Map for equality,
     * as per the definition in the Map interface.
     *
     * @param  o object to be compared for equality with this Hashtable
     * @return true if the specified Object is equal to this Map.
     * @see Map#equals(Object)
     * @since 1.2
     */
    public synchronized boolean equals(Object o) {
        return false;
    }

    /** 
     * Returns the hash code value for this Map as per the definition in the
     * Map interface.
     *
     * @see Map#hashCode()
     * @since 1.2
     */
    public synchronized int hashCode() {
        return 0;
    }

    /** 
     * Reconstitute the Hashtable from a stream (i.e., deserialize it).
     */
    private void readObject(ObjectInputStream s)
        throws IOException, ClassNotFoundException
    { }

    /** 
     * Save the state of the Hashtable to a stream (i.e., serialize it).
     *
     * @serialData The <i>capacity</i> of the Hashtable (the length of the
     *		   bucket array) is emitted (int), followed  by the
     *		   <i>size</i> of the Hashtable (the number of key-value
     *		   mappings), followed by the key (Object) and value (Object)
     *		   for each key-value mapping represented by the Hashtable
     *		   The key-value mappings are emitted in no particular order.
     */
    private synchronized void writeObject(ObjectOutputStream s)
        throws IOException
    { }

    static class Entry implements Map.Entry { 

        public Object getKey() {
            return null;
        }

        public Object getValue() {
            return null;
        }
    
        public Object setValue(Object newValue) {
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

}
