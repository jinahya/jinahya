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
import java.text.DateFormat;
import java.util.StringTokenizer;
import java.util.Collections;
import java.util.Map;
import java.security.Permission;
import java.security.AccessController;

/** 
 * The abstract class <code>URLConnection</code> is the superclass
 * of all classes that represent a communications link between the
 * application and a URL. Instances of this class can be used both to
 * read from and to write to the resource referenced by the URL. In
 * general, creating a connection to a URL is a multistep process:
 * <p>
 * <center><table border=2 summary="Describes the process of creating a connection to a URL: openConnection() and connect() over time.">
 * <tr><th><code>openConnection()</code></th>
 *     <th><code>connect()</code></th></tr>
 * <tr><td>Manipulate parameters that affect the connection to the remote
 *         resource.</td>
 *     <td>Interact with the resource; query header fields and
 *         contents.</td></tr>
 * </table>
 * ----------------------------&gt;
 * <br>time</center>
 *
 * <ol>
 * <li>The connection object is created by invoking the
 *     <code>openConnection</code> method on a URL.
 * <li>The setup parameters and general request properties are manipulated.
 * <li>The actual connection to the remote object is made, using the
 *    <code>connect</code> method.
 * <li>The remote object becomes available. The header fields and the contents
 *     of the remote object can be accessed.
 * </ol>
 * <p>
 * The setup parameters are modified using the following methods:
 * <ul>
 *   <li><code>setAllowUserInteraction</code>
 *   <li><code>setDoInput</code>
 *   <li><code>setDoOutput</code>
 *   <li><code>setIfModifiedSince</code>
 *   <li><code>setUseCaches</code>
 * </ul>
 * <p>
 * and the general request properties are modified using the method:
 * <ul>
 *   <li><code>setRequestProperty</code>
 * </ul>
 * <p>
 * Default values for the <code>AllowUserInteraction</code> and
 * <code>UseCaches</code> parameters can be set using the methods
 * <code>setDefaultAllowUserInteraction</code> and
 * <code>setDefaultUseCaches</code>.
 * <p>
 * Each of the above <code>set</code> methods has a corresponding
 * <code>get</code> method to retrieve the value of the parameter or
 * general request property. The specific parameters and general
 * request properties that are applicable are protocol specific. 
 * <p>
 * The following methods are used to access the header fields and 
 * the contents after the connection is made to the remote object:
 * <ul>
 *   <li><code>getContent</code>
 *   <li><code>getHeaderField</code>
 *   <li><code>getInputStream</code>
 *   <li><code>getOutputStream</code>
 * </ul>
 * <p>
 * Certain header fields are accessed frequently. The methods:
 * <ul>
 *   <li><code>getContentEncoding</code>
 *   <li><code>getContentLength</code>
 *   <li><code>getContentType</code>
 *   <li><code>getDate</code>
 *   <li><code>getExpiration</code>
 *   <li><code>getLastModifed</code>
 * </ul>
 * <p>
 * provide convenient access to these fields. The 
 * <code>getContentType</code> method is used by the 
 * <code>getContent</code> method to determine the type of the remote 
 * object; subclasses may find it convenient to override the 
 * <code>getContentType</code> method. 
 * <p>
 * In the common case, all of the pre-connection parameters and 
 * general request properties can be ignored: the pre-connection 
 * parameters and request properties default to sensible values. For
 * most clients of this interface, there are only two interesting
 * methods: <code>getInputStream</code> and <code>getContent</code>,
 * which are mirrored in the <code>URL</code> class by convenience methods.
 * <p>
 * More information on the request properties and header fields of
 * an <code>http</code> connection can be found at:
 * <blockquote><pre>
 * <a href="http://www.ietf.org/rfc/rfc2068.txt">http://www.ietf.org/rfc/rfc2068.txt</a>
 * </pre></blockquote>
 *
 * Note about <code>fileNameMap</code>: In versions prior to JDK 1.1.6, 
 * field <code>fileNameMap</code> of <code>URLConnection</code> was public.
 * In JDK 1.1.6 and later, <code>fileNameMap</code> is private; accessor 
 * and mutator methods {@link #getFileNameMap() getFileNameMap} and 
 * {@link #setFileNameMap(java.net.FileNameMap) setFileNameMap} are added
 * to access it.  This change is also described on the <a href=
 * "http://java.sun.com/products/jdk/1.2/compatibility.html#incompatibilities1.2">
 * Compatibility</a> page.
 *
 * Calling the <tt>close()</tt> methods on the <tt>InputStream</tt> or <tt>OutputStream</tt> of an 
 * <tt>URLConnection</tt> after a request may free network resources associated with this 
 * instance, unless particular protocol specifications specify different behaviours 
 * for it.
 *
 * @author  James Gosling
 * @version 1.75, 05/03/00
 * @see     java.net.URL#openConnection()
 * @see     java.net.URLConnection#connect()
 * @see     java.net.URLConnection#getContent()
 * @see     java.net.URLConnection#getContentEncoding()
 * @see     java.net.URLConnection#getContentLength()
 * @see     java.net.URLConnection#getContentType()
 * @see     java.net.URLConnection#getDate()
 * @see     java.net.URLConnection#getExpiration()
 * @see     java.net.URLConnection#getHeaderField(int)
 * @see     java.net.URLConnection#getHeaderField(java.lang.String)
 * @see     java.net.URLConnection#getInputStream()
 * @see     java.net.URLConnection#getLastModified()
 * @see     java.net.URLConnection#getOutputStream()
 * @see     java.net.URLConnection#setAllowUserInteraction(boolean)
 * @see     java.net.URLConnection#setDefaultUseCaches(boolean)
 * @see     java.net.URLConnection#setDoInput(boolean)
 * @see     java.net.URLConnection#setDoOutput(boolean)
 * @see     java.net.URLConnection#setIfModifiedSince(long)
 * @see     java.net.URLConnection#setRequestProperty(java.lang.String, java.lang.String)
 * @see     java.net.URLConnection#setUseCaches(boolean)
 * @since   JDK1.0
 */
