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
 * Red-Black tree based implementation of the <tt>SortedMap</tt> interface.
 * This class guarantees that the map will be in ascending key order, sorted
 * according to the <i>natural order</i> for the key's class (see
 * <tt>Comparable</tt>), or by the comparator provided at creation time,
 * depending on which constructor is used.<p>
 *
 * This implementation provides guaranteed log(n) time cost for the
 * <tt>containsKey</tt>, <tt>get</tt>, <tt>put</tt> and <tt>remove</tt>
 * operations.  Algorithms are adaptations of those in Cormen, Leiserson, and
 * Rivest's <I>Introduction to Algorithms</I>.<p>
 *
 * Note that the ordering maintained by a sorted map (whether or not an
 * explicit comparator is provided) must be <i>consistent with equals</i> if
 * this sorted map is to correctly implement the <tt>Map</tt> interface.  (See
 * <tt>Comparable</tt> or <tt>Comparator</tt> for a precise definition of
 * <i>consistent with equals</i>.)  This is so because the <tt>Map</tt>
 * interface is defined in terms of the equals operation, but a map performs
 * all key comparisons using its <tt>compareTo</tt> (or <tt>compare</tt>)
 * method, so two keys that are deemed equal by this method are, from the
 * standpoint of the sorted map, equal.  The behavior of a sorted map
 * <i>is</i> well-defined even if its ordering is inconsistent with equals; it
 * just fails to obey the general contract of the <tt>Map</tt> interface.<p>
 *
 * <b>Note that this implementation is not synchronized.</b> If multiple
 * threads access a map concurrently, and at least one of the threads modifies
 * the map structurally, it <i>must</i> be synchronized externally.  (A
 * structural modification is any operation that adds or deletes one or more
 * mappings; merely changing the value associated with an existing key is not
 * a structural modification.)  This is typically accomplished by
 * synchronizing on some object that naturally encapsulates the map.  If no
 * such object exists, the map should be "wrapped" using the
 * <tt>Collections.synchronizedMap</tt> method.  This is best done at creation
 * time, to prevent accidental unsynchronized access to the map: 
 * <pre>
 *     Map m = Collections.synchronizedMap(new TreeMap(...));
 * </pre><p>
 *
 * The iterators returned by all of this class's "collection view methods" are
 * <i>fail-fast</i>: if the map is structurally modified at any time after the
 * iterator is created, in any way except through the iterator's own
 * <tt>remove</tt> or <tt>add</tt> methods, the iterator throws a
 * <tt>ConcurrentModificationException</tt>.  Thus, in the face of concurrent
 * modification, the iterator fails quickly and cleanly, rather than risking
 * arbitrary, non-deterministic behavior at an undetermined time in the
 * future.
 *
 * <p>Note that the fail-fast behavior of an iterator cannot be guaranteed
 * as it is, generally speaking, impossible to make any hard guarantees in the
 * presence of unsynchronized concurrent modification.  Fail-fast iterators
 * throw <tt>ConcurrentModificationException</tt> on a best-effort basis. 
 * Therefore, it would be wrong to write a program that depended on this
 * exception for its correctness:   <i>the fail-fast behavior of iterators
 * should be used only to detect bugs.</i><p>
 *
 * This class is a member of the 
 * <a href="{@docRoot}/../guide/collections/index.html">
 * Java Collections Framework</a>.
 *
 * @author  Josh Bloch and Doug Lea
 * @version 1.43, 02/02/00
 * @see Map
 * @see HashMap
 * @see Hashtable
 * @see Comparable
 * @see Comparator
 * @see Collection
 * @see Collections#synchronizedMap(Map)
 * @since 1.2
 */
