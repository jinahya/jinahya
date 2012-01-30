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
 * This interface is used to represent a group of principals. (A principal
 * represents an entity such as an individual user or a company). <p>     
 *
 * Note that Group extends Principal. Thus, either a Principal or a Group can 
 * be passed as an argument to methods containing a Principal parameter. For 
 * example, you can add either a Principal or a Group to a Group object by 
 * calling the object's <code>addMember</code> method, passing it the 
 * Principal or Group.
 *
 * @author 	Satish Dharmaraj
 */
public interface Group extends Principal
{

    /** 
     * Adds the specified member to the group. 
     *  
     * @param user the principal to add to this group.
     * 
     * @return true if the member was successfully added, 
     * false if the principal was already a member.
     */
    public boolean addMember(Principal user);

    /** 
     * Removes the specified member from the group.
     * 
     * @param user the principal to remove from this group.
     * 
     * @return true if the principal was removed, or 
     * false if the principal was not a member.
     */
    public boolean removeMember(Principal user);

    /** 
     * Returns true if the passed principal is a member of the group. 
     * This method does a recursive search, so if a principal belongs to a 
     * group which is a member of this group, true is returned.
     * 
     * @param member the principal whose membership is to be checked.
     * 
     * @return true if the principal is a member of this group, 
     * false otherwise.
     */
    public boolean isMember(Principal member);

    /** 
     * Returns an enumeration of the members in the group.
     * The returned objects can be instances of either Principal 
     * or Group (which is a subclass of Principal).
     * 
     * @return an enumeration of the group members.
     */
    public Enumeration members();
}
