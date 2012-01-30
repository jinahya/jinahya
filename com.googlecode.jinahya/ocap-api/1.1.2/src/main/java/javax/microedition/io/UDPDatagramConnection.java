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

/** 
 * This interface defines a datagram connection which knows
 * it's local end point address.
 * The protocol is transaction oriented, and delivery and duplicate
 * protection are not guaranteed.  Applications requiring ordered
 * reliable delivery of streams of data should use
 * the <code>SocketConnection</code>.
 * <p>
 * A <code>UDPDatagramConnection</code> is returned from
 * <code>Connector.open()</code> in response to a request to
 * open a <code>datagram://</code> URL connection string.
 * If the connection string omits both the <code>host</code>
 * and <code>port</code> fields in the URL string, then the
 * system will allocate an available port. The local
 * address and the local port can be discovered using
 * the accessor methods within this interface.
 * </p>
 * <p>
 * The syntax described here for the datagram URL connection string
 * is also valid for the <code>Datagram.setAddress()</code> method
 * used to assign a destination address to a <code>Datagram</code>
 * to be sent. e.g., <code>datagram://</code><em>host:port</em>
 * </p>
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
 * <TD>&lt;datagram_connection_string&gt; </TD>
 * <TD>::= "<strong>datagram://</strong>" |
 *         "<strong>datagram://</strong>"&lt;hostport&gt; </TD>
 * </TR>
 * <TR>
 * <TD>&lt;hostport&gt; </TD>
 * <TD>::= <I>host</I> ":" <I>port </I> </TD>
 * </TR>
 * <TR>
 * <TD>&lt;host&gt; </TD>
 * <TD>::= <I>host name or IP address </I>
 * (omitted for inbound connections) </TD>
 * </TR>
 * <TR>
 * <TD>&lt;port&gt; </TD>
 * <TD>::= <I>numeric port number </I>(omitted for system assigned port) </TD>
 * </TR>
 * </TABLE>
 * @since MIDP 2.0
 */
public interface UDPDatagramConnection extends DatagramConnection
{

    /** 
     * Gets the local address to which the
     * datagram connection is bound.
     *
     * <P>The host address(IP number) that can be used to connect to this
     * end of the datagram connection from an external system.
     * Since IP addresses may be dynamically assigned, a remote application
     * will need to be robust in the face of IP number reassignment.</P>
     * <P> The local hostname (if available) can be accessed from
     * <code> System.getProperty("microedition.hostname")</code>
     * </P>
     *
     * @return the local address to which the datagram connection is bound.
     * @exception  IOException  if the connection was closed.
     * @see ServerSocketConnection
     */
    public String getLocalAddress() throws IOException;

    /** 
     * Returns the local port to which this datagram connection is bound.
     *
     * @return the local port number to which this datagram connection
     *         is connected.
     * @exception  IOException  if the connection was closed.
     * @see ServerSocketConnection
     */
    public int getLocalPort() throws IOException;
}
