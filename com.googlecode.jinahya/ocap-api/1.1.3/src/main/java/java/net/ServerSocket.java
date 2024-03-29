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
import java.security.AccessController;
import java.security.PrivilegedExceptionAction;

/** 
 * This class implements server sockets. A server socket waits for 
 * requests to come in over the network. It performs some operation 
 * based on that request, and then possibly returns a result to the requester.
 * <p>
 * The actual work of the server socket is performed by an instance 
 * of the <code>SocketImpl</code> class. An application can 
 * change the socket factory that creates the socket 
 * implementation to configure itself to create sockets 
 * appropriate to the local firewall. 
 *
 * @author  unascribed
 * @version 1.43 10/17/00
 * @see     java.net.SocketImpl
 * @see     java.net.ServerSocket#setSocketFactory(java.net.SocketImplFactory)
 * @since   JDK1.0
 */
public class ServerSocket
{

    /** 
     * Creates an unbound server socket.
     *
     * @exception IOException IO error when opening the socket.
     * @revised 1.4
     */
    public ServerSocket() throws IOException { }

    /** 
     * Creates a server socket, bound to the specified port. A port of 
     * <code>0</code> creates a socket on any free port. 
     * <p>
     * The maximum queue length for incoming connection indications (a 
     * request to connect) is set to <code>50</code>. If a connection 
     * indication arrives when the queue is full, the connection is refused.
     * <p>
     * If the application has specified a server socket factory, that 
     * factory's <code>createSocketImpl</code> method is called to create 
     * the actual socket implementation. Otherwise a "plain" socket is created.
     * <p>
     * If there is a security manager, 
     * its <code>checkListen</code> method is called
     * with the <code>port</code> argument
     * as its argument to ensure the operation is allowed. 
     * This could result in a SecurityException.
     *
     *
     * @param      port  the port number, or <code>0</code> to use any
     *                   free port.
     * 
     * @exception  IOException  if an I/O error occurs when opening the socket.
     * @exception  SecurityException
     * if a security manager exists and its <code>checkListen</code> 
     * method doesn't allow the operation.
     * 
     * @see        java.net.SocketImpl
     * @see        java.net.SocketImplFactory#createSocketImpl()
     * @see        java.net.ServerSocket#setSocketFactory(java.net.SocketImplFactory)
     * @see        SecurityManager#checkListen
     */
    public ServerSocket(int port) throws IOException { }

    /** 
     * Creates a server socket and binds it to the specified local port 
     * number, with the specified backlog. 
     * A port number of <code>0</code> creates a socket on any 
     * free port. 
     * <p>
     * The maximum queue length for incoming connection indications (a 
     * request to connect) is set to the <code>backlog</code> parameter. If 
     * a connection indication arrives when the queue is full, the 
     * connection is refused. 
     * <p>
     * If the application has specified a server socket factory, that 
     * factory's <code>createSocketImpl</code> method is called to create 
     * the actual socket implementation. Otherwise a "plain" socket is created.
     * <p>
     * If there is a security manager, 
     * its <code>checkListen</code> method is called
     * with the <code>port</code> argument
     * as its argument to ensure the operation is allowed. 
     * This could result in a SecurityException.
     *
     * <P>The <code>backlog</code> argument must be a positive
     * value greater than 0. If the value passed if equal or less
     * than 0, then the default value will be assumed.
     * <P>
     *
     * @param      port     the specified port, or <code>0</code> to use
     *                      any free port.
     * @param      backlog  the maximum length of the queue.
     * 
     * @exception  IOException  if an I/O error occurs when opening the socket.
     * @exception  SecurityException
     * if a security manager exists and its <code>checkListen</code> 
     * method doesn't allow the operation.
     * 
     * @see        java.net.SocketImpl
     * @see        java.net.SocketImplFactory#createSocketImpl()
     * @see        java.net.ServerSocket#setSocketFactory(java.net.SocketImplFactory)
     * @see        SecurityManager#checkListen
     */
    public ServerSocket(int port, int backlog) throws IOException { }

