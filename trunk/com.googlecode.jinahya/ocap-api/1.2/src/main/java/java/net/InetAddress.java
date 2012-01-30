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

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Random;
import java.util.Iterator;
import java.util.LinkedList;
import java.security.AccessController;
import java.io.ObjectStreamException;

/** 
 * This class represents an Internet Protocol (IP) address.
 *
 * <p> An IP address is either a 32-bit or 128-bit unsigned number
 * used by IP, a lower-level protocol on which protocols like UDP and
 * TCP are built. The IP address architecture is defined by <a
 * href="http://www.ietf.org/rfc/rfc790.txt"><i>RFC&nbsp;790:
 * Assigned Numbers</i></a>, <a
 * href="http://www.ietf.org/rfc/rfc1918.txt"> <i>RFC&nbsp;1918:
 * Address Allocation for Private Internets</i></a>, <a
 * href="http://www.ietf.org/rfc/rfc2365.txt"><i>RFC&nbsp;2365:
 * Administratively Scoped IP Multicast</i></a>, and <a
 * href="http://www.ietf.org/rfc/rfc2373.txt"><i>RFC&nbsp;2373: IP
 * Version 6 Addressing Architecture</i></a>. An instance of an
 * InetAddress consists of an IP address and possibly its
 * corresponding host name (depending on whether it is constructed
 * with a host name or whether it has already done reverse host name
 * resolution).
 *
 * <h4> Address types </h4>
 *
 * <blockquote><table cellspacing=2 summary="Description of unicast and multicast address types">
 *   <tr><th valign=top><i>unicast</i></th>
 *       <td>An identifier for a single interface. A packet sent to
 *         a unicast address is delivered to the interface identified by
 *         that address.
 *
 *         <p> The Unspecified Address -- Also called anylocal or wildcard 
 *         address. It must never be assigned to any node. It indicates the
 *         absence of an address. One example of its use is as the target of
 *         bind, which allows a server to accept a client connection on any
 *         interface, in case the server host has multiple interfaces.
 *
 *         <p> The <i>unspecified</i> address must not be used as
 *         the destination address of an IP packet.
 *        
 *         <p> The <i>Loopback</i> Addresses -- This is the address
 *         assigned to the loopback interface. Anything sent to this
 *         IP address loops around and becomes IP input on the local
 *         host. This address is often used when testing a
 *         client.</td></tr>
 *   <tr><th valign=top><i>multicast</i></th>
 *       <td>An identifier for a set of interfaces (typically belonging 
 *         to different nodes). A packet sent to a multicast address is
 *         delivered to all interfaces identified by that address.</td></tr>
 * </table></blockquote>
 *
 * <h4> IP address scope </h4>
 *
 * <p> <i>Link-local</i> addresses are designed to be used for addressing
 * on a single link for purposes such as auto-address configuration,
 * neighbor discovery, or when no routers are present. 
 *
 * <p> <i>Site-local</i> addresses are designed to be used for addressing
 * inside of a site without the need for a global prefix.
 *
 * <p> <i>Global</i> addresses are unique across the internet.
 *
 * <h4> Textual representation of IP addresses </h4>
 * 
 * The textual representation of an IP address is address family specific.
 *
 * <p>
 *
 * For IPv4 address format, please refer to <A
 * HREF="Inet4Address.html#format">Inet4Address#format</A>; For IPv6
 * address format, please refer to <A
 * HREF="Inet6Address.html#format">Inet6Address#format</A>.
 *
 * <h4> Host Name Resolution </h4>
 * 
 * Host name-to-IP address <i>resolution</i> is accomplished through
 * the use of a combination of local machine configuration information
 * and network naming services such as the Domain Name System (DNS)
 * and Network Information Service(NIS). The particular naming
 * services(s) being used is by default the local machine configured
 * one. For any host name, its corresponding IP address is returned.
 *
 * <p> <i>Reverse name resolution</i> means that for any IP address,
 * the host associated with the IP address is returned.
 *
 * <p> The InetAddress class provides methods to resolve host names to
 * their IP addresses and vise versa.
 *
 * <h4> InetAddress Caching </h4>
 *
 * The InetAddress class has a cache to store successful as well as
 * unsuccessful host name resolutions. The positive caching is there
 * to guard against DNS spoofing attacks; while the negative caching
 * is used to improve performance.
 *
 * <p> By default, the result of positive host name resolutions are
 * cached forever, because there is no general rule to decide when it
 * is safe to remove cache entries. The result of unsuccessful host
 * name resolution is cached for a very short period of time (10
 * seconds) to improve performance.
 *
 * <p> Under certain circumstances where it can be determined that DNS
 * spoofing attacks are not possible, a Java security property can be
 * set to a different Time-to-live (TTL) value for positive
 * caching. Likewise, a system admin can configure a different
 * negative caching TTL value when needed.
 *
 * <p> Two Java security properties control the TTL values used for
 *  positive and negative host name resolution caching:
 * 
 * <blockquote>
 * <dl>
 * <dt><b>networkaddress.cache.ttl</b> (default: -1)</dt>
 * <dd>Indicates the caching policy for successful name lookups from
 * the name service. The value is specified as as integer to indicate
 * the number of seconds to cache the successful lookup.
 * <p>
 * A value of -1 indicates "cache forever".
 * </dt><p>
 * <p>
 * <dt><b>networkaddress.cache.negative.ttl</b> (default: 10)</dt>
 * <dd>Indicates the caching policy for un-successful name lookups
 * from the name service. The value is specified as as integer to
 * indicate the number of seconds to cache the failure for
 * un-successful lookups.
 * <p>
 * A value of 0 indicates "never cache".
 * A value of -1 indicates "cache forever".
 * </dd>
 * </dl>
 * </blockquote>
 *
 * @author  Chris Warth
 * @version 1.85, 05/14/05
 * @see     java.net.InetAddress#getByAddress(byte[])
 * @see     java.net.InetAddress#getByAddress(java.lang.String, byte[])
 * @see     java.net.InetAddress#getAllByName(java.lang.String)
 * @see     java.net.InetAddress#getByName(java.lang.String)
 * @see     java.net.InetAddress#getLocalHost()
 * @since JDK1.0
 */
