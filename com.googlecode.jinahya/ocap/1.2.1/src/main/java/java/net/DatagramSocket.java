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

import java.io.FileDescriptor;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.security.AccessController;
import java.security.PrivilegedExceptionAction;

/** 
 * This class represents a socket for sending and receiving datagram packets.
 *
 * <p>A datagram socket is the sending or receiving point for a packet
 * delivery service. Each packet sent or received on a datagram socket
 * is individually addressed and routed. Multiple packets sent from
 * one machine to another may be routed differently, and may arrive in
 * any order.
 *
 * <p>UDP broadcasts sends are always enabled on a DatagramSocket.
 * In order to receive broadcast packets a DatagramSocket
 * should be bound to the wildcard address. In some
 * implementations, broadcast packets may also be received when
 * a DatagramSocket is bound to a more specific address.
 * <p>
 * Example:
 * <code>
 *		DatagramSocket s = new DatagramSocket(null);
 *		s.bind(new InetSocketAddress(8888));
 * </code>
 * Which is equivalent to:
 * <code>
 *		DatagramSocket s = new DatagramSocket(8888);
 * </code>
 * Both cases will create a DatagramSocket able to receive broadcasts on
 * UDP port 8888.
 *
 * @author  Pavani Diwanji
 * @version 1.49, 02/02/00
 * @see     java.net.DatagramPacket
 * @since JDK1.0
 */
public class DatagramSocket
{

    /** 
     * Constructs a datagram socket and binds it to any available port
     * on the local host machine.  The socket will be bound to the wildcard
     * address, an IP address chosen by the kernel.
     * 
     * <p>If there is a security manager, 
     * its <code>checkListen</code> method is first called
     * with 0 as its argument to ensure the operation is allowed. 
     * This could result in a SecurityException.
     *
     * @exception  SocketException  if the socket could not be opened,
     *               or the socket could not bind to the specified local port.
     * @exception  SecurityException  if a security manager exists and its  
     *             <code>checkListen</code> method doesn't allow the operation.
     * 
     * @see SecurityManager#checkListen
     */
    public DatagramSocket() throws SocketException { }

    /** 
     * Creates an unbound datagram socket with the specified
     * DatagramSocketImpl.
     *
     * @param impl an instance of a <B>DatagramSocketImpl</B>
     *        the subclass wishes to use on the DatagramSocket.
     * @since   1.4
     */
    protected DatagramSocket(DatagramSocketImpl impl) { }

    /** 
     * Creates a datagram socket, bound to the specified local
     * socket address.
     * <p>
     * If, if the address is <code>null</code>, creates an unbound socket.
     * <p>
     * <p>If there is a security manager, 
     * its <code>checkListen</code> method is first called
     * with the port from the socket address
     * as its argument to ensure the operation is allowed. 
     * This could result in a SecurityException.
     * 
     * @param bindaddr local socket address to bind, or <code>null</code>
     *		       for an unbound socket.
     * 
     * @exception  SocketException  if the socket could not be opened,
     *               or the socket could not bind to the specified local port.
     * @exception  SecurityException  if a security manager exists and its  
     *             <code>checkListen</code> method doesn't allow the operation.
     * 
     * @see SecurityManager#checkListen
     * @since   1.4
     */
    public DatagramSocket(SocketAddress bindaddr) throws SocketException { }

    /** 
     * Constructs a datagram socket and binds it to the specified port
     * on the local host machine.  The socket will be bound to the wildcard
     * address, an IP address chosen by the kernel.
     * 
     * <p>If there is a security manager, 
     * its <code>checkListen</code> method is first called
     * with the <code>port</code> argument
     * as its argument to ensure the operation is allowed. 
     * This could result in a SecurityException.
     *
     * @param      port port to use.
     * @exception  SocketException  if the socket could not be opened,
     *               or the socket could not bind to the specified local port.
     * @exception  SecurityException  if a security manager exists and its  
     *             <code>checkListen</code> method doesn't allow the operation.
     * 
     * @see SecurityManager#checkListen
     */
    public DatagramSocket(int port) throws SocketException { }

