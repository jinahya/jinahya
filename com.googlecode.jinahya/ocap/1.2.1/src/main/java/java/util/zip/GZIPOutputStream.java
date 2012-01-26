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


  


package java.util.zip;

import java.io.OutputStream;
import java.io.IOException;

/** 
 * This class implements a stream filter for writing compressed data in
 * the GZIP file format.
 * @version 	1.17, 02/02/00
 * @author 	David Connelly
 *
 */
public class GZIPOutputStream extends DeflaterOutputStream
{
    /** 
     * CRC-32 of uncompressed data.
     */
    protected CRC32 crc;

    /** 
     * Creates a new output stream with the specified buffer size.
     * @param out the output stream
     * @param size the output buffer size
     * @exception IOException If an I/O error has occurred.
     * @exception IllegalArgumentException if size is <= 0
     */
    public GZIPOutputStream(OutputStream out, int size) throws IOException { 
	super(out, new Deflater(Deflater.DEFAULT_COMPRESSION, true), size);    
    }

    /** 
     * Creates a new output stream with a default buffer size.
     * @param out the output stream
     * @exception IOException If an I/O error has occurred.
     */
    public GZIPOutputStream(OutputStream out) throws IOException { 
        this(out, 512);
    }

    /** 
     * Writes array of bytes to the compressed output stream. This method
     * will block until all the bytes are written.
     * @param buf the data to be written
     * @param off the start offset of the data
     * @param len the length of the data
     * @exception IOException If an I/O error has occurred.
     */
    public synchronized void write(byte[] buf, int off, int len)
        throws IOException
    { }

    /** 
     * Finishes writing compressed data to the output stream without closing
     * the underlying stream. Use this method when applying multiple filters
     * in succession to the same output stream.
     * @exception IOException if an I/O error has occurred
     */
    public void finish() throws IOException { }
}
