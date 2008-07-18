package jinahya.io;


import java.io.IOException;


/**
 * @author Jin Kwon
 */
public interface BitInput {

    /**
     * @param length
     * @exception IllegalArgumentException
     * @exception IOException
     */
    public int readInt(int length) throws IOException;

    /**
     */
    public int readUnsignedInt(int length) throws IOException;

    /**
     */
    public long readLong(int length) throws IOException;

    /**
     */
    public long readUnsignedLong(int length) throws IOException;
}