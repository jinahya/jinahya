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
import java.util.Hashtable;
import java.util.StringTokenizer;

/** 
 * This class is for Serializable permissions. A SerializablePermission
 * contains a name (also referred to as a "target name") but
 * no actions list; you either have the named permission
 * or you don't.
 *
 * <P>
 * The target name is the name of the Serializable permission (see below).
 *
 * <P>
 * The following table lists all the possible SerializablePermission target names,
 * and for each provides a description of what the permission allows
 * and a discussion of the risks of granting code the permission.
 * <P>
 *
 * <table border=1 cellpadding=5 summary="Permission target name, what the permission allows, and associated risks">
 * <tr>
 * <th>Permission Target Name</th>
 * <th>What the Permission Allows</th>
 * <th>Risks of Allowing this Permission</th>
 * </tr>
 *
 * <tr>
 *   <td>enableSubclassImplementation</td>
 *   <td>Subclass implementation of ObjectOutputStream or ObjectInputStream
 * to override the default serialization or deserialization, respectively,
 * of objects</td>
 *   <td>Code can use this to serialize or
 * deserialize classes in a purposefully malfeasant manner. For example,
 * during serialization, malicious code can use this to
 * purposefully store confidential private field data in a way easily accessible
 * to attackers. Or, during deserializaiton it could, for example, deserialize
 * a class with all its private fields zeroed out.</td>
 * </tr>
 *
 * <tr>
 *   <td>enableSubstitution</td>
 *   <td>Substitution of one object for another during
 * serialization or deserialization</td>
 *   <td>This is dangerous because malicious code
 * can replace the actual object with one which has incorrect or
 * malignant data.</td>
 * </tr>
 *
 * </table>
 *
 * @see java.security.BasicPermission
 * @see java.security.Permission
 * @see java.security.Permissions
 * @see java.security.PermissionCollection
 * @see java.lang.SecurityManager
 *
 * @version 1.13, 02/02/00
 *
 * @author Joe Fialli
 * @since 1.2
 */
public final class SerializablePermission extends BasicPermission
{
    /** 
     * @serial
     */
    private String actions;

    /** 
     * Creates a new SerializablePermission with the specified name.
     * The name is the symbolic name of the SerializablePermission, such as
     * "enableSubstitution", etc.
     *
     * @param name the name of the SerializablePermission.
     */
    public SerializablePermission(String name) { 
        super(name);
    }

    /** 
     * Creates a new SerializablePermission object with the specified name.
     * The name is the symbolic name of the SerializablePermission, and the
     * actions String is currently unused and should be null.
     *
     * @param name the name of the SerializablePermission.
     * @param actions currently unused and must be set to null
     */
    public SerializablePermission(String name, String actions) { 
        super(name, actions);
    }
}