    /** 
     * Creates a datagram socket, bound to the specified local
     * address.  The local port must be between 0 and 65535 inclusive.
     * If the IP address is 0.0.0.0, the socket will be bound to the
     * wildcard address, an IP address chosen by the kernel.
     * 
     * <p>If there is a security manager, 
     * its <code>checkListen</code> method is first called
     * with the <code>port</code> argument
     * as its argument to ensure the operation is allowed. 
     * This could result in a SecurityException.
     * 
     * @param port local port to use
     * @param laddr local address to bind
     * 
     * @exception  SocketException  if the socket could not be opened,
     *               or the socket could not bind to the specified local port.
     * @exception  SecurityException  if a security manager exists and its  
     *             <code>checkListen</code> method doesn't allow the operation.
     * 
     * @see SecurityManager#checkListen
     * @since   JDK1.1
     */
    public DatagramSocket(int port, InetAddress laddr) throws SocketException
    { }

    /** 
     * Binds this DatagramSocket to a specific address & port.
     * <p>
     * If the address is <code>null</code>, then the system will pick up
     * an ephemeral port and a valid local address to bind the socket.
     *<p>
     * @param	addr The address & port to bind to.
     * @throws	SocketException if any error happens during the bind, or if the
     *		socket is already bound.
     * @throws	SecurityException  if a security manager exists and its  
     *             <code>checkListen</code> method doesn't allow the operation.
     * @throws IllegalArgumentException if addr is a SocketAddress subclass
     *         not supported by this socket.
     * @since 1.4
     */
    public synchronized void bind(SocketAddress addr) throws SocketException { }

    /** 
     * Connects the socket to a remote address for this socket. When a
     * socket is connected to a remote address, packets may only be
     * sent to or received from that address. By default a datagram
     * socket is not connected.
     *
     * <p>If the remote destination to which the socket is connected does not
     * exist, or is otherwise unreachable, and if an ICMP destination unreachable
     * packet has been received for that address, then a subsequent call to 
     * send or receive may throw a PortUnreachableException. Note, there is no 
     * guarantee that the exception will be thrown.
     *
     * <p>A caller's permission to send and receive datagrams to a
     * given host and port are checked at connect time. When a socket
     * is connected, receive and send <b>will not
     * perform any security checks</b> on incoming and outgoing
     * packets, other than matching the packet's and the socket's
     * address and port. On a send operation, if the packet's address
     * is set and the packet's address and the socket's address do not
     * match, an IllegalArgumentException will be thrown. A socket
     * connected to a multicast address may only be used to send packets.
     *
     * @param address the remote address for the socket
     *
     * @param port the remote port for the socket.
     *
     * @exception IllegalArgumentException if the address is null,
     * or the port is out of range.
     *
     * @exception SecurityException if the caller is not allowed to
     * send datagrams to and receive datagrams from the address and port.
     *
     * @see #disconnect
     * @see #send
     * @see #receive 
     */
    public void connect(InetAddress address, int port) { }

    /** 
     * Connects this socket to a remote socket address (IP address + port number).
     * <p>
     * @param	addr	The remote address.
     * @throws	SocketException if the connect fails
     * @throws	IllegalArgumentException if addr is null or addr is a SocketAddress
     *		subclass not supported by this socket
     * @since 1.4
     * @see #connect
     */
    public void connect(SocketAddress addr) throws SocketException { }

    /** 
     * Disconnects the socket. This does nothing if the socket is not
     * connected.
     *
     * @see #connect
     */
    public void disconnect() { }

    /** 
     * Returns the binding state of the socket.
     *
     * @return true if the socket succesfuly bound to an address
     * @since 1.4
     */
    public boolean isBound() {
        return false;
    }

    /** 
     * Returns the connection state of the socket.
     *
     * @return true if the socket succesfuly connected to a server
     * @since 1.4
     */
    public boolean isConnected() {
        return false;
    }