public class InetAddress implements java.io.Serializable
{
    /** 
     * @serial
     */
     String hostName;

    /** 
     * Holds a 32-bit IPv4 address.
     *
     * @serial
     */
     int address;

    /** 
     * Specifies the address family type, for instance, '1' for IPv4
     * addresses, and '2' for IPv6 addresses.
     *
     * @serial
     */
     int family;

    /** use serialVersionUID from JDK 1.0.2 for interoperability */
    private static final long serialVersionUID = 3286316764910316507L;

    /*
     * This hidden constructor does not necessarily correspond to
     * a constructor in the original source file -- it keeps javadoc
     * from generating an inappropriate default constructor.
     */
    InetAddress() { }

    /** 
     * Utility routine to check if the InetAddress is an
     * IP multicast address.
     * @return a <code>boolean</code> indicating if the InetAddress is 
     * an IP multicast address
     * @since   JDK1.1
     */
    public boolean isMulticastAddress() {
        return false;
    }

    /** 
     * Utility routine to check if the InetAddress in a wildcard address.
     * @return a <code>boolean</code> indicating if the Inetaddress is
     *         a wildcard address.
     * @since 1.4
     */
    public boolean isAnyLocalAddress() {
        return false;
    }

    /** 
     * Utility routine to check if the InetAddress is a loopback address. 
     *
     * @return a <code>boolean</code> indicating if the InetAddress is 
     * a loopback address; or false otherwise.
     * @since 1.4
     */
    public boolean isLoopbackAddress() {
        return false;
    }

    /** 
     * Utility routine to check if the InetAddress is an link local address. 
     *
     * @return a <code>boolean</code> indicating if the InetAddress is 
     * a link local address; or false if address is not a link local unicast address.
     * @since 1.4
     */
    public boolean isLinkLocalAddress() {
        return false;
    }

    /** 
     * Utility routine to check if the InetAddress is a site local address. 
     *
     * @return a <code>boolean</code> indicating if the InetAddress is 
     * a site local address; or false if address is not a site local unicast address.
     * @since 1.4
     */
    public boolean isSiteLocalAddress() {
        return false;
    }

    /** 
     * Utility routine to check if the multicast address has global scope.
     *
     * @return a <code>boolean</code> indicating if the address has 
     *         is a multicast address of global scope, false if it is not 
     *         of global scope or it is not a multicast address
     * @since 1.4
     */
    public boolean isMCGlobal() {
        return false;
    }

    /** 
     * Utility routine to check if the multicast address has node scope.
     *
     * @return a <code>boolean</code> indicating if the address has 
     *         is a multicast address of node-local scope, false if it is not 
     *         of node-local scope or it is not a multicast address
     * @since 1.4
     */
    public boolean isMCNodeLocal() {
        return false;
    }

    /** 
     * Utility routine to check if the multicast address has link scope.
     *
     * @return a <code>boolean</code> indicating if the address has 
     *         is a multicast address of link-local scope, false if it is not 
     *         of link-local scope or it is not a multicast address
     * @since 1.4
     */
    public boolean isMCLinkLocal() {
        return false;
    }

    /** 
     * Utility routine to check if the multicast address has site scope.
     *
     * @return a <code>boolean</code> indicating if the address has 
     *         is a multicast address of site-local scope, false if it is not 
     *         of site-local scope or it is not a multicast address
     * @since 1.4
     */
    public boolean isMCSiteLocal() {
        return false;
    }

