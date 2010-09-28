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

package jinahya.crypto.bouncycastle;


import java.io.InputStream;
import java.io.OutputStream;
import jinahya.crypto.AbstractCipherWrapper;
import jinahya.crypto.AbstractCipherWrapper.Initializer;

import org.bouncycastle.crypto.BufferedBlockCipher;
import org.bouncycastle.crypto.CipherParameters;



/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class BufferedBlockCipherWrapper
    extends AbstractCipherWrapper<BufferedBlockCipher> {


    public BufferedBlockCipherWrapper(final BufferedBlockCipher cipher,
                                      final CipherParameters params) {

        this(cipher, new Initializer<BufferedBlockCipher>() {
            @Override
            public void init(final BufferedBlockCipher cipher,
                             final boolean mode) {
                cipher.init(mode, params);
            }
        });
    }


    /**
     *
     * @param cipher
     * @param initializer
     */
    public BufferedBlockCipherWrapper(
        final BufferedBlockCipher cipher,
        final Initializer<BufferedBlockCipher> initializer) {

        super(cipher, initializer);
    }



    @Override
    protected synchronized void process(final BufferedBlockCipher cipher,
                                        final InputStream input,
                                        final OutputStream output)
        throws Exception {

        final byte[] inbuf = buffer();
        final byte[] outbuf = new byte[cipher.getOutputSize(inbuf.length)];

        int outlen;
        for (int inlen = -1; (inlen = input.read(inbuf)) != -1;) {
            outlen = cipher.processBytes(inbuf, 0, inlen, outbuf, 0);
            output.write(outbuf, 0, outlen);
        }

        outlen = cipher.doFinal(outbuf, 0);
        output.write(outbuf, 0, outlen);
    }
}
