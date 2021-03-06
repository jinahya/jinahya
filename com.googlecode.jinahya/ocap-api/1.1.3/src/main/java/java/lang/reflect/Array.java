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


  


package java.lang.reflect;

/** 
 * The <code>Array</code> class provides static methods to dynamically create and
 * access Java arrays.
 *
 * <p><code>Array</code> permits widening conversions to occur during a get or set
 * operation, but throws an <code>IllegalArgumentException</code> if a narrowing
 * conversion would occur.
 *
 * @author Nakul Saraiya
 */
public final class Array
{

    /*
     * This hidden constructor does not necessarily correspond to
     * a constructor in the original source file -- it keeps javadoc
     * from generating an inappropriate default constructor.
     */
    private Array() { }

    /** 
     * Creates a new array with the specified component type and
     * length.
     * Invoking this method is equivalent to creating an array
     * as follows:
     * <blockquote>
     * <pre>
     * int[] x = {length};
     * Array.newInstance(componentType, x);
     * </pre>
     * </blockquote>
     *
     * @param componentType the <code>Class</code> object representing the
     * component type of the new array
     * @param length the length of the new array
     * @return the new array
     * @exception NullPointerException if the specified
     * <code>componentType</code> parameter is null
     * @exception IllegalArgumentException if componentType is Void.TYPE
     * @exception NegativeArraySizeException if the specified <code>length</code> 
     * is negative
     */
    public static Object newInstance(Class componentType, int length)
        throws NegativeArraySizeException
    {
        return null;
    }

    /** 
     * Creates a new array
     * with the specified component type and dimensions. 
     * If <code>componentType</code>
     * represents a non-array class or interface, the new array
     * has <code>dimensions.length</code> dimensions and&nbsp;
     * <code>componentType&nbsp;</code> as its component type. If
     * <code>componentType</code> represents an array class, the
     * number of dimensions of the new array is equal to the sum
     * of <code>dimensions.length</code> and the number of
     * dimensions of <code>componentType</code>. In this case, the
     * component type of the new array is the component type of
     * <code>componentType</code>.
     * 
     * <p>The number of dimensions of the new array must not
     * exceed the number of array dimensions supported by the
     * implementation (typically 255).
     *
     * @param componentType the <code>Class</code> object representing the component
     * type of the new array
     * @param dimensions an array of <code>int</code> types representing the dimensions of
     * the new array
     * @return the new array
     * @exception NullPointerException if the specified 
     * <code>componentType</code> argument is null
     * @exception IllegalArgumentException if the specified <code>dimensions</code> 
     * argument is a zero-dimensional array, or if the number of
     * requested dimensions exceeds the limit on the number of array dimensions 
     * supported by the implementation (typically 255), or if componentType 
     * is Void.TYPE.
     * @exception NegativeArraySizeException if any of the components in
     * the specified <code>dimensions</code> argument is negative.
     */
    public static Object newInstance(Class componentType, int[] dimensions)
        throws IllegalArgumentException, NegativeArraySizeException
    {
        return null;
    }

    /** 
     * Returns the length of the specified array object, as an <code>int</code>.
     *
     * @param array the array
     * @return the length of the array
     * @exception IllegalArgumentException if the object argument is not
     * an array
     */
    public static int getLength(Object array)
        throws IllegalArgumentException
    {
        return 0;
    }

    /** 
     * Returns the value of the indexed component in the specified
     * array object.  The value is automatically wrapped in an object
     * if it has a primitive type.
     *
     * @param array the array
     * @param index the index
     * @return the (possibly wrapped) value of the indexed component in
     * the specified array
     * @exception NullPointerException If the specified object is null
     * @exception IllegalArgumentException If the specified object is not
     * an array
     * @exception ArrayIndexOutOfBoundsException If the specified <code>index</code> 
     * argument is negative, or if it is greater than or equal to the
     * length of the specified array
     */
    public static Object get(Object array, int index)
        throws IllegalArgumentException, ArrayIndexOutOfBoundsException
    {
        return null;
    }

