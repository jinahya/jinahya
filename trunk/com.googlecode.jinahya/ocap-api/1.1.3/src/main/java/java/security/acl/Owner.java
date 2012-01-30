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

import java.security.Principal;

/** 
 * Interface for managing owners of Access Control Lists (ACLs) or ACL 
 * configurations. (Note that the Acl interface in the 
 * <code> java.security.acl </code> package extends this Owner
 * interface.) The initial owner Principal should be specified as an 
 * argument to the constructor of the class implementing this interface.   
 *   
 * @see java.security.acl.Acl    
 *
 */
public interface Owner
{

    /** 
     * Adds an owner. Only owners can modify ACL contents. The caller 
     * principal must be an owner of the ACL in order to invoke this method.
     * That is, only an owner can add another owner. The initial owner is 
     * configured at ACL construction time. 
     * 
     * @param caller the principal invoking this method. It must be an owner 
     * of the ACL.
     * 
     * @param owner the owner that should be added to the list of owners.
     * 
     * @return true if successful, false if owner is already an owner.
     * @exception NotOwnerException if the caller principal is not an owner 
     * of the ACL.
     */
    public boolean addOwner(Principal caller, Principal owner)
        throws NotOwnerException;

    /** 
     * Deletes an owner. If this is the last owner in the ACL, an exception is 
     * raised.<p>
     * 
     * The caller principal must be an owner of the ACL in order to invoke 
     * this method. 
     * 
     * @param caller the principal invoking this method. It must be an owner 
     * of the ACL.
     * 
     * @param owner the owner to be removed from the list of owners.
     * 
     * @return true if the owner is removed, false if the owner is not part 
     * of the list of owners.
     * 
     * @exception NotOwnerException if the caller principal is not an owner 
     * of the ACL.
     * 
     * @exception LastOwnerException if there is only one owner left, so that
     * deleteOwner would leave the ACL owner-less.
     */
    public boolean deleteOwner(Principal caller, Principal owner)
        throws NotOwnerException, LastOwnerException;

    /** 
     * Returns true if the given principal is an owner of the ACL.
     * 
     * @param owner the principal to be checked to determine whether or not 
     * it is an owner.
     * 
     * @return true if the passed principal is in the list of owners, false 
     * if not.
     */
    public boolean isOwner(Principal owner);
}
