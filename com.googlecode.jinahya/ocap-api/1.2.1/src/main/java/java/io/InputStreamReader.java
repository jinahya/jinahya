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
 * An InputStreamReader is a bridge from byte streams to character streams: It
 * reads bytes and translates them into characters according to a specified <a
 * href="../lang/package-summary.html#charenc">character encoding</a>.  The
 * encoding that it uses may be specified by name, or the platform's default
 * encoding may be accepted.
 *
 * <p> Each invocation of one of an InputStreamReader's read() methods may
 * cause one or more bytes to be read from the underlying byte-input stream.
 * To enable the efficient conversion of bytes to characters, more bytes may
 * be read ahead from the underlying stream than are necessary to satisfy the
 * current read operation.
 *
 * <p> For top efficiency, consider wrapping an InputStreamReader within a
 * BufferedReader.  For example:
 *
 * <pre>
 * BufferedReader in
 *   = new BufferedReader(new InputStreamReader(System.in));
 * </pre>
 *
 * @see BufferedReader
 * @see InputStream
 * @see <a href="../lang/package-summary.html#charenc">Character encodings</a>
 *
 * @version 	1.25, 00/02/02
 * @author	Mark Reinhold
 * @since	JDK1.1
 */
public class InputStreamReader extends Reader
{

    /** 
     * Create an InputStreamReader that uses the default character encoding.
     *
     * @param  in   An InputStream
     */
    public InputStreamReader(InputStream in) { }

    /** 
     * Create an InputStreamReader that uses the named character encoding.
     *
     * @param  in   An InputStream
     * @param  enc  The name of a supported
     *              <a href="../lang/package-summary.html#charenc">character
     *              encoding</a>
     *
     * @exception  UnsupportedEncodingException
     *             If the named encoding is not supported
     */
    public InputStreamReader(InputStream in, String enc)
        throws UnsupportedEncodingException
    { }

    /** 
     * Returns the canonical name of the character encoding being used by this
     * stream.  
     *
     * <p> If this instance was created with the {@link
     * #InputStreamReader(InputStream, String)} constructor then the returned
     * encoding name, being canonical, may differ from the encoding name passed 
     * to the constructor. This method may return <code>null</code> if the stream
     * has been closed. </p>
     *
     * <p> NOTE : In J2ME CDC, there is no concept of historical name, so only
     * canonical name of character encoding is returned.
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
     * Read a single character.
     *
     * @return The character read, or -1 if the end of the stream has been
     *         reached
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
     * @return     The number of characters read, or -1 if the end of the stream
     *             has been reached
     *
     * @exception  IOException  If an I/O error occurs
     */
    public int read(char[] cbuf, int off, int len) throws IOException {
        return 0;
    }

    /** 
     * Tell whether this stream is ready to be read.  An InputStreamReader is
     * ready if its input buffer is not empty, or if bytes are available to be
     * read from the underlying byte stream.
     *
     * @exception  IOException  If an I/O error occurs
     */
    public boolean ready() throws IOException {
        return false;
    }

    /** 
     * Close the stream.
     *
     * @exception  IOException  If an I/O error occurs
     */
    public void close() throws IOException { }
}
