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

package jinahya.crypto;


import java.io.InputStream;
import java.io.OutputStream;

import javax.crypto.Cipher;
import javax.crypto.ShortBufferException;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class CipherWrapper extends AbstractCipherWrapper<Cipher> {



    /**
     *
     * @param cipher
     * @param initializer
     */
    public CipherWrapper(final Cipher cipher,
                            final Initializer<Cipher> initializer) {

        super(cipher, initializer);
    }


    @Override
    protected synchronized void process(final Cipher cipher,
                                        final InputStream input,
                                        final OutputStream output)
        throws Exception {

        final byte[] inbuf = getBuffer();
        byte[] outbuf = new byte[inbuf.length];

        int outlen;

        for (int inlen = -1; (inlen = input.read(inbuf)) != -1;) {
            if (inlen == 0) {
                continue;
            }
            while (true) {
                try {
                    outlen = cipher.update(inbuf, 0, inlen, outbuf, 0);
                    if (outlen > 0) {
                        output.write(outbuf, 0, outlen);
                    }
                    break;
                } catch (ShortBufferException sbe) {
                    outbuf = new byte[outbuf.length * 2];
                }
            }
        }

        while (true) {
            try {
                outlen = cipher.doFinal(outbuf, 0);
                if (outlen > 0) {
                    output.write(outbuf, 0, outlen);
                }
                break;
            } catch (ShortBufferException sbe) {
                outbuf = new byte[outbuf.length * 2];
            }
        }

    }


    private Cipher cipher;
    //private Key key;

    private Initializer initializer;
}
