package jinahya.bitio;


import java.io.IOException;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public interface BitOutput {


    public void writeInt(int length, int value) throws IOException;


    public void writeUnsignedInt(int length, int value) throws IOException;


    public void writeLong(int length, long value) throws IOException;


    public void writeUnsignedLong(int length, long value) throws IOException;


    public long getCount();


    public void setCount(long count);
}