    /** 
     * Returns the value of the indexed component in the specified
     * array object, as a <code>boolean</code>.
     *
     * @param array the array
     * @param index the index
     * @return the value of the indexed component in the specified array
     * @exception NullPointerException If the specified object is null
     * @exception IllegalArgumentException If the specified object is not
     * an array, or if the indexed element cannot be converted to the
     * return type by an identity or widening conversion
     * @exception ArrayIndexOutOfBoundsException If the specified <code>index</code> 
     * argument is negative, or if it is greater than or equal to the
     * length of the specified array
     * @see Array#get
     */
    public static boolean getBoolean(Object array, int index)
        throws IllegalArgumentException, ArrayIndexOutOfBoundsException
    {
        return false;
    }

    /** 
     * Returns the value of the indexed component in the specified
     * array object, as a <code>byte</code>.
     *
     * @param array the array
     * @param index the index
     * @return the value of the indexed component in the specified array
     * @exception NullPointerException If the specified object is null
     * @exception IllegalArgumentException If the specified object is not
     * an array, or if the indexed element cannot be converted to the
     * return type by an identity or widening conversion
     * @exception ArrayIndexOutOfBoundsException If the specified <code>index</code> 
     * argument is negative, or if it is greater than or equal to the
     * length of the specified array
     * @see Array#get
     */
    public static byte getByte(Object array, int index)
        throws IllegalArgumentException, ArrayIndexOutOfBoundsException
    {
        return ' ';
    }

    /** 
     * Returns the value of the indexed component in the specified
     * array object, as a <code>char</code>.
     *
     * @param array the array
     * @param index the index
     * @return the value of the indexed component in the specified array
     * @exception NullPointerException If the specified object is null
     * @exception IllegalArgumentException If the specified object is not
     * an array, or if the indexed element cannot be converted to the
     * return type by an identity or widening conversion
     * @exception ArrayIndexOutOfBoundsException If the specified <code>index</code> 
     * argument is negative, or if it is greater than or equal to the
     * length of the specified array
     * @see Array#get
     */
    public static char getChar(Object array, int index)
        throws IllegalArgumentException, ArrayIndexOutOfBoundsException
    {
        return ' ';
    }

    /** 
     * Returns the value of the indexed component in the specified
     * array object, as a <code>short</code>.
     *
     * @param array the array
     * @param index the index
     * @return the value of the indexed component in the specified array
     * @exception NullPointerException If the specified object is null
     * @exception IllegalArgumentException If the specified object is not
     * an array, or if the indexed element cannot be converted to the
     * return type by an identity or widening conversion
     * @exception ArrayIndexOutOfBoundsException If the specified <code>index</code> 
     * argument is negative, or if it is greater than or equal to the
     * length of the specified array
     * @see Array#get
     */
    public static short getShort(Object array, int index)
        throws IllegalArgumentException, ArrayIndexOutOfBoundsException
    {
        return -1;
    }

    /** 
     * Returns the value of the indexed component in the specified
     * array object, as an <code>int</code>.
     *
     * @param array the array
     * @param index the index
     * @return the value of the indexed component in the specified array
     * @exception NullPointerException If the specified object is null
     * @exception IllegalArgumentException If the specified object is not
     * an array, or if the indexed element cannot be converted to the
     * return type by an identity or widening conversion
     * @exception ArrayIndexOutOfBoundsException If the specified <code>index</code> 
     * argument is negative, or if it is greater than or equal to the
     * length of the specified array
     * @see Array#get
     */
    public static int getInt(Object array, int index)
        throws IllegalArgumentException, ArrayIndexOutOfBoundsException
    {
        return 0;
    }

    /** 
     * Returns the value of the indexed component in the specified
     * array object, as a <code>long</code>.
     *
     * @param array the array
     * @param index the index
     * @return the value of the indexed component in the specified array
     * @exception NullPointerException If the specified object is null
     * @exception IllegalArgumentException If the specified object is not
     * an array, or if the indexed element cannot be converted to the
     * return type by an identity or widening conversion
     * @exception ArrayIndexOutOfBoundsException If the specified <code>index</code> 
     * argument is negative, or if it is greater than or equal to the
     * length of the specified array
     * @see Array#get
     */
    public static long getLong(Object array, int index)
        throws IllegalArgumentException, ArrayIndexOutOfBoundsException
    {
        return -1;
    }