public abstract class URLConnection
{
    /** 
     * The URL represents the remote object on the World Wide Web to 
     * which this connection is opened. 
     * <p>
     * The value of this field can be accessed by the 
     * <code>getURL</code> method. 
     * <p>
     * The default value of this variable is the value of the URL 
     * argument in the <code>URLConnection</code> constructor. 
     *
     * @see     java.net.URLConnection#getURL()
     * @see     java.net.URLConnection#url
     */
    protected URL url;

    /** 
     * This variable is set by the <code>setDoInput</code> method. Its 
     * value is returned by the <code>getDoInput</code> method. 
     * <p>
     * A URL connection can be used for input and/or output. Setting the 
     * <code>doInput</code> flag to <code>true</code> indicates that 
     * the application intends to read data from the URL connection. 
     * <p>
     * The default value of this field is <code>true</code>. 
     *
     * @see     java.net.URLConnection#getDoInput()
     * @see     java.net.URLConnection#setDoInput(boolean)
     */
    protected boolean doInput;

    /** 
     * This variable is set by the <code>setDoOutput</code> method. Its 
     * value is returned by the <code>getDoOutput</code> method. 
     * <p>
     * A URL connection can be used for input and/or output. Setting the 
     * <code>doOutput</code> flag to <code>true</code> indicates 
     * that the application intends to write data to the URL connection. 
     * <p>
     * The default value of this field is <code>false</code>. 
     *
     * @see     java.net.URLConnection#getDoOutput()
     * @see     java.net.URLConnection#setDoOutput(boolean)
     */
    protected boolean doOutput;

    /** 
     * If <code>true</code>, this <code>URL</code> is being examined in 
     * a context in which it makes sense to allow user interactions such 
     * as popping up an authentication dialog. If <code>false</code>, 
     * then no user interaction is allowed. 
     * <p>
     * The value of this field can be set by the 
     * <code>setAllowUserInteraction</code> method.
     * Its value is returned by the 
     * <code>getAllowUserInteraction</code> method.
     * Its default value is the value of the argument in the last invocation 
     * of the <code>setDefaultAllowUserInteraction</code> method. 
     *
     * @see     java.net.URLConnection#getAllowUserInteraction()
     * @see     java.net.URLConnection#setAllowUserInteraction(boolean)
     * @see     java.net.URLConnection#setDefaultAllowUserInteraction(boolean)
     */
    protected boolean allowUserInteraction;

