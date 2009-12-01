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


import java.io.ByteArrayInputStream;
import java.io.CharArrayWriter;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;

import jinahya.bitio.BitInput;


/**
 *
 *
 * @author <a href="jinahya@gmail.com">Jin Kwon</a>
 */
public final class RFC4648Encoder {


    /**
     *
     *
     * @param alphabet
     * @param input
     * @param output
     * @throws IOException if an I/O error occurs
     */
    public static void encode(final String alphabet, final InputStream input,
                              final Writer output)
        throws IOException {

        new RFC4648Encoder(alphabet, input, output).encode();
    }


    /**
     *
     *
     * @param alphabet
     * @param input
     * @return encoded char array
     * @throws IOException if an I/O error occurs
     */
    public static char[] encode(final String alphabet, final InputStream input)
        throws IOException {

        CharArrayWriter out = new CharArrayWriter();
        try {
            encode(alphabet, input, out);
            out.flush();
            return out.toCharArray();
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
    public static void encode(final String alphabet, final byte[] input,
                              final Writer output)
        throws IOException {

        ByteArrayInputStream in = new ByteArrayInputStream(input);
        try {
            encode(alphabet, in, output);
        } finally {
            in.close();
        }
    }


    /**
     *
     *
     * @param alphabet
     * @param input
     * @return encoded char array
     * @throws IOException if an I/O error occurs
     */
    public static char[] encode(final String alphabet, final byte[] input)
        throws IOException {

        ByteArrayInputStream in = new ByteArrayInputStream(input);
        try {
            return encode(alphabet, in);
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
    private RFC4648Encoder(final String alphabet, final InputStream input,
                           final Writer output) {
        super();

        this.alphabet = alphabet;

        this.input = new BitInput(input);
        this.output = output;
    }


    /**
     *
     *
     * @throws IOException if an I/O error occurs
     */
    private void encode() throws IOException {

        int bitsPerChar = RFC4648Utils.bitsPerChar(alphabet);
        int bytesPerWord = RFC4648Utils.bytesPerWord(bitsPerChar);
        int charsPerWord = RFC4648Utils.charsPerWord(bytesPerWord, bitsPerChar);

        while (true) {

            try {
                int unsigned = input.readUnsignedInt(bitsPerChar);
                output.write(alphabet.charAt(unsigned));
            } catch (EOFException eofe) {
                return;
            }

            for (int i = 1; i < charsPerWord; i++) {
                int available = 8 - ((bitsPerChar * i) % 8);
                if (available >= bitsPerChar) {
                    int unsigned = input.readUnsignedInt(bitsPerChar);
                    output.write(alphabet.charAt(unsigned));
                } else { // need next octet
                    int required = bitsPerChar - available;
                    int unsigned =
                        (input.readUnsignedInt(available) << required);
                    try {
                        unsigned |= input.readUnsignedInt(required);
                        output.write(alphabet.charAt(unsigned));
                    } catch (EOFException eofe) {
                        output.write(alphabet.charAt(unsigned));
                        for (int j = i + 1; j < charsPerWord; j++) {
                            output.write(RFC4648Constants.PAD);
                        }
                        return;
                    }
                }
            }
        }
    }


    private String alphabet;

    private BitInput input;
    private Writer output;
}
