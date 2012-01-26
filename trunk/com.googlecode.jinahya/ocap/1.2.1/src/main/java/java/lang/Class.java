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

import java.lang.reflect.Member;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.lang.reflect.InvocationTargetException;
import java.io.InputStream;
import java.io.ObjectStreamClass;
import java.io.ObjectStreamField;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.List;
import java.util.WeakHashMap;
import java.util.Map;
import java.util.Collections;

/** 
 * Instances of the class <code>Class</code> represent classes and interfaces
 * in a running Java application.  Every array also belongs to a class that is
 * reflected as a <code>Class</code> object that is shared by all arrays with
 * the same element type and number of dimensions.  The primitive Java types
 * (<code>boolean</code>, <code>byte</code>, <code>char</code>,
 * <code>short</code>, <code>int</code>, <code>long</code>,
 * <code>float</code>, and <code>double</code>), and the keyword
 * <code>void</code> are also represented as <code>Class</code> objects.
 *
 * <p> <code>Class</code> has no public constructor. Instead <code>Class</code>
 * objects are constructed automatically by the Java Virtual Machine as classes
 * are loaded and by calls to the <code>defineClass</code> method in the class
 * loader.
 *
 * <p> The following example uses a <code>Class</code> object to print the
 * class name of an object:
 *
 * <p> <blockquote><pre>
 *     void printClassName(Object obj) {
 *         System.out.println("The class of " + obj +
 *                            " is " + obj.getClass().getName());
 *     }
 * </pre></blockquote>
 * 
 * <p> It is also possible to get the <code>Class</code> object for a named
 * type (or for void) using a class literal 
 * (JLS Section <A HREF="http://java.sun.com/docs/books/jls/second_edition/html/expressions.doc.html#251530">15.8.2</A>). 
 * For example:
 *
 * <p> <blockquote><pre>
 *     System.out.println("The name of class Foo is: "+Foo.class.getName());
 * </pre></blockquote>
 *
 * @author  unascribed
 * @version 1.135, 05/25/01
 * @see     java.lang.ClassLoader#defineClass(byte[], int, int)
 * @since   JDK1.0
 */
public final class Class implements java.io.Serializable
{
    /** 
     * Class Class is special cased within the Serialization Stream Protocol. 
     *
     * A Class instance is written intially into an ObjectOutputStream in the 
     * following format:
     * <pre>
     *      <code>TC_CLASS</code> ClassDescriptor
     *      A ClassDescriptor is a special cased serialization of 
     *      a <code>java.io.ObjectStreamClass</code> instance. 
     * </pre>
     * A new handle is generated for the initial time the class descriptor
     * is written into the stream. Future references to the class descriptor
     * are written as references to the initial class descriptor instance.
     *
     * @see java.io.ObjectStreamClass
     */
    private static final ObjectStreamField[] serialPersistentFields = null;

    /** use serialVersionUID from JDK 1.1 for interoperability */
    private static final long serialVersionUID = 3206093459760846163L;

    /*
     * This hidden constructor does not necessarily correspond to
     * a constructor in the original source file -- it keeps javadoc
     * from generating an inappropriate default constructor.
     */
    private Class() { }

    /** 
     * Converts the object to a string. The string representation is the
     * string "class" or "interface", followed by a space, and then by the
     * fully qualified name of the class in the format returned by
     * <code>getName</code>.  If this <code>Class</code> object represents a
     * primitive type, this method returns the name of the primitive type.  If
     * this <code>Class</code> object represents void this method returns
     * "void".
     *
     * @return a string representation of this class object.
     */
    public java.lang.String toString() {
        return null;
    }

    /** 
     * Returns the <code>Class</code> object associated with the class or
     * interface with the given string name.  Invoking this method is
     * equivalent to:
     *
     * <blockquote><pre>
     *  Class.forName(className, true, currentLoader)
     * </pre></blockquote>
     *
     * where <code>currentLoader</code> denotes the defining class loader of
     * the current class.
     *
     * <p> For example, the following code fragment returns the
     * runtime <code>Class</code> descriptor for the class named
     * <code>java.lang.Thread</code>:
     *
     * <blockquote><pre>
     *   Class&nbsp;t&nbsp;= Class.forName("java.lang.Thread")
     * </pre></blockquote>
     * <p>
     * A call to <tt>forName("X")</tt> causes the class named 
     * <tt>X</tt> to be initialized.
     *
     * @param      className   the fully qualified name of the desired class.
     * @return     the <code>Class</code> object for the class with the
     *             specified name.
     * @exception LinkageError if the linkage fails
     * @exception ExceptionInInitializerError if the initialization provoked
     *            by this method fails
     * @exception ClassNotFoundException if the class cannot be located
     */
    public static java.lang.Class forName(java.lang.String className)
        throws java.lang.ClassNotFoundException
    {
        return null;
    }

