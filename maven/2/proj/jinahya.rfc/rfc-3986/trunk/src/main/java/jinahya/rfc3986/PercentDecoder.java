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
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class PercentDecoder {


    /**
     *
     * @param in
     * @return
     * @throws IOException
     */
    public static byte[] decode(final byte[] in) throws IOException {

        if (in == null) {
            throw new IllegalArgumentException(
                "param:0:" + byte[].class + ": is null");
        }

        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            decode(new ByteArrayInputStream(in), out);
            out.flush();
            return out.toByteArray();
        } finally {
            out.close();
        }
    }


    public static void decode(final byte[] in, final OutputStream out)
        throws IOException {

        if (in == null) {
            throw new IllegalArgumentException(
                "param:0:" + byte[].class + ": is null");
        }

        if (out == null) {
            throw new IllegalArgumentException(
                "param:0:" + OutputStream.class + ": is null");
        }

        decode(new ByteArrayInputStream(in), out);
    }



    /**
     *
     * @param in
     * @return
     * @throws IOException
     */
    public static String decode(final String in) throws IOException {

        if (in == null) {
            throw new IllegalArgumentException(
                "param:0:" + String.class + ": is null");
        }

        return new String(decode(in.getBytes("US-ASCII")), "UTF-8");
    }


    /**
     *
     * @param in
     * @param out
     * @throws IOException
     */
    public static void decode(final String in, final OutputStream out)
        throws IOException {

        if (in == null) {
            throw new IllegalArgumentException(
                "param:0:" + String.class + ": is null");
        }

        if (out == null) {
            throw new IllegalArgumentException(
                "param:0:" + OutputStream.class + ": is null");
        }

        decode(in.getBytes("US-ASCII"), out);
    }


    /**
     *
     * @param in
     * @param out
     * @throws IOException
     */
    public static void decode(final InputStream in, final OutputStream out)
        throws IOException {

        if (in == null) {
            throw new IllegalArgumentException(
                "param:0:" + InputStream.class + ": is null");
        }

        if (out == null) {
            throw new IllegalArgumentException(
                "param:1:" + OutputStream.class + ": is null");
        }

        final Reader reader = new InputStreamReader(in, "US-ASCII");
        try {
            decode(reader, out);
        } finally {
            reader.close();
        }
    }


    /**
     *
     * @param in
     * @param out
     * @throws IOException
     */
    public static void decode(final Reader in, final OutputStream out)
        throws IOException {

        if (in == null) {
            throw new IllegalArgumentException(
                "param:0:" + Reader.class + ": is null");
        }

        if (out == null) {
            throw new IllegalArgumentException(
                "param:1:" + OutputStream.class + ": is null");
        }

        final char[] ch = new char[2];

        for (int c = -1; (c = in.read()) != -1; ) {

            if (c >= 0x30 && c <= 0x39) { // digit
                out.write(c);
                continue;
            }

            if (c >= 0x41 && c <= 0x5A) { // upper case alpha
                out.write(c);
                continue;
            }

            if (c >= 0x61 && c <= 0x7A) { // lower case alpha
                out.write(c);
                continue;
            }

            if (c == 0x2D || c == 0x5F || c == 0x2E || c == 0x7E) { // - _ . ~
                out.write(c);
                continue;
            }

            if (c == 0x25) { // '%'

                if ((ch[0] = (char) in.read()) == -1) {
                    throw new EOFException("eof");
                }

                if ((ch[1] = (char) in.read()) == -1) {
                    throw new EOFException("eof");
                }

                out.write(Integer.parseInt(new String(ch), 16));
                continue;
            }

            throw new IOException("illegal octet: " + (char) c);
        }
    }
}

