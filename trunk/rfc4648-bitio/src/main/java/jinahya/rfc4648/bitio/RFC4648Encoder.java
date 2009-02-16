package jinahya.rfc4648.bitio;


import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;

import jinahya.bitio.BitInput;
import jinahya.bitio.BitInputAdapter;
import jinahya.bitio.BitInputImpl;

import jinahya.rfc4648.AbstractRFC4648Encoder;
import jinahya.rfc4648.EncodingOutputHandler;
import jinahya.rfc4648.RFC4648Utils;


/**
 * @author Jin Kwon
 */
public class RFC4648Encoder extends AbstractRFC4648Encoder {


    public RFC4648Encoder(String alphabet, EncodingOutputHandler handler,
                          final InputStream input) {

        super(alphabet, handler);

        this.input = new BitInputImpl(new BitInputAdapter() {
                public int getOctet() throws IOException {
                    return input.read();
                }});
    }


    @Override
    public void encodeFinal() throws IOException {
        int bitsPerChar = RFC4648Utils.bitsPerChar(alphabet);
        int bytesPerWord = RFC4648Utils.bytesPerWord(bitsPerChar);
        int charsPerWord = RFC4648Utils.charsPerWord(bytesPerWord, bitsPerChar);
        while (true) {
            for (int i = 0; i < charsPerWord; i++) {
                int available = 8 - ((bitsPerChar * i) % 8);
                if (available >= bitsPerChar) {
                    try {
                        int unsigned = input.readUnsignedInt(bitsPerChar);
                        handler.encoded(alphabet.charAt(unsigned));
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
                        handler.encoded(alphabet.charAt(unsigned));
                    } catch (EOFException eofe) {
                        handler.encoded(alphabet.charAt(unsigned));
                        for (int j = i + 1; j < charsPerWord; j++) {
                            handler.encoded(pad);
                        }
                        return;
                    }
                }
            }
        }
    }


    private BitInput input;



    protected static void encode(String alphabet, InputStream input,
                                 final java.io.Writer output)
        throws IOException {

        new RFC4648Encoder
            (alphabet,
             new EncodingOutputHandler() {
                 public void encoded(char encoded) throws IOException {
                     output.write(encoded);
                 }
             },
             input).encodeFinal();
    }
}