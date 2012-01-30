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
 * @(#)HandshakeCompletedEvent.java	1.7 05/03/12
 */
  
/*
 * NOTE:
 * Because of various external restrictions (i.e. US export
 * regulations, etc.), the actual source code can not be provided
 * at this time. This file represents the skeleton of the source
 * file, so that javadocs of the API can be created.
 */

package javax.net.ssl;

import java.util.EventObject;
import java.security.cert.Certificate;

/** 
 * This event indicates that an SSL handshake completed on a given
 * SSL connection.  All of the core information about that handshake's
 * result is captured through an "SSLSession" object.  As a convenience,
 * this event class provides direct access to some important session
 * attributes.
 *
 * <P> The source of this event is the SSLSocket on which handshaking
 * just completed.
 *
 * @see SSLSocket
 * @see HandshakeCompletedListener
 * @see SSLSession
 *
 * @since 1.4
 * @version 1.18
 * @author David Brownell
 */
public class HandshakeCompletedEvent extends EventObject
{

    /** 
     * Constructs a new HandshakeCompletedEvent.
     *
     * @param sock the SSLSocket acting as the source of the event
     * @param s the SSLSession this event is associated with
     */
    public HandshakeCompletedEvent(SSLSocket sock, SSLSession s) { super(null); }

    /** 
     * Returns the session that triggered this event.
     *
     * @return the <code>SSLSession</code> for this handshake
     */
    public SSLSession getSession() { return null; }

    /** 
     * Returns the cipher suite in use by the session which was produced
     * by the handshake.  (This is a convenience method for
     * getting the ciphersuite from the SSLsession.)
     *
     * @return the name of the cipher suite negotiated during this session.
     */
    public String getCipherSuite() { return null; }

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
     * @return an ordered array of certificates, with the local
     *		certificate first followed by any
     *		certificate authorities.  If no certificates were sent,
     *		then null is returned.
     */
    public Certificate[] getLocalCertificates() { return null; }

    /** 
     * Returns the identity of the peer which was established as part
     * of defining the session.
     *
     * @return an ordered array of the peer certificates,
     *		with the peer's own certificate first followed by
     *		any certificate authorities.
     * @exception SSLPeerUnverifiedException if the peer is not verified.
     */
    public Certificate[] getPeerCertificates() throws SSLPeerUnverifiedException
    { return null; }

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
     *		the original JSSE
     *		{@link javax.security.cert.X509Certificate} format).
     * @exception SSLPeerUnverifiedException if the peer is not verified.
     */
    public javax.security.cert.X509Certificate[] getPeerCertificateChain()
        throws SSLPeerUnverifiedException
    { return null; }

    /** 
     * Returns the socket which is the source of this event.
     * (This is a convenience function, to let applications
     * write code without type casts.)
     *
     * @return the socket on which the connection was made.
     */
    public SSLSocket getSocket() { return null; }
}
