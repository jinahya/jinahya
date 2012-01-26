/**
<p>This is not an official specification document, and usage is restricted.
</p>
<a name="notice"><strong><center>
NOTICE
</center></strong><br>
<br>

(c) 2005-2008 Sun Microsystems, Inc. All Rights Reserved.
<p>

Neither this file nor any files generated from it describe a complete
specification, and they may only be used as described below.  
<p>
Sun Microsystems Inc. owns the copyright in this file and it is provided
to you for informative use only. For example, 
this file and any files generated from it may be used to generate other documentation, 
such as a unified set of documents of API signatures for a platform 
that includes technologies expressed as Java APIs. 
This file may also be used to produce "compilation stubs," 
which allow applications to be compiled and validated for such platforms. 
By contrast, no permission is given for you to incorporate this file, 
in whole or in part, in an implementation of a Java specification.
<p>
Any work generated from this file, such as unified javadocs or compiled
stub files, must be accompanied by this notice in its entirety.
<p>
This work corresponds to the API signatures of JSR 927: Java TV API 1.1.1.  
In the event of a discrepency between this work and the JSR 927 specification, 
which is available at http://www.jcp.org/en/jsr/detail?id=927, the latter takes precedence.
*/



  


package javax.media;

import java.io.IOException;
import java.net.URL;
import java.net.MalformedURLException;
import java.util.Enumeration;
import java.util.Vector;
import javax.media.protocol.DataSource;
import javax.media.protocol.URLDataSource;

