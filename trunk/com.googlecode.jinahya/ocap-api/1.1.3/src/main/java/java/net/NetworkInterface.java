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

import java.net.SocketException;
import java.util.Enumeration;
import java.util.NoSuchElementException;
import java.security.AccessController;

/** 
 * This class represents a Network Interface made up of a name, 
 * and a list of IP addresses assigned to this interface.
 * It is used to identify the local interface on which a multicast group
 * is joined. 
 *
 * Interfaces are normally known by names such as "le0".
 *
 * @since 1.4
 */
public final class NetworkInterface
{

    /*
     * This hidden constructor does not necessarily correspond to
     * a constructor in the original source file -- it keeps javadoc
     * from generating an inappropriate default constructor.
     */
    private NetworkInterface() { }

    /** 
     * Get the name of this network interface.
     *
     * @return the name of this network interface
     */
    public String getName() {
        return null;
    }

    /** 
     * Convenience method to return an Enumeration with all or a
     * subset of the InetAddresses bound to this network interface.
     * <p>
     * If there is a security manager, its <code>checkConnect</code> 
     * method is called for each InetAddress. Only InetAddresses where
     * the <code>checkConnect</code> doesn't throw a SecurityException
     * will be returned in the Enumeration.
     * @return an Enumeration object with all or a subset of the InetAddresses
     * bound to this network interface
     */
    public Enumeration getInetAddresses() {
        return null;
    }

    /** 
     * Get the display name of this network interface.
     * A display name is a human readable String describing the network
     * device.
     *
     * @return the display name of this network interface, 
     *         or null if no display name is available.
     */
    public String getDisplayName() {
        return null;
    }

    /** 
     * Searches for the network interface with the specified name.
     *
     * @param   name 
     *		The name of the network interface.
     *
     * @return  A <tt>NetworkInterface</tt> with the specified name,
     *          or <tt>null</tt> if there is no network interface
     *		with the specified name.
     *
     * @throws	SocketException  
     *	        If an I/O error occurs.
     *
     * @throws  NullPointerException
     *		If the specified name is <tt>null</tt>.
     */
    public static NetworkInterface getByName(String name)
        throws java.net.SocketException
    {
        return null;
    }

    /** 
     * Convenience method to search for a network interface that
     * has the specified Internet Protocol (IP) address bound to
     * it.
     * <p>
     * If the specified IP address is bound to multiple network 
     * interfaces it is not defined which network interface is
     * returned.
     *
     * @param   addr
     *		The <tt>InetAddress</tt> to search with.
     *
     * @return  A <tt>NetworkInterface</tt> 
     *          or <tt>null</tt> if there is no network interface
     *          with the specified IP address.
     *
     * @throws  SocketException  
     *          If an I/O error occurs. 
     *
     * @throws  NullPointerException
     *          If the specified address is <tt>null</tt>.
     */
    public static NetworkInterface getByInetAddress(InetAddress addr)
        throws java.net.SocketException
    {
        return null;
    }

    /** 
     * Returns all the interfaces on this machine. Returns null if no
     * network interfaces could be found on this machine.
     * 
     * NOTE: can use getNetworkInterfaces()+getInetAddresses() 
     *       to obtain all IP addresses for this node
     *
     * @return an Enumeration of NetworkInterfaces found on this machine
     * @exception  SocketException  if an I/O error occurs.
     */
    public static Enumeration getNetworkInterfaces()
        throws java.net.SocketException
    {
        return null;
    }

    /** 
     * Compares this object against the specified object.
     * The result is <code>true</code> if and only if the argument is
     * not <code>null</code> and it represents the same NetworkInterface
     * as this object.
     * <p>
     * Two instances of <code>NetworkInterface</code> represent the same 
     * NetworkInterface if both name and addrs are the same for both.
     *
     * @param   obj   the object to compare against.
     * @return  <code>true</code> if the objects are the same;
     *          <code>false</code> otherwise.
     * @see     java.net.InetAddress#getAddress()
     */
    public boolean equals(Object obj) {
        return false;
    }

    public int hashCode() {
        return 0;
    }

    public String toString() {
        return null;
    }
}
