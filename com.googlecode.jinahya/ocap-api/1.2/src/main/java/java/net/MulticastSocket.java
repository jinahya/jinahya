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

import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.Enumeration;

/** 
 * The multicast datagram socket class is useful for sending
 * and receiving IP multicast packets.  A MulticastSocket is
 * a (UDP) DatagramSocket, with additional capabilities for
 * joining "groups" of other multicast hosts on the internet.
 * <P>
 * A multicast group is specified by a class D IP address
 * and by a standard UDP port number. Class D IP addresses
 * are in the range <CODE>224.0.0.0</CODE> to <CODE>239.255.255.255</CODE>,
 * inclusive. The address 224.0.0.0 is reserved and should not be used.
 * <P>
 * One would join a multicast group by first creating a MulticastSocket
 * with the desired port, then invoking the
 * <CODE>joinGroup(InetAddress groupAddr)</CODE>
 * method:
 * <PRE>
 * // join a Multicast group and send the group salutations
 * ...
 * String msg = "Hello";
 * InetAddress group = InetAddress.getByName("228.5.6.7");
 * MulticastSocket s = new MulticastSocket(6789);
 * s.joinGroup(group);
 * DatagramPacket hi = new DatagramPacket(msg.getBytes(), msg.length(),
 *                             group, 6789);
 * s.send(hi);
 * // get their responses!
 * byte[] buf = new byte[1000];
 * DatagramPacket recv = new DatagramPacket(buf, buf.length);
 * s.receive(recv);
 * ...
 * // OK, I'm done talking - leave the group...
 * s.leaveGroup(group);
 * </PRE>
 *
 * When one sends a message to a multicast group, <B>all</B> subscribing
 * recipients to that host and port receive the message (within the
 * time-to-live range of the packet, see below).  The socket needn't
 * be a member of the multicast group to send messages to it.
 * <P>
 * When a socket subscribes to a multicast group/port, it receives
 * datagrams sent by other hosts to the group/port, as do all other
 * members of the group and port.  A socket relinquishes membership
 * in a group by the leaveGroup(InetAddress addr) method.  <B>
 * Multiple MulticastSocket's</B> may subscribe to a multicast group
 * and port concurrently, and they will all receive group datagrams.
 * <P>
 * Currently applets are not allowed to use multicast sockets.
 *
 * @author Pavani Diwanji
 * @since  JDK1.1
 */
public class MulticastSocket extends DatagramSocket
{

    /** 
     * Create a multicast socket.
     * 
     * <p>If there is a security manager, 
     * its <code>checkListen</code> method is first called
     * with 0 as its argument to ensure the operation is allowed. 
     * This could result in a SecurityException.
     * <p>
     * When the socket is created the 
     * {@link DatagramSocket#setReuseAddress(true)} method is 
     * called to enable the SO_REUSEADDR socket option. 
     *
     * @exception IOException if an I/O exception occurs
     * while creating the MulticastSocket
     * @exception  SecurityException  if a security manager exists and its  
     *             <code>checkListen</code> method doesn't allow the operation.
     * @see SecurityManager#checkListen
     * @see java.net.DatagramSocket#setReuseAddress(boolean) 
     */
    public MulticastSocket() throws IOException { }

    /** 
     * Create a multicast socket and bind it to a specific port.
     * 
     * <p>If there is a security manager, 
     * its <code>checkListen</code> method is first called
     * with the <code>port</code> argument
     * as its argument to ensure the operation is allowed. 
     * This could result in a SecurityException.
     * <p>
     * When the socket is created the
     * {@link DatagramSocket#setReuseAddress(true)} method is
     * called to enable the SO_REUSEADDR socket option. 
     * 
     * @param port port to use
     * @exception IOException if an I/O exception occurs
     * while creating the MulticastSocket
     * @exception  SecurityException  if a security manager exists and its  
     *             <code>checkListen</code> method doesn't allow the operation.
     * @see SecurityManager#checkListen
     * @see java.net.DatagramSocket#setReuseAddress(boolean) 
     */
    public MulticastSocket(int port) throws IOException { }

    /** 
     * Create a MulticastSocket bound to the specified socket address.
     * <p>
     * Or, if the address is <code>null</code>, create an unbound socket.
     * <p>
     * <p>If there is a security manager, 
     * its <code>checkListen</code> method is first called
     * with the SocketAddress port as its argument to ensure the operation is allowed. 
     * This could result in a SecurityException.
     * <p>
     * When the socket is created the
     * {@link DatagramSocket#setReuseAddress(true)} method is
     * called to enable the SO_REUSEADDR socket option. 
     *
     * @param bindaddr Socket address to bind to, or <code>null</code> for
     *                 an unbound socket.
     * @exception IOException if an I/O exception occurs
     * while creating the MulticastSocket
     * @exception  SecurityException  if a security manager exists and its  
     *             <code>checkListen</code> method doesn't allow the operation.
     * @see SecurityManager#checkListen
     * @see java.net.DatagramSocket#setReuseAddress(boolean) 
     *
     * @since 1.4
     */
    public MulticastSocket(SocketAddress bindaddr) throws IOException { }

