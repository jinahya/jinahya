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

import java.io.*;

import java.util.Properties;
import java.util.PropertyPermission;
import java.util.StringTokenizer;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.AllPermission;

/** 
 * The <code>System</code> class contains several useful class fields
 * and methods. It cannot be instantiated.
 * <p>
 * Among the facilities provided by the <code>System</code> class
 * are standard input, standard output, and error output streams;
 * access to externally defined "properties"; a means of
 * loading files and libraries; and a utility method for quickly
 * copying a portion of an array.
 *
 * @author  Arthur van Hoff
 * @version 1.114 05/18/01
 * @since   JDK1.0
 */
public final class System
{
    /** 
     * The "standard" input stream. This stream is already
     * open and ready to supply input data. Typically this stream
     * corresponds to keyboard input or another input source specified by
     * the host environment or user.
     */
    public static final InputStream in = null;

    /** 
     * The "standard" output stream. This stream is already
     * open and ready to accept output data. Typically this stream
     * corresponds to display output or another output destination
     * specified by the host environment or user.
     * <p>
     * For simple stand-alone Java applications, a typical way to write
     * a line of output data is:
     * <blockquote><pre>
     *     System.out.println(data)
     * </pre></blockquote>
     * <p>
     * See the <code>println</code> methods in class <code>PrintStream</code>.
     *
     * @see     java.io.PrintStream#println()
     * @see     java.io.PrintStream#println(boolean)
     * @see     java.io.PrintStream#println(char)
     * @see     java.io.PrintStream#println(char[])
     * @see     java.io.PrintStream#println(double)
     * @see     java.io.PrintStream#println(float)
     * @see     java.io.PrintStream#println(int)
     * @see     java.io.PrintStream#println(long)
     * @see     java.io.PrintStream#println(java.lang.Object)
     * @see     java.io.PrintStream#println(java.lang.String)
     */
    public static final PrintStream out = null;

    /** 
     * The "standard" error output stream. This stream is already
     * open and ready to accept output data.
     * <p>
     * Typically this stream corresponds to display output or another
     * output destination specified by the host environment or user. By
     * convention, this output stream is used to display error messages
     * or other information that should come to the immediate attention
     * of a user even if the principal output stream, the value of the
     * variable <code>out</code>, has been redirected to a file or other
     * destination that is typically not continuously monitored.
     */
    public static final PrintStream err = null;

    /*
     * This hidden constructor does not necessarily correspond to
     * a constructor in the original source file -- it keeps javadoc
     * from generating an inappropriate default constructor.
     */
    private System() { }

    /** 
     * Reassigns the "standard" input stream.
     *
     * <p>First, if there is a security manager, its <code>checkPermission</code>
     * method is called with a <code>RuntimePermission("setIO")</code> permission
     *  to see if it's ok to reassign the "standard" input stream.
     * <p>
     *
     * @param in the new standard input stream.
     *
     * @throws SecurityException
     *        if a security manager exists and its
     *        <code>checkPermission</code> method doesn't allow
     *        reassigning of the standard input stream.
     *
     * @see SecurityManager#checkPermission
     * @see java.lang.RuntimePermission
     *
     * @since   JDK1.1
     */
    public static void setIn(InputStream in) { }

    /** 
     * Reassigns the "standard" output stream.
     *
     * <p>First, if there is a security manager, its <code>checkPermission</code>
     * method is called with a <code>RuntimePermission("setIO")</code> permission
     *  to see if it's ok to reassign the "standard" output stream.
     *
     * @param out the new standard output stream
     *
     * @throws SecurityException
     *        if a security manager exists and its
     *        <code>checkPermission</code> method doesn't allow
     *        reassigning of the standard output stream.
     *
     * @see SecurityManager#checkPermission
     * @see java.lang.RuntimePermission
     *
     * @since   JDK1.1
     */
    public static void setOut(PrintStream out) { }

