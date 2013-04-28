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
 * A data input stream lets an application read primitive Java data
 * types from an underlying input stream in a machine-independent
 * way. An application uses a data output stream to write data that
 * can later be read by a data input stream.
 * <p>
 * Data input streams and data output streams represent Unicode
 * strings in a format that is a slight modification of UTF-8. (For
 * more information, see X/Open Company Ltd., "File System Safe
 * UCS Transformation Format (FSS_UTF)", X/Open Preliminary
 * Specification, Document Number: P316. This information also
 * appears in ISO/IEC 10646, Annex P.) Note that in the 
 * following tables, the most significant bit appears in the
 * far left-hand column.
 * <p>
 * All characters in the range <code>'&#92;u0001'</code> to
 * <code>'&#92;u007F'</code> are represented by a single byte:
 *
 * <center>
 *    <table border="3" summary="Bit values and bytes">
 *        <tr>
 *            <td></td>
 *            <th id="bit" colspan=2><P ALIGN="LEFT">Bit Values</P></th>
 *        </tr>
 *        <tr> 
 *            <th id="byte1">Byte 1&nbsp;</th>
 *            <td headers="bit byte1"><i>0</i></td>
 *            <td>bits 6-0</td>
 *        </tr>
 *     </table>
 * </center>
 *
 * <p>
 * The null character <code>'&#92;u0000'</code> and characters in the
 * range <code>'&#92;u0080'</code> to <code>'&#92;u07FF'</code> are
 * represented by a pair of bytes:
 *
 * <center>
 *     <table border="3" summary="Bit values and bytes">
 *        <tr>
 *            <td></td>
 *            <th id="bit" colspan=4><P ALIGN="LEFT">Bit Values</P></th>
 *        </tr>
 *         <tr>
 *             <th id="byte1">Byte 1&nbsp;</th>
 *             <td headers="bit byte1">1</td>
 *             <td headers="bit byte1">1</td>
 *             <td headers="bit byte1">0</td>
 *             <td headers="bit byte1">bits 10-6</td>
 *         </tr>
 *         <tr>
 *             <th id="byte2">Byte 2&nbsp;</th>
 *             <td headers="bit byte2">1</td>
 *             <td headers="bit byte2">0</td>
 *             <td headers="bit byte2" colspan=2>bits 5-0</td>
 *         </tr>
 *      </table>
 *  </center>
 *
 * <br>
 * Characters in the range <code>'&#92;u0800'</code> to
 * <code>'&#92;uFFFF'</code> are represented by three bytes:
 *
 * <center>
 *    <table border="3" summary="Bit values and bytes">
 *        <tr>
 *            <td></td>
 *            <th id="bit" colspan=5><P ALIGN="LEFT">Bit Values</P></th>
 *        </tr>
 * 
 *        <tr>
 *            <th id="byte1">Byte 1&nbsp;</th>
 *            <td headers="bit byte1">1</td>
 *            <td headers="bit byte1">1</td>
 *            <td headers="bit byte1">1</td>
 *            <td headers="bit byte1">0</td>
 *            <td headers="bit byte1">bits 15-12</td>
 *        </tr>
 *        <tr>
 *            <th id="byte2">Byte 2&nbsp;</th>
 *            <td headers="bit byte2">1</td>
 *            <td headers="bit byte2">0</td>
 *            <td headers="bit byte2" colspan=3>bits 11-6</td>
 *        </tr>
 *        <tr>
 *            <th id="byte3">Byte 3&nbsp;</th>
 *            <td headers="bit byte3">1</td>
 *            <td headers="bit byte3">0</td>
 *            <td headers="bit byte3" colspan=3>bits 5-0</td>
 *        </tr>
 *     </table>
 *   </center>
 * <p>
 * The two differences between this format and the
 * "standard" UTF-8 format are the following:
 * <ul>
 * <li>The null byte <code>'&#92;u0000'</code> is encoded in 2-byte format
 *     rather than 1-byte, so that the encoded strings never have
 *     embedded nulls.
 * <li>Only the 1-byte, 2-byte, and 3-byte formats are used.
 * </ul>
 *
 * @author  Arthur van Hoff
 * @version 1.52 10/17/00
 * @see     java.io.DataOutputStream
 * @since   JDK1.0
 */
public class DataInputStream extends FilterInputStream implements DataInput
{

    /** 
     * Creates a DataInputStream that uses the specified
     * underlying InputStream.
     *
     * @param  in   the specified input stream
     */
    public DataInputStream(InputStream in) { 
        super(in);
    }

