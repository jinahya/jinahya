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


    private static final int[] powers = new int[] {
        0x01,
        0x03,
        0x07,
        0x0F, // 15
        0x1F, // 31
        0x3F, // 63
        0x7F, // 127
        0xFF  // 255
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
            octet <<= index;
            octet >>>= index;
            index += length;
            return (octet >>> (available - length));
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
    public int readUnsignedInt(int length) throws IOException {

        if (validate) {
            if (length < 1 || length >= 32) {
                throw new IllegalArgumentException("unacceptable: " + length);
            }
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
        int value = readUnsignedByte(1);
        for (int i = 0; i < 31 - length; i++) {
            value |= (value <<= 1);
        }
        value <<= length;
        System.out.println("value: " + value);
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
        return (readUnsignedByte(1) < 0 ?
                ~readUnsignedLong(length - 1) : readUnsignedLong(length - 1));
    }



    private BitInputAdapter adapter;

    private int index; // current bit index
    private int octet; // current octet



    public boolean getValidate() { return validate; }

    public void setValidate(boolean validate) { this.validate = validate; }


    private boolean validate;
}