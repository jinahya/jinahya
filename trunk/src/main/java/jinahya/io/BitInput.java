package jinahya.io;


import java.io.IOException;


/**
 *
 * @author Jin Kwon
 */
public interface BitInput {

    /**
     * Reads specified number of bits and returns as a single integer.
     * The <code>bitLength</code> must greater than 0 and less of equals to 32.
     * 
     * @param bitLength number of bits to read.
     * @return an integer holding specified bit sequence.
     * @throws IOException if an I/O error occurs.
     * @throws IllegalArgumentException if the bitLength is out of valid range.
     */
    public int readSignedInt(int bitLength) throws IOException;

    public int readUnsignedInt(int bitLength) throws IOException;

    public long readSignedLong(int bitLength) throws IOException;

    public long readUnsignedLong(int bitLength) throws IOException;

    public int getBitCount();

    public void setBitCount(int bitCount);
}