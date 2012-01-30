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


  


package java.math;

import java.io.*;

import java.util.Random;

/** 
 * Immutable arbitrary-precision integers.  All operations behave as if
 * BigIntegers were represented in two's-complement notation (like Java's
 * primitive integer types).  BigInteger provides analogues to all of Java's
 * primitive integer operators, and all relevant methods from java.lang.Math.
 * Additionally, BigInteger provides operations for modular arithmetic, GCD
 * calculation, primality testing, prime generation, bit manipulation,
 * and a few other miscellaneous operations.
 * <p>
 * Semantics of arithmetic operations exactly mimic those of Java's integer
 * arithmetic operators, as defined in <i>The Java Language Specification</i>.
 * For example, division by zero throws an <tt>ArithmeticException</tt>, and
 * division of a negative by a positive yields a negative (or zero) remainder.
 * All of the details in the Spec concerning overflow are ignored, as
 * BigIntegers are made as large as necessary to accommodate the results of an
 * operation.
 * <p>
 * Semantics of shift operations extend those of Java's shift operators
 * to allow for negative shift distances.  A right-shift with a negative
 * shift distance results in a left shift, and vice-versa.  The unsigned
 * right shift operator (&gt;&gt;&gt;) is omitted, as this operation makes
 * little sense in combination with the "infinite word size" abstraction
 * provided by this class.
 * <p>
 * Semantics of bitwise logical operations exactly mimic those of Java's
 * bitwise integer operators.  The binary operators (<tt>and</tt>,
 * <tt>or</tt>, <tt>xor</tt>) implicitly perform sign extension on the shorter
 * of the two operands prior to performing the operation.
 * <p>
 * Comparison operations perform signed integer comparisons, analogous to
 * those performed by Java's relational and equality operators.
 * <p>
 * Modular arithmetic operations are provided to compute residues, perform
 * exponentiation, and compute multiplicative inverses.  These methods always
 * return a non-negative result, between <tt>0</tt> and <tt>(modulus - 1)</tt>,
 * inclusive.
 * <p>
 * Bit operations operate on a single bit of the two's-complement
 * representation of their operand.  If necessary, the operand is sign-
 * extended so that it contains the designated bit.  None of the single-bit
 * operations can produce a BigInteger with a different sign from the
 * BigInteger being operated on, as they affect only a single bit, and the
 * "infinite word size" abstraction provided by this class ensures that there
 * are infinitely many "virtual sign bits" preceding each BigInteger.
 * <p>
 * For the sake of brevity and clarity, pseudo-code is used throughout the
 * descriptions of BigInteger methods.  The pseudo-code expression
 * <tt>(i + j)</tt> is shorthand for "a BigInteger whose value is
 * that of the BigInteger <tt>i</tt> plus that of the BigInteger <tt>j</tt>."
 * The pseudo-code expression <tt>(i == j)</tt> is shorthand for
 * "<tt>true</tt> if and only if the BigInteger <tt>i</tt> represents the same
 * value as the the BigInteger <tt>j</tt>."  Other pseudo-code expressions are
 * interpreted similarly.
 * <p>
 * All methods and constructors in this class throw
 * <CODE>NullPointerException</CODE> when passed
 * a null object reference for any input parameter.
 *
 * @see     BigDecimal
 * @version 1.36, 04/21/00
 * @author  Josh Bloch
 * @author  Michael McCloskey
 * @since JDK1.1
 */
public class BigInteger extends Number implements Comparable
{
    /** 
     * The BigInteger constant zero.
     *
     * @since   1.2
     */
    public static final BigInteger ZERO = null;

    /** 
     * The BigInteger constant one.
     *
     * @since   1.2
     */
    public static final BigInteger ONE = null;

    /** 
     * The signum of this BigInteger: -1 for negative, 0 for zero, or
     * 1 for positive.  Note that the BigInteger zero <i>must</i> have
     * a signum of 0.  This is necessary to ensures that there is exactly one
     * representation for each BigInteger value.
     *
     * @serial
     */
     int signum;

    /** 
     * This field is required for historical reasons. The magnitude of a
     * BigInteger used to be in a byte representation, and is still serialized
     * that way. The mag field is used in all real computations but the
     * magnitude field is required for storage.
     *
     * @serial
     */
    private byte[] magnitude;

    /** 
     * The bitCount of this BigInteger, as returned by bitCount(), or -1
     * (either value is acceptable).
     *
     * @serial
     * @see #bitCount
     */
    private int bitCount;

