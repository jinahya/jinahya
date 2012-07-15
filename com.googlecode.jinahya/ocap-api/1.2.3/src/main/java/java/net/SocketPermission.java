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

import java.util.Enumeration;
import java.util.Vector;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;
import java.net.InetAddress;
import java.security.Permission;
import java.security.PermissionCollection;
import java.io.Serializable;
import java.io.ObjectStreamField;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;

/** 
 * This class represents access to a network via sockets.
 * A SocketPermission consists of a 
 * host specification and a set of "actions" specifying ways to
 * connect to that host. The host is specified as
 * <pre>
 *    host = (hostname | IPv4address | iPv6reference) [:portrange]
 *    portrange = portnumber | -portnumber | portnumber-[portnumber]
 * </pre>
 * The host is expressed as a DNS name, as a numerical IP address,
 * or as "localhost" (for the local machine).
 * The wildcard "*" may be included once in a DNS name host
 * specification. If it is included, it must be in the leftmost 
 * position, as in "*.sun.com".
 * <p>
 * The format of the IPv6reference should follow that specified in <a
 * href="http://www.ietf.org/rfc/rfc2732.txt"><i>RFC&nbsp;2732: Format
 * for Literal IPv6 Addresses in URLs</i></a>:
 * <pre>
 *    ipv6reference = "[" IPv6address "]"
 *</pre>
 * For example, you can construct a SocketPermission instance
 * as the following:
 * <pre>
 *    String hostAddress = inetaddress.getHostAddress();
 *    if (inetaddress instanceof Inet6Address) {
 *        sp = new SocketPermission("[" + hostAddress + "]:" + port, action);
 *    } else {
 *        sp = new SocketPermission(hostAddress + ":" + port, action);
 *    }
 * </pre>
 * or
 * <pre>
 *    String host = url.getHost();
 *    sp = new SocketPermission(host + ":" + port, action);
 * </pre>
 * <p>
 * The <A HREF="Inet6Address.html#lform">full uncompressed form</A> of
 * an IPv6 literal address is also valid.
 * <p>
 * The port or portrange is optional. A port specification of the 
 * form "N-", where <i>N</i> is a port number, signifies all ports
 * numbered <i>N</i> and above, while a specification of the
 * form "-N" indicates all ports numbered <i>N</i> and below.
 * <p>
 * The possible ways to connect to the host are 
 * <pre>
 * accept
 * connect
 * listen
 * resolve
 * </pre>
 * The "listen" action is only meaningful when used with "localhost". 
 * The "resolve" action is implied when any of the other actions are present.
 * The action "resolve" refers to host/ip name service lookups.
 * 
 * <p>As an example of the creation and meaning of SocketPermissions,  
 * note that if the following permission:
 * 
 * <pre>
 *   p1 = new SocketPermission("puffin.eng.sun.com:7777", "connect,accept");
 * </pre>
 * 
 * is granted to some code, it allows that code to connect to port 7777 on
 * <code>puffin.eng.sun.com</code>, and to accept connections on that port.
 * 
 * <p>Similarly, if the following permission:
 * 
 * <pre>
 *   p1 = new SocketPermission("puffin.eng.sun.com:7777", "connect,accept");
 *   p2 = new SocketPermission("localhost:1024-", "accept,connect,listen");
 * </pre>
 * 
 * is granted to some code, it allows that code to 
 * accept connections on, connect to, or listen on any port between
 * 1024 and 65535 on the local host.
 *
 * <p>Note: Granting code permission to accept or make connections to remote
 * hosts may be dangerous because malevolent code can then more easily
 * transfer and share confidential data among parties who may not
 * otherwise have access to the data.
 * 
 * @see java.security.Permissions
 * @see SocketPermission
 *
 * @version 1.41 10/27/00
 *
 * @author Marianne Mueller
 * @author Roland Schemers 
 *
 * @serial exclude
 */
public final class SocketPermission extends Permission implements Serializable
{
    /** 
     * the actions string. 
     *
     * @serial
     */
    private String actions;

    private static final long serialVersionUID = -7204263841984476862L;

    /** 
     * Creates a new SocketPermission object with the specified actions.
     * The host is expressed as a DNS name, or as a numerical IP address.
     * Optionally, a port or a portrange may be supplied (separated
     * from the DNS name or IP address by a colon).
     * <p>
     * To specify the local machine, use "localhost" as the <i>host</i>.
     * Also note: An empty <i>host</i> String ("") is equivalent to "localhost".
     * <p>
     * The <i>actions</i> parameter contains a comma-separated list of the
     * actions granted for the specified host (and port(s)). Possible actions are
     * "connect", "listen", "accept", "resolve", or 
     * any combination of those. "resolve" is automatically added
     * when any of the other three are specified.
     * <p>
     * Examples of SocketPermission instantiation are the following: 
     * <pre>
     *    nr = new SocketPermission("www.catalog.com", "connect");
     *    nr = new SocketPermission("www.sun.com:80", "connect");
     *    nr = new SocketPermission("*.sun.com", "connect");
     *    nr = new SocketPermission("*.edu", "resolve");
     *    nr = new SocketPermission("204.160.241.0", "connect");
     *    nr = new SocketPermission("localhost:1024-65535", "listen");
     *    nr = new SocketPermission("204.160.241.0:1024-65535", "connect");
     * </pre>
     * 
     * @param host the hostname or IPaddress of the computer, optionally
     * including a colon followed by a port or port range. 
     * @param action the action string.
     */
    public SocketPermission(String host, String action) { 
	super(host);    
    }