    /** 
     * Returns the address to which this socket is connected. Returns null
     * if the socket is not connected.
     *
     * @return the address to which this socket is connected.
     */
    public InetAddress getInetAddress() {
        return null;
    }

    /** 
     * Returns the port for this socket. Returns -1 if the socket is not
     * connected.
     *
     * @return the port to which this socket is connected.
     */
    public int getPort() {
        return 0;
    }

    /** 
     * Returns the address of the endpoint this socket is connected to, or
     * <code>null</null> if it is unconnected.
     * @return a <code>SocketAddress</code> reprensenting the remote endpoint of this
     *	       socket, or <code>null</code> if it is not connected yet.
     * @see #getInetAddress()
     * @see #getPort()
     * @see #connect(SocketAddress)
     * @since 1.4
     */
    public SocketAddress getRemoteSocketAddress() {
        return null;
    }

    /** 
     * Returns the address of the endpoint this socket is bound to, or
     * <code>null</code> if it is not bound yet.
     *
     * @return a <code>SocketAddress</code> representing the local endpoint of this
     *	       socket, or <code>null</code> if it is not bound yet.
     * @see #getLocalAddress()
     * @see #getLocalPort()
     * @see #bind(SocketAddress)
     * @since 1.4
     */
    public SocketAddress getLocalSocketAddress() {
        return null;
    }

    /** 
     * Sends a datagram packet from this socket. The
     * <code>DatagramPacket</code> includes information indicating the
     * data to be sent, its length, the IP address of the remote host,
     * and the port number on the remote host.
     *
     * <p>If there is a security manager, and the socket is not currently
     * connected to a remote address, this method first performs some
     * security checks. First, if <code>p.getAddress().isMulticastAddress()</code>
     * is true, this method calls the
     * security manager's <code>checkMulticast</code> method
     * with <code>p.getAddress()</code> as its argument.
     * If the evaluation of that expression is false,
     * this method instead calls the security manager's 
     * <code>checkConnect</code> method with arguments
     * <code>p.getAddress().getHostAddress()</code> and
     * <code>p.getPort()</code>. Each call to a security manager method
     * could result in a SecurityException if the operation is not allowed.
     * 
     * @param      p   the <code>DatagramPacket</code> to be sent.
     * 
     * @exception  IOException  if an I/O error occurs.
     * @exception  SecurityException  if a security manager exists and its  
     *             <code>checkMulticast</code> or <code>checkConnect</code> 
     *             method doesn't allow the send.
     * @exception  PortUnreachableException may be thrown if the socket is connected
     *             to a currently unreachable destination. Note, there is no 
     * 		   guarantee that the exception will be thrown.
     * 
     * @see        java.net.DatagramPacket
     * @see        SecurityManager#checkMulticast(InetAddress)
     * @see        SecurityManager#checkConnect
     * @revised 1.4
     * @spec JSR-51
     */
    public void send(DatagramPacket p) throws IOException { }

    /** 
     * Receives a datagram packet from this socket. When this method
     * returns, the <code>DatagramPacket</code>'s buffer is filled with
     * the data received. The datagram packet also contains the sender's
     * IP address, and the port number on the sender's machine.
     * <p>
     * This method blocks until a datagram is received. The
     * <code>length</code> field of the datagram packet object contains
     * the length of the received message. If the message is longer than
     * the packet's length, the message is truncated.
     * <p>
     * If there is a security manager, a packet cannot be received if the
     * security manager's <code>checkAccept</code> method
     * does not allow it.
     * 
     * @param      p   the <code>DatagramPacket</code> into which to place
     *                 the incoming data.
     * @exception  IOException  if an I/O error occurs.
     * @exception  SocketTimeoutException  if setSoTimeout was previously called
     *		       and the timeout has expired.
     * @exception  PortUnreachableException may be thrown if the socket is connected
     *       	   to a currently unreachable destination. Note, there is no guarantee that the
     *       	   exception will be thrown.
     * @see        java.net.DatagramPacket
     * @see        java.net.DatagramSocket
     * @revised 1.4
     * @spec JSR-51
     */
    public synchronized void receive(DatagramPacket p) throws IOException { }

