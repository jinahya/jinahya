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


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


/**
 *
 * @author <a href="jinahya@gmail.com">Jin Kwon</a>
 */
public class PercentEncoder {


    /**
     * 
     * @param input
     * @param output
     * @throws IOException
     */
    public static void encode(final InputStream input,
                              final OutputStream output)
        throws IOException {

        if (input == null) {
            throw new IllegalArgumentException("null input");
        }

        if (output == null) {
            throw new IllegalArgumentException("null output");
        }

        for (int b = -1; (b = input.read()) != -1; ) {
            encode(b, output);
        }
    }


    static void encode(final int input, final OutputStream output)
        throws IOException {

        if (input >= 0x30 && input <= 0x39) { // digit
            output.write(input);
        } else if (input >= 0x41 && input <= 0x5A) { // upper case alpha
            output.write(input);
        } else if (input >= 0x61 && input <= 0x7A) { // lower case alpha
            output.write(input);
        } else if (input == 0x2D || input == 0x5F || input == 0x2E
            || input == 0x7E) {
            // - _ . ~
            output.write(input);
        } else {
            output.write(0x25);
            output.write(encode(input >> 4));
            output.write(encode(input & 0xF));
        }
    }


    private static int encode(final int i) {
        return i + (i < 0x0A ? 0x30 : 0x37);
    }
}