    /** 
     * The bitLength of this BigInteger, as returned by bitLength(), or -1
     * (either value is acceptable).
     *
     * @serial
     * @see #bitLength
     */
    private int bitLength;

    /** 
     * The lowest set bit of this BigInteger, as returned by getLowestSetBit(),
     * or -2 (either value is acceptable).
     *
     * @serial
     * @see #getLowestSetBit
     */
    private int lowestSetBit;

    /** 
     * The index of the lowest-order byte in the magnitude of this BigInteger
     * that contains a nonzero byte, or -2 (either value is acceptable).  The
     * least significant byte has int-number 0, the next byte in order of
     * increasing significance has byte-number 1, and so forth.
     *
     * @serial
     */
    private int firstNonzeroByteNum;

    /** 
     * Translates a byte array containing the two's-complement binary
     * representation of a BigInteger into a BigInteger.  The input array is
     * assumed to be in <i>big-endian</i> byte-order: the most significant
     * byte is in the zeroth element.
     *
     * @param  val big-endian two's-complement binary representation of
     *	       BigInteger.
     * @throws NumberFormatException <tt>val</tt> is zero bytes long.
     */
    public BigInteger(byte[] val) { }

    /** 
     * Translates the sign-magnitude representation of a BigInteger into a
     * BigInteger.  The sign is represented as an integer signum value: -1 for
     * negative, 0 for zero, or 1 for positive.  The magnitude is a byte array
     * in <i>big-endian</i> byte-order: the most significant byte is in the
     * zeroth element.  A zero-length magnitude array is permissible, and will
     * result in in a BigInteger value of 0, whether signum is -1, 0 or 1.
     *
     * @param  signum signum of the number (-1 for negative, 0 for zero, 1
     * 	       for positive).
     * @param  magnitude big-endian binary representation of the magnitude of
     * 	       the number.
     * @throws NumberFormatException <tt>signum</tt> is not one of the three
     *	       legal values (-1, 0, and 1), or <tt>signum</tt> is 0 and
     *	       <tt>magnitude</tt> contains one or more non-zero bytes.
     */
    public BigInteger(int signum, byte[] magnitude) { }

    /** 
     * Translates the String representation of a BigInteger in the specified
     * radix into a BigInteger.  The String representation consists of an
     * optional minus sign followed by a sequence of one or more digits in the
     * specified radix.  The character-to-digit mapping is provided by
     * <tt>Character.digit</tt>.  The String may not contain any extraneous
     * characters (whitespace, for example).
     *
     * @param val String representation of BigInteger.
     * @param radix radix to be used in interpreting <tt>val</tt>.
     * @throws NumberFormatException <tt>val</tt> is not a valid representation
     *	       of a BigInteger in the specified radix, or <tt>radix</tt> is
     *	       outside the range from {@link Character#MIN_RADIX} to
     *	       {@link Character#MAX_RADIX}, inclusive.
     * @see    Character#digit
     */
    public BigInteger(String val, int radix) { }

    /** 
     * Translates the decimal String representation of a BigInteger into a
     * BigInteger.  The String representation consists of an optional minus
     * sign followed by a sequence of one or more decimal digits.  The
     * character-to-digit mapping is provided by <tt>Character.digit</tt>.
     * The String may not contain any extraneous characters (whitespace, for
     * example).
     *
     * @param val decimal String representation of BigInteger.
     * @throws NumberFormatException <tt>val</tt> is not a valid representation
     *	       of a BigInteger.
     * @see    Character#digit
     */
    public BigInteger(String val) { }

    /** 
     * Constructs a randomly generated BigInteger, uniformly distributed over
     * the range <tt>0</tt> to <tt>(2<sup>numBits</sup> - 1)</tt>, inclusive.
     * The uniformity of the distribution assumes that a fair source of random
     * bits is provided in <tt>rnd</tt>.  Note that this constructor always
     * constructs a non-negative BigInteger.
     *
     * @param  numBits maximum bitLength of the new BigInteger.
     * @param  rnd source of randomness to be used in computing the new
     *	       BigInteger.
     * @throws IllegalArgumentException <tt>numBits</tt> is negative.
     * @see #bitLength
     */
    public BigInteger(int numBits, Random rnd) { }

