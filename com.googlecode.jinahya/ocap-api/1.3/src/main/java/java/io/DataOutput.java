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
 * The <code>DataOutput</code> interface provides
 * for converting data from any of the Java
 * primitive types to a series of bytes and
 * writing these bytes to a binary stream.
 * There is  also a facility for converting
 * a <code>String</code> into Java modified
 * UTF-8 format and writing the resulting series
 * of bytes.
 * <p>
 * For all the methods in this interface that
 * write bytes, it is generally true that if
 * a byte cannot be written for any reason,
 * an <code>IOException</code> is thrown.
 *
 * @author  Frank Yellin
 * @version 1.13, 02/02/00
 * @see     java.io.DataInput
 * @see     java.io.DataOutputStream
 * @since   JDK1.0
 */
public interface DataOutput
{

    /** 
     * Writes to the output stream the eight
     * low-order bits of the argument <code>b</code>.
     * The 24 high-order  bits of <code>b</code>
     * are ignored.
     *
     * @param      b   the byte to be written.
     * @exception  IOException  if an I/O error occurs.
     */
    public void write(int b) throws IOException;

    /** 
     * Writes to the output stream all the bytes in array <code>b</code>.
     * If <code>b</code> is <code>null</code>,
     * a <code>NullPointerException</code> is thrown.
     * If <code>b.length</code> is zero, then
     * no bytes are written. Otherwise, the byte
     * <code>b[0]</code> is written first, then
     * <code>b[1]</code>, and so on; the last byte
     * written is <code>b[b.length-1]</code>.
     *
     * @param      b   the data.
     * @exception  IOException  if an I/O error occurs.
     */
    public void write(byte[] b) throws IOException;

    /** 
     * Writes <code>len</code> bytes from array
     * <code>b</code>, in order,  to
     * the output stream.  If <code>b</code>
     * is <code>null</code>, a <code>NullPointerException</code>
     * is thrown.  If <code>off</code> is negative,
     * or <code>len</code> is negative, or <code>off+len</code>
     * is greater than the length of the array
     * <code>b</code>, then an <code>IndexOutOfBoundsException</code>
     * is thrown.  If <code>len</code> is zero,
     * then no bytes are written. Otherwise, the
     * byte <code>b[off]</code> is written first,
     * then <code>b[off+1]</code>, and so on; the
     * last byte written is <code>b[off+len-1]</code>.
     *
     * @param      b     the data.
     * @param      off   the start offset in the data.
     * @param      len   the number of bytes to write.
     * @exception  IOException  if an I/O error occurs.
     */
    public void write(byte[] b, int off, int len) throws IOException;

    /** 
     * Writes a <code>boolean</code> value to this output stream.
     * If the argument <code>v</code>
     * is <code>true</code>, the value <code>(byte)1</code>
     * is written; if <code>v</code> is <code>false</code>,
     * the  value <code>(byte)0</code> is written.
     * The byte written by this method may
     * be read by the <code>readBoolean</code>
     * method of interface <code>DataInput</code>,
     * which will then return a <code>boolean</code>
     * equal to <code>v</code>.
     *
     * @param      v   the boolean to be written.
     * @exception  IOException  if an I/O error occurs.
     */
    public void writeBoolean(boolean v) throws IOException;

    /** 
     * Writes to the output stream the eight low-
     * order bits of the argument <code>v</code>.
     * The 24 high-order bits of <code>v</code>
     * are ignored. (This means  that <code>writeByte</code>
     * does exactly the same thing as <code>write</code>
     * for an integer argument.) The byte written
     * by this method may be read by the <code>readByte</code>
     * method of interface <code>DataInput</code>,
     * which will then return a <code>byte</code>
     * equal to <code>(byte)v</code>.
     *
     * @param      v   the byte value to be written.
     * @exception  IOException  if an I/O error occurs.
     */
    public void writeByte(int v) throws IOException;