/** 
 * <code>Manager</code> is the access point for obtaining
 * system dependent resources such as <code>Players</code>,
 * <code>DataSources</code>, and the system <code>TimeBase</code>.
 * <p>
 *
 * A <code>Player</code> is an object used to
 * control and render multimedia data that
 * is specific to the content type of the data.
 * A <code>DataSource</code> is an object used to 
 * deliver time-based multimedia data that is specific
 * to a delivery protocol.
 * A <code>DataSource</code> provides
 * a <code>Player</code> with media data;
 * a <CODE>Player</CODE> must have a <CODE>DataSource</CODE>.
 * <code>Manager</code> provides access to a protocol and media independent
 * mechanism for constructing <code>Players</code> and
 * <code>DataSources</code>.
 *
 * <h2>Creating Players and DataSources</h2>
 *
 * <code>Manager</code> will create<code>Players</code> from a
 * <code>URL</code>, a <CODE>MediaLocator</CODE> or a <code>DataSource</code>.
 * Creating a <code>Player</code> requires the following:
 * <ul>
 * <li> Obtain the connected <code>DataSource</code> for the specified
 * protocol
 * <li> Obtain the <code>Player</code> for the content-type
 * specified by the <code>DataSource</code>
 * <li> Attach the <code>DataSource</code> to the <code>Player</code>
 * using the <code>setSource</code> method.
 * </ul>
 *
 * It is possible to create multiple Players for the same scarce resource
 * (such as a hardware video decoder).
 * However, doing so may affect the state of other Players which depend on
 * that resource.
 *
 * <h3>Finding DataSources by Protocol</h3>
 *
 * A <code>MediaLocator</code> defines a protocol for obtaining
 * content.
 * <code>DataSources</code> are identified by the protocol
 * that they support. <code>Manager</code> uses the protocol
 * name to find <code>DataSource</code> classes.
 * <p>
 * 
 * To find a <code>DataSource</code> using a <code>MediaLocator</code>,
 * <code>Manager</code> constructs a list of class names from the protocol
 * package-prefix list and the protocol name obtained
 * from the <code>MediaLocator</code>.
 * For each class name in the constructed list a new <code>DataSource</code>
 * is instanced, the <code>MediaLocator</code> is attached,
 * and the <code>DataSource</code> is connected.
 * If no errors have occurred, the procces is considered finished and the
 * connected <code>DataSource</code> is used by
 * <code>Manager</code> in any following operations.
 * If there was an error then the next class name in the list
 * is tried.
 * The exact details of the search algorithm is described in
 * the method documentation below.
 *
 * <h3>Finding Players by Content Type</h3>
 *
 * A <code>Player</code> is a <code>MediaHandler</code>.
 * A <code>MediaHandler</code> is a an object that reads
 * data from a <code>DataSource</code>. There are two types
 * of supported <code>MediaHandler</code>: <code>MediaProxy</code>,
 * and <code>Player</code>.
 * <p>
 *
 * <code>MediaHandlers</code> are identified by the content type that they
 * support. A <code>DataSource</code> identifies the content type
 * of the data it produces with the <code>getContentType</code> method.
 * <code>Manager</code> uses the content type name to
 * find instances of <code>MediaHandler</code>.
 * <p>
 *
 * To find a <code>MediaHandler</code> using a content type name,
 * <code>Manager</code> constructs a list of class names from
 * the content package-prefix list and the content type name.
 * For each class name in the constructed list a new <code>MediaHandler</code>
 * is instanced, and the <code>DataSource</code> is attached to
 * the <code>MediaHandler</code> using <coded>MediaHandler.setSource</code>.
 * <p>
 *
 * If the <code>MediaHandler</code> is a <code>Player</code> and the
 * <code>setSource</code> was successful the process is finished
 * and the <code>Player</code> is returned.
 * If the <code>setSource</code> failed, another name in the
 * list is tried.
 * <p>
 *
 * If the <code>MediaHandler</code> is a <code>MediaProxy</code>
 * then a new <code>DataSource</code> is obtained from the
 * <code>MediaProxy</code>, a new list is created for the
 * content type the <code>DataSource</code> supports and the
 * whole thing is tried again.
 * <p>
 * 
 * If a valid <code>Player</code>, is not found then the whole
 * procedure is repeated is repeated with "unknown" substituted
 * for the content-type name. The "unknown" content type is supported
 * by generic <code>Players</code> that are capable of handling
 * a large variety of media types, often in a platform dependent
 * way.
 *<p>
 *
 * The detailed creation algorithm is specified in the methods below.
 * <p>
 *
 * <h2>Player Threads</h2>
 *
 * <code>Players</code> render media data asynchronously from
 * the main program flow.
 * This implies that a <code>Player</code> must often manage one
 * or more threads.
 * The threads managed by the <code>Player</code> are not
 * in the thread group of the application that calls
 * <code>createPlayer</code>.
 *
 * <h2>System Time Base</h2>
 *
 * All <code>Players</code> need a <code>TimeBase</code>. Many  
 * use a system-wide <code>TimeBase</code>, often based on
 * a time-of-day clock.
 * <code>Manager</code> provides access to the system <code>TimeBase</code>
 * through <code>getSystemTimeBase</code>.
 * 
 *
 * @see java.net.URL
 * @see MediaLocator
 * @see PackageManager
 * @see javax.media.protocol.DataSource
 * @see javax.media.protocol.URLDataSource
 * @see MediaHandler
 * @see Player
 * @see MediaProxy
 * @see TimeBase
 *
 * @version 1.58, 07/09/19.
 */
public final class Manager
{
    public static final String UNKNOWN_CONTENT_NAME = "unknown";

    /*
     * This hidden constructor does not necessarily correspond to
     * a constructor in the original source file -- it keeps javadoc
     * from generating an inappropriate default constructor.
     */
    private Manager() { }

    /** 
     * Create a <CODE>Player</CODE> for the specified media.
     * This creates a MediaLocator from the URL and then
     * calls <CODE>createPlayer</CODE>.
     *
     * @param sourceURL The <CODE>URL</CODE> that describes the media data.
     * @return A new <CODE>Player</CODE>.
     * @exception NoPlayerException Thrown if no <CODE>Player</CODE>
     * can be found.
     * @exception IOException Thrown if there was a problem connecting
     * with the source.
     */
    public static Player createPlayer(URL sourceURL)
        throws IOException, NoPlayerException
    {
        return null;
    }