    /** 
     * Returns the <code>Class</code> object associated with the class or
     * interface with the given string name, using the given class loader.
     * Given the fully qualified name for a class or interface (in the same
     * format returned by <code>getName</code>) this method attempts to
     * locate, load, and link the class or interface.  The specified class
     * loader is used to load the class or interface.  If the parameter
     * <code>loader</code> is null, the class is loaded through the bootstrap
     * class loader.  The class is initialized only if the
     * <code>initialize</code> parameter is <code>true</code> and if it has
     * not been initialized earlier.
     *
     * <p> If <code>name</code> denotes a primitive type or void, an attempt
     * will be made to locate a user-defined class in the unnamed package whose
     * name is <code>name</code>. Therefore, this method cannot be used to
     * obtain any of the <code>Class</code> objects representing primitive
     * types or void.
     *
     * <p> If <code>name</code> denotes an array class, the component type of
     * the array class is loaded but not initialized.
     *
     * <p> For example, in an instance method the expression:
     *
     * <blockquote><pre>
     *  Class.forName("Foo")
     * </pre></blockquote>
     *
     * is equivalent to:
     *
     * <blockquote><pre>
     *  Class.forName("Foo", true, this.getClass().getClassLoader())
     * </pre></blockquote>
     *
     * Note that this method throws errors related to loading, linking or
     * initializing as specified in Sections 12.2, 12.3 and 12.4 of <em>The
     * Java Language Specification</em>.
     * Note that this method does not check whether the requested class 
     * is accessible to its caller.
     *
     * <p> If the <code>loader</code> is <code>null</code>, and a security
     * manager is present, and the caller's class loader is not null, then this
     * method calls the security manager's <code>checkPermission</code> method
     * with a <code>RuntimePermission("getClassLoader")</code> permission to
     * ensure it's ok to access the bootstrap class loader.
     *
     * @param name       fully qualified name of the desired class
     * @param initialize whether the class must be initialized
     * @param loader     class loader from which the class must be loaded
     * @return           class object representing the desired class
     * 
     * @exception LinkageError if the linkage fails
     * @exception ExceptionInInitializerError if the initialization provoked
     *            by this method fails
     * @exception ClassNotFoundException if the class cannot be located by
     *            the specified class loader
     *
     * @see 	  java.lang.Class#forName(String) 
     * @see 	  java.lang.ClassLoader
     * @since 	  1.2
     */
    public static java.lang.Class forName(java.lang.String name, boolean
        initialize, java.lang.ClassLoader loader)
        throws java.lang.ClassNotFoundException
    {
        return null;
    }

    /** 
     * Creates a new instance of the class represented by this <tt>Class</tt>
     * object.  The class is instantiated as if by a <code>new</code>
     * expression with an empty argument list.  The class is initialized if it
     * has not already been initialized.
     *
     * <p>If there is a security manager, this method first calls the security
     * manager's <code>checkMemberAccess</code> method with <code>this</code>
     * and <code>Member.PUBLIC</code> as its arguments. If the class is in a
     * package, then this method also calls the security manager's
     * <code>checkPackageAccess</code> method with the package name as its
     * argument. Either of these calls could result in a SecurityException.
     *
     * @return     a newly allocated instance of the class represented by this
     *             object.
     * @exception  IllegalAccessException  if the class or its nullary 
     *               constructor is not accessible.
     * @exception  InstantiationException 
     *               if this <code>Class</code> represents an abstract class,
     *               an interface, an array class, a primitive type, or void;
     *               or if the class has no nullary constructor;
     *               or if the instantiation fails for some other reason.
     * @exception  ExceptionInInitializerError if the initialization
     *               provoked by this method fails.
     * @exception  SecurityException if there is no permission to create a new
     *               instance.
     *
     */
    public java.lang.Object newInstance()
        throws java.lang.InstantiationException,
        java.lang.IllegalAccessException
    {
        return null;
    }

    /** 
     * Determines if the specified <code>Object</code> is assignment-compatible
     * with the object represented by this <code>Class</code>.  This method is
     * the dynamic equivalent of the Java language <code>instanceof</code>
     * operator. The method returns <code>true</code> if the specified
     * <code>Object</code> argument is non-null and can be cast to the
     * reference type represented by this <code>Class</code> object without
     * raising a <code>ClassCastException.</code> It returns <code>false</code>
     * otherwise.
     *
     * <p> Specifically, if this <code>Class</code> object represents a
     * declared class, this method returns <code>true</code> if the specified
     * <code>Object</code> argument is an instance of the represented class (or
     * of any of its subclasses); it returns <code>false</code> otherwise. If
     * this <code>Class</code> object represents an array class, this method
     * returns <code>true</code> if the specified <code>Object</code> argument
     * can be converted to an object of the array class by an identity
     * conversion or by a widening reference conversion; it returns
     * <code>false</code> otherwise. If this <code>Class</code> object
     * represents an interface, this method returns <code>true</code> if the
     * class or any superclass of the specified <code>Object</code> argument
     * implements this interface; it returns <code>false</code> otherwise. If
     * this <code>Class</code> object represents a primitive type, this method
     * returns <code>false</code>.
     *
     * @param   obj the object to check
     * @return  true if <code>obj</code> is an instance of this class
     *
     * @since JDK1.1
     */
    public boolean isInstance(java.lang.Object obj) {
        return false;
    }

    /** 
     * Determines if the class or interface represented by this
     * <code>Class</code> object is either the same as, or is a superclass or
     * superinterface of, the class or interface represented by the specified
     * <code>Class</code> parameter. It returns <code>true</code> if so;
     * otherwise it returns <code>false</code>. If this <code>Class</code>
     * object represents a primitive type, this method returns
     * <code>true</code> if the specified <code>Class</code> parameter is
     * exactly this <code>Class</code> object; otherwise it returns
     * <code>false</code>.
     *
     * <p> Specifically, this method tests whether the type represented by the
     * specified <code>Class</code> parameter can be converted to the type
     * represented by this <code>Class</code> object via an identity conversion
     * or via a widening reference conversion. See <em>The Java Language
     * Specification</em>, sections 5.1.1 and 5.1.4 , for details.
     * 
     * @param cls the <code>Class</code> object to be checked
     * @return the <code>boolean</code> value indicating whether objects of the
     * type <code>cls</code> can be assigned to objects of this class
     * @exception NullPointerException if the specified Class parameter is
     *            null.
     * @since JDK1.1
     */
    public boolean isAssignableFrom(java.lang.Class cls) {
        return false;
    }

    /** 
     * Determines if the specified <code>Class</code> object represents an
     * interface type.
     *
     * @return  <code>true</code> if this object represents an interface;
     *          <code>false</code> otherwise.
     */
    public boolean isInterface() {
        return false;
    }

