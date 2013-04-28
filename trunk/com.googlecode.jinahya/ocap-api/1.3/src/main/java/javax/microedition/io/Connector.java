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

import java.io.*;

/** 
 * This class is a factory for creating new Connection objects.
 * <p>
 * The creation of Connections is performed dynamically by looking
 * up a protocol implementation class whose name is formed from the
 * <!-- platform name (read from a system property) and the --> protocol name
 * of the requested connection. <!-- (extracted from the parameter string
 * supplied by the application programmer.) -->
 *
 * The parameter string that describes the target should conform
 * to the URL format as described in RFC 2396.
 * This takes the general form:
 * <p>
 * <code>{scheme}:[{target}][{params}]</code>
 * <p>
 * where <code>{scheme}</code> is the name of a protocol such as
 * <i>http</i>.
 * <p>
 * The <code>{target}</code> is normally some kind of network
 * address.
 * <p>
 * Any <code>{params}</code> are formed as a series of equates
 * of the form ";x=y".  Example: ";type=a".
 * <p>
 * An optional second parameter may be specified to the open
 * function. This is a mode flag that indicates to the protocol
 * handler the intentions of the calling code. The options here
 * specify if the connection is going to be read (READ), written
 * (WRITE), or both (READ_WRITE). The validity of these flag
 * settings is protocol dependent. For instance, a connection
 * for a printer would not allow read access, and would throw
 * an IllegalArgumentException. If the mode parameter is not
 * specified, READ_WRITE is used by default.
 * <p>
 * An optional third parameter is a boolean flag that indicates
 * if the calling code can handle timeout exceptions. If this
 * flag is set, the protocol implementation may throw an
 * InterruptedIOException when it detects a timeout condition.
 * This flag is only a hint to the protocol handler, and it
 * does not guarantee that such exceptions will actually be thrown.
 * If this parameter is not set, no timeout exceptions will be
 * thrown.
 * <p>
 * Because connections are frequently opened just to gain access
 * to a specific input or output stream, four convenience
 * functions are provided for this purpose.
 *
 * See also: {@link DatagramConnection DatagramConnection}
 * for information relating to datagram addressing
 *
 * @author  Nik Shaylor
 * @version 12/17/01 (CLDC 1.1)
 * @since   CLDC 1.0
 */
public class Connector
{
    /** 
     * Access mode READ.
     */
    public static final int READ = 1;

    /** 
     * Access mode WRITE.
     */
    public static final int WRITE = 2;

    /** 
     * Access mode READ_WRITE.
     */
    public static final int READ_WRITE = 3;

    /*
     * This hidden constructor does not necessarily correspond to
     * a constructor in the original source file -- it keeps javadoc
     * from generating an inappropriate default constructor.
     */
    private Connector() { }

    /** 
     * Create and open a Connection. In case of <code>file</code> protocol,
     * the file gets overwritten.
     *
     * @param name             The URL for the connection.
     * @return                 A new Connection object.
     *
     * @exception IllegalArgumentException If a parameter is invalid.
     * @exception ConnectionNotFoundException If the target of the
     *   name cannot be found, or if the requested protocol type
     *   is not supported.
     * @exception IOException  If some other kind of I/O error occurs.
     * @exception SecurityException  May be thrown if access to the
     *   protocol handler is prohibited.
     */
    public static Connection open(String name) throws IOException {
        return null;
    }

    /** 
     * Create and open a Connection. In case of <code>file</code> protocol,
     * the file gets overwritten for mode WRITE or READ_WRITE.
     *
     * @param name             The URL for the connection.
     * @param mode             The access mode.
     * @return                 A new Connection object.
     *
     * @exception IllegalArgumentException If a parameter is invalid.
     * @exception ConnectionNotFoundException If the target of the
     *   name cannot be found, or if the requested protocol type
     *   is not supported.
     * @exception IOException  If some other kind of I/O error occurs.
     * @exception SecurityException  May be thrown if access to the
     *   protocol handler is prohibited.
     */
    public static Connection open(String name, int mode) throws IOException {
        return null;
    }