    /** 
     * Reads some number of bytes from the contained input stream and 
     * stores them into the buffer array <code>b</code>. The number of 
     * bytes actually read is returned as an integer. This method blocks 
     * until input data is available, end of file is detected, or an 
     * exception is thrown. 
     * 
     * <p>If <code>b</code> is null, a <code>NullPointerException</code> is 
     * thrown. If the length of <code>b</code> is zero, then no bytes are 
     * read and <code>0</code> is returned; otherwise, there is an attempt 
     * to read at least one byte. If no byte is available because the 
     * stream is at end of file, the value <code>-1</code> is returned;
     * otherwise, at least one byte is read and stored into <code>b</code>. 
     * 
     * <p>The first byte read is stored into element <code>b[0]</code>, the 
     * next one into <code>b[1]</code>, and so on. The number of bytes read 
     * is, at most, equal to the length of <code>b</code>. Let <code>k</code> 
     * be the number of bytes actually read; these bytes will be stored in 
     * elements <code>b[0]</code> through <code>b[k-1]</code>, leaving 
     * elements <code>b[k]</code> through <code>b[b.length-1]</code> 
     * unaffected. 
     * 
     * <p>If the first byte cannot be read for any reason other than end of 
     * file, then an <code>IOException</code> is thrown. In particular, an 
     * <code>IOException</code> is thrown if the input stream has been closed. 
     * 
     * <p>The <code>read(b)</code> method has the same effect as: 
     * <blockquote><pre>
     * read(b, 0, b.length) 
     * </pre></blockquote>
     *
     * @param      b   the buffer into which the data is read.
     * @return     the total number of bytes read into the buffer, or
     *             <code>-1</code> if there is no more data because the end
     *             of the stream has been reached.
     * @exception  IOException  if an I/O error occurs.
     * @see        java.io.FilterInputStream#in
     * @see        java.io.InputStream#read(byte[], int, int)
     */
    public final int read(byte[] b) throws IOException {
        return 0;
    }

    /** 
     * Reads up to <code>len</code> bytes of data from the contained 
     * input stream into an array of bytes.  An attempt is made to read 
     * as many as <code>len</code> bytes, but a smaller number may be read, 
     * possibly zero. The number of bytes actually read is returned as an 
     * integer.
     *
     * <p> This method blocks until input data is available, end of file is
     * detected, or an exception is thrown.
     *
     * <p> If <code>b</code> is <code>null</code>, a
     * <code>NullPointerException</code> is thrown.
     *
     * <p> If <code>off</code> is negative, or <code>len</code> is negative, or
     * <code>off+len</code> is greater than the length of the array
     * <code>b</code>, then an <code>IndexOutOfBoundsException</code> is
     * thrown.
     *
     * <p> If <code>len</code> is zero, then no bytes are read and
     * <code>0</code> is returned; otherwise, there is an attempt to read at
     * least one byte. If no byte is available because the stream is at end of
     * file, the value <code>-1</code> is returned; otherwise, at least one
     * byte is read and stored into <code>b</code>.
     *
     * <p> The first byte read is stored into element <code>b[off]</code>, the
     * next one into <code>b[off+1]</code>, and so on. The number of bytes read
     * is, at most, equal to <code>len</code>. Let <i>k</i> be the number of
     * bytes actually read; these bytes will be stored in elements
     * <code>b[off]</code> through <code>b[off+</code><i>k</i><code>-1]</code>,
     * leaving elements <code>b[off+</code><i>k</i><code>]</code> through
     * <code>b[off+len-1]</code> unaffected.
     *
     * <p> In every case, elements <code>b[0]</code> through
     * <code>b[off]</code> and elements <code>b[off+len]</code> through
     * <code>b[b.length-1]</code> are unaffected.
     *
     * <p> If the first byte cannot be read for any reason other than end of
     * file, then an <code>IOException</code> is thrown. In particular, an
     * <code>IOException</code> is thrown if the input stream has been closed.
     *
     * @param      b     the buffer into which the data is read.
     * @param      off   the start offset of the data.
     * @param      len   the maximum number of bytes read.
     * @return     the total number of bytes read into the buffer, or
     *             <code>-1</code> if there is no more data because the end
     *             of the stream has been reached.
     * @exception  IOException  if an I/O error occurs.
     * @see        java.io.FilterInputStream#in
     * @see        java.io.InputStream#read(byte[], int, int)
     */
    public final int read(byte[] b, int off, int len) throws IOException {
        return 0;
    }

    /** 
     * See the general contract of the <code>readFully</code>
     * method of <code>DataInput</code>.
     * <p>
     * Bytes
     * for this operation are read from the contained
     * input stream.
     *
     * @param      b   the buffer into which the data is read.
     * @exception  EOFException  if this input stream reaches the end before
     *               reading all the bytes.
     * @exception  IOException   if an I/O error occurs.
     * @see        java.io.FilterInputStream#in
     */
    public final void readFully(byte[] b) throws IOException { }

