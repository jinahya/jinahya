package jinahya.io;


import java.io.IOException;


/**
 * @author Jin Kwon
 */
public class BitOutputImpl implements BitOutput {


    /**
     * @param adapter
     */
    public BitOutputImpl(BitOutputAdapter adapter) {
        this(adapter, false);
    }


    /**
     * @param adapter
     * @param validate
     */
    public BitOutputImpl(BitOutputAdapter adapter, boolean validate) {
        super();

        this.adapter = adapter;
        this.validate = validate;

        index = 0x00;
        octet = 0x00;
    }



    private static final int[] powers  = new int[] {
        0x01,
        0x03,
        0x07,
        0x0F, // 15 = 2^4 - 1
        0x1F, // 31 = 2^5 - 1
        0x3F, // 63 = 2^6 - 1
        0x7F, // 127 = 2^7 - 1
        0xFF
    };


    /**
     * @param length 0 < length < 8
     * @param value 2^0 < value < 2^8
     * @throws IOException if an I/O error occurs
     */
    protected void writeUnsignedByte(int length, int value) throws IOException {

        int available = 0x08 - index;
        if (available >= length) {
            //System.out.println("\t\tbyte: " + length + "/" + value);
            //System.out.println("\t\t\tb: " + (value & powers[length - 1]));
            octet |= ((value & powers[length - 1]) << (available - length));
            if ((index += length) == 0x08) {
                //System.out.println("octet: " + octet);
                adapter.putOctet(octet);
                index = 0x00;
                octet = 0x00;
            }
            return;
        }
        int required = length - available;
        writeUnsignedByte(available, value >> required); // high available bits
        writeUnsignedByte(required, value); // low required bits
    }


    /**
     * @param length 0 < length < 16
     * @param value 2^0 < value < 2^16
     * @throws IOException if an I/O error occurs
     */
    protected void writeUnsignedShort(int length, int value)
        throws IOException {

        //System.out.println("\tshort: " + length + "/" + value);

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
        //System.out.println("int: " + length + "/" + value);
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
        //System.out.println("value: " + value);
        writeUnsignedByte(1, value >> (length - 1)); // sign bit
        writeUnsignedInt(length - 1, value);
    }


    /** {@inheritDoc} */
    public void writeUnsignedLong(int length, long value) throws IOException {
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
        writeUnsignedByte(1, (value < 0L ? 0x01 : 0x00));
        writeUnsignedLong(length - 1, value);
    }



    private BitOutputAdapter adapter;

    private int index;
    private byte octet;



    public boolean getValidate() { return validate; }

    public void setValidate(boolean validate) { this.validate = validate; }


    private boolean validate;
}