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
 * An element in a stack trace, as returned by {@link
 * Throwable#getStackTrace()}.  Each element represents a single stack frame.
 * All stack frames except for the one at the top of the stack represent
 * a method invocation.  The frame at the top of the stack represents the 
 * the execution point at which the stack trace was generated.  Typically,
 * this is the point at which the throwable corresponding to the stack trace
 * was created.
 *
 * @since  1.4
 * @author Josh Bloch
 */
public final class StackTraceElement implements java.io.Serializable
{
    private java.lang.String declaringClass;

    private java.lang.String methodName;

    private java.lang.String fileName;

    private int lineNumber;

    private boolean isCompiled;

    private java.lang.String methodSignature;

    /*
     * This hidden constructor does not necessarily correspond to
     * a constructor in the original source file -- it keeps javadoc
     * from generating an inappropriate default constructor.
     */
    private StackTraceElement() { }

    /** 
     * Returns the name of the source file containing the execution point
     * represented by this stack trace element.  Generally, this corresponds
     * to the <tt>SourceFile</tt> attribute of the relevant <tt>class</tt>
     * file (as per <i>The Java Virtual Machine Specification</i>, Section
     * 4.7.7).  In some systems, the name may refer to some source code unit
     * other than a file, such as an entry in source repository.
     *
     * @return the name of the file containing the execution point
     *         represented by this stack trace element, or <tt>null</tt> if
     *         this information is unavailable.
     */
    public java.lang.String getFileName() {
        return null;
    }

    /** 
     * Returns the line number of the source line containing the execution
     * point represented by this stack trace element.  Generally, this is
     * derived from the <tt>LineNumberTable</tt> attribute of the relevant
     * <tt>class</tt> file (as per <i>The Java Virtual Machine
     * Specification</i>, Section 4.7.8).
     *
     * @return the line number of the source line containing the execution
     *         point represented by this stack trace element, or a negative
     *         number if this information is unavailable.
     */
    public int getLineNumber() {
        return 0;
    }

    /** 
     * Returns the fully qualified name of the class containing the
     * execution point represented by this stack trace element.
     *
     * @return the fully qualified name of the <tt>Class</tt> containing
     *         the execution point represented by this stack trace element.
     */
    public java.lang.String getClassName() {
        return null;
    }

    /** 
     * Returns the name of the method containing the execution point
     * represented by this stack trace element.  If the execution point is
     * contained in an instance or class initializer, this method will return
     * the appropriate <i>special method name</i>, <tt>&lt;init&gt;</tt> or
     * <tt>&lt;clinit&gt;</tt>, as per Section 3.9 of <i>The Java Virtual
     * Machine Specification</i>.
     *
     * @return the name of the method containing the execution point
     *         represented by this stack trace element.
     */
    public java.lang.String getMethodName() {
        return null;
    }

    /** 
     * Returns true if the method containing the execution point
     * represented by this stack trace element is a native method.
     *
     * @return <tt>true</tt> if the method containing the execution point
     *         represented by this stack trace element is a native method.
     */
    public boolean isNativeMethod() {
        return false;
    }

    /** 
     * Returns a string representation of this stack trace element.  The
     * format of this string depends on the implementation, but the following
     * examples may be regarded as typical:
     * <ul>
     * <li>
     *   <tt>"MyClass.mash(MyClass.java:9)"</tt> - Here, <tt>"MyClass"</tt>
     *   is the <i>fully-qualified name</i> of the class containing the
     *   execution point represented by this stack trace element,
     *   <tt>"mash"</tt> is the name of the method containing the execution
     *   point, <tt>"MyClass.java"</tt> is the source file containing the
     *   execution point, and <tt>"9"</tt> is the line number of the source
     *   line containing the execution point.
     * <li>
     *   <tt>"MyClass.mash(MyClass.java)"</tt> - As above, but the line
     *   number is unavailable.
     * <li>
     *   <tt>"MyClass.mash(Unknown Source)"</tt> - As above, but neither
     *   the file name nor the line  number are available.
     * <li>
     *   <tt>"MyClass.mash(Native Method)"</tt> - As above, but neither
     *   the file name nor the line  number are available, and the method
     *   containing the execution point is known to be a native method.
     * </ul>
     * @see    Throwable#printStackTrace()
     */
    public java.lang.String toString() {
        return null;
    }

    /** 
     * Returns true if the specified object is another
     * <tt>StackTraceElement</tt> instance representing the same execution
     * point as this instance.  Two stack trace elements <tt>a</tt> and
     * <tt>b</tt> are equal if and only if:
     * <pre>
     *     equals(a.getFileName(), b.getFileName()) &&
     *     a.getLineNumber() == b.getLineNumber()) &&
     *     equals(a.getClassName(), b.getClassName()) &&
     *     equals(a.getMethodName(), b.getMethodName())
     * </pre>
     * where <tt>equals</tt> is defined as:
     * <pre>
     *     static boolean equals(Object a, Object b) {
     *         return a==b || (a != null && a.equals(b));
     *     }
     * </pre>
     * 
     * @param  obj the object to be compared with this stack trace element.
     * @return true if the specified object is another
     *         <tt>StackTraceElement</tt> instance representing the same
     *         execution point as this instance.
     */
    public boolean equals(java.lang.Object obj) {
        return false;
    }

    /** 
     * Returns a hash code value for this stack trace element.
     */
    public int hashCode() {
        return 0;
    }

    private static final long serialVersionUID = 6992337162326171013L;
}
