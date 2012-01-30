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
 * This class implements a vector of bits that grows as needed. Each 
 * component of the bit set has a <code>boolean</code> value. The 
 * bits of a <code>BitSet</code> are indexed by nonnegative integers. 
 * Individual indexed bits can be examined, set, or cleared. One 
 * <code>BitSet</code> may be used to modify the contents of another 
 * <code>BitSet</code> through logical AND, logical inclusive OR, and 
 * logical exclusive OR operations.
 * <p>
 * By default, all bits in the set initially have the value 
 * <code>false</code>. 
 * <p>
 * Every bit set has a current size, which is the number of bits 
 * of space currently in use by the bit set. Note that the size is
 * related to the implementation of a bit set, so it may change with
 * implementation. The length of a bit set relates to logical length
 * of a bit set and is defined independently of implementation.
 * <p>
 * Unless otherwise noted, passing a null parameter to any of the
 * methods in a <code>BitSet</code> will result in a
 * <code>NullPointerException</code>.
 *
 * A <code>BitSet</code> is not safe for multithreaded use without
 * external synchronization.
 *
 * @author  Arthur van Hoff
 * @author  Michael McCloskey
 * @version 1.46, 02/02/00
 * @since   JDK1.0
 */
public class BitSet implements Cloneable, Serializable
{
    /** 
     * The bits in this BitSet.  The ith bit is stored in bits[i/64] at
     * bit position i % 64 (where bit position 0 refers to the least
     * significant bit and 63 refers to the most significant bit).
     * INVARIANT: The words in bits[] above unitInUse-1 are zero.
     *
     * @serial
     */
    private long[] bits;

    /* use serialVersionUID from JDK 1.0.2 for interoperability */
    private static final long serialVersionUID = 7997698588986878753L;

    /** 
     * Creates a new bit set. All bits are initially <code>false</code>.
     */
    public BitSet() { }

    /** 
     * Creates a bit set whose initial size is large enough to explicitly
     * represent bits with indices in the range <code>0</code> through
     * <code>nbits-1</code>. All bits are initially <code>false</code>. 
     *
     * @param     nbits   the initial size of the bit set.
     * @exception NegativeArraySizeException if the specified initial size
     *               is negative.
     */
    public BitSet(int nbits) { }

    /** 
     * Sets the bit at the specified index to to the complement of its
     * current value.
     * 
     * @param   bitIndex the index of the bit to flip.
     * @exception IndexOutOfBoundsException if the specified index is negative.
     * @since   1.4
     */
    public void flip(int bitIndex) { }

    /** 
     * Sets each bit from the specified fromIndex(inclusive) to the
     * specified toIndex(exclusive) to the complement of its current
     * value.
     * 
     * @param     fromIndex   index of the first bit to flip.
     * @param     toIndex index after the last bit to flip.
     * @exception IndexOutOfBoundsException if <tt>fromIndex</tt> is negative,
     *            or <tt>toIndex</tt> is negative, or <tt>fromIndex</tt> is
     *            larger than <tt>toIndex</tt>.
     * @since   1.4
     */
    public void flip(int fromIndex, int toIndex) { }

    /** 
     * Sets the bit at the specified index to <code>true</code>.
     *
     * @param     bitIndex   a bit index.
     * @exception IndexOutOfBoundsException if the specified index is negative.
     * @since     JDK1.0
     */
    public void set(int bitIndex) { }

    /** 
     * Sets the bit at the specified index to the specified value.
     *
     * @param     bitIndex   a bit index.
     * @param     value a boolean value to set.
     * @exception IndexOutOfBoundsException if the specified index is negative.
     * @since     1.4
     */
    public void set(int bitIndex, boolean value) { }

    /** 
     * Sets the bits from the specified fromIndex(inclusive) to the
     * specified toIndex(exclusive) to <code>true</code>.
     *
     * @param     fromIndex   index of the first bit to be set.
     * @param     toIndex index after the last bit to be set.
     * @exception IndexOutOfBoundsException if <tt>fromIndex</tt> is negative,
     *            or <tt>toIndex</tt> is negative, or <tt>fromIndex</tt> is
     *            larger than <tt>toIndex</tt>.
     * @since     1.4
     */
    public void set(int fromIndex, int toIndex) { }

    /** 
     * Sets the bits from the specified fromIndex(inclusive) to the
     * specified toIndex(exclusive) to the specified value.
     *
     * @param     fromIndex   index of the first bit to be set.
     * @param     toIndex index after the last bit to be set
     * @param     value value to set the selected bits to
     * @exception IndexOutOfBoundsException if <tt>fromIndex</tt> is negative,
     *            or <tt>toIndex</tt> is negative, or <tt>fromIndex</tt> is
     *            larger than <tt>toIndex</tt>.
     * @since     1.4
     */
    public void set(int fromIndex, int toIndex, boolean value) { }

    /** 
     * Sets the bit specified by the index to <code>false</code>.
     *
     * @param     bitIndex   the index of the bit to be cleared.
     * @exception IndexOutOfBoundsException if the specified index is negative.
     * @since     JDK1.0
     */
    public void clear(int bitIndex) { }

