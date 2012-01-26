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
 * Thrown by a method invocation on a proxy instance if its invocation
 * handler's {@link InvocationHandler#invoke invoke} method throws a
 * checked exception (a <code>Throwable</code> that is not assignable
 * to <code>RuntimeException</code> or <code>Error</code>) that
 * is not assignable to any of the exception types declared in the
 * <code>throws</code> clause of the method that was invoked on the
 * proxy instance and dispatched to the invocation handler.
 *
 * <p>An <code>UndeclaredThrowableException</code> instance contains
 * the undeclared checked exception that was thrown by the invocation
 * handler, and it can be retrieved with the
 * <code>getUndeclaredThrowable()</code> method.
 * <code>UndeclaredThrowableException</code> extends
 * <code>RuntimeException</code>, so it is an unchecked exception
 * that wraps a checked exception.
 *
 * <p>As of release 1.4, this exception has been retrofitted to
 * conform to the general purpose exception-chaining mechanism.  The
 * "undeclared checked exception that was thrown by the invocation
 * handler" that may be provided at construction time and accessed via
 * the {@link #getUndeclaredThrowable()} method is now known as the
 * <i>cause</i>, and may be accessed via the {@link
 * Throwable#getCause()} method, as well as the aforementioned "legacy
 * method."
 *
 * @author	Peter Jones
 * @version	1.6, 00/02/02
 * @see		InvocationHandler
 * @since	JDK1.3
 */
public class UndeclaredThrowableException extends RuntimeException
{
    static final long serialVersionUID = 330127114055056639L;

    /** 
     * the undeclared checked exception that was thrown
     * @serial
     */
    private Throwable undeclaredThrowable;

    /** 
     * Constructs an <code>UndeclaredThrowableException</code> with the
     * specified <code>Throwable</code>.
     *
     * @param	undeclaredThrowable the undeclared checked exception
     *		that was thrown
     */
    public UndeclaredThrowableException(Throwable undeclaredThrowable) { }

    /** 
     * Constructs an <code>UndeclaredThrowableException</code> with the
     * specified <code>Throwable</code> and a detail message.
     *
     * @param	undeclaredThrowable the undeclared checked exception
     *		that was thrown
     * @param	s the detail message
     */
    public UndeclaredThrowableException(Throwable undeclaredThrowable, String s)
    { }

    /** 
     * Returns the <code>Throwable</code> instance wrapped in this
     * <code>UndeclaredThrowableException</code>, which may be <tt>null</tt>.
     *
     * <p>This method predates the general-purpose exception chaining facility.
     * The {@link Throwable#getCause()} method is now the preferred means of
     * obtaining this information.
     *
     * @return the undeclared checked exception that was thrown
     */
    public Throwable getUndeclaredThrowable() {
        return null;
    }

    /** 
     * Returns the the cause of this exception (the <code>Throwable</code>
     * instance wrapped in this <code>UndeclaredThrowableException</code>,
     * which may be <tt>null</tt>).
     *
     * @return  the cause of this exception.
     * @since   1.4
     */
    public Throwable getCause() {
        return null;
    }
}