    /** 
     * Create a <code>Player</code> for the specified media.
     * <p>
     * The algorithm for creating a <CODE>Player</CODE> from
     * a <code>MediaLocator</code> is:
     * <ol>
     * <li>Get the protocol from the <code>MediaLocator</code>.
     * <li>Get a list of <code>DataSource</code> classes that
     * support the protocol, using the protocol package-prefix-list.
     * <li> For each source class in the list:
     * <ol>
     * <li>Instantiate a new <code>DataSource</code>,
     * <li>Call the <code>connect</code> method to connect the source.
     * <li>Get the media content-type-name (using <code>getContentType</code>)
     * from the source.
     * <li>Get a list of <code>MediaHandler</code> classes that support the
     * media-content-type-name, using the content package-prefix-list.
     * <li>For each <code>MediaHandler</code> class in the list:
     * <ol>
     * <li>Instantiate a new <code>MediaHandler</code>.
     * <li>Attach the source to the <code>MediaHandler</code> by calling
     * <code>MediaHandler.setSource</code>.
     * <li>If there are no failures, determine the type of
     * the <code>MediaHandler</code>; otherwise try the next
     * <coded>MediaHandler</code> in the list.
     * <li>If the <code>MediaHandler</code> is a <code>Player</code>,
     * return the new <code>Player</code>.
     * <li>If the <code>MediaHandler</code> is a <code>MediaProxy</code>,
     * obtain a new <code>DataSource</code> from the <code>MediaProxy</code>,
     * obtain the list of <code>MediaHandlers</code> that support the new
     * <code>DataSource</code>, and continue searching the new list.
     * </ol>
     * <li>If no <code>MediaHandler</code> is found for this source,
     * try the next source in the list.
     * </ol>
     * <li>If no <code>Player</code> is found after trying all of the sources,
     * reuse the source list.<br>
     * This time, for each source class in the list:
     * <ol>
     * <li>Instantiate the source.
     * <li>Call the <code>connect</code> method to connect to the source.
     * <li>Use the content package-prefix-list to create a list of 
     * <code>MediaHandler</code> classes that support the
     * "unknown" content-type-name.
     * <li>For each <code>MediaHandler</code> class in the list,
     * search for a <code>Player</code> as in the previous search.
     * <ol>
     * <li>If no <code>Player</code> is found after trying all of the sources,
     * a <CODE>NoPlayerException</CODE> is thrown.
     * </ol>
     * </ol>
     * @param sourceLocator A <CODE>MediaLocator</CODE> that describes
     * the media content.
     * @return A <CODE>Player</CODE> for the media described by the source.
     * @exception NoPlayerException Thrown if no <CODE>Player</CODE> can
     * be found.
     * @exception IOException Thrown if there was a problem connecting
     * with the source.
     */
    public static Player createPlayer(MediaLocator sourceLocator)
        throws IOException, NoPlayerException
    {
        return null;
    }

    /** 
     * Create a <code>Player</code> for the <code>DataSource</code>.
     * <p>
     * The algorithm for creating a <CODE>Player</CODE> from
     * a <code>DataSource</code> is:
     * <ol>
     * <li>Get the media content-type-name from the source by
     * calling <code>getContentType</code>.
     * <li>Use the content package-prefix-list to get a list of 
     * <code>Player</code> classes that support the media content-type name.
     * <li>For each <code>Player</code> class in the list:
     * <ol>
     * <li>Instantiate a new <code>Player</code>.
     * <li>Attach the source to the <code>Player</code> by calling
     * <code>setSource</code> on the <code>Player</code>.
     * <li>If there are no failures,  return the new <code>Player</code>;
     * otherwise,
     * try the next <code>Player</code> in the list.
     *      </ol>
     * <li>If no <code>Player</code> is found for this source:
     * <ol>
     * <li>Use the content package-prefix-list to create a list 
     * of <code>Player</code> classes that support the
     * "unknown" content-type-name.
     * <li>For each <code>Player</code> class in the list:
     * <ol>
     * <li>Instantiate a new <code>Player</code>.
     * <li>Attach the source to the <code>Player</code> by
     * calling <code>setSource</code> 
     * on the <code>Player</code>.
     * <li>If there are no failures, return the new <code>Player</code>;
     * otherwise, try the next <code>Player</code> in the list.
     * </ol>
     * </ol>
     * <li>If no <code>Player</code> can be created,
     * a <CODE>NoPlayerException</CODE> is thrown.
     * </ol>
     * @param DataSource The <CODE>DataSource</CODE> that describes
     * the media content.
     * @return A new <code>Player</code>.
     * @exception NoPlayerException Thrown if a <code>Player</code> can't
     * be created.
     * @exception IOException Thrown if there was a problem connecting
     * with the source.
     */
    public static Player createPlayer(DataSource source)
        throws IOException, NoPlayerException
    {
        return null;
    }