    /** 
     * Utility routine to check if the multicast address has organization scope.
     *
     * @return a <code>boolean</code> indicating if the address has 
     *         is a multicast address of organization-local scope, 
     *         false if it is not of organization-local scope 
     *         or it is not a multicast address
     * @since 1.4
     */
    public boolean isMCOrgLocal() {
        return false;
    }

    /** 
     * Gets the host name for this IP address.
     *
     * <p>If this InetAddress was created with a host name,
     * this host name will be remembered and returned; 
     * otherwise, a reverse name lookup will be performed
     * and the result will be returned based on the system 
     * configured name lookup service. If a lookup of the name service
     * is required, call 
     * {@link #getCanonicalHostName() getCanonicalHostName}.
     *
     * <p>If there is a security manager, its
     * <code>checkConnect</code> method is first called
     * with the hostname and <code>-1</code> 
     * as its arguments to see if the operation is allowed.
     * If the operation is not allowed, it will return
     * the textual representation of the IP address.
     *
     * @return  the host name for this IP address, or if the operation
     *    is not allowed by the security check, the textual 
     *    representation of the IP address.
     * 
     * @see InetAddress#getCanonicalHostName
     * @see SecurityManager#checkConnect
     */
    public String getHostName() {
        return null;
    }

    /** 
     * Gets the fully qualified domain name for this IP address.
     * Best effort method, meaning we may not be able to return 
     * the FQDN depending on the underlying system configuration.
     *
     * <p>If there is a security manager, this method first
     * calls its <code>checkConnect</code> method
     * with the hostname and <code>-1</code> 
     * as its arguments to see if the calling code is allowed to know
     * the hostname for this IP address, i.e., to connect to the host.
     * If the operation is not allowed, it will return
     * the textual representation of the IP address.
     * 
     * @return  the fully qualified domain name for this IP address, 
     *    or if the operation is not allowed by the security check,
     *    the textual representation of the IP address.
     *
     * @see SecurityManager#checkConnect
     *
     * @since 1.4
     */
    public String getCanonicalHostName() {
        return null;
    }

    /** 
     * Returns the raw IP address of this <code>InetAddress</code>
     * object. The result is in network byte order: the highest order
     * byte of the address is in <code>getAddress()[0]</code>.
     *
     * @return  the raw IP address of this object.
     */
    public byte[] getAddress() {
        return null;
    }

    /** 
     * Returns the IP address string in textual presentation.
     *
     * @return  the raw IP address in a string format.
     * @since   JDK1.0.2
     */
    public String getHostAddress() {
        return null;
    }

    /** 
     * Returns a hashcode for this IP address.
     *
     * @return  a hash code value for this IP address.
     */
    public int hashCode() {
        return 0;
    }

    /** 
     * Compares this object against the specified object.
     * The result is <code>true</code> if and only if the argument is
     * not <code>null</code> and it represents the same IP address as
     * this object.
     * <p>
     * Two instances of <code>InetAddress</code> represent the same IP
     * address if the length of the byte arrays returned by
     * <code>getAddress</code> is the same for both, and each of the
     * array components is the same for the byte arrays.
     *
     * @param   obj   the object to compare against.
     * @return  <code>true</code> if the objects are the same;
     *          <code>false</code> otherwise.
     * @see     java.net.InetAddress#getAddress()
     */
    public boolean equals(Object obj) {
        return false;
    }

    /** 
     * Converts this IP address to a <code>String</code>. The 
     * string returned is of the form: hostname / literal IP 
     * address.
     *
     * If the host name is unresolved, no reverse name service loopup
     * is performed. The hostname part will be represented by an empty string.
     *
     * @return  a string representation of this IP address.
     */
    public String toString() {
        return null;
    }

    /** 
     * Create an InetAddress based on the provided host name and IP address
     * No name service is checked for the validity of the address. 
     *
     * <p> The host name can either be a machine name, such as
     * "<code>java.sun.com</code>", or a textual representation of its IP
     * address.
     *
     * <p> For <code>host</code> specified in literal IPv6 address,
     * either the form defined in RFC 2732 or the literal IPv6 address
     * format defined in RFC 2373 is accepted.
     *
     * <p> If addr specifies an IPv4 address an instance of Inet4Address 
     * will be returned; otherwise, an instance of Inet6Address 
     * will be returned.
     *
     * <p> IPv4 address byte array must be 4 bytes long and IPv6 byte array 
     * must be 16 bytes long
     *
     * @param host the specified host
     * @param addr the raw IP address in network byte order
     * @return  an InetAddress object created from the raw IP address.
     * @exception  UnknownHostException  if IP address is of illegal length
     * @since 1.4
     */
    public static InetAddress getByAddress(String host, byte[] addr)
        throws UnknownHostException
    {
        return null;
    }

