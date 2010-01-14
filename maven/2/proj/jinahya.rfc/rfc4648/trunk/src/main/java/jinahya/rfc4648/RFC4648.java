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

package jinahya.rfc4648;


import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;

import jinahya.bitio.BitInput;
import jinahya.bitio.BitOutput;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class RFC4648 {


    private static final int OCTET_SIZE = 8;


    /**
     *
     * @param a
     * @param b
     * @return calculated least common multiple
     */
    private static int LCM(final int a, final int b) {
        return ((a * b) / GCD(a, b));
    }


    /**
     *
     * @param a
     * @param b
     * @return calculated greate common devisor
     */
    private static int GCD(final int a, final int b) {
        return (b == 0 ? a : GCD(b, a % b));
    }


    protected RFC4648(final byte[] alphabet, final boolean padding) {
        super();

        encode = new byte[alphabet.length];
        System.arraycopy(alphabet, 0, encode, 0, encode.length);

        decode = new byte[128]; // number of visible characters in ascii
        for (int i = 0; i < decode.length; i++) {
            decode[i] = -1;
        }
        for (byte i = 0; i < encode.length; i++) {
            decode[encode[i]] = i;
        }

        this.padding = padding;

        bitsPerChar = (int) (Math.log(encode.length) / Math.log(2.0d));
        bytesPerWord = LCM(OCTET_SIZE, bitsPerChar) / OCTET_SIZE;
        charsPerWord = bytesPerWord * OCTET_SIZE / bitsPerChar;
    }


    /**
     *
     * @param input
     * @param output
     * @throws IOException
     */
    public final void encode(final InputStream input, final Writer output)
        throws IOException {

        encode(new BitInput(input), output);
    }


    private void encode(BitInput input, Writer output) throws IOException {

        outer:
        while (true) {

            for (int i = 0; i < charsPerWord; i++) {

                int available = 8 - ((bitsPerChar * i) % 8);

                if (available >= bitsPerChar) {
                    try {
                        int unsigned = input.readUnsignedInt(bitsPerChar);
                        output.write(encode[unsigned]);
                    } catch (EOFException eofe) { // i == 0
                        break outer;
                    }
                } else { // need next octet
                    int required = bitsPerChar - available;
                    int unsigned =
                        (input.readUnsignedInt(available) << required);
                    try {
                        unsigned |= input.readUnsignedInt(required);
                        output.write(encode[unsigned]);
                    } catch (EOFException eofe) {
                        output.write(encode[unsigned]);
                        if (padding) {
                            for (int j = i + 1; j < charsPerWord; j++) {
                                output.write(RFC4648Constants.PAD);
                            }
                        }
                        break outer;
                    }
                }
            }
        }

        output.flush();
    }


    /**
     *
     * @param input
     * @param output
     * @throws IOException if I/O error occurs
     */
    public final void decode(final Reader input, final OutputStream output)
        throws IOException {

        decode(input, new BitOutput(output));
        output.flush();
    }


    /**
     *
     * @param input
     * @param output
     * @throws IOException if I/O error occurs
     */
    private void decode(Reader input, BitOutput output) throws IOException {

        int c;

        outer:
        while (true) {

            for (int i = 0; i < charsPerWord; i++) {

                c = input.read();

                if (c == -1) { // end of stream

                    if (i == 0) { // first character in a word
                        break outer;
                    }

                    if (((i * bitsPerChar) % OCTET_SIZE) >= bitsPerChar) {
                        throw new IOException("not finished properly");
                    }

                    if (!padding) {
                        break outer;
                    }

                    throw new EOFException("not finished properly");

                } else if (c == RFC4648Constants.PAD) {

                    if (!padding) {
                        throw new  IOException("bad padding; no pad allowed");
                    }

                    if (i == 0) { // first character in a word
                        throw new IOException("bad padding");
                    }

                    if (((i * bitsPerChar) % 8) >= bitsPerChar) {
                        throw new IOException("bad padding");
                    }

                    for (int j = i + 1; j < charsPerWord; j++) {
                        c = input.read(); // pad
                        if (c == -1) { // end of stream?
                            throw new EOFException("not finished properly");
                        }
                        if (c != RFC4648Constants.PAD) { // not the pad char?
                            throw new IOException("bad padding");
                        }
                    }

                    break outer;

                } else {

                    int value = decode[c];
                    if (value == -1) {
                        throw new IOException("bad character: " + c);
                    }
                    output.writeUnsignedInt(bitsPerChar, value);
                }
            }
        }


        /*
        System.out.println(output.getCount());
        output.align(8);
        System.out.println(output.getCount());
         */
    }


    private byte[] encode;
    private byte[] decode;

    private boolean padding;

    protected final int bitsPerChar;
    protected final int bytesPerWord;
    protected final int charsPerWord;
}