    /** 
     * Create and open a Connection. In case of <code>file</code> protocol,
     * the file gets overwritten for mode WRITE or READ_WRITE.
     *
     * @param name             The URL for the connection
     * @param mode             The access mode
     * @param timeouts         A flag to indicate that the caller
     *                         wants timeout exceptions
     * @return                 A new Connection object
     *
     * @exception IllegalArgumentException If a parameter is invalid.
     * @exception ConnectionNotFoundException If the target of the
     *   name cannot be found, or if the requested protocol type
     *   is not supported.
     * @exception IOException  If some other kind of I/O error occurs.
     * @exception SecurityException  May be thrown if access to the
     *   protocol handler is prohibited.
     */
    public static Connection open(String name, int mode, boolean timeouts)
        throws IOException
    {
        return null;
    }

    /** 
     * Create and open a connection input stream.
     *
     * @param  name            The URL for the connection.  Some possible URL
     *			       prefixes that can be used include: "comm:", 
     *			       "http:", "socket:", and "ssl:" when available
     *			       by the implementation and following the
     *			       respective connection specification.
     * @return                 A DataInputStream.
     *
     * @exception IllegalArgumentException If a parameter is invalid.
     * @exception ConnectionNotFoundException If the target of the
     *   name cannot be found, or if the requested protocol type
     *   is not supported.
     * @exception IOException  If some other kind of I/O error occurs.
     * @exception SecurityException  May be thrown if access to the
     *   protocol handler is prohibited.
     */
    public static DataInputStream openDataInputStream(String name)
        throws IOException
    {
        return null;
    }

    /** 
     * Create and open a connection output stream.
     *
     * @param  name            The URL for the connection.  Some possible URL
     *			       prefixes that can be used include: "comm:", 
     *			       "http:", "socket:", and "ssl:" when available
     *			       by the implementation and following the
     *			       respective connection specification.
     * @return                 A DataOutputStream.
     *
     * @exception IllegalArgumentException If a parameter is invalid.
     * @exception ConnectionNotFoundException If the target of the
     *   name cannot be found, or if the requested protocol type
     *   is not supported.
     * @exception IOException  If some other kind of I/O error occurs.
     * @exception SecurityException  May be thrown if access to the
     *   protocol handler is prohibited.
     */
    public static DataOutputStream openDataOutputStream(String name)
        throws IOException
    {
        return null;
    }

    /** 
     * Create and open a connection input stream.
     *
     * @param  name            The URL for the connection.  Some possible URL
     *			       prefixes that can be used include: "comm:", 
     *			       "http:", "socket:", and "ssl:" when available
     *			       by the implementation and following the
     *			       respective connection specification.
     * @return                 An InputStream.
     *
     * @exception IllegalArgumentException If a parameter is invalid.
     * @exception ConnectionNotFoundException If the target of the
     *   name cannot be found, or if the requested protocol type
     *   is not supported.
     * @exception IOException  If some other kind of I/O error occurs.
     * @exception SecurityException  May be thrown if access to the
     *   protocol handler is prohibited.
     */
    public static InputStream openInputStream(String name) throws IOException {
        return null;
    }

    /** 
     * Create and open a connection output stream. In case of <code>file</code> protocol,
     * the file gets overwritten.
     *
     * @param  name            The URL for the connection.  Some possible URL
     *			       prefixes that can be used include: "comm:", 
     *			       "http:", "socket:", and "ssl:" when available
     *			       by the implementation and following the
     *			       respective connection specification.
     * @return                 An OutputStream.
     *
     * @exception IllegalArgumentException If a parameter is invalid.
     * @exception ConnectionNotFoundException If the target of the
     *   name cannot be found, or if the requested protocol type
     *   is not supported.
     * @exception IOException  If some other kind of I/O error occurs.
     * @exception SecurityException  May be thrown if access to the
     *   protocol handler is prohibited.
     */
    public static OutputStream openOutputStream(String name) throws IOException
    {
        return null;
    }
}
