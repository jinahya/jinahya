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
 * This class represents a datagram packet. 
 * <p>
 * Datagram packets are used to implement a connectionless packet 
 * delivery service. Each message is routed from one machine to 
 * another based solely on information contained within that packet. 
 * Multiple packets sent from one machine to another might be routed 
 * differently, and might arrive in any order. Packet delivery is
 * not guaranteed.
 *
 * @author  Pavani Diwanji
 * @author  Benjamin Renaud
 * @version 1.30, 02/02/00
 * @since   JDK1.0
 */
public final class DatagramPacket
{

    /** 
     * Constructs a <code>DatagramPacket</code> for receiving packets of 
     * length <code>length</code>, specifying an offset into the buffer.
     * <p>
     * The <code>length</code> argument must be less than or equal to 
     * <code>buf.length</code>. 
     *
     * @param   buf      buffer for holding the incoming datagram.
     * @param   offset   the offset for the buffer
     * @param   length   the number of bytes to read.
     *
     * @since JDK1.2
     */
    public DatagramPacket(byte[] buf, int offset, int length) { }

    /** 
     * Constructs a <code>DatagramPacket</code> for receiving packets of 
     * length <code>length</code>. 
     * <p>
     * The <code>length</code> argument must be less than or equal to 
     * <code>buf.length</code>. 
     *
     * @param   buf      buffer for holding the incoming datagram.
     * @param   length   the number of bytes to read.
     */
    public DatagramPacket(byte[] buf, int length) { }

    /** 
     * Constructs a datagram packet for sending packets of length
     * <code>length</code> with offset <code>ioffset</code>to the
     * specified port number on the specified host. The
     * <code>length</code> argument must be less than or equal to
     * <code>buf.length</code>.
     *
     * @param   buf      the packet data.
     * @param   offset   the packet data offset.
     * @param   length   the packet data length.
     * @param   address  the destination address.
     * @param   port     the destination port number.
     * @see java.net.InetAddress
     *
     * @since JDK1.2
     */
    public DatagramPacket(byte[] buf, int offset, int length, InetAddress
        address, int port)
    { }

    /** 
     * Constructs a datagram packet for sending packets of length
     * <code>length</code> with offset <code>ioffset</code>to the
     * specified port number on the specified host. The
     * <code>length</code> argument must be less than or equal to
     * <code>buf.length</code>.
     *
     * @param   buf      the packet data.
     * @param   offset   the packet data offset.
     * @param   length   the packet data length.
     * @param   address  the destination socket address.
     * @throws  IllegalArgumentException if address type is not supported
     * @see java.net.InetAddress
     *
     * @since 1.4
     */
    public DatagramPacket(byte[] buf, int offset, int length, SocketAddress
        address) throws SocketException
    { }

    /** 
     * Constructs a datagram packet for sending packets of length 
     * <code>length</code> to the specified port number on the specified 
     * host. The <code>length</code> argument must be less than or equal 
     * to <code>buf.length</code>. 
     *
     * @param   buf      the packet data.
     * @param   length   the packet length.
     * @param   address  the destination address.
     * @param   port     the destination port number.
     * @see     java.net.InetAddress
     */
    public DatagramPacket(byte[] buf, int length, InetAddress address, int port)
    { }

    /** 
     * Constructs a datagram packet for sending packets of length 
     * <code>length</code> to the specified port number on the specified 
     * host. The <code>length</code> argument must be less than or equal 
     * to <code>buf.length</code>. 
     *
     * @param   buf      the packet data.
     * @param   length   the packet length.
     * @param   address  the destination address.
     * @throws  IllegalArgumentException if address type is not supported
     * @since 1.4
     * @see     java.net.InetAddress
     */
    public DatagramPacket(byte[] buf, int length, SocketAddress address)
        throws SocketException
    { }

    /** 
     * Returns the IP address of the machine to which this datagram is being
     * sent or from which the datagram was received.
     *
     * @return  the IP address of the machine to which this datagram is being
     *          sent or from which the datagram was received.
     * @see     java.net.InetAddress
     * @see #setAddress(java.net.InetAddress)
     */
    public synchronized InetAddress getAddress() {
        return null;
    }

