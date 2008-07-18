package jinahya.io;


import java.io.EOFException;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;


/**
 * @author Jin Kwon
 */
public class BitInputStream extends FilterInputStream implements BitInput {


    /**
     * @param in
     */
    public BitInputStream(InputStream in) {
        super(in);

        index = 8;
        octet = 0xFFFFFFFF;

        count = 0L;
    }


    /** {@inheritDoc} */
    //@Override
    public void close() throws IOException {
        if (index != 8) {
            throw new IllegalStateException("not octet-aligned");
        }
        super.close();
    }


    /**
     */
    public void finish() {
        if (index != 8) {
            throw new IllegalStateException("not octet-aligned");
        }
        in = null;
    }


    /** {@inheritDoc} */
    //@Override
    public int read() throws IOException {
        if (index != 8) {
            throw new IllegalStateException("not octet-aligned");
        }
        try {
            return readUnsignedShort(8);
        } catch (EOFException eofe) {
            return -1;
        }
    }


    /** {@inheritDoc} */
    //@Override
    public int read(byte[] b) throws IOException {
        return read(b, 0, b.length); // for sure
        //return super.read(b);
    }


    /** {@inheritDoc} */
    //@Override
    public int read(byte[] b, int off, int len) throws IOException {
        if (off < 0 || len < 0 || len > b.length - off) {
            throw new IndexOutOfBoundsException("doc");
        }
        if (index != 8) {
            throw new IllegalStateException("not octet-aligned");
        }
        for (int i = off; i < len; i++) {
            try {
                b[i] = (byte)readUnsignedShort(8);
            } catch (EOFException eofe) {
                return (i == off ? -1 : i);
            }
        }
        return len;
    }


    /**
     * @param length
     * @return
     * @exception IllegalArgumentException
     * @exception IOException
     */
    private int readUnsignedByte(int length) throws IOException {
        if (length < 1 || length >= 8) {
            throw new IllegalArgumentException("unacceptable: " + length);
        }
        if (index == 8) {
            if ((octet = in.read()) == -1) {
                throw new EOFException("EOF");
            }
            index = 0;
        }
        int value = 0;
        int available = 8 - index;
        if (available >= length) {
            value = (octet << index) >>> (8 - length);
            index += length;
            return value;
        }
        int required = length - available;
        value = readUnsignedByte(available) << required;
        value |= readUnsignedByte(required);
        return value;
    }


    private int readUnsignedShort(int length) throws IOException {
        int value = 0;
        //int dividend = length;
        //int divisor = 7;
        int quotient = length / 7;

        for (int i = 0; i < quotient; i++) {
            value <<= 7;
            value |= readUnsignedByte(7);
        }
        int remainder = length % 7;
        if (remainder > 0) {
            value <<= remainder;
            value |= readUnsignedByte(remainder);
        }
        return value;
    }


    /** {@inheritDoc} */
    public int readInt(int length) throws IOException {
        int shift = length - 1;
        return 0 - (readUnsignedByte(1) << shift) + readUnsignedInt(shift);
    }


    /** {@inheritDoc} */
    public int readUnsignedInt(int length) throws IOException {
        if (length < 1 || length >=32) {
            throw new IllegalArgumentException("unacceptable: " + length);
        }
        int value = 0;
        //int dividend = length;
        //int divisor = 15;
        int quotient = length / 15;
        for (int i = 0; i < quotient; i++) {
            value <<= 15;
            value |= readUnsignedShort(15);
        }
        int remainder = length % 15;
        if (remainder > 0) {
            value <<= remainder;
            value |= readUnsignedShort(remainder);
        }
        return value;
    }


    /** {@inheritDoc} */
    public long readLong(int length) throws IOException {
        int shift = length - 1;
        return 0L - (readUnsignedByte(1) << shift) + readUnsignedLong(shift);
    }


    /** {@inheritDoc} */
    public long readUnsignedLong(int length) throws IOException {
        if (length < 1 || length >= 64) {
            throw new IllegalArgumentException("unacceptalbe: " + length);
        }
        long value = 0L;
        //int dividend = length;
        //int divisor = 31;
        int quotient = length / 31;
        for (int i = 0; i < quotient; i++) {
            value <<= 31;
            value |= readUnsignedInt(31);
        }
        int remainder = length % 31;
        if (remainder > 0) {
            value <<= remainder;
            value |= readUnsignedInt(remainder);
        }
        return value;
    }


    public long getCount() { return count; }


    public void setCount(long count) { this.count = count; }


    private byte index;
    private int octet;

    private long count;
}