    /** 
     * If <code>true</code>, the protocol is allowed to use caching 
     * whenever it can. If <code>false</code>, the protocol must always 
     * try to get a fresh copy of the object. 
     * <p>
     * This field is set by the <code>setUseCaches</code> method. Its 
     * value is returned by the <code>getUseCaches</code> method.
     * <p>
     * Its default value is the value given in the last invocation of the 
     * <code>setDefaultUseCaches</code> method. 
     *
     * @see     java.net.URLConnection#setUseCaches(boolean)
     * @see     java.net.URLConnection#getUseCaches()
     * @see     java.net.URLConnection#setDefaultUseCaches(boolean)
     */
    protected boolean useCaches;

    /** 
     * Some protocols support skipping the fetching of the object unless 
     * the object has been modified more recently than a certain time. 
     * <p>
     * A nonzero value gives a time as the number of milliseconds since 
     * January 1, 1970, GMT. The object is fetched only if it has been 
     * modified more recently than that time. 
     * <p>
     * This variable is set by the <code>setIfModifiedSince</code> 
     * method. Its value is returned by the 
     * <code>getIfModifiedSince</code> method.
     * <p>
     * The default value of this field is <code>0</code>, indicating 
     * that the fetching must always occur. 
     *
     * @see     java.net.URLConnection#getIfModifiedSince()
     * @see     java.net.URLConnection#setIfModifiedSince(long)
     */
    protected long ifModifiedSince;

    /** 
     * If <code>false</code>, this connection object has not created a 
     * communications link to the specified URL. If <code>true</code>, 
     * the communications link has been established. 
     */
    protected boolean connected;

    /** 
     * Constructs a URL connection to the specified URL. A connection to 
     * the object referenced by the URL is not created. 
     *
     * @param   url   the specified URL.
     */
    protected URLConnection(URL url) { }

    /** 
     * Loads filename map (a mimetable) from a data file. It will
     * first try to load the user-specific table, defined
     * by &quot;content.types.user.table&quot; property. If that fails,
     * it tries to load the default built-in table at 
     * lib/content-types.properties under java home.
     *
     * @return the FileNameMap
     * @since 1.2
     * @see #setFileNameMap(java.net.FileNameMap)
     */
    public static synchronized FileNameMap getFileNameMap() {
        return null;
    }

    /** 
     * Sets the FileNameMap.
     * <p>
     * If there is a security manager, this method first calls
     * the security manager's <code>checkSetFactory</code> method 
     * to ensure the operation is allowed. 
     * This could result in a SecurityException.
     *
     * @param map the FileNameMap to be set
     * @exception  SecurityException  if a security manager exists and its  
     *             <code>checkSetFactory</code> method doesn't allow the operation.
     * @see        SecurityManager#checkSetFactory
     * @see #getFileNameMap()
     * @since 1.2
     */
    public static void setFileNameMap(FileNameMap map) { }

    /** 
     * Opens a communications link to the resource referenced by this 
     * URL, if such a connection has not already been established. 
     * <p>
     * If the <code>connect</code> method is called when the connection 
     * has already been opened (indicated by the <code>connected</code> 
     * field having the value <code>true</code>), the call is ignored. 
     * <p>
     * URLConnection objects go through two phases: first they are
     * created, then they are connected.  After being created, and
     * before being connected, various options can be specified
     * (e.g., doInput and UseCaches).  After connecting, it is an
     * error to try to set them.  Operations that depend on being
     * connected, like getContentLength, will implicitly perform the
     * connection, if necessary.
     *
     * @exception  IOException  if an I/O error occurs while opening the
     *               connection.
     * @see java.net.URLConnection#connected 
     */
    public abstract void connect() throws IOException;

    /** 
     * Returns the value of this <code>URLConnection</code>'s <code>URL</code>
     * field.
     *
     * @return  the value of this <code>URLConnection</code>'s <code>URL</code>
     *          field.
     * @see     java.net.URLConnection#url
     */
    public URL getURL() {
        return null;
    }

