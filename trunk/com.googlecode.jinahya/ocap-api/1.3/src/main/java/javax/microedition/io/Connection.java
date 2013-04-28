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
 * This is the most basic type of generic connection. Only the
 * <code>close</code> method is defined.  No open method is 
 * defined here because opening is always done using the 
 * <code>Connector.open()</code> methods.
 *
 * @author  Nik Shaylor
 * @version 12/17/01 (CLDC 1.1)
 * @since   CLDC 1.0
 */
public interface Connection
{

    /** 
     * Close the connection.
     * <p>
     * When a connection has been closed, access to any of its methods
     * that involve an I/O operation will cause an <code>IOException</code>
     * to be thrown.
     * Closing an already closed connection has no effect. Streams 
     * derived from the connection may be open when method is called.
     * Any open streams will cause the connection to be held open
     * until they themselves are closed. In this latter case access
     * to the open streams is permitted, but access to the connection
     * is not.
     *
     * @exception IOException  If an I/O error occurs
     */
    public void close() throws IOException;
}