public class TreeMap extends AbstractMap
    implements SortedMap, Cloneable, java.io.Serializable
{
    /** 
     * The Comparator used to maintain order in this TreeMap, or
     * null if this TreeMap uses its elements natural ordering.
     *
     * @serial
     */
    private Comparator comparator;

    /** 
     * Constructs a new, empty map, sorted according to the keys' natural
     * order.  All keys inserted into the map must implement the
     * <tt>Comparable</tt> interface.  Furthermore, all such keys must be
     * <i>mutually comparable</i>: <tt>k1.compareTo(k2)</tt> must not throw a
     * ClassCastException for any elements <tt>k1</tt> and <tt>k2</tt> in the
     * map.  If the user attempts to put a key into the map that violates this
     * constraint (for example, the user attempts to put a string key into a
     * map whose keys are integers), the <tt>put(Object key, Object
     * value)</tt> call will throw a <tt>ClassCastException</tt>.
     *
     * @see Comparable
     */
    public TreeMap() { }

    /** 
     * Constructs a new, empty map, sorted according to the given comparator.
     * All keys inserted into the map must be <i>mutually comparable</i> by
     * the given comparator: <tt>comparator.compare(k1, k2)</tt> must not
     * throw a <tt>ClassCastException</tt> for any keys <tt>k1</tt> and
     * <tt>k2</tt> in the map.  If the user attempts to put a key into the
     * map that violates this constraint, the <tt>put(Object key, Object
     * value)</tt> call will throw a <tt>ClassCastException</tt>.
     *
     * @param c the comparator that will be used to sort this map.  A
     *        <tt>null</tt> value indicates that the keys' <i>natural
     *        ordering</i> should be used.
     */
    public TreeMap(Comparator c) { }

    /** 
     * Constructs a new map containing the same mappings as the given map,
     * sorted according to the keys' <i>natural order</i>.  All keys inserted
     * into the new map must implement the <tt>Comparable</tt> interface.
     * Furthermore, all such keys must be <i>mutually comparable</i>:
     * <tt>k1.compareTo(k2)</tt> must not throw a <tt>ClassCastException</tt>
     * for any elements <tt>k1</tt> and <tt>k2</tt> in the map.  This method
     * runs in n*log(n) time.
     *
     * @param  m the map whose mappings are to be placed in this map.
     * @throws ClassCastException the keys in t are not Comparable, or
     *         are not mutually comparable.
     * @throws NullPointerException if the specified map is null.
     */
    public TreeMap(Map m) { }

    /** 
     * Constructs a new map containing the same mappings as the given
     * <tt>SortedMap</tt>, sorted according to the same ordering.  This method
     * runs in linear time.
     *
     * @param  m the sorted map whose mappings are to be placed in this map,
     *         and whose comparator is to be used to sort this map.
     * @throws NullPointerException if the specified sorted map is null.
     */
    public TreeMap(SortedMap m) { }

    /** 
     * Returns the number of key-value mappings in this map.
     *
     * @return the number of key-value mappings in this map.
     */
    public int size() {
        return 0;
    }

    /** 
     * Returns <tt>true</tt> if this map contains a mapping for the specified
     * key.
     *
     * @param key key whose presence in this map is to be tested.
     * 
     * @return <tt>true</tt> if this map contains a mapping for the
     *            specified key.
     * @throws ClassCastException if the key cannot be compared with the keys
     *                  currently in the map.
     * @throws NullPointerException key is <tt>null</tt> and this map uses
     *                  natural ordering, or its comparator does not tolerate
     *            <tt>null</tt> keys.
     */
    public boolean containsKey(Object key) {
        return false;
    }

    /** 
     * Returns <tt>true</tt> if this map maps one or more keys to the
     * specified value.  More formally, returns <tt>true</tt> if and only if
     * this map contains at least one mapping to a value <tt>v</tt> such
     * that <tt>(value==null ? v==null : value.equals(v))</tt>.  This
     * operation will probably require time linear in the Map size for most
     * implementations of Map.
     *
     * @param value value whose presence in this Map is to be tested.
     * @return  <tt>true</tt> if a mapping to <tt>value</tt> exists;
     *		<tt>false</tt> otherwise.
     * @since 1.2
     */
    public boolean containsValue(Object value) {
        return false;
    }

    /** 
     * Returns the value to which this map maps the specified key.  Returns
     * <tt>null</tt> if the map contains no mapping for this key.  A return
     * value of <tt>null</tt> does not <i>necessarily</i> indicate that the
     * map contains no mapping for the key; it's also possible that the map
     * explicitly maps the key to <tt>null</tt>.  The <tt>containsKey</tt>
     * operation may be used to distinguish these two cases.
     *
     * @param key key whose associated value is to be returned.
     * @return the value to which this map maps the specified key, or
     *               <tt>null</tt> if the map contains no mapping for the key.
     * @throws    ClassCastException key cannot be compared with the keys
     *                  currently in the map.
     * @throws NullPointerException key is <tt>null</tt> and this map uses
     *                  natural ordering, or its comparator does not tolerate
     *                  <tt>null</tt> keys.
     * 
     * @see #containsKey(Object)
     */
    public Object get(Object key) {
        return null;
    }

    /** 
     * Returns the comparator used to order this map, or <tt>null</tt> if this
     * map uses its keys' natural order.
     *
     * @return the comparator associated with this sorted map, or
     *                <tt>null</tt> if it uses its keys' natural sort method.
     */
    public Comparator comparator() {
        return null;
    }

    /** 
     * Returns the first (lowest) key currently in this sorted map.
     *
     * @return the first (lowest) key currently in this sorted map.
     * @throws    NoSuchElementException Map is empty.
     */
    public Object firstKey() {
        return null;
    }

    /** 
     * Returns the last (highest) key currently in this sorted map.
     *
     * @return the last (highest) key currently in this sorted map.
     * @throws    NoSuchElementException Map is empty.
     */
    public Object lastKey() {
        return null;
    }

    /** 
     * Copies all of the mappings from the specified map to this map.  These
     * mappings replace any mappings that this map had for any of the keys
     * currently in the specified map.
     *
     * @param     map mappings to be stored in this map.
     * @throws    ClassCastException class of a key or value in the specified
     *                   map prevents it from being stored in this map.
     * 
     * @throws NullPointerException if the given map is <tt>null</tt> or
     *         this map does not permit <tt>null</tt> keys and a 
     *         key in the specified map is <tt>null</tt>.
     */
    public void putAll(Map map) { }

    /** 
     * Associates the specified value with the specified key in this map.
     * If the map previously contained a mapping for this key, the old
     * value is replaced.
     *
     * @param key key with which the specified value is to be associated.
     * @param value value to be associated with the specified key.
     * 
     * @return previous value associated with specified key, or <tt>null</tt>
     *         if there was no mapping for key.  A <tt>null</tt> return can
     *         also indicate that the map previously associated <tt>null</tt>
     *         with the specified key.
     * @throws    ClassCastException key cannot be compared with the keys
     *            currently in the map.
     * @throws NullPointerException key is <tt>null</tt> and this map uses
     *         natural order, or its comparator does not tolerate
     *         <tt>null</tt> keys.
     */
    public Object put(Object key, Object value) {
        return null;
    }

    /** 
     * Removes the mapping for this key from this TreeMap if present.
     *
     * @param  key key for which mapping should be removed
     * @return previous value associated with specified key, or <tt>null</tt>
     *         if there was no mapping for key.  A <tt>null</tt> return can
     *         also indicate that the map previously associated
     *         <tt>null</tt> with the specified key.
     * 
     * @throws    ClassCastException key cannot be compared with the keys
     *            currently in the map.
     * @throws NullPointerException key is <tt>null</tt> and this map uses
     *         natural order, or its comparator does not tolerate
     *         <tt>null</tt> keys.
     */
    public Object remove(Object key) {
        return null;
    }

    /** 
     * Removes all mappings from this TreeMap.
     */
    public void clear() { }

    /** 
     * Returns a shallow copy of this <tt>TreeMap</tt> instance. (The keys and
     * values themselves are not cloned.)
     *
     * @return a shallow copy of this Map.
     */
    public Object clone() {
        return null;
    }

    /** 
     * Returns a Set view of the keys contained in this map.  The set's
     * iterator will return the keys in ascending order.  The map is backed by
     * this <tt>TreeMap</tt> instance, so changes to this map are reflected in
     * the Set, and vice-versa.  The Set supports element removal, which
     * removes the corresponding mapping from the map, via the
     * <tt>Iterator.remove</tt>, <tt>Set.remove</tt>, <tt>removeAll</tt>,
     * <tt>retainAll</tt>, and <tt>clear</tt> operations.  It does not support
     * the <tt>add</tt> or <tt>addAll</tt> operations.
     *
     * @return a set view of the keys contained in this TreeMap.
     */
    public Set keySet() {
        return null;
    }

    /** 
     * Returns a collection view of the values contained in this map.  The
     * collection's iterator will return the values in the order that their
     * corresponding keys appear in the tree.  The collection is backed by
     * this <tt>TreeMap</tt> instance, so changes to this map are reflected in
     * the collection, and vice-versa.  The collection supports element
     * removal, which removes the corresponding mapping from the map through
     * the <tt>Iterator.remove</tt>, <tt>Collection.remove</tt>,
     * <tt>removeAll</tt>, <tt>retainAll</tt>, and <tt>clear</tt> operations.
     * It does not support the <tt>add</tt> or <tt>addAll</tt> operations.
     *
     * @return a collection view of the values contained in this map.
     */
    public Collection values() {
        return null;
    }

    /** 
     * Returns a set view of the mappings contained in this map.  The set's
     * iterator returns the mappings in ascending key order.  Each element in
     * the returned set is a <tt>Map.Entry</tt>.  The set is backed by this
     * map, so changes to this map are reflected in the set, and vice-versa.
     * The set supports element removal, which removes the corresponding
     * mapping from the TreeMap, through the <tt>Iterator.remove</tt>,
     * <tt>Set.remove</tt>, <tt>removeAll</tt>, <tt>retainAll</tt> and
     * <tt>clear</tt> operations.  It does not support the <tt>add</tt> or
     * <tt>addAll</tt> operations.
     *
     * @return a set view of the mappings contained in this map.
     * @see Map.Entry
     */
    public Set entrySet() {
        return null;
    }

    /** 
     * Returns a view of the portion of this map whose keys range from
     * <tt>fromKey</tt>, inclusive, to <tt>toKey</tt>, exclusive.  (If
     * <tt>fromKey</tt> and <tt>toKey</tt> are equal, the returned sorted map
     * is empty.)  The returned sorted map is backed by this map, so changes
     * in the returned sorted map are reflected in this map, and vice-versa.
     * The returned sorted map supports all optional map operations.<p>
     *
     * The sorted map returned by this method will throw an
     * <tt>IllegalArgumentException</tt> if the user attempts to insert a key
     * less than <tt>fromKey</tt> or greater than or equal to
     * <tt>toKey</tt>.<p>
     *
     * Note: this method always returns a <i>half-open range</i> (which
     * includes its low endpoint but not its high endpoint).  If you need a
     * <i>closed range</i> (which includes both endpoints), and the key type
     * allows for calculation of the successor a given key, merely request the
     * subrange from <tt>lowEndpoint</tt> to <tt>successor(highEndpoint)</tt>.
     * For example, suppose that <tt>m</tt> is a sorted map whose keys are
     * strings.  The following idiom obtains a view containing all of the
     * key-value mappings in <tt>m</tt> whose keys are between <tt>low</tt>
     * and <tt>high</tt>, inclusive:
     *             <pre>    SortedMap sub = m.submap(low, high+"\0");</pre>
     * A similar technique can be used to generate an <i>open range</i> (which
     * contains neither endpoint).  The following idiom obtains a view
     * containing all of the key-value mappings in <tt>m</tt> whose keys are
     * between <tt>low</tt> and <tt>high</tt>, exclusive:
     *             <pre>    SortedMap sub = m.subMap(low+"\0", high);</pre>
     *
     * @param fromKey low endpoint (inclusive) of the subMap.
     * @param toKey high endpoint (exclusive) of the subMap.
     * 
     * @return a view of the portion of this map whose keys range from
     *                <tt>fromKey</tt>, inclusive, to <tt>toKey</tt>, exclusive.
     * 
     * @throws ClassCastException if <tt>fromKey</tt> and <tt>toKey</tt>
     *         cannot be compared to one another using this map's comparator
     *         (or, if the map has no comparator, using natural ordering).
     * @throws IllegalArgumentException if <tt>fromKey</tt> is greater than
     *         <tt>toKey</tt>.
     * @throws NullPointerException if <tt>fromKey</tt> or <tt>toKey</tt> is
     *               <tt>null</tt> and this map uses natural order, or its
     *               comparator does not tolerate <tt>null</tt> keys.
     */
    public SortedMap subMap(Object fromKey, Object toKey) {
        return null;
    }

    /** 
     * Returns a view of the portion of this map whose keys are strictly less
     * than <tt>toKey</tt>.  The returned sorted map is backed by this map, so
     * changes in the returned sorted map are reflected in this map, and
     * vice-versa.  The returned sorted map supports all optional map
     * operations.<p>
     *
     * The sorted map returned by this method will throw an
     * <tt>IllegalArgumentException</tt> if the user attempts to insert a key
     * greater than or equal to <tt>toKey</tt>.<p>
     *
     * Note: this method always returns a view that does not contain its
     * (high) endpoint.  If you need a view that does contain this endpoint,
     * and the key type allows for calculation of the successor a given key,
     * merely request a headMap bounded by <tt>successor(highEndpoint)</tt>.
     * For example, suppose that suppose that <tt>m</tt> is a sorted map whose
     * keys are strings.  The following idiom obtains a view containing all of
     * the key-value mappings in <tt>m</tt> whose keys are less than or equal
     * to <tt>high</tt>:
     * <pre>
     *     SortedMap head = m.headMap(high+"\0");
     * </pre>
     *
     * @param toKey high endpoint (exclusive) of the headMap.
     * @return a view of the portion of this map whose keys are strictly
     *                less than <tt>toKey</tt>.
     *
     * @throws ClassCastException if <tt>toKey</tt> is not compatible
     *         with this map's comparator (or, if the map has no comparator,
     *         if <tt>toKey</tt> does not implement <tt>Comparable</tt>).
     * @throws IllegalArgumentException if this map is itself a subMap,
     *         headMap, or tailMap, and <tt>toKey</tt> is not within the
     *         specified range of the subMap, headMap, or tailMap.
     * @throws NullPointerException if <tt>toKey</tt> is <tt>null</tt> and
     *               this map uses natural order, or its comparator does not
     *               tolerate <tt>null</tt> keys.
     */
    public SortedMap headMap(Object toKey) {
        return null;
    }

    /** 
     * Returns a view of the portion of this map whose keys are greater than
     * or equal to <tt>fromKey</tt>.  The returned sorted map is backed by
     * this map, so changes in the returned sorted map are reflected in this
     * map, and vice-versa.  The returned sorted map supports all optional map
     * operations.<p>
     *
     * The sorted map returned by this method will throw an
     * <tt>IllegalArgumentException</tt> if the user attempts to insert a key
     * less than <tt>fromKey</tt>.<p>
     *
     * Note: this method always returns a view that contains its (low)
     * endpoint.  If you need a view that does not contain this endpoint, and
     * the element type allows for calculation of the successor a given value,
     * merely request a tailMap bounded by <tt>successor(lowEndpoint)</tt>.
     * For For example, suppose that suppose that <tt>m</tt> is a sorted map
     * whose keys are strings.  The following idiom obtains a view containing
     * all of the key-value mappings in <tt>m</tt> whose keys are strictly
     * greater than <tt>low</tt>: <pre>
     *     SortedMap tail = m.tailMap(low+"\0");
     * </pre>
     *
     * @param fromKey low endpoint (inclusive) of the tailMap.
     * @return a view of the portion of this map whose keys are greater
     *                than or equal to <tt>fromKey</tt>.
     * @throws ClassCastException if <tt>fromKey</tt> is not compatible
     *         with this map's comparator (or, if the map has no comparator,
     *         if <tt>fromKey</tt> does not implement <tt>Comparable</tt>).
     * @throws IllegalArgumentException if this map is itself a subMap,
     *         headMap, or tailMap, and <tt>fromKey</tt> is not within the
     *         specified range of the subMap, headMap, or tailMap.
     * @throws NullPointerException if <tt>fromKey</tt> is <tt>null</tt> and
     *               this map uses natural order, or its comparator does not
     *               tolerate <tt>null</tt> keys.
     */
    public SortedMap tailMap(Object fromKey) {
        return null;
    }

    /** 
     * Reconstitute the <tt>TreeMap</tt> instance from a stream (i.e.,
     * deserialize it).
     */
    private void readObject(java.io.ObjectInputStream s)
        throws java.io.IOException, ClassNotFoundException
    { }

    /** 
     * Save the state of the <tt>TreeMap</tt> instance to a stream (i.e.,
     * serialize it).
     *
     * @serialData The <i>size</i> of the TreeMap (the number of key-value
     *             mappings) is emitted (int), followed by the key (Object)
     *             and value (Object) for each key-value mapping represented
     *             by the TreeMap. The key-value mappings are emitted in
     *             key-order (as determined by the TreeMap's Comparator,
     *             or by the keys' natural ordering if the TreeMap has no
     *             Comparator).
     */
    private void writeObject(java.io.ObjectOutputStream s)
        throws java.io.IOException
    { }

    private static final long serialVersionUID = 919286545866124006L;

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
