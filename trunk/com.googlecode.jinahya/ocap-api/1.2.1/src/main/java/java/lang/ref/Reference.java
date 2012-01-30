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


  


package java.lang.ref;

/** 
 * Abstract base class for reference objects.  This class defines the
 * operations common to all reference objects.	Because reference objects are
 * implemented in close cooperation with the garbage collector, this class may
 * not be subclassed directly.
 *
 * @version  1.35 08/27/01
 * @author   Mark Reinhold
 * @since    1.2
 */
public abstract class Reference
{

    Reference(Object referent) { }

    Reference(Object referent, ReferenceQueue queue) { }

    /** 
     * Returns this reference object's referent.  If this reference object has
     * been cleared, either by the program or by the garbage collector, then
     * this method returns <code>null</code>.
     *
     * @return	 The object to which this reference refers, or
     *		 <code>null</code> if this reference object has been cleared
     */
    public Object get() {
        return null;
    }

    /** 
     * Clears this reference object.  Invoking this method will not cause this
     * object to be enqueued.
     */
    public void clear() { }

    /** 
     * Tells whether or not this reference object has been enqueued, either by
     * the program or by the garbage collector.	 If this reference object was
     * not registered with a queue when it was created, then this method will
     * always return <code>false</code>.
     *
     * @return	 <code>true</code> if and only if this reference object has
     *		 been enqueued
     */
    public boolean isEnqueued() {
        return false;
    }

    /** 
     * Adds this reference object to the queue with which it is registered,
     * if any.
     *
     * @return	 <code>true</code> if this reference object was successfully
     *		 enqueued; <code>false</code> if it was already enqueued or if
     *		 it was not registered with a queue when it was created
     */
    public boolean enqueue() {
        return false;
    }
}
