package jinahya.io;


import java.io.IOException;


/**
 * @author Jin Kwon
 */
public class BitOutputImpl implements BitOutput {


    /**
     * @param out
     */
    public BitOutputImpl(BitOutputAdapter adapter) {
        super();

        this.adapter = adapter;

        index = 0x00;
        octet = 0x00;
    }


    protected void writeUnsignedByte(int length, int value) throws IOException {
        int available = 0x08 - index;
        if (available >= length) {
            octet |= (value << (available - length));
            if ((index += length) == 0x08) {
                adapter.putOctet(octet);
                index = 0x00;
                octet = 0x00;
            }
            return;
        }
        int required = length - available;
        writeUnsignedByte(available, value >> required);
        writeUnsignedByte(required, value);
    }


    protected void writeUnsignedShort(int length, int value)
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


    private BitOutputAdapter adapter;

    private int index;
    private byte octet;
}