    /** 
     * Writes two bytes to the output
     * stream to represent the value of the argument.
     * The byte values to be written, in the  order
     * shown, are: <p>
     * <pre><code>
     * (byte)(0xff &amp; (v &gt;&gt; 8))
     * (byte)(0xff &amp; v)
     * </code> </pre> <p>
     * The bytes written by this method may be
     * read by the <code>readShort</code> method
     * of interface <code>DataInput</code> , which
     * will then return a <code>short</code> equal
     * to <code>(short)v</code>.
     *
     * @param      v   the <code>short</code> value to be written.
     * @exception  IOException  if an I/O error occurs.
     */
    public void writeShort(int v) throws IOException;

    /** 
     * Writes a <code>char</code> value, wich
     * is comprised of two bytes, to the
     * output stream.
     * The byte values to be written, in the  order
     * shown, are:
     * <p><pre><code>
     * (byte)(0xff &amp; (v &gt;&gt; 8))
     * (byte)(0xff &amp; v)
     * </code></pre><p>
     * The bytes written by this method may be
     * read by the <code>readChar</code> method
     * of interface <code>DataInput</code> , which
     * will then return a <code>char</code> equal
     * to <code>(char)v</code>.
     *
     * @param      v   the <code>char</code> value to be written.
     * @exception  IOException  if an I/O error occurs.
     */
    public void writeChar(int v) throws IOException;

    /** 
     * Writes an <code>int</code> value, which is
     * comprised of four bytes, to the output stream.
     * The byte values to be written, in the  order
     * shown, are:
     * <p><pre><code>
     * (byte)(0xff &amp; (v &gt;&gt; 24))
     * (byte)(0xff &amp; (v &gt;&gt; 16))
     * (byte)(0xff &amp; (v &gt;&gt; &#32; &#32;8))
     * (byte)(0xff &amp; v)
     * </code></pre><p>
     * The bytes written by this method may be read
     * by the <code>readInt</code> method of interface
     * <code>DataInput</code> , which will then
     * return an <code>int</code> equal to <code>v</code>.
     *
     * @param      v   the <code>int</code> value to be written.
     * @exception  IOException  if an I/O error occurs.
     */
    public void writeInt(int v) throws IOException;

    /** 
     * Writes a <code>long</code> value, which is
     * comprised of eight bytes, to the output stream.
     * The byte values to be written, in the  order
     * shown, are:
     * <p><pre><code>
     * (byte)(0xff &amp; (v &gt;&gt; 56))
     * (byte)(0xff &amp; (v &gt;&gt; 48))
     * (byte)(0xff &amp; (v &gt;&gt; 40))
     * (byte)(0xff &amp; (v &gt;&gt; 32))
     * (byte)(0xff &amp; (v &gt;&gt; 24))
     * (byte)(0xff &amp; (v &gt;&gt; 16))
     * (byte)(0xff &amp; (v &gt;&gt;  8))
     * (byte)(0xff &amp; v)
     * </code></pre><p>
     * The bytes written by this method may be
     * read by the <code>readLong</code> method
     * of interface <code>DataInput</code> , which
     * will then return a <code>long</code> equal
     * to <code>v</code>.
     *
     * @param      v   the <code>long</code> value to be written.
     * @exception  IOException  if an I/O error occurs.
     */
    public void writeLong(long v) throws IOException;

    /** 
     * Writes a <code>float</code> value,
     * which is comprised of four bytes, to the output stream.
     * It does this as if it first converts this
     * <code>float</code> value to an <code>int</code>
     * in exactly the manner of the <code>Float.floatToIntBits</code>
     * method  and then writes the <code>int</code>
     * value in exactly the manner of the  <code>writeInt</code>
     * method.  The bytes written by this method
     * may be read by the <code>readFloat</code>
     * method of interface <code>DataInput</code>,
     * which will then return a <code>float</code>
     * equal to <code>v</code>.
     *
     * @param      v   the <code>float</code> value to be written.
     * @exception  IOException  if an I/O error occurs.
     */
    public void writeFloat(float v) throws IOException;

    /** 
     * Writes a <code>double</code> value,
     * which is comprised of eight bytes, to the output stream.
     * It does this as if it first converts this
     * <code>double</code> value to a <code>long</code>
     * in exactly the manner of the <code>Double.doubleToLongBits</code>
     * method  and then writes the <code>long</code>
     * value in exactly the manner of the  <code>writeLong</code>
     * method. The bytes written by this method
     * may be read by the <code>readDouble</code>
     * method of interface <code>DataInput</code>,
     * which will then return a <code>double</code>
     * equal to <code>v</code>.
     *
     * @param      v   the <code>double</code> value to be written.
     * @exception  IOException  if an I/O error occurs.
     */
    public void writeDouble(double v) throws IOException;

