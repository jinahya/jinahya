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
 * The Boolean class wraps a value of the primitive type 
 * <code>boolean</code> in an object. An object of type 
 * <code>Boolean</code> contains a single field whose type is 
 * <code>boolean</code>. 
 * <p>
 * In addition, this class provides many methods for 
 * converting a <code>boolean</code> to a <code>String</code> and a 
 * <code>String</code> to a <code>boolean</code>, as well as other 
 * constants and methods useful when dealing with a 
 * <code>boolean</code>. 
 *
 * @author  Arthur van Hoff
 * @version 1.38, 02/02/00
 * @since   JDK1.0
 */
public final class Boolean implements java.io.Serializable
{
    /** 
     * The <code>Boolean</code> object corresponding to the primitive 
     * value <code>true</code>. 
     */
    public static final java.lang.Boolean TRUE = null;

    /** 
     * The <code>Boolean</code> object corresponding to the primitive 
     * value <code>false</code>. 
     */
    public static final java.lang.Boolean FALSE = null;

    /** 
     * The Class object representing the primitive type boolean.
     *
     * @since   JDK1.1
     */
    public static final java.lang.Class TYPE = null;

    /** 
     * The value of the Boolean.
     *
     * @serial
     */
    private boolean value;

    /** use serialVersionUID from JDK 1.0.2 for interoperability */
    private static final long serialVersionUID = -3665804199014368530L;

    /** 
     * Allocates a <code>Boolean</code> object representing the 
     * <code>value</code> argument. 
     *
     * <p><b>Note: It is rarely appropriate to use this constructor.
     * Unless a <i>new</i> instance is required, the static factory
     * {@link #valueOf(boolean)} is generally a better choice. It is
     * likely to yield significantly better space and time performance.</b>
     * 
     * @param   value   the value of the <code>Boolean</code>.
     */
    public Boolean(boolean value) { }

    /** 
     * Allocates a <code>Boolean</code> object representing the value 
     * <code>true</code> if the string argument is not <code>null</code> 
     * and is equal, ignoring case, to the string <code>"true"</code>. 
     * Otherwise, allocate a <code>Boolean</code> object representing the 
     * value <code>false</code>. Examples:<p>
     * <tt>new&nbsp;Boolean("True")</tt> produces a <tt>Boolean</tt> object 
     * that represents <tt>true</tt>.<br>
     * <tt>new&nbsp;Boolean("yes")</tt> produces a <tt>Boolean</tt> object 
     * that represents <tt>false</tt>.
     *
     * @param   s   the string to be converted to a <code>Boolean</code>.
     */
    public Boolean(java.lang.String s) { }

    /** 
     * Returns the value of this <tt>Boolean</tt> object as a boolean 
     * primitive.
     *
     * @return  the primitive <code>boolean</code> value of this object.
     */
    public boolean booleanValue() {
        return false;
    }

    /** 
     * Returns a <tt>Boolean</tt> instance representing the specified
     * <tt>boolean</tt> value.  If the specified <tt>boolean</tt> value
     * is <tt>true</tt>, this method returns <tt>Boolean.TRUE</tt>;
     * if it is <tt>false</tt>, this method returns <tt>Boolean.FALSE</tt>.
     * If a new <tt>Boolean</tt> instance is not required, this method
     * should generally be used in preference to the constructor
     * {@link #Boolean(boolean)}, as this method is likely to to yield
     * significantly better space and time performance.
     *
     * @param  b a boolean value.
     * @return a <tt>Boolean</tt> instance representing <tt>b</tt>.
     * @since  1.4
     */
    public static java.lang.Boolean valueOf(boolean b) {
        return null;
    }

    /** 
     * Returns a <code>Boolean</code> with a value represented by the
     * specified String.  The <code>Boolean</code> returned represents the
     * value <code>true</code> if the string argument is not <code>null</code>
     * and is equal, ignoring case, to the string <code>"true"</code>. <p>
     * Example: <tt>Boolean.valueOf("True")</tt> returns <tt>true</tt>.<br>
     * Example: <tt>Boolean.valueOf("yes")</tt> returns <tt>false</tt>.
     *
     * @param   s   a string.
     * @return  the <code>Boolean</code> value represented by the string.
     */
    public static java.lang.Boolean valueOf(java.lang.String s) {
        return null;
    }

    /** 
     * Returns a <tt>String</tt> object representing the specified
     * boolean.  If the specified boolean is <code>true</code>, then
     * the string <tt>"true"</tt> will be returned, otherwise the
     * string <tt>"false"</tt> will be returned.
     *
     * @param b	the boolean to be converted
     * @return the string representation of the specified <code>boolean</code>
     * @since 1.4
     */
    public static java.lang.String toString(boolean b) {
        return null;
    }

    /** 
     * Returns a <tt>String</tt> object representing this Boolean's
     * value.  If this object represents the value <code>true</code>,
     * a string equal to <code>"true"</code> is returned. Otherwise, a
     * string equal to <code>"false"</code> is returned.
     *
     * @return  a string representation of this object. 
     */
    public java.lang.String toString() {
        return null;
    }

    /** 
     * Returns a hash code for this <tt>Boolean</tt> object.
     *
     * @return  the integer <tt>1231</tt> if this object represents 
     * <tt>true</tt>; returns the integer <tt>1237</tt> if this 
     * object represents <tt>false</tt>. 
     */
    public int hashCode() {
        return 0;
    }

    /** 
     * Returns <code>true</code> if and only if the argument is not 
     * <code>null</code> and is a <code>Boolean </code>object that 
     * represents the same <code>boolean</code> value as this object. 
     *
     * @param   obj   the object to compare with.
     * @return  <code>true</code> if the Boolean objects represent the 
     *          same value; <code>false</code> otherwise.
     */
    public boolean equals(java.lang.Object obj) {
        return false;
    }

    /** 
     * Returns <code>true</code> if and only if the system property 
     * named by the argument exists and is equal to the string 
     * <code>"true"</code>. (Beginning with version 1.0.2 of the 
     * Java<font size="-2"><sup>TM</sup></font> platform, the test of 
     * this string is case insensitive.) A system property is accessible 
     * through <code>getProperty</code>, a method defined by the 
     * <code>System</code> class.
     * <p>
     * If there is no property with the specified name, or if the specified
     * name is empty or null, then <code>false</code> is returned.
     *
     * @param   name   the system property name.
     * @return  the <code>boolean</code> value of the system property.
     * @see     java.lang.System#getProperty(java.lang.String)
     * @see     java.lang.System#getProperty(java.lang.String, java.lang.String)
     */
    public static boolean getBoolean(java.lang.String name) {
        return false;
    }
}
