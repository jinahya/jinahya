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
 * Piped character-output streams.
 *
 * @version 	1.13, 00/02/02
 * @author	Mark Reinhold
 * @since	JDK1.1
 */
public class PipedWriter extends Writer
{

    /** 
     * Creates a piped writer connected to the specified piped 
     * reader. Data characters written to this stream will then be 
     * available as input from <code>snk</code>.
     *
     * @param      snk   The piped reader to connect to.
     * @exception  IOException  if an I/O error occurs.
     */
    public PipedWriter(PipedReader snk) throws IOException { }

    /** 
     * Creates a piped writer that is not yet connected to a 
     * piped reader. It must be connected to a piped reader, 
     * either by the receiver or the sender, before being used. 
     *
     * @see     java.io.PipedReader#connect(java.io.PipedWriter)
     * @see     java.io.PipedWriter#connect(java.io.PipedReader)
     */
    public PipedWriter() { }

    /** 
     * Connects this piped writer to a receiver. If this object
     * is already connected to some other piped reader, an 
     * <code>IOException</code> is thrown.
     * <p>
     * If <code>snk</code> is an unconnected piped reader and 
     * <code>src</code> is an unconnected piped writer, they may 
     * be connected by either the call:
     * <blockquote><pre>
     * src.connect(snk)</pre></blockquote>
     * or the call:
     * <blockquote><pre>
     * snk.connect(src)</pre></blockquote>
     * The two calls have the same effect.
     *
     * @param      snk   the piped reader to connect to.
     * @exception  IOException  if an I/O error occurs.
     */
    public synchronized void connect(PipedReader snk) throws IOException { }

    /** 
     * Writes the specified <code>char</code> to the piped output stream. 
     * If a thread was reading data characters from the connected piped input 
     * stream, but the thread is no longer alive, then an 
     * <code>IOException</code> is thrown.
     * <p>
     * Implements the <code>write</code> method of <code>Writer</code>.
     *
     * @param      c   the <code>char</code> to be written.
     * @exception  IOException  if an I/O error occurs.
     */
    public void write(int c) throws IOException { }

    /** 
     * Writes <code>len</code> characters from the specified character array 
     * starting at offset <code>off</code> to this piped output stream. 
     * If a thread was reading data characters from the connected piped input 
     * stream, but the thread is no longer alive, then an 
     * <code>IOException</code> is thrown.
     *
     * @param      cbuf  the data.
     * @param      off   the start offset in the data.
     * @param      len   the number of characters to write.
     * @exception  IOException  if an I/O error occurs.
     */
    public void write(char[] cbuf, int off, int len) throws IOException { }

    /** 
     * Flushes this output stream and forces any buffered output characters 
     * to be written out. 
     * This will notify any readers that characters are waiting in the pipe.
     *
     * @exception IOException if an I/O error occurs.
     */
    public synchronized void flush() throws IOException { }

    /** 
     * Closes this piped output stream and releases any system resources 
     * associated with this stream. This stream may no longer be used for 
     * writing characters.
     *
     * @exception  IOException  if an I/O error occurs.
     */
    public void close() throws IOException { }
}
