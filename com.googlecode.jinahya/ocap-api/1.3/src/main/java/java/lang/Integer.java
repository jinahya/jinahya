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
 * The <code>Integer</code> class wraps a value of the primitive type
 * <code>int</code> in an object. An object of type
 * <code>Integer</code> contains a single field whose type is
 * <code>int</code>.
 *
 *  <p>
 * 
 * In addition, this class provides several methods for converting an
 * <code>int</code> to a <code>String</code> and a <code>String</code>
 * to an <code>int</code>, as well as other constants and methods
 * useful when dealing with an <code>int</code>.
 *
 * @author  Lee Boynton
 * @author  Arthur van Hoff
 * @version 1.67 11/10/00
 * @since   JDK1.0
 */
public final class Integer extends java.lang.Number
    implements java.lang.Comparable
{
    /** 
     * A constant holding the minimum value an <code>int</code> can
     * have, -2<sup>31</sup>.
     */
    public static final int MIN_VALUE = -2147483648;

    /** 
     * A constant holding the maximum value an <code>int</code> can
     * have, 2<sup>31</sup>-1.
     */
    public static final int MAX_VALUE = 2147483647;

    /** 
     * The <code>Class</code> instance representing the primitive type
     * <code>int</code>.
     *
     * @since   JDK1.1
     */
    public static final java.lang.Class TYPE = null;

    /** 
     * The value of the <code>Integer</code>.
     *
     * @serial
     */
    private int value;

    /** 
     * Constructs a newly allocated <code>Integer</code> object that
     * represents the specified <code>int</code> value.
     *
     * @param   value   the value to be represented by the 
     *			<code>Integer</code> object.
     */
    public Integer(int value) { }

    /** 
     * Constructs a newly allocated <code>Integer</code> object that
     * represents the <code>int</code> value indicated by the
     * <code>String</code> parameter. The string is converted to an
     * <code>int</code> value in exactly the manner used by the
     * <code>parseInt</code> method for radix 10.
     *
     * @param      s   the <code>String</code> to be converted to an
     *                 <code>Integer</code>.
     * @exception  NumberFormatException  if the <code>String</code> does not
     *               contain a parsable integer.
     * @see        java.lang.Integer#parseInt(java.lang.String, int)
     */
    public Integer(java.lang.String s) throws java.lang.NumberFormatException
    { }

    /** 
     * Returns a string representation of the first argument in the
     * radix specified by the second argument.
     * <p>
     * If the radix is smaller than <code>Character.MIN_RADIX</code>
     * or larger than <code>Character.MAX_RADIX</code>, then the radix
     * <code>10</code> is used instead.
     * <p>
     * If the first argument is negative, the first element of the
     * result is the ASCII minus character <code>'-'</code>
     * (<code>'&#92;u002D'</code>). If the first argument is not
     * negative, no sign character appears in the result.
     * <p>
     * The remaining characters of the result represent the magnitude
     * of the first argument. If the magnitude is zero, it is
     * represented by a single zero character <code>'0'</code>
     * (<code>'&#92;u0030'</code>); otherwise, the first character of
     * the representation of the magnitude will not be the zero
     * character.  The following ASCII characters are used as digits: 
     * <blockquote><pre>
     *   0123456789abcdefghijklmnopqrstuvwxyz
     * </pre></blockquote>
     * These are <code>'&#92;u0030'</code> through
     * <code>'&#92;u0039'</code> and <code>'&#92;u0061'</code> through
     * <code>'&#92;u007A'</code>. If <code>radix</code> is
     * <var>N</var>, then the first <var>N</var> of these characters
     * are used as radix-<var>N</var> digits in the order shown. Thus,
     * the digits for hexadecimal (radix 16) are
     * <code>0123456789abcdef</code>. If uppercase letters are
     * desired, the {@link java.lang.String#toUpperCase()} method may
     * be called on the result:
     * <blockquote><pre>
     * Integer.toString(n, 16).toUpperCase()
     * </pre></blockquote>
     *
     * @param   i       an integer to be converted to a string.
     * @param   radix   the radix to use in the string representation.
     * @return  a string representation of the argument in the specified radix.
     * @see     java.lang.Character#MAX_RADIX
     * @see     java.lang.Character#MIN_RADIX
     */
    public static java.lang.String toString(int i, int radix) {
        return null;
    }

    /** 
     * Returns a string representation of the integer argument as an
     * unsigned integer in base&nbsp;16.
     * <p>
     * The unsigned integer value is the argument plus 2<sup>32</sup>
     * if the argument is negative; otherwise, it is equal to the
     * argument.  This value is converted to a string of ASCII digits
     * in hexadecimal (base&nbsp;16) with no extra leading
     * <code>0</code>s. If the unsigned magnitude is zero, it is
     * represented by a single zero character <code>'0'</code>
     * (<code>'&#92;u0030'</code>); otherwise, the first character of
     * the representation of the unsigned magnitude will not be the
     * zero character. The following characters are used as
     * hexadecimal digits:
     * <blockquote><pre>
     * 0123456789abcdef
     * </pre></blockquote>
     * These are the characters <code>'&#92;u0030'</code> through
     * <code>'&#92;u0039'</code> and <code>'&#92;u0061'</code> through
     * <code>'&#92;u0066'</code>. If uppercase letters are
     * desired, the {@link java.lang.String#toUpperCase()} method may
     * be called on the result:
     * <blockquote><pre>
     * Integer.toHexString(n).toUpperCase()
     * </pre></blockquote>
     *
     * @param   i   an integer to be converted to a string.
     * @return  the string representation of the unsigned integer value
     *          represented by the argument in hexadecimal (base&nbsp;16).
     * @since   JDK1.0.2
     */
    public static java.lang.String toHexString(int i) {
        return null;
    }

    /** 
     * Returns a string representation of the integer argument as an
     * unsigned integer in base&nbsp;8.
     * <p>
     * The unsigned integer value is the argument plus 2<sup>32</sup>
     * if the argument is negative; otherwise, it is equal to the
     * argument.  This value is converted to a string of ASCII digits
     * in octal (base&nbsp;8) with no extra leading <code>0</code>s.
     * <p>
     * If the unsigned magnitude is zero, it is represented by a
     * single zero character <code>'0'</code>
     * (<code>'&#92;u0030'</code>); otherwise, the first character of
     * the representation of the unsigned magnitude will not be the
     * zero character. The following characters are used as octal
     * digits:
     * <blockquote><pre>
     * 01234567
     * </pre></blockquote>
     * These are the characters <code>'&#92;u0030'</code> through
     * <code>'&#92;u0037'</code>.
     *
     * @param   i   an integer to be converted to a string.
     * @return  the string representation of the unsigned integer value
     *          represented by the argument in octal (base&nbsp;8).
     * @since   JDK1.0.2
     */
    public static java.lang.String toOctalString(int i) {
        return null;
    }

    /** 
     * Returns a string representation of the integer argument as an
     * unsigned integer in base&nbsp;2.
     * <p>
     * The unsigned integer value is the argument plus 2<sup>32</sup>
     * if the argument is negative; otherwise it is equal to the
     * argument.  This value is converted to a string of ASCII digits
     * in binary (base&nbsp;2) with no extra leading <code>0</code>s.
     * If the unsigned magnitude is zero, it is represented by a
     * single zero character <code>'0'</code>
     * (<code>'&#92;u0030'</code>); otherwise, the first character of
     * the representation of the unsigned magnitude will not be the
     * zero character. The characters <code>'0'</code>
     * (<code>'&#92;u0030'</code>) and <code>'1'</code>
     * (<code>'&#92;u0031'</code>) are used as binary digits.
     *
     * @param   i   an integer to be converted to a string.
     * @return  the string representation of the unsigned integer value
     *          represented by the argument in binary (base&nbsp;2).
     * @since   JDK1.0.2
     */
    public static java.lang.String toBinaryString(int i) {
        return null;
    }

    /** 
     * Returns a <code>String</code> object representing the
     * specified integer. The argument is converted to signed decimal
     * representation and returned as a string, exactly as if the
     * argument and radix 10 were given as arguments to the {@link
     * #toString(int, int)} method.
     *
     * @param   i   an integer to be converted.
     * @return  a string representation of the argument in base&nbsp;10.
     */
    public static java.lang.String toString(int i) {
        return null;
    }

    /** 
     * Parses the string argument as a signed integer in the radix 
     * specified by the second argument. The characters in the string 
     * must all be digits of the specified radix (as determined by 
     * whether {@link java.lang.Character#digit(char, int)} returns a 
     * nonnegative value), except that the first character may be an 
     * ASCII minus sign <code>'-'</code> (<code>'&#92;u002D'</code>) to 
     * indicate a negative value. The resulting integer value is returned. 
     * <p>
     * An exception of type <code>NumberFormatException</code> is
     * thrown if any of the following situations occurs:
     * <ul>
     * <li>The first argument is <code>null</code> or is a string of
     * length zero.
     * <li>The radix is either smaller than 
     * {@link java.lang.Character#MIN_RADIX} or
     * larger than {@link java.lang.Character#MAX_RADIX}. 
     * <li>Any character of the string is not a digit of the specified
     * radix, except that the first character may be a minus sign
     * <code>'-'</code> (<code>'&#92;u002D'</code>) provided that the
     * string is longer than length 1.
     * <li>The value represented by the string is not a value of type
     * <code>int</code>. 
     * </ul><p>
     * Examples:
     * <blockquote><pre>
     * parseInt("0", 10) returns 0
     * parseInt("473", 10) returns 473
     * parseInt("-0", 10) returns 0
     * parseInt("-FF", 16) returns -255
     * parseInt("1100110", 2) returns 102
     * parseInt("2147483647", 10) returns 2147483647
     * parseInt("-2147483648", 10) returns -2147483648
     * parseInt("2147483648", 10) throws a NumberFormatException
     * parseInt("99", 8) throws a NumberFormatException
     * parseInt("Kona", 10) throws a NumberFormatException
     * parseInt("Kona", 27) returns 411787
     * </pre></blockquote>
     *
     * @param      s   the <code>String</code> containing the integer 
     * 			representation to be parsed
     * @param      radix   the radix to be used while parsing <code>s</code>.
     * @return     the integer represented by the string argument in the
     *             specified radix.
     * @exception  NumberFormatException if the <code>String</code>
     * 		   does not contain a parsable <code>int</code>.
     */
    public static int parseInt(java.lang.String s, int radix)
        throws java.lang.NumberFormatException
    {
        return 0;
    }

    /** 
     * Parses the string argument as a signed decimal integer. The 
     * characters in the string must all be decimal digits, except that 
     * the first character may be an ASCII minus sign <code>'-'</code> 
     * (<code>'&#92;u002D'</code>) to indicate a negative value. The resulting 
     * integer value is returned, exactly as if the argument and the radix 
     * 10 were given as arguments to the 
     * {@link #parseInt(java.lang.String, int)} method.
     *
     * @param s	   a <code>String</code> containing the <code>int</code>
     *             representation to be parsed
     * @return     the integer value represented by the argument in decimal.
     * @exception  NumberFormatException  if the string does not contain a
     *               parsable integer.
     */
    public static int parseInt(java.lang.String s)
        throws java.lang.NumberFormatException
    {
        return 0;
    }

    /** 
     * Returns an <code>Integer</code> object holding the value
     * extracted from the specified <code>String</code> when parsed
     * with the radix given by the second argument. The first argument
     * is interpreted as representing a signed integer in the radix
     * specified by the second argument, exactly as if the arguments
     * were given to the {@link #parseInt(java.lang.String, int)}
     * method. The result is an <code>Integer</code> object that
     * represents the integer value specified by the string.
     * <p>
     * In other words, this method returns an <code>Integer</code>
     * object equal to the value of:
     *
     * <blockquote><code>
     * new Integer(Integer.parseInt(s, radix))  
     * </code></blockquote>
     *
     * @param      s   the string to be parsed.
     * @param      radix the radix to be used in interpreting <code>s</code>
     * @return     an <code>Integer</code> object holding the value
     *             represented by the string argument in the specified
     *             radix.
     * @exception NumberFormatException if the <code>String</code>
     * 		  does not contain a parsable <code>int</code>.
     */
    public static java.lang.Integer valueOf(java.lang.String s, int radix)
        throws java.lang.NumberFormatException
    {
        return null;
    }

    /** 
     * Returns an <code>Integer</code> object holding the
     * value of the specified <code>String</code>. The argument is
     * interpreted as representing a signed decimal integer, exactly
     * as if the argument were given to the {@link
     * #parseInt(java.lang.String)} method. The result is an
     * <code>Integer</code> object that represents the integer value
     * specified by the string.
     * <p>
     * In other words, this method returns an <code>Integer</code>
     * object equal to the value of:
     *
     * <blockquote><code>
     * new Integer(Integer.parseInt(s))
     * </code></blockquote>
     *
     * @param      s   the string to be parsed.
     * @return     an <code>Integer</code> object holding the value
     *             represented by the string argument.
     * @exception  NumberFormatException  if the string cannot be parsed 
     *             as an integer.
     */
    public static java.lang.Integer valueOf(java.lang.String s)
        throws java.lang.NumberFormatException
    {
        return null;
    }

    /** 
     * Returns the value of this <code>Integer</code> as a
     * <code>byte</code>.
     */
    public byte byteValue() {
        return ' ';
    }

    /** 
     * Returns the value of this <code>Integer</code> as a
     * <code>short</code>.
     */
    public short shortValue() {
        return -1;
    }

    /** 
     * Returns the value of this <code>Integer</code> as an
     * <code>int</code>.
     */
    public int intValue() {
        return 0;
    }

    /** 
     * Returns the value of this <code>Integer</code> as a
     * <code>long</code>.
     */
    public long longValue() {
        return -1;
    }

    /** 
     * Returns the value of this <code>Integer</code> as a
     * <code>float</code>.
     */
    public float floatValue() {
        return 0.0f;
    }

    /** 
     * Returns the value of this <code>Integer</code> as a
     * <code>double</code>.
     */
    public double doubleValue() {
        return 0.0d;
    }

    /** 
     * Returns a <code>String</code> object representing this
     * <code>Integer</code>'s value. The value is converted to signed
     * decimal representation and returned as a string, exactly as if
     * the integer value were given as an argument to the {@link
     * java.lang.Integer#toString(int)} method.
     *
     * @return  a string representation of the value of this object in
     *          base&nbsp;10.
     */
    public java.lang.String toString() {
        return null;
    }

    /** 
     * Returns a hash code for this <code>Integer</code>.
     *
     * @return  a hash code value for this object, equal to the 
     *          primitive <code>int</code> value represented by this 
     *          <code>Integer</code> object. 
     */
    public int hashCode() {
        return 0;
    }

    /** 
     * Compares this object to the specified object.  The result is
     * <code>true</code> if and only if the argument is not
     * <code>null</code> and is an <code>Integer</code> object that
     * contains the same <code>int</code> value as this object.
     *
     * @param   obj   the object to compare with.
     * @return  <code>true</code> if the objects are the same;
     *          <code>false</code> otherwise.
     */
    public boolean equals(java.lang.Object obj) {
        return false;
    }

    /** 
     * Determines the integer value of the system property with the
     * specified name.
     * <p>
     * The first argument is treated as the name of a system property. 
     * System properties are accessible through the 
     * {@link java.lang.System#getProperty(java.lang.String)} method. The 
     * string value of this property is then interpreted as an integer 
     * value and an <code>Integer</code> object representing this value is 
     * returned. Details of possible numeric formats can be found with 
     * the definition of <code>getProperty</code>. 
     * <p>
     * If there is no property with the specified name, if the specified name
     * is empty or <code>null</code>, or if the property does not have 
     * the correct numeric format, then <code>null</code> is returned.
     * <p>
     * In other words, this method returns an <code>Integer</code>
     * object equal to the value of:
     *
     * <blockquote><code>
     * getInteger(nm, null)
     * </code></blockquote>
     *
     * @param   nm   property name.
     * @return  the <code>Integer</code> value of the property.
     * @see     java.lang.System#getProperty(java.lang.String)
     * @see     java.lang.System#getProperty(java.lang.String, java.lang.String)
     */
    public static java.lang.Integer getInteger(java.lang.String nm) {
        return null;
    }

    /** 
     * Determines the integer value of the system property with the
     * specified name.
     * <p>
     * The first argument is treated as the name of a system property.
     * System properties are accessible through the {@link
     * java.lang.System#getProperty(java.lang.String)} method. The 
     * string value of this property is then interpreted as an integer 
     * value and an <code>Integer</code> object representing this value is 
     * returned. Details of possible numeric formats can be found with 
     * the definition of <code>getProperty</code>. 
     * <p>
     * The second argument is the default value. An <code>Integer</code> object
     * that represents the value of the second argument is returned if there
     * is no property of the specified name, if the property does not have
     * the correct numeric format, or if the specified name is empty or
     *  <code>null</code>.
     * <p>
     * In other words, this method returns an <code>Integer</code> object 
     * equal to the value of:
     * <blockquote><code>
     * getInteger(nm, new Integer(val))
     * </code></blockquote>
     * but in practice it may be implemented in a manner such as: 
     * <blockquote><pre>
     * Integer result = getInteger(nm, null);
     * return (result == null) ? new Integer(val) : result;
     * </pre></blockquote>
     * to avoid the unnecessary allocation of an <code>Integer</code> 
     * object when the default value is not needed. 
     *
     * @param   nm   property name.
     * @param   val   default value.
     * @return  the <code>Integer</code> value of the property.
     * @see     java.lang.System#getProperty(java.lang.String)
     * @see     java.lang.System#getProperty(java.lang.String, java.lang.String)
     */
    public static java.lang.Integer getInteger(java.lang.String nm, int val) {
        return null;
    }

    /** 
     * Returns the integer value of the system property with the
     * specified name.  The first argument is treated as the name of a
     * system property.  System properties are accessible through the
     * {@link java.lang.System#getProperty(java.lang.String)} method.
     * The string value of this property is then interpreted as an
     * integer value, as per the <code>Integer.decode</code> method,
     * and an <code>Integer</code> object representing this value is
     * returned.
     * <p>
     * <ul><li>If the property value begins with the two ASCII characters 
     *         <code>0x</code> or the ASCII character <code>#</code>, not 
     *      followed by a minus sign, then the rest of it is parsed as a 
     *      hexadecimal integer exactly as by the method 
     *      {@link #valueOf(java.lang.String, int)} with radix 16. 
     * <li>If the property value begins with the ASCII character 
     *     <code>0</code> followed by another character, it is parsed as an 
     *     octal integer exactly as by the method 
     *     {@link #valueOf(java.lang.String, int)} with radix 8. 
     * <li>Otherwise, the property value is parsed as a decimal integer 
     * exactly as by the method {@link #valueOf(java.lang.String, int)} 
     * with radix 10. 
     * </ul><p>
     * The second argument is the default value. The default value is
     * returned if there is no property of the specified name, if the
     * property does not have the correct numeric format, or if the
     * specified name is empty or <code>null</code>.
     *
     * @param   nm   property name.
     * @param   val   default value.
     * @return  the <code>Integer</code> value of the property.
     * @see     java.lang.System#getProperty(java.lang.String)
     * @see java.lang.System#getProperty(java.lang.String, java.lang.String)
     * @see java.lang.Integer#decode
     */
    public static java.lang.Integer getInteger(java.lang.String nm,
        java.lang.Integer val)
    {
        return null;
    }

    /** 
     * Decodes a <code>String</code> into an <code>Integer</code>.
     * Accepts decimal, hexadecimal, and octal numbers numbers given
     * by the following grammar:
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
     * leading zero) is parsed as by the <code>Integer.parseInt</code>
     * method with the indicated radix (10, 16, or 8).  This sequence
     * of characters must represent a positive value or a {@link
     * NumberFormatException} will be thrown.  The result is negated
     * if first character of the specified <code>String</code> is the
     * minus sign.  No whitespace characters are permitted in the
     * <code>String</code>.
     *
     * @param     nm the <code>String</code> to decode.
     * @return    a <code>Integer</code> object holding the <code>int</code>
     *		   value represented by <code>nm</code>
     * @exception NumberFormatException  if the <code>String</code> does not
     *            contain a parsable integer.
     * @see java.lang.Integer#parseInt(java.lang.String, int)
     * @since 1.2
     */
    public static java.lang.Integer decode(java.lang.String nm)
        throws java.lang.NumberFormatException
    {
        return null;
    }

    /** 
     * Compares two <code>Integer</code> objects numerically.
     *
     * @param   anotherInteger   the <code>Integer</code> to be compared.
     * @return	the value <code>0</code> if this <code>Integer</code> is
     * 		equal to the argument <code>Integer</code>; a value less than
     * 		<code>0</code> if this <code>Integer</code> is numerically less
     * 		than the argument <code>Integer</code>; and a value greater 
     * 		than <code>0</code> if this <code>Integer</code> is numerically
     * 		 greater than the argument <code>Integer</code> (signed
     * 		 comparison).
     * @since   1.2
     */
    public int compareTo(java.lang.Integer anotherInteger) {
        return 0;
    }

    /** 
     * Compares this <code>Integer</code> object to another object.
     * If the object is an <code>Integer</code>, this function behaves
     * like <code>compareTo(Integer)</code>.  Otherwise, it throws a
     * <code>ClassCastException</code> (as <code>Integer</code>
     * objects are only comparable to other <code>Integer</code>
     * objects).
     *
     * @param   o the <code>Object</code> to be compared.
     * @return  the value <code>0</code> if the argument is a 
     *		<code>Integer</code> numerically equal to this 
     *		<code>Integer</code>; a value less than <code>0</code> 
     *		if the argument is a <code>Integer</code> numerically 
     *		greater than this <code>Integer</code>; and a value 
     *		greater than <code>0</code> if the argument is a 
     *		<code>Integer</code> numerically less than this 
     *		<code>Integer</code>.
     * @exception <code>ClassCastException</code> if the argument is not an
     *		  <code>Integer</code>.
     * @see     java.lang.Comparable
     * @since   1.2
     */
    public int compareTo(java.lang.Object o) {
        return 0;
    }

    /** use serialVersionUID from JDK 1.0.2 for interoperability */
    private static final long serialVersionUID = 1360826667806852920L;

}
