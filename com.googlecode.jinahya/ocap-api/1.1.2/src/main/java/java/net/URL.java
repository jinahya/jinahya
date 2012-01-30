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
import java.util.Hashtable;
import java.util.StringTokenizer;

/** 
 * Class <code>URL</code> represents a Uniform Resource
 * Locator, a pointer to a "resource" on the World
 * Wide Web. A resource can be something as simple as a file or a
 * directory, or it can be a reference to a more complicated object,
 * such as a query to a database or to a search engine. More
 * information on the types of URLs and their formats can be found at:
 * <blockquote>
 *     <a href="http://archive.ncsa.uiuc.edu/SDG/Software/Mosaic/Demo/url-primer.html">
 *    <i>http://archive.ncsa.uiuc.edu/SDG/Software/Mosaic/Demo/url-primer.html</i></a>
 * </blockquote>
 * <p>
 * In general, a URL can be broken into several parts. The previous
 * example of a URL indicates that the protocol to use is
 * <code>http</code> (HyperText Transfer Protocol) and that the
 * information resides on a host machine named
 * <code>www.ncsa.uiuc.edu</code>. The information on that host
 * machine is named <code>/SDG/Software/Mosaic/Demo/url-primer.html</code>. The exact
 * meaning of this name on the host machine is both protocol
 * dependent and host dependent. The information normally resides in
 * a file, but it could be generated on the fly. This component of
 * the URL is called the <i>path</i> component.
 * <p>
 * A URL can optionally specify a "port", which is the
 * port number to which the TCP connection is made on the remote host
 * machine. If the port is not specified, the default port for
 * the protocol is used instead. For example, the default port for
 * <code>http</code> is <code>80</code>. An alternative port could be
 * specified as:
 * <blockquote><pre>
 *     http://archive.ncsa.uiuc.edu:80/SDG/Software/Mosaic/Demo/url-primer.html
 * </pre></blockquote>
 * <p>
 * The syntax of <code>URL</code> is defined by  <a
 * href="http://www.ietf.org/rfc/rfc2396.txt""><i>RFC&nbsp;2396: Uniform
 * Resource Identifiers (URI): Generic Syntax</i></a>, amended by <a
 * href="http://www.ietf.org/rfc/rfc2732.txt"><i>RFC&nbsp;2732: Format for
 * Literal IPv6 Addresses in URLs</i></a>.
 * <p>
 * A URL may have appended to it a "fragment", also known
 * as a "ref" or a "reference". The fragment is indicated by the sharp
 * sign character "#" followed by more characters. For example,
 * <blockquote><pre>
 *     http://java.sun.com/index.html#chapter1
 * </pre></blockquote>
 * <p>
 * This fragment is not technically part of the URL. Rather, it
 * indicates that after the specified resource is retrieved, the
 * application is specifically interested in that part of the
 * document that has the tag <code>chapter1</code> attached to it. The
 * meaning of a tag is resource specific.
 * <p>
 * An application can also specify a "relative URL",
 * which contains only enough information to reach the resource
 * relative to another URL. Relative URLs are frequently used within
 * HTML pages. For example, if the contents of the URL:
 * <blockquote><pre>
 *     http://java.sun.com/index.html
 * </pre></blockquote>
 * contained within it the relative URL:
 * <blockquote><pre>
 *     FAQ.html
 * </pre></blockquote>
 * it would be a shorthand for:
 * <blockquote><pre>
 *     http://java.sun.com/FAQ.html
 * </pre></blockquote>
 * <p>
 * The relative URL need not specify all the components of a URL. If
 * the protocol, host name, or port number is missing, the value is
 * inherited from the fully specified URL. The file component must be
 * specified. The optional fragment is not inherited.
 *
 * @author  James Gosling
 * @version 1.106, 03/12/05
 * @since JDK1.0 
 */
public final class URL implements java.io.Serializable
{
    static final long serialVersionUID = -7627629688361524110L;

    /** 
     * The protocol to use (ftp, http, nntp, ... etc.) .
     * @serial
     */
    private String protocol;

    /** 
     * The host name to connect to.
     * @serial
     */
    private String host;

    /** 
     * The protocol port to connect to.
     * @serial
     */
    private int port;

