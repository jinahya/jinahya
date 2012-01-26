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
import java.util.jar.JarFile;
import java.util.jar.JarEntry;
import java.util.jar.Attributes;
import java.util.jar.Manifest;
import java.security.Permission;

/** 
 * A URL Connection to a Java ARchive (JAR) file or an entry in a JAR
 * file.
 *
 * <p>The syntax of a JAR URL is:
 *
 * <pre>
 * jar:&lt;url&gt;!/{entry}
 * </pre>
 *
 * <p>for example:
 *
 * <p><code>
 * jar:http://www.foo.com/bar/baz.jar!/COM/foo/Quux.class<br>
 * </code>
 *
 * <p>Jar URLs should be used to refer to a JAR file or entries in 
 * a JAR file. The example above is a JAR URL which refers to a JAR
 * entry. If the entry name is omitted, the URL refers to the whole
 * JAR file:
 *
 * <code>
 * jar:http://www.foo.com/bar/baz.jar!/
 * </code>
 * 
 * <p>Users should cast the generic URLConnection to a
 * JarURLConnection when they know that the URL they created is a JAR
 * URL, and they need JAR-specific functionality. For example:
 *
 * <pre>
 * URL url = new URL("jar:file:/home/duke/duke.jar!/");
 * JarURLConnection jarConnection = (JarURLConnection)url.openConnection();
 * Manifest manifest = jarConnection.getManifest();
 * </pre>
 *
 * <p>Examples:
 * 
 * <dl>
 * 
 * <dt>A Jar entry
 * <dd><code>jar:http://www.foo.com/bar/baz.jar!/COM/foo/Quux.class</code>
 *
 * <dt>A Jar file
 * <dd><code>jar:http://www.foo.com/bar/baz.jar!/</code>
 *
 * <dt>A Jar directory
 * <dd><code>jar:http://www.foo.com/bar/baz.jar!/COM/foo/</code>
 *
 * </dl>
 *
 * <p><code>!/</code> is refered to as the <em>separator</em>.
 *
 * <p>When constructing a JAR url via <code>new URL(context, spec)</code>,
 * the following rules apply:
 *
 * <ul>
 *
 * <li>if there is no context URL and the specification passed to the
 * URL constructor doesn't contain a separator, the URL is considered
 * to refer to a JarFile.
 *
 * <li>if there is a context URL, the context URL is assumed to refer
 * to a JAR file or a Jar directory.
 *
 * <li>if the specification begins with a '/', the Jar directory is
 * ignored, and the spec is considered to be at the root of the Jar
 * file.
 *
 * <p>Examples:
 *
 * <dl>
 *
 * <dt>context: <b>jar:http://www.foo.com/bar/jar.jar!/</b>, 
 * spec:<b>baz/entry.txt</b>
 *
 * <dd>url:<b>jar:http://www.foo.com/bar/jar.jar!/baz/entry.txt</b>
 *
 * <dt>context: <b>jar:http://www.foo.com/bar/jar.jar!/baz</b>, 
 * spec:<b>entry.txt</b>
 *
 * <dd>url:<b>jar:http://www.foo.com/bar/jar.jar!/baz/entry.txt</b>
 *
 * <dt>context: <b>jar:http://www.foo.com/bar/jar.jar!/baz</b>, 
 * spec:<b>/entry.txt</b>
 *
 * <dd>url:<b>jar:http://www.foo.com/bar/jar.jar!/entry.txt</b>
 *
 * </dl>
 *
 * </ul>
 *
 * @see java.net.URL
 * @see java.net.URLConnection
 *
 * @see java.util.jar.JarFile
 * @see java.util.jar.JarInputStream
 * @see java.util.jar.Manifest
 * @see java.util.zip.ZipEntry
 *
 * @author Benjamin Renaud
 * @since 1.2 
 */
public abstract class JarURLConnection extends URLConnection
{
    /** 
     * The connection to the JAR file URL, if the connection has been
     * initiated. This should be set by connect.
     */
    protected URLConnection jarFileURLConnection;