    /** 
     * Constructs a randomly generated positive BigInteger that is probably
     * prime, with the specified bitLength.<p>
     *
     * It is recommended that the {@link #probablePrime probablePrime}
     * method be used in preference to this constructor unless there
     * is a compelling need to specify a certainty.
     *
     * @param  bitLength bitLength of the returned BigInteger.
     * @param  certainty a measure of the uncertainty that the caller is
     *         willing to tolerate.  The probability that the new BigInteger
     *	       represents a prime number will exceed
     *	       <tt>(1 - 1/2<sup>certainty</sup></tt>).  The execution time of
     *	       this constructor is proportional to the value of this parameter.
     * @param  rnd source of random bits used to select candidates to be
     *	       tested for primality.
     * @throws ArithmeticException <tt>bitLength &lt; 2</tt>.
     * @see    #bitLength
     */
    public BigInteger(int bitLength, int certainty, Random rnd) { }

    /** 
     * Returns a BigInteger whose value is equal to that of the
     * specified <code>long</code>.  This "static factory method" is
     * provided in preference to a (<code>long</code>) constructor
     * because it allows for reuse of frequently used BigIntegers.
     *
     * @param  val value of the BigInteger to return.
     * @return a BigInteger with the specified value.
     */
    public static BigInteger valueOf(long val) {
        return null;
    }

    /** 
     * Returns a BigInteger whose value is <tt>(this + val)</tt>.
     *
     * @param  val value to be added to this BigInteger.
     * @return <tt>this + val</tt>
     */
    public BigInteger add(BigInteger val) {
        return null;
    }

    /** 
     * Returns a BigInteger whose value is <tt>(this - val)</tt>.
     *
     * @param  val value to be subtracted from this BigInteger.
     * @return <tt>this - val</tt>
     */
    public BigInteger subtract(BigInteger val) {
        return null;
    }

    /** 
     * Returns a BigInteger whose value is <tt>(this * val)</tt>.
     *
     * @param  val value to be multiplied by this BigInteger.
     * @return <tt>this * val</tt>
     */
    public BigInteger multiply(BigInteger val) {
        return null;
    }

    /** 
     * Returns a BigInteger whose value is <tt>(this / val)</tt>.
     *
     * @param  val value by which this BigInteger is to be divided.
     * @return <tt>this / val</tt>
     * @throws ArithmeticException <tt>val==0</tt>
     */
    public BigInteger divide(BigInteger val) {
        return null;
    }

    /** 
     * Returns an array of two BigIntegers containing <tt>(this / val)</tt>
     * followed by <tt>(this % val)</tt>.
     *
     * @param  val value by which this BigInteger is to be divided, and the
     *	       remainder computed.
     * @return an array of two BigIntegers: the quotient <tt>(this / val)</tt>
     *	       is the initial element, and the remainder <tt>(this % val)</tt>
     *	       is the final element.
     * @throws ArithmeticException <tt>val==0</tt>
     */
    public BigInteger[] divideAndRemainder(BigInteger val) {
        return null;
    }

    /** 
     * Returns a BigInteger whose value is <tt>(this % val)</tt>.
     *
     * @param  val value by which this BigInteger is to be divided, and the
     *	       remainder computed.
     * @return <tt>this % val</tt>
     * @throws ArithmeticException <tt>val==0</tt>
     */
    public BigInteger remainder(BigInteger val) {
        return null;
    }

    /** 
     * Returns a BigInteger whose value is <tt>(this<sup>exponent</sup>)</tt>.
     * Note that <tt>exponent</tt> is an integer rather than a BigInteger.
     *
     * @param  exponent exponent to which this BigInteger is to be raised.
     * @return <tt>this<sup>exponent</sup></tt>
     * @throws ArithmeticException <tt>exponent</tt> is negative.  (This would
     *	       cause the operation to yield a non-integer value.)
     */
    public BigInteger pow(int exponent) {
        return null;
    }

    /** 
     * Returns a BigInteger whose value is the greatest common divisor of
     * <tt>abs(this)</tt> and <tt>abs(val)</tt>.  Returns 0 if
     * <tt>this==0 &amp;&amp; val==0</tt>.
     *
     * @param  val value with with the GCD is to be computed.
     * @return <tt>GCD(abs(this), abs(val))</tt>
     */
    public BigInteger gcd(BigInteger val) {
        return null;
    }

    /** 
     * Returns a BigInteger whose value is the absolute value of this
     * BigInteger. 
     *
     * @return <tt>abs(this)</tt>
     */
    public BigInteger abs() {
        return null;
    }

