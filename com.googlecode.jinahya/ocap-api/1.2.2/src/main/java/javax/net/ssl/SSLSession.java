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
 * @(#)SSLSession.java	1.8 05/03/12
 */
  
/*
 * NOTE:
 * Because of various external restrictions (i.e. US export
 * regulations, etc.), the actual source code can not be provided
 * at this time. This file represents the skeleton of the source
 * file, so that javadocs of the API can be created.
 */

package javax.net.ssl;

import java.net.InetAddress;

/** 
 * In SSL, sessions are used to describe an ongoing relationship between
 * two entities.  Each SSL connection involves one session at a time, but
 * that session may be used on many connections between those entities,
 * simultaneously or sequentially.  The session used on a connection may
 * also be replaced by a different session.  Sessions are created, or
 * rejoined, as part of the SSL handshaking protocol. Sessions may be
 * invalidated due to policies affecting security or resource usage,
 * or by an application explicitly calling <code>invalidate</code>.
 * Session management policies are typically used to tune performance.
 *
 * <P> In addition to the standard session attributes, SSL sessions expose
 * these read-only attributes:  <UL>
 *
 *	<LI> <em>Peer Identity.</em>  Sessions are between a particular
 *	client and a particular server.  The identity of the peer may
 *	have been established as part of session setup.  Peers are
 *	generally identified by X.509 certificate chains.
 *
 *	<LI> <em>Cipher Suite Name.</em>  Cipher suites describe the
 *	kind of cryptographic protection that's used by connections
 *	in a particular session.
 *
 *	<LI> <em>Peer Host.</em>  All connections in a session are
 *	between the same two hosts.  The address of the host on the other
 *	side of the connection is available.
 *
 *	</UL>
 *
 * <P> Sessions may be explicitly invalidated.  Invalidation may also
 * be done implicitly, when faced with certain kinds of errors.
 *
 * @since 1.4
 * @version 1.26
 * @author David Brownell
 */
public interface SSLSession
{

    /** 
     * Returns the identifier assigned to this Session.
     *
     * @return the Session identifier
     */
    public byte[] getId();

    /** 
     * Returns the context in which this session is bound.
     * <P>
     * This context may be unavailable in some environments,
     * in which case this method returns null.
     * <P>
     * If the context is available and there is a
     * security manager installed, the caller may require
     * permission to access it or a security exception may be thrown.
     * In a Java 2 environment, the security manager's
     * <code>checkPermission</code> method is called with a
     * <code>SSLPermission("getSSLSessionContext")</code> permission.
     *
     * @return the session context used for this session, or null
     * if the context is unavailable.
     */
    public SSLSessionContext getSessionContext();

    /** 
     * Returns the time at which this Session representation was created,
     * in milliseconds since midnight, January 1, 1970 UTC.
     *
     * @return the time this Session was created
     */
    public long getCreationTime();

    /** 
     * Returns the last time this Session representation was accessed by the
     * session level infrastructure, in milliseconds since
     * midnight, January 1, 1970 UTC.
     * <P>
     * Access indicates a new connection being established using session data.
     * Application level operations, such as getting or setting a value
     * associated with the session, are not reflected in this access time.
     *
     * <P> This information is particularly useful in session management
     * policies.  For example, a session manager thread could leave all
     * sessions in a given context which haven't been used in a long time;
     * or, the sessions might be sorted according to age to optimize some task.
     *
     * @return the last time this Session was accessed
     */
    public long getLastAccessedTime();

    /** 
     * Invalidates the session.
     * <P>
     * Future connections will not be able to
     * resume or join this session.  However, any existing connection
     * using this session can continue to use the session until the 
     * connection is closed.
     */
    public void invalidate();

    /** 
     * Binds the specified <code>value</code> object into the
     * session's application layer data
     * with the given <code>name</code>.
     * <P>
     * Any existing binding using the same <code>name</code> is
     * replaced.  If the new (or existing) <code>value</code> implements the
     * <code>SSLSessionBindingListener</code> interface, the object
     * represented by <code>value</code> is notified appropriately.
     * <p>
     * For security reasons, the same named values may not be
     * visible across different access control contexts.
     *
     * @param name the name to which the data object will be bound.
     *        This may not be null.
     * @param value the data object to be bound. This may not be null.
     * @throws IllegalArgumentException if either argument is null.
     */
    public void putValue(String name, Object value);

    /** 
     * Returns the object bound to the given name in the session's
     * application layer data.  Returns null if there is no such binding.
     * <p>
     * For security reasons, the same named values may not be
     * visible across different access control contexts.
     *
     * @param name the name of the binding to find.
     * @return the value bound to that name, or null if the binding does
     *         not exist.
     * @throws IllegalArgumentException if the argument is null.
     */
    public Object getValue(String name);

    /** 
     * Removes the object bound to the given name in the session's
     * application layer data.  Does nothing if there is no object
     * bound to the given name.  If the bound existing object
     * implements the <code>SessionBindingListener</code> interface,
     * it is notified appropriately.
     * <p>
     * For security reasons, the same named values may not be
     * visible across different access control contexts.
     *
     * @param name the name of the object to remove visible
     *        across different access control contexts
     * @throws IllegalArgumentException if the argument is null.
     */
    public void removeValue(String name);

    /** 
     * Returns an array of the names of all the application layer
     * data objects bound into the Session.
     * <p>
     * For security reasons, the same named values may not be
     * visible across different access control contexts.
     *
     * @return a non-null (possibly empty) array of names of the objects
     *  bound to this Session.
     */
    public String[] getValueNames();

    /** 
     * Returns the identity of the peer which was established as part
     * of defining the session.
     *
     * @return an ordered array of peer certificates,
     *		with the peer's own certificate first followed by any
     *		certificate authorities.
     * @exception SSLPeerUnverifiedException if the peer's identity has not 
     *         been verified
     */
    public java.security.cert.Certificate[] getPeerCertificates()
        throws SSLPeerUnverifiedException;

    /** 
     * Returns the certificate(s) that were sent to the peer during
     * handshaking.
     *
     * When multiple certificates are available for use in a
     * handshake, the implementation chooses what it considers the
     * "best" certificate chain available, and transmits that to
     * the other side.  This method allows the caller to know
     * which certificate chain was actually used.
     *
     * @return an ordered array of certificates,
     * with the local certificate first followed by any
     * certificate authorities.  If no certificates were sent,
     * then null is returned.
     */
    public java.security.cert.Certificate[] getLocalCertificates();

    /** 
     * Returns the identity of the peer which was identified as part
     * of defining the session.
     *
     * <p><em>Note: this method exists for compatibility with previous
     * releases. New applications should use
     * {@link #getPeerCertificates} instead.</em></p>
     *
     * @return an ordered array of peer X.509 certificates,
     *		with the peer's own certificate first followed by any
     *		certificate authorities.  (The certificates are in
     *		the original JSSE certificate
     *		{@link javax.security.cert.X509Certificate} format.)
     * @exception SSLPeerUnverifiedException if the peer's identity
     *		has not been verified
     */
    public javax.security.cert.X509Certificate[] getPeerCertificateChain()
        throws SSLPeerUnverifiedException;

    /** 
     * Returns the name of the SSL cipher suite which is used for all
     * connections in the session.
     *
     * <P> This defines the level of protection
     * provided to the data sent on the connection, including the kind
     * of encryption used and most aspects of how authentication is done.
     *
     * @return the name of the session's cipher suite
     */
    public String getCipherSuite();

    /** 
     * Returns the standard name of the protocol used for all
     * connections in the session. 
     *
     * <P> This defines the protocol used in the connection. 
     *
     * @return the standard name of the protocol used for all
     * connections in the session.
     */
    public String getProtocol();

    /** 
     * Returns the host name of the peer in this session.
     * <P>
     * For the server, this is the client's host;  and for
     * the client, it is the server's host. The name may not be
     * a fully qualified host name or even a host name at all as 
     * it may represent a string encoding of the peer's network address.
     * If such a name is desired, it might
     * be resolved through a name service based on the value returned
     * by this method.
     * <P>
     * This value is not authenticated and should not be relied upon.
     *
     * @return the host name of the peer host
     */
    public String getPeerHost();
}
