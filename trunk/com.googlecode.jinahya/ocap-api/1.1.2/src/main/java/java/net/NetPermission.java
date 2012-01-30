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

import java.security.*;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.StringTokenizer;

/** 
 * This class is for various network permissions.
 * A NetPermission contains a name (also referred to as a "target name") but
 * no actions list; you either have the named permission
 * or you don't.
 * <P>
 * The target name is the name of the network permission (see below). The naming
 * convention follows the  hierarchical property naming convention.
 * Also, an asterisk
 * may appear at the end of the name, following a ".", or by itself, to
 * signify a wildcard match. For example: "foo.*" or "*" is valid,
 * "*foo" or "a*b" is not valid.
 * <P>
 * The following table lists all the possible NetPermission target names,
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
 *   <td>setDefaultAuthenticator</td>
 *   <td>The ability to set the
 * way authentication information is retrieved when
 * a proxy or HTTP server asks for authentication</td>
 *   <td>Malicious
 * code can set an authenticator that monitors and steals user
 * authentication input as it retrieves the input from the user.</td>
 * </tr>
 *
 * <tr>
 *   <td>requestPasswordAuthentication</td>
 *   <td>The ability
 * to ask the authenticator registered with the system for
 * a password</td>
 *   <td>Malicious code may steal this password.</td>
 * </tr>
 *
 * <tr>
 *   <td>specifyStreamHandler</td>
 *   <td>The ability
 * to specify a stream handler when constructing a URL</td>
 *   <td>Malicious code may create a URL with resources that it would
 *normally not have access to (like file:/foo/fum/), specifying a
 *stream handler that gets the actual bytes from someplace it does 
 *have access to. Thus it might be able to trick the system into
 *creating a ProtectionDomain/CodeSource for a class even though
 *that class really didn't come from that location.</td>
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
 * @version 1.39 00/02/02
 *
 * @author Marianne Mueller
 * @author Roland Schemers
 */
public final class NetPermission extends BasicPermission
{
    private static final long serialVersionUID = -8343910153355041693L;

    /** 
     * Creates a new NetPermission with the specified name.
     * The name is the symbolic name of the NetPermission, such as
     * "setDefaultAuthenticator", etc. An asterisk
     * may appear at the end of the name, following a ".", or by itself, to
     * signify a wildcard match.
     *
     * @param name the name of the NetPermission.
     */
    public NetPermission(String name) { 
        super(name);
    }

    /** 
     * Creates a new NetPermission object with the specified name.
     * The name is the symbolic name of the NetPermission, and the
     * actions String is currently unused and should be null.
     *
     * @param name the name of the NetPermission.
     * @param actions should be null.
     */
    public NetPermission(String name, String actions) { 
        super(name, actions);
    }
}