    /** 
     * The specified file name on that host. <code>file</code> is
     * defined as <code>path[?query]</code>
     * @serial
     */
    private String file;

    /** 
     * The authority part of this URL.
     * @serial
     */
    private String authority;

    /** 
     * # reference.
     * @serial
     */
    private String ref;

    private int hashCode;

    /** 
     * Creates a <code>URL</code> object from the specified
     * <code>protocol</code>, <code>host</code>, <code>port</code>
     * number, and <code>file</code>.<p>
     *
     * <code>host</code> can be expressed as a host name or a literal
     * IP address. If IPv6 literal address is used, it should be
     * enclosed in square brackets (<tt>'['</tt> and <tt>']'</tt>), as
     * specified by <a
     * href="http://www.ietf.org/rfc/rfc2732.txt">RFC&nbsp;2732</a>;
     * However, the literal IPv6 address format defined in <a
     * href="http://www.ietf.org/rfc/rfc2373.txt"><i>RFC&nbsp;2373: IP
     * Version 6 Addressing Architecture</i></a> is also accepted.<p>
     *
     * Specifying a <code>port</code> number of <code>-1</code>
     * indicates that the URL should use the default port for the
     * protocol.<p>
     *
     * If this is the first URL object being created with the specified
     * protocol, a <i>stream protocol handler</i> object, an instance of
     * class <code>URLStreamHandler</code>, is created for that protocol:
     * <ol>
     * <li>If the application has previously set up an instance of
     *     <code>URLStreamHandlerFactory</code> as the stream handler factory,
     *     then the <code>createURLStreamHandler</code> method of that instance
     *     is called with the protocol string as an argument to create the
     *     stream protocol handler.
     * <li>If no <code>URLStreamHandlerFactory</code> has yet been set up,
     *     or if the factory's <code>createURLStreamHandler</code> method
     *     returns <code>null</code>, then the constructor finds the
     *     value of the system property:
     *     <blockquote><pre>
     *         java.protocol.handler.pkgs
     *     </pre></blockquote>
     *     If the value of that system property is not <code>null</code>,
     *     it is interpreted as a list of packages separated by a vertical
     *     slash character '<code>|</code>'. The constructor tries to load
     *     the class named:
     *     <blockquote><pre>
     *         &lt;<i>package</i>&gt;.&lt;<i>protocol</i>&gt;.Handler
     *     </pre></blockquote>
     *     where &lt;<i>package</i>&gt; is replaced by the name of the package
     *     and &lt;<i>protocol</i>&gt; is replaced by the name of the protocol.
     *     If this class does not exist, or if the class exists but it is not
     *     a subclass of <code>URLStreamHandler</code>, then the next package
     *     in the list is tried.
     * <li>If the previous step fails to find a protocol handler, then the
     *     constructor tries to load from a system default package.
     *     <blockquote><pre>
     *         &lt;<i>system default package</i>&gt;.&lt;<i>protocol</i>&gt;.Handler
     *     </pre></blockquote>
     *     If this class does not exist, or if the class exists but it is not a
     *     subclass of <code>URLStreamHandler</code>, then a
     *     <code>MalformedURLException</code> is thrown.
     * </ol>
     *
     * <p>No validation of the inputs is performed by this constructor.
     *
     * @param      protocol   the name of the protocol to use.
     * @param      host       the name of the host.
     * @param      port       the port number on the host.
     * @param      file       the file on the host
     * @exception  MalformedURLException  if an unknown protocol is specified.
     * @see        java.lang.System#getProperty(java.lang.String)
     * @see        java.net.URL#setURLStreamHandlerFactory(
     *			java.net.URLStreamHandlerFactory)
     * @see        java.net.URLStreamHandler
     * @see        java.net.URLStreamHandlerFactory#createURLStreamHandler(
     *                  java.lang.String)
     */
    public URL(String protocol, String host, int port, String file)
        throws MalformedURLException
    { }

