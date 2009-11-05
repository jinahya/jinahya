package jinahya.bitio;


import java.io.IOException;
import java.io.OutputStream;


/**
 *
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class BitOutput {


    /**
     *
     * @param out
     */
    public BitOutput(final OutputStream out) {
        this(new ByteOutput() {
            public void writeByte(int b) throws IOException {
                out.write(b);
            }
        });
    }


    /**
     *
     * @param output
     */
    public BitOutput(final ByteOutput output) {
        super();

        this.output = output;
    }


    private static final int[] powers  = new int[] {
        0x01, // 1   0000 0001
        0x03, // 3   0000 0011
        0x07, // 7   0000 0111
        0x0F, // 15  0000 1111
        0x1F, // 31  0001 1111
        0x3F, // 63  0011 1111
        0x7F, // 127 0111 1111
    };


    /**
     *
     * @param value
     * @throws IOException
     */
    public void writeBoolean(boolean value) throws IOException {
        writeUnsignedByte(1, value ? 0x01 : 0x00);
    }


    public void writeUnsignedByte(final int length, final int value)
        throws IOException {

        if (avail >= length) {
            octet <<= length;
            octet |= (value & powers[length - 1]);
            if ((avail -= length) == 0) {
                output.writeByte(octet);
                octet = 0x00;
                avail = 0x08;
            }
        } else {
            int requi = length - avail;
            writeUnsignedByte(avail, value >> (requi));
            writeUnsignedByte(requi, value);
        }
    }


    public void writeUnsignedShort(final int length, final int value)
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


    /**
     *
     * @param length
     * @param value
     * @throws IOException
     */
    public void writeUnsignedInt(final int length, final int value)
        throws IOException {

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


    /**
     *
     * @param length
     * @param value
     * @throws IOException
     */
    public void writeInt(final int length, final int value) throws IOException {

        if (length <= 1 || length > 32) {
            throw new IllegalArgumentException("illegal length: " + length);
        }

        writeBoolean(value < 0);
        writeUnsignedInt(length - 1, value);
    }


    /**
     *
     * @param length
     * @param value
     * @throws IOException
     */
    public void writeUnsignedLong(final int length, final long value)
        throws IOException {

        if (length < 1 || length >= 64) {
            throw new IllegalArgumentException("illegal length: " + length);
        }

        int quotient = length / 31;
        int remainder = length % 31;
        if (remainder > 0) {
            writeUnsignedInt(remainder, (int) (value >>> (quotient * 31)));
        }
        for (int i = quotient - 1; i >= 0; i--) {
            writeUnsignedInt(31, (int) ((value >> (i * 31)) & 0x7FFFFFFFL));
        }
    }


    /**
     *
     * @param length
     * @param value
     * @throws IOException
     */
    public void writeLong(int length, long value) throws IOException {

        if (length <= 1 || length > 64) {
            throw new IllegalArgumentException("illegal length: " + length);
        }

        writeBoolean(value < 0L);
        writeUnsignedLong(length - 1, value);
    }


    /**
     *
     * @return
     */
    public long getCount() {
        return count;
    }


    /**
     *
     * @throws IOException
     */
    public void close() throws IOException {
        if (avail < 0x08) {
            writeUnsignedByte(avail, 0x00);
        }
    }


    private ByteOutput output;

    private int octet = 0x00;
    private int avail = 0x08;

    private long count = 0L;
}
