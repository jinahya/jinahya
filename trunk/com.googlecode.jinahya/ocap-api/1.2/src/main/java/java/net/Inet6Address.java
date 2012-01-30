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

import java.security.AccessController;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectStreamException;
import java.io.InvalidObjectException;

/** 
 * This class represents an Internet Protocol version 6 (IPv6) address.
 * Defined by <a href="http://www.ietf.org/rfc/rfc2373.txt">
 * <i>RFC&nbsp;2373: IP Version 6 Addressing Architecture</i></a>.
 *
 * <h4> <A NAME="format">Textual representation of IP addresses<a> </h4>
 *
 * Textual representation of IPv6 address used as input to methods
 * takes one of the following forms:
 * 
 * <ol>
 *   <li><p> <A NAME="lform">The preferred form<a> is x:x:x:x:x:x:x:x, where the 'x's are
 *   the hexadecimal values of the eight 16-bit pieces of the
 *   address. This is the full form.  For example,
 *
 *   <blockquote><table cellpadding=0 cellspacing=0 summary="layout">
 *   <tr><td><tt>1080:0:0:0:8:800:200C:417A</tt><td></tr>
 *   </table></blockquote>
 *
 *   <p> Note that it is not necessary to write the leading zeros in
 *   an individual field. However, there must be at least one numeral
 *   in every field, except as described below.</li>
 *
 *   <li><p> Due to some methods of allocating certain styles of IPv6
 *   addresses, it will be common for addresses to contain long
 *   strings of zero bits. In order to make writing addresses
 *   containing zero bits easier, a special syntax is available to
 *   compress the zeros. The use of "::" indicates multiple groups
 *   of 16-bits of zeros. The "::" can only appear once in an address.
 *   The "::" can also be used to compress the leading and/or trailing
 *   zeros in an address. For example,
 *
 *   <blockquote><table cellpadding=0 cellspacing=0 summary="layout">
 *   <tr><td><tt>1080::8:800:200C:417A</tt><td></tr>
 *   </table></blockquote>
 *
 *   <li><p> An alternative form that is sometimes more convenient
 *   when dealing with a mixed environment of IPv4 and IPv6 nodes is
 *   x:x:x:x:x:x:d.d.d.d, where the 'x's are the hexadecimal values
 *   of the six high-order 16-bit pieces of the address, and the 'd's
 *   are the decimal values of the four low-order 8-bit pieces of the
 *   standard IPv4 representation address, for example,
 *
 *   <blockquote><table cellpadding=0 cellspacing=0 summary="layout">
 *   <tr><td><tt>::FFFF:129.144.52.38</tt><td></tr>
 *   <tr><td><tt>::129.144.52.38</tt><td></tr>
 *   </table></blockquote>
 *
 *   <p> where "::FFFF:d.d.d.d" and "::d.d.d.d" are, respectively, the
 *   general forms of an IPv4-mapped IPv6 address and an
 *   IPv4-compatible IPv6 address. Note that the IPv4 portion must be
 *   in the "d.d.d.d" form. The following forms are invalid:
 *
 *   <blockquote><table cellpadding=0 cellspacing=0 summary="layout">
 *   <tr><td><tt>::FFFF:d.d.d</tt><td></tr>
 *   <tr><td><tt>::FFFF:d.d</tt><td></tr>
 *   <tr><td><tt>::d.d.d</tt><td></tr>
 *   <tr><td><tt>::d.d</tt><td></tr>
 *   </table></blockquote>
 *
 *   <p> The following form:
 *
 *   <blockquote><table cellpadding=0 cellspacing=0 summary="layout">
 *   <tr><td><tt>::FFFF:d</tt><td></tr>
 *   </table></blockquote>
 *
 *   <p> is valid, however it is an unconventional representation of
 *   the IPv4-compatible IPv6 address,
 *
 *   <blockquote><table cellpadding=0 cellspacing=0 summary="layout">
 *   <tr><td><tt>::255.255.0.d</tt><td></tr>
 *   </table></blockquote>
 *
 *   <p> while "::d" corresponds to the general IPv6 address
 *   "0:0:0:0:0:0:0:d".</li>
 * </ol>
 *
 * <p> For methods that return a textual representation as output
 * value, the full form is used. Inet6Address will return the full
 * form because it is unambiguous when used in combination with other
 * textual data.
 *
 * <h4> Special IPv6 address </h4>
 *
 * <blockquote>
 * <table cellspacing=2 summary="Description of IPv4-mapped address"> <tr><th valign=top><i>IPv4-mapped address</i></th>
 *         <td>Of the form::ffff:w.x.y.z, this IPv6 address is used to
 *         represent an IPv4 address. It allows the native program to
 *         use the same address data structure and also the same
 *         socket when communicating with both IPv4 and IPv6 nodes.
 *
 *         <p>In InetAddress and Inet6Address, it is used for internal
 *         representation; it has no functional role. Java will never
 *         return an IPv4-mapped address.  These classes can take an
 *         IPv4-mapped address as input, both in byte array and text
 *         representation. However, it will be converted into an IPv4
 *         address.</td></tr>
 * </table></blockquote>
 */
public final class Inet6Address extends InetAddress
{
    /** 
     * Holds a 128-bit (16 bytes) IPv6 address.
     *
     * @serial
     */
     byte[] ipaddress;

    private static final long serialVersionUID = 6880410070516793377L;

    /*
     * This hidden constructor does not necessarily correspond to
     * a constructor in the original source file -- it keeps javadoc
     * from generating an inappropriate default constructor.
     */
    private Inet6Address() { }

    /** 
     * Utility routine to check if the InetAddress is an IP multicast
     * address. 11111111 at the start of the address identifies the
     * address as being a multicast address.
     *
     * @return a <code>boolean</code> indicating if the InetAddress is
     * an IP multicast address
     * @since JDK1.1
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
     * Utility routine to check if the InetAddress is an
     * IPv4 compatible IPv6 address. 
     *
     * @return a <code>boolean</code> indicating if the InetAddress is 
     * an IPv4 compatible IPv6 address; or false if address is IPv4 address.
     * @since 1.4
     */
    public boolean isIPv4CompatibleAddress() {
        return false;
    }

    private void readObject(ObjectInputStream s)
        throws IOException, ClassNotFoundException
    { }
}
