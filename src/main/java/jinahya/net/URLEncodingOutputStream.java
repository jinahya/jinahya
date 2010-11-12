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

package jinahya.net;


import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class URLEncodingOutputStream extends FilterOutputStream {


    /**
     * 
     * @param out
     */
    public URLEncodingOutputStream(final OutputStream out) {
        super(out);
    }


    @Override
    public void write(final int b) throws IOException {

        if ((b >= 0x30 && b <= 0x39) // digit
            || (b >= 0x41 && b <= 0x5A) // upper case alpha
            || (b >= 0x61 && b <= 0x7A) // lower case alpha
            || (b == 0x2E || b == 0x2D || b == 0x2A || b == 0x5F)) {
            // . - * _
            super.write(b);
        } else if (b == 0x20) {
            super.write(0x2B);
        } else {
            super.write(0x25);
            super.write(itoa(b >> 4));
            super.write(itoa(b & 0xF));
        }
    }


    private int itoa(final int i) {
        return i + (i < 0x0A ? 0x30 : 0x37);
    }
}
