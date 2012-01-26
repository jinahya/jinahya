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

import java.lang.ref.*;

/** 
 * This class provides thread-local variables.  These variables differ from
 * their normal counterparts in that each thread that accesses one (via its
 * <tt>get</tt> or <tt>set</tt> method) has its own, independently initialized
 * copy of the variable.  <tt>ThreadLocal</tt> instances are typically private
 * static fields in classes that wish to associate state with a thread (e.g.,
 * a user ID or Transaction ID).
 *
 * <p>For example, in the class below, the private static <tt>ThreadLocal</tt>
 * instance (<tt>serialNum</tt>) maintains a "serial number" for each thread
 * that invokes the class's static <tt>SerialNum.get()</tt> method, which
 * returns the current thread's serial number.  (A thread's serial number is
 * assigned the first time it invokes <tt>SerialNum.get()</tt>, and remains
 * unchanged on subsequent calls.)
 * <pre>
 * public class SerialNum {
 *     // The next serial number to be assigned
 *     private static int nextSerialNum = 0;
 * 
 *     private static ThreadLocal serialNum = new ThreadLocal() {
 *         protected synchronized Object initialValue() {
 *             return new Integer(nextSerialNum++);
 *         }
 *     };
 * 
 *     public static int get() {
 *         return ((Integer) (serialNum.get())).intValue();
 *     }
 * }
 * </pre>
 * 
 * <p>Each thread holds an implicit reference to its copy of a thread-local
 * variable as long as the thread is alive and the <tt>ThreadLocal</tt>
 * instance is accessible; after a thread goes away, all of its copies of
 * thread-local instances are subject to garbage collection (unless other
 * references to these copies exist). 
 *
 * @author  Josh Bloch and Doug Lea
 * @version 1.21, 03/12/05
 * @since   1.2
 */
public class ThreadLocal
{

    public ThreadLocal() { }

    /** 
     * Returns the current thread's initial value for this thread-local
     * variable.  This method will be invoked at most once per accessing
     * thread for each thread-local, the first time the thread accesses the
     * variable with the {@link #get()} method.  The <tt>initialValue</tt>
     * method will not be invoked in a thread if the thread invokes the {@link
     * #set(Object)} method prior to the <tt>get</tt> method.
     *
     * <p>This implementation simply returns <tt>null</tt>; if the programmer
     * desires thread-local variables to be initialized to some value other
     * than <tt>null</tt>, <tt>ThreadLocal</tt> must be subclassed, and this
     * method overridden.  Typically, an anonymous inner class will be used.
     * Typical implementations of <tt>initialValue</tt> will invoke an
     * appropriate constructor and return the newly constructed object.
     *
     * @return the initial value for this thread-local
     */
    protected java.lang.Object initialValue() {
        return null;
    }

    /** 
     * Returns the value in the current thread's copy of this thread-local
     * variable.  Creates and initializes the copy if this is the first time
     * the thread has called this method.
     *
     * @return the current thread's value of this thread-local
     */
    public java.lang.Object get() {
        return null;
    }

    /** 
     * Sets the current thread's copy of this thread-local variable
     * to the specified value.  Many applications will have no need for
     * this functionality, relying solely on the {@link #initialValue()}
     * method to set the values of thread-locals.
     *
     * @param value the value to be stored in the current threads' copy of
     *	      this thread-local.
     */
    public void set(java.lang.Object value) { }
}
