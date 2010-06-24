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

package jinahya.util;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.io.UTFDataFormatException;
import java.util.Random;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public final class ModifiedUTF8 {


    /**
     * Generates a string.
     *
     * @param length the number of chars to be generated
     * @param random the random to be used
     * @param acceptor the char acceptor or null
     * @return a genearated string
     */
    public static String generate(final int length, final Random random,
                                  final Acceptor acceptor) {

        if (length <= 0) {
            throw new IllegalArgumentException(
                "param:0:" + Integer.TYPE + " is null");
        }

        if (random == null) {
            throw new IllegalArgumentException(
                "param:1:" + Random.class + " is null");
        }

        /*
        if (acceptor == null) {
            throw new IllegalArgumentException(
                "param:2:" + Acceptor.class + " is null");
        }
         */

        final StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < length;) {
            final char c = generate(random);
            if (acceptor == null || acceptor.accept(c)) {
                buffer.append(c);
                i++;
            }
        }
        return buffer.toString();
    }


    /**
     *
     * @param length the number of characters to generate
     * @param random the random to be used
     * @param acceptor the char acceptor or null
     * @param writer the writer to which generated chars be written
     */
    public static void generate(final int length, final Random random,
                                final Acceptor acceptor, final Writer writer)
        throws IOException {

        if (length <= 0) {
            throw new IllegalArgumentException(
                "param:0:" + Integer.TYPE + " is null");
        }

        if (random == null) {
            throw new IllegalArgumentException(
                "param:1:" + Random.class + " is null");
        }

        /*
        if (acceptor == null) {
            throw new IllegalArgumentException(
                "param:2:" + Acceptor.class + " is null");
        }
         */

        if (writer == null) {
            throw new IllegalArgumentException(
                "param:3:" + Writer.class + " is null");
        }

        for (int i = 0; i < length;) {
            final char c = generate(random);
            if (acceptor == null || acceptor.accept(c)) {
                writer.write(c);
                i++;
            }
        }
    }


    /**
     * Generates a single char.
     *
     * @param acceptor the char acceptor
     * @return a generated char
     */
    public static char generate(final Random random) {

        if (random == null) {
            throw new IllegalArgumentException(
                "param:0:" + Random.class + " is null");
        }

        final int n = random.nextInt(3);

        if (n == 0) {
            final int a = random.nextInt(128);
            return (char) a;
        } else if (n == 1) {
            final int a = random.nextInt(32) + 192;
            final int b = random.nextInt(64) + 128;
            return (char) (((a & 0x1F) << 6) | (b & 0x3F));
        } else { // n == 2
            final int a = random.nextInt(16) + 224;
            final int b = random.nextInt(64) + 128;
            final int c = random.nextInt(64) + 128;
            return (char) (((a & 0x0F) << 12) | ((b & 0x3F) << 6) | (c & 0x3F));
        }
    }



    /**
     * The interface for the character accepting function.
     */
    public static interface Acceptor {

        /**
         * Check whether the specified <code>ch</code> is acceptable or not.
         *
         * @param c the char to be checked.
         * @return true if given <code>ch</code> is acceptable, false otherwise.
         */
        boolean accept(char c);
    }


    /**
     *
     * @param in
     * @return
     * @throws IOException
     */
    public static byte[] encode(final Reader in) throws IOException {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            encode(in, out);
            out.flush();
            return out.toByteArray();
        } finally {
            out.close();
        }
    }


    /**
     *
     * @param in
     * @return
     * @throws IOException
     */
    public static byte[] encode(final String string) throws IOException {
        final Reader in = new StringReader(string);
        try {
            return encode(in);
        } finally {
            in.close();
        }
    }


    /**
     *
     * @param in
     * @param out
     * @throws IOException
     */
    public static void encode(final Reader in, final OutputStream out)
        throws IOException {

        for (int c = -1; (c = in.read()) != -1;) {
            if (c >= '\u0001' && c <= '\u007F') {
                out.write(c);
            } else if (c == '\u0000' || c <= '\u07FF') {
                out.write(0xC0 | (0x1F & (c >> 6)));
                out.write(0x80 | (0x3F & c));
            } else { // if (c <= '\uFFFF') {
                out.write(0xE0 | (0x0F & (c >> 12)));
                out.write(0x80 | (0x3F & (c >> 6)));
                out.write(0x80 | (0x3F & c));
            }
        }
    }

    /**
     *
     * @param bytes
     * @return
     * @throws IOException
     */
    public static String decode(final byte[] bytes) throws IOException {
        final InputStream in = new ByteArrayInputStream(bytes);
        try {
            return decode(in);
        } finally {
           in.close();
        }
    }


    /**
     *
     * @param in
     * @return
     * @throws IOException
     */
    public static String decode(final InputStream in) throws IOException {
        final Writer out = new StringWriter();
        try {
            decode(in, out);
            out.flush();
            return out.toString();
        } finally {
            out.close();
        }
    }


    /**
     *
     * @param in
     * @param out
     * @throws IOException
     */
    public static void decode(final InputStream in, final Writer out)
        throws IOException {

        for (int a = -1; (a = in.read()) != -1;) {
            if (a >> 7 == 0) { // 0xxxxxxx
                out.write(a);
            } else if (a >> 5 == 6) { // 011xxxxx
                int b = in.read();
                if (b == -1) {
                    throw new UTFDataFormatException("eof");
                }
                if (b >> 6 != 2) { // !10xxxxxx
                    throw new UTFDataFormatException(
                        "illeal b: " + Integer.toHexString(b));
                }
                out.write(((a & 0x1F) << 6) | (b & 0x3F));
            } else if (a >> 4 == 14) { // 1110xxxx
                int b = in.read();
                if (b == -1) {
                    throw new UTFDataFormatException("eof");
                }
                if (b >> 6 != 2) { // !10xxxxxx
                    throw new UTFDataFormatException(
                        "illeal b: " + Integer.toHexString(b));
                }
                int c = in.read();
                if (c == -1) {
                    throw new UTFDataFormatException("eof");
                }
                if (c >> 6 != 2) { // !10xxxxxx
                    throw new UTFDataFormatException(
                        "illeal c: " + Integer.toHexString(c));
                }

                out.write(((a & 0x0F) << 12) | ((b & 0x3F) << 6) | (c & 0x3F));
            } else {
                throw new UTFDataFormatException(
                    "illeal a: " + Integer.toHexString(a));
            }
        }
    }


    /**
     *
     */
    private ModifiedUTF8() {
        super();
    }
}
