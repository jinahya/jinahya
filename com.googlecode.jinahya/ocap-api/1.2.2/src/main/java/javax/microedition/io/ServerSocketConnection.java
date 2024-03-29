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


  


package javax.microedition.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;

/** 
 * This interface defines the server socket stream connection.
 * <P>
 * A server socket is accessed using a generic connection string 
 * with the host omitted. For example, <code>socket://:79</code> 
 * defines an inbound server socket on port <code>79</code>.
 * The host can be discovered using the <code>getLocalAddress</code>
 * method. 
 * </P> 
 * <P>
 * The <code>acceptAndOpen()</code> method returns a 
 * <code>SocketConnection</code> instance. In addition to the
 * normal <code>StreamConnection</code> behavior, the 
 * <code>SocketConnection</code> supports accessing the IP
 * end point addresses of the live connection and access
 * to socket options that control the buffering and timing delays 
 * associated with specific application usage of the connection.
 * </P>
 * <P>
 * Access to server socket connections may be restricted by the
 * security policy of the device.  <code>Connector.open</code> MUST
 * check access for the initial server socket connection and
 * <code>acceptAndOpen</code> MUST check before returning
 * each new <code>SocketConnection</code>.
 * </P>
 * <P>
 * A server socket can be used to dynamically select an
 * available port by omitting both the host and the port
 * parameters in the connection URL string.
 * For example, <code>socket://</code> 
 * defines an inbound server socket on a port which is allocated 
 * by the system.
 * To discover the assigned port number use the 
 * <code>getLocalPort</code> method.
 * </P> 
 * <H2>
 * BNF Format for Connector.open() string
 * </H2>
 * <P>
 * The URI must conform to the BNF syntax specified below.  If the URI
 * does not conform to this syntax, an <code>IllegalArgumentException</code>
 * is thrown.
 * </P>
 * <TABLE BORDER="1">
 * <TR>
 * <TD>&lt;socket_connection_string&gt; </TD>
 * <TD>::= "<strong>socket://</strong>" | 
 *          "<strong>socket://</strong>"&lt;hostport&gt; </TD>
 * </TR>
 * <TR>
 * <TD>&lt;hostport&gt; </TD>
 * <TD>::= <I>host</I> ":" <I>port </I> </TD>
 * </TR>
 * <TR>
 * <TD>&lt;host&gt; </TD>
 * <TD>::= omitted for inbound connections, 
 * See <a href="SocketConnection.html">SocketConnection</a>
 * </TD>
 * </TR>
 * <TR>
 * <TD>&lt;port&gt; </TD>
 * <TD>::= <I>numeric port number </I>(omitted for system assigned port) </TD>
 * </TR>
 * </TABLE>
 * <H2>
 * Examples
 * </H2>
 * <P>
 * The following examples show how a <code>ServerSocketConnection</code>
 * would be used to access a sample loopback program.
 * </P>
 * <PRE>
 *   // Create the server listening socket for port 1234 
 *   ServerSocketConnection scn = (ServerSocketConnection)
 *                            Connector.open("socket://:1234");
 *
 *   // Wait for a connection.
 *   SocketConnection sc = (SocketConnection) scn.acceptAndOpen();
 *
 *   // Set application specific hints on the socket.
 *   sc.setSocketOption(DELAY, 0);
 *   sc.setSocketOption(LINGER, 0);
 *   sc.setSocketOption(KEEPALIVE, 0);
 *   sc.setSocketOption(RCVBUF, 128);
 *   sc.setSocketOption(SNDBUF, 128);
 *
 *   // Get the input stream of the connection.
 *   DataInputStream is = sc.openDataInputStream();
 *
 *   // Get the output stream of the connection.
 *   DataOutputStream os = sc.openDataOutputStream();
 *
 *   // Read the input data.
 *   String result = is.readUTF();
 *
 *   // Echo the data back to the sender.
 *   os.writeUTF(result);
 *
 *   // Close everything.
 *   is.close();
 *   os.close();
 *   sc.close();
 *   scn.close();
 *   ..
 * </PRE>
 * @since MIDP 2.0
 */
public interface ServerSocketConnection extends StreamConnectionNotifier
{

    /** 
     * Gets the local address to which the socket is bound.
     *
     * <P>The host address(IP number) that can be used to connect to this
     * end of the socket connection from an external system. 
     * Since IP addresses may be dynamically assigned, a remote application
     * will need to be robust in the face of IP number reasssignment.</P>
     * <P> The local hostname (if available) can be accessed from 
     * <code> System.getProperty("microedition.hostname")</code>
     * </P>
     *
     * @return the local address to which the socket is bound.
     * @exception  IOException  if the connection was closed
     * @see SocketConnection
     */
    public String getLocalAddress() throws IOException;

    /** 
     * Returns the local port to which this socket is bound.
     *
     * @return the local port number to which this socket is connected.
     * @exception  IOException  if the connection was closed
     * @see SocketConnection
     */
    public int getLocalPort() throws IOException;
}