    /** 
     * Determines if this <code>Class</code> object represents an array class.
     *
     * @return  <code>true</code> if this object represents an array class;
     *          <code>false</code> otherwise.
     * @since   JDK1.1
     */
    public boolean isArray() {
        return false;
    }

    /** 
     * Determines if the specified <code>Class</code> object represents a
     * primitive type.
     *
     * <p> There are nine predefined <code>Class</code> objects to represent
     * the eight primitive types and void.  These are created by the Java
     * Virtual Machine, and have the same names as the primitive types that
     * they represent, namely <code>boolean</code>, <code>byte</code>,
     * <code>char</code>, <code>short</code>, <code>int</code>,
     * <code>long</code>, <code>float</code>, and <code>double</code>.
     *
     * <p> These objects may only be accessed via the following public static
     * final variables, and are the only <code>Class</code> objects for which
     * this method returns <code>true</code>.
     *
     * @return true if and only if this class represents a primitive type
     *
     * @see     java.lang.Boolean#TYPE
     * @see     java.lang.Character#TYPE
     * @see     java.lang.Byte#TYPE
     * @see     java.lang.Short#TYPE
     * @see     java.lang.Integer#TYPE
     * @see     java.lang.Long#TYPE
     * @see     java.lang.Float#TYPE
     * @see     java.lang.Double#TYPE
     * @see     java.lang.Void#TYPE
     * @since JDK1.1
     */
    public boolean isPrimitive() {
        return false;
    }

    /** 
     * Returns the  name of the entity (class, interface, array class,
     * primitive type, or void) represented by this <tt>Class</tt> object,
     * as a <tt>String</tt>.
     * 
     * <p> If this class object represents a reference type that is not an
     * array type then the binary name of the class is returned, as specified
     * by the Java Language Specification, Second Edition.
     *
     * <p> If this class object represents a primitive type or void, then the
     * name returned is a <tt>String</tt> equal to the Java language
     * keyword corresponding to the primitive type or void.
     * 
     * <p> If this class object represents a class of arrays, then the internal
     * form of the name consists of the name of the element type preceded by
     * one or more '<tt>[</tt>' characters representing the depth of the array
     * nesting.  The encoding of element type names is as follows:
     *
     * <blockquote><table summary="Element types and encodings">
     * <tr><th> Element Type <th> Encoding
     * <tr><td> boolean      <td align=center> Z
     * <tr><td> byte         <td align=center> B
     * <tr><td> char         <td align=center> C
     * <tr><td> class or interface  <td align=center> L<i>classname;</i>
     * <tr><td> double       <td align=center> D
     * <tr><td> float        <td align=center> F
     * <tr><td> int          <td align=center> I
     * <tr><td> long         <td align=center> J
     * <tr><td> short        <td align=center> S
     * </table></blockquote>
     *
     * <p> The class or interface name <i>classname</i> is the binary name of
     * the class specified above.
     *
     * <p> Examples:
     * <blockquote><pre>
     * String.class.getName()
     *     returns "java.lang.String"
     * byte.class.getName()
     *     returns "byte"
     * (new Object[3]).getClass().getName()
     *     returns "[Ljava.lang.Object;"
     * (new int[3][4][5][6][7][8][9]).getClass().getName()
     *     returns "[[[[[[[I"
     * </pre></blockquote>
     *
     * @return  the name of the class or interface
     *          represented by this object.
     */
    public java.lang.String getName() {
        return null;
    }

    /** 
     * Returns the class loader for the class.  Some implementations may use
     * null to represent the bootstrap class loader. This method will return
     * null in such implementations if this class was loaded by the bootstrap
     * class loader.
     *
     * <p> If a security manager is present, and the caller's class loader is
     * not null and the caller's class loader is not the same as or an ancestor of
     * the class loader for the class whose class loader is requested, then
     * this method calls the security manager's <code>checkPermission</code> 
     * method with a <code>RuntimePermission("getClassLoader")</code> 
     * permission to ensure it's ok to access the class loader for the class.
     * 
     * <p>If this object
     * represents a primitive type or void, null is returned.
     *
     * @return  the class loader that loaded the class or interface
     *          represented by this object.
     * @throws SecurityException
     *    if a security manager exists and its 
     *    <code>checkPermission</code> method denies
     *    access to the class loader for the class.
     * @see java.lang.ClassLoader
     * @see SecurityManager#checkPermission
     * @see java.lang.RuntimePermission
     */
    public java.lang.ClassLoader getClassLoader() {
        return null;
    }

    /** 
     * Returns the <code>Class</code> representing the superclass of the entity
     * (class, interface, primitive type or void) represented by this
     * <code>Class</code>.  If this <code>Class</code> represents either the
     * <code>Object</code> class, an interface, a primitive type, or void, then
     * null is returned.  If this object represents an array class then the
     * <code>Class</code> object representing the <code>Object</code> class is
     * returned.
     *
     * @return the superclass of the class represented by this object.
     */
    public java.lang.Class getSuperclass() {
        return null;
    }

    /** 
     * Gets the package for this class.  The class loader of this class is used
     * to find the package.  If the class was loaded by the bootstrap class
     * loader the set of packages loaded from CLASSPATH is searched to find the
     * package of the class. Null is returned if no package object was created
     * by the class loader of this class.
     *
     * <p> Packages have attributes for versions and specifications only if the
     * information was defined in the manifests that accompany the classes, and
     * if the class loader created the package instance with the attributes
     * from the manifest.
     *
     * @return the package of the class, or null if no package
     *         information is available from the archive or codebase.
     */
    public java.lang.Package getPackage() {
        return null;
    }

