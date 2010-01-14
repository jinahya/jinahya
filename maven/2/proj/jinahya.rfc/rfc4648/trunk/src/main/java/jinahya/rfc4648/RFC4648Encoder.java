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
 */

package jinahya.rfc4648;


import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;

import jinahya.bitio.BitInput;


/**
 *
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
abstract class RFC4648Encoder {


    /**
     *
     *
     * @param alphabet alphabe to be used
     * @param padding flag for padding
     * @param input binary input
     * @param output character output
     */
    protected RFC4648Encoder(final byte[] alphabet, final boolean padding,
                             final InputStream input,
                             final Writer output) {
        super();

        this.alphabet = alphabet;
        this.padding = padding;

        bitsPerChar = RFC4648Utils.bitsPerChar(alphabet);
        bytesPerWord = RFC4648Utils.bytesPerWord(bitsPerChar);
        charsPerWord = RFC4648Utils.charsPerWord(bytesPerWord, bitsPerChar);

        //this.input = new BitInput(input);
        //this.output = output;
    }


    /**
     *
     *
     * @throws IOException if an I/O error occurs
     */
    public final void encode(InputStream input, Writer output)
        throws IOException {

        encode(new BitInput(input), output);
    }


    public final void encode(BitInput input, Writer output) throws IOException {

        outer:
        while (true) {

            try {
                int unsigned = input.readUnsignedInt(bitsPerChar);
                output.write(alphabet[unsigned]);
            } catch (EOFException eofe) {
                break outer;
            }

            for (int i = 1; i < charsPerWord; i++) {
                int available = 8 - ((bitsPerChar * i) % 8);
                if (available >= bitsPerChar) {
                    int unsigned = input.readUnsignedInt(bitsPerChar);
                    output.write(alphabet[unsigned]);
                } else { // need next octet
                    int required = bitsPerChar - available;
                    int unsigned =
                        (input.readUnsignedInt(available) << required);
                    try {
                        unsigned |= input.readUnsignedInt(required);
                        output.write(alphabet[unsigned]);
                    } catch (EOFException eofe) {
                        output.write(alphabet[unsigned]);
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


    private byte[] alphabet;
    private boolean padding;

    private int bitsPerChar;
    private int bytesPerWord;
    private int charsPerWord;

    //private BitInput input;
    //private Writer output;
}