    /** 
     * Gets the local address to which the socket is bound.
     *
     * <p>If there is a security manager, its
     * <code>checkConnect</code> method is first called
     * with the host address and <code>-1</code>
     * as its arguments to see if the operation is allowed.
     *
     * @see SecurityManager#checkConnect
     * @return  the local address to which the socket is bound, or
     *		an <code>InetAddress</code> representing any local
     *		address if either the socket is not bound, or
     *		the security manager <code>checkConnect</code>
     *		method does not allow the operation
     * @since   1.1
     */
    public InetAddress getLocalAddress() {
        return null;
    }

    /** 
     * Returns the port number on the local host to which this socket is bound.
     *
     * @return  the port number on the local host to which this socket is bound.
     */
    public int getLocalPort() {
        return 0;
    }

    /** 
     *Enable/disable SO_TIMEOUT with the specified timeout, in
     *  milliseconds. With this option set to a non-zero timeout,
     *  a call to receive() for this DatagramSocket
     *  will block for only this amount of time.  If the timeout expires,
     *  a <B>java.net.SocketTimeoutException</B> is raised, though the
     *  DatagramSocket is still valid.  The option <B>must</B> be enabled
     *  prior to entering the blocking operation to have effect.  The
     *  timeout must be > 0.
     *  A timeout of zero is interpreted as an infinite timeout.
     *
     * @param timeout the specified timeout in milliseconds.
     * @throws SocketException if there is an error in the underlying protocol, such as an UDP error. 
     * @since   JDK1.1
     * @see #getSoTimeout()
     */
    public synchronized void setSoTimeout(int timeout) throws SocketException
    { }

    /** 
     * Retrive setting for SO_TIMEOUT.  0 returns implies that the
     * option is disabled (i.e., timeout of infinity).
     *
     * @return the setting for SO_TIMEOUT
     * @throws SocketException if there is an error in the underlying protocol, such as an UDP error.
     * @since   JDK1.1
     * @see #setSoTimeout(int)
     */
    public synchronized int getSoTimeout() throws SocketException {
        return 0;
    }

    /** 
     * Sets the SO_SNDBUF option to the specified value for this
     * <tt>DatagramSocket</tt>. The SO_SNDBUF option is used by the 
     * network implementation as a hint to size the underlying
     * network I/O buffers. The SO_SNDBUF setting may also be used 
     * by the network implementation to determine the maximum size
     * of the packet that can be sent on this socket.
     * <p>
     * As SO_SNDBUF is a hint, applications that want to verify
     * what size the buffer is should call {@link #getSendBufferSize()}.
     * <p>
     * Increasing the buffer size may allow multiple outgoing packets 
     * to be queued by the network implementation when the send rate
     * is high. 
     * <p>
     * Note: If {@link #send()} is used to send a 
     * <code>DatagramPacket</code> that is larger than the setting
     * of SO_SNDBUF then it is implementation specific if the
     * packet is sent or discarded.
     *
     * @param size the size to which to set the send buffer
     * size. This value must be greater than 0.
     *
     * @exception SocketException if there is an error 
     * in the underlying protocol, such as an UDP error.
     * @exception IllegalArgumentException if the value is 0 or is
     * negative.
     * @see #getSendBufferSize()
     */
    public synchronized void setSendBufferSize(int size) throws SocketException
    { }

    /** 
     * Get value of the SO_SNDBUF option for this <tt>DatagramSocket</tt>, that is the
     * buffer size used by the platform for output on this <tt>DatagramSocket</tt>.
     *
     * @return the value of the SO_SNDBUF option for this <tt>DatagramSocket</tt>
     * @exception SocketException if there is an error in 
     * the underlying protocol, such as an UDP error.
     * @see #setSendBufferSize
     */
    public synchronized int getSendBufferSize() throws SocketException {
        return 0;
    }