    /** 
     * Determines the interfaces implemented by the class or interface
     * represented by this object.
     *
     * <p> If this object represents a class, the return value is an array
     * containing objects representing all interfaces implemented by the
     * class. The order of the interface objects in the array corresponds to
     * the order of the interface names in the <code>implements</code> clause
     * of the declaration of the class represented by this object. For 
     * example, given the declaration:
     * <blockquote><pre>
     * class Shimmer implements FloorWax, DessertTopping { ... }
     * </pre></blockquote>
     * suppose the value of <code>s</code> is an instance of 
     * <code>Shimmer</code>; the value of the expression:
     * <blockquote><pre>
     * s.getClass().getInterfaces()[0]
     * </pre></blockquote>
     * is the <code>Class</code> object that represents interface 
     * <code>FloorWax</code>; and the value of:
     * <blockquote><pre>
     * s.getClass().getInterfaces()[1]
     * </pre></blockquote>
     * is the <code>Class</code> object that represents interface 
     * <code>DessertTopping</code>.
     *
     * <p> If this object represents an interface, the array contains objects
     * representing all interfaces extended by the interface. The order of the
     * interface objects in the array corresponds to the order of the interface
     * names in the <code>extends</code> clause of the declaration of the
     * interface represented by this object.
     *
     * <p> If this object represents a class or interface that implements no
     * interfaces, the method returns an array of length 0.
     *
     * <p> If this object represents a primitive type or void, the method
     * returns an array of length 0.
     *
     * @return an array of interfaces implemented by this class.
     */
    public java.lang.Class[] getInterfaces() {
        return null;
    }

    /** 
     * Returns the <code>Class</code> representing the component type of an
     * array.  If this class does not represent an array class this method
     * returns null.
     *
     * @return the <code>Class</code> representing the component type of this
     * class if this class is an array
     * @see     java.lang.reflect.Array
     * @since JDK1.1
     */
    public java.lang.Class getComponentType() {
        return null;
    }

    /** 
     * Returns the Java language modifiers for this class or interface, encoded
     * in an integer. The modifiers consist of the Java Virtual Machine's
     * constants for <code>public</code>, <code>protected</code>,
     * <code>private</code>, <code>final</code>, <code>static</code>,
     * <code>abstract</code> and <code>interface</code>; they should be decoded
     * using the methods of class <code>Modifier</code>.
     *
     * <p> If the underlying class is an array class, then its
     * <code>public</code>, <code>private</code> and <code>protected</code>
     * modifiers are the same as those of its component type.  If this
     * <code>Class</code> represents a primitive type or void, its
     * <code>public</code> modifier is always <code>true</code>, and its
     * <code>protected</code> and <code>private</code> modifiers are always
     * <code>false</code>. If this object represents an array class, a
     * primitive type or void, then its <code>final</code> modifier is always
     * <code>true</code> and its interface modifier is always
     * <code>false</code>. The values of its other modifiers are not determined
     * by this specification.
     *
     * <p> The modifier encodings are defined in <em>The Java Virtual Machine
     * Specification</em>, table 4.1.
     *
     * @return the <code>int</code> representing the modifiers for this class
     * @see     java.lang.reflect.Modifier
     * @since JDK1.1
     */
    public int getModifiers() {
        return 0;
    }

    /** 
     * Gets the signers of this class.
     *
     * @return  the signers of this class, or null if there are no signers.  In
     * 		particular, this method returns null if this object represents
     * 		a primitive type or void.
     * @since 	JDK1.1
     */
    public java.lang.Object[] getSigners() {
        return null;
    }

    /** 
     * If the class or interface represented by this <code>Class</code> object
     * is a member of another class, returns the <code>Class</code> object
     * representing the class in which it was declared.  This method returns
     * null if this class or interface is not a member of any other class.  If
     * this <code>Class</code> object represents an array class, a primitive
     * type, or void,then this method returns null.
     *
     * @return the declaring class for this class
     * @since JDK1.1
     */
    public java.lang.Class getDeclaringClass() {
        return null;
    }

    /** 
     * Returns an array containing <code>Class</code> objects representing all
     * the public classes and interfaces that are members of the class
     * represented by this <code>Class</code> object.  This includes public
     * class and interface members inherited from superclasses and public class
     * and interface members declared by the class.  This method returns an
     * array of length 0 if this <code>Class</code> object has no public member
     * classes or interfaces.  This method also returns an array of length 0 if
     * this <code>Class</code> object represents a primitive type, an array
     * class, or void.
     * 
     * <p>For this class and each of its superclasses, the following
     * security checks are performed:
     * If there is a security manager, the security manager's
     * <code>checkMemberAccess</code> method is called with <code>this</code>
     * and <code>Member.PUBLIC</code> as its arguments, where <code>this</code>
     * is this class or the superclass whose members are being determined. If
     * the class is in a package, then the security manager's
     * <code>checkPackageAccess</code> method is also called with the package
     * name as its argument. Either of these calls could result in a
     * SecurityException.
     *
     * @return the array of <code>Class</code> objects representing the public
     * members of this class
     * @exception SecurityException    if access to the information is denied.
     * @see       SecurityManager#checkMemberAccess(Class, int)
     * @see       SecurityManager#checkPackageAccess(String)
     *
     * @since JDK1.1
     */
    public java.lang.Class[] getClasses() {
        return null;
    }

    /** 
     * Returns an array containing <code>Field</code> objects reflecting all
     * the accessible public fields of the class or interface represented by
     * this <code>Class</code> object.  The elements in the array returned are
     * not sorted and are not in any particular order.  This method returns an
     * array of length 0 if the class or interface has no accessible public
     * fields, or if it represents an array class, a primitive type, or void.
     *
     * <p> Specifically, if this <code>Class</code> object represents a class,
     * this method returns the public fields of this class and of all its
     * superclasses.  If this <code>Class</code> object represents an
     * interface, this method returns the fields of this interface and of all
     * its superinterfaces.
     *
     * <p>If there is a security manager, this method first
     * calls the security manager's <code>checkMemberAccess</code> method
     * with <code>this</code> and <code>Member.PUBLIC</code> 
     * as its arguments. If the class is in a package, then this method
     * also calls the security manager's <code>checkPackageAccess</code> 
     * method with the package name as its argument. Either of these calls
     * could result in a SecurityException.
     * 
     * <p> The implicit length field for array class is not reflected by this
     * method. User code should use the methods of class <code>Array</code> to
     * manipulate arrays.
     *
     * <p> See <em>The Java Language Specification</em>, sections 8.2 and 8.3.
     *
     * @return the array of <code>Field</code> objects representing the
     * public fields
     * @exception SecurityException    if access to the information is denied.
     * @see       java.lang.reflect.Field
     * @see       SecurityManager#checkMemberAccess(Class, int)
     * @see       SecurityManager#checkPackageAccess(String)
     * @since JDK1.1
     */
    public Field[] getFields() throws java.lang.SecurityException {
        return null;
    }

