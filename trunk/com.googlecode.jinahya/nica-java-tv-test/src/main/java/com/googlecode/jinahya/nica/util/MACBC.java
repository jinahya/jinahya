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


import org.bouncycastle.crypto.digests.SHA512Digest;
import org.bouncycastle.crypto.macs.HMac;
import org.bouncycastle.crypto.params.KeyParameter;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class MACBC extends MAC {


    public MACBC(final byte[] key) {
        super();

        if (key == null) {
            throw new IllegalArgumentException("null key");
        }

        if (key.length != AES.KEY_SIZE_IN_BYTES) {
            throw new IllegalArgumentException(
                "key.length(" + key.length + ") != " + AES.KEY_SIZE_IN_BYTES);
        }

//        this.key = key.clone();
        keyParameter = new KeyParameter(key);
    }


    public byte[] authenticate(byte[] message) {

        if (message == null) {
            throw new IllegalArgumentException("null message");
        }

        final HMac mac = new HMac(new SHA512Digest());
//        mac.init(new KeyParameter(key));
        mac.init(keyParameter);
        mac.update(message, 0, message.length);

        final byte[] authenticated = new byte[mac.getMacSize()];
        mac.doFinal(authenticated, 0);

        return authenticated;
    }


    /**
     * key.
     */
//    private final byte[] key;
    /**
     * keyParameter.
     */
    private final KeyParameter keyParameter;


}

