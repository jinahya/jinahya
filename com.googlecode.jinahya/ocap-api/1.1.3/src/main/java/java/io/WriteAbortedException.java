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

/** 
 * Signals that one of the ObjectStreamExceptions was thrown during a
 * write operation.  Thrown during a read operation when one of the
 * ObjectStreamExceptions was thrown during a write operation.  The 
 * exception that terminated the write can be found in the detail
 * field. The stream is reset to it's initial state and all references
 * to objects already deserialized are discarded.
 *
 * <p>As of release 1.4, this exception has been retrofitted to conform to
 * the general purpose exception-chaining mechanism.  The "exception causing
 * the abort" that is provided at construction time and
 * accessed via the public {@link #detail} field is now known as the
 * <i>cause</i>, and may be accessed via the {@link Throwable#getCause()}
 * method, as well as the aforementioned "legacy field."
 *
 * @author  unascribed
 * @version 1.12, 02/02/00
 * @since   JDK1.1
 */
public class WriteAbortedException extends ObjectStreamException
{

    static final long serialVersionUID = -3326426625597282442L;

    /** 
     * Exception that was caught while writing the ObjectStream.
     *
     * <p>This field predates the general-purpose exception chaining facility.
     * The {@link Throwable#getCause()} method is now the preferred means of
     * obtaining this information.
     *
     * @serial
     */
    public Exception detail;

    /** 
     * Constructs a WriteAbortedException with a string describing
     * the exception and the exception causing the abort.
     * @param s   String describing the exception.
     * @param ex  Exception causing the abort.
     */
    public WriteAbortedException(String s, Exception ex) { }

    /** 
     * Produce the message and include the message from the nested
     * exception, if there is one.
     */
    public String getMessage() {
        return null;
    }

    /** 
     * Returns the exception that terminated the operation (the <i>cause</i>).
     *
     * @return  the exception that terminated the operation (the <i>cause</i>),
     *          which may be null.
     * @since   1.4
     */
    public Throwable getCause() {
        return null;
    }
}