    /** 
     * Returns an array containing <code>Method</code> objects reflecting all
     * the public <em>member</em> methods of the class or interface represented
     * by this <code>Class</code> object, including those declared by the class
     * or interface and and those inherited from superclasses and
     * superinterfaces.  The elements in the array returned are not sorted and
     * are not in any particular order.  This method returns an array of length
     * 0 if this <code>Class</code> object represents a class or interface that
     * has no public member methods, or if this <code>Class</code> object
     * represents an array class, primitive type, or void.
     *
     * <p>If there is a security manager, this method first
     * calls the security manager's <code>checkMemberAccess</code> method
     * with <code>this</code> and <code>Member.PUBLIC</code> 
     * as its arguments. If the class is in a package, then this method
     * also calls the security manager's <code>checkPackageAccess</code> 
     * method with the package name 
     * as its argument. Either of these calls could result in a SecurityException.
     * 
     * <p> The class initialization method <code>&lt;clinit&gt;</code> is not
     * included in the returned array. If the class declares multiple public
     * member methods with the same parameter types, they are all included in
     * the returned array.
     *
     * <p> See <em>The Java Language Specification</em>, sections 8.2 and 8.4.
     *
     * @return the array of <code>Method</code> objects representing the
     * public methods of this class
     * @exception SecurityException    if access to the information is denied.
     * @see       java.lang.reflect.Method
     * @see       SecurityManager#checkMemberAccess(Class, int)
     * @see       SecurityManager#checkPackageAccess(String)
     * @since JDK1.1
     */
    public Method[] getMethods() throws java.lang.SecurityException {
        return null;
    }

    /** 
     * Returns an array containing <code>Constructor</code> objects reflecting
     * all the public constructors of the class represented by this
     * <code>Class</code> object.  An array of length 0 is returned if the
     * class has no public constructors, or if the class is an array class, or
     * if the class reflects a primitive type or void.
     *
     * <p>If there is a security manager, this method first
     * calls the security manager's <code>checkMemberAccess</code> method
     * with <code>this</code> and <code>Member.PUBLIC</code> 
     * as its arguments. If the class is in a package, then this method
     * also calls the security manager's <code>checkPackageAccess</code> 
     * method with the package name 
     * as its argument. Either of these calls could result in a SecurityException.
     * 
     * @return the array containing <code>Method</code> objects for all the
     * declared public constructors of this class matches the specified
     * <code>parameterTypes</code>
     * @exception SecurityException    if access to the information is denied.
     * @see       java.lang.reflect.Constructor
     * @see       SecurityManager#checkMemberAccess(Class, int)
     * @see       SecurityManager#checkPackageAccess(String)
     * @since JDK1.1
     */
    public Constructor[] getConstructors() throws java.lang.SecurityException {
        return null;
    }

    /** 
     * Returns a <code>Field</code> object that reflects the specified public
     * member field of the class or interface represented by this
     * <code>Class</code> object. The <code>name</code> parameter is a
     * <code>String</code> specifying the simple name of the desired field.
     *
     * <p>If there is a security manager, this method first
     * calls the security manager's <code>checkMemberAccess</code> method
     * with <code>this</code> and <code>Member.PUBLIC</code> 
     * as its arguments. If the class is in a package, then this method
     * also calls the security manager's <code>checkPackageAccess</code> 
     * method with the package name 
     * as its argument. Either of these calls could result in a SecurityException.
     *
     * <p> The field to be reflected is determined by the algorithm that
     * follows.  Let C be the class represented by this object:
     * <OL>
     * <LI> If C declares a public field with the name specified, that is the
     *      field to be reflected.</LI>
     * <LI> If no field was found in step 1 above, this algorithm is applied
     * 	    recursively to each direct superinterface of C. The direct
     * 	    superinterfaces are searched in the order they were declared.</LI>
     * <LI> If no field was found in steps 1 and 2 above, and C has a
     *      superclass S, then this algorithm is invoked recursively upon S.
     *      If C has no superclass, then a <code>NoSuchFieldException</code>
     *      is thrown.</LI>
     * </OL>
     *
     * <p> See <em>The Java Language Specification</em>, sections 8.2 and 8.3.
     * 
     * @param name the field name
     * @return  the <code>Field</code> object of this class specified by 
     * <code>name</code>
     * @exception NoSuchFieldException if a field with the specified name is
     *              not found.
     * @exception NullPointerException if <code>name</code> is <code>null</code>
     * @exception SecurityException    if access to the information is denied.
     * @see       java.lang.reflect.Field
     * @see       SecurityManager#checkMemberAccess(Class, int)
     * @see       SecurityManager#checkPackageAccess(String)
     * @since JDK1.1
     */
    public Field getField(java.lang.String name)
        throws java.lang.NoSuchFieldException, java.lang.SecurityException
    {
        return null;
    }