    /** 
     * Returns the value of the <code>content-length</code> header field.
     *
     * @return  the content length of the resource that this connection's URL
     *          references, or <code>-1</code> if the content length is
     *          not known.
     */
    public int getContentLength() {
        return 0;
    }

    /** 
     * Returns the value of the <code>content-type</code> header field.
     *
     * @return  the content type of the resource that the URL references,
     *          or <code>null</code> if not known.
     * @see     java.net.URLConnection#getHeaderField(java.lang.String)
     */
    public String getContentType() {
        return null;
    }

    /** 
     * Returns the value of the <code>content-encoding</code> header field.
     *
     * @return  the content encoding of the resource that the URL references,
     *          or <code>null</code> if not known.
     * @see     java.net.URLConnection#getHeaderField(java.lang.String)
     */
    public String getContentEncoding() {
        return null;
    }

    /** 
     * Returns the value of the <code>expires</code> header field. 
     *
     * @return  the expiration date of the resource that this URL references,
     *          or 0 if not known. The value is the number of milliseconds since
     *          January 1, 1970 GMT.
     * @see     java.net.URLConnection#getHeaderField(java.lang.String)
     */
    public long getExpiration() {
        return -1;
    }

    /** 
     * Returns the value of the <code>date</code> header field. 
     *
     * @return  the sending date of the resource that the URL references,
     *          or <code>0</code> if not known. The value returned is the
     *          number of milliseconds since January 1, 1970 GMT.
     * @see     java.net.URLConnection#getHeaderField(java.lang.String)
     */
    public long getDate() {
        return -1;
    }

    /** 
     * Returns the value of the <code>last-modified</code> header field. 
     * The result is the number of milliseconds since January 1, 1970 GMT.
     *
     * @return  the date the resource referenced by this
     *          <code>URLConnection</code> was last modified, or 0 if not known.
     * @see     java.net.URLConnection#getHeaderField(java.lang.String)
     */
    public long getLastModified() {
        return -1;
    }

    /** 
     * Returns the value of the named header field.
     * <p>
     * If called on a connection that sets the same header multiple times
     * with possibly different values, only the last value is returned.
     * 
     *
     * @param   name   the name of a header field.
     * @return  the value of the named header field, or <code>null</code>
     *          if there is no such field in the header.
     */
    public String getHeaderField(String name) {
        return null;
    }

    /** 
     * Returns an unmodifiable Map of the header fields.
     * The Map keys are Strings that represent the
     * response-header field names. Each Map value is an
     * unmodifiable List of Strings that represents 
     * the corresponding field values.
     *
     * @return a Map of header fields
     * @since 1.4
     */
    public Map getHeaderFields() {
        return null;
    }

    /** 
     * Returns the value of the named field parsed as a number.
     * <p>
     * This form of <code>getHeaderField</code> exists because some 
     * connection types (e.g., <code>http-ng</code>) have pre-parsed 
     * headers. Classes for that connection type can override this method 
     * and short-circuit the parsing. 
     *
     * @param   name      the name of the header field.
     * @param   Default   the default value.
     * @return  the value of the named field, parsed as an integer. The
     *          <code>Default</code> value is returned if the field is
     *          missing or malformed.
     */
    public int getHeaderFieldInt(String name, int Default) {
        return 0;
    }

    /** 
     * Returns the value of the named field parsed as date.
     * The result is the number of milliseconds since January 1, 1970 GMT
     * represented by the named field. 
     * <p>
     * This form of <code>getHeaderField</code> exists because some 
     * connection types (e.g., <code>http-ng</code>) have pre-parsed 
     * headers. Classes for that connection type can override this method 
     * and short-circuit the parsing. 
     *
     * @param   name     the name of the header field.
     * @param   Default   a default value.
     * @return  the value of the field, parsed as a date. The value of the
     *          <code>Default</code> argument is returned if the field is
     *          missing or malformed.
     */
    public long getHeaderFieldDate(String name, long Default) {
        return -1;
    }

