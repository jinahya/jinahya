package jinahya.io;


import java.io.EOFException;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;


/**
 *
 * @author Jin Kwon
 */
public class BitInputStream extends FilterInputStream implements BitInput {


    public BitInputStream(InputStream in) {
        super(in);
 
        bitIndexToRead = 8;
        byteValue = 0xFF;

        bitCount = 0;
    }


    /** {@inheritDoc} */
    //@Override
    public int read() throws IOException {
        return readUnsignedShort(8);
    }


    private int readUnsignedByte(int bitLength) throws IOException {
        if (bitIndexToRead == 8) {
            if ((byteValue = in.read()) == -1) {
                throw new EOFException("EOF");
            }
            bitIndexToRead = 0;
        }
        int value = 0;
        int available = 8 - bitIndexToRead;
        if (available >= bitLength) {
            value = (byteValue << bitIndexToRead);
            value >>= (bitIndexToRead + available - bitLength);
            bitIndexToRead += bitLength;
        } else { // available < bitLength
            value = readUnsignedByte(available);
            value <<= (bitLength - available);
            value |= readUnsignedByte(bitLength - available);
        }
        bitCount += bitLength;
        return value;
    }


    private int readUnsignedShort(int bitLength) throws IOException {

        int value = 0;
        int dividend = bitLength;
        int divisor = 7;
        int quotient = bitLength / divisor;
        int remainder = dividend % divisor;
        for (int i = 0; i < quotient; i++) {
            value <<= divisor;
            value |= readUnsignedByte(divisor);
        }
        if (remainder > 0) {
            value <<= remainder;
            value |= readUnsignedByte(remainder);
        }
        return value;
    }


    /** {@inheritDoc} */
    public int readSignedInt(int bitLength) throws IOException {
        if (bitLength <= 1 || bitLength > 32) {
            throw new IllegalArgumentException
                    ("Illegal bit length: " + bitLength);
        }
        return (readUnsignedInt(1) << (bitLength - 1) |
                readUnsignedInt(bitLength - 1));
    }


    /** {@inheritDoc} */
    public int readUnsignedInt(int bitLength) throws IOException {
        if (bitLength < 1 || bitLength >=32) {
            throw new IllegalArgumentException
                    ("Invalid bit length: " + bitLength);
        }
        int value = 0;
        int dividend = bitLength;
        int divisor = 15;
        int quotient = dividend / divisor;
        int remainder = dividend % divisor;
        for (int i = 0; i < quotient; i++) {
            value <<= divisor;
            value |= readUnsignedShort(divisor);
        }
        if (remainder > 0) {
            value <<= remainder;
            value |= readUnsignedShort(remainder);
        }
        return value;
    }


    /** {@inheritDoc} */
    public long readSignedLong(int bitLength) throws IOException {
        if (bitLength <= 1 || bitLength > 64) {
            throw new IllegalArgumentException
                    ("Illegal bit length: " + bitLength);
        }
        return (readUnsignedLong(1) << (bitLength - 1) |
                readUnsignedLong(bitLength - 1));
    }


    /** {@inheritDoc} */
    public long readUnsignedLong(int bitLength) throws IOException {
        if (bitLength < 1 || bitLength >= 64) {
            throw new IllegalArgumentException
                    ("Invalid bit length: " + bitLength);
        }
        long value = 0L;
        int dividend = bitLength;
        int divisor = 31;
        int quotient = dividend / divisor;
        int remainder = dividend % divisor;
        for (int i = 0; i < quotient; i++) {
            value <<= divisor;
            value |= readUnsignedInt(divisor);
        }
        if (remainder > 0) {
            value <<= remainder;
            value |= readUnsignedInt(remainder);
        }
        return value;
    }


    public int getBitCount() {
        return bitCount;
    }

    
    public void setBitCount(int bitCount) {
        this.bitCount = bitCount;
    }


    private int bitIndexToRead;
    private int byteValue;

    private int bitCount;
}
