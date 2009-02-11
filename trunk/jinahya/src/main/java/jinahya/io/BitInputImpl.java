package jinahya.io;


import java.io.IOException;


/**
 * @author Jin Kwon
 */
public class BitInputImpl implements BitInput {

    /**
     * @param adapter
     */
    public BitInputStream(BitInputAdapter adapter) {
        super();

        this.adapter = adapter;

        index = 0x08;
        octet = 0xFF;
    }


    protected int readUnsignedByte(int length) throws IOException {

        if (index == 0x08) {
            if ((octet = adapter.getOctet()) == -1) {
                throw new EOFException("EOF");
            }
            index = 0x00;
        }

        int available = 0x08 - index;

        if (available >= length) {
            index += length;
            return (octet << index) >>> (0x08 - length);
        }

        int required = length - available;
        return (readUnsignedByte(available) << required) |
            readUnsignedByte(required);

    }


    protected int readUnsignedShort(int length) throws IOException {

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
    public int readInt(int length) throws IOException {
        int shift = length - 1;
        return 0 - (readUnsignedByte(1) << shift) + readUnsignedInt(shift);
    }


    /** {@inheritDoc} */
    public int readUnsignedInt(int length) throws IOException {

        if (length < 1 || length >= 32) {
            throw new IllegalArgumentException("unacceptable: " + length);
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
    public long readLong(int length) throws IOException {
        int shift = length - 1;
        return 0L - (readUnsignedByte(1) << shift) + readUnsignedLong(shift);
    }


    /** {@inheritDoc} */
    public long readUnsignedLong(int length) throws IOException {

        if (length < 1 || length >= 64) {
            throw new IllegalArgumentException("unacceptalbe: " + length);
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


    private BitInputAdapter adapter;

    private int index; // current bit index
    private int octet; // current octet
}