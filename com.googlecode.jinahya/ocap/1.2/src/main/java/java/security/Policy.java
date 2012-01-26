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

import java.io.*;
import java.lang.reflect.*;

import java.lang.RuntimePermission;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;
import java.util.StringTokenizer;
import java.util.PropertyPermission;
import java.util.WeakHashMap;

/** 
 * This is an abstract class for representing the system security
 * policy for a Java application environment (specifying
 * which permissions are available for code from various sources).
 * That is, the security policy is represented by a Policy subclass
 * providing an implementation of the abstract methods
 * in this Policy class.
 *
 * <p>There is only one Policy object in effect at any given time.
 *
 * <p>The source location for the policy information utilized by the
 * Policy object is up to the Policy implementation.
 * The policy configuration may be stored, for example, as a
 * flat ASCII file, as a serialized binary file of
 * the Policy class, or as a database.
 *
 * <p>The currently-installed Policy object can be obtained by
 * calling the <code>getPolicy</code> method, and it can be
 * changed by a call to the <code>setPolicy</code> method (by
 * code with permission to reset the Policy).
 *
 * <p>The <code>refresh</code> method causes the policy
 * object to refresh/reload its current configuration.
 *
 * <p>This is implementation-dependent. For example, if the policy
 * object stores its policy in configuration files, calling
 * <code>refresh</code> will cause it to re-read the configuration 
 * policy files. The refreshed policy may not have an effect on classes
 * in a particular ProtectionDomain. This is dependent on the Policy
 * provider's implementation of the 
 * {@link #implies(ProtectionDomain,Permission) implies}
 * method and the PermissionCollection caching strategy.
 *
 * <p>The default Policy implementation can be changed by setting the
 * value of the "policy.provider" security property (in the Java
 * security properties file) to the fully qualified name of
 * the desired Policy implementation class.
 * The Java security properties file is located in the file named
 * &lt;JAVA_HOME&gt;/lib/security/java.security, where &lt;JAVA_HOME&gt;
 * refers to the directory where the SDK was installed.
 *
 * @author Roland Schemers
 * @author Gary Ellison
 * @version 1.74, 03/12/05
 * @see java.security.CodeSource
 * @see java.security.PermissionCollection
 * @see java.security.SecureClassLoader
 */
public abstract class Policy
{

    public Policy() { }

    /** 
     * Returns the installed Policy object. This value should not be cached,
     * as it may be changed by a call to <code>setPolicy</code>.
     * This method first calls
     * <code>SecurityManager.checkPermission</code> with a
     * <code>SecurityPermission("getPolicy")</code> permission
     * to ensure it's ok to get the Policy object..
     *
     * @return the installed Policy.
     *
     * @throws SecurityException
     *        if a security manager exists and its
     *        <code>checkPermission</code> method doesn't allow
     *        getting the Policy object.
     *
     * @see SecurityManager#checkPermission(Permission)
     * @see #setPolicy(java.security.Policy)
     */
    public static Policy getPolicy() {
        return null;
    }

    /** 
     * Sets the system-wide Policy object. This method first calls
     * <code>SecurityManager.checkPermission</code> with a
     * <code>SecurityPermission("setPolicy")</code>
     * permission to ensure it's ok to set the Policy.
     *
     * @param policy the new system Policy object.
     *
     * @throws SecurityException
     *        if a security manager exists and its
     *        <code>checkPermission</code> method doesn't allow
     *        setting the Policy.
     *
     * @see SecurityManager#checkPermission(Permission)
     * @see #getPolicy()
     *
     */
    public static void setPolicy(Policy policy) { }

    /** 
     * Evaluates the global policy and returns a
     * PermissionCollection object specifying the set of
     * permissions allowed for code from the specified
     * code source.
     *
     * @param codesource the CodeSource associated with the caller.
     * This encapsulates the original location of the code (where the code
     * came from) and the public key(s) of its signer.
     *
     * @return the set of permissions allowed for code from <i>codesource</i>
     * according to the policy.The returned set of permissions must be 
     * a new mutable instance and it must support heterogeneous 
     * Permission types.
     *
     */
    public abstract PermissionCollection getPermissions(CodeSource codesource);

    /** 
     * Evaluates the global policy and returns a
     * PermissionCollection object specifying the set of
     * permissions allowed given the characteristics of the 
     * protection domain.
     *
     * @param domain the ProtectionDomain associated with the caller.
     *
     * @return the set of permissions allowed for the <i>domain</i>
     * according to the policy.The returned set of permissions must be 
     * a new mutable instance and it must support heterogeneous 
     * Permission types.
     *
     * @see java.security.ProtectionDomain
     * @see java.security.SecureClassLoader
     * @since 1.4
     */
    public PermissionCollection getPermissions(ProtectionDomain domain) {
        return null;
    }

    /** 
     * Evaluates the global policy for the permissions granted to
     * the ProtectionDomain and tests whether the permission is 
     * granted.
     *
     * @param domain the ProtectionDomain to test
     * @param permission the Permission object to be tested for implication.
     *
     * @return true if "permission" is a proper subset of a permission
     * granted to this ProtectionDomain.
     *
     * @see java.security.ProtectionDomain
     * @since 1.4
     */
    public boolean implies(ProtectionDomain domain, Permission permission) {
        return false;
    }

    /** 
     * Refreshes/reloads the policy configuration. The behavior of this method
     * depends on the implementation. For example, calling <code>refresh</code>
     * on a file-based policy will cause the file to be re-read.
     *
     */
    public abstract void refresh();
}