    /** 
     * Creates a URL from the specified <code>protocol</code>
     * name, <code>host</code> name, and <code>file</code> name. The
     * default port for the specified protocol is used.
     * <p>
     * This method is equivalent to calling the four-argument
     * constructor with the arguments being <code>protocol</code>,
     * <code>host</code>, <code>-1</code>, and <code>file</code>.
     *
     * No validation of the inputs is performed by this constructor.
     *
     * @param      protocol   the name of the protocol to use.
     * @param      host       the name of the host.
     * @param      file       the file on the host.
     * @exception  MalformedURLException  if an unknown protocol is specified.
     * @see        java.net.URL#URL(java.lang.String, java.lang.String,
     *			int, java.lang.String)
     */
    public URL(String protocol, String host, String file)
        throws MalformedURLException
    { }

    /** 
     * Creates a <code>URL</code> object from the specified
     * <code>protocol</code>, <code>host</code>, <code>port</code>
     * number, <code>file</code>, and <code>handler</code>. Specifying
     * a <code>port</code> number of <code>-1</code> indicates that
     * the URL should use the default port for the protocol. Specifying
     * a <code>handler</code> of <code>null</code> indicates that the URL
     * should use a default stream handler for the protocol, as outlined
     * for:
     *     java.net.URL#URL(java.lang.String, java.lang.String, int,
     *                      java.lang.String)
     *
     * <p>If the handler is not null and there is a security manager,
     * the security manager's <code>checkPermission</code>
     * method is called with a
     * <code>NetPermission("specifyStreamHandler")</code> permission.
     * This may result in a SecurityException.
     *
     * No validation of the inputs is performed by this constructor.
     *
     * @param      protocol   the name of the protocol to use.
     * @param      host       the name of the host.
     * @param      port       the port number on the host.
     * @param      file       the file on the host
     * @param	   handler    the stream handler for the URL.
     * @exception  MalformedURLException  if an unknown protocol is specified.
     * @exception  SecurityException
     *        if a security manager exists and its
     *        <code>checkPermission</code> method doesn't allow
     *        specifying a stream handler explicitly.
     * @see        java.lang.System#getProperty(java.lang.String)
     * @see        java.net.URL#setURLStreamHandlerFactory(
     *			java.net.URLStreamHandlerFactory)
     * @see        java.net.URLStreamHandler
     * @see        java.net.URLStreamHandlerFactory#createURLStreamHandler(
     *			java.lang.String)
     * @see        SecurityManager#checkPermission
     * @see        java.net.NetPermission
     */
    public URL(String protocol, String host, int port, String file,
        URLStreamHandler handler) throws MalformedURLException
    { }

    /** 
     * Creates a <code>URL</code> object from the <code>String</code>
     * representation.
     * <p>
     * This constructor is equivalent to a call to the two-argument
     * constructor with a <code>null</code> first argument.
     *
     * @param      spec   the <code>String</code> to parse as a URL.
     * @exception  MalformedURLException  If the string specifies an
     *               unknown protocol.
     * @see        java.net.URL#URL(java.net.URL, java.lang.String)
     */
    public URL(String spec) throws MalformedURLException { }

    /** 
     * Creates a URL by parsing the given spec within a specified context.
     *
     * The new URL is created from the given context URL and the spec
     * argument as described in
     * RFC2396 &quot;Uniform Resource Identifiers : Generic * Syntax&quot; :
     * <blockquote><pre>
     *          &lt;scheme&gt;://&lt;authority&gt;&lt;path&gt;?&lt;query&gt;#&lt;fragment&gt;
     * </pre></blockquote>
     * The reference is parsed into the scheme, authority, path, query and
     * fragment parts. If the path component is empty and the scheme,
     * authority, and query components are undefined, then the new URL is a
     * reference to the current document. Otherwise, the fragment and query
     * parts present in the spec are used in the new URL.
     * <p>
     * If the scheme component is defined in the given spec and does not match
     * the scheme of the context, then the new URL is created as an absolute
     * URL based on the spec alone. Otherwise the scheme component is inherited
     * from the context URL.
     * <p>
     * If the authority component is present in the spec then the spec is
     * treated as absolute and the spec authority and path will replace the
     * context authority and path. If the authority component is absent in the
     * spec then the authority of the new URL will be inherited from the
     * context.
     * <p>
     * If the spec's path component begins with a slash character
     * &quot;/&quot; then the
     * path is treated as absolute and the spec path replaces the context path.
     * <p>
     * Otherwise, the path is treated as a relative path and is appended to the
     * context path, as described in RFC2396. Also, in this case, 
     * the path is canonicalized through the removal of directory 
     * changes made by occurences of &quot;..&quot; and &quot;.&quot;.
     * <p>
     * For a more detailed description of URL parsing, refer to RFC2396.
     *
     * @param      context   the context in which to parse the specification.
     * @param      spec      the <code>String</code> to parse as a URL.
     * @exception  MalformedURLException  if no protocol is specified, or an
     *               unknown protocol is found.
     * @see        java.net.URL#URL(java.lang.String, java.lang.String,
     *			int, java.lang.String)
     * @see        java.net.URLStreamHandler
     * @see        java.net.URLStreamHandler#parseURL(java.net.URL,
     *			java.lang.String, int, int)
     */
    public URL(URL context, String spec) throws MalformedURLException { }