    /** 
     * Sets the bits from the specified fromIndex(inclusive) to the
     * specified toIndex(exclusive) to <code>false</code>.
     *
     * @param     fromIndex   index of the first bit to be cleared.
     * @param     toIndex index after the last bit to be cleared. 
     * @exception IndexOutOfBoundsException if <tt>fromIndex</tt> is negative,
     *            or <tt>toIndex</tt> is negative, or <tt>fromIndex</tt> is
     *            larger than <tt>toIndex</tt>.
     * @since     1.4
     */
    public void clear(int fromIndex, int toIndex) { }

    /** 
     * Sets all of the bits in this BitSet to <code>false</code>.
     *
     * @since   1.4
     */
    public void clear() { }

    /** 
     * Returns the value of the bit with the specified index. The value 
     * is <code>true</code> if the bit with the index <code>bitIndex</code> 
     * is currently set in this <code>BitSet</code>; otherwise, the result 
     * is <code>false</code>.
     *
     * @param     bitIndex   the bit index.
     * @return    the value of the bit with the specified index.
     * @exception IndexOutOfBoundsException if the specified index is negative.
     */
    public boolean get(int bitIndex) {
        return false;
    }

    /** 
     * Returns a new <tt>BitSet</tt> composed of bits from this <tt>BitSet</tt>
     * from <tt>fromIndex</tt>(inclusive) to <tt>toIndex</tt>(exclusive).
     *
     * @param     fromIndex   index of the first bit to include.
     * @param     toIndex     index after the last bit to include.
     * @return    a new <tt>BitSet</tt> from a range of this <tt>BitSet</tt>.
     * @exception IndexOutOfBoundsException if <tt>fromIndex</tt> is negative,
     *            or <tt>toIndex</tt> is negative, or <tt>fromIndex</tt> is
     *            larger than <tt>toIndex</tt>.
     * @since   1.4
     */
    public BitSet get(int fromIndex, int toIndex) {
        return null;
    }

    /** 
     * Returns the index of the first bit that is set to <code>true</code>
     * that occurs on or after the specified starting index. If no such
     * bit exists then -1 is returned.
     *
     * To iterate over the <code>true</code> bits in a <code>BitSet</code>,
     * use the following loop:
     *
     * for(int i=bs.nextSetBit(0); i>=0; i=bs.nextSetBit(i+1)) {
     *     // operate on index i here
     * }
     * 
     * @param   fromIndex the index to start checking from (inclusive).
     * @return  the index of the next set bit.
     * @throws  IndexOutOfBoundsException if the specified index is negative.
     * @since   1.4
     */
    public int nextSetBit(int fromIndex) {
        return 0;
    }

    /** 
     * Returns the index of the first bit that is set to <code>false</code>
     * that occurs on or after the specified starting index.
     * 
     * @param   fromIndex the index to start checking from (inclusive).
     * @return  the index of the next clear bit.
     * @throws  IndexOutOfBoundsException if the specified index is negative.
     * @since   1.4
     */
    public int nextClearBit(int fromIndex) {
        return 0;
    }

    /** 
     * Returns the "logical size" of this <code>BitSet</code>: the index of
     * the highest set bit in the <code>BitSet</code> plus one. Returns zero
     * if the <code>BitSet</code> contains no set bits.
     *
     * @return  the logical size of this <code>BitSet</code>.
     * @since   1.2
     */
    public int length() {
        return 0;
    }

    /** 
     * Returns true if this <code>BitSet</code> contains no bits that are set
     * to <code>true</code>.
     *
     * @return    boolean indicating whether this <code>BitSet</code> is empty.
     * @since     1.4
     */
    public boolean isEmpty() {
        return false;
    }

    /** 
     * Returns true if the specified <code>BitSet</code> has any bits set to
     * <code>true</code> that are also set to <code>true</code> in this
     * <code>BitSet</code>.
     *
     * @param	set <code>BitSet</code> to intersect with
     * @return  boolean indicating whether this <code>BitSet</code> intersects
     *          the specified <code>BitSet</code>.
     * @since   1.4
     */
    public boolean intersects(BitSet set) {
        return false;
    }

    /** 
     * Returns the number of bits set to <tt>true</tt> in this
     * <code>BitSet</code>.
     *
     * @return  the number of bits set to <tt>true</tt> in this
     *          <code>BitSet</code>.
     * @since   1.4
     */
    public int cardinality() {
        return 0;
    }

    /** 
     * Performs a logical <b>AND</b> of this target bit set with the 
     * argument bit set. This bit set is modified so that each bit in it 
     * has the value <code>true</code> if and only if it both initially 
     * had the value <code>true</code> and the corresponding bit in the 
     * bit set argument also had the value <code>true</code>. 
     *
     * @param   set   a bit set. 
     */
    public void and(BitSet set) { }

    /** 
     * Performs a logical <b>OR</b> of this bit set with the bit set 
     * argument. This bit set is modified so that a bit in it has the 
     * value <code>true</code> if and only if it either already had the 
     * value <code>true</code> or the corresponding bit in the bit set 
     * argument has the value <code>true</code>.
     *
     * @param   set   a bit set.
     */
    public void or(BitSet set) { }

