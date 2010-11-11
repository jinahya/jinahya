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


import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


/**
 *
 * @author <a href="mailto:support@minigate.net">Minigate Co., Ltd.</a>
 */
public class PercentDecoder {


    /**
     * 
     * @param input
     * @param output
     * @throws IOException
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
            output.write(decode(b, input));
        }
    }


    static int decode(final int b, final InputStream input)
        throws IOException {

        if (b >= 0x30 && b <= 0x39) { // digit
            return b;
        } else if (b >= 0x41 && b <= 0x5A) { // upper case alpha
            return b;
        } else if (b >= 0x61 && b <= 0x7A) { // lower case alpha
            return b;
        } else if (b == 0x2D || b == 0x5F || b == 0x2E || b == 0x7E) {
            // - _ . ~
            return b;
        } else if (b == 0x25) { // '%'
            return ((decode(input.read()) << 4)
                    | (decode(input.read()) & 0x0F));
        } else {
            throw new IOException("illegal octet: " + b);
        }
    }


    private static int decode(final int i) throws EOFException {
        if (i == -1) {
            throw new EOFException("eof");
        }
        return i - (i >= 0x41 ? 0x37 : 0x30);
    }
}
