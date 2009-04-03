package jinahya.bitio;


import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;


/**
 *
 *
 * @author Jin Kwon
 */
public class BitInputImpl implements BitInput {


    /**
     *
     *
     */
    public static interface ByteInput {

        /**
         *
         *
         * @return
         * @exception IOException if an I/O error occurs
         */
        public int readByte() throws IOException;
    }


    /**
     *
     *
     * @param in
     */
    public BitInputImpl(final InputStream in) {
        this(new ByteInput() {
                public int readByte() throws IOException {
                    return in.read();
                }
            });
    }


    /**
     *
     *
     * @param input
     */
    public BitInputImpl(ByteInput input) {
        super();

        this.input = input;

        index = 0x08;
        octet = 0xFF;

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


    /**
     *
     *
     * @param length
     * @throws IOException if an I/O error occurs
     * @throws IllegalArgumentException
     */
    protected int readUnsignedByte(int length) throws IOException {

        if (length < 1 || length >= 8) {
            throw new IllegalArgumentException("illegal length: " + length);
        }

        if (index == 0x08) {
            if ((octet = input.readByte()) == -1) {
                throw new EOFException("EOF");
            }
            index = 0x00;
        }

        int available = 0x08 - index;

        if (available >= length) {
            index += length;
            count += length;
            return (octet >>> (available - length)) & powers[length -1];
        }

        int required = length - available;
        return ((readUnsignedByte(available) << required) |
                readUnsignedByte(required));
    }


    /**
     *
     *
     * @param length
     * @throws IOException if an I/O error occurs
     * @throws IllegalArgumentException
     */
    protected int readUnsignedShort(int length) throws IOException {

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


    /** {@inheritDoc} */
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


    /** {@inheritDoc} */
    public int readInt(int length) throws IOException {

        if (length <= 1 || length > 32) {
            throw new IllegalArgumentException("illegal length: " + length);
        }

        int value = readUnsignedByte(1);
        value <<= (length - 1);
        for (int i = 0; i < 32 - (length - 1); i++) {
            value |= (value <<= 1);
        }

        return value | readUnsignedInt(length -1);
    }


    /** {@inheritDoc} */
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


    /** {@inheritDoc} */
    public long readLong(int length) throws IOException {

        if (length <= 1 || length > 64) {
            throw new IllegalArgumentException("illegal length: " + length);
        }

        long value = readUnsignedByte(1); // sign bit
        value <<= (length - 1); // zero padding
        for (int i = 0; i < 64 - (length - 1); i++) {
            value |= (value <<= 1); // copy shift
        }

        return value | readUnsignedLong(length -1);
    }



    private ByteInput input;

    private int index; // current bit index
    private int octet; // current octet



    /** {@inheritDoc} */
    public long getCount() { return count; }


    /** {@inheritDoc} */
    public void setCount(long count) { this.count = count; }


    private long count;
}