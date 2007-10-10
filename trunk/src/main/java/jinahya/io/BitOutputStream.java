package jinahya.io;


import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;


/**
 *
 * @author Jin Kwon
 */
public class BitOutputStream extends FilterOutputStream implements BitOutput {


    public BitOutputStream(OutputStream out) {
        super(out);

        bitIndexToWrite = 0;
        byteValue = 0x00;

        bitCount = 0;
    }


    //@Override
    public void write(int b) throws IOException {
        writeUnsignedShort(b, 8);
    }


    private void writeUnsignedByte(int bitValue, int bitLength)
        throws IOException {

        int available = 8 - bitIndexToWrite;
        if (available >= bitLength) {
            byteValue |= (bitValue << (available - bitLength));
            bitIndexToWrite += bitLength;
        } else { // avabilable < length
            writeUnsignedByte(bitValue >> (bitLength - available), available);
            writeUnsignedByte((bitValue << available) >> available,
                              (bitLength - available));
        }
        if (bitIndexToWrite == 8) {
            out.write(byteValue);
            byteValue = 0x00;
            bitIndexToWrite = 0;
        }
        bitCount += bitLength;
    }


    private void writeUnsignedShort(int bitValue, int bitLength)
        throws IOException {

        int shifted = bitValue << (32 - bitLength);

        int dividend = bitLength;
        int divisor = 7;
        int quotient = dividend / divisor;
        int remainder = dividend % divisor;

        for (int i = 0; i < quotient; i++) {
            writeUnsignedByte(shifted >>> (32 - divisor), divisor);
            shifted <<= divisor;
        }
        if (remainder > 0) {
            writeUnsignedByte(shifted >>> (32 - remainder), remainder);
        }
    }


    public void writeSignedInt(int bitValue, int bitLength) throws IOException {
        if (bitLength <= 1 || bitLength > 32) {
            throw new IllegalArgumentException
                    ("Invalid bit length: " + bitLength);
        }

        writeUnsignedByte((bitValue < 0 ? 1 : 0), 1); // sign bit
        writeUnsignedInt(bitValue, bitLength - 1);
    }


    public void writeUnsignedInt(int bitValue, int bitLength)
        throws IOException {

        if (bitLength < 1 || bitLength >= 32) {
            throw new IllegalArgumentException
                    ("Invalid bit length: " + bitLength);
        }

        int shifted = bitValue << (32 - bitLength);

        int dividend = bitLength;
        int divisor = 15;
        int quotient = dividend / divisor;
        int remainder = dividend % divisor;

        for (int i = 0; i < quotient; i++) {
            writeUnsignedShort(shifted >>> (32 - divisor), divisor);
            shifted <<= divisor;
        }
        if (remainder > 0) {
            writeUnsignedShort(shifted >>> (32 - remainder), remainder);
        }
    }


    public void writeSignedLong(long bitValue, int bitLength)
        throws IOException {

        if (bitLength <= 1 || bitLength > 64) {
            throw new IllegalArgumentException
                    ("Invalid bit length: " + bitLength);
        }
        writeUnsignedByte((bitValue < 0 ? 1 : 0), 1);
        writeUnsignedLong(bitValue, bitLength -1);
    }


    public void writeUnsignedLong(long bitValue, int bitLength)
        throws IOException {

        if (bitLength < 1 || bitLength >= 64) {
            throw new IllegalArgumentException
                    ("Invalid bit length: " + bitLength);
        }

        long shifted = bitValue << (64 - bitLength);

        int dividend = bitLength;
        int divisor = 15;
        int quotient = dividend / divisor;
        int remainder = dividend % divisor;

        for (int i = 0; i < quotient; i++) {
            writeUnsignedInt((int)(shifted >>> (32 - divisor)), divisor);
            shifted <<= divisor;
        }
        if (remainder > 0) {
            writeUnsignedInt((int)(shifted >>> (32 - remainder)), remainder);
        }
    }


    public int getBitCount() {
        return bitCount;
    }


    public void setBitCount(int bitCount) {
        this.bitCount = bitCount;
    }


    private int bitIndexToWrite;
    private int byteValue;

    private int bitCount;
}