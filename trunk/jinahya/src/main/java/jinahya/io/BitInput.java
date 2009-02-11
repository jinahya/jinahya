package jinahya.io;


import java.io.IOException;


/**
 * @author Jin Kwon
 */
public interface BitInput {

    /**
     * Reads a signed integer.
     *
     * @param length number of bits
     * @exception IllegalArgumentException length <= 0 || length > 32
     * @exception IOException if an I/O error occurs
     */
    public int readInt(int length) throws IOException;


    /**
     * Reads an unsigned integer.
     *
     * @param length number of bits
     * @throws IllegalArgumentException length <= 0 || length >= 32
     * @exception IOException if an I/O error occurs
     */
    public int readUnsignedInt(int length) throws IOException;


    /**
     * Reads a signed long.
     *
     * @param length number of bits
     * @exception IllegalArgumentException length <= 0 || length > 64
     * @exception IOException if an I/O error occurs
     */
    public long readLong(int length) throws IOException;


    /**
     * Reads an unsigned long.
     *
     * @param length number of bits
     * @exception IllegalArgumentException length <= 0 || length > 64

     * @exception IOException if an I/O error occurs
     */
    public long readUnsignedLong(int length) throws IOException;
}