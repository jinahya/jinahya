package jinahya.io;


import java.io.EOFException;
import java.io.IOException;


/**
 * @author Jin Kwon
 */
public class BitInputImpl implements BitInput {


    /**
     * @param adapter
     */
    public BitInputImpl(BitInputAdapter adapter) {
        this(adapter, false);
    }


    /**
     * @param adapter
     * @param validate
     */
    public BitInputImpl(BitInputAdapter adapter, boolean validate) {
        super();

        this.adapter = adapter;
        this.validate = validate;

        index = 0x08;
        octet = 0xFF;
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
            return (octet >>> (available - length)) & powers[length -1];
        }

        int required = length - available;
        return ((readUnsignedByte(available) << required) |
                readUnsignedByte(required));
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
    public int readUnsignedInt(int length) throws IOException {

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
        int value = readUnsignedByte(1);
        value <<= (length - 1);
        for (int i = 0; i < 32 - (length - 1); i++) {
            value |= (value <<= 1);
        }

        return value | readUnsignedInt(length -1);
    }




    /** {@inheritDoc} */
    public long readUnsignedLong(int length) throws IOException {
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
        long value = readUnsignedByte(1);
        value <<= (length - 1);
        for (int i = 0; i < 64 - (length - 1); i++) {
            value |= (value <<= 1);
        }

        return value | readUnsignedLong(length -1);
    }



    private BitInputAdapter adapter;

    private int index; // current bit index
    private int octet; // current octet



    public boolean getValidate() { return validate; }

    public void setValidate(boolean validate) { this.validate = validate; }


    private boolean validate;
}