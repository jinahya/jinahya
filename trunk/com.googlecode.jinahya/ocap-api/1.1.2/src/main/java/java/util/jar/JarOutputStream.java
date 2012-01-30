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


  


package java.util.jar;

import java.util.zip.*;
import java.io.*;

/** 
 * The <code>JarOutputStream</code> class is used to write the contents
 * of a JAR file to any output stream. It extends the class
 * <code>java.util.zip.ZipOutputStream</code> with support
 * for writing an optional <code>Manifest</code> entry. The
 * <code>Manifest</code> can be used to specify meta-information about
 * the JAR file and its entries.
 *
 * @author  David Connelly
 * @version 1.19, 02/02/00
 * @see	    Manifest
 * @see	    java.util.zip.ZipOutputStream
 * @since   1.2
 */
public class JarOutputStream extends ZipOutputStream
{

    /** 
     * Creates a new <code>JarOutputStream</code> with the specified
     * <code>Manifest</code>. The manifest is written as the first
     * entry to the output stream.
     *
     * @param out the actual output stream
     * @param man the optional <code>Manifest</code>
     * @exception IOException if an I/O error has occurred
     */
    public JarOutputStream(OutputStream out, Manifest man) throws IOException { 
        super(out);
    }

    /** 
     * Creates a new <code>JarOutputStream</code> with no manifest.
     * @param out the actual output stream
     * @exception IOException if an I/O error has occurred
     */
    public JarOutputStream(OutputStream out) throws IOException { 
        super(out);
    }

    /** 
     * Begins writing a new JAR file entry and positions the stream
     * to the start of the entry data. This method will also close
     * any previous entry. The default compression method will be
     * used if no compression method was specified for the entry.
     * The current time will be used if the entry has no set modification
     * time.
     *
     * @param ze the ZIP/JAR entry to be written
     * @exception ZipException if a ZIP error has occurred
     * @exception IOException if an I/O error has occurred
     */
    public void putNextEntry(ZipEntry ze) throws IOException { }
}
