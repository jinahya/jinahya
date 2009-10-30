package jinahya.rfc4648;


import java.io.ByteArrayInputStream;
import java.io.CharArrayWriter;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;

import jinahya.bitio.BitInput;
import jinahya.bitio.BitInputImpl;


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

        this.input = new BitInputImpl(input);
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
