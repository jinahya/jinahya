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
 * @(#)SSLSessionContext.java	1.9 05/03/12
 */
  
/*
 * NOTE:
 * Because of various external restrictions (i.e. US export
 * regulations, etc.), the actual source code can not be provided
 * at this time. This file represents the skeleton of the source
 * file, so that javadocs of the API can be created.
 */

package javax.net.ssl;

import java.util.Enumeration;

/** 
 * A <code>SSLSessionContext</code> represents a set of
 * <code>SSLSession</code>s associated with a single entity. For example,
 * it could be associated with a server or client who participates in many
 * sessions concurrently.
 * <p>
 * Not all environments will contain session contexts.
 * <p>
 * There are <code>SSLSessionContext</code> parameters that affect how
 * sessions are stored:
 * <UL>
 *      <LI>Sessions can be set to expire after a specified
 *      time limit.
 *      <LI>The number of sessions that can be stored in context
 *      can be limited.
 * </UL>
 * A session can be retrieved based on its session id, and all session id's
 * in a <code>SSLSessionContext</code> can be listed.
 *
 * @see SSLSession
 *
 * @since 1.4
 * @author Nathan Abramson
 * @author David Brownell
 * @version 1.12
 */
public interface SSLSessionContext
{

    /** 
     * Returns the <code>SSLSession</code> bound to the specified session id.
     *
     * @param sessionId the Session identifier
     * @return the <code>SSLSession</code> or null if
     * the specified session id does not refer to a valid SSLSession.
     */
    public SSLSession getSession(byte[] sessionId);

    /** 
     * Returns an Enumeration of all session id's grouped under this
     * <code>SSLSessionContext</code>.
     *
     * @return an enumeration of all the Session id's
     */
    public Enumeration getIds();

    /** 
     * Sets the timeout limit for <code>SSLSession</code> objects grouped
     * under this <code>SSLSessionContext</code>.
     * <p>
     * If the timeout limit is set to 't' seconds, a session exceeds the
     * timeout limit 't' seconds after its creation time.
     * When the timeout limit is exceeded for a session, the
     * <code>SSLSession</code> object is invalidated and future connections
     * cannot resume or rejoin the session.
     * A check for sessions exceeding the timeout is made immediately whenever
     * the timeout limit is changed for this <code>SSLSessionContext</code>.
     *
     * @param seconds the new session timeout limit in seconds; zero means
     *		there is no limit.
     *
     * @exception IllegalArgumentException if the timeout specified is < 0.
     * @see #getSessionTimeout
     */
    public void setSessionTimeout(int seconds) throws IllegalArgumentException;

    /** 
     * Returns the timeout limit of <code>SSLSession</code> objects grouped
     * under this <code>SSLSessionContext</code>.
     * <p>
     * If the timeout limit is set to 't' seconds, a session exceeds the
     * timeout limit 't' seconds after its creation time.
     * When the timeout limit is exceeded for a session, the
     * <code>SSLSession</code> object is invalidated and future connections
     * cannot resume or rejoin the session.
     * A check for sessions exceeding the timeout limit is made immediately
     * whenever the timeout limit is changed for this
     * <code>SSLSessionContext</code>.
     *
     * @return the session timeout limit in seconds; zero means there is no
     * limit.
     * @see #setSessionTimeout
     */
    public int getSessionTimeout();

    /** 
     * Sets the size of the cache used for storing
     * <code>SSLSession</code> objects grouped under this
     * <code>SSLSessionContext</code>.
     *
     * @param size the new session cache size limit; zero means there is no
     * limit.
     * @exception IllegalArgumentException if the specified size is < 0.
     * @see #getSessionCacheSize
     */
    public void setSessionCacheSize(int size) throws IllegalArgumentException;

    /** 
     * Returns the size of the cache used for storing
     * <code>SSLSession</code> objects grouped under this
     * <code>SSLSessionContext</code>.
     *
     * @return size of the session cache; zero means there is no size limit.
     * @see #setSessionCacheSize
     */
    public int getSessionCacheSize();
}
