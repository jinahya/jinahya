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


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;

import jinahya.io.bitio.BitInput;
import jinahya.io.bitio.BitOutput;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class Base {


    /** Default pad character. */
    private static final char PAD = '=';


    /** MAGIC NUMBER: OCTET SIZE. */
    private static final int MN_OS = 8;


    /** MAGIC NUMBER: ASCII SIZE. */
    private static final int MN_AS = 128;


    /** MAGIC NUMBER: SMALLEST VISIBLE ASCII. */
    private static final int MN_SVA = 33;


    /**
     * Returns the Least Common Muliple value for given two operands.
     *
     * @param a the first operand
     * @param b the second operand
     * @return calculated least common multiple
     */
    private static int lcm(final int a, final int b) {
        return ((a * b) / gcd(a, b));
    }


    /**
     * Returns the Greatest Common Divisor for given two operands.
     *
     * @param a the first operand
     * @param b the second operand
     * @return calculated greate common devisor
     */
    private static int gcd(final int a, final int b) {
        if (b == 0) {
            return a;
        } else {
            return gcd(b, a % b);
        }
    }


    /**
     * Create a new instance.
     *
     * @param alphabet alphabe to be used
     * @param padding flag for padding
     */
    protected Base(final byte[] alphabet, final boolean padding) {
        super();

        encode = new byte[alphabet.length];
        System.arraycopy(alphabet, 0, encode, 0, encode.length);

        decode = new byte[MN_AS - MN_SVA + 1];
        for (int i = 0; i < decode.length; i++) {
            decode[i] = -1;
        }
        for (byte i = 0; i < encode.length; i++) {
            decode[encode[i] - MN_SVA] = i;
        }

        this.padding = padding;

        bitsPerChar = (int) (Math.log(encode.length) / Math.log(2.0d));
        bytesPerWord = lcm(MN_OS, bitsPerChar) / MN_OS;
        charsPerWord = bytesPerWord * MN_OS / bitsPerChar;
    }


    /**
     *
     * @param input
     * @return
     * @throws IOException
     */
    public final String encode(final byte[] input) throws IOException {
        return encode(new ByteArrayInputStream(input));
    }


    /**
     *
     * @param input
     * @return
     * @throws IOException
     */
    public final String encode(final InputStream input) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final Writer output = new OutputStreamWriter(baos, "US-ASCII");
        encode(input, output);
        output.flush();
        return new String(baos.toByteArray(), "US-ASCII");
    }


    /**
     *
     * @param input binary input
     * @param output character output
     * @throws IOException if an I/O error occurs
     */
    public final void encode(final InputStream input, final Writer output)
        throws IOException {

        encode(new BitInput(input), output);
    }


    /**
     *
     * @param input binary input
     * @param output character output
     * @throws IOException if an I/O error occurs
     */
    private void encode(final BitInput input, final Writer output)
        throws IOException {

        outer:
        while (true) {

            for (int i = 0; i < charsPerWord; i++) {

                int available = MN_OS - ((bitsPerChar * i) % MN_OS);

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
                                output.write(PAD);
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
     * @return
     * @throws IOException
     */
    public final byte[] decode(final String input) throws IOException {
        return decode(new InputStreamReader(
            new ByteArrayInputStream(input.getBytes("US-ASCII"))));
    }


    /**
     *
     * @param input
     * @return
     * @throws IOException
     */
    public final byte[] decode(final Reader input) throws IOException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        decode(input, output);
        output.flush();
        return output.toByteArray();
    }


    /**
     *
     * @param input character input
     * @param output binary output
     * @throws IOException if I/O error occurs
     */
    public final void decode(final Reader input, final OutputStream output)
        throws IOException {

        decode(input, new BitOutput(output));
        output.flush();
    }


    /**
     *
     * @param input character input
     * @param output binary output
     * @throws IOException if I/O error occurs
     */
    private void decode(final Reader input, final BitOutput output)
        throws IOException {

        int c;

        outer:
        while (true) {

            for (int i = 0; i < charsPerWord; i++) {

                c = input.read();

                if (c == -1) { // end of stream

                    if (i == 0) { // first character in a word
                        break outer;
                    }

                    if (((i * bitsPerChar) % MN_OS) >= bitsPerChar) {
                        throw new IOException("not finished properly");
                    }

                    if (!padding) {
                        break outer;
                    }

                    throw new EOFException("not finished properly");

                } else if (c == PAD) {

                    if (!padding) {
                        throw new  IOException("bad padding; no pad allowed");
                    }

                    if (i == 0) { // first character in a word
                        throw new IOException("bad padding");
                    }

                    if (((i * bitsPerChar) % MN_OS) >= bitsPerChar) {
                        throw new IOException("bad padding");
                    }

                    for (int j = i + 1; j < charsPerWord; j++) {
                        c = input.read(); // pad
                        if (c == -1) { // end of stream?
                            throw new EOFException("not finished properly");
                        }
                        if (c != PAD) { // not the pad char?
                            throw new IOException("bad padding");
                        }
                    }

                    break outer;

                } else {

                    int value = decode[c - MN_SVA];
                    if (value == -1) {
                        throw new IOException("bad character: " + (char) c);
                    }
                    output.writeUnsignedInt(bitsPerChar, value);
                }
            }
        }
    }


    /** Characters for encoding. */
    private byte[] encode;

    /** Characters for decoding. */
    private byte[] decode;

    /** flag for padding. */
    private boolean padding;

    /** number of bits for a character. */
    private final int bitsPerChar;

    /** number of bytes for a word. */
    private final int bytesPerWord;

    /** number of characters for a word. */
    private final int charsPerWord;
}
