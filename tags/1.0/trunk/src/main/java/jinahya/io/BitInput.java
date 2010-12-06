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


import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.util.BitSet;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class BitInput {


    /**
     *
     * @param in
     */
    public BitInput(final InputStream in) {
        super();

        this.in = in;

        this.set = new BitSet(8);
    }


    /**
     * Reads an unsigned byte value.
     *
     * @param length bit length between 0 (exclusive) and 8 (inclusive)
     * @return an unsigned byte value.
     * @throws IOException if an I/O error occurs.
     */
    public int readUnsignedByte(final int length) throws IOException {

        //System.out.println("readUnsignedByte(" + length + ")");

        if (length <= 0) {
            throw new IllegalArgumentException(
                "illegal length: " + length + " <= 0");
        }

        if (length > 8) {
            throw new IllegalArgumentException(
                "illegal length: " + length + " > 8");
        }

        if (index == 8) {
            int octet = in.read();
            if (octet == -1) {
                throw new EOFException("eof");
            }
            for (int i = 7; i >= 0; i--) {
                set.set(i, (octet & 0x01) == 0x01);
                octet >>= 1;
            }
            index = 0;
            count++;
        }

        final int required = length - (8 - index);
        if (required > 0) {
            return (readUnsignedByte(length - required) << required)
                | readUnsignedByte(required);
        }

        int value = 0x00;
        for (int i = 0; i < length; i++) {
            value <<= 1;
            value |= (set.get(index + i) ? 0x01 : 0x00);
        }

        index += length;

        return value;
    }


    /**
     * 
     * @return
     * @throws IOException
     */
    public boolean readBoolean() throws IOException {

        return readUnsignedByte(1) == 0x01;
    }


    /**
     *
     * @param length bit length between 0 (exclusive) and 16 (inclusive).
     * @return an unsigne short value.
     * @throws IOException if an I/O error occurs.
     */
    public int readUnsignedShort(final int length) throws IOException {

        //System.out.println("readUnsignedShort(" + length + ")");

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

        int value = 0x00;
        for (int i = 0; i < quotient; i++) {
            value <<= 0x08;
            value |= readUnsignedByte(8);
        }

        if (remainder > 0) {
            value <<= remainder;
            value |= readUnsignedByte(remainder);
        }

        return value;
    }


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
            readUnsignedByte(8 - index);
            octets--;
        }

        if (octets == length) {
            return;
        }

        for (int i = 0; i < octets; i++) {
            readUnsignedByte(8);
        }
    }


    private final InputStream in;
    private final BitSet set;
    private int index = 8;

    private int count = 0;
}
