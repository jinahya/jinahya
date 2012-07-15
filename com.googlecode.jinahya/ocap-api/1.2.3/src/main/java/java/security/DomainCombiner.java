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
 * A <code>DomainCombiner</code> provides a means to dynamically
 * update the ProtectionDomains associated with the current
 * <code>AccessControlContext</code>.
 *
 * <p> A <code>DomainCombiner</code> is passed as a parameter to the
 * appropriate constructor for <code>AccessControlContext</code>.
 * The newly constructed context is then passed to the
 * <code>AccessController.doPrivileged(..., context)</code> method
 * to bind the provided context (and associated <code>DomainCombiner</code>)
 * with the current execution Thread.  Subsequent calls to
 * <code>AccessController.getContext</code> or
 * <code>AccessController.checkPermission</code>
 * cause the <code>DomainCombiner.combine</code> to get invoked.
 *
 * <p> The combine method takes two arguments.  The first argument represents
 * an array of ProtectionDomains from the current execution Thread,
 * since the most recent call to <code>AccessController.doPrivileged</code>.
 * If no call to doPrivileged was made, then the first argument will contain
 * all the ProtectionDomains from the current execution Thread.
 * The second argument represents an array of inherited ProtectionDomains,
 * which may be <code>null</code>.  ProtectionDomains may be inherited
 * from a parent Thread, or from a privileged context.  If no call to
 * doPrivileged was made, then the second argument will contain the
 * ProtectionDomains inherited from the parent Thread.  If one or more calls
 * to doPrivileged were made, and the most recent call was to
 * doPrivileged(action, context), then the second argument will contain the
 * ProtectionDomains from the privileged context.  If the most recent call
 * was to doPrivileged(action), then there is no privileged context,
 * and the second argument will be <code>null</code>.
 *
 * <p> The <code>combine</code> method investigates the two input arrays
 * of ProtectionDomains and returns a single array containing the updated
 * ProtectionDomains.  In the simplest case, the <code>combine</code>
 * method merges the two stacks into one.  In more complex cases,
 * the <code>combine</code> method returns a modified
 * stack of ProtectionDomains.  The modification may have added new
 * ProtectionDomains, removed certain ProtectionDomains, or simply
 * updated existing ProtectionDomains.  Re-ordering and other optimizations
 * to the ProtectionDomains are also permitted.  Typically the
 * <code>combine</code> method bases its updates on the information
 * encapsulated in the <code>DomainCombiner</code>.
 *
 * <p> After the <code>AccessController.getContext</code> method
 * receives the combined stack of ProtectionDomains back from
 * the <code>DomainCombiner</code>, it returns a new
 * AccessControlContext that has both the combined ProtectionDomains
 * as well as the <code>DomainCombiner</code>.
 * 
 * @see AccessController
 * @see AccessControlContext
 * @version 1.3, 02/02/00
 */
public interface DomainCombiner
{

    /** 
     * Modify or update the provided ProtectionDomains.
     * ProtectionDomains may be added to or removed from the given
     * ProtectionDomains.  The ProtectionDomains may be re-ordered.
     * Individual ProtectionDomains may be may be modified (with a new
     * set of Permissions, for example).
     *
     * <p>
     *
     * @param currentDomains the ProtectionDomains associated with the
     *		current execution Thread, up to the most recent
     *		privileged <code>ProtectionDomain</code>.
     *		The ProtectionDomains are are listed in order of execution,
     *		with the most recently executing <code>ProtectionDomain</code>
     *		residing at the beginning of the array. This parameter may
     *		be <code>null</code> if the current execution Thread
     *		has no associated ProtectionDomains.<p>
     *
     * @param assignedDomains an array of inherited ProtectionDomains.
     *		ProtectionDomains may be inherited from a parent Thread,
     *		or from a privileged <code>AccessControlContext</code>.
     *		This parameter may be <code>null</code>
     *		if there are no inherited ProtectionDomains.
     *
     * @return a new array consisting of the updated ProtectionDomains,
     *		or <code>null</code>.
     */
    public ProtectionDomain[] combine(ProtectionDomain[] currentDomains,
        ProtectionDomain[] assignedDomains);
}
