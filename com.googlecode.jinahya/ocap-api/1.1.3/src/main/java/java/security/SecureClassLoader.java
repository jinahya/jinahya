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


  


package java.security;

import java.util.HashMap;
import java.util.ArrayList;
import java.net.URL;

/** 
 * This class extends ClassLoader with additional support for defining
 * classes with an associated code source and permissions which are
 * retrieved by the system policy by default.
 *
 * @version 1.73, 02/02/00
 * @author  Li Gong 
 * @author  Roland Schemers
 */
public class SecureClassLoader extends ClassLoader
{

    /** 
     * Creates a new SecureClassLoader using the specified parent
     * class loader for delegation.
     *
     * <p>If there is a security manager, this method first
     * calls the security manager's <code>checkCreateClassLoader</code> 
     * method  to ensure creation of a class loader is allowed.
     * <p>
     * @param parent the parent ClassLoader
     * @exception  SecurityException  if a security manager exists and its  
     *             <code>checkCreateClassLoader</code> method doesn't allow 
     *             creation of a class loader.
     * @see SecurityManager#checkCreateClassLoader
     */
    protected SecureClassLoader(ClassLoader parent) { }

    /** 
     * Creates a new SecureClassLoader using the default parent class
     * loader for delegation.
     *
     * <p>If there is a security manager, this method first
     * calls the security manager's <code>checkCreateClassLoader</code> 
     * method  to ensure creation of a class loader is allowed.
     *
     * @exception  SecurityException  if a security manager exists and its  
     *             <code>checkCreateClassLoader</code> method doesn't allow 
     *             creation of a class loader.
     * @see SecurityManager#checkCreateClassLoader
     */
    protected SecureClassLoader() { }

    /** 
     * Converts an array of bytes into an instance of class Class,
     * with an optional CodeSource. Before the
     * class can be used it must be resolved.
     * <p>
     * If a non-null CodeSource is supplied a ProtectionDomain is
     * constructed and associated with the class being defined.
     * <p>
     * @param      name the expected name of the class, or <code>null</code>
     *                  if not known, using '.' and not '/' as the separator
     *                  and without a trailing ".class" suffix.
     * @param      b    the bytes that make up the class data. The bytes in 
     *             positions <code>off</code> through <code>off+len-1</code> 
     *             should have the format of a valid class file as defined 
     *             by the 
     *             <a href="http://java.sun.com/docs/books/vmspec/">Java 
     *             Virtual Machine Specification</a>.
     * @param      off  the start offset in <code>b</code> of the class data
     * @param      len  the length of the class data
     * @param      cs   the associated CodeSource, or <code>null</code> if none
     * @return the <code>Class</code> object created from the data,
     *         and optional CodeSource.
     * @exception  ClassFormatError if the data did not contain a valid class
     * @exception  IndexOutOfBoundsException if either <code>off</code> or 
     *             <code>len</code> is negative, or if 
     *             <code>off+len</code> is greater than <code>b.length</code>.
     *
     * @exception  SecurityException if an attempt is made to add this class
     *             to a package that contains classes that were signed by
     *             a different set of certificates than this class, or if 
     *             the class name begins with "java.".
     */
    protected final Class defineClass(String name, byte[] b, int off, int len,
        CodeSource cs)
    {
        return null;
    }

    /** 
     * Returns the permissions for the given CodeSource object.
     * <p>
     * This method is invoked by the defineClass method which takes
     * a CodeSource as an argument when it is constructing the
     * ProtectionDomain for the class being defined.
     * <p>
     * @param codesource the codesource.
     *
     * @return the permissions granted to the codesource.
     *
     */
    protected PermissionCollection getPermissions(CodeSource codesource) {
        return null;
    }
}
