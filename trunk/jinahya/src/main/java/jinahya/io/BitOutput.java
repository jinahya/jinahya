package jinahya.io;


import java.io.IOException;


/**
 * @author Jin Kwon
 */
public interface BitOutput {


    /**
     * Writes a signed int.
     *
     * @param length number of bits
     * @param value int value
     * @exception IllegalArgumentException
     * @exception IOException if an I/O error occurs
     */
    public void writeInt(int length, int value) throws IOException;


    /**
     * Writes an unsigned int.
     *
     * @param length
     * @param value
     * @exception IllegalArgumentException
     * @exception IOException if an I/O error occurs
     */
    public void writeUnsignedInt(int length, int value) throws IOException;


    /**
     * Writes an signed long
     *
     * @param length number of bits
     * @param value
     * @exception IllegalArgumentException
     * @exception IOException if an I/O error occurs
     */
    public void writeLong(int length, long value) throws IOException;


    /**
     * Writes an unsigned long
     *
     * @param length
     * @param value
     * @exception IllegalArgumentException
     * @exception IOException if an I/O error occurs
     */
    public void writeUnsignedLong(int length, long value) throws IOException;
}