    /** 
     * See the general contract of the <code>readFully</code>
     * method of <code>DataInput</code>.
     * <p>
     * Bytes
     * for this operation are read from the contained
     * input stream.
     *
     * @param      b     the buffer into which the data is read.
     * @param      off   the start offset of the data.
     * @param      len   the number of bytes to read.
     * @exception  EOFException  if this input stream reaches the end before
     *               reading all the bytes.
     * @exception  IOException   if an I/O error occurs.
     * @see        java.io.FilterInputStream#in
     */
    public final void readFully(byte[] b, int off, int len) throws IOException
    { }

    /** 
     * See the general contract of the <code>skipBytes</code>
     * method of <code>DataInput</code>.
     * <p>
     * Bytes
     * for this operation are read from the contained
     * input stream.
     *
     * @param      n   the number of bytes to be skipped.
     * @return     the actual number of bytes skipped.
     * @exception  IOException   if an I/O error occurs.
     */
    public final int skipBytes(int n) throws IOException {
        return 0;
    }

    /** 
     * See the general contract of the <code>readBoolean</code>
     * method of <code>DataInput</code>.
     * <p>
     * Bytes
     * for this operation are read from the contained
     * input stream.
     *
     * @return     the <code>boolean</code> value read.
     * @exception  EOFException  if this input stream has reached the end.
     * @exception  IOException   if an I/O error occurs.
     * @see        java.io.FilterInputStream#in
     */
    public final boolean readBoolean() throws IOException {
        return false;
    }

    /** 
     * See the general contract of the <code>readByte</code>
     * method of <code>DataInput</code>.
     * <p>
     * Bytes
     * for this operation are read from the contained
     * input stream.
     *
     * @return     the next byte of this input stream as a signed 8-bit
     *             <code>byte</code>.
     * @exception  EOFException  if this input stream has reached the end.
     * @exception  IOException   if an I/O error occurs.
     * @see        java.io.FilterInputStream#in
     */
    public final byte readByte() throws IOException {
        return ' ';
    }

    /** 
     * See the general contract of the <code>readUnsignedByte</code>
     * method of <code>DataInput</code>.
     * <p>
     * Bytes
     * for this operation are read from the contained
     * input stream.
     *
     * @return     the next byte of this input stream, interpreted as an
     *             unsigned 8-bit number.
     * @exception  EOFException  if this input stream has reached the end.
     * @exception  IOException   if an I/O error occurs.
     * @see         java.io.FilterInputStream#in
     */
    public final int readUnsignedByte() throws IOException {
        return 0;
    }

    /** 
     * See the general contract of the <code>readShort</code>
     * method of <code>DataInput</code>.
     * <p>
     * Bytes
     * for this operation are read from the contained
     * input stream.
     *
     * @return     the next two bytes of this input stream, interpreted as a
     *             signed 16-bit number.
     * @exception  EOFException  if this input stream reaches the end before
     *               reading two bytes.
     * @exception  IOException   if an I/O error occurs.
     * @see        java.io.FilterInputStream#in
     */
    public final short readShort() throws IOException {
        return -1;
    }

    /** 
     * See the general contract of the <code>readUnsignedShort</code>
     * method of <code>DataInput</code>.
     * <p>
     * Bytes
     * for this operation are read from the contained
     * input stream.
     *
     * @return     the next two bytes of this input stream, interpreted as an
     *             unsigned 16-bit integer.
     * @exception  EOFException  if this input stream reaches the end before
     *               reading two bytes.
     * @exception  IOException   if an I/O error occurs.
     * @see        java.io.FilterInputStream#in
     */
    public final int readUnsignedShort() throws IOException {
        return 0;
    }

    /** 
     * See the general contract of the <code>readChar</code>
     * method of <code>DataInput</code>.
     * <p>
     * Bytes
     * for this operation are read from the contained
     * input stream.
     *
     * @return     the next two bytes of this input stream as a Unicode
     *             character.
     * @exception  EOFException  if this input stream reaches the end before
     *               reading two bytes.
     * @exception  IOException   if an I/O error occurs.
     * @see        java.io.FilterInputStream#in
     */
    public final char readChar() throws IOException {
        return ' ';
    }

    /** 
     * See the general contract of the <code>readInt</code>
     * method of <code>DataInput</code>.
     * <p>
     * Bytes
     * for this operation are read from the contained
     * input stream.
     *
     * @return     the next four bytes of this input stream, interpreted as an
     *             <code>int</code>.
     * @exception  EOFException  if this input stream reaches the end before
     *               reading four bytes.
     * @exception  IOException   if an I/O error occurs.
     * @see        java.io.FilterInputStream#in
     */
    public final int readInt() throws IOException {
        return 0;
    }

