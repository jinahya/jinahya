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
 * A character stream that collects its output in a string buffer, which can
 * then be used to construct a string.
 * <p>
 * Closing a <tt>StringWriter</tt> has no effect. The methods in this class
 * can be called after the stream has been closed without generating an
 * <tt>IOException</tt>.
 *
 * @version 	1.18, 00/02/02
 * @author	Mark Reinhold
 * @since	JDK1.1
 */
public class StringWriter extends Writer
{

    /** 
     * Create a new string writer, using the default initial string-buffer
     * size.
     */
    public StringWriter() { }

    /** 
     * Create a new string writer, using the specified initial string-buffer
     * size.
     *
     * @param initialSize  an int specifying the initial size of the buffer.
     */
    public StringWriter(int initialSize) { }

    /** 
     * Write a single character.
     */
    public void write(int c) { }

    /** 
     * Write a portion of an array of characters.
     *
     * @param  cbuf  Array of characters
     * @param  off   Offset from which to start writing characters
     * @param  len   Number of characters to write
     */
    public void write(char[] cbuf, int off, int len) { }

    /** 
     * Write a string.
     */
    public void write(String str) { }

    /** 
     * Write a portion of a string.
     *
     * @param  str  String to be written
     * @param  off  Offset from which to start writing characters
     * @param  len  Number of characters to write
     */
    public void write(String str, int off, int len) { }

    /** 
     * Return the buffer's current value as a string.
     */
    public String toString() {
        return null;
    }

    /** 
     * Return the string buffer itself.
     *
     * @return StringBuffer holding the current buffer value.
     */
    public StringBuffer getBuffer() {
        return null;
    }

    /** 
     * Flush the stream.
     */
    public void flush() { }

    /** 
     * Closing a <tt>StringWriter</tt> has no effect. The methods in this
     * class can be called after the stream has been closed without generating
     * an <tt>IOException</tt>.
     */
    public void close() throws IOException { }
}
