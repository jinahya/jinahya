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
 * A data output stream lets an application write primitive Java data 
 * types to an output stream in a portable way. An application can 
 * then use a data input stream to read the data back in. 
 *
 * @author  unascribed
 * @version 1.34 10/17/00
 * @see     java.io.DataInputStream
 * @since   JDK1.0
 */
public class DataOutputStream extends FilterOutputStream implements DataOutput
{
    /** 
     * The number of bytes written to the data output stream so far. 
     * If this counter overflows, it will be wrapped to Integer.MAX_VALUE.
     */
    protected int written;

    /** 
     * Creates a new data output stream to write data to the specified 
     * underlying output stream. The counter <code>written</code> is 
     * set to zero.
     *
     * @param   out   the underlying output stream, to be saved for later 
     *                use.
     * @see     java.io.FilterOutputStream#out
     */
    public DataOutputStream(OutputStream out) { 
        super(out);
    }

    /** 
     * Writes the specified byte (the low eight bits of the argument 
     * <code>b</code>) to the underlying output stream. If no exception 
     * is thrown, the counter <code>written</code> is incremented by 
     * <code>1</code>.
     * <p>
     * Implements the <code>write</code> method of <code>OutputStream</code>.
     *
     * @param      b   the <code>byte</code> to be written.
     * @exception  IOException  if an I/O error occurs.
     * @see        java.io.FilterOutputStream#out
     */
    public synchronized void write(int b) throws IOException { }

    /** 
     * Writes <code>len</code> bytes from the specified byte array 
     * starting at offset <code>off</code> to the underlying output stream. 
     * If no exception is thrown, the counter <code>written</code> is 
     * incremented by <code>len</code>.
     *
     * @param      b     the data.
     * @param      off   the start offset in the data.
     * @param      len   the number of bytes to write.
     * @exception  IOException  if an I/O error occurs.
     * @see        java.io.FilterOutputStream#out
     */
    public synchronized void write(byte[] b, int off, int len)
        throws IOException
    { }

    /** 
     * Flushes this data output stream. This forces any buffered output 
     * bytes to be written out to the stream. 
     * <p>
     * The <code>flush</code> method of <code>DataOuputStream</code> 
     * calls the <code>flush</code> method of its underlying output stream.
     *
     * @exception  IOException  if an I/O error occurs.
     * @see        java.io.FilterOutputStream#out
     * @see        java.io.OutputStream#flush()
     */
    public void flush() throws IOException { }

    /** 
     * Writes a <code>boolean</code> to the underlying output stream as 
     * a 1-byte value. The value <code>true</code> is written out as the 
     * value <code>(byte)1</code>; the value <code>false</code> is 
     * written out as the value <code>(byte)0</code>. If no exception is 
     * thrown, the counter <code>written</code> is incremented by 
     * <code>1</code>.
     *
     * @param      v   a <code>boolean</code> value to be written.
     * @exception  IOException  if an I/O error occurs.
     * @see        java.io.FilterOutputStream#out
     */
    public final void writeBoolean(boolean v) throws IOException { }

    /** 
     * Writes out a <code>byte</code> to the underlying output stream as 
     * a 1-byte value. If no exception is thrown, the counter 
     * <code>written</code> is incremented by <code>1</code>.
     *
     * @param      v   a <code>byte</code> value to be written.
     * @exception  IOException  if an I/O error occurs.
     * @see        java.io.FilterOutputStream#out
     */
    public final void writeByte(int v) throws IOException { }

    /** 
     * Writes a <code>short</code> to the underlying output stream as two
     * bytes, high byte first. If no exception is thrown, the counter 
     * <code>written</code> is incremented by <code>2</code>.
     *
     * @param      v   a <code>short</code> to be written.
     * @exception  IOException  if an I/O error occurs.
     * @see        java.io.FilterOutputStream#out
     */
    public final void writeShort(int v) throws IOException { }

    /** 
     * Writes a <code>char</code> to the underlying output stream as a 
     * 2-byte value, high byte first. If no exception is thrown, the 
     * counter <code>written</code> is incremented by <code>2</code>.
     *
     * @param      v   a <code>char</code> value to be written.
     * @exception  IOException  if an I/O error occurs.
     * @see        java.io.FilterOutputStream#out
     */
    public final void writeChar(int v) throws IOException { }

