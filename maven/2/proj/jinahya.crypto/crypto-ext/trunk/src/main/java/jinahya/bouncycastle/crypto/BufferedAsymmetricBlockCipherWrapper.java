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

package jinahya.bouncycastle.crypto;


import java.io.InputStream;
import java.io.OutputStream;

import jinahya.crypto.AbstractCipherWrapper;

import org.bouncycastle.crypto.BufferedAsymmetricBlockCipher;
import org.bouncycastle.crypto.CipherParameters;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class BufferedAsymmetricBlockCipherWrapper
    extends AbstractCipherWrapper<BufferedAsymmetricBlockCipher> {


    /**
     * 
     * @param cipher
     * @param params
     */
    public BufferedAsymmetricBlockCipherWrapper(
        final BufferedAsymmetricBlockCipher cipher,
        final CipherParameters params) {

        this(cipher, new Initializer<BufferedAsymmetricBlockCipher>() {
            @Override
            public void init(final BufferedAsymmetricBlockCipher cipher,
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
    public BufferedAsymmetricBlockCipherWrapper(
        final BufferedAsymmetricBlockCipher cipher,
        final Initializer<BufferedAsymmetricBlockCipher> initializer) {

        super(cipher, initializer);
    }


    @Override
    protected void process(final BufferedAsymmetricBlockCipher cipher,
                           final InputStream in, final OutputStream out)
        throws Exception {

        final byte[] inbuf = getBuffer();

        for (int inlen = -1; true; ) {
            inlen = cipher.getInputBlockSize() - cipher.getBufferPosition();
            if (inlen == 0) {
                out.write(cipher.doFinal());
                continue;
            }
            if (inlen > inbuf.length) {
                inlen = inbuf.length;
            }
            inlen = in.read(inbuf, 0, inlen);
            if (inlen == -1) {
                break;
            }
            if (inlen > 0) {
                cipher.processBytes(inbuf, 0, inlen);
            }
        }
    }
}
