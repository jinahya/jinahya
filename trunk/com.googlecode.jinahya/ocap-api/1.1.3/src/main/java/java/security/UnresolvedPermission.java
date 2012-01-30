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

import java.lang.reflect.*;
import java.security.cert.*;

import java.io.IOException;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

/** 
 * The UnresolvedPermission class is used to hold Permissions that
 * were "unresolved" when the Policy was initialized. 
 * An unresolved permission is one whose actual Permission class
 * does not yet exist at the time the Policy is initialized (see below).
 * 
 * <p>The policy for a Java runtime (specifying 
 * which permissions are available for code from various principals)
 * is represented by a Policy object.
 * Whenever a Policy is initialized or refreshed, Permission objects of
 * appropriate classes are created for all permissions
 * allowed by the Policy. 
 * 
 * <p>Many permission class types 
 * referenced by the policy configuration are ones that exist
 * locally (i.e., ones that can be found on CLASSPATH).
 * Objects for such permissions can be instantiated during
 * Policy initialization. For example, it is always possible
 * to instantiate a java.io.FilePermission, since the
 * FilePermission class is found on the CLASSPATH.
 * 
 * <p>Other permission classes may not yet exist during Policy
 * initialization. For example, a referenced permission class may
 * be in a JAR file that will later be loaded.
 * For each such class, an UnresolvedPermission is instantiated.
 * Thus, an UnresolvedPermission is essentially a "placeholder"
 * containing information about the permission.
 * 
 * <p>Later, when code calls AccessController.checkPermission 
 * on a permission of a type that was previously unresolved,
 * but whose class has since been loaded, previously-unresolved
 * permissions of that type are "resolved". That is,
 * for each such UnresolvedPermission, a new object of
 * the appropriate class type is instantiated, based on the
 * information in the UnresolvedPermission.
 *
 * <p> To instantiate the new class, UnresolvedPermission assumes
 * the class provides a zero, one, and/or two-argument constructor.
 * The zero-argument constructor would be used to instantiate
 * a permission without a name and without actions.
 * A one-arg constructor is assumed to take a <code>String</code>
 * name as input, and a two-arg constructor is assumed to take a
 * <code>String</code> name and <code>String</code> actions
 * as input.  UnresolvedPermission may invoke a
 * constructor with a <code>null</code> name and/or actions.
 * If an appropriate permission constructor is not available,
 * the UnresolvedPermission is ignored and the relevant permission
 * will not be granted to executing code.
 *
 * <p> The newly created permission object replaces the
 * UnresolvedPermission, which is removed.
 *
 * @see java.security.Permission
 * @see java.security.Permissions
 * @see java.security.PermissionCollection
 * @see java.security.Policy
 *
 * @version 1.17 00/02/02
 *
 * @author Roland Schemers
 */
public final class UnresolvedPermission extends Permission
    implements java.io.Serializable
{
    /** 
     * The class name of the Permission class that will be
     * created when this unresolved permission is resolved.
     *
     * @serial
     */
    private String type;

    /** 
     * The permission name.
     *
     * @serial
     */
    private String name;

    /** 
     * The actions of the permission.
     *
     * @serial
     */
    private String actions;

    /** 
     * Creates a new UnresolvedPermission containing the permission
     * information needed later to actually create a Permission of the
     * specified class, when the permission is resolved.
     * 
     * @param type the class name of the Permission class that will be
     * created when this unresolved permission is resolved.
     * @param name the name of the permission.
     * @param actions the actions of the permission.
     * @param certs the certificates the permission's class was signed with.
     * This is a list of certificate chains, where each chain is composed of a
     * signer certificate and optionally its supporting certificate chain.
     * Each chain is ordered bottom-to-top (i.e., with the signer certificate
     * first and the (root) certificate authority last).
     */
    public UnresolvedPermission(String type, String name, String actions,
        java.security.cert.Certificate[] certs) { 
        super(type);    
    }

    /** 
     * This method always returns false for unresolved permissions.
     * That is, an UnresolvedPermission is never considered to
     * imply another permission.
     *
     * @param p the permission to check against.
     * 
     * @return false.
     */
    public boolean implies(Permission p) {
        return false;
    }

    /** 
     * Checks two UnresolvedPermission objects for equality. 
     * Checks that <i>obj</i> is an UnresolvedPermission, and has 
     * the same type (class) name, permission name, actions, and
     * certificates as this object.
     * 
     * @param obj the object we are testing for equality with this object.
     * 
     * @return true if obj is an UnresolvedPermission, and has the same 
     * type (class) name, permission name, actions, and
     * certificates as this object.
     */
    public boolean equals(Object obj) {
        return false;
    }

    /** 
     * Returns the hash code value for this object.
     *
     * @return a hash code value for this object.
     */
    public int hashCode() {
        return 0;
    }

    /** 
     * Returns the canonical string representation of the actions,
     * which currently is the empty string "", since there are no actions for 
     * an UnresolvedPermission. That is, the actions for the
     * permission that will be created when this UnresolvedPermission
     * is resolved may be non-null, but an UnresolvedPermission
     * itself is never considered to have any actions.
     *
     * @return the empty string "".
     */
    public String getActions() {
        return null;
    }

    /** 
     * Returns a string describing this UnresolvedPermission.  The convention 
     * is to specify the class name, the permission name, and the actions, in
     * the following format: '(unresolved "ClassName" "name" "actions")'.
     * 
     * @return information about this UnresolvedPermission.
     */
    public String toString() {
        return null;
    }

    /** 
     * Returns a new PermissionCollection object for storing 
     * UnresolvedPermission  objects.
     * <p>
     * @return a new PermissionCollection object suitable for 
     * storing UnresolvedPermissions.
     */
    public PermissionCollection newPermissionCollection() {
        return null;
    }

    /** 
     * Restores this object from a stream (i.e., deserializes it).
     */
    private synchronized void readObject(java.io.ObjectInputStream ois)
        throws IOException, ClassNotFoundException
    { }

    /** 
     * Writes this object out to a stream (i.e., serializes it).
     *
     * @serialData An initial <code>String</code> denoting the
     * <code>type</code> is followed by a <code>String</code> denoting the
     * <code>name</code> is followed by a <code>String</code> denoting the
     * <code>actions</code> is followed by an <code>int</code> indicating the
     * number of certificates to follow 
     * (a value of "zero" denotes that there are no certificates associated
     * with this object).
     * Each certificate is written out starting with a <code>String</code>
     * denoting the certificate type, followed by an
     * <code>int</code> specifying the length of the certificate encoding,
     * followed by the certificate encoding itself which is written out as an
     * array of bytes.
     */
    private synchronized void writeObject(java.io.ObjectOutputStream oos)
        throws IOException
    { }
}