    /** 
     * Returns the value of the indexed component in the specified
     * array object, as a <code>float</code>.
     *
     * @param array the array
     * @param index the index
     * @return the value of the indexed component in the specified array
     * @exception NullPointerException If the specified object is null
     * @exception IllegalArgumentException If the specified object is not
     * an array, or if the indexed element cannot be converted to the
     * return type by an identity or widening conversion
     * @exception ArrayIndexOutOfBoundsException If the specified <code>index</code> 
     * argument is negative, or if it is greater than or equal to the
     * length of the specified array
     * @see Array#get
     */
    public static float getFloat(Object array, int index)
        throws IllegalArgumentException, ArrayIndexOutOfBoundsException
    {
        return 0.0f;
    }

    /** 
     * Returns the value of the indexed component in the specified
     * array object, as a <code>double</code>.
     *
     * @param array the array
     * @param index the index
     * @return the value of the indexed component in the specified array
     * @exception NullPointerException If the specified object is null
     * @exception IllegalArgumentException If the specified object is not
     * an array, or if the indexed element cannot be converted to the
     * return type by an identity or widening conversion
     * @exception ArrayIndexOutOfBoundsException If the specified <code>index</code> 
     * argument is negative, or if it is greater than or equal to the
     * length of the specified array
     * @see Array#get
     */
    public static double getDouble(Object array, int index)
        throws IllegalArgumentException, ArrayIndexOutOfBoundsException
    {
        return 0.0d;
    }

    /** 
     * Sets the value of the indexed component of the specified array
     * object to the specified new value.  The new value is first
     * automatically unwrapped if the array has a primitive component
     * type.
     * @param array the array
     * @param index the index into the array
     * @param value the new value of the indexed component
     * @exception NullPointerException If the specified object argument
     * is null, or if the array component type is primitive and the specified
     * value is null
     * @exception IllegalArgumentException If the specified object argument
     * is not an array, or if the array component type is primitive and
     * the specified value cannot be converted to the primitive type by
     * a combination of unwrapping and identity or widening conversions
     * @exception ArrayIndexOutOfBoundsException If the specified <code>index</code> 
     * argument is negative, or if it is greater than or equal to
     * the length of the specified array
     */
    public static void set(Object array, int index, Object value)
        throws IllegalArgumentException, ArrayIndexOutOfBoundsException
    { }

    /** 
     * Sets the value of the indexed component of the specified array
     * object to the specified <code>boolean</code> value.
     * @param array the array
     * @param index the index into the array
     * @param z the new value of the indexed component
     * @exception NullPointerException If the specified object argument
     * is null
     * @exception IllegalArgumentException If the specified object argument
     * is not an array, or if the the specified value cannot be converted
     * to the underlying array's component type by an identity or a
     * primitive widening widening conversion
     * @exception ArrayIndexOutOfBoundsException If the specified <code>index</code> 
     * argument is negative, or if it is greater than or equal to
     * the length of the specified array
     * @see Array#set
     */
    public static void setBoolean(Object array, int index, boolean z)
        throws IllegalArgumentException, ArrayIndexOutOfBoundsException
    { }

    /** 
     * Sets the value of the indexed component of the specified array
     * object to the specified <code>byte</code> value.
     * @param array the array
     * @param index the index into the array
     * @param b the new value of the indexed component
     * @exception NullPointerException If the specified object argument
     * is null
     * @exception IllegalArgumentException If the specified object argument
     * is not an array, or if the the specified value cannot be converted
     * to the underlying array's component type by an identity or a
     * primitive widening widening conversion
     * @exception ArrayIndexOutOfBoundsException If the specified <code>index</code> 
     * argument is negative, or if it is greater than or equal to
     * the length of the specified array
     * @see Array#set
     */
    public static void setByte(Object array, int index, byte b)
        throws IllegalArgumentException, ArrayIndexOutOfBoundsException
    { }

