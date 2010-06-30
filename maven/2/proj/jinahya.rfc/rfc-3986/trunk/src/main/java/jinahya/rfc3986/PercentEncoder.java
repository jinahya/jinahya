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
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.Writer;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class PercentEncoder {


    /**
     *
     * @param s
     * @return
     * @throws IOException
     */
    public static String encode(final String s) throws IOException {
        final StringWriter out = new StringWriter();
        try {
            encode(new ByteArrayInputStream(s.getBytes("UTF-8")), out);
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
    public static void encode(final InputStream in, final OutputStream out)
        throws IOException {

        final Writer writer = new OutputStreamWriter(out, "UTF-8");
        try {
            encode(in, writer);
            writer.flush();
        } finally {
            //writer.close();
        }
    }


    /**
     * 
     * @param in
     * @param out
     * @throws IOException
     */
    public static void encode(final InputStream in, final Writer out)
        throws IOException {

        if (in == null) {
            throw new IllegalArgumentException(
                "param:0:" + InputStream.class + " is null");
        }

        if (out == null) {
            throw new IllegalArgumentException(
                "param:1:" + Writer.class + " is null");
        }

        for (int b = -1; (b = in.read()) != -1; ) {

            if (b >= 0x30 && b <= 0x39) { // digit
                out.write(b);
                continue;
            }

            if (b >= 0x41 && b <= 0x5A) { // upper case alpha
                out.write(b);
                continue;
            }

            if (b >= 0x61 && b <= 0x7A) { // lower case alpha
                out.write(b);
                continue;
            }

            if (b == 0x2D || b == 0x5F || b == 0x2E || b == 0x7E) { // - _ . ~
                out.write(b);
                continue;
            }

            out.write(0x25);     // '%'
            if (b < 0x10) {
                out.write(0x30); // '0'
            }
            out.write(Integer.toHexString(b).toUpperCase());
        }
    }
}

