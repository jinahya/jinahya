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
import java.util.Iterator;
import java.util.Map;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Collections;
import java.util.StringTokenizer;
import java.io.ObjectStreamField;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;

/** 
 * The BasicPermission class extends the Permission class, and
 * can be used as the base class for permissions that want to
 * follow the same naming convention as BasicPermission.
 * <P>
 * The name for a BasicPermission is the name of the given permission
 * (for example, "exit",
 * "setFactory", "print.queueJob", etc). The naming
 * convention follows the  hierarchical property naming convention.
 * An asterisk may appear by itself, or if immediately preceded by a "."
 * may appear at the end of the name, to signify a wildcard match.
 * For example, "*" and "java.*" are valid, while "*java", "a*b",
 * and "java*" are not valid.
 * <P>
 * The action string (inherited from Permission) is unused.
 * Thus, BasicPermission is commonly used as the base class for
 * "named" permissions
 * (ones that contain a name but no actions list; you either have the
 * named permission or you don't.)
 * Subclasses may implement actions on top of BasicPermission,
 * if desired.
 * <p>
 * <P>
 * @see java.security.Permission
 * @see java.security.Permissions
 * @see java.security.PermissionCollection
 * @see java.lang.RuntimePermission
 * @see java.security.SecurityPermission
 * @see java.util.PropertyPermission
 * @see java.net.NetPermission
 * @see java.lang.SecurityManager
 *
 * @version 1.26 00/02/02
 *
 * @author Marianne Mueller
 * @author Roland Schemers
 */
public abstract class BasicPermission extends java.security.Permission
    implements java.io.Serializable
{

    /** 
     * Creates a new BasicPermission with the specified name.
     * Name is the symbolic name of the permission, such as
     * "setFactory",
     * "print.queueJob", or "topLevelWindow", etc.
     *
     * @param name the name of the BasicPermission.
     *
     * @throws NullPointerException if <code>name</code> is <code>null</code>.
     * @throws IllegalArgumentException if <code>name</code> is empty.
     */
    public BasicPermission(String name) { 
        super(name);
    }

    /** 
     * Creates a new BasicPermission object with the specified name.
     * The name is the symbolic name of the BasicPermission, and the
     * actions String is currently unused.
     *
     * @param name the name of the BasicPermission.
     * @param actions ignored.
     *
     * @throws NullPointerException if <code>name</code> is <code>null</code>.
     * @throws IllegalArgumentException if <code>name</code> is empty.
     */
    public BasicPermission(String name, String actions) { 
        super(name);    
    }

    /** 
     * Checks if the specified permission is "implied" by
     * this object.
     * <P>
     * More specifically, this method returns true if:<p>
     * <ul>
     * <li> <i>p</i>'s class is the same as this object's class, and<p>
     * <li> <i>p</i>'s name equals or (in the case of wildcards)
     *      is implied by this object's
     *      name. For example, "a.b.*" implies "a.b.c".
     * </ul>
     *
     * @param p the permission to check against.
     *
     * @return true if the passed permission is equal to or
     * implied by this permission, false otherwise.
     */
    public boolean implies(java.security.Permission p) {
        return false;
    }

    /** 
     * Checks two BasicPermission objects for equality.
     * Checks that <i>obj</i>'s class is the same as this object's class
     * and has the same name as this object.
     * <P>
     * @param obj the object we are testing for equality with this object.
     * @return true if <i>obj</i> is a BasicPermission, and has the same name
     *  as this BasicPermission object, false otherwise.
     */
    public boolean equals(Object obj) {
        return false;
    }

    /** 
     * Returns the hash code value for this object.
     * The hash code used is the hash code of the name, that is,
     * <code>getName().hashCode()</code>, where <code>getName</code> is
     * from the Permission superclass.
     *
     * @return a hash code value for this object.
     */
    public int hashCode() {
        return 0;
    }

    /** 
     * Returns the canonical string representation of the actions,
     * which currently is the empty string "", since there are no actions for
     * a BasicPermission.
     *
     * @return the empty string "".
     */
    public String getActions() {
        return null;
    }

    /** 
     * Returns a new PermissionCollection object for storing BasicPermission
     * objects.
     * <p>
     * A BasicPermissionCollection stores a collection of
     * BasicPermission permissions.
     *
     * <p>BasicPermission objects must be stored in a manner that allows them
     * to be inserted in any order, but that also enables the
     * PermissionCollection <code>implies</code> method
     * to be implemented in an efficient (and consistent) manner.
     *
     * @return a new PermissionCollection object suitable for
     * storing BasicPermissions.
     */
    public java.security.PermissionCollection newPermissionCollection() {
        return null;
    }

    /** 
     * readObject is called to restore the state of the BasicPermission from
     * a stream. 
     */
    private void readObject(ObjectInputStream s)
        throws IOException, ClassNotFoundException
    { }
}

