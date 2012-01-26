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

import java.net.*;

/** 
 * <code>MediaLocator</code> describes the location of
 * media content. <code>MediaLocator</code> is closely
 * related to <code>URL</code>. <code>URLs</code>
 * can be obtained from <code>MediaLocators</code>, and
 * <code>MediaLocators</code> can be constructed from
 * <code>URL</code>.
 * Unlike a <code>URL</code>, a <code>MediaLocator</code>
 * can be instanced without a <code>URLStreamHandler</code>
 * installed on the System. 
 * 
 * @see java.net.URL
 * @see java.net.URLStreamHandler
 * @version 1.8, 97/08/25.
 */
public class MediaLocator
{

    /** 
     * @param url The <CODE>URL</CODE> to construct this media locator from.
     */
    public MediaLocator(URL url) { }

    /**  */
    public MediaLocator(String locatorString) { }

    /** 
     * Get the <CODE>URL</CODE> associated with this <CODE>MediaLocator</CODE>.
     */
    public URL getURL() throws MalformedURLException {
        return null;
    }

    /** 
     * Get the beginning of the locator string
     * up to but not including the first colon.
     * @return The protocol for this <CODE>MediaLocator</CODE>.
     */
    public String getProtocol() {
        return null;
    }

    /** 
     * Get the <CODE>MediaLocator</CODE> string with the protocol removed.
     *
     * @return The argument string.
     */
    public String getRemainder() {
        return null;
    }

    /** 
     * Used for printing <CODE>MediaLocators</CODE>.
     * @return A string for printing <CODE>MediaLocators</CODE>.
     */
    public String toString() {
        return null;
    }

    /** 
     * Create a string from the <CODE>URL</CODE> argument that can
     * be used to construct the <CODE>MediaLocator</CODE>.
     *
     * @return A string for the <CODE>MediaLocator</CODE>.
     */
    public String toExternalForm() {
        return null;
    }
}