    /** 
     * Returns a BigInteger whose value is <tt>(-this)</tt>.
     *
     * @return <tt>-this</tt>
     */
    public BigInteger negate() {
        return null;
    }

    /** 
     * Returns the signum function of this BigInteger.
     *
     * @return -1, 0 or 1 as the value of this BigInteger is negative, zero or
     *	       positive.
     */
    public int signum() {
        return 0;
    }

    /** 
     * Returns a BigInteger whose value is <tt>(this mod m</tt>).  This method
     * differs from <tt>remainder</tt> in that it always returns a
     * <i>non-negative</i> BigInteger.
     *
     * @param  m the modulus.
     * @return <tt>this mod m</tt>
     * @throws ArithmeticException <tt>m &lt;= 0</tt>
     * @see    #remainder
     */
    public BigInteger mod(BigInteger m) {
        return null;
    }

    /** 
     * Returns a BigInteger whose value is
     * <tt>(this<sup>exponent</sup> mod m)</tt>.  (Unlike <tt>pow</tt>, this
     * method permits negative exponents.)
     *
     * @param  exponent the exponent.
     * @param  m the modulus.
     * @return <tt>this<sup>exponent</sup> mod m</tt>
     * @throws ArithmeticException <tt>m &lt;= 0</tt>
     * @see    #modInverse
     */
    public BigInteger modPow(BigInteger exponent, BigInteger m) {
        return null;
    }

    /** 
     * Returns a BigInteger whose value is <tt>(this<sup>-1</sup> mod m)</tt>.
     *
     * @param  m the modulus.
     * @return <tt>this<sup>-1</sup> mod m</tt>.
     * @throws ArithmeticException <tt> m &lt;= 0</tt>, or this BigInteger
     *	       has no multiplicative inverse mod m (that is, this BigInteger
     *	       is not <i>relatively prime</i> to m).
     */
    public BigInteger modInverse(BigInteger m) {
        return null;
    }

    /** 
     * Returns a BigInteger whose value is <tt>(this &lt;&lt; n)</tt>.
     * The shift distance, <tt>n</tt>, may be negative, in which case
     * this method performs a right shift.
     * (Computes <tt>floor(this * 2<sup>n</sup>)</tt>.)
     *
     * @param  n shift distance, in bits.
     * @return <tt>this &lt;&lt; n</tt>
     * @see #shiftRight
     */
    public BigInteger shiftLeft(int n) {
        return null;
    }

    /** 
     * Returns a BigInteger whose value is <tt>(this &gt;&gt; n)</tt>.  Sign
     * extension is performed.  The shift distance, <tt>n</tt>, may be
     * negative, in which case this method performs a left shift.
     * (Computes <tt>floor(this / 2<sup>n</sup>)</tt>.) 
     *
     * @param  n shift distance, in bits.
     * @return <tt>this &gt;&gt; n</tt>
     * @see #shiftLeft
     */
    public BigInteger shiftRight(int n) {
        return null;
    }

    /** 
     * Returns a BigInteger whose value is <tt>(this &amp; val)</tt>.  (This
     * method returns a negative BigInteger if and only if this and val are
     * both negative.)
     *
     * @param val value to be AND'ed with this BigInteger.
     * @return <tt>this &amp; val</tt>
     */
    public BigInteger and(BigInteger val) {
        return null;
    }

    /** 
     * Returns a BigInteger whose value is <tt>(this | val)</tt>.  (This method
     * returns a negative BigInteger if and only if either this or val is
     * negative.) 
     *
     * @param val value to be OR'ed with this BigInteger.
     * @return <tt>this | val</tt>
     */
    public BigInteger or(BigInteger val) {
        return null;
    }

    /** 
     * Returns a BigInteger whose value is <tt>(this ^ val)</tt>.  (This method
     * returns a negative BigInteger if and only if exactly one of this and
     * val are negative.)
     *
     * @param val value to be XOR'ed with this BigInteger.
     * @return <tt>this ^ val</tt>
     */
    public BigInteger xor(BigInteger val) {
        return null;
    }

    /** 
     * Returns a BigInteger whose value is <tt>(~this)</tt>.  (This method
     * returns a negative value if and only if this BigInteger is
     * non-negative.)
     *
     * @return <tt>~this</tt>
     */
    public BigInteger not() {
        return null;
    }