    /** 
     * Reassigns the "standard" error output stream.
     *
     * <p>First, if there is a security manager, its <code>checkPermission</code>
     * method is called with a <code>RuntimePermission("setIO")</code> permission
     *  to see if it's ok to reassign the "standard" error output stream.
     *
     * @param err the new standard error output stream.
     *
     * @throws SecurityException
     *        if a security manager exists and its
     *        <code>checkPermission</code> method doesn't allow
     *        reassigning of the standard error output stream.
     *
     * @see SecurityManager#checkPermission
     * @see java.lang.RuntimePermission
     *
     * @since   JDK1.1
     */
    public static void setErr(PrintStream err) { }

    /** 
     * Sets the System security.
     *
     * <p> If there is a security manager already installed, this method first
     * calls the security manager's <code>checkPermission</code> method
     * with a <code>RuntimePermission("setSecurityManager")</code>
     * permission to ensure it's ok to replace the existing
     * security manager.
     * This may result in throwing a <code>SecurityException</code>.
     *
     * <p> Otherwise, the argument is established as the current
     * security manager. If the argument is <code>null</code> and no
     * security manager has been established, then no action is taken and
     * the method simply returns.
     *
     * @param      s   the security manager.
     * @exception  SecurityException  if the security manager has already
     *             been set and its <code>checkPermission</code> method
     *             doesn't allow it to be replaced.
     * @see #getSecurityManager
     * @see SecurityManager#checkPermission
     * @see java.lang.RuntimePermission
     */
    public static void setSecurityManager(java.lang.SecurityManager s) { }

    /** 
     * Gets the system security interface.
     *
     * @return  if a security manager has already been established for the
     *          current application, then that security manager is returned;
     *          otherwise, <code>null</code> is returned.
     * @see     #setSecurityManager
     */
    public static java.lang.SecurityManager getSecurityManager() {
        return null;
    }

    /** 
     * Returns the current time in milliseconds.  Note that
     * while the unit of time of the return value is a millisecond,
     * the granularity of the value depends on the underlying
     * operating system and may be larger.  For example, many
     * operating systems measure time in units of tens of
     * milliseconds.
     *
     * <p> See the description of the class <code>Date</code> for
     * a discussion of slight discrepancies that may arise between
     * "computer time" and coordinated universal time (UTC).
     *
     * @return  the difference, measured in milliseconds, between
     *          the current time and midnight, January 1, 1970 UTC.
     * @see     java.util.Date
     */
    public static long currentTimeMillis() {
        return -1;
    }