    /** 
     * Create a server with the specified port, listen backlog, and 
     * local IP address to bind to.  The <i>bindAddr</i> argument
     * can be used on a multi-homed host for a ServerSocket that
     * will only accept connect requests to one of its addresses.
     * If <i>bindAddr</i> is null, it will default accepting
     * connections on any/all local addresses.
     * The port must be between 0 and 65535, inclusive.
     * 
     * <P>If there is a security manager, this method 
     * calls its <code>checkListen</code> method
     * with the <code>port</code> argument
     * as its argument to ensure the operation is allowed. 
     * This could result in a SecurityException.
     *
     * <P>The <code>backlog</code> argument must be a positive
     * value greater than 0. If the value passed if equal or less
     * than 0, then the default value will be assumed.
     * <P>
     * @param port the local TCP port
     * @param backlog the listen backlog
     * @param bindAddr the local InetAddress the server will bind to
     * 
     * @throws  SecurityException if a security manager exists and 
     * its <code>checkListen</code> method doesn't allow the operation.
     * 
     * @throws  IOException if an I/O error occurs when opening the socket.
     *
     * @see SocketOptions
     * @see SocketImpl
     * @see SecurityManager#checkListen
     * @since   JDK1.1
     */
    public ServerSocket(int port, int backlog, InetAddress bindAddr)
        throws IOException
    { }

    /** 
     * Binds the <code>ServerSocket</code> to a specific address
     * (IP address and port number).
     * <p>
     * If the address is <code>null</code>, then the system will pick up
     * an ephemeral port and a valid local address to bind the socket.
     * <p>
     * @param	endpoint	The IP address & port number to bind to.
     * @throws	IOException if the bind operation fails, or if the socket
     *			   is already bound.
     * @throws	SecurityException	if a <code>SecurityManager</code> is present and
     * its <code>checkListen</code> method doesn't allow the operation.
     * @throws  IllegalArgumentException if endpoint is a
     *          SocketAddress subclass not supported by this socket
     * @since 1.4
     */
    public void bind(SocketAddress endpoint) throws IOException { }

    /** 
     * Binds the <code>ServerSocket</code> to a specific address
     * (IP address and port number).
     * <p>
     * If the address is <code>null</code>, then the system will pick up
     * an ephemeral port and a valid local address to bind the socket.
     * <P>
     * The <code>backlog</code> argument must be a positive
     * value greater than 0. If the value passed if equal or less
     * than 0, then the default value will be assumed.
     * @param	endpoint	The IP address & port number to bind to.
     * @param	backlog		The listen backlog length.
     * @throws	IOException if the bind operation fails, or if the socket
     *			   is already bound.
     * @throws	SecurityException	if a <code>SecurityManager</code> is present and
     * its <code>checkListen</code> method doesn't allow the operation.
     * @throws  IllegalArgumentException if endpoint is a
     *          SocketAddress subclass not supported by this socket
     * @since 1.4
     */
    public void bind(SocketAddress endpoint, int backlog) throws IOException { }

    /** 
     * Returns the local address of this server socket.
     *
     * @return  the address to which this socket is bound,
     *          or <code>null</code> if the socket is unbound.
     */
    public InetAddress getInetAddress() {
        return null;
    }

    /** 
     * Returns the port on which this socket is listening.
     *
     * @return  the port number to which this socket is listening or
     *	        -1 if the socket is not bound yet.
     */
    public int getLocalPort() {
        return 0;
    }

    /** 
     * Returns the address of the endpoint this socket is bound to, or
     * <code>null</code> if it is not bound yet.
     *
     * @return a <code>SocketAddress</code> representing the local endpoint of this
     *	       socket, or <code>null</code> if it is not bound yet.
     * @see #getInetAddress()
     * @see #getLocalPort()
     * @see #bind(SocketAddress)
     * @since 1.4
     */
    public SocketAddress getLocalSocketAddress() {
        return null;
    }

    /** 
     * Listens for a connection to be made to this socket and accepts 
     * it. The method blocks until a connection is made. 
     *
     * <p>A new Socket <code>s</code> is created and, if there 
     * is a security manager, 
     * the security manager's <code>checkAccept</code> method is called
     * with <code>s.getInetAddress().getHostAddress()</code> and
     * <code>s.getPort()</code>
     * as its arguments to ensure the operation is allowed. 
     * This could result in a SecurityException.
     * 
     * @exception  IOException  if an I/O error occurs when waiting for a
     *               connection.
     * @exception  SecurityException  if a security manager exists and its  
     *             <code>checkListen</code> method doesn't allow the operation.
     * @exception  SocketTimeoutException if a timeout was previously set with setSoTimeout and
     *             the timeout has been reached.
     *
     * @return the new Socket
     * @see SecurityManager#checkAccept
     * @revised 1.4
     * @spec JSR-51
     */
    public Socket accept() throws IOException {
        return null;
    }

