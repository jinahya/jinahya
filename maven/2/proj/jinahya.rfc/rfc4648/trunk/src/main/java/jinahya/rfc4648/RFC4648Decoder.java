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

package jinahya.rfc4648;


import java.io.EOFException;
import java.io.IOException;
import java.io.Reader;
import java.io.OutputStream;

import jinahya.bitio.BitOutput;


/**
 *
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
abstract class RFC4648Decoder {


    /*
    public static void decode(final String alphabet, final Reader input,
                              final OutputStream output)
        throws IOException {

        new RFC4648Decoder(alphabet, input, output).decode();
    }


    public static byte[] decode(final String alphabet, final Reader input)
        throws IOException {

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            decode(alphabet, input, out);
            out.flush();
            return out.toByteArray();
        } finally {
            out.close();
        }
    }

    public static void decode(final String alphabet, final char[] input,
                              final OutputStream output)
        throws IOException {

        Reader in = new CharArrayReader(input);
        try {
            decode(alphabet, in, output);
        } finally {
            in.close();
        }
    }

    public static byte[] decode(final String alphabet, final char[] input)
        throws IOException {

        Reader in = new CharArrayReader(input);
        try {
            return decode(alphabet, in);
        } finally {
            in.close();
        }
    }
     */


    /**
     *
     *
     * @param alphabet
     * @param padding
     * @param input
     * @param output
     */
    protected RFC4648Decoder(final byte[] alphabet, final boolean padding,
                             final Reader input, final OutputStream output) {

        super();

        for (int i = 0; i < this.alphabet.length; i++) {
            this.alphabet[i] = -1;
        }

        for (byte i = 0; i < alphabet.length; i++) {
            this.alphabet[alphabet[i] & 0xFF] = i;
        }

        this.padding = padding;

        this.input = input;
        this.output = new BitOutput(output);
    }


    public final void decode() throws IOException {

        int bitsPerChar = RFC4648Utils.bitsPerChar(alphabet);
        int bytesPerWord = RFC4648Utils.bytesPerWord(bitsPerChar);
        int charsPerWord = RFC4648Utils.charsPerWord(bytesPerWord, bitsPerChar);

        outer:
        while (true) {

            for (int i = 0; i < charsPerWord; i++) {

                int c = input.read();

                if (c == -1) { // end of stream

                    if (i == 0) { // first character in a word
                        break outer;
                    } else if (((i * bitsPerChar) % 8) >= bitsPerChar) {
                        throw new IOException("not finished properly");
                    }

                    throw new EOFException("not finished properly");

                } else if (c == RFC4648Constants.PAD) {

                    if (!padding) {
                        throw new  IOException("bad padding; no pad allowed");
                    }

                    if (i == 0) { // first character in a word
                        throw new IOException("bad padding");
                    } else if (((i * bitsPerChar) % 8) >= bitsPerChar) {
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

                    int value = alphabet[c];
                    if (value == -1) {
                        throw new IOException("bad character: " + c);
                    }
                    output.writeUnsignedInt(bitsPerChar, value);
                }
            }
        }

        output.alignOctets(1);
    }


    private byte[] alphabet = new byte[128];
    private boolean padding;

    private Reader input;
    private BitOutput output;
}
