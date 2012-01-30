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


  


package java.net;

/** 
 * The class PasswordAuthentication is a data holder that is used by
 * Authenticator.  It is simply a repository for a user name and a password.
 *
 * @see java.net.Authenticator
 * @see java.net.Authenticator#getPasswordAuthentication()
 *
 * @author  Bill Foote
 * @version 1.10, 02/02/00
 * @since   1.2
 */
public final class PasswordAuthentication
{

    /** 
     * Creates a new <code>PasswordAuthentication</code> object from the given
     * user name and password.
     *
     * <p> Note that the given user password is cloned before it is stored in
     * the new <code>PasswordAuthentication</code> object.
     *
     * @param userName the user name
     * @param password the user's password
     */
    public PasswordAuthentication(String userName, char[] password) { }

    /** 
     * Returns the user name.
     *
     * @return the user name
     */
    public String getUserName() {
        return null;
    }

    /** 
     * Returns the user password.
     *
     * <p> Note that this method returns a reference to the password. It is
     * the caller's responsibility to zero out the password information after
     * it is no longer needed.
     *
     * @return the password
     */
    public char[] getPassword() {
        return null;
    }
}