    /** 
     * Returns the key for the <code>n</code><sup>th</sup> header field.
     * It returns <code>null</code> if there are fewer than <code>n+1</code> fields. 
     *
     * @param   n   an index, where n>=0
     * @return  the key for the <code>n</code><sup>th</sup> header field,
     *          or <code>null</code> if there are fewer than <code>n+1</code>
     *		fields.
     */
    public String getHeaderFieldKey(int n) {
        return null;
    }

    /** 
     * Returns the value for the <code>n</code><sup>th</sup> header field. 
     * It returns <code>null</code> if there are fewer than
     * <code>n+1</code>fields. 
     * <p>
     * This method can be used in conjunction with the 
     * {@link #getHeaderFieldKey(int) getHeaderFieldKey} method to iterate through all 
     * the headers in the message. 
     *
     * @param   n   an index, where n>=0
     * @return  the value of the <code>n</code><sup>th</sup> header field
     *		or <code>null</code> if there are fewer than <code>n+1</code> fields
     * @see     java.net.URLConnection#getHeaderFieldKey(int)
     */
    public String getHeaderField(int n) {
        return null;
    }

    /** 
     * Retrieves the contents of this URL connection. 
     * <p>
     * This method first determines the content type of the object by 
     * calling the <code>getContentType</code> method. If this is 
     * the first time that the application has seen that specific content 
     * type, a content handler for that content type is created: 
     * <ol>
     * <li>If the application has set up a content handler factory instance
     *     using the <code>setContentHandlerFactory</code> method, the
     *     <code>createContentHandler</code> method of that instance is called
     *     with the content type as an argument; the result is a content
     *     handler for that content type.
     * <li>If no content handler factory has yet been set up, or if the
     *     factory's <code>createContentHandler</code> method returns
     *     <code>null</code>, then the application loads the class named:
     *     <blockquote><pre>
     *         sun.net.www.content.&lt;<i>contentType</i>&gt;
     *     </pre></blockquote>
     *     where &lt;<i>contentType</i>&gt; is formed by taking the
     *     content-type string, replacing all slash characters with a
     *     <code>period</code> ('.'), and all other non-alphanumeric characters
     *     with the underscore character '<code>_</code>'. The alphanumeric
     *     characters are specifically the 26 uppercase ASCII letters
     *     '<code>A</code>' through '<code>Z</code>', the 26 lowercase ASCII
     *     letters '<code>a</code>' through '<code>z</code>', and the 10 ASCII
     *     digits '<code>0</code>' through '<code>9</code>'. If the specified
     *     class does not exist, or is not a subclass of
     *     <code>ContentHandler</code>, then an
     *     <code>UnknownServiceException</code> is thrown.
     * </ol>
     *
     * @return     the object fetched. The <code>instanceof</code> operator
     *               should be used to determine the specific kind of object
     *               returned.
     * @exception  IOException              if an I/O error occurs while
     *               getting the content.
     * @exception  UnknownServiceException  if the protocol does not support
     *               the content type.
     * @see        java.net.ContentHandlerFactory#createContentHandler(java.lang.String)
     * @see        java.net.URLConnection#getContentType()
     * @see        java.net.URLConnection#setContentHandlerFactory(java.net.ContentHandlerFactory)
     */
    public Object getContent() throws IOException {
        return null;
    }

    /** 
     * Retrieves the contents of this URL connection. 
     *
     * @param classes the <code>Class</code> array 
     * indicating the requested types
     * @return     the object fetched that is the first match of the type
     *               specified in the classes array. null if none of 
     *               the requested types are supported.
     *               The <code>instanceof</code> operator should be used to 
     *               determine the specific kind of object returned.
     * @exception  IOException              if an I/O error occurs while
     *               getting the content.
     * @exception  UnknownServiceException  if the protocol does not support
     *               the content type.
     * @see        java.net.URLConnection#getContent()
     * @see        java.net.ContentHandlerFactory#createContentHandler(java.lang.String)
     * @see        java.net.URLConnection#getContent(java.lang.Class[])
     * @see        java.net.URLConnection#setContentHandlerFactory(java.net.ContentHandlerFactory)
     */
    public Object getContent(Class[] classes) throws IOException {
        return null;
    }

