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


  


package java.lang;

/** 
 * The <code>Byte</code> class wraps a value of primitive type
 * <code>byte</code> in an object.  An object of type
 * <code>Byte</code> contains a single field whose type is
 * <code>byte</code>.
 *
 * <p>
 *
 * In addition, this class provides several methods for converting a
 * <code>byte</code> to a <code>String</code> and a <code>String</code>
 * to a <code>byte</code>, as well as other constants and methods
 * useful when dealing with a <code>byte</code>.
 *
 * @author  Nakul Saraiya
 * @version 1.20, 02/02/00
 * @see     java.lang.Number
 * @since   JDK1.1
 */
public final class Byte extends java.lang.Number implements java.lang.Comparable
{
    /** 
     * A constant holding the minimum value a <code>byte</code> can
     * have, -2<sup>7</sup>.
     */
    public static final byte MIN_VALUE = -128;

    /** 
     * A constant holding the maximum value a <code>byte</code> can
     * have, 2<sup>7</sup>-1.
     */
    public static final byte MAX_VALUE = 127;

    /** 
     * The <code>Class</code> instance representing the primitive type
     * <code>byte</code>.
     */
    public static final java.lang.Class TYPE = null;

    /** 
     * The value of the <code>Byte</code>.
     *
     * @serial
     */
    private byte value;

    /** 
     * Constructs a newly allocated <code>Byte</code> object that
     * represents the specified <code>byte</code> value.
     *
     * @param value	the value to be represented by the 
     *			<code>Byte</code>.
     */
    public Byte(byte value) { }

    /** 
     * Constructs a newly allocated <code>Byte</code> object that
     * represents the <code>byte</code> value indicated by the
     * <code>String</code> parameter. The string is converted to a
     * <code>byte</code> value in exactly the manner used by the
     * <code>parseByte</code> method for radix 10.
     *
     * @param s		the <code>String</code> to be converted to a 
     *			<code>Byte</code>
     * @exception	NumberFormatException If the <code>String</code> 
     *			does not contain a parsable <code>byte</code>.
     * @see        java.lang.Byte#parseByte(java.lang.String, int)
     */
    public Byte(java.lang.String s) throws java.lang.NumberFormatException { }

    /** 
     * Returns a new <code>String</code> object representing the
     * specified <code>byte</code>. The radix is assumed to be 10.
     *
     * @param b	the <code>byte</code> to be converted
     * @return the string representation of the specified <code>byte</code>
     * @see java.lang.Integer#toString(int)
     */
    public static java.lang.String toString(byte b) {
        return null;
    }

    /** 
     * Parses the string argument as a signed decimal
     * <code>byte</code>. The characters in the string must all be
     * decimal digits, except that the first character may be an ASCII
     * minus sign <code>'-'</code> (<code>'&#92;u002D'</code>) to
     * indicate a negative value. The resulting <code>byte</code> value is
     * returned, exactly as if the argument and the radix 10 were
     * given as arguments to the {@link #parseByte(java.lang.String,
     * int)} method.
     *
     * @param s		a <code>String</code> containing the 
     *                  <code>byte</code> representation to be parsed
     * @return 		the <code>byte</code> value represented by the 
     *                  argument in decimal
     * @exception	NumberFormatException if the the string does not
     *			contain a parsable <code>byte</code>.
     */
    public static byte parseByte(java.lang.String s)
        throws java.lang.NumberFormatException
    {
        return ' ';
    }

    /** 
     * Parses the string argument as a signed <code>byte</code> in the
     * radix specified by the second argument. The characters in the
     * string must all be digits, of the specified radix (as
     * determined by whether {@link java.lang.Character#digit(char,
     * int)} returns a nonnegative value) except that the first
     * character may be an ASCII minus sign <code>'-'</code>
     * (<code>'&#92;u002D'</code>) to indicate a negative value.  The
     * resulting <code>byte</code> value is returned.
     * <p>
     * An exception of type <code>NumberFormatException</code> is
     * thrown if any of the following situations occurs:
     * <ul>
     * <li> The first argument is <code>null</code> or is a string of
     * length zero.
     *
     * <li> The radix is either smaller than {@link
     * java.lang.Character#MIN_RADIX} or larger than {@link
     * java.lang.Character#MAX_RADIX}.
     *
     * <li> Any character of the string is not a digit of the specified
     * radix, except that the first character may be a minus sign
     * <code>'-'</code> (<code>'&#92;u002D'</code>) provided that the
     * string is longer than length 1.
     *
     * <li> The value represented by the string is not a value of type
     * <code>byte</code>.
     * </ul>
     *
     * @param s		the <code>String</code> containing the 
     *			<code>byte</code>
     *                  representation to be parsed
     * @param radix	the radix to be used while parsing <code>s</code>
     * @return 		the <code>byte</code> value represented by the string 
     *                   argument in the specified radix
     * @exception	NumberFormatException If the string does
     *                  not contain a parsable <code>byte</code>.
     */
    public static byte parseByte(java.lang.String s, int radix)
        throws java.lang.NumberFormatException
    {
        return ' ';
    }