    /** 
     * Creates a URL by parsing the given spec with the specified handler
     * within a specified context. If the handler is null, the parsing
     * occurs as with the two argument constructor.
     *
     * @param      context   the context in which to parse the specification.
     * @param      spec      the <code>String</code> to parse as a URL.
     * @param	   handler   the stream handler for the URL.
     * @exception  MalformedURLException  if no protocol is specified, or an
     *               unknown protocol is found.
     * @exception  SecurityException
     *        if a security manager exists and its
     *        <code>checkPermission</code> method doesn't allow
     *        specifying a stream handler.
     * @see        java.net.URL#URL(java.lang.String, java.lang.String,
     *			int, java.lang.String)
     * @see        java.net.URLStreamHandler
     * @see        java.net.URLStreamHandler#parseURL(java.net.URL,
     *			java.lang.String, int, int)
     */
    public URL(URL context, String spec, URLStreamHandler handler)
        throws MalformedURLException
    { }

    /** 
     * Sets the fields of the URL. This is not a public method so that
     * only URLStreamHandlers can modify URL fields. URLs are
     * otherwise constant.
     *
     * @param protocol the name of the protocol to use
     * @param host the name of the host
     *       @param port the port number on the host
     * @param file the file on the host
     * @param ref the internal reference in the URL
     */
    protected void set(String protocol, String host, int port, String file,
        String ref)
    { }

    /** 
     * Sets the specified 8 fields of the URL. This is not a public method so
     * that only URLStreamHandlers can modify URL fields. URLs are otherwise
     * constant.
     *
     * @param protocol the name of the protocol to use
     * @param host the name of the host
     * @param port the port number on the host
     * @param authority the authority part for the url
     * @param userInfo the username and password
     * @param path the file on the host
     * @param ref the internal reference in the URL
     * @param query the query part of this URL
     * @since 1.3
     */
    protected void set(String protocol, String host, int port, String authority,
        String userInfo, String path, String query, String ref)
    { }

    /** 
     * Gets the query part of this <code>URL</code>.
     *
     * @return  the query part of this <code>URL</code>, 
     * or <CODE>null</CODE> if one does not exist
     * @since 1.3
     */
    public String getQuery() {
        return null;
    }

    /** 
     * Gets the path part of this <code>URL</code>.
     *
     * @return  the path part of this <code>URL</code>, or an
     * empty string if one does not exist
     * @since 1.3
     */
    public String getPath() {
        return null;
    }

    /** 
     * Gets the userInfo part of this <code>URL</code>.
     *
     * @return  the userInfo part of this <code>URL</code>, or 
     * <CODE>null</CODE> if one does not exist
     */
    public String getUserInfo() {
        return null;
    }

    /** 
     * Gets the authority part of this <code>URL</code>.
     *
     * @return  the authority part of this <code>URL</code>
     * @since 1.3
     */
    public String getAuthority() {
        return null;
    }

    /** 
     * Gets the port number of this <code>URL</code>.
     *
     * @return  the port number, or -1 if the port is not set
     */
    public int getPort() {
        return 0;
    }

    /** 
     * Gets the default port number of the protocol associated
     * with this <code>URL</code>. If the URL scheme or the URLStreamHandler
     * for the URL do not define a default port number,
     * then -1 is returned.
     *
     * @return  the port number
     */
    public int getDefaultPort() {
        return 0;
    }

