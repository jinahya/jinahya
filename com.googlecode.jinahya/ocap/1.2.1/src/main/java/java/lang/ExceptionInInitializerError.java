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
 * Signals that an unexpected exception has occurred in a static initializer. 
 * An <code>ExceptionInInitializerError</code> is thrown to indicate that an 
 * exception occurred during evaluation of a static initializer or the
 * initializer for a static variable.
 *
 * <p>As of release 1.4, this exception has been retrofitted to conform to
 * the general purpose exception-chaining mechanism.  The "saved throwable
 * object" that may be provided at construction time and accessed via
 * the {@link #getException()} method is now known as the <i>cause</i>,
 * and may be accessed via the {@link Throwable#getCause()} method, as well
 * as the aforementioned "legacy method."
 *
 * @author  Frank Yellin
 * @version 1.11, 02/02/00
 *
 * @since   JDK1.1
 */
public class ExceptionInInitializerError extends java.lang.LinkageError
{
    /**
     * Use serialVersionUID from JDK 1.1.X for interoperability
     */
    private static final long serialVersionUID = 1521711792217232256L;

    /** 
     * This field holds the exception if the 
     * ExceptionInInitializerError(Throwable thrown) constructor was
     * used to instantiate the object
     * 
     * @serial 
     * 
     */
    private java.lang.Throwable exception;

    /** 
     * Constructs an <code>ExceptionInInitializerError</code> with 
     * <code>null</code> as its detail message string and with no saved
     * throwable object.
     * A detail message is a String that describes this particular exception.
     */
    public ExceptionInInitializerError() { }

    /** 
     * Constructs a new <code>ExceptionInInitializerError</code> class by 
     * saving a reference to the <code>Throwable</code> object thrown for 
     * later retrieval by the {@link #getException()} method. The detail 
     * message string is set to <code>null</code>.
     *
     * @param thrown The exception thrown
     */
    public ExceptionInInitializerError(java.lang.Throwable thrown) { }

    /** 
     * Constructs an ExceptionInInitializerError with the specified detail 
     * message string.  A detail message is a String that describes this 
     * particular exception. The detail message string is saved for later 
     * retrieval by the {@link Throwable#getMessage()} method. There is no 
     * saved throwable object. 
     *
     *
     * @param s the detail message
     */
    public ExceptionInInitializerError(java.lang.String s) { }

    /** 
     * Returns the exception that occurred during a static initialization that
     * caused this error to be created.
     *
     * <p>This method predates the general-purpose exception chaining facility.
     * The {@link Throwable#getCause()} method is now the preferred means of
     * obtaining this information.
     * 
     * @return the saved throwable object of this 
     *         <code>ExceptionInInitializerError</code>, or <code>null</code> 
     *         if this <code>ExceptionInInitializerError</code> has no saved 
     *         throwable object. 
     */
    public java.lang.Throwable getException() {
        return null;
    }

    /** 
     * Returns the cause of this error (the exception that occurred
     * during a static initialization that caused this error to be created).
     * 
     * @return  the cause of this error or <code>null</code> if the
     *          cause is nonexistent or unknown.
     * @since   1.4
     */
    public java.lang.Throwable getCause() {
        return null;
    }
}
