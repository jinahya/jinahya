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

import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.InvalidObjectException;

/** 
 * This class implements an IP Socket Address (IP address + port number)
 * It can also be a pair (hostname + port number), in which case an attempt
 * will be made to resolve the hostname. If resolution fails then the address
 * is said to be <I>unresolved</I> but can still be used on some circumstances
 * like connecting through a proxy.
 * <p>
 * It provides an immutable object used by sockets for binding, connecting, or
 * as returned values.
 * <p>
 * The <i>wildcard</i> is a special local IP address. It usually means "any"
 * and can only be used for <code>bind</code> operations.
 *
 * @see	java.net.Socket
 * @see	java.net.ServerSocket
 * @since 1.4
 */
public class InetSocketAddress extends SocketAddress
{
    private String hostname;

    private InetAddress addr;

    private int port;

    private static final long serialVersionUID = 5076001401234631237L;

    /** 
     * Creates a socket address where the IP address is the wildcard address
     * and the port number a specified value.
     * <p>
     * A valid port value is between 0 and 65535.
     * A port number of <code>zero</code> will let the system pick up an
     * ephemeral port in a <code>bind</code> operation.
     * <p>
     * @param	port	The port number
     * @throws IllegalArgumentException if the port parameter is outside the specified
     * range of valid port values. 
     */
    public InetSocketAddress(int port) { }

    /** 
     * Creates a socket address from an IP address and a port number.
     * <p>
     * A valid port value is between 0 and 65535.
     * A port number of <code>zero</code> will let the system pick up an
     * ephemeral port in a <code>bind</code> operation.
     * <P>
     * A <code>null</code> address will assign the <i>wildcard</i> address.
     * <p>
     * @param	addr	The IP address
     * @param	port	The port number
     * @throws IllegalArgumentException if the port parameter is outside the specified
     * range of valid port values.
     */
    public InetSocketAddress(InetAddress addr, int port) { }

    /** 
     * Creates a socket address from a hostname and a port number.
     * <p>
     * An attempt will be made to resolve the hostname into an InetAddress.
     * If that attempt fails, the address will be flagged as <I>unresolved</I>.
     * <p>
     * A valid port value is between 0 and 65535.
     * A port number of <code>zero</code> will let the system pick up an
     * ephemeral port in a <code>bind</code> operation.
     * <P>
     * @param	hostname the Host name
     * @param	port	The port number
     * @throws IllegalArgumentException if the port parameter is outside the range
     * of valid port values, or if the hostname parameter is <TT>null</TT>.
     * @see	#isUnresolved()
     */
    public InetSocketAddress(String hostname, int port) { }

    /** 
     * Gets the port number.
     *
     * @return the port number.
     */
    public final int getPort() {
        return 0;
    }

    /** 
     * 
     * Gets the <code>InetAddress</code>.
     *
     * @return the InetAdress or <code>null</code> if it is unresolved.
     */
    public final InetAddress getAddress() {
        return null;
    }

    /** 
     * Gets the <code>hostname</code>.
     *
     * @return	the hostname part of the address.
     */
    public final String getHostName() {
        return null;
    }

    /** 
     * Checks wether the address has been resolved or not.
     *
     * @return <code>true</code> if the hostname couldn't be resolved into
     *		an <code>InetAddress</code>.
     */
    public final boolean isUnresolved() {
        return false;
    }

    /** 
     * Constructs a string representation of this InetSocketAddress.
     * This String is constructed by calling toString() on the InetAddress
     * and concatenating the port number (with a colon). If the address
     * is unresolved then the part before the colon will only contain the hostname.
     *
     * @return  a string representation of this object.
     */
    public String toString() {
        return null;
    }

    /** 
     * Compares this object against the specified object.
     * The result is <code>true</code> if and only if the argument is
     * not <code>null</code> and it represents the same address as
     * this object.
     * <p>
     * Two instances of <code>InetSocketAddress</code> represent the same
     * address if both the InetAddresses (or hostnames if it is unresolved) and port
     * numbers are equal.
     * If both addresses are unresolved, then the hostname & the port number
     * are compared.
     *
     * @param   obj   the object to compare against.
     * @return  <code>true</code> if the objects are the same;
     *          <code>false</code> otherwise.
     * @see java.net.InetAddress#equals(java.lang.Object)
     */
    public final boolean equals(Object obj) {
        return false;
    }

    /** 
     * Returns a hashcode for this socket address.
     *
     * @return  a hash code value for this socket address.
     */
    public final int hashCode() {
        return 0;
    }

    private void readObject(ObjectInputStream s)
        throws IOException, ClassNotFoundException
    { }
}