    /** 
     * Returns a permission object representing the permission
     * necessary to make the connection represented by this
     * object. This method returns null if no permission is
     * required to make the connection. By default, this method
     * returns <code>java.security.AllPermission</code>. Subclasses
     * should override this method and return the permission
     * that best represents the permission required to make a 
     * a connection to the URL. For example, a <code>URLConnection</code>
     * representing a <code>file:</code> URL would return a 
     * <code>java.io.FilePermission</code> object.
     *
     * <p>The permission returned may dependent upon the state of the
     * connection. For example, the permission before connecting may be
     * different from that after connecting. For example, an HTTP
     * sever, say foo.com, may redirect the connection to a different
     * host, say bar.com. Before connecting the permission returned by
     * the connection will represent the permission needed to connect
     * to foo.com, while the permission returned after connecting will
     * be to bar.com.
     * 
     * <p>Permissions are generally used for two purposes: to protect
     * caches of objects obtained through URLConnections, and to check
     * the right of a recipient to learn about a particular URL. In
     * the first case, the permission should be obtained
     * <em>after</em> the object has been obtained. For example, in an
     * HTTP connection, this will represent the permission to connect
     * to the host from which the data was ultimately fetched. In the
     * second case, the permission should be obtained and tested
     * <em>before</em> connecting.
     *
     * @return the permission object representing the permission
     * necessary to make the connection represented by this
     * URLConnection. 
     *
     * @exception IOException if the computation of the permission
     * requires network or file I/O and an exception occurs while
     * computing it.  
     */
    public Permission getPermission() throws IOException {
        return null;
    }

    /** 
     * Returns an input stream that reads from this open connection.
     *
     * @return     an input stream that reads from this open connection.
     * @exception  IOException              if an I/O error occurs while
     *               creating the input stream.
     * @exception  UnknownServiceException  if the protocol does not support
     *               input.
     */
    public InputStream getInputStream() throws IOException {
        return null;
    }

    /** 
     * Returns an output stream that writes to this connection.
     *
     * @return     an output stream that writes to this connection.
     * @exception  IOException              if an I/O error occurs while
     *               creating the output stream.
     * @exception  UnknownServiceException  if the protocol does not support
     *               output.
     */
    public OutputStream getOutputStream() throws IOException {
        return null;
    }

    /** 
     * Returns a <code>String</code> representation of this URL connection.
     *
     * @return  a string representation of this <code>URLConnection</code>.
     */
    public String toString() {
        return null;
    }

    /** 
     * Sets the value of the <code>doInput</code> field for this 
     * <code>URLConnection</code> to the specified value. 
     * <p>
     * A URL connection can be used for input and/or output.  Set the DoInput
     * flag to true if you intend to use the URL connection for input,
     * false if not.  The default is true.
     *
     * @param   doinput   the new value.
     * @throws IllegalStateException if already connected
     * @see     java.net.URLConnection#doInput
     * @see #getDoInput()
     */
    public void setDoInput(boolean doinput) { }

    /** 
     * Returns the value of this <code>URLConnection</code>'s
     * <code>doInput</code> flag.
     *
     * @return  the value of this <code>URLConnection</code>'s
     *          <code>doInput</code> flag.
     * @see     #setDoInput(boolean)
     */
    public boolean getDoInput() {
        return false;
    }

    /** 
     * Sets the value of the <code>doOutput</code> field for this 
     * <code>URLConnection</code> to the specified value. 
     * <p>
     * A URL connection can be used for input and/or output.  Set the DoOutput
     * flag to true if you intend to use the URL connection for output,
     * false if not.  The default is false.
     *
     * @param   dooutput   the new value.
     * @throws IllegalStateException if already connected
     * @see #getDoOutput()
     */
    public void setDoOutput(boolean dooutput) { }

    /** 
     * Returns the value of this <code>URLConnection</code>'s
     * <code>doOutput</code> flag.
     *
     * @return  the value of this <code>URLConnection</code>'s
     *          <code>doOutput</code> flag.
     * @see     #setDoOutput(boolean)
     */
    public boolean getDoOutput() {
        return false;
    }