    /** 
     * Writes a string to the output stream.
     * For every character in the string
     * <code>s</code>,  taken in order, one byte
     * is written to the output stream.  If
     * <code>s</code> is <code>null</code>, a <code>NullPointerException</code>
     * is thrown.<p>  If <code>s.length</code>
     * is zero, then no bytes are written. Otherwise,
     * the character <code>s[0]</code> is written
     * first, then <code>s[1]</code>, and so on;
     * the last character written is <code>s[s.length-1]</code>.
     * For each character, one byte is written,
     * the low-order byte, in exactly the manner
     * of the <code>writeByte</code> method . The
     * high-order eight bits of each character
     * in the string are ignored.
     *
     * @param      s   the string of bytes to be written.
     * @exception  IOException  if an I/O error occurs.
     */
    public void writeBytes(String s) throws IOException;

    /** 
     * Writes every character in the string <code>s</code>,
     * to the output stream, in order,
     * two bytes per character. If <code>s</code>
     * is <code>null</code>, a <code>NullPointerException</code>
     * is thrown.  If <code>s.length</code>
     * is zero, then no characters are written.
     * Otherwise, the character <code>s[0]</code>
     * is written first, then <code>s[1]</code>,
     * and so on; the last character written is
     * <code>s[s.length-1]</code>. For each character,
     * two bytes are actually written, high-order
     * byte first, in exactly the manner of the
     * <code>writeChar</code> method.
     *
     * @param      s   the string value to be written.
     * @exception  IOException  if an I/O error occurs.
     */
    public void writeChars(String s) throws IOException;

    /** 
     * Writes two bytes of length information
     * to the output stream, followed
     * by the Java modified UTF representation
     * of  every character in the string <code>s</code>.
     * If <code>s</code> is <code>null</code>,
     * a <code>NullPointerException</code> is thrown.
     * Each character in the string <code>s</code>
     * is converted to a group of one, two, or
     * three bytes, depending on the value of the
     * character.<p>
     * If a character <code>c</code>
     * is in the range <code>&#92;u0001</code> through
     * <code>&#92;u007f</code>, it is represented
     * by one byte:<p>
     * <pre>(byte)c </pre>  <p>
     * If a character <code>c</code> is <code>&#92;u0000</code>
     * or is in the range <code>&#92;u0080</code>
     * through <code>&#92;u07ff</code>, then it is
     * represented by two bytes, to be written
     * in the order shown:<p> <pre><code>
     * (byte)(0xc0 | (0x1f &amp; (c &gt;&gt; 6)))
     * (byte)(0x80 | (0x3f &amp; c))
     *  </code></pre>  <p> If a character
     * <code>c</code> is in the range <code>&#92;u0800</code>
     * through <code>uffff</code>, then it is
     * represented by three bytes, to be written
     * in the order shown:<p> <pre><code>
     * (byte)(0xe0 | (0x0f &amp; (c &gt;&gt; 12)))
     * (byte)(0x80 | (0x3f &amp; (c &gt;&gt;  6)))
     * (byte)(0x80 | (0x3f &amp; c))
     *  </code></pre>  <p> First,
     * the total number of bytes needed to represent
     * all the characters of <code>s</code> is
     * calculated. If this number is larger than
     * <code>65535</code>, then a <code>UTFDataFormatException</code>
     * is thrown. Otherwise, this length is written
     * to the output stream in exactly the manner
     * of the <code>writeShort</code> method;
     * after this, the one-, two-, or three-byte
     * representation of each character in the
     * string <code>s</code> is written.<p>  The
     * bytes written by this method may be read
     * by the <code>readUTF</code> method of interface
     * <code>DataInput</code> , which will then
     * return a <code>String</code> equal to <code>s</code>.
     *
     * @param      str   the string value to be written.
     * @exception  IOException  if an I/O error occurs.
     */
    public void writeUTF(String str) throws IOException;
}