/**
 * A BasicPermissionCollection stores a collection
 * of BasicPermission permissions. BasicPermission objects
 * must be stored in a manner that allows them to be inserted in any
 * order, but enable the implies function to evaluate the implies
 * method in an efficient (and consistent) manner.
 *
 * A BasicPermissionCollection handles comparing a permission like "a.b.c.d.e"
 * with a Permission such as "a.b.*", or "*".
 *
 * @see java.security.Permission
 * @see java.security.Permissions
 * @see java.security.PermissionsImpl
 *
 * @version 1.26 02/02/00
 *
 * @author Roland Schemers
 *
 * @serial include
 */

final class BasicPermissionCollection
extends PermissionCollection
implements java.io.Serializable
{

    private static final long serialVersionUID = 739301742472979399L;

    /**
     * This is set to <code>true</code> if this BasicPermissionCollection
     * contains a BasicPermission with '*' as its permission name.
     *
     * @see #serialPersistentFields
     */
    private boolean all_allowed; 

    /**
     * The class to which all BasicPermissions in this
     * BasicPermissionCollection belongs.
     *
     * @see #serialPersistentFields
     */
    private Class permClass;

    /**
     * Create an empty BasicPermissionCollection object.
     *
     */

    public BasicPermissionCollection() {
    }

    /**
     * Adds a permission to the BasicPermissions. The key for the hash is
     * permission.path.
     *
     * @param permission the Permission object to add.
     *
     * @exception IllegalArgumentException - if the permission is not a
     *                                       BasicPermission, or if
     *					     the permission is not of the
     *					     same Class as the other
     *					     permissions in this collection.
     *
     * @exception SecurityException - if this BasicPermissionCollection object
     *                                has been marked readonly
     */

    public void add(Permission permission)
    {
    }

    /**
     * Check and see if this set of permissions implies the permissions
     * expressed in "permission".
     *
     * @param p the Permission object to compare
     *
     * @return true if "permission" is a proper subset of a permission in
     * the set, false if not.
     */

    public boolean implies(Permission permission)
    {
    	return false;
    }

    /**
     * Returns an enumeration of all the BasicPermission objects in the
     * container.
     *
     * @return an enumeration of all the BasicPermission objects.
     */

    public Enumeration elements() {
        return null;
    }

    /**
     * @serialField permissions java.util.Hashtable
     *    The BasicPermissions in this BasicPermissionCollection.
     *    All BasicPermissions in the collection must belong to the same class.
     *    The Hashtable is indexed by the BasicPermission name; the value
     *    of the Hashtable entry is the permission.
     * @serialField all_allowed boolean
     *   This is set to <code>true</code> if this BasicPermissionCollection
     *   contains a BasicPermission with '*' as its permission name.
     * @serialField permClass java.lang.Class
     *   The class to which all BasicPermissions in this
     *   BasicPermissionCollection belongs.
     */
    private static final ObjectStreamField[] serialPersistentFields = {
        new ObjectStreamField("permissions", Hashtable.class),
	new ObjectStreamField("all_allowed", Boolean.TYPE),
	new ObjectStreamField("permClass", Class.class),
    };

    /**
     * @serialData Default fields.
     */
    /*
     * Writes the contents of the perms field out as a Hashtable for
     * serialization compatibility with earlier releases. all_allowed
     * and permClass unchanged.
     */
    private void writeObject(ObjectOutputStream out) throws IOException {
    }

    /**
     * readObject is called to restore the state of the
     * BasicPermissionCollection from a stream.
     */
    private void readObject(java.io.ObjectInputStream in)
	 throws IOException, ClassNotFoundException
    { }
}
