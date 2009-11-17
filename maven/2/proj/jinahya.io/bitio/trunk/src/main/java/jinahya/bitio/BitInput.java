/*
 *  Copyright 2009 Jin Kwon.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *  under the License.
 */

package jinahya.bitio;


import java.io.IOException;
import java.io.InputStream;


/**
 *
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class BitInput {


    /**
     * Creates a new instance with specified input.
     *
     * @param in octet input
     */
    public BitInput(final InputStream in) {
        this(new ByteInput() {
            public int readByte() throws IOException {
                return in.read();
            }
        });
    }


    /**
     * Creates a new instance with specified input.
     *
     * @param input
     */
    public BitInput(final ByteInput input) {
        super();

        this.input = input;
    }


    /**
     * Reads one bit and returns true if it is one.
     *
     * @return true for one, false for zero
     * @throws IOException
     */
    public boolean readBoolean() throws IOException {
        return (readUnsignedByte(1) == 0x01);
    }


    private int readUnsignedByte(int length) throws IOException {

        if (length < 1 || length >= 8) {
            throw new IllegalArgumentException("illegal length: " + length);
        }

        if (avail == 0x00) {
            octet = input.readByte();

            {
                System.out.print("readOctet: ");
                String binary = Integer.toBinaryString(octet & 0xFF);
                for (int i = 0; i < (8 - binary.length()); i++) {
                    System.out.print(("0"));
                }
                System.out.println(Integer.toBinaryString(octet & 0xFF) + " (" + octet + ")");
            }

            avail = 0x08;
        }

        if (avail >= length) {
            System.out.println("readUnsignedByte(" + length + ")");
            int value = ((octet & 0xFF) >>> (avail - length));
            avail -= length;
            count += length;
            octet &= (0xFF >> (8 - avail));
            return value;
        } else {
            int requi = length - avail;
            return ((readUnsignedByte(avail) << requi) |
                    readUnsignedByte(requi));
        }
    }


    private int readUnsignedShort(int length) throws IOException {

        if (length < 1 || length >= 16) {
            throw new IllegalArgumentException("illegal length: " + length);
        }

        System.out.println("readUnsignedShort(" + length + ")");

        int value = 0x00;

        int quotient = length / 0x07;
        for (int i = 0; i < quotient; i++) {
            value <<= 0x07;
            value |= readUnsignedByte(0x07);
        }

        int remainder = length % 7;
        if (remainder > 0x00) {
            value <<= remainder;
            value |= readUnsignedByte(remainder);
        }

        return value;
    }


    /**
     * Reads specifed number of bits and returns an unsigned integer.
     *
     * @param length number of bits to read
     * @return an unsigned integer
     * @throws IOException if an I/O error occurs
     */
    public int readUnsignedInt(int length) throws IOException {

        if (length < 1 || length >= 32) {
            throw new IllegalArgumentException("illegal length: " + length);
        }

        System.out.println("readUnsignedInt(" + length + ")");

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
     * Read specifed length of bits and return an integer.
     *
     * @param length number of bits to read
     * @return a signed integer
     * @throws IOException if an I/O error occurs.
     */
    public int readInt(int length) throws IOException {

        if (length <= 1 || length > 32) {
            throw new IllegalArgumentException("illegal length: " + length);
        }

        System.out.println("readInt(" + length + ")");

        int value = (0 - readUnsignedByte(1)) << (length - 1);

        value |= readUnsignedInt(length -1);

        return value;
    }


    /**
     * Reads specified number of bits and returns and unsigned integer.
     *
     * @param length number of bits to read.
     * @return an unsigned integer
     * @throws IOException if an I/O error occurs.
     */
    public long readUnsignedLong(int length) throws IOException {

        if (length < 1 || length >= 64) {
            throw new IllegalArgumentException("illegal length: " + length);
        }

        System.out.println("readUnsignedLong(" + length + ")");

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
     * Reads specified number of bits and returns a signed long.
     *
     * @param length number of bits to read
     * @return a signed long
     * @throws IOException if an I/O error occurs.
     */
    public long readLong(int length) throws IOException {

        if (length <= 1 || length > 64) {
            throw new IllegalArgumentException("illegal length: " + length);
        }

        System.out.println("readLong(" + length + ")");

        long value = ((long) (0 - readUnsignedByte(1))) << (length - 1);

        value |= readUnsignedLong(length -1);

        return value;
    }


    /**
     * Returns the number of bits consumed so far.
     *
     * @return number of bits consumed so far.
     */
    public long getCount() {
        return count;
    }


    /**
     *
     * @param octetLength number of octets to be aligned
     * @throws IOException if an I/O error occurs
     */
    public void alignOctets(int octetLength) throws IOException {
        if (octetLength <= 0x00) {
            throw new IllegalArgumentException
                ("illegal octet length: " + octetLength);
        }

        int length = (int) (count % (octetLength * 8));

        int quotient = length / 7;
        for (int i = 0; i < quotient; i++) {
            readUnsignedByte(7);
        }

        int remainder = length % 7;
        if (remainder > 0) {
            readUnsignedByte(remainder);
        }
    }


    /**
     * Closes this input widh zero padding for octet alignment.
     *
     * @throws IOException if an I/O error occursa
     */
    /*
    public void close() throws IOException {
        alignOctets((byte) 0x01);
    }
     */


    private ByteInput input;

    private int octet = 0x00;
    private int avail = 0x00;

    private long count = 0L;
}