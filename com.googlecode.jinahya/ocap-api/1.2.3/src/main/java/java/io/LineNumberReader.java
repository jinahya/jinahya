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
 * A buffered character-input stream that keeps track of line numbers. 
 * This class defines methods <CODE>void setLineNumber(int)</CODE> and 
 * <CODE>int getLineNumber()</CODE> for setting and getting the current 
 * line number respectively. 
 * <P>
 * By default, line numbering begins at 0. This number increments as data is
 * read, and can be changed with a call to <CODE>setLineNumber(int)</CODE>. 
 * Note however, that <CODE>setLineNumber(int)</CODE> does not actually change the current
 * position in the stream; it only changes the value that will be returned
 * by <CODE>getLineNumber()</CODE>. 
 * <P>
 * A line is considered to be terminated by any one of a line feed ('\n'), a carriage
 * return ('\r'), or a carriage return followed immediately by a linefeed.
 *
 * @version 	1.13, 00/02/02
 * @author	Mark Reinhold
 * @since	JDK1.1
 */
public class LineNumberReader extends BufferedReader
{

    /** 
     * Create a new line-numbering reader, using the default input-buffer
     * size.
     *
     * @param in  a Reader object to provide the underlying stream.
     */
    public LineNumberReader(Reader in) { 
        super(in);
    }

    /** 
     * Create a new line-numbering reader, reading characters into a buffer of
     * the given size.
     *
     * @param in  a Reader object to provide the underlying stream.
     * @param sz  an int specifying the size of the buffer.
     */
    public LineNumberReader(Reader in, int sz) { 
        super(in, sz);
    }

    /** 
     * Set the current line number.
     *
     * @param lineNumber  an int specifying the line number.
     * @see #getLineNumber
     */
    public void setLineNumber(int lineNumber) { }

    /** 
     * Get the current line number.
     *
     * @return The current line number.
     * @see #setLineNumber
     */
    public int getLineNumber() {
        return 0;
    }

    /** 
     * Read a single character.  Line terminators are compressed into single
     * newline ('\n') characters.
     *
     * @return     The character read, or -1 if the end of the stream has been
     *             reached
     *
     * @exception  IOException  If an I/O error occurs
     */
    public int read() throws IOException {
        return 0;
    }

    /** 
     * Read characters into a portion of an array.
     *
     * @param      cbuf  Destination buffer
     * @param      off   Offset at which to start storing characters
     * @param      len   Maximum number of characters to read
     *
     * @return     The number of bytes read, or -1 if the end of the stream has
     *             already been reached
     *
     * @exception  IOException  If an I/O error occurs
     */
    public int read(char[] cbuf, int off, int len) throws IOException {
        return 0;
    }

    /** 
     * Read a line of text.  A line is considered to be terminated by any one
     * of a line feed ('\n'), a carriage return ('\r'), or a carriage return
     * followed immediately by a linefeed.
     *
     * @return     A String containing the contents of the line, not including
     *             any line-termination characters, or null if the end of the
     *             stream has been reached
     *
     * @exception  IOException  If an I/O error occurs
     */
    public String readLine() throws IOException {
        return null;
    }

    /** 
     * Skip characters.
     *
     * @param  n  The number of characters to skip
     *
     * @return    The number of characters actually skipped
     *
     * @exception  IOException  If an I/O error occurs
     */
    public long skip(long n) throws IOException {
        return -1;
    }

    /** 
     * Mark the present position in the stream.  Subsequent calls to reset()
     * will attempt to reposition the stream to this point, and will also reset
     * the line number appropriately.
     *
     * @param  readAheadLimit  Limit on the number of characters that may be
     *                         read while still preserving the mark.  After
     *                         reading this many characters, attempting to
     *                         reset the stream may fail.
     *
     * @exception  IOException  If an I/O error occurs
     */
    public void mark(int readAheadLimit) throws IOException { }

    /** 
     * Reset the stream to the most recent mark.
     *
     * @exception  IOException  If the stream has not been marked,
     *                          or if the mark has been invalidated
     */
    public void reset() throws IOException { }
}
