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
 * This class provides support for general purpose decompression using
 * popular ZLIB compression library. The ZLIB compression library was
 * initially developed as part of the PNG graphics standard and is not
 * protected by patents. It is fully described in the specifications at 
 * the <a href="package-summary.html#package_description">java.util.zip
 * package description</a>.
 *
 * <p>The following code fragment demonstrates a trivial compression
 * and decompression of a string using <tt>Deflater</tt> and
 * <tt>Inflater</tt>.
 * NOTE: <B>java.util.zip.Defalter</B> is found in J2ME CDC profiles such as
 * J2ME Foundation Profile.
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
 * NOTE: <B>java.util.zip.Defalter</B> is found in J2ME CDC profiles such as
 * J2ME Foundation Profile.
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
 * @see		Deflater
 * @version 	1.36, 05/03/00
 * @author 	David Connelly
 *
 */
public class Inflater
{

    /** 
     * Creates a new decompressor. If the parameter 'nowrap' is true then
     * the ZLIB header and checksum fields will not be used. This provides
     * compatibility with the compression format used by both GZIP and PKZIP.
     * <p>
     * Note: When using the 'nowrap' option it is also necessary to provide
     * an extra "dummy" byte as input. This is required by the ZLIB native
     * library in order to support certain optimizations.
     *
     * @param nowrap if true then support GZIP compatible compression
     */
    public Inflater(boolean nowrap) { }

    /** 
     * Creates a new decompressor.
     */
    public Inflater() { }

    /** 
     * Sets input data for decompression. Should be called whenever
     * needsInput() returns true indicating that more input data is
     * required.
     * @param b the input data bytes
     * @param off the start offset of the input data
     * @param len the length of the input data
     * @see Inflater#needsInput
     */
    public synchronized void setInput(byte[] b, int off, int len) { }

    /** 
     * Sets input data for decompression. Should be called whenever
     * needsInput() returns true indicating that more input data is
     * required.
     * @param b the input data bytes
     * @see Inflater#needsInput
     */
    public void setInput(byte[] b) { }

    /** 
     * Sets the preset dictionary to the given array of bytes. Should be
     * called when inflate() returns 0 and needsDictionary() returns true
     * indicating that a preset dictionary is required. The method getAdler()
     * can be used to get the Adler-32 value of the dictionary needed.
     * @param b the dictionary data bytes
     * @param off the start offset of the data
     * @param len the length of the data
     * @see Inflater#needsDictionary
     * @see Inflater#getAdler
     */
    public synchronized void setDictionary(byte[] b, int off, int len) { }

    /** 
     * Sets the preset dictionary to the given array of bytes. Should be
     * called when inflate() returns 0 and needsDictionary() returns true
     * indicating that a preset dictionary is required. The method getAdler()
     * can be used to get the Adler-32 value of the dictionary needed.
     * @param b the dictionary data bytes
     * @see Inflater#needsDictionary
     * @see Inflater#getAdler
     */
    public void setDictionary(byte[] b) { }

    /** 
     * Returns the total number of bytes remaining in the input buffer.
     * This can be used to find out what bytes still remain in the input
     * buffer after decompression has finished.
     * @return the total number of bytes remaining in the input buffer
     */
    public synchronized int getRemaining() {
        return 0;
    }

    /** 
     * Returns true if no data remains in the input buffer. This can
     * be used to determine if #setInput should be called in order
     * to provide more input.
     * @return true if no data remains in the input buffer
     */
    public synchronized boolean needsInput() {
        return false;
    }

    /** 
     * Returns true if a preset dictionary is needed for decompression.
     * @return true if a preset dictionary is needed for decompression
     * @see Inflater#setDictionary
     */
    public synchronized boolean needsDictionary() {
        return false;
    }

    /** 
     * Return true if the end of the compressed data stream has been
     * reached.
     * @return true if the end of the compressed data stream has been
     * reached
     */
    public synchronized boolean finished() {
        return false;
    }

    /** 
     * Uncompresses bytes into specified buffer. Returns actual number
     * of bytes uncompressed. A return value of 0 indicates that
     * needsInput() or needsDictionary() should be called in order to
     * determine if more input data or a preset dictionary is required.
     * In the later case, getAdler() can be used to get the Adler-32
     * value of the dictionary required.
     * @param b the buffer for the uncompressed data
     * @param off the start offset of the data
     * @param len the maximum number of uncompressed bytes
     * @return the actual number of uncompressed bytes
     * @exception DataFormatException if the compressed data format is invalid
     * @see Inflater#needsInput
     * @see Inflater#needsDictionary
     */
    public synchronized int inflate(byte[] b, int off, int len)
        throws DataFormatException
    {
        return 0;
    }

    /** 
     * Uncompresses bytes into specified buffer. Returns actual number
     * of bytes uncompressed. A return value of 0 indicates that
     * needsInput() or needsDictionary() should be called in order to
     * determine if more input data or a preset dictionary is required.
     * In the later case, getAdler() can be used to get the Adler-32
     * value of the dictionary required.
     * @param b the buffer for the uncompressed data
     * @return the actual number of uncompressed bytes
     * @exception DataFormatException if the compressed data format is invalid
     * @see Inflater#needsInput
     * @see Inflater#needsDictionary
     */
    public int inflate(byte[] b) throws DataFormatException {
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
     * Resets inflater so that a new set of input data can be processed.
     */
    public synchronized void reset() { }

    /** 
     * Closes the decompressor and discards any unprocessed input.
     * This method should be called when the decompressor is no longer
     * being used, but will also be called automatically by the finalize()
     * method. Once this method is called, the behavior of the Inflater
     * object is undefined.
     */
    public synchronized void end() { }

    /** 
     * Closes the decompressor when garbage is collected.
     */
    protected void finalize() { }
}
