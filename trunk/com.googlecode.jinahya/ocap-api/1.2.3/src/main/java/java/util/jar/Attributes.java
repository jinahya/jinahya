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


  


package java.util.jar;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Collection;
import java.util.AbstractSet;
import java.util.Iterator;

/** 
 * The Attributes class maps Manifest attribute names to associated string
 * values. Valid attribute names are case-insensitive, are restricted to 
 * the ASCII characters in the set [0-9a-zA-Z_-], and cannot exceed 70 
 * characters in length. Attribute values can contain any characters and 
 * will be UTF8-encoded when written to the output stream.  See the 
 * <a href="../../../../guide/jar/jar.html">JAR File Specification</a> 
 * for more information about valid attribute names and values.
 *
 * @author  David Connelly
 * @version 1.47, 06/24/03
 * @see	    Manifest
 * @since   1.2
 */
public class Attributes implements Map, Cloneable
{
    /** 
     * The attribute name-value mappings.
     */
    protected Map map;

    /** 
     * Constructs a new, empty Attributes object with default size.
     */
    public Attributes() { }

    /** 
     * Constructs a new, empty Attributes object with the specified
     * initial size.
     *
     * @param size the initial number of attributes
     */
    public Attributes(int size) { }

    /** 
     * Constructs a new Attributes object with the same attribute name-value
     * mappings as in the specified Attributes.
     *
     * @param attr the specified Attributes
     */
    public Attributes(Attributes attr) { }

    /** 
     * Returns the value of the specified attribute name, or null if the
     * attribute name was not found.
     *
     * @param name the attribute name
     * @return the value of the specified attribute name, or null if
     *         not found.
     */
    public Object get(Object name) {
        return null;
    }

    /** 
     * Returns the value of the specified attribute name, specified as
     * a string, or null if the attribute was not found. The attribute
     * name is case-insensitive.
     * <p>
     * This method is defined as:
     * <pre>
     *	    return (String)get(new Attributes.Name((String)name));
     * </pre>
     *
     * @param name the attribute name as a string
     * @return the String value of the specified attribute name, or null if
     *         not found.
     * @throws IllegalArgumentException if the attribute name is invalid
     */
    public String getValue(String name) {
        return null;
    }

    /** 
     * Returns the value of the specified Attributes.Name, or null if the
     * attribute was not found.
     * <p>
     * This method is defined as:
     * <pre>
     *     return (String)get(name);
     * </pre>
     *
     * @param name the Attributes.Name object
     * @return the String value of the specified Attribute.Name, or null if
     *         not found.
     */
    public String getValue(java.util.jar.Attributes.Name name) {
        return null;
    }

    /** 
     * Associates the specified value with the specified attribute name
     * (key) in this Map. If the Map previously contained a mapping for
     * the attribute name, the old value is replaced.
     *
     * @param name the attribute name
     * @param value the attribute value
     * @return the previous value of the attribute, or null if none
     * @exception ClassCastException if the name is not a Attributes.Name
     *            or the value is not a String
     */
    public Object put(Object name, Object value) {
        return null;
    }

    /** 
     * Associates the specified value with the specified attribute name,
     * specified as a String. The attributes name is case-insensitive.
     * If the Map previously contained a mapping for the attribute name,
     * the old value is replaced.
     * <p>
     * This method is defined as:
     * <pre>
     *	    return (String)put(new Attributes.Name(name), value);
     * </pre>
     *
     * @param name the attribute name as a string
     * @param value the attribute value
     * @return the previous value of the attribute, or null if none
     * @exception IllegalArgumentException if the attribute name is invalid
     */
    public String putValue(String name, String value) {
        return null;
    }

    /** 
     * Removes the attribute with the specified name (key) from this Map.
     * Returns the previous attribute value, or null if none.
     *
     * @param name attribute name
     * @return the previous value of the attribute, or null if none
     */
    public Object remove(Object name) {
        return null;
    }

    /** 
     * Returns true if this Map maps one or more attribute names (keys)
     * to the specified value.
     *
     * @param value the attribute value
     * @return true if this Map maps one or more attribute names to
     *         the specified value
     */
    public boolean containsValue(Object value) {
        return false;
    }

    /** 
     * Returns true if this Map contains the specified attribute name (key).
     *
     * @param name the attribute name
     * @return true if this Map contains the specified attribute name
     */
    public boolean containsKey(Object name) {
        return false;
    }