    /** 
     * Returns a BigInteger whose value is <tt>(this &amp; ~val)</tt>.  This
     * method, which is equivalent to <tt>and(val.not())</tt>, is provided as
     * a convenience for masking operations.  (This method returns a negative
     * BigInteger if and only if <tt>this</tt> is negative and <tt>val</tt> is
     * positive.)
     *
     * @param val value to be complemented and AND'ed with this BigInteger.
     * @return <tt>this &amp; ~val</tt>
     */
    public BigInteger andNot(BigInteger val) {
        return null;
    }

    /** 
     * Returns <tt>true</tt> if and only if the designated bit is set.
     * (Computes <tt>((this &amp; (1&lt;&lt;n)) != 0)</tt>.)
     *
     * @param  n index of bit to test.
     * @return <tt>true</tt> if and only if the designated bit is set.
     * @throws ArithmeticException <tt>n</tt> is negative.
     */
    public boolean testBit(int n) {
        return false;
    }

    /** 
     * Returns a BigInteger whose value is equivalent to this BigInteger
     * with the designated bit set.  (Computes <tt>(this | (1&lt;&lt;n))</tt>.)
     *
     * @param  n index of bit to set.
     * @return <tt>this | (1&lt;&lt;n)</tt>
     * @throws ArithmeticException <tt>n</tt> is negative.
     */
    public BigInteger setBit(int n) {
        return null;
    }

    /** 
     * Returns a BigInteger whose value is equivalent to this BigInteger
     * with the designated bit cleared.
     * (Computes <tt>(this &amp; ~(1&lt;&lt;n))</tt>.)
     *
     * @param  n index of bit to clear.
     * @return <tt>this & ~(1&lt;&lt;n)</tt>
     * @throws ArithmeticException <tt>n</tt> is negative.
     */
    public BigInteger clearBit(int n) {
        return null;
    }

    /** 
     * Returns a BigInteger whose value is equivalent to this BigInteger
     * with the designated bit flipped.
     * (Computes <tt>(this ^ (1&lt;&lt;n))</tt>.)
     *
     * @param  n index of bit to flip.
     * @return <tt>this ^ (1&lt;&lt;n)</tt>
     * @throws ArithmeticException <tt>n</tt> is negative.
     */
    public BigInteger flipBit(int n) {
        return null;
    }

    /** 
     * Returns the index of the rightmost (lowest-order) one bit in this
     * BigInteger (the number of zero bits to the right of the rightmost
     * one bit).  Returns -1 if this BigInteger contains no one bits.
     * (Computes <tt>(this==0? -1 : log<sub>2</sub>(this &amp; -this))</tt>.)
     *
     * @return index of the rightmost one bit in this BigInteger.
     */
    public int getLowestSetBit() {
        return 0;
    }

    /** 
     * Returns the number of bits in the minimal two's-complement
     * representation of this BigInteger, <i>excluding</i> a sign bit.
     * For positive BigIntegers, this is equivalent to the number of bits in
     * the ordinary binary representation.  (Computes
     * <tt>(ceil(log<sub>2</sub>(this &lt; 0 ? -this : this+1)))</tt>.)
     *
     * @return number of bits in the minimal two's-complement
     *         representation of this BigInteger, <i>excluding</i> a sign bit.
     */
    public int bitLength() {
        return 0;
    }

    /** 
     * Returns the number of bits in the two's complement representation
     * of this BigInteger that differ from its sign bit.  This method is
     * useful when implementing bit-vector style sets atop BigIntegers.
     *
     * @return number of bits in the two's complement representation
     *         of this BigInteger that differ from its sign bit.
     */
    public int bitCount() {
        return 0;
    }

    /** 
     * Returns <tt>true</tt> if this BigInteger is probably prime,
     * <tt>false</tt> if it's definitely composite.  If
     * <tt>certainty</tt> is <tt> &lt;= 0</tt>, <tt>true</tt> is
     * returned.
     *
     * @param  certainty a measure of the uncertainty that the caller is
     *	       willing to tolerate: if the call returns <tt>true</tt>
     *	       the probability that this BigInteger is prime exceeds
     *	       <tt>(1 - 1/2<sup>certainty</sup>)</tt>.  The execution time of
     * 	       this method is proportional to the value of this parameter.
     * @return <tt>true</tt> if this BigInteger is probably prime,
     * 	       <tt>false</tt> if it's definitely composite.
     */
    public boolean isProbablePrime(int certainty) {
        return false;
    }