    /** 
     * Returns a <code>Byte</code> object holding the value
     * extracted from the specified <code>String</code> when parsed
     * with the radix given by the second argument. The first argument
     * is interpreted as representing a signed <code>byte</code> in
     * the radix specified by the second argument, exactly as if the
     * argument were given to the {@link #parseByte(java.lang.String,
     * int)} method. The result is a <code>Byte</code> object that
     * represents the <code>byte</code> value specified by the string.
     * <p> In other words, this method returns a <code>Byte</code> object
     * equal to the value of:
     *
     * <blockquote><code>
     * new Byte(Byte.parseByte(s, radix))
     * </code></blockquote>
     *
     * @param s		the string to be parsed
     * @param radix 	the radix to be used in interpreting <code>s</code>
     * @return 		a <code>Byte</code> object holding the value 
     * 			represented by the string argument in the 
     *			specified radix.
     * @exception	NumberFormatException If the <code>String</code> does 
     *			not contain a parsable <code>byte</code>.
     */
    public static java.lang.Byte valueOf(java.lang.String s, int radix)
        throws java.lang.NumberFormatException
    {
        return null;
    }

    /** 
     * Returns a <code>Byte</code> object holding the value
     * given by the specified <code>String</code>. The argument is
     * interpreted as representing a signed decimal <code>byte</code>,
     * exactly as if the argument were given to the {@link
     * #parseByte(java.lang.String)} method. The result is a
     * <code>Byte</code> object that represents the <code>byte</code>
     * value specified by the string.  <p> In other words, this method
     * returns a <code>Byte</code> object equal to the value of:
     *
     * <blockquote><code>
     * new Byte(Byte.parseByte(s))
     * </code></blockquote>
     *
     * @param s		the string to be parsed
     * @return 		a <code>Byte</code> object holding the value
     * 			represented by the string argument
     * @exception	NumberFormatException If the <code>String</code> does
     *			not contain a parsable <code>byte</code>.
     */
    public static java.lang.Byte valueOf(java.lang.String s)
        throws java.lang.NumberFormatException
    {
        return null;
    }

    /** 
     * Decodes a <code>String</code> into a <code>Byte</code>.
     * Accepts decimal, hexadecimal, and octal numbers given by
     * the following grammar:
     *
     * <blockquote>
     * <dl>
     * <dt><i>DecodableString:</i>
     * <dd><i>Sign<sub>opt</sub> DecimalNumeral</i>
     * <dd><i>Sign<sub>opt</sub></i> <code>0x</code> <i>HexDigits</i>
     * <dd><i>Sign<sub>opt</sub></i> <code>0X</code> <i>HexDigits</i>
     * <dd><i>Sign<sub>opt</sub></i> <code>#</code> <i>HexDigits</i>
     * <dd><i>Sign<sub>opt</sub></i> <code>0</code> <i>OctalDigits</i>
     * <p>
     * <dt><i>Sign:</i>
     * <dd><code>-</code>
     * </dl>
     * </blockquote>
     *
     * <i>DecimalNumeral</i>, <i>HexDigits</i>, and <i>OctalDigits</i>
     * are defined in <a href="http://java.sun.com/docs/books/jls/second_edition/html/lexical.doc.html#48282">&sect;3.10.1</a> 
     * of the <a href="http://java.sun.com/docs/books/jls/html/">Java 
     * Language Specification</a>.
     * <p>
     * The sequence of characters following an (optional) negative
     * sign and/or radix specifier (&quot;<code>0x</code>&quot;,
     * &quot;<code>0X</code>&quot;, &quot;<code>#</code>&quot;, or
     * leading zero) is parsed as by the <code>Byte.parseByte</code>
     * method with the indicated radix (10, 16, or 8).  This sequence
     * of characters must represent a positive value or a {@link
     * NumberFormatException} will be thrown.  The result is negated
     * if first character of the specified <code>String</code> is the
     * minus sign.  No whitespace characters are permitted in the
     * <code>String</code>.
     *
     * @param     nm the <code>String</code> to decode.
     * @return 	 a <code>Byte</code> object holding the <code>byte</code>
     * 		value represented by <code>nm</code>
     * @exception NumberFormatException  if the <code>String</code> does not
     *            contain a parsable <code>byte</code>.
     * @see java.lang.Byte#parseByte(java.lang.String, int)
     */
    public static java.lang.Byte decode(java.lang.String nm)
        throws java.lang.NumberFormatException
    {
        return null;
    }