    /** 
     * Copies an array from the specified source array, beginning at the
     * specified position, to the specified position of the destination array.
     * A subsequence of array components are copied from the source
     * array referenced by <code>src</code> to the destination array
     * referenced by <code>dest</code>. The number of components copied is
     * equal to the <code>length</code> argument. The components at
     * positions <code>srcPos</code> through
     * <code>srcPos+length-1</code> in the source array are copied into
     * positions <code>destPos</code> through
     * <code>destPos+length-1</code>, respectively, of the destination
     * array.
     * <p>
     * If the <code>src</code> and <code>dest</code> arguments refer to the
     * same array object, then the copying is performed as if the
     * components at positions <code>srcPos</code> through
     * <code>srcPos+length-1</code> were first copied to a temporary
     * array with <code>length</code> components and then the contents of
     * the temporary array were copied into positions
     * <code>destPos</code> through <code>destPos+length-1</code> of the
     * destination array.
     * <p>
     * If <code>dest</code> is <code>null</code>, then a
     * <code>NullPointerException</code> is thrown.
     * <p>
     * If <code>src</code> is <code>null</code>, then a
     * <code>NullPointerException</code> is thrown and the destination
     * array is not modified.
     * <p>
     * Otherwise, if any of the following is true, an
     * <code>ArrayStoreException</code> is thrown and the destination is
     * not modified:
     * <ul>
     * <li>The <code>src</code> argument refers to an object that is not an
     *     array.
     * <li>The <code>dest</code> argument refers to an object that is not an
     *     array.
     * <li>The <code>src</code> argument and <code>dest</code> argument refer
     *     to arrays whose component types are different primitive types.
     * <li>The <code>src</code> argument refers to an array with a primitive
     *    component type and the <code>dest</code> argument refers to an array
     *     with a reference component type.
     * <li>The <code>src</code> argument refers to an array with a reference
     *    component type and the <code>dest</code> argument refers to an array
     *     with a primitive component type.
     * </ul>
     * <p>
     * Otherwise, if any of the following is true, an
     * <code>IndexOutOfBoundsException</code> is
     * thrown and the destination is not modified:
     * <ul>
     * <li>The <code>srcPos</code> argument is negative.
     * <li>The <code>destPos</code> argument is negative.
     * <li>The <code>length</code> argument is negative.
     * <li><code>srcPos+length</code> is greater than
     *     <code>src.length</code>, the length of the source array.
     * <li><code>destPos+length</code> is greater than
     *     <code>dest.length</code>, the length of the destination array.
     * </ul>
     * <p>
     * Otherwise, if any actual component of the source array from
     * position <code>srcPos</code> through
     * <code>srcPos+length-1</code> cannot be converted to the component
     * type of the destination array by assignment conversion, an
     * <code>ArrayStoreException</code> is thrown. In this case, let
     * <b><i>k</i></b> be the smallest nonnegative integer less than
     * length such that <code>src[srcPos+</code><i>k</i><code>]</code>
     * cannot be converted to the component type of the destination
     * array; when the exception is thrown, source array components from
     * positions <code>srcPos</code> through
     * <code>srcPos+</code><i>k</i><code>-1</code>
     * will already have been copied to destination array positions
     * <code>destPos</code> through
     * <code>destPos+</code><i>k</I><code>-1</code> and no other
     * positions of the destination array will have been modified.
     * (Because of the restrictions already itemized, this
     * paragraph effectively applies only to the situation where both
     * arrays have component types that are reference types.)
     *
     * @param      src      the source array.
     * @param      srcPos   starting position in the source array.
     * @param      dest     the destination array.
     * @param      destPos  starting position in the destination data.
     * @param      length   the number of array elements to be copied.
     * @exception  IndexOutOfBoundsException  if copying would cause
     *               access of data outside array bounds.
     * @exception  ArrayStoreException  if an element in the <code>src</code>
     *               array could not be stored into the <code>dest</code> array
     *               because of a type mismatch.
     * @exception  NullPointerException if either <code>src</code> or
     *               <code>dest</code> is <code>null</code>.
     */
    public static void arraycopy(java.lang.Object src, int srcPos,
        java.lang.Object dest, int destPos, int length)
    { }

    /** 
     * Returns the same hash code for the given object as
     * would be returned by the default method hashCode(),
     * whether or not the given object's class overrides
     * hashCode().
     * The hash code for the null reference is zero.
     *
     * @param x object for which the hashCode is to be calculated
     * @return  the hashCode
     * @since   JDK1.1
     */
    public static int identityHashCode(java.lang.Object x) {
        return 0;
    }