    /** 
     * Returns a <code>Method</code> object that reflects the specified public
     * member method of the class or interface represented by this
     * <code>Class</code> object. The <code>name</code> parameter is a
     * <code>String</code> specifying the simple name the desired method. The
     * <code>parameterTypes</code> parameter is an array of <code>Class</code>
     * objects that identify the method's formal parameter types, in declared
     * order. If <code>parameterTypes</code> is <code>null</code>, it is 
     * treated as if it were an empty array.
     *
     * <p>If there is a security manager, this method first
     * calls the security manager's <code>checkMemberAccess</code> method
     * with <code>this</code> and <code>Member.PUBLIC</code> 
     * as its arguments. If the class is in a package, then this method
     * also calls the security manager's <code>checkPackageAccess</code> 
     * method with the package name 
     * as its argument. Either of these calls could result in a SecurityException.
     *
     * <p> If the <code>name</code> is "&lt;init&gt;"or "&lt;clinit&gt;" a
     * <code>NoSuchMethodException</code> is raised. Otherwise, the method to
     * be reflected is determined by the algorithm that follows.  Let C be the
     * class represented by this object:
     * <OL>
     * <LI> C is searched for any <I>matching methods</I>. If no matching
     * 	    method is found, the algorithm of step 1 is invoked recursively on
     * 	    the superclass of C.</LI>
     * <LI> If no method was found in step 1 above, the superinterfaces of C
     *      are searched for a matching method. If any such method is found, it
     *      is reflected.</LI>
     * </OL>
     *
     * To find a matching method in a class C:&nbsp; If C declares exactly one
     * public method with the specified name and exactly the same formal
     * parameter types, that is the method reflected. If more than one such
     * method is found in C, and one of these methods has a return type that is
     * more specific than any of the others, that method is reflected;
     * otherwise one of the methods is chosen arbitrarily.
     *
     * <p> See <em>The Java Language Specification</em>, sections 8.2 and 8.4.
     *
     * @param name the name of the method
     * @param parameterTypes the list of parameters
     * @return the <code>Method</code> object that matches the specified
     * <code>name</code> and <code>parameterTypes</code>
     * @exception NoSuchMethodException if a matching method is not found
     *            or if the name is "&lt;init&gt;"or "&lt;clinit&gt;".
     * @exception NullPointerException if <code>name</code> is <code>null</code>
     * @exception SecurityException    if access to the information is denied.
     * @see       java.lang.reflect.Method
     * @see       SecurityManager#checkMemberAccess(Class, int)
     * @see       SecurityManager#checkPackageAccess(String)
     * @since JDK1.1
     */
    public Method getMethod(java.lang.String name, java.lang.Class[]
        parameterTypes)
        throws java.lang.NoSuchMethodException, java.lang.SecurityException
    {
        return null;
    }

    /** 
     * Returns a <code>Constructor</code> object that reflects the specified
     * public constructor of the class represented by this <code>Class</code>
     * object. The <code>parameterTypes</code> parameter is an array of
     * <code>Class</code> objects that identify the constructor's formal
     * parameter types, in declared order.
     *
     * <p> The constructor to reflect is the public constructor of the class
     * represented by this <code>Class</code> object whose formal parameter
     * types match those specified by <code>parameterTypes</code>.
     *
     * <p>If there is a security manager, this method first
     * calls the security manager's <code>checkMemberAccess</code> method
     * with <code>this</code> and <code>Member.PUBLIC</code> 
     * as its arguments. If the class is in a package, then this method
     * also calls the security manager's <code>checkPackageAccess</code> method
     * with the package name as its argument. Either of these calls could
     * result in a SecurityException.
     *
     * @param parameterTypes the parameter array
     * @return the <code>Method</code> object of the public constructor that
     * matches the specified <code>parameterTypes</code>
     * @exception NoSuchMethodException if a matching method is not found.
     * @exception SecurityException     if access to the information is denied.
     * @see       java.lang.reflect.Constructor
     * @see       SecurityManager#checkMemberAccess(Class, int)
     * @see       SecurityManager#checkPackageAccess(String)
     * @since JDK1.1
     */
    public Constructor getConstructor(java.lang.Class[] parameterTypes)
        throws java.lang.NoSuchMethodException, java.lang.SecurityException
    {
        return null;
    }

    /** 
     * Returns an array of <code>Class</code> objects reflecting all the
     * classes and interfaces declared as members of the class represented by
     * this <code>Class</code> object. This includes public, protected, default
     * (package) access, and private classes and interfaces declared by the
     * class, but excludes inherited classes and interfaces.  This method
     * returns an array of length 0 if the class declares no classes or
     * interfaces as members, or if this <code>Class</code> object represents a
     * primitive type, an array class, or void.
     *
     * <p>If there is a security manager, this method first
     * calls the security manager's <code>checkMemberAccess</code> method
     * with <code>this</code> and <code>Member.DECLARED</code> 
     * as its arguments. If the class is in a package, then this method also
     * calls the security manager's <code>checkPackageAccess</code> method with
     * the package name as its argument. Either of these calls could result in
     * a SecurityException.
     *
     * @return the array of <code>Class</code> objects representing all the 
     * declared members of this class
     * @exception SecurityException    if access to the information is denied.
     * @see       SecurityManager#checkMemberAccess(Class, int)
     * @see       SecurityManager#checkPackageAccess(String)
     * @since JDK1.1
     */
    public java.lang.Class[] getDeclaredClasses()
        throws java.lang.SecurityException
    {
        return null;
    }

