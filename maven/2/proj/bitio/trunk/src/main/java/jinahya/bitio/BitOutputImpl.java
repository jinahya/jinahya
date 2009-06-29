package jinahya.bitio;


import java.io.IOException;
import java.io.OutputStream;


/**
 *
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class BitOutputImpl implements BitOutput {


    public static interface ByteOutput {
        public void writeByte(int b) throws IOException;
    }


    public BitOutputImpl(final OutputStream out) {
        this(new ByteOutput() {
                public void writeByte(int b) throws IOException {
                    out.write(b);
                }
            });
    }


    public BitOutputImpl(ByteOutput output) {
        super();

        this.output = output;

        index = 0x00;
        octet = 0x00;

        count = 0L;
    }


    private static final int[] powers  = new int[] {
        0x01, // 1   0000 0001
        0x03, // 3   0000 0011
        0x07, // 7   0000 0111
        0x0F, // 15  0000 1111
        0x1F, // 31  0001 1111
        0x3F, // 63  0011 1111
        0x7F, // 127 0111 1111
        0xFF  // 255 1111 1111
    };


    protected void writeUnsignedByte(int length, int value) throws IOException {

        if (length < 1 || length >= 8) {
            throw new IllegalArgumentException("illegal length: " + length);
        }

        int available = 0x08 - index;
        if (available >= length) {
            octet |= ((value & powers[length - 1]) << (available - length));
            count += length;
            if ((index += length) == 0x08) {
                output.writeByte(octet);
                index = 0x00;
                octet = 0x00;
            }
            return;
        }
        int required = length - available;
        writeUnsignedByte(available, value >> required); // high available bits
        writeUnsignedByte(required, value); // low required bits
    }


    protected void writeUnsignedShort(int length, int value)
        throws IOException {

        if (length < 1 || length >= 16) {
            throw new IllegalArgumentException("illegal length: " + length);
        }

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
    public void writeUnsignedInt(int length, int value) throws IOException {

        if (length < 1 || length >= 32) {
            throw new IllegalArgumentException("illegal length: " + length);
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
    public void writeInt(int length, int value) throws IOException {

        if (length <= 1 || length > 32) {
            throw new IllegalArgumentException("illegal length: " + length);
        }

        writeUnsignedByte(1, (value < 0 ? 0x01 : 0x00)); // sign bit
        writeUnsignedInt(length - 1, value);
    }


    /** {@inheritDoc} */
    public void writeUnsignedLong(int length, long value) throws IOException {

        if (length < 1 || length >= 64) {
            throw new IllegalArgumentException("illegal length: " + length);
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


    /** {@inheritDoc} */
    public void writeLong(int length, long value) throws IOException {

        if (length <= 1 || length > 64) {
            throw new IllegalArgumentException("illegal length: " + length);
        }

        writeUnsignedByte(1, (value < 0L ? 0x01 : 0x00)); // sign bit
        writeUnsignedLong(length - 1, value);
    }


    public long getCount() {
        return count;
    }


    public void setCount(long count) {
        this.count = count;
    }


    private ByteOutput output;

    private int index;
    private byte octet;

    private long count;
}