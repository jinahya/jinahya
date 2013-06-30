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

/** 
 * The abstract class <code>ContentHandler</code> is the superclass 
 * of all classes that read an <code>Object</code> from a 
 * <code>URLConnection</code>. 
 * <p>
 * An application does not generally call the 
 * <code>getContent</code> method in this class directly. Instead, an 
 * application calls the <code>getContent</code> method in class 
 * <code>URL</code> or in <code>URLConnection</code>.
 * The application's content handler factory (an instance of a class that 
 * implements the interface <code>ContentHandlerFactory</code> set 
 * up by a call to <code>setContentHandler</code>) is 
 * called with a <code>String</code> giving the MIME type of the 
 * object being received on the socket. The factory returns an 
 * instance of a subclass of <code>ContentHandler</code>, and its 
 * <code>getContent</code> method is called to create the object. 
 * <p>
 * If no content handler could be found, URLConnection will 
 * look for a content handler in a user-defineable set of places.
 * By default it looks in sun.net.www.content, but users can define a 
 * vertical-bar delimited set of class prefixes to search through in 
 * addition by defining the java.content.handler.pkgs property.
 * The class name must be of the form:
 * <pre>
 *     {package-prefix}.{major}.{minor}
 * e.g.
 *     YoyoDyne.experimental.text.plain
 * </pre>
 * If the loading of the content handler class would be performed by 
 * a classloader that is outside of the the delegation chain of the caller,
 * the JVM will need the RuntimePermission "getClassLoader".
 * 
 * @author  James Gosling
 * @version 1.14, 02/02/00
 * @see     java.net.ContentHandler#getContent(java.net.URLConnection)
 * @see     java.net.ContentHandlerFactory
 * @see     java.net.URL#getContent()
 * @see     java.net.URLConnection
 * @see     java.net.URLConnection#getContent()
 * @see     java.net.URLConnection#setContentHandlerFactory(java.net.ContentHandlerFactory)
 * @since   JDK1.0
 */
public abstract class ContentHandler
{

    public ContentHandler() { }

    /** 
     * Given a URL connect stream positioned at the beginning of the 
     * representation of an object, this method reads that stream and 
     * creates an object from it. 
     *
     * @param      urlc   a URL connection.
     * @return     the object read by the <code>ContentHandler</code>.
     * @exception  IOException  if an I/O error occurs while reading the object.
     */
    public abstract Object getContent(URLConnection urlc) throws IOException;

    /** 
     * Given a URL connect stream positioned at the beginning of the 
     * representation of an object, this method reads that stream and 
     * creates an object that matches one of the types specified. 
     *
     * The default implementation of this method should call getContent()
     * and screen the return type for a match of the suggested types.
     *
     * @param      urlc   a URL connection.
     * @param      classes	an array of types requested  
     * @return     the object read by the <code>ContentHandler</code> that is 
     *                 the first match of the suggested types. 
     *                 null if none of the requested  are supported.
     * @exception  IOException  if an I/O error occurs while reading the object.
     */
    public Object getContent(URLConnection urlc, Class[] classes)
        throws IOException
    {
        return null;
    }
}
