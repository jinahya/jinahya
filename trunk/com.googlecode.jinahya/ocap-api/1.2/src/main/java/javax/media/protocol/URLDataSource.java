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



  


package javax.media.protocol;

import java.io.*;
import java.net.*;

import javax.media.MediaLocator;
import javax.media.Duration;
import javax.media.Time;

/** 
 * A default data-source created directly from
 * a <code>URL</code> using <code>URLConnection</code>.
 *
 * @see java.net.URL
 * @see java.net.URLConnection
 *
 * @see InputSourceStream
 * @version 1.20, 05/10/13.
 *
 */
public class URLDataSource extends PullDataSource
{
    protected URLConnection conn;

    protected ContentDescriptor contentType;

    protected boolean connected;

    /** 
     * Implemented by subclasses.
     */
    protected URLDataSource() { }

    /** 
     * Construct a <CODE>URLDataSource</CODE>
     * directly from a <code>URL</code>.
     */
    public URLDataSource(URL url) throws IOException { }

    public PullSourceStream[] getStreams() {
        return null;
    }

    /** 
     * Initialize the connection with the source.
     *
     * @exception IOException Thrown if there are problems setting
     * up the connection.
     */
    public void connect() throws IOException { }

    /** 
     * Return the content type name.
     * 
     * @return The content type name.
     */
    public String getContentType() {
        return null;
    }

    /** 
     * Disconnect the source.
     */
    public void disconnect() { }

    public void start() throws IOException { }

    /** 
     * Stops the 
     *
     */
    public void stop() throws IOException { }

    /** 
     * Returns <code>Duration.DURATION_UNKNOWN</code>.
     * The duration is not available from an <code>InputStream</code>.
     *
     * @return <code>Duration.DURATION_UNKNOWN</code>.
     */
    public Time getDuration() {
        return null;
    }

    /** 
     * Returns an empty array, because this source
     * doesn't provide any controls.
     *
     * @return empty <code>Object</code> array.
     */
    public Object[] getControls() {
        return null;
    }

    /** 
     * Returns null, because this source doesn't provide
     * any controls.
     */
    public Object getControl(String controlName) {
        return null;
    }
}