    /** 
     * See the general contract of the <code>readLong</code>
     * method of <code>DataInput</code>.
     * <p>
     * Bytes
     * for this operation are read from the contained
     * input stream.
     *
     * @return     the next eight bytes of this input stream, interpreted as a
     *             <code>long</code>.
     * @exception  EOFException  if this input stream reaches the end before
     *               reading eight bytes.
     * @exception  IOException   if an I/O error occurs.
     * @see        java.io.FilterInputStream#in
     */
    public final long readLong() throws IOException {
        return -1;
    }

    /** 
     * See the general contract of the <code>readFloat</code>
     * method of <code>DataInput</code>.
     * <p>
     * Bytes
     * for this operation are read from the contained
     * input stream.
     *
     * @return     the next four bytes of this input stream, interpreted as a
     *             <code>float</code>.
     * @exception  EOFException  if this input stream reaches the end before
     *               reading four bytes.
     * @exception  IOException   if an I/O error occurs.
     * @see        java.io.DataInputStream#readInt()
     * @see        java.lang.Float#intBitsToFloat(int)
     */
    public final float readFloat() throws IOException {
        return 0.0f;
    }

    /** 
     * See the general contract of the <code>readDouble</code>
     * method of <code>DataInput</code>.
     * <p>
     * Bytes
     * for this operation are read from the contained
     * input stream.
     *
     * @return     the next eight bytes of this input stream, interpreted as a
     *             <code>double</code>.
     * @exception  EOFException  if this input stream reaches the end before
     *               reading eight bytes.
     * @exception  IOException   if an I/O error occurs.
     * @see        java.io.DataInputStream#readLong()
     * @see        java.lang.Double#longBitsToDouble(long)
     */
    public final double readDouble() throws IOException {
        return 0.0d;
    }

    /** 
     * See the general contract of the <code>readLine</code>
     * method of <code>DataInput</code>.
     * <p>
     * Bytes
     * for this operation are read from the contained
     * input stream.
     *
     * @deprecated This method does not properly convert bytes to characters.
     * As of JDK&nbsp;1.1, the preferred way to read lines of text is via the
     * <code>BufferedReader.readLine()</code> method.  Programs that use the
     * <code>DataInputStream</code> class to read lines can be converted to use
     * the <code>BufferedReader</code> class by replacing code of the form:
     * <blockquote><pre>
     *     DataInputStream d =&nbsp;new&nbsp;DataInputStream(in);
     * </pre></blockquote>
     * with:
     * <blockquote><pre>
     *     BufferedReader d
     *          =&nbsp;new&nbsp;BufferedReader(new&nbsp;InputStreamReader(in));
     * </pre></blockquote>
     *
     * @return     the next line of text from this input stream.
     * @exception  IOException  if an I/O error occurs.
     * @see        java.io.BufferedReader#readLine()
     * @see        java.io.FilterInputStream#in
     */
    public final String readLine() throws IOException {
        return null;
    }

    /** 
     * See the general contract of the <code>readUTF</code>
     * method of <code>DataInput</code>.
     * <p>
     * Bytes
     * for this operation are read from the contained
     * input stream.
     *
     * @return     a Unicode string.
     * @exception  EOFException  if this input stream reaches the end before
     *               reading all the bytes.
     * @exception  IOException   if an I/O error occurs.
     * @exception  UTFDataFormatException if the bytes do not represent a valid UTF-8 encoding of a string.
     * @see        java.io.DataInputStream#readUTF(java.io.DataInput)
     */
    public final String readUTF() throws IOException {
        return null;
    }

    /** 
     * Reads from the
     * stream <code>in</code> a representation
     * of a Unicode  character string encoded in
     * Java modified UTF-8 format; this string
     * of characters  is then returned as a <code>String</code>.
     * The details of the modified UTF-8 representation
     * are  exactly the same as for the <code>readUTF</code>
     * method of <code>DataInput</code>.
     *
     * @param      in   a data input stream.
     * @return     a Unicode string.
     * @exception  EOFException            if the input stream reaches the end
     *               before all the bytes.
     * @exception  IOException             if an I/O error occurs.
     * @exception  UTFDataFormatException  if the bytes do not represent a
     *               valid Java modified UTF-8 encoding of a Unicode string.
     * @see        java.io.DataInputStream#readUnsignedShort()
     */
    public static final String readUTF(DataInput in) throws IOException {
        return null;
    }
}
