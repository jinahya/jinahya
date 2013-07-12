/*
 * Copyright 2012 Jin Kwon <jinahya at gmail.com>.
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


package com.googlecode.jinahya.nica.util;


import org.bouncycastle.crypto.digests.SHA1Digest;
import org.bouncycastle.crypto.macs.HMac;
import org.bouncycastle.crypto.params.KeyParameter;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class HacBC extends Hac {


    /**
     * Creates a new instance.
     *
     * @param key encryption key
     */
    public HacBC(final byte[] key) {

        super();

        if (key == null) {
            throw new NullPointerException("key");
        }

        if (key.length != Aes.KEY_SIZE_IN_BYTES) {
            throw new IllegalArgumentException(
                "key.length(" + key.length + ") != " + Aes.KEY_SIZE_IN_BYTES);
        }

        mac = new HMac(new SHA1Digest());
        mac.init(new KeyParameter(key));
    }


    //@Override // commented for pre5
    public byte[] authenticate(final byte[] message) {

        if (message == null) {
            throw new NullPointerException("message");
        }

        final byte[] output = new byte[mac.getMacSize()];

        mac.reset();
        mac.update(message, 0, message.length);
        mac.doFinal(output, 0);

        return output;
    }


    /**
     * mac.
     */
    private final HMac mac;


}