    /** 
     * Returns the value of this <code>Byte</code> as a
     * <code>byte</code>.
     */
    public byte byteValue() {
        return ' ';
    }

    /** 
     * Returns the value of this <code>Byte</code> as a
     * <code>short</code>.
     */
    public short shortValue() {
        return -1;
    }

    /** 
     * Returns the value of this <code>Byte</code> as an
     * <code>int</code>.
     */
    public int intValue() {
        return 0;
    }

    /** 
     * Returns the value of this <code>Byte</code> as a
     * <code>long</code>.
     */
    public long longValue() {
        return -1;
    }

    /** 
     * Returns the value of this <code>Byte</code> as a
     * <code>float</code>.
     */
    public float floatValue() {
        return 0.0f;
    }

    /** 
     * Returns the value of this <code>Byte</code> as a
     * <code>double</code>.
     */
    public double doubleValue() {
        return 0.0d;
    }

    /** 
     * Returns a <code>String</code> object representing this
     * <code>Byte</code>'s value.  The value is converted to signed
     * decimal representation and returned as a string, exactly as if
     * the <code>byte</code> value were given as an argument to the
     * {@link java.lang.Byte#toString(byte)} method.
     *
     * @return  a string representation of the value of this object in
     *          base&nbsp;10.
     */
    public java.lang.String toString() {
        return null;
    }

    /** 
     * Returns a hash code for this <code>Byte</code>.
     */
    public int hashCode() {
        return 0;
    }

    /** 
     * Compares this object to the specified object.  The result is
     * <code>true</code> if and only if the argument is not
     * <code>null</code> and is a <code>Byte</code> object that
     * contains the same <code>byte</code> value as this object.
     *
     * @param obj	the object to compare with
     * @return 		<code>true</code> if the objects are the same;
     * 			<code>false</code> otherwise.
     */
    public boolean equals(java.lang.Object obj) {
        return false;
    }

    /** 
     * Compares two <code>Byte</code> objects numerically.
     *
     * @param   anotherByte   the <code>Byte</code> to be compared.
     * @return	the value <code>0</code> if this <code>Byte</code> is
     * 		equal to the argument <code>Byte</code>; a value less than
     * 		<code>0</code> if this <code>Byte</code> is numerically less
     * 		than the argument <code>Byte</code>; and a value greater than
     * 		 <code>0</code> if this <code>Byte</code> is numerically
     * 		 greater than the argument <code>Byte</code> (signed
     * 		 comparison).
     * @since   1.2
     */
    public int compareTo(java.lang.Byte anotherByte) {
        return 0;
    }

    /** 
     * Compares this <code>Byte</code> object to another object.  If the
     * object is a <code>Byte</code>, this function behaves like
     * <code>compareTo(Byte)</code>.  Otherwise, it throws a
     * <code>ClassCastException</code> (as <code>Byte</code> objects
     * are only comparable to other <code>Byte</code> objects).
     *
     * @param   o the <code>Object</code> to be compared.
     * @return  the value <code>0</code> if the argument is a <code>Byte</code>
     *		numerically equal to this <code>Byte</code>; a value less than
     *		<code>0</code> if the argument is a <code>Byte</code> 
     *		numerically greater than this <code>Byte</code>; and a 
     *		value greater than <code>0</code> if the argument is a
     *		 <code>Byte</code> numerically less than this 
     *		<code>Byte</code>.
     * @exception <code>ClassCastException</code> if the argument is not a
     *		  <code><code>Byte</code></code>. 
     * @see     java.lang.Comparable
     * @since   1.2
     */
    public int compareTo(java.lang.Object o) {
        return 0;
    }

    /** use serialVersionUID from JDK 1.1. for interoperability */
    private static final long serialVersionUID = -7183698231559129828L;

}
