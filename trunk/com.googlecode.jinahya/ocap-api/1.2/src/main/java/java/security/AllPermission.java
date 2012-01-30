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

import java.security.*;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.StringTokenizer;

/** 
 * The AllPermission is a permission that implies all other permissions.
 * <p>
 * <b>Note:</b> Granting AllPermission should be done with extreme care,
 * as it implies all other permissions. Thus, it grants code the ability 
 * to run with security
 * disabled.  Extreme caution should be taken before granting such
 * a permission to code.  This permission should be used only during testing,
 * or in extremely rare cases where an application or applet is
 * completely trusted and adding the necessary permissions to the policy 
 * is prohibitively cumbersome.
 * 
 * @see java.security.Permission
 * @see java.security.AccessController
 * @see java.security.Permissions
 * @see java.security.PermissionCollection
 * @see java.lang.SecurityManager
 *
 * @version 1.14 00/02/17
 *
 * @author Roland Schemers
 *
 * @serial exclude
 */
public final class AllPermission extends java.security.Permission
{

    /** 
     * Creates a new AllPermission object.
     */
    public AllPermission() { 
        super(null);    
    }

    /** 
     * Creates a new AllPermission object. This
     * constructor exists for use by the <code>Policy</code> object
     * to instantiate new Permission objects.
     *
     * @param name ignored
     * @param actions ignored.
     */
    public AllPermission(String name, String actions) { 
        super(name);    
    }

    /** 
     * Checks if the specified permission is "implied" by 
     * this object. This method always returns true.
     *
     * @param p the permission to check against.
     *
     * @return return
     */
    public boolean implies(java.security.Permission p) {
        return false;
    }

    /** 
     * Checks two AllPermission objects for equality. Two AllPermission
     * objects are always equal.
     *
     * @param obj the object we are testing for equality with this object.
     * @return true if <i>obj</i> is an AllPermission, false otherwise.
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
     * Returns the canonical string representation of the actions.
     *
     * @return the actions.
     */
    public String getActions() {
        return null;
    }

    /** 
     * Returns a new PermissionCollection object for storing AllPermission 
     * objects.
     * <p>
     * 
     * @return a new PermissionCollection object suitable for 
     * storing AllPermissions.
     */
    public java.security.PermissionCollection newPermissionCollection() {
        return null;
    }
}

/**
 * A AllPermissionCollection stores a collection
 * of AllPermission permissions. AllPermission objects
 * must be stored in a manner that allows them to be inserted in any
 * order, but enable the implies function to evaluate the implies
 * method in an efficient (and consistent) manner. 
 *
 * @see java.security.Permission
 * @see java.security.Permissions
 *
 * @version 1.14 02/17/00
 *
 * @author Roland Schemers
 *
 * @serial include
 */

final class AllPermissionCollection
extends PermissionCollection 
implements java.io.Serializable 
{

    // use serialVersionUID from JDK 1.2.2 for interoperability
    private static final long serialVersionUID = -4023755556366636806L;

    private boolean all_allowed; // true if any all permissions have been added

    /**
     * Create an empty AllPermissions object.
     *
     */

    public AllPermissionCollection() {
    }

    /**
     * Adds a permission to the AllPermissions. The key for the hash is
     * permission.path.
     *
     * @param permission the Permission object to add.
     *
     * @exception IllegalArgumentException - if the permission is not a
     *                                       AllPermission
     *
     * @exception SecurityException - if this AllPermissionCollection object
     *                                has been marked readonly
     */

    public void add(Permission permission)
    { }

    /**
     * Check and see if this set of permissions implies the permissions 
     * expressed in "permission".
     *
     * @param p the Permission object to compare
     *
     * @return always returns true.
     */

    public boolean implies(Permission permission) 
    {
	return false;
    }

    /**
     * Returns an enumeration of all the AllPermission objects in the 
     * container.
     *
     * @return an enumeration of all the AllPermission objects.
     */
    public Enumeration elements()
    {
	return null;
    }
}