    /** 
     * Determines the IP address of a host, given the host's name.
     *
     * <p> The host name can either be a machine name, such as
     * "<code>java.sun.com</code>", or a textual representation of its
     * IP address. If a literal IP address is supplied, only the
     * validity of the address format is checked.
     *
     * <p> For <code>host</code> specified in literal IPv6 address,
     * either the form defined in RFC 2732 or the literal IPv6 address
     * format defined in RFC 2373 is accepted.
     *
     * <p> If the host is <tt>null</tt> then an <tt>InetAddress</tt>
     * representing an address of the loopback interface is returned.
     * See <a href="http://www.ietf.org/rfc/rfc3330.txt">RFC&nbsp;3330</a>
     * section&nbsp;2 and <a href="http://www.ietf.org/rfc/rfc2373.txt">RFC&nbsp;2373</a>
     * section&nbsp;2.5.3. </p>
     *
     * @param      host   the specified host, or <code>null</code>.
     * @return     an IP address for the given host name.
     * @exception  UnknownHostException  if no IP address for the
     *               <code>host</code> could be found.
     * @exception  SecurityException if a security manager exists
     *             and its checkConnect method doesn't allow the operation
     */
    public static InetAddress getByName(String host) throws UnknownHostException
    {
        return null;
    }

    /** 
     * Given the name of a host, returns an array of its IP addresses,
     * based on the configured name service on the system.
     * 
     * <p> The host name can either be a machine name, such as
     * "<code>java.sun.com</code>", or a textual representation of its IP
     * address. If a literal IP address is supplied, only the
     * validity of the address format is checked.
     *
     * <p> For <code>host</code> specified in literal IPv6 address,
     * either the form defined in RFC 2732 or the literal IPv6 address
     * format defined in RFC 2373 is accepted.
     *
     * <p> If the host is <tt>null</tt> then an <tt>InetAddress</tt>
     * representing an address of the loopback interface is returned.
     * See <a href="http://www.ietf.org/rfc/rfc3330.txt">RFC&nbsp;3330</a>
     * section&nbsp;2 and <a href="http://www.ietf.org/rfc/rfc2373.txt">RFC&nbsp;2373</a>
     * section&nbsp;2.5.3. </p>
     *
     * <p> If there is a security manager and <code>host</code> is not 
     * null and <code>host.length() </code> is not equal to zero, the
     * security manager's
     * <code>checkConnect</code> method is called
     * with the hostname and <code>-1</code> 
     * as its arguments to see if the operation is allowed.
     *
     * @param      host   the name of the host, or <code>null</code>.
     * @return     an array of all the IP addresses for a given host name.
     * 
     * @exception  UnknownHostException  if no IP address for the
     *               <code>host</code> could be found.
     * @exception  SecurityException  if a security manager exists and its  
     *               <code>checkConnect</code> method doesn't allow the operation.
     * 
     * @see SecurityManager#checkConnect
     */
    public static InetAddress[] getAllByName(String host)
        throws UnknownHostException
    {
        return null;
    }

    /** 
     * Returns an <code>InetAddress</code> object given the raw IP address . 
     * The argument is in network byte order: the highest order
     * byte of the address is in <code>getAddress()[0]</code>.
     *
     * <p> This method doesn't block, i.e. no reverse name service lookup
     * is performed.
     *
     * <p> IPv4 address byte array must be 4 bytes long and IPv6 byte array 
     * must be 16 bytes long
     *
     * @param addr the raw IP address in network byte order
     * @return  an InetAddress object created from the raw IP address.
     * @exception  UnknownHostException  if IP address is of illegal length
     * @since 1.4
     */
    public static InetAddress getByAddress(byte[] addr)
        throws UnknownHostException
    {
        return null;
    }

    /** 
     * Returns the local host.
     *
     * <p>If there is a security manager, its
     * <code>checkConnect</code> method is called
     * with the local host name and <code>-1</code> 
     * as its arguments to see if the operation is allowed. 
     * If the operation is not allowed, an InetAddress representing
     * the loopback address is returned.
     *
     * @return     the IP address of the local host.
     * 
     * @exception  UnknownHostException  if no IP address for the
     *               <code>host</code> could be found.
     * 
     * @see SecurityManager#checkConnect
     */
    public static InetAddress getLocalHost() throws UnknownHostException {
        return null;
    }

    /** 
     * Replaces the de-serialized object with an Inet4Address object.
     *
     * @return the alternate object to the de-serialized object.
     *
     * @throws ObjectStreamException if a new object replacing this
     * object could not be created
     */
    private Object readResolve() throws ObjectStreamException {
        return null;
    }
}