    /** 
     * Sets the SO_RCVBUF option to the specified value for this
     * <tt>DatagramSocket</tt>. The SO_RCVBUF option is used by the
     * the network implementation as a hint to size the underlying
     * network I/O buffers. The SO_RCVBUF setting may also be used
     * by the network implementation to determine the maximum size
     * of the packet that can be received on this socket.
     * <p>
     * Because SO_RCVBUF is a hint, applications that want to
     * verify what size the buffers were set to should call
     * {@link #getReceiveBufferSize()}.
     * <p>
     * Increasing SO_RCVBUF may allow the network implementation
     * to buffer multiple packets when packets arrive faster than
     * are being received using {@link #receive()}.
     * <p>
     * Note: It is implementation specific if a packet larger
     * than SO_RCVBUF can be received.
     *
     * @param size the size to which to set the receive buffer
     * size. This value must be greater than 0.
     *
     * @exception SocketException if there is an error in 
     * the underlying protocol, such as an UDP error.
     * @exception IllegalArgumentException if the value is 0 or is
     * negative.
     * @see #getReceiveBufferSize()
     */
    public synchronized void setReceiveBufferSize(int size)
        throws SocketException
    { }

    /** 
     * Get value of the SO_RCVBUF option for this <tt>DatagramSocket</tt>, that is the
     * buffer size used by the platform for input on this <tt>DatagramSocket</tt>.
     *
     * @return the value of the SO_RCVBUF option for this <tt>DatagramSocket</tt>
     * @exception SocketException if there is an error in the underlying protocol, such as an UDP error.
     * @see #setReceiveBufferSize(int)
     */
    public synchronized int getReceiveBufferSize() throws SocketException {
        return 0;
    }

    /** 
     * Enable/disable the SO_REUSEADDR socket option.
     * <p>
     * For UDP sockets it may be necessary to bind more than one
     * socket to the same socket address. This is typically for the
     * purpose of receiving multicast packets. 
     * The <tt>SO_REUSEADDR</tt> socket option allows multiple
     * sockets to be bound to the same socket address if the
     * <tt>SO_REUSEADDR</tt> socket option is enabled prior
     * to binding the socket using {@link #bind(SocketAddress)}.
     * <p>
     * When a <tt>DatagramSocket</tt> is created the initial setting
     * of <tt>SO_REUSEADDR</tt> is disabled.
     * <p>
     * The behaviour when <tt>SO_REUSEADDR</tt> is enabled or
     * disabled after a socket is bound (See {@link #isBound()})
     * is not defined.
     * 
     * @param on  whether to enable or disable the 
     * @exception SocketException if an error occurs enabling or
     *            disabling the <tt>SO_RESUEADDR</tt> socket option,
     *	   	  or the socket is closed.
     * @since 1.4
     * @see #getReuseAddress()     
     * @see #bind(SocketAddress)     
     * @see #isBound()
     * @see #isClosed()
     */
    public synchronized void setReuseAddress(boolean on) throws SocketException
    { }

    /** 
     * Tests if SO_REUSEADDR is enabled.
     *
     * @return a <code>boolean</code> indicating whether or not SO_REUSEADDR is enabled.
     * @exception SocketException if there is an error
     * in the underlying protocol, such as an UDP error. 
     * @since   1.4
     * @see #setReuseAddress(boolean)
     */
    public synchronized boolean getReuseAddress() throws SocketException {
        return false;
    }

    /** 
     * Enable/disable SO_BROADCAST.
     * @param on     whether or not to have broadcast turned on.
     * @exception SocketException if there is an error
     * in the underlying protocol, such as an UDP error.
     * @since 1.4
     * @see #getBroadcast()
     */
    public synchronized void setBroadcast(boolean on) throws SocketException { }

    /** 
     * Tests if SO_BROADCAST is enabled.
     * @return a <code>boolean</code> indicating whether or not SO_BROADCAST is enabled.
     * @exception SocketException if there is an error
     * in the underlying protocol, such as an UDP error.
     * @since 1.4
     * @see #setBroadcast(boolean)
     */
    public synchronized boolean getBroadcast() throws SocketException {
        return false;
    }

