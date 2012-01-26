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

/** 
 * This class provides support for general purpose compression using the
 * popular ZLIB compression library. The ZLIB compression library was
 * initially developed as part of the PNG graphics standard and is not
 * protected by patents. It is fully described in the specifications at 
 * the <a href="package-summary.html#package_description">java.util.zip
 * package description</a>.
 *
 * <p>The following code fragment demonstrates a trivial compression
 * and decompression of a string using <tt>Deflater</tt> and
 * <tt>Inflater</tt>.
 *
 * <blockquote><pre>
 * // Encode a String into bytes
 * String inputString = "blahblahblah??";
 * byte[] input = inputString.getBytes("UTF-8");
 *
 * // Compress the bytes
 * byte[] output = new byte[100];
 * Deflater compresser = new Deflater();
 * compresser.setInput(input);
 * compresser.finish();
 * int compressedDataLength = compresser.deflate(output);
 *
 * // Decompress the bytes
 * Inflater decompresser = new Inflater();
 * decompresser.setInput(output, 0, compressedDataLength);
 * byte[] result = new byte[100];
 * int resultLength = decompresser.inflate(result);
 * decompresser.end();
 *
 * // Decode the bytes into a String
 * String outputString = new String(result, 0, resultLength, "UTF-8");
 * </pre></blockquote>
 * 
 * @see		Inflater
 * @version 	1.35, 05/03/00
 * @author 	David Connelly
 */
public class Deflater
{
    /** 
     * Compression method for the deflate algorithm (the only one currently
     * supported).
     */
    public static final int DEFLATED = 8;

    /** 
     * Compression level for no compression.
     */
    public static final int NO_COMPRESSION = 0;

    /** 
     * Compression level for fastest compression.
     */
    public static final int BEST_SPEED = 1;

    /** 
     * Compression level for best compression.
     */
    public static final int BEST_COMPRESSION = 9;

    /** 
     * Default compression level.
     */
    public static final int DEFAULT_COMPRESSION = -1;

    /** 
     * Compression strategy best used for data consisting mostly of small
     * values with a somewhat random distribution. Forces more Huffman coding
     * and less string matching.
     */
    public static final int FILTERED = 1;

    /** 
     * Compression strategy for Huffman coding only.
     */
    public static final int HUFFMAN_ONLY = 2;

    /** 
     * Default compression strategy.
     */
    public static final int DEFAULT_STRATEGY = 0;

    /** 
     * Creates a new compressor using the specified compression level.
     * If 'nowrap' is true then the ZLIB header and checksum fields will
     * not be used in order to support the compression format used in
     * both GZIP and PKZIP.
     * @param level the compression level (0-9)
     * @param nowrap if true then use GZIP compatible compression
     */
    public Deflater(int level, boolean nowrap) { }

    /** 
     * Creates a new compressor using the specified compression level.
     * Compressed data will be generated in ZLIB format.
     * @param level the compression level (0-9)
     */
    public Deflater(int level) { }

    /** 
     * Creates a new compressor with the default compression level.
     * Compressed data will be generated in ZLIB format.
     */
    public Deflater() { }

    /** 
     * Sets input data for compression. This should be called whenever
     * needsInput() returns true indicating that more input data is required.
     * @param b the input data bytes
     * @param off the start offset of the data
     * @param len the length of the data
     * @see Deflater#needsInput
     */
    public synchronized void setInput(byte[] b, int off, int len) { }

    /** 
     * Sets input data for compression. This should be called whenever
     * needsInput() returns true indicating that more input data is required.
     * @param b the input data bytes
     * @see Deflater#needsInput
     */
    public void setInput(byte[] b) { }

    /** 
     * Sets preset dictionary for compression. A preset dictionary is used
     * when the history buffer can be predetermined. When the data is later
     * uncompressed with Inflater.inflate(), Inflater.getAdler() can be called
     * in order to get the Adler-32 value of the dictionary required for
     * decompression.
     * @param b the dictionary data bytes
     * @param off the start offset of the data
     * @param len the length of the data
     * @see Inflater#inflate
     * @see Inflater#getAdler
     */
    public synchronized void setDictionary(byte[] b, int off, int len) { }

    /** 
     * Sets preset dictionary for compression. A preset dictionary is used
     * when the history buffer can be predetermined. When the data is later
     * uncompressed with Inflater.inflate(), Inflater.getAdler() can be called
     * in order to get the Adler-32 value of the dictionary required for
     * decompression.
     * @param b the dictionary data bytes
     * @see Inflater#inflate
     * @see Inflater#getAdler
     */
    public void setDictionary(byte[] b) { }

    /** 
     * Sets the compression strategy to the specified value.
     * @param strategy the new compression strategy
     * @exception IllegalArgumentException if the compression strategy is
     *				           invalid
     */
    public synchronized void setStrategy(int strategy) { }

    /** 
     * Sets the current compression level to the specified value.
     * @param level the new compression level (0-9)
     * @exception IllegalArgumentException if the compression level is invalid
     */
    public synchronized void setLevel(int level) { }

    /** 
     * Returns true if the input data buffer is empty and setInput()
     * should be called in order to provide more input.
     * @return true if the input data buffer is empty and setInput()
     * should be called in order to provide more input
     */
    public boolean needsInput() {
        return false;
    }

    /** 
     * When called, indicates that compression should end with the current
     * contents of the input buffer.
     */
    public synchronized void finish() { }

    /** 
     * Returns true if the end of the compressed data output stream has
     * been reached.
     * @return true if the end of the compressed data output stream has
     * been reached
     */
    public synchronized boolean finished() {
        return false;
    }

    /** 
     * Fills specified buffer with compressed data. Returns actual number
     * of bytes of compressed data. A return value of 0 indicates that
     * needsInput() should be called in order to determine if more input
     * data is required.
     * @param b the buffer for the compressed data
     * @param off the start offset of the data
     * @param len the maximum number of bytes of compressed data
     * @return the actual number of bytes of compressed data
     */
    public synchronized int deflate(byte[] b, int off, int len) {
        return 0;
    }

    /** 
     * Fills specified buffer with compressed data. Returns actual number
     * of bytes of compressed data. A return value of 0 indicates that
     * needsInput() should be called in order to determine if more input
     * data is required.
     * @param b the buffer for the compressed data
     * @return the actual number of bytes of compressed data
     */
    public int deflate(byte[] b) {
        return 0;
    }

    /** 
     * Returns the ADLER-32 value of the uncompressed data.
     * @return the ADLER-32 value of the uncompressed data
     */
    public synchronized int getAdler() {
        return 0;
    }

    /** 
     * Returns the total number of bytes input so far.
     * @return the total number of bytes input so far
     */
    public synchronized int getTotalIn() {
        return 0;
    }

    /** 
     * Returns the total number of bytes output so far.
     * @return the total number of bytes output so far
     */
    public synchronized int getTotalOut() {
        return 0;
    }

    /** 
     * Resets deflater so that a new set of input data can be processed.
     * Keeps current compression level and strategy settings.
     */
    public synchronized void reset() { }

    /** 
     * Closes the compressor and discards any unprocessed input.
     * This method should be called when the compressor is no longer
     * being used, but will also be called automatically by the
     * finalize() method. Once this method is called, the behavior
     * of the Deflater object is undefined.
     */
    public synchronized void end() { }

    /** 
     * Closes the compressor when garbage is collected.
     */
    protected void finalize() { }
}
