package jinahya.rfc4648;


import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;

import jinahya.bitio.BitInput;
import jinahya.bitio.BitInputImpl;


/**
 *
 *
 * @author Jin Kwon
 */
public class RFC4648Encoder {


    /**
     *
     *
     * @param alphabet
     * @param input
     * @param output
     */
    public RFC4648Encoder(String alphabet, InputStream input, Writer output) {
        super();

        this.alphabet = alphabet;

        this.input = new BitInputImpl(input);
        this.output = output;
    }


    /**
     *
     *
     * @throws IOException if an I/O error occurs
     */
    public void encode() throws IOException {
        int bitsPerChar = RFC4648Utils.bitsPerChar(alphabet);
        int bytesPerWord = RFC4648Utils.bytesPerWord(bitsPerChar);
        int charsPerWord = RFC4648Utils.charsPerWord(bytesPerWord, bitsPerChar);
        while (true) {
            for (int i = 0; i < charsPerWord; i++) {
                int available = 8 - ((bitsPerChar * i) % 8);
                if (available >= bitsPerChar) {
                    try {
                        int unsigned = input.readUnsignedInt(bitsPerChar);
                        output.write(alphabet.charAt(unsigned));
                    } catch (EOFException eofe) {
                        if (i != 0) {
                            throw new IOException("this couldn't happen");
                        }
                        // i == 0
                        return;
                    }
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
                            output.write(RFC4648Constants.pad);
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