    /** 
     * Compares this BigInteger with the specified BigInteger.  This method is
     * provided in preference to individual methods for each of the six
     * boolean comparison operators (&lt;, ==, &gt;, &gt;=, !=, &lt;=).  The
     * suggested idiom for performing these comparisons is:
     * <tt>(x.compareTo(y)</tt> &lt;<i>op</i>&gt; <tt>0)</tt>,
     * where &lt;<i>op</i>&gt; is one of the six comparison operators.
     *
     * @param  val BigInteger to which this BigInteger is to be compared.
     * @return -1, 0 or 1 as this BigInteger is numerically less than, equal
     *         to, or greater than <tt>val</tt>.
     */
    public int compareTo(BigInteger val) {
        return 0;
    }

    /** 
     * Compares this BigInteger with the specified Object.  If the Object is a
     * BigInteger, this method behaves like <tt>compareTo(BigInteger)</tt>.
     * Otherwise, it throws a <tt>ClassCastException</tt> (as BigIntegers are
     * comparable only to other BigIntegers).
     *
     * @param   o Object to which this BigInteger is to be compared.
     * @return  a negative number, zero, or a positive number as this
     *		BigInteger is numerically less than, equal to, or greater
     *		than <tt>o</tt>, which must be a BigInteger.
     * @throws  ClassCastException <tt>o</tt> is not a BigInteger.
     * @see     #compareTo(java.math.BigInteger)
     * @see     Comparable
     * @since   1.2
     */
    public int compareTo(Object o) {
        return 0;
    }

    /** 
     * Compares this BigInteger with the specified Object for equality.
     *
     * @param  x Object to which this BigInteger is to be compared.
     * @return <tt>true</tt> if and only if the specified Object is a
     *	       BigInteger whose value is numerically equal to this BigInteger.
     */
    public boolean equals(Object x) {
        return false;
    }

    /** 
     * Returns the minimum of this BigInteger and <tt>val</tt>.
     *
     * @param  val value with with the minimum is to be computed.
     * @return the BigInteger whose value is the lesser of this BigInteger and 
     *	       <tt>val</tt>.  If they are equal, either may be returned.
     */
    public BigInteger min(BigInteger val) {
        return null;
    }

    /** 
     * Returns the maximum of this BigInteger and <tt>val</tt>.
     *
     * @param  val value with with the maximum is to be computed.
     * @return the BigInteger whose value is the greater of this and
     *         <tt>val</tt>.  If they are equal, either may be returned.
     */
    public BigInteger max(BigInteger val) {
        return null;
    }

    /** 
     * Returns the hash code for this BigInteger.
     *
     * @return hash code for this BigInteger.
     */
    public int hashCode() {
        return 0;
    }

    /** 
     * Returns the String representation of this BigInteger in the
     * given radix.  If the radix is outside the range from {@link
     * Character#MIN_RADIX} to {@link Character#MAX_RADIX} inclusive,
     * it will default to 10 (as is the case for
     * <tt>Integer.toString</tt>).  The digit-to-character mapping
     * provided by <tt>Character.forDigit</tt> is used, and a minus
     * sign is prepended if appropriate.  (This representation is
     * compatible with the {@link #BigInteger(String, int) (String,
     * <code>int</code>)} constructor.)
     *
     * @param  radix  radix of the String representation.
     * @return String representation of this BigInteger in the given radix.
     * @see    Integer#toString
     * @see    Character#forDigit
     * @see    #BigInteger(java.lang.String, int)
     */
    public String toString(int radix) {
        return null;
    }

    /** 
     * Returns the decimal String representation of this BigInteger.
     * The digit-to-character mapping provided by
     * <tt>Character.forDigit</tt> is used, and a minus sign is
     * prepended if appropriate.  (This representation is compatible
     * with the {@link #BigInteger(String) (String)} constructor, and
     * allows for String concatenation with Java's + operator.)
     *
     * @return decimal String representation of this BigInteger.
     * @see    Character#forDigit
     * @see    #BigInteger(java.lang.String)
     */
    public String toString() {
        return null;
    }

