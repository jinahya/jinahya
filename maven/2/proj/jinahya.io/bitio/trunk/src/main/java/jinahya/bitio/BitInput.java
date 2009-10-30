package jinahya.bitio;


import java.io.IOException;


/**
 * An interface for read bits.
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public interface BitInput {


    /**
     *
     * @param length
     * @return
     * @throws IOException
     */
    public int readInt(int length) throws IOException;


    /**
     *
     * @param length
     * @return
     * @throws IOException
     */
    public int readUnsignedInt(int length) throws IOException;


    /**
     *
     * @param length
     * @return
     * @throws IOException
     */
    public long readLong(int length) throws IOException;


    /**
     *
     * @param length
     * @return
     * @throws IOException
     */
    public long readUnsignedLong(int length) throws IOException;


    /**
     * Returns the number of bits read.
     *
     * @return number of bits
     */
    public long getCount();


    /**
     * Sets the number of bits read.
     *
     * @param count
     */
    public void setCount(long count);
}