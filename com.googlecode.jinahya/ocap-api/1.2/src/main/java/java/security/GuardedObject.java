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

/** 
 * A GuardedObject is an object that is used to protect access to
 * another object.
 *
 * <p>A GuardedObject encapsulates a target object and a Guard object,
 * such that access to the target object is possible
 * only if the Guard object allows it.
 * Once an object is encapsulated by a GuardedObject,
 * access to that object is controlled by the <code>getObject</code>
 * method, which invokes the
 * <code>checkGuard</code> method on the Guard object that is
 * guarding access. If access is not allowed,
 * an exception is thrown.
 *
 * @see Guard
 * @see Permission
 *
 * @version 1.11 00/02/02
 * @author Roland Schemers
 * @author Li Gong
 */
public class GuardedObject implements java.io.Serializable
{
    private Object object;

    private Guard guard;

    /** 
     * Constructs a GuardedObject using the specified object and guard.
     * If the Guard object is null, then no restrictions will
     * be placed on who can access the object.
     *
     * @param object the object to be guarded.
     *
     * @param guard the Guard object that guards access to the object.
     */
    public GuardedObject(Object object, Guard guard) { }

    /** 
     * Retrieves the guarded object, or throws an exception if access
     * to the guarded object is denied by the guard.
     *
     * @return the guarded object.
     *
     * @exception SecurityException if access to the guarded object is
     * denied.
     */
    public Object getObject() throws SecurityException {
        return null;
    }

    /** 
     * Writes this object out to a stream (i.e., serializes it).
     * We check the guard if there is one.
     */
    private synchronized void writeObject(java.io.ObjectOutputStream oos)
        throws java.io.IOException
    { }
}