    /** 
     * Creates the new JarURLConnection to the specified URL.
     * @param url the URL 
     * @throws MalformedURLException if no legal protocol 
     * could be found in a specification string or the 
     * string could not be parsed. 
     */
    protected JarURLConnection(URL url) throws MalformedURLException { 
        super(url);
    }

    /** 
     * Returns the URL for the Jar file for this connection.
     *
     * @return the URL for the Jar file for this connection.
     */
    public URL getJarFileURL() {
        return null;
    }

    /** 
     * Return the entry name for this connection. This method
     * returns null if the JAR file URL corresponding to this
     * connection points to a JAR file and not a JAR file entry.
     *
     * @return the entry name for this connection, if any.  
     */
    public String getEntryName() {
        return null;
    }

    /** 
     * Return the JAR file for this connection. The returned object is
     * not modifiable, and will throw UnsupportedOperationException
     * if the caller attempts to modify it.
     *
     * @return the JAR file for this connection. If the connection is
     * a connection to an entry of a JAR file, the JAR file object is
     * returned
     *
     * @exception IOException if an IOException occurs while trying to
     * connect to the JAR file for this connection.
     *
     * @see #connect
     */
    public abstract JarFile getJarFile() throws IOException;

    /** 
     * Returns the Manifest for this connection, or null if none. The
     * returned object is not modifiable, and will throw
     * UnsupportedOperationException if the caller attempts to modify
     * it.
     *
     * @return the manifest object corresponding to the JAR file object
     * for this connection.
     *
     * @exception IOException if getting the JAR file for this
     * connection causes an IOException to be trown.
     *
     * @see #getJarFile
     */
    public Manifest getManifest() throws IOException {
        return null;
    }

    /** 
     * Return the JAR entry object for this connection, if any. This
     * method returns null if the JAR file URL corresponding to this
     * connection points to a JAR file and not a JAR file entry. The
     * returned object is not modifiable, and will throw
     * UnsupportedOperationException if the caller attempts to modify
     * it.  
     *
     * @return the JAR entry object for this connection, or null if
     * the JAR URL for this connection points to a JAR file.
     *
     * @exception IOException if getting the JAR file for this
     * connection causes an IOException to be trown.
     *
     * @see #getJarFile
     * @see #getJarEntry
     */
    public JarEntry getJarEntry() throws IOException {
        return null;
    }

    /** 
     * Return the Attributes object for this connection if the URL
     * for it points to a JAR file entry, null otherwise.
     * 
     * @return the Attributes object for this connection if the URL
     * for it points to a JAR file entry, null otherwise.  
     *
     * @exception IOException if getting the JAR entry causes an
     * IOException to be thrown.
     *
     * @see #getJarEntry
     */
    public Attributes getAttributes() throws IOException {
        return null;
    }

    /** 
     * Returns the main Attributes for the JAR file for this
     * connection.
     *
     * @return the main Attributes for the JAR file for this
     * connection.
     *
     * @exception IOException if getting the manifest causes an
     * IOException to be thrown.
     *
     * @see #getJarFile
     * @see #getManifest 
     */
    public Attributes getMainAttributes() throws IOException {
        return null;
    }

    /** 
     * Return the Certificate object for this connection if the URL
     * for it points to a JAR file entry, null otherwise. This method 
     * can only be called once
     * the connection has been completely verified by reading
     * from the input stream until the end of the stream has been
     * reached. Otherwise, this method will return <code>null</code>
     * <p>
     * ***NOTE: In J2ME CDC, there is no support for certificates by design.  
     * If CDC by itself or a J2ME profile built on CDC does not add back the 
     * java.security.Signature class (plus all its dependencies), 
     * a <code>null</code> value must always be returned.
     * 
     * @return the Certificate object for this connection if the URL
     * for it points to a JAR file entry, null otherwise.  
     *
     * @exception IOException if getting the JAR entry causes an
     * IOException to be thrown.
     *
     * @see #getJarEntry
     */
    public java.security.cert.Certificate[] getCertificates() throws IOException
    {
        return null;
    }
}