    /** 
     * Sets the value of the indexed component of the specified array
     * object to the specified <code>char</code> value.
     * @param array the array
     * @param index the index into the array
     * @param c the new value of the indexed component
     * @exception NullPointerException If the specified object argument
     * is null
     * @exception IllegalArgumentException If the specified object argument
     * is not an array, or if the the specified value cannot be converted
     * to the underlying array's component type by an identity or a
     * primitive widening widening conversion
     * @exception ArrayIndexOutOfBoundsException If the specified <code>index</code> 
     * argument is negative, or if it is greater than or equal to
     * the length of the specified array
     * @see Array#set
     */
    public static void setChar(Object array, int index, char c)
        throws IllegalArgumentException, ArrayIndexOutOfBoundsException
    { }

    /** 
     * Sets the value of the indexed component of the specified array
     * object to the specified <code>short</code> value.
     * @param array the array
     * @param index the index into the array
     * @param s the new value of the indexed component
     * @exception NullPointerException If the specified object argument
     * is null
     * @exception IllegalArgumentException If the specified object argument
     * is not an array, or if the the specified value cannot be converted
     * to the underlying array's component type by an identity or a
     * primitive widening widening conversion
     * @exception ArrayIndexOutOfBoundsException If the specified <code>index</code> 
     * argument is negative, or if it is greater than or equal to
     * the length of the specified array
     * @see Array#set
     */
    public static void setShort(Object array, int index, short s)
        throws IllegalArgumentException, ArrayIndexOutOfBoundsException
    { }

    /** 
     * Sets the value of the indexed component of the specified array
     * object to the specified <code>int</code> value.
     * @param array the array
     * @param index the index into the array
     * @param i the new value of the indexed component
     * @exception NullPointerException If the specified object argument
     * is null
     * @exception IllegalArgumentException If the specified object argument
     * is not an array, or if the the specified value cannot be converted
     * to the underlying array's component type by an identity or a
     * primitive widening widening conversion
     * @exception ArrayIndexOutOfBoundsException If the specified <code>index</code> 
     * argument is negative, or if it is greater than or equal to
     * the length of the specified array
     * @see Array#set
     */
    public static void setInt(Object array, int index, int i)
        throws IllegalArgumentException, ArrayIndexOutOfBoundsException
    { }

    /** 
     * Sets the value of the indexed component of the specified array
     * object to the specified <code>long</code> value.
     * @param array the array
     * @param index the index into the array
     * @param l the new value of the indexed component
     * @exception NullPointerException If the specified object argument
     * is null
     * @exception IllegalArgumentException If the specified object argument
     * is not an array, or if the the specified value cannot be converted
     * to the underlying array's component type by an identity or a
     * primitive widening widening conversion
     * @exception ArrayIndexOutOfBoundsException If the specified <code>index</code> 
     * argument is negative, or if it is greater than or equal to
     * the length of the specified array
     * @see Array#set
     */
    public static void setLong(Object array, int index, long l)
        throws IllegalArgumentException, ArrayIndexOutOfBoundsException
    { }

    /** 
     * Sets the value of the indexed component of the specified array
     * object to the specified <code>float</code> value.
     * @param array the array
     * @param index the index into the array
     * @param f the new value of the indexed component
     * @exception NullPointerException If the specified object argument
     * is null
     * @exception IllegalArgumentException If the specified object argument
     * is not an array, or if the the specified value cannot be converted
     * to the underlying array's component type by an identity or a
     * primitive widening widening conversion
     * @exception ArrayIndexOutOfBoundsException If the specified <code>index</code> 
     * argument is negative, or if it is greater than or equal to
     * the length of the specified array
     * @see Array#set
     */
    public static void setFloat(Object array, int index, float f)
        throws IllegalArgumentException, ArrayIndexOutOfBoundsException
    { }

    /** 
     * Sets the value of the indexed component of the specified array
     * object to the specified <code>double</code> value.
     * @param array the array
     * @param index the index into the array
     * @param d the new value of the indexed component
     * @exception NullPointerException If the specified object argument
     * is null
     * @exception IllegalArgumentException If the specified object argument
     * is not an array, or if the the specified value cannot be converted
     * to the underlying array's component type by an identity or a
     * primitive widening widening conversion
     * @exception ArrayIndexOutOfBoundsException If the specified <code>index</code> 
     * argument is negative, or if it is greater than or equal to
     * the length of the specified array
     * @see Array#set
     */
    public static void setDouble(Object array, int index, double d)
        throws IllegalArgumentException, ArrayIndexOutOfBoundsException
    { }
}
