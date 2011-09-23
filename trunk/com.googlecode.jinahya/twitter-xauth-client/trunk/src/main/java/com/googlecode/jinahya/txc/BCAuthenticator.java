/*
 * Copyright 2011 Jin Kwon.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package com.googlecode.jinahya.txc;


import org.bouncycastle.crypto.Mac;
import org.bouncycastle.crypto.digests.SHA1Digest;
import org.bouncycastle.crypto.macs.HMac;
import org.bouncycastle.crypto.params.KeyParameter;


/**
 * Authenticator using BouncyCastle API.
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class BCAuthenticator implements Authenticator {


    /** The Mac instance. */
    private static final Mac MAC = new HMac(new SHA1Digest());


    @Override
    public byte[] authenticate(final byte[] key, final byte[] input)
        throws XTweetException {

        synchronized (MAC) {
            MAC.reset();
            MAC.init(new KeyParameter(key));
            MAC.update(input, 0, input.length);
            final byte[] output = new byte[MAC.getMacSize()];
            MAC.doFinal(output, 0);
            return output;
        }
    }
}