    /** 
     * Copies all of the attribute name-value mappings from the specified
     * Attributes to this Map. Duplicate mappings will be replaced.
     *
     * @param attr the Attributes to be stored in this map
     * @exception ClassCastException if attr is not an Attributes
     */
    public void putAll(Map attr) { }

    /** 
     * Removes all attributes from this Map.
     */
    public void clear() { }

    /** 
     * Returns the number of attributes in this Map.
     */
    public int size() {
        return 0;
    }

    /** 
     * Returns true if this Map contains no attributes.
     */
    public boolean isEmpty() {
        return false;
    }

    /** 
     * Returns a Set view of the attribute names (keys) contained in this Map.
     */
    public Set keySet() {
        return null;
    }

    /** 
     * Returns a Collection view of the attribute values contained in this Map.
     */
    public Collection values() {
        return null;
    }

    /** 
     * Returns a Collection view of the attribute name-value mappings
     * contained in this Map.
     */
    public Set entrySet() {
        return null;
    }

    /** 
     * Compares the specified Attributes object with this Map for equality.
     * Returns true if the given object is also an instance of Attributes
     * and the two Attributes objects represent the same mappings.
     *
     * @param o the Object to be compared
     * @return true if the specified Object is equal to this Map
     */
    public boolean equals(Object o) {
        return false;
    }

    /** 
     * Returns the hash code value for this Map.
     */
    public int hashCode() {
        return 0;
    }

    /** 
     * Returns a copy of the Attributes, implemented as follows:
     * <pre>
     *     public Object clone() { return new Attributes(this); }
     * </pre>
     * Since the attribute names and values are themselves immutable,
     * the Attributes returned can be safely modified without affecting
     * the original.
     */
    public Object clone() {
        return null;
    }

    /** 
     * The Attributes.Name class represents an attribute name stored in
     * this Map. Valid attribute names are case-insensitive, are restricted 
     * to the ASCII characters in the set [0-9a-zA-Z_-], and cannot exceed 
     * 70 characters in length. Attribute values can contain any characters 
     * and will be UTF8-encoded when written to the output stream.  See the 
     * <a href="../../../../guide/jar/jar.html">JAR File Specification</a> 
     * for more information about valid attribute names and values.
     */
    public static class Name
    {
        /** 
         * <code>Name</code> object for <code>Manifest-Version</code> 
         * manifest attribute. This attribute indicates the version number 
         * of the manifest standard to which a JAR file's manifest conforms.
         * @see <a href="../../../../guide/jar/jar.html#JAR Manifest">
         *      Manifest and Signature Specification</a>
         */
        public static final java.util.jar.Attributes.Name MANIFEST_VERSION =
            null;

        /** 
         * <code>Name</code> object for <code>Signature-Version</code> 
         * manifest attribute used when signing JAR files.
         * @see <a href="../../../../guide/jar/jar.html#JAR Manifest">
         *      Manifest and Signature Specification</a>
         */
        public static final java.util.jar.Attributes.Name SIGNATURE_VERSION =
            null;

        /** 
         * <code>Name</code> object for <code>Content-Type</code> 
         * manifest attribute.
         */
        public static final java.util.jar.Attributes.Name CONTENT_TYPE = null;

        /** 
         * <code>Name</code> object for <code>Class-Path</code> 
         * manifest attribute. Bundled extensions can use this attribute 
         * to find other JAR files containing needed classes.
         * @see <a href="../../../../guide/extensions/spec.html#bundled">
         *      Extensions Specification</a>
         */
        public static final java.util.jar.Attributes.Name CLASS_PATH = null;

        /** 
         * <code>Name</code> object for <code>Main-Class</code> manifest 
         * attribute used for launching applications packaged in JAR files. 
         * The <code>Main-Class</code> attribute is used in conjunction 
         * with the <code>-jar</code> command-line option of the 
         * <tt>java</tt> application launcher.
         */
        public static final java.util.jar.Attributes.Name MAIN_CLASS = null;

        /** 
         * <code>Name</code> object for <code>Sealed</code> manifest attribute 
         * used for sealing.
         * @see <a href="../../../../guide/extensions/spec.html#sealing">
         *      Extension Sealing</a>
         */
        public static final java.util.jar.Attributes.Name SEALED = null;

        /** 
         * <code>Name</code> object for <code>Extension-List</code> manifest attribute 
         * used for declaring dependencies on installed extensions.
         * @see <a href="../../../../guide/extensions/spec.html#dependnecy">
         *      Installed extension dependency</a>
         */
        public static final java.util.jar.Attributes.Name EXTENSION_LIST = null;

