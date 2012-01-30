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
 * This abstract class is the superclass of all classes representing 
 * an output stream of bytes. An output stream accepts output bytes 
 * and sends them to some sink.
 * <p>
 * Applications that need to define a subclass of 
 * <code>OutputStream</code> must always provide at least a method 
 * that writes one byte of output. 
 *
 * @author  Arthur van Hoff
 * @version 1.23, 02/02/00
 * @see     java.io.BufferedOutputStream
 * @see     java.io.ByteArrayOutputStream
 * @see     java.io.DataOutputStream
 * @see     java.io.FilterOutputStream
 * @see     java.io.InputStream
 * @see     java.io.OutputStream#write(int)
 * @since   JDK1.0
 */
public abstract class OutputStream
{

    public OutputStream() { }

    /** 
     * Writes the specified byte to this output stream. The general 
     * contract for <code>write</code> is that one byte is written 
     * to the output stream. The byte to be written is the eight 
     * low-order bits of the argument <code>b</code>. The 24 
     * high-order bits of <code>b</code> are ignored.
     * <p>
     * Subclasses of <code>OutputStream</code> must provide an 
     * implementation for this method. 
     *
     * @param      b   the <code>byte</code>.
     * @exception  IOException  if an I/O error occurs. In particular, 
     *             an <code>IOException</code> may be thrown if the 
     *             output stream has been closed.
     */
    public abstract void write(int b) throws IOException;

    /** 
     * Writes <code>b.length</code> bytes from the specified byte array 
     * to this output stream. The general contract for <code>write(b)</code> 
     * is that it should have exactly the same effect as the call 
     * <code>write(b, 0, b.length)</code>.
     *
     * @param      b   the data.
     * @exception  IOException  if an I/O error occurs.
     * @see        java.io.OutputStream#write(byte[], int, int)
     */
    public void write(byte[] b) throws IOException { }

    /** 
     * Writes <code>len</code> bytes from the specified byte array 
     * starting at offset <code>off</code> to this output stream. 
     * The general contract for <code>write(b, off, len)</code> is that 
     * some of the bytes in the array <code>b</code> are written to the 
     * output stream in order; element <code>b[off]</code> is the first 
     * byte written and <code>b[off+len-1]</code> is the last byte written 
     * by this operation.
     * <p>
     * The <code>write</code> method of <code>OutputStream</code> calls 
     * the write method of one argument on each of the bytes to be 
     * written out. Subclasses are encouraged to override this method and 
     * provide a more efficient implementation. 
     * <p>
     * If <code>b</code> is <code>null</code>, a 
     * <code>NullPointerException</code> is thrown.
     * <p>
     * If <code>off</code> is negative, or <code>len</code> is negative, or 
     * <code>off+len</code> is greater than the length of the array 
     * <code>b</code>, then an <tt>IndexOutOfBoundsException</tt> is thrown.
     *
     * @param      b     the data.
     * @param      off   the start offset in the data.
     * @param      len   the number of bytes to write.
     * @exception  IOException  if an I/O error occurs. In particular, 
     *             an <code>IOException</code> is thrown if the output 
     *             stream is closed.
     */
    public void write(byte[] b, int off, int len) throws IOException { }

    /** 
     * Flushes this output stream and forces any buffered output bytes 
     * to be written out. The general contract of <code>flush</code> is 
     * that calling it is an indication that, if any bytes previously 
     * written have been buffered by the implementation of the output 
     * stream, such bytes should immediately be written to their 
     * intended destination.
     * <p>
     * The <code>flush</code> method of <code>OutputStream</code> does nothing.
     *
     * @exception  IOException  if an I/O error occurs.
     */
    public void flush() throws IOException { }

    /** 
     * Closes this output stream and releases any system resources 
     * associated with this stream. The general contract of <code>close</code> 
     * is that it closes the output stream. A closed stream cannot perform 
     * output operations and cannot be reopened.
     * <p>
     * The <code>close</code> method of <code>OutputStream</code> does nothing.
     *
     * @exception  IOException  if an I/O error occurs.
     */
    public void close() throws IOException { }
}
