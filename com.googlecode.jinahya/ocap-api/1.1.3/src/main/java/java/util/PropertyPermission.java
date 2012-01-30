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


  


package java.util;

import java.security.*;

import java.io.Serializable;
import java.io.IOException;
import java.util.Map;
import java.util.HashMap;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Collections;
import java.io.ObjectStreamField;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;

/** 
 * This class is for property permissions.
 *
 * <P>
 * The name is the name of the property ("java.home",
 * "os.name", etc). The naming
 * convention follows the  hierarchical property naming convention.
 * Also, an asterisk
 * may appear at the end of the name, following a ".", or by itself, to
 * signify a wildcard match. For example: "java.*" or "*" is valid,
 * "*java" or "a*b" is not valid.
 * <P>
 * <P>
 * The actions to be granted are passed to the constructor in a string containing
 * a list of zero or more comma-separated keywords. The possible keywords are
 * "read" and "write". Their meaning is defined as follows:
 * <P>
 * <DL>
 *    <DT> read
 *    <DD> read permission. Allows <code>System.getProperty</code> to
 *         be called.
 *    <DT> write
 *    <DD> write permission. Allows <code>System.setProperty</code> to
 *         be called.
 * </DL>
 * <P>
 * The actions string is converted to lowercase before processing.
 * <P>
 * Care should be taken before granting code permission to access
 * certain system properties.  For example, granting permission to
 * access the "java.home" system property gives potentially malevolent
 * code sensitive information about the system environment (the Java
 * installation directory).  Also, granting permission to access
 * the "user.name" and "user.home" system properties gives potentially
 * malevolent code sensitive information about the user environment
 * (the user's account name and home directory).
 *
 * @see java.security.BasicPermission
 * @see java.security.Permission
 * @see java.security.Permissions
 * @see java.security.PermissionCollection
 * @see java.lang.SecurityManager
 *
 * @version 1.24 00/02/02
 *
 * @author Roland Schemers
 * @since 1.2
 *
 * @serial exclude
 */
public final class PropertyPermission extends BasicPermission
{
    /** 
     * The actions string.
     *
     * @serial 
     */
    private String actions;

    /** 
     * Creates a new PropertyPermission object with the specified name.
     * The name is the name of the system property, and
     * <i>actions</i> contains a comma-separated list of the
     * desired actions granted on the property. Possible actions are
     * "read" and "write".
     *
     * @param name the name of the PropertyPermission.
     * @param actions the actions string.
     */
    public PropertyPermission(String name, String actions) { 
        super(name, actions);
    }

    /** 
     * Checks if this PropertyPermission object "implies" the specified
     * permission.
     * <P>
     * More specifically, this method returns true if:<p>
     * <ul>
     * <li> <i>p</i> is an instanceof PropertyPermission,<p>
     * <li> <i>p</i>'s actions are a subset of this
     * object's actions, and <p>
     * <li> <i>p</i>'s name is implied by this object's
     *      name. For example, "java.*" implies "java.home".
     * </ul>
     * @param p the permission to check against.
     *
     * @return true if the specified permission is implied by this object,
     * false if not.
     */
    public boolean implies(Permission p) {
        return false;
    }

    /** 
     * Checks two PropertyPermission objects for equality. Checks that <i>obj</i> is
     * a PropertyPermission, and has the same name and actions as this object.
     * <P>
     * @param obj the object we are testing for equality with this object.
     * @return true if obj is a PropertyPermission, and has the same name and
     * actions as this PropertyPermission object.
     */
    public boolean equals(Object obj) {
        return false;
    }

    /** 
     * Returns the hash code value for this object.
     * The hash code used is the hash code of this permissions name, that is,
     * <code>getName().hashCode()</code>, where <code>getName</code> is
     * from the Permission superclass.
     *
     * @return a hash code value for this object.
     */
    public int hashCode() {
        return 0;
    }

    /** 
     * Returns the "canonical string representation" of the actions.
     * That is, this method always returns present actions in the following order:
     * read, write. For example, if this PropertyPermission object
     * allows both write and read actions, a call to <code>getActions</code>
     * will return the string "read,write".
     *
     * @return the canonical string representation of the actions.
     */
    public String getActions() {
        return null;
    }

    /** 
     * Returns a new PermissionCollection object for storing
     * PropertyPermission objects.
     * <p>
     *
     * @return a new PermissionCollection object suitable for storing
     * PropertyPermissions.
     */
    public PermissionCollection newPermissionCollection() {
        return null;
    }

    /** 
     * readObject is called to restore the state of the PropertyPermission from
     * a stream.
     */
    private synchronized void readObject(ObjectInputStream s)
        throws java.io.IOException, ClassNotFoundException
    { }

    /** 
     * WriteObject is called to save the state of the PropertyPermission
     * to a stream. The actions are serialized, and the superclass
     * takes care of the name.
     */
    private synchronized void writeObject(ObjectOutputStream s)
        throws java.io.IOException
    { }
}

/**
 * A PropertyPermissionCollection stores a set of PropertyPermission
 * permissions.
 *
 * @see java.security.Permission
 * @see java.security.Permissions
 * @see java.security.PermissionCollection
 *
 * @version 1.24, 02/02/00
 *
 * @author Roland Schemers
 *
 * @serial include
 */
final class PropertyPermissionCollection extends PermissionCollection
implements Serializable
{

    /**
     * Key is property name; value is PropertyPermission.
     * Not serialized; see serialization section at end of class.
     */
    private transient Map perms;

    /**
     * Boolean saying if "*" is in the collection.
     *
     * @see #serialPersistentFields
     */
    private boolean all_allowed;

    /**
     * Create an empty PropertyPermissions object.
     *
     */

    public PropertyPermissionCollection() {
    }

    /**
     * Adds a permission to the PropertyPermissions. The key for the hash is
     * the name.
     *
     * @param permission the Permission object to add.
     *
     * @exception IllegalArgumentException - if the permission is not a
     *                                       PropertyPermission
     *
     * @exception SecurityException - if this PropertyPermissionCollection
     *                                object has been marked readonly
     */

    public void add(Permission permission)
    { }

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
     * Returns an enumeration of all the PropertyPermission objects in the
     * container.
     *
     * @return an enumeration of all the PropertyPermission objects.
     */

    public Enumeration elements() {
        return null;
    }                                                

    private static final long serialVersionUID = 7015263904581634791L;

    /**
     * @serialField permissions java.util.Hashtable
     *     A table of the PropertyPermissions.
     * @serialField all_allowed boolean
     *     boolean saying if "*" is in the collection.
     */
    private static final ObjectStreamField[] serialPersistentFields = {
        new ObjectStreamField("permissions", Hashtable.class),
	new ObjectStreamField("all_allowed", Boolean.TYPE),
    };

    /**
     * @serialData Default fields.
     */
    private void writeObject(ObjectOutputStream out) throws IOException {
    }

    /*
     * Reads in a Hashtable of PropertyPermissions and saves them in the 
     * perms field. Reads in all_allowed.
     */
    private void readObject(ObjectInputStream in) throws IOException, 
    ClassNotFoundException {
    }
}
