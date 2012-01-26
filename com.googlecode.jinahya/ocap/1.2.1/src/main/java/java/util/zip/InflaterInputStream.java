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

import java.io.FilterInputStream;
import java.io.InputStream;
import java.io.IOException;
import java.io.EOFException;

/** 
 * This class implements a stream filter for uncompressing data in the
 * "deflate" compression format. It is also used as the basis for other
 * decompression filters, such as GZIPInputStream.
 * NOTE: <B>java.util.zip.GZIPInputStream</B> is found in J2ME CDC profiles 
 * such as J2ME Foundation Profile.
 *
 * @see		Inflater
 * @version 	1.28, 02/02/00
 * @author 	David Connelly
 */
public class InflaterInputStream extends FilterInputStream
{
    /** 
     * Decompressor for this stream.
     */
    protected Inflater inf;

    /** 
     * Input buffer for decompression.
     */
    protected byte[] buf;

    /** 
     * Length of input buffer.
     */
    protected int len;

    /** 
     * Creates a new input stream with the specified decompressor and
     * buffer size.
     * @param in the input stream
     * @param inf the decompressor ("inflater")
     * @param size the input buffer size
     * @exception IllegalArgumentException if size is <= 0
     */
    public InflaterInputStream(InputStream in, Inflater inf, int size) { 
        super(in);
    }

    /** 
     * Creates a new input stream with the specified decompressor and a
     * default buffer size.
     * @param in the input stream
     * @param inf the decompressor ("inflater")
     */
    public InflaterInputStream(InputStream in, Inflater inf) { 
        super(in);    
    }

    /** 
     * Creates a new input stream with a default decompressor and buffer size.
     * @param in the input stream
     */
    public InflaterInputStream(InputStream in) { 
        super(in);    
    }

    /** 
     * Reads a byte of uncompressed data. This method will block until
     * enough input is available for decompression.
     * @return the byte read, or -1 if end of compressed input is reached
     * @exception IOException if an I/O error has occurred
     */
    public int read() throws IOException {
        return 0;
    }

    /** 
     * Reads uncompressed data into an array of bytes. This method will
     * block until some input can be decompressed.
     * @param b the buffer into which the data is read
     * @param off the start offset of the data
     * @param len the maximum number of bytes read
     * @return the actual number of bytes read, or -1 if the end of the
     *         compressed input is reached or a preset dictionary is needed
     * @exception ZipException if a ZIP format error has occurred
     * @exception IOException if an I/O error has occurred
     */
    public int read(byte[] b, int off, int len) throws IOException {
        return 0;
    }

    /** 
     * Returns 0 after EOF has reached, otherwise always return 1.
     * <p>
     * Programs should not count on this method to return the actual number
     * of bytes that could be read without blocking.
     *
     * @return     1 before EOF and 0 after EOF.
     * @exception  IOException  if an I/O error occurs.
     * 
     */
    public int available() throws IOException {
        return 0;
    }

    /** 
     * Skips specified number of bytes of uncompressed data.
     * @param n the number of bytes to skip
     * @return the actual number of bytes skipped.
     * @exception IOException if an I/O error has occurred
     * @exception IllegalArgumentException if n < 0
     */
    public long skip(long n) throws IOException {
        return -1;
    }

    /** 
     * Closes the input stream.
     * @exception IOException if an I/O error has occurred
     */
    public void close() throws IOException { }

    /** 
     * Fills input buffer with more data to decompress.
     * @exception IOException if an I/O error has occurred
     */
    protected void fill() throws IOException { }
}
