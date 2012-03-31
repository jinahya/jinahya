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
This work corresponds to the API signatures of CDC Security
(Java Secure Socket Extension - JSSE) Optional Package interfaces and modules.

*/




/*
 * @(#)SSLServerSocketFactory.java	1.9 06/05/23
 */
  
/*
 * NOTE:
 * Because of various external restrictions (i.e. US export
 * regulations, etc.), the actual source code can not be provided
 * at this time. This file represents the skeleton of the source
 * file, so that javadocs of the API can be created.
 */

package javax.net.ssl;

import java.security.*;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.SocketException;
import javax.net.ServerSocketFactory;

/** 
 * <code>SSLServerSocketFactory</code>s create 
 * <code>SSLServerSocket</code>s.
 *
 * @since 1.4
 * @see SSLSocket
 * @see SSLServerSocket
 * @version 1.20
 * @author David Brownell
 */
public abstract class SSLServerSocketFactory extends ServerSocketFactory
{

    /** 
     * Constructor is used only by subclasses.
     */
    protected SSLServerSocketFactory() { }

    /** 
     * Returns the default SSL server socket factory.
     * The default implementation can be changed by setting the value of the
     * "ssl.ServerSocketFactory.provider" security property (in the Java
     * security properties file) to the desired class.
     *
     * <p>If SSL has not been
     * configured properly for this virtual machine, the factory will be
     * inoperative (use of which will report instantiation exceptions).
     *
     * @return the default <code>ServerSocketFactory</code>
     * @throws UnsupportedOperationException if the underlying
     *          implementation does not support <code>SSLServerSocket</code>
     *          funcationality.
     */
    public static synchronized ServerSocketFactory getDefault() throws
	UnsupportedOperationException
	{ return null; }

    /** 
     * Returns the list of cipher suites which are enabled by default.
     * Unless a different list is enabled, handshaking on an SSL connection
     * will use one of these cipher suites.  The minimum quality of service
     * for these defaults requires confidentiality protection and server
     * authentication (that is, no anonymous cipher suites).
     *
     * @see #getSupportedCipherSuites()
     * @return array of the cipher suites enabled by default
     */
    public abstract String[] getDefaultCipherSuites();

    /** 
     * Returns the names of the cipher suites which could be enabled for use
     * on an SSL connection created by this factory.
     * Normally, only a subset of these will actually
     * be enabled by default, since this list may include cipher suites which
     * do not meet quality of service requirements for those defaults.  Such
     * cipher suites are useful in specialized applications.
     *
     * @return an array of cipher suite names
     * @see #getDefaultCipherSuites()
     */
    public abstract String[] getSupportedCipherSuites();
}
