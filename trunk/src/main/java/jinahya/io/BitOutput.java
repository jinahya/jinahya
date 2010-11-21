/*
 *  Copyright 2010 Jin Kwon.
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
 */

package jinahya.io;


import java.io.IOException;
import java.io.OutputStream;
import java.util.BitSet;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class BitOutput {


    /**
     *
     * @param out
     */
    public BitOutput(final OutputStream out) {
        super();

        this.out = out;

        this.set = new BitSet(8);
    }


    /**
     * Writes an unsigned byte value.
     *
     * @param length bit length; between 0 (exclusive) and 8 (inclusive).
     * @param value value
     * @throws IOException if an I/O error occurs.
     */
    public void writeUnsignedByte(final int length, final int value)
        throws IOException {

        //System.out.println("writeUnsignedByte(" + length + ", " + value + ")");

        if (length <= 0) {
            throw new IllegalArgumentException(
                "illegal length: " + length + " <= 0");
        }

        if (length > 8) {
            throw new IllegalArgumentException(
                "illegal length: " + length + " > 8");
        }

        final int required = length - (8 - index);
        if (required > 0) {
            writeUnsignedByte(length - required, value >> required);
            writeUnsignedByte(required, value);
            return;
        }

        int ivalue = value;
        for (int i = length - 1; i >= 0; i--) {
            set.set(index + i, (ivalue & 0x01) == 0x01);
            ivalue >>= 1;
        }
        index += length;
        if (index == 8) {
            int octet = 0x00;
            for (int i = 0; i < 8; i++) {
                octet <<= 1;
                octet |= (set.get(i) ? 0x01 : 0x00);
            }
            out.write(octet);
            index = 0;
            count++;
        }
    }


    /**
     *
     * @param value
     * @throws IOException
     */
    public void writeBoolean(final boolean value) throws IOException {
        writeUnsignedByte(0x01, value ? 0x01 : 0x00);
    }


    /**
     *
     * @param length
     * @param value
     * @throws IOException
     */
    public void writeUnsignedShort(final int length, final int value)
        throws IOException {

        //System.out.println("writeUnsignedShort(" + length + ", " + value + ")");

        if (length <= 0) {
            throw new IllegalArgumentException(
                "illegal length: " + length + " <= 0");
        }

        if (length > 16) {
            throw new IllegalArgumentException(
                "illegal length: " + length + " > 16");
        }

        final int quotient = length / 8;
        final int remainder = length % 8;

        if (remainder > 0) {
            writeUnsignedByte(remainder, value >> (quotient * 8));
        }

        for (int i = quotient - 1; i >= 0; i--) {
            writeUnsignedByte(8, value >> (8 * i));
        }
    }


    /**
     * Aling to given <code>length</code> as octets.
     *
     * @param length octet length
     */
    public void aling(final int length) throws IOException {

        if (length <= 0) {
            throw new IllegalArgumentException(
                "illegal length: " + length + " <= 0");
        }

        int octets = count % length;

        if (octets > 0) {
            octets = length - octets;
        } else if (octets == 0) {
            octets = length;
        } else { // mod < 0
            octets = 0 - octets;
        }

        if (index > 0) {
            writeUnsignedByte(8 - index, 0);
            octets--;
        }

        if (octets == length) {
            return;
        }

        for (int i = 0; i < octets; i++) {
            writeUnsignedByte(8, 0);
        }
    }


    private final OutputStream out;

    private final BitSet set;
    private int index = 0;

    private int count = 0;


    int count() {
        return count;
    }

    void count(final int count) {
        this.count = count;
    }
}
