package jinahya.bitio;


import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;


/**
 *
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class BitInput {


    /**
     *
     * @param in
     */
    public BitInput(final InputStream in) {
        this(new ByteInput() {
            public int readByte() throws IOException {
                return in.read();
            }
        });
    }


    /**
     *
     * @param input
     */
    public BitInput(final ByteInput input) {
        super();

        this.input = input;
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
     * @return
     * @throws IOException
     */
    public boolean readBoolean() throws IOException {
        return readUnsignedByte(1) == 0x01;
    }


    /**
     *
     * @param length
     * @return
     * @throws IOException
     */
    public int readUnsignedByte(int length) throws IOException {

        if (length < 1 || length >= 8) {
            throw new IllegalArgumentException("illegal length: " + length);
        }

        if (avail == 0x00) {
            if ((octet = input.readByte()) == -1) {
                throw new EOFException("EOF");
            }
            avail = 0x08;
        }

        if (avail >= length) {
            avail -= length;
            count += length;
            int value = (octet >> length);
            octet &= powers[avail - 1];
            return value;
        } else {
            int requi = length - avail;
            return ((readUnsignedByte(avail) << requi) |
                     readUnsignedByte(requi));
        }
    }


    /**
     *
     * @param length
     * @return
     * @throws IOException
     */
    public int readUnsignedShort(int length) throws IOException {

        if (length < 1 || length >= 16) {
            throw new IllegalArgumentException("illegal length: " + length);
        }

        int value = 0x00;

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


    /**
     *
     * @param length
     * @return
     * @throws IOException
     */
    public int readUnsignedInt(int length) throws IOException {

        if (length < 1 || length >= 32) {
            throw new IllegalArgumentException("illegal length: " + length);
       }

        int value = 0x00;

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


    /**
     *
     * @param length
     * @return
     * @throws IOException
     */
    public int readInt(int length) throws IOException {

        if (length <= 1 || length > 32) {
            throw new IllegalArgumentException("illegal length: " + length);
        }

        int value = (readBoolean() ? 0 : -1) << (length - 1);

        return value | readUnsignedInt(length -1);
    }


    /**
     *
     * @param length
     * @return
     * @throws IOException
     */
    public long readUnsignedLong(int length) throws IOException {

        if (length < 1 || length >= 64) {
            throw new IllegalArgumentException("illegal length: " + length);
        }

        long value = 0x00L;

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


    /**
     *
     * @param length
     * @return
     * @throws IOException
     */
    public long readLong(int length) throws IOException {

        if (length <= 1 || length > 64) {
            throw new IllegalArgumentException("illegal length: " + length);
        }

        long value = (readBoolean() ? -1L : 0L) << (length - 1);

        return value | readUnsignedLong(length -1);
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
        if (avail > 0x00) {
            readUnsignedByte(avail);
        }
    }


    private ByteInput input;

    private int octet = 0x00;
    private int avail = 0x00;

    private long count = 0L;
}