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

import java.util.Vector;

/** 
 * An AccessControlContext is used to make system resource access decisions
 * based on the context it encapsulates.
 * 
 * <p>More specifically, it encapsulates a context and
 * has a single method, <code>checkPermission</code>,
 * that is equivalent to the <code>checkPermission</code> method
 * in the AccessController class, with one difference: The AccessControlContext
 * <code>checkPermission</code> method makes access decisions based on the 
 * context it encapsulates,
 * rather than that of the current execution thread.
 * 
 * <p>Thus, the purpose of AccessControlContext is for those situations where
 * a security check that should be made within a given context
 * actually needs to be done from within a
 * <i>different</i> context (for example, from within a worker thread).
 * 
 * <p> An AccessControlContext is created by calling the 
 * <code>AccessController.getContext</code> method. 
 * The <code>getContext</code> method takes a "snapshot"
 * of the current calling context, and places
 * it in an AccessControlContext object, which it returns. A sample call is
 * the following:
 * 
 * <pre>
 * 
 *   AccessControlContext acc = AccessController.getContext()
 * 
 * </pre>
 * 
 * <p>
 * Code within a different context can subsequently call the
 * <code>checkPermission</code> method on the
 * previously-saved AccessControlContext object. A sample call is the
 * following:
 * 
 * <pre>
 * 
 *   acc.checkPermission(permission)
 * 
 * </pre> 
 * 
 * @see AccessController
 *
 * @author Roland Schemers
 */
public final class AccessControlContext
{

    /** 
     * Create an AccessControlContext with the given set of ProtectionDomains.
     * Context must not be null. Duplicate domains will be removed from the
     * context.
     *
     * @param context the ProtectionDomains associated with this context.
     */
    public AccessControlContext(ProtectionDomain[] context) { }

    /** 
     * Create a new <code>AccessControlContext</code> with the given
     * <code>AccessControlContext</code> and <code>DomainCombiner</code>.
     * This constructor associates the provided
     * <code>DomainCombiner</code> with the provided
     * <code>AccessControlContext</code>.
     *
     * <p>
     *
     * @param acc the <code>AccessControlContext</code> associated
     *		with the provided <code>DomainCombiner</code>. <p>
     *
     * @param combiner the <code>DomainCombiner</code> to be associated
     *		with the provided <code>AccessControlContext</code>.
     *
     * @exception NullPointerException if the provided
     *		<code>context</code> is <code>null</code>. <p>
     *
     * @exception SecurityException if the caller does not have permission
     *		to invoke this constructor.
     */
    public AccessControlContext(AccessControlContext acc, DomainCombiner
        combiner)
    { }

    /** 
     * Get the <code>DomainCombiner</code> associated with this
     * <code>AccessControlContext</code>.
     *
     * <p>
     *
     * @return the <code>DomainCombiner</code> associated with this
     *		<code>AccessControlContext</code>, or <code>null</code>
     *		if there is none.
     *
     * @exception SecurityException if the caller does not have permission
     *		to get the <code>DomainCombiner</code> associated with this
     *		<code>AccessControlContext</code>.
     */
    public DomainCombiner getDomainCombiner() {
        return null;
    }

    /** 
     * Determines whether the access request indicated by the
     * specified permission should be allowed or denied, based on
     * the security policy currently in effect, and the context in
     * this object.
     * <p>
     * This method quietly returns if the access request
     * is permitted, or throws a suitable AccessControlException otherwise. 
     *
     * @param perm the requested permission.
     * 
     * @exception AccessControlException if the specified permission
     * is not permitted, based on the current security policy and the
     * context encapsulated by this object.
     * @exception NullPointerException if the permission to check for is null.
     */
    public void checkPermission(Permission perm) throws AccessControlException
    { }

    /** 
     * Checks two AccessControlContext objects for equality. 
     * Checks that <i>obj</i> is
     * an AccessControlContext and has the same set of ProtectionDomains
     * as this context.
     * <P>
     * @param obj the object we are testing for equality with this object.
     * @return true if <i>obj</i> is an AccessControlContext, and has the 
     * same set of ProtectionDomains as this context, false otherwise.
     */
    public boolean equals(Object obj) {
        return false;
    }

    /** 
     * Returns the hash code value for this context. The hash code
     * is computed by exclusive or-ing the hash code of all the protection
     * domains in the context together.
     * 
     * @return a hash code value for this context.
     */
    public int hashCode() {
        return 0;
    }
}
