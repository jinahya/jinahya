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
import java.io.OutputStream;


/**
 *
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class BitOutput {


    /**
     * Creates a new instance with specifed output.
     *
     * @param out output to which octets are write
     */
    public BitOutput(final OutputStream out) {
        this(new ByteOutput() {
            public void writeByte(int b) throws IOException {
                out.write(b);
            }
        });
    }


    /**
     * Creates a new instance with specified output.
     *
     * @param output output to which octets are write
     */
    public BitOutput(final ByteOutput output) {
        super();

        this.output = output;
    }


    /**
     * Writes a boolean value. Only one bit is consumed.
     *
     * @param value boolean value
     * @throws IOException if an I/O error occurs.
     */
    public void writeBoolean(boolean value) throws IOException {
        writeUnsignedByte(1, value ? 0x01 : 0x00);
    }


    /**
     * Writes an array of bytes.
     *
     * @param value byte array
     * @throws IOException if an I/O error occurs.
     */
    public void writeBytes(byte[] value) throws IOException {
        for (int i = 0; i < value.length; i++) {
            writeUnsignedShort(8, value[i]);
        }
    }


    private void writeUnsignedByte(final int length, final int value)
        throws IOException {

        /*
        if (length < 0x01 || length >= 0x08) {
            throw new IllegalArgumentException("illegal length: " + length);
        }
         */

        /*
        if (value < 0x00) {
            throw new IllegalArgumentException("illegal value: " + value);
        }
         */

        if (avail >= length) {
            ////System.out.println("writeUnsignedByte(" + length + ", " + value + ")");
            octet <<= length;
            octet |= (value & (0xFF >> (0x08 - length)));
            count += length;
            if ((avail -= length) == 0x00) {

                /*
                {
                    //System.out.print("writeOctet: ");
                    String binary = Integer.toBinaryString(octet & 0xFF);
                    for (int i = 0; i < (8 - binary.length()); i++) {
                        //System.out.print(("0"));
                    }
                    //System.out.println(Integer.toBinaryString(octet & 0xFF) + " (" + octet + ")");
                }
                 */

                output.writeByte(octet);
                octet = 0x00;
                avail = 0x08;
            }
        } else {
            int requi = length - avail;
            writeUnsignedByte(avail, (value >> (requi)));
            writeUnsignedByte(requi, value);
        }
    }


    private void writeUnsignedShort(final int length, final int value)
        throws IOException {

        /*
        if (length < 0x01 || length >= 0x10) {
            throw new IllegalArgumentException("illegal length: " + length);
        }
         */

        /*
        if (value < 0x00) {
            throw new IllegalArgumentException("illegal value: " + value);
        }
         */

        ////System.out.println("writeUnsignedShort(" + length + ", " + value + ")");

        int quotient = length / 0x07;
        int remainder = length % 0x07;
        if (remainder > 0) {
            writeUnsignedByte(remainder, (value >> (quotient * 0x07)));
        }
        for (int i = quotient - 1; i >= 0; i--) {
            writeUnsignedByte(7, (value >> (i * 0x07)));
        }
    }


    /**
     * Writes an unsigned integer.
     *
     * @param length number of bits
     * @param value int value
     * @throws IOException if an I/O error occurs.
     */
    public void writeUnsignedInt(final int length, final int value)
        throws IOException {

        ////System.out.println("writeUnsignedInt(" + length + ", " + value + ")");

        if (length < 0x1 || length >= 0x20) {
            throw new IllegalArgumentException("illegal length: " + length);
        }

        /*
        if (value < 0x00) {
            throw new IllegalArgumentException("illegal value: " + value);
        }
         */

        int quotient = length / 0x0F;
        int remainder = length % 0x0F;
        if (remainder > 0) {
            writeUnsignedShort(remainder, (value >> (quotient * 0x0F)));
        }
        for (int i = quotient -1; i >= 0; i--) {
            writeUnsignedShort(0x0F, (value >> (i * 0x0F)));
        }
    }


    /**
     * Writes an signed integer.
     *
     * @param length number of bits
     * @param value int value
     * @throws IOException if an I/O error occurs.
     */
    public void writeInt(final int length, final int value) throws IOException {

        ////System.out.println("writeInt(" + length + ", " + value + ")");

        if (length <= 0x01 || length > 0x20) {
            throw new IllegalArgumentException("illegal length: " + length);
        }

        writeUnsignedByte(1, value >> (length - 1));
        writeUnsignedInt(length - 1, value);

        /*
        writeUnsignedByte(1, (value >>> (length - 1)) & 0x01);
        writeUnsignedInt(length - 1, value & Integer.MAX_VALUE);
         */
    }


    /**
     * Writes an unsigned long.
     *
     * @param length number of bits
     * @param value value
     * @throws IOException if an I/O error occurs.
     */
    public void writeUnsignedLong(final int length, final long value)
        throws IOException {

        ////System.out.println("writeUnsignedLong(" + length + ", " + value + ")");

        if (length < 0x01 || length >= 0x40) {
            throw new IllegalArgumentException("illegal length: " + length);
        }

        /*
        if (value < 0x00L) {
            throw new IllegalArgumentException("illegal value: " + value);
        }
         */

        int quotient = length / 0x1F;
        int remainder = length % 0x1F;
        if (remainder > 0) {
            writeUnsignedInt(remainder, (int) (value >> (quotient * 0x1F)));
            /*
            writeUnsignedInt(remainder,
                (int) ((value >> (quotient * 0x1F)) & Integer.MAX_VALUE));
             */
        }
        for (int i = quotient - 1; i >= 0; i--) {
            writeUnsignedInt(0x1F, (int) (value >> (i * 0x1F)));

            /*
            writeUnsignedInt(0x1F,
                (int) ((value >> (i * 0x1F)) & Integer.MAX_VALUE));
             */
        }
    }


    /**
     * Writes a signed long.
     *
     * @param length number of bits
     * @param value value
     * @throws IOException if an I/O error occurs.
     */
    public void writeLong(int length, long value) throws IOException {

        if (length <= 0x01 || length > 0x40) {
            throw new IllegalArgumentException("illegal length: " + length);
        }

        ////System.out.println("writeLong(" + length + ", " + value + ")");

        writeUnsignedByte(1, (int) (value >>> (length - 1)));
        writeUnsignedLong(length - 1, value);

        /*
        writeUnsignedByte(1, (int) ((value >>> (length - 1)) & 0x01));
        writeUnsignedLong(length - 1, value & Long.MAX_VALUE);
         */
    }


    /**
     * Returns the number of bits written so far.
     *
     * @return number of bits written so far.
     */
    public long getCount() {
        return count;
    }


    /**
     *
     * @param octetLength
     * @throws IOException
     */
    public void alignOctets(int octetLength) throws IOException {
        if (octetLength <= 0x00) {
            throw new IllegalArgumentException
                ("illegal octet length: " + octetLength);
        }
        //System.out.println("octetLength: " + octetLength);
        //System.out.println("count: " + count);
        int length = (octetLength * 8) - (int) (count % 8);
        //System.out.println("length: " + length);

        int quotient = length / 7;
        //System.out.println("quotient: " + quotient);
        for (int i = 0; i < quotient; i++) {
            writeUnsignedByte(7, 0x00);
        }

        int remainder = length % 7;
        //System.out.println("remainder: " + remainder);
        if (remainder > 0) {
            writeUnsignedByte(remainder, 0x00);
        }
    }


    /**
     * Closes this output padding zero for octec alignment.
     *
     * @throws IOException if an I/O error occurs
     */
    /*
    public void close() throws IOException {
        alignOctets((byte) 0x01);
    }
     */


    private ByteOutput output;

    private int octet = 0x00;
    private int avail = 0x08;

    private long count = 0L;
}