    /** 
     * Subclasses of ServerSocket use this method to override accept()
     * to return their own subclass of socket.  So a FooServerSocket
     * will typically hand this method an <i>empty</i> FooSocket.  On
     * return from implAccept the FooSocket will be connected to a client.
     *
     * @param s the Socket
     * @throws IOException if an I/O error occurs when waiting 
     * for a connection.
     * @since   JDK1.1
     * @revised 1.4
     * @spec JSR-51
     */
    protected final void implAccept(Socket s) throws IOException { }

    /** 
     * Closes this socket. 
     * 
     * Any thread currently blocked in {@link #accept()} will throw
     * a {@link SocketException}.
     *
     * @exception  IOException  if an I/O error occurs when closing the socket.
     * @revised 1.4
     * @spec JSR-51
     */
    public void close() throws IOException { }

    /** 
     * Returns the binding state of the ServerSocket.
     *
     * @return true if the ServerSocket succesfuly bound to an address
     * @since 1.4
     */
    public boolean isBound() {
        return false;
    }

    /** 
     * Returns the closed state of the ServerSocket.
     *
     * @return true if the socket has been closed
     * @since 1.4
     */
    public boolean isClosed() {
        return false;
    }

    /** 
     * Enable/disable SO_TIMEOUT with the specified timeout, in
     * milliseconds.  With this option set to a non-zero timeout,
     * a call to accept() for this ServerSocket
     * will block for only this amount of time.  If the timeout expires,
     * a <B>java.net.SocketTimeoutException</B> is raised, though the
     * ServerSocket is still valid.  The option <B>must</B> be enabled
     * prior to entering the blocking operation to have effect.  The 
     * timeout must be > 0.
     * A timeout of zero is interpreted as an infinite timeout.  
     * @param timeout the specified timeout, in milliseconds
     * @exception SocketException if there is an error in 
     * the underlying protocol, such as a TCP error. 
     * @since   JDK1.1
     * @see #getSoTimeout()
     */
    public synchronized void setSoTimeout(int timeout) throws SocketException
    { }

    /** 
     * Retrive setting for SO_TIMEOUT.  0 returns implies that the
     * option is disabled (i.e., timeout of infinity).
     * @return the SO_TIMEOUT value
     * @exception IOException if an I/O error occurs
     * @since   JDK1.1
     * @see #setSoTimeout(int)
     */
    public synchronized int getSoTimeout() throws IOException {
        return 0;
    }

    /** 
     * Enable/disable the SO_REUSEADDR socket option.
     * <p>
     * When a TCP connection is closed the connection may remain
     * in a timeout state for a period of time after the connection
     * is closed (typically known as the <tt>TIME_WAIT</tt> state 
     * or <tt>2MSL</tt> wait state).
     * For applications using a well known socket address or port 
     * it may not be possible to bind a socket to the required
     * <tt>SocketAddress</tt> if there is a connection in the
     * timeout state involving the socket address or port. 
     * <p>
     * Enabling <tt>SO_REUSEADDR</tt> prior to binding the socket
     * using {@link #bind(SocketAddress)} allows the socket to be
     * bound even though a previous connection is in a timeout
     * state.
     * <p>
     * When a <tt>ServerSocket</tt> is created the initial setting
     * of <tt>SO_REUSEADDR</tt> is not defined. Applications can
     * use {@link getReuseAddress()} to determine the initial 
     * setting of <tt>SO_REUSEADDR</tt>. 
     * <p>
     * The behaviour when <tt>SO_REUSEADDR</tt> is enabled or
     * disabled after a socket is bound (See {@link #isBound()})
     * is not defined.
     * 
     * @param on  whether to enable or disable the socket option
     * @exception SocketException if an error occurs enabling or
     *            disabling the <tt>SO_RESUEADDR</tt> socket option,
     *		  or the socket is closed.
     * @since 1.4
     * @see #getReuseAddress()     
     * @see #bind(SocketAddress)
     * @see #isBound()
     * @see #isClosed()
     */
    public void setReuseAddress(boolean on) throws SocketException { }

