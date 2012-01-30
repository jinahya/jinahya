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
 * Piped character-input streams.
 *
 * @version 	1.12, 00/02/02
 * @author	Mark Reinhold
 * @since	JDK1.1
 */
public class PipedReader extends Reader
{

    /** 
     * Creates a <code>PipedReader</code> so
     * that it is connected to the piped writer
     * <code>src</code>. Data written to <code>src</code> 
     * will then be  available as input from this stream.
     *
     * @param      src   the stream to connect to.
     * @exception  IOException  if an I/O error occurs.
     */
    public PipedReader(PipedWriter src) throws IOException { }

    /** 
     * Creates a <code>PipedReader</code> so
     * that it is not  yet connected. It must be
     * connected to a <code>PipedWriter</code>
     * before being used.
     *
     * @see     java.io.PipedReader#connect(java.io.PipedWriter)
     * @see     java.io.PipedWriter#connect(java.io.PipedReader)
     */
    public PipedReader() { }

    /** 
     * Causes this piped reader to be connected
     * to the piped  writer <code>src</code>.
     * If this object is already connected to some
     * other piped writer, an <code>IOException</code>
     * is thrown.
     * <p>
     * If <code>src</code> is an
     * unconnected piped writer and <code>snk</code>
     * is an unconnected piped reader, they
     * may be connected by either the call:
     * <p>
     * <pre><code>snk.connect(src)</code> </pre> 
     * <p>
     * or the call:
     * <p>
     * <pre><code>src.connect(snk)</code> </pre> 
     * <p>
     * The two
     * calls have the same effect.
     *
     * @param      src   The piped writer to connect to.
     * @exception  IOException  if an I/O error occurs.
     */
    public void connect(PipedWriter src) throws IOException { }

    /** 
     * Reads the next character of data from this piped stream.
     * If no character is available because the end of the stream 
     * has been reached, the value <code>-1</code> is returned. 
     * This method blocks until input data is available, the end of
     * the stream is detected, or an exception is thrown. 
     *
     * If a thread was providing data characters
     * to the connected piped writer, but
     * the  thread is no longer alive, then an
     * <code>IOException</code> is thrown.
     *
     * @return     the next character of data, or <code>-1</code> if the end of the
     *             stream is reached.
     * @exception  IOException  if the pipe is broken.
     */
    public synchronized int read() throws IOException {
        return 0;
    }

    /** 
     * Reads up to <code>len</code> characters of data from this piped
     * stream into an array of characters. Less than <code>len</code> characters
     * will be read if the end of the data stream is reached. This method 
     * blocks until at least one character of input is available. 
     * If a thread was providing data characters to the connected piped output, 
     * but the thread is no longer alive, then an <code>IOException</code> 
     * is thrown.
     *
     * @param      cbuf     the buffer into which the data is read.
     * @param      off   the start offset of the data.
     * @param      len   the maximum number of characters read.
     * @return     the total number of characters read into the buffer, or
     *             <code>-1</code> if there is no more data because the end of
     *             the stream has been reached.
     * @exception  IOException  if an I/O error occurs.
     */
    public synchronized int read(char[] cbuf, int off, int len)
        throws IOException
    {
        return 0;
    }

    /** 
     * Tell whether this stream is ready to be read.  A piped character
     * stream is ready if the circular buffer is not empty.
     *
     * @exception  IOException  If an I/O error occurs
     */
    public synchronized boolean ready() throws IOException {
        return false;
    }

    /** 
     * Closes this piped stream and releases any system resources 
     * associated with the stream. 
     *
     * @exception  IOException  if an I/O error occurs.
     */
    public void close() throws IOException { }
}
