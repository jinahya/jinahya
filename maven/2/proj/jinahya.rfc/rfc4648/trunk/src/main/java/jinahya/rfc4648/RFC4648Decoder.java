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


import java.io.ByteArrayOutputStream;
import java.io.CharArrayReader;
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
public final class RFC4648Decoder {


    /**
     *
     *
     * @param alphabet
     * @param input
     * @param output
     * @throws IOException if an I/O error occurs
     */
    public static void decode(final String alphabet, final Reader input,
                              final OutputStream output)
        throws IOException {

        new RFC4648Decoder(alphabet, input, output).decode();
    }


    /**
     *
     *
     * @param alphabet
     * @param input
     * @return decoded byte array
     * @throws IOException if an I/O error occurs
     */
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


    /**
     *
     *
     * @param alphabet
     * @param input
     * @param output
     * @throws IOException if an I/O error occurs
     */
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


    /**
     *
     *
     * @param alphabet
     * @param input
     * @return decoded byte array
     * @throws IOException if an I/O error occurs
     */
    public static byte[] decode(final String alphabet, final char[] input)
        throws IOException {

        Reader in = new CharArrayReader(input);
        try {
            return decode(alphabet, in);
        } finally {
            in.close();
        }
    }


    /**
     *
     *
     * @param alphabet
     * @param input
     * @param output
     */
    private RFC4648Decoder(final String alphabet, final Reader input,
                           final OutputStream output) {

        super();

        this.alphabet = alphabet;

        this.input = input;
        this.output = new BitOutput(output);
    }


    private void decode() throws IOException {

        int bitsPerChar = RFC4648Utils.bitsPerChar(alphabet);
        int bytesPerWord = RFC4648Utils.bytesPerWord(bitsPerChar);
        int charsPerWord = RFC4648Utils.charsPerWord(bytesPerWord, bitsPerChar);

        while (true) {

            for (int i = 0; i < charsPerWord; i++) {
                int c = input.read();
                if (c == -1) { // end of stream
                    if (i == 0) {
                        return;
                    }
                    throw new EOFException("not finished properly");
                }
                if (c == RFC4648Constants.PAD) {
                    if (i == 0) { // :(
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
                    return;
                }

                int index = alphabet.indexOf(c);
                if (index == -1) {
                    throw new IOException("bad character: " + c);
                }
                output.writeUnsignedInt(bitsPerChar, index);
            }
        }
    }


    private String alphabet;

    private Reader input;
    private BitOutput output;
}