    /** 
     * Sets traffic class or type-of-service octet in the IP
     * datagram header for datagrams sent from this DatagramSocket.
     * As the underlying network implementation may ignore this
     * value applications should consider it a hint.
     *
     * <P> The tc <B>must</B> be in the range <code> 0 <= tc <=
     * 255</code> or an IllegalArgumentException will be thrown.
     * <p>Notes:
     * <p> for Internet Protocol v4 the value consists of an octet
     * with precedence and TOS fields as detailed in RFC 1349. The
     * TOS field is bitset created by bitwise-or'ing values such
     * the following :-
     * <p>
     * <UL>
     * <LI><CODE>IPTOS_LOWCOST (0x02)</CODE></LI>
     * <LI><CODE>IPTOS_RELIABILITY (0x04)</CODE></LI>
     * <LI><CODE>IPTOS_THROUGHPUT (0x08)</CODE></LI>
     * <LI><CODE>IPTOS_LOWDELAY (0x10)</CODE></LI>
     * </UL>
     * The last low order bit is always ignored as this
     * corresponds to the MBZ (must be zero) bit.
     * <p>
     * Setting bits in the precedence field may result in a 
     * SocketException indicating that the operation is not
     * permitted.
     * <p>
     * for Internet Protocol v6 <code>tc</code> is the value that 
     * would be placed into the sin6_flowinfo field of the IP header.
     *
     * @param tc	an <code>int</code> value for the bitset.
     * @throws SocketException if there is an error setting the
     * traffic class or type-of-service 
     * @since 1.4
     * @see #getTrafficClass
     */
    public synchronized void setTrafficClass(int tc) throws SocketException { }

    /** 
     * Gets traffic class or type-of-service in the IP datagram 
     * header for packets sent from this DatagramSocket.
     * <p>
     * As the underlying network implementation may ignore the
     * traffic class or type-of-service set using {@link #setTrafficClass()}
     * this method may return a different value than was previously
     * set using the {@link #setTrafficClass()} method on this 
     * DatagramSocket.
     *
     * @return the traffic class or type-of-service already set
     * @throws SocketException if there is an error obtaining the
     * traffic class or type-of-service value.
     * @since 1.4
     * @see #setTrafficClass
     */
    public synchronized int getTrafficClass() throws SocketException {
        return 0;
    }

    /** 
     * Closes this datagram socket.
     * <p>
     * Any thread currently blocked in {#link receive} upon this socket
     * will throw a {@link SocketException}.
     *
     * <p> If this socket has an associated channel then the channel is closed
     * as well.
     *
     * @revised 1.4
     * @spec JSR-51
     */
    public void close() { }

    /** 
     * Returns wether the socket is closed or not.
     *
     * @return true if the socket has been closed
     * @since 1.4
     */
    public boolean isClosed() {
        return false;
    }

    /** 
     * Sets the datagram socket implementation factory for the
     * application. The factory can be specified only once.
     * <p>
     * When an application creates a new datagram socket, the socket
     * implementation factory's <code>createDatagramSocketImpl</code> method is
     * called to create the actual datagram socket implementation.
     * 
     * <p>If there is a security manager, this method first calls
     * the security manager's <code>checkSetFactory</code> method 
     * to ensure the operation is allowed. 
     * This could result in a SecurityException.
     *
     * @param      fac   the desired factory.
     * @exception  IOException  if an I/O error occurs when setting the
     *              datagram socket factory.
     * @exception  SocketException  if the factory is already defined.
     * @exception  SecurityException  if a security manager exists and its  
     *             <code>checkSetFactory</code> method doesn't allow the 
     *     operation.
     * @see        
     *     java.net.DatagramSocketImplFactory#createDatagramSocketImpl()
     * @see       SecurityManager#checkSetFactory
     */
    public static synchronized void
        setDatagramSocketImplFactory(DatagramSocketImplFactory fac)
        throws IOException
    { }
}
