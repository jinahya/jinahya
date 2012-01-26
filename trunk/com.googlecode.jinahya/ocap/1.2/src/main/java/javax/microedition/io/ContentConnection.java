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


  


package javax.microedition.io;

import java.io.*;

/** 
 * This interface defines the stream connection over which
 * content is passed.
 *
 * @author  Nik Shaylor
 * @version 12/17/01 (CLDC 1.1)
 * @since   CLDC 1.0
 */
public interface ContentConnection extends StreamConnection
{

    /** 
     * Returns the type of content that the resource connected to is
     * providing.  For instance, if the connection is via HTTP, then
     * the value of the <code>content-type</code> header field is returned.
     *
     * @return  the content type of the resource that the URL references,
     *          or <code>null</code> if not known.<p>
     *          NOTE: In certain environments, such as HttpConnection
     *          implemented on top of WAP, the content type is
     *          always known even if content-type header field is 
     *          missing from the server response.  Therefore, in
     *          certain cases, the content type might always be non-null
     *          even if the content-type header field is missing.
     */
    public String getType();

    /** 
     * Returns a string describing the encoding of the content which
     * the resource connected to is providing.
     * E.g. if the connection is via HTTP, the value of the
     * <code>content-encoding</code> header field is returned.
     *
     * @return  the content encoding of the resource that the URL
     *          references, or <code>null</code> if not known.
     */
    public String getEncoding();

    /** 
     * Returns the length of the content which is being provided.
     * E.g. if the connection is via HTTP, then the value of the
     * <code>content-length</code> header field is returned.
     *
     * @return  the content length of the resource that this connection's
     *          URL references, or <code>-1</code> if the content length
     *          is not known.
     */
    public long getLength();
}
