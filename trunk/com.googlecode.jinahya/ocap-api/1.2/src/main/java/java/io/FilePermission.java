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


  


package java.io;

import java.security.*;

import java.util.Enumeration;
import java.util.List;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.Collections;
import java.io.ObjectStreamField;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;

/** 
 * This class represents access to a file or directory.  A FilePermission consists
 * of a pathname and a set of actions valid for that pathname.
 * <P>
 * Pathname is the pathname of the file or directory granted the specified
 * actions. A pathname that ends in "/*" (where "/" is
 * the file separator character, <code>File.separatorChar</code>) indicates
 * all the files and directories contained in that directory. A pathname
 * that ends with "/-" indicates (recursively) all files
 * and subdirectories contained in that directory. A pathname consisting of
 * the special token "&lt;&lt;ALL FILES&gt;&gt;" matches <b>any</b> file.
 * <P>
 * Note: A pathname consisting of a single "*" indicates all the files
 * in the current directory, while a pathname consisting of a single "-" 
 * indicates all the files in the current directory and
 * (recursively) all files and subdirectories contained in the current 
 * directory.
 * <P>
 * The actions to be granted are passed to the constructor in a string containing 
 * a list of one or more comma-separated keywords. The possible keywords are
 * "read", "write", "execute", and "delete". Their meaning is defined as follows:
 * <P>
 * <DL> 
 *    <DT> read <DD> read permission
 *    <DT> write <DD> write permission
 *    <DT> execute 
 *    <DD> execute permission. Allows <code>Runtime.exec</code> to
 *         be called. Corresponds to <code>SecurityManager.checkExec</code>.
 *    <DT> delete
 *    <DD> delete permission. Allows <code>File.delete</code> to
 *         be called. Corresponds to <code>SecurityManager.checkDelete</code>.
 * </DL>
 * <P>
 * The actions string is converted to lowercase before processing.
 * <P>
 * Be careful when granting FilePermissions. Think about the implications 
 * of granting read and especially write access to various files and 
 * directories. The "&lt;&lt;ALL FILES>>" permission with write action is 
 * especially dangerous. This grants permission to write to the entire 
 * file system. One thing this effectively allows is replacement of the 
 * system binary, including the JVM runtime environment.
 * 
 * <p>Please note: Code can always read a file from the same
 * directory it's in (or a subdirectory of that directory); it does not
 * need explicit permission to do so.
 * 
 * @see java.security.Permission
 * @see java.security.Permissions
 * @see java.security.PermissionCollection
 *
 * @version 1.67 00/02/02
 *
 * @author Marianne Mueller
 * @author Roland Schemers
 * @since 1.2
 *
 * @serial exclude
 */
public final class FilePermission extends Permission implements Serializable
{
    /** 
     * the actions string. 
     *
     * @serial
     */
    private String actions;

    private static final long serialVersionUID = 7930732926638008763L;

    /** 
     * Creates a new FilePermission object with the specified actions.
     * <i>path</i> is the pathname of a
     * file or directory, and <i>actions</i> contains a comma-separated list of the
     * desired actions granted on the file or directory. Possible actions are
     * "read", "write", "execute", and "delete". 
     * 
     * <p>A pathname that ends in "/*" (where "/" is
     * the file separator character, <code>File.separatorChar</code>) indicates
     * a directory and all the files contained in that directory. A pathname
     * that ends with "/-" indicates a directory and (recursively) all files
     * and subdirectories contained in that directory. The special pathname
     * "&lt;&lt;ALL FILES&gt;&gt;" matches all files.
     * 
     * <p>A pathname consisting of a single "*" indicates all the files
     * in the current directory, while a pathname consisting of a single "-" 
     * indicates all the files in the current directory and
     * (recursively) all files and subdirectories contained in the current 
     * directory.
     * 
     * @param path the pathname of the file/directory.
     * @param actions the action string.
     */
    public FilePermission(String path, String actions) { 
        super(path);
    }

    /** 
     * Checks if this FilePermission object "implies" the specified permission.
     * <P>
     * More specifically, this method returns true if:<p>
     * <ul>
     * <li> <i>p</i> is an instanceof FilePermission,<p>
     * <li> <i>p</i>'s actions are a proper subset of this
     * object's actions, and <p>
     * <li> <i>p</i>'s pathname is implied by this object's
     *      pathname. For example, "/tmp/*" implies "/tmp/foo", since
     *      "/tmp/*" encompasses the "/tmp" directory and all files in that
     *      directory, including the one named "foo".
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
     * Checks two FilePermission objects for equality. Checks that <i>obj</i> is
     * a FilePermission, and has the same pathname and actions as this object.
     * <P>
     * @param obj the object we are testing for equality with this object.
     * @return true if obj is a FilePermission, and has the same pathname and
     * actions as this FilePermission object.
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
     * Returns the "canonical string representation" of the actions.
     * That is, this method always returns present actions in the following order: 
     * read, write, execute, delete. For example, if this FilePermission object
     * allows both write and read actions, a call to <code>getActions</code>
     * will return the string "read,write".
     *
     * @return the canonical string representation of the actions.
     */
    public String getActions() {
        return null;
    }