    /** 
     * Returns an array of <code>Field</code> objects reflecting all the fields
     * declared by the class or interface represented by this
     * <code>Class</code> object. This includes public, protected, default
     * (package) access, and private fields, but excludes inherited fields.
     * The elements in the array returned are not sorted and are not in any
     * particular order.  This method returns an array of length 0 if the class
     * or interface declares no fields, or if this <code>Class</code> object
     * represents a primitive type, an array class, or void.
     *
     * <p> See <em>The Java Language Specification</em>, sections 8.2 and 8.3.
     *
     * <p>If there is a security manager, this method first
     * calls the security manager's <code>checkMemberAccess</code> method with
     * <code>this</code> and <code>Member.DECLARED</code> as its arguments. If
     * the class is in a package, then this method also calls the security
     * manager's <code>checkPackageAccess</code> method with the package name
     * as its argument. Either of these calls could result in a
     * SecurityException.
     *
     * @return    the array of <code>Field</code> objects representing all the
     * declared fields of this class
     * @exception SecurityException    if access to the information is denied.
     * @see       java.lang.reflect.Field
     * @see       SecurityManager#checkMemberAccess(Class, int)
     * @see       SecurityManager#checkPackageAccess(String)
     * @since JDK1.1
     */
    public Field[] getDeclaredFields() throws java.lang.SecurityException {
        return null;
    }

    /** 
     * Returns an array of <code>Method</code> objects reflecting all the
     * methods declared by the class or interface represented by this
     * <code>Class</code> object. This includes public, protected, default
     * (package) access, and private methods, but excludes inherited methods.
     * The elements in the array returned are not sorted and are not in any
     * particular order.  This method returns an array of length 0 if the class
     * or interface declares no methods, or if this <code>Class</code> object
     * represents a primitive type, an array class, or void.  The class
     * initialization method <code>&lt;clinit&gt;</code> is not included in the
     * returned array. If the class declares multiple public member methods
     * with the same parameter types, they are all included in the returned
     * array.
     *
     * <p> See <em>The Java Language Specification</em>, section 8.2.
     *
     * <p>If there is a security manager, this method first
     * calls the security manager's <code>checkMemberAccess</code> method
     * with <code>this</code> and <code>Member.DECLARED</code> 
     * as its arguments. If the class is in a package, then this method
     * also calls the security manager's <code>checkPackageAccess</code> method
     * with the package name as its argument. Either of these calls could
     * result in a SecurityException.
     *
     * @return    the array of <code>Method</code> objects representing all the
     * declared methods of this class
     * @exception SecurityException    if access to the information is denied.
     * @see       java.lang.reflect.Method
     * @see       SecurityManager#checkMemberAccess(Class, int)
     * @see       SecurityManager#checkPackageAccess(String)
     * @since JDK1.1
     */
    public Method[] getDeclaredMethods() throws java.lang.SecurityException {
        return null;
    }

    /** 
     * Returns an array of <code>Constructor</code> objects reflecting all the
     * constructors declared by the class represented by this
     * <code>Class</code> object. These are public, protected, default
     * (package) access, and private constructors.  The elements in the array
     * returned are not sorted and are not in any particular order.  If the
     * class has a default constructor, it is included in the returned array.
     * This method returns an array of length 0 if this <code>Class</code>
     * object represents an interface, a primitive type, an array class, or
     * void.
     *
     * <p> See <em>The Java Language Specification</em>, section 8.2.
     *
     * <p>If there is a security manager, this method first
     * calls the security manager's <code>checkMemberAccess</code> method
     * with <code>this</code> and <code>Member.DECLARED</code> 
     * as its arguments. If the class is in a package, then this method
     * also calls the security manager's <code>checkPackageAccess</code> method
     * with the package name as its argument. Either of these calls could
     * result in a SecurityException.
     *
     * @return    the array of <code>Method</code> objects representing all the
     * declared constructors of this class
     * @exception SecurityException    if access to the information is denied.
     * @see       java.lang.reflect.Constructor
     * @see       SecurityManager#checkMemberAccess(Class, int)
     * @see       SecurityManager#checkPackageAccess(String)
     * @since JDK1.1
     */
    public Constructor[] getDeclaredConstructors()
        throws java.lang.SecurityException
    {
        return null;
    }

    /** 
     * Returns a <code>Field</code> object that reflects the specified declared
     * field of the class or interface represented by this <code>Class</code>
     * object. The <code>name</code> parameter is a <code>String</code> that
     * specifies the simple name of the desired field.  Note that this method
     * will not reflect the <code>length</code> field of an array class.
     *
     * <p>If there is a security manager, this method first
     * calls the security manager's <code>checkMemberAccess</code> method
     * with <code>this</code> and <code>Member.DECLARED</code> 
     * as its arguments. If the class is in a package, then this method
     * also calls the security manager's <code>checkPackageAccess</code> method
     * with the package name as its argument. Either of these calls could
     * result in a SecurityException.
     *
     * @param name the name of the field
     * @return the <code>Field</code> object for the specified field in this
     * class
     * @exception NoSuchFieldException if a field with the specified name is
     *              not found.
     * @exception NullPointerException if <code>name</code> is <code>null</code>
     * @exception SecurityException    if access to the information is denied.
     * @see       java.lang.reflect.Field
     * @see       SecurityManager#checkMemberAccess(Class, int)
     * @see       SecurityManager#checkPackageAccess(String)
     * @since JDK1.1
     */
    public Field getDeclaredField(java.lang.String name)
        throws java.lang.NoSuchFieldException, java.lang.SecurityException
    {
        return null;
    }

