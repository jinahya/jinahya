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
import java.io.File;
import java.io.OutputStream;
import java.util.Hashtable;

/** 
 * The abstract class <code>URLStreamHandler</code> is the common
 * superclass for all stream protocol handlers. A stream protocol
 * handler knows how to make a connection for a particular protocol
 * type, such as <code>http</code>.
 * <p>
 * In most cases, an instance of a <code>URLStreamHandler</code>
 * subclass is not created directly by an application. Rather, the
 * first time a protocol name is encountered when constructing a
 * <code>URL</code>, the appropriate stream protocol handler is
 * automatically loaded.
 *
 * @author  James Gosling
 * @version 1.48 10/27/00
 * @see     java.net.URL#URL(java.lang.String, java.lang.String, int, java.lang.String)
 * @since   JDK1.0
 */
public abstract class URLStreamHandler
{

    public URLStreamHandler() { }

    /** 
     * Opens a connection to the object referenced by the
     * <code>URL</code> argument.
     * This method should be overridden by a subclass.
     *
     * <p>If for the handler's protocol (such as HTTP or JAR), there
     * exists a public, specialized URLConnection subclass belonging
     * to one of the following packages or one of their subpackages:
     * java.lang, java.io, java.util, java.net, the connection
     * returned will be of that subclass. For example, for HTTP an
     * HttpURLConnection will be returned, and for JAR a
     * JarURLConnection will be returned.
     * NOTE: <B>java.net.HttpURLConnection</B> is found in J2ME CDC profiles 
     * such as J2ME Foundation Profile.
     *
     * @param      u   the URL that this connects to.
     * @return     a <code>URLConnection</code> object for the <code>URL</code>.
     * @exception  IOException  if an I/O error occurs while opening the
     *               connection.
     */
    protected abstract URLConnection openConnection(URL u) throws IOException;

    /** 
     * Parses the string representation of a <code>URL</code> into a
     * <code>URL</code> object.
     * <p>
     * If there is any inherited context, then it has already been
     * copied into the <code>URL</code> argument.
     * <p>
     * The <code>parseURL</code> method of <code>URLStreamHandler</code>
     * parses the string representation as if it were an
     * <code>http</code> specification. Most URL protocol families have a
     * similar parsing. A stream protocol handler for a protocol that has
     * a different syntax must override this routine.
     *
     * @param   u       the <code>URL</code> to receive the result of parsing
     *                  the spec.
     * @param   spec    the <code>String</code> representing the URL that
     *                  must be parsed.
     * @param   start   the character index at which to begin parsing. This is
     *                  just past the '<code>:</code>' (if there is one) that
     *                  specifies the determination of the protocol name.
     * @param   limit   the character position to stop parsing at. This is the
     *                  end of the string or the position of the
     *                  "<code>#</code>" character, if present. All information
     *                  after the sharp sign indicates an anchor.
     */
    protected void parseURL(URL u, String spec, int start, int limit) { }

    /** 
     * Returns the default port for a URL parsed by this handler. This method
     * is meant to be overidden by handlers with default port numbers.
     * @return the default port for a <code>URL</code> parsed by this handler.
     */
    protected int getDefaultPort() {
        return 0;
    }

    /** 
     * Provides the default equals calculation. May be overidden by handlers
     * for other protocols that have different requirements for equals().
     * This method requires that none of its arguments is null. This is 
     * guaranteed by the fact that it is only called by java.net.URL class.
     * @param u1 a URL object
     * @param u2 a URL object
     * @return <tt>true</tt> if the two urls are 
     * considered equal, ie. they refer to the same 
     * fragment in the same file.
     */
    protected boolean equals(URL u1, URL u2) {
        return false;
    }

    /** 
     * Provides the default hash calculation. May be overidden by handlers for
     * other protocols that have different requirements for hashCode
     * calculation.
     * @param u a URL object
     * @return an <tt>int</tt> suitable for hash table indexing
     */
    protected int hashCode(URL u) {
        return 0;
    }

    /** 
     * Compare two urls to see whether they refer to the same file,
     * i.e., having the same protocol, host, port, and path.
     * This method requires that none of its arguments is null. This is 
     * guaranteed by the fact that it is only called indirectly
     * by java.net.URL class.
     * @param u1 a URL object
     * @param u2 a URL object
     * @return true if u1 and u2 refer to the same file
     */
    protected boolean sameFile(URL u1, URL u2) {
        return false;
    }

    /** 
     * Get the IP address of our host. An empty host field or a DNS failure
     * will result in a null return.
     *
     * @param u a URL object
     * @return an <code>InetAddress</code> representing the host
     * IP address.
     */
    protected synchronized InetAddress getHostAddress(URL u) {
        return null;
    }

    /** 
     * Compares the host components of two URLs.
     * @param u1 the URL of the first host to compare 
     * @param u2 the URL of the second host to compare 
     * @return	<tt>true</tt> if and only if they 
     * are equal, <tt>false</tt> otherwise.
     */
    protected boolean hostsEqual(URL u1, URL u2) {
        return false;
    }

    /** 
     * Converts a <code>URL</code> of a specific protocol to a
     * <code>String</code>.
     *
     * @param   u   the URL.
     * @return  a string representation of the <code>URL</code> argument.
     */
    protected String toExternalForm(URL u) {
        return null;
    }

    /** 
     * Sets the fields of the <code>URL</code> argument to the indicated values.
     * Only classes derived from URLStreamHandler are supposed to be able
     * to call the set method on a URL.
     *
     * @param   u         the URL to modify.
     * @param   protocol  the protocol name.
     * @param   host      the remote host value for the URL.
     * @param   port      the port on the remote machine.
     * @param   authority the authority part for the URL.
     * @param   userInfo the userInfo part of the URL.
     * @param   path      the path component of the URL. 
     * @param   query     the query part for the URL.
     * @param   ref       the reference.
     * @exception	SecurityException	if the protocol handler of the URL is 
     *					different from this one
     * @see     java.net.URL#set(java.lang.String, java.lang.String, int, java.lang.String, java.lang.String)
     */
    protected void setURL(URL u, String protocol, String host, int port, String
        authority, String userInfo, String path, String query, String ref)
    { }
}
