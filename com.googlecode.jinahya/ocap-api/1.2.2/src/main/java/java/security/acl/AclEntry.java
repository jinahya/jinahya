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


  


package java.security.acl;

import java.util.Enumeration;
import java.security.Principal;

/** 
 * This is the interface used for representing one entry in an Access
 * Control List (ACL).<p>
 *
 * An ACL can be thought of as a data structure with multiple ACL entry
 * objects. Each ACL entry object contains a set of permissions associated
 * with a particular principal. (A principal represents an entity such as
 * an individual user or a group). Additionally, each ACL entry is specified
 * as being either positive or negative. If positive, the permissions are
 * to be granted to the associated principal. If negative, the permissions
 * are to be denied. Each principal can have at most one positive ACL entry
 * and one negative entry; that is, multiple positive or negative ACL
 * entries are not allowed for any principal.
 *
 * Note: ACL entries are by default positive. An entry becomes a
 * negative entry only if the
 * {@link #setNegativePermissions() setNegativePermissions}
 * method is called on it.
 *
 * @see java.security.acl.Acl
 *
 * @author 	Satish Dharmaraj
 */
public interface AclEntry extends Cloneable
{

    /** 
     * Specifies the principal for which permissions are granted or denied
     * by this ACL entry. If a principal was already set for this ACL entry,
     * false is returned, otherwise true is returned.
     *
     * @param user the principal to be set for this entry.
     *
     * @return true if the principal is set, false if there was
     * already a principal set for this entry.
     *
     * @see #getPrincipal
     */
    public boolean setPrincipal(Principal user);

    /** 
     * Returns the principal for which permissions are granted or denied by
     * this ACL entry. Returns null if there is no principal set for this
     * entry yet.
     *
     * @return the principal associated with this entry.
     *
     * @see #setPrincipal
     */
    public Principal getPrincipal();

    /** 
     * Sets this ACL entry to be a negative one. That is, the associated
     * principal (e.g., a user or a group) will be denied the permission set
     * specified in the entry.
     *
     * Note: ACL entries are by default positive. An entry becomes a
     * negative entry only if this <code>setNegativePermissions</code>
     * method is called on it.
     */
    public void setNegativePermissions();

    /** 
     * Returns true if this is a negative ACL entry (one denying the
     * associated principal the set of permissions in the entry), false
     * otherwise.
     *
     * @return true if this is a negative ACL entry, false if it's not.
     */
    public boolean isNegative();

    /** 
     * Adds the specified permission to this ACL entry. Note: An entry can
     * have multiple permissions.
     *
     * @param permission the permission to be associated with
     * the principal in this entry.
     *
     * @return true if the permission was added, false if the
     * permission was already part of this entry's permission set.
     */
    public boolean addPermission(Permission permission);

    /** 
     * Removes the specified permission from this ACL entry.
     *
     * @param permission the permission to be removed from this entry.
     *
     * @return true if the permission is removed, false if the
     * permission was not part of this entry's permission set.
     */
    public boolean removePermission(Permission permission);

    /** 
     * Checks if the specified permission is part of the
     * permission set in this entry.
     *
     * @param permission the permission to be checked for.
     *
     * @return true if the permission is part of the
     * permission set in this entry, false otherwise.
     */
    public boolean checkPermission(Permission permission);

    /** 
     * Returns an enumeration of the permissions in this ACL entry.
     *
     * @return an enumeration of the permissions in this ACL entry.
     */
    public Enumeration permissions();

    /** 
     * Returns a string representation of the contents of this ACL entry.
     *
     * @return a string representation of the contents.
     */
    public String toString();

    /** 
     * Clones this ACL entry.
     *
     * @return a clone of this ACL entry.
     */
    public Object clone();
}