    /** 
     * Writes an <code>int</code> to the underlying output stream as four
     * bytes, high byte first. If no exception is thrown, the counter 
     * <code>written</code> is incremented by <code>4</code>.
     *
     * @param      v   an <code>int</code> to be written.
     * @exception  IOException  if an I/O error occurs.
     * @see        java.io.FilterOutputStream#out
     */
    public final void writeInt(int v) throws IOException { }

    /** 
     * Writes a <code>long</code> to the underlying output stream as eight
     * bytes, high byte first. In no exception is thrown, the counter 
     * <code>written</code> is incremented by <code>8</code>.
     *
     * @param      v   a <code>long</code> to be written.
     * @exception  IOException  if an I/O error occurs.
     * @see        java.io.FilterOutputStream#out
     */
    public final void writeLong(long v) throws IOException { }

    /** 
     * Converts the float argument to an <code>int</code> using the 
     * <code>floatToIntBits</code> method in class <code>Float</code>, 
     * and then writes that <code>int</code> value to the underlying 
     * output stream as a 4-byte quantity, high byte first. If no 
     * exception is thrown, the counter <code>written</code> is 
     * incremented by <code>4</code>.
     *
     * @param      v   a <code>float</code> value to be written.
     * @exception  IOException  if an I/O error occurs.
     * @see        java.io.FilterOutputStream#out
     * @see        java.lang.Float#floatToIntBits(float)
     */
    public final void writeFloat(float v) throws IOException { }

    /** 
     * Converts the double argument to a <code>long</code> using the 
     * <code>doubleToLongBits</code> method in class <code>Double</code>, 
     * and then writes that <code>long</code> value to the underlying 
     * output stream as an 8-byte quantity, high byte first. If no 
     * exception is thrown, the counter <code>written</code> is 
     * incremented by <code>8</code>.
     *
     * @param      v   a <code>double</code> value to be written.
     * @exception  IOException  if an I/O error occurs.
     * @see        java.io.FilterOutputStream#out
     * @see        java.lang.Double#doubleToLongBits(double)
     */
    public final void writeDouble(double v) throws IOException { }

    /** 
     * Writes out the string to the underlying output stream as a 
     * sequence of bytes. Each character in the string is written out, in 
     * sequence, by discarding its high eight bits. If no exception is 
     * thrown, the counter <code>written</code> is incremented by the 
     * length of <code>s</code>.
     *
     * @param      s   a string of bytes to be written.
     * @exception  IOException  if an I/O error occurs.
     * @see        java.io.FilterOutputStream#out
     */
    public final void writeBytes(String s) throws IOException { }

    /** 
     * Writes a string to the underlying output stream as a sequence of 
     * characters. Each character is written to the data output stream as 
     * if by the <code>writeChar</code> method. If no exception is 
     * thrown, the counter <code>written</code> is incremented by twice 
     * the length of <code>s</code>.
     *
     * @param      s   a <code>String</code> value to be written.
     * @exception  IOException  if an I/O error occurs.
     * @see        java.io.DataOutputStream#writeChar(int)
     * @see        java.io.FilterOutputStream#out
     */
    public final void writeChars(String s) throws IOException { }

    /** 
     * Writes a string to the underlying output stream using Java modified
     * UTF-8 encoding in a machine-independent manner. 
     * <p>
     * First, two bytes are written to the output stream as if by the 
     * <code>writeShort</code> method giving the number of bytes to 
     * follow. This value is the number of bytes actually written out, 
     * not the length of the string. Following the length, each character 
     * of the string is output, in sequence, using the modified UTF-8 encoding
     * for the character. If no exception is thrown, the counter 
     * <code>written</code> is incremented by the total number of 
     * bytes written to the output stream. This will be at least two 
     * plus the length of <code>str</code>, and at most two plus 
     * thrice the length of <code>str</code>.
     *
     * @param      str   a string to be written.
     * @exception  IOException  if an I/O error occurs.
     */
    public final void writeUTF(String str) throws IOException { }

    /** 
     * Returns the current value of the counter <code>written</code>, 
     * the number of bytes written to this data output stream so far.
     * If the counter overflows, it will be wrapped to Integer.MAX_VALUE.
     *
     * @return  the value of the <code>written</code> field.
     * @see     java.io.DataOutputStream#written
     */
    public final int size() {
        return 0;
    }
}
