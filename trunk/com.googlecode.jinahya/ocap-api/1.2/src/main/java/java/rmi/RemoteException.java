/*
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
This work corresponds to the API signatures of JSR 217: Personal Basis
Profile 1.1. In the event of a discrepency between this work and the
JSR 217 specification, which is available at
http://www.jcp.org/en/jsr/detail?id=217, the latter takes precedence.
*/


  


package java.rmi;

/** 
 * A <code>RemoteException</code> is the common superclass for a number of
 * communication-related exceptions that may occur during the execution of a
 * remote method call.  Each method of a remote interface, an interface that
 * extends <code>java.rmi.Remote</code>, must list
 * <code>RemoteException</code> in its throws clause.
 *
 * <p>As of release 1.4, this exception has been retrofitted to conform to
 * the general purpose exception-chaining mechanism.  The "wrapped remote
 * exception" that may be provided at construction time and accessed via
 * the public {@link #detail} field is now known as the <i>cause</i>, and
 * may be accessed via the {@link Throwable#getCause()} method, as well as
 * the aforementioned "legacy field."
 *
 * @version 1.22, 01/23/03
 * @author  Ann Wollrath
 * @since   JDK1.1
 */
public class RemoteException extends java.io.IOException
{

    /* indicate compatibility with JDK 1.1.x version of class */
    private static final long serialVersionUID = -5148567311918794206L;

    /** 
     * Nested Exception to hold wrapped remote exception.
     *
     * <p>This field predates the general-purpose exception chaining facility.
     * The {@link Throwable#getCause()} method is now the preferred means of
     * obtaining this information.
     *
     * @serial
     */
    public Throwable detail;

    /** 
     * Constructs a <code>RemoteException</code> with no specified
     * detail message.
     */
    public RemoteException() { }

    /** 
     * Constructs a <code>RemoteException</code> with the specified
     * detail message.
     *
     * @param s the detail message
     */
    public RemoteException(String s) { }

    /** 
     * Constructs a <code>RemoteException</code> with the specified
     * detail message and nested exception.
     *
     * @param s the detail message
     * @param ex the nested exception
     */
    public RemoteException(String s, Throwable ex) { }

    /** 
     * Returns the detail message, including the message from the nested
     * exception if there is one.
     * 
     * @return	the detail message, including nested exception message if any
     */
    public String getMessage() { return null; }

    /** 
     * Returns the wrapped remote exception (the <i>cause</i>).
     *
     * @return  the wrapped remote exception, which may be <tt>null</tt>.
     * @since   1.4
     */
    public Throwable getCause() {return null;  }
}
