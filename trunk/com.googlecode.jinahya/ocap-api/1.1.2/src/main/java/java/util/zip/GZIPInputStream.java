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

import java.io.SequenceInputStream;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.IOException;
import java.io.EOFException;

/** 
 * This class implements a stream filter for reading compressed data in
 * the GZIP format.
 *
 * @see		InflaterInputStream
 * @version 	1.22, 02/02/00
 * @author 	David Connelly
 *
 */
public class GZIPInputStream extends InflaterInputStream
{
    /** 
     * CRC-32 for uncompressed data.
     */
    protected CRC32 crc;

    /** 
     * Indicates end of input stream.
     */
    protected boolean eos;

    /** 
     * GZIP header magic number.
     */
    public static final int GZIP_MAGIC = 35615;

    /** 
     * Creates a new input stream with the specified buffer size.
     * @param in the input stream
     * @param size the input buffer size
     * @exception IOException if an I/O error has occurred
     * @exception IllegalArgumentException if size is <= 0
     */
    public GZIPInputStream(InputStream in, int size) throws IOException { 
    	super(in, new Inflater(true), size);
    }

    /** 
     * Creates a new input stream with a default buffer size.
     * @param in the input stream
     * @exception IOException if an I/O error has occurred
     */
    public GZIPInputStream(InputStream in) throws IOException { 
        this(in, 512);   
    }

    /** 
     * Reads uncompressed data into an array of bytes. Blocks until enough
     * input is available for decompression.
     * @param buf the buffer into which the data is read
     * @param off the start offset of the data
     * @param len the maximum number of bytes read
     * @return	the actual number of bytes read, or -1 if the end of the
     *		compressed input stream is reached
     * @exception IOException if an I/O error has occurred or the compressed
     *			      input data is corrupt
     */
    public int read(byte[] buf, int off, int len) throws IOException {
        return 0;
    }

    /** 
     * Closes the input stream.
     * @exception IOException if an I/O error has occurred
     */
    public void close() throws IOException { }
}
