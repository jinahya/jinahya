package jinahya.io;


import java.io.IOException;


/**
 * @author Jin Kwon
 */
public interface BitOutput {


    /**
     * @param length
     * @param value
     * @exception IOException
     */
    public void writeInt(int length, int value) throws IOException;

    /**
     * @param length
     * @param value
     */
    public void writeUnsignedInt(int length, int value) throws IOException;

    /**
     */
    public void writeLong(int length, long value) throws IOException;


    /**
     */
    public void writeUnsignedLong(int length, long value) throws IOException;
}