    /** 
     * Determines the current system properties. 
     * <p>
     * First, if there is a security manager, its
     * <code>checkPropertiesAccess</code> method is called with no
     * arguments. This may result in a security exception.
     * <p>
     * The current set of system properties for use by the 
     * {@link #getProperty(String)} method is returned as a 
     * <code>Properties</code> object. If there is no current set of 
     * system properties, a set of system properties is first created and 
     * initialized. This set of system properties always includes values 
     * for the following keys: 
     * <table summary="Shows property keys and associated values">
     * <tr><th>Key</th>
     *     <th>Description of Associated Value</th></tr>
     * <tr><td><code>java.version</code></td>
     *     <td>Java Runtime Environment version</td></tr>
     * <tr><td><code>java.vendor</code></td>
     *     <td>Java Runtime Environment vendor</td></tr
     * <tr><td><code>java.vendor.url</code></td>
     *     <td>Java vendor URL</td></tr>
     * <tr><td><code>java.home</code></td>
     *     <td>Java installation directory</td></tr>
     * <tr><td><code>java.vm.specification.version</code></td>
     *     <td>Java Virtual Machine specification version</td></tr>
     * <tr><td><code>java.vm.specification.vendor</code></td>
     *     <td>Java Virtual Machine specification vendor</td></tr>
     * <tr><td><code>java.vm.specification.name</code></td>
     *     <td>Java Virtual Machine specification name</td></tr>
     * <tr><td><code>java.vm.version</code></td>
     *     <td>Java Virtual Machine implementation version</td></tr>
     * <tr><td><code>java.vm.vendor</code></td>
     *     <td>Java Virtual Machine implementation vendor</td></tr>
     * <tr><td><code>java.vm.name</code></td>
     *     <td>Java Virtual Machine implementation name</td></tr>
     * <tr><td><code>java.specification.version</code></td>
     *     <td>Java Runtime Environment specification  version</td></tr>
     * <tr><td><code>java.specification.vendor</code></td>
     *     <td>Java Runtime Environment specification  vendor</td></tr>
     * <tr><td><code>java.specification.name</code></td>
     *     <td>Java Runtime Environment specification  name</td></tr>
     * <tr><td><code>java.class.version</code></td>
     *     <td>Java class format version number</td></tr>
     * <tr><td><code>java.class.path</code></td>
     *     <td>Java class path</td></tr>
     * <tr><td><code>java.library.path</code></td>
     *     <td>List of paths to search when loading libraries</td></tr>
     * <tr><td><code>java.io.tmpdir</code></td>
     *     <td>Default temp file path</td></tr>
     * <tr><td><code>java.compiler</code></td>
     *     <td>Name of JIT compiler to use</td></tr>
     * <tr><td><code>java.ext.dirs</code></td>
     *     <td>Path of extension directory or directories</td></tr>
     * <tr><td><code>os.name</code></td>
     *     <td>Operating system name</td></tr>
     * <tr><td><code>os.arch</code></td>
     *     <td>Operating system architecture</td></tr>
     * <tr><td><code>os.version</code></td>
     *     <td>Operating system version</td></tr>
     * <tr><td><code>file.separator</code></td>
     *     <td>File separator ("/" on UNIX)</td></tr>
     * <tr><td><code>path.separator</code></td>
     *     <td>Path separator (":" on UNIX)</td></tr>
     * <tr><td><code>line.separator</code></td>
     *     <td>Line separator ("\n" on UNIX)</td></tr>
     * <tr><td><code>user.name</code></td>
     *     <td>User's account name</td></tr>
     * <tr><td><code>user.home</code></td>
     *     <td>User's home directory</td></tr>
     * <tr><td><code>user.dir</code></td>
     *     <td>User's current working directory</td></tr>
     * </table>
     * <p>
     * Multiple paths in a system property value are separated by the path
     * separator character of the platform.
     * <p>
     * Note that even if the security manager does not permit the
     * <code>getProperties</code> operation, it may choose to permit the
     * {@link #getProperty(String)} operation.
     *
     * @return     the system properties
     * @exception  SecurityException  if a security manager exists and its
     *             <code>checkPropertiesAccess</code> method doesn't allow access
     *              to the system properties.
     * @see        #setProperties
     * @see        java.lang.SecurityException
     * @see        java.lang.SecurityManager#checkPropertiesAccess()
     * @see        java.util.Properties
     */
    public static Properties getProperties() {
        return null;
    }

    /** 
     * Sets the system properties to the <code>Properties</code>
     * argument.
     * <p>
     * First, if there is a security manager, its
     * <code>checkPropertiesAccess</code> method is called with no
     * arguments. This may result in a security exception.
     * <p>
     * The argument becomes the current set of system properties for use
     * by the {@link #getProperty(String)} method. If the argument is
     * <code>null</code>, then the current set of system properties is
     * forgotten.
     *
     * @param      props   the new system properties.
     * @exception  SecurityException  if a security manager exists and its
     *             <code>checkPropertiesAccess</code> method doesn't allow access
     *              to the system properties.
     * @see        #getProperties
     * @see        java.util.Properties
     * @see        java.lang.SecurityException
     * @see        java.lang.SecurityManager#checkPropertiesAccess()
     */
    public static void setProperties(Properties props) { }

