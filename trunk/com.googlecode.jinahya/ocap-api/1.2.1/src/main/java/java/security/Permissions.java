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

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.NoSuchElementException;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Collections;
import java.io.Serializable;
import java.io.ObjectStreamField;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;

/** 
 * This class represents a heterogeneous collection of Permissions. That is,
 * it contains different types of Permission objects, organized into
 * PermissionCollections. For example, if any
 * <code>java.io.FilePermission</code> objects are added to an instance of
 * this class, they are all stored in a single
 * PermissionCollection. It is the PermissionCollection returned by a call to
 * the <code>newPermissionCollection</code> method in the FilePermission class.
 * Similarly, any <code>java.lang.RuntimePermission</code> objects are
 * stored in the PermissionCollection returned by a call to the 
 * <code>newPermissionCollection</code> method in the
 * RuntimePermission class. Thus, this class represents a collection of
 * PermissionCollections.
 * 
 * <p>When the <code>add</code> method is called to add a Permission, the 
 * Permission is stored in the appropriate PermissionCollection. If no such 
 * collection exists yet, the Permission object's class is determined and the
 * <code>newPermissionCollection</code> method is called on that class to create
 * the PermissionCollection and add it to the Permissions object. If
 * <code>newPermissionCollection</code> returns null, then a default 
 * PermissionCollection that uses a hashtable will be created and used. Each 
 * hashtable entry stores a Permission object as both the key and the value.
 *
 * <p> Enumerations returned via the <code>elements</code> method are
 * not <em>fail-fast</em>.  Modifications to a collection should not be
 * performed while enumerating over that collection.
 * 
 * @see Permission
 * @see PermissionCollection
 * @see AllPermission
 * 
 * @version 1.46, 00/02/02
 *
 * @author Marianne Mueller
 * @author Roland Schemers
 *
 * @serial exclude
 */
public final class Permissions extends PermissionCollection
    implements Serializable
{

    /** 
     * Creates a new Permissions object containing no PermissionCollections.
     */
    public Permissions() { }

    /** 
     * Adds a permission object to the PermissionCollection for the class the
     * permission belongs to. For example, if <i>permission</i> is a
     * FilePermission, it is added to the FilePermissionCollection stored
     * in this Permissions object. 
     * 
     * This method creates
     * a new PermissionCollection object (and adds the permission to it)
     * if an appropriate collection does not yet exist. <p>
     *
     * @param permission the Permission object to add.
     * 
     * @exception SecurityException if this Permissions object is
     * marked as readonly.
     * 
     * @see PermissionCollection#isReadOnly()
     */
    public void add(Permission permission) { }

    /** 
     * Checks to see if this object's PermissionCollection for permissions of
     * the specified permission's type implies the permissions 
     * expressed in the <i>permission</i> object. Returns true if the
     * combination of permissions in the appropriate PermissionCollection
     * (e.g., a FilePermissionCollection for a FilePermission) together
     * imply the specified permission.
     * 
     * <p>For example, suppose there is a FilePermissionCollection in this
     * Permissions object, and it contains one FilePermission that specifies
     * "read" access for  all files in all subdirectories of the "/tmp"
     * directory, and another FilePermission that specifies "write" access
     * for all files in the "/tmp/scratch/foo" directory.
     * Then if the <code>implies</code> method
     * is called with a permission specifying both "read" and "write" access
     * to files in the "/tmp/scratch/foo" directory, <code>true</code> is
     * returned.
     *
     * <p>Additionally, if this PermissionCollection contains the
     * AllPermission, this method will always return true.
     * <p>
     * @param permission the Permission object to check.
     *
     * @return true if "permission" is implied by the permissions in the
     * PermissionCollection it
     * belongs to, false if not.
     */
    public boolean implies(Permission permission) {
        return false;
    }

    /** 
     * Returns an enumeration of all the Permission objects in all the
     * PermissionCollections in this Permissions object.
     *
     * @return an enumeration of all the Permissions.
     */
    public Enumeration elements() {
        return null;
    }

    private void readObject(ObjectInputStream in)
        throws IOException, ClassNotFoundException
    { }

    /** 
     * @serialData Default fields.
     */
    private void writeObject(ObjectOutputStream out) throws IOException { }

    private static final long serialVersionUID = 4858622370623524688L;

    /**
     * @serialField perms java.util.Hashtable
     *     A table of the Permission classes and PermissionCollections.
     * @serialField allPermission java.security.PermissionCollection
     */
    private static final ObjectStreamField[] serialPersistentFields = {
        new ObjectStreamField("perms", Hashtable.class),
	new ObjectStreamField("allPermission", PermissionCollection.class),
    };
}

/**
 * A PermissionsHash stores a homogeneous set of permissions in a hashtable.
 *
 * @see Permission
 * @see Permissions
 *
 * @version 1.46, 02/02/00
 *
 * @author Roland Schemers
 *
 * @serial include
 */

final class PermissionsHash extends PermissionCollection
implements Serializable
{
    /**
     * Create an empty PermissionsHash object.
     */

    PermissionsHash() {
    }

    /**
     * Adds a permission to the PermissionsHash.
     *
     * @param permission the Permission object to add.
     */

    public void add(Permission permission)
    { }

    /**
     * Check and see if this set of permissions implies the permissions 
     * expressed in "permission".
     *
     * @param permission the Permission object to compare
     *
     * @return true if "permission" is a proper subset of a permission in 
     * the set, false if not.
     */

    public boolean implies(Permission permission) 
    {
        return false;
    }

    /**
     * Returns an enumeration of all the Permission objects in the container.
     *
     * @return an enumeration of all the Permissions.
     */

    public Enumeration elements() {
        return null;
    }

    private static final long serialVersionUID = -8491988220802933440L;

    /**
     * @serialField perms java.util.Hashtable
     *     A table of the Permissions (both key and value are same).
     */
    private static final ObjectStreamField[] serialPersistentFields = {
        new ObjectStreamField("perms", Hashtable.class),
    };

    /**
     * @serialData Default fields.
     */
    /*
     * Writes the contents of the permsMap field out as a Hashtable for
     * serialization compatibility with earlier releases.
     */
    private void writeObject(ObjectOutputStream out) throws IOException {
    }

    /*
     * Reads in a Hashtable of Permission/Permission and saves them in the 
     * permsMap field. 
     */
    private void readObject(ObjectInputStream in) throws IOException, 
    ClassNotFoundException {
    }
}