    /** 
     * Set the default time-to-live for multicast packets sent out
     * on this <code>MulticastSocket</code> in order to control the 
     * scope of the multicasts.
     *
     * <P> The ttl <B>must</B> be in the range <code> 0 <= ttl <=
     * 255</code> or an IllegalArgumentException will be thrown.
     * @exception IOException if an I/O exception occurs
     * while setting the default time-to-live value
     * @param ttl the time-to-live
     * @see #getTimeToLive()
     */
    public void setTimeToLive(int ttl) throws IOException { }

    /** 
     * Get the default time-to-live for multicast packets sent out on
     * the socket.
     * @exception IOException if an I/O exception occurs while
     * getting the default time-to-live value
     * @return the default time-to-live value
     * @see #setTimeToLive(int)
     */
    public int getTimeToLive() throws IOException {
        return 0;
    }

    /** 
     * Joins a multicast group. Its behavior may be affected by
     * <code>setInterface</code> or <code>setNetworkInterface</code>.
     * 
     * <p>If there is a security manager, this method first
     * calls its <code>checkMulticast</code> method
     * with the <code>mcastaddr</code> argument
     * as its argument.
     * 
     * @param mcastaddr is the multicast address to join
     * 
     * @exception IOException if there is an error joining
     * or when the address is not a multicast address.
     * @exception  SecurityException  if a security manager exists and its  
     * <code>checkMulticast</code> method doesn't allow the join.
     * 
     * @see SecurityManager#checkMulticast(InetAddress)
     */
    public void joinGroup(InetAddress mcastaddr) throws IOException { }

    /** 
     * Leave a multicast group. Its behavior may be affected by
     * <code>setInterface</code> or <code>setNetworkInterface</code>.
     * 
     * <p>If there is a security manager, this method first
     * calls its <code>checkMulticast</code> method
     * with the <code>mcastaddr</code> argument
     * as its argument.
     * 
     * @param mcastaddr is the multicast address to leave
     * @exception IOException if there is an error leaving
     * or when the address is not a multicast address.
     * @exception  SecurityException  if a security manager exists and its  
     * <code>checkMulticast</code> method doesn't allow the operation.
     * 
     * @see SecurityManager#checkMulticast(InetAddress)
     */
    public void leaveGroup(InetAddress mcastaddr) throws IOException { }

    /** 
     * Joins the specified multicast group at the specified interface.
     *
     * <p>If there is a security manager, this method first
     * calls its <code>checkMulticast</code> method
     * with the <code>mcastaddr</code> argument
     * as its argument.
     * 
     * @param mcastaddr is the multicast address to join
     * @param netIf specifies the local interface to receive multicast
     *        datagram packets, or <i>null</i> to defer to the interface set by
     *	     {@link MulticastSocket#setInterface(InetAddress)} or 
     *	     {@link MulticastSocket#setNetworkInterface(NetworkInterface)}
     *
     * @exception IOException if there is an error joining
     * or when the address is not a multicast address.
     * @exception  SecurityException  if a security manager exists and its  
     * <code>checkMulticast</code> method doesn't allow the join.
     * @throws  IllegalArgumentException if mcastaddr is null or is a
     *          SocketAddress subclass not supported by this socket
     * 
     * @see SecurityManager#checkMulticast(InetAddress)
     * @since 1.4
     */
    public void joinGroup(SocketAddress mcastaddr, NetworkInterface netIf)
        throws IOException
    { }

    /** 
     * Leave a multicast group on a specified local interface.
     * 
     * <p>If there is a security manager, this method first
     * calls its <code>checkMulticast</code> method
     * with the <code>mcastaddr</code> argument
     * as its argument.
     * 
     * @param mcastaddr is the multicast address to leave
     * @param netIf specifies the local interface or <i>null</i> to defer
     *		   to the interface set by
     *		   {@link MulticastSocket#setInterface(InetAddress)} or 
     *		   {@link MulticastSocket#setNetworkInterface(NetworkInterface)}
     * @exception IOException if there is an error leaving
     * or when the address is not a multicast address.
     * @exception  SecurityException  if a security manager exists and its  
     * <code>checkMulticast</code> method doesn't allow the operation.
     * @throws  IllegalArgumentException if mcastaddr is null or is a
     *          SocketAddress subclass not supported by this socket
     * 
     * @see SecurityManager#checkMulticast(InetAddress)
     * @since 1.4
     */
    public void leaveGroup(SocketAddress mcastaddr, NetworkInterface netIf)
        throws IOException
    { }

