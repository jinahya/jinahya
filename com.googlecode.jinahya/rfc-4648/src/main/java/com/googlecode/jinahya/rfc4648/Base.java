/*
 * Copyright 2011 Jin Kwon.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package com.googlecode.jinahya.rfc4648;


import com.googlecode.jinahya.io.BitInput;
import com.googlecode.jinahya.io.BitInput.StreamInput;
import com.googlecode.jinahya.io.BitOutput;
import com.googlecode.jinahya.io.BitOutput.StreamOutput;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Abstract Base class.
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public abstract class Base {


    /**
     * logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(Base.class);


    /**
     * Default pad character.
     */
    private static final char PAD = '=';


    /**
     * MAGIC NUMBER: OCTET SIZE.
     */
    private static final int OCTET_SIZE = 8;


    /**
     * MAGIC NUMBER: ASCII SIZE.
     */
    private static final int ASCII_SIZE = 128;


    /**
     * MAGIC NUMBER: SMALLEST VISIBLE ASCII.
     */
    private static final int SMALLEST_VISIBLE_ASCII = 0x21; // 33


    /**
     * Returns the Least Common Multiple value for given two operands.
     *
     * @param a the first operand
     * @param b the second operand
     *
     * @return calculated least common multiple
     */
    private static int lcm(final int a, final int b) {

        if (a < 0) {
            throw new IllegalArgumentException("a(" + a + ") < 0");
        }

        if (b < 0) {
            throw new IllegalArgumentException("b(" + b + ") < 0");
        }

        return (a / gcd(a, b)) * b;
    }


    /**
     * Returns the Greatest Common Divisor for given two operands.
     *
     * @param a the first operand
     * @param b the second operand
     *
     * @return calculated greatest common devisor
     */
    private static int gcd(final int a, final int b) {

        if (a < 0) {
            throw new IllegalArgumentException("a(" + a + ") < 0");
        }

        if (b < 0) {
            throw new IllegalArgumentException("b(" + b + ") < 0");
        }

        if (a < b) {
            return gcd(b, a);
        }

        if (b == 0) {
            return a;
        }

        return gcd(b, a % b);
    }


    /**
     * Create a new instance.
     *
     * @param alphabet alphabet to be used
     * @param padding flag for padding
     */
    protected Base(final byte[] alphabet, final boolean padding) {

        super();

        if (alphabet == null) {
            throw new NullPointerException("alphabet");
        }

        if (alphabet.length == 0) {
            throw new IllegalArgumentException("empty alphabet");
        }

        encode = alphabet;

        decode = new byte[ASCII_SIZE - SMALLEST_VISIBLE_ASCII + 1];
        for (int i = 0; i < decode.length; i++) {
            decode[i] = -1;
        }
        boolean lower_ = false;
        for (byte i = 0; i < encode.length; i++) {
            decode[encode[i] - SMALLEST_VISIBLE_ASCII] = i;
            if (encode[i] >= 0x61 && encode[i] <= 0x7A) { // 'a' <= c <= 'z'
                lower_ = true;
            }
        }
        this.lower = lower_;

        this.padding = padding;

        bitsPerChar = (int) (Math.log(encode.length) / Math.log(2.0d));
        bytesPerWord = lcm(OCTET_SIZE, bitsPerChar) / OCTET_SIZE;
        charsPerWord = bytesPerWord * OCTET_SIZE / bitsPerChar;
    }


    /**
     * Encodes bits from {@code input} and write those encoded characters to
     * {@code output}.
     *
     * @param input binary input
     * @param output character output
     *
     * @throws IOException if an I/O error occurs
     */
    private void encode(final BitInput input, final Writer output)
        throws IOException {

        if (input == null) {
            throw new IllegalArgumentException("input");
        }

        if (output == null) {
            throw new IllegalArgumentException("output");
        }

        outer:
        while (true) {
            for (int i = 0; i < charsPerWord; i++) {
                final int available =
                    OCTET_SIZE - ((bitsPerChar * i) % OCTET_SIZE);
                if (available >= bitsPerChar) {
                    try {
                        final int unsigned = input.readUnsignedInt(bitsPerChar);
                        output.write(encode[unsigned]);
                    } catch (EOFException eofe) { // i == 0
                        break outer;
                    }
                } else { // need next octet
                    final int required = bitsPerChar - available;
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
    }


    /**
     * Encodes bytes from given input stream and writes encoded characters to
     * specified writer.
     *
     * @param input the binary input stream.
     * @param output the character output writer
     *
     * @throws IOException if an I/O error occurs
     */
    public void encode(final InputStream input, final Writer output)
        throws IOException {

        if (input == null) {
            throw new NullPointerException("input");
        }

        if (output == null) {
            throw new NullPointerException("output");
        }

        encode(new BitInput(new StreamInput(input)), output);
    }


    /**
     * Encodes bytes from given input stream and writes encoded bytes to
     * specified output stream.
     *
     * @param input the input stream
     * @param output the output stream
     *
     * @throws IOException if an I/O error occurs.
     */
    public void encode(final InputStream input, final OutputStream output)
        throws IOException {

        if (input == null) {
            throw new NullPointerException("input");
        }

        if (output == null) {
            throw new NullPointerException("output");
        }

        final Writer writer = new OutputStreamWriter(output, "US-ASCII");
        encode(input, writer);
        writer.flush();
    }


    private byte[] encode(final InputStream input, int outputCapacity)
        throws IOException {

        if (input == null) {
            throw new NullPointerException("input");
        }

        final ByteArrayOutputStream output;
        if (outputCapacity > 0) {
            output = new ByteArrayOutputStream(outputCapacity);
        } else {
            output = new ByteArrayOutputStream();
        }

        encode(input, output);
        output.flush();

        return output.toByteArray();
    }


    /**
     * Encodes bytes from given input stream and returns result as an array of
     * bytes.
     *
     * @param input the input stream
     *
     * @return an array of encoded bytes
     *
     * @throws IOException if an I/O error occurs.
     */
    public byte[] encode(final InputStream input) throws IOException {

        return encode(input, -1);
    }


    /**
     * Encodes given bytes and returns result.
     *
     * @param input the input bytes
     *
     * @return encoded result
     *
     * @throws IOException if an I/O error occurs.
     */
    public byte[] encode(final byte[] input) throws IOException {

        if (input == null) {
            throw new NullPointerException("input");
        }

        int wordCount = input.length / bytesPerWord;
        if (input.length % bytesPerWord > 0) {
            wordCount++;
        }
        final int outputCapacity = wordCount * charsPerWord;

        final byte[] output =
            encode(new ByteArrayInputStream(input), outputCapacity);

        if (output.length > outputCapacity) {
            LOGGER.debug("input.length: {}", Integer.valueOf(input.length));
            LOGGER.debug("bytes/word: {}", Integer.valueOf(bytesPerWord));
            LOGGER.debug("chars/word: {}", Integer.valueOf(charsPerWord));
            LOGGER.debug("encoded.output.length({}) > outputCapacity({})",
                         Integer.valueOf(output.length),
                         Integer.valueOf(outputCapacity));
        }

        return output;
    }


    /**
     * Encodes given bytes and returns the result as a string.
     *
     * @param input the input bytes.
     * @param outputCharset the charset name to encode output string.
     *
     * @return the result string
     *
     * @throws IOException if an I/O error occurs.
     */
    public String encodeToString(final byte[] input, final String outputCharset)
        throws IOException {

        if (outputCharset == null) {
            throw new NullPointerException("outputCharset");
        }

        return new String(encode(input), outputCharset);
    }


    public byte[] encode(final String input, final String inputCharset)
        throws IOException {

        if (input == null) {
            throw new NullPointerException("input");
        }

        if (inputCharset == null) {
            throw new NullPointerException("inputCharset");
        }

        return encode(input.getBytes(inputCharset));
    }


    public String encodeToString(final String input, final String inputCharset,
                                 final String outputCharset)
        throws IOException {

        if (outputCharset == null) {
            throw new NullPointerException("outputCharset");
        }

        return new String(encode(input, inputCharset), outputCharset);
    }


    /**
     * Decodes characters from {@code input} and writes decoded binary to
     * {@code output}.
     *
     * @param input character input
     * @param output binary output
     *
     * @throws IOException if an I/O error occurs
     */
    private void decode(final Reader input, final BitOutput output)
        throws IOException {

        if (input == null) {
            throw new NullPointerException("input");
        }

        if (output == null) {
            throw new NullPointerException("output");
        }

        outer:
        while (true) {
            int c;
            for (int i = 0; i < charsPerWord; i++) {
                c = input.read();
                if (c == -1) { // end of stream
                    if (i == 0) { // first character in a word; ok
                        break outer;
                    }
                    if (((i * bitsPerChar) % OCTET_SIZE) >= bitsPerChar) {
                        throw new EOFException("not finished properly");
                    }
                    if (!padding) {
                        break outer;
                    }
                    throw new EOFException("not finished properly");
                } else if (c == PAD) {
                    if (!padding) {
                        throw new IOException("bad padding; no pads allowed");
                    }
                    if (i == 0) { // first character in a word
                        throw new IOException("bad padding");
                    }
                    if (((i * bitsPerChar) % OCTET_SIZE) >= bitsPerChar) {
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
                    if (!lower && (c >= 0x61 && c <= 0x7A)) { // 'a' <= c <= 'z'
                        c -= 0x20; // to upper
                    }
                    final int value = decode[c - SMALLEST_VISIBLE_ASCII];
                    if (value == -1) {
                        throw new IOException("bad character: " + (char) c);
                    }
                    output.writeUnsignedInt(bitsPerChar, value);
                }
            }
        }
    }


    /**
     * Decodes characters from {@code input} and writes decoded bytes to
     * {@code output}.
     *
     * @param input character input
     * @param output binary output
     *
     * @throws IOException if an I/O error occurs
     */
    public void decode(final Reader input, final OutputStream output)
        throws IOException {

        if (output == null) {
            throw new NullPointerException("outpute");
        }

        decode(input, new BitOutput(new StreamOutput(output)));
    }


    /**
     * Decodes characters from {@code input} and writes decoded octets to
     * {@code output}.
     *
     * @param input input
     * @param output output
     *
     * @throws IOException if an I/O error occurs.
     */
    public void decode(final InputStream input, final OutputStream output)
        throws IOException {

        if (input == null) {
            throw new NullPointerException("input");
        }

        final Reader reader = new InputStreamReader(input, "US-ASCII");

        decode(reader, output);
    }


    private byte[] decode(final InputStream input, final int outputCapacity)
        throws IOException {

        if (input == null) {
            throw new NullPointerException("input");
        }

        final ByteArrayOutputStream output;
        if (outputCapacity >= 0) {
            output = new ByteArrayOutputStream(outputCapacity);
        } else {
            output = new ByteArrayOutputStream();
        }

        decode(input, output);
        output.flush();

        return output.toByteArray();
    }


    /**
     * Decodes characters from {@code input} and return decoded bytes.
     *
     * @param input character input
     *
     * @return decoded bytes
     *
     * @throws IOException if an I/O error occurs.
     */
    public byte[] decode(final InputStream input) throws IOException {

        return decode(input, -1);
    }


    /**
     * Decodes given encoded bytes and return decoded bytes.
     *
     * @param input the encoded bytes to decode
     *
     * @return the decoded bytes
     *
     * @throws IOException if an I/O error occurs.
     */
    public byte[] decode(final byte[] input) throws IOException {

        if (input == null) {
            throw new NullPointerException("input");
        }

        final int outputCapacity = input.length * bytesPerWord / charsPerWord;

        final byte[] output =
            decode(new ByteArrayInputStream(input), outputCapacity);
        if (output.length > outputCapacity) {
            LOGGER.debug("input.length: {}", Integer.valueOf(input.length));
            LOGGER.debug("bytes/word: {}", Integer.valueOf(bytesPerWord));
            LOGGER.debug("chars/word: {}", Integer.valueOf(charsPerWord));
            LOGGER.debug("decoded.output.length({}) > outputCapacity({})",
                         Integer.valueOf(output.length),
                         Integer.valueOf(outputCapacity));
        }

        return output;
    }


    public String decodeToString(final byte[] input, final String outputCharset)
        throws IOException {

        if (outputCharset == null) {
            throw new NullPointerException("outputCharset");
        }

        return new String(decode(input), outputCharset);
    }


    public byte[] decode(final String input, final String inputCharset)
        throws IOException {

        if (input == null) {
            throw new NullPointerException("input");
        }

        if (inputCharset == null) {
            throw new NullPointerException("inputCharset");
        }

        return decode(input.getBytes(inputCharset));
    }


    /**
     * Decodes given encoded input string.
     *
     * @param input the encoded input string.
     * @param inputCharset the charset name to decode input string.
     * @param outputCharset the charset name to encode output string.
     *
     * @return a decoded output as a string
     *
     * @throws IOException if an I/O error occurs.
     */
    public String decodeToString(final String input,
                                 final String inputCharset,
                                 final String outputCharset)
        throws IOException {

        if (outputCharset == null) {
            throw new NullPointerException("outputCharset");
        }

        return new String(decode(input, inputCharset), outputCharset);
    }


    /**
     * characters for encoding.
     */
    private final byte[] encode;


    /**
     * characters for decoding.
     */
    private final byte[] decode;


    /**
     * flag for lower-case characters only.
     */
    private final boolean lower;


    /**
     * flag for padding.
     */
    private final boolean padding;


    /**
     * number of bits per character.
     */
    private final int bitsPerChar;


    /**
     * number of bytes per word.
     */
    private final int bytesPerWord;


    /**
     * number of characters per word.
     */
    private final int charsPerWord;


}