    /** 
     * Gets the protocol name of this <code>URL</code>.
     *
     * @return  the protocol of this <code>URL</code>.
     */
    public String getProtocol() {
        return null;
    }

    /** 
     * Gets the host name of this <code>URL</code>, if applicable.
     * The format of the host conforms to RFC 2732, i.e. for a
     * literal IPv6 address, this method will return the IPv6 address
     * enclosed in square brackets (<tt>'['</tt> and <tt>']'</tt>).
     *
     * @return  the host name of this <code>URL</code>.
     */
    public String getHost() {
        return null;
    }

    /** 
     * Gets the file name of this <code>URL</code>.
     * The returned file portion will be
     * the same as <CODE>getPath()</CODE>, plus the concatenation of
     * the value of <CODE>getQuery()</CODE>, if any. If there is 
     * no query portion, this method and <CODE>getPath()</CODE> will
     * return identical results.
     *
     * @return  the file name of this <code>URL</code>,
     * or an empty string if one does not exist
     */
    public String getFile() {
        return null;
    }

    /** 
     * Gets the anchor (also known as the "reference") of this
     * <code>URL</code>.
     *
     * @return  the anchor (also known as the "reference") of this
     *          <code>URL</code>, or <CODE>null</CODE> if one does not exist
     */
    public String getRef() {
        return null;
    }

    /** 
     * Compares this URL for equality with another object.<p>
     *
     * If the given object is not a URL then this method immediately returns
     * <code>false</code>.<p>
     *
     * Two URL objects are equal if they have the same protocol, reference
     * equivalent hosts, have the same port number on the host, and the same
     * file and fragment of the file.<p>
     *
     * Two hosts are considered equivalent if both host names can be resolved
     * into the same IP addresses; else if either host name can't be
     * resolved, the host names must be equal without regard to case; or both
     * host names equal to null.<p>
     *
     * Since hosts comparison requires name resolution, this operation is a
     * blocking operation. <p>
     *
     * Note: The defined behavior for <code>equals</code> is known to
     * be inconsistent with virtual hosting in HTTP.
     *
     * @param   obj   the URL to compare against.
     * @return  <code>true</code> if the objects are the same;
     *          <code>false</code> otherwise.
     */
    public boolean equals(Object obj) {
        return false;
    }

    /** 
     * Creates an integer suitable for hash table indexing.<p>
     * 
     * The hash code is based upon all the URL components relevant for URL
     * comparison. As such, this operation is a blocking operation.<p>
     *
     * @return  a hash code for this <code>URL</code>.
     */
    public synchronized int hashCode() {
        return 0;
    }

    /** 
     * Compares two URLs, excluding the fragment component.<p>
     *
     * Returns <code>true</code> if this <code>URL</code> and the
     * <code>other</code> argument are equal without taking the
     * fragment component into consideration.
     *
     * @param   other   the <code>URL</code> to compare against.
     * @return  <code>true</code> if they reference the same remote object;
     *          <code>false</code> otherwise.
     */
    public boolean sameFile(URL other) {
        return false;
    }

    /** 
     * Constructs a string representation of this <code>URL</code>. The
     * string is created by calling the <code>toExternalForm</code>
     * method of the stream protocol handler for this object.
     *
     * @return  a string representation of this object.
     * @see     java.net.URL#URL(java.lang.String, java.lang.String, int,
     *			java.lang.String)
     * @see     java.net.URLStreamHandler#toExternalForm(java.net.URL)
     */
    public String toString() {
        return null;
    }

    /** 
     * Constructs a string representation of this <code>URL</code>. The
     * string is created by calling the <code>toExternalForm</code>
     * method of the stream protocol handler for this object.
     *
     * @return  a string representation of this object.
     * @see     java.net.URL#URL(java.lang.String, java.lang.String,
     *			int, java.lang.String)
     * @see     java.net.URLStreamHandler#toExternalForm(java.net.URL)
     */
    public String toExternalForm() {
        return null;
    }

