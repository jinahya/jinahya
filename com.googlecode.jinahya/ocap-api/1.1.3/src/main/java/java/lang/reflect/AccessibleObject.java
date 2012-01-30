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
 * The AccessibleObject class is the base class for Field, Method and
 * Constructor objects.  It provides the ability to flag a reflected
 * object as suppressing default Java language access control checks
 * when it is used.  The access checks--for public, default (package)
 * access, protected, and private members--are performed when Fields,
 * Methods or Constructors are used to set or get fields, to invoke
 * methods, or to create and initialize new instances of classes,
 * respectively.
 *
 * <p>Setting the <tt>accessible</tt> flag in a reflected object
 * permits sophisticated applications with sufficient privilege, such
 * as Java Object Serialization or other persistence mechanisms, to
 * manipulate objects in a manner that would normally be prohibited.
 *
 * @see Field
 * @see Method
 * @see Constructor
 * @see ReflectPermission
 *
 * @since 1.2
 */
public class AccessibleObject
{

    /** 
     * Constructor: only used by the Java Virtual Machine.
     */
    protected AccessibleObject() { }

    /** 
     * Convenience method to set the <tt>accessible</tt> flag for an
     * array of objects with a single security check (for efficiency).
     *
     * <p>First, if there is a security manager, its
     * <code>checkPermission</code> method is called with a
     * <code>ReflectPermission("suppressAccessChecks")</code> permission.
     *
     * <p>A <code>SecurityException</code> is raised if <code>flag</code> is
     * <code>true</code> but accessibility of any of the elements of the input
     * <code>array</code> may not be changed (for example, if the element
     * object is a {@link Constructor} object for the class {@link
     * java.lang.Class}).  In the event of such a SecurityException, the
     * accessiblity of objects is set to <code>flag</code> for array elements
     * upto (and excluding) the element for which the exception occurred; the
     * accessiblity of elements beyond (and including) the element for which
     * the exception occurred is unchanged.
     *
     * @param array the array of AccessibleObjects
     * @param flag  the new value for the <tt>accessible</tt> flag
     *              in each object
     * @throws SecurityException if the request is denied.
     * @see SecurityManager#checkPermission
     * @see java.lang.RuntimePermission
     */
    public static void setAccessible(AccessibleObject[] array, boolean flag)
        throws SecurityException
    { }

    /** 
     * Set the <tt>accessible</tt> flag for this object to
     * the indicated boolean value.  A value of <tt>true</tt> indicates that
     * the reflected object should suppress Java language access
     * checking when it is used.  A value of <tt>false</tt> indicates 
     * that the reflected object should enforce Java language access checks.
     *
     * <p>First, if there is a security manager, its
     * <code>checkPermission</code> method is called with a
     * <code>ReflectPermission("suppressAccessChecks")</code> permission.
     * 
     * <p>A <code>SecurityException</code> is raised if <code>flag</code> is
     * <code>true</code> but accessibility of this object may not be changed
     * (for example, if this element object is a {@link Constructor} object for
     * the class {@link java.lang.Class}).
     *
     * <p>A <code>SecurityException</code> is raised if this object is a {@link
     * java.lang.reflect.Constructor} object for the class
     * <code>java.lang.Class</code>, and <code>flag</code> is true.
     *
     * @param flag the new value for the <tt>accessible</tt> flag
     * @throws SecurityException if the request is denied.
     * @see SecurityManager#checkPermission
     * @see java.lang.RuntimePermission
     */
    public void setAccessible(boolean flag) throws SecurityException { }

    /** 
     * Get the value of the <tt>accessible</tt> flag for this object.
     *
     * @return the value of the object's <tt>accessible</tt> flag
     */
    public boolean isAccessible() {
        return false;
    }
}