    /** 
     * Gets the system property indicated by the specified key.
     * <p>
     * First, if there is a security manager, its
     * <code>checkPropertyAccess</code> method is called with the key as
     * its argument. This may result in a SecurityException.
     * <p>
     * If there is no current set of system properties, a set of system
     * properties is first created and initialized in the same manner as
     * for the <code>getProperties</code> method.
     *
     * @param      key   the name of the system property.
     * @return     the string value of the system property,
     *             or <code>null</code> if there is no property with that key.
     *
     * @exception  SecurityException  if a security manager exists and its
     *             <code>checkPropertyAccess</code> method doesn't allow
     *              access to the specified system property.
     * @exception  NullPointerException if <code>key</code> is
     *             <code>null</code>.
     * @exception  IllegalArgumentException if <code>key</code> is empty.
     * @see        #setProperty
     * @see        java.lang.SecurityException
     * @see        java.lang.SecurityManager#checkPropertyAccess(java.lang.String)
     * @see        java.lang.System#getProperties()
     */
    public static java.lang.String getProperty(java.lang.String key) {
        return null;
    }

    /** 
     * Gets the system property indicated by the specified key.
     * <p>
     * First, if there is a security manager, its
     * <code>checkPropertyAccess</code> method is called with the
     * <code>key</code> as its argument.
     * <p>
     * If there is no current set of system properties, a set of system
     * properties is first created and initialized in the same manner as
     * for the <code>getProperties</code> method.
     *
     * @param      key   the name of the system property.
     * @param      def   a default value.
     * @return     the string value of the system property,
     *             or the default value if there is no property with that key.
     *
     * @exception  SecurityException  if a security manager exists and its
     *             <code>checkPropertyAccess</code> method doesn't allow
     *             access to the specified system property.
     * @exception  NullPointerException if <code>key</code> is
     *             <code>null</code>.
     * @exception  IllegalArgumentException if <code>key</code> is empty.
     * @see        #setProperty
     * @see        java.lang.SecurityManager#checkPropertyAccess(java.lang.String)
     * @see        java.lang.System#getProperties()
     */
    public static java.lang.String getProperty(java.lang.String key,
        java.lang.String def)
    {
        return null;
    }

    /** 
     * Sets the system property indicated by the specified key.
     * <p>
     * First, if a security manager exists, its
     * <code>SecurityManager.checkPermission</code> method
     * is called with a <code>PropertyPermission(key, "write")</code>
     * permission. This may result in a SecurityException being thrown.
     * If no exception is thrown, the specified property is set to the given
     * value.
     * <p>
     *
     * @param      key   the name of the system property.
     * @param      value the value of the system property.
     * @return     the previous value of the system property,
     *             or <code>null</code> if it did not have one.
     *
     * @exception  SecurityException  if a security manager exists and its
     *             <code>checkPermission</code> method doesn't allow
     *             setting of the specified property.
     * @exception  NullPointerException if <code>key</code> is
     *             <code>null</code>.
     * @exception  IllegalArgumentException if <code>key</code> is empty.
     * @see        #getProperty
     * @see        java.lang.System#getProperty(java.lang.String)
     * @see        java.lang.System#getProperty(java.lang.String, java.lang.String)
     * @see        java.util.PropertyPermission
     * @see        SecurityManager#checkPermission
     * @since      1.2
     */
    public static java.lang.String setProperty(java.lang.String key,
        java.lang.String value)
    {
        return null;
    }