    /** 
     * Returns a new PermissionCollection object for storing FilePermission 
     * objects.
     * <p>
     * FilePermission objects must be stored in a manner that allows them 
     * to be inserted into the collection in any order, but that also enables the 
     * PermissionCollection <code>implies</code>
     * method to be implemented in an efficient (and consistent) manner.
     * 
     * <p>For example, if you have two FilePermissions:
     * <OL>
     * <LI>  <code>"/tmp/-", "read"</code>
     * <LI>  <code>"/tmp/scratch/foo", "write"</code>
     * </OL>
     * 
     * <p>and you are calling the <code>implies</code> method with the FilePermission: 
     * 
     * <pre>
     *   "/tmp/scratch/foo", "read,write", 
     * </pre>
     * 
     * then the <code>implies</code> function must
     * take into account both the "/tmp/-" and "/tmp/scratch/foo"
     * permissions, so the effective permission is "read,write",
     * and <code>implies</code> returns true. The "implies" semantics for 
     * FilePermissions are handled properly by the PermissionCollection object
     * returned by this <code>newPermissionCollection</code> method.
     *
     * @return a new PermissionCollection object suitable for storing 
     * FilePermissions.
     */
    public PermissionCollection newPermissionCollection() {
        return null;
    }

    /** 
     * readObject is called to restore the state of the FilePermission from
     * a stream.
     */
    private void readObject(java.io.ObjectInputStream s)
        throws java.io.IOException, ClassNotFoundException
    { }

    /** 
     * WriteObject is called to save the state of the FilePermission 
     * to a stream. The actions are serialized, and the superclass
     * takes care of the name.
     */
    private void writeObject(java.io.ObjectOutputStream s)
        throws java.io.IOException
    { }
}

/**
 * A FilePermissionCollection stores a set of FilePermission permissions. 
 * FilePermission objects
 * must be stored in a manner that allows them to be inserted in any
 * order, but enable the implies function to evaluate the implies
 * method.
 * For example, if you have two FilePermissions:
 * <OL>
 * <LI> "/tmp/-", "read"
 * <LI> "/tmp/scratch/foo", "write"
 * </OL>
 * And you are calling the implies function with the FilePermission: 
 * "/tmp/scratch/foo", "read,write", then the implies function must
 * take into account both the /tmp/- and /tmp/scratch/foo
 * permissions, so the effective permission is "read,write".
 * 
 * @see java.security.Permission
 * @see java.security.Permissions
 * @see java.security.PermissionCollection
 *
 * @version 1.67 02/02/00
 *
 * @author Marianne Mueller
 * @author Roland Schemers
 *
 * @serial include
 *
 */

final class FilePermissionCollection extends PermissionCollection
implements Serializable {

    /**
     * Create an empty FilePermissions object.
     *
     */

    public FilePermissionCollection() { }

    /**
     * Adds a permission to the FilePermissions. The key for the hash is
     * permission.path.
     *
     * @param permission the Permission object to add.
     *
     * @exception IllegalArgumentException - if the permission is not a
     *                                       FilePermission 
     *
     * @exception SecurityException - if this FilePermissionCollection object
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
     * @return true if "permission" is a proper subset of a permission in 
     * the set, false if not.
     */

    public boolean implies(Permission permission) 
    {
	return false;
    }

    /**
     * Returns an enumeration of all the FilePermission objects in the 
     * container.
     *
     * @return an enumeration of all the FilePermission objects.
     */

    public Enumeration elements() {
	return null;
    }

    private static final long serialVersionUID = 2202956749081564585L;

    /**
     * @serialField permissions java.util.Vector
     *     A list of FilePermission objects.
     */
    private static final ObjectStreamField[] serialPersistentFields = {
        new ObjectStreamField("permissions", Vector.class),
    };

    /**
     * @serialData "permissions" field (a Vector containing the FilePermissions).
     */
    private void writeObject(ObjectOutputStream out) throws IOException {
    }

    /*
     * Reads in a Vector of FilePermissions and saves them in the perms field.
     */
    private void readObject(ObjectInputStream in) throws IOException, 
    ClassNotFoundException {
    }
}