    /** 
     * Checks if this socket permission object "implies" the 
     * specified permission.
     * <P>
     * More specifically, this method first ensures that all of the following
     * are true (and returns false if any of them are not):<p>
     * <ul>
     * <li> <i>p</i> is an instanceof SocketPermission,<p>
     * <li> <i>p</i>'s actions are a proper subset of this
     * object's actions, and<p>
     * <li> <i>p</i>'s port range is included in this port range. Note:
     * port range is ignored when p only contains the action, 'resolve'.<p>
     * </ul>
     * 
     * Then <code>implies</code> checks each of the following, in order,
     * and for each returns true if the stated condition is true:<p>
     * <ul>
     * <li> If this object was initialized with a single IP address and one of <i>p</i>'s 
     * IP addresses is equal to this object's IP address.<p>
     * <li>If this object is a wildcard domain (such as *.sun.com), and
     * <i>p</i>'s canonical name (the name without any preceding *)
     * ends with this object's canonical host name. For example, *.sun.com
     * implies *.eng.sun.com..<p>
     * <li>If this object was not initialized with a single IP address, and one of this
     * object's IP addresses equals one of <i>p</i>'s IP addresses.<p>
     * <li>If this canonical name equals <i>p</i>'s canonical name.<p>
     * </ul>
     * 
     * If none of the above are true, <code>implies</code> returns false.
     * @param p the permission to check against.
     *
     * @return true if the specified permission is implied by this object,
     * false if not.  
     */
    public boolean implies(Permission p) {
        return false;
    }

    /** 
     * Checks two SocketPermission objects for equality. 
     * <P>
     * @param obj the object to test for equality with this object.
     * 
     * @return true if <i>obj</i> is a SocketPermission, and has the
     *  same hostname, port range, and actions as this
     *  SocketPermission object. However, port range will be ignored
     *  in the comparison if <i>obj</i> only contains the action, 'resolve'.
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
     * Returns the canonical string representation of the actions.
     * Always returns present actions in the following order: 
     * connect, listen, accept, resolve.  
     *
     * @return the canonical string representation of the actions.
     */
    public String getActions() {
        return null;
    }

    /** 
     * Returns a new PermissionCollection object for storing SocketPermission 
     * objects.
     * <p>
     * SocketPermission objects must be stored in a manner that allows them 
     * to be inserted into the collection in any order, but that also enables the 
     * PermissionCollection <code>implies</code>
     * method to be implemented in an efficient (and consistent) manner.
     *
     * @return a new PermissionCollection object suitable for storing SocketPermissions.
     */
    public java.security.PermissionCollection newPermissionCollection() {
        return null;
    }

    /** 
     * readObject is called to restore the state of the SocketPermission from
     * a stream.
     */
    private synchronized void readObject(ObjectInputStream s)
        throws IOException, ClassNotFoundException
    { }

    /** 
     * WriteObject is called to save the state of the SocketPermission 
     * to a stream. The actions are serialized, and the superclass
     * takes care of the name.
     */
    private synchronized void writeObject(ObjectOutputStream s)
        throws IOException
    { }
}


/**
 *
 * @see java.security.Permission
 * @see java.security.Permissions
 * @see java.security.PermissionCollection
 *
 * @version 1.41 10/27/00
 *
 * @author Roland Schemers
 *
 * @serial include
 */

final class SocketPermissionCollection extends PermissionCollection 
implements Serializable
{

    /**
     * Create an empty SocketPermissions object.
     *
     */

    public SocketPermissionCollection() {
    }

    /**
     * Adds a permission to the SocketPermissions. The key for the hash is
     * the name in the case of wildcards, or all the IP addresses.
     *
     * @param permission the Permission object to add.
     *
     * @exception IllegalArgumentException - if the permission is not a
     *                                       SocketPermission
     *
     * @exception SecurityException - if this SocketPermissionCollection object
     *                                has been marked readonly
     */

    public void add(Permission permission)
    { }

    /**
     * Check and see if this collection of permissions implies the permissions 
     * expressed in "permission".
     *
     * @param p the Permission object to compare
     *
     * @return true if "permission" is a proper subset of a permission in 
     * the collection, false if not.
     */

    public boolean implies(Permission permission) 
    {
	return false;
    }

    /**
     * Returns an enumeration of all the SocketPermission objects in the 
     * container.
     *
     * @return an enumeration of all the SocketPermission objects.
     */

    public Enumeration elements() {
        return null;
    }

    private static final long serialVersionUID = 2787186408602843674L;

    /**
     * @serialField permissions java.util.Vector
     *     A list of the SocketPermissions for this set.
     */
    private static final ObjectStreamField[] serialPersistentFields = {
        new ObjectStreamField("permissions", Vector.class),
    };

    /**
     * @serialData "permissions" field (a Vector containing the SocketPermissions).
     */
    private void writeObject(ObjectOutputStream out) throws IOException {
    }

    private void readObject(ObjectInputStream in) throws IOException, 
    ClassNotFoundException {
    }
}