    /** 
     * Returns a <code>Method</code> object that reflects the specified
     * declared method of the class or interface represented by this
     * <code>Class</code> object. The <code>name</code> parameter is a
     * <code>String</code> that specifies the simple name of the desired
     * method, and the <code>parameterTypes</code> parameter is an array of
     * <code>Class</code> objects that identify the method's formal parameter
     * types, in declared order.  If more than one method with the same
     * parameter types is declared in a class, and one of these methods has a
     * return type that is more specific than any of the others, that method is
     * returned; otherwise one of the methods is chosen arbitrarily.  If the
     * name is "&lt;init&gt;"or "&lt;clinit&gt;" a <code>NoSuchMethodException</code>
     * is raised.
     *
     * <p>If there is a security manager, this method first
     * calls the security manager's <code>checkMemberAccess</code> method
     * with <code>this</code> and <code>Member.DECLARED</code> 
     * as its arguments. If the class is in a package, then this method also
     * calls the security manager's <code>checkPackageAccess</code> method with
     * the package name as its argument. Either of these calls could result in
     * a SecurityException.
     *
     * @param name the name of the method
     * @param parameterTypes the parameter array
     * @return    the <code>Method</code> object for the method of this class
     * matching the specified name and parameters
     * @exception NoSuchMethodException if a matching method is not found.
     * @exception NullPointerException if <code>name</code> is <code>null</code>
     * @exception SecurityException     if access to the information is denied.
     * @see       java.lang.reflect.Method
     * @see       SecurityManager#checkMemberAccess(Class, int)
     * @see       SecurityManager#checkPackageAccess(String)
     * @since JDK1.1
     */
    public Method getDeclaredMethod(java.lang.String name, java.lang.Class[]
        parameterTypes)
        throws java.lang.NoSuchMethodException, java.lang.SecurityException
    {
        return null;
    }

    /** 
     * Returns a <code>Constructor</code> object that reflects the specified
     * constructor of the class or interface represented by this
     * <code>Class</code> object.  The <code>parameterTypes</code> parameter is
     * an array of <code>Class</code> objects that identify the constructor's
     * formal parameter types, in declared order.
     *
     * <p>If there is a security manager, this method first
     * calls the security manager's <code>checkMemberAccess</code> method
     * with <code>this</code> and <code>Member.DECLARED</code> 
     * as its arguments. If the class is in a package, then this method
     * also calls the security manager's <code>checkPackageAccess</code> 
     * method with the package name 
     * as its argument. Either of these calls could result in a SecurityException.
     *
     * @param parameterTypes the parameter array
     * @return    The <code>Method</code> object for the constructor with the
     * specified parameter list
     * @exception NoSuchMethodException if a matching method is not found.
     * @exception SecurityException     if access to the information is denied.
     * @see       java.lang.reflect.Constructor
     * @see       SecurityManager#checkMemberAccess(Class, int)
     * @see       SecurityManager#checkPackageAccess(String)
     * @since JDK1.1
     */
    public Constructor getDeclaredConstructor(java.lang.Class[] parameterTypes)
        throws java.lang.NoSuchMethodException, java.lang.SecurityException
    {
        return null;
    }

    /** 
     * Finds a resource with a given name.  This method returns null if no
     * resource with this name is found.  The rules for searching
     * resources associated with a given class are implemented by the
     * defining class loader of the class.
     *
     * <p> This method delegates the call to its class loader, after making
     * these changes to the resource name: if the resource name starts with
     * "/", it is unchanged; otherwise, the package name is prepended to the
     * resource name after converting "." to "/".  If this object was loaded by
     * the bootstrap loader, the call is delegated to
     * <code>ClassLoader.getSystemResourceAsStream</code>.
     *
     * @param name  name of the desired resource
     * @return      a <code>java.io.InputStream</code> object.
     * @throws NullPointerException if <code>name</code> is <code>null</code>.
     * @see         java.lang.ClassLoader
     * @since JDK1.1
     */
    public InputStream getResourceAsStream(java.lang.String name) {
        return null;
    }

    /** 
     * Finds a resource with a given name.  This method returns null if no
     * resource with this name is found.  The rules for searching resources
     * associated with a given class are implemented by the * defining class
     * loader of the class.
     *
     * <p> This method delegates the call to its class loader, after making
     * these changes to the resource name: if the resource name starts with
     * "/", it is unchanged; otherwise, the package name is prepended to the
     * resource name after converting "." to "/".  If this object was loaded by
     * the bootstrap loader, the call is delegated to
     * <code>ClassLoader.getSystemResource</code>.
     *
     * @param name  name of the desired resource
     * @return      a <code>java.net.URL</code> object.
     * @see         java.lang.ClassLoader
     * @since JDK1.1
     */
    public java.net.URL getResource(java.lang.String name) {
        return null;
    }

    /** 
     * Returns the <code>ProtectionDomain</code> of this class.  If there is a
     * security manager installed, this method first calls the security
     * manager's <code>checkPermission</code> method with a
     * <code>RuntimePermission("getProtectionDomain")</code> permission to
     * ensure it's ok to get the
     * <code>ProtectionDomain</code>.
     *
     * @return the ProtectionDomain of this class
     *
     * @throws SecurityException
     *        if a security manager exists and its 
     *        <code>checkPermission</code> method doesn't allow 
     *        getting the ProtectionDomain.
     *
     * @see java.security.ProtectionDomain
     * @see SecurityManager#checkPermission
     * @see java.lang.RuntimePermission
     * @since 1.2
     */
    public java.security.ProtectionDomain getProtectionDomain() {
        return null;
    }

    /** 
     * Returns the assertion status that would be assigned to this
     * class if it were to be initialized at the time this method is invoked.
     * If this class has had its assertion status set, the most recent
     * setting will be returned; otherwise, if any package default assertion
     * status pertains to this class, the most recent setting for the most
     * specific pertinent package default assertion status is returned;
     * otherwise, if this class is not a system class (i.e., it has a
     * class loader) its class loader's default assertion status is returned;
     * otherwise, the system class default assertion status is returned.
     * <p>
     * Few programmers will have any need for this method; it is provided
     * for the benefit of the JRE itself.  (It allows a class to determine at
     * the time that it is initialized whether assertions should be enabled.)
     * Note that this method is not guaranteed to return the actual
     * assertion status that was (or will be) associated with the specified
     * class when it was (or will be) initialized.
     *
     * @return the desired assertion status of the specified class.
     * @see    java.lang.ClassLoader#setClassAssertionStatus
     * @see    java.lang.ClassLoader#setPackageAssertionStatus
     * @see    java.lang.ClassLoader#setDefaultAssertionStatus
     * @since  1.4
     */
    public boolean desiredAssertionStatus() {
        return false;
    }
}