    /** 
     * Returns a <code>URLConnection</code> object that represents a
     * connection to the remote object referred to by the <code>URL</code>.
     *
     * <p>A new connection is opened every time by calling the
     * <code>openConnection</code> method of the protocol handler for
     * this URL.
     *
     * <p>If for the URL's protocol (such as HTTP or JAR), there
     * exists a public, specialized URLConnection subclass belonging
     * to one of the following packages or one of their subpackages:
     * java.lang, java.io, java.util, java.net, the connection
     * returned will be of that subclass. For example, for HTTP an
     * HttpURLConnection will be returned, and for JAR a
     * JarURLConnection will be returned.
     * NOTE: <B>java.net.HttpURLConnection</B> is found in J2ME CDC profiles 
     * such as J2ME Foundation Profile.
     * Subclasses of these should
     *
     * @return     a <code>URLConnection</code> to the URL.
     * @exception  IOException  if an I/O exception occurs.
     * @see        java.net.URL#URL(java.lang.String, java.lang.String,
     *             int, java.lang.String)
     * @see        java.net.URLConnection
     * @see java.net.URLStreamHandler#openConnection(java.net.URL)
     */
    public URLConnection openConnection() throws IOException {
        return null;
    }

    /** 
     * Opens a connection to this <code>URL</code> and returns an
     * <code>InputStream</code> for reading from that connection. This
     * method is a shorthand for:
     * <blockquote><pre>
     *     openConnection().getInputStream()
     * </pre></blockquote>
     *
     * @return     an input stream for reading from the URL connection.
     * @exception  IOException  if an I/O exception occurs.
     * @see        java.net.URL#openConnection()
     * @see        java.net.URLConnection#getInputStream()
     */
    public final InputStream openStream() throws IOException {
        return null;
    }

    /** 
     * Gets the contents of this URL. This method is a shorthand for:
     * <blockquote><pre>
     *     openConnection().getContent()
     * </pre></blockquote>
     *
     * @return     the contents of this URL.
     * @exception  IOException  if an I/O exception occurs.
     * @see        java.net.URLConnection#getContent()
     */
    public final Object getContent() throws IOException {
        return null;
    }

    /** 
     * Gets the contents of this URL. This method is a shorthand for:
     * <blockquote><pre>
     *     openConnection().getContent(Class[])
     * </pre></blockquote>
     *
     * @param classes an array of Java types
     * @return     the content object of this URL that is the first match of
     *               the types specified in the classes array.
     *               null if none of the requested types are supported.
     * @exception  IOException  if an I/O exception occurs.
     * @see        java.net.URLConnection#getContent(Class[])
     * @since 1.3
     */
    public final Object getContent(Class[] classes) throws IOException {
        return null;
    }

    /** 
     * Sets an application's <code>URLStreamHandlerFactory</code>.
     * This method can be called at most once in a given Java Virtual
     * Machine.
     *
     *<p> The <code>URLStreamHandlerFactory</code> instance is used to
     *construct a stream protocol handler from a protocol name.
     *
     * <p> If there is a security manager, this method first calls
     * the security manager's <code>checkSetFactory</code> method
     * to ensure the operation is allowed.
     * This could result in a SecurityException.
     *
     * @param      fac   the desired factory.
     * @exception  Error  if the application has already set a factory.
     * @exception  SecurityException  if a security manager exists and its
     *             <code>checkSetFactory</code> method doesn't allow
     *		   the operation.
     * @see        java.net.URL#URL(java.lang.String, java.lang.String,
     *             int, java.lang.String)
     * @see        java.net.URLStreamHandlerFactory
     * @see        SecurityManager#checkSetFactory
     */
    public static void setURLStreamHandlerFactory(URLStreamHandlerFactory fac)
    { }

    /** 
     * readObject is called to restore the state of the URL from the
     * stream.  It reads the components of the URL and finds the local
     * stream handler.
     */
    private synchronized void readObject(java.io.ObjectInputStream s)
        throws IOException, ClassNotFoundException
    { }

    /** 
     * WriteObject is called to save the state of the URL to an
     * ObjectOutputStream. The handler is not saved since it is
     * specific to this system.
     *
     * @serialData the default write object value. When read back in,
     * the reader must ensure that calling getURLStreamHandler with
     * the protocol variable returns a valid URLStreamHandler and
     * throw an IOException if it does not.
     */
    private synchronized void writeObject(java.io.ObjectOutputStream s)
        throws IOException
    { }
}
