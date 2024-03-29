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
import java.io.InputStream;
import java.io.OutputStream;
import java.io.FileDescriptor;

/** 
 * The abstract class <code>SocketImpl</code> is a common superclass 
 * of all classes that actually implement sockets. It is used to 
 * create both client and server sockets. 
 * <p>
 * A "plain" socket implements these methods exactly as 
 * described, without attempting to go through a firewall or proxy. 
 *
 * @author  unascribed
 * @version 1.31 10/17/00
 * @since   JDK1.0
 */
public abstract class SocketImpl implements SocketOptions
{
    /** 
     * The file descriptor object for this socket. 
     */
    protected FileDescriptor fd;

    /** 
     * The IP address of the remote end of this socket. 
     */
    protected InetAddress address;

    /** 
     * The port number on the remote host to which this socket is connected. 
     */
    protected int port;

    /** 
     * The local port number to which this socket is connected. 
     */
    protected int localport;

    public SocketImpl() { }

    /** 
     * Creates either a stream or a datagram socket. 
     *
     * @param      stream   if <code>true</code>, create a stream socket;
     *                      otherwise, create a datagram socket.
     * @exception  IOException  if an I/O error occurs while creating the
     *               socket.
     */
    protected abstract void create(boolean stream) throws IOException;

    /** 
     * Connects this socket to the specified port on the named host. 
     *
     * @param      host   the name of the remote host.
     * @param      port   the port number.
     * @exception  IOException  if an I/O error occurs when connecting to the
     *               remote host.
     */
    protected abstract void connect(String host, int port) throws IOException;

    /** 
     * Connects this socket to the specified port number on the specified host.
     *
     * @param      address   the IP address of the remote host.
     * @param      port      the port number.
     * @exception  IOException  if an I/O error occurs when attempting a
     *               connection.
     */
    protected abstract void connect(InetAddress address, int port)
        throws IOException;

    /** 
     * Connects this socket to the specified port number on the specified host.
     * A timeout of zero is interpreted as an infinite timeout. The connection
     * will then block until established or an error occurs.
     *
     * @param      address   the Socket address of the remote host.
     * @param	  timeout  the timeout value, in milliseconds, or zero for no timeout.
     * @exception  IOException  if an I/O error occurs when attempting a
     *               connection.
     * @since 1.4
     */
    protected abstract void connect(SocketAddress address, int timeout)
        throws IOException;

    /** 
     * Binds this socket to the specified port number on the specified host. 
     *
     * @param      host   the IP address of the remote host.
     * @param      port   the port number.
     * @exception  IOException  if an I/O error occurs when binding this socket.
     */
    protected abstract void bind(InetAddress host, int port) throws IOException;

    /** 
     * Sets the maximum queue length for incoming connection indications 
     * (a request to connect) to the <code>count</code> argument. If a 
     * connection indication arrives when the queue is full, the 
     * connection is refused. 
     *
     * @param      backlog   the maximum length of the queue.
     * @exception  IOException  if an I/O error occurs when creating the queue.
     */
    protected abstract void listen(int backlog) throws IOException;

    /** 
     * Accepts a connection. 
     *
     * @param      s   the accepted connection.
     * @exception  IOException  if an I/O error occurs when accepting the
     *               connection.
     */
    protected abstract void accept(SocketImpl s) throws IOException;

    /** 
     * Returns an input stream for this socket.
     *
     * @return     a stream for reading from this socket.
     * @exception  IOException  if an I/O error occurs when creating the
     *               input stream.
     */
    protected abstract InputStream getInputStream() throws IOException;

    /** 
     * Returns an output stream for this socket.
     *
     * @return     an output stream for writing to this socket.
     * @exception  IOException  if an I/O error occurs when creating the
     *               output stream.
     */
    protected abstract OutputStream getOutputStream() throws IOException;

    /** 
     * Returns the number of bytes that can be read from this socket
     * without blocking.
     *
     * @return     the number of bytes that can be read from this socket
     *             without blocking.
     * @exception  IOException  if an I/O error occurs when determining the
     *               number of bytes available.
     */
    protected abstract int available() throws IOException;

    /** 
     * Closes this socket. 
     *
     * @exception  IOException  if an I/O error occurs when closing this socket.
     */
    protected abstract void close() throws IOException;

    /** 
     * Places the input stream for this socket at "end of stream".
     * Any data sent to this socket is acknowledged and then
     * silently discarded.
     *
     * If you read from a socket input stream after invoking 
     * shutdownInput() on the socket, the stream will return EOF.
     *
     * @exception IOException if an I/O error occurs when shutting down this
     * socket.
     * @see java.net.Socket#shutdownOutput()
     * @see java.net.Socket#close()
     * @see java.net.Socket#setSoLinger(boolean, int)
     */
    protected void shutdownInput() throws IOException { }

    /** 
     * Disables the output stream for this socket.
     * For a TCP socket, any previously written data will be sent
     * followed by TCP's normal connection termination sequence.
     *
     * If you write to a socket output stream after invoking 
     * shutdownOutput() on the socket, the stream will throw 
     * an IOException.
     *
     * @exception IOException if an I/O error occurs when shutting down this
     * socket.
     * @see java.net.Socket#shutdownInput()
     * @see java.net.Socket#close()
     * @see java.net.Socket#setSoLinger(boolean, int)
     */
    protected void shutdownOutput() throws IOException { }

    /** 
     * Returns the value of this socket's <code>fd</code> field.
     *
     * @return  the value of this socket's <code>fd</code> field.
     * @see     java.net.SocketImpl#fd
     */
    protected FileDescriptor getFileDescriptor() {
        return null;
    }

    /** 
     * Returns the value of this socket's <code>address</code> field.
     *
     * @return  the value of this socket's <code>address</code> field.
     * @see     java.net.SocketImpl#address
     */
    protected InetAddress getInetAddress() {
        return null;
    }

    /** 
     * Returns the value of this socket's <code>port</code> field.
     *
     * @return  the value of this socket's <code>port</code> field.
     * @see     java.net.SocketImpl#port
     */
    protected int getPort() {
        return 0;
    }

    /** 
     * Returns whether or not this SocketImpl supports sending 
     * urgent data. By default, false is returned
     * unless the method is overridden in a sub-class
     *
     * @return  true if urgent data supported
     * @see     java.net.SocketImpl#address
     * @since 1.4
     */
    protected boolean supportsUrgentData() {
        return false;
    }

    /** 
     * Send one byte of urgent data on the socket.
     * The byte to be sent is the low eight bits of the parameter
     * @param data The byte of data to send
     * @exception IOException if there is an error
     *  sending the data.
     * @since 1.4
     */
    protected abstract void sendUrgentData(int data) throws IOException;

    /** 
     * Returns the value of this socket's <code>localport</code> field.
     *
     * @return  the value of this socket's <code>localport</code> field.
     * @see     java.net.SocketImpl#localport
     */
    protected int getLocalPort() {
        return 0;
    }

    /** 
     * Returns the address and port of this socket as a <code>String</code>.
     *
     * @return  a string representation of this socket.
     */
    public String toString() {
        return null;
    }
}
