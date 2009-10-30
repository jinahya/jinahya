package jinahya.bitio;


import java.io.IOException;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public interface BitOutput {


    /**
     *
     * @param length
     * @param value
     * @throws IOException
     */
    public void writeInt(int length, int value) throws IOException;


    /**
     *
     * @param length
     * @param value
     * @throws IOException
     */
    public void writeUnsignedInt(int length, int value) throws IOException;


    /**
     *
     * @param length
     * @param value
     * @throws IOException
     */
    public void writeLong(int length, long value) throws IOException;


    /**
     *
     * @param length
     * @param value
     * @throws IOException
     */
    public void writeUnsignedLong(int length, long value) throws IOException;


    /**
     *
     * @return
     */
    public long getCount();


    /**
     *
     * @param count
     */
    public void setCount(long count);
}