    /** 
     * Create a <code>DataSource</code> for the specified media.
     *
     * @param sourceURL The <CODE>URL</CODE> that describes the media data.
     * @return A new <CODE>DataSource</CODE> for the media.
     * @exception NoDataSourceException Thrown if no <code>DataSource</code>
     * can be found.
     * @exception IOException Thrown if there was a problem connecting
     * with the source.
     */
    public static DataSource createDataSource(URL sourceURL)
        throws IOException, NoDataSourceException
    {
        return null;
    }

    /** 
     * Create a <code>DataSource</code> for the specified media.
     * <p>
     *
     * Returns a data source for the protocol specified by
     * the <CODE>MediaLocator</CODE>. The returned data source
     * is <i>connected</i>; <code>DataSource.connect</code> has been
     * invoked.
     * <p>
     *
     * The algorithm for creating a <code>DataSource</code> from
     * a <code>MediaLocator</code> is:
     * <ol>
     * <li>Get the protocol from the <code>MediaLocator</code>.
     * <li>Use the protocol package-prefix list to get a list of 
     * <code>DataSource</code> classes that
     * support the protocol.
     * <li> For each source class in the list:
     * <ol>
     * <li>Instantiate a new <code>DataSource</code>.
     * <li>Call <code>connect</code> to connect the source.
     * <li>If there are no errors, return the connected
     * source; otherwise, try the next source in the list.
     * </ol>
     * <li>If no source has been found, obtain a <code>URL</code> from the
     * <code>MediaLocator</code> and use it to create
     * a <code>URLDataSource</code>
     * <li>If no source can be found, a <CODE>NoDataSourceException</CODE>
     * is thrown.
     * </ol>
     * 
     *
     * @param sourceLocator The source protocol for the media data.
     * @return A connected <CODE>DataSource</CODE>.
     * @exception NoDataSourceException Thrown if no <CODE>DataSource</CODE>
     * can be found.
     * @exception IOException Thrown if there was a problem connecting
     * with the source.
     */
    public static DataSource createDataSource(MediaLocator sourceLocator)
        throws IOException, NoDataSourceException
    {
        return null;
    }

    /** 
     * Get the time-base object for the system.
     * @return The system time base.
     */
    public static TimeBase getSystemTimeBase() {
        return null;
    }

    /** 
     * Build a list of <CODE>DataSource</CODE> class names from the
     * protocol prefix-list and a protocol name.
     * <p>
     * The first name in the list will always be:
     * <blockquote><pre>
     * media.protocol.&lt;protocol&gt;DataSource
     * </pre></blockquote>
     * <p>
     *
     * Each additional name looks like:
     * <blockquote><pre>
     * &lt;protocol-prefix&gt;.media.protocol.&lt;protocol&gt;.DataSource
     * </pre></blockquote>
     * for every <CODE>&lt;protocol-prefix&gt;</CODE> in the
     * protocol-prefix-list.
     *
     * @param protocol The name of the protocol the source must
     * support.
     * @return A vector of strings, where each string is
     * a <CODE>Player</CODE> class-name.
     */
    public static Vector getDataSourceList(String protocolName) {
        return null;
    }

    /** 
     * Build a list of <CODE>Handler/CODE> classes from the
     * content-prefix-list and a content name.
     * <p>
     * The first name in the list will always be:
     * <blockquote><pre>
     * media.content.&lt;contentType&gt;.Handler
     * </pre></blockquote>
     * <p>
     *
     * Each additional name looks like:
     * <blockquote><pre>
     * &lt;content-prefix&gt;.media.content.&lt;contentName&gt;.Player
     * </pre></blockquote>
     * for every <CODE>&lt;content-prefix&gt;</CODE> in the
     * content-prefix-list.
     *
     * @param contentName The content type to use in the class name.
     * @return A vector of strings where each one is a <CODE>Player</CODE>
     * class-name.
     */
    public static Vector getHandlerClassList(String contentName) {
        return null;
    }
}
