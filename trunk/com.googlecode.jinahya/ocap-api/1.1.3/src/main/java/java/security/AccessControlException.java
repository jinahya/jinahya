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

/** 
 * <p> This exception is thrown by the AccessController to indicate
 * that a requested access (to a critical system resource such as the
 * file system or the network) is denied.
 *
 * <p> The reason to deny access can vary.  For example, the requested
 * permission might be of an incorrect type,  contain an invalid
 * value, or request access that is not allowed according to the
 * security policy.  Such information should be given whenever
 * possible at the time the exception is thrown.
 *
 * @version 	1.9, 02/02/00
 * @author Li Gong
 * @author Roland Schemers
 */
public class AccessControlException extends SecurityException
{
    private Permission perm;

    /** 
     * Constructs an <code>AccessControlException</code> with the
     * specified, detailed message. 
     *
     * @param   s   the detail message.
     */
    public AccessControlException(String s) { }

    /** 
     * Constructs an <code>AccessControlException</code> with the
     * specified, detailed message, and the requested permission that caused
     * the exception. 
     *
     * @param   s   the detail message.
     * @param   p   the permission that caused the exception.
     */
    public AccessControlException(String s, Permission p) { }

    /** 
     * Gets the Permission object associated with this exeception, or
     * null if there was no corresponding Permission object.
     *
     * @return the Permission object.
     */
    public Permission getPermission() {
        return null;
    }
}
