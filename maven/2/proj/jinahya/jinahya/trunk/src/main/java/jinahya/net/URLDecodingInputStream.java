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


import java.io.EOFException;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class URLDecodingInputStream extends FilterInputStream {


    public URLDecodingInputStream(final InputStream in) {
        super(in);
    }


    @Override
    public int read() throws IOException {

        final int b = super.read();
        if (b == -1) {
            return b;
        }
                
        if ((b >= 0x30 && b <= 0x39) // digit
            || (b >= 0x41 && b <= 0x5A) // upper case alpha
            || (b >= 0x61 && b <= 0x7A) // lower case alpha
            || (b == 0x2E || b == 0x2D || b == 0x2A || b == 0x5F)) { // . - * _
            return b;
        } else if (b == 0x2B) { // +
            return 0x20; // SPACE
        } else if (b == 0x25) { // %
            return ((decode(super.read()) << 4)
                    | (decode(super.read()) & 0x0F));
        } else {
            throw new IOException("illegal octet: " + b);
        }
    }


    private int decode(final int i) throws EOFException {
        if (i == -1) {
            throw new EOFException("eof");
        }
        return i - (i >= 0x41 ? 0x37 : 0x30);
    }


    @Override
    public int read(byte[] b) throws IOException {
        return read(b, 0, b.length);
    }


    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        for (int i = 0; i < len; i++) {
            if ((b[off + i] = (byte)read()) == -1) {
                if (i == 0)  {
                    return -1;
                }
                return i;
            }
        }
        return len;
    }

}
