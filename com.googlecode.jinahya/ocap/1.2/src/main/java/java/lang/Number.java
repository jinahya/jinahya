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
 * The abstract class <code>Number</code> is the superclass of classes
 * <code>BigDecimal</code>, <code>BigInteger</code>,
 * <code>Byte</code>, <code>Double</code>, <code>Float</code>,
 * <code>Integer</code>, <code>Long</code>, and <code>Short</code>.
 * NOTE: <B>java.math.BigDecimal</B> is found in J2ME CDC profiles such as 
 * J2ME Foundation Profile.
 * <p>
 * Subclasses of <code>Number</code> must provide methods to convert 
 * the represented numeric value to <code>byte</code>, <code>double</code>,
 * <code>float</code>, <code>int</code>, <code>long</code>, and
 * <code>short</code>.
 *
 * @author	Lee Boynton
 * @author	Arthur van Hoff
 * @version 1.25, 02/02/00
 * @see     java.lang.Byte
 * @see     java.lang.Double
 * @see     java.lang.Float
 * @see     java.lang.Integer
 * @see     java.lang.Long
 * @see     java.lang.Short
 * @since   JDK1.0
 */
public abstract class Number implements java.io.Serializable
{

    public Number() { }

    /** 
     * Returns the value of the specified number as an <code>int</code>.
     * This may involve rounding or truncation.
     *
     * @return  the numeric value represented by this object after conversion
     *          to type <code>int</code>.
     */
    public abstract int intValue();

    /** 
     * Returns the value of the specified number as a <code>long</code>.
     * This may involve rounding or truncation.
     *
     * @return  the numeric value represented by this object after conversion
     *          to type <code>long</code>.
     */
    public abstract long longValue();

    /** 
     * Returns the value of the specified number as a <code>float</code>.
     * This may involve rounding.
     *
     * @return  the numeric value represented by this object after conversion
     *          to type <code>float</code>.
     */
    public abstract float floatValue();

    /** 
     * Returns the value of the specified number as a <code>double</code>.
     * This may involve rounding.
     *
     * @return  the numeric value represented by this object after conversion
     *          to type <code>double</code>.
     */
    public abstract double doubleValue();

    /** 
     * Returns the value of the specified number as a <code>byte</code>.
     * This may involve rounding or truncation.
     *
     * @return  the numeric value represented by this object after conversion
     *          to type <code>byte</code>.
     * @since   JDK1.1
     */
    public byte byteValue() {
        return ' ';
    }

    /** 
     * Returns the value of the specified number as a <code>short</code>.
     * This may involve rounding or truncation.
     *
     * @return  the numeric value represented by this object after conversion
     *          to type <code>short</code>.
     * @since   JDK1.1
     */
    public short shortValue() {
        return -1;
    }

    /** use serialVersionUID from JDK 1.0.2 for interoperability */
    private static final long serialVersionUID = -8742448824652078965L;

}
