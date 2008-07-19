package jinahya.io;


import java.io.EOFException;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;


/**
 * @author Jin Kwon
 */
public class BitInputStream extends FilterInputStream implements BitInput {

    private static final int[] powers = new int[8];
    //new int[] { 1, 2, 4, 8, 16, 32, 64, 128};

    static {
        powers[0] = 1;
        for (int i = 1; i < powers.length; i++) {
            powers[i] = (powers[i - 1] * 2);
        }
    }


    /**
     * @param in
     */
    public BitInputStream(InputStream in) {
        super(in);

        index = 8;
        octet = -1;
        count = 0L;
    }


    private int readUnsignedByte(int length) throws IOException {
        if (index == 8) {
            if ((octet = (byte)in.read()) == -1) {
                throw new EOFException("EOF");
            }
            index = 0;
        }
        int value = 0;
        int available = 8 - index;
        if (available >= length) {
            value = (octet >>> (available - length)) & powers[length];
            index += length;
            count += length;
            return value;
        }
        int required = length - available;
        value = readUnsignedByte(available) << required;
        value |= readUnsignedByte(required);
        return value;
    }


    private int readUnsignedShort(int length) throws IOException {
        int value = 0;
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
        if (length < 1 || length >= 32) {
            throw new IllegalArgumentException("unacceptable: " + length);
        }
        int value = 0;
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


    private int index;
    private byte octet;

    private long count;
}