    /** 
     * Set the value of the <code>allowUserInteraction</code> field of 
     * this <code>URLConnection</code>. 
     *
     * @param   allowuserinteraction   the new value.
     * @throws IllegalStateException if already connected
     * @see     #getAllowUserInteraction()
     */
    public void setAllowUserInteraction(boolean allowuserinteraction) { }

    /** 
     * Returns the value of the <code>allowUserInteraction</code> field for
     * this object.
     *
     * @return  the value of the <code>allowUserInteraction</code> field for
     *          this object.
     * @see     #setAllowUserInteraction(boolean)
     */
    public boolean getAllowUserInteraction() {
        return false;
    }

    /** 
     * Sets the default value of the 
     * <code>allowUserInteraction</code> field for all future 
     * <code>URLConnection</code> objects to the specified value. 
     *
     * @param   defaultallowuserinteraction   the new value.
     * @see     #getDefaultAllowUserInteraction()
     */
    public static void setDefaultAllowUserInteraction(boolean
        defaultallowuserinteraction)
    { }

    /** 
     * Returns the default value of the <code>allowUserInteraction</code>
     * field.
     * <p>
     * Ths default is "sticky", being a part of the static state of all
     * URLConnections.  This flag applies to the next, and all following
     * URLConnections that are created.
     *
     * @return  the default value of the <code>allowUserInteraction</code>
     *          field.
     * @see     #setDefaultAllowUserInteraction(boolean)
     */
    public static boolean getDefaultAllowUserInteraction() {
        return false;
    }

    /** 
     * Sets the value of the <code>useCaches</code> field of this 
     * <code>URLConnection</code> to the specified value. 
     * <p>
     * Some protocols do caching of documents.  Occasionally, it is important
     * to be able to "tunnel through" and ignore the caches (e.g., the
     * "reload" button in a browser).  If the UseCaches flag on a connection
     * is true, the connection is allowed to use whatever caches it can.
     *  If false, caches are to be ignored.
     *  The default value comes from DefaultUseCaches, which defaults to
     * true.
     *
     * @param usecaches a <code>boolean</code> indicating whether 
     * or not to allow caching
     * @throws IllegalStateException if already connected
     * @see #getUseCaches()
     */
    public void setUseCaches(boolean usecaches) { }

    /** 
     * Returns the value of this <code>URLConnection</code>'s
     * <code>useCaches</code> field.
     *
     * @return  the value of this <code>URLConnection</code>'s
     *          <code>useCaches</code> field.
     * @see #setUseCaches(boolean)
     */
    public boolean getUseCaches() {
        return false;
    }

    /** 
     * Sets the value of the <code>ifModifiedSince</code> field of 
     * this <code>URLConnection</code> to the specified value.
     *
     * @param   ifmodifiedsince   the new value.
     * @throws IllegalStateException if already connected
     * @see     #getIfModifiedSince()
     */
    public void setIfModifiedSince(long ifmodifiedsince) { }

    /** 
     * Returns the value of this object's <code>ifModifiedSince</code> field.
     *
     * @return  the value of this object's <code>ifModifiedSince</code> field.
     * @see #setIfModifiedSince(long)
     */
    public long getIfModifiedSince() {
        return -1;
    }

    /** 
     * Returns the default value of a <code>URLConnection</code>'s
     * <code>useCaches</code> flag.
     * <p>
     * Ths default is "sticky", being a part of the static state of all
     * URLConnections.  This flag applies to the next, and all following
     * URLConnections that are created.
     *
     * @return  the default value of a <code>URLConnection</code>'s
     *          <code>useCaches</code> flag.
     * @see     #setDefaultUseCaches(boolean)
     */
    public boolean getDefaultUseCaches() {
        return false;
    }

    /** 
     * Sets the default value of the <code>useCaches</code> field to the 
     * specified value. 
     *
     * @param   defaultusecaches   the new value.
     * @see     #getDefaultUseCaches()
     */
    public void setDefaultUseCaches(boolean defaultusecaches) { }

