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
 * This class implements a character buffer that can be used as an Writer.
 * The buffer automatically grows when data is written to the stream.  The data
 * can be retrieved using toCharArray() and toString().
 *
 * @author	Herb Jellinek
 * @version 	1.15, 02/02/00
 * @since       JDK1.1
 */
public class CharArrayWriter extends Writer
{
    /** 
     * The buffer where data is stored.
     */
    protected char[] buf;

    /** 
     * The number of chars in the buffer.
     */
    protected int count;

    /** 
     * Creates a new CharArrayWriter.
     */
    public CharArrayWriter() { }

    /** 
     * Creates a new CharArrayWriter with the specified initial size.
     *
     * @param initialSize  an int specifying the initial buffer size.
     * @exception IllegalArgumentException if initialSize is negative
     */
    public CharArrayWriter(int initialSize) { }

    /** 
     * Writes a character to the buffer.
     */
    public void write(int c) { }

    /** 
     * Writes characters to the buffer.
     * @param c	the data to be written
     * @param off	the start offset in the data
     * @param len	the number of chars that are written
     */
    public void write(char[] c, int off, int len) { }

    /** 
     * Write a portion of a string to the buffer.
     * @param  str  String to be written from
     * @param  off  Offset from which to start reading characters
     * @param  len  Number of characters to be written
     */
    public void write(String str, int off, int len) { }

    /** 
     * Writes the contents of the buffer to another character stream.
     *
     * @param out	the output stream to write to
     * @throws IOException If an I/O error occurs.
     */
    public void writeTo(Writer out) throws IOException { }

    /** 
     * Resets the buffer so that you can use it again without
     * throwing away the already allocated buffer.
     */
    public void reset() { }

    /** 
     * Returns a copy of the input data.
     *
     * @return an array of chars copied from the input data.
     */
    public char[] toCharArray() {
        return null;
    }

    /** 
     * Returns the current size of the buffer.
     *
     * @return an int representing the current size of the buffer.
     */
    public int size() {
        return 0;
    }

    /** 
     * Converts input data to a string.
     * @return the string.
     */
    public String toString() {
        return null;
    }

    /** 
     * Flush the stream.
     */
    public void flush() { }

    /** 
     * Close the stream.  This method does not release the buffer, since its
     * contents might still be required.
     */
    public void close() { }
}
