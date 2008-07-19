package jinahya.io;


import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;


/**
 * @author Jin Kwon
 */
public class BitOutputStream extends FilterOutputStream implements BitOutput {


    private static final int[] powers = new int[8];
    //new int[] { 1, 2, 4, 8, 16, 32, 64, 128};

    static {
        powers[0] = 1;
        for (int i = 1; i < powers.length; i++) {
            powers[i] = (powers[i - 1] * 2);
        }
    }


    /**
     * @param out
     */
    public BitOutputStream(OutputStream out) {
        super(out);

        index = 0;
        octet = 0;

        count = 0L;
    }


    private void writeUnsignedByte(int length, int value)
        throws IOException {

        int available = 8 - index;
        if (available >= length) {
            octet |= ((value & powers[length]) << (available - length));
            index += length;
            count += length;
            if (index == 8) {
                out.write(octet);
                index = 0;
                octet = 0;
            }
            return;
        }

        int required = length - available;
        writeUnsignedByte(available, value >> required);
        writeUnsignedByte(required, value);
    }


    private void writeUnsignedShort(int length, int value)
        throws IOException {

        int quotient = length / 7;
        int remainder = length % 7;
        if (remainder > 0) {
            writeUnsignedByte(remainder, value >> (quotient * 7));
        }
        for (int i = quotient - 1; i >= 0; i--) {
            writeUnsignedByte(7, value >> (i * 7));
        }
    }


    /** {@inheritDoc} */
    public void writeInt(int length, int value) throws IOException {
        writeUnsignedByte(1, (value < 0 ? 0x01 : 0x00));
        writeUnsignedInt(length - 1, value);
    }


    /** {@inheritDoc} */
    public void writeUnsignedInt(int length, int value) throws IOException {
        if (length < 1 || length >= 32) {
            throw new IllegalArgumentException("unacceptable: " + length);
        }
        int quotient = length / 15;
        int remainder = length % 15;
        if (remainder > 0) {
            writeUnsignedShort(remainder, value >> (quotient * 15));
        }
        for (int i = quotient -1; i >= 0; i--) {
            writeUnsignedShort(15, value >> (i * 15));
        }
    }


    /** {@inheritDoc} */
    public void writeLong(int length, long value) throws IOException {
        writeUnsignedByte(1, (value < 0L ? 0x01 : 0x00));
        writeUnsignedLong(length - 1, value);
    }


    /** {@inheritDoc} */
    public void writeUnsignedLong(int length, long value) throws IOException {
        if (length < 1 || length >= 64) {
            throw new IllegalArgumentException("unacceptable: " + length);
        }
        int quotient = length / 31;
        int remainder = length % 31;
        if (remainder > 0) {
            writeUnsignedInt(remainder, (int)(value >> (quotient * 31)));
        }
        for (int i = quotient - 1; i >= 0; i--) {
            writeUnsignedInt(31, (int)(value >> (i * 31)));
        }
    }


    public long getCount() { return count; }


    private int index;
    private byte octet;

    private long count;
}