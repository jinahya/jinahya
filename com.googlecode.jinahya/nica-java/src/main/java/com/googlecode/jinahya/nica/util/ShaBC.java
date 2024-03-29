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


import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.digests.SHA1Digest;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class ShaBC extends Sha {


    /**
     * Creates a new instance.
     */
    public ShaBC() {

        super();

        digest = new SHA1Digest();
    }


    //@Override // commented for pre5
    public byte[] hash(final byte[] data) {

        if (data == null) {
            throw new NullPointerException("null data");
        }

        final byte[] hashed = new byte[digest.getDigestSize()];

//        digest.reset();
        digest.update(data, 0, data.length);
        digest.doFinal(hashed, 0); // reset?

        return hashed;
    }


    /**
     * digest.
     */
    private final Digest digest;


}
