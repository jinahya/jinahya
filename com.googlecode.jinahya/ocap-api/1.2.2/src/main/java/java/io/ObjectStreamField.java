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


  


package java.io;

import java.lang.reflect.Field;

/** 
 * A description of a Serializable field from a Serializable class.
 * An array of ObjectStreamFields is used to declare the Serializable
 * fields of a class.
 *
 * @author	Mike Warres
 * @author	Roger Riggs
 * @version 1.38, 01/07/25
 * @see ObjectStreamClass
 * @since 1.2
 */
public class ObjectStreamField implements Comparable
{

    /** 
     * Create a Serializable field with the specified type.
     * This field should be documented with a <code>serialField</code>
     * tag. 
     *
     * @param n the name of the serializable field
     * @param clazz the <code>Class</code> object of the serializable field
     */
    public ObjectStreamField(String name, Class type) { }

    /** 
     * Creates an ObjectStreamField representing a serializable field with the
     * given name and type.  If unshared is false, values of the represented
     * field are serialized and deserialized in the default manner--if the
     * field is non-primitive, object values are serialized and deserialized as
     * if they had been written and read by calls to writeObject and
     * readObject.  If unshared is true, values of the represented field are
     * serialized and deserialized as if they had been written and read by
     * calls to writeUnshared and readUnshared.
     *
     * @param   name field name
     * @param   type field type
     * @param   unshared if false, write/read field values in the same manner
     *          as writeObject/readObject; if true, write/read in the same
     *          manner as writeUnshared/readUnshared
     */
    public ObjectStreamField(String name, Class type, boolean unshared) { }

    /** 
     * Get the name of this field.
     *
     * @return a <code>String</code> representing the name of the serializable
     * field 
     */
    public String getName() {
        return null;
    }

    /** 
     * Get the type of the field.
     *
     * @return	the <code>Class</code> object of the serializable field 
     */
    public Class getType() {
        return null;
    }

    /** 
     * Returns character encoding of field type.  The encoding is as follows:
     * <blockquote><pre>
     * B            byte
     * C            char
     * D            double
     * F            float
     * I            int
     * J            long
     * L            class or interface
     * S            short
     * Z            boolean
     * [            array
     * </pre></blockquote>
     *
     * @return	the typecode of the serializable field
     */
    public char getTypeCode() {
        return ' ';
    }

    /** 
     * Return the JVM type signature.
     *
     * @return	null if this field has a primitive type.
     */
    public String getTypeString() {
        return null;
    }

    /** 
     * Offset of field within instance data.
     *
     * @return	the offset of this field
     * @see #setOffset
     */
    public int getOffset() {
        return 0;
    }

    /** 
     * Offset within instance data.
     *
     * @param	offset the offset of the field
     * @see #getOffset
     */
    protected void setOffset(int offset) { }

    /** 
     * Return true if this field has a primitive type.
     *
     * @return	true if and only if this field corresponds to a primitive type
     */
    public boolean isPrimitive() {
        return false;
    }

    /** 
     * Compare this field with another <code>ObjectStreamField</code>.
     * Return -1 if this is smaller, 0 if equal, 1 if greater.
     * Types that are primitives are "smaller" than object types.
     * If equal, the field names are compared.
     */
    public int compareTo(Object obj) {
        return 0;
    }

    /** 
     * Return a string that describes this field.
     */
    public String toString() {
        return null;
    }
}
