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

/** 
 * Abstract datagram and multicast socket implementation base class.
 * @author Pavani Diwanji
 * @since  JDK1.1
 */
public abstract class DatagramSocketImpl implements SocketOptions
{
    /** 
     * The local port number.
     */
    protected int localPort;

    /** 
     * The file descriptor object.
     */
    protected FileDescriptor fd;

    public DatagramSocketImpl() { }

    /** 
     * Creates a datagram socket.
     * @exception SocketException if there is an error in the 
     * underlying protocol, such as a TCP error. 
     */
    protected abstract void create() throws SocketException;

    /** 
     * Binds a datagram socket to a local port and address.
     * @param lport the local port
     * @param laddr the local address
     * @exception SocketException if there is an error in the
     * underlying protocol, such as a TCP error.
     */
    protected abstract void bind(int lport, InetAddress laddr)
        throws SocketException;

    /** 
     * Sends a datagram packet. The packet contains the data and the
     * destination address to send the packet to.
     * @param p the packet to be sent.
     * @exception IOException if an I/O exception occurs while sending the 
     * datagram packet.
     * @exception  PortUnreachableException may be thrown if the socket is connected
     * to a currently unreachable destination. Note, there is no guarantee that 
     * the exception will be thrown.
     */
    protected abstract void send(DatagramPacket p) throws IOException;

    /** 
     * Connects a datagram socket to a remote destination. This associates the remote
     * address with the local socket so that datagrams may only be sent to this destination
     * and received from this destination. This may be overridden to call a native
     * system connect. 
     *
     * <p>If the remote destination to which the socket is connected does not
     * exist, or is otherwise unreachable, and if an ICMP destination unreachable
     * packet has been received for that address, then a subsequent call to 
     * send or receive may throw a PortUnreachableException. 
     * Note, there is no guarantee that the exception will be thrown.
     * @param address the remote InetAddress to connect to
     * @param port the remote port number
     * @exception   SocketException may be thrown if the socket cannot be
     * connected to the remote destination
     * @since 1.4
     */
    protected void connect(InetAddress address, int port) throws SocketException
    { }

    /** 
     * Disconnects a datagram socket from its remote destination. 
     * @since 1.4
     */
    protected void disconnect() { }

    /** 
     * Peek at the packet to see who it is from.
     * @param i an InetAddress object 
     * @return the address which the packet came from.
     * @exception IOException if an I/O exception occurs
     * @exception  PortUnreachableException may be thrown if the socket is connected
     *       to a currently unreachable destination. Note, there is no guarantee that the
     *       exception will be thrown.
     */
    protected abstract int peek(InetAddress i) throws IOException;

    /** 
     * Peek at the packet to see who it is from. The data is returned,
     * but not consumed, so that a subsequent peekData/receive operation 
     * will see the same data.
     * @param p the Packet Received.
     * @return the address which the packet came from.
     * @exception IOException if an I/O exception occurs
     * @exception  PortUnreachableException may be thrown if the socket is connected
     *       to a currently unreachable destination. Note, there is no guarantee that the
     *       exception will be thrown.
     * @since 1.4
     */
    protected abstract int peekData(DatagramPacket p) throws IOException;

    /** 
     * Receive the datagram packet.
     * @param p the Packet Received.
     * @exception IOException if an I/O exception occurs
     * while receiving the datagram packet.
     * @exception  PortUnreachableException may be thrown if the socket is connected
     *       to a currently unreachable destination. Note, there is no guarantee that the
     *       exception will be thrown.
     */
    protected abstract void receive(DatagramPacket p) throws IOException;

    /** 
     * Set the TTL (time-to-live) option.
     * @param ttl an <tt>int</tt> specifying the time-to-live value
     * @exception IOException if an I/O exception occurs
     * while setting the time-to-live option.
     * @see #getTimeToLive()
     */
    protected abstract void setTimeToLive(int ttl) throws IOException;

    /** 
     * Retrieve the TTL (time-to-live) option.
     * @exception IOException if an I/O exception occurs
     * while retrieving the time-to-live option
     * @return an <tt>int</tt> representing the time-to-live value
     * @see #setTimeToLive(int)
     */
    protected abstract int getTimeToLive() throws IOException;

    /** 
     * Join the multicast group.
     * @param inetaddr multicast address to join.
     * @exception IOException if an I/O exception occurs
     * while joining the multicast group.
     */
    protected abstract void join(InetAddress inetaddr) throws IOException;

    /** 
     * Leave the multicast group.
     * @param inetaddr multicast address to leave.
     * @exception IOException if an I/O exception occurs
     * while leaving the multicast group.
     */
    protected abstract void leave(InetAddress inetaddr) throws IOException;

    /** 
     * Join the multicast group.
     * @param mcastaddr address to join.
     * @param netIf specifies the local interface to receive multicast
     *        datagram packets
     * @throws IOException if an I/O exception occurs while joining
     * the multicast group
     * @since 1.4
     */
    protected abstract void joinGroup(SocketAddress mcastaddr, NetworkInterface
        netIf) throws IOException;

    /** 
     * Leave the multicast group.
     * @param mcastaddr address to leave.
     * @param netIf specified the local interface to leave the group at
     * @throws IOException if an I/O exception occurs while leaving
     * the multicast group
     * @since 1.4
     */
    protected abstract void leaveGroup(SocketAddress mcastaddr, NetworkInterface
        netIf) throws IOException;

    /** 
     * Close the socket.
     */
    protected abstract void close();

    /** 
     * Gets the local port.
     * @return an <tt>int</tt> representing the local port value
     */
    protected int getLocalPort() {
        return 0;
    }

    /** 
     * Gets the datagram socket file descriptor.
     * @return a <tt>FileDescriptor</tt> object representing the datagram socket
     * file descriptor
     */
    protected FileDescriptor getFileDescriptor() {
        return null;
    }
}
