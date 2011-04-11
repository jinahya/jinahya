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


package jinahya.rfc3986;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class PercentDecoder {


    /**
     * Decodes given <code>string</code>.
     *
     * @param string encoded string
     * @return decoded string
     * @throws IOException if an I/O error occurs.
     */
    public static String decode(final String string) throws IOException {
        return decode(string, "UTF-8");
    }


    /**
     * Decodes given <code>string</code>.
     *
     * @param string encoded string
     * @param encoding output string encoding
     * @return decoded string
     * @throws IOException if an I/O error occurs
     */
    public static String decode(final String string, final String encoding)
        throws IOException {

        return new String(decode(string.getBytes("US-ASCII")), encoding);
    }


    /**
     * Decodes given <code>bytes</code>.
     *
     * @param bytes bytes to decode
     * @return decoded output
     * @throws IOException if an I/O error occurs.
     */
    public static byte[] decode(final byte[] bytes) throws IOException {

        if (bytes == null) {
            throw new IllegalArgumentException("null bytes");
        }

        final ByteArrayOutputStream output = new ByteArrayOutputStream();
        decode(new ByteArrayInputStream(bytes), output);
        output.flush();
        return output.toByteArray();
    }


    /**
     * Decodes from <code>input</code> to <code>output</code>.
     *
     * @param input input
     * @param output output
     * @throws IOException if an I/O error occurs.
     */
    public static void decode(final InputStream input,
                              final OutputStream output)
        throws IOException {

        if (input == null) {
            throw new IllegalArgumentException("null input");
        }

        if (output == null) {
            throw new IllegalArgumentException("null output");
        }

        for (int b = -1; (b = input.read()) != -1;) {

            if ((b >= 0x30 && b <= 0x39) // digit
                || (b >= 0x41 && b <= 0x5A) // upper case alpha
                || (b >= 0x61 && b <= 0x7A) // lower case alpha
                || (b == 0x2D // -
                    || b == 0x5F // _
                    || b == 0x2E // .
                    || b == 0x7E)) { // ~
                output.write(b);
            } else if (b == 0x25) { // '%'
                int high = input.read();
                if (high == -1) {
                    throw new EOFException("eof");
                }
                int row = input.read();
                if (row == -1) {
                    throw new EOFException("eof");
                }
                output.write((atoi(high) << 4) | atoi(row));
            } else {
                throw new IOException("illegal octet: " + b);
            }
        }
    }


    static int atoi(final int i) throws EOFException {
        return i - (i >= 0x41 ? 0x37 : 0x30);
    }


    /**
     * Creates a new instance.
     */
    protected PercentDecoder() {
        super();
    }
}
