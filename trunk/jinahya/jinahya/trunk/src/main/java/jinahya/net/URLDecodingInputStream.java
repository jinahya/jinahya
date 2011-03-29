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


import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class URLDecodingInputStream extends FilterInputStream {


    /**
     * 
     * @param in
     */
    public URLDecodingInputStream(final InputStream in) {
        super(in);
    }


    @Override
    public int read() throws IOException {

        final int b = super.read();
        if (b == -1) {
            return b;
        }

        return URLDecoder.decode(b, in);
    }


    @Override
    public int read(final byte[] b) throws IOException {
        return read(b, 0, b.length);
    }


    @Override
    public int read(final byte[] b, final int off, final int len)
        throws IOException {

        for (int i = 0; i < len; i++) {
            int r = read();
            if (r == -1) {
                return i == 0 ? -1 : i;
            }
            b[off + i] = (byte) r;
        }
        return len;
    }


    @Override
    public long skip(final long n) throws IOException {
        long l = 0;
        for (; l < n; l++) {
            int b = read();
            if (b == -1) {
                break;
            }
        }
        return l;
    }
}
