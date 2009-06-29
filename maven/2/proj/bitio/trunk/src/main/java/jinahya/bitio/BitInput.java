package jinahya.bitio;


import java.io.IOException;


/**
 *
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public interface BitInput {


    public int readInt(int length) throws IOException;


    public int readUnsignedInt(int length) throws IOException;


    public long readLong(int length) throws IOException;


    public long readUnsignedLong(int length) throws IOException;


    public long getCount();


    public void setCount(long count);
}