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


  


package java.io;

/** 
 * This class implements a character buffer that can be used as a
 * character-input stream.
 *
 * @author	Herb Jellinek
 * @version 	1.15, 02/02/00
 * @since       JDK1.1
 */
public class CharArrayReader extends Reader
{
    /** The character buffer.  */
    protected char[] buf;

    /** The current buffer position.  */
    protected int pos;

    /** The position of mark in buffer.  */
    protected int markedPos;

    /** 
     *  The index of the end of this buffer.  There is not valid
     *  data at or beyond this index.
     */
    protected int count;

    /** 
     * Create an CharArrayReader from the specified array of chars.
     * @param buf	Input buffer (not copied)
     */
    public CharArrayReader(char[] buf) { }

    /** 
     * Create an CharArrayReader from the specified array of chars.
     * @param buf	Input buffer (not copied)
     * @param offset    Offset of the first char to read
     * @param length	Number of chars to read
     */
    public CharArrayReader(char[] buf, int offset, int length) { }

    /** 
     * Read a single character.
     * 
     * @exception   IOException  If an I/O error occurs
     */
    public int read() throws IOException {
        return 0;
    }

    /** 
     * Read characters into a portion of an array.
     * @param b	 Destination buffer
     * @param off  Offset at which to start storing characters
     * @param len   Maximum number of characters to read
     * @return  The actual number of characters read, or -1 if
     * 		the end of the stream has been reached
     * 
     * @exception   IOException  If an I/O error occurs
     */
    public int read(char[] b, int off, int len) throws IOException {
        return 0;
    }

    /** 
     * Skip characters.
     * @param n The number of characters to skip
     * @return	The number of characters actually skipped
     * 
     * @exception   IOException  If an I/O error occurs
     */
    public long skip(long n) throws IOException {
        return -1;
    }

    /** 
     * Tell whether this stream is ready to be read.  Character-array readers
     * are always ready to be read.
     *
     * @exception  IOException  If an I/O error occurs
     */
    public boolean ready() throws IOException {
        return false;
    }

    /** 
     * Tell whether this stream supports the mark() operation, which it does.
     */
    public boolean markSupported() {
        return false;
    }

    /** 
     * Mark the present position in the stream.  Subsequent calls to reset()
     * will reposition the stream to this point.
     *
     * @param  readAheadLimit  Limit on the number of characters that may be
     *                         read while still preserving the mark.  Because
     *                         the stream's input comes from a character array,
     *                         there is no actual limit; hence this argument is
     *                         ignored.
     *
     * @exception  IOException  If an I/O error occurs
     */
    public void mark(int readAheadLimit) throws IOException { }

    /** 
     * Reset the stream to the most recent mark, or to the beginning if it has
     * never been marked.
     *
     * @exception  IOException  If an I/O error occurs
     */
    public void reset() throws IOException { }

    /** 
     * Close the stream.
     */
    public void close() { }
}