        /** 
         * <code>Name</code> object for <code>Extension-Name</code> manifest attribute 
         * used for declaring dependencies on installed extensions.
         * @see <a href="../../../../guide/extensions/spec.html#dependency">
         *      Installed extension dependency</a>
         */
        public static final java.util.jar.Attributes.Name EXTENSION_NAME = null;

        /** 
         * <code>Name</code> object for <code>Extension-Name</code> manifest attribute 
         * used for declaring dependencies on installed extensions.
         * @see <a href="../../../../guide/extensions/spec.html#dependency">
         *      Installed extension dependency</a>
         */
        public static final java.util.jar.Attributes.Name EXTENSION_INSTALLATION
            = null;

        /** 
         * <code>Name</code> object for <code>Implementation-Title</code> 
         * manifest attribute used for package versioning.
         * @see <a href="../../../../guide/versioning/spec/VersioningSpecification.html#PackageVersioning">
         *      Java Product Versioning Specification</a>
         */
        public static final java.util.jar.Attributes.Name IMPLEMENTATION_TITLE =
            null;

        /** 
         * <code>Name</code> object for <code>Implementation-Version</code> 
         * manifest attribute used for package versioning.
         * @see <a href="../../../../guide/versioning/spec/VersioningSpecification.html#PackageVersioning">
         *      Java Product Versioning Specification</a>
         */
        public static final java.util.jar.Attributes.Name IMPLEMENTATION_VERSION
            = null;

        /** 
         * <code>Name</code> object for <code>Implementation-Vendor</code> 
         * manifest attribute used for package versioning.
         * @see <a href="../../../../guide/versioning/spec/VersioningSpecification.html#PackageVersioning">
         *      Java Product Versioning Specification</a>
         */
        public static final java.util.jar.Attributes.Name IMPLEMENTATION_VENDOR
            = null;

        /** 
         * <code>Name</code> object for <code>Implementation-Vendor-Id</code> 
         * manifest attribute used for package versioning.
         * @see <a href="../../../../guide/versioning/spec/VersioningSpecification.html#PackageVersioning">
         *      Java Product Versioning Specification</a>
         */
        public static final java.util.jar.Attributes.Name
            IMPLEMENTATION_VENDOR_ID = null;

        /** 
         * <code>Name</code> object for <code>Implementation-Vendor-URL</code> 
         * manifest attribute used for package versioning.
         * @see <a href="../../../../guide/versioning/spec/VersioningSpecification.html#PackageVersioning">
         *      Java Product Versioning Specification</a>
         */
        public static final java.util.jar.Attributes.Name IMPLEMENTATION_URL =
            null;

        /** 
         * <code>Name</code> object for <code>Specification-Title</code> 
         * manifest attribute used for package versioning.
         * @see <a href="../../../../guide/versioning/spec/VersioningSpecification.html#PackageVersioning">
         *      Java Product Versioning Specification</a>
         */
        public static final java.util.jar.Attributes.Name SPECIFICATION_TITLE =
            null;

        /** 
         * <code>Name</code> object for <code>Specification-Version</code> 
         * manifest attribute used for package versioning.
         * @see <a href="../../../../guide/versioning/spec/VersioningSpecification.html#PackageVersioning">
         *      Java Product Versioning Specification</a>
         */
        public static final java.util.jar.Attributes.Name SPECIFICATION_VERSION
            = null;

        /** 
         * <code>Name</code> object for <code>Specification-Vendor</code> 
         * manifest attribute used for package versioning.
         * @see <a href="../../../../guide/versioning/spec/VersioningSpecification.html#PackageVersioning">
         *      Java Product Versioning Specification</a>
         */
        public static final java.util.jar.Attributes.Name SPECIFICATION_VENDOR =
            null;

        /** 
         * Constructs a new attribute name using the given string name.
         *
         * @param name the attribute string name
         * @exception IllegalArgumentException if the attribute name was
         *            invalid
         * @exception NullPointerException if the attribute name was null
         */
        public Name(String name) { }

        /** 
         * Compares this attribute name to another for equality.
         * @param o the object to compare
         * @return true if this attribute name is equal to the
         *         specified attribute object
         */
        public boolean equals(Object o) {
            return false;
        }

        /** 
         * Computes the hash value for this attribute name.
         */
        public int hashCode() {
            return 0;
        }

        /** 
         * Returns the attribute name as a String.
         */
        public String toString() {
            return null;
        }
    }
}
