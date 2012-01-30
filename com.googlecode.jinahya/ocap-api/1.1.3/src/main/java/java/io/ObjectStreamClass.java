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

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Proxy;
import java.security.AccessController;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

/** 
 * Serialization's descriptor for classes.
 * It contains the name and serialVersionUID of the class.
 * <br>
 * The ObjectStreamClass for a specific class loaded in this Java VM can
 * be found/created using the lookup method.<p>
 * The algorithm to compute the SerialVersionUID is described in 
 * <a href="../../../guide/serialization/spec/class.doc4.html"> Object Serialization Specification, Section 4.4, Stream Unique Identifiers</a>.
 *
 * @author	Mike Warres
 * @author	Roger Riggs
 * @version 1.98 02/02/00
 * @see ObjectStreamField
 * @see <a href="../../../guide/serialization/spec/class.doc.html"> Object Serialization Specification, Section 4, Class Descriptors</a>
 * @since   JDK1.1
 */
public class ObjectStreamClass implements Serializable
{

    private static final long serialVersionUID = -6120832682080437368L;

    /** serialPersistentFields value indicating no serializable fields  */
    public static final ObjectStreamField[] NO_FIELDS = null;

    private static final ObjectStreamField[] serialPersistentFields = null;

    /*
     * This hidden constructor does not necessarily correspond to
     * a constructor in the original source file -- it keeps javadoc
     * from generating an inappropriate default constructor.
     */
    private ObjectStreamClass() { }

    /** 
     * Find the descriptor for a class that can be serialized. 
     * Creates an ObjectStreamClass instance if one does not exist 
     * yet for class. Null is returned if the specified class does not 
     * implement java.io.Serializable or java.io.Externalizable.
     *
     * @param cl class for which to get the descriptor
     * @return the class descriptor for the specified class
     */
    public static ObjectStreamClass lookup(Class cl) {
        return null;
    }

    /** 
     * The name of the class described by this descriptor.
     *
     * @return a <code>String</code> representing the fully qualified name of
     * the class
     */
    public String getName() {
        return null;
    }

    /** 
     * Return the serialVersionUID for this class.
     * The serialVersionUID defines a set of classes all with the same name
     * that have evolved from a common root class and agree to be serialized
     * and deserialized using a common format.
     * NonSerializable classes have a serialVersionUID of 0L.
     *
     * @return the SUID of the class described by this descriptor
     */
    public long getSerialVersionUID() {
        return -1;
    }

    /** 
     * Return the class in the local VM that this version is mapped to.
     * Null is returned if there is no corresponding local class.
     *
     * @return the <code>Class</code> instance that this descriptor represents
     */
    public Class forClass() {
        return null;
    }

    /** 
     * Return an array of the fields of this serializable class.
     *
     * @return an array containing an element for each persistent
     * field of this class. Returns an array of length zero if
     * there are no fields.
     * @since 1.2
     */
    public ObjectStreamField[] getFields() {
        return null;
    }

    /** 
     * Get the field of this class by name.
     *
     * @param name the name of the data field to look for
     * @return The ObjectStreamField object of the named field or null if there
     * is no such named field.
     */
    public ObjectStreamField getField(String name) {
        return null;
    }

    /** 
     * Return a string describing this ObjectStreamClass.
     */
    public String toString() {
        return null;
    }
}