    /** 
     * Tests if SO_REUSEADDR is enabled.
     *
     * @return a <code>boolean</code> indicating whether or not SO_REUSEADDR is enabled.
     * @exception SocketException if there is an error
     * in the underlying protocol, such as a TCP error. 
     * @since   1.4
     * @see #setReuseAddress(boolean)
     */
    public boolean getReuseAddress() throws SocketException {
        return false;
    }

    /** 
     * Returns the implementation address and implementation port of 
     * this socket as a <code>String</code>.
     *
     * @return  a string representation of this socket.
     */
    public String toString() {
        return null;
    }

    /** 
     * Sets the server socket implementation factory for the 
     * application. The factory can be specified only once. 
     * <p>
     * When an application creates a new server socket, the socket 
     * implementation factory's <code>createSocketImpl</code> method is 
     * called to create the actual socket implementation. 
     * <p>
     * If there is a security manager, this method first calls
     * the security manager's <code>checkSetFactory</code> method 
     * to ensure the operation is allowed. 
     * This could result in a SecurityException.
     *
     * @param      fac   the desired factory.
     * @exception  IOException  if an I/O error occurs when setting the
     *               socket factory.
     * @exception  SocketException  if the factory has already been defined.
     * @exception  SecurityException  if a security manager exists and its  
     *             <code>checkSetFactory</code> method doesn't allow the operation.
     * @see        java.net.SocketImplFactory#createSocketImpl()
     * @see        SecurityManager#checkSetFactory
     */
    public static synchronized void setSocketFactory(SocketImplFactory fac)
        throws IOException
    { }

    /** 
     * Sets a default proposed value for the SO_RCVBUF option for sockets 
     * accepted from this <tt>ServerSocket</tt>. The value actually set 
     * in the accepted socket must be determined by calling 
     * {@link Socket#getReceiveBufferSize()} after the socket 
     * is returned by {@link #accept()}. 
     * <p>
     * The value of SO_RCVBUF is used both to set the size of the internal
     * socket receive buffer, and to set the size of the TCP receive window
     * that is advertized to the remote peer.
     * <p>
     * It is possible to change the value subsequently, by calling 
     * {@link Socket#setReceiveBufferSize(int)}. However, if the application 
     * wishes to allow a receive window larger than 64K bytes, as defined by RFC1323
     * then the proposed value must be set in the ServerSocket <B>before</B> 
     * it is bound to a local address. This implies, that the ServerSocket must be 
     * created with the no-argument constructor, then setReceiveBufferSize() must 
     * be called and lastly the ServerSocket is bound to an address by calling bind(). 
     * <p>
     * Failure to do this will not cause an error, and the buffer size may be set to the
     * requested value but the TCP receive window in sockets accepted from 
     * this ServerSocket will be no larger than 64K bytes.
     *
     * @exception SocketException if there is an error
     * in the underlying protocol, such as a TCP error. 
     *
     * @param size the size to which to set the receive buffer
     * size. This value must be greater than 0.
     *
     * @exception IllegalArgumentException if the 
     * value is 0 or is negative.
     *
     * @since 1.4
     * @see #getReceiveBufferSize
     */
    public synchronized void setReceiveBufferSize(int size)
        throws SocketException
    { }

    /** 
     * Gets the value of the SO_RCVBUF option for this <tt>ServerSocket</tt>, 
     * that is the proposed buffer size that will be used for Sockets accepted
     * from this <tt>ServerSocket</tt>.
     * 
     * <p>Note, the value actually set in the accepted socket is determined by
     * calling {@link Socket#getReceiveBufferSize()}.
     * @return the value of the SO_RCVBUF option for this <tt>Socket</tt>.
     * @exception SocketException if there is an error
     * in the underlying protocol, such as a TCP error. 
     * @see #setReceiveBufferSize(int)
     * @since 1.4
     */
    public synchronized int getReceiveBufferSize() throws SocketException {
        return 0;
    }
}
