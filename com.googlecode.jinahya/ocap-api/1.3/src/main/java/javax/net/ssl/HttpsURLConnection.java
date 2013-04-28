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
 * @(#)HttpsURLConnection.java	1.8 05/03/12
 */
  
/*
 * NOTE:
 * Because of various external restrictions (i.e. US export
 * regulations, etc.), the actual source code can not be provided
 * at this time. This file represents the skeleton of the source
 * file, so that javadocs of the API can be created.
 */

package javax.net.ssl;

import java.net.URL;
import java.net.HttpURLConnection;
import java.io.IOException;

/** 
 * <code>HttpsURLConnection</code> extends <code>HttpURLConnection</code>
 * with support for https-specific features.
 * <P>
 * See <A HREF="http://www.w3.org/pub/WWW/Protocols/">
 * http://www.w3.org/pub/WWW/Protocols/</A> and
 * <A HREF="http://www.ietf.org/"> RFC 2818 </A>
 * for more details on the
 * https specification.
 * <P>
 * This class uses <code>HostnameVerifier</code> and
 * <code>SSLSocketFactory</code>.
 * There are default implementations defined for both classes.
 * However, the implementations can be replaced on a per-class (static) or
 * per-instance basis.  All new <code>HttpsURLConnection</code>s instances
 * will be assigned
 * the "default" static values at instance creation, but they can be overriden
 * by calling the appropriate per-instance set method(s) before
 * <code>connect</code>ing.
 *
 * @since 1.4
 * @version 1.19
 */
public abstract class HttpsURLConnection extends HttpURLConnection
{
    /** 
     * The hostnameVerifier for this object.
     */
    protected HostnameVerifier hostnameVerifier;

    /** 
     * Creates an <code>HttpsURLConnection</code> using the
     * URL specified.
     *
     * @param url the URL
     * @throws IOException if the connection can't be established
     */
    protected HttpsURLConnection(URL url) throws IOException { super(null); }

    /** 
     * Returns the cipher suite in use on this connection.
     *
     * @return the cipher suite
     * @throws IllegalStateException if this method is called before
     *		the connection has been established.
     */
    public abstract String getCipherSuite();

    /** 
     * Returns the certificate(s) that were sent to the server during
     * handshaking.
     * <P>
     * When multiple certificates are available for use in a
     * handshake, the implementation chooses what it considers the
     * "best" certificate chain available, and transmits that to
     * the other side.  This method allows the caller to know
     * which certificate chain was actually sent.
     *
     * @return an ordered array of certificates,
     *		with the client's own certificate first followed by any
     *		certificate authorities.  If no certificates were sent,
     *		then null is returned.
     * @throws IllegalStateException if this method is called before
     *		the connection has been established.
     */
    public abstract java.security.cert.Certificate[] getLocalCertificates();

    /** 
     * Returns the server's certificate chain which was established
     * as part of defining the session.
     *
     * @return an ordered array of server certificates,
     *		with the peer's own certificate first followed by
     *		any certificate authorities.
     * @throws SSLPeerUnverifiedException if the peer is not verified.
     * @throws IllegalStateException if this method is called before
     *		the connection has been established.
     */
    public abstract java.security.cert.Certificate[] getServerCertificates()
        throws SSLPeerUnverifiedException;

    /** 
     * Sets the default HostnameVerifier inherited when an instance
     * of this class is created.
     * <P>
     * If this method is not called, the default hostname verifier
     * assumes the connection should not be permitted.
     *
     * @param v the default host name verifier
     * @throws IllegalArgumentException if the HostnameVerifier
     *		parameter is null.
     * @see #getDefaultHostnameVerifier()
     */
    public static void setDefaultHostnameVerifier(HostnameVerifier v) { }

    /** 
     * Gets the default HostnameVerifier that it inherited when an
     * instance of this class is created.
     *
     * @return the default host name verifier
     * @see #setDefaultHostnameVerifier(HostnameVerifier)
     */
    public static HostnameVerifier getDefaultHostnameVerifier() { return null; }

    /** 
     * Sets the HostnameVerifier.
     * <P>
     * When called on a new instance of this class, this method overrides
     * the default static hostname verifier set by
     * {@link #setDefaultHostnameVerifier(HostnameVerifier)
     *		setDefaultHostnameVerifier}.
     *
     * @param v the host name verifier
     * @throws IllegalArgumentException if the HostnameVerifier
     *	parameter is null.
     * @see #getHostnameVerifier()
     * @see #setDefaultHostnameVerifier(HostnameVerifier)
     */
    public void setHostnameVerifier(HostnameVerifier v) { }

    /** 
     * Gets the HostnameVerifier in place on this instance.
     * <P>
     *
     * @return the host name verifier
     * @see #setHostnameVerifier(HostnameVerifier)
     * @see #setDefaultHostnameVerifier(HostnameVerifier)
     */
    public HostnameVerifier getHostnameVerifier() { return null; }

    /** 
     * Sets the default SSL socket factory inherited when an instance
     * of this class is created.
     * <P>
     * The socket factories are used when creating sockets for secure
     * https URL connections.
     *
     * @param sf the default SSL socket factory
     * @throws IllegalArgumentException if the SSLSocketFactory
     *		parameter is null.
     * @see #getDefaultSSLSocketFactory()
     */
    public static void setDefaultSSLSocketFactory(SSLSocketFactory sf) { }

    /** 
     * Gets the default static SSL socket factory used when creating sockets
     * for secure https URL connections.
     *
     * @return the default <code>SSLSocketFactory</code>
     * @see #setDefaultSSLSocketFactory(SSLSocketFactory)
     */
    public static SSLSocketFactory getDefaultSSLSocketFactory() { return null; }

    /** 
     * Sets the SSL socket factory to be used when creating sockets
     * for secure https URL connections.
     * <P>
     * If called on a new instance of this class, this method overrides
     * the default static socket factory set by
     * {@link #setDefaultSSLSocketFactory(SSLSocketFactory)
     *		setDefaultSSLSocketFactory}.
     *
     * @param sf the SSL socket factory
     * @throws IllegalArgumentException if the SSLSocketFactory
     *		parameter is null.
     * @see #getSSLSocketFactory()
     */
    public void setSSLSocketFactory(SSLSocketFactory sf) { }

    /** 
     * Gets the SSL socket factory to be used when creating sockets
     * for secure https URL connections.
     *
     * @return the <code>SSLSocketFactory</code>
     * @see #setSSLSocketFactory(SSLSocketFactory)
     */
    public SSLSocketFactory getSSLSocketFactory() { return null; }
}
