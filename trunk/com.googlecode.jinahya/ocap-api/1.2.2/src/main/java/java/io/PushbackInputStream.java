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
 * A <code>PushbackInputStream</code> adds
 * functionality to another input stream, namely
 * the  ability to "push back" or "unread"
 * one byte. This is useful in situations where
 * it is  convenient for a fragment of code
 * to read an indefinite number of data bytes
 * that  are delimited by a particular byte
 * value; after reading the terminating byte,
 * the  code fragment can "unread" it, so that
 * the next read operation on the input stream
 * will reread the byte that was pushed back.
 * For example, bytes representing the  characters
 * constituting an identifier might be terminated
 * by a byte representing an  operator character;
 * a method whose job is to read just an identifier
 * can read until it  sees the operator and
 * then push the operator back to be re-read.
 *
 * @author  David Connelly
 * @author  Jonathan Payne
 * @version 1.31, 02/02/00
 * @since   JDK1.0
 */
public class PushbackInputStream extends FilterInputStream
{
    /** 
     * The pushback buffer.
     * @since   JDK1.1
     */
    protected byte[] buf;

    /** 
     * The position within the pushback buffer from which the next byte will
     * be read.  When the buffer is empty, <code>pos</code> is equal to
     * <code>buf.length</code>; when the buffer is full, <code>pos</code> is
     * equal to zero.
     *
     * @since   JDK1.1
     */
    protected int pos;

    /** 
     * Creates a <code>PushbackInputStream</code>
     * with a pushback buffer of the specified <code>size</code>,
     * and saves its  argument, the input stream
     * <code>in</code>, for later use. Initially,
     * there is no pushed-back byte  (the field
     * <code>pushBack</code> is initialized to
     * <code>-1</code>).
     *
     * @param  in    the input stream from which bytes will be read.
     * @param  size  the size of the pushback buffer.
     * @exception IllegalArgumentException if size is <= 0
     * @since  JDK1.1
     */
    public PushbackInputStream(InputStream in, int size) { 
        super(in);
    }

    /** 
     * Creates a <code>PushbackInputStream</code>
     * and saves its  argument, the input stream
     * <code>in</code>, for later use. Initially,
     * there is no pushed-back byte  (the field
     * <code>pushBack</code> is initialized to
     * <code>-1</code>).
     *
     * @param   in   the input stream from which bytes will be read.
     */
    public PushbackInputStream(InputStream in) { 
        super(in);    
    }

    /** 
     * Reads the next byte of data from this input stream. The value 
     * byte is returned as an <code>int</code> in the range 
     * <code>0</code> to <code>255</code>. If no byte is available 
     * because the end of the stream has been reached, the value 
     * <code>-1</code> is returned. This method blocks until input data 
     * is available, the end of the stream is detected, or an exception 
     * is thrown. 
     *
     * <p> This method returns the most recently pushed-back byte, if there is
     * one, and otherwise calls the <code>read</code> method of its underlying
     * input stream and returns whatever value that method returns.
     *
     * @return     the next byte of data, or <code>-1</code> if the end of the
     *             stream has been reached.
     * @exception  IOException  if an I/O error occurs.
     * @see        java.io.InputStream#read()
     */
    public int read() throws IOException {
        return 0;
    }

    /** 
     * Reads up to <code>len</code> bytes of data from this input stream into
     * an array of bytes.  This method first reads any pushed-back bytes; after
     * that, if fewer than than <code>len</code> bytes have been read then it
     * reads from the underlying input stream.  This method blocks until at
     * least 1 byte of input is available.
     *
     * @param      b     the buffer into which the data is read.
     * @param      off   the start offset of the data.
     * @param      len   the maximum number of bytes read.
     * @return     the total number of bytes read into the buffer, or
     *             <code>-1</code> if there is no more data because the end of
     *             the stream has been reached.
     * @exception  IOException  if an I/O error occurs.
     * @see        java.io.InputStream#read(byte[], int, int)
     */
    public int read(byte[] b, int off, int len) throws IOException {
        return 0;
    }

    /** 
     * Pushes back a byte by copying it to the front of the pushback buffer.
     * After this method returns, the next byte to be read will have the value
     * <code>(byte)b</code>.
     *
     * @param      b   the <code>int</code> value whose low-order 
     * 			byte is to be pushed back.
     * @exception IOException If there is not enough room in the pushback
     *			      buffer for the byte.
     */
    public void unread(int b) throws IOException { }

    /** 
     * Pushes back a portion of an array of bytes by copying it to the front
     * of the pushback buffer.  After this method returns, the next byte to be
     * read will have the value <code>b[off]</code>, the byte after that will
     * have the value <code>b[off+1]</code>, and so forth.
     *
     * @param b the byte array to push back.
     * @param off the start offset of the data.
     * @param len the number of bytes to push back.
     * @exception IOException If there is not enough room in the pushback
     *			      buffer for the specified number of bytes.
     * @since     JDK1.1
     */
    public void unread(byte[] b, int off, int len) throws IOException { }

    /** 
     * Pushes back an array of bytes by copying it to the front of the
     * pushback buffer.  After this method returns, the next byte to be read
     * will have the value <code>b[0]</code>, the byte after that will have the
     * value <code>b[1]</code>, and so forth.
     *
     * @param b the byte array to push back
     * @exception IOException If there is not enough room in the pushback
     *			      buffer for the specified number of bytes.
     * @since     JDK1.1
     */
    public void unread(byte[] b) throws IOException { }

    /** 
     * Returns the number of bytes that can be read from this input stream
     * without blocking.  This method calls the <code>available</code> method
     * of the underlying input stream; it returns that value plus the number of
     * bytes that have been pushed back.
     *
     * @return     the number of bytes that can be read from the input stream
     *             without blocking.
     * @exception  IOException  if an I/O error occurs.
     * @see        java.io.FilterInputStream#in
     * @see        java.io.InputStream#available()
     */
    public int available() throws IOException {
        return 0;
    }

    /** 
     * Skips over and discards <code>n</code> bytes of data from this 
     * input stream. The <code>skip</code> method may, for a variety of 
     * reasons, end up skipping over some smaller number of bytes, 
     * possibly zero.  If <code>n</code> is negative, no bytes are skipped.
     * 
     * <p> The <code>skip</code> method of <code>PushbackInputStream</code>
     * first skips over the bytes in the pushback buffer, if any.  It then
     * calls the <code>skip</code> method of the underlying input stream if
     * more bytes need to be skipped.  The actual number of bytes skipped
     * is returned.
     *
     * @param      n   the number of bytes to be skipped.
     * @return     the actual number of bytes skipped.
     * @exception  IOException  if an I/O error occurs.
     * @see        java.io.FilterInputStream#in
     * @see        java.io.InputStream#skip(long n)
     * @since      1.2
     */
    public long skip(long n) throws IOException {
        return -1;
    }

    /** 
     * Tests if this input stream supports the <code>mark</code> and
     * <code>reset</code> methods, which it does not.
     *
     * @return   <code>false</code>, since this class does not support the
     *           <code>mark</code> and <code>reset</code> methods.
     * @see     java.io.InputStream#mark(int)
     * @see     java.io.InputStream#reset()
     */
    public boolean markSupported() {
        return false;
    }

    /** 
     * Closes this input stream and releases any system resources 
     * associated with the stream. 
     *
     * @exception  IOException  if an I/O error occurs.
     */
    public synchronized void close() throws IOException { }
}
