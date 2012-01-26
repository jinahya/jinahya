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

import java.io.*;

/** 
 * A piped output stream can be connected to a piped input stream 
 * to create a communications pipe. The piped output stream is the 
 * sending end of the pipe. Typically, data is written to a 
 * <code>PipedOutputStream</code> object by one thread and data is 
 * read from the connected <code>PipedInputStream</code> by some 
 * other thread. Attempting to use both objects from a single thread 
 * is not recommended as it may deadlock the thread.
 *
 * @author  James Gosling
 * @version 1.23, 02/02/00
 * @see     java.io.PipedInputStream
 * @since   JDK1.0
 */
public class PipedOutputStream extends java.io.OutputStream
{

    /** 
     * Creates a piped output stream connected to the specified piped 
     * input stream. Data bytes written to this stream will then be 
     * available as input from <code>snk</code>.
     *
     * @param      snk   The piped input stream to connect to.
     * @exception  IOException  if an I/O error occurs.
     */
    public PipedOutputStream(java.io.PipedInputStream snk)
        throws java.io.IOException
    { }

    /** 
     * Creates a piped output stream that is not yet connected to a 
     * piped input stream. It must be connected to a piped input stream, 
     * either by the receiver or the sender, before being used. 
     *
     * @see     java.io.PipedInputStream#connect(java.io.PipedOutputStream)
     * @see     java.io.PipedOutputStream#connect(java.io.PipedInputStream)
     */
    public PipedOutputStream() { }

    /** 
     * Connects this piped output stream to a receiver. If this object
     * is already connected to some other piped input stream, an 
     * <code>IOException</code> is thrown.
     * <p>
     * If <code>snk</code> is an unconnected piped input stream and 
     * <code>src</code> is an unconnected piped output stream, they may 
     * be connected by either the call:
     * <blockquote><pre>
     * src.connect(snk)</pre></blockquote>
     * or the call:
     * <blockquote><pre>
     * snk.connect(src)</pre></blockquote>
     * The two calls have the same effect.
     *
     * @param      snk   the piped input stream to connect to.
     * @exception  IOException  if an I/O error occurs.
     */
    public synchronized void connect(java.io.PipedInputStream snk)
        throws java.io.IOException
    { }

    /** 
     * Writes the specified <code>byte</code> to the piped output stream. 
     * If a thread was reading data bytes from the connected piped input 
     * stream, but the thread is no longer alive, then an 
     * <code>IOException</code> is thrown.
     * <p>
     * Implements the <code>write</code> method of <code>OutputStream</code>.
     *
     * @param      b   the <code>byte</code> to be written.
     * @exception  IOException  if an I/O error occurs.
     */
    public void write(int b) throws java.io.IOException { }

    /** 
     * Writes <code>len</code> bytes from the specified byte array 
     * starting at offset <code>off</code> to this piped output stream. 
     * If a thread was reading data bytes from the connected piped input 
     * stream, but the thread is no longer alive, then an 
     * <code>IOException</code> is thrown.
     *
     * @param      b     the data.
     * @param      off   the start offset in the data.
     * @param      len   the number of bytes to write.
     * @exception  IOException  if an I/O error occurs.
     */
    public void write(byte[] b, int off, int len) throws java.io.IOException { }

    /** 
     * Flushes this output stream and forces any buffered output bytes 
     * to be written out. 
     * This will notify any readers that bytes are waiting in the pipe.
     *
     * @exception IOException if an I/O error occurs.
     */
    public synchronized void flush() throws java.io.IOException { }

    /** 
     * Closes this piped output stream and releases any system resources 
     * associated with this stream. This stream may no longer be used for 
     * writing bytes.
     *
     * @exception  IOException  if an I/O error occurs.
     */
    public void close() throws java.io.IOException { }
}
