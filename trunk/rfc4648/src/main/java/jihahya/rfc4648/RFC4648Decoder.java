package jinahya.rfc4648;


import java.io.EOFException;
import java.io.IOException;
import java.io.Reader;

import jinahya.bitio.BitOutput;
import jinahya.bitio.BitOutputAdapter;
import jinahya.bitio.BitOutputImpl;


/**
 *
 *
 * @author Jin Kwon
 */
public class RFC4648Decoder {


    public RFC4648Decoder(String alphabet, Reader input,
                          final DecodingOutputHandler handler) {

        super();

        this.alphabet = alphabet;
        this.input = input;

        output = new BitOutputImpl(new BitOutputAdapter() {
                public void putOctet(int octet) throws IOException {
                    handler.decoded(octet);
                }});

        pad = RFC4648Constants.pad;
    }


    public void decode() throws IOException {
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
                if (c == pad) {
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
                        if (c != pad) { // not the pad char?
                            throw new IOException("bad padding");
                        }
                    }
                    return;
                }
                output.writeUnsignedInt(bitsPerChar, alphabet.indexOf(c));
            }
        }
    }


    private String alphabet;
    private Reader input;
    private BitOutput output;
    private char pad;


    public static void decode(String alphabet, Reader input,
                              final java.io.OutputStream output)
        throws IOException {

        new RFC4648Decoder
            (alphabet, input,
             new DecodingOutputHandler() {
                 public void decoded(int decoded) throws IOException {
                     output.write(decoded);
                 }
                }).decode();
    }
}