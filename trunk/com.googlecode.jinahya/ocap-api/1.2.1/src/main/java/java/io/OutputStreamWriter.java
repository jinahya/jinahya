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
 * An OutputStreamWriter is a bridge from character streams to byte streams:
 * Characters written to it are translated into bytes according to a specified
 * <a href="../lang/package-summary.html#charenc">character encoding</a>.  The
 * encoding that it uses may be specified by name, or the platform's default
 * encoding may be accepted.
 *
 * <p> Each invocation of a write() method causes the encoding converter to be
 * invoked on the given character(s).  The resulting bytes are accumulated in a
 * buffer before being written to the underlying output stream.  The size of
 * this buffer may be specified, but by default it is large enough for most
 * purposes.  Note that the characters passed to the write() methods are not
 * buffered.
 *
 * <p> For top efficiency, consider wrapping an OutputStreamWriter within a
 * BufferedWriter so as to avoid frequent converter invocations.  For example:
 *
 * <pre>
 * Writer out
 *   = new BufferedWriter(new OutputStreamWriter(System.out));
 * </pre>
 *
 * <p> A <i>surrogate pair</i> is a character represented by a sequence of two
 * <tt>char</tt> values: A <i>high</i> surrogate in the range '&#92;uD800' to
 * '&#92;uDBFF' followed by a <i>low</i> surrogate in the range '&#92;uDC00' to
 * '&#92;uDFFF'.  If the character represented by a surrogate pair cannot be
 * encoded by a given encoding then a encoding-dependent <i>substitution
 * sequence</i> is written to the output stream.
 *
 * <p> A <i>malformed surrogate element</i> is a high surrogate that is not
 * followed by a low surrogate or a low surrogate that is not preceeded by a
 * high surrogate.  It is illegal to attempt to write a character stream
 * containing malformed surrogate elements.  The behavior of an instance of
 * this class when a malformed surrogate element is written is not specified.
 *
 * @see BufferedWriter
 * @see OutputStream
 * @see <a href="../lang/package-summary.html#charenc">Character encodings</a>
 *
 * @version 	1.28, 02/02/00
 * @author	Mark Reinhold
 * @since	JDK1.1
 */
public class OutputStreamWriter extends Writer
{

    /** 
     * Create an OutputStreamWriter that uses the named character encoding.
     *
     * @param  out  An OutputStream
     * @param  enc  The name of a supported
     *              <a href="../lang/package-summary.html#charenc">character
     *              encoding</a>
     *
     * @exception  UnsupportedEncodingException
     *             If the named encoding is not supported
     */
    public OutputStreamWriter(OutputStream out, String enc)
        throws UnsupportedEncodingException
    { }

    /** 
     * Create an OutputStreamWriter that uses the default character encoding.
     *
     * @param  out  An OutputStream
     */
    public OutputStreamWriter(OutputStream out) { }

    /** 
     * Returns the canonical name of the character encoding being used by this
     * stream.  
     * 
     * <p> If this instance was created with the {@link 
     * #OutputStreamWriter(OutputStream, String)} constructor then the returned
     * encoding name, being canonical, may differ from the encoding name passed
     * to the constructor. This method may return <code>null</code> if the stream
     * has been closed. </p>
     *
     * <p> NOTE : In J2ME CDC, there is no concept of historical name, so only
     * canonical name of character encoding is returned.  For a list of
     * acceptable canonical names of the character encoding see:
     * <a href="http://java.sun.com/j2se/1.4.2/docs/guide/intl/encoding.doc.html">http://java.sun.com/j2se/1.4.2/docs/guide/intl/encoding.doc.html</a>
     *
     * @return a String representing the encoding name, or possibly
     *         <code>null</code> if the stream has been closed
     *
     * @see <a href="../lang/package-summary.html#charenc">Character
     *      encodings</a>
     */
    public String getEncoding() {
        return null;
    }

    /** 
     * Write a single character.
     *
     * @exception  IOException  If an I/O error occurs
     */
    public void write(int c) throws IOException { }

    /** 
     * Write a portion of an array of characters.
     *
     * @param  cbuf  Buffer of characters
     * @param  off   Offset from which to start writing characters
     * @param  len   Number of characters to write
     *
     * @exception  IOException  If an I/O error occurs
     */
    public void write(char[] cbuf, int off, int len) throws IOException { }

    /** 
     * Write a portion of a string.
     *
     * @param  str  A String
     * @param  off  Offset from which to start writing characters
     * @param  len  Number of characters to write
     *
     * @exception  IOException  If an I/O error occurs
     */
    public void write(String str, int off, int len) throws IOException { }

    /** 
     * Flush the stream.
     *
     * @exception  IOException  If an I/O error occurs
     */
    public void flush() throws IOException { }

    /** 
     * Close the stream.
     *
     * @exception  IOException  If an I/O error occurs
     */
    public void close() throws IOException { }
}