    /** 
     * Returns the port number on the remote host to which this datagram is
     * being sent or from which the datagram was received.
     *
     * @return  the port number on the remote host to which this datagram is
     *          being sent or from which the datagram was received.
     * @see #setPort(int)
     */
    public synchronized int getPort() {
        return 0;
    }

    /** 
     * Returns the data buffer. The data received or the data to be sent
     * starts from the <code>offset</code> in the buffer, 
     * and runs for <code>length</code> long.
     *
     * @return  the buffer used to receive or  send data
     * @see #setData(byte[], int, int)
     */
    public synchronized byte[] getData() {
        return null;
    }

    /** 
     * Returns the offset of the data to be sent or the offset of the
     * data received.
     *
     * @return  the offset of the data to be sent or the offset of the
     *          data received.
     *
     * @since JDK1.2
     */
    public synchronized int getOffset() {
        return 0;
    }

    /** 
     * Returns the length of the data to be sent or the length of the
     * data received.
     *
     * @return  the length of the data to be sent or the length of the
     *          data received.
     * @see #setLength(int)
     */
    public synchronized int getLength() {
        return 0;
    }

    /** 
     * Set the data buffer for this packet. This sets the
     * data, length and offset of the packet.
     *
     * @param buf the buffer to set for this packet
     *
     * @param offset the offset into the data
     *
     * @param length the length of the data 
     *       and/or the length of the buffer used to receive data
     *
     * @exception NullPointerException if the argument is null
     *
     * @see #getData
     * @see #getOffset
     * @see #getLength
     *
     * @since JDK1.2 
     */
    public synchronized void setData(byte[] buf, int offset, int length) { }

    /** 
     * Sets the IP address of the machine to which this datagram
     * is being sent.
     * @param iaddr the <code>InetAddress</code>
     * @since   JDK1.1
     * @see #getAddress()
     */
    public synchronized void setAddress(InetAddress iaddr) { }

    /** 
     * Sets the port number on the remote host to which this datagram
     * is being sent.
     * @param iport the port number
     * @since   JDK1.1
     * @see #getPort()
     */
    public synchronized void setPort(int iport) { }

    /** 
     * Sets the SocketAddress (usually IP address + port number) of the remote
     * host to which this datagram is being sent.
     *
     * @param address the <code>SocketAddress</code>
     * @throws  IllegalArgumentException if address is null or is a
     *          SocketAddress subclass not supported by this socket
     * 
     * @since 1.4
     * @see #getSocketAddress
     */
    public synchronized void setSocketAddress(SocketAddress address) { }

    /** 
     * Gets the SocketAddress (usually IP address + port number) of the remote
     * host that this packet is being sent to or is coming from.
     *
     * @return the <code>SocketAddress</code>
     * @since 1.4
     * @see #setSocketAddress
     */
    public synchronized SocketAddress getSocketAddress() {
        return null;
    }

    /** 
     * Set the data buffer for this packet. With the offset of 
     * this DatagramPacket set to 0, and the length set to
     * the length of <code>buf</code>.
     *
     * @param buf the buffer to set for this packet.
     *
     * @exception NullPointerException if the argument is null.
     *
     * @see #getLength
     * @see #getData
     *
     * @since JDK1.1 
     */
    public synchronized void setData(byte[] buf) { }

    /** 
     * Set the length for this packet. The length of the packet is
     * the number of bytes from the packet's data buffer that will be
     * sent, or the number of bytes of the packet's data buffer that
     * will be used for receiving data. The length must be lesser or
     * equal to the offset plus the length of the packet's buffer.
     *
     * @param length the length to set for this packet.
     * 
     * @exception IllegalArgumentException if the length is negative
     * of if the length is greater than the packet's data buffer
     * length.
     *
     * @see #getLength
     * @see #setData
     *
     * @since JDK1.1 
     */
    public synchronized void setLength(int length) { }
}
