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
            int shift = bitLength - available;
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

        int dividend = bitLength;
        int divisor = 7;
        int quotient = dividend / divisor;
        int remainder = dividend % divisor;

        for (int i = 0; i < quotient; i++) {
            writeUnsignedByte((bitValue << (divisor * i)) >> ((quotient - i) * divisor + remainder), divisor);
        }
        if (remainder > 0) {
            writeUnsignedByte((bitValue << (quotient * divisor)) >> (quotient * divisor), remainder);
        }
    }


    public void writeSignedInt(int bitValue, int bitLength) throws IOException {
        if (bitLength <= 1 || bitLength > 32) {
            throw new IllegalArgumentException
                    ("Invalid bit length: " + bitLength);
        }
        int boundary = (int)Math.pow(2, bitLength -1);
        if (bitValue < -boundary || bitValue >= boundary) {
            throw new IllegalArgumentException
                    ("Invalid bit value: " + bitValue);
        }
        writeUnsignedInt(bitValue >>> (bitLength - 1), 1); // sign bit
        writeUnsignedInt((bitValue << 1) >>> 1, bitLength - 1);
        /*
        boolean negative = (bitValue < 0);
        writeBoolean(negative);
        int unsigned = negative ? (bitValue + boundary) : bitValue;
        if (negative && bitLength == 32) {
            unsigned++;
        }
        writeUnsignedInt(unsigned, bitLength - 1);
         */
    }


    public void writeUnsignedInt(int bitValue, int bitLength)
        throws IOException {

        if (bitLength < 1 || bitLength >= 32) {
            throw new IllegalArgumentException
                    ("Invalid bit length: " + bitLength);
        }
        int boundary = (int)Math.pow(2, bitLength);
        if (bitValue < 0 || bitValue >= boundary) {
            throw new IllegalArgumentException
                    ("Invalid bit value: " + bitValue);
        }
        int dividend = bitLength;
        int divisor = 15;
        int quotient = dividend / divisor;
        int remainder = dividend % divisor;

        for (int i = 0; i < quotient; i++) {
            writeUnsignedByte((bitValue << (divisor * i)) >> ((quotient - i) * divisor + remainder), divisor);
        }
        if (remainder > 0) {
            writeUnsignedByte((bitValue << (quotient * divisor)) >> (quotient * divisor), remainder);
        }
        /*
        if (remainder > 0) {
            writeUnsignedShort((bitValue >> (dividend - remainder)), remainder);
        }
        int operand = (int)Math.pow(2, divisor) - 1;
        for (int i = quotient - 1; i >= 0; i--) {
            writeUnsignedShort((bitValue >> (divisor * i)) & operand, divisor);
        }
         */
    }


    public void writeSignedLong(long bitValue, int bitLength)
        throws IOException {

        if (bitLength <= 1 || bitLength > 64) {
            throw new IllegalArgumentException
                    ("Invalid bit length: " + bitLength);
        }
        long boundary = (long)Math.pow(2, bitLength -1);
        if (bitValue < -boundary || bitValue >= boundary) {
            throw new IllegalArgumentException
                    ("Invalid bit value: " + bitValue);
        }
        writeUnsignedLong(bitValue >>> (bitLength - 1), 1); // sign bit
        writeUnsignedLong((bitValue << 1) >>> 1, bitLength - 1);
        /*
        boolean negative = (bitValue < 0);
        writeBoolean(negative);
        long unsigned = negative ? (bitValue + boundary) : bitValue;
        if (negative && bitLength == 64) {
            unsigned++;
        }
        writeUnsignedLong(unsigned, bitLength - 1);
         */
    }


    public void writeUnsignedLong(long bitValue, int bitLength)
        throws IOException {

        if (bitLength < 1 || bitLength >= 64) {
            throw new IllegalArgumentException
                    ("Invalid bit length: " + bitLength);
        }
        long boundary = (long)Math.pow(2, bitLength);
        if (bitValue < 0 || bitValue >= boundary) {
            throw new IllegalArgumentException
                    ("Invalid bit value: " + bitValue);
        }
        int dividend = bitLength;
        int divisor = 31;
        int quotient = dividend / divisor;
        int remainder = dividend % divisor;
        for (int i = 0; i < quotient; i++) {
            writeUnsignedInt((int)(bitValue << (divisor * i)) >> ((quotient - i) * divisor + remainder), divisor);
        }
        if (remainder > 0) {
            writeUnsignedInt((int)(bitValue << (quotient * divisor)) >> (quotient * divisor), remainder);
        }
        /*
        if (remainder > 0) {
            writeUnsignedInt((int)(bitValue >> (dividend - remainder)),
                             remainder);
        }
        long operand = (long)Math.pow(2, divisor) - 1;
        for (int i = quotient - 1; i >= 0; i--) {
            writeUnsignedInt((int)((bitValue >> (divisor * i)) & operand),
                             divisor);
        }
         */
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