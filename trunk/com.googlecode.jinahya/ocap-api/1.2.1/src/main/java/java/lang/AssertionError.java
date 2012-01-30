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
 * Thrown to indicate that an assertion has failed.
 *
 * <p>The seven one-argument public constructors provided by this
 * class ensure that the assertion error returned by the invocation:
 * <pre>
 *     new AssertionError(<i>expression</i>)
 * </pre>
 * has as its detail message the <i>string conversion</i> of
 * <i>expression</i> (as defined in <a
 * href="http://java.sun.com/docs/books/jls/second_edition/html/j.title.doc.html">
 * <i>The Java Language Specification, Second Edition</i></a>, 
 * <a href="http://java.sun.com/docs/books/jls/second_edition/html/expressions.doc.html#40220">
 * Section  15.18.1.1</a>), regardless of the type of <i>expression</i>.
 *
 * @version 1.7, 03/12/05
 * @since   JDK1.4
 */
public class AssertionError extends java.lang.Error
{

    /** 
     * Constructs an AssertionError with no detail message.
     */
    public AssertionError() { }

    /** 
     * Constructs an AssertionError with its detail message derived
     * from the specified object, which is converted to a string as
     * defined in <i>The Java Language Specification, Second
     * Edition</i>, Section 15.18.1.1.
     *<p>
     * If the specified object is an instance of <tt>Throwable</tt>, it
     * becomes the <i>cause</i> of the newly constructed assertion error.
     *
     * @param detailMessage value to be used in constructing detail message
     * @see   Throwable#getCause()
     */
    public AssertionError(java.lang.Object detailMessage) { }

    /** 
     * Constructs an AssertionError with its detail message derived
     * from the specified <code>boolean</code>, which is converted to
     * a string as defined in <i>The Java Language Specification,
     * Second Edition</i>, Section 15.18.1.1.
     *
     * @param detailMessage value to be used in constructing detail message
     */
    public AssertionError(boolean detailMessage) { }

    /** 
     * Constructs an AssertionError with its detail message derived
     * from the specified <code>char</code>, which is converted to a
     * string as defined in <i>The Java Language Specification, Second
     * Edition</i>, Section 15.18.1.1.
     *
     * @param detailMessage value to be used in constructing detail message
     */
    public AssertionError(char detailMessage) { }

    /** 
     * Constructs an AssertionError with its detail message derived
     * from the specified <code>int</code>, which is converted to a
     * string as defined in <i>The Java Language Specification, Second
     * Edition</i>, Section 15.18.1.1.
     *
     * @param detailMessage value to be used in constructing detail message
     */
    public AssertionError(int detailMessage) { }

    /** 
     * Constructs an AssertionError with its detail message derived
     * from the specified <code>long</code>, which is converted to a
     * string as defined in <i>The Java Language Specification, Second
     * Edition</i>, Section 15.18.1.1.
     *
     * @param detailMessage value to be used in constructing detail message
     */
    public AssertionError(long detailMessage) { }

    /** 
     * Constructs an AssertionError with its detail message derived
     * from the specified <code>float</code>, which is converted to a
     * string as defined in <i>The Java Language Specification, Second
     * Edition</i>, Section 15.18.1.1.
     *
     * @param detailMessage value to be used in constructing detail message
     */
    public AssertionError(float detailMessage) { }

    /** 
     * Constructs an AssertionError with its detail message derived
     * from the specified <code>double</code>, which is converted to a
     * string as defined in <i>The Java Language Specification, Second
     * Edition</i>, Section 15.18.1.1.
     *
     * @param detailMessage value to be used in constructing detail message
     */
    public AssertionError(double detailMessage) { }
}
