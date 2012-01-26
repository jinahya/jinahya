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
 * This interface defines the capabilities that a stream connection
 * must have.
 * <p>
 * In a typical implementation of this interface (for instance
 * in MIDP 2.0), all <code>StreamConnections</code> have one 
 * underlying <code>InputStream</code> and one <code>OutputStream</code>.
 * Opening a <code>DataInputStream</code> counts as opening an
 * <code>InputStream</code> and opening a <code>DataOutputStream</code>
 * counts as opening an <code>OutputStream</code>.  Trying to open
 * another <code>InputStream</code> or <code>OutputStream</code>
 * causes an <code>IOException</code>.  Trying to open the
 * <code>InputStream</code> or <code>OutputStream</code> after
 * they have been closed causes an <code>IOException</code>.
 * <p>
 * NOTE of clarification for implementations supporting multiple streams: 
 * Each <code>openXXXStream</code> (where XXX can be
 * either Input or Output) call returns 
 * a unique new stream, <b>not</b> a copy of a single stream.  When an opened 
 * stream is closed, a <code>StreamConnection</code> implementation may 
 * choose to close the connection and close all other opened streams derived 
 * from that connection.  When this choice of implementation happens, further 
 * access to the connection and streams will throw an <code>IOException</code>.
 * When this choice of implementation is <b>not</b> chosen, where one opened 
 * stream is closed, all other opened streams must remain open and any
 * other <code>openXXXStream</code> call is valid and must return a new 
 * unique <code>XXXStream</code>.
 * <p>
 * <p>
 * The methods of <code>StreamConnection</code> are not 
 * synchronized.  The only stream method that can be called safely
 * in another thread is <code>close</code>.
 *
 * @author  Nik Shaylor, Antero Taivalsaari
 * @version 12/17/01 (CLDC 1.1)
 * @since   CLDC 1.0
 */
public interface StreamConnection extends InputConnection, OutputConnection
{
}