    /** 
     * Performs a logical <b>XOR</b> of this bit set with the bit set 
     * argument. This bit set is modified so that a bit in it has the 
     * value <code>true</code> if and only if one of the following 
     * statements holds: 
     * <ul>
     * <li>The bit initially has the value <code>true</code>, and the 
     *     corresponding bit in the argument has the value <code>false</code>.
     * <li>The bit initially has the value <code>false</code>, and the 
     *     corresponding bit in the argument has the value <code>true</code>. 
     * </ul>
     *
     * @param   set   a bit set.
     */
    public void xor(BitSet set) { }

    /** 
     * Clears all of the bits in this <code>BitSet</code> whose corresponding
     * bit is set in the specified <code>BitSet</code>.
     *
     * @param     set the <code>BitSet</code> with which to mask this
     *            <code>BitSet</code>.
     * @since     JDK1.2
     */
    public void andNot(BitSet set) { }

    /** 
     * Returns a hash code value for this bit set. The has code 
     * depends only on which bits have been set within this 
     * <code>BitSet</code>. The algorithm used to compute it may 
     * be described as follows.<p>
     * Suppose the bits in the <code>BitSet</code> were to be stored 
     * in an array of <code>long</code> integers called, say, 
     * <code>bits</code>, in such a manner that bit <code>k</code> is 
     * set in the <code>BitSet</code> (for nonnegative values of 
     * <code>k</code>) if and only if the expression 
     * <pre>((k&gt;&gt;6) &lt; bits.length) && ((bits[k&gt;&gt;6] & (1L &lt;&lt; (bit & 0x3F))) != 0)</pre>
     * is true. Then the following definition of the <code>hashCode</code> 
     * method would be a correct implementation of the actual algorithm:
     * <pre>
     * public int hashCode() {
     *      long h = 1234;
     *      for (int i = bits.length; --i &gt;= 0; ) {
     *           h ^= bits[i] * (i + 1);
     *      }
     *      return (int)((h &gt;&gt; 32) ^ h);
     * }</pre>
     * Note that the hash code values change if the set of bits is altered.
     * <p>Overrides the <code>hashCode</code> method of <code>Object</code>.
     *
     * @return  a hash code value for this bit set.
     */
    public int hashCode() {
        return 0;
    }

    /** 
     * Returns the number of bits of space actually in use by this 
     * <code>BitSet</code> to represent bit values. 
     * The maximum element in the set is the size - 1st element.
     *
     * @return  the number of bits currently in this bit set.
     */
    public int size() {
        return 0;
    }

    /** 
     * Compares this object against the specified object.
     * The result is <code>true</code> if and only if the argument is 
     * not <code>null</code> and is a <code>Bitset</code> object that has 
     * exactly the same set of bits set to <code>true</code> as this bit 
     * set. That is, for every nonnegative <code>int</code> index <code>k</code>, 
     * <pre>((BitSet)obj).get(k) == this.get(k)</pre>
     * must be true. The current sizes of the two bit sets are not compared. 
     * <p>Overrides the <code>equals</code> method of <code>Object</code>.
     *
     * @param   obj   the object to compare with.
     * @return  <code>true</code> if the objects are the same;
     *          <code>false</code> otherwise.
     * @see     java.util.BitSet#size()
     */
    public boolean equals(Object obj) {
        return false;
    }

    /** 
     * Cloning this <code>BitSet</code> produces a new <code>BitSet</code> 
     * that is equal to it.
     * The clone of the bit set is another bit set that has exactly the 
     * same bits set to <code>true</code> as this bit set and the same 
     * current size. 
     * <p>Overrides the <code>clone</code> method of <code>Object</code>.
     *
     * @return  a clone of this bit set.
     * @see     java.util.BitSet#size()
     */
    public Object clone() {
        return null;
    }

    /** 
     * Returns a string representation of this bit set. For every index 
     * for which this <code>BitSet</code> contains a bit in the set 
     * state, the decimal representation of that index is included in 
     * the result. Such indices are listed in order from lowest to 
     * highest, separated by ",&nbsp;" (a comma and a space) and 
     * surrounded by braces, resulting in the usual mathematical 
     * notation for a set of integers.<p>
     * Overrides the <code>toString</code> method of <code>Object</code>.
     * <p>Example:
     * <pre>
     * BitSet drPepper = new BitSet();</pre>
     * Now <code>drPepper.toString()</code> returns "<code>{}</code>".<p>
     * <pre>
     * drPepper.set(2);</pre>
     * Now <code>drPepper.toString()</code> returns "<code>{2}</code>".<p>
     * <pre>
     * drPepper.set(4);
     * drPepper.set(10);</pre>
     * Now <code>drPepper.toString()</code> returns "<code>{2, 4, 10}</code>".
     *
     * @return  a string representation of this bit set.
     */
    public String toString() {
        return null;
    }

    /** 
     * This override of readObject makes sure unitsInUse is set properly
     * when deserializing a bitset
     *
     */
    private void readObject(ObjectInputStream in)
        throws IOException, ClassNotFoundException
    { }
}