    /** 
     * Terminates the currently running Java Virtual Machine. The
     * argument serves as a status code; by convention, a nonzero status
     * code indicates abnormal termination.
     * <p>
     * This method calls the <code>exit</code> method in class
     * <code>Runtime</code>. This method never returns normally.
     * <p>
     * The call <code>System.exit(n)</code> is effectively equivalent to
     * the call:
     * <blockquote><pre>
     * Runtime.getRuntime().exit(n)
     * </pre></blockquote>
     * <p>
     * If process model exists, System.exit() must rely on the process 
     * exiting to release resources.
     * <p>
     * If process model does not exist, and a security manager exists,
     * the security manager by default must prohibit calls to System.exit()
     * by throwing SecurityException.  Otherwise, the system will hang 
     * indefinitely when called,
     * <p>
     * @param      status   exit status.
     * @throws  SecurityException
     *        if a security manager exists and its <code>checkExit</code>
     *        method doesn't allow exit with the specified status.
     * @see        java.lang.Runtime#exit(int)
     */
    public static void exit(int status) { }

    /** 
     * Runs the garbage collector.
     * <p>
     * Calling the <code>gc</code> method suggests that the Java Virtual
     * Machine expend effort toward recycling unused objects in order to
     * make the memory they currently occupy available for quick reuse.
     * When control returns from the method call, the Java Virtual
     * Machine has made a best effort to reclaim space from all discarded
     * objects.
     * <p>
     * The call <code>System.gc()</code> is effectively equivalent to the
     * call:
     * <blockquote><pre>
     * Runtime.getRuntime().gc()
     * </pre></blockquote>
     *
     * @see     java.lang.Runtime#gc()
     */
    public static void gc() { }

    /** 
     * Runs the finalization methods of any objects pending finalization.
     * <p>
     * Calling this method suggests that the Java Virtual Machine expend
     * effort toward running the <code>finalize</code> methods of objects
     * that have been found to be discarded but whose <code>finalize</code>
     * methods have not yet been run. When control returns from the
     * method call, the Java Virtual Machine has made a best effort to
     * complete all outstanding finalizations.
     * <p>
     * The call <code>System.runFinalization()</code> is effectively
     * equivalent to the call:
     * <blockquote><pre>
     * Runtime.getRuntime().runFinalization()
     * </pre></blockquote>
     *
     * @see     java.lang.Runtime#runFinalization()
     */
    public static void runFinalization() { }

    /** 
     * Loads a code file with the specified filename from the local file
     * system as a dynamic library. The filename
     * argument must be a complete path name.
     * <p>
     * The call <code>System.load(name)</code> is effectively equivalent
     * to the call:
     * <blockquote><pre>
     * Runtime.getRuntime().load(name)
     * </pre></blockquote>
     *
     * @param      filename   the file to load.
     * @exception  SecurityException  if a security manager exists and its
     *             <code>checkLink</code> method doesn't allow
     *             loading of the specified dynamic library
     * @exception  UnsatisfiedLinkError  if the file does not exist.
     * @see        java.lang.Runtime#load(java.lang.String)
     * @see        java.lang.SecurityManager#checkLink(java.lang.String)
     */
    public static void load(java.lang.String filename) { }

    /** 
     * Loads the system library specified by the <code>libname</code>
     * argument. The manner in which a library name is mapped to the
     * actual system library is system dependent.
     * <p>
     * The call <code>System.loadLibrary(name)</code> is effectively
     * equivalent to the call
     * <blockquote><pre>
     * Runtime.getRuntime().loadLibrary(name)
     * </pre></blockquote>
     *
     * @param      libname   the name of the library.
     * @exception  SecurityException  if a security manager exists and its
     *             <code>checkLink</code> method doesn't allow
     *             loading of the specified dynamic library
     * @exception  UnsatisfiedLinkError  if the library does not exist.
     * @see        java.lang.Runtime#loadLibrary(java.lang.String)
     * @see        java.lang.SecurityManager#checkLink(java.lang.String)
     */
    public static void loadLibrary(java.lang.String libname) { }

    /** 
     * Maps a library name into a platform-specific string representing
     * a native library.
     *
     * @param      libname the name of the library.
     * @return     a platform-dependent native library name.
     * @see        java.lang.System#loadLibrary(java.lang.String)
     * @see        java.lang.ClassLoader#findLibrary(java.lang.String)
     * @since      1.2
     */
    public static java.lang.String mapLibraryName(java.lang.String
        libname)
    {
        return null;
    }
}
