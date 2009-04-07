package jinahya.bitio;


import java.io.IOException;


/**
 * An interface for reading bit level values.
 *
 * @author Jin Kwon
 */
public interface BitInput {


    /**
     * Reads a signed integer.
     *
     * @param length number of bits
     * @throws IllegalArgumentException length <= 0 || length > 32
     * @throws EOFException if there is no octet to use
     * @throws IOException if an I/O error occurs
     */
    public int readInt(int length) throws IOException;


    /**
     * Reads an unsigned integer.
     *
     * @param length number of bits
     * @throws IllegalArgumentException length <= 0 || length >= 32
     * @throws EOFException if there is no octet to use
     * @throws IOException if an I/O error occurs
     */
    public int readUnsignedInt(int length) throws IOException;


    /**
     * Reads a signed long.
     *
     * @param length number of bits
     * @throws IllegalArgumentException length <= 0 || length > 64
     * @throws EOFException if there is no octet to use
     * @throws IOException if an I/O error occurs
     */
    public long readLong(int length) throws IOException;


    /**
     * Reads an unsigned long.
     *
     * @param length number of bits
     * @throws IllegalArgumentException length <= 0 || length > 64
     * @throws EOFException if there is no octet to use
     * @throws IOException if an I/O error occurs
     */
    public long readUnsignedLong(int length) throws IOException;



    /**
     * Returns the number of bits consumed.
     *
     * @return number of bits
     */
    public long getCount();


    /**
     * Sets the number of bits consumed.
     *
     * @param count number of bits
     */
    public void setCount(long count);
}