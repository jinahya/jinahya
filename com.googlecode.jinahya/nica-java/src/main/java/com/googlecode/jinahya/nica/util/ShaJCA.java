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


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class ShaJCA extends Sha {


    /**
     * Hash algorithm.
     */
    private static final String ALGORITHM = "SHA-1";


    /**
     * Creates a new instance.
     */
    public ShaJCA() {
        super();

        try {
            messageDigest = MessageDigest.getInstance(ALGORITHM);
        } catch (NoSuchAlgorithmException nsae) {
            throw new RuntimeException(
                "\"" + ALGORITHM + "\" not available?", nsae);
        }
    }


    //@Override // commented for pre5
    public byte[] hash(final byte[] data) {

        if (data == null) {
            throw new NullPointerException("dmeata");
        }

//        messageDigest.reset();
        messageDigest.update(data);

        return messageDigest.digest(); // reset

//        return messageDigest.digest(data); // works the same.
    }


    /**
     * messageDigest.
     */
    private final MessageDigest messageDigest;


}