    /** 
     * Returns a byte array containing the two's-complement
     * representation of this BigInteger.  The byte array will be in
     * <i>big-endian</i> byte-order: the most significant byte is in
     * the zeroth element.  The array will contain the minimum number
     * of bytes required to represent this BigInteger, including at
     * least one sign bit, which is <tt>(ceil((this.bitLength() +
     * 1)/8))</tt>.  (This representation is compatible with the
     * {@link #BigInteger(byte[]) (byte[])} constructor.)
     *
     * @return a byte array containing the two's-complement representation of
     *	       this BigInteger.
     * @see    #BigInteger(byte[])
     */
    public byte[] toByteArray() {
        return null;
    }

    /** 
     * Converts this BigInteger to an <code>int</code>.  This
     * conversion is analogous to a <a
     * href="http://java.sun.com/docs/books/jls/second_edition/html/conversions.doc.html#25363"><i>narrowing
     * primitive conversion</i></a> from <code>long</code> to
     * <code>int</code> as defined in the <a
     * href="http://java.sun.com/docs/books/jls/html/">Java Language
     * Specification</a>: if this BigInteger is too big to fit in an
     * <code>int</code>, only the low-order 32 bits are returned.
     * Note that this conversion can lose information about the
     * overall magnitude of the BigInteger value as well as return a
     * result with the opposite sign.
     *
     * @return this BigInteger converted to an <code>int</code>.
     */
    public int intValue() {
        return 0;
    }

    /** 
     * Converts this BigInteger to a <code>long</code>.  This
     * conversion is analogous to a <a
     * href="http://java.sun.com/docs/books/jls/second_edition/html/conversions.doc.html#25363"><i>narrowing
     * primitive conversion</i></a> from <code>long</code> to
     * <code>int</code> as defined in the <a
     * href="http://java.sun.com/docs/books/jls/html/">Java Language
     * Specification</a>: if this BigInteger is too big to fit in a
     * <code>long</code>, only the low-order 64 bits are returned.
     * Note that this conversion can lose information about the
     * overall magnitude of the BigInteger value as well as return a
     * result with the opposite sign.
     *
     * @return this BigInteger converted to a <code>long</code>.
     */
    public long longValue() {
        return -1;
    }

    /** 
     * Converts this BigInteger to a <code>float</code>.  This
     * conversion is similar to the <a
     * href="http://java.sun.com/docs/books/jls/second_edition/html/conversions.doc.html#25363"><i>narrowing
     * primitive conversion</i></a> from <code>double</code> to
     * <code>float</code> defined in the <a
     * href="http://java.sun.com/docs/books/jls/html/">Java Language
     * Specification</a>: if this BigInteger has too great a magnitude
     * to represent as a <code>float</code>, it will be converted to
     * {@link Float#NEGATIVE_INFINITY} or {@link
     * Float#POSITIVE_INFINITY} as appropriate.  Note that even when
     * the return value is finite, this conversion can lose
     * information about the precision of the BigInteger value.
     *
     * @return this BigInteger converted to a <code>float</code>.
     */
    public float floatValue() {
        return 0.0f;
    }

    /** 
     * Converts this BigInteger to a <code>double</code>.  This
     * conversion is similar to the <a
     * href="http://java.sun.com/docs/books/jls/second_edition/html/conversions.doc.html#25363"><i>narrowing
     * primitive conversion</i></a> from <code>double</code> to
     * <code>float</code> defined in the <a
     * href="http://java.sun.com/docs/books/jls/html/">Java Language
     * Specification</a>: if this BigInteger has too great a magnitude
     * to represent as a <code>double</code>, it will be converted to
     * {@link Double#NEGATIVE_INFINITY} or {@link
     * Double#POSITIVE_INFINITY} as appropriate.  Note that even when
     * the return value is finite, this conversion can lose
     * information about the precision of the BigInteger value.
     *
     * @return this BigInteger converted to a <code>double</code>.
     */
    public double doubleValue() {
        return 0.0d;
    }

    /** 
     * Reconstitute the <tt>BigInteger</tt> instance from a stream (that is,
     * deserialize it). The magnitude is read in as an array of bytes
     * for historical reasons, but it is converted to an array of ints
     * and the byte array is discarded.
     */
    private void readObject(ObjectInputStream s)
        throws IOException, ClassNotFoundException
    { }

    /** 
     * Ensure that magnitude (the obsolete byte array representation)
     * is set prior to serializaing this BigInteger.  This provides a
     * serialized form that is compatible with older (pre-1.3) versions.
     */
    private synchronized Object writeReplace() {
        return null;
    }

    /** use serialVersionUID from JDK 1.1. for interoperability */
    private static final long serialVersionUID = -8287574255936472291L;

}