    /** 
     * Sets the general request property. If a property with the key already
     * exists, overwrite its value with the new value.
     *
     * <p> NOTE: HTTP requires all request properties which can
     * legally have multiple instances with the same key
     * to use a comma-seperated list syntax which enables multiple
     * properties to be appended into a single property.
     *
     * @param   key     the keyword by which the request is known
     *                  (e.g., "<code>accept</code>").
     * @param   value   the value associated with it.
     * @throws IllegalStateException if already connected
     * @throws NullPointerException if key is <CODE>null</CODE>
     * @see #getRequestProperty(java.lang.String)
     */
    public void setRequestProperty(String key, String value) { }

    /** 
     * Adds a general request property specified by a
     * key-value pair.  This method will not overwrite
     * existing values associated with the same key.
     *
     * @param   key     the keyword by which the request is known
     *                  (e.g., "<code>accept</code>").
     * @param   value  the value associated with it.
     * @throws IllegalStateException if already connected
     * @throws NullPointerException if key is null
     * @see #getRequestProperties(java.lang.String)
     * @since 1.4
     */
    public void addRequestProperty(String key, String value) { }

    /** 
     * Returns the value of the named general request property for this
     * connection.
     *
     * @param key the keyword by which the request is known (e.g., "accept").
     * @return  the value of the named general request property for this
     *           connection. If key is null, then null is returned.
     * @throws IllegalStateException if already connected
     * @see #setRequestProperty(java.lang.String, java.lang.String)
     */
    public String getRequestProperty(String key) {
        return null;
    }

    /** 
     * Returns an unmodifiable Map of general request
     * properties for this connection. The Map keys
     * are Strings that represent the request-header
     * field names. Each Map value is a unmodifiable List 
     * of Strings that represents the corresponding 
     * field values.
     *
     * @return  a Map of the general request properties for this connection.
     * @throws IllegalStateException if already connected
     * @since 1.4
     */
    public Map getRequestProperties() {
        return null;
    }

    /** 
     * Sets the <code>ContentHandlerFactory</code> of an 
     * application. It can be called at most once by an application. 
     * <p>
     * The <code>ContentHandlerFactory</code> instance is used to 
     * construct a content handler from a content type 
     * <p>
     * If there is a security manager, this method first calls
     * the security manager's <code>checkSetFactory</code> method 
     * to ensure the operation is allowed. 
     * This could result in a SecurityException.
     *
     * @param      fac   the desired factory.
     * @exception  Error  if the factory has already been defined.
     * @exception  SecurityException  if a security manager exists and its  
     *             <code>checkSetFactory</code> method doesn't allow the operation.
     * @see        java.net.ContentHandlerFactory
     * @see        java.net.URLConnection#getContent()
     * @see        SecurityManager#checkSetFactory
     */
    public static synchronized void
        setContentHandlerFactory(ContentHandlerFactory fac)
    { }

    /** 
     * Tries to determine the content type of an object, based 
     * on the specified "file" component of a URL.
     * This is a convenience method that can be used by 
     * subclasses that override the <code>getContentType</code> method. 
     *
     * @param   fname   a filename.
     * @return  a guess as to what the content type of the object is,
     *          based upon its file name.
     * @see     java.net.URLConnection#getContentType()
     */
    public static String guessContentTypeFromName(String fname) {
        return null;
    }

    /** 
     * Tries to determine the type of an input stream based on the 
     * characters at the beginning of the input stream. This method can 
     * be used by subclasses that override the 
     * <code>getContentType</code> method. 
     * <p>
     * Ideally, this routine would not be needed. But many 
     * <code>http</code> servers return the incorrect content type; in 
     * addition, there are many nonstandard extensions. Direct inspection 
     * of the bytes to determine the content type is often more accurate 
     * than believing the content type claimed by the <code>http</code> server.
     *
     * @param      is   an input stream that supports marks.
     * @return     a guess at the content type, or <code>null</code> if none
     *             can be determined.
     * @exception  IOException  if an I/O error occurs while reading the
     *               input stream.
     * @see        java.io.InputStream#mark(int)
     * @see        java.io.InputStream#markSupported()
     * @see        java.net.URLConnection#getContentType()
     */
    public static String guessContentTypeFromStream(InputStream is)
        throws IOException
    {
        return null;
    }
}
