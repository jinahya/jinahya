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
 * This class is the superclass of all classes that filter output 
 * streams. These streams sit on top of an already existing output 
 * stream (the <i>underlying</i> output stream) which it uses as its 
 * basic sink of data, but possibly transforming the data along the 
 * way or providing additional functionality. 
 * <p>
 * The class <code>FilterOutputStream</code> itself simply overrides 
 * all methods of <code>OutputStream</code> with versions that pass 
 * all requests to the underlying output stream. Subclasses of 
 * <code>FilterOutputStream</code> may further override some of these 
 * methods as well as provide additional methods and fields. 
 *
 * @author  Jonathan Payne
 * @version 1.28, 02/02/00
 * @since   JDK1.0
 */
public class FilterOutputStream extends OutputStream
{
    /** 
     * The underlying output stream to be filtered. 
     */
    protected OutputStream out;

    /** 
     * Creates an output stream filter built on top of the specified 
     * underlying output stream. 
     *
     * @param   out   the underlying output stream to be assigned to 
     *                the field <tt>this.out</tt> for later use, or 
     *                <code>null</code> if this instance is to be 
     *                created without an underlying stream.
     */
    public FilterOutputStream(OutputStream out) { 
    
    }

    /** 
     * Writes the specified <code>byte</code> to this output stream. 
     * <p>
     * The <code>write</code> method of <code>FilterOutputStream</code> 
     * calls the <code>write</code> method of its underlying output stream, 
     * that is, it performs <tt>out.write(b)</tt>.
     * <p>
     * Implements the abstract <tt>write</tt> method of <tt>OutputStream</tt>. 
     *
     * @param      b   the <code>byte</code>.
     * @exception  IOException  if an I/O error occurs.
     */
    public void write(int b) throws IOException { }

    /** 
     * Writes <code>b.length</code> bytes to this output stream. 
     * <p>
     * The <code>write</code> method of <code>FilterOutputStream</code> 
     * calls its <code>write</code> method of three arguments with the 
     * arguments <code>b</code>, <code>0</code>, and 
     * <code>b.length</code>. 
     * <p>
     * Note that this method does not call the one-argument 
     * <code>write</code> method of its underlying stream with the single 
     * argument <code>b</code>. 
     *
     * @param      b   the data to be written.
     * @exception  IOException  if an I/O error occurs.
     * @see        java.io.FilterOutputStream#write(byte[], int, int)
     */
    public void write(byte[] b) throws IOException { }

    /** 
     * Writes <code>len</code> bytes from the specified 
     * <code>byte</code> array starting at offset <code>off</code> to 
     * this output stream. 
     * <p>
     * The <code>write</code> method of <code>FilterOutputStream</code> 
     * calls the <code>write</code> method of one argument on each 
     * <code>byte</code> to output. 
     * <p>
     * Note that this method does not call the <code>write</code> method 
     * of its underlying input stream with the same arguments. Subclasses 
     * of <code>FilterOutputStream</code> should provide a more efficient 
     * implementation of this method. 
     *
     * @param      b     the data.
     * @param      off   the start offset in the data.
     * @param      len   the number of bytes to write.
     * @exception  IOException  if an I/O error occurs.
     * @see        java.io.FilterOutputStream#write(int)
     */
    public void write(byte[] b, int off, int len) throws IOException { }

    /** 
     * Flushes this output stream and forces any buffered output bytes 
     * to be written out to the stream. 
     * <p>
     * The <code>flush</code> method of <code>FilterOutputStream</code> 
     * calls the <code>flush</code> method of its underlying output stream. 
     *
     * @exception  IOException  if an I/O error occurs.
     * @see        java.io.FilterOutputStream#out
     */
    public void flush() throws IOException { }

    /** 
     * Closes this output stream and releases any system resources 
     * associated with the stream. 
     * <p>
     * The <code>close</code> method of <code>FilterOutputStream</code> 
     * calls its <code>flush</code> method, and then calls the 
     * <code>close</code> method of its underlying output stream. 
     *
     * @exception  IOException  if an I/O error occurs.
     * @see        java.io.FilterOutputStream#flush()
     * @see        java.io.FilterOutputStream#out
     */
    public void close() throws IOException { }
}
