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
 * This interface defines the capabilities that a connection notifier
 * must have.
 *
 * @author  Nik Shaylor
 * @version 03/13/02 (CLDC 1.1)
 * @since   CLDC 1.0
 */
public interface StreamConnectionNotifier extends Connection
{

    /** 
     * Returns a <code>StreamConnection</code> object that represents
     * a server side socket connection.  The method blocks until
     * a connection is made.
     *
     * <!-- GCFOP begin -->
     * <p>
     * If a connection is attempted and a security manager exists, the
     * security manager's <code>checkAccept(host, port)</code> method is
     * called to ensure that a connection from the remote host and port
     * is permitted.
     * <!-- GCFOP end -->
     *
     * @return  A <code>StreamConnection</code> to communicate with a client.
     * @exception  IOException  If an I/O error occurs.
     *
     * <!-- GCFOP begin -->
     * @throws SecurityException If a security
     * manager exists, and its <code>checkAccept</code> method does
     * not allow the operation.
     * <!-- GCFOP end -->
     */
    public StreamConnection acceptAndOpen() throws IOException;
}
