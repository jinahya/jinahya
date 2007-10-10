package jinahya.io;


import java.io.IOException;


/**
 *
 * @author Jin Kwon
 */
public interface BitOutput {

    public void writeSignedInt(int bitValue, int bitLength) throws IOException;

    public void writeUnsignedInt(int bitValue, int bitLength)
        throws IOException;

    public void writeSignedLong(long bitValue, int bitLength)
        throws IOException;

    public void writeUnsignedLong(long bitValue, int bitLength)
        throws IOException;

    public int getBitCount();

    public void setBitCount(int bitCount);
}