    /** 
     * Set the multicast network interface used by methods
     * whose behavior would be affected by the value of the
     * network interface. Useful for multihomed hosts.
     * @param inf the InetAddress
     * @exception SocketException if there is an error in 
     * the underlying protocol, such as a TCP error. 
     * @see #getInterface()
     */
    public void setInterface(InetAddress inf) throws SocketException { }

    /** 
     * Retrieve the address of the network interface used for
     * multicast packets.
     * 
     * @return An <code>InetAddress</code> representing
     *  the address of the network interface used for 
     *  multicast packets.
     *
     * @exception SocketException if there is an error in 
     * the underlying protocol, such as a TCP error.
     * 
     * @see #setInterface(java.net.InetAddress)
     */
    public InetAddress getInterface() throws SocketException {
        return null;
    }

    /** 
     * Specify the network interface for outgoing multicast datagrams 
     * sent on this socket.
     *
     * @param netIf the interface
     * @exception SocketException if there is an error in 
     * the underlying protocol, such as a TCP error. 
     * @see #getNetworkInterface()
     * @since 1.4
     */
    public void setNetworkInterface(NetworkInterface netIf)
        throws SocketException
    { }

    /** 
     * Get the multicast network interface set.
     *
     * @exception SocketException if there is an error in 
     * the underlying protocol, such as a TCP error. 
     * @return the multicast <code>NetworkInterface</code> currently set
     * @see #setNetworkInterface(NetworkInterface)
     * @since 1.4
     */
    public NetworkInterface getNetworkInterface() throws SocketException {
        return null;
    }

    /** 
     * Disable/Enable local loopback of multicast datagrams
     * The option is used by the platform's networking code as a hint 
     * for setting whether multicast data will be looped back to 
     * the local socket.
     *
     * <p>Because this option is a hint, applications that want to
     * verify what loopback mode is set to should call 
     * {@link #getLoopbackMode()}
     * @param disable <code>true</code> to disable the LoopbackMode
     * @throws SocketException if an error occurs while setting the value
     * @since 1.4
     * @see #getLoopbackMode
     */
    public void setLoopbackMode(boolean disable) throws SocketException { }

    /** 
     * Get the setting for local loopback of multicast datagrams.
     *
     * @throws SocketException  if an error occurs while getting the value
     * @return true if the LoopbackMode has been disabled
     * @since 1.4
     * @see #setLoopbackMode
     */
    public boolean getLoopbackMode() throws SocketException {
        return false;
    }

    /** 
     * Sends a datagram packet to the destination, with a TTL (time-
     * to-live) other than the default for the socket.  This method
     * need only be used in instances where a particular TTL is desired;
     * otherwise it is preferable to set a TTL once on the socket, and
     * use that default TTL for all packets.  This method does <B>not
     * </B> alter the default TTL for the socket. Its behavior may be
     * affected by <code>setInterface</code>.
     *
     * <p>If there is a security manager, this method first performs some
     * security checks. First, if <code>p.getAddress().isMulticastAddress()</code>
     * is true, this method calls the
     * security manager's <code>checkMulticast</code> method
     * with <code>p.getAddress()</code> and <code>ttl</code> as its arguments.
     * If the evaluation of that expression is false,
     * this method instead calls the security manager's 
     * <code>checkConnect</code> method with arguments
     * <code>p.getAddress().getHostAddress()</code> and
     * <code>p.getPort()</code>. Each call to a security manager method
     * could result in a SecurityException if the operation is not allowed.
     * 
     * @param p	is the packet to be sent. The packet should contain
     * the destination multicast ip address and the data to be sent.
     * One does not need to be the member of the group to send
     * packets to a destination multicast address.
     * @param ttl optional time to live for multicast packet.
     * default ttl is 1.
     * 
     * @exception IOException is raised if an error occurs i.e
     * error while setting ttl.
     * @exception  SecurityException  if a security manager exists and its  
     *             <code>checkMulticast</code> or <code>checkConnect</code> 
     *             method doesn't allow the send.
     * 
     * @deprecated Use the following code or its equivalent instead:
     *	......
     *	int ttl = mcastSocket.getTimeToLive();
     *  mcastSocket.setTimeToLive(newttl);
     *	mcastSocket.send(p);
     *	mcastSocket.setTimeToLive(ttl);
     *	......
     *
     * @see DatagramSocket#send
     * @see DatagramSocket#receive
     * @see SecurityManager#checkMulticast(java.net.InetAddress, byte)
     * @see SecurityManager#checkConnect
     */
    public void send(DatagramPacket p, byte ttl) throws